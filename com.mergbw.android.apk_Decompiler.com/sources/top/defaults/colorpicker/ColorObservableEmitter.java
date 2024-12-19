package top.defaults.colorpicker;

import java.util.ArrayList;
import java.util.List;

class ColorObservableEmitter implements ColorObservable {
    private int color;
    private List<ColorObserver> observers = new ArrayList();

    ColorObservableEmitter() {
    }

    public void subscribe(ColorObserver colorObserver) {
        if (colorObserver != null) {
            this.observers.add(colorObserver);
        }
    }

    public void unsubscribe(ColorObserver colorObserver) {
        if (colorObserver != null) {
            this.observers.remove(colorObserver);
        }
    }

    public int getColor() {
        return this.color;
    }

    /* access modifiers changed from: package-private */
    public void onColor(int i, boolean z, boolean z2) {
        this.color = i;
        for (ColorObserver onColor : this.observers) {
            onColor.onColor(i, z, z2);
        }
    }
}
