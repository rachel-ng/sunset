package com.youth.banner.transformer;

import android.view.View;

public class RotateUpPageTransformer extends BasePageTransformer {
    private static final float DEFAULT_MAX_ROTATE = 15.0f;
    private float mMaxRotate;

    public RotateUpPageTransformer() {
        this.mMaxRotate = DEFAULT_MAX_ROTATE;
    }

    public RotateUpPageTransformer(float f) {
        this.mMaxRotate = f;
    }

    public void transformPage(View view, float f) {
        if (f < -1.0f) {
            view.setRotation(this.mMaxRotate);
            view.setPivotX((float) view.getWidth());
            view.setPivotY(0.0f);
        } else if (f > 1.0f) {
            view.setRotation(-this.mMaxRotate);
            view.setPivotX(0.0f);
            view.setPivotY(0.0f);
        } else if (f < 0.0f) {
            view.setPivotX(((float) view.getWidth()) * (((-f) * 0.5f) + 0.5f));
            view.setPivotY(0.0f);
            view.setRotation((-this.mMaxRotate) * f);
        } else {
            view.setPivotX(((float) view.getWidth()) * 0.5f * (1.0f - f));
            view.setPivotY(0.0f);
            view.setRotation((-this.mMaxRotate) * f);
        }
    }
}
