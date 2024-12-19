package com.mergbw.core.ble;

import com.clj.fastble.data.BleDevice;

public class CommandData {
    private BleDevice bleDevice;
    private byte[] data;

    public CommandData(BleDevice bleDevice2, byte[] bArr) {
        setBleDevice(bleDevice2);
        setData(bArr);
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }

    public BleDevice getBleDevice() {
        return this.bleDevice;
    }

    public void setBleDevice(BleDevice bleDevice2) {
        this.bleDevice = bleDevice2;
    }
}
