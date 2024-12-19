package com.mergbw.core.network.app.download;

import android.content.Context;
import com.mergbw.core.utils.MeRGBWLog;
import java.io.File;

public class FirmwareDownloadTask implements Runnable {
    /* access modifiers changed from: private */
    public final DownloadCallback mCallBack;
    private final Context mContext;
    private final int mDeviceModel;
    private final int mFileId;

    public FirmwareDownloadTask(Context context, int i, int i2, DownloadCallback downloadCallback) {
        this.mContext = context;
        this.mFileId = i;
        this.mCallBack = downloadCallback;
        this.mDeviceModel = i2;
    }

    public void run() {
        String str = "/file/download/" + this.mFileId;
        String absolutePath = new File(this.mContext.getExternalCacheDir(), "Firmware_File.bin").getAbsolutePath();
        MeRGBWLog.e("AppUpdateTask, download url: " + str);
        DownloadUtil.downloadFile(str, absolutePath, new DownloadCallback() {
            public void onProgress(long j, long j2, int i) {
                if (FirmwareDownloadTask.this.mCallBack != null) {
                    FirmwareDownloadTask.this.mCallBack.onProgress(j, j2, i);
                }
            }

            public void onFinish(File file) {
                if (FirmwareDownloadTask.this.mCallBack != null) {
                    FirmwareDownloadTask.this.mCallBack.onFinish(file);
                }
            }

            public void onError(String str) {
                if (FirmwareDownloadTask.this.mCallBack != null) {
                    FirmwareDownloadTask.this.mCallBack.onError(str);
                }
            }
        });
    }
}
