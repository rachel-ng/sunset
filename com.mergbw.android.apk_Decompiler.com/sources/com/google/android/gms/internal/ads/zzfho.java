package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.formats.AdManagerAdViewOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zzcb;
import com.google.android.gms.ads.internal.client.zzcf;
import com.google.android.gms.ads.internal.client.zzfk;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.util.zzt;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfho {
    public final zzfk zza;
    public final zzbnz zzb;
    public final zzepc zzc;
    public final zzl zzd;
    public final zzq zze;
    public final String zzf;
    public final ArrayList zzg;
    public final ArrayList zzh;
    public final zzbhk zzi;
    public final zzw zzj;
    public final int zzk;
    public final AdManagerAdViewOptions zzl;
    public final PublisherAdViewOptions zzm;
    public final zzcb zzn;
    public final zzfhb zzo;
    public final boolean zzp;
    public final boolean zzq;
    public final boolean zzr;
    public final Bundle zzs;
    public final zzcf zzt;

    /* synthetic */ zzfho(zzfhm zzfhm, zzfhn zzfhn) {
        zzfk zzfk;
        zzbhk zzbhk;
        this.zze = zzfhm.zzb;
        this.zzf = zzfhm.zzc;
        this.zzt = zzfhm.zzu;
        int i = zzfhm.zza.zza;
        long j = zzfhm.zza.zzb;
        Bundle bundle = zzfhm.zza.zzc;
        int i2 = zzfhm.zza.zzd;
        List list = zzfhm.zza.zze;
        boolean z = zzfhm.zza.zzf;
        int i3 = zzfhm.zza.zzg;
        boolean z2 = true;
        if (!zzfhm.zza.zzh && !zzfhm.zze) {
            z2 = false;
        }
        this.zzd = new zzl(i, j, bundle, i2, list, z, i3, z2, zzfhm.zza.zzi, zzfhm.zza.zzj, zzfhm.zza.zzk, zzfhm.zza.zzl, zzfhm.zza.zzm, zzfhm.zza.zzn, zzfhm.zza.zzo, zzfhm.zza.zzp, zzfhm.zza.zzq, zzfhm.zza.zzr, zzfhm.zza.zzs, zzfhm.zza.zzt, zzfhm.zza.zzu, zzfhm.zza.zzv, zzt.zza(zzfhm.zza.zzw), zzfhm.zza.zzx, zzfhm.zza.zzy, zzfhm.zza.zzz);
        if (zzfhm.zzd != null) {
            zzfk = zzfhm.zzd;
        } else {
            zzfk = zzfhm.zzh != null ? zzfhm.zzh.zzf : null;
        }
        this.zza = zzfk;
        this.zzg = zzfhm.zzf;
        this.zzh = zzfhm.zzg;
        if (zzfhm.zzf == null) {
            zzbhk = null;
        } else {
            zzbhk = zzfhm.zzh == null ? new zzbhk(new NativeAdOptions.Builder().build()) : zzfhm.zzh;
        }
        this.zzi = zzbhk;
        this.zzj = zzfhm.zzi;
        this.zzk = zzfhm.zzm;
        this.zzl = zzfhm.zzj;
        this.zzm = zzfhm.zzk;
        this.zzn = zzfhm.zzl;
        this.zzb = zzfhm.zzn;
        this.zzo = new zzfhb(zzfhm.zzo, (zzfha) null);
        this.zzp = zzfhm.zzp;
        this.zzq = zzfhm.zzq;
        this.zzc = zzfhm.zzr;
        this.zzr = zzfhm.zzs;
        this.zzs = zzfhm.zzt;
    }

    public final zzbjm zza() {
        PublisherAdViewOptions publisherAdViewOptions = this.zzm;
        if (publisherAdViewOptions == null && this.zzl == null) {
            return null;
        }
        if (publisherAdViewOptions != null) {
            return publisherAdViewOptions.zzb();
        }
        return this.zzl.zza();
    }

    public final boolean zzb() {
        zzbeg zzbeg = zzbep.zzde;
        return this.zzf.matches((String) zzba.zzc().zza(zzbeg));
    }
}
