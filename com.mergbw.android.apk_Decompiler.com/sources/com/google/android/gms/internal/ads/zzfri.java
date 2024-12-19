package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfri extends zzfrf {
    private String zza;
    private boolean zzb;
    private boolean zzc;
    private byte zzd;

    zzfri() {
    }

    public final zzfrf zza(String str) {
        if (str != null) {
            this.zza = str;
            return this;
        }
        throw new NullPointerException("Null clientVersion");
    }

    public final zzfrf zzb(boolean z) {
        this.zzc = true;
        this.zzd = (byte) (this.zzd | 2);
        return this;
    }

    public final zzfrf zzc(boolean z) {
        this.zzb = z;
        this.zzd = (byte) (this.zzd | 1);
        return this;
    }

    public final zzfrg zzd() {
        String str;
        if (this.zzd == 3 && (str = this.zza) != null) {
            return new zzfrk(str, this.zzb, this.zzc, (zzfrj) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" clientVersion");
        }
        if ((this.zzd & 1) == 0) {
            sb.append(" shouldGetAdvertisingId");
        }
        if ((this.zzd & 2) == 0) {
            sb.append(" isGooglePlayServicesAvailable");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
