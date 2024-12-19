package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zzdg;
import com.google.android.gms.ads.internal.client.zze;
import com.google.android.gms.ads.internal.client.zzs;
import com.google.android.gms.ads.rewarded.OnAdMetadataChangedListener;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzffw implements OnAdMetadataChangedListener, zzdaz, zzczo, zzczl, zzdab, zzdbw, zzfeh, zzdhi {
    private final zzfiy zza;
    private final AtomicReference zzb = new AtomicReference();
    private final AtomicReference zzc = new AtomicReference();
    private final AtomicReference zzd = new AtomicReference();
    private final AtomicReference zze = new AtomicReference();
    private final AtomicReference zzf = new AtomicReference();
    private final AtomicReference zzg = new AtomicReference();
    private final AtomicReference zzh = new AtomicReference();

    public zzffw(zzfiy zzfiy) {
        this.zza = zzfiy;
    }

    public final void onAdMetadataChanged() {
        zzfdy.zza(this.zzb, new zzffk());
    }

    public final void zza() {
        this.zza.zza();
        zzfdy.zza(this.zzd, new zzffs());
        zzfdy.zza(this.zze, new zzfft());
    }

    public final void zzb() {
        zzfdy.zza(this.zze, new zzffu());
    }

    public final void zzc() {
        zzfdy.zza(this.zzd, new zzffe());
        zzfdy.zza(this.zze, new zzfff());
        zzfdy.zza(this.zzd, new zzffg());
    }

    public final void zzdB(zze zze2) {
        int i = zze2.zza;
        zzfdy.zza(this.zzc, new zzffp(zze2));
        zzfdy.zza(this.zzc, new zzffq(i));
        zzfdy.zza(this.zze, new zzffr(i));
    }

    public final void zzdG() {
        zzfdy.zza(this.zzd, new zzffl());
    }

    public final void zzdf() {
    }

    public final void zzds(zzbyh zzbyh, String str, String str2) {
        zzfdy.zza(this.zzd, new zzffv(zzbyh));
        zzfdy.zza(this.zzf, new zzffb(zzbyh, str, str2));
        zzfdy.zza(this.zze, new zzffc(zzbyh));
        zzfdy.zza(this.zzg, new zzffd(zzbyh, str, str2));
    }

    public final void zze() {
        zzfdy.zza(this.zze, new zzffo());
    }

    public final void zzf() {
        zzfdy.zza(this.zze, new zzffa());
    }

    public final void zzg(OnAdMetadataChangedListener onAdMetadataChangedListener) {
        this.zzb.set(onAdMetadataChangedListener);
    }

    public final void zzh(zzs zzs) {
        zzfdy.zza(this.zzh, new zzffh(zzs));
    }

    public final void zzi(zzdg zzdg) {
        this.zzh.set(zzdg);
    }

    public final void zzj(zzbzd zzbzd) {
        this.zzd.set(zzbzd);
    }

    public final void zzk(zzbzh zzbzh) {
        this.zzc.set(zzbzh);
    }

    public final void zzl(zzfeh zzfeh) {
        throw null;
    }

    @Deprecated
    public final void zzm(zzbyn zzbyn) {
        this.zze.set(zzbyn);
    }

    @Deprecated
    public final void zzn(zzbyi zzbyi) {
        this.zzg.set(zzbyi);
    }

    public final void zzo(zzbzi zzbzi) {
        this.zzf.set(zzbzi);
    }

    public final void zzq(zze zze2) {
        zzfdy.zza(this.zzd, new zzffm(zze2));
        zzfdy.zza(this.zzd, new zzffn(zze2));
    }

    public final void zzs() {
        zzfdy.zza(this.zzc, new zzffi());
        zzfdy.zza(this.zze, new zzffj());
    }
}
