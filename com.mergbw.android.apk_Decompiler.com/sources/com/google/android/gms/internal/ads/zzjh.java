package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzjh extends zzcj {
    @Deprecated
    public static final zzn zzd = new zzjg();
    private static final String zzl = Integer.toString(1001, 36);
    private static final String zzm = Integer.toString(1002, 36);
    private static final String zzn = Integer.toString(1003, 36);
    private static final String zzo = Integer.toString(1004, 36);
    private static final String zzp = Integer.toString(1005, 36);
    private static final String zzq = Integer.toString(1006, 36);
    public final int zze;
    public final String zzf;
    public final int zzg;
    public final zzan zzh;
    public final int zzi;
    public final zzvo zzj;
    final boolean zzk;

    private zzjh(int i, Throwable th, int i2) {
        this(i, th, (String) null, i2, (String) null, -1, (zzan) null, 4, false);
    }

    public static zzjh zzb(Throwable th, String str, int i, zzan zzan, int i2, boolean z, int i3) {
        return new zzjh(1, th, (String) null, i3, str, i, zzan, zzan == null ? 4 : i2, z);
    }

    public static zzjh zzc(IOException iOException, int i) {
        return new zzjh(0, iOException, i);
    }

    public static zzjh zzd(RuntimeException runtimeException, int i) {
        return new zzjh(2, runtimeException, i);
    }

    /* access modifiers changed from: package-private */
    public final zzjh zza(zzvo zzvo) {
        String message = getMessage();
        int i = zzgd.zza;
        return new zzjh(message, getCause(), this.zzb, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, zzvo, this.zzc, this.zzk);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzjh(int r14, java.lang.Throwable r15, java.lang.String r16, int r17, java.lang.String r18, int r19, com.google.android.gms.internal.ads.zzan r20, int r21, boolean r22) {
        /*
            r13 = this;
            r4 = r14
            r8 = r21
            if (r4 == 0) goto L_0x005f
            r0 = 1
            if (r4 == r0) goto L_0x000f
            java.lang.String r0 = "Unexpected runtime error"
            r5 = r18
            r6 = r19
            goto L_0x0065
        L_0x000f:
            java.lang.String r1 = java.lang.String.valueOf(r20)
            int r2 = com.google.android.gms.internal.ads.zzgd.zza
            if (r8 == 0) goto L_0x0034
            if (r8 == r0) goto L_0x0031
            r0 = 2
            if (r8 == r0) goto L_0x002e
            r0 = 3
            if (r8 == r0) goto L_0x002b
            r0 = 4
            if (r8 != r0) goto L_0x0025
            java.lang.String r0 = "YES"
            goto L_0x0036
        L_0x0025:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        L_0x002b:
            java.lang.String r0 = "NO_EXCEEDS_CAPABILITIES"
            goto L_0x0036
        L_0x002e:
            java.lang.String r0 = "NO_UNSUPPORTED_DRM"
            goto L_0x0036
        L_0x0031:
            java.lang.String r0 = "NO_UNSUPPORTED_TYPE"
            goto L_0x0036
        L_0x0034:
            java.lang.String r0 = "NO"
        L_0x0036:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r5 = r18
            r2.append(r5)
            java.lang.String r3 = " error, index="
            r2.append(r3)
            r6 = r19
            r2.append(r6)
            java.lang.String r3 = ", format="
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = ", format_supported="
            r2.append(r1)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            goto L_0x0065
        L_0x005f:
            r5 = r18
            r6 = r19
            java.lang.String r0 = "Source error"
        L_0x0065:
            r1 = 0
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0072
            java.lang.String r1 = ": null"
            java.lang.String r0 = r0.concat(r1)
        L_0x0072:
            r1 = r0
            r9 = 0
            long r10 = android.os.SystemClock.elapsedRealtime()
            r0 = r13
            r2 = r15
            r3 = r17
            r4 = r14
            r5 = r18
            r6 = r19
            r7 = r20
            r8 = r21
            r12 = r22
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjh.<init>(int, java.lang.Throwable, java.lang.String, int, java.lang.String, int, com.google.android.gms.internal.ads.zzan, int, boolean):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzjh(String str, Throwable th, int i, int i2, String str2, int i3, zzan zzan, int i4, zzvo zzvo, long j, boolean z) {
        super(str, th, i, j);
        boolean z2;
        int i5;
        boolean z3 = z;
        boolean z4 = false;
        if (z3) {
            i5 = i2;
            if (i5 == 1) {
                i5 = 1;
                z2 = true;
            } else {
                z2 = false;
            }
        } else {
            i5 = i2;
            z2 = true;
        }
        zzeq.zzd(z2);
        zzeq.zzd(th != null ? true : z4);
        this.zze = i5;
        this.zzf = str2;
        this.zzg = i3;
        this.zzh = zzan;
        this.zzi = i4;
        this.zzj = zzvo;
        this.zzk = z3;
    }
}
