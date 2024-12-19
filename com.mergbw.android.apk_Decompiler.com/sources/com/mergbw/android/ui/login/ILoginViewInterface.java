package com.mergbw.android.ui.login;

public interface ILoginViewInterface {
    void onLoginResult(boolean z, String str);

    void onNetError();

    void onRegisterResult(boolean z, String str);

    void onResetResult(boolean z, String str);

    void onSmsCodeResult(boolean z, String str);
}
