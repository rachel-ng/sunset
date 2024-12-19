package com.mergbw.core.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.mergbw.core.R;

public class LineColorPickerView extends View {
    private static final int defaultSizeLong = 420;
    private static final int defaultSizeShort = 70;
    private Bitmap bitmapForColor;
    private Bitmap bitmapForIndicator;
    private OnColorPickerChangeListener colorPickerChangeListener;
    private int[] colors;
    private int curX;
    private int curY;
    private int currentColor;
    private LinearGradient linearGradient;
    private int mBottom;
    private int mIndicatorColor;
    private boolean mIndicatorEnable;
    private int mLeft;
    private int mRadius;
    private int mRight;
    private int mTop;
    private boolean needReDrawColorTable;
    private boolean needReDrawIndicator;
    private Orientation orientation;
    private final Paint paint;
    private final Paint paintForIndicator;
    private final Rect rect;
    private final Rect rectForIndicator;

    public interface OnColorPickerChangeListener {
        void onColorChanged(LineColorPickerView lineColorPickerView, int i);

        void onStartTrackingTouch(LineColorPickerView lineColorPickerView);

        void onStopTrackingTouch(LineColorPickerView lineColorPickerView);
    }

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    public LineColorPickerView(Context context) {
        super(context);
        this.rect = new Rect();
        this.rectForIndicator = new Rect();
        this.needReDrawColorTable = true;
        this.needReDrawIndicator = true;
        this.colors = null;
        this.bitmapForColor = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        this.bitmapForIndicator = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        setLayerType(1, (Paint) null);
        Paint paint2 = new Paint();
        this.paint = paint2;
        paint2.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.paintForIndicator = paint3;
        paint3.setAntiAlias(true);
        this.curY = Integer.MAX_VALUE;
        this.curX = Integer.MAX_VALUE;
    }

