package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzftr extends zzfty {
    private String zza;
    private byte zzb;
    private int zzc;

    zzftr() {
    }

    public final zzfty zza(String str) {
        this.zza = "";
        return this;
    }

    public final zzfty zzb(boolean z) {
        this.zzb = (byte) (this.zzb | 1);
        return this;
    }

    public final zzfty zzc(boolean z) {
        this.zzb = (byte) (this.zzb | 2);
        return this;
    }

    public final zzftz zzd() {
        if (this.zzb == 3 && this.zza != null && this.zzc != 0) {
            return new zzftt(this.zza, false, false, (zzftp) null, (zzftq) null, this.zzc, (zzfts) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" fileOwner");
        }
        if ((this.zzb & 1) == 0) {
            sb.append(" hasDifferentDmaOwner");
        }
        if ((this.zzb & 2) == 0) {
            sb.append(" skipChecks");
        }
        if (this.zzc == 0) {
            sb.append(" filePurpose");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    public final zzfty zze(int i) {
        this.zzc = 1;
        return this;
    }
}
