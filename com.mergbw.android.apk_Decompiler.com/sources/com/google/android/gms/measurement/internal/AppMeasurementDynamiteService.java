package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzde;
import com.google.android.gms.internal.measurement.zzdg;
import com.google.android.gms.internal.measurement.zzdh;
import com.google.android.gms.internal.measurement.zzdm;
import com.google.android.gms.internal.measurement.zzdo;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.0.2 */
public class AppMeasurementDynamiteService extends zzde {
    zzhj zza = null;
    private final Map<Integer, zziu> zzb = new ArrayMap();

    /* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.0.2 */
    class zza implements zziu {
        private zzdh zza;

        zza(zzdh zzdh) {
            this.zza = zzdh;
        }

        public final void onEvent(String str, String str2, Bundle bundle, long j) {
            try {
                this.zza.zza(str, str2, bundle, j);
            } catch (RemoteException e) {
                if (AppMeasurementDynamiteService.this.zza != null) {
                    AppMeasurementDynamiteService.this.zza.zzj().zzu().zza("Event listener threw exception", e);
                }
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.0.2 */
    class zzb implements zzir {
        private zzdh zza;

        zzb(zzdh zzdh) {
            this.zza = zzdh;
        }

        public final void interceptEvent(String str, String str2, Bundle bundle, long j) {
            try {
                this.zza.zza(str, str2, bundle, j);
            } catch (RemoteException e) {
                if (AppMeasurementDynamiteService.this.zza != null) {
                    AppMeasurementDynamiteService.this.zza.zzj().zzu().zza("Event interceptor threw exception", e);
                }
            }
        }
    }

    public void beginAdUnitExposure(String str, long j) throws RemoteException {
        zza();
        this.zza.zze().zza(str, j);
    }

    @EnsuresNonNull({"scion"})
    private final void zza() {
        if (this.zza == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        zza();
        this.zza.zzp().zza(str, str2, bundle);
    }

    public void clearMeasurementEnabled(long j) throws RemoteException {
        zza();
        this.zza.zzp().zza((Boolean) null);
    }

    public void endAdUnitExposure(String str, long j) throws RemoteException {
        zza();
        this.zza.zze().zzb(str, j);
    }

    public void generateEventId(zzdg zzdg) throws RemoteException {
        zza();
        long zzm = this.zza.zzt().zzm();
        zza();
        this.zza.zzt().zza(zzdg, zzm);
    }

    public void getAppInstanceId(zzdg zzdg) throws RemoteException {
        zza();
        this.zza.zzl().zzb((Runnable) new zzi(this, zzdg));
    }

    public void getCachedAppInstanceId(zzdg zzdg) throws RemoteException {
        zza();
        zza(zzdg, this.zza.zzp().zzag());
    }

    public void getConditionalUserProperties(String str, String str2, zzdg zzdg) throws RemoteException {
        zza();
        this.zza.zzl().zzb((Runnable) new zzm(this, zzdg, str, str2));
    }

    public void getCurrentScreenClass(zzdg zzdg) throws RemoteException {
        zza();
        zza(zzdg, this.zza.zzp().zzah());
    }

    public void getCurrentScreenName(zzdg zzdg) throws RemoteException {
        zza();
        zza(zzdg, this.zza.zzp().zzai());
    }

    public void getGmpAppId(zzdg zzdg) throws RemoteException {
        zza();
        zza(zzdg, this.zza.zzp().zzaj());
    }

    public void getMaxUserProperties(String str, zzdg zzdg) throws RemoteException {
        zza();
        this.zza.zzp();
        zziv.zza(str);
        zza();
        this.zza.zzt().zza(zzdg, 25);
    }

    public void getSessionId(zzdg zzdg) throws RemoteException {
        zza();
        this.zza.zzp().zza(zzdg);
    }

    public void getTestFlag(zzdg zzdg, int i) throws RemoteException {
        zza();
        if (i == 0) {
            this.zza.zzt().zza(zzdg, this.zza.zzp().zzak());
        } else if (i == 1) {
            this.zza.zzt().zza(zzdg, this.zza.zzp().zzaf().longValue());
        } else if (i == 2) {
            zznp zzt = this.zza.zzt();
            double doubleValue = this.zza.zzp().zzad().doubleValue();
            Bundle bundle = new Bundle();
            bundle.putDouble("r", doubleValue);
            try {
                zzdg.zza(bundle);
            } catch (RemoteException e) {
                zzt.zzu.zzj().zzu().zza("Error returning double value to wrapper", e);
            }
        } else if (i == 3) {
            this.zza.zzt().zza(zzdg, this.zza.zzp().zzae().intValue());
        } else if (i == 4) {
            this.zza.zzt().zza(zzdg, this.zza.zzp().zzac().booleanValue());
        }
    }

    public void getUserProperties(String str, String str2, boolean z, zzdg zzdg) throws RemoteException {
        zza();
        this.zza.zzl().zzb((Runnable) new zzk(this, zzdg, str, str2, z));
    }

    public void initForTests(Map map) throws RemoteException {
        zza();
    }

    public void initialize(IObjectWrapper iObjectWrapper, zzdo zzdo, long j) throws RemoteException {
        zzhj zzhj = this.zza;
        if (zzhj == null) {
            this.zza = zzhj.zza((Context) Preconditions.checkNotNull((Context) ObjectWrapper.unwrap(iObjectWrapper)), zzdo, Long.valueOf(j));
        } else {
            zzhj.zzj().zzu().zza("Attempting to initialize multiple times");
        }
    }

    public void isDataCollectionEnabled(zzdg zzdg) throws RemoteException {
        zza();
        this.zza.zzl().zzb((Runnable) new zzl(this, zzdg));
    }

    public void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) throws RemoteException {
        zza();
        this.zza.zzp().zza(str, str2, bundle, z, z2, j);
    }

    public void logEventAndBundle(String str, String str2, Bundle bundle, zzdg zzdg, long j) throws RemoteException {
        Bundle bundle2;
        zza();
        Preconditions.checkNotEmpty(str2);
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString("_o", "app");
        this.zza.zzl().zzb((Runnable) new zzh(this, zzdg, new zzbd(str2, new zzbc(bundle), "app", j), str));
    }

    public void logHealthData(int i, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Object obj;
        Object obj2;
        zza();
        Object obj3 = null;
        if (iObjectWrapper == null) {
            obj = null;
        } else {
            obj = ObjectWrapper.unwrap(iObjectWrapper);
        }
        if (iObjectWrapper2 == null) {
            obj2 = null;
        } else {
            obj2 = ObjectWrapper.unwrap(iObjectWrapper2);
        }
        if (iObjectWrapper3 != null) {
            obj3 = ObjectWrapper.unwrap(iObjectWrapper3);
        }
        this.zza.zzj().zza(i, true, false, str, obj, obj2, obj3);
    }

    public void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzan();
            zzaa.onActivityCreated((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
    }

    public void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzan();
            zzaa.onActivityDestroyed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityPaused(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzan();
            zzaa.onActivityPaused((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityResumed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzan();
            zzaa.onActivityResumed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzdg zzdg, long j) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        Bundle bundle = new Bundle();
        if (zzaa != null) {
            this.zza.zzp().zzan();
            zzaa.onActivitySaveInstanceState((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
        try {
            zzdg.zza(bundle);
        } catch (RemoteException e) {
            this.zza.zzj().zzu().zza("Error returning bundle value to wrapper", e);
        }
    }

    public void onActivityStarted(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzan();
            zzaa.onActivityStarted((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityStopped(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzan();
            zzaa.onActivityStopped((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void performAction(Bundle bundle, zzdg zzdg, long j) throws RemoteException {
        zza();
        zzdg.zza((Bundle) null);
    }

    public void registerOnMeasurementEventListener(zzdh zzdh) throws RemoteException {
        zziu zziu;
        zza();
        synchronized (this.zzb) {
            zziu = this.zzb.get(Integer.valueOf(zzdh.zza()));
            if (zziu == null) {
                zziu = new zza(zzdh);
                this.zzb.put(Integer.valueOf(zzdh.zza()), zziu);
            }
        }
        this.zza.zzp().zza(zziu);
    }

    public void resetAnalyticsData(long j) throws RemoteException {
        zza();
        this.zza.zzp().zza(j);
    }

    private final void zza(zzdg zzdg, String str) {
        zza();
        this.zza.zzt().zza(zzdg, str);
    }

    public void setConditionalUserProperty(Bundle bundle, long j) throws RemoteException {
        zza();
        if (bundle == null) {
            this.zza.zzj().zzg().zza("Conditional user property must not be null");
        } else {
            this.zza.zzp().zzb(bundle, j);
        }
    }

    public void setConsent(Bundle bundle, long j) throws RemoteException {
        zza();
        this.zza.zzp().zzc(bundle, j);
    }

    public void setConsentThirdParty(Bundle bundle, long j) throws RemoteException {
        zza();
        this.zza.zzp().zzd(bundle, j);
    }

    public void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j) throws RemoteException {
        zza();
        this.zza.zzq().zza((Activity) ObjectWrapper.unwrap(iObjectWrapper), str, str2);
    }

    public void setDataCollectionEnabled(boolean z) throws RemoteException {
        zza();
        this.zza.zzp().zzc(z);
    }

    public void setDefaultEventParameters(Bundle bundle) {
        zza();
        this.zza.zzp().zzc(bundle);
    }

    public void setEventInterceptor(zzdh zzdh) throws RemoteException {
        zza();
        zzb zzb2 = new zzb(zzdh);
        if (this.zza.zzl().zzg()) {
            this.zza.zzp().zza((zzir) zzb2);
        } else {
            this.zza.zzl().zzb((Runnable) new zzj(this, zzb2));
        }
    }

    public void setInstanceIdProvider(zzdm zzdm) throws RemoteException {
        zza();
    }

    public void setMeasurementEnabled(boolean z, long j) throws RemoteException {
        zza();
        this.zza.zzp().zza(Boolean.valueOf(z));
    }

    public void setMinimumSessionDuration(long j) throws RemoteException {
        zza();
    }

    public void setSessionTimeoutDuration(long j) throws RemoteException {
        zza();
        this.zza.zzp().zzc(j);
    }

    public void setSgtmDebugInfo(Intent intent) throws RemoteException {
        zza();
        this.zza.zzp().zza(intent);
    }

    public void setUserId(String str, long j) throws RemoteException {
        zza();
        this.zza.zzp().zza(str, j);
    }

    public void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j) throws RemoteException {
        zza();
        this.zza.zzp().zza(str, str2, ObjectWrapper.unwrap(iObjectWrapper), z, j);
    }

    public void unregisterOnMeasurementEventListener(zzdh zzdh) throws RemoteException {
        zziu remove;
        zza();
        synchronized (this.zzb) {
            remove = this.zzb.remove(Integer.valueOf(zzdh.zza()));
        }
        if (remove == null) {
            remove = new zza(zzdh);
        }
        this.zza.zzp().zzb(remove);
    }
}
