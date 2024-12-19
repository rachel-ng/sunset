package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzhe implements Thread.UncaughtExceptionHandler {
    private final String zza;
    private final /* synthetic */ zzhc zzb;

    public zzhe(zzhc zzhc, String str) {
        this.zzb = zzhc;
        Preconditions.checkNotNull(str);
        this.zza = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zzb.zzj().zzg().zza(this.zza, th);
    }
}
