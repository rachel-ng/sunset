package com.mergbw.core.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.mergbw.core.R;

public class SubItemView extends View {
    public static final int DEFAULT_RECT = 0;
    public static final int LEFT_BOTTOM_ARC = 5;
    public static final int LEFT_STRAIGHT = 1;
    public static final int LEFT_TOP_ARC = 4;
    public static final int RIGHT_BOTTOM_ARC = 3;
    public static final int RIGHT_TOP_ARC = 2;
    private int color = ViewCompat.MEASURED_STATE_MASK;
    private final Paint mPaint = new Paint(1);
    private int mType = 0;

    public SubItemView(Context context) {
        super(context);
    }

    public SubItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SubItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SubItemView, i, 0);
        this.mType = obtainStyledAttributes.getInteger(R.styleable.SubItemView_type, 0);
        obtainStyledAttributes.recycle();
    }

    public void setColor(int i) {
        this.color = i;
        invalidate();
    }

    public void setType(int i) {
        this.mType = i;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.color);
        int i = this.mType;
        if (i == 0) {
            drawDefault(canvas);
        } else if (i == 1) {
            drawLeftStraight(canvas);
        } else if (i == 2) {
            drawRightTopArc(canvas);
        } else if (i == 3) {
            drawRightBottomArc(canvas);
        } else if (i == 4) {
            drawLeftTopArc(canvas);
        } else if (i == 5) {
            drawLeftBottomArc(canvas);
        }
    }

    private void drawDefault(Canvas canvas) {
        int width = getWidth();
        canvas.drawRect(0.0f, (float) (width / 4), (float) width, (float) ((width * 3) / 4), this.mPaint);
    }

    private void drawLeftStraight(Canvas canvas) {
        int height = getHeight() / 4;
        float f = (float) height;
        canvas.drawArc(new RectF(0.0f, f, (float) (height * 2), (float) ((getHeight() * 3) / 4)), 90.0f, 180.0f, false, this.mPaint);
        Canvas canvas2 = canvas;
        float f2 = f;
        float f3 = f;
        canvas2.drawRect(f2, f3, (float) getWidth(), (float) ((getHeight() * 3) / 4), this.mPaint);
    }

    private void drawRightTopArc(Canvas canvas) {
        int height = getHeight();
        int i = height / 4;
        float f = (float) i;
        canvas.drawRect(0.0f, f, f, (float) ((height * 3) / 4), this.mPaint);
        int i2 = height / 2;
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth((float) i2);
        int i3 = i2 / 2;
        canvas.drawArc(new RectF((float) (0 - i3), (float) ((height - i) - i3), (float) (i + i + i3), (float) (height + i + i3)), 270.0f, 90.0f, false, this.mPaint);
    }

    private void drawRightBottomArc(Canvas canvas) {
        int height = getHeight();
        int i = height / 4;
        float f = (float) i;
        canvas.drawRect(0.0f, f, f, (float) ((height * 3) / 4), this.mPaint);
        int i2 = height / 2;
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth((float) i2);
        int i3 = i2 / 2;
        canvas.drawArc(new RectF((float) (0 - i3), (float) ((0 - i) - i3), (float) (i + i + i3), (float) (i + i3)), 0.0f, 90.0f, false, this.mPaint);
    }

    private void drawLeftTopArc(Canvas canvas) {
        int height = getHeight();
        int i = (height * 3) / 4;
        float f = (float) i;
        int i2 = height / 4;
        canvas.drawRect(f, (float) i2, (float) height, f, this.mPaint);
        int i3 = height / 2;
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth((float) i3);
        int i4 = i3 / 2;
        canvas.drawArc(new RectF((float) ((i - i2) - i4), (float) ((height - i2) - i4), (float) (i + i2 + i4), (float) (height + i2 + i4)), 180.0f, 90.0f, false, this.mPaint);
    }

    private void drawLeftBottomArc(Canvas canvas) {
        int height = getHeight();
        int i = (height * 3) / 4;
        float f = (float) i;
        int i2 = height / 4;
        canvas.drawRect(f, (float) i2, (float) height, f, this.mPaint);
        int i3 = height / 2;
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth((float) i3);
        int i4 = i3 / 2;
        canvas.drawArc(new RectF((float) ((i - i2) - i4), (float) ((0 - i2) - i4), (float) (i + i2 + i4), (float) (i2 + i4)), 90.0f, 90.0f, false, this.mPaint);
    }
}
