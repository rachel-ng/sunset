package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdq;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.analytics.connector.internal.zza;
import com.google.firebase.analytics.connector.internal.zzc;
import com.google.firebase.analytics.connector.internal.zzd;
import com.google.firebase.analytics.connector.internal.zze;
import com.google.firebase.events.Event;
import com.google.firebase.events.Subscriber;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-measurement-api@@22.0.2 */
public class AnalyticsConnectorImpl implements AnalyticsConnector {
    private static volatile AnalyticsConnector zzb;
    final Map<String, zza> zza = new ConcurrentHashMap();
    private final AppMeasurementSdk zzc;

    public int getMaxUserProperties(String str) {
        return this.zzc.getMaxUserProperties(str);
    }

    public AnalyticsConnector.AnalyticsConnectorHandle registerAnalyticsConnectorListener(final String str, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        Object obj;
        Preconditions.checkNotNull(analyticsConnectorListener);
        if (!zzd.zzf(str) || zza(str)) {
            return null;
        }
        AppMeasurementSdk appMeasurementSdk = this.zzc;
        if ("fiam".equals(str)) {
            obj = new zzc(appMeasurementSdk, analyticsConnectorListener);
        } else {
            obj = "clx".equals(str) ? new zze(appMeasurementSdk, analyticsConnectorListener) : null;
        }
        if (obj == null) {
            return null;
        }
        this.zza.put(str, obj);
        return new AnalyticsConnector.AnalyticsConnectorHandle(this) {
            private final /* synthetic */ AnalyticsConnectorImpl zzb;

            {
                this.zzb = r1;
            }

            public void registerEventNames(Set<String> set) {
                if (this.zzb.zza(str) && str.equals("fiam") && set != null && !set.isEmpty()) {
                    this.zzb.zza.get(str).zza(set);
                }
            }

            public void unregister() {
                if (this.zzb.zza(str)) {
                    AnalyticsConnector.AnalyticsConnectorListener zza2 = this.zzb.zza.get(str).zza();
                    if (zza2 != null) {
                        zza2.onMessageTriggered(0, (Bundle) null);
                    }
                    this.zzb.zza.remove(str);
                }
            }

            public void unregisterEventNames() {
                if (this.zzb.zza(str) && str.equals("fiam")) {
                    this.zzb.zza.get(str).zzb();
                }
            }
        };
    }

    public static AnalyticsConnector getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static AnalyticsConnector getInstance(FirebaseApp firebaseApp) {
        return (AnalyticsConnector) firebaseApp.get(AnalyticsConnector.class);
    }

    public static AnalyticsConnector getInstance(FirebaseApp firebaseApp, Context context, Subscriber subscriber) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(subscriber);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (AnalyticsConnectorImpl.class) {
                if (zzb == null) {
                    Bundle bundle = new Bundle(1);
                    if (firebaseApp.isDefaultApp()) {
                        subscriber.subscribe(DataCollectionDefaultChange.class, new zzb(), new zza());
                        bundle.putBoolean("dataCollectionDefaultEnabled", firebaseApp.isDataCollectionDefaultEnabled());
                    }
                    zzb = new AnalyticsConnectorImpl(zzdq.zza(context, (String) null, (String) null, (String) null, bundle).zzb());
                }
            }
        }
        return zzb;
    }

    public List<AnalyticsConnector.ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        for (Bundle zza2 : this.zzc.getConditionalUserProperties(str, str2)) {
            arrayList.add(zzd.zza(zza2));
        }
        return arrayList;
    }

    public Map<String, Object> getUserProperties(boolean z) {
        return this.zzc.getUserProperties((String) null, (String) null, z);
    }

    private AnalyticsConnectorImpl(AppMeasurementSdk appMeasurementSdk) {
        Preconditions.checkNotNull(appMeasurementSdk);
        this.zzc = appMeasurementSdk;
    }

    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        if (str2 == null || zzd.zza(str2, bundle)) {
            this.zzc.clearConditionalUserProperty(str, str2, bundle);
        }
    }

    static /* synthetic */ void zza(Event event) {
        boolean z = ((DataCollectionDefaultChange) event.getPayload()).enabled;
        synchronized (AnalyticsConnectorImpl.class) {
            ((AnalyticsConnectorImpl) Preconditions.checkNotNull(zzb)).zzc.zza(z);
        }
    }

    public void logEvent(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (zzd.zzf(str) && zzd.zza(str2, bundle) && zzd.zzb(str, str2, bundle)) {
            zzd.zza(str, str2, bundle);
            this.zzc.logEvent(str, str2, bundle);
        }
    }

    public void setConditionalUserProperty(AnalyticsConnector.ConditionalUserProperty conditionalUserProperty) {
        if (zzd.zzb(conditionalUserProperty)) {
            this.zzc.setConditionalUserProperty(zzd.zza(conditionalUserProperty));
        }
    }

    public void setUserProperty(String str, String str2, Object obj) {
        if (zzd.zzf(str) && zzd.zza(str, str2)) {
            this.zzc.setUserProperty(str, str2, obj);
        }
    }

    /* access modifiers changed from: private */
    public final boolean zza(String str) {
        return !str.isEmpty() && this.zza.containsKey(str) && this.zza.get(str) != null;
    }
}