    public LineColorPickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LineColorPickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.rect = new Rect();
        this.rectForIndicator = new Rect();
        this.needReDrawColorTable = true;
        this.needReDrawIndicator = true;
        this.colors = null;
        this.bitmapForColor = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        this.bitmapForIndicator = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        setLayerType(1, (Paint) null);
        Paint paint2 = new Paint();
        this.paint = paint2;
        paint2.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.paintForIndicator = paint3;
        paint3.setAntiAlias(true);
        this.curY = Integer.MAX_VALUE;
        this.curX = Integer.MAX_VALUE;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ColorPickerView, i, 0);
        this.mIndicatorColor = obtainStyledAttributes.getColor(R.styleable.ColorPickerView_indicatorColor, -1);
        this.orientation = obtainStyledAttributes.getInteger(R.styleable.ColorPickerView_orientation, 0) == 0 ? Orientation.HORIZONTAL : Orientation.VERTICAL;
        this.mIndicatorEnable = obtainStyledAttributes.getBoolean(R.styleable.ColorPickerView_indicatorEnable, true);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = getSuggestedMinimumWidth() + getPaddingLeft() + getPaddingRight();
        }
        if (mode2 != 1073741824) {
            size2 = getSuggestedMinimumHeight() + getPaddingTop() + getPaddingBottom();
        }
        int i3 = 420;
        int max = Math.max(size, this.orientation == Orientation.HORIZONTAL ? 420 : 70);
        if (this.orientation == Orientation.HORIZONTAL) {
            i3 = 70;
        }
        setMeasuredDimension(max, Math.max(size2, i3));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mTop = getPaddingTop();
        this.mLeft = getPaddingLeft();
        this.mBottom = getMeasuredHeight() - getPaddingBottom();
        this.mRight = getMeasuredWidth() - getPaddingRight();
        int i5 = this.curX;
        int i6 = this.curY;
        if (i5 == i6 || i6 == Integer.MAX_VALUE) {
            this.curX = getWidth() / 2;
            this.curY = getHeight() / 2;
        }
        calculBounds();
        int[] iArr = this.colors;
        if (iArr == null) {
            setColors(createDefaultColorTable());
        } else {
            setColors(iArr);
        }
        createBitmap();
        if (this.mIndicatorEnable) {
            this.needReDrawIndicator = true;
        }
    }

    private void createBitmap() {
        int height = this.rect.height();
        int width = this.rect.width();
        int i = this.mRadius * 2;
        Bitmap bitmap = this.bitmapForColor;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.bitmapForColor.recycle();
            this.bitmapForColor = null;
        }
        Bitmap bitmap2 = this.bitmapForIndicator;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.bitmapForIndicator.recycle();
            this.bitmapForIndicator = null;
        }
        this.bitmapForColor = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.bitmapForIndicator = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    }

    private void calculBounds() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = this.mBottom - this.mTop;
        int i6 = this.mRight - this.mLeft;
        int min = Math.min(i6, i5);
        if (this.orientation == Orientation.HORIZONTAL) {
            if (i6 <= i5) {
                min = i6 / 6;
            }
        } else if (i6 >= i5) {
            min = i5 / 6;
        }
        int i7 = min / 9;
        this.mRadius = (i7 * 7) / 2;
        int i8 = (i7 * 3) / 2;
        if (this.orientation == Orientation.HORIZONTAL) {
            int i9 = this.mLeft;
            int i10 = this.mRadius;
            i4 = i9 + i10;
            i2 = this.mRight - i10;
            i3 = (getHeight() / 2) - i8;
            i = (getHeight() / 2) + i8;
        } else {
            int i11 = this.mTop;
            int i12 = this.mRadius;
            i = this.mBottom - i12;
            i2 = (getWidth() / 2) + i8;
            i3 = i11 + i12;
            i4 = (getWidth() / 2) - i8;
        }
        this.rect.set(i4, i3, i2, i);
    }

    public void setColors(int... iArr) {
        this.linearGradient = null;
        this.colors = iArr;
        if (this.orientation == Orientation.HORIZONTAL) {
            this.linearGradient = new LinearGradient((float) this.rect.left, (float) this.rect.top, (float) this.rect.right, (float) this.rect.top, iArr, (float[]) null, Shader.TileMode.CLAMP);
        } else {
            this.linearGradient = new LinearGradient((float) this.rect.left, (float) this.rect.top, (float) this.rect.left, (float) this.rect.bottom, iArr, (float[]) null, Shader.TileMode.CLAMP);
        }
        this.needReDrawColorTable = true;
        invalidate();
    }

    public int[] createDefaultColorTable() {
        return new int[]{Color.rgb(255, 0, 0), Color.rgb(255, 255, 0), Color.rgb(0, 255, 0), Color.rgb(0, 255, 255), Color.rgb(0, 0, 255), Color.rgb(255, 0, 255), Color.rgb(255, 0, 0)};
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.needReDrawColorTable) {
            createColorTableBitmap();
        }
        canvas.drawBitmap(this.bitmapForColor, (Rect) null, this.rect, this.paint);
        if (this.mIndicatorEnable) {
            if (this.needReDrawIndicator) {
                createIndicatorBitmap();
            }
            Rect rect2 = this.rectForIndicator;
            int i = this.curX;
            int i2 = this.mRadius;
            int i3 = this.curY;
            rect2.set(i - i2, i3 - i2, i + i2, i3 + i2);
            canvas.drawBitmap(this.bitmapForIndicator, (Rect) null, this.rectForIndicator, this.paint);
        }
    }

    private void createIndicatorBitmap() {
        this.paintForIndicator.setColor(this.mIndicatorColor);
        this.paintForIndicator.setShadowLayer((float) 3, 0.0f, 0.0f, -7829368);
        Canvas canvas = new Canvas(this.bitmapForIndicator);
        int i = this.mRadius;
        canvas.drawCircle((float) i, (float) i, (float) (i - 3), this.paintForIndicator);
        this.needReDrawIndicator = false;
    }

    private void createColorTableBitmap() {
        int i;
        Canvas canvas = new Canvas(this.bitmapForColor);
        RectF rectF = new RectF(0.0f, 0.0f, (float) this.bitmapForColor.getWidth(), (float) this.bitmapForColor.getHeight());
        if (this.orientation == Orientation.HORIZONTAL) {
            i = this.bitmapForColor.getHeight() / 2;
        } else {
            i = this.bitmapForColor.getWidth() / 2;
        }
        this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        float f = (float) i;
        canvas.drawRoundRect(rectF, f, f, this.paint);
        this.paint.setShader(this.linearGradient);
        canvas.drawRoundRect(rectF, f, f, this.paint);
        this.paint.setShader((Shader) null);
        this.needReDrawColorTable = false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (!inBoundOfColorTable(x, y)) {
            OnColorPickerChangeListener onColorPickerChangeListener = this.colorPickerChangeListener;
            if (onColorPickerChangeListener != null) {
                onColorPickerChangeListener.onStopTrackingTouch(this);
                calcuColor();
                this.colorPickerChangeListener.onColorChanged(this, this.currentColor);
            }
            return true;
        }
        if (this.orientation == Orientation.HORIZONTAL) {
            this.curX = x;
            this.curY = getHeight() / 2;
        } else {
            this.curX = getWidth() / 2;
            this.curY = y;
        }
        if (motionEvent.getActionMasked() == 0) {
            OnColorPickerChangeListener onColorPickerChangeListener2 = this.colorPickerChangeListener;
            if (onColorPickerChangeListener2 != null) {
                onColorPickerChangeListener2.onStartTrackingTouch(this);
            }
            calcuColor();
            invalidate();
            return true;
        } else if (motionEvent.getActionMasked() == 1) {
            OnColorPickerChangeListener onColorPickerChangeListener3 = this.colorPickerChangeListener;
            if (onColorPickerChangeListener3 != null) {
                onColorPickerChangeListener3.onStopTrackingTouch(this);
                calcuColor();
                this.colorPickerChangeListener.onColorChanged(this, this.currentColor);
            }
            invalidate();
            return true;
        } else if (motionEvent.getActionMasked() != 2) {
            return super.onTouchEvent(motionEvent);
        } else {
            calcuColor();
            invalidate();
            return true;
        }
    }

    public int getColor() {
        return calcuColor();
    }

    private boolean inBoundOfColorTable(int i, int i2) {
        if (this.orientation == Orientation.HORIZONTAL) {
            int i3 = this.mLeft;
            int i4 = this.mRadius;
            if (i <= i3 + i4 || i >= this.mRight - i4) {
                return false;
            }
            return true;
        }
        int i5 = this.mTop;
        int i6 = this.mRadius;
        if (i2 <= i5 + i6 || i2 >= this.mBottom - i6) {
            return false;
        }
        return true;
    }

    private int calcuColor() {
        int i;
        int i2 = 1;
        if (this.orientation == Orientation.HORIZONTAL) {
            i = (this.rect.bottom - this.rect.top) / 2;
            if (this.curX >= this.rect.left) {
                if (this.curX > this.rect.right) {
                    i2 = this.bitmapForColor.getWidth() - 1;
                } else {
                    i2 = this.curX - this.rect.left;
                }
            }
        } else {
            int i3 = (this.rect.right - this.rect.left) / 2;
            if (this.curY >= this.rect.top) {
                if (this.curY > this.rect.bottom) {
                    i2 = this.bitmapForColor.getHeight() - 1;
                } else {
                    i2 = this.curY - this.rect.top;
                }
            }
            int i4 = i2;
            i2 = i3;
            i = i4;
        }
        int pixelToColor = pixelToColor(this.bitmapForColor.getPixel(i2, i));
        this.currentColor = pixelToColor;
        return pixelToColor;
    }

    private int pixelToColor(int i) {
        return Color.argb(Color.alpha(i), Color.red(i), Color.green(i), Color.blue(i));
    }

    public void setOnColorPickerChangeListener(OnColorPickerChangeListener onColorPickerChangeListener) {
        this.colorPickerChangeListener = onColorPickerChangeListener;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.selX = this.curX;
        savedState.selY = this.curY;
        savedState.color = this.bitmapForColor;
        if (this.mIndicatorEnable) {
            savedState.indicator = this.bitmapForIndicator;
        }
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.curX = savedState.selX;
        this.curY = savedState.selY;
        this.colors = savedState.colors;
        this.bitmapForColor = savedState.color;
        if (this.mIndicatorEnable) {
            this.bitmapForIndicator = savedState.indicator;
            this.needReDrawIndicator = true;
        }
        this.needReDrawColorTable = true;
    }

    private static class SavedState extends View.BaseSavedState {
        Bitmap color;
        int[] colors;
        Bitmap indicator = null;
        int selX;
        int selY;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.selX);
            parcel.writeInt(this.selY);
            parcel.writeParcelable(this.color, i);
            parcel.writeIntArray(this.colors);
            Bitmap bitmap = this.indicator;
            if (bitmap != null) {
                parcel.writeParcelable(bitmap, i);
            }
        }
    }

    public void setPosition(int i, int i2) {
        if (inBoundOfColorTable(i, i2)) {
            this.curX = i;
            this.curY = i2;
            if (this.mIndicatorEnable) {
                this.needReDrawIndicator = true;
            }
            invalidate();
        }
    }

    public void showDefaultColorTable() {
        setColors(createDefaultColorTable());
    }

    public int getIndicatorColor() {
        return this.mIndicatorColor;
    }

    public void setIndicatorColor(int i) {
        this.mIndicatorColor = i;
        this.needReDrawIndicator = true;
        invalidate();
    }

    public void setOrientation(Orientation orientation2) {
        this.orientation = orientation2;
        this.needReDrawIndicator = true;
        this.needReDrawColorTable = true;
        requestLayout();
    }
}
