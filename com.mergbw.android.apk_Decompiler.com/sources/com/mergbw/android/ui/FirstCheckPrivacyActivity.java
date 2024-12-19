package com.mergbw.android.ui;

import android.os.Bundle;
import android.view.View;
import com.mergbw.android.UserinfoManage;
import com.mergbw.android.databinding.ActivityFirstCheckPrivacyBinding;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.core.R;
import com.mergbw.core.ui.BaseActivity;

public class FirstCheckPrivacyActivity extends BaseActivity {
    private boolean isAccept = false;
    private ActivityFirstCheckPrivacyBinding mBinding;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityFirstCheckPrivacyBinding inflate = ActivityFirstCheckPrivacyBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        initViews();
    }

    private void initViews() {
        this.mBinding.btnRefuse.setOnClickListener(new FirstCheckPrivacyActivity$$ExternalSyntheticLambda0(this));
        this.mBinding.ivAcceptPrivacy.setOnClickListener(new FirstCheckPrivacyActivity$$ExternalSyntheticLambda1(this));
        this.mBinding.btnAccept.setOnClickListener(new FirstCheckPrivacyActivity$$ExternalSyntheticLambda2(this));
        this.mBinding.tvPrivacyAgreement.getPaint().setFlags(8);
        this.mBinding.tvPrivacyAgreement.getPaint().setAntiAlias(true);
        this.mBinding.tvPrivacyAgreement.setOnClickListener(new FirstCheckPrivacyActivity$$ExternalSyntheticLambda3());
        this.mBinding.tvUserAgreement.getPaint().setFlags(8);
        this.mBinding.tvUserAgreement.getPaint().setAntiAlias(true);
        this.mBinding.tvUserAgreement.setOnClickListener(new FirstCheckPrivacyActivity$$ExternalSyntheticLambda4());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-FirstCheckPrivacyActivity  reason: not valid java name */
    public /* synthetic */ void m693lambda$initViews$0$commergbwandroiduiFirstCheckPrivacyActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-android-ui-FirstCheckPrivacyActivity  reason: not valid java name */
    public /* synthetic */ void m694lambda$initViews$1$commergbwandroiduiFirstCheckPrivacyActivity(View view) {
        if (this.isAccept) {
            this.isAccept = false;
            this.mBinding.ivAcceptPrivacy.setImageResource(R.mipmap.icon_unchecked_02);
            return;
        }
        this.isAccept = true;
        this.mBinding.ivAcceptPrivacy.setImageResource(R.mipmap.icon_checked_02);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-android-ui-FirstCheckPrivacyActivity  reason: not valid java name */
    public /* synthetic */ void m695lambda$initViews$2$commergbwandroiduiFirstCheckPrivacyActivity(View view) {
        if (!this.isAccept) {
            showToast(getString(com.mergbw.android.R.string.accept_privacy_hint_2));
            return;
        }
        UserinfoManage.getInstance().setIsReceive(true);
        JumpManager.getInstance().jumpToHomePage();
        finish();
    }
}
