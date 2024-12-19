package com.mergbw.core.network.app.download;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

public class DownloadInterceptor implements Interceptor {
    private DownloadListener listener;

    public DownloadInterceptor(DownloadListener downloadListener) {
        this.listener = downloadListener;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response proceed = chain.proceed(chain.request());
        return proceed.newBuilder().body(new DownloadResponseBody(proceed.body(), this.listener)).build();
    }
}
