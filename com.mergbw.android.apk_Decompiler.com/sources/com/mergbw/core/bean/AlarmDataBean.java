package com.mergbw.core.bean;

import java.io.Serializable;

public class AlarmDataBean implements Serializable {
    private boolean isSet;
    private int timeHour;
    private int timeMinute;
    private byte[] weekRepeat;

    public boolean isSet() {
        return this.isSet;
    }

    public void setSet(boolean z) {
        this.isSet = z;
    }

    public int getTimeHour() {
        return this.timeHour;
    }

    public void setTimeHour(int i) {
        this.timeHour = i;
    }

    public int getTimeMinute() {
        return this.timeMinute;
    }

    public void setTimeMinute(int i) {
        this.timeMinute = i;
    }

    public byte[] getWeekRepeat() {
        return this.weekRepeat;
    }

    public void setWeekRepeat(byte[] bArr) {
        this.weekRepeat = bArr;
    }
}
