package com.mergbw.core.network.factory;

import com.mergbw.core.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FactoryDataHelper {
    private static final boolean isTestServer = false;
    private static FactoryDataHelper mHelper;
    private static Retrofit mRetrofit;

    public static FactoryDataHelper getInstance() {
        if (mHelper == null) {
            mHelper = new FactoryDataHelper();
        }
        return mHelper;
    }

    private FactoryDataHelper() {
        mRetrofit = new Retrofit.Builder().client(new OkHttpClient.Builder().build()).baseUrl(getFactoryServerAddress()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    public FactoryDataApi getApi() {
        return (FactoryDataApi) mRetrofit.create(FactoryDataApi.class);
    }

    public static String getFactoryServerAddress() {
        return Constants.FACTORY_SERVER_ADDRESS;
    }

    public static String getFactoryFileAddress() {
        return Constants.FACTORY_IMAGE_ADDRESS;
    }
}
