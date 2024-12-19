package top.defaults.colorpicker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class ColorWheelView extends FrameLayout implements ColorObservable, Updatable {
    private float centerX;
    private float centerY;
    private int currentColor;
    private final PointF currentPoint;
    private final ColorObservableEmitter emitter;
    private final ThrottledTouchEventHandler handler;
    private boolean onlyUpdateOnTouchEventUp;
    private float radius;
    private final ColorWheelSelector selector;
    private float selectorRadiusPx;
    private float stroke;

    public ColorWheelView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ColorWheelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorWheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectorRadiusPx = 42.0f;
        this.currentPoint = new PointF();
        this.currentColor = -65281;
        this.emitter = new ColorObservableEmitter();
        this.handler = new ThrottledTouchEventHandler(this);
        this.stroke = TypedValue.applyDimension(1, 3.0f, Resources.getSystem().getDisplayMetrics());
        this.selectorRadiusPx = getResources().getDisplayMetrics().density * 14.0f;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        ColorWheelPalette colorWheelPalette = new ColorWheelPalette(context);
        int i2 = (int) (this.selectorRadiusPx - (this.stroke / 2.0f));
        colorWheelPalette.setPadding(i2, i2, i2, i2);
        addView(colorWheelPalette, layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        ColorWheelSelector colorWheelSelector = new ColorWheelSelector(context);
        this.selector = colorWheelSelector;
        colorWheelSelector.setSelectorRadiusPx(this.selectorRadiusPx);
        addView(colorWheelSelector, layoutParams2);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int min = Math.min(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(min, 1073741824), View.MeasureSpec.makeMeasureSpec(min, 1073741824));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        int paddingLeft = (i - getPaddingLeft()) - getPaddingRight();
        int paddingTop = (i2 - getPaddingTop()) - getPaddingBottom();
        float min = (((float) Math.min(paddingLeft, paddingTop)) * 0.5f) - this.selectorRadiusPx;
        this.radius = min;
        if (min >= 0.0f) {
            this.centerX = ((float) paddingLeft) * 0.5f;
            this.centerY = ((float) paddingTop) * 0.5f;
            setColor(this.currentColor, false);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                update(motionEvent);
                return true;
            } else if (actionMasked != 2) {
                return super.onTouchEvent(motionEvent);
            }
        }
        this.handler.onTouchEvent(motionEvent);
        return true;
    }

    public void update(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        boolean z = motionEvent.getActionMasked() == 1;
        if (!this.onlyUpdateOnTouchEventUp || z) {
            this.emitter.onColor(getColorAtPoint(x, y), true, z);
        }
        updateSelector(x, y, getColorAtPoint(x, y));
    }

    private int getColorAtPoint(float f, float f2) {
        float f3 = f - this.centerX;
        float f4 = f2 - this.centerY;
        double sqrt = Math.sqrt((double) ((f3 * f3) + (f4 * f4)));
        float[] fArr = {0.0f, 0.0f, 1.0f};
        fArr[0] = ((float) ((Math.atan2((double) f4, (double) (-f3)) / 3.141592653589793d) * 180.0d)) + 180.0f;
        fArr[1] = Math.max(0.0f, Math.min(1.0f, (float) (sqrt / ((double) this.radius))));
        return Color.HSVToColor(fArr);
    }

    public void setOnlyUpdateOnTouchEventUp(boolean z) {
        this.onlyUpdateOnTouchEventUp = z;
    }

    public void setColor(int i, boolean z) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        float f = fArr[1] * this.radius;
        double d = (double) ((float) (((double) (fArr[0] / 180.0f)) * 3.141592653589793d));
        updateSelector((float) ((((double) f) * Math.cos(d)) + ((double) this.centerX)), (float) ((((double) (-f)) * Math.sin(d)) + ((double) this.centerY)), i);
        this.currentColor = i;
        if (!this.onlyUpdateOnTouchEventUp) {
            this.emitter.onColor(i, false, z);
        }
    }

    private void updateSelector(float f, float f2, int i) {
        float f3 = f - this.centerX;
        float f4 = f2 - this.centerY;
        double sqrt = Math.sqrt((double) ((f3 * f3) + (f4 * f4)));
        float f5 = this.radius;
        if (sqrt > ((double) f5)) {
            f3 = (float) (((double) f3) * (((double) f5) / sqrt));
            f4 = (float) (((double) f4) * (((double) f5) / sqrt));
        }
        this.currentPoint.x = f3 + this.centerX;
        this.currentPoint.y = f4 + this.centerY;
        this.selector.setCurrentPoint(this.currentPoint, i);
    }

    public void subscribe(ColorObserver colorObserver) {
        this.emitter.subscribe(colorObserver);
    }

    public void unsubscribe(ColorObserver colorObserver) {
        this.emitter.unsubscribe(colorObserver);
    }

    public int getColor() {
        return this.emitter.getColor();
    }
}
