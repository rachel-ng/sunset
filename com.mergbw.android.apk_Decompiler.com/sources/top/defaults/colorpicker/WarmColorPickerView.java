package top.defaults.colorpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

public class WarmColorPickerView extends LinearLayout implements ColorObservable {
    private ColorObservable observableOnDuty;
    List<ColorObserver> observers;
    private boolean onlyUpdateOnTouchEventUp;
    private final WarmWheelView warmWheelView;

    public WarmColorPickerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public WarmColorPickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WarmColorPickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.observers = new ArrayList();
        setOrientation(1);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ColorPickerView);
        this.onlyUpdateOnTouchEventUp = obtainStyledAttributes.getBoolean(R.styleable.ColorPickerView_onlyUpdateOnTouchEventUp, false);
        obtainStyledAttributes.recycle();
        WarmWheelView warmWheelView2 = new WarmWheelView(context);
        this.warmWheelView = warmWheelView2;
        int i2 = (int) (getResources().getDisplayMetrics().density * 0.0f);
        addView(warmWheelView2, new LinearLayout.LayoutParams(-2, -2));
        updateObservableOnDuty();
        setPadding(i2, i2, i2, i2);
    }

    public void setOnlyUpdateOnTouchEventUp(boolean z) {
        this.onlyUpdateOnTouchEventUp = z;
        updateObservableOnDuty();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int min = Math.min(View.MeasureSpec.getSize(i), (View.MeasureSpec.getSize(i2) - (getPaddingTop() + getPaddingBottom())) + getPaddingLeft() + getPaddingRight());
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(min, View.MeasureSpec.getMode(i)), View.MeasureSpec.makeMeasureSpec((min - (getPaddingLeft() + getPaddingRight())) + getPaddingTop() + getPaddingBottom(), View.MeasureSpec.getMode(i2)));
    }

    private void updateObservableOnDuty() {
        if (this.observableOnDuty != null) {
            for (ColorObserver unsubscribe : this.observers) {
                this.observableOnDuty.unsubscribe(unsubscribe);
            }
        }
        WarmWheelView warmWheelView2 = this.warmWheelView;
        this.observableOnDuty = warmWheelView2;
        warmWheelView2.setOnlyUpdateOnTouchEventUp(this.onlyUpdateOnTouchEventUp);
        List<ColorObserver> list = this.observers;
        if (list != null) {
            for (ColorObserver next : list) {
                this.observableOnDuty.subscribe(next);
                next.onColor(this.observableOnDuty.getColor(), false, true);
            }
        }
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
