package com.mergbw.core.bean;

import com.mergbw.core.RGBMode;

public class SceneData {
    private int BValue;
    private int GValue;
    private int RGBPercent;
    private int RValue;
    private int WhitePercent;
    private int iconRes;
    private boolean isPlaying;
    private boolean isSelected;
    private RGBMode mode;
    private int sceneIndex;
    private String sceneName;
    private int sceneNameRes;
    private int speed;

    public SceneData() {
    }

    public SceneData(int i, int i2, int i3, int i4) {
        setSceneNameRes(i);
        setIconRes(i2);
        setSceneIndex(i3);
        setSpeed(i4);
    }

    public SceneData(String str, int i) {
        setSceneName(str);
        setSceneIndex(i);
    }

    public String getSceneName() {
        return this.sceneName;
    }

    public void setSceneName(String str) {
        this.sceneName = str;
    }

    public int getSceneNameRes() {
        return this.sceneNameRes;
    }

    public void setSceneNameRes(int i) {
        this.sceneNameRes = i;
    }

    public int getIconRes() {
        return this.iconRes;
    }

    public void setIconRes(int i) {
        this.iconRes = i;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int i) {
        this.speed = i;
    }

    public int getSceneIndex() {
        return this.sceneIndex;
    }

    public void setSceneIndex(int i) {
        this.sceneIndex = i;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public SceneData setRGBValue(int i, int i2, int i3) {
        setRValue(i);
        setGValue(i2);
        setBValue(i3);
        return this;
    }

    public int getRValue() {
        return this.RValue;
    }

    public void setRValue(int i) {
        this.RValue = i;
    }

    public int getGValue() {
        return this.GValue;
    }

    public void setGValue(int i) {
        this.GValue = i;
    }

    public int getBValue() {
        return this.BValue;
    }

    public void setBValue(int i) {
        this.BValue = i;
    }

    public SceneData setLightPercent(int i, int i2) {
        setRGBPercent(i);
        setWhitePercent(i2);
        return this;
    }

    public int getRGBPercent() {
        return this.RGBPercent;
    }

    public void setRGBPercent(int i) {
        this.RGBPercent = i;
    }

    public int getWhitePercent() {
        return this.WhitePercent;
    }

    public void setWhitePercent(int i) {
        this.WhitePercent = i;
    }

    public RGBMode getMode() {
        return this.mode;
    }

    public SceneData setMode(RGBMode rGBMode) {
        this.mode = rGBMode;
        return this;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void setPlaying(boolean z) {
        this.isPlaying = z;
    }
}
