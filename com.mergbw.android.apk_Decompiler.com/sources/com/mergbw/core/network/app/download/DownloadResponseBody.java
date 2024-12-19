package com.mergbw.core.network.app.download;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class DownloadResponseBody extends ResponseBody {
    private BufferedSource bufferedSource;
    /* access modifiers changed from: private */
    public DownloadListener listener;
    private final ResponseBody responseBody;

    public DownloadResponseBody(ResponseBody responseBody2, DownloadListener downloadListener) {
        this.responseBody = responseBody2;
        this.listener = downloadListener;
    }

    public MediaType contentType() {
        return this.responseBody.contentType();
    }

    public long contentLength() {
        return this.responseBody.contentLength();
    }

    public BufferedSource source() {
        if (this.bufferedSource == null) {
            this.bufferedSource = Okio.buffer(getSource(this.responseBody.source()));
        }
        return this.bufferedSource;
    }

    private Source getSource(Source source) {
        return new ForwardingSource(source) {
            long downloadBytes = 0;

            public long read(Buffer buffer, long j) throws IOException {
                long read = super.read(buffer, j);
                if (-1 != read) {
                    this.downloadBytes += read;
                }
                DownloadResponseBody.this.listener.onProgress(DownloadResponseBody.this.contentLength(), this.downloadBytes);
                return read;
            }
        };
    }
}
