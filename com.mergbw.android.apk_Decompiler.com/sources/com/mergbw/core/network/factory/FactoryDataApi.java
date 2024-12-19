package com.mergbw.core.network.factory;

import com.mergbw.core.network.factory.bean.BaseResult;
import com.mergbw.core.network.factory.bean.FactoryActivityInfoBean;
import com.mergbw.core.network.factory.bean.FactoryInfoBean;
import com.mergbw.core.network.factory.bean.FactoryProductInfoBean;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FactoryDataApi {
    @GET("/anonymous/activity/{userId}/{siteCode}")
    Observable<BaseResult<List<FactoryActivityInfoBean>>> getFactoryActivityInfo(@Path("userId") int i, @Path("siteCode") String str);

    @GET("/anonymous/user/{userId}")
    Observable<BaseResult<FactoryInfoBean>> getFactoryInfo(@Path("userId") int i);

    @GET("/anonymous/promotion/{userId}/{siteCode}")
    Observable<BaseResult<List<FactoryProductInfoBean>>> getFactoryProductInfo(@Path("userId") int i, @Path("siteCode") String str);
}
