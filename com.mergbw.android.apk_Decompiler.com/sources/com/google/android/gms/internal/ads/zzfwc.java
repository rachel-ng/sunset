package com.google.android.gms.internal.ads;

import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfwc extends zzfwu {
    private final String zza;
    private final String zzb;

    /* synthetic */ zzfwc(String str, String str2, zzfwb zzfwb) {
        this.zza = str;
        this.zzb = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfwu) {
            zzfwu zzfwu = (zzfwu) obj;
            String str = this.zza;
            if (str != null ? str.equals(zzfwu.zzb()) : zzfwu.zzb() == null) {
                String str2 = this.zzb;
                if (str2 != null ? str2.equals(zzfwu.zza()) : zzfwu.zza() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public final String toString() {
        return "OverlayDisplayUpdateRequest{sessionToken=" + this.zza + ", appId=" + this.zzb + VectorFormat.DEFAULT_SUFFIX;
    }

    public final String zza() {
        return this.zzb;
    }

    public final String zzb() {
        return this.zza;
    }

    public final int hashCode() {
        int i;
        String str = this.zza;
        int i2 = 0;
        if (str == null) {
            i = 0;
        } else {
            i = str.hashCode();
        }
        String str2 = this.zzb;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return ((i ^ 1000003) * 1000003) ^ i2;
    }
}
