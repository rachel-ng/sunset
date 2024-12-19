package com.google.android.gms.internal.ads;

import androidx.work.WorkRequest;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzbgt {
    public static final zzbfv zza = zzbfv.zzb("gads:dynamite_load:fail:sample_rate", WorkRequest.MIN_BACKOFF_MILLIS);
    public static final zzbfv zzb = zzbfv.zzd("gads:report_dynamite_crash_in_background_thread", false);
    public static final zzbfv zzc = zzbfv.zzc("gads:public_beta:traffic_multiplier", "1.0");
    public static final zzbfv zzd = zzbfv.zzc("gads:sdk_crash_report_class_prefix", "com.google.");
    public static final zzbfv zze = zzbfv.zzd("gads:sdk_crash_report_enabled", false);
    public static final zzbfv zzf = zzbfv.zzd("gads:sdk_crash_report_full_stacktrace", false);
    public static final zzbfv zzg = zzbfv.zza("gads:trapped_exception_sample_rate", 0.01d);
}
