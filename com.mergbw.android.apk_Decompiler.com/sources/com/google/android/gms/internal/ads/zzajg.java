package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzajg implements zzadu {
    public static final zzaea zza = new zzaje();
    private static final zzaho zzb = new zzajf();
    private final zzfu zzc;
    private final zzaen zzd;
    private final zzaej zze;
    private final zzael zzf;
    private final zzafa zzg;
    private zzadx zzh;
    private zzafa zzi;
    private zzafa zzj;
    private int zzk;
    private zzcd zzl;
    private long zzm;
    private long zzn;
    private long zzo;
    private int zzp;
    private zzaji zzq;
    private boolean zzr;

    public zzajg() {
        throw null;
    }

    public zzajg(int i) {
        this.zzc = new zzfu(10);
        this.zzd = new zzaen();
        this.zze = new zzaej();
        this.zzm = C.TIME_UNSET;
        this.zzf = new zzael();
        zzadt zzadt = new zzadt();
        this.zzg = zzadt;
        this.zzj = zzadt;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0192  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"extractorOutput", "realTrackOutput"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzg(com.google.android.gms.internal.ads.zzadv r19) throws java.io.IOException {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            int r2 = r0.zzk
            r3 = -1
            r4 = 0
            if (r2 != 0) goto L_0x000f
            r0.zzl(r1, r4)     // Catch:{ EOFException -> 0x000e }
            goto L_0x000f
        L_0x000e:
            return r3
        L_0x000f:
            com.google.android.gms.internal.ads.zzaji r2 = r0.zzq
            r8 = 1
            if (r2 != 0) goto L_0x01ab
            com.google.android.gms.internal.ads.zzaen r2 = r0.zzd
            com.google.android.gms.internal.ads.zzfu r14 = new com.google.android.gms.internal.ads.zzfu
            int r2 = r2.zzc
            r14.<init>((int) r2)
            byte[] r2 = r14.zzM()
            com.google.android.gms.internal.ads.zzaen r9 = r0.zzd
            int r9 = r9.zzc
            r15 = r1
            com.google.android.gms.internal.ads.zzadi r15 = (com.google.android.gms.internal.ads.zzadi) r15
            r15.zzm(r2, r4, r9, r4)
            com.google.android.gms.internal.ads.zzaen r2 = r0.zzd
            int r9 = r2.zza
            r9 = r9 & r8
            r10 = 36
            r11 = 21
            if (r9 == 0) goto L_0x003c
            int r2 = r2.zze
            if (r2 == r8) goto L_0x0043
            r11 = r10
            goto L_0x0043
        L_0x003c:
            int r2 = r2.zze
            if (r2 == r8) goto L_0x0041
            goto L_0x0043
        L_0x0041:
            r11 = 13
        L_0x0043:
            int r2 = r14.zze()
            int r9 = r11 + 4
            r12 = 1231971951(0x496e666f, float:976486.94)
            r13 = 1447187017(0x56425249, float:5.3414667E13)
            r8 = 1483304551(0x58696e67, float:1.02664153E15)
            if (r2 < r9) goto L_0x0061
            r14.zzK(r11)
            int r2 = r14.zzg()
            if (r2 == r8) goto L_0x0075
            if (r2 != r12) goto L_0x0061
            r2 = r12
            goto L_0x0075
        L_0x0061:
            int r2 = r14.zze()
            r9 = 40
            if (r2 < r9) goto L_0x0074
            r14.zzK(r10)
            int r2 = r14.zzg()
            if (r2 != r13) goto L_0x0074
            r2 = r13
            goto L_0x0075
        L_0x0074:
            r2 = r4
        L_0x0075:
            r9 = -1
            if (r2 == r12) goto L_0x0083
            if (r2 == r13) goto L_0x0085
            if (r2 == r8) goto L_0x0083
            r19.zzj()
            r5 = r9
            r2 = 0
            goto L_0x00dd
        L_0x0083:
            r5 = r9
            goto L_0x009f
        L_0x0085:
            long r11 = r19.zzd()
            long r16 = r19.zzf()
            com.google.android.gms.internal.ads.zzaen r13 = r0.zzd
            r5 = r9
            r9 = r11
            r11 = r16
            com.google.android.gms.internal.ads.zzajj r2 = com.google.android.gms.internal.ads.zzajj.zzb(r9, r11, r13, r14)
            com.google.android.gms.internal.ads.zzaen r8 = r0.zzd
            int r8 = r8.zzc
            r15.zzo(r8, r4)
            goto L_0x00dd
        L_0x009f:
            com.google.android.gms.internal.ads.zzaen r9 = r0.zzd
            com.google.android.gms.internal.ads.zzajk r9 = com.google.android.gms.internal.ads.zzajk.zza(r9, r14)
            com.google.android.gms.internal.ads.zzaej r10 = r0.zze
            boolean r11 = r10.zza()
            if (r11 != 0) goto L_0x00b9
            int r11 = r9.zzd
            if (r11 == r3) goto L_0x00b9
            int r12 = r9.zze
            if (r12 == r3) goto L_0x00b9
            r10.zza = r11
            r10.zzb = r12
        L_0x00b9:
            long r10 = r19.zzf()
            com.google.android.gms.internal.ads.zzaen r12 = r0.zzd
            int r12 = r12.zzc
            r15.zzo(r12, r4)
            if (r2 != r8) goto L_0x00cf
            long r12 = r19.zzd()
            com.google.android.gms.internal.ads.zzajl r2 = com.google.android.gms.internal.ads.zzajl.zzb(r12, r9, r10)
            goto L_0x00dd
        L_0x00cf:
            long r8 = r9.zzc
            int r2 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r2 == 0) goto L_0x00d8
            long r9 = r10 + r8
            goto L_0x00d9
        L_0x00d8:
            r9 = r5
        L_0x00d9:
            com.google.android.gms.internal.ads.zzaji r2 = r0.zzi(r1, r9, r4)
        L_0x00dd:
            com.google.android.gms.internal.ads.zzcd r8 = r0.zzl
            long r9 = r19.zzf()
            if (r8 == 0) goto L_0x0132
            int r11 = r8.zza()
            r12 = r4
        L_0x00ea:
            if (r12 >= r11) goto L_0x0132
            com.google.android.gms.internal.ads.zzcc r13 = r8.zzb(r12)
            boolean r14 = r13 instanceof com.google.android.gms.internal.ads.zzahv
            if (r14 == 0) goto L_0x012f
            com.google.android.gms.internal.ads.zzahv r13 = (com.google.android.gms.internal.ads.zzahv) r13
            int r11 = r8.zza()
            r12 = r4
        L_0x00fb:
            if (r12 >= r11) goto L_0x0125
            com.google.android.gms.internal.ads.zzcc r14 = r8.zzb(r12)
            boolean r15 = r14 instanceof com.google.android.gms.internal.ads.zzahz
            if (r15 == 0) goto L_0x0122
            com.google.android.gms.internal.ads.zzahz r14 = (com.google.android.gms.internal.ads.zzahz) r14
            java.lang.String r15 = r14.zzf
            java.lang.String r7 = "TLEN"
            boolean r7 = r15.equals(r7)
            if (r7 == 0) goto L_0x0122
            com.google.android.gms.internal.ads.zzgbc r7 = r14.zzc
            java.lang.Object r7 = r7.get(r4)
            java.lang.String r7 = (java.lang.String) r7
            long r7 = java.lang.Long.parseLong(r7)
            long r7 = com.google.android.gms.internal.ads.zzgd.zzr(r7)
            goto L_0x012a
        L_0x0122:
            int r12 = r12 + 1
            goto L_0x00fb
        L_0x0125:
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x012a:
            com.google.android.gms.internal.ads.zzajd r7 = com.google.android.gms.internal.ads.zzajd.zzb(r9, r13, r7)
            goto L_0x0133
        L_0x012f:
            int r12 = r12 + 1
            goto L_0x00ea
        L_0x0132:
            r7 = 0
        L_0x0133:
            boolean r8 = r0.zzr
            if (r8 == 0) goto L_0x013d
            com.google.android.gms.internal.ads.zzajh r2 = new com.google.android.gms.internal.ads.zzajh
            r2.<init>()
            goto L_0x014e
        L_0x013d:
            if (r7 == 0) goto L_0x0141
            r2 = r7
            goto L_0x0144
        L_0x0141:
            if (r2 != 0) goto L_0x0144
            r2 = 0
        L_0x0144:
            if (r2 == 0) goto L_0x014a
            r2.zzh()
            goto L_0x014e
        L_0x014a:
            com.google.android.gms.internal.ads.zzaji r2 = r0.zzi(r1, r5, r4)
        L_0x014e:
            r0.zzq = r2
            com.google.android.gms.internal.ads.zzadx r5 = r0.zzh
            r5.zzO(r2)
            com.google.android.gms.internal.ads.zzal r2 = new com.google.android.gms.internal.ads.zzal
            r2.<init>()
            com.google.android.gms.internal.ads.zzaen r5 = r0.zzd
            java.lang.String r5 = r5.zzb
            r2.zzX(r5)
            r5 = 4096(0x1000, float:5.74E-42)
            r2.zzP(r5)
            com.google.android.gms.internal.ads.zzaen r5 = r0.zzd
            int r5 = r5.zze
            r2.zzy(r5)
            com.google.android.gms.internal.ads.zzaen r5 = r0.zzd
            int r5 = r5.zzd
            r2.zzY(r5)
            com.google.android.gms.internal.ads.zzaej r5 = r0.zze
            int r5 = r5.zza
            r2.zzF(r5)
            com.google.android.gms.internal.ads.zzaej r5 = r0.zze
            int r5 = r5.zzb
            r2.zzG(r5)
            com.google.android.gms.internal.ads.zzcd r5 = r0.zzl
            r2.zzQ(r5)
            com.google.android.gms.internal.ads.zzaji r5 = r0.zzq
            int r5 = r5.zzc()
            r6 = -2147483647(0xffffffff80000001, float:-1.4E-45)
            if (r5 == r6) goto L_0x019b
            com.google.android.gms.internal.ads.zzaji r5 = r0.zzq
            int r5 = r5.zzc()
            r2.zzx(r5)
        L_0x019b:
            com.google.android.gms.internal.ads.zzafa r5 = r0.zzj
            com.google.android.gms.internal.ads.zzan r2 = r2.zzad()
            r5.zzl(r2)
            long r5 = r19.zzf()
            r0.zzo = r5
            goto L_0x01c3
        L_0x01ab:
            long r5 = r0.zzo
            r7 = 0
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 == 0) goto L_0x01c3
            long r7 = r19.zzf()
            int r2 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x01c3
            long r5 = r5 - r7
            r2 = r1
            com.google.android.gms.internal.ads.zzadi r2 = (com.google.android.gms.internal.ads.zzadi) r2
            int r5 = (int) r5
            r2.zzo(r5, r4)
        L_0x01c3:
            int r2 = r0.zzp
            if (r2 != 0) goto L_0x022d
            r19.zzj()
            boolean r2 = r18.zzk(r19)
            if (r2 == 0) goto L_0x01d2
            goto L_0x023f
        L_0x01d2:
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzc
            r2.zzK(r4)
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzc
            int r2 = r2.zzg()
            int r5 = r0.zzk
            long r5 = (long) r5
            boolean r5 = zzj(r2, r5)
            if (r5 == 0) goto L_0x0224
            int r5 = com.google.android.gms.internal.ads.zzaeo.zzb(r2)
            if (r5 != r3) goto L_0x01ed
            goto L_0x0224
        L_0x01ed:
            com.google.android.gms.internal.ads.zzaen r5 = r0.zzd
            r5.zza(r2)
            long r5 = r0.zzm
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x0209
            com.google.android.gms.internal.ads.zzaji r2 = r0.zzq
            long r5 = r19.zzf()
            long r5 = r2.zze(r5)
            r0.zzm = r5
        L_0x0209:
            com.google.android.gms.internal.ads.zzaen r2 = r0.zzd
            int r5 = r2.zzc
            r0.zzp = r5
            com.google.android.gms.internal.ads.zzaji r6 = r0.zzq
            boolean r7 = r6 instanceof com.google.android.gms.internal.ads.zzajc
            if (r7 != 0) goto L_0x0217
            r2 = r5
            goto L_0x022d
        L_0x0217:
            com.google.android.gms.internal.ads.zzajc r6 = (com.google.android.gms.internal.ads.zzajc) r6
            long r3 = r0.zzn
            int r1 = r2.zzg
            long r1 = (long) r1
            long r3 = r3 + r1
            r0.zzh(r3)
            r1 = 0
            throw r1
        L_0x0224:
            com.google.android.gms.internal.ads.zzadi r1 = (com.google.android.gms.internal.ads.zzadi) r1
            r5 = 1
            r1.zzo(r5, r4)
            r0.zzk = r4
            goto L_0x023e
        L_0x022d:
            r5 = 1
            com.google.android.gms.internal.ads.zzafa r6 = r0.zzj
            int r1 = r6.zzf(r1, r2, r5)
            if (r1 != r3) goto L_0x0237
            goto L_0x023f
        L_0x0237:
            int r2 = r0.zzp
            int r2 = r2 - r1
            r0.zzp = r2
            if (r2 <= 0) goto L_0x0240
        L_0x023e:
            r3 = r4
        L_0x023f:
            return r3
        L_0x0240:
            com.google.android.gms.internal.ads.zzafa r5 = r0.zzj
            long r1 = r0.zzn
            long r6 = r0.zzh(r1)
            com.google.android.gms.internal.ads.zzaen r1 = r0.zzd
            int r9 = r1.zzc
            r10 = 0
            r11 = 0
            r8 = 1
            r5.zzs(r6, r8, r9, r10, r11)
            long r1 = r0.zzn
            com.google.android.gms.internal.ads.zzaen r3 = r0.zzd
            int r3 = r3.zzg
            long r5 = (long) r3
            long r1 = r1 + r5
            r0.zzn = r1
            r0.zzp = r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzajg.zzg(com.google.android.gms.internal.ads.zzadv):int");
    }

    private final long zzh(long j) {
        return this.zzm + ((j * 1000000) / ((long) this.zzd.zzd));
    }

    private final zzaji zzi(zzadv zzadv, long j, boolean z) throws IOException {
        ((zzadi) zzadv).zzm(this.zzc.zzM(), 0, 4, false);
        this.zzc.zzK(0);
        this.zzd.zza(this.zzc.zzg());
        if (zzadv.zzd() != -1) {
            j = zzadv.zzd();
        }
        return new zzajb(j, zzadv.zzf(), this.zzd, false);
    }

    private static boolean zzj(int i, long j) {
        return ((long) (i & -128000)) == (j & -128000);
    }

    private final boolean zzk(zzadv zzadv) throws IOException {
        zzaji zzaji = this.zzq;
        if (zzaji != null) {
            long zzd2 = zzaji.zzd();
            if (zzd2 != -1 && zzadv.zze() > zzd2 - 4) {
                return true;
            }
        }
        try {
            return !zzadv.zzm(this.zzc.zzM(), 0, 4, true);
        } catch (EOFException unused) {
            return true;
        }
    }

    private final boolean zzl(zzadv zzadv, boolean z) throws IOException {
        int i;
        int i2;
        int zzb2;
        zzadv.zzj();
        if (zzadv.zzf() == 0) {
            zzcd zza2 = this.zzf.zza(zzadv, (zzaho) null);
            this.zzl = zza2;
            if (zza2 != null) {
                this.zze.zzb(zza2);
            }
            i2 = (int) zzadv.zze();
            if (!z) {
                ((zzadi) zzadv).zzo(i2, false);
            }
            i = 0;
        } else {
            i2 = 0;
            i = 0;
        }
        int i3 = i;
        int i4 = i3;
        while (true) {
            if (!zzk(zzadv)) {
                this.zzc.zzK(0);
                int zzg2 = this.zzc.zzg();
                if ((i == 0 || zzj(zzg2, (long) i)) && (zzb2 = zzaeo.zzb(zzg2)) != -1) {
                    i3++;
                    if (i3 != 1) {
                        if (i3 == 4) {
                            break;
                        }
                    } else {
                        this.zzd.zza(zzg2);
                        i = zzg2;
                    }
                    ((zzadi) zzadv).zzl(zzb2 - 4, false);
                } else {
                    int i5 = i4 + 1;
                    if (i4 != (true != z ? 131072 : 32768)) {
                        if (z) {
                            zzadv.zzj();
                            ((zzadi) zzadv).zzl(i2 + i5, false);
                        } else {
                            ((zzadi) zzadv).zzo(1, false);
                        }
                        i = 0;
                        i4 = i5;
                        i3 = 0;
                    } else if (z) {
                        return false;
                    } else {
                        throw zzch.zza("Searched too many bytes.", (Throwable) null);
                    }
                }
            } else if (i3 <= 0) {
                throw new EOFException();
            }
        }
        if (z) {
            ((zzadi) zzadv).zzo(i2 + i4, false);
        } else {
            zzadv.zzj();
        }
        this.zzk = i;
        return true;
    }

    public final void zza() {
        this.zzr = true;
    }

    public final int zzb(zzadv zzadv, zzaeq zzaeq) throws IOException {
        zzeq.zzb(this.zzi);
        int i = zzgd.zza;
        int zzg2 = zzg(zzadv);
        if (zzg2 == -1 && (this.zzq instanceof zzajc)) {
            if (this.zzq.zza() != zzh(this.zzn)) {
                zzajc zzajc = (zzajc) this.zzq;
                throw null;
            }
        }
        return zzg2;
    }

    public final /* synthetic */ List zzc() {
        return zzgbc.zzm();
    }

    public final void zzd(zzadx zzadx) {
        this.zzh = zzadx;
        zzafa zzw = zzadx.zzw(0, 1);
        this.zzi = zzw;
        this.zzj = zzw;
        this.zzh.zzD();
    }

    public final void zze(long j, long j2) {
        this.zzk = 0;
        this.zzm = C.TIME_UNSET;
        this.zzn = 0;
        this.zzp = 0;
        zzaji zzaji = this.zzq;
        if (zzaji instanceof zzajc) {
            zzajc zzajc = (zzajc) zzaji;
            throw null;
        }
    }

    public final boolean zzf(zzadv zzadv) throws IOException {
        return zzl(zzadv, true);
    }
}
