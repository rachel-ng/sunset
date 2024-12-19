package com.google.android.gms.internal.consent_sdk;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
final class zzam implements zzaw {
    private final zzag zza;
    private final zzam zzb = this;
    private final zzdq zzc;
    private final zzdq zzd;
    private final zzdq zze;
    private final zzdq zzf;
    private final zzdq zzg;

    /* synthetic */ zzam(zzag zzag, zzbp zzbp, zzal zzal) {
        this.zza = zzag;
        zzdq zzb2 = zzdm.zzb(new zzbx(zzag.zzb));
        this.zzc = zzb2;
        zzdn zzb3 = zzdo.zzb(zzbp);
        this.zzd = zzb3;
        zzdl zzdl = new zzdl();
        this.zze = zzdl;
        zzcb zzcb = new zzcb(zzag.zzb, zzb2, zzar.zza, zzat.zza, zzag.zzh, zzag.zzi, zzdl, zzag.zzc);
        this.zzf = zzcb;
        zzbv zzbv = new zzbv(zzb2, zzar.zza, zzcb);
        this.zzg = zzbv;
        zzdl.zzb(zzdl, zzdm.zzb(new zzbc(zzag.zzb, zzag.zzd, zzb2, zzag.zzc, zzb3, zzbv)));
    }

    public final zzbb zza() {
        return (zzbb) this.zze.zza();
    }
}
