package com.google.android.gms.internal.measurement;

import java.io.File;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzch implements zzci {
    public final String zza(String str, zzcj zzcj, zzck zzck) {
        return str;
    }

    public final /* synthetic */ String zza(String str) {
        return zza(str, zzcj.zza);
    }

    public final /* synthetic */ String zza(File file, String str) {
        return zza(file, str, zzcj.zza);
    }

    public final /* synthetic */ String zza(String str, zzcj zzcj) {
        return zza(str, zzcj, zzck.RAW_FILE_IO_TYPE);
    }

    public final /* synthetic */ String zza(File file, String str, zzcj zzcj) {
        return zza(new File(file, str).getPath(), zzcj);
    }

    zzch() {
    }
}
