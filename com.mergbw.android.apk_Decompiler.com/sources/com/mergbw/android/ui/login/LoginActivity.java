package com.mergbw.android.ui.login;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.mergbw.android.R;
import com.mergbw.android.databinding.ActivityLoginBinding;
import com.mergbw.core.event.EventMessage;
import com.mergbw.core.event.GlobalEvent;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.utils.MeRGBWLog;
import com.mergbw.core.utils.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginActivity extends BaseActivity implements ILoginViewInterface {
    private ActivityLoginBinding mBinding;
    private LoginViewModel mViewModel;

    public void onNetError() {
    }

    public void onRegisterResult(boolean z, String str) {
    }

    public void onResetResult(boolean z, String str) {
    }

    public void onSmsCodeResult(boolean z, String str) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityLoginBinding inflate = ActivityLoginBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        LoginViewModel loginViewModel = (LoginViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(LoginViewModel.class);
        this.mViewModel = loginViewModel;
        loginViewModel.setListener(this);
        initViews();
        EventBus.getDefault().register(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initViews() {
        this.mBinding.ivBack.setOnClickListener(new LoginActivity$$ExternalSyntheticLambda0(this));
        this.mBinding.btnRegister.setOnClickListener(new LoginActivity$$ExternalSyntheticLambda1());
        this.mBinding.tvForgetPassword.setOnClickListener(new LoginActivity$$ExternalSyntheticLambda2());
        this.mBinding.btnLogin.setOnClickListener(new LoginActivity$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-login-LoginActivity  reason: not valid java name */
    public /* synthetic */ void m865lambda$initViews$0$commergbwandroiduiloginLoginActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$3$com-mergbw-android-ui-login-LoginActivity  reason: not valid java name */
    public /* synthetic */ void m866lambda$initViews$3$commergbwandroiduiloginLoginActivity(View view) {
        doLogin();
    }

    private void doLogin() {
        String obj = this.mBinding.etUsername.getText().toString();
        if (!StringUtils.checkEmail(obj)) {
            this.mBinding.tvEmailError.setVisibility(0);
            this.mBinding.viewEmailDivider.setBackgroundColor(Color.parseColor("#66E1526D"));
            return;
        }
        String obj2 = this.mBinding.etPassword.getText().toString();
        if (!StringUtils.checkPassword(obj2)) {
            this.mBinding.tvPasswordError.setVisibility(0);
            this.mBinding.viewPasswordDivider.setBackgroundColor(Color.parseColor("#66E1526D"));
            return;
        }
        this.mViewModel.login(obj, obj2);
        showWait(getString(R.string.logging_in));
    }

    public void onLoginResult(boolean z, String str) {
        if (z) {
            showToast("登录成功");
            hideWait();
            finish();
            return;
        }
        showToast(str);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage eventMessage) {
        MeRGBWLog.e("onEvent: " + eventMessage.getAction());
        if (GlobalEvent.USER_REGISTER_SUCCESS.equals(eventMessage.getAction())) {
            finish();
        }
    }
}
