package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlinx.coroutines.flow.MutableStateFlow;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Lifecycle$$ExternalSyntheticLambda0 implements LifecycleEventObserver {
    public final /* synthetic */ MutableStateFlow f$0;

    public /* synthetic */ Lifecycle$$ExternalSyntheticLambda0(MutableStateFlow mutableStateFlow) {
        this.f$0 = mutableStateFlow;
    }

    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Lifecycle._get_currentStateFlow_$lambda$0(this.f$0, lifecycleOwner, event);
    }
}
