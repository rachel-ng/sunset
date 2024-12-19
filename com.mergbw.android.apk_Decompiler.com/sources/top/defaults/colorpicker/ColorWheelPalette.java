package top.defaults.colorpicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;

public class ColorWheelPalette extends View {
    private float centerX;
    private float centerY;
    private Paint huePaint;
    private float radius;
    private Paint saturationPaint;

    public ColorWheelPalette(Context context) {
        this(context, (AttributeSet) null);
    }

    public ColorWheelPalette(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorWheelPalette(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.huePaint = new Paint(1);
        this.saturationPaint = new Paint(1);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        float min = ((float) Math.min((i - getPaddingLeft()) - getPaddingRight(), (i2 - getPaddingTop()) - getPaddingBottom())) * 0.5f;
        this.radius = min;
        if (min >= 0.0f) {
            this.centerX = ((float) i) * 0.5f;
            this.centerY = ((float) i2) * 0.5f;
            this.huePaint.setShader(new SweepGradient(this.centerX, this.centerY, new int[]{SupportMenu.CATEGORY_MASK, -65281, -16776961, -16711681, -16711936, InputDeviceCompat.SOURCE_ANY, SupportMenu.CATEGORY_MASK}, (float[]) null));
            this.saturationPaint.setShader(new RadialGradient(this.centerX, this.centerY, this.radius, -1, ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.CLAMP));
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(this.centerX, this.centerY, this.radius, this.huePaint);
        canvas.drawCircle(this.centerX, this.centerY, this.radius, this.saturationPaint);
    }
}
