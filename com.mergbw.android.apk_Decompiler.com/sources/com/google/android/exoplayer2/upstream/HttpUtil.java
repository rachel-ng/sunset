package com.google.android.exoplayer2.upstream;

import android.text.TextUtils;
import com.google.android.exoplayer2.util.Assertions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HttpUtil {
    private static final Pattern CONTENT_RANGE_WITH_SIZE = Pattern.compile("bytes (?:(?:\\d+-\\d+)|\\*)/(\\d+)");
    private static final Pattern CONTENT_RANGE_WITH_START_AND_END = Pattern.compile("bytes (\\d+)-(\\d+)/(?:\\d+|\\*)");
    private static final String TAG = "HttpUtil";

    private HttpUtil() {
    }

    public static String buildRangeRequestHeader(long j, long j2) {
        if (j == 0 && j2 == -1) {
            return null;
        }
        StringBuilder sb = new StringBuilder("bytes=");
        sb.append(j);
        sb.append("-");
        if (j2 != -1) {
            sb.append((j + j2) - 1);
        }
        return sb.toString();
    }

    public static long getDocumentSize(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Matcher matcher = CONTENT_RANGE_WITH_SIZE.matcher(str);
        if (matcher.matches()) {
            return Long.parseLong((String) Assertions.checkNotNull(matcher.group(1)));
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long getContentLength(java.lang.String r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "Inconsistent headers ["
            boolean r1 = android.text.TextUtils.isEmpty(r10)
            java.lang.String r2 = "]"
            java.lang.String r3 = "HttpUtil"
            if (r1 != 0) goto L_0x0025
            long r4 = java.lang.Long.parseLong(r10)     // Catch:{ NumberFormatException -> 0x0011 }
            goto L_0x0027
        L_0x0011:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r4 = "Unexpected Content-Length ["
            r1.<init>(r4)
            r1.append(r10)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.google.android.exoplayer2.util.Log.e(r3, r1)
        L_0x0025:
            r4 = -1
        L_0x0027:
            boolean r1 = android.text.TextUtils.isEmpty(r11)
            if (r1 != 0) goto L_0x009b
            java.util.regex.Pattern r1 = CONTENT_RANGE_WITH_START_AND_END
            java.util.regex.Matcher r1 = r1.matcher(r11)
            boolean r6 = r1.matches()
            if (r6 == 0) goto L_0x009b
            r6 = 2
            java.lang.String r6 = r1.group(r6)     // Catch:{ NumberFormatException -> 0x0087 }
            java.lang.Object r6 = com.google.android.exoplayer2.util.Assertions.checkNotNull(r6)     // Catch:{ NumberFormatException -> 0x0087 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ NumberFormatException -> 0x0087 }
            long r6 = java.lang.Long.parseLong(r6)     // Catch:{ NumberFormatException -> 0x0087 }
            r8 = 1
            java.lang.String r1 = r1.group(r8)     // Catch:{ NumberFormatException -> 0x0087 }
            java.lang.Object r1 = com.google.android.exoplayer2.util.Assertions.checkNotNull(r1)     // Catch:{ NumberFormatException -> 0x0087 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ NumberFormatException -> 0x0087 }
            long r8 = java.lang.Long.parseLong(r1)     // Catch:{ NumberFormatException -> 0x0087 }
            long r6 = r6 - r8
            r8 = 1
            long r6 = r6 + r8
            r8 = 0
            int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r1 >= 0) goto L_0x0063
            r4 = r6
            goto L_0x009b
        L_0x0063:
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 == 0) goto L_0x009b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0087 }
            r1.<init>(r0)     // Catch:{ NumberFormatException -> 0x0087 }
            r1.append(r10)     // Catch:{ NumberFormatException -> 0x0087 }
            java.lang.String r10 = "] ["
            r1.append(r10)     // Catch:{ NumberFormatException -> 0x0087 }
            r1.append(r11)     // Catch:{ NumberFormatException -> 0x0087 }
            r1.append(r2)     // Catch:{ NumberFormatException -> 0x0087 }
            java.lang.String r10 = r1.toString()     // Catch:{ NumberFormatException -> 0x0087 }
            com.google.android.exoplayer2.util.Log.w(r3, r10)     // Catch:{ NumberFormatException -> 0x0087 }
            long r10 = java.lang.Math.max(r4, r6)     // Catch:{ NumberFormatException -> 0x0087 }
            r4 = r10
            goto L_0x009b
        L_0x0087:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r0 = "Unexpected Content-Range ["
            r10.<init>(r0)
            r10.append(r11)
            r10.append(r2)
            java.lang.String r10 = r10.toString()
            com.google.android.exoplayer2.util.Log.e(r3, r10)
        L_0x009b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.HttpUtil.getContentLength(java.lang.String, java.lang.String):long");
    }
}
