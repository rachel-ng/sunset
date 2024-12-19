package com.google.android.gms.internal.consent_sdk;

import android.app.Application;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
final class zzag extends zza {
    /* access modifiers changed from: private */
    public final zzag zza = this;
    /* access modifiers changed from: private */
    public final zzdq zzb;
    /* access modifiers changed from: private */
    public final zzdq zzc;
    /* access modifiers changed from: private */
    public final zzdq zzd;
    private final zzdq zze;
    private final zzdq zzf;
    private final zzdq zzg;
    /* access modifiers changed from: private */
    public final zzdq zzh;
    /* access modifiers changed from: private */
    public final zzdq zzi;
    private final zzdq zzj;
    private final zzdq zzk;
    private final zzdq zzl;

    /* synthetic */ zzag(Application application, zzaf zzaf) {
        zzdn zzb2 = zzdo.zzb(application);
        this.zzb = zzb2;
        zzdq zzb3 = zzdm.zzb(new zzaq(zzb2));
        this.zzc = zzb3;
        zzdq zzb4 = zzdm.zzb(zzac.zza);
        this.zzd = zzb4;
        zzae zzae = new zzae(this);
        this.zze = zzae;
        zzdq zzb5 = zzdm.zzb(new zzbo(zzae, zzat.zza));
        this.zzf = zzb5;
        zzo zzo = new zzo(zzb2, zzb3);
        this.zzg = zzo;
        zzdq zzb6 = zzdm.zzb(new zzf(zzat.zza));
        this.zzh = zzb6;
        zzao zzao = new zzao(zzb2, zzb3, zzat.zza);
        this.zzi = zzao;
        zzaa zzaa = new zzaa(zzb6, zzao, zzb3);
        this.zzj = zzaa;
        zzv zzv = new zzv(zzb2, zzb4, zzar.zza, zzat.zza, zzb3, zzb5, zzo, zzaa, zzb6);
        this.zzk = zzv;
        this.zzl = zzdm.zzb(new zzk(zzb3, zzv, zzb5));
    }

    public final zzj zzb() {
        return (zzj) this.zzl.zza();
    }

    public final zzbn zzc() {
        return (zzbn) this.zzf.zza();
    }
}
