package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LifecycleKt$eventFlow$1$$ExternalSyntheticLambda0 implements LifecycleEventObserver {
    public final /* synthetic */ ProducerScope f$0;

    public /* synthetic */ LifecycleKt$eventFlow$1$$ExternalSyntheticLambda0(ProducerScope producerScope) {
        this.f$0 = producerScope;
    }

    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        LifecycleKt$eventFlow$1.invokeSuspend$lambda$0(this.f$0, lifecycleOwner, event);
    }
}
