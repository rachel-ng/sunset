package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzuq implements zzwg {
    private final zzaea zza;
    private zzadu zzb;
    private zzadv zzc;

    public zzuq(zzaea zzaea) {
        this.zza = zzaea;
    }

    public final long zzb() {
        zzadv zzadv = this.zzc;
        if (zzadv != null) {
            return zzadv.zzf();
        }
        return -1;
    }

    public final void zzc() {
        zzadu zzadu = this.zzb;
        if (zzadu != null && (zzadu instanceof zzajg)) {
            ((zzajg) zzadu).zza();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r14v0 */
    /* JADX WARNING: type inference failed for: r14v1, types: [int] */
    /* JADX WARNING: type inference failed for: r14v4 */
    /* JADX WARNING: type inference failed for: r14v5 */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
        if (r0.zzf() != r11) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0078, code lost:
        if (r0.zzf() != r11) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007b, code lost:
        r2 = false;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(com.google.android.gms.internal.ads.zzu r8, android.net.Uri r9, java.util.Map r10, long r11, long r13, com.google.android.gms.internal.ads.zzadx r15) throws java.io.IOException {
        /*
            r7 = this;
            com.google.android.gms.internal.ads.zzadi r6 = new com.google.android.gms.internal.ads.zzadi
            r0 = r6
            r1 = r8
            r2 = r11
            r4 = r13
            r0.<init>(r1, r2, r4)
            r7.zzc = r6
            com.google.android.gms.internal.ads.zzadu r8 = r7.zzb
            if (r8 == 0) goto L_0x0010
            return
        L_0x0010:
            com.google.android.gms.internal.ads.zzaea r8 = r7.zza
            com.google.android.gms.internal.ads.zzadu[] r8 = r8.zza(r9, r10)
            int r10 = r8.length
            com.google.android.gms.internal.ads.zzgaz r13 = com.google.android.gms.internal.ads.zzgbc.zzi(r10)
            r14 = 0
            r0 = 1
            if (r10 != r0) goto L_0x0025
            r8 = r8[r14]
            r7.zzb = r8
            goto L_0x00cd
        L_0x0025:
            r1 = r14
        L_0x0026:
            if (r1 >= r10) goto L_0x0087
            r2 = r8[r1]
            boolean r3 = r2.zzf(r6)     // Catch:{ EOFException -> 0x006e, all -> 0x0059 }
            if (r3 == 0) goto L_0x0045
            r7.zzb = r2     // Catch:{ EOFException -> 0x006e, all -> 0x0059 }
            if (r2 != 0) goto L_0x003e
            long r1 = r6.zzf()
            int r10 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r10 != 0) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            r0 = r14
        L_0x003e:
            com.google.android.gms.internal.ads.zzeq.zzf(r0)
            r6.zzj()
            goto L_0x0087
        L_0x0045:
            java.util.List r2 = r2.zzc()     // Catch:{ EOFException -> 0x006e, all -> 0x0059 }
            r13.zzh(r2)     // Catch:{ EOFException -> 0x006e, all -> 0x0059 }
            com.google.android.gms.internal.ads.zzadu r2 = r7.zzb
            if (r2 != 0) goto L_0x007d
            long r2 = r6.zzf()
            int r2 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x007b
            goto L_0x007d
        L_0x0059:
            r8 = move-exception
            com.google.android.gms.internal.ads.zzadu r9 = r7.zzb
            if (r9 != 0) goto L_0x0066
            long r9 = r6.zzf()
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 != 0) goto L_0x0067
        L_0x0066:
            r14 = r0
        L_0x0067:
            com.google.android.gms.internal.ads.zzeq.zzf(r14)
            r6.zzj()
            throw r8
        L_0x006e:
            com.google.android.gms.internal.ads.zzadu r2 = r7.zzb
            if (r2 != 0) goto L_0x007d
            long r2 = r6.zzf()
            int r2 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x007b
            goto L_0x007d
        L_0x007b:
            r2 = r14
            goto L_0x007e
        L_0x007d:
            r2 = r0
        L_0x007e:
            com.google.android.gms.internal.ads.zzeq.zzf(r2)
            r6.zzj()
            int r1 = r1 + 1
            goto L_0x0026
        L_0x0087:
            com.google.android.gms.internal.ads.zzadu r10 = r7.zzb
            if (r10 != 0) goto L_0x00cd
            com.google.android.gms.internal.ads.zzxs r10 = new com.google.android.gms.internal.ads.zzxs
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
        L_0x0092:
            int r12 = r8.length
            if (r14 >= r12) goto L_0x00ae
            r15 = r8[r14]
            java.lang.Class r15 = r15.getClass()
            java.lang.String r15 = r15.getSimpleName()
            r11.append(r15)
            int r12 = r12 + -1
            if (r14 >= r12) goto L_0x00ab
            java.lang.String r12 = ", "
            r11.append(r12)
        L_0x00ab:
            int r14 = r14 + 1
            goto L_0x0092
        L_0x00ae:
            java.lang.String r8 = r11.toString()
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "None of the available extractors ("
            r11.<init>(r12)
            r11.append(r8)
            java.lang.String r8 = ") could read the stream."
            r11.append(r8)
            java.lang.String r8 = r11.toString()
            com.google.android.gms.internal.ads.zzgbc r11 = r13.zzi()
            r10.<init>(r8, r9, r11)
            throw r10
        L_0x00cd:
            com.google.android.gms.internal.ads.zzadu r8 = r7.zzb
            r8.zzd(r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzuq.zzd(com.google.android.gms.internal.ads.zzu, android.net.Uri, java.util.Map, long, long, com.google.android.gms.internal.ads.zzadx):void");
    }

    public final void zze() {
        if (this.zzb != null) {
            this.zzb = null;
        }
        this.zzc = null;
    }

    public final int zza(zzaeq zzaeq) throws IOException {
        zzadu zzadu = this.zzb;
        zzadu.getClass();
        zzadv zzadv = this.zzc;
        zzadv.getClass();
        return zzadu.zzb(zzadv, zzaeq);
    }

    public final void zzf(long j, long j2) {
        zzadu zzadu = this.zzb;
        zzadu.getClass();
        zzadu.zze(j, j2);
    }
}
