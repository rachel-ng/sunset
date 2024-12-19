package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzmh extends zzmf<zzme, zzme> {
    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zza(Object obj) {
        return ((zzme) obj).zza();
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzli zzli) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zzb(Object obj) {
        return ((zzme) obj).zzb();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzc(Object obj) {
        zzme zzme = ((zzjk) obj).zzb;
        if (zzme != zzme.zzc()) {
            return zzme;
        }
        zzme zzd = zzme.zzd();
        zza(obj, zzd);
        return zzd;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzd(Object obj) {
        return ((zzjk) obj).zzb;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza(Object obj, Object obj2) {
        zzme zzme = (zzme) obj;
        zzme zzme2 = (zzme) obj2;
        if (zzme.zzc().equals(zzme2)) {
            return zzme;
        }
        if (zzme.zzc().equals(zzme)) {
            return zzme.zza(zzme, zzme2);
        }
        return zzme.zza(zzme2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza() {
        return zzme.zzd();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zze(Object obj) {
        zzme zzme = (zzme) obj;
        zzme.zze();
        return zzme;
    }

    zzmh() {
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, int i2) {
        ((zzme) obj).zza((i << 3) | 5, (Object) Integer.valueOf(i2));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, long j) {
        ((zzme) obj).zza((i << 3) | 1, (Object) Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, Object obj2) {
        ((zzme) obj).zza((i << 3) | 3, (Object) (zzme) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, zzia zzia) {
        ((zzme) obj).zza((i << 3) | 2, (Object) zzia);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, int i, long j) {
        ((zzme) obj).zza(i << 3, (Object) Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final void zzf(Object obj) {
        ((zzjk) obj).zzb.zze();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, Object obj2) {
        zza(obj, (zzme) obj2);
    }

    private static void zza(Object obj, zzme zzme) {
        ((zzjk) obj).zzb = zzme;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(Object obj, Object obj2) {
        zza(obj, (zzme) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, zzna zzna) throws IOException {
        ((zzme) obj).zza(zzna);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, zzna zzna) throws IOException {
        ((zzme) obj).zzb(zzna);
    }
}
