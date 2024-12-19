package top.defaults.colorpicker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

public class BrightnessSliderView extends ColorSliderView {
    public BrightnessSliderView(Context context) {
        super(context);
    }

    public BrightnessSliderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BrightnessSliderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public float resolveValue(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        return fArr[2];
    }

    /* access modifiers changed from: protected */
    public void configurePaint(Paint paint) {
        float[] fArr = new float[3];
        Color.colorToHSV(this.baseColor, fArr);
        fArr[2] = 0.0f;
        int HSVToColor = Color.HSVToColor(fArr);
        fArr[2] = 1.0f;
        paint.setShader(new LinearGradient(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), HSVToColor, Color.HSVToColor(fArr), Shader.TileMode.CLAMP));
    }

    /* access modifiers changed from: protected */
    public int assembleColor() {
        float[] fArr = new float[3];
        Color.colorToHSV(this.baseColor, fArr);
        fArr[2] = this.currentValue;
        return Color.HSVToColor(fArr);
    }
}
