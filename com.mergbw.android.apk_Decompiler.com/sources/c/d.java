package c;

public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    static final char[] f37a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(byte[] bArr, char c2, int i) {
        StringBuffer stringBuffer = new StringBuffer((i << 1) + (c2 == 0 ? 0 : i));
        for (int i2 = 0; i2 < i; i2++) {
            char[] cArr = f37a;
            stringBuffer.append(cArr[(bArr[i2] >>> 4) & 15]);
            stringBuffer.append(cArr[bArr[i2] & 15]);
            if (c2 != 0 && i2 < i - 1) {
                stringBuffer.append(c2);
            }
        }
        return stringBuffer.toString();
    }

    public static String b(byte[] bArr) {
        String str = "";
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                hexString = SessionDescription.SUPPORTED_SDP_VERSION + hexString;
            }
            str = str + hexString.toUpperCase();
        }
        return str;
    }

    public static String a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static byte[] a(String str) {
        int i;
        c cVar = new c(str.length() / 2);
        int length = str.length();
        if (str.startsWith("0x")) {
            length -= 2;
            i = 2;
        } else {
            i = 0;
        }
        while (length > 0) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                i++;
                length--;
            }
            if (length >= 2) {
                int i2 = i + 2;
                cVar.a((byte) Integer.parseInt(str.substring(i, i2), 16));
                length -= 2;
                i = i2;
            } else {
                throw new NumberFormatException("Odd number of hexadecimal digits");
            }
        }
        return cVar.a();
    }

    public static String a(int i) {
        String hexString = Integer.toHexString(i & 255);
        if (hexString.length() != 1) {
            return hexString;
        }
        return SessionDescription.SUPPORTED_SDP_VERSION + hexString;
    }
}
