package com.mergbw.core.ble;

import com.mergbw.core.utils.StringUtils;

public class CastDataBean {
    private byte[] data;
    private int len;
    private int type;

    public int getLen() {
        return this.len;
    }

    public void setLen(int i) {
        this.len = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }

    public String toString() {
        return "len: " + getLen() + " type: " + getType() + " data: " + StringUtils.byte2HexStr(getData());
    }
}
