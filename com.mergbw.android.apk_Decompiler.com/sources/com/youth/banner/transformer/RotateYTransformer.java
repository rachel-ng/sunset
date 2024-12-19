package com.youth.banner.transformer;

import android.view.View;

public class RotateYTransformer extends BasePageTransformer {
    private static final float DEFAULT_MAX_ROTATE = 35.0f;
    private float mMaxRotate;

    public RotateYTransformer() {
        this.mMaxRotate = DEFAULT_MAX_ROTATE;
    }

    public RotateYTransformer(float f) {
        this.mMaxRotate = f;
    }

    public void transformPage(View view, float f) {
        view.setPivotY((float) (view.getHeight() / 2));
        if (f < -1.0f) {
            view.setRotationY(this.mMaxRotate * -1.0f);
            view.setPivotX((float) view.getWidth());
        } else if (f <= 1.0f) {
            view.setRotationY(this.mMaxRotate * f);
            if (f < 0.0f) {
                view.setPivotX(((float) view.getWidth()) * (((-f) * 0.5f) + 0.5f));
                view.setPivotX((float) view.getWidth());
                return;
            }
            view.setPivotX(((float) view.getWidth()) * 0.5f * (1.0f - f));
            view.setPivotX(0.0f);
        } else {
            view.setRotationY(this.mMaxRotate * 1.0f);
            view.setPivotX(0.0f);
        }
    }
}
