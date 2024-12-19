package com.mergbw.core.utils;

import android.text.TextUtils;
import androidx.core.view.InputDeviceCompat;
import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Locale;

public class StringUtils {
    public static int bytesToInt2(byte b2, byte b3) {
        return ((b2 & 255) << 8) | (b3 & 255);
    }

    private static boolean isEmojiCharacter(char c2) {
        return c2 == 0 || c2 == 9 || c2 == 10 || c2 == 13 || (c2 >= ' ' && c2 <= 55295) || ((c2 >= 57344 && c2 <= 65533) || (c2 >= 0 && c2 <= 65535));
    }

    public static String str2HexStr(String str) {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(charArray[(bytes[i] & 240) >> 4]);
            sb.append(charArray[bytes[i] & 15]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    public static String hexStr2Str(String str) {
        char[] charArray = str.toCharArray();
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((("0123456789ABCDEF".indexOf(charArray[i2]) * 16) + "0123456789ABCDEF".indexOf(charArray[i2 + 1])) & 255);
        }
        return new String(bArr);
    }

    public static String byte2HexStr(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                hexString = SessionDescription.SUPPORTED_SDP_VERSION + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase(Locale.getDefault()).trim();
    }

    public static int bytesToInt(byte[] bArr) {
        return (bArr[3] & 255) | ((bArr[0] & 255) << Ascii.CAN) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
    }

    public static byte[] hexStr2Bytes(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            bArr[i] = Byte.decode("0x" + str.substring(i2, i3) + str.substring(i3, i2 + 2)).byteValue();
        }
        return bArr;
    }

    public static byte[] toByteArray(String str) {
        if (isEmpty(str)) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        byte[] bArr = new byte[(lowerCase.length() >> 1)];
        int i = 0;
        for (int i2 = 0; i2 < lowerCase.length() && i <= lowerCase.length() - 1; i2++) {
            bArr[i2] = (byte) ((((byte) (Character.digit(lowerCase.charAt(i), 16) & 255)) << 4) | ((byte) (Character.digit(lowerCase.charAt(i + 1), 16) & 255)));
            i += 2;
        }
        return bArr;
    }

    public static byte[] toBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (Integer.parseInt(str.substring(i2, i2 + 2), 16) & 255);
        }
        return bArr;
    }

    public static String strToUnicode(String str) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            String hexString = Integer.toHexString(charAt);
            if (charAt > 128) {
                sb.append("\\u" + hexString);
            } else {
                sb.append("\\u00" + hexString);
            }
        }
        return sb.toString();
    }

    public static String unicodeToString(String str) {
        int length = str.length() / 6;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < length) {
            int i2 = i * 6;
            i++;
            String substring = str.substring(i2, i * 6);
            sb.append(new String(Character.toChars(Integer.valueOf(substring.substring(2, 4) + "00", 16).intValue() + Integer.valueOf(substring.substring(4), 16).intValue())));
        }
        return sb.toString();
    }

    public static byte[] getBooleanArray(byte b2) {
        byte[] bArr = new byte[8];
        for (int i = 7; i >= 0; i--) {
            bArr[i] = (byte) (b2 & 1);
            b2 = (byte) (b2 >> 1);
        }
        return bArr;
    }

    public static String byteToBit(byte b2) {
        return "" + ((byte) ((b2 >> 7) & 1)) + ((byte) ((b2 >> 6) & 1)) + ((byte) ((b2 >> 5) & 1)) + ((byte) ((b2 >> 4) & 1)) + ((byte) ((b2 >> 3) & 1)) + ((byte) ((b2 >> 2) & 1)) + ((byte) ((b2 >> 1) & 1)) + ((byte) (b2 & 1));
    }

    public static byte BitToByte(String str) {
        int i;
        if (str == null) {
            return 0;
        }
        int length = str.length();
        if (length != 4 && length != 8) {
            return 0;
        }
        if (length != 8) {
            i = Integer.parseInt(str, 2);
        } else if (str.charAt(0) == '0') {
            i = Integer.parseInt(str, 2);
        } else {
            i = Integer.parseInt(str, 2) + InputDeviceCompat.SOURCE_ANY;
        }
        return (byte) i;
    }

    public static boolean isEmpty(String str) {
        if (str != null && !str.trim().isEmpty()) {
            return TextUtils.isEmpty(str);
        }
        return true;
    }

    public static int calculateLength(String str) {
        try {
            return str.getBytes("utf-8").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static boolean isContainsEmoji(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isEmojiCharacter(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static int getWeekInt() {
        int i = Calendar.getInstance().get(7);
        if (i == 1) {
            return 7;
        }
        return i - 1;
    }

    public static boolean checkEmail(String str) {
        return str.matches("\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}");
    }

    public static String UseApplyPatternMethodFormat(double d) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern("###.##");
        return decimalFormat.format(d);
    }

    public static boolean checkPassword(String str) {
        if (!isEmpty(str) && str.length() >= 8) {
            return true;
        }
        return false;
    }

    public static boolean checkVerifyCode(String str) {
        if (!isEmpty(str) && str.matches("^[0-9]+$") && str.length() == 6) {
            return true;
        }
        return false;
    }

    public static boolean checkNumber(String str) {
        return str.matches("^[0-9]+$");
    }
}
