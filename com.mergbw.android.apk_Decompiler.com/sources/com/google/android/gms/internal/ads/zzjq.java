package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Looper;
import com.google.android.exoplayer2.ExoPlayer;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzjq {
    final Context zza;
    zzer zzb = zzer.zza;
    zzfyw zzc;
    zzfyw zzd;
    zzfyw zze;
    zzfyw zzf;
    zzfyw zzg;
    zzfxu zzh;
    Looper zzi = zzgd.zzy();
    zzk zzj = zzk.zza;
    int zzk = 1;
    boolean zzl = true;
    zzmr zzm = zzmr.zze;
    long zzn = 500;
    long zzo = ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS;
    boolean zzp = true;
    boolean zzq;
    String zzr = "";
    zzja zzs = new zzja(0.97f, 1.03f, 1000, 1.0E-7f, zzgd.zzr(20), zzgd.zzr(500), 0.999f, (zziz) null);

    static /* synthetic */ zzvn zza(Context context) {
        return new zzvb(context, new zzadn());
    }

    public zzjq(Context context, zzcgn zzcgn) {
        Context context2 = context;
        zzjj zzjj = new zzjj(zzcgn);
        zzjk zzjk = new zzjk(context2);
        zzjl zzjl = new zzjl(context2);
        zzjm zzjm = new zzjm();
        zzjn zzjn = new zzjn(context2);
        zzjo zzjo = new zzjo();
        context.getClass();
        this.zza = context2;
        this.zzc = zzjj;
        this.zzd = zzjk;
        this.zze = zzjl;
        this.zzf = zzjm;
        this.zzg = zzjn;
        this.zzh = zzjo;
    }
}
