package com.mergbw.core.bean;

public class TabItemBean {
    public static final int TAB_ATMOSPHERE = 26;
    public static final int TAB_COLOR_1 = 11;
    public static final int TAB_COLOR_5 = 15;
    public static final int TAB_DIY_1 = 41;
    public static final int TAB_ILLUMINATING = 16;
    public static final int TAB_MUSIC_1 = 31;
    public static final int TAB_MUSIC_5 = 35;
    public static final int TAB_MUSIC_6 = 36;
    public static final int TAB_SCENE_1 = 21;
    public static final int TAB_SCENE_5 = 25;
    public static final int TAB_WHITE_NOISE = 51;
    private int id;
    private int name;

    public TabItemBean() {
    }

    public TabItemBean(int i, int i2) {
        setId(i);
        setName(i2);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getName() {
        return this.name;
    }

    public void setName(int i) {
        this.name = i;
    }
}
