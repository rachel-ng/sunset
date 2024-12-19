package com.google.android.gms.measurement.internal;

import android.net.Uri;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzkh implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ Uri zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zzki zze;

    zzkh(zzki zzki, boolean z, Uri uri, String str, String str2) {
        this.zza = z;
        this.zzb = uri;
        this.zzc = str;
        this.zzd = str2;
        this.zze = zzki;
    }

    public final void run() {
        zzki.zza(this.zze, this.zza, this.zzb, this.zzc, this.zzd);
    }
}
