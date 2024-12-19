package com.google.android.gms.internal.ads;

import android.os.IBinder;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfvu extends zzfwo {
    private IBinder zza;
    private String zzb;
    private int zzc;
    private float zzd;
    private int zze;
    private String zzf;
    private byte zzg;

    zzfvu() {
    }

    public final zzfwo zza(String str) {
        this.zzf = str;
        return this;
    }

    public final zzfwo zzb(String str) {
        this.zzb = str;
        return this;
    }

    public final zzfwo zzc(int i) {
        this.zzg = (byte) (this.zzg | 4);
        return this;
    }

    public final zzfwo zzd(int i) {
        this.zzc = i;
        this.zzg = (byte) (this.zzg | 1);
        return this;
    }

    public final zzfwo zze(float f) {
        this.zzd = f;
        this.zzg = (byte) (this.zzg | 2);
        return this;
    }

    public final zzfwo zzf(int i) {
        this.zzg = (byte) (this.zzg | 8);
        return this;
    }

    public final zzfwo zzg(IBinder iBinder) {
        if (iBinder != null) {
            this.zza = iBinder;
            return this;
        }
        throw new NullPointerException("Null windowToken");
    }

    public final zzfwo zzh(int i) {
        this.zze = i;
        this.zzg = (byte) (this.zzg | 16);
        return this;
    }

    public final zzfwp zzi() {
        IBinder iBinder;
        if (this.zzg == 31 && (iBinder = this.zza) != null) {
            return new zzfvw(iBinder, this.zzb, this.zzc, this.zzd, 0, 0, (String) null, this.zze, (String) null, this.zzf, (String) null, (zzfvv) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" windowToken");
        }
        if ((this.zzg & 1) == 0) {
            sb.append(" layoutGravity");
        }
        if ((this.zzg & 2) == 0) {
            sb.append(" layoutVerticalMargin");
        }
        if ((this.zzg & 4) == 0) {
            sb.append(" displayMode");
        }
        if ((this.zzg & 8) == 0) {
            sb.append(" triggerMode");
        }
        if ((this.zzg & 16) == 0) {
            sb.append(" windowWidthPx");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
