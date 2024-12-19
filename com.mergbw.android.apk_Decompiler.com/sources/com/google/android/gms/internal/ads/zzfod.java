package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzfod implements Runnable {
    public final /* synthetic */ zzfoe zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ zzfmn zzc;

    public /* synthetic */ zzfod(zzfoe zzfoe, String str, zzfmn zzfmn) {
        this.zza = zzfoe;
        this.zzb = str;
        this.zzc = zzfmn;
    }

    public final void run() {
        this.zza.zzb(this.zzb, this.zzc);
    }
}
