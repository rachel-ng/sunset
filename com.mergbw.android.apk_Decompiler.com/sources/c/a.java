package c;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public abstract class a {
    public static byte[] a(byte[] bArr, String str) {
        if (str == null) {
            try {
                System.out.print("Key为空null");
                return null;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            } catch (NoSuchPaddingException e2) {
                e2.printStackTrace();
                return null;
            } catch (InvalidKeyException e3) {
                e3.printStackTrace();
                return null;
            }
        } else if (str.length() != 32) {
            System.out.print("Key长度不是16位");
            return null;
        } else {
            SecretKeySpec secretKeySpec = new SecretKeySpec(a(str), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/NoPadding");
            instance.init(2, secretKeySpec);
            try {
                return instance.doFinal(bArr);
            } catch (Exception e4) {
                System.out.println(e4.toString());
                return null;
            }
        }
    }

    public static String b(String str, String str2) {
        try {
            if (str2.length() != 32) {
                return null;
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(a(str2), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/NoPadding");
            instance.init(1, secretKeySpec);
            return a(instance.doFinal(a(str)));
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvalidKeyException e3) {
            e3.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e4) {
            e4.printStackTrace();
            return null;
        } catch (BadPaddingException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    public static String a(String str, String str2) {
        byte[] a2 = a(a(str), str2);
        if (a2 == null || a2.length == 0) {
            return null;
        }
        return a(a2);
    }

    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                hexString = SessionDescription.SUPPORTED_SDP_VERSION + hexString;
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static byte[] a(String str) {
        if (str.length() < 1) {
            return null;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            bArr[i] = (byte) ((Integer.parseInt(str.substring(i2, i3), 16) * 16) + Integer.parseInt(str.substring(i3, i2 + 2), 16));
        }
        return bArr;
    }
}
