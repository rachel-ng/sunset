package com.mergbw.core.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class GradientView extends View {
    private int[] colors;
    private Paint mPaint;

    public GradientView(Context context) {
        super(context);
    }

    public GradientView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPaint = new Paint(1);
    }

    public void setColors(int[] iArr) {
        this.colors = iArr;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int[] iArr = this.colors;
        if (iArr != null && iArr.length >= 1) {
            float height = (float) (((double) getHeight()) * 0.11d);
            RectF rectF = new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.mPaint.setShader(new LinearGradient(0.0f, 0.0f, (float) getWidth(), 0.0f, this.colors, (float[]) null, Shader.TileMode.MIRROR));
            canvas.drawRoundRect(rectF, height, height, this.mPaint);
        }
    }
}
