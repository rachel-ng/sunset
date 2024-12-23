package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.MotionWidget;

public class ViewState {
    public int bottom;
    public int left;
    public int right;
    public float rotation;

    /* renamed from: top  reason: collision with root package name */
    public int f17top;

    public void getState(MotionWidget motionWidget) {
        this.left = motionWidget.getLeft();
        this.f17top = motionWidget.getTop();
        this.right = motionWidget.getRight();
        this.bottom = motionWidget.getBottom();
        this.rotation = (float) ((int) motionWidget.getRotationZ());
    }

    public int width() {
        return this.right - this.left;
    }

    public int height() {
        return this.bottom - this.f17top;
    }
}
