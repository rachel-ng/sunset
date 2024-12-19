package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzic implements Runnable {
    private final /* synthetic */ zzbd zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzhn zzc;

    zzic(zzhn zzhn, zzbd zzbd, String str) {
        this.zza = zzbd;
        this.zzb = str;
        this.zzc = zzhn;
    }

    public final void run() {
        this.zzc.zza.zzr();
        this.zzc.zza.zza(this.zza, this.zzb);
    }
}
