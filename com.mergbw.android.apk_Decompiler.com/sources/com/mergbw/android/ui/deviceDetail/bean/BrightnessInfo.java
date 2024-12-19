package com.mergbw.android.ui.deviceDetail.bean;

public class BrightnessInfo {
    private int brightness;
    private boolean enable;

    public BrightnessInfo(boolean z, int i) {
        this.enable = z;
        this.brightness = i - 5;
    }

    public int getBrightness() {
        return this.brightness;
    }

    public void setBrightness(int i) {
        this.brightness = i;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }
}
