package top.defaults.colorpicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;

public abstract class ColorSliderView extends View implements ColorObservable, Updatable {
    protected int baseColor;
    private ColorObserver bindObserver;
    private Paint borderPaint;
    private ColorObservable boundObservable;
    private Paint colorPaint;
    private Path currentSelectorPath;
    protected float currentValue;
    private ColorObservableEmitter emitter;
    private ThrottledTouchEventHandler handler;
    private boolean onlyUpdateOnTouchEventUp;
    private Paint selectorPaint;
    private Path selectorPath;
    protected float selectorSize;

    /* access modifiers changed from: protected */
    public abstract int assembleColor();

    /* access modifiers changed from: protected */
    public abstract void configurePaint(Paint paint);

    /* access modifiers changed from: protected */
    public abstract float resolveValue(int i);

    public ColorSliderView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ColorSliderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorSliderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.baseColor = -1;
        this.currentSelectorPath = new Path();
        this.currentValue = 1.0f;
        this.emitter = new ColorObservableEmitter();
        this.handler = new ThrottledTouchEventHandler(this);
        this.bindObserver = new ColorObserver() {
            public void onColor(int i, boolean z, boolean z2) {
                ColorSliderView.this.setBaseColor(i, z, z2);
            }
        };
        this.colorPaint = new Paint(1);
        Paint paint = new Paint(1);
        this.borderPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.borderPaint.setStrokeWidth(0.0f);
        this.borderPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        Paint paint2 = new Paint(1);
        this.selectorPaint = paint2;
        paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
        Path path = new Path();
        this.selectorPath = path;
        path.setFillType(Path.FillType.WINDING);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        configurePaint(this.colorPaint);
        this.selectorPath.reset();
        this.selectorSize = ((float) i2) * 0.25f;
        this.selectorPath.moveTo(0.0f, 0.0f);
        this.selectorPath.lineTo(this.selectorSize * 2.0f, 0.0f);
        Path path = this.selectorPath;
        float f = this.selectorSize;
        path.lineTo(f, f);
        this.selectorPath.close();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float width = (float) getWidth();
        float f = this.selectorSize;
        Canvas canvas2 = canvas;
        float height = (float) getHeight();
        canvas2.drawRect(f, f, width - f, height, this.colorPaint);
        float f2 = this.selectorSize;
        canvas2.drawRect(f2, f2, width - f2, height, this.borderPaint);
        this.selectorPath.offset(this.currentValue * (width - (this.selectorSize * 2.0f)), 0.0f, this.currentSelectorPath);
        canvas.drawPath(this.currentSelectorPath, this.selectorPaint);
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
        updateValue(motionEvent.getX());
        boolean z = motionEvent.getActionMasked() == 1;
        if (!this.onlyUpdateOnTouchEventUp || z) {
            this.emitter.onColor(assembleColor(), true, z);
        }
    }

    /* access modifiers changed from: package-private */
    public void setBaseColor(int i, boolean z, boolean z2) {
        this.baseColor = i;
        configurePaint(this.colorPaint);
        if (!z) {
            this.currentValue = resolveValue(i);
        } else {
            i = assembleColor();
        }
        if (!this.onlyUpdateOnTouchEventUp) {
            this.emitter.onColor(i, z, z2);
        } else if (z2) {
            this.emitter.onColor(i, z, true);
        }
        invalidate();
    }

    private void updateValue(float f) {
        float f2 = this.selectorSize;
        float width = ((float) getWidth()) - this.selectorSize;
        if (f < f2) {
            f = f2;
        }
        if (f > width) {
            f = width;
        }
        this.currentValue = (f - f2) / (width - f2);
        invalidate();
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

    public void setOnlyUpdateOnTouchEventUp(boolean z) {
        this.onlyUpdateOnTouchEventUp = z;
    }

    public void bind(ColorObservable colorObservable) {
        if (colorObservable != null) {
            colorObservable.subscribe(this.bindObserver);
            setBaseColor(colorObservable.getColor(), true, true);
        }
        this.boundObservable = colorObservable;
    }

    public void unbind() {
        ColorObservable colorObservable = this.boundObservable;
        if (colorObservable != null) {
            colorObservable.unsubscribe(this.bindObserver);
            this.boundObservable = null;
        }
    }
}
