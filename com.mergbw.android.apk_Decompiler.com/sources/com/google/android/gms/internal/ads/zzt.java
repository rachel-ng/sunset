package com.google.android.gms.internal.ads;

import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.Arrays;
import java.util.Locale;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzt {
    public static final zzt zza;
    public static final zzt zzb;
    @Deprecated
    public static final zzn zzc = new zzp();
    private static final String zzj = Integer.toString(0, 36);
    private static final String zzk = Integer.toString(1, 36);
    private static final String zzl = Integer.toString(2, 36);
    private static final String zzm = Integer.toString(3, 36);
    private static final String zzn = Integer.toString(4, 36);
    private static final String zzo = Integer.toString(5, 36);
    public final int zzd;
    public final int zze;
    public final int zzf;
    public final byte[] zzg;
    public final int zzh;
    public final int zzi;
    private int zzp;

    static {
        zzr zzr = new zzr();
        zzr.zzc(1);
        zzr.zzb(2);
        zzr.zzd(3);
        zza = zzr.zzg();
        zzr zzr2 = new zzr();
        zzr2.zzc(1);
        zzr2.zzb(1);
        zzr2.zzd(2);
        zzb = zzr2.zzg();
    }

    /* synthetic */ zzt(int i, int i2, int i3, byte[] bArr, int i4, int i5, zzs zzs) {
        this.zzd = i;
        this.zze = i2;
        this.zzf = i3;
        this.zzg = bArr;
        this.zzh = i4;
        this.zzi = i5;
    }

    @Pure
    public static int zza(int i) {
        if (i == 1) {
            return 1;
        }
        if (i != 9) {
            return (i == 4 || i == 5 || i == 6 || i == 7) ? 2 : -1;
        }
        return 6;
    }

    @Pure
    public static int zzb(int i) {
        if (i == 1) {
            return 3;
        }
        if (i == 4) {
            return 10;
        }
        if (i == 13) {
            return 2;
        }
        if (i == 16) {
            return 6;
        }
        if (i != 18) {
            return (i == 6 || i == 7) ? 3 : -1;
        }
        return 7;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        r1 = r5.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0023, code lost:
        r1 = r5.zzi;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002b, code lost:
        r5 = r5.zzh;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000f, code lost:
        r1 = r5.zze;
     */
    @org.checkerframework.checker.nullness.qual.EnsuresNonNullIf(expression = {"#1"}, result = false)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzg(com.google.android.gms.internal.ads.zzt r5) {
        /*
            r0 = 1
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r5.zzd
            r2 = 2
            r3 = -1
            r4 = 0
            if (r1 == r3) goto L_0x000f
            if (r1 == r0) goto L_0x000f
            if (r1 != r2) goto L_0x0016
        L_0x000f:
            int r1 = r5.zze
            if (r1 == r3) goto L_0x0018
            if (r1 != r2) goto L_0x0016
            goto L_0x0018
        L_0x0016:
            r0 = r4
            goto L_0x0032
        L_0x0018:
            int r1 = r5.zzf
            if (r1 == r3) goto L_0x001f
            r2 = 3
            if (r1 != r2) goto L_0x0016
        L_0x001f:
            byte[] r1 = r5.zzg
            if (r1 != 0) goto L_0x0016
            int r1 = r5.zzi
            r2 = 8
            if (r1 == r3) goto L_0x002b
            if (r1 != r2) goto L_0x0016
        L_0x002b:
            int r5 = r5.zzh
            if (r5 == r3) goto L_0x0032
            if (r5 == r2) goto L_0x0032
            goto L_0x0016
        L_0x0032:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzt.zzg(com.google.android.gms.internal.ads.zzt):boolean");
    }

    private static String zzh(int i) {
        if (i == -1) {
            return "Unset color range";
        }
        if (i == 1) {
            return "Full range";
        }
        if (i == 2) {
            return "Limited range";
        }
        return "Undefined color range " + i;
    }

    private static String zzi(int i) {
        if (i == -1) {
            return "Unset color space";
        }
        if (i == 6) {
            return "BT2020";
        }
        if (i == 1) {
            return "BT709";
        }
        if (i == 2) {
            return "BT601";
        }
        return "Undefined color space " + i;
    }

    private static String zzj(int i) {
        if (i == -1) {
            return "Unset color transfer";
        }
        if (i == 10) {
            return "Gamma 2.2";
        }
        if (i == 1) {
            return "Linear";
        }
        if (i == 2) {
            return "sRGB";
        }
        if (i == 3) {
            return "SDR SMPTE 170M";
        }
        if (i == 6) {
            return "ST2084 PQ";
        }
        if (i == 7) {
            return "HLG";
        }
        return "Undefined color transfer " + i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzt zzt = (zzt) obj;
            return this.zzd == zzt.zzd && this.zze == zzt.zze && this.zzf == zzt.zzf && Arrays.equals(this.zzg, zzt.zzg) && this.zzh == zzt.zzh && this.zzi == zzt.zzi;
        }
    }

    public final int hashCode() {
        int i = this.zzp;
        if (i != 0) {
            return i;
        }
        int hashCode = ((((((((((this.zzd + 527) * 31) + this.zze) * 31) + this.zzf) * 31) + Arrays.hashCode(this.zzg)) * 31) + this.zzh) * 31) + this.zzi;
        this.zzp = hashCode;
        return hashCode;
    }

    public final String toString() {
        String str;
        int i = this.zzh;
        int i2 = this.zzf;
        int i3 = this.zze;
        String zzi2 = zzi(this.zzd);
        String zzh2 = zzh(i3);
        String zzj2 = zzj(i2);
        String str2 = "NA";
        if (i != -1) {
            str = i + "bit Luma";
        } else {
            str = str2;
        }
        int i4 = this.zzi;
        if (i4 != -1) {
            str2 = i4 + "bit Chroma";
        }
        return "ColorInfo(" + zzi2 + ", " + zzh2 + ", " + zzj2 + ", " + (this.zzg != null) + ", " + str + ", " + str2 + ")";
    }

    public final zzr zzc() {
        return new zzr(this, (zzq) null);
    }

    public final String zzd() {
        String str;
        String str2;
        if (zzf()) {
            str = String.format(Locale.US, "%s/%s/%s", new Object[]{zzi(this.zzd), zzh(this.zze), zzj(this.zzf)});
        } else {
            str = "NA/NA/NA";
        }
        if (zze()) {
            str2 = this.zzh + RemoteSettings.FORWARD_SLASH_STRING + this.zzi;
        } else {
            str2 = "NA/NA";
        }
        return str + RemoteSettings.FORWARD_SLASH_STRING + str2;
    }

    public final boolean zze() {
        return (this.zzh == -1 || this.zzi == -1) ? false : true;
    }

    public final boolean zzf() {
        return (this.zzd == -1 || this.zze == -1 || this.zzf == -1) ? false : true;
    }
}
