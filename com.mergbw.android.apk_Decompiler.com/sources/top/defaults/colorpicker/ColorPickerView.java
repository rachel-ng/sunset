package top.defaults.colorpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

public class ColorPickerView extends LinearLayout implements ColorObservable {
    private AlphaSliderView alphaSliderView;
    private BrightnessSliderView brightnessSliderView;
    private final ColorWheelView colorWheelView;
    private int initialColor;
    private ColorObservable observableOnDuty;
    List<ColorObserver> observers;
    private boolean onlyUpdateOnTouchEventUp;
    private final int sliderHeight;
    private final int sliderMargin;

    public ColorPickerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorPickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.initialColor = -1;
        this.observers = new ArrayList();
        setOrientation(1);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ColorPickerView);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.ColorPickerView_enableAlpha, false);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.ColorPickerView_enableBrightness, false);
        this.onlyUpdateOnTouchEventUp = obtainStyledAttributes.getBoolean(R.styleable.ColorPickerView_onlyUpdateOnTouchEventUp, false);
        obtainStyledAttributes.recycle();
        ColorWheelView colorWheelView2 = new ColorWheelView(context);
        this.colorWheelView = colorWheelView2;
        int i2 = (int) (getResources().getDisplayMetrics().density * 0.0f);
        this.sliderMargin = 0;
        this.sliderHeight = 0;
        addView(colorWheelView2, new LinearLayout.LayoutParams(-2, -2));
        setEnabledBrightness(z2);
        setEnabledAlpha(z);
        setPadding(i2, i2, i2, i2);
    }

    public void setOnlyUpdateOnTouchEventUp(boolean z) {
        this.onlyUpdateOnTouchEventUp = z;
        updateObservableOnDuty();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = (View.MeasureSpec.getSize(i2) - (getPaddingTop() + getPaddingBottom())) + getPaddingLeft() + getPaddingRight();
        if (this.brightnessSliderView != null) {
            size2 -= this.sliderMargin + this.sliderHeight;
        }
        if (this.alphaSliderView != null) {
            size2 -= this.sliderMargin + this.sliderHeight;
        }
        int min = Math.min(size, size2);
        int paddingLeft = (min - (getPaddingLeft() + getPaddingRight())) + getPaddingTop() + getPaddingBottom();
        if (this.brightnessSliderView != null) {
            paddingLeft += this.sliderMargin + this.sliderHeight;
        }
        if (this.alphaSliderView != null) {
            paddingLeft += this.sliderMargin + this.sliderHeight;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(min, View.MeasureSpec.getMode(i)), View.MeasureSpec.makeMeasureSpec(paddingLeft, View.MeasureSpec.getMode(i2)));
    }

    public void setInitialColor(int i) {
        this.initialColor = i;
        this.colorWheelView.setColor(i, true);
    }

    public void setColor(int i) {
        this.colorWheelView.setColor(i, false);
    }

    public void setEnabledBrightness(boolean z) {
        if (z) {
            if (this.brightnessSliderView == null) {
                this.brightnessSliderView = new BrightnessSliderView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, this.sliderHeight);
                layoutParams.topMargin = this.sliderMargin;
                addView(this.brightnessSliderView, 1, layoutParams);
            }
            this.brightnessSliderView.bind(this.colorWheelView);
            updateObservableOnDuty();
        } else {
            BrightnessSliderView brightnessSliderView2 = this.brightnessSliderView;
            if (brightnessSliderView2 != null) {
                brightnessSliderView2.unbind();
                removeView(this.brightnessSliderView);
                this.brightnessSliderView = null;
            }
            updateObservableOnDuty();
        }
        if (this.alphaSliderView != null) {
            setEnabledAlpha(true);
        }
    }

    public void setEnabledAlpha(boolean z) {
        if (z) {
            if (this.alphaSliderView == null) {
                this.alphaSliderView = new AlphaSliderView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, this.sliderHeight);
                layoutParams.topMargin = this.sliderMargin;
                addView(this.alphaSliderView, layoutParams);
            }
            ColorObservable colorObservable = this.brightnessSliderView;
            if (colorObservable == null) {
                colorObservable = this.colorWheelView;
            }
            this.alphaSliderView.bind(colorObservable);
            updateObservableOnDuty();
            return;
        }
        AlphaSliderView alphaSliderView2 = this.alphaSliderView;
        if (alphaSliderView2 != null) {
            alphaSliderView2.unbind();
            removeView(this.alphaSliderView);
            this.alphaSliderView = null;
        }
        updateObservableOnDuty();
    }

    private void updateObservableOnDuty() {
        if (this.observableOnDuty != null) {
            for (ColorObserver unsubscribe : this.observers) {
                this.observableOnDuty.unsubscribe(unsubscribe);
            }
        }
        this.colorWheelView.setOnlyUpdateOnTouchEventUp(false);
        BrightnessSliderView brightnessSliderView2 = this.brightnessSliderView;
        if (brightnessSliderView2 != null) {
            brightnessSliderView2.setOnlyUpdateOnTouchEventUp(false);
        }
        AlphaSliderView alphaSliderView2 = this.alphaSliderView;
        if (alphaSliderView2 != null) {
            alphaSliderView2.setOnlyUpdateOnTouchEventUp(false);
        }
        BrightnessSliderView brightnessSliderView3 = this.brightnessSliderView;
        if (brightnessSliderView3 == null && this.alphaSliderView == null) {
            ColorWheelView colorWheelView2 = this.colorWheelView;
            this.observableOnDuty = colorWheelView2;
            colorWheelView2.setOnlyUpdateOnTouchEventUp(this.onlyUpdateOnTouchEventUp);
        } else {
            AlphaSliderView alphaSliderView3 = this.alphaSliderView;
            if (alphaSliderView3 != null) {
                this.observableOnDuty = alphaSliderView3;
                alphaSliderView3.setOnlyUpdateOnTouchEventUp(this.onlyUpdateOnTouchEventUp);
            } else {
                this.observableOnDuty = brightnessSliderView3;
                brightnessSliderView3.setOnlyUpdateOnTouchEventUp(this.onlyUpdateOnTouchEventUp);
            }
        }
        List<ColorObserver> list = this.observers;
        if (list != null) {
            for (ColorObserver next : list) {
                this.observableOnDuty.subscribe(next);
                next.onColor(this.observableOnDuty.getColor(), false, true);
            }
        }
    }

    public void reset() {
        this.colorWheelView.setColor(this.initialColor, true);
    }

    public void subscribe(ColorObserver colorObserver) {
        this.observableOnDuty.subscribe(colorObserver);
        this.observers.add(colorObserver);
    }

    public void unsubscribe(ColorObserver colorObserver) {
        this.observableOnDuty.unsubscribe(colorObserver);
        this.observers.remove(colorObserver);
    }

    public int getColor() {
        return this.observableOnDuty.getColor();
    }
}
