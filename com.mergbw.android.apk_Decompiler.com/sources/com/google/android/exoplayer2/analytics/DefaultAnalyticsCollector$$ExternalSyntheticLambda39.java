package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda39 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ DecoderCounters f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda39(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        this.f$0 = eventTime;
        this.f$1 = decoderCounters;
    }

    public final void invoke(Object obj) {
        DefaultAnalyticsCollector.lambda$onAudioDisabled$9(this.f$0, this.f$1, (AnalyticsListener) obj);
    }
}
