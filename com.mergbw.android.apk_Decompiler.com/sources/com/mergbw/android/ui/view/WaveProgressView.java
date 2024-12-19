package com.mergbw.android.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.mergbw.android.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class WaveProgressView extends View {
    public static final int BOTTOM_TEXT = 3;
    public static final int CENTER_TEXT = 2;
    public static final int NONE = 0;
    private static final int OUTER_RING_COLOR = -134184049;
    private static final float STRETCH_FACTOR_A = 15.0f;
    public static final int TOP_TEXT = 1;
    private static final int TRANSLATE_X_SPEED_ONE = 5;
    private static final int TRANSLATE_X_SPEED_TWO = 3;
    private static final int WAVE_PAINT_COLOR = -4636161;
    private int bindingText;
    private Paint bottomPaint;
    private String bottomText;
    private int bottomTextColor;
    private float bottomTextSize;
    private Paint centerPaint;
    private String centerText;
    private int centerTextColor;
    private float centerTextSize;
    private Path cirPath;
    private int height;
    protected Context mContext;
    private DrawFilter mDrawFilter;
    private float[] mResetOneYPositions;
    private float[] mResetTwoYPositions;
    private Paint mWavePaint;
    private int mXOffsetSpeedOne;
    private int mXOffsetSpeedTwo;
    private int mXOneOffset;
    private int mXTwoOffset;
    private float[] mYPositions;
    private int max;
    private Paint outerRingPaint;
    private int progress;
    private int strokeColor;
    private float strokeWidth;
    private int submergedTextColor;
    private Rect textRect;
    private Paint topPaint;
    private String topText;
    private int topTextColor;
    private float topTextSize;
    private float waveHeight;
    private int wavePaintColor;
    private int width;

    @Retention(RetentionPolicy.SOURCE)
    private @interface BindingText {
    }

    public WaveProgressView(Context context) {
        super(context);
        init(context, (AttributeSet) null);
    }

    public WaveProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public WaveProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WaveProgressView);
        this.bindingText = obtainStyledAttributes.getInt(R.styleable.WaveProgressView_bindingText, 0);
        this.wavePaintColor = obtainStyledAttributes.getColor(R.styleable.WaveProgressView_wave_color, WAVE_PAINT_COLOR);
        this.submergedTextColor = obtainStyledAttributes.getColor(R.styleable.WaveProgressView_submerged_textColor, 268435440);
        this.max = obtainStyledAttributes.getInt(R.styleable.WaveProgressView_max, 100);
        this.waveHeight = obtainStyledAttributes.getDimension(R.styleable.WaveProgressView_wave_height, STRETCH_FACTOR_A);
        this.strokeWidth = obtainStyledAttributes.getDimension(R.styleable.WaveProgressView_stroke_width, getResources().getDimension(R.dimen.stroke_width));
        this.strokeColor = obtainStyledAttributes.getColor(R.styleable.WaveProgressView_stroke_color, OUTER_RING_COLOR);
        this.topText = obtainStyledAttributes.getString(R.styleable.WaveProgressView_top_text);
        this.topTextColor = obtainStyledAttributes.getColor(R.styleable.WaveProgressView_top_textColor, ViewCompat.MEASURED_STATE_MASK);
        this.topTextSize = obtainStyledAttributes.getDimension(R.styleable.WaveProgressView_top_textSize, getResources().getDimension(R.dimen.top_text_size));
        this.centerText = obtainStyledAttributes.getString(R.styleable.WaveProgressView_center_text);
        this.centerTextColor = obtainStyledAttributes.getColor(R.styleable.WaveProgressView_center_textColor, ViewCompat.MEASURED_STATE_MASK);
        this.centerTextSize = obtainStyledAttributes.getDimension(R.styleable.WaveProgressView_center_textSize, getResources().getDimension(R.dimen.center_text_size));
        this.bottomText = obtainStyledAttributes.getString(R.styleable.WaveProgressView_bottom_text);
        this.bottomTextColor = obtainStyledAttributes.getColor(R.styleable.WaveProgressView_bottom_textColor, -7829368);
        this.bottomTextSize = obtainStyledAttributes.getDimension(R.styleable.WaveProgressView_bottom_textSize, getResources().getDimension(R.dimen.bottom_text_size));
        if (this.topText == null) {
            this.topText = "";
        }
        if (this.centerText == null) {
            this.centerText = "";
        }
        if (this.bottomText == null) {
            this.bottomText = "";
        }
        Paint paint = new Paint();
        this.outerRingPaint = paint;
        paint.setAntiAlias(true);
        this.outerRingPaint.setStrokeWidth(this.strokeWidth);
        this.outerRingPaint.setColor(this.strokeColor);
        this.outerRingPaint.setStyle(Paint.Style.STROKE);
        Paint paint2 = new Paint();
        this.mWavePaint = paint2;
        paint2.setAntiAlias(true);
        this.mWavePaint.setStyle(Paint.Style.FILL);
        this.mWavePaint.setColor(this.wavePaintColor);
        this.mDrawFilter = new PaintFlagsDrawFilter(0, 3);
        Paint paint3 = new Paint();
        this.topPaint = paint3;
        paint3.setAntiAlias(true);
        this.topPaint.setTextSize(this.topTextSize);
        Paint paint4 = new Paint();
        this.centerPaint = paint4;
        paint4.setAntiAlias(true);
        this.centerPaint.setTextSize(this.centerTextSize);
        Paint paint5 = new Paint();
        this.bottomPaint = paint5;
        paint5.setAntiAlias(true);
        this.bottomPaint.setTextSize(this.bottomTextSize);
        setProgress(obtainStyledAttributes.getInt(R.styleable.WaveProgressView_progress, 0));
        this.textRect = new Rect();
        this.cirPath = new Path();
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE && mode2 == Integer.MIN_VALUE) {
            int dp2px = dp2px(this.mContext, 150.0f);
            setMeasuredDimension(dp2px, dp2px);
        } else if (mode == Integer.MIN_VALUE) {
            setMeasuredDimension(dp2px(this.mContext, 150.0f), View.MeasureSpec.getSize(i2));
        } else if (mode2 == Integer.MIN_VALUE) {
            int size = View.MeasureSpec.getSize(i);
            setMeasuredDimension(size, size);
        } else {
            super.onMeasure(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i < i2) {
            this.height = i;
            this.width = i;
        } else {
            this.height = i2;
            this.width = i2;
        }
        int i5 = this.width;
        this.mYPositions = new float[i5];
        this.mResetOneYPositions = new float[i5];
        this.mResetTwoYPositions = new float[i5];
        float f = (float) (6.283185307179586d / ((double) i5));
        int i6 = 0;
        while (true) {
            int i7 = this.width;
            if (i6 < i7) {
                this.mYPositions[i6] = (float) ((((double) this.waveHeight) * Math.sin((double) (((float) i6) * f))) - ((double) this.waveHeight));
                i6++;
            } else {
                this.cirPath.addCircle(((float) i7) / 2.0f, ((float) this.height) / 2.0f, ((((float) i7) / 2.0f) - this.strokeWidth) + 0.3f, Path.Direction.CW);
                this.mXOffsetSpeedOne = (dp2px(this.mContext, 5.0f) * this.width) / dp2px(this.mContext, 330.0f);
                this.mXOffsetSpeedTwo = (dp2px(this.mContext, 3.0f) * this.width) / dp2px(this.mContext, 330.0f);
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        super.onDraw(canvas);
        canvas.setDrawFilter(this.mDrawFilter);
        canvas.save();
        canvas.clipPath(this.cirPath);
        resetPositionY();
        int i4 = this.height;
        float f = ((float) this.progress) / ((float) this.max);
        float f2 = this.strokeWidth;
        float f3 = (((float) i4) - (f * (((float) i4) - (f2 * 2.0f)))) - f2;
        for (int i5 = 0; i5 < this.width; i5++) {
            float f4 = (float) i5;
            float f5 = f4;
            float f6 = f4;
            canvas.drawLine(f5, f3 - this.mResetOneYPositions[i5], f6, (float) this.height, this.mWavePaint);
            canvas.drawLine(f5, f3 - this.mResetTwoYPositions[i5], f6, (float) this.height, this.mWavePaint);
        }
        canvas.restore();
        float height2 = (((float) this.height) / 4.0f) + (((float) this.textRect.height()) / 2.0f);
        float height3 = (((float) this.height) / 2.0f) + (((float) this.textRect.height()) / 2.0f);
        float height4 = (((float) (this.height * 5)) / 7.0f) + (((float) this.textRect.height()) / 2.0f);
        Paint paint = this.topPaint;
        if (height2 <= ((float) this.textRect.height()) + f3 || (i = this.submergedTextColor) == 268435440) {
            i = this.topTextColor;
        }
        paint.setColor(i);
        Paint paint2 = this.topPaint;
        String str = this.topText;
        paint2.getTextBounds(str, 0, str.length(), this.textRect);
        canvas.drawText(this.topText, (((float) this.width) / 2.0f) - (((float) this.textRect.width()) / 2.0f), height2, this.topPaint);
        Paint paint3 = this.centerPaint;
        if (height3 <= ((float) this.textRect.height()) + f3 || (i2 = this.submergedTextColor) == 268435440) {
            i2 = this.centerTextColor;
        }
        paint3.setColor(i2);
        Paint paint4 = this.centerPaint;
        String str2 = this.centerText;
        paint4.getTextBounds(str2, 0, str2.length(), this.textRect);
        canvas.drawText(this.centerText, (((float) this.width) / 2.0f) - (((float) this.textRect.width()) / 2.0f), height3, this.centerPaint);
        Paint paint5 = this.bottomPaint;
        if (height4 <= f3 + ((float) this.textRect.height()) || (i3 = this.submergedTextColor) == 268435440) {
            i3 = this.bottomTextColor;
        }
        paint5.setColor(i3);
        Paint paint6 = this.bottomPaint;
        String str3 = this.bottomText;
        paint6.getTextBounds(str3, 0, str3.length(), this.textRect);
        canvas.drawText(this.bottomText, (((float) this.width) / 2.0f) - (((float) this.textRect.width()) / 2.0f), height4, this.bottomPaint);
        float f7 = this.strokeWidth;
        if (f7 > 0.0f) {
            int i6 = this.width;
            canvas.drawCircle(((float) i6) / 2.0f, ((float) this.height) / 2.0f, (((float) i6) / 2.0f) - (f7 / 2.0f), this.outerRingPaint);
        }
        int i7 = this.mXOneOffset + this.mXOffsetSpeedOne;
        this.mXOneOffset = i7;
        int i8 = this.mXTwoOffset + this.mXOffsetSpeedTwo;
        this.mXTwoOffset = i8;
        int i9 = this.width;
        if (i7 >= i9) {
            this.mXOneOffset = 0;
        }
        if (i8 > i9) {
            this.mXTwoOffset = 0;
        }
        if (this.waveHeight > 0.0f) {
            postInvalidate();
        }
    }

    private void resetPositionY() {
        float[] fArr = this.mYPositions;
        int length = fArr.length;
        int i = this.mXOneOffset;
        int i2 = length - i;
        System.arraycopy(fArr, i, this.mResetOneYPositions, 0, i2);
        System.arraycopy(this.mYPositions, 0, this.mResetOneYPositions, i2, this.mXOneOffset);
        float[] fArr2 = this.mYPositions;
        int length2 = fArr2.length;
        int i3 = this.mXTwoOffset;
        int i4 = length2 - i3;
        System.arraycopy(fArr2, i3, this.mResetTwoYPositions, 0, i4);
        System.arraycopy(this.mYPositions, 0, this.mResetTwoYPositions, i4, this.mXTwoOffset);
    }

    private int dp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int i) {
        this.max = i;
        invalidate();
    }

    public void setWaveHeight(float f) {
        this.waveHeight = f;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
        String str = String.format("%.2f", new Object[]{Float.valueOf((((float) i) / ((float) this.max)) * 100.0f)}) + "%";
        int i2 = this.bindingText;
        if (i2 == 1) {
            this.topText = str;
        } else if (i2 == 2) {
            this.centerText = str;
        } else if (i2 == 3) {
            this.bottomText = str;
        }
        postInvalidate();
    }

    public int getBindingText() {
        return this.bindingText;
    }

    public void setBindingText(int i) {
        this.bindingText = i;
    }

    public String getTopText() {
        return this.topText;
    }

    public void setTopText(String str) {
        if (str == null) {
            str = "";
        }
        this.topText = str;
        invalidate();
    }

    public String getCenterText() {
        return this.centerText;
    }

    public void setCenterText(String str) {
        if (str == null) {
            str = "";
        }
        this.centerText = str;
        invalidate();
    }

    public String getBottomText() {
        return this.bottomText;
    }

    public void setBottomText(String str) {
        if (str == null) {
            str = "";
        }
        this.bottomText = str;
        invalidate();
    }

    public float getTopTextSize() {
        return this.topTextSize;
    }

    public void setTopTextSize(float f) {
        this.topPaint.setTextSize(f);
        invalidate();
    }

    public float getCenterTextSize() {
        return this.centerTextSize;
    }

    public void setCenterTextSize(float f) {
        this.centerPaint.setTextSize(f);
        invalidate();
    }

    public float getBottomTextSize() {
        return this.bottomTextSize;
    }

    public void setBottomTextSize(float f) {
        this.bottomPaint.setTextSize(f);
        invalidate();
    }

    public int getTopTextColor() {
        return this.topTextColor;
    }

    public void setTopTextColor(int i) {
        this.topTextColor = i;
        invalidate();
    }

    public int getCenterTextColor() {
        return this.centerTextColor;
    }

    public void setCenterTextColor(int i) {
        this.centerTextColor = i;
        invalidate();
    }

    public int getBottomTextColor() {
        return this.bottomTextColor;
    }

    public void setBottomTextColor(int i) {
        this.bottomTextColor = i;
        invalidate();
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public void setStrokeWidth(float f) {
        this.strokeWidth = f;
        invalidate();
    }

    public int getStrokeColor() {
        return this.strokeColor;
    }

    public void setStrokeColor(int i) {
        this.outerRingPaint.setColor(i);
        invalidate();
    }

    public int getWaveColor() {
        return this.wavePaintColor;
    }

    public void setWaveColor(int i) {
        this.mWavePaint.setColor(i);
        invalidate();
    }
}
