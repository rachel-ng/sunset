package com.mergbw.core.ble;

import android.bluetooth.BluetoothGatt;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleMtuChangedCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.mergbw.core.Constants;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.utils.AppUtils;
import com.mergbw.core.utils.MeRGBWLog;
import com.mergbw.core.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;

public class RGBDeviceManager {
    private static final int DEFAULT_MTU = 20;
    private boolean isUpgrade = false;
    private BleManager mBleManager;
    /* access modifiers changed from: private */
    public final Map<String, SendCommandQueue> mDataQueue = new HashMap();
    /* access modifiers changed from: private */
    public final Map<String, BleCallback> mListeners = new HashMap();

    public static RGBDeviceManager getInstance() {
        return BleManagerHolder.sBleManager;
    }

    private static class BleManagerHolder {
        /* access modifiers changed from: private */
        public static final RGBDeviceManager sBleManager = new RGBDeviceManager();

        private BleManagerHolder() {
        }
    }

    public void init() {
        BleManager instance = BleManager.getInstance();
        this.mBleManager = instance;
        instance.init(AppUtils.getApp());
    }

    private class sendThread extends Thread {
        private sendThread() {
        }

        public void run() {
            super.run();
            while (true) {
                CommandData take = TotalCommandDataQueue.getInstance().take();
                if (take != null) {
                    RGBDeviceManager.this.sendData(take.getBleDevice(), take.getData());
                }
            }
        }
    }

    public void setCallback(String str, BleCallback bleCallback) {
        this.mListeners.put(str, bleCallback);
    }

    public void removeCallback(String str) {
        this.mListeners.remove(str);
    }

    public void connectDevice(DeviceInfoBean deviceInfoBean) {
        MeRGBWLog.i("connectDevice: " + deviceInfoBean.getDeviceName() + " " + deviceInfoBean.getDeviceMac());
        connectDevice(new BleDevice(this.mBleManager.getBluetoothAdapter().getRemoteDevice(deviceInfoBean.getDeviceMac())));
    }

    public void connectDevice(String str) {
        MeRGBWLog.i("connectDevice: " + str);
        connectDevice(new BleDevice(this.mBleManager.getBluetoothAdapter().getRemoteDevice(str)));
    }

