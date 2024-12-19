package com.mergbw.core.network.app.download;

import java.io.File;

public interface DownloadCallback {
    void onError(String str);

    void onFinish(File file);

    void onProgress(long j, long j2, int i);
}
