package com.mergbw.core.ble;

import com.clj.fastble.data.BleDevice;

public abstract class BleCallback {
    public void onAlreadyConnected(BleDevice bleDevice) {
    }

    public void onConnected(BleDevice bleDevice) {
    }

    public void onConnectedFailed(BleDevice bleDevice) {
    }

    public void onDisconnected(BleDevice bleDevice) {
    }

    public void onMtuChanged(BleDevice bleDevice, boolean z, int i) {
    }

    public void onNotifyData(BleDevice bleDevice, byte b2, byte[] bArr) {
    }

    public void onNotifySuccess(BleDevice bleDevice) {
    }
}
