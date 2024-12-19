package com.mergbw.core.network.app.download;

import com.mergbw.core.Constants;
import com.mergbw.core.network.app.WebApi;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DownloadHelper {
    private static OkHttpClient.Builder mBuilder;

    private static Retrofit getDownloadRetrofit(DownloadListener downloadListener) {
        if (mBuilder == null) {
            mBuilder = new OkHttpClient.Builder().addInterceptor(new DownloadInterceptor(downloadListener));
        }
        return new Retrofit.Builder().baseUrl(Constants.SERVER_ADDRESS).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(mBuilder.build()).build();
    }

    public static void cancel(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public static void downloadFile(String str, DownloadListener downloadListener, Observer<ResponseBody> observer) {
        ((WebApi) getDownloadRetrofit(downloadListener).create(WebApi.class)).downloadFile(str).subscribeOn(Schedulers.io()).subscribe(observer);
    }
}
