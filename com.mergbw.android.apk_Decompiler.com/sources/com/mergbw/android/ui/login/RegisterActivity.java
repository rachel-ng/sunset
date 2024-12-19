package com.mergbw.android.ui.login;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.mergbw.android.databinding.ActivityRegisterBinding;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.utils.StringUtils;

public class RegisterActivity extends BaseActivity implements ILoginViewInterface {
    private ActivityRegisterBinding mBinding;
    private String mEmail;
    private LoginViewModel mViewModel;

    public void onLoginResult(boolean z, String str) {
    }

    public void onNetError() {
    }

    public void onResetResult(boolean z, String str) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityRegisterBinding inflate = ActivityRegisterBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        LoginViewModel loginViewModel = (LoginViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(LoginViewModel.class);
        this.mViewModel = loginViewModel;
        loginViewModel.setListener(this);
        initViews();
    }

    private void initViews() {
        this.mBinding.ivBack.setOnClickListener(new RegisterActivity$$ExternalSyntheticLambda0(this));
        this.mBinding.btnNext.setOnClickListener(new RegisterActivity$$ExternalSyntheticLambda1(this));
        this.mBinding.btnSubmit.setOnClickListener(new RegisterActivity$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-login-RegisterActivity  reason: not valid java name */
    public /* synthetic */ void m867lambda$initViews$0$commergbwandroiduiloginRegisterActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-android-ui-login-RegisterActivity  reason: not valid java name */
    public /* synthetic */ void m868lambda$initViews$1$commergbwandroiduiloginRegisterActivity(View view) {
        String obj = this.mBinding.etEmail.getText().toString();
        this.mEmail = obj;
        if (StringUtils.checkEmail(obj)) {
            this.mBinding.tvEmialError.setVisibility(4);
            this.mBinding.viewEmailDivider.setBackgroundColor(Color.parseColor("#33979797"));
            this.mViewModel.getVerifyCode(this.mEmail);
            showWait("发送验证码中");
            return;
        }
        this.mBinding.tvEmialError.setVisibility(0);
        this.mBinding.viewEmailDivider.setBackgroundColor(Color.parseColor("#66E1526D"));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-android-ui-login-RegisterActivity  reason: not valid java name */
    public /* synthetic */ void m869lambda$initViews$2$commergbwandroiduiloginRegisterActivity(View view) {
        doRegister();
    }

    private void doRegister() {
        String obj = this.mBinding.etVerify.getText().toString();
        if (!StringUtils.checkVerifyCode(obj)) {
            this.mBinding.tvVerifyError.setVisibility(0);
            this.mBinding.viewVerifyDvider.setBackgroundColor(Color.parseColor("#66E1526D"));
            return;
        }
        String obj2 = this.mBinding.etPassword.getText().toString();
        String obj3 = this.mBinding.etPasswordAgain.getText().toString();
        if (!StringUtils.checkPassword(obj2)) {
            this.mBinding.tvPasswordError.setVisibility(0);
            this.mBinding.viewPasswordDivider.setBackgroundColor(Color.parseColor("#66E1526D"));
        } else if (!obj2.equals(obj3)) {
            this.mBinding.tvPasswordAgainError.setVisibility(0);
            this.mBinding.viewPasswordAgainDivider.setBackgroundColor(Color.parseColor("#66E1526D"));
        } else {
            this.mViewModel.register(this.mEmail, obj2, obj);
            showWait("注册中");
        }
    }

    public void onSmsCodeResult(boolean z, String str) {
        hideWait();
        if (z) {
            showToast("验证码发送成功");
            this.mBinding.layoutEmail.setVisibility(8);
            this.mBinding.layoutVerify.setVisibility(0);
            return;
        }
        showToast(str);
    }

    public void onRegisterResult(boolean z, String str) {
        hideWait();
        if (z) {
            finish();
        } else {
            showToast(str);
        }
    }
}
