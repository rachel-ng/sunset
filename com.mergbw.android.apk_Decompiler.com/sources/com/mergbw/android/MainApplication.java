package com.mergbw.android;

import android.app.Activity;
import android.app.Application;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mergbw.android.google.GoogleOpenAdManager;
import com.mergbw.android.google.GoogleRemoteConfigManager;
import com.mergbw.core.ble.BluetoothMonitorReceiver;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.utils.AssetsCopyer;
import com.mergbw.core.utils.ThreadManager;

public class MainApplication extends Application implements Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {
    private Activity currentActivity;
    private BluetoothMonitorReceiver mReceiver;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onCreate(this, lifecycleOwner);
    }

    public /* synthetic */ void onDestroy(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onDestroy(this, lifecycleOwner);
    }

    public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onPause(this, lifecycleOwner);
    }

    public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onResume(this, lifecycleOwner);
    }

    public /* synthetic */ void onStop(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onStop(this, lifecycleOwner);
    }

    public void onCreate() {
        super.onCreate();
        RGBDeviceManager.getInstance().init();
        ARouter.init(this);
        ThreadManager.getShortPool().execute(new MainApplication$$ExternalSyntheticLambda0(this));
        registerBluetoothReceiver();
        registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        GoogleRemoteConfigManager.getInstance().init();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-mergbw-android-MainApplication  reason: not valid java name */
    public /* synthetic */ void m689lambda$onCreate$0$commergbwandroidMainApplication() {
        AssetsCopyer.releaseAssets(getApplicationContext());
    }

    public void onTerminate() {
        super.onTerminate();
        unregisterReceiver(this.mReceiver);
    }

    private void registerBluetoothReceiver() {
        this.mReceiver = new BluetoothMonitorReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        registerReceiver(this.mReceiver, intentFilter);
    }

    public void onStart(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onStart(this, lifecycleOwner);
        GoogleOpenAdManager.getInstance().showAdIfAvailable(this.currentActivity);
    }

    public void onActivityStarted(Activity activity) {
        if (!GoogleOpenAdManager.getInstance().isShowingAd) {
            this.currentActivity = activity;
        }
    }
}
