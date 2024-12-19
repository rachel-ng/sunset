package com.mergbw.core.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ColorCheckableView extends View {
    private boolean check;
    private int color;
    private Paint mPaint;

    public ColorCheckableView(Context context) {
        super(context);
        this.color = -4638209;
        this.check = false;
    }

    public ColorCheckableView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.color = -4638209;
        this.check = false;
        this.mPaint = new Paint(1);
    }

    public void setColor(int i) {
        this.color = i;
        invalidate();
    }

    public void setCheck(boolean z) {
        this.check = z;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = (float) (getWidth() / 2);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.color);
        canvas.drawCircle(width, width, (float) ((getWidth() * 3) / 8), this.mPaint);
        if (this.check) {
            int width2 = getWidth() / 20;
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth((float) width2);
            float width3 = (float) ((getWidth() / 2) - width2);
            float f = (float) (width2 / 2);
            float f2 = (width - width3) - f;
            float f3 = width + width3 + f;
            canvas.drawArc(new RectF(f2, f2, f3, f3), 360.0f, 360.0f, false, this.mPaint);
        }
    }
}
