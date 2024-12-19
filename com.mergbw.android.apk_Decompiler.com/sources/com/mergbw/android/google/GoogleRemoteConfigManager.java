package com.mergbw.android.google;

import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.ConfigUpdate;
import com.google.firebase.remoteconfig.ConfigUpdateListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.mergbw.android.R;
import com.mergbw.core.utils.MeRGBWLog;

public class GoogleRemoteConfigManager {
    private static final String SHOW_BANNER_AD_KEY = "showBannerAd";
    private static final String SHOW_OPEN_AD_IN_FIRST_PAGE_KEY = "showOpenAdInFirstPage";
    private static final String SHOW_OPEN_AD_KEY = "showOpenAd";
    private static final String SHOW_OPEN_AD_TIME_LIMIT_KEY = "showAdLimit";
    private static final String WELCOME_SHOW_TIME_KEY = "welcomeShowTime";
    private static GoogleRemoteConfigManager instance;
    /* access modifiers changed from: private */
    public FirebaseRemoteConfig mFirebaseRemoteConfig;

    public static GoogleRemoteConfigManager getInstance() {
        if (instance == null) {
            instance = new GoogleRemoteConfigManager();
        }
        return instance;
    }

    public void init() {
        this.mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        this.mFirebaseRemoteConfig.setConfigSettingsAsync(new FirebaseRemoteConfigSettings.Builder().build());
        this.mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);
        this.mFirebaseRemoteConfig.addOnConfigUpdateListener(new ConfigUpdateListener() {
            public void onUpdate(ConfigUpdate configUpdate) {
                MeRGBWLog.i("GoogleRemoteConfigManager ConfigUpdateListener Updated keys: " + configUpdate.getUpdatedKeys());
                GoogleRemoteConfigManager.this.mFirebaseRemoteConfig.activate().addOnCompleteListener(new GoogleRemoteConfigManager$1$$ExternalSyntheticLambda0());
            }

            static /* synthetic */ void lambda$onUpdate$0(Task task) {
                if (task.isSuccessful()) {
                    boolean booleanValue = ((Boolean) task.getResult()).booleanValue();
                    MeRGBWLog.i("GoogleRemoteConfigManager ConfigUpdateListener updated: " + booleanValue);
                    return;
                }
                MeRGBWLog.i("GoogleRemoteConfigManager ConfigUpdateListener error!");
            }

            public void onError(FirebaseRemoteConfigException firebaseRemoteConfigException) {
                MeRGBWLog.e("GoogleRemoteConfigManager ConfigUpdateListener error with code: " + firebaseRemoteConfigException.getCode());
            }
        });
        this.mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(new GoogleRemoteConfigManager$$ExternalSyntheticLambda0());
    }

    static /* synthetic */ void lambda$init$0(Task task) {
        if (task.isSuccessful()) {
            boolean booleanValue = ((Boolean) task.getResult()).booleanValue();
            MeRGBWLog.i("GoogleRemoteConfigManager fetchAndActivate updated: " + booleanValue);
            return;
        }
        MeRGBWLog.i("GoogleRemoteConfigManager fetchAndActivate error!");
    }

    public boolean isShowOpenAd() {
        boolean z = this.mFirebaseRemoteConfig.getBoolean(SHOW_OPEN_AD_KEY);
        MeRGBWLog.i("GoogleRemoteConfigManager isShowOpenAd: " + z);
        return z;
    }

    public boolean isShowOpenAdInFirstPage() {
        boolean z = this.mFirebaseRemoteConfig.getBoolean(SHOW_OPEN_AD_IN_FIRST_PAGE_KEY);
        MeRGBWLog.i("GoogleRemoteConfigManager isShowOpenAdInFirstPage: " + z);
        return z;
    }

    public int getShowAdHourLimit() {
        int i = (int) this.mFirebaseRemoteConfig.getDouble(SHOW_OPEN_AD_TIME_LIMIT_KEY);
        MeRGBWLog.i("GoogleRemoteConfigManager showAdHourLimit: " + i);
        return i;
    }

    public long getWelcomeShowTime() {
        long j = this.mFirebaseRemoteConfig.getLong(WELCOME_SHOW_TIME_KEY);
        MeRGBWLog.i("GoogleRemoteConfigManager welcomeShowTime: " + j);
        return j;
    }

    public boolean isShowBannerAd() {
        boolean z = this.mFirebaseRemoteConfig.getBoolean(SHOW_BANNER_AD_KEY);
        MeRGBWLog.i("GoogleRemoteConfigManager isShowBannerAd: " + z);
        return z;
    }
}
