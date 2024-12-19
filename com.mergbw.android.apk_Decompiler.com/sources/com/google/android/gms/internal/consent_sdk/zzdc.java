package com.google.android.gms.internal.consent_sdk;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
final class zzdc extends zzdd {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzdd zzc;

    zzdc(zzdd zzdd, int i, int i2) {
        this.zzc = zzdd;
        this.zza = i;
        this.zzb = i2;
    }

    public final Object get(int i) {
        zzcw.zza(i, this.zzb, FirebaseAnalytics.Param.INDEX);
        return this.zzc.get(i + this.zza);
    }

    public final int size() {
        return this.zzb;
    }

    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    /* access modifiers changed from: package-private */
    public final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public final Object[] zze() {
        return this.zzc.zze();
    }

    public final zzdd zzf(int i, int i2) {
        zzcw.zzc(i, i2, this.zzb);
        int i3 = this.zza;
        return this.zzc.subList(i + i3, i2 + i3);
    }
}
