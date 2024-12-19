package com.bumptech.glide.load.data.mediastore;

import android.content.ContentResolver;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class ThumbnailStreamOpener {
    private static final FileService DEFAULT_SERVICE = new FileService();
    private static final String TAG = "ThumbStreamOpener";
    private final ArrayPool byteArrayPool;
    private final ContentResolver contentResolver;
    private final List<ImageHeaderParser> parsers;
    private final ThumbnailQuery query;
    private final FileService service;

    ThumbnailStreamOpener(List<ImageHeaderParser> list, ThumbnailQuery thumbnailQuery, ArrayPool arrayPool, ContentResolver contentResolver2) {
        this(list, DEFAULT_SERVICE, thumbnailQuery, arrayPool, contentResolver2);
    }

    ThumbnailStreamOpener(List<ImageHeaderParser> list, FileService fileService, ThumbnailQuery thumbnailQuery, ArrayPool arrayPool, ContentResolver contentResolver2) {
        this.service = fileService;
        this.query = thumbnailQuery;
        this.byteArrayPool = arrayPool;
        this.contentResolver = contentResolver2;
        this.parsers = list;
    }

    /* access modifiers changed from: package-private */
    public int getOrientation(Uri uri) {
        InputStream inputStream = null;
        try {
            InputStream openInputStream = this.contentResolver.openInputStream(uri);
            int orientation = ImageHeaderParserUtils.getOrientation(this.parsers, openInputStream, this.byteArrayPool);
            if (openInputStream != null) {
                try {
                    openInputStream.close();
                } catch (IOException unused) {
                }
            }
            return orientation;
        } catch (IOException | NullPointerException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to open uri: " + uri, e);
            }
            if (inputStream == null) {
                return -1;
            }
            try {
                inputStream.close();
                return -1;
            } catch (IOException unused2) {
                return -1;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
    }

    public InputStream open(Uri uri) throws FileNotFoundException {
        String path = getPath(uri);
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        File file = this.service.get(path);
        if (!isValid(file)) {
            return null;
        }
        Uri fromFile = Uri.fromFile(file);
        try {
            return this.contentResolver.openInputStream(fromFile);
        } catch (NullPointerException e) {
            throw ((FileNotFoundException) new FileNotFoundException("NPE opening uri: " + uri + " -> " + fromFile).initCause(e));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0031 A[Catch:{ all -> 0x0046 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getPath(android.net.Uri r7) {
        /*
            r6 = this;
            java.lang.String r0 = "ThumbStreamOpener"
            java.lang.String r1 = "Failed to query for thumbnail for Uri: "
            r2 = 0
            com.bumptech.glide.load.data.mediastore.ThumbnailQuery r3 = r6.query     // Catch:{ SecurityException -> 0x0028, all -> 0x0026 }
            android.database.Cursor r3 = r3.query(r7)     // Catch:{ SecurityException -> 0x0028, all -> 0x0026 }
            if (r3 == 0) goto L_0x0020
            boolean r4 = r3.moveToFirst()     // Catch:{ SecurityException -> 0x001e }
            if (r4 == 0) goto L_0x0020
            r4 = 0
            java.lang.String r7 = r3.getString(r4)     // Catch:{ SecurityException -> 0x001e }
            if (r3 == 0) goto L_0x001d
            r3.close()
        L_0x001d:
            return r7
        L_0x001e:
            r4 = move-exception
            goto L_0x002a
        L_0x0020:
            if (r3 == 0) goto L_0x0025
            r3.close()
        L_0x0025:
            return r2
        L_0x0026:
            r7 = move-exception
            goto L_0x0048
        L_0x0028:
            r4 = move-exception
            r3 = r2
        L_0x002a:
            r5 = 3
            boolean r5 = android.util.Log.isLoggable(r0, r5)     // Catch:{ all -> 0x0046 }
            if (r5 == 0) goto L_0x0040
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0046 }
            r5.<init>(r1)     // Catch:{ all -> 0x0046 }
            r5.append(r7)     // Catch:{ all -> 0x0046 }
            java.lang.String r7 = r5.toString()     // Catch:{ all -> 0x0046 }
            android.util.Log.d(r0, r7, r4)     // Catch:{ all -> 0x0046 }
        L_0x0040:
            if (r3 == 0) goto L_0x0045
            r3.close()
        L_0x0045:
            return r2
        L_0x0046:
            r7 = move-exception
            r2 = r3
        L_0x0048:
            if (r2 == 0) goto L_0x004d
            r2.close()
        L_0x004d:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.data.mediastore.ThumbnailStreamOpener.getPath(android.net.Uri):java.lang.String");
    }

    private boolean isValid(File file) {
        return this.service.exists(file) && 0 < this.service.length(file);
    }
}
