package com.mergbw.android.ui;

import android.os.Bundle;
import android.view.View;
import com.mergbw.android.R;
import com.mergbw.android.databinding.ActivityAgreementBinding;
import com.mergbw.core.ui.BaseActivity;

public class AgreementActivity extends BaseActivity {
    public static final int PRIVACY_AGREEMENT = 1;
    public static final int USER_AGREEMENT = 2;
    private ActivityAgreementBinding mBinding;
    private int mType;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityAgreementBinding inflate = ActivityAgreementBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        this.mType = getIntent().getIntExtra(SessionDescription.ATTR_TYPE, 1);
        initViews();
    }

    private void initViews() {
        this.mBinding.ivBack.setOnClickListener(new AgreementActivity$$ExternalSyntheticLambda0(this));
        this.mBinding.webView.setBackgroundColor(0);
        int i = this.mType;
        String str = "file:android_asset/privacy/privacyAgreementCN.html";
        if (i == 1) {
            this.mBinding.tvTitle.setText(R.string.privacy_agreement);
            if (!getResources().getConfiguration().locale.getLanguage().equals("zh")) {
                str = "file:android_asset/privacy/privacyAgreementEN.html";
            }
        } else if (i == 2) {
            this.mBinding.tvTitle.setText(R.string.user_agreement);
            str = getResources().getConfiguration().locale.getLanguage().equals("zh") ? "file:android_asset/privacy/userAgreementCN.html" : "file:android_asset/privacy/userAgreementEN.html";
        }
        this.mBinding.webView.loadUrl(str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-AgreementActivity  reason: not valid java name */
    public /* synthetic */ void m692lambda$initViews$0$commergbwandroiduiAgreementActivity(View view) {
        finish();
    }
}
