package com.google.android.gms.internal.ads;

import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzftt extends zzftz {
    private final String zzb;
    private final int zzc;

    /* synthetic */ zzftt(String str, boolean z, boolean z2, zzftp zzftp, zzftq zzftq, int i, zzfts zzfts) {
        this.zzb = str;
        this.zzc = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzftz) {
            zzftz zzftz = (zzftz) obj;
            if (this.zzb.equals(zzftz.zzc())) {
                zzftz.zzd();
                zzftz.zze();
                zzftz.zza();
                zzftz.zzb();
                int i = this.zzc;
                int zzf = zzftz.zzf();
                if (i == 0) {
                    throw null;
                } else if (zzf == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.zzb.hashCode() ^ 1000003;
        if (this.zzc != 0) {
            return (((((hashCode * 1000003) ^ 1237) * 1000003) ^ 1237) * 583896283) ^ 1;
        }
        throw null;
    }

    public final String toString() {
        String str = this.zzc != 1 ? "null" : "READ_AND_WRITE";
        String str2 = this.zzb;
        return "FileComplianceOptions{fileOwner=" + str2 + ", hasDifferentDmaOwner=false, skipChecks=false, dataForwardingNotAllowedResolver=null, multipleProductIdGroupsResolver=null, filePurpose=" + str + VectorFormat.DEFAULT_SUFFIX;
    }

    public final zzftp zza() {
        return null;
    }

    public final zzftq zzb() {
        return null;
    }

    public final String zzc() {
        return this.zzb;
    }

    public final boolean zzd() {
        return false;
    }

    public final boolean zze() {
        return false;
    }

    public final int zzf() {
        return this.zzc;
    }
}
