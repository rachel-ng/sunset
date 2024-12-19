package com.mergbw.android.ui.login;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.mergbw.android.UserinfoManage;
import com.mergbw.core.event.EventMessage;
import com.mergbw.core.event.GlobalEvent;
import com.mergbw.core.network.app.NetworkCallback;
import com.mergbw.core.network.app.bean.UserResponse;
import com.mergbw.core.network.app.presenter.UserDataPresenter;
import org.greenrobot.eventbus.EventBus;

public class LoginViewModel extends AndroidViewModel {
    /* access modifiers changed from: private */
    public ILoginViewInterface mListener;
    private final UserDataPresenter mWebPresenter = new UserDataPresenter(new NetworkCallback() {
        public void onGetSMSCodeResult(boolean z, String str) {
            if (LoginViewModel.this.mListener != null) {
                LoginViewModel.this.mListener.onSmsCodeResult(z, (String) null);
            }
        }

        public void onRegisterResult(boolean z, String str, UserResponse userResponse) {
            if (z) {
                UserinfoManage.getInstance().loginSuccess(userResponse);
                EventBus.getDefault().post(new EventMessage(GlobalEvent.USER_REGISTER_SUCCESS).setData(userResponse));
            }
            if (LoginViewModel.this.mListener != null) {
                LoginViewModel.this.mListener.onRegisterResult(z, (String) null);
            }
        }

        public void onLoginResult(boolean z, String str, UserResponse userResponse) {
            if (z) {
                UserinfoManage.getInstance().loginSuccess(userResponse);
                EventBus.getDefault().post(new EventMessage(GlobalEvent.USER_LOGIN_SUCCESS).setData(userResponse));
            }
            if (LoginViewModel.this.mListener != null) {
                LoginViewModel.this.mListener.onLoginResult(z, (String) null);
            }
        }

        public void onResetPasswordResult(boolean z, String str) {
            if (LoginViewModel.this.mListener != null) {
                LoginViewModel.this.mListener.onResetResult(z, (String) null);
            }
        }
    });

    public LoginViewModel(Application application) {
        super(application);
    }

    public void setListener(ILoginViewInterface iLoginViewInterface) {
        this.mListener = iLoginViewInterface;
    }

    public void getVerifyCode(String str) {
        this.mWebPresenter.getVerifyCode(str);
    }

    public void register(String str, String str2, String str3) {
        this.mWebPresenter.register(str, str2, str3);
    }

    public void login(String str, String str2) {
        this.mWebPresenter.login(str, str2);
    }

    public void resetPassword(String str, String str2, String str3) {
        this.mWebPresenter.resetPassword(str, str2, str3);
    }
}
