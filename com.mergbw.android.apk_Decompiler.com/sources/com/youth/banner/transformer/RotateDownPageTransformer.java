package com.youth.banner.transformer;

import android.view.View;

public class RotateDownPageTransformer extends BasePageTransformer {
    private static final float DEFAULT_MAX_ROTATE = 15.0f;
    private float mMaxRotate;

    public RotateDownPageTransformer() {
        this.mMaxRotate = DEFAULT_MAX_ROTATE;
    }

    public RotateDownPageTransformer(float f) {
        this.mMaxRotate = f;
    }

    public void transformPage(View view, float f) {
        if (f < -1.0f) {
            view.setRotation(this.mMaxRotate * -1.0f);
            view.setPivotX((float) view.getWidth());
            view.setPivotY((float) view.getHeight());
        } else if (f > 1.0f) {
            view.setRotation(this.mMaxRotate);
            view.getWidth();
            view.setPivotX((float) 0);
            view.setPivotY((float) view.getHeight());
        } else if (f < 0.0f) {
            view.setPivotX(((float) view.getWidth()) * (((-f) * 0.5f) + 0.5f));
            view.setPivotY((float) view.getHeight());
            view.setRotation(this.mMaxRotate * f);
        } else {
            view.setPivotX(((float) view.getWidth()) * 0.5f * (1.0f - f));
            view.setPivotY((float) view.getHeight());
            view.setRotation(this.mMaxRotate * f);
        }
    }
}
