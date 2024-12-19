package com.mergbw.core.ble;

import com.mergbw.core.utils.MeRGBWLog;

public class CommandUtils {
    private static final byte CMD_HEAD = 85;
    private static final byte CMD_SEQUENCE = -1;

    public static byte[] getCommand(byte b2, byte[] bArr) {
        int i = 5;
        if (bArr != null) {
            i = 5 + bArr.length;
        }
        byte[] bArr2 = new byte[i];
        bArr2[0] = CMD_HEAD;
        bArr2[1] = b2;
        bArr2[2] = -1;
        bArr2[3] = (byte) i;
        if (bArr != null) {
            System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += 255 & bArr2[i3];
        }
        while (i2 > 255) {
            i2 &= 255;
        }
        bArr2[i - 1] = (byte) ((~i2) & 255);
        return bArr2;
    }

    public static boolean checkValid(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < bArr.length - 1; i2++) {
            i += bArr[i2] & 255;
        }
        while (i > 255) {
            i &= 255;
        }
        if (((~(i + bArr[bArr.length - 1])) & 255) == 0) {
            return true;
        }
        MeRGBWLog.e("checkSum error!");
        return false;
    }

    public static byte[] getByteArray(int i) {
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] int2ByteArray(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] getSeqCommand(byte b2, byte[] bArr, byte b3, int i) {
        int i2 = i + 5;
        byte[] bArr2 = new byte[i2];
        bArr2[0] = CMD_HEAD;
        bArr2[1] = b2;
        bArr2[2] = b3;
        bArr2[3] = (byte) i2;
        System.arraycopy(bArr, 0, bArr2, 4, i);
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += 255 & bArr2[i4];
        }
        while (i3 > 255) {
            i3 = (i3 >> 8) + (i3 & 255);
        }
        bArr2[i + 4] = (byte) ((~i3) & 255);
        return bArr2;
    }

    public static byte[] getSeqCommandWithoutLength(byte b2, byte[] bArr, byte b3, int i) {
        int i2 = i + 4;
        byte[] bArr2 = new byte[i2];
        bArr2[0] = CMD_HEAD;
        bArr2[1] = b2;
        bArr2[2] = b3;
        if (b2 == 4) {
            System.arraycopy(bArr, 0, bArr2, 3, i);
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += 255 & bArr2[i4];
        }
        while (i3 > 255) {
            i3 = (i3 >> 8) + (i3 & 255);
        }
        bArr2[i + 3] = (byte) ((~i3) & 255);
        return bArr2;
    }
}
