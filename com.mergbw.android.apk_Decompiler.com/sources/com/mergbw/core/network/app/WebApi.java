package com.mergbw.core.network.app;

import com.mergbw.core.network.app.bean.CommonResult;
import com.mergbw.core.network.app.bean.DeviceModelInfo;
import com.mergbw.core.network.app.bean.DeviceOverViewBean;
import com.mergbw.core.network.app.bean.DeviceTypeInfo;
import com.mergbw.core.network.app.bean.FirmwareVersionBean;
import com.mergbw.core.network.app.bean.UserOverViewBean;
import com.mergbw.core.network.app.bean.UserResponse;
import io.reactivex.Observable;
import java.util.List;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface WebApi {
    @POST("/device/addDeviceModel")
    Observable<CommonResult> addDeviceModel(@Body RequestBody requestBody);

    @POST("/device/addDeviceType")
    Observable<CommonResult> addDeviceType(@Body RequestBody requestBody);

    @POST("/track/appTrack")
    Observable<CommonResult> appTrack(@Body RequestBody requestBody);

    @GET("/device/deleteDeviceModel")
    Observable<CommonResult> deleteDeviceModel(@Query("deviceType") int i);

    @GET("/device/deleteDeviceType")
    Observable<CommonResult> deleteDeviceType(@Query("deviceType") int i);

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String str);

    @GET("/version/getFirmwareVersionInfo")
    Observable<CommonResult<FirmwareVersionBean>> getAppVersionInfo(@Query("deviceType") int i);

    @GET("/device/deviceModelList")
    Observable<CommonResult<List<DeviceModelInfo>>> getDeviceModelList();

    @GET("/trackData/deviceOverView")
    Observable<CommonResult<DeviceOverViewBean>> getDeviceOverView();

    @GET("/device/deviceTypeList")
    Observable<CommonResult<List<DeviceTypeInfo>>> getDeviceTypeList();

    @GET("/trackData/userOverView")
    Observable<CommonResult<UserOverViewBean>> getUserOverView();

    @POST("/user/getVerifyCode")
    Observable<CommonResult> getVerifyCode(@Body RequestBody requestBody);

    @POST("/user/loginByPassword")
    Observable<CommonResult<UserResponse>> loginByPassword(@Body RequestBody requestBody);

    @POST("/user/register")
    Observable<CommonResult<UserResponse>> register(@Body RequestBody requestBody);

    @POST("/user/resetPassword")
    Observable<CommonResult> resetPassword(@Body RequestBody requestBody);

    @POST("/device/updateDeviceModel")
    Observable<CommonResult> updateDeviceModel(@Body RequestBody requestBody);

    @POST("/device/updateDeviceType")
    Observable<CommonResult> updateDeviceType(@Body RequestBody requestBody);

    @POST("/user/updateNickname")
    Observable<CommonResult> updateNickname(@Body RequestBody requestBody);

    @Multipart
    @POST("/user/uploadIcon")
    Observable<CommonResult<UserResponse>> uploadIcon(@PartMap Map<String, RequestBody> map, @Part MultipartBody.Part part);
}
