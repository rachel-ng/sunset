package com.mergbw.android.ui.deviceDetail.viewModel;

import android.app.Application;
import android.graphics.Color;
import com.mergbw.android.ui.deviceDetail.bean.BrightnessInfo;
import com.mergbw.core.RGBMode;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.utils.ViewDataUtils;

public class DeviceDetailViewModel6 extends BaseDeviceDetailViewModel {
    private RGBMode mCurrentMode;
    private SceneData mCurrentScene;

    public DeviceDetailViewModel6(Application application) {
        super(application, "DeviceDetailViewModel6");
    }

    /* access modifiers changed from: protected */
    public void onBleResponse(byte b2, byte[] bArr) {
        super.onBleResponse(b2, bArr);
        if (b2 == 0) {
            ViewDataUtils.analyseSyncData(this.mCurrentDevice, bArr);
            this.mDetailViewData.postValue(this.mCurrentDevice);
        }
        if (b2 == 14 && bArr[4] == 0) {
            this.mCurrentDevice.setBound(true);
        }
    }

    public void setColor(int i) {
        super.setColor(i);
        sendData(CommandUtils.getCommand((byte) 3, new byte[]{(byte) (Color.red(i) & 255), (byte) (Color.green(i) & 255), (byte) (Color.blue(i) & 255)}));
        if (this.mCurrentMode != RGBMode.COLOR_MODEL) {
            changeBrightness(100, 0);
            this.mCurrentMode = RGBMode.COLOR_MODEL;
            this.mBrightnessInfo.postValue(new BrightnessInfo(true, 100));
        }
        this.mCurrentScene = null;
    }

    public void setBrightness(int i) {
        super.setBrightness(i);
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
    }

    public void startVoiceData(int i, int i2) {
        super.startVoiceData(i, i2);
        if (this.mCurrentMode != RGBMode.MUSIC_MODEL) {
            changeBrightness(100, 0);
            this.mCurrentMode = RGBMode.MUSIC_MODEL;
            this.mBrightnessInfo.postValue(new BrightnessInfo(true, 100));
        }
        this.mCurrentScene = null;
    }

    public void setIlluminating(SceneData sceneData) {
        this.mCurrentScene = sceneData;
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
    }

    public void setScene(SceneData sceneData) {
        super.setScene(sceneData);
        this.mCurrentScene = sceneData;
        sendData(CommandUtils.getCommand((byte) 6, new byte[]{(byte) (sceneData.getSceneIndex() & 255)}));
        if (this.mCurrentMode != RGBMode.STATIC_MODEL) {
            setWhiteBrightness(0);
            this.mCurrentMode = RGBMode.STATIC_MODEL;
        }
        this.mBrightnessInfo.postValue(new BrightnessInfo(false, 0));
    }

    public void setWarmColor(int i) {
        sendData(CommandUtils.getCommand((byte) 3, new byte[]{(byte) (Color.red(i) & 255), (byte) (Color.green(i) & 255), (byte) (((int) ((((double) (Color.blue(i) - 150)) / 105.0d) * 255.0d)) & 255)}));
        if (this.mCurrentMode != RGBMode.WARM_MODE) {
            changeBrightness(100, 80);
            this.mCurrentMode = RGBMode.WARM_MODE;
            this.mBrightnessInfo.postValue(new BrightnessInfo(true, 100));
        }
        this.mCurrentScene = null;
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
}
