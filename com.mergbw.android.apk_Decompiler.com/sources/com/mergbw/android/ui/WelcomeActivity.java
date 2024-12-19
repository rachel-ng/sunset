package com.mergbw.android.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.mergbw.android.UserinfoManage;
import com.mergbw.android.databinding.ActivityWelcomeBinding;
import com.mergbw.android.google.GoogleOpenAdManager;
import com.mergbw.android.google.GoogleRemoteConfigManager;
import com.mergbw.android.google.GoogleTrackManager;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.core.track.TrackManager;
import com.mergbw.core.ui.BaseActivity;

public class WelcomeActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public final Runnable goToMain = new WelcomeActivity$$ExternalSyntheticLambda0(this);
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public boolean needShowAd = false;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) ActivityWelcomeBinding.inflate(getLayoutInflater()).getRoot());
        checkAd();
    }

    private /* synthetic */ void lambda$onCreate$0() {
        JumpManager.getInstance().jumpToHomePage();
        finish();
    }

    private void checkAd() {
        long welcomeShowTime = GoogleRemoteConfigManager.getInstance().getWelcomeShowTime();
        if (!UserinfoManage.getInstance().isReceive()) {
            this.mHandler.postDelayed(this.goToMain, welcomeShowTime);
        } else if (GoogleRemoteConfigManager.getInstance().isShowOpenAdInFirstPage()) {
            this.needShowAd = true;
            GoogleOpenAdManager.getInstance().showAdIfAvailable(this, new GoogleOpenAdManager.OnShowAdCompleteListener() {
                public void onShowAdFailed() {
                    boolean unused = WelcomeActivity.this.needShowAd = false;
                }

                public void onShowAdSuccess() {
                    boolean unused = WelcomeActivity.this.needShowAd = false;
                    WelcomeActivity.this.mHandler.removeCallbacks(WelcomeActivity.this.goToMain);
                }

                public void onShowAdComplete() {
                    WelcomeActivity.this.goToMain();
                }
            });
            this.mHandler.postDelayed(this.goToMain, welcomeShowTime);
        } else {
            this.mHandler.postDelayed(this.goToMain, welcomeShowTime);
        }
    }

    /* access modifiers changed from: private */
    public void goToMain() {
        TrackManager.getInstance().launcherTrack(!UserinfoManage.getInstance().isReceive());
        GoogleTrackManager.getInstance().launcherTrack(!UserinfoManage.getInstance().isReceive());
        if (!UserinfoManage.getInstance().isReceive()) {
            JumpManager.getInstance().jumpToFirstCheck();
            finish();
        } else if (this.needShowAd) {
            this.needShowAd = false;
            GoogleOpenAdManager.getInstance().showAdIfAvailable(this, new GoogleOpenAdManager.OnShowAdCompleteListener() {
                public void onShowAdFailed() {
                    JumpManager.getInstance().jumpToHomePage();
                    WelcomeActivity.this.finish();
                }

                public void onShowAdSuccess() {
                    JumpManager.getInstance().jumpToHomePage();
                    WelcomeActivity.this.finish();
                }

                public void onShowAdComplete() {
                    JumpManager.getInstance().jumpToHomePage();
                    WelcomeActivity.this.finish();
                }
            });
        } else {
            JumpManager.getInstance().jumpToHomePage();
            finish();
        }
    }
}
