package com.google.android.gms.internal.ads;

import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfrk extends zzfrg {
    private final String zza;
    private final boolean zzb;
    private final boolean zzc;

    /* synthetic */ zzfrk(String str, boolean z, boolean z2, zzfrj zzfrj) {
        this.zza = str;
        this.zzb = z;
        this.zzc = z2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfrg) {
            zzfrg zzfrg = (zzfrg) obj;
            return this.zza.equals(zzfrg.zzb()) && this.zzb == zzfrg.zzd() && this.zzc == zzfrg.zzc();
        }
    }

    public final int hashCode() {
        int i = 1237;
        int hashCode = (((this.zza.hashCode() ^ 1000003) * 1000003) ^ (true != this.zzb ? 1237 : 1231)) * 1000003;
        if (true == this.zzc) {
            i = 1231;
        }
        return hashCode ^ i;
    }

    public final String toString() {
        return "AdShield2Options{clientVersion=" + this.zza + ", shouldGetAdvertisingId=" + this.zzb + ", isGooglePlayServicesAvailable=" + this.zzc + VectorFormat.DEFAULT_SUFFIX;
    }

    public final String zzb() {
        return this.zza;
    }

    public final boolean zzc() {
        return this.zzc;
    }

    public final boolean zzd() {
        return this.zzb;
    }
}
