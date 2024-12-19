package com.ota66.sdk;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import c.d;
import com.ota66.sdk.ble.OTACallBack;

public class OTAUtils {
    private static final char[] HEX_DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] HEX_DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static int MTU_SIZE = 23;
    /* access modifiers changed from: private */
    public OTACallBack callBack;
    /* access modifiers changed from: private */
    public boolean isConnected;
    /* access modifiers changed from: private */
    public boolean isQuick;
    private b.a mBleCallBack;
    private b.b mBleScanner;
    /* access modifiers changed from: private */
    public BluetoothGatt mBluetoothGatt;
    private Context mContext;
    private String otaKey = "";
    private boolean otaKeyCmd0x74 = true;

    class a implements Runnable {
        a() {
        }

        public void run() {
            String str;
            if (OTAUtils.this.isConnected) {
                boolean z = true;
                if (c.b.a(OTAUtils.this.mBluetoothGatt)) {
                    OTAUtils.this.callBack.onOTA(true);
                    return;
                }
                if (OTAUtils.this.isQuick) {
                    str = "010201";
                } else {
                    str = "0102";
                    z = false;
                }
                OTAUtils oTAUtils = OTAUtils.this;
                if (oTAUtils.sendCommand(oTAUtils.mBluetoothGatt, str, z)) {
                    OTAUtils.this.callBack.onOTA(false);
                    return;
                }
                return;
            }
            OTAUtils.this.callBack.onError(1007);
        }
    }

    class b implements Runnable {
        b() {
        }

        public void run() {
            String str;
            if (OTAUtils.this.isConnected) {
                boolean z = true;
                if (c.b.a(OTAUtils.this.mBluetoothGatt)) {
                    OTAUtils.this.callBack.onResource(true);
                    return;
                }
                if (OTAUtils.this.isQuick) {
                    str = "010301";
                } else {
                    str = "0103";
                    z = false;
                }
                OTAUtils oTAUtils = OTAUtils.this;
                if (oTAUtils.sendCommand(oTAUtils.mBluetoothGatt, str, z)) {
                    OTAUtils.this.callBack.onResource(false);
                    return;
                }
                return;
            }
            OTAUtils.this.callBack.onError(1007);
        }
    }

    private class c implements b.c {
        private c() {
        }

        public void a(boolean z) {
            boolean unused = OTAUtils.this.isConnected = z;
            OTAUtils.this.callBack.onConnected(z);
        }

        public void onDeviceSearch(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            OTAUtils.this.callBack.onDeviceSearch(bluetoothDevice, i, bArr);
        }

        public void onError(int i) {
            Log.e("TAG", "onError: =========" + i);
            OTAUtils.this.callBack.onError(i);
        }

        public void onOTAFinish() {
            OTAUtils.this.callBack.onOTAFinish();
        }

        public void onProcess(float f) {
            OTAUtils.this.callBack.onProcess(f);
        }

        public void onResourceFinish() {
            OTAUtils.this.callBack.onResourceFinish();
        }

        public void onStartSecurityData() {
            OTAUtils.this.callBack.onStartSecurityData();
        }

        /* synthetic */ c(OTAUtils oTAUtils, a aVar) {
            this();
        }

        public void a() {
            OTAUtils.this.callBack.onRebootSuccess();
        }

        public void a(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            OTAUtils.this.callBack.onPhyUpdate();
        }
    }

    public OTAUtils(Context context, OTACallBack oTACallBack) {
        this.mContext = context;
        this.callBack = oTACallBack;
        init();
    }

    public static String bytes2HexString(byte[] bArr) {
        return bytes2HexString(bArr, true);
    }

    private void init() {
        c cVar = new c(this, (a) null);
        this.mBleScanner = new b.b(this.mContext, cVar);
        b.a aVar = new b.a();
        this.mBleCallBack = aVar;
        aVar.a((b.c) cVar);
        this.mBleCallBack.v = this.mContext.getSharedPreferences("data", 0).getString("AESKey", "123");
    }

    /* access modifiers changed from: private */
    public boolean sendCommand(BluetoothGatt bluetoothGatt, String str, boolean z) {
        boolean a2 = c.b.a(bluetoothGatt, str, z);
        if (!a2) {
            this.callBack.onError(1003);
        }
        return a2;
    }

    private void sendOtaKey() {
        String str;
        String str2 = this.otaKey;
        if (str2 != null && !str2.isEmpty()) {
            byte[] bytes = str2.getBytes();
            if (this.otaKeyCmd0x74) {
                str = "74";
            } else {
                str = "05";
            }
            String str3 = str + bytes2HexString(bytes);
            Log.d("OTAKEY", "sendOtaKey: " + str3);
            sendCommand(this.mBluetoothGatt, str3, false);
        }
    }

    public void cancleOTA() {
        if (checkOTA()) {
            this.mBleCallBack.b();
            BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
            if (bluetoothGatt != null) {
                bluetoothGatt.disconnect();
                return;
            }
            return;
        }
        this.mBleCallBack.b();
        BluetoothGatt bluetoothGatt2 = this.mBluetoothGatt;
        if (bluetoothGatt2 != null) {
            bluetoothGatt2.disconnect();
        }
    }

    public boolean checkOTA() {
        if (this.isConnected) {
            return c.b.a(this.mBluetoothGatt);
        }
        return false;
    }

    public void close() {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt != null) {
            bluetoothGatt.close();
        }
    }

    public void connectDevice(String str) {
        if (this.isConnected) {
            Log.e("TAG", "connectDevice: 已经连上");
            this.callBack.onConnected(this.isConnected);
            return;
        }
        this.mBluetoothGatt = ((BluetoothManager) this.mContext.getApplicationContext().getSystemService("bluetooth")).getAdapter().getRemoteDevice(str).connectGatt(this.mContext.getApplicationContext(), false, this.mBleCallBack);
    }

    public void disConnectDevice() {
        BluetoothGatt bluetoothGatt;
        if (this.isConnected && (bluetoothGatt = this.mBluetoothGatt) != null) {
            bluetoothGatt.disconnect();
        }
    }

    public void reBoot() {
        boolean z;
        String str;
        if (!this.isConnected) {
            this.callBack.onError(1007);
        } else if (c.b.a(this.mBluetoothGatt)) {
            if (this.isQuick) {
                str = "0401";
                z = true;
            } else {
                str = "04";
                z = false;
            }
            if (sendCommand(this.mBluetoothGatt, str, z)) {
                this.callBack.onReboot();
            }
        } else {
            this.callBack.onError(1008);
        }
    }

    public void setOtaKey(String str) {
        this.otaKey = str;
    }

    public void setOtaKeyCmd0x74(boolean z) {
        this.otaKeyCmd0x74 = z;
    }

    public boolean setPHY() {
        if (Build.VERSION.SDK_INT < 26 || !BluetoothAdapter.getDefaultAdapter().isLe2MPhySupported()) {
            return false;
        }
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null) {
            return true;
        }
        bluetoothGatt.setPreferredPhy(2, 2, 0);
        return true;
    }

    public void setRetryTimes(int i) {
        this.mBleCallBack.a(i);
    }

    public void starScan() {
        this.mBleScanner.a();
    }

    public void startOTA() {
        if (this.isConnected) {
            sendOtaKey();
        }
        new Handler(Looper.getMainLooper()).postDelayed(new a(), 800);
    }

    public void startResource() {
        if (this.isConnected) {
            sendOtaKey();
        }
        new Handler(Looper.getMainLooper()).postDelayed(new b(), 800);
    }

    public void startSecurity() {
        this.mBleCallBack.a(this.mBluetoothGatt);
    }

    public void stopScan() {
        this.mBleScanner.b();
    }

    public void updateFirmware(String str, boolean z, a.b bVar) {
        this.isQuick = z;
        if (!this.isConnected) {
            this.callBack.onError(1007);
        } else if (c.b.a(this.mBluetoothGatt)) {
            a.a aVar = new a.a(str, z);
            if (aVar.a() != 200) {
                this.callBack.onError(1000);
                return;
            }
            this.mBleCallBack.a(aVar, bVar);
            sendCommand(this.mBluetoothGatt, ("01" + d.a(aVar.c().size())) + "00", true);
        } else {
            this.callBack.onError(1008);
        }
    }

    public void updateResource(String str, boolean z) {
        this.isQuick = z;
        if (!this.isConnected) {
            this.callBack.onError(1007);
        } else if (c.b.a(this.mBluetoothGatt)) {
            a.a aVar = new a.a(str, z);
            if (aVar.a() != 200) {
                this.callBack.onError(1000);
                return;
            }
            this.mBleCallBack.a(aVar, a.b.RESOURCE);
            sendCommand(this.mBluetoothGatt, ("01" + d.a(aVar.c().size())) + "00", true);
        } else {
            this.callBack.onError(1008);
        }
    }

    public static String bytes2HexString(byte[] bArr, boolean z) {
        if (bArr == null) {
            return "";
        }
        char[] cArr = z ? HEX_DIGITS_UPPER : HEX_DIGITS_LOWER;
        if (r1 <= 0) {
            return "";
        }
        char[] cArr2 = new char[(r1 << 1)];
        int i = 0;
        for (byte b2 : bArr) {
            int i2 = i + 1;
            cArr2[i] = cArr[(b2 >> 4) & 15];
            i += 2;
            cArr2[i2] = cArr[b2 & 15];
        }
        return new String(cArr2);
    }

    public void updateFirmware(String str, boolean z) {
        updateFirmware(str, z, a.b.OTA);
    }
}
