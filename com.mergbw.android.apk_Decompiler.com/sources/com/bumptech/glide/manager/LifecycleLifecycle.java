package com.bumptech.glide.manager;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.bumptech.glide.util.Util;
import java.util.HashSet;
import java.util.Set;

final class LifecycleLifecycle implements Lifecycle, LifecycleObserver {
    private final Lifecycle lifecycle;
    private final Set<LifecycleListener> lifecycleListeners = new HashSet();

    LifecycleLifecycle(Lifecycle lifecycle2) {
        this.lifecycle = lifecycle2;
        lifecycle2.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(LifecycleOwner lifecycleOwner) {
        for (T onStart : Util.getSnapshot(this.lifecycleListeners)) {
            onStart.onStart();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(LifecycleOwner lifecycleOwner) {
        for (T onStop : Util.getSnapshot(this.lifecycleListeners)) {
            onStop.onStop();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner lifecycleOwner) {
        for (T onDestroy : Util.getSnapshot(this.lifecycleListeners)) {
            onDestroy.onDestroy();
        }
        lifecycleOwner.getLifecycle().removeObserver(this);
    }

    public void addListener(LifecycleListener lifecycleListener) {
        this.lifecycleListeners.add(lifecycleListener);
        if (this.lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
            lifecycleListener.onDestroy();
        } else if (this.lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            lifecycleListener.onStart();
        } else {
            lifecycleListener.onStop();
        }
    }

    public void removeListener(LifecycleListener lifecycleListener) {
        this.lifecycleListeners.remove(lifecycleListener);
    }
}
