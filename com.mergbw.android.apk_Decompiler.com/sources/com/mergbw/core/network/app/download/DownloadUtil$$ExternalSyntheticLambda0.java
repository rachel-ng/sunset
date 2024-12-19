package com.mergbw.core.network.app.download;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DownloadUtil$$ExternalSyntheticLambda0 implements DownloadListener {
    public final /* synthetic */ DownloadCallback f$0;

    public /* synthetic */ DownloadUtil$$ExternalSyntheticLambda0(DownloadCallback downloadCallback) {
        this.f$0 = downloadCallback;
    }

    public final void onProgress(long j, long j2) {
        this.f$0.onProgress(j, j2, (int) ((((double) j2) / ((double) j)) * 100.0d));
    }
}
