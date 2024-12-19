package com.mergbw.android.ui;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import com.mergbw.android.databinding.ActivityAboutBinding;
import com.mergbw.android.google.GoogleTrackManager;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.core.track.TrackManager;
import com.mergbw.core.ui.BaseActivity;

public class AboutActivity extends BaseActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityAboutBinding inflate = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView((View) inflate.getRoot());
        inflate.ivBack.setOnClickListener(new AboutActivity$$ExternalSyntheticLambda0(this));
        inflate.layoutPrivacy.setOnClickListener(new AboutActivity$$ExternalSyntheticLambda1());
        inflate.layoutAgreement.setOnClickListener(new AboutActivity$$ExternalSyntheticLambda2());
        inflate.layoutResume.setOnClickListener(new AboutActivity$$ExternalSyntheticLambda3());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-mergbw-android-ui-AboutActivity  reason: not valid java name */
    public /* synthetic */ void m691lambda$onCreate$0$commergbwandroiduiAboutActivity(View view) {
        finish();
    }

    static /* synthetic */ void lambda$onCreate$1(View view) {
        JumpManager.getInstance().jumpToAgreementPage(1);
        TrackManager.getInstance().homeClickTrack(3, 0, 1);
        GoogleTrackManager.getInstance().homeClickTrack(3, 0, 1);
    }

    static /* synthetic */ void lambda$onCreate$2(View view) {
        JumpManager.getInstance().jumpToAgreementPage(2);
        TrackManager.getInstance().homeClickTrack(3, 0, 2);
        GoogleTrackManager.getInstance().homeClickTrack(3, 0, 2);
    }

    public String getVersionName() {
        return getPackageInfo().versionName;
    }

    private PackageInfo getPackageInfo() {
        try {
            return this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 16384);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
