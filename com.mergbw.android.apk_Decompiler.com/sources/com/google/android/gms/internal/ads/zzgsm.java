package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgsm implements zzghw {
    private final zzgqk zza;
    private final zzgtl zzb;
    private final zzgtl zzc;

    /* synthetic */ zzgsm(zzgqk zzgqk, zzgsl zzgsl) {
        zzgtl zzgtl;
        this.zza = zzgqk;
        if (zzgqk.zzg()) {
            zzgtm zzb2 = zzgpg.zza().zzb();
            zzgtr zza2 = zzgoy.zza(zzgqk);
            this.zzb = zzb2.zza(zza2, "mac", "compute");
            zzgtl = zzb2.zza(zza2, "mac", "verify");
        } else {
            zzgtl = zzgoy.zza;
            this.zzb = zzgtl;
        }
        this.zzc = zzgtl;
    }
}
