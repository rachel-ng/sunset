package com.mergbw.core.network.app;

import android.content.Context;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.mergbw.core.Constants;
import com.mergbw.core.utils.AppUtils;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {
    private static NetworkHelper mHelper;
    private static Retrofit mRetrofit;

    public static NetworkHelper getInstance() {
        if (mHelper == null) {
            mHelper = new NetworkHelper();
        }
        return mHelper;
    }

    private NetworkHelper() {
        mRetrofit = new Retrofit.Builder().client(new OkHttpClient.Builder().cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor((Context) AppUtils.getApp()))).build()).baseUrl(Constants.SERVER_ADDRESS).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    public WebApi getApi() {
        return (WebApi) mRetrofit.create(WebApi.class);
    }
}
