package top.defaults.colorpicker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.Locale;

public class ColorPickerPopup {
    private String cancelTitle;
    private Context context;
    private boolean enableAlpha;
    private boolean enableBrightness;
    private int initialColor;
    private String okTitle;
    private boolean onlyUpdateOnTouchEventUp;
    /* access modifiers changed from: private */
    public PopupWindow popupWindow;
    /* access modifiers changed from: private */
    public boolean showIndicator;
    /* access modifiers changed from: private */
    public boolean showValue;

    public static abstract class ColorPickerObserver implements ColorObserver {
        public final void onColor(int i, boolean z, boolean z2) {
        }

        public abstract void onColorPicked(int i);
    }

    private ColorPickerPopup(Builder builder) {
        this.context = builder.context;
        this.initialColor = builder.initialColor;
        this.enableBrightness = builder.enableBrightness;
        this.enableAlpha = builder.enableAlpha;
        this.okTitle = builder.okTitle;
        this.cancelTitle = builder.cancelTitle;
        this.showIndicator = builder.showIndicator;
        this.showValue = builder.showValue;
        this.onlyUpdateOnTouchEventUp = builder.onlyUpdateOnTouchEventUp;
    }

    public void show(ColorPickerObserver colorPickerObserver) {
        show((View) null, colorPickerObserver);
    }

    public void show(View view, final ColorPickerObserver colorPickerObserver) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        if (layoutInflater != null) {
            View inflate = layoutInflater.inflate(R.layout.top_defaults_view_color_picker_popup, (ViewGroup) null);
            final ColorPickerView colorPickerView = (ColorPickerView) inflate.findViewById(R.id.colorPickerView);
            PopupWindow popupWindow2 = new PopupWindow(inflate, -2, -2);
            this.popupWindow = popupWindow2;
            popupWindow2.setBackgroundDrawable(new ColorDrawable(-1));
            this.popupWindow.setOutsideTouchable(true);
            colorPickerView.setInitialColor(this.initialColor);
            colorPickerView.setEnabledBrightness(this.enableBrightness);
            colorPickerView.setEnabledAlpha(this.enableAlpha);
            colorPickerView.setOnlyUpdateOnTouchEventUp(this.onlyUpdateOnTouchEventUp);
            colorPickerView.subscribe(colorPickerObserver);
            TextView textView = (TextView) inflate.findViewById(R.id.cancel);
            textView.setText(this.cancelTitle);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ColorPickerPopup.this.popupWindow.dismiss();
                }
            });
            TextView textView2 = (TextView) inflate.findViewById(R.id.ok);
            textView2.setText(this.okTitle);
            textView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ColorPickerPopup.this.popupWindow.dismiss();
                    ColorPickerObserver colorPickerObserver = colorPickerObserver;
                    if (colorPickerObserver != null) {
                        colorPickerObserver.onColorPicked(colorPickerView.getColor());
                    }
                }
            });
            final View findViewById = inflate.findViewById(R.id.colorIndicator);
            final TextView textView3 = (TextView) inflate.findViewById(R.id.colorHex);
            int i = 8;
            findViewById.setVisibility(this.showIndicator ? 0 : 8);
            if (this.showValue) {
                i = 0;
            }
            textView3.setVisibility(i);
            if (this.showIndicator) {
                findViewById.setBackgroundColor(this.initialColor);
            }
            if (this.showValue) {
                textView3.setText(colorHex(this.initialColor));
            }
            colorPickerView.subscribe(new ColorObserver() {
                public void onColor(int i, boolean z, boolean z2) {
                    if (ColorPickerPopup.this.showIndicator) {
                        findViewById.setBackgroundColor(i);
                    }
                    if (ColorPickerPopup.this.showValue) {
                        textView3.setText(ColorPickerPopup.this.colorHex(i));
                    }
                }
            });
            this.popupWindow.setElevation(10.0f);
            this.popupWindow.setAnimationStyle(R.style.TopDefaultsViewColorPickerPopupAnimation);
            if (view == null) {
                view = inflate;
            }
            this.popupWindow.showAtLocation(view, 17, 0, 0);
        }
    }

    public void dismiss() {
        PopupWindow popupWindow2 = this.popupWindow;
        if (popupWindow2 != null) {
            popupWindow2.dismiss();
        }
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String cancelTitle = "Cancel";
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public boolean enableAlpha = false;
        /* access modifiers changed from: private */
        public boolean enableBrightness = true;
        /* access modifiers changed from: private */
        public int initialColor = -65281;
        /* access modifiers changed from: private */
        public String okTitle = "OK";
        /* access modifiers changed from: private */
        public boolean onlyUpdateOnTouchEventUp = false;
        /* access modifiers changed from: private */
        public boolean showIndicator = true;
        /* access modifiers changed from: private */
        public boolean showValue = true;

        public Builder(Context context2) {
            this.context = context2;
        }

        public Builder initialColor(int i) {
            this.initialColor = i;
            return this;
        }

        public Builder enableBrightness(boolean z) {
            this.enableBrightness = z;
            return this;
        }

        public Builder enableAlpha(boolean z) {
            this.enableAlpha = z;
            return this;
        }

        public Builder okTitle(String str) {
            this.okTitle = str;
            return this;
        }

        public Builder cancelTitle(String str) {
            this.cancelTitle = str;
            return this;
        }

        public Builder showIndicator(boolean z) {
            this.showIndicator = z;
            return this;
        }

        public Builder showValue(boolean z) {
            this.showValue = z;
            return this;
        }

        public Builder onlyUpdateOnTouchEventUp(boolean z) {
            this.onlyUpdateOnTouchEventUp = z;
            return this;
        }

        public ColorPickerPopup build() {
            return new ColorPickerPopup(this);
        }
    }

    /* access modifiers changed from: private */
    public String colorHex(int i) {
        int alpha = Color.alpha(i);
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        return String.format(Locale.getDefault(), "0x%02X%02X%02X%02X", new Object[]{Integer.valueOf(alpha), Integer.valueOf(red), Integer.valueOf(green), Integer.valueOf(blue)});
    }
}
