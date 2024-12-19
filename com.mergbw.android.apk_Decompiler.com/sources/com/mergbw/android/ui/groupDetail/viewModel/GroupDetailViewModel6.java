package com.mergbw.android.ui.groupDetail.viewModel;

import android.app.Application;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import com.mergbw.android.ui.deviceDetail.bean.BrightnessInfo;
import com.mergbw.core.RGBMode;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.utils.ThreadManager;

public class GroupDetailViewModel6 extends BaseGroupDetailViewModel {
    private RGBMode mCurrentMode;
    private SceneData mCurrentScene;

    public GroupDetailViewModel6(Application application) {
        super(application);
    }

    public void setRGBColor(int i) {
        super.setRGBColor(i);
        ThreadManager.getShortPool().execute(new GroupDetailViewModel6$$ExternalSyntheticLambda5(this, i));
        this.mCurrentScene = null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setRGBColor$0$com-mergbw-android-ui-groupDetail-viewModel-GroupDetailViewModel6  reason: not valid java name */
    public /* synthetic */ void m835lambda$setRGBColor$0$commergbwandroiduigroupDetailviewModelGroupDetailViewModel6(int i) {
        this.mShowLoading.postValue(true);
        sendData(CommandUtils.getCommand((byte) 3, new byte[]{(byte) (Color.red(i) & 255), (byte) (Color.green(i) & 255), (byte) (Color.blue(i) & 255)}));
        if (this.mCurrentMode != RGBMode.COLOR_MODEL) {
            changeBrightness(100, 0);
            this.mCurrentMode = RGBMode.COLOR_MODEL;
            this.mBrightnessInfo.postValue(new BrightnessInfo(true, 100));
        }
        dismissLoading();
    }

    public void setBrightness(int i) {
        super.setBrightness(i);
        ThreadManager.getShortPool().execute(new GroupDetailViewModel6$$ExternalSyntheticLambda0(this, i));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setBrightness$1$com-mergbw-android-ui-groupDetail-viewModel-GroupDetailViewModel6  reason: not valid java name */
    public /* synthetic */ void m833lambda$setBrightness$1$commergbwandroiduigroupDetailviewModelGroupDetailViewModel6(int i) {
        this.mShowLoading.postValue(true);
        if (this.mCurrentMode == RGBMode.WARM_MODE) {
            int i2 = i + 5;
            changeBrightness(i2, (int) (((double) i2) * 0.8d));
        } else if (this.mCurrentMode == RGBMode.DIY_MODEL) {
            SceneData sceneData = this.mCurrentScene;
            if (sceneData != null) {
                int sceneIndex = sceneData.getSceneIndex();
                if (sceneIndex == 1) {
                    setWhiteBrightness(i + 5);
                } else if (sceneIndex == 2) {
                    setRGBBrightness(i + 5);
                } else if (sceneIndex == 3) {
                    int i3 = i + 5;
                    changeBrightness(i3, (int) (((double) i3) * 0.8d));
                } else if (sceneIndex == 4) {
                    int i4 = i + 5;
                    changeBrightness(i4, (int) (((double) i4) * 0.2d));
                }
            }
        } else {
            sendData(CommandUtils.getCommand((byte) 5, new byte[]{(byte) ((i + 5) & 255)}));
        }
        dismissLoading();
    }

    public void startVoiceData(int i, int i2) {
        super.startVoiceData(i, i2);
        if (this.mCurrentMode != RGBMode.MUSIC_MODEL) {
            this.mCurrentMode = RGBMode.MUSIC_MODEL;
            ThreadManager.getShortPool().execute(new GroupDetailViewModel6$$ExternalSyntheticLambda4(this));
        }
        this.mCurrentScene = null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$startVoiceData$2$com-mergbw-android-ui-groupDetail-viewModel-GroupDetailViewModel6  reason: not valid java name */
    public /* synthetic */ void m838lambda$startVoiceData$2$commergbwandroiduigroupDetailviewModelGroupDetailViewModel6() {
        changeBrightness(100, 0);
        this.mBrightnessInfo.postValue(new BrightnessInfo(true, 100));
    }

    public void setIlluminating(SceneData sceneData) {
        this.mCurrentScene = sceneData;
        ThreadManager.getShortPool().execute(new GroupDetailViewModel6$$ExternalSyntheticLambda6(this, sceneData));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setIlluminating$3$com-mergbw-android-ui-groupDetail-viewModel-GroupDetailViewModel6  reason: not valid java name */
    public /* synthetic */ void m834lambda$setIlluminating$3$commergbwandroiduigroupDetailviewModelGroupDetailViewModel6(SceneData sceneData) {
        this.mShowLoading.postValue(true);
        if (sceneData.getMode() == RGBMode.DIY_MODEL) {
            sendData(CommandUtils.getCommand((byte) 3, new byte[]{(byte) sceneData.getRValue(), (byte) sceneData.getGValue(), (byte) sceneData.getBValue()}));
            changeBrightness(sceneData.getRGBPercent(), sceneData.getWhitePercent());
            int rGBPercent = sceneData.getRGBPercent();
            if (sceneData.getSceneIndex() == 1) {
                rGBPercent = sceneData.getWhitePercent();
            }
            this.mBrightnessInfo.postValue(new BrightnessInfo(true, rGBPercent));
            this.mCurrentMode = RGBMode.DIY_MODEL;
        }
        if (sceneData.getMode() == RGBMode.STATIC_MODEL) {
            sendData(CommandUtils.getCommand((byte) 6, new byte[]{(byte) (sceneData.getSceneIndex() & 255)}));
            this.mBrightnessInfo.postValue(new BrightnessInfo(false, 0));
            if (this.mCurrentMode != RGBMode.STATIC_MODEL) {
                setWhiteBrightness(0);
                this.mCurrentMode = RGBMode.STATIC_MODEL;
            }
        }
        dismissLoading();
    }

    public void setScene(SceneData sceneData) {
        super.setScene(sceneData);
        this.mCurrentScene = sceneData;
        ThreadManager.getShortPool().execute(new GroupDetailViewModel6$$ExternalSyntheticLambda2(this, sceneData));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setScene$4$com-mergbw-android-ui-groupDetail-viewModel-GroupDetailViewModel6  reason: not valid java name */
    public /* synthetic */ void m836lambda$setScene$4$commergbwandroiduigroupDetailviewModelGroupDetailViewModel6(SceneData sceneData) {
        this.mShowLoading.postValue(true);
        sendData(CommandUtils.getCommand((byte) 6, new byte[]{(byte) (sceneData.getSceneIndex() & 255)}));
        if (this.mCurrentMode != RGBMode.STATIC_MODEL) {
            setWhiteBrightness(0);
            this.mCurrentMode = RGBMode.STATIC_MODEL;
        }
        this.mBrightnessInfo.postValue(new BrightnessInfo(false, 0));
        dismissLoading();
    }

    public void setWarmColor(int i) {
        ThreadManager.getShortPool().execute(new GroupDetailViewModel6$$ExternalSyntheticLambda3(this, i));
        this.mCurrentScene = null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setWarmColor$5$com-mergbw-android-ui-groupDetail-viewModel-GroupDetailViewModel6  reason: not valid java name */
    public /* synthetic */ void m837lambda$setWarmColor$5$commergbwandroiduigroupDetailviewModelGroupDetailViewModel6(int i) {
        this.mShowLoading.postValue(true);
        sendData(CommandUtils.getCommand((byte) 3, new byte[]{(byte) (Color.red(i) & 255), (byte) (Color.green(i) & 255), (byte) (((int) ((((double) (Color.blue(i) - 150)) / 105.0d) * 255.0d)) & 255)}));
        if (this.mCurrentMode != RGBMode.WARM_MODE) {
            changeBrightness(100, 80);
            this.mCurrentMode = RGBMode.WARM_MODE;
            this.mBrightnessInfo.postValue(new BrightnessInfo(true, 100));
        }
        dismissLoading();
    }

    private void changeBrightness(int i, int i2) {
        setWhiteBrightness(i2);
        setRGBBrightness(i);
    }

    private void setRGBBrightness(int i) {
        sendData(CommandUtils.getCommand((byte) 5, new byte[]{(byte) (i & 255)}));
    }

    private void setWhiteBrightness(int i) {
        sendData(CommandUtils.getCommand((byte) 17, new byte[]{(byte) (i & 255)}));
    }

    private void dismissLoading() {
        new Handler(Looper.getMainLooper()).postDelayed(new GroupDetailViewModel6$$ExternalSyntheticLambda1(this), 1000);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$dismissLoading$6$com-mergbw-android-ui-groupDetail-viewModel-GroupDetailViewModel6  reason: not valid java name */
    public /* synthetic */ void m832lambda$dismissLoading$6$commergbwandroiduigroupDetailviewModelGroupDetailViewModel6() {
        this.mShowLoading.postValue(false);
    }
}
