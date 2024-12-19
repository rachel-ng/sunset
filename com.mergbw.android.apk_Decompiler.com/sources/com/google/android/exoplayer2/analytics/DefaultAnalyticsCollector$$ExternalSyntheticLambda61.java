package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.DeviceInfo;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda61 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ DeviceInfo f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda61(AnalyticsListener.EventTime eventTime, DeviceInfo deviceInfo) {
        this.f$0 = eventTime;
        this.f$1 = deviceInfo;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onDeviceInfoChanged(this.f$0, this.f$1);
    }
}
