package c;

public abstract class e {
    public static String a(String str, int i) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i2 = 0; i2 < i - length; i2++) {
            stringBuffer.append(SessionDescription.SUPPORTED_SDP_VERSION);
        }
        return stringBuffer.append(str).toString();
    }

    public static String a(String str) {
        String str2 = "";
        for (int i = 0; i < str.length() / 2; i++) {
            StringBuilder sb = new StringBuilder();
            int i2 = i * 2;
            sb.append(str.substring(i2, i2 + 2));
            sb.append(str2);
            str2 = sb.toString();
        }
        return str2;
    }
}
