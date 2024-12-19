package com.mergbw.android.ui.login;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.mergbw.android.databinding.ActivityResetPasswordBinding;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.utils.StringUtils;

public class ResetPasswordActivity extends BaseActivity implements ILoginViewInterface {
    private ActivityResetPasswordBinding mBinding;
    private String mEmail;
    private LoginViewModel mViewModel;

    public void onLoginResult(boolean z, String str) {
    }

    public void onNetError() {
    }

    public void onRegisterResult(boolean z, String str) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityResetPasswordBinding inflate = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        LoginViewModel loginViewModel = (LoginViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(LoginViewModel.class);
        this.mViewModel = loginViewModel;
        loginViewModel.setListener(this);
        initViews();
    }

    private void initViews() {
        this.mBinding.ivBack.setOnClickListener(new ResetPasswordActivity$$ExternalSyntheticLambda0(this));
        this.mBinding.btnNext.setOnClickListener(new ResetPasswordActivity$$ExternalSyntheticLambda1(this));
        this.mBinding.btnSubmit.setOnClickListener(new ResetPasswordActivity$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-login-ResetPasswordActivity  reason: not valid java name */
    public /* synthetic */ void m870lambda$initViews$0$commergbwandroiduiloginResetPasswordActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-android-ui-login-ResetPasswordActivity  reason: not valid java name */
    public /* synthetic */ void m871lambda$initViews$1$commergbwandroiduiloginResetPasswordActivity(View view) {
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
    /* renamed from: lambda$initViews$2$com-mergbw-android-ui-login-ResetPasswordActivity  reason: not valid java name */
    public /* synthetic */ void m872lambda$initViews$2$commergbwandroiduiloginResetPasswordActivity(View view) {
        doReset();
    }

    private void doReset() {
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
            showWait("重置中");
        }
    }

    public void onSmsCodeResult(boolean z, String str) {
        hideWait();
        if (z) {
            this.mBinding.layoutEmail.setVisibility(8);
            this.mBinding.layoutVerify.setVisibility(0);
            return;
        }
        showToast(str);
    }

    public void onResetResult(boolean z, String str) {
        hideWait();
        if (z) {
            showToast("重置成功");
            finish();
            return;
        }
        showToast(str);
    }
}
