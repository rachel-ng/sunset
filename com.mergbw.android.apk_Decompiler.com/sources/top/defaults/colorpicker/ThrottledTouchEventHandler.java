package top.defaults.colorpicker;

import android.view.MotionEvent;

class ThrottledTouchEventHandler {
    private long lastPassedEventTime;
    private int minInterval;
    private Updatable updatable;

    ThrottledTouchEventHandler(Updatable updatable2) {
        this(16, updatable2);
    }

    private ThrottledTouchEventHandler(int i, Updatable updatable2) {
        this.lastPassedEventTime = 0;
        this.minInterval = i;
        this.updatable = updatable2;
    }

    /* access modifiers changed from: package-private */
    public void onTouchEvent(MotionEvent motionEvent) {
        if (this.updatable != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.lastPassedEventTime > ((long) this.minInterval)) {
                this.lastPassedEventTime = currentTimeMillis;
                this.updatable.update(motionEvent);
            }
        }
    }
}
