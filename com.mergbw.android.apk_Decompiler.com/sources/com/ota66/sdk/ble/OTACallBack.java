package com.ota66.sdk.ble;

import android.bluetooth.BluetoothDevice;

public interface OTACallBack {
    void onConnected(boolean z);

    void onDeviceSearch(BluetoothDevice bluetoothDevice, int i, byte[] bArr);

    void onError(int i);

    void onOTA(boolean z);

    void onOTAFinish();

    void onPhyUpdate();

    void onProcess(float f);

    void onReboot();

    void onRebootSuccess();

    void onResource(boolean z);

    void onResourceFinish();

    void onStartSecurityData();
}
