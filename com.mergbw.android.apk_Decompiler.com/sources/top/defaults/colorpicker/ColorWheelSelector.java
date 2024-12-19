package top.defaults.colorpicker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class ColorWheelSelector extends View {
    private int currentColor;
    private PointF currentPoint;
    private final Paint selectorPaint;
    private float selectorRadiusPx;
    private final float strokeWidth;

    public ColorWheelSelector(Context context) {
        this(context, (AttributeSet) null);
    }

    public ColorWheelSelector(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorWheelSelector(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectorRadiusPx = TypedValue.applyDimension(1, 40.0f, Resources.getSystem().getDisplayMetrics());
        this.currentPoint = new PointF();
        this.strokeWidth = TypedValue.applyDimension(1, 3.0f, Resources.getSystem().getDisplayMetrics());
        this.selectorPaint = new Paint(1);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.selectorPaint.setStyle(Paint.Style.STROKE);
        this.selectorPaint.setStrokeWidth(this.strokeWidth);
        this.selectorPaint.setColor(Color.parseColor("#FFF1F1F1"));
        canvas.drawCircle(this.currentPoint.x, this.currentPoint.y, this.selectorRadiusPx - (this.strokeWidth / 2.0f), this.selectorPaint);
        this.selectorPaint.setStyle(Paint.Style.FILL);
        this.selectorPaint.setColor(this.currentColor);
        canvas.drawCircle(this.currentPoint.x, this.currentPoint.y, this.selectorRadiusPx - this.strokeWidth, this.selectorPaint);
    }

    public void setSelectorRadiusPx(float f) {
        this.selectorRadiusPx = f;
    }

    public void setCurrentPoint(PointF pointF, int i) {
        this.currentPoint = pointF;
        this.currentColor = i;
        invalidate();
    }
}
