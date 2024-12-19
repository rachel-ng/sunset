package com.mergbw.core.network.app;

import com.mergbw.core.network.app.bean.UserResponse;

public abstract class NetworkCallback {
    public void onGetSMSCodeResult(boolean z, String str) {
    }

    public void onLoginResult(boolean z, String str, UserResponse userResponse) {
    }

    public void onRegisterResult(boolean z, String str, UserResponse userResponse) {
    }

    public void onResetPasswordResult(boolean z, String str) {
    }

    public void onUpdateNicknameResult(boolean z, String str) {
    }
}
