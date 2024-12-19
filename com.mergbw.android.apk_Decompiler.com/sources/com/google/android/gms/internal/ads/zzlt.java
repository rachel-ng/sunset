package com.google.android.gms.internal.ads;

import android.util.Pair;
import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzlt {
    private final zzcz zza = new zzcz();
    private final zzdb zzb = new zzdb();
    private final zzmx zzc;
    private final zzfb zzd;
    private long zze;
    private int zzf;
    private boolean zzg;
    private zzlq zzh;
    private zzlq zzi;
    private zzlq zzj;
    private int zzk;
    private Object zzl;
    private long zzm;
    private final zzkz zzn;

    public zzlt(zzmx zzmx, zzfb zzfb, zzkz zzkz) {
        this.zzc = zzmx;
        this.zzd = zzfb;
        this.zzn = zzkz;
    }

    private final boolean zzA(zzdc zzdc, zzvo zzvo) {
        if (!zzC(zzvo)) {
            return false;
        }
        int i = zzdc.zzn(zzvo.zza, this.zza).zzd;
        if (zzdc.zze(i, this.zzb, 0).zzq == zzdc.zza(zzvo.zza)) {
            return true;
        }
        return false;
    }

    private final boolean zzB(zzdc zzdc) {
        zzlq zzlq = this.zzh;
        if (zzlq == null) {
            return true;
        }
        int zza2 = zzdc.zza(zzlq.zzb);
        while (true) {
            zza2 = zzdc.zzi(zza2, this.zza, this.zzb, this.zzf, this.zzg);
            while (true) {
                zzlq.getClass();
                if (zzlq.zzg() == null || zzlq.zzf.zzg) {
                    zzlq zzg2 = zzlq.zzg();
                } else {
                    zzlq = zzlq.zzg();
                }
            }
            zzlq zzg22 = zzlq.zzg();
            if (zza2 == -1 || zzg22 == null || zzdc.zza(zzg22.zzb) != zza2) {
                boolean zzn2 = zzn(zzlq);
                zzlq.zzf = zzh(zzdc, zzlq.zzf);
            } else {
                zzlq = zzg22;
            }
        }
        boolean zzn22 = zzn(zzlq);
        zzlq.zzf = zzh(zzdc, zzlq.zzf);
        if (!zzn22) {
            return true;
        }
        return false;
    }

    private static final boolean zzC(zzvo zzvo) {
        return !zzvo.zzb() && zzvo.zze == -1;
    }

    private final long zzs(zzdc zzdc, Object obj, int i) {
        zzdc.zzn(obj, this.zza);
        this.zza.zzi(i);
        this.zza.zzk(i);
        return 0;
    }

    private final zzlr zzt(zzdc zzdc, zzlq zzlq, long j) {
        long j2;
        zzdc zzdc2 = zzdc;
        zzlr zzlr = zzlq.zzf;
        long zze2 = (zzlq.zze() + zzlr.zze) - j;
        if (zzlr.zzg) {
            int zza2 = zzdc2.zza(zzlr.zza.zza);
            int i = this.zzf;
            boolean z = this.zzg;
            long j3 = 0;
            int zzi2 = zzdc.zzi(zza2, this.zza, this.zzb, i, z);
            if (zzi2 != -1) {
                int i2 = zzdc2.zzd(zzi2, this.zza, true).zzd;
                Object obj = this.zza.zzc;
                obj.getClass();
                long j4 = zzlr.zza.zzd;
                if (zzdc2.zze(i2, this.zzb, 0).zzp == zzi2) {
                    Pair zzm2 = zzdc.zzm(this.zzb, this.zza, i2, C.TIME_UNSET, Math.max(0, zze2));
                    if (zzm2 != null) {
                        obj = zzm2.first;
                        long longValue = ((Long) zzm2.second).longValue();
                        zzlq zzg2 = zzlq.zzg();
                        if (zzg2 == null || !zzg2.zzb.equals(obj)) {
                            j4 = this.zze;
                            this.zze = 1 + j4;
                        } else {
                            j4 = zzg2.zzf.zza.zzd;
                        }
                        j2 = longValue;
                        j3 = C.TIME_UNSET;
                    }
                } else {
                    j2 = 0;
                }
                zzvo zzx = zzx(zzdc, obj, j2, j4, this.zzb, this.zza);
                if (!(j3 == C.TIME_UNSET || zzlr.zzc == C.TIME_UNSET)) {
                    zzdc2.zzn(zzlr.zza.zza, this.zza).zzb();
                    this.zza.zzg();
                }
                return zzu(zzdc, zzx, j3, j2);
            }
        } else {
            zzvo zzvo = zzlr.zza;
            zzdc2.zzn(zzvo.zza, this.zza);
            if (zzvo.zzb()) {
                int i3 = zzvo.zzb;
                if (this.zza.zza(i3) != -1) {
                    int zzf2 = this.zza.zzf(i3, zzvo.zzc);
                    if (zzf2 < 0) {
                        return zzv(zzdc, zzvo.zza, i3, zzf2, zzlr.zzc, zzvo.zzd);
                    }
                    long j5 = zzlr.zzc;
                    if (j5 == C.TIME_UNSET) {
                        zzdb zzdb = this.zzb;
                        zzcz zzcz = this.zza;
                        Pair zzm3 = zzdc.zzm(zzdb, zzcz, zzcz.zzd, C.TIME_UNSET, Math.max(0, zze2));
                        if (zzm3 != null) {
                            j5 = ((Long) zzm3.second).longValue();
                        }
                    }
                    zzs(zzdc2, zzvo.zza, zzvo.zzb);
                    return zzw(zzdc, zzvo.zza, Math.max(0, j5), zzlr.zzc, zzvo.zzd);
                }
            } else {
                int i4 = zzvo.zze;
                if (i4 != -1) {
                    this.zza.zzm(i4);
                }
                zzcz zzcz2 = this.zza;
                int i5 = zzvo.zze;
                int zze3 = zzcz2.zze(i5);
                zzcz2.zzn(i5);
                if (zze3 != this.zza.zza(zzvo.zze)) {
                    return zzv(zzdc, zzvo.zza, zzvo.zze, zze3, zzlr.zze, zzvo.zzd);
                }
                zzs(zzdc2, zzvo.zza, zzvo.zze);
                return zzw(zzdc, zzvo.zza, 0, zzlr.zze, zzvo.zzd);
            }
        }
        return null;
    }

    private final zzlr zzu(zzdc zzdc, zzvo zzvo, long j, long j2) {
        zzvo zzvo2 = zzvo;
        zzdc zzdc2 = zzdc;
        zzdc.zzn(zzvo2.zza, this.zza);
        if (zzvo.zzb()) {
            return zzv(zzdc, zzvo2.zza, zzvo2.zzb, zzvo2.zzc, j, zzvo2.zzd);
        }
        return zzw(zzdc, zzvo2.zza, j2, j, zzvo2.zzd);
    }

    private final zzlr zzv(zzdc zzdc, Object obj, int i, int i2, long j, long j2) {
        zzvo zzvo = new zzvo(obj, i, i2, j2);
        long zzh2 = zzdc.zzn(zzvo.zza, this.zza).zzh(zzvo.zzb, zzvo.zzc);
        if (i2 == this.zza.zze(i)) {
            this.zza.zzj();
        }
        this.zza.zzn(zzvo.zzb);
        return new zzlr(zzvo, (zzh2 == C.TIME_UNSET || zzh2 > 0) ? 0 : Math.max(0, -1 + zzh2), j, C.TIME_UNSET, zzh2, false, false, false, false);
    }

    private final zzlr zzw(zzdc zzdc, Object obj, long j, long j2, long j3) {
        long j4;
        long j5;
        long j6;
        zzdc zzdc2 = zzdc;
        Object obj2 = obj;
        long j7 = j;
        zzdc2.zzn(obj2, this.zza);
        int zzc2 = this.zza.zzc(j7);
        if (zzc2 != -1) {
            this.zza.zzm(zzc2);
        }
        if (zzc2 == -1) {
            this.zza.zzb();
        } else {
            this.zza.zzn(zzc2);
        }
        zzvo zzvo = new zzvo(obj2, j3, zzc2);
        boolean zzC = zzC(zzvo);
        boolean zzA = zzA(zzdc2, zzvo);
        boolean zzz = zzz(zzdc2, zzvo, zzC);
        if (zzc2 != -1) {
            this.zza.zzn(zzc2);
        }
        if (zzc2 != -1) {
            this.zza.zzi(zzc2);
            j4 = 0;
        } else {
            j4 = -9223372036854775807L;
        }
        if (j4 != C.TIME_UNSET) {
            j6 = 0;
            j5 = 0;
        } else {
            j6 = j4;
            j5 = this.zza.zze;
        }
        if (j5 != C.TIME_UNSET && j7 >= j5) {
            j7 = Math.max(0, j5 - 1);
        }
        return new zzlr(zzvo, j7, j2, j6, j5, false, zzC, zzA, zzz);
    }

    private static zzvo zzx(zzdc zzdc, Object obj, long j, long j2, zzdb zzdb, zzcz zzcz) {
        zzdc zzdc2 = zzdc;
        Object obj2 = obj;
        long j3 = j;
        zzcz zzcz2 = zzcz;
        zzdc.zzn(obj, zzcz2);
        zzdb zzdb2 = zzdb;
        zzdc.zze(zzcz2.zzd, zzdb, 0);
        zzdc.zza(obj);
        zzcz.zzb();
        zzdc.zzn(obj, zzcz2);
        int zzd2 = zzcz2.zzd(j);
        if (zzd2 == -1) {
            long j4 = j2;
            return new zzvo(obj, j2, zzcz2.zzc(j));
        }
        long j5 = j2;
        return new zzvo(obj, zzd2, zzcz2.zze(zzd2), j2);
    }

    private final void zzy() {
        zzvo zzvo;
        zzgaz zzgaz = new zzgaz();
        for (zzlq zzlq = this.zzh; zzlq != null; zzlq = zzlq.zzg()) {
            zzgaz.zzf(zzlq.zzf.zza);
        }
        zzlq zzlq2 = this.zzi;
        if (zzlq2 == null) {
            zzvo = null;
        } else {
            zzvo = zzlq2.zzf.zza;
        }
        this.zzd.zzh(new zzls(this, zzgaz, zzvo));
    }

    private final boolean zzz(zzdc zzdc, zzvo zzvo, boolean z) {
        int zza2 = zzdc.zza(zzvo.zza);
        if (!zzdc.zze(zzdc.zzd(zza2, this.zza, false).zzd, this.zzb, 0).zzj) {
            if (zzdc.zzi(zza2, this.zza, this.zzb, this.zzf, this.zzg) != -1 || !z) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final zzlq zza() {
        zzlq zzlq = this.zzh;
        if (zzlq == null) {
            return null;
        }
        if (zzlq == this.zzi) {
            this.zzi = zzlq.zzg();
        }
        zzlq.zzn();
        int i = this.zzk - 1;
        this.zzk = i;
        if (i == 0) {
            this.zzj = null;
            zzlq zzlq2 = this.zzh;
            this.zzl = zzlq2.zzb;
            this.zzm = zzlq2.zzf.zza.zzd;
        }
        this.zzh = this.zzh.zzg();
        zzy();
        return this.zzh;
    }

    public final zzlq zzb() {
        zzlq zzlq = this.zzi;
        zzeq.zzb(zzlq);
        this.zzi = zzlq.zzg();
        zzy();
        zzlq zzlq2 = this.zzi;
        zzeq.zzb(zzlq2);
        return zzlq2;
    }

    public final zzlq zzd() {
        return this.zzj;
    }

    public final zzlq zze() {
        return this.zzh;
    }

    public final zzlq zzf() {
        return this.zzi;
    }

    public final zzlr zzg(long j, zzmg zzmg) {
        zzlq zzlq = this.zzj;
        if (zzlq != null) {
            return zzt(zzmg.zza, zzlq, j);
        }
        return zzu(zzmg.zza, zzmg.zzb, zzmg.zzc, zzmg.zzr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0065  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzlr zzh(com.google.android.gms.internal.ads.zzdc r19, com.google.android.gms.internal.ads.zzlr r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            com.google.android.gms.internal.ads.zzvo r3 = r2.zza
            boolean r12 = zzC(r3)
            boolean r13 = r0.zzA(r1, r3)
            boolean r14 = r0.zzz(r1, r3, r12)
            com.google.android.gms.internal.ads.zzvo r4 = r2.zza
            java.lang.Object r4 = r4.zza
            com.google.android.gms.internal.ads.zzcz r5 = r0.zza
            r1.zzn(r4, r5)
            boolean r1 = r3.zzb()
            r4 = -1
            r5 = 0
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r1 != 0) goto L_0x0037
            int r1 = r3.zze
            if (r1 != r4) goto L_0x0030
            goto L_0x0037
        L_0x0030:
            com.google.android.gms.internal.ads.zzcz r9 = r0.zza
            r9.zzi(r1)
            r9 = r5
            goto L_0x0038
        L_0x0037:
            r9 = r7
        L_0x0038:
            boolean r1 = r3.zzb()
            if (r1 == 0) goto L_0x004b
            com.google.android.gms.internal.ads.zzcz r1 = r0.zza
            int r5 = r3.zzb
            int r6 = r3.zzc
            long r5 = r1.zzh(r5, r6)
        L_0x0048:
            r7 = r9
            r9 = r5
            goto L_0x0057
        L_0x004b:
            int r1 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r1 == 0) goto L_0x0052
            r7 = r5
            r9 = r7
            goto L_0x0057
        L_0x0052:
            com.google.android.gms.internal.ads.zzcz r1 = r0.zza
            long r5 = r1.zze
            goto L_0x0048
        L_0x0057:
            boolean r1 = r3.zzb()
            if (r1 == 0) goto L_0x0065
            com.google.android.gms.internal.ads.zzcz r1 = r0.zza
            int r4 = r3.zzb
            r1.zzn(r4)
            goto L_0x006e
        L_0x0065:
            int r1 = r3.zze
            if (r1 == r4) goto L_0x006e
            com.google.android.gms.internal.ads.zzcz r4 = r0.zza
            r4.zzn(r1)
        L_0x006e:
            com.google.android.gms.internal.ads.zzlr r15 = new com.google.android.gms.internal.ads.zzlr
            long r4 = r2.zzb
            long r1 = r2.zzc
            r11 = 0
            r16 = r1
            r1 = r15
            r2 = r3
            r3 = r4
            r5 = r16
            r1.<init>(r2, r3, r5, r7, r9, r11, r12, r13, r14)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlt.zzh(com.google.android.gms.internal.ads.zzdc, com.google.android.gms.internal.ads.zzlr):com.google.android.gms.internal.ads.zzlr");
    }

    public final zzvo zzi(zzdc zzdc, Object obj, long j) {
        long j2;
        int zza2;
        int i = zzdc.zzn(obj, this.zza).zzd;
        Object obj2 = this.zzl;
        if (obj2 == null || (zza2 = zzdc.zza(obj2)) == -1 || zzdc.zzd(zza2, this.zza, false).zzd != i) {
            zzlq zzlq = this.zzh;
            while (true) {
                if (zzlq == null) {
                    zzlq zzlq2 = this.zzh;
                    while (true) {
                        if (zzlq2 != null) {
                            int zza3 = zzdc.zza(zzlq2.zzb);
                            if (zza3 != -1 && zzdc.zzd(zza3, this.zza, false).zzd == i) {
                                j2 = zzlq2.zzf.zza.zzd;
                                break;
                            }
                            zzlq2 = zzlq2.zzg();
                        } else {
                            j2 = this.zze;
                            this.zze = 1 + j2;
                            if (this.zzh == null) {
                                this.zzl = obj;
                                this.zzm = j2;
                            }
                        }
                    }
                } else if (zzlq.zzb.equals(obj)) {
                    j2 = zzlq.zzf.zza.zzd;
                    break;
                } else {
                    zzlq = zzlq.zzg();
                }
            }
        } else {
            j2 = this.zzm;
        }
        long j3 = j2;
        zzdc.zzn(obj, this.zza);
        zzdc.zze(this.zza.zzd, this.zzb, 0);
        int zza4 = zzdc.zza(obj);
        Object obj3 = obj;
        while (true) {
            zzdb zzdb = this.zzb;
            if (zza4 < zzdb.zzp) {
                return zzx(zzdc, obj3, j, j3, zzdb, this.zza);
            }
            zzdc.zzd(zza4, this.zza, true);
            this.zza.zzb();
            zzcz zzcz = this.zza;
            if (zzcz.zzd(zzcz.zze) != -1) {
                obj3 = this.zza.zzc;
                obj3.getClass();
            }
            zza4--;
        }
    }

    public final void zzj() {
        if (this.zzk != 0) {
            zzlq zzlq = this.zzh;
            zzeq.zzb(zzlq);
            this.zzl = zzlq.zzb;
            this.zzm = zzlq.zzf.zza.zzd;
            while (zzlq != null) {
                zzlq.zzn();
                zzlq = zzlq.zzg();
            }
            this.zzh = null;
            this.zzj = null;
            this.zzi = null;
            this.zzk = 0;
            zzy();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzk(zzgaz zzgaz, zzvo zzvo) {
        this.zzc.zzS(zzgaz.zzi(), zzvo);
    }

    public final void zzl(long j) {
        zzlq zzlq = this.zzj;
        if (zzlq != null) {
            zzlq.zzm(j);
        }
    }

    public final boolean zzm(zzvm zzvm) {
        zzlq zzlq = this.zzj;
        return zzlq != null && zzlq.zza == zzvm;
    }

    public final boolean zzn(zzlq zzlq) {
        zzeq.zzb(zzlq);
        boolean z = false;
        if (zzlq.equals(this.zzj)) {
            return false;
        }
        this.zzj = zzlq;
        while (zzlq.zzg() != null) {
            zzlq = zzlq.zzg();
            zzlq.getClass();
            if (zzlq == this.zzi) {
                this.zzi = this.zzh;
                z = true;
            }
            zzlq.zzn();
            this.zzk--;
        }
        zzlq zzlq2 = this.zzj;
        zzlq2.getClass();
        zzlq2.zzo((zzlq) null);
        zzy();
        return z;
    }

    public final boolean zzo() {
        zzlq zzlq = this.zzj;
        if (zzlq == null) {
            return true;
        }
        if (zzlq.zzf.zzi || !zzlq.zzr() || this.zzj.zzf.zze == C.TIME_UNSET) {
            return false;
        }
        return this.zzk < 100;
    }

    public final boolean zzp(zzdc zzdc, long j, long j2) {
        zzlr zzlr;
        long j3;
        boolean z;
        zzdc zzdc2 = zzdc;
        zzlq zzlq = this.zzh;
        zzlq zzlq2 = null;
        while (zzlq != null) {
            zzlr zzlr2 = zzlq.zzf;
            if (zzlq2 == null) {
                zzlr = zzh(zzdc2, zzlr2);
                long j4 = j;
            } else {
                zzlr zzt = zzt(zzdc2, zzlq2, j);
                if (zzt == null) {
                    return !zzn(zzlq2);
                }
                if (zzlr2.zzb != zzt.zzb || !zzlr2.zza.equals(zzt.zza)) {
                    return !zzn(zzlq2);
                }
                zzlr = zzt;
            }
            zzlq.zzf = zzlr.zza(zzlr2.zzc);
            long j5 = zzlr2.zze;
            int i = (j5 > C.TIME_UNSET ? 1 : (j5 == C.TIME_UNSET ? 0 : -1));
            long j6 = zzlr.zze;
            if (i == 0 || j5 == j6) {
                zzlq2 = zzlq;
                zzlq = zzlq.zzg();
            } else {
                zzlq.zzq();
                long j7 = zzlr.zze;
                if (j7 == C.TIME_UNSET) {
                    j3 = Long.MAX_VALUE;
                } else {
                    j3 = j7 + zzlq.zze();
                }
                if (zzlq == this.zzi) {
                    boolean z2 = zzlq.zzf.zzf;
                    if (j2 == Long.MIN_VALUE || j2 >= j3) {
                        z = true;
                        return zzn(zzlq) && !z;
                    }
                }
                z = false;
                if (zzn(zzlq)) {
                }
            }
        }
        return true;
    }

    public final boolean zzq(zzdc zzdc, int i) {
        this.zzf = i;
        return zzB(zzdc);
    }

    public final boolean zzr(zzdc zzdc, boolean z) {
        this.zzg = z;
        return zzB(zzdc);
    }

    public final zzlq zzc(zzlr zzlr) {
        long j;
        zzlq zzlq = this.zzj;
        if (zzlq == null) {
            j = MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US;
        } else {
            j = (zzlq.zze() + zzlq.zzf.zze) - zzlr.zzb;
        }
        zzlq zzd2 = zzlh.zzd(this.zzn.zza, zzlr, j);
        zzlq zzlq2 = this.zzj;
        if (zzlq2 != null) {
            zzlq2.zzo(zzd2);
        } else {
            this.zzh = zzd2;
            this.zzi = zzd2;
        }
        this.zzl = null;
        this.zzj = zzd2;
        this.zzk++;
        zzy();
        return zzd2;
    }
}
