package com.google.android.gms.internal.ads;

import java.io.IOException;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public class zzch extends IOException {
    public final boolean zza;
    public final int zzb;

    protected zzch(String str, Throwable th, boolean z, int i) {
        super(str, th);
        this.zza = z;
        this.zzb = i;
    }

    public static zzch zza(String str, Throwable th) {
        return new zzch(str, th, true, 1);
    }

    public static zzch zzb(String str, Throwable th) {
        return new zzch(str, th, true, 0);
    }

    public static zzch zzc(String str) {
        return new zzch(str, (Throwable) null, false, 1);
    }

    public final String getMessage() {
        String message = super.getMessage();
        return message + "{contentIsMalformed=" + this.zza + ", dataType=" + this.zzb + VectorFormat.DEFAULT_SUFFIX;
    }
}
