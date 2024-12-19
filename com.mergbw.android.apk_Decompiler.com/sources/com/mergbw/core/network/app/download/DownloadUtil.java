package com.mergbw.core.network.app.download;

import android.text.TextUtils;
import com.mergbw.core.utils.MeRGBWLog;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.io.File;
import okhttp3.ResponseBody;

public class DownloadUtil {
    public static void downloadFile(String str, final String str2, final DownloadCallback downloadCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (downloadCallback != null) {
                downloadCallback.onError("url or path empty");
            }
            MeRGBWLog.e("downloadFile url or path empty");
            return;
        }
        File file = new File(str2);
        if (file.exists()) {
            file.delete();
        }
        DownloadHelper.downloadFile(str, new DownloadUtil$$ExternalSyntheticLambda0(downloadCallback), new Observer<ResponseBody>() {
            public void onSubscribe(Disposable disposable) {
                MeRGBWLog.e("onSubscribe");
            }

            public void onNext(ResponseBody responseBody) {
                DownloadUtil.saveFile(str2, responseBody, downloadCallback);
            }

            public void onError(Throwable th) {
                th.printStackTrace();
                MeRGBWLog.e("onError " + th.getMessage());
                DownloadCallback downloadCallback = downloadCallback;
                if (downloadCallback != null) {
                    downloadCallback.onError(th.getMessage());
                }
            }

            public void onComplete() {
                MeRGBWLog.e("download onComplete ");
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0050, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0051, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0053, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0054, code lost:
        r1 = null;
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x005f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0060, code lost:
        r4.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0073, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0074, code lost:
        r5.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x007d, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x007e, code lost:
        r5.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0050 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0004] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x005b A[SYNTHETIC, Splitter:B:45:0x005b] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0065 A[SYNTHETIC, Splitter:B:50:0x0065] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x006f A[SYNTHETIC, Splitter:B:56:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0079 A[SYNTHETIC, Splitter:B:61:0x0079] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void saveFile(java.lang.String r4, okhttp3.ResponseBody r5, com.mergbw.core.network.app.download.DownloadCallback r6) {
        /*
            if (r4 != 0) goto L_0x0003
            return
        L_0x0003:
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0053, all -> 0x0050 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x0053, all -> 0x0050 }
            boolean r4 = r1.exists()     // Catch:{ Exception -> 0x004d, all -> 0x0050 }
            if (r4 != 0) goto L_0x0012
            r1.createNewFile()     // Catch:{ Exception -> 0x004d, all -> 0x0050 }
        L_0x0012:
            r4 = 4096(0x1000, float:5.74E-42)
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x004d, all -> 0x0050 }
            java.io.InputStream r5 = r5.byteStream()     // Catch:{ Exception -> 0x004d, all -> 0x0050 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
        L_0x001f:
            int r0 = r5.read(r4)     // Catch:{ Exception -> 0x0043, all -> 0x0041 }
            r3 = -1
            if (r0 != r3) goto L_0x003c
            r2.flush()     // Catch:{ Exception -> 0x0043, all -> 0x0041 }
            if (r5 == 0) goto L_0x0033
            r5.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0033
        L_0x002f:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0033:
            r2.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x0068
        L_0x0037:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0068
        L_0x003c:
            r3 = 0
            r2.write(r4, r3, r0)     // Catch:{ Exception -> 0x0043, all -> 0x0041 }
            goto L_0x001f
        L_0x0041:
            r4 = move-exception
            goto L_0x0047
        L_0x0043:
            r4 = move-exception
            goto L_0x004b
        L_0x0045:
            r4 = move-exception
            r2 = r0
        L_0x0047:
            r0 = r5
            goto L_0x006d
        L_0x0049:
            r4 = move-exception
            r2 = r0
        L_0x004b:
            r0 = r5
            goto L_0x0056
        L_0x004d:
            r4 = move-exception
            r2 = r0
            goto L_0x0056
        L_0x0050:
            r4 = move-exception
            r2 = r0
            goto L_0x006d
        L_0x0053:
            r4 = move-exception
            r1 = r0
            r2 = r1
        L_0x0056:
            r4.printStackTrace()     // Catch:{ all -> 0x006c }
            if (r0 == 0) goto L_0x0063
            r0.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x0063
        L_0x005f:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0063:
            if (r2 == 0) goto L_0x0068
            r2.close()     // Catch:{ IOException -> 0x0037 }
        L_0x0068:
            r6.onFinish(r1)
            return
        L_0x006c:
            r4 = move-exception
        L_0x006d:
            if (r0 == 0) goto L_0x0077
            r0.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x0077
        L_0x0073:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0077:
            if (r2 == 0) goto L_0x0081
            r2.close()     // Catch:{ IOException -> 0x007d }
            goto L_0x0081
        L_0x007d:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0081:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mergbw.core.network.app.download.DownloadUtil.saveFile(java.lang.String, okhttp3.ResponseBody, com.mergbw.core.network.app.download.DownloadCallback):void");
    }
}
