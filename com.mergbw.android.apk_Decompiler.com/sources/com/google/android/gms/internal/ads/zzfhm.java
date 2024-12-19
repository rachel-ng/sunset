package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.formats.AdManagerAdViewOptions;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.ads.internal.client.zzcb;
import com.google.android.gms.ads.internal.client.zzcf;
import com.google.android.gms.ads.internal.client.zzfk;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfhm {
    /* access modifiers changed from: private */
    public zzl zza;
    /* access modifiers changed from: private */
    public zzq zzb;
    /* access modifiers changed from: private */
    public String zzc;
    /* access modifiers changed from: private */
    public zzfk zzd;
    /* access modifiers changed from: private */
    public boolean zze;
    /* access modifiers changed from: private */
    public ArrayList zzf;
    /* access modifiers changed from: private */
    public ArrayList zzg;
    /* access modifiers changed from: private */
    public zzbhk zzh;
    /* access modifiers changed from: private */
    public zzw zzi;
    /* access modifiers changed from: private */
    public AdManagerAdViewOptions zzj;
    /* access modifiers changed from: private */
    public PublisherAdViewOptions zzk;
    /* access modifiers changed from: private */
    public zzcb zzl;
    /* access modifiers changed from: private */
    public int zzm = 1;
    /* access modifiers changed from: private */
    public zzbnz zzn;
    /* access modifiers changed from: private */
    public final zzfgz zzo = new zzfgz();
    /* access modifiers changed from: private */
    public boolean zzp = false;
    /* access modifiers changed from: private */
    public boolean zzq = false;
    /* access modifiers changed from: private */
    public zzepc zzr;
    /* access modifiers changed from: private */
    public boolean zzs = false;
    /* access modifiers changed from: private */
    public Bundle zzt;
    /* access modifiers changed from: private */
    public zzcf zzu;

    public final zzfhm zzA(Bundle bundle) {
        this.zzt = bundle;
        return this;
    }

    public final zzfhm zzB(boolean z) {
        this.zze = z;
        return this;
    }

    public final zzfhm zzC(int i) {
        this.zzm = i;
        return this;
    }

    public final zzfhm zzD(zzbhk zzbhk) {
        this.zzh = zzbhk;
        return this;
    }

    public final zzfhm zzE(ArrayList arrayList) {
        this.zzf = arrayList;
        return this;
    }

    public final zzfhm zzF(ArrayList arrayList) {
        this.zzg = arrayList;
        return this;
    }

    public final zzfhm zzG(PublisherAdViewOptions publisherAdViewOptions) {
        this.zzk = publisherAdViewOptions;
        if (publisherAdViewOptions != null) {
            this.zze = publisherAdViewOptions.zzc();
            this.zzl = publisherAdViewOptions.zza();
        }
        return this;
    }

    public final zzfhm zzH(zzl zzl2) {
        this.zza = zzl2;
        return this;
    }

    public final zzfhm zzI(zzfk zzfk) {
        this.zzd = zzfk;
        return this;
    }

    public final zzfho zzJ() {
        Preconditions.checkNotNull(this.zzc, "ad unit must not be null");
        Preconditions.checkNotNull(this.zzb, "ad size must not be null");
        Preconditions.checkNotNull(this.zza, "ad request must not be null");
        return new zzfho(this, (zzfhn) null);
    }

    public final String zzL() {
        return this.zzc;
    }

    public final boolean zzS() {
        return this.zzq;
    }

    public final zzfhm zzU(zzcf zzcf) {
        this.zzu = zzcf;
        return this;
    }

    public final zzl zzf() {
        return this.zza;
    }

    public final zzq zzh() {
        return this.zzb;
    }

    public final zzfgz zzp() {
        return this.zzo;
    }

    public final zzfhm zzq(zzfho zzfho) {
        this.zzo.zza(zzfho.zzo.zza);
        this.zza = zzfho.zzd;
        this.zzb = zzfho.zze;
        this.zzu = zzfho.zzt;
        this.zzc = zzfho.zzf;
        this.zzd = zzfho.zza;
        this.zzf = zzfho.zzg;
        this.zzg = zzfho.zzh;
        this.zzh = zzfho.zzi;
        this.zzi = zzfho.zzj;
        zzr(zzfho.zzl);
        zzG(zzfho.zzm);
        this.zzp = zzfho.zzp;
        this.zzq = zzfho.zzq;
        this.zzr = zzfho.zzc;
        this.zzs = zzfho.zzr;
        this.zzt = zzfho.zzs;
        return this;
    }

    public final zzfhm zzr(AdManagerAdViewOptions adManagerAdViewOptions) {
        this.zzj = adManagerAdViewOptions;
        if (adManagerAdViewOptions != null) {
            this.zze = adManagerAdViewOptions.getManualImpressionsEnabled();
        }
        return this;
    }

    public final zzfhm zzs(zzq zzq2) {
        this.zzb = zzq2;
        return this;
    }

    public final zzfhm zzt(String str) {
        this.zzc = str;
        return this;
    }

    public final zzfhm zzu(zzw zzw) {
        this.zzi = zzw;
        return this;
    }

    public final zzfhm zzv(zzepc zzepc) {
        this.zzr = zzepc;
        return this;
    }

    public final zzfhm zzw(zzbnz zzbnz) {
        this.zzn = zzbnz;
        this.zzd = new zzfk(false, true, false);
        return this;
    }

    public final zzfhm zzx(boolean z) {
        this.zzp = z;
        return this;
    }

    public final zzfhm zzy(boolean z) {
        this.zzq = z;
        return this;
    }

    public final zzfhm zzz(boolean z) {
        this.zzs = true;
        return this;
    }
}