    public void connectDevice(BleDevice bleDevice) {
        if (this.mBleManager.isConnected(bleDevice)) {
            MeRGBWLog.i("connectDevice: " + bleDevice.getMac() + " Already Connected!");
            for (BleCallback onAlreadyConnected : this.mListeners.values()) {
                onAlreadyConnected.onAlreadyConnected(bleDevice);
            }
            return;
        }
        this.mBleManager.connect(bleDevice, (BleGattCallback) new BleGattCallback() {
            public void onStartConnect() {
            }

            public void onConnectFail(BleDevice bleDevice, BleException bleException) {
                MeRGBWLog.i("connectDevice: " + bleDevice.getMac() + " onConnectFail: " + bleException.toString());
                for (BleCallback onConnectedFailed : RGBDeviceManager.this.mListeners.values()) {
                    onConnectedFailed.onConnectedFailed(bleDevice);
                }
            }

            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt bluetoothGatt, int i) {
                MeRGBWLog.i("connectDevice: " + bleDevice.getMac() + " onConnectSuccess");
                for (BleCallback onConnected : RGBDeviceManager.this.mListeners.values()) {
                    onConnected.onConnected(bleDevice);
                }
                RGBDeviceManager.this.setMtu(bleDevice);
                if (!RGBDeviceManager.this.mDataQueue.containsKey(bleDevice.getMac())) {
                    RGBDeviceManager.this.mDataQueue.put(bleDevice.getMac(), new SendCommandQueue());
                }
                MeRGBWLog.i("onConnectSuccess mDataQueue size: " + RGBDeviceManager.this.mDataQueue.size());
            }

            public void onDisConnected(boolean z, BleDevice bleDevice, BluetoothGatt bluetoothGatt, int i) {
                MeRGBWLog.i("onDisConnected: " + bleDevice.getMac() + " onDisConnected");
                for (BleCallback onDisconnected : RGBDeviceManager.this.mListeners.values()) {
                    onDisconnected.onDisconnected(bleDevice);
                }
                SendCommandQueue sendCommandQueue = (SendCommandQueue) RGBDeviceManager.this.mDataQueue.get(bleDevice.getMac());
                if (sendCommandQueue != null) {
                    sendCommandQueue.stop();
                    RGBDeviceManager.this.mDataQueue.remove(bleDevice.getMac());
                }
                MeRGBWLog.i("onConnectSuccess mDataQueue size: " + RGBDeviceManager.this.mDataQueue.size());
            }
        });
    }

    public boolean isConnected(String str) {
        return this.mBleManager.isConnected(new BleDevice(this.mBleManager.getBluetoothAdapter().getRemoteDevice(str)));
    }

    public void disconnectDevice(DeviceInfoBean deviceInfoBean) {
        this.mBleManager.disconnect(new BleDevice(this.mBleManager.getBluetoothAdapter().getRemoteDevice(deviceInfoBean.getDeviceMac())));
    }

    public void disconnectDevice(BleDevice bleDevice) {
        this.mBleManager.disconnect(bleDevice);
    }

    public void disconnectAll() {
        this.mBleManager.disconnectAllDevice();
    }

    /* access modifiers changed from: private */
    public void setNotify(BleDevice bleDevice) {
        this.mBleManager.notify(bleDevice, Constants.GATT_SERVICE_PRIMARY, Constants.CHARACTERISTIC_NOTIFY, new BleNotifyCallback() {
            public void onNotifySuccess(BleDevice bleDevice) {
                MeRGBWLog.i("setNotify onNotifySuccess");
                for (BleCallback onNotifySuccess : RGBDeviceManager.this.mListeners.values()) {
                    onNotifySuccess.onNotifySuccess(bleDevice);
                }
            }

            public void onNotifyFailure(BleException bleException) {
                MeRGBWLog.e("setNotify onNotifyFailure: " + bleException.getDescription());
            }

            public void onCharacteristicChanged(BleDevice bleDevice, byte[] bArr) {
                MeRGBWLog.i(bleDevice.getMac() + " setNotify onCharacteristicChanged: " + StringUtils.byte2HexStr(bArr));
                for (BleCallback onNotifyData : RGBDeviceManager.this.mListeners.values()) {
                    onNotifyData.onNotifyData(bleDevice, bArr[1], bArr);
                }
            }
        });
    }

    public void sendCommandData(String str, byte[] bArr) {
        if (isConnected(str)) {
            sendCommandData(new BleDevice(this.mBleManager.getBluetoothAdapter().getRemoteDevice(str)), bArr);
        }
    }

    public void sendCommandData(DeviceInfoBean deviceInfoBean, byte[] bArr) {
        if (deviceInfoBean != null && isConnected(deviceInfoBean.getDeviceMac())) {
            sendCommandData(new BleDevice(this.mBleManager.getBluetoothAdapter().getRemoteDevice(deviceInfoBean.getDeviceMac())), bArr);
        }
    }

    public void sendCommandData(BleDevice bleDevice, byte[] bArr) {
        SendCommandQueue sendCommandQueue = this.mDataQueue.get(bleDevice.getMac());
        if (sendCommandQueue == null) {
            sendCommandQueue = new SendCommandQueue();
            this.mDataQueue.put(bleDevice.getMac(), sendCommandQueue);
        }
        sendCommandQueue.put(new CommandData(bleDevice, bArr));
    }

    public void sendAudioColor(DeviceInfoBean deviceInfoBean, byte[] bArr) {
        if (isConnected(deviceInfoBean.getDeviceMac())) {
            BleDevice bleDevice = new BleDevice(this.mBleManager.getBluetoothAdapter().getRemoteDevice(deviceInfoBean.getDeviceMac()));
            SendCommandQueue sendCommandQueue = this.mDataQueue.get(bleDevice.getMac());
            if (sendCommandQueue == null) {
                sendCommandQueue = new SendCommandQueue();
                this.mDataQueue.put(bleDevice.getMac(), sendCommandQueue);
            }
            sendCommandQueue.sendAudioData(bleDevice, bArr);
        }
    }

    public void sendData(final BleDevice bleDevice, byte[] bArr) {
        MeRGBWLog.i(bleDevice.getMac() + " sendData: " + StringUtils.byte2HexStr(bArr));
        this.mBleManager.write(bleDevice, Constants.GATT_SERVICE_PRIMARY, Constants.CHARACTERISTIC_WRITEABLE, bArr, false, new BleWriteCallback() {
            public void onWriteFailure(BleException bleException) {
            }

            public void onWriteSuccess(int i, int i2, byte[] bArr) {
                MeRGBWLog.i(bleDevice.getMac() + " sendData onWriteSuccess: " + StringUtils.byte2HexStr(bArr));
            }
        });
    }

    /* access modifiers changed from: private */
    public void setMtu(final BleDevice bleDevice) {
        this.mBleManager.setMtu(bleDevice, 247, new BleMtuChangedCallback() {
            public void onSetMTUFailure(BleException bleException) {
                MeRGBWLog.i("setMtu onSetMTUFailure: " + bleException.getDescription());
                RGBDeviceManager.this.setNotify(bleDevice);
                for (BleCallback onMtuChanged : RGBDeviceManager.this.mListeners.values()) {
                    onMtuChanged.onMtuChanged(bleDevice, false, 20);
                }
                SendCommandQueue sendCommandQueue = (SendCommandQueue) RGBDeviceManager.this.mDataQueue.get(bleDevice.getMac());
                if (sendCommandQueue != null) {
                    sendCommandQueue.setMtu(20);
                }
            }

            public void onMtuChanged(int i) {
                MeRGBWLog.i("setMtu onMtuChanged: " + i);
                RGBDeviceManager.this.setNotify(bleDevice);
                for (BleCallback onMtuChanged : RGBDeviceManager.this.mListeners.values()) {
                    onMtuChanged.onMtuChanged(bleDevice, true, i);
                }
                SendCommandQueue sendCommandQueue = (SendCommandQueue) RGBDeviceManager.this.mDataQueue.get(bleDevice.getMac());
                if (sendCommandQueue != null) {
                    sendCommandQueue.setMtu(i);
                }
            }
        });
    }

    public int getMtu(DeviceInfoBean deviceInfoBean) {
        SendCommandQueue sendCommandQueue = this.mDataQueue.get(deviceInfoBean.getDeviceMac());
        if (sendCommandQueue != null) {
            return sendCommandQueue.getMtu();
        }
        return 20;
    }

    public boolean isUpgrade() {
        return this.isUpgrade;
    }

    public synchronized void setUpgrade(boolean z) {
        this.isUpgrade = z;
    }
}
