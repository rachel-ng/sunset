package com.mergbw.core.network.app.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mergbw.core.network.BasePresenter;
import com.mergbw.core.network.ErrorCodeUtils;
import com.mergbw.core.network.app.NetworkCallback;
import com.mergbw.core.network.app.NetworkHelper;
import com.mergbw.core.network.app.WebApi;
import com.mergbw.core.network.app.bean.CommonResult;
import com.mergbw.core.network.app.bean.UserResponse;
import com.mergbw.core.track.bean.TrackInfoBean;
import com.mergbw.core.utils.MeRGBWLog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UserDataPresenter extends BasePresenter {
    /* access modifiers changed from: private */
    public final NetworkCallback mListener;
    private final WebApi mWebApi = NetworkHelper.getInstance().getApi();

    public UserDataPresenter(NetworkCallback networkCallback) {
        this.mListener = networkCallback;
    }

    public void getVerifyCode(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("username", (Object) str);
        this.mWebApi.getVerifyCode(RequestBody.Companion.create(JSON.toJSONString(jSONObject), MediaType.parse("application/json; charset=utf-8"))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult commonResult) {
                if (UserDataPresenter.this.mListener != null) {
                    UserDataPresenter.this.mListener.onGetSMSCodeResult(commonResult.isSuccess(), ErrorCodeUtils.getErrorMsg(commonResult.getResultCode()));
                }
            }

            public void onError(Throwable th) {
                if (UserDataPresenter.this.mListener != null) {
                    UserDataPresenter.this.mListener.onGetSMSCodeResult(false, "网络错误");
                }
            }
        });
    }

    public void register(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("username", (Object) str);
        jSONObject.put("password", (Object) str2);
        jSONObject.put("verifyCode", (Object) str3);
        this.mWebApi.register(RequestBody.Companion.create(JSON.toJSONString(jSONObject), MediaType.parse("application/json; charset=utf-8"))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult<UserResponse>>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult<UserResponse> commonResult) {
                if (UserDataPresenter.this.mListener != null) {
                    UserDataPresenter.this.mListener.onRegisterResult(commonResult.isSuccess(), ErrorCodeUtils.getErrorMsg(commonResult.getResultCode()), commonResult.getData());
                }
            }

            public void onError(Throwable th) {
                if (UserDataPresenter.this.mListener != null) {
                    UserDataPresenter.this.mListener.onRegisterResult(false, "网络错误", (UserResponse) null);
                }
            }
        });
    }

    public void login(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("username", (Object) str);
        jSONObject.put("password", (Object) str2);
        this.mWebApi.loginByPassword(RequestBody.Companion.create(JSON.toJSONString(jSONObject), MediaType.parse("application/json; charset=utf-8"))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult<UserResponse>>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult<UserResponse> commonResult) {
                if (UserDataPresenter.this.mListener != null) {
                    UserDataPresenter.this.mListener.onLoginResult(commonResult.isSuccess(), ErrorCodeUtils.getErrorMsg(commonResult.getResultCode()), commonResult.getData());
                }
            }

            public void onError(Throwable th) {
                if (UserDataPresenter.this.mListener != null) {
                    UserDataPresenter.this.mListener.onLoginResult(false, "网络错误", (UserResponse) null);
                }
            }
        });
    }

    public void resetPassword(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("username", (Object) str);
        jSONObject.put("password", (Object) str2);
        jSONObject.put("verifyCode", (Object) str3);
        this.mWebApi.resetPassword(RequestBody.Companion.create(JSON.toJSONString(jSONObject), MediaType.parse("application/json; charset=utf-8"))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult commonResult) {
                if (UserDataPresenter.this.mListener != null) {
                    UserDataPresenter.this.mListener.onResetPasswordResult(commonResult.isSuccess(), ErrorCodeUtils.getErrorMsg(commonResult.getResultCode()));
                }
            }

            public void onError(Throwable th) {
                if (UserDataPresenter.this.mListener != null) {
                    UserDataPresenter.this.mListener.onResetPasswordResult(false, "网络错误");
                }
            }
        });
    }

    public void updateNickname(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("username", (Object) str);
        jSONObject.put("nickname", (Object) str2);
        this.mWebApi.updateNickname(RequestBody.Companion.create(JSON.toJSONString(jSONObject), MediaType.parse("application/json; charset=utf-8"))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonResult>() {
            public void onComplete() {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult commonResult) {
                if (UserDataPresenter.this.mListener != null) {
                    UserDataPresenter.this.mListener.onUpdateNicknameResult(commonResult.isSuccess(), ErrorCodeUtils.getErrorMsg(commonResult.getResultCode()));
                }
            }

            public void onError(Throwable th) {
                if (UserDataPresenter.this.mListener != null) {
                    UserDataPresenter.this.mListener.onUpdateNicknameResult(false, "网络错误");
                }
            }
        });
    }

    public void track(TrackInfoBean trackInfoBean) {
        this.mWebApi.appTrack(RequestBody.Companion.create(JSON.toJSONString(trackInfoBean), MediaType.parse("application/json; charset=utf-8"))).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<CommonResult>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(CommonResult commonResult) {
                MeRGBWLog.i("track result: " + commonResult.isSuccess());
            }
        });
    }
}
