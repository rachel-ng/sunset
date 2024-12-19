package com.google.android.gms.internal.ads;

import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzajw {
    private static final byte[] zza = "OpusHead".getBytes(zzfxs.zzc);

    static {
        int i = zzgd.zza;
    }

    public static zzcd zza(zzajm zzajm) {
        zzgh zzgh;
        zzajn zzb = zzajm.zzb(Atom.TYPE_hdlr);
        zzajn zzb2 = zzajm.zzb(Atom.TYPE_keys);
        zzajn zzb3 = zzajm.zzb(Atom.TYPE_ilst);
        if (!(zzb == null || zzb2 == null || zzb3 == null || zzg(zzb.zza) != 1835299937)) {
            zzfu zzfu = zzb2.zza;
            zzfu.zzK(12);
            int zzg = zzfu.zzg();
            String[] strArr = new String[zzg];
            for (int i = 0; i < zzg; i++) {
                int zzg2 = zzfu.zzg();
                zzfu.zzL(4);
                strArr[i] = zzfu.zzA(zzg2 - 8, zzfxs.zzc);
            }
            zzfu zzfu2 = zzb3.zza;
            zzfu2.zzK(8);
            ArrayList arrayList = new ArrayList();
            while (zzfu2.zzb() > 8) {
                int zzd = zzfu2.zzd() + zzfu2.zzg();
                int zzg3 = zzfu2.zzg() - 1;
                if (zzg3 < 0 || zzg3 >= zzg) {
                    zzfk.zzf("AtomParsers", "Skipped metadata with unknown key index: " + zzg3);
                } else {
                    String str = strArr[zzg3];
                    int i2 = zzakf.zzb;
                    while (true) {
                        int zzd2 = zzfu2.zzd();
                        if (zzd2 >= zzd) {
                            zzgh = null;
                            break;
                        }
                        int zzg4 = zzfu2.zzg();
                        if (zzfu2.zzg() == 1684108385) {
                            int zzg5 = zzfu2.zzg();
                            int zzg6 = zzfu2.zzg();
                            int i3 = zzg4 - 16;
                            byte[] bArr = new byte[i3];
                            zzfu2.zzG(bArr, 0, i3);
                            zzgh = new zzgh(str, bArr, zzg6, zzg5);
                            break;
                        }
                        zzfu2.zzK(zzd2 + zzg4);
                    }
                    if (zzgh != null) {
                        arrayList.add(zzgh);
                    }
                }
                zzfu2.zzK(zzd);
            }
            if (!arrayList.isEmpty()) {
                return new zzcd((List) arrayList);
            }
        }
        return null;
    }

    public static zzcd zzb(zzajn zzajn) {
        int i;
        zzfu zzfu = zzajn.zza;
        zzfu.zzK(8);
        zzcd zzcd = new zzcd(C.TIME_UNSET, new zzcc[0]);
        while (zzfu.zzb() >= 8) {
            int zzd = zzfu.zzd();
            int zzg = zzfu.zzg() + zzd;
            int zzg2 = zzfu.zzg();
            zzcd zzcd2 = null;
            if (zzg2 == 1835365473) {
                zzfu.zzK(zzd);
                zzfu.zzL(8);
                zze(zzfu);
                while (true) {
                    if (zzfu.zzd() >= zzg) {
                        break;
                    }
                    int zzd2 = zzfu.zzd();
                    int zzg3 = zzfu.zzg() + zzd2;
                    if (zzfu.zzg() == 1768715124) {
                        zzfu.zzK(zzd2);
                        zzfu.zzL(8);
                        ArrayList arrayList = new ArrayList();
                        while (zzfu.zzd() < zzg3) {
                            zzcc zza2 = zzakf.zza(zzfu);
                            if (zza2 != null) {
                                arrayList.add(zza2);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            zzcd2 = new zzcd((List) arrayList);
                        }
                    } else {
                        zzfu.zzK(zzg3);
                    }
                }
                zzcd = zzcd.zzd(zzcd2);
            } else if (zzg2 == 1936553057) {
                zzfu.zzK(zzd);
                zzfu.zzL(12);
                while (true) {
                    if (zzfu.zzd() >= zzg) {
                        break;
                    }
                    int zzd3 = zzfu.zzd();
                    int zzg4 = zzfu.zzg();
                    if (zzfu.zzg() != 1935766900) {
                        zzfu.zzK(zzd3 + zzg4);
                    } else if (zzg4 >= 16) {
                        zzfu.zzL(4);
                        int i2 = -1;
                        int i3 = 0;
                        for (int i4 = 0; i4 < 2; i4++) {
                            int zzm = zzfu.zzm();
                            int zzm2 = zzfu.zzm();
                            if (zzm == 0) {
                                i2 = zzm2;
                            } else if (zzm == 1) {
                                i3 = zzm2;
                            }
                        }
                        if (i2 == 12) {
                            i = PsExtractor.VIDEO_STREAM_MASK;
                        } else if (i2 == 13) {
                            i = 120;
                        } else {
                            if (i2 == 21 && zzfu.zzb() >= 8 && zzfu.zzd() + 8 <= zzg) {
                                int zzg5 = zzfu.zzg();
                                int zzg6 = zzfu.zzg();
                                if (zzg5 >= 12 && zzg6 == 1936877170) {
                                    i = zzfu.zzn();
                                }
                            }
                            i = -2147483647;
                        }
                        if (i != -2147483647) {
                            zzcd2 = new zzcd(C.TIME_UNSET, new zzaim((float) i, i3));
                        }
                    }
                }
                zzcd = zzcd.zzd(zzcd2);
            } else if (zzg2 == -1451722374) {
                zzcd = zzcd.zzd(zzi(zzfu));
            }
            zzfu.zzK(zzg);
        }
        return zzcd;
    }

    public static zzgn zzc(zzfu zzfu) {
        long j;
        long j2;
        zzfu.zzK(8);
        if (zzajo.zze(zzfu.zzg()) == 0) {
            j2 = zzfu.zzu();
            j = zzfu.zzu();
        } else {
            j2 = zzfu.zzt();
            j = zzfu.zzt();
        }
        return new zzgn(j2, j, zzfu.zzu());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:342:0x0712, code lost:
        if (r8.zzd(1) > 0) goto L_0x0714;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:0x0736, code lost:
        if (r15 == 1) goto L_0x0738;
     */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x0641  */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x0659  */
    /* JADX WARNING: Removed duplicated region for block: B:361:0x0748  */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x0778  */
    /* JADX WARNING: Removed duplicated region for block: B:376:0x077a  */
    /* JADX WARNING: Removed duplicated region for block: B:456:0x0968  */
    /* JADX WARNING: Removed duplicated region for block: B:457:0x096a  */
    /* JADX WARNING: Removed duplicated region for block: B:508:0x0afd  */
    /* JADX WARNING: Removed duplicated region for block: B:509:0x0b01  */
    /* JADX WARNING: Removed duplicated region for block: B:520:0x0b67 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0192  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x019b  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x019d  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01df  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List zzd(com.google.android.gms.internal.ads.zzajm r56, com.google.android.gms.internal.ads.zzaej r57, long r58, com.google.android.gms.internal.ads.zzae r60, boolean r61, boolean r62, com.google.android.gms.internal.ads.zzfxu r63) throws com.google.android.gms.internal.ads.zzch {
        /*
            r0 = r56
            r11 = r60
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            r14 = 0
        L_0x000a:
            java.util.List r1 = r0.zzc
            int r1 = r1.size()
            if (r14 >= r1) goto L_0x0b6f
            java.util.List r1 = r0.zzc
            java.lang.Object r1 = r1.get(r14)
            r15 = r1
            com.google.android.gms.internal.ads.zzajm r15 = (com.google.android.gms.internal.ads.zzajm) r15
            int r1 = r15.zzd
            r2 = 1953653099(0x7472616b, float:7.681346E31)
            if (r1 == r2) goto L_0x002d
            r3 = r57
            r0 = r63
            r2 = r12
            r32 = r14
            r31 = 0
            goto L_0x0b5e
        L_0x002d:
            r1 = 1836476516(0x6d766864, float:4.7662196E27)
            com.google.android.gms.internal.ads.zzajn r1 = r0.zzb(r1)
            r1.getClass()
            r2 = 1835297121(0x6d646961, float:4.4181236E27)
            com.google.android.gms.internal.ads.zzajm r2 = r15.zza(r2)
            r2.getClass()
            r3 = 1751411826(0x68646c72, float:4.3148E24)
            com.google.android.gms.internal.ads.zzajn r3 = r2.zzb(r3)
            r3.getClass()
            com.google.android.gms.internal.ads.zzfu r3 = r3.zza
            int r3 = zzg(r3)
            r4 = 1936684398(0x736f756e, float:1.8971874E31)
            r8 = -1
            if (r3 != r4) goto L_0x0059
            r5 = 1
            goto L_0x007f
        L_0x0059:
            r4 = 1986618469(0x76696465, float:1.1834389E33)
            if (r3 != r4) goto L_0x0060
            r5 = 2
            goto L_0x007f
        L_0x0060:
            r4 = 1952807028(0x74657874, float:7.272211E31)
            if (r3 == r4) goto L_0x007e
            r4 = 1935832172(0x7362746c, float:1.7941596E31)
            if (r3 == r4) goto L_0x007e
            r4 = 1937072756(0x73756274, float:1.944137E31)
            if (r3 == r4) goto L_0x007e
            r4 = 1668047728(0x636c6370, float:4.3605968E21)
            if (r3 != r4) goto L_0x0075
            goto L_0x007e
        L_0x0075:
            r4 = 1835365473(0x6d657461, float:4.4382975E27)
            if (r3 != r4) goto L_0x007c
            r5 = 5
            goto L_0x007f
        L_0x007c:
            r5 = r8
            goto L_0x007f
        L_0x007e:
            r5 = 3
        L_0x007f:
            if (r5 != r8) goto L_0x008d
            r0 = r63
            r33 = r12
            r32 = r14
            r1 = r15
            r4 = 0
            r31 = 0
            goto L_0x0b28
        L_0x008d:
            r3 = 1953196132(0x746b6864, float:7.46037E31)
            com.google.android.gms.internal.ads.zzajn r3 = r15.zzb(r3)
            r3.getClass()
            com.google.android.gms.internal.ads.zzfu r3 = r3.zza
            r6 = 8
            r3.zzK(r6)
            int r17 = r3.zzg()
            int r17 = com.google.android.gms.internal.ads.zzajo.zze(r17)
            r13 = 16
            if (r17 != 0) goto L_0x00ac
            r4 = r6
            goto L_0x00ad
        L_0x00ac:
            r4 = r13
        L_0x00ad:
            r3.zzL(r4)
            int r4 = r3.zzg()
            r6 = 4
            r3.zzL(r6)
            int r20 = r3.zzd()
            r7 = 0
        L_0x00bd:
            if (r17 != 0) goto L_0x00c1
            r9 = r6
            goto L_0x00c3
        L_0x00c1:
            r9 = 8
        L_0x00c3:
            r23 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r7 >= r9) goto L_0x00ec
            byte[] r9 = r3.zzM()
            int r25 = r20 + r7
            byte r9 = r9[r25]
            if (r9 == r8) goto L_0x00e9
            if (r17 != 0) goto L_0x00db
            long r25 = r3.zzu()
            goto L_0x00df
        L_0x00db:
            long r25 = r3.zzv()
        L_0x00df:
            r27 = 0
            int r7 = (r25 > r27 ? 1 : (r25 == r27 ? 0 : -1))
            if (r7 != 0) goto L_0x00e6
            goto L_0x00ef
        L_0x00e6:
            r8 = r25
            goto L_0x00f1
        L_0x00e9:
            int r7 = r7 + 1
            goto L_0x00bd
        L_0x00ec:
            r3.zzL(r9)
        L_0x00ef:
            r8 = r23
        L_0x00f1:
            r3.zzL(r13)
            int r7 = r3.zzg()
            int r10 = r3.zzg()
            r3.zzL(r6)
            int r6 = r3.zzg()
            int r3 = r3.zzg()
            r13 = 65536(0x10000, float:9.18355E-41)
            r0 = -65536(0xffffffffffff0000, float:NaN)
            if (r7 != 0) goto L_0x0119
            if (r10 != r13) goto L_0x0118
            if (r6 != r0) goto L_0x0117
            if (r3 != 0) goto L_0x0116
            r0 = 90
            goto L_0x0137
        L_0x0116:
            r6 = r0
        L_0x0117:
            r10 = r13
        L_0x0118:
            r7 = 0
        L_0x0119:
            if (r7 != 0) goto L_0x012a
            if (r10 != r0) goto L_0x0127
            if (r6 != r13) goto L_0x0126
            if (r3 != 0) goto L_0x0124
            r0 = 270(0x10e, float:3.78E-43)
            goto L_0x0137
        L_0x0124:
            r10 = r0
            goto L_0x0128
        L_0x0126:
            r10 = r0
        L_0x0127:
            r13 = r6
        L_0x0128:
            r7 = 0
            goto L_0x012b
        L_0x012a:
            r13 = r6
        L_0x012b:
            if (r7 != r0) goto L_0x0136
            if (r10 != 0) goto L_0x0136
            if (r13 != 0) goto L_0x0136
            if (r3 != r0) goto L_0x0136
            r0 = 180(0xb4, float:2.52E-43)
            goto L_0x0137
        L_0x0136:
            r0 = 0
        L_0x0137:
            com.google.android.gms.internal.ads.zzajv r13 = new com.google.android.gms.internal.ads.zzajv
            r13.<init>(r4, r8, r0)
            int r0 = (r58 > r23 ? 1 : (r58 == r23 ? 0 : -1))
            if (r0 != 0) goto L_0x0147
            long r3 = r13.zzb
            r32 = r3
            goto L_0x0149
        L_0x0147:
            r32 = r58
        L_0x0149:
            com.google.android.gms.internal.ads.zzfu r0 = r1.zza
            com.google.android.gms.internal.ads.zzgn r0 = zzc(r0)
            long r9 = r0.zzc
            int r0 = (r32 > r23 ? 1 : (r32 == r23 ? 0 : -1))
            if (r0 != 0) goto L_0x0156
            goto L_0x0163
        L_0x0156:
            r34 = 1000000(0xf4240, double:4.940656E-318)
            java.math.RoundingMode r38 = java.math.RoundingMode.FLOOR
            r36 = r9
            long r0 = com.google.android.gms.internal.ads.zzgd.zzt(r32, r34, r36, r38)
            r23 = r0
        L_0x0163:
            r0 = 1835626086(0x6d696e66, float:4.515217E27)
            com.google.android.gms.internal.ads.zzajm r0 = r2.zza(r0)
            r0.getClass()
            r1 = 1937007212(0x7374626c, float:1.9362132E31)
            com.google.android.gms.internal.ads.zzajm r0 = r0.zza(r1)
            r0.getClass()
            r1 = 1835296868(0x6d646864, float:4.418049E27)
            com.google.android.gms.internal.ads.zzajn r1 = r2.zzb(r1)
            r1.getClass()
            com.google.android.gms.internal.ads.zzfu r1 = r1.zza
            r6 = 8
            r1.zzK(r6)
            int r2 = r1.zzg()
            int r2 = com.google.android.gms.internal.ads.zzajo.zze(r2)
            if (r2 != 0) goto L_0x0194
            r3 = r6
            goto L_0x0196
        L_0x0194:
            r3 = 16
        L_0x0196:
            r1.zzL(r3)
            if (r2 != 0) goto L_0x019d
            r2 = 4
            goto L_0x019e
        L_0x019d:
            r2 = r6
        L_0x019e:
            long r3 = r1.zzu()
            r1.zzL(r2)
            int r1 = r1.zzq()
            int r2 = r1 >> 10
            int r7 = r1 >> 5
            r1 = r1 & 31
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r2 = r2 & 31
            int r2 = r2 + 96
            char r2 = (char) r2
            r8.append(r2)
            r2 = r7 & 31
            int r2 = r2 + 96
            char r2 = (char) r2
            r8.append(r2)
            int r1 = r1 + 96
            char r1 = (char) r1
            r8.append(r1)
            java.lang.String r1 = r8.toString()
            java.lang.Long r2 = java.lang.Long.valueOf(r3)
            android.util.Pair r8 = android.util.Pair.create(r2, r1)
            r1 = 1937011556(0x73747364, float:1.9367383E31)
            com.google.android.gms.internal.ads.zzajn r0 = r0.zzb(r1)
            if (r0 == 0) goto L_0x0b67
            int r7 = r13.zza
            int r4 = r13.zzc
            java.lang.Object r1 = r8.second
            r3 = r1
            java.lang.String r3 = (java.lang.String) r3
            com.google.android.gms.internal.ads.zzfu r0 = r0.zza
            r2 = 12
            r0.zzK(r2)
            int r1 = r0.zzg()
            r32 = r14
            com.google.android.gms.internal.ads.zzajs r14 = new com.google.android.gms.internal.ads.zzajs
            r14.<init>(r1)
            r33 = r12
            r12 = 0
        L_0x0201:
            if (r12 >= r1) goto L_0x0a6f
            r19 = r13
            int r13 = r0.zzd()
            r34 = r15
            int r15 = r0.zzg()
            if (r15 <= 0) goto L_0x0213
            r2 = 1
            goto L_0x0214
        L_0x0213:
            r2 = 0
        L_0x0214:
            java.lang.String r6 = "childAtomSize must be positive"
            com.google.android.gms.internal.ads.zzady.zzb(r2, r6)
            int r2 = r0.zzg()
            r6 = 1635148593(0x61766331, float:2.840654E20)
            r29 = r1
            r1 = 1701733238(0x656e6376, float:7.035987E22)
            if (r2 == r6) goto L_0x040e
            r6 = 1635148595(0x61766333, float:2.8406544E20)
            if (r2 == r6) goto L_0x040e
            if (r2 == r1) goto L_0x040e
            r6 = 1831958048(0x6d317620, float:3.4326032E27)
            if (r2 == r6) goto L_0x040e
            r6 = 1836070006(0x6d703476, float:4.646239E27)
            if (r2 == r6) goto L_0x040e
            r6 = 1752589105(0x68766331, float:4.6541277E24)
            if (r2 == r6) goto L_0x040e
            r6 = 1751479857(0x68657631, float:4.3344087E24)
            if (r2 == r6) goto L_0x040e
            r6 = 1932670515(0x73323633, float:1.4119387E31)
            if (r2 == r6) goto L_0x040e
            r6 = 1211250227(0x48323633, float:182488.8)
            if (r2 == r6) goto L_0x040e
            r6 = 1987063864(0x76703038, float:1.21789965E33)
            if (r2 == r6) goto L_0x040e
            r6 = 1987063865(0x76703039, float:1.2178997E33)
            if (r2 == r6) goto L_0x040e
            r6 = 1635135537(0x61763031, float:2.8383572E20)
            if (r2 == r6) goto L_0x040e
            r6 = 1685479798(0x64766176, float:1.8179687E22)
            if (r2 == r6) goto L_0x040e
            r6 = 1685479729(0x64766131, float:1.817961E22)
            if (r2 == r6) goto L_0x040e
            r6 = 1685481573(0x64766865, float:1.8181686E22)
            if (r2 == r6) goto L_0x040e
            r6 = 1685481521(0x64766831, float:1.8181627E22)
            if (r2 != r6) goto L_0x0271
            goto L_0x040e
        L_0x0271:
            r1 = 1836069985(0x6d703461, float:4.6462328E27)
            if (r2 == r1) goto L_0x03d8
            r1 = 1701733217(0x656e6361, float:7.0359778E22)
            if (r2 == r1) goto L_0x03d8
            r1 = 1633889587(0x61632d33, float:2.6191674E20)
            if (r2 == r1) goto L_0x03d8
            r1 = 1700998451(0x65632d33, float:6.7050686E22)
            if (r2 == r1) goto L_0x03d8
            r1 = 1633889588(0x61632d34, float:2.6191676E20)
            if (r2 == r1) goto L_0x03d8
            r1 = 1835823201(0x6d6c7061, float:4.573395E27)
            if (r2 == r1) goto L_0x03d8
            r1 = 1685353315(0x64747363, float:1.803728E22)
            if (r2 == r1) goto L_0x03d8
            r1 = 1685353317(0x64747365, float:1.8037282E22)
            if (r2 == r1) goto L_0x03d8
            r1 = 1685353320(0x64747368, float:1.8037286E22)
            if (r2 == r1) goto L_0x03d8
            r1 = 1685353324(0x6474736c, float:1.803729E22)
            if (r2 == r1) goto L_0x03d8
            r1 = 1685353336(0x64747378, float:1.8037304E22)
            if (r2 == r1) goto L_0x03d8
            r1 = 1935764850(0x73616d72, float:1.7860208E31)
            if (r2 == r1) goto L_0x03d8
            r1 = 1935767394(0x73617762, float:1.7863284E31)
            if (r2 == r1) goto L_0x03d8
            r1 = 1819304813(0x6c70636d, float:1.1624469E27)
            if (r2 == r1) goto L_0x03d8
            r1 = 1936684916(0x736f7774, float:1.89725E31)
            if (r2 == r1) goto L_0x03d8
            r1 = 1953984371(0x74776f73, float:7.841539E31)
            if (r2 == r1) goto L_0x03d8
            r1 = 778924082(0x2e6d7032, float:5.398721E-11)
            if (r2 == r1) goto L_0x03d8
            r1 = 778924083(0x2e6d7033, float:5.3987214E-11)
            if (r2 == r1) goto L_0x03d8
            r1 = 1835557169(0x6d686131, float:4.4948762E27)
            if (r2 == r1) goto L_0x03d8
            r1 = 1835560241(0x6d686d31, float:4.495783E27)
            if (r2 == r1) goto L_0x03d8
            r1 = 1634492771(0x616c6163, float:2.7252807E20)
            if (r2 == r1) goto L_0x03d8
            r1 = 1634492791(0x616c6177, float:2.7252842E20)
            if (r2 == r1) goto L_0x03d8
            r1 = 1970037111(0x756c6177, float:2.9964816E32)
            if (r2 == r1) goto L_0x03d8
            r1 = 1332770163(0x4f707573, float:4.03422899E9)
            if (r2 == r1) goto L_0x03d8
            r1 = 1716281667(0x664c6143, float:2.4128923E23)
            if (r2 != r1) goto L_0x02f0
            goto L_0x03d8
        L_0x02f0:
            r1 = 1414810956(0x54544d4c, float:3.64731957E12)
            if (r2 == r1) goto L_0x035d
            r1 = 1954034535(0x74783367, float:7.865797E31)
            if (r2 == r1) goto L_0x035d
            r1 = 2004251764(0x77767474, float:4.998699E33)
            if (r2 == r1) goto L_0x035d
            r1 = 1937010800(0x73747070, float:1.9366469E31)
            if (r2 == r1) goto L_0x035d
            r1 = 1664495672(0x63363038, float:3.360782E21)
            if (r2 != r1) goto L_0x030a
            goto L_0x035d
        L_0x030a:
            r1 = 1835365492(0x6d657474, float:4.4383032E27)
            if (r2 != r1) goto L_0x0330
            int r1 = r13 + 16
            r0.zzK(r1)
            r1 = 0
            r0.zzx(r1)
            java.lang.String r2 = r0.zzx(r1)
            if (r2 == 0) goto L_0x0348
            com.google.android.gms.internal.ads.zzal r1 = new com.google.android.gms.internal.ads.zzal
            r1.<init>()
            r1.zzJ(r7)
            r1.zzX(r2)
            com.google.android.gms.internal.ads.zzan r1 = r1.zzad()
            r14.zzb = r1
            goto L_0x0348
        L_0x0330:
            r1 = 1667329389(0x63616d6d, float:4.1584024E21)
            if (r2 != r1) goto L_0x0348
            com.google.android.gms.internal.ads.zzal r1 = new com.google.android.gms.internal.ads.zzal
            r1.<init>()
            r1.zzJ(r7)
            java.lang.String r2 = "application/x-camera-motion"
            r1.zzX(r2)
            com.google.android.gms.internal.ads.zzan r1 = r1.zzad()
            r14.zzb = r1
        L_0x0348:
            r27 = r3
            r2 = r4
            r18 = r5
            r3 = r7
            r44 = r8
            r21 = r9
            r20 = r12
            r38 = r13
            r6 = r14
            r40 = r15
            r16 = r29
            goto L_0x03d3
        L_0x035d:
            int r1 = r13 + 16
            r0.zzK(r1)
            r1 = 1414810956(0x54544d4c, float:3.64731957E12)
            r35 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            if (r2 != r1) goto L_0x0373
            java.lang.String r1 = "application/ttml+xml"
        L_0x036e:
            r2 = r1
        L_0x036f:
            r39 = r35
            r1 = 0
            goto L_0x03a2
        L_0x0373:
            r1 = 1954034535(0x74783367, float:7.865797E31)
            if (r2 != r1) goto L_0x0389
            int r1 = r15 + -16
            byte[] r2 = new byte[r1]
            r6 = 0
            r0.zzG(r2, r6, r1)
            com.google.android.gms.internal.ads.zzgbc r1 = com.google.android.gms.internal.ads.zzgbc.zzn(r2)
            java.lang.String r2 = "application/x-quicktime-tx3g"
            r39 = r35
            goto L_0x03a2
        L_0x0389:
            r6 = 0
            r1 = 2004251764(0x77767474, float:4.998699E33)
            if (r2 != r1) goto L_0x0392
            java.lang.String r1 = "application/x-mp4-vtt"
            goto L_0x036e
        L_0x0392:
            r1 = 1937010800(0x73747070, float:1.9366469E31)
            if (r2 != r1) goto L_0x039c
            r35 = 0
            java.lang.String r1 = "application/ttml+xml"
            goto L_0x036e
        L_0x039c:
            r1 = 1
            r14.zzd = r1
            java.lang.String r2 = "application/x-mp4-cea-608"
            goto L_0x036f
        L_0x03a2:
            com.google.android.gms.internal.ads.zzal r6 = new com.google.android.gms.internal.ads.zzal
            r6.<init>()
            r6.zzJ(r7)
            r6.zzX(r2)
            r6.zzO(r3)
            r35 = r3
            r2 = r39
            r6.zzab(r2)
            r6.zzL(r1)
            com.google.android.gms.internal.ads.zzan r1 = r6.zzad()
            r14.zzb = r1
            r2 = r4
            r18 = r5
            r3 = r7
            r44 = r8
            r21 = r9
            r20 = r12
            r38 = r13
            r6 = r14
            r40 = r15
            r16 = r29
            r27 = r35
        L_0x03d3:
            r4 = 3
            r5 = -1
            r31 = 0
            goto L_0x040a
        L_0x03d8:
            r35 = r3
            r16 = r29
            r6 = 1
            r1 = r0
            r3 = 12
            r27 = r35
            r3 = r13
            r41 = r4
            r4 = r15
            r18 = r5
            r5 = r7
            r31 = 0
            r6 = r27
            r43 = r7
            r7 = r62
            r44 = r8
            r8 = r60
            r21 = r9
            r10 = 3
            r9 = r14
            r10 = r12
            zzm(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            r20 = r12
            r38 = r13
            r6 = r14
            r40 = r15
            r2 = r41
            r3 = r43
            r4 = 3
            r5 = -1
        L_0x040a:
            r42 = 4
            goto L_0x0a4f
        L_0x040e:
            r27 = r3
            r41 = r4
            r18 = r5
            r43 = r7
            r44 = r8
            r21 = r9
            r16 = r29
            r31 = 0
            int r3 = r13 + 16
            r0.zzK(r3)
            r3 = 16
            r0.zzL(r3)
            int r4 = r0.zzq()
            int r5 = r0.zzq()
            r6 = 50
            r0.zzL(r6)
            int r6 = r0.zzd()
            if (r2 != r1) goto L_0x0467
            android.util.Pair r2 = zzh(r0, r13, r15)
            if (r2 == 0) goto L_0x0461
            java.lang.Object r1 = r2.first
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r11 != 0) goto L_0x044d
            r7 = 0
            goto L_0x0457
        L_0x044d:
            java.lang.Object r7 = r2.second
            com.google.android.gms.internal.ads.zzakq r7 = (com.google.android.gms.internal.ads.zzakq) r7
            java.lang.String r7 = r7.zzb
            com.google.android.gms.internal.ads.zzae r7 = r11.zzb(r7)
        L_0x0457:
            com.google.android.gms.internal.ads.zzakq[] r8 = r14.zza
            java.lang.Object r2 = r2.second
            com.google.android.gms.internal.ads.zzakq r2 = (com.google.android.gms.internal.ads.zzakq) r2
            r8[r12] = r2
            r2 = r1
            goto L_0x0463
        L_0x0461:
            r2 = r1
            r7 = r11
        L_0x0463:
            r0.zzK(r6)
            goto L_0x0468
        L_0x0467:
            r7 = r11
        L_0x0468:
            r1 = 1831958048(0x6d317620, float:3.4326032E27)
            if (r2 != r1) goto L_0x0475
            java.lang.String r1 = "video/mpeg"
            r55 = r2
            r2 = r1
            r1 = r55
            goto L_0x047f
        L_0x0475:
            r1 = 1211250227(0x48323633, float:182488.8)
            if (r2 != r1) goto L_0x047d
            java.lang.String r2 = "video/3gpp"
            goto L_0x047f
        L_0x047d:
            r1 = r2
            r2 = 0
        L_0x047f:
            r8 = 1065353216(0x3f800000, float:1.0)
            r3 = r6
            r35 = r7
            r7 = r8
            r20 = r12
            r25 = r31
            r6 = 0
            r9 = 0
            r10 = 0
            r11 = -1
            r12 = -1
            r17 = 0
            r28 = 8
            r29 = 8
            r30 = -1
            r36 = 0
            r37 = -1
        L_0x049a:
            int r8 = r3 - r13
            if (r8 >= r15) goto L_0x09ab
            r0.zzK(r3)
            int r8 = r0.zzd()
            int r38 = r0.zzg()
            if (r38 != 0) goto L_0x04c2
            int r38 = r0.zzd()
            r39 = r10
            int r10 = r38 - r13
            if (r10 != r15) goto L_0x04bf
            r49 = r4
            r48 = r5
            r47 = r7
            r54 = r9
            goto L_0x09b5
        L_0x04bf:
            r10 = r31
            goto L_0x04c6
        L_0x04c2:
            r39 = r10
            r10 = r38
        L_0x04c6:
            if (r10 <= 0) goto L_0x04ce
            r38 = r13
            r40 = r15
            r13 = 1
            goto L_0x04d4
        L_0x04ce:
            r38 = r13
            r40 = r15
            r13 = r31
        L_0x04d4:
            java.lang.String r15 = "childAtomSize must be positive"
            com.google.android.gms.internal.ads.zzady.zzb(r13, r15)
            int r13 = r0.zzg()
            r15 = 1635148611(0x61766343, float:2.8406573E20)
            if (r13 != r15) goto L_0x0532
            int r8 = r8 + 8
            if (r2 != 0) goto L_0x04e8
            r2 = 1
            goto L_0x04ea
        L_0x04e8:
            r2 = r31
        L_0x04ea:
            r15 = 0
            com.google.android.gms.internal.ads.zzady.zzb(r2, r15)
            r0.zzK(r8)
            com.google.android.gms.internal.ads.zzacx r2 = com.google.android.gms.internal.ads.zzacx.zza(r0)
            java.util.List r8 = r2.zza
            int r9 = r2.zzb
            r14.zzc = r9
            if (r25 != 0) goto L_0x0503
            float r7 = r2.zzj
            r9 = r7
            r7 = r31
            goto L_0x0505
        L_0x0503:
            r9 = r7
            r7 = 1
        L_0x0505:
            java.lang.String r11 = r2.zzk
            int r12 = r2.zzg
            int r13 = r2.zzh
            int r15 = r2.zzi
            r25 = r7
            int r7 = r2.zze
            int r2 = r2.zzf
            java.lang.String r28 = "video/avc"
        L_0x0515:
            r45 = r1
            r49 = r4
            r48 = r5
            r29 = r7
            r36 = r8
            r7 = r9
            r9 = r11
            r30 = r13
            r46 = r14
            r11 = r15
            r4 = 3
            r5 = -1
            r42 = 4
            r55 = r28
            r28 = r2
            r2 = r55
            goto L_0x099a
        L_0x0532:
            r15 = 1752589123(0x68766343, float:4.6541328E24)
            if (r13 != r15) goto L_0x056b
            int r8 = r8 + 8
            if (r2 != 0) goto L_0x053d
            r2 = 1
            goto L_0x053f
        L_0x053d:
            r2 = r31
        L_0x053f:
            r9 = 0
            com.google.android.gms.internal.ads.zzady.zzb(r2, r9)
            r0.zzK(r8)
            com.google.android.gms.internal.ads.zzaek r2 = com.google.android.gms.internal.ads.zzaek.zza(r0)
            java.util.List r8 = r2.zza
            int r9 = r2.zzb
            r14.zzc = r9
            if (r25 != 0) goto L_0x0558
            float r7 = r2.zzh
            r9 = r7
            r7 = r31
            goto L_0x055a
        L_0x0558:
            r9 = r7
            r7 = 1
        L_0x055a:
            java.lang.String r11 = r2.zzi
            int r12 = r2.zze
            int r13 = r2.zzf
            int r15 = r2.zzg
            r25 = r7
            int r7 = r2.zzc
            int r2 = r2.zzd
            java.lang.String r28 = "video/hevc"
            goto L_0x0515
        L_0x056b:
            r15 = 1685480259(0x64766343, float:1.8180206E22)
            if (r13 == r15) goto L_0x0978
            r15 = 1685485123(0x64767643, float:1.8185683E22)
            if (r13 != r15) goto L_0x0577
            goto L_0x0978
        L_0x0577:
            r15 = 1987076931(0x76706343, float:1.21891066E33)
            if (r13 != r15) goto L_0x05cc
            if (r2 != 0) goto L_0x0580
            r2 = 1
            goto L_0x0582
        L_0x0580:
            r2 = r31
        L_0x0582:
            r11 = 0
            com.google.android.gms.internal.ads.zzady.zzb(r2, r11)
            int r8 = r8 + 12
            r0.zzK(r8)
            r15 = 2
            r0.zzL(r15)
            int r2 = r0.zzm()
            int r8 = r2 >> 4
            r11 = 1
            r2 = r2 & r11
            int r12 = r0.zzm()
            int r13 = r0.zzm()
            int r12 = com.google.android.gms.internal.ads.zzt.zza(r12)
            if (r11 == r2) goto L_0x05a7
            r2 = r15
            goto L_0x05a8
        L_0x05a7:
            r2 = r11
        L_0x05a8:
            int r13 = com.google.android.gms.internal.ads.zzt.zzb(r13)
            r11 = 1987063864(0x76703038, float:1.21789965E33)
            if (r1 != r11) goto L_0x05b4
            java.lang.String r11 = "video/x-vnd.on2.vp8"
            goto L_0x05b6
        L_0x05b4:
            java.lang.String r11 = "video/x-vnd.on2.vp9"
        L_0x05b6:
            r45 = r1
            r30 = r2
            r49 = r4
            r48 = r5
            r28 = r8
            r29 = r28
            r2 = r11
            r11 = r13
            r46 = r14
            r4 = 3
            r5 = -1
            r42 = 4
            goto L_0x099a
        L_0x05cc:
            r15 = 1635135811(0x61763143, float:2.8384055E20)
            r45 = r1
            java.lang.String r1 = "AtomParsers"
            if (r13 != r15) goto L_0x07a9
            int r8 = r8 + 8
            r0.zzK(r8)
            com.google.android.gms.internal.ads.zzr r2 = new com.google.android.gms.internal.ads.zzr
            r2.<init>()
            com.google.android.gms.internal.ads.zzft r8 = new com.google.android.gms.internal.ads.zzft
            byte[] r11 = r0.zzM()
            int r12 = r11.length
            r8.<init>(r11, r12)
            int r11 = r0.zzd()
            r15 = 8
            int r11 = r11 * r15
            r8.zzk(r11)
            r11 = 1
            r8.zzn(r11)
            r12 = 3
            int r13 = r8.zzd(r12)
            r12 = 6
            r8.zzm(r12)
            boolean r12 = r8.zzo()
            boolean r15 = r8.zzo()
            r28 = 10
            r11 = 2
            if (r13 != r11) goto L_0x0622
            if (r12 == 0) goto L_0x061f
            r12 = 1
            if (r12 == r15) goto L_0x0615
            r13 = r28
            goto L_0x0617
        L_0x0615:
            r13 = 12
        L_0x0617:
            r2.zzf(r13)
            r2.zza(r13)
            r15 = r12
            goto L_0x0632
        L_0x061f:
            r13 = r11
            r12 = r31
        L_0x0622:
            r15 = 1
            if (r13 > r11) goto L_0x0632
            if (r15 == r12) goto L_0x062a
            r11 = 8
            goto L_0x062c
        L_0x062a:
            r11 = r28
        L_0x062c:
            r2.zzf(r11)
            r2.zza(r11)
        L_0x0632:
            r11 = 13
            r8.zzm(r11)
            r8.zzl()
            r12 = 4
            int r13 = r8.zzd(r12)
            if (r13 == r15) goto L_0x0659
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r11 = "Unsupported obu_type: "
            r8.<init>(r11)
            r8.append(r13)
            java.lang.String r8 = r8.toString()
            com.google.android.gms.internal.ads.zzfk.zze(r1, r8)
            com.google.android.gms.internal.ads.zzt r1 = r2.zzg()
        L_0x0656:
            r11 = 3
            goto L_0x0789
        L_0x0659:
            boolean r13 = r8.zzo()
            if (r13 == 0) goto L_0x0669
            java.lang.String r8 = "Unsupported obu_extension_flag"
            com.google.android.gms.internal.ads.zzfk.zze(r1, r8)
            com.google.android.gms.internal.ads.zzt r1 = r2.zzg()
            goto L_0x0656
        L_0x0669:
            boolean r13 = r8.zzo()
            r8.zzl()
            if (r13 == 0) goto L_0x0686
            r13 = 8
            int r15 = r8.zzd(r13)
            r13 = 127(0x7f, float:1.78E-43)
            if (r15 <= r13) goto L_0x0686
            java.lang.String r8 = "Excessive obu_size"
            com.google.android.gms.internal.ads.zzfk.zze(r1, r8)
            com.google.android.gms.internal.ads.zzt r1 = r2.zzg()
            goto L_0x0656
        L_0x0686:
            r13 = 3
            int r15 = r8.zzd(r13)
            r8.zzl()
            boolean r13 = r8.zzo()
            if (r13 == 0) goto L_0x069e
            java.lang.String r8 = "Unsupported reduced_still_picture_header"
            com.google.android.gms.internal.ads.zzfk.zze(r1, r8)
            com.google.android.gms.internal.ads.zzt r1 = r2.zzg()
            goto L_0x0656
        L_0x069e:
            boolean r13 = r8.zzo()
            if (r13 == 0) goto L_0x06ae
            java.lang.String r8 = "Unsupported timing_info_present_flag"
            com.google.android.gms.internal.ads.zzfk.zze(r1, r8)
            com.google.android.gms.internal.ads.zzt r1 = r2.zzg()
            goto L_0x0656
        L_0x06ae:
            boolean r13 = r8.zzo()
            if (r13 == 0) goto L_0x06be
            java.lang.String r8 = "Unsupported initial_display_delay_present_flag"
            com.google.android.gms.internal.ads.zzfk.zze(r1, r8)
            com.google.android.gms.internal.ads.zzt r1 = r2.zzg()
            goto L_0x0656
        L_0x06be:
            r1 = 5
            int r13 = r8.zzd(r1)
            r11 = r31
        L_0x06c5:
            if (r11 > r13) goto L_0x06db
            r12 = 12
            r8.zzm(r12)
            int r12 = r8.zzd(r1)
            r1 = 7
            if (r12 <= r1) goto L_0x06d6
            r8.zzl()
        L_0x06d6:
            int r11 = r11 + 1
            r1 = 5
            r12 = 4
            goto L_0x06c5
        L_0x06db:
            r1 = r12
            int r11 = r8.zzd(r1)
            int r12 = r8.zzd(r1)
            r13 = 1
            int r11 = r11 + r13
            r8.zzm(r11)
            int r12 = r12 + r13
            r8.zzm(r12)
            boolean r11 = r8.zzo()
            if (r11 == 0) goto L_0x06f7
            r11 = 7
            r8.zzm(r11)
        L_0x06f7:
            r11 = 7
            r8.zzm(r11)
            boolean r11 = r8.zzo()
            if (r11 == 0) goto L_0x0705
            r12 = 2
            r8.zzm(r12)
        L_0x0705:
            boolean r12 = r8.zzo()
            if (r12 == 0) goto L_0x070d
            r12 = 1
            goto L_0x0714
        L_0x070d:
            r12 = 1
            int r13 = r8.zzd(r12)
            if (r13 <= 0) goto L_0x071d
        L_0x0714:
            boolean r13 = r8.zzo()
            if (r13 != 0) goto L_0x071d
            r8.zzm(r12)
        L_0x071d:
            if (r11 == 0) goto L_0x0724
            r11 = 3
            r8.zzm(r11)
            goto L_0x0725
        L_0x0724:
            r11 = 3
        L_0x0725:
            r8.zzm(r11)
            boolean r12 = r8.zzo()
            r13 = 2
            if (r15 != r13) goto L_0x0735
            if (r12 == 0) goto L_0x073b
            r8.zzl()
            goto L_0x073b
        L_0x0735:
            r12 = 1
            if (r15 != r12) goto L_0x073b
        L_0x0738:
            r12 = r31
            goto L_0x0742
        L_0x073b:
            boolean r12 = r8.zzo()
            if (r12 == 0) goto L_0x0738
            r12 = 1
        L_0x0742:
            boolean r13 = r8.zzo()
            if (r13 == 0) goto L_0x0785
            r13 = 8
            int r15 = r8.zzd(r13)
            int r1 = r8.zzd(r13)
            int r29 = r8.zzd(r13)
            if (r12 != 0) goto L_0x0769
            r12 = 1
            if (r15 != r12) goto L_0x076a
            r13 = 13
            if (r1 != r13) goto L_0x0767
            if (r29 != 0) goto L_0x0764
            r8 = r12
            r15 = r8
            goto L_0x076f
        L_0x0764:
            r15 = r12
            r1 = r13
            goto L_0x076a
        L_0x0767:
            r15 = r12
            goto L_0x076a
        L_0x0769:
            r12 = 1
        L_0x076a:
            int r8 = r8.zzd(r12)
            r13 = r1
        L_0x076f:
            int r1 = com.google.android.gms.internal.ads.zzt.zza(r15)
            r2.zzc(r1)
            if (r8 != r12) goto L_0x077a
            r1 = 1
            goto L_0x077b
        L_0x077a:
            r1 = 2
        L_0x077b:
            r2.zzb(r1)
            int r1 = com.google.android.gms.internal.ads.zzt.zzb(r13)
            r2.zzd(r1)
        L_0x0785:
            com.google.android.gms.internal.ads.zzt r1 = r2.zzg()
        L_0x0789:
            int r2 = r1.zzf
            int r8 = r1.zze
            int r12 = r1.zzd
            int r13 = r1.zzi
            int r1 = r1.zzh
            java.lang.String r15 = "video/av01"
            r29 = r1
            r49 = r4
            r48 = r5
            r30 = r8
            r4 = r11
            r28 = r13
            r46 = r14
            r5 = -1
            r42 = 4
            r11 = r2
            r2 = r15
            goto L_0x099a
        L_0x07a9:
            r42 = 4
            r15 = 1668050025(0x636c6c69, float:4.3612434E21)
            if (r13 != r15) goto L_0x07d3
            if (r6 != 0) goto L_0x07b6
            java.nio.ByteBuffer r6 = zzl()
        L_0x07b6:
            r1 = 21
            r6.position(r1)
            short r1 = r0.zzD()
            r6.putShort(r1)
            short r1 = r0.zzD()
            r6.putShort(r1)
            r49 = r4
            r48 = r5
            r46 = r14
        L_0x07cf:
            r4 = 3
        L_0x07d0:
            r5 = -1
            goto L_0x099a
        L_0x07d3:
            r15 = 1835295606(0x6d646376, float:4.4176764E27)
            if (r13 != r15) goto L_0x0843
            if (r6 != 0) goto L_0x07de
            java.nio.ByteBuffer r6 = zzl()
        L_0x07de:
            short r1 = r0.zzD()
            short r8 = r0.zzD()
            short r13 = r0.zzD()
            short r15 = r0.zzD()
            r46 = r14
            short r14 = r0.zzD()
            r47 = r7
            short r7 = r0.zzD()
            r48 = r5
            short r5 = r0.zzD()
            r49 = r4
            short r4 = r0.zzD()
            long r50 = r0.zzu()
            long r52 = r0.zzu()
            r54 = r9
            r9 = 1
            r6.position(r9)
            r6.putShort(r14)
            r6.putShort(r7)
            r6.putShort(r1)
            r6.putShort(r8)
            r6.putShort(r13)
            r6.putShort(r15)
            r6.putShort(r5)
            r6.putShort(r4)
            r4 = 10000(0x2710, double:4.9407E-320)
            long r4 = r50 / r4
            int r1 = (int) r4
            short r1 = (short) r1
            r6.putShort(r1)
            r4 = 10000(0x2710, double:4.9407E-320)
            long r4 = r52 / r4
            int r1 = (int) r4
            short r1 = (short) r1
            r6.putShort(r1)
        L_0x083e:
            r7 = r47
            r9 = r54
            goto L_0x07cf
        L_0x0843:
            r49 = r4
            r48 = r5
            r47 = r7
            r54 = r9
            r46 = r14
            r4 = 1681012275(0x64323633, float:1.3149704E22)
            if (r13 != r4) goto L_0x0860
            if (r2 != 0) goto L_0x0856
            r1 = 1
            goto L_0x0858
        L_0x0856:
            r1 = r31
        L_0x0858:
            r4 = 0
            com.google.android.gms.internal.ads.zzady.zzb(r1, r4)
            java.lang.String r1 = "video/3gpp"
            r2 = r1
            goto L_0x083e
        L_0x0860:
            r4 = 0
            r5 = 1702061171(0x65736473, float:7.183675E22)
            if (r13 != r5) goto L_0x0889
            if (r2 != 0) goto L_0x086a
            r1 = 1
            goto L_0x086c
        L_0x086a:
            r1 = r31
        L_0x086c:
            com.google.android.gms.internal.ads.zzady.zzb(r1, r4)
            com.google.android.gms.internal.ads.zzajq r1 = zzj(r0, r8)
            java.lang.String r2 = r1.zza
            byte[] r4 = r1.zzb
            if (r4 == 0) goto L_0x0886
            com.google.android.gms.internal.ads.zzgbc r4 = com.google.android.gms.internal.ads.zzgbc.zzn(r4)
            r17 = r1
            r36 = r4
            goto L_0x083e
        L_0x0886:
            r17 = r1
            goto L_0x083e
        L_0x0889:
            r4 = 1885434736(0x70617370, float:2.7909473E29)
            if (r13 != r4) goto L_0x08a7
            int r8 = r8 + 8
            r0.zzK(r8)
            int r1 = r0.zzp()
            int r4 = r0.zzp()
            float r1 = (float) r1
            float r4 = (float) r4
            float r1 = r1 / r4
            r7 = r1
            r9 = r54
            r4 = 3
            r5 = -1
            r25 = 1
            goto L_0x099a
        L_0x08a7:
            r4 = 1937126244(0x73763364, float:1.9506033E31)
            if (r13 != r4) goto L_0x08db
            int r1 = r8 + 8
        L_0x08ae:
            int r4 = r1 - r8
            if (r4 >= r10) goto L_0x08d1
            r0.zzK(r1)
            int r4 = r0.zzg()
            int r4 = r4 + r1
            int r5 = r0.zzg()
            r7 = 1886547818(0x70726f6a, float:3.0012025E29)
            if (r5 != r7) goto L_0x08cf
            byte[] r5 = r0.zzM()
            byte[] r1 = java.util.Arrays.copyOfRange(r5, r1, r4)
            r39 = r1
            goto L_0x083e
        L_0x08cf:
            r1 = r4
            goto L_0x08ae
        L_0x08d1:
            r7 = r47
            r9 = r54
            r4 = 3
            r5 = -1
            r39 = 0
            goto L_0x099a
        L_0x08db:
            r4 = 1936995172(0x73743364, float:1.9347576E31)
            if (r13 != r4) goto L_0x0917
            int r1 = r0.zzm()
            r4 = 3
            r0.zzL(r4)
            if (r1 != 0) goto L_0x0976
            int r1 = r0.zzm()
            if (r1 == 0) goto L_0x090f
            r5 = 1
            if (r1 == r5) goto L_0x0906
            r5 = 2
            if (r1 == r5) goto L_0x08fd
            if (r1 == r4) goto L_0x08fa
            goto L_0x0976
        L_0x08fa:
            r37 = r4
            goto L_0x0911
        L_0x08fd:
            r7 = r47
            r9 = r54
            r5 = -1
            r37 = 2
            goto L_0x099a
        L_0x0906:
            r7 = r47
            r9 = r54
            r5 = -1
            r37 = 1
            goto L_0x099a
        L_0x090f:
            r37 = r31
        L_0x0911:
            r7 = r47
            r9 = r54
            goto L_0x07d0
        L_0x0917:
            r4 = 3
            r5 = 1668246642(0x636f6c72, float:4.4165861E21)
            if (r13 != r5) goto L_0x0976
            r5 = -1
            if (r12 != r5) goto L_0x0996
            if (r11 != r5) goto L_0x0974
            int r7 = r0.zzg()
            r8 = 1852009592(0x6e636c78, float:1.7596057E28)
            if (r7 == r8) goto L_0x0941
            r8 = 1852009571(0x6e636c63, float:1.7596032E28)
            if (r7 != r8) goto L_0x0931
            goto L_0x0941
        L_0x0931:
            java.lang.String r8 = "Unsupported color type: "
            java.lang.String r7 = com.google.android.gms.internal.ads.zzajo.zzf(r7)
            java.lang.String r7 = r8.concat(r7)
            com.google.android.gms.internal.ads.zzfk.zzf(r1, r7)
            r11 = r5
            r12 = r11
            goto L_0x0996
        L_0x0941:
            int r1 = r0.zzq()
            int r7 = r0.zzq()
            r8 = 2
            r0.zzL(r8)
            r8 = 19
            if (r10 != r8) goto L_0x095f
            int r8 = r0.zzm()
            r8 = r8 & 128(0x80, float:1.794E-43)
            if (r8 == 0) goto L_0x095d
            r10 = 19
            r8 = 1
            goto L_0x0961
        L_0x095d:
            r10 = 19
        L_0x095f:
            r8 = r31
        L_0x0961:
            int r1 = com.google.android.gms.internal.ads.zzt.zza(r1)
            r9 = 1
            if (r9 == r8) goto L_0x096a
            r8 = 2
            goto L_0x096b
        L_0x096a:
            r8 = 1
        L_0x096b:
            int r7 = com.google.android.gms.internal.ads.zzt.zzb(r7)
            r12 = r1
            r11 = r7
            r30 = r8
            goto L_0x0996
        L_0x0974:
            r12 = r5
            goto L_0x0996
        L_0x0976:
            r5 = -1
            goto L_0x0996
        L_0x0978:
            r45 = r1
            r49 = r4
            r48 = r5
            r47 = r7
            r54 = r9
            r46 = r14
            r4 = 3
            r5 = -1
            r42 = 4
            com.google.android.gms.internal.ads.zzado r1 = com.google.android.gms.internal.ads.zzado.zza(r0)
            if (r1 == 0) goto L_0x0996
            java.lang.String r1 = r1.zza
            java.lang.String r2 = "video/dolby-vision"
            r9 = r1
            r7 = r47
            goto L_0x099a
        L_0x0996:
            r7 = r47
            r9 = r54
        L_0x099a:
            int r3 = r3 + r10
            r13 = r38
            r10 = r39
            r15 = r40
            r1 = r45
            r14 = r46
            r5 = r48
            r4 = r49
            goto L_0x049a
        L_0x09ab:
            r49 = r4
            r48 = r5
            r47 = r7
            r54 = r9
            r39 = r10
        L_0x09b5:
            r38 = r13
            r46 = r14
            r40 = r15
            r4 = 3
            r5 = -1
            r42 = 4
            if (r2 != 0) goto L_0x09c9
            r2 = r41
            r3 = r43
            r6 = r46
            goto L_0x0a4f
        L_0x09c9:
            com.google.android.gms.internal.ads.zzal r1 = new com.google.android.gms.internal.ads.zzal
            r1.<init>()
            r3 = r43
            r1.zzJ(r3)
            r1.zzX(r2)
            r9 = r54
            r1.zzz(r9)
            r2 = r49
            r1.zzac(r2)
            r2 = r48
            r1.zzI(r2)
            r8 = r47
            r1.zzT(r8)
            r2 = r41
            r1.zzW(r2)
            r7 = r39
            r1.zzU(r7)
            r7 = r37
            r1.zzaa(r7)
            r7 = r36
            r1.zzL(r7)
            r7 = r35
            r1.zzE(r7)
            com.google.android.gms.internal.ads.zzr r7 = new com.google.android.gms.internal.ads.zzr
            r7.<init>()
            r7.zzc(r12)
            r8 = r30
            r7.zzb(r8)
            r7.zzd(r11)
            if (r6 == 0) goto L_0x0a1a
            byte[] r6 = r6.array()
            goto L_0x0a1b
        L_0x0a1a:
            r6 = 0
        L_0x0a1b:
            r7.zze(r6)
            r6 = r29
            r7.zzf(r6)
            r6 = r28
            r7.zza(r6)
            com.google.android.gms.internal.ads.zzt r6 = r7.zzg()
            r1.zzA(r6)
            if (r17 == 0) goto L_0x0a47
            long r6 = r17.zzc
            int r6 = com.google.android.gms.internal.ads.zzgea.zze(r6)
            r1.zzx(r6)
            long r6 = r17.zzd
            int r6 = com.google.android.gms.internal.ads.zzgea.zze(r6)
            r1.zzS(r6)
        L_0x0a47:
            com.google.android.gms.internal.ads.zzan r1 = r1.zzad()
            r6 = r46
            r6.zzb = r1
        L_0x0a4f:
            int r13 = r38 + r40
            r0.zzK(r13)
            int r12 = r20 + 1
            r11 = r60
            r4 = r2
            r7 = r3
            r14 = r6
            r1 = r16
            r5 = r18
            r13 = r19
            r9 = r21
            r3 = r27
            r15 = r34
            r8 = r44
            r2 = 12
            r6 = 8
            goto L_0x0201
        L_0x0a6f:
            r18 = r5
            r44 = r8
            r21 = r9
            r19 = r13
            r6 = r14
            r34 = r15
            r31 = 0
            if (r61 != 0) goto L_0x0af3
            r0 = 1701082227(0x65647473, float:6.742798E22)
            r1 = r34
            com.google.android.gms.internal.ads.zzajm r0 = r1.zza(r0)
            if (r0 == 0) goto L_0x0af5
            r2 = 1701606260(0x656c7374, float:6.9788014E22)
            com.google.android.gms.internal.ads.zzajn r0 = r0.zzb(r2)
            if (r0 != 0) goto L_0x0a94
            r4 = 0
            goto L_0x0ae4
        L_0x0a94:
            com.google.android.gms.internal.ads.zzfu r0 = r0.zza
            r2 = 8
            r0.zzK(r2)
            int r2 = r0.zzg()
            int r2 = com.google.android.gms.internal.ads.zzajo.zze(r2)
            int r3 = r0.zzp()
            long[] r4 = new long[r3]
            long[] r5 = new long[r3]
            r7 = r31
        L_0x0aad:
            if (r7 >= r3) goto L_0x0ae0
            r8 = 1
            if (r2 != r8) goto L_0x0ab7
            long r9 = r0.zzv()
            goto L_0x0abb
        L_0x0ab7:
            long r9 = r0.zzu()
        L_0x0abb:
            r4[r7] = r9
            if (r2 != r8) goto L_0x0ac4
            long r9 = r0.zzt()
            goto L_0x0ac9
        L_0x0ac4:
            int r9 = r0.zzg()
            long r9 = (long) r9
        L_0x0ac9:
            r5[r7] = r9
            short r9 = r0.zzD()
            if (r9 != r8) goto L_0x0ad8
            r9 = 2
            r0.zzL(r9)
            int r7 = r7 + 1
            goto L_0x0aad
        L_0x0ad8:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Unsupported media rate."
            r0.<init>(r1)
            throw r0
        L_0x0ae0:
            android.util.Pair r4 = android.util.Pair.create(r4, r5)
        L_0x0ae4:
            if (r4 == 0) goto L_0x0af5
            java.lang.Object r0 = r4.first
            long[] r0 = (long[]) r0
            java.lang.Object r2 = r4.second
            long[] r2 = (long[]) r2
            r29 = r0
            r30 = r2
            goto L_0x0af9
        L_0x0af3:
            r1 = r34
        L_0x0af5:
            r29 = 0
            r30 = 0
        L_0x0af9:
            com.google.android.gms.internal.ads.zzan r0 = r6.zzb
            if (r0 != 0) goto L_0x0b01
            r0 = r63
            r4 = 0
            goto L_0x0b28
        L_0x0b01:
            com.google.android.gms.internal.ads.zzakp r4 = new com.google.android.gms.internal.ads.zzakp
            int r17 = r19.zza
            r0 = r44
            java.lang.Object r0 = r0.first
            java.lang.Long r0 = (java.lang.Long) r0
            long r19 = r0.longValue()
            com.google.android.gms.internal.ads.zzan r0 = r6.zzb
            int r2 = r6.zzd
            com.google.android.gms.internal.ads.zzakq[] r3 = r6.zza
            int r5 = r6.zzc
            r16 = r4
            r25 = r0
            r26 = r2
            r27 = r3
            r28 = r5
            r16.<init>(r17, r18, r19, r21, r23, r25, r26, r27, r28, r29, r30)
            r0 = r63
        L_0x0b28:
            java.lang.Object r2 = r0.apply(r4)
            com.google.android.gms.internal.ads.zzakp r2 = (com.google.android.gms.internal.ads.zzakp) r2
            if (r2 == 0) goto L_0x0b5a
            r3 = 1835297121(0x6d646961, float:4.4181236E27)
            com.google.android.gms.internal.ads.zzajm r1 = r1.zza(r3)
            r1.getClass()
            r3 = 1835626086(0x6d696e66, float:4.515217E27)
            com.google.android.gms.internal.ads.zzajm r1 = r1.zza(r3)
            r1.getClass()
            r3 = 1937007212(0x7374626c, float:1.9362132E31)
            com.google.android.gms.internal.ads.zzajm r1 = r1.zza(r3)
            r1.getClass()
            r3 = r57
            com.google.android.gms.internal.ads.zzaks r1 = zzk(r2, r1, r3)
            r2 = r33
            r2.add(r1)
            goto L_0x0b5e
        L_0x0b5a:
            r3 = r57
            r2 = r33
        L_0x0b5e:
            int r14 = r32 + 1
            r0 = r56
            r11 = r60
            r12 = r2
            goto L_0x000a
        L_0x0b67:
            java.lang.String r0 = "Malformed sample table (stbl) missing sample description (stsd)"
            r1 = 0
            com.google.android.gms.internal.ads.zzch r0 = com.google.android.gms.internal.ads.zzch.zza(r0, r1)
            throw r0
        L_0x0b6f:
            r2 = r12
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzajw.zzd(com.google.android.gms.internal.ads.zzajm, com.google.android.gms.internal.ads.zzaej, long, com.google.android.gms.internal.ads.zzae, boolean, boolean, com.google.android.gms.internal.ads.zzfxu):java.util.List");
    }

    public static void zze(zzfu zzfu) {
        int zzd = zzfu.zzd();
        zzfu.zzL(4);
        if (zzfu.zzg() != 1751411826) {
            zzd += 4;
        }
        zzfu.zzK(zzd);
    }

    private static int zzf(zzfu zzfu) {
        int zzm = zzfu.zzm();
        int i = zzm & 127;
        while ((zzm & 128) == 128) {
            zzm = zzfu.zzm();
            i = (i << 7) | (zzm & 127);
        }
        return i;
    }

    private static int zzg(zzfu zzfu) {
        zzfu.zzK(16);
        return zzfu.zzg();
    }

    private static Pair zzh(zzfu zzfu, int i, int i2) throws zzch {
        Pair pair;
        Integer num;
        zzakq zzakq;
        int i3;
        int i4;
        byte[] bArr;
        zzfu zzfu2 = zzfu;
        int zzd = zzfu.zzd();
        while (zzd - i < i2) {
            zzfu2.zzK(zzd);
            int zzg = zzfu.zzg();
            boolean z = true;
            zzady.zzb(zzg > 0, "childAtomSize must be positive");
            if (zzfu.zzg() == 1936289382) {
                int i5 = zzd + 8;
                int i6 = 0;
                int i7 = -1;
                String str = null;
                Integer num2 = null;
                while (i5 - zzd < zzg) {
                    zzfu2.zzK(i5);
                    int zzg2 = zzfu.zzg();
                    int zzg3 = zzfu.zzg();
                    if (zzg3 == 1718775137) {
                        num2 = Integer.valueOf(zzfu.zzg());
                    } else if (zzg3 == 1935894637) {
                        zzfu2.zzL(4);
                        str = zzfu2.zzA(4, zzfxs.zzc);
                    } else if (zzg3 == 1935894633) {
                        i7 = i5;
                        i6 = zzg2;
                    }
                    i5 += zzg2;
                }
                if (C.CENC_TYPE_cenc.equals(str) || C.CENC_TYPE_cbc1.equals(str) || C.CENC_TYPE_cens.equals(str) || C.CENC_TYPE_cbcs.equals(str)) {
                    zzady.zzb(num2 != null, "frma atom is mandatory");
                    zzady.zzb(i7 != -1, "schi atom is mandatory");
                    int i8 = i7 + 8;
                    while (true) {
                        if (i8 - i7 >= i6) {
                            num = num2;
                            zzakq = null;
                            break;
                        }
                        zzfu2.zzK(i8);
                        int zzg4 = zzfu.zzg();
                        if (zzfu.zzg() == 1952804451) {
                            int zze = zzajo.zze(zzfu.zzg());
                            zzfu2.zzL(1);
                            if (zze == 0) {
                                zzfu2.zzL(1);
                                i4 = 0;
                                i3 = 0;
                            } else {
                                int zzm = zzfu.zzm();
                                i4 = zzm & 15;
                                i3 = (zzm & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                            }
                            boolean z2 = zzfu.zzm() == 1;
                            int zzm2 = zzfu.zzm();
                            byte[] bArr2 = new byte[16];
                            zzfu2.zzG(bArr2, 0, 16);
                            if (!z2 || zzm2 != 0) {
                                bArr = null;
                            } else {
                                int zzm3 = zzfu.zzm();
                                byte[] bArr3 = new byte[zzm3];
                                zzfu2.zzG(bArr3, 0, zzm3);
                                bArr = bArr3;
                            }
                            num = num2;
                            zzakq = new zzakq(z2, str, zzm2, bArr2, i3, i4, bArr);
                        } else {
                            Integer num3 = num2;
                            i8 += zzg4;
                        }
                    }
                    if (zzakq == null) {
                        z = false;
                    }
                    zzady.zzb(z, "tenc atom is mandatory");
                    int i9 = zzgd.zza;
                    pair = Pair.create(num, zzakq);
                } else {
                    pair = null;
                }
                if (pair != null) {
                    return pair;
                }
            }
            zzd += zzg;
        }
        return null;
    }

    private static zzcd zzi(zzfu zzfu) {
        short zzD = zzfu.zzD();
        zzfu.zzL(2);
        String zzA = zzfu.zzA(zzD, zzfxs.zzc);
        int max = Math.max(zzA.lastIndexOf(43), zzA.lastIndexOf(45));
        try {
            return new zzcd(C.TIME_UNSET, new zzgk(Float.parseFloat(zzA.substring(0, max)), Float.parseFloat(zzA.substring(max, zzA.length() - 1))));
        } catch (IndexOutOfBoundsException | NumberFormatException unused) {
            return null;
        }
    }

    private static zzajq zzj(zzfu zzfu, int i) {
        zzfu.zzK(i + 12);
        zzfu.zzL(1);
        zzf(zzfu);
        zzfu.zzL(2);
        int zzm = zzfu.zzm();
        if ((zzm & 128) != 0) {
            zzfu.zzL(2);
        }
        if ((zzm & 64) != 0) {
            zzfu.zzL(zzfu.zzm());
        }
        if ((zzm & 32) != 0) {
            zzfu.zzL(2);
        }
        zzfu.zzL(1);
        zzf(zzfu);
        String zzd = zzcg.zzd(zzfu.zzm());
        if (MimeTypes.AUDIO_MPEG.equals(zzd) || MimeTypes.AUDIO_DTS.equals(zzd) || MimeTypes.AUDIO_DTS_HD.equals(zzd)) {
            return new zzajq(zzd, (byte[]) null, -1, -1);
        }
        zzfu.zzL(4);
        long zzu = zzfu.zzu();
        long zzu2 = zzfu.zzu();
        zzfu.zzL(1);
        int zzf = zzf(zzfu);
        byte[] bArr = new byte[zzf];
        zzfu.zzG(bArr, 0, zzf);
        return new zzajq(zzd, bArr, zzu2 <= 0 ? -1 : zzu2, zzu > 0 ? zzu : -1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x0271 A[LOOP:7: B:101:0x0271->B:105:0x027b, LOOP_START, PHI: r16 
      PHI: (r16v11 int) = (r16v7 int), (r16v12 int) binds: [B:100:0x026f, B:105:0x027b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x02d0  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0319  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x031c  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0341  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0350  */
    /* JADX WARNING: Removed duplicated region for block: B:239:0x025f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x018e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.ads.zzaks zzk(com.google.android.gms.internal.ads.zzakp r40, com.google.android.gms.internal.ads.zzajm r41, com.google.android.gms.internal.ads.zzaej r42) throws com.google.android.gms.internal.ads.zzch {
        /*
            r1 = r40
            r0 = r41
            r2 = r42
            r3 = 1937011578(0x7374737a, float:1.936741E31)
            com.google.android.gms.internal.ads.zzajn r3 = r0.zzb(r3)
            r4 = 0
            if (r3 == 0) goto L_0x0018
            com.google.android.gms.internal.ads.zzan r5 = r1.zzf
            com.google.android.gms.internal.ads.zzajt r6 = new com.google.android.gms.internal.ads.zzajt
            r6.<init>(r3, r5)
            goto L_0x0026
        L_0x0018:
            r3 = 1937013298(0x73747a32, float:1.9369489E31)
            com.google.android.gms.internal.ads.zzajn r3 = r0.zzb(r3)
            if (r3 == 0) goto L_0x05d8
            com.google.android.gms.internal.ads.zzaju r6 = new com.google.android.gms.internal.ads.zzaju
            r6.<init>(r3)
        L_0x0026:
            int r3 = r6.zzb()
            r5 = 0
            if (r3 != 0) goto L_0x0044
            com.google.android.gms.internal.ads.zzaks r9 = new com.google.android.gms.internal.ads.zzaks
            long[] r2 = new long[r5]
            int[] r3 = new int[r5]
            long[] r6 = new long[r5]
            int[] r7 = new int[r5]
            r10 = 0
            r4 = 0
            r0 = r9
            r1 = r40
            r5 = r6
            r6 = r7
            r7 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r9
        L_0x0044:
            r7 = 1937007471(0x7374636f, float:1.9362445E31)
            com.google.android.gms.internal.ads.zzajn r7 = r0.zzb(r7)
            if (r7 != 0) goto L_0x0059
            r7 = 1668232756(0x636f3634, float:4.4126776E21)
            com.google.android.gms.internal.ads.zzajn r7 = r0.zzb(r7)
            r7.getClass()
            r9 = 1
            goto L_0x005a
        L_0x0059:
            r9 = r5
        L_0x005a:
            r10 = 1937011555(0x73747363, float:1.9367382E31)
            com.google.android.gms.internal.ads.zzajn r10 = r0.zzb(r10)
            r10.getClass()
            r11 = 1937011827(0x73747473, float:1.9367711E31)
            com.google.android.gms.internal.ads.zzajn r11 = r0.zzb(r11)
            r11.getClass()
            r12 = 1937011571(0x73747373, float:1.9367401E31)
            com.google.android.gms.internal.ads.zzajn r12 = r0.zzb(r12)
            if (r12 == 0) goto L_0x007a
            com.google.android.gms.internal.ads.zzfu r12 = r12.zza
            goto L_0x007b
        L_0x007a:
            r12 = r4
        L_0x007b:
            r13 = 1668576371(0x63747473, float:4.5093966E21)
            com.google.android.gms.internal.ads.zzajn r0 = r0.zzb(r13)
            if (r0 == 0) goto L_0x0087
            com.google.android.gms.internal.ads.zzfu r0 = r0.zza
            goto L_0x0088
        L_0x0087:
            r0 = r4
        L_0x0088:
            com.google.android.gms.internal.ads.zzfu r11 = r11.zza
            com.google.android.gms.internal.ads.zzfu r10 = r10.zza
            com.google.android.gms.internal.ads.zzfu r7 = r7.zza
            com.google.android.gms.internal.ads.zzajp r13 = new com.google.android.gms.internal.ads.zzajp
            r13.<init>(r10, r7, r9)
            r7 = 12
            r11.zzK(r7)
            int r9 = r11.zzp()
            r10 = -1
            int r9 = r9 + r10
            int r14 = r11.zzp()
            int r15 = r11.zzp()
            if (r0 == 0) goto L_0x00b0
            r0.zzK(r7)
            int r16 = r0.zzp()
            goto L_0x00b2
        L_0x00b0:
            r16 = r5
        L_0x00b2:
            if (r12 == 0) goto L_0x00c5
            r12.zzK(r7)
            int r7 = r12.zzp()
            if (r7 <= 0) goto L_0x00c3
            int r4 = r12.zzp()
            int r4 = r4 + r10
            goto L_0x00c7
        L_0x00c3:
            r12 = r4
            goto L_0x00c6
        L_0x00c5:
            r7 = r5
        L_0x00c6:
            r4 = r10
        L_0x00c7:
            int r5 = r6.zza()
            com.google.android.gms.internal.ads.zzan r8 = r1.zzf
            java.lang.String r8 = r8.zzn
            if (r5 == r10) goto L_0x0173
            java.lang.String r1 = "audio/raw"
            boolean r1 = r1.equals(r8)
            if (r1 != 0) goto L_0x00e9
            java.lang.String r1 = "audio/g711-mlaw"
            boolean r1 = r1.equals(r8)
            if (r1 != 0) goto L_0x00e9
            java.lang.String r1 = "audio/g711-alaw"
            boolean r1 = r1.equals(r8)
            if (r1 == 0) goto L_0x0173
        L_0x00e9:
            if (r9 != 0) goto L_0x0173
            if (r16 != 0) goto L_0x0172
            if (r7 != 0) goto L_0x0172
            int r0 = r13.zza
            long[] r1 = new long[r0]
            int[] r2 = new int[r0]
        L_0x00f5:
            boolean r4 = r13.zza()
            if (r4 == 0) goto L_0x0106
            int r4 = r13.zzb
            long r6 = r13.zzd
            r1[r4] = r6
            int r6 = r13.zzc
            r2[r4] = r6
            goto L_0x00f5
        L_0x0106:
            long r6 = (long) r15
            r4 = 8192(0x2000, float:1.14794E-41)
            int r4 = r4 / r5
            r8 = 0
            r9 = 0
        L_0x010c:
            if (r8 >= r0) goto L_0x0119
            r11 = r2[r8]
            int r12 = com.google.android.gms.internal.ads.zzgd.zza
            int r11 = r11 + r4
            int r11 = r11 + r10
            int r11 = r11 / r4
            int r9 = r9 + r11
            int r8 = r8 + 1
            goto L_0x010c
        L_0x0119:
            long[] r8 = new long[r9]
            int[] r10 = new int[r9]
            long[] r11 = new long[r9]
            int[] r9 = new int[r9]
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x0125:
            if (r12 >= r0) goto L_0x0165
            r16 = r2[r12]
            r21 = r1[r12]
            r39 = r16
            r16 = r0
            r0 = r39
        L_0x0131:
            if (r0 <= 0) goto L_0x015c
            int r23 = java.lang.Math.min(r4, r0)
            r8[r15] = r21
            r41 = r1
            int r1 = r5 * r23
            r10[r15] = r1
            int r14 = java.lang.Math.max(r14, r1)
            r24 = r2
            long r1 = (long) r13
            long r1 = r1 * r6
            r11[r15] = r1
            r1 = 1
            r9[r15] = r1
            r1 = r10[r15]
            long r1 = (long) r1
            long r21 = r21 + r1
            int r13 = r13 + r23
            int r0 = r0 - r23
            int r15 = r15 + 1
            r1 = r41
            r2 = r24
            goto L_0x0131
        L_0x015c:
            r41 = r1
            r24 = r2
            int r12 = r12 + 1
            r0 = r16
            goto L_0x0125
        L_0x0165:
            long r0 = (long) r13
            long r6 = r6 * r0
            r0 = r3
            r15 = r6
            r2 = r8
            r6 = r9
            r3 = r10
            r5 = r11
            r4 = r14
            r7 = r40
            goto L_0x0331
        L_0x0172:
            r9 = 0
        L_0x0173:
            long[] r1 = new long[r3]
            int[] r2 = new int[r3]
            long[] r5 = new long[r3]
            int[] r8 = new int[r3]
            r22 = r9
            r9 = 0
            r21 = 0
            r23 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r29 = 0
        L_0x018a:
            java.lang.String r10 = "AtomParsers"
            if (r9 >= r3) goto L_0x025f
            r24 = 1
        L_0x0190:
            if (r21 != 0) goto L_0x01b5
            boolean r24 = r13.zza()
            if (r24 == 0) goto L_0x01ad
            r31 = r14
            r32 = r15
            long r14 = r13.zzd
            r33 = r3
            int r3 = r13.zzc
            r21 = r3
            r27 = r14
            r14 = r31
            r15 = r32
            r3 = r33
            goto L_0x0190
        L_0x01ad:
            r33 = r3
            r31 = r14
            r32 = r15
            r3 = 0
            goto L_0x01bd
        L_0x01b5:
            r33 = r3
            r31 = r14
            r32 = r15
            r3 = r21
        L_0x01bd:
            if (r24 != 0) goto L_0x01db
            java.lang.String r3 = "Unexpected end of chunk data"
            com.google.android.gms.internal.ads.zzfk.zzf(r10, r3)
            long[] r1 = java.util.Arrays.copyOf(r1, r9)
            int[] r2 = java.util.Arrays.copyOf(r2, r9)
            long[] r5 = java.util.Arrays.copyOf(r5, r9)
            int[] r8 = java.util.Arrays.copyOf(r8, r9)
            r3 = r9
            r4 = r25
            r15 = r26
            goto L_0x026c
        L_0x01db:
            if (r0 != 0) goto L_0x01e0
        L_0x01dd:
            r10 = r25
            goto L_0x01f7
        L_0x01e0:
            if (r23 != 0) goto L_0x01f3
            if (r16 <= 0) goto L_0x01ef
            int r16 = r16 + -1
            int r23 = r0.zzp()
            int r25 = r0.zzg()
            goto L_0x01e0
        L_0x01ef:
            r10 = -1
            r23 = 0
            goto L_0x01f4
        L_0x01f3:
            r10 = -1
        L_0x01f4:
            int r23 = r23 + -1
            goto L_0x01dd
        L_0x01f7:
            r1[r9] = r27
            int r14 = r6.zzc()
            r2[r9] = r14
            r15 = r26
            if (r14 <= r15) goto L_0x0206
            r26 = r14
            goto L_0x0208
        L_0x0206:
            r26 = r15
        L_0x0208:
            long r14 = (long) r10
            long r14 = r29 + r14
            r5[r9] = r14
            if (r12 != 0) goto L_0x0211
            r14 = 1
            goto L_0x0212
        L_0x0211:
            r14 = 0
        L_0x0212:
            r8[r9] = r14
            if (r9 != r4) goto L_0x0226
            r14 = 1
            r8[r9] = r14
            int r7 = r7 + -1
            if (r7 <= 0) goto L_0x0226
            r12.getClass()
            int r4 = r12.zzp()
            r14 = -1
            int r4 = r4 + r14
        L_0x0226:
            r21 = r4
            r14 = r5
            r15 = r32
            long r4 = (long) r15
            long r29 = r29 + r4
            int r4 = r31 + -1
            if (r4 != 0) goto L_0x0241
            if (r22 <= 0) goto L_0x0240
            int r4 = r11.zzp()
            int r5 = r11.zzg()
            int r22 = r22 + -1
            r15 = r5
            goto L_0x0241
        L_0x0240:
            r4 = 0
        L_0x0241:
            r5 = r2[r9]
            r24 = r1
            r32 = r2
            long r1 = (long) r5
            long r27 = r27 + r1
            r1 = -1
            int r2 = r3 + -1
            int r9 = r9 + 1
            r25 = r10
            r5 = r14
            r1 = r24
            r3 = r33
            r14 = r4
            r4 = r21
            r21 = r2
            r2 = r32
            goto L_0x018a
        L_0x025f:
            r24 = r1
            r32 = r2
            r33 = r3
            r31 = r14
            r15 = r26
            r14 = r5
            r4 = r25
        L_0x026c:
            long r11 = (long) r4
            long r11 = r29 + r11
            if (r0 == 0) goto L_0x0281
        L_0x0271:
            if (r16 <= 0) goto L_0x0281
            int r4 = r0.zzp()
            if (r4 == 0) goto L_0x027b
            r0 = 0
            goto L_0x0282
        L_0x027b:
            r0.zzg()
            int r16 = r16 + -1
            goto L_0x0271
        L_0x0281:
            r0 = 1
        L_0x0282:
            if (r7 != 0) goto L_0x02d0
            if (r31 != 0) goto L_0x02c1
            if (r21 != 0) goto L_0x02b4
            if (r22 != 0) goto L_0x02ac
            if (r23 != 0) goto L_0x02a3
            if (r0 != 0) goto L_0x0299
            r0 = 0
            r4 = 0
            r6 = 0
            r9 = 0
            r13 = 0
            r14 = 0
            r7 = r40
            r41 = r1
            goto L_0x02de
        L_0x0299:
            r7 = r40
            r41 = r1
            r16 = r2
            r21 = r3
            goto L_0x0328
        L_0x02a3:
            r4 = 0
            r9 = 0
            r13 = 0
            r7 = r40
            r6 = r0
            r41 = r1
            goto L_0x02be
        L_0x02ac:
            r4 = 0
            r9 = 0
            r7 = r40
            r6 = r0
            r41 = r1
            goto L_0x02bc
        L_0x02b4:
            r4 = 0
            r7 = r40
            r6 = r0
            r41 = r1
            r9 = r21
        L_0x02bc:
            r13 = r22
        L_0x02be:
            r14 = r23
            goto L_0x02ce
        L_0x02c1:
            r7 = r40
            r6 = r0
            r41 = r1
            r9 = r21
            r13 = r22
            r14 = r23
            r4 = r31
        L_0x02ce:
            r0 = 0
            goto L_0x02de
        L_0x02d0:
            r6 = r0
            r41 = r1
            r0 = r7
            r9 = r21
            r13 = r22
            r14 = r23
            r4 = r31
            r7 = r40
        L_0x02de:
            int r1 = r7.zza
            r16 = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r21 = r3
            java.lang.String r3 = "Inconsistent stbl box for track "
            r2.<init>(r3)
            r2.append(r1)
            java.lang.String r1 = ": remainingSynchronizationSamples "
            r2.append(r1)
            r2.append(r0)
            java.lang.String r0 = ", remainingSamplesAtTimestampDelta "
            r2.append(r0)
            r2.append(r4)
            java.lang.String r0 = ", remainingSamplesInChunk "
            r2.append(r0)
            r2.append(r9)
            java.lang.String r0 = ", remainingTimestampDeltaChanges "
            r2.append(r0)
            r2.append(r13)
            java.lang.String r0 = ", remainingSamplesAtTimestampOffset "
            r2.append(r0)
            r2.append(r14)
            r0 = 1
            if (r0 == r6) goto L_0x031c
            java.lang.String r0 = ", ctts invalid"
            goto L_0x031e
        L_0x031c:
            java.lang.String r0 = ""
        L_0x031e:
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r10, r0)
        L_0x0328:
            r2 = r41
            r6 = r8
            r4 = r15
            r3 = r16
            r0 = r21
            r15 = r11
        L_0x0331:
            long r12 = r7.zzc
            java.math.RoundingMode r14 = java.math.RoundingMode.FLOOR
            r10 = 1000000(0xf4240, double:4.940656E-318)
            r8 = r15
            long r8 = com.google.android.gms.internal.ads.zzgd.zzt(r8, r10, r12, r14)
            long[] r1 = r7.zzh
            if (r1 != 0) goto L_0x0350
            long r0 = r7.zzc
            com.google.android.gms.internal.ads.zzgd.zzF(r5, r10, r0)
            com.google.android.gms.internal.ads.zzaks r10 = new com.google.android.gms.internal.ads.zzaks
            r0 = r10
            r1 = r40
            r7 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r10
        L_0x0350:
            int r8 = r1.length
            r9 = 1
            if (r8 != r9) goto L_0x040d
            int r8 = r7.zzb
            if (r8 != r9) goto L_0x040d
            int r8 = r5.length
            r9 = 2
            if (r8 < r9) goto L_0x040d
            long[] r9 = r7.zzi
            r9.getClass()
            r12 = 0
            r13 = r9[r12]
            r21 = r1[r12]
            long r10 = r7.zzc
            r9 = r0
            long r0 = r7.zzd
            java.math.RoundingMode r27 = java.math.RoundingMode.FLOOR
            r23 = r10
            r25 = r0
            long r0 = com.google.android.gms.internal.ads.zzgd.zzt(r21, r23, r25, r27)
            long r0 = r0 + r13
            int r10 = r8 + -1
            r11 = 4
            int r11 = java.lang.Math.min(r11, r10)
            r12 = 0
            int r11 = java.lang.Math.max(r12, r11)
            int r8 = r8 + -4
            int r8 = java.lang.Math.min(r8, r10)
            int r8 = java.lang.Math.max(r12, r8)
            r21 = r5[r12]
            int r10 = (r21 > r13 ? 1 : (r21 == r13 ? 0 : -1))
            if (r10 > 0) goto L_0x040b
            r10 = r5[r11]
            int r10 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x040b
            r10 = r5[r8]
            int r8 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r8 >= 0) goto L_0x040b
            int r8 = (r0 > r15 ? 1 : (r0 == r15 ? 0 : -1))
            if (r8 > 0) goto L_0x040b
            long r30 = r13 - r21
            com.google.android.gms.internal.ads.zzan r8 = r7.zzf
            int r8 = r8.zzB
            long r10 = (long) r8
            long r12 = r7.zzc
            java.math.RoundingMode r36 = java.math.RoundingMode.FLOOR
            r32 = r10
            r34 = r12
            long r10 = com.google.android.gms.internal.ads.zzgd.zzt(r30, r32, r34, r36)
            com.google.android.gms.internal.ads.zzan r8 = r7.zzf
            int r8 = r8.zzB
            long r12 = (long) r8
            r14 = r9
            long r8 = r7.zzc
            long r21 = r15 - r0
            java.math.RoundingMode r27 = java.math.RoundingMode.FLOOR
            r23 = r12
            r25 = r8
            long r0 = com.google.android.gms.internal.ads.zzgd.zzt(r21, r23, r25, r27)
            r8 = 0
            int r12 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r12 != 0) goto L_0x03d5
            int r10 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r10 == 0) goto L_0x040e
            r10 = 0
        L_0x03d5:
            r8 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r12 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r12 > 0) goto L_0x040e
            int r8 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r8 <= 0) goto L_0x03e1
            goto L_0x040e
        L_0x03e1:
            int r8 = (int) r10
            r9 = r42
            r9.zza = r8
            int r0 = (int) r0
            r9.zzb = r0
            long r0 = r7.zzc
            r8 = 1000000(0xf4240, double:4.940656E-318)
            com.google.android.gms.internal.ads.zzgd.zzF(r5, r8, r0)
            long[] r0 = r7.zzh
            r1 = 0
            r8 = r0[r1]
            long r12 = r7.zzd
            java.math.RoundingMode r14 = java.math.RoundingMode.FLOOR
            r10 = 1000000(0xf4240, double:4.940656E-318)
            long r8 = com.google.android.gms.internal.ads.zzgd.zzt(r8, r10, r12, r14)
            com.google.android.gms.internal.ads.zzaks r10 = new com.google.android.gms.internal.ads.zzaks
            r0 = r10
            r1 = r40
            r7 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r10
        L_0x040b:
            r14 = r9
            goto L_0x040e
        L_0x040d:
            r14 = r0
        L_0x040e:
            long[] r0 = r7.zzh
            int r1 = r0.length
            r8 = 1
            if (r1 != r8) goto L_0x0458
            r8 = 0
            r9 = r0[r8]
            r0 = 0
            int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r9 != 0) goto L_0x0457
            long[] r0 = r7.zzi
            r0.getClass()
            r9 = r0[r8]
            r0 = 0
        L_0x0425:
            int r1 = r5.length
            if (r0 >= r1) goto L_0x043e
            r11 = r5[r0]
            long r17 = r11 - r9
            long r11 = r7.zzc
            java.math.RoundingMode r23 = java.math.RoundingMode.FLOOR
            r19 = 1000000(0xf4240, double:4.940656E-318)
            r21 = r11
            long r11 = com.google.android.gms.internal.ads.zzgd.zzt(r17, r19, r21, r23)
            r5[r0] = r11
            int r0 = r0 + 1
            goto L_0x0425
        L_0x043e:
            long r17 = r15 - r9
            long r0 = r7.zzc
            java.math.RoundingMode r23 = java.math.RoundingMode.FLOOR
            r19 = 1000000(0xf4240, double:4.940656E-318)
            r21 = r0
            long r8 = com.google.android.gms.internal.ads.zzgd.zzt(r17, r19, r21, r23)
            com.google.android.gms.internal.ads.zzaks r10 = new com.google.android.gms.internal.ads.zzaks
            r0 = r10
            r1 = r40
            r7 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r10
        L_0x0457:
            r1 = 1
        L_0x0458:
            int r0 = r7.zzb
            r8 = 1
            if (r0 != r8) goto L_0x045f
            r0 = 1
            goto L_0x0460
        L_0x045f:
            r0 = 0
        L_0x0460:
            long[] r8 = r7.zzi
            int[] r9 = new int[r1]
            int[] r1 = new int[r1]
            r8.getClass()
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x046d:
            long[] r15 = r7.zzh
            r41 = r4
            int r4 = r15.length
            if (r12 >= r4) goto L_0x04d8
            r16 = r3
            r3 = r8[r12]
            r21 = -1
            int r21 = (r3 > r21 ? 1 : (r3 == r21 ? 0 : -1))
            if (r21 == 0) goto L_0x04c3
            r22 = r15[r12]
            r21 = r14
            long r14 = r7.zzc
            r42 = r10
            r29 = r11
            long r10 = r7.zzd
            java.math.RoundingMode r28 = java.math.RoundingMode.FLOOR
            r24 = r14
            r26 = r10
            long r10 = com.google.android.gms.internal.ads.zzgd.zzt(r22, r24, r26, r28)
            r14 = 1
            int r15 = com.google.android.gms.internal.ads.zzgd.zzc(r5, r3, r14, r14)
            r9[r12] = r15
            long r3 = r3 + r10
            r10 = 0
            int r3 = com.google.android.gms.internal.ads.zzgd.zza(r5, r3, r0, r10)
            r1[r12] = r3
        L_0x04a3:
            r3 = r9[r12]
            r4 = r1[r12]
            if (r3 >= r4) goto L_0x04b4
            r11 = r6[r3]
            r11 = r11 & r14
            if (r11 != 0) goto L_0x04b4
            int r3 = r3 + 1
            r9[r12] = r3
            r14 = 1
            goto L_0x04a3
        L_0x04b4:
            int r11 = r4 - r3
            int r11 = r42 + r11
            if (r13 == r3) goto L_0x04bc
            r3 = 1
            goto L_0x04bd
        L_0x04bc:
            r3 = r10
        L_0x04bd:
            r3 = r29 | r3
            r29 = r3
            r13 = r4
            goto L_0x04cc
        L_0x04c3:
            r42 = r10
            r29 = r11
            r21 = r14
            r10 = 0
            r11 = r42
        L_0x04cc:
            int r12 = r12 + 1
            r4 = r41
            r10 = r11
            r3 = r16
            r14 = r21
            r11 = r29
            goto L_0x046d
        L_0x04d8:
            r16 = r3
            r29 = r11
            r3 = r14
            r11 = r10
            r10 = 0
            if (r11 == r3) goto L_0x04e3
            r0 = 1
            goto L_0x04e4
        L_0x04e3:
            r0 = r10
        L_0x04e4:
            r0 = r29 | r0
            if (r0 == 0) goto L_0x04eb
            long[] r3 = new long[r11]
            goto L_0x04ec
        L_0x04eb:
            r3 = r2
        L_0x04ec:
            if (r0 == 0) goto L_0x04f1
            int[] r4 = new int[r11]
            goto L_0x04f3
        L_0x04f1:
            r4 = r16
        L_0x04f3:
            r8 = 1
            if (r8 != r0) goto L_0x04f8
            r8 = r10
            goto L_0x04fa
        L_0x04f8:
            r8 = r41
        L_0x04fa:
            if (r0 == 0) goto L_0x04ff
            int[] r12 = new int[r11]
            goto L_0x0500
        L_0x04ff:
            r12 = r6
        L_0x0500:
            long[] r11 = new long[r11]
            r41 = r8
            r15 = r10
            r13 = 0
        L_0x0507:
            long[] r8 = r7.zzh
            int r8 = r8.length
            if (r10 >= r8) goto L_0x05b2
            long[] r8 = r7.zzi
            r28 = r8[r10]
            r8 = r9[r10]
            r17 = r9
            r9 = r1[r10]
            r30 = r1
            if (r0 == 0) goto L_0x052a
            int r1 = r9 - r8
            java.lang.System.arraycopy(r2, r8, r3, r15, r1)
            r31 = r2
            r2 = r16
            java.lang.System.arraycopy(r2, r8, r4, r15, r1)
            java.lang.System.arraycopy(r6, r8, r12, r15, r1)
            goto L_0x052e
        L_0x052a:
            r31 = r2
            r2 = r16
        L_0x052e:
            r1 = r41
        L_0x0530:
            if (r8 >= r9) goto L_0x058b
            r42 = r9
            r16 = r10
            long r9 = r7.zzd
            java.math.RoundingMode r27 = java.math.RoundingMode.FLOOR
            r23 = 1000000(0xf4240, double:4.940656E-318)
            r21 = r13
            r25 = r9
            long r9 = com.google.android.gms.internal.ads.zzgd.zzt(r21, r23, r25, r27)
            r21 = r5[r8]
            long r32 = r21 - r28
            r21 = r5
            r22 = r6
            long r5 = r7.zzc
            java.math.RoundingMode r38 = java.math.RoundingMode.FLOOR
            r34 = 1000000(0xf4240, double:4.940656E-318)
            r36 = r5
            long r5 = com.google.android.gms.internal.ads.zzgd.zzt(r32, r34, r36, r38)
            r32 = r12
            int r12 = r7.zzb
            r33 = r3
            r3 = 1
            if (r12 == r3) goto L_0x056b
            r12 = r4
            r3 = 0
            long r5 = java.lang.Math.max(r3, r5)
            goto L_0x056e
        L_0x056b:
            r12 = r4
            r3 = 0
        L_0x056e:
            long r9 = r9 + r5
            r11[r15] = r9
            if (r0 == 0) goto L_0x0579
            r5 = r12[r15]
            if (r5 <= r1) goto L_0x0579
            r1 = r2[r8]
        L_0x0579:
            int r15 = r15 + 1
            int r8 = r8 + 1
            r9 = r42
            r4 = r12
            r10 = r16
            r5 = r21
            r6 = r22
            r12 = r32
            r3 = r33
            goto L_0x0530
        L_0x058b:
            r33 = r3
            r21 = r5
            r22 = r6
            r16 = r10
            r32 = r12
            r12 = r4
            r3 = 0
            long[] r5 = r7.zzh
            r8 = r5[r16]
            long r13 = r13 + r8
            int r10 = r16 + 1
            r41 = r1
            r16 = r2
            r4 = r12
            r9 = r17
            r5 = r21
            r1 = r30
            r2 = r31
            r12 = r32
            r3 = r33
            goto L_0x0507
        L_0x05b2:
            r33 = r3
            r32 = r12
            r12 = r4
            long r0 = r7.zzd
            java.math.RoundingMode r27 = java.math.RoundingMode.FLOOR
            r23 = 1000000(0xf4240, double:4.940656E-318)
            r21 = r13
            r25 = r0
            long r8 = com.google.android.gms.internal.ads.zzgd.zzt(r21, r23, r25, r27)
            com.google.android.gms.internal.ads.zzaks r10 = new com.google.android.gms.internal.ads.zzaks
            r0 = r10
            r1 = r40
            r2 = r33
            r3 = r12
            r4 = r41
            r5 = r11
            r6 = r32
            r7 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r10
        L_0x05d8:
            java.lang.String r0 = "Track has no sample table size information"
            com.google.android.gms.internal.ads.zzch r0 = com.google.android.gms.internal.ads.zzch.zza(r0, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzajw.zzk(com.google.android.gms.internal.ads.zzakp, com.google.android.gms.internal.ads.zzajm, com.google.android.gms.internal.ads.zzaej):com.google.android.gms.internal.ads.zzaks");
    }

    private static ByteBuffer zzl() {
        return ByteBuffer.allocate(25).order(ByteOrder.LITTLE_ENDIAN);
    }

    /* JADX WARNING: Removed duplicated region for block: B:122:0x01bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zzm(com.google.android.gms.internal.ads.zzfu r26, int r27, int r28, int r29, int r30, java.lang.String r31, boolean r32, com.google.android.gms.internal.ads.zzae r33, com.google.android.gms.internal.ads.zzajs r34, int r35) throws com.google.android.gms.internal.ads.zzch {
        /*
            r0 = r26
            r1 = r28
            r2 = r29
            r3 = r30
            r4 = r31
            r5 = r33
            r6 = r34
            int r7 = r1 + 16
            r0.zzK(r7)
            r7 = 6
            r8 = 8
            if (r32 == 0) goto L_0x0020
            int r10 = r26.zzq()
            r0.zzL(r7)
            goto L_0x0024
        L_0x0020:
            r0.zzL(r8)
            r10 = 0
        L_0x0024:
            r14 = 4
            r11 = 2
            r12 = 1
            r9 = 16
            if (r10 == 0) goto L_0x0086
            if (r10 != r12) goto L_0x002e
            goto L_0x0086
        L_0x002e:
            if (r10 != r11) goto L_0x054c
            r0.zzL(r9)
            long r19 = r26.zzt()
            double r19 = java.lang.Double.longBitsToDouble(r19)
            long r12 = java.lang.Math.round(r19)
            int r7 = (int) r12
            int r10 = r26.zzp()
            r0.zzL(r14)
            int r12 = r26.zzp()
            int r13 = r26.zzp()
            r19 = r13 & 1
            r13 = r13 & r11
            if (r19 != 0) goto L_0x007a
            if (r12 != r8) goto L_0x0058
            r9 = 3
            goto L_0x0081
        L_0x0058:
            if (r12 != r9) goto L_0x0061
            if (r13 == 0) goto L_0x005f
            r9 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x0081
        L_0x005f:
            r9 = r11
            goto L_0x0081
        L_0x0061:
            r9 = 24
            if (r12 != r9) goto L_0x006d
            if (r13 == 0) goto L_0x006a
            r9 = 1342177280(0x50000000, float:8.5899346E9)
            goto L_0x0081
        L_0x006a:
            r9 = 21
            goto L_0x0081
        L_0x006d:
            r9 = 32
            if (r12 != r9) goto L_0x0080
            if (r13 == 0) goto L_0x0076
            r12 = 1610612736(0x60000000, float:3.6893488E19)
            goto L_0x0078
        L_0x0076:
            r12 = 22
        L_0x0078:
            r9 = r12
            goto L_0x0081
        L_0x007a:
            r9 = 32
            if (r12 != r9) goto L_0x0080
            r9 = r14
            goto L_0x0081
        L_0x0080:
            r9 = -1
        L_0x0081:
            r0.zzL(r8)
            r12 = 0
            goto L_0x00a6
        L_0x0086:
            int r8 = r26.zzq()
            r0.zzL(r7)
            int r7 = r26.zzn()
            int r12 = r26.zzd()
            int r12 = r12 + -4
            r0.zzK(r12)
            int r12 = r26.zzg()
            r13 = 1
            if (r10 != r13) goto L_0x00a4
            r0.zzL(r9)
        L_0x00a4:
            r10 = r8
            r9 = -1
        L_0x00a6:
            int r8 = r26.zzd()
            r13 = 1701733217(0x656e6361, float:7.0359778E22)
            r11 = r27
            if (r11 != r13) goto L_0x00dd
            android.util.Pair r11 = zzh(r0, r1, r2)
            if (r11 == 0) goto L_0x00d7
            java.lang.Object r13 = r11.first
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            if (r5 != 0) goto L_0x00c3
            r14 = 0
            goto L_0x00ce
        L_0x00c3:
            java.lang.Object r14 = r11.second
            com.google.android.gms.internal.ads.zzakq r14 = (com.google.android.gms.internal.ads.zzakq) r14
            java.lang.String r14 = r14.zzb
            com.google.android.gms.internal.ads.zzae r5 = r5.zzb(r14)
            r14 = r5
        L_0x00ce:
            com.google.android.gms.internal.ads.zzakq[] r5 = r6.zza
            java.lang.Object r11 = r11.second
            com.google.android.gms.internal.ads.zzakq r11 = (com.google.android.gms.internal.ads.zzakq) r11
            r5[r35] = r11
            goto L_0x00d8
        L_0x00d7:
            r14 = r5
        L_0x00d8:
            r0.zzK(r8)
            r11 = r13
            goto L_0x00de
        L_0x00dd:
            r14 = r5
        L_0x00de:
            r5 = 1633889587(0x61632d33, float:2.6191674E20)
            java.lang.String r13 = "audio/mhm1"
            java.lang.String r15 = "audio/ac4"
            if (r11 != r5) goto L_0x00ec
            java.lang.String r5 = "audio/ac3"
        L_0x00e9:
            r11 = r9
            goto L_0x01b1
        L_0x00ec:
            r5 = 1700998451(0x65632d33, float:6.7050686E22)
            if (r11 != r5) goto L_0x00f4
            java.lang.String r5 = "audio/eac3"
            goto L_0x00e9
        L_0x00f4:
            r5 = 1633889588(0x61632d34, float:2.6191676E20)
            if (r11 != r5) goto L_0x00fd
            r11 = r9
            r5 = r15
            goto L_0x01b1
        L_0x00fd:
            r5 = 1685353315(0x64747363, float:1.803728E22)
            if (r11 != r5) goto L_0x0105
            java.lang.String r5 = "audio/vnd.dts"
            goto L_0x00e9
        L_0x0105:
            r5 = 1685353320(0x64747368, float:1.8037286E22)
            if (r11 == r5) goto L_0x01ad
            r5 = 1685353324(0x6474736c, float:1.803729E22)
            if (r11 != r5) goto L_0x0111
            goto L_0x01ad
        L_0x0111:
            r5 = 1685353317(0x64747365, float:1.8037282E22)
            if (r11 != r5) goto L_0x0119
            java.lang.String r5 = "audio/vnd.dts.hd;profile=lbr"
            goto L_0x00e9
        L_0x0119:
            r5 = 1685353336(0x64747378, float:1.8037304E22)
            if (r11 != r5) goto L_0x0121
            java.lang.String r5 = "audio/vnd.dts.uhd;profile=p2"
            goto L_0x00e9
        L_0x0121:
            r5 = 1935764850(0x73616d72, float:1.7860208E31)
            if (r11 != r5) goto L_0x0129
            java.lang.String r5 = "audio/3gpp"
            goto L_0x00e9
        L_0x0129:
            r5 = 1935767394(0x73617762, float:1.7863284E31)
            if (r11 != r5) goto L_0x0131
            java.lang.String r5 = "audio/amr-wb"
            goto L_0x00e9
        L_0x0131:
            r5 = 1936684916(0x736f7774, float:1.89725E31)
            java.lang.String r22 = "audio/raw"
            if (r11 != r5) goto L_0x013d
        L_0x0138:
            r5 = r22
            r11 = 2
            goto L_0x01b1
        L_0x013d:
            r5 = 1953984371(0x74776f73, float:7.841539E31)
            if (r11 != r5) goto L_0x0148
            r5 = r22
            r11 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x01b1
        L_0x0148:
            r5 = 1819304813(0x6c70636d, float:1.1624469E27)
            if (r11 != r5) goto L_0x0155
            r5 = -1
            if (r9 != r5) goto L_0x0151
            goto L_0x0138
        L_0x0151:
            r11 = r9
            r5 = r22
            goto L_0x01b1
        L_0x0155:
            r5 = 778924082(0x2e6d7032, float:5.398721E-11)
            if (r11 == r5) goto L_0x01a9
            r5 = 778924083(0x2e6d7033, float:5.3987214E-11)
            if (r11 != r5) goto L_0x0160
            goto L_0x01a9
        L_0x0160:
            r5 = 1835557169(0x6d686131, float:4.4948762E27)
            if (r11 != r5) goto L_0x0168
            java.lang.String r5 = "audio/mha1"
            goto L_0x00e9
        L_0x0168:
            r5 = 1835560241(0x6d686d31, float:4.495783E27)
            if (r11 != r5) goto L_0x0170
            r11 = r9
            r5 = r13
            goto L_0x01b1
        L_0x0170:
            r5 = 1634492771(0x616c6163, float:2.7252807E20)
            if (r11 != r5) goto L_0x0179
            java.lang.String r5 = "audio/alac"
            goto L_0x00e9
        L_0x0179:
            r5 = 1634492791(0x616c6177, float:2.7252842E20)
            if (r11 != r5) goto L_0x0182
            java.lang.String r5 = "audio/g711-alaw"
            goto L_0x00e9
        L_0x0182:
            r5 = 1970037111(0x756c6177, float:2.9964816E32)
            if (r11 != r5) goto L_0x018b
            java.lang.String r5 = "audio/g711-mlaw"
            goto L_0x00e9
        L_0x018b:
            r5 = 1332770163(0x4f707573, float:4.03422899E9)
            if (r11 != r5) goto L_0x0194
            java.lang.String r5 = "audio/opus"
            goto L_0x00e9
        L_0x0194:
            r5 = 1716281667(0x664c6143, float:2.4128923E23)
            if (r11 != r5) goto L_0x019d
            java.lang.String r5 = "audio/flac"
            goto L_0x00e9
        L_0x019d:
            r5 = 1835823201(0x6d6c7061, float:4.573395E27)
            if (r11 != r5) goto L_0x01a6
            java.lang.String r5 = "audio/true-hd"
            goto L_0x00e9
        L_0x01a6:
            r11 = r9
            r5 = 0
            goto L_0x01b1
        L_0x01a9:
            java.lang.String r5 = "audio/mpeg"
            goto L_0x00e9
        L_0x01ad:
            java.lang.String r5 = "audio/vnd.dts.hd"
            goto L_0x00e9
        L_0x01b1:
            r16 = r11
            r33 = 0
            r11 = 0
            r22 = 0
        L_0x01b8:
            int r9 = r8 - r1
            if (r9 >= r2) goto L_0x0503
            r0.zzK(r8)
            int r9 = r26.zzg()
            if (r9 <= 0) goto L_0x01c7
            r1 = 1
            goto L_0x01c8
        L_0x01c7:
            r1 = 0
        L_0x01c8:
            java.lang.String r2 = "childAtomSize must be positive"
            com.google.android.gms.internal.ads.zzady.zzb(r1, r2)
            int r1 = r26.zzg()
            r35 = r7
            r7 = 1835557187(0x6d686143, float:4.4948815E27)
            if (r1 != r7) goto L_0x0237
            int r1 = r8 + 8
            r0.zzK(r1)
            r1 = 1
            r0.zzL(r1)
            int r2 = r26.zzm()
            r0.zzL(r1)
            boolean r7 = java.util.Objects.equals(r5, r13)
            if (r7 == 0) goto L_0x0200
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object[] r7 = new java.lang.Object[r1]
            r1 = 0
            r7[r1] = r2
            java.lang.String r2 = "mhm1.%02X"
            java.lang.String r2 = java.lang.String.format(r2, r7)
            r23 = r13
            goto L_0x0212
        L_0x0200:
            r1 = 0
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r23 = r13
            r7 = 1
            java.lang.Object[] r13 = new java.lang.Object[r7]
            r13[r1] = r2
            java.lang.String r2 = "mha1.%02X"
            java.lang.String r2 = java.lang.String.format(r2, r13)
        L_0x0212:
            int r7 = r26.zzq()
            byte[] r13 = new byte[r7]
            r0.zzG(r13, r1, r7)
            if (r11 != 0) goto L_0x022c
            com.google.android.gms.internal.ads.zzgbc r11 = com.google.android.gms.internal.ads.zzgbc.zzn(r13)
            r7 = r35
            r24 = r12
            r17 = 3
            r20 = 2
            r12 = r1
            goto L_0x04f4
        L_0x022c:
            java.lang.Object r7 = r11.get(r1)
            byte[] r7 = (byte[]) r7
            com.google.android.gms.internal.ads.zzgbc r11 = com.google.android.gms.internal.ads.zzgbc.zzo(r13, r7)
            goto L_0x026e
        L_0x0237:
            r23 = r13
            r7 = 1835557200(0x6d686150, float:4.4948854E27)
            if (r1 != r7) goto L_0x0284
            int r1 = r8 + 8
            r0.zzK(r1)
            int r1 = r26.zzm()
            if (r1 <= 0) goto L_0x0279
            byte[] r2 = new byte[r1]
            r7 = 0
            r0.zzG(r2, r7, r1)
            if (r11 != 0) goto L_0x0262
            com.google.android.gms.internal.ads.zzgbc r11 = com.google.android.gms.internal.ads.zzgbc.zzn(r2)
            r2 = r33
            r24 = r12
            r17 = 3
            r20 = 2
            r12 = r7
            r7 = r35
            goto L_0x04f4
        L_0x0262:
            java.lang.Object r1 = r11.get(r7)
            byte[] r1 = (byte[]) r1
            com.google.android.gms.internal.ads.zzgbc r11 = com.google.android.gms.internal.ads.zzgbc.zzo(r1, r2)
        L_0x026c:
            r2 = r33
        L_0x026e:
            r7 = r35
            r24 = r12
        L_0x0272:
            r12 = 0
            r17 = 3
            r20 = 2
            goto L_0x04f4
        L_0x0279:
            r2 = r35
            r24 = r12
            r12 = 0
            r17 = 3
            r20 = 2
            goto L_0x04f1
        L_0x0284:
            r7 = 1702061171(0x65736473, float:7.183675E22)
            if (r1 == r7) goto L_0x0449
            if (r32 == 0) goto L_0x02ce
            r13 = 2002876005(0x77617665, float:4.5729223E33)
            if (r1 != r13) goto L_0x02ce
            int r1 = r26.zzd()
            if (r1 < r8) goto L_0x0299
            r7 = 0
            r13 = 1
            goto L_0x029b
        L_0x0299:
            r7 = 0
            r13 = 0
        L_0x029b:
            com.google.android.gms.internal.ads.zzady.zzb(r13, r7)
        L_0x029e:
            int r7 = r1 - r8
            if (r7 >= r9) goto L_0x02c3
            r0.zzK(r1)
            int r7 = r26.zzg()
            if (r7 <= 0) goto L_0x02ad
            r13 = 1
            goto L_0x02ae
        L_0x02ad:
            r13 = 0
        L_0x02ae:
            com.google.android.gms.internal.ads.zzady.zzb(r13, r2)
            int r13 = r26.zzg()
            r25 = r2
            r2 = 1702061171(0x65736473, float:7.183675E22)
            if (r13 == r2) goto L_0x02c0
            int r1 = r1 + r7
            r2 = r25
            goto L_0x029e
        L_0x02c0:
            r2 = r35
            goto L_0x02c6
        L_0x02c3:
            r2 = r35
            r1 = -1
        L_0x02c6:
            r7 = -1
            r13 = 4
            r17 = 3
            r20 = 2
            goto L_0x0452
        L_0x02ce:
            r2 = 1684103987(0x64616333, float:1.6630662E22)
            if (r1 != r2) goto L_0x02e3
            int r1 = r8 + 8
            r0.zzK(r1)
            java.lang.String r1 = java.lang.Integer.toString(r30)
            com.google.android.gms.internal.ads.zzan r1 = com.google.android.gms.internal.ads.zzact.zzc(r0, r1, r4, r14)
            r6.zzb = r1
            goto L_0x0279
        L_0x02e3:
            r2 = 1684366131(0x64656333, float:1.692581E22)
            if (r1 != r2) goto L_0x02f8
            int r1 = r8 + 8
            r0.zzK(r1)
            java.lang.String r1 = java.lang.Integer.toString(r30)
            com.google.android.gms.internal.ads.zzan r1 = com.google.android.gms.internal.ads.zzact.zzd(r0, r1, r4, r14)
            r6.zzb = r1
            goto L_0x0279
        L_0x02f8:
            r2 = 1684103988(0x64616334, float:1.6630663E22)
            if (r1 != r2) goto L_0x033e
            int r1 = r8 + 8
            r0.zzK(r1)
            java.lang.String r1 = java.lang.Integer.toString(r30)
            int r2 = com.google.android.gms.internal.ads.zzacw.zza
            r2 = 1
            r0.zzL(r2)
            int r7 = r26.zzm()
            r13 = 32
            r7 = r7 & r13
            com.google.android.gms.internal.ads.zzal r13 = new com.google.android.gms.internal.ads.zzal
            r13.<init>()
            r13.zzK(r1)
            r13.zzX(r15)
            r1 = 2
            r13.zzy(r1)
            int r1 = r7 >> 5
            if (r2 == r1) goto L_0x032a
            r1 = 44100(0xac44, float:6.1797E-41)
            goto L_0x032d
        L_0x032a:
            r1 = 48000(0xbb80, float:6.7262E-41)
        L_0x032d:
            r13.zzY(r1)
            r13.zzE(r14)
            r13.zzO(r4)
            com.google.android.gms.internal.ads.zzan r1 = r13.zzad()
            r6.zzb = r1
            goto L_0x0279
        L_0x033e:
            r2 = 1684892784(0x646d6c70, float:1.7518768E22)
            if (r1 != r2) goto L_0x0361
            if (r12 <= 0) goto L_0x034d
            r2 = r33
            r7 = r12
            r24 = r7
            r10 = 2
            goto L_0x0272
        L_0x034d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid sample rate for Dolby TrueHD MLP stream: "
            r0.<init>(r1)
            r0.append(r12)
            java.lang.String r0 = r0.toString()
            r2 = 0
            com.google.android.gms.internal.ads.zzch r0 = com.google.android.gms.internal.ads.zzch.zza(r0, r2)
            throw r0
        L_0x0361:
            r2 = 0
            r7 = 1684305011(0x64647473, float:1.6856995E22)
            if (r1 == r7) goto L_0x0420
            r7 = 1969517683(0x75647473, float:2.8960097E32)
            if (r1 != r7) goto L_0x036e
            goto L_0x0420
        L_0x036e:
            r7 = 1682927731(0x644f7073, float:1.5306315E22)
            if (r1 != r7) goto L_0x038c
            int r1 = r8 + 8
            int r7 = r9 + -8
            byte[] r11 = zza
            int r13 = r11.length
            int r13 = r13 + r7
            byte[] r13 = java.util.Arrays.copyOf(r11, r13)
            r0.zzK(r1)
            int r1 = r11.length
            r0.zzG(r13, r1, r7)
            java.util.List r11 = com.google.android.gms.internal.ads.zzaep.zze(r13)
            goto L_0x026c
        L_0x038c:
            r7 = 1684425825(0x64664c61, float:1.6993019E22)
            if (r1 != r7) goto L_0x03c5
            int r1 = r8 + 12
            int r7 = r9 + -12
            int r11 = r9 + -8
            byte[] r11 = new byte[r11]
            r13 = 102(0x66, float:1.43E-43)
            r18 = 0
            r11[r18] = r13
            r13 = 76
            r21 = 1
            r11[r21] = r13
            r13 = 97
            r20 = 2
            r11[r20] = r13
            r13 = 67
            r17 = 3
            r11[r17] = r13
            r0.zzK(r1)
            r13 = 4
            r0.zzG(r11, r13, r7)
            com.google.android.gms.internal.ads.zzgbc r11 = com.google.android.gms.internal.ads.zzgbc.zzn(r11)
            r2 = r33
            r7 = r35
            r24 = r12
            r12 = 0
            goto L_0x04f4
        L_0x03c5:
            r7 = 1634492771(0x616c6163, float:2.7252807E20)
            r13 = 4
            r17 = 3
            r20 = 2
            if (r1 != r7) goto L_0x041c
            int r1 = r8 + 12
            int r10 = r9 + -12
            byte[] r11 = new byte[r10]
            r0.zzK(r1)
            r1 = 0
            r0.zzG(r11, r1, r10)
            int r1 = com.google.android.gms.internal.ads.zzes.zza
            com.google.android.gms.internal.ads.zzfu r1 = new com.google.android.gms.internal.ads.zzfu
            r1.<init>((byte[]) r11)
            r10 = 9
            r1.zzK(r10)
            int r10 = r1.zzm()
            r2 = 20
            r1.zzK(r2)
            int r1 = r1.zzp()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r10)
            android.util.Pair r1 = android.util.Pair.create(r1, r2)
            java.lang.Object r2 = r1.first
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            java.lang.Object r1 = r1.second
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            com.google.android.gms.internal.ads.zzgbc r11 = com.google.android.gms.internal.ads.zzgbc.zzn(r11)
            r10 = r1
            r7 = r2
            r24 = r12
            r12 = 0
            goto L_0x04f2
        L_0x041c:
            r2 = r35
            goto L_0x04ee
        L_0x0420:
            r7 = 1634492771(0x616c6163, float:2.7252807E20)
            r13 = 4
            r17 = 3
            r20 = 2
            com.google.android.gms.internal.ads.zzal r1 = new com.google.android.gms.internal.ads.zzal
            r1.<init>()
            r1.zzJ(r3)
            r1.zzX(r5)
            r1.zzy(r10)
            r2 = r35
            r1.zzY(r2)
            r1.zzE(r14)
            r1.zzO(r4)
            com.google.android.gms.internal.ads.zzan r1 = r1.zzad()
            r6.zzb = r1
            goto L_0x04ee
        L_0x0449:
            r2 = r35
            r13 = 4
            r17 = 3
            r20 = 2
            r1 = r8
            r7 = -1
        L_0x0452:
            if (r1 == r7) goto L_0x04ee
            com.google.android.gms.internal.ads.zzajq r22 = zzj(r0, r1)
            java.lang.String r1 = r22.zza
            byte[] r5 = r22.zzb
            if (r5 == 0) goto L_0x04e9
            java.lang.String r11 = "audio/vorbis"
            boolean r11 = r11.equals(r1)
            if (r11 == 0) goto L_0x04c9
            com.google.android.gms.internal.ads.zzfu r11 = new com.google.android.gms.internal.ads.zzfu
            r11.<init>((byte[]) r5)
            r7 = 1
            r11.zzL(r7)
            r13 = 0
        L_0x0474:
            int r21 = r11.zzb()
            r7 = 255(0xff, float:3.57E-43)
            if (r21 <= 0) goto L_0x048c
            int r0 = r11.zzf()
            if (r0 != r7) goto L_0x048c
            r0 = 1
            r11.zzL(r0)
            int r13 = r13 + 255
            r0 = r26
            r7 = 1
            goto L_0x0474
        L_0x048c:
            int r0 = r11.zzm()
            int r13 = r13 + r0
            r0 = 0
        L_0x0492:
            int r24 = r11.zzb()
            if (r24 <= 0) goto L_0x04a9
            r24 = r12
            int r12 = r11.zzf()
            if (r12 != r7) goto L_0x04ab
            r12 = 1
            r11.zzL(r12)
            int r0 = r0 + 255
            r12 = r24
            goto L_0x0492
        L_0x04a9:
            r24 = r12
        L_0x04ab:
            r12 = 1
            int r7 = r11.zzm()
            int r0 = r0 + r7
            byte[] r7 = new byte[r13]
            int r11 = r11.zzd()
            r12 = 0
            java.lang.System.arraycopy(r5, r11, r7, r12, r13)
            int r11 = r11 + r13
            int r13 = r5.length
            int r11 = r11 + r0
            int r13 = r13 - r11
            byte[] r0 = new byte[r13]
            java.lang.System.arraycopy(r5, r11, r0, r12, r13)
            com.google.android.gms.internal.ads.zzgbc r11 = com.google.android.gms.internal.ads.zzgbc.zzo(r7, r0)
            goto L_0x04ec
        L_0x04c9:
            r24 = r12
            r12 = 0
            java.lang.String r0 = "audio/mp4a-latm"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x04df
            com.google.android.gms.internal.ads.zzacp r0 = com.google.android.gms.internal.ads.zzacq.zza(r5)
            int r7 = r0.zza
            int r10 = r0.zzb
            java.lang.String r0 = r0.zzc
            goto L_0x04e2
        L_0x04df:
            r0 = r33
            r7 = r2
        L_0x04e2:
            com.google.android.gms.internal.ads.zzgbc r11 = com.google.android.gms.internal.ads.zzgbc.zzn(r5)
            r2 = r0
            r5 = r1
            goto L_0x04f4
        L_0x04e9:
            r24 = r12
            r12 = 0
        L_0x04ec:
            r5 = r1
            goto L_0x04f1
        L_0x04ee:
            r24 = r12
            r12 = 0
        L_0x04f1:
            r7 = r2
        L_0x04f2:
            r2 = r33
        L_0x04f4:
            int r8 = r8 + r9
            r0 = r26
            r1 = r28
            r33 = r2
            r13 = r23
            r12 = r24
            r2 = r29
            goto L_0x01b8
        L_0x0503:
            r2 = r7
            com.google.android.gms.internal.ads.zzan r0 = r6.zzb
            if (r0 != 0) goto L_0x054c
            if (r5 == 0) goto L_0x054c
            com.google.android.gms.internal.ads.zzal r0 = new com.google.android.gms.internal.ads.zzal
            r0.<init>()
            r0.zzJ(r3)
            r0.zzX(r5)
            r1 = r33
            r0.zzz(r1)
            r0.zzy(r10)
            r0.zzY(r2)
            r9 = r16
            r0.zzR(r9)
            r0.zzL(r11)
            r0.zzE(r14)
            r0.zzO(r4)
            if (r22 == 0) goto L_0x0546
            long r1 = r22.zzc
            int r1 = com.google.android.gms.internal.ads.zzgea.zze(r1)
            r0.zzx(r1)
            long r1 = r22.zzd
            int r1 = com.google.android.gms.internal.ads.zzgea.zze(r1)
            r0.zzS(r1)
        L_0x0546:
            com.google.android.gms.internal.ads.zzan r0 = r0.zzad()
            r6.zzb = r0
        L_0x054c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzajw.zzm(com.google.android.gms.internal.ads.zzfu, int, int, int, int, java.lang.String, boolean, com.google.android.gms.internal.ads.zzae, com.google.android.gms.internal.ads.zzajs, int):void");
    }
}
