package top.defaults.checkerboarddrawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

public class CheckerboardDrawable extends Drawable {
    private int colorEven;
    private int colorOdd;
    private Paint paint;
    private int size;

    public int getOpacity() {
        return -1;
    }

    public static CheckerboardDrawable create() {
        return new CheckerboardDrawable(new Builder());
    }

    private CheckerboardDrawable(Builder builder) {
        this.paint = new Paint(1);
        this.size = builder.size;
        this.colorOdd = builder.colorOdd;
        this.colorEven = builder.colorEven;
        configurePaint();
    }

    private void configurePaint() {
        int i = this.size;
        Bitmap createBitmap = Bitmap.createBitmap(i * 2, i * 2, Bitmap.Config.ARGB_8888);
        Paint paint2 = new Paint(1);
        paint2.setStyle(Paint.Style.FILL);
        Canvas canvas = new Canvas(createBitmap);
        int i2 = this.size;
        Rect rect = new Rect(0, 0, i2, i2);
        paint2.setColor(this.colorOdd);
        canvas.drawRect(rect, paint2);
        int i3 = this.size;
        rect.offset(i3, i3);
        canvas.drawRect(rect, paint2);
        paint2.setColor(this.colorEven);
        rect.offset(-this.size, 0);
        canvas.drawRect(rect, paint2);
        int i4 = this.size;
        rect.offset(i4, -i4);
        canvas.drawRect(rect, paint2);
        this.paint.setShader(new BitmapShader(createBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
    }

    public void draw(Canvas canvas) {
        canvas.drawPaint(this.paint);
    }

    public void setAlpha(int i) {
        this.paint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public int colorEven = -789517;
        /* access modifiers changed from: private */
        public int colorOdd = -4013374;
        /* access modifiers changed from: private */
        public int size = 40;

        public Builder size(int i) {
            this.size = i;
            return this;
        }

        public Builder colorOdd(int i) {
            this.colorOdd = i;
            return this;
        }

        public Builder colorEven(int i) {
            this.colorEven = i;
            return this;
        }

        public CheckerboardDrawable build() {
            return new CheckerboardDrawable(this);
        }
    }
}
