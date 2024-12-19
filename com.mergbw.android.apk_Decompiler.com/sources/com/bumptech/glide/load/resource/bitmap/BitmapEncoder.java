package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;

public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");
    public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);
    private static final String TAG = "BitmapEncoder";
    private final ArrayPool arrayPool;

    public BitmapEncoder(ArrayPool arrayPool2) {
        this.arrayPool = arrayPool2;
    }

    @Deprecated
    public BitmapEncoder() {
        this.arrayPool = null;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:22|(2:42|43)|44|45) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:44:0x00be */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0062 A[Catch:{ all -> 0x0058 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0069 A[SYNTHETIC, Splitter:B:30:0x0069] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0074 A[Catch:{ all -> 0x00bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00bb A[SYNTHETIC, Splitter:B:42:0x00bb] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean encode(com.bumptech.glide.load.engine.Resource<android.graphics.Bitmap> r9, java.io.File r10, com.bumptech.glide.load.Options r11) {
        /*
            r8 = this;
            java.lang.String r0 = "BitmapEncoder"
            java.lang.String r1 = "Compressed with type: "
            java.lang.Object r9 = r9.get()
            android.graphics.Bitmap r9 = (android.graphics.Bitmap) r9
            android.graphics.Bitmap$CompressFormat r2 = r8.getFormat(r9, r11)
            int r3 = r9.getWidth()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            int r4 = r9.getHeight()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.String r5 = "encode: [%dx%d] %s"
            com.bumptech.glide.util.pool.GlideTrace.beginSectionFormat(r5, r3, r4, r2)
            long r3 = com.bumptech.glide.util.LogTime.getLogTime()     // Catch:{ all -> 0x00bf }
            com.bumptech.glide.load.Option<java.lang.Integer> r5 = COMPRESSION_QUALITY     // Catch:{ all -> 0x00bf }
            java.lang.Object r5 = r11.get(r5)     // Catch:{ all -> 0x00bf }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ all -> 0x00bf }
            int r5 = r5.intValue()     // Catch:{ all -> 0x00bf }
            r6 = 0
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x005a }
            r7.<init>(r10)     // Catch:{ IOException -> 0x005a }
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r10 = r8.arrayPool     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            if (r10 == 0) goto L_0x0046
            com.bumptech.glide.load.data.BufferedOutputStream r10 = new com.bumptech.glide.load.data.BufferedOutputStream     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r6 = r8.arrayPool     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            r10.<init>(r7, r6)     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            r6 = r10
            goto L_0x0047
        L_0x0046:
            r6 = r7
        L_0x0047:
            r9.compress(r2, r5, r6)     // Catch:{ IOException -> 0x005a }
            r6.close()     // Catch:{ IOException -> 0x005a }
            r6.close()     // Catch:{ IOException -> 0x0050 }
        L_0x0050:
            r10 = 1
            goto L_0x006d
        L_0x0052:
            r9 = move-exception
            r6 = r7
            goto L_0x00b9
        L_0x0055:
            r10 = move-exception
            r6 = r7
            goto L_0x005b
        L_0x0058:
            r9 = move-exception
            goto L_0x00b9
        L_0x005a:
            r10 = move-exception
        L_0x005b:
            r5 = 3
            boolean r5 = android.util.Log.isLoggable(r0, r5)     // Catch:{ all -> 0x0058 }
            if (r5 == 0) goto L_0x0067
            java.lang.String r5 = "Failed to encode Bitmap"
            android.util.Log.d(r0, r5, r10)     // Catch:{ all -> 0x0058 }
        L_0x0067:
            if (r6 == 0) goto L_0x006c
            r6.close()     // Catch:{ IOException -> 0x006c }
        L_0x006c:
            r10 = 0
        L_0x006d:
            r5 = 2
            boolean r5 = android.util.Log.isLoggable(r0, r5)     // Catch:{ all -> 0x00bf }
            if (r5 == 0) goto L_0x00b5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bf }
            r5.<init>(r1)     // Catch:{ all -> 0x00bf }
            r5.append(r2)     // Catch:{ all -> 0x00bf }
            java.lang.String r1 = " of size "
            r5.append(r1)     // Catch:{ all -> 0x00bf }
            int r1 = com.bumptech.glide.util.Util.getBitmapByteSize(r9)     // Catch:{ all -> 0x00bf }
            r5.append(r1)     // Catch:{ all -> 0x00bf }
            java.lang.String r1 = " in "
            r5.append(r1)     // Catch:{ all -> 0x00bf }
            double r1 = com.bumptech.glide.util.LogTime.getElapsedMillis(r3)     // Catch:{ all -> 0x00bf }
            r5.append(r1)     // Catch:{ all -> 0x00bf }
            java.lang.String r1 = ", options format: "
            r5.append(r1)     // Catch:{ all -> 0x00bf }
            com.bumptech.glide.load.Option<android.graphics.Bitmap$CompressFormat> r1 = COMPRESSION_FORMAT     // Catch:{ all -> 0x00bf }
            java.lang.Object r11 = r11.get(r1)     // Catch:{ all -> 0x00bf }
            r5.append(r11)     // Catch:{ all -> 0x00bf }
            java.lang.String r11 = ", hasAlpha: "
            r5.append(r11)     // Catch:{ all -> 0x00bf }
            boolean r9 = r9.hasAlpha()     // Catch:{ all -> 0x00bf }
            r5.append(r9)     // Catch:{ all -> 0x00bf }
            java.lang.String r9 = r5.toString()     // Catch:{ all -> 0x00bf }
            android.util.Log.v(r0, r9)     // Catch:{ all -> 0x00bf }
        L_0x00b5:
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            return r10
        L_0x00b9:
            if (r6 == 0) goto L_0x00be
            r6.close()     // Catch:{ IOException -> 0x00be }
        L_0x00be:
            throw r9     // Catch:{ all -> 0x00bf }
        L_0x00bf:
            r9 = move-exception
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.BitmapEncoder.encode(com.bumptech.glide.load.engine.Resource, java.io.File, com.bumptech.glide.load.Options):boolean");
    }

    private Bitmap.CompressFormat getFormat(Bitmap bitmap, Options options) {
        Bitmap.CompressFormat compressFormat = (Bitmap.CompressFormat) options.get(COMPRESSION_FORMAT);
        if (compressFormat != null) {
            return compressFormat;
        }
        if (bitmap.hasAlpha()) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    public EncodeStrategy getEncodeStrategy(Options options) {
        return EncodeStrategy.TRANSFORMED;
    }
}
