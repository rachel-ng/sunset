package top.defaults.colorpicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.InputDeviceCompat;

public class WarmWheelPalette extends View {
    private float centerX;
    private float centerY;
    private final Paint huePaint;
    private float radius;

    public WarmWheelPalette(Context context) {
        this(context, (AttributeSet) null);
    }

    public WarmWheelPalette(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WarmWheelPalette(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.huePaint = new Paint(1);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        float min = ((float) Math.min((i - getPaddingLeft()) - getPaddingRight(), (i2 - getPaddingTop()) - getPaddingBottom())) * 0.5f;
        this.radius = min;
        if (min >= 0.0f) {
            this.centerX = ((float) i) * 0.5f;
            this.centerY = ((float) i2) * 0.5f;
            this.huePaint.setShader(new SweepGradient(this.centerX, this.centerY, InputDeviceCompat.SOURCE_ANY, -1));
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(this.centerX, this.centerY, this.radius, this.huePaint);
    }
}
