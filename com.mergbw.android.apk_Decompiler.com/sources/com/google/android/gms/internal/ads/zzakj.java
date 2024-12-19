package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.metadata.mp4.MdtaMetadataEntry;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzakj implements zzadu, zzaet {
    @Deprecated
    public static final zzaea zza = new zzakh();
    private int zzA;
    private zzaie zzB;
    private final zzalt zzb;
    private final int zzc;
    private final zzfu zzd;
    private final zzfu zze;
    private final zzfu zzf;
    private final zzfu zzg;
    private final ArrayDeque zzh;
    private final zzakn zzi;
    private final List zzj;
    private zzgbc zzk;
    private int zzl;
    private int zzm;
    private long zzn;
    private int zzo;
    private zzfu zzp;
    private int zzq;
    private int zzr;
    private int zzs;
    private int zzt;
    private boolean zzu;
    private zzadx zzv;
    private zzaki[] zzw;
    private long[][] zzx;
    private int zzy;
    private long zzz;

    @Deprecated
    public zzakj() {
        this(zzalt.zza, 16);
    }

    private static int zzi(int i) {
        if (i != 1751476579) {
            return i != 1903435808 ? 0 : 1;
        }
        return 2;
    }

    private static int zzj(zzaks zzaks, long j) {
        int zza2 = zzaks.zza(j);
        return zza2 == -1 ? zzaks.zzb(j) : zza2;
    }

    private static long zzk(zzaks zzaks, long j, long j2) {
        int zzj2 = zzj(zzaks, j);
        if (zzj2 == -1) {
            return j2;
        }
        return Math.min(zzaks.zzc[zzj2], j2);
    }

    private final void zzl() {
        this.zzl = 0;
        this.zzo = 0;
    }

    private final void zzm(long j) throws zzch {
        zzcd zzcd;
        long j2;
        List list;
        int i;
        zzcd zzcd2;
        int i2;
        int i3;
        while (!this.zzh.isEmpty() && ((zzajm) this.zzh.peek()).zza == j) {
            zzajm zzajm = (zzajm) this.zzh.pop();
            if (zzajm.zzd == 1836019574) {
                ArrayList arrayList = new ArrayList();
                int i4 = this.zzA;
                zzaej zzaej = new zzaej();
                zzajn zzb2 = zzajm.zzb(Atom.TYPE_udta);
                if (zzb2 != null) {
                    zzcd zzb3 = zzajw.zzb(zzb2);
                    zzaej.zzb(zzb3);
                    zzcd = zzb3;
                } else {
                    zzcd = null;
                }
                zzajm zza2 = zzajm.zza(Atom.TYPE_meta);
                zzcd zza3 = zza2 != null ? zzajw.zza(zza2) : null;
                zzcc[] zzccArr = new zzcc[1];
                zzajn zzb4 = zzajm.zzb(Atom.TYPE_mvhd);
                zzb4.getClass();
                boolean z = i4 == 1;
                zzccArr[0] = zzajw.zzc(zzb4.zza);
                zzcd zzcd3 = new zzcd(C.TIME_UNSET, zzccArr);
                zzcd zzcd4 = zzcd;
                long j3 = -9223372036854775807L;
                List zzd2 = zzajw.zzd(zzajm, zzaej, C.TIME_UNSET, (zzae) null, 1 == (this.zzc & 1), z, new zzakg());
                long j4 = -9223372036854775807L;
                int i5 = 0;
                int i6 = -1;
                int i7 = 0;
                while (true) {
                    j2 = 0;
                    if (i7 >= zzd2.size()) {
                        break;
                    }
                    zzaks zzaks = (zzaks) zzd2.get(i7);
                    if (zzaks.zzb == 0) {
                        list = zzd2;
                    } else {
                        zzakp zzakp = zzaks.zza;
                        list = zzd2;
                        long j5 = zzakp.zze;
                        if (j5 == j3) {
                            j5 = zzaks.zzh;
                        }
                        int i8 = i5 + 1;
                        long max = Math.max(j4, j5);
                        zzaki zzaki = new zzaki(zzakp, zzaks, this.zzv.zzw(i5, zzakp.zzb));
                        if (MimeTypes.AUDIO_TRUEHD.equals(zzakp.zzf.zzn)) {
                            i = zzaks.zze * 16;
                        } else {
                            i = zzaks.zze + 30;
                        }
                        zzal zzb5 = zzakp.zzf.zzb();
                        zzb5.zzP(i);
                        if (zzakp.zzb == 2) {
                            if ((this.zzc & 8) != 0) {
                                zzb5.zzV(zzakp.zzf.zzg | (i6 == -1 ? 1 : 2));
                            }
                            if (j5 > 0 && (i3 = zzaks.zzb) > 0) {
                                zzb5.zzH(((float) i3) / (((float) j5) / 1000000.0f));
                            }
                        }
                        int i9 = zzakp.zzb;
                        int i10 = zzakf.zzb;
                        if (i9 == 1 && zzaej.zza()) {
                            zzb5.zzF(zzaej.zza);
                            zzb5.zzG(zzaej.zzb);
                        }
                        int i11 = zzakp.zzb;
                        zzcd[] zzcdArr = new zzcd[3];
                        if (this.zzj.isEmpty()) {
                            i2 = 0;
                            zzcd2 = null;
                        } else {
                            zzcd2 = new zzcd(this.zzj);
                            i2 = 0;
                        }
                        zzcdArr[i2] = zzcd2;
                        zzcdArr[1] = zzcd4;
                        zzcdArr[2] = zzcd3;
                        int i12 = i6;
                        zzcd zzcd5 = new zzcd(C.TIME_UNSET, new zzcc[i2]);
                        if (zza3 != null) {
                            for (int i13 = 0; i13 < zza3.zza(); i13++) {
                                zzcc zzb6 = zza3.zzb(i13);
                                if (zzb6 instanceof zzgh) {
                                    zzgh zzgh = (zzgh) zzb6;
                                    if (!zzgh.zza.equals(MdtaMetadataEntry.KEY_ANDROID_CAPTURE_FPS)) {
                                        zzcd5 = zzcd5.zzc(zzgh);
                                    } else if (i11 == 2) {
                                        zzcd5 = zzcd5.zzc(zzgh);
                                    }
                                }
                            }
                        }
                        for (int i14 = 0; i14 < 3; i14++) {
                            zzcd5 = zzcd5.zzd(zzcdArr[i14]);
                        }
                        if (zzcd5.zza() > 0) {
                            zzb5.zzQ(zzcd5);
                        }
                        zzaki.zzc.zzl(zzb5.zzad());
                        int i15 = zzakp.zzb;
                        int i16 = i12;
                        if (i15 == 2) {
                            if (i16 == -1) {
                                i6 = arrayList.size();
                                arrayList.add(zzaki);
                                i5 = i8;
                                j4 = max;
                            }
                        }
                        i6 = i16;
                        arrayList.add(zzaki);
                        i5 = i8;
                        j4 = max;
                    }
                    i7++;
                    zzd2 = list;
                    j3 = C.TIME_UNSET;
                }
                this.zzy = i6;
                this.zzz = j4;
                zzaki[] zzakiArr = (zzaki[]) arrayList.toArray(new zzaki[0]);
                this.zzw = zzakiArr;
                int length = zzakiArr.length;
                long[][] jArr = new long[length][];
                int[] iArr = new int[length];
                long[] jArr2 = new long[length];
                boolean[] zArr = new boolean[length];
                for (int i17 = 0; i17 < zzakiArr.length; i17++) {
                    jArr[i17] = new long[zzakiArr[i17].zzb.zzb];
                    jArr2[i17] = zzakiArr[i17].zzb.zzf[0];
                }
                int i18 = 0;
                while (i18 < zzakiArr.length) {
                    int i19 = -1;
                    long j6 = Long.MAX_VALUE;
                    for (int i20 = 0; i20 < zzakiArr.length; i20++) {
                        if (!zArr[i20]) {
                            long j7 = jArr2[i20];
                            if (j7 <= j6) {
                                i19 = i20;
                                j6 = j7;
                            }
                        }
                    }
                    int i21 = iArr[i19];
                    long[] jArr3 = jArr[i19];
                    jArr3[i21] = j2;
                    zzaks zzaks2 = zzakiArr[i19].zzb;
                    j2 += (long) zzaks2.zzd[i21];
                    int i22 = i21 + 1;
                    iArr[i19] = i22;
                    if (i22 < jArr3.length) {
                        jArr2[i19] = zzaks2.zzf[i22];
                    } else {
                        zArr[i19] = true;
                        i18++;
                    }
                }
                this.zzx = jArr;
                this.zzv.zzD();
                this.zzv.zzO(this);
                this.zzh.clear();
                this.zzl = 2;
            } else if (!this.zzh.isEmpty()) {
                ((zzajm) this.zzh.peek()).zzc(zzajm);
            }
        }
        if (this.zzl != 2) {
            zzl();
        }
    }

    public final long zza() {
        return this.zzz;
    }

    public final /* synthetic */ List zzc() {
        return this.zzk;
    }

    public final void zzd(zzadx zzadx) {
        if ((this.zzc & 16) == 0) {
            zzadx = new zzalw(zzadx, this.zzb);
        }
        this.zzv = zzadx;
    }

    public final void zze(long j, long j2) {
        this.zzh.clear();
        this.zzo = 0;
        this.zzq = -1;
        this.zzr = 0;
        this.zzs = 0;
        this.zzt = 0;
        if (j != 0) {
            for (zzaki zzaki : this.zzw) {
                zzaks zzaks = zzaki.zzb;
                int zza2 = zzaks.zza(j2);
                if (zza2 == -1) {
                    zza2 = zzaks.zzb(j2);
                }
                zzaki.zze = zza2;
                zzafb zzafb = zzaki.zzd;
                if (zzafb != null) {
                    zzafb.zzb();
                }
            }
        } else if (this.zzl != 3) {
            zzl();
        } else {
            this.zzi.zzb();
            this.zzj.clear();
        }
    }

    public final boolean zzf(zzadv zzadv) throws IOException {
        zzaex zzb2 = zzako.zzb(zzadv, (this.zzc & 2) != 0);
        this.zzk = zzb2 != null ? zzgbc.zzn(zzb2) : zzgbc.zzm();
        return zzb2 == null;
    }

    public final zzaer zzg(long j) {
        long j2;
        long j3;
        int zzb2;
        zzaki[] zzakiArr = this.zzw;
        if (zzakiArr.length == 0) {
            zzaeu zzaeu = zzaeu.zza;
            return new zzaer(zzaeu, zzaeu);
        }
        int i = this.zzy;
        long j4 = -1;
        if (i != -1) {
            zzaks zzaks = zzakiArr[i].zzb;
            int zzj2 = zzj(zzaks, j);
            if (zzj2 == -1) {
                zzaeu zzaeu2 = zzaeu.zza;
                return new zzaer(zzaeu2, zzaeu2);
            }
            long j5 = zzaks.zzf[zzj2];
            j2 = zzaks.zzc[zzj2];
            if (j5 >= j || zzj2 >= zzaks.zzb - 1 || (zzb2 = zzaks.zzb(j)) == -1 || zzb2 == zzj2) {
                j3 = -9223372036854775807L;
            } else {
                j3 = zzaks.zzf[zzb2];
                j4 = zzaks.zzc[zzb2];
            }
            j = j5;
        } else {
            j2 = Long.MAX_VALUE;
            j3 = -9223372036854775807L;
        }
        int i2 = 0;
        while (true) {
            zzaki[] zzakiArr2 = this.zzw;
            if (i2 >= zzakiArr2.length) {
                break;
            }
            if (i2 != this.zzy) {
                zzaks zzaks2 = zzakiArr2[i2].zzb;
                long zzk2 = zzk(zzaks2, j, j2);
                if (j3 != C.TIME_UNSET) {
                    j4 = zzk(zzaks2, j3, j4);
                }
                j2 = zzk2;
            }
            i2++;
        }
        zzaeu zzaeu3 = new zzaeu(j, j2);
        if (j3 == C.TIME_UNSET) {
            return new zzaer(zzaeu3, zzaeu3);
        }
        return new zzaer(zzaeu3, new zzaeu(j3, j4));
    }

    public final boolean zzh() {
        return true;
    }

    public zzakj(zzalt zzalt, int i) {
        this.zzb = zzalt;
        this.zzc = i;
        this.zzk = zzgbc.zzm();
        this.zzl = (i & 4) != 0 ? 3 : 0;
        this.zzi = new zzakn();
        this.zzj = new ArrayList();
        this.zzg = new zzfu(16);
        this.zzh = new ArrayDeque();
        this.zzd = new zzfu(zzgr.zza);
        this.zze = new zzfu(4);
        this.zzf = new zzfu();
        this.zzq = -1;
        this.zzv = zzadx.zza;
        this.zzw = new zzaki[0];
    }

    /* JADX WARNING: Removed duplicated region for block: B:229:0x0095 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzb(com.google.android.gms.internal.ads.zzadv r33, com.google.android.gms.internal.ads.zzaeq r34) throws java.io.IOException {
        /*
            r32 = this;
            r0 = r32
            r1 = r33
            r2 = r34
        L_0x0006:
            int r3 = r0.zzl
            r4 = 1718909296(0x66747970, float:2.8862439E23)
            r7 = 0
            r9 = 2
            r10 = -1
            r11 = 8
            r12 = 1
            if (r3 == 0) goto L_0x0273
            r14 = 262144(0x40000, double:1.295163E-318)
            if (r3 == r12) goto L_0x01e2
            if (r3 == r9) goto L_0x002c
            com.google.android.gms.internal.ads.zzakn r3 = r0.zzi
            java.util.List r4 = r0.zzj
            r3.zza(r1, r2, r4)
            long r1 = r2.zza
            int r1 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r1 != 0) goto L_0x002b
            r32.zzl()
        L_0x002b:
            return r12
        L_0x002c:
            long r3 = r33.zzf()
            int r11 = r0.zzq
            if (r11 != r10) goto L_0x00b3
            r16 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r25 = r10
            r26 = r25
            r20 = r12
            r27 = r20
            r18 = r16
            r21 = r18
            r23 = r21
            r11 = 0
        L_0x0048:
            com.google.android.gms.internal.ads.zzaki[] r5 = r0.zzw
            int r6 = r5.length
            if (r11 >= r6) goto L_0x0099
            r5 = r5[r11]
            int r6 = r5.zze
            com.google.android.gms.internal.ads.zzaks r5 = r5.zzb
            int r9 = r5.zzb
            if (r6 != r9) goto L_0x0058
            goto L_0x0095
        L_0x0058:
            long[] r5 = r5.zzc
            r28 = r5[r6]
            long[][] r5 = r0.zzx
            int r9 = com.google.android.gms.internal.ads.zzgd.zza
            r5 = r5[r11]
            r30 = r5[r6]
            long r28 = r28 - r3
            int r5 = (r28 > r7 ? 1 : (r28 == r7 ? 0 : -1))
            if (r5 < 0) goto L_0x0071
            int r5 = (r28 > r14 ? 1 : (r28 == r14 ? 0 : -1))
            if (r5 < 0) goto L_0x006f
            goto L_0x0071
        L_0x006f:
            r5 = 0
            goto L_0x0072
        L_0x0071:
            r5 = r12
        L_0x0072:
            if (r5 != 0) goto L_0x0078
            if (r27 != 0) goto L_0x0080
            r6 = 0
            goto L_0x007a
        L_0x0078:
            r6 = r27
        L_0x007a:
            if (r5 != r6) goto L_0x0089
            int r9 = (r28 > r23 ? 1 : (r28 == r23 ? 0 : -1))
            if (r9 >= 0) goto L_0x0089
        L_0x0080:
            r27 = r5
            r26 = r11
            r23 = r28
            r21 = r30
            goto L_0x008b
        L_0x0089:
            r27 = r6
        L_0x008b:
            int r6 = (r30 > r18 ? 1 : (r30 == r18 ? 0 : -1))
            if (r6 >= 0) goto L_0x0095
            r20 = r5
            r25 = r11
            r18 = r30
        L_0x0095:
            int r11 = r11 + 1
            r9 = 2
            goto L_0x0048
        L_0x0099:
            int r5 = (r18 > r16 ? 1 : (r18 == r16 ? 0 : -1))
            if (r5 == 0) goto L_0x00ab
            if (r20 == 0) goto L_0x00ab
            r5 = 10485760(0xa00000, double:5.180654E-317)
            long r18 = r18 + r5
            int r5 = (r21 > r18 ? 1 : (r21 == r18 ? 0 : -1))
            if (r5 < 0) goto L_0x00ab
            r11 = r25
            goto L_0x00ad
        L_0x00ab:
            r11 = r26
        L_0x00ad:
            r0.zzq = r11
            if (r11 != r10) goto L_0x00b3
            goto L_0x01e1
        L_0x00b3:
            com.google.android.gms.internal.ads.zzaki[] r5 = r0.zzw
            r5 = r5[r11]
            com.google.android.gms.internal.ads.zzafa r6 = r5.zzc
            int r9 = r5.zze
            com.google.android.gms.internal.ads.zzaks r11 = r5.zzb
            long[] r10 = r11.zzc
            r12 = r10[r9]
            int[] r10 = r11.zzd
            r10 = r10[r9]
            com.google.android.gms.internal.ads.zzafb r11 = r5.zzd
            long r3 = r12 - r3
            int r14 = r0.zzr
            long r14 = (long) r14
            long r3 = r3 + r14
            int r7 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r7 < 0) goto L_0x01de
            r7 = 262144(0x40000, double:1.295163E-318)
            int r7 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r7 < 0) goto L_0x00da
            goto L_0x01de
        L_0x00da:
            com.google.android.gms.internal.ads.zzakp r2 = r5.zza
            int r2 = r2.zzg
            r7 = 1
            if (r2 != r7) goto L_0x00e6
            r7 = 8
            long r3 = r3 + r7
            int r10 = r10 + -8
        L_0x00e6:
            int r2 = (int) r3
            r1.zzk(r2)
            com.google.android.gms.internal.ads.zzakp r2 = r5.zza
            int r3 = r2.zzj
            if (r3 == 0) goto L_0x0150
            com.google.android.gms.internal.ads.zzfu r2 = r0.zze
            byte[] r2 = r2.zzM()
            r4 = 0
            r2[r4] = r4
            r7 = 1
            r2[r7] = r4
            r7 = 2
            r2[r7] = r4
            int r4 = 4 - r3
        L_0x0101:
            int r7 = r0.zzs
            if (r7 >= r10) goto L_0x0193
            int r7 = r0.zzt
            if (r7 != 0) goto L_0x013b
            r1.zzi(r2, r4, r3)
            int r7 = r0.zzr
            int r7 = r7 + r3
            r0.zzr = r7
            com.google.android.gms.internal.ads.zzfu r7 = r0.zze
            r8 = 0
            r7.zzK(r8)
            com.google.android.gms.internal.ads.zzfu r7 = r0.zze
            int r7 = r7.zzg()
            if (r7 < 0) goto L_0x0133
            r0.zzt = r7
            com.google.android.gms.internal.ads.zzfu r7 = r0.zzd
            r7.zzK(r8)
            com.google.android.gms.internal.ads.zzfu r7 = r0.zzd
            r12 = 4
            r6.zzq(r7, r12)
            int r7 = r0.zzs
            int r7 = r7 + r12
            r0.zzs = r7
            int r10 = r10 + r4
            goto L_0x0101
        L_0x0133:
            java.lang.String r1 = "Invalid NAL length"
            r2 = 0
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zza(r1, r2)
            throw r1
        L_0x013b:
            r8 = 0
            int r7 = r6.zzf(r1, r7, r8)
            int r8 = r0.zzr
            int r8 = r8 + r7
            r0.zzr = r8
            int r8 = r0.zzs
            int r8 = r8 + r7
            r0.zzs = r8
            int r8 = r0.zzt
            int r8 = r8 - r7
            r0.zzt = r8
            goto L_0x0101
        L_0x0150:
            com.google.android.gms.internal.ads.zzan r2 = r2.zzf
            java.lang.String r3 = "audio/ac4"
            java.lang.String r2 = r2.zzn
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0173
            int r2 = r0.zzs
            if (r2 != 0) goto L_0x0170
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzf
            com.google.android.gms.internal.ads.zzacw.zzb(r10, r2)
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzf
            r3 = 7
            r6.zzq(r2, r3)
            int r2 = r0.zzs
            int r2 = r2 + r3
            r0.zzs = r2
        L_0x0170:
            int r10 = r10 + 7
            goto L_0x0178
        L_0x0173:
            if (r11 == 0) goto L_0x0178
            r11.zzd(r1)
        L_0x0178:
            int r2 = r0.zzs
            if (r2 >= r10) goto L_0x0193
            int r2 = r10 - r2
            r3 = 0
            int r2 = r6.zzf(r1, r2, r3)
            int r3 = r0.zzr
            int r3 = r3 + r2
            r0.zzr = r3
            int r3 = r0.zzs
            int r3 = r3 + r2
            r0.zzs = r3
            int r3 = r0.zzt
            int r3 = r3 - r2
            r0.zzt = r3
            goto L_0x0178
        L_0x0193:
            com.google.android.gms.internal.ads.zzaks r1 = r5.zzb
            long[] r2 = r1.zzf
            r3 = r2[r9]
            int[] r1 = r1.zzg
            r1 = r1[r9]
            if (r11 == 0) goto L_0x01bd
            r22 = 0
            r23 = 0
            r16 = r11
            r17 = r6
            r18 = r3
            r20 = r1
            r21 = r10
            r16.zzc(r17, r18, r20, r21, r22, r23)
            r1 = 1
            int r9 = r9 + r1
            com.google.android.gms.internal.ads.zzaks r1 = r5.zzb
            int r1 = r1.zzb
            if (r9 != r1) goto L_0x01cc
            r1 = 0
            r11.zza(r6, r1)
            goto L_0x01cc
        L_0x01bd:
            r21 = 0
            r22 = 0
            r16 = r6
            r17 = r3
            r19 = r1
            r20 = r10
            r16.zzs(r17, r19, r20, r21, r22)
        L_0x01cc:
            int r1 = r5.zze
            r2 = 1
            int r1 = r1 + r2
            r5.zze = r1
            r1 = -1
            r0.zzq = r1
            r1 = 0
            r0.zzr = r1
            r0.zzs = r1
            r0.zzt = r1
            r10 = 0
            goto L_0x01e1
        L_0x01de:
            r2.zza = r12
            r10 = 1
        L_0x01e1:
            return r10
        L_0x01e2:
            long r5 = r0.zzn
            int r3 = r0.zzo
            long r7 = (long) r3
            long r5 = r5 - r7
            long r7 = r33.zzf()
            long r7 = r7 + r5
            com.google.android.gms.internal.ads.zzfu r3 = r0.zzp
            if (r3 == 0) goto L_0x0244
            byte[] r9 = r3.zzM()
            int r10 = r0.zzo
            int r5 = (int) r5
            r1.zzi(r9, r10, r5)
            int r5 = r0.zzm
            if (r5 != r4) goto L_0x0229
            r4 = 1
            r0.zzu = r4
            r3.zzK(r11)
            int r4 = r3.zzg()
            int r4 = zzi(r4)
            if (r4 == 0) goto L_0x0210
            goto L_0x0226
        L_0x0210:
            r4 = 4
            r3.zzL(r4)
        L_0x0214:
            int r4 = r3.zzb()
            if (r4 <= 0) goto L_0x0225
            int r4 = r3.zzg()
            int r4 = zzi(r4)
            if (r4 == 0) goto L_0x0214
            goto L_0x0226
        L_0x0225:
            r4 = 0
        L_0x0226:
            r0.zzA = r4
            goto L_0x025d
        L_0x0229:
            java.util.ArrayDeque r4 = r0.zzh
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x025d
            java.util.ArrayDeque r4 = r0.zzh
            java.lang.Object r4 = r4.peek()
            com.google.android.gms.internal.ads.zzajm r4 = (com.google.android.gms.internal.ads.zzajm) r4
            com.google.android.gms.internal.ads.zzajn r5 = new com.google.android.gms.internal.ads.zzajn
            int r6 = r0.zzm
            r5.<init>(r6, r3)
            r4.zzd(r5)
            goto L_0x025d
        L_0x0244:
            boolean r3 = r0.zzu
            if (r3 != 0) goto L_0x0252
            int r3 = r0.zzm
            r4 = 1835295092(0x6d646174, float:4.4175247E27)
            if (r3 != r4) goto L_0x0252
            r3 = 1
            r0.zzA = r3
        L_0x0252:
            r3 = 262144(0x40000, double:1.295163E-318)
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x025f
            int r3 = (int) r5
            r1.zzk(r3)
        L_0x025d:
            r13 = 0
            goto L_0x0267
        L_0x025f:
            long r3 = r33.zzf()
            long r3 = r3 + r5
            r2.zza = r3
            r13 = 1
        L_0x0267:
            r0.zzm(r7)
            if (r13 == 0) goto L_0x0006
            int r3 = r0.zzl
            r5 = 2
            if (r3 == r5) goto L_0x0006
            r3 = 1
            return r3
        L_0x0273:
            r5 = r9
            r3 = r12
            int r6 = r0.zzo
            if (r6 != 0) goto L_0x02e3
            com.google.android.gms.internal.ads.zzfu r6 = r0.zzg
            byte[] r6 = r6.zzM()
            r9 = 0
            boolean r6 = r1.zzn(r6, r9, r11, r3)
            if (r6 != 0) goto L_0x02cb
            int r1 = r0.zzA
            if (r1 != r5) goto L_0x02c9
            int r1 = r0.zzc
            r1 = r1 & r5
            if (r1 == 0) goto L_0x02c9
            com.google.android.gms.internal.ads.zzadx r1 = r0.zzv
            r2 = 4
            com.google.android.gms.internal.ads.zzafa r1 = r1.zzw(r9, r2)
            com.google.android.gms.internal.ads.zzaie r2 = r0.zzB
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r2 != 0) goto L_0x02a1
            r5 = 0
            goto L_0x02ab
        L_0x02a1:
            com.google.android.gms.internal.ads.zzcd r5 = new com.google.android.gms.internal.ads.zzcd
            r6 = 1
            com.google.android.gms.internal.ads.zzcc[] r6 = new com.google.android.gms.internal.ads.zzcc[r6]
            r6[r9] = r2
            r5.<init>(r3, r6)
        L_0x02ab:
            com.google.android.gms.internal.ads.zzal r2 = new com.google.android.gms.internal.ads.zzal
            r2.<init>()
            r2.zzQ(r5)
            com.google.android.gms.internal.ads.zzan r2 = r2.zzad()
            r1.zzl(r2)
            com.google.android.gms.internal.ads.zzadx r1 = r0.zzv
            r1.zzD()
            com.google.android.gms.internal.ads.zzadx r1 = r0.zzv
            com.google.android.gms.internal.ads.zzaes r2 = new com.google.android.gms.internal.ads.zzaes
            r2.<init>(r3, r7)
            r1.zzO(r2)
        L_0x02c9:
            r1 = -1
            return r1
        L_0x02cb:
            r0.zzo = r11
            com.google.android.gms.internal.ads.zzfu r3 = r0.zzg
            r5 = 0
            r3.zzK(r5)
            com.google.android.gms.internal.ads.zzfu r3 = r0.zzg
            long r5 = r3.zzu()
            r0.zzn = r5
            com.google.android.gms.internal.ads.zzfu r3 = r0.zzg
            int r3 = r3.zzg()
            r0.zzm = r3
        L_0x02e3:
            long r5 = r0.zzn
            r9 = 1
            int r3 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r3 != 0) goto L_0x0302
            com.google.android.gms.internal.ads.zzfu r3 = r0.zzg
            byte[] r3 = r3.zzM()
            r1.zzi(r3, r11, r11)
            int r3 = r0.zzo
            int r3 = r3 + r11
            r0.zzo = r3
            com.google.android.gms.internal.ads.zzfu r3 = r0.zzg
            long r5 = r3.zzv()
            r0.zzn = r5
            goto L_0x032d
        L_0x0302:
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x032d
            long r5 = r33.zzd()
            r7 = -1
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x031e
            java.util.ArrayDeque r3 = r0.zzh
            java.lang.Object r3 = r3.peek()
            com.google.android.gms.internal.ads.zzajm r3 = (com.google.android.gms.internal.ads.zzajm) r3
            if (r3 == 0) goto L_0x031d
            long r5 = r3.zza
            goto L_0x031e
        L_0x031d:
            r5 = r7
        L_0x031e:
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x032d
            long r7 = r33.zzf()
            long r5 = r5 - r7
            int r3 = r0.zzo
            long r7 = (long) r3
            long r5 = r5 + r7
            r0.zzn = r5
        L_0x032d:
            long r5 = r0.zzn
            int r3 = r0.zzo
            long r7 = (long) r3
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 < 0) goto L_0x0468
            int r5 = r0.zzm
            r6 = 1836019574(0x6d6f6f76, float:4.631354E27)
            r7 = 1835365473(0x6d657461, float:4.4382975E27)
            if (r5 == r6) goto L_0x0416
            r6 = 1953653099(0x7472616b, float:7.681346E31)
            if (r5 == r6) goto L_0x0416
            r6 = 1835297121(0x6d646961, float:4.4181236E27)
            if (r5 == r6) goto L_0x0416
            r6 = 1835626086(0x6d696e66, float:4.515217E27)
            if (r5 == r6) goto L_0x0416
            r6 = 1937007212(0x7374626c, float:1.9362132E31)
            if (r5 == r6) goto L_0x0416
            r6 = 1701082227(0x65647473, float:6.742798E22)
            if (r5 == r6) goto L_0x0416
            if (r5 != r7) goto L_0x035d
            goto L_0x0416
        L_0x035d:
            r6 = 1835296868(0x6d646864, float:4.418049E27)
            if (r5 == r6) goto L_0x03e2
            r6 = 1836476516(0x6d766864, float:4.7662196E27)
            if (r5 == r6) goto L_0x03e2
            r6 = 1751411826(0x68646c72, float:4.3148E24)
            if (r5 == r6) goto L_0x03e2
            r6 = 1937011556(0x73747364, float:1.9367383E31)
            if (r5 == r6) goto L_0x03e2
            r6 = 1937011827(0x73747473, float:1.9367711E31)
            if (r5 == r6) goto L_0x03e2
            r6 = 1937011571(0x73747373, float:1.9367401E31)
            if (r5 == r6) goto L_0x03e2
            r6 = 1668576371(0x63747473, float:4.5093966E21)
            if (r5 == r6) goto L_0x03e2
            r6 = 1701606260(0x656c7374, float:6.9788014E22)
            if (r5 == r6) goto L_0x03e2
            r6 = 1937011555(0x73747363, float:1.9367382E31)
            if (r5 == r6) goto L_0x03e2
            r6 = 1937011578(0x7374737a, float:1.936741E31)
            if (r5 == r6) goto L_0x03e2
            r6 = 1937013298(0x73747a32, float:1.9369489E31)
            if (r5 == r6) goto L_0x03e2
            r6 = 1937007471(0x7374636f, float:1.9362445E31)
            if (r5 == r6) goto L_0x03e2
            r6 = 1668232756(0x636f3634, float:4.4126776E21)
            if (r5 == r6) goto L_0x03e2
            r6 = 1953196132(0x746b6864, float:7.46037E31)
            if (r5 == r6) goto L_0x03e2
            if (r5 == r4) goto L_0x03e2
            r4 = 1969517665(0x75647461, float:2.8960062E32)
            if (r5 == r4) goto L_0x03e2
            r4 = 1801812339(0x6b657973, float:2.7741754E26)
            if (r5 == r4) goto L_0x03e2
            r4 = 1768715124(0x696c7374, float:1.7865732E25)
            if (r5 != r4) goto L_0x03b5
            goto L_0x03e2
        L_0x03b5:
            long r3 = r33.zzf()
            int r5 = r0.zzo
            long r5 = (long) r5
            long r10 = r3 - r5
            int r3 = r0.zzm
            r4 = 1836086884(0x6d707664, float:4.6512205E27)
            if (r3 != r4) goto L_0x03da
            long r14 = r10 + r5
            com.google.android.gms.internal.ads.zzaie r3 = new com.google.android.gms.internal.ads.zzaie
            long r7 = r0.zzn
            long r16 = r7 - r5
            r8 = 0
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r7 = r3
            r7.<init>(r8, r10, r12, r14, r16)
            r0.zzB = r3
        L_0x03da:
            r3 = 0
            r0.zzp = r3
            r3 = 1
            r0.zzl = r3
            goto L_0x0006
        L_0x03e2:
            if (r3 != r11) goto L_0x03e6
            r3 = 1
            goto L_0x03e7
        L_0x03e6:
            r3 = 0
        L_0x03e7:
            com.google.android.gms.internal.ads.zzeq.zzf(r3)
            long r3 = r0.zzn
            r5 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 > 0) goto L_0x03f5
            r3 = 1
            goto L_0x03f6
        L_0x03f5:
            r3 = 0
        L_0x03f6:
            com.google.android.gms.internal.ads.zzeq.zzf(r3)
            com.google.android.gms.internal.ads.zzfu r3 = new com.google.android.gms.internal.ads.zzfu
            long r4 = r0.zzn
            int r4 = (int) r4
            r3.<init>((int) r4)
            com.google.android.gms.internal.ads.zzfu r4 = r0.zzg
            byte[] r4 = r4.zzM()
            byte[] r5 = r3.zzM()
            r6 = 0
            java.lang.System.arraycopy(r4, r6, r5, r6, r11)
            r0.zzp = r3
            r3 = 1
            r0.zzl = r3
            goto L_0x0006
        L_0x0416:
            long r3 = r33.zzf()
            long r5 = r0.zzn
            long r3 = r3 + r5
            int r8 = r0.zzo
            long r8 = (long) r8
            int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r5 == 0) goto L_0x0448
            int r5 = r0.zzm
            if (r5 != r7) goto L_0x0448
            com.google.android.gms.internal.ads.zzfu r5 = r0.zzf
            r5.zzH(r11)
            com.google.android.gms.internal.ads.zzfu r5 = r0.zzf
            byte[] r5 = r5.zzM()
            r6 = 0
            r1.zzh(r5, r6, r11)
            com.google.android.gms.internal.ads.zzfu r5 = r0.zzf
            com.google.android.gms.internal.ads.zzajw.zze(r5)
            com.google.android.gms.internal.ads.zzfu r5 = r0.zzf
            int r5 = r5.zzd()
            r1.zzk(r5)
            r33.zzj()
        L_0x0448:
            long r3 = r3 - r8
            java.util.ArrayDeque r5 = r0.zzh
            com.google.android.gms.internal.ads.zzajm r6 = new com.google.android.gms.internal.ads.zzajm
            int r7 = r0.zzm
            r6.<init>(r7, r3)
            r5.push(r6)
            long r5 = r0.zzn
            int r7 = r0.zzo
            long r7 = (long) r7
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 != 0) goto L_0x0463
            r0.zzm(r3)
            goto L_0x0006
        L_0x0463:
            r32.zzl()
            goto L_0x0006
        L_0x0468:
            java.lang.String r1 = "Atom size less than header length (unsupported)."
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zzc(r1)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakj.zzb(com.google.android.gms.internal.ads.zzadv, com.google.android.gms.internal.ads.zzaeq):int");
    }
}
