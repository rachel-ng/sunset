package androidx.lifecycle;

import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FlowLiveDataConversions$asFlow$1$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ ProducerScope f$0;

    public /* synthetic */ FlowLiveDataConversions$asFlow$1$$ExternalSyntheticLambda0(ProducerScope producerScope) {
        this.f$0 = producerScope;
    }

    public final void onChanged(Object obj) {
        FlowLiveDataConversions$asFlow$1.invokeSuspend$lambda$0(this.f$0, obj);
    }
}
