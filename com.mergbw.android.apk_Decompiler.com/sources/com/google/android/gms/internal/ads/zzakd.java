package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.base.Ascii;
import com.mergbw.core.ble.CommandList;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzakd implements zzadu {
    @Deprecated
    public static final zzaea zza = new zzajz();
    private static final byte[] zzb = {-94, 57, 79, 82, 90, -101, 79, Ascii.DC4, -94, 68, 108, 66, 124, 100, -115, CommandList.CMD_CONFIG_RGB_ORDER_REQ};
    private static final zzan zzc;
    private zzakc zzA;
    private int zzB;
    private int zzC;
    private int zzD;
    private boolean zzE;
    private zzadx zzF;
    private zzafa[] zzG;
    private zzafa[] zzH;
    private boolean zzI;
    private final zzalt zzd;
    private final List zze;
    private final SparseArray zzf;
    private final zzfu zzg;
    private final zzfu zzh;
    private final zzfu zzi;
    private final byte[] zzj;
    private final zzfu zzk;
    private final zzagu zzl;
    private final zzfu zzm;
    private final ArrayDeque zzn;
    private final ArrayDeque zzo;
    private zzgbc zzp;
    private int zzq;
    private int zzr;
    private long zzs;
    private int zzt;
    private zzfu zzu;
    private long zzv;
    private int zzw;
    private long zzx;
    private long zzy;
    private long zzz;

    static {
        zzal zzal = new zzal();
        zzal.zzX(MimeTypes.APPLICATION_EMSG);
        zzc = zzal.zzad();
    }

    @Deprecated
    public zzakd() {
        this(zzalt.zza, 32, (zzgb) null, (zzakp) null, zzgbc.zzm(), (zzafa) null);
    }

    private static int zza(int i) throws zzch {
        if (i >= 0) {
            return i;
        }
        throw zzch.zza("Unexpected negative value: " + i, (Throwable) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0116  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.ads.zzae zzg(java.util.List r18) {
        /*
            int r0 = r18.size()
            r1 = 0
            r3 = r1
            r4 = 0
        L_0x0007:
            if (r3 >= r0) goto L_0x012b
            r5 = r18
            java.lang.Object r6 = r5.get(r3)
            com.google.android.gms.internal.ads.zzajn r6 = (com.google.android.gms.internal.ads.zzajn) r6
            int r7 = r6.zzd
            r8 = 1886614376(0x70737368, float:3.013775E29)
            if (r7 != r8) goto L_0x0124
            if (r4 != 0) goto L_0x001f
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
        L_0x001f:
            com.google.android.gms.internal.ads.zzfu r6 = r6.zza
            byte[] r6 = r6.zzM()
            com.google.android.gms.internal.ads.zzfu r7 = new com.google.android.gms.internal.ads.zzfu
            r7.<init>((byte[]) r6)
            int r9 = r7.zze()
            r10 = 32
            if (r9 >= r10) goto L_0x0039
        L_0x0032:
            r16 = r3
            r17 = r4
        L_0x0036:
            r2 = 0
            goto L_0x0104
        L_0x0039:
            r7.zzK(r1)
            int r9 = r7.zzb()
            int r10 = r7.zzg()
            java.lang.String r11 = "PsshAtomUtil"
            if (r10 == r9) goto L_0x0062
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Advertised atom size ("
            r7.<init>(r8)
            r7.append(r10)
            java.lang.String r8 = ") does not match buffer size: "
            r7.append(r8)
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r11, r7)
            goto L_0x0032
        L_0x0062:
            int r9 = r7.zzg()
            if (r9 == r8) goto L_0x007a
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Atom type is not pssh: "
            r7.<init>(r8)
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r11, r7)
            goto L_0x0032
        L_0x007a:
            int r8 = r7.zzg()
            int r8 = com.google.android.gms.internal.ads.zzajo.zze(r8)
            r9 = 1
            if (r8 <= r9) goto L_0x0097
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r9 = "Unsupported pssh version: "
            r7.<init>(r9)
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r11, r7)
            goto L_0x0032
        L_0x0097:
            java.util.UUID r10 = new java.util.UUID
            long r12 = r7.zzt()
            long r14 = r7.zzt()
            r10.<init>(r12, r14)
            if (r8 != r9) goto L_0x00d0
            int r9 = r7.zzp()
            java.util.UUID[] r12 = new java.util.UUID[r9]
            r13 = r1
        L_0x00ad:
            if (r13 >= r9) goto L_0x00cb
            java.util.UUID r14 = new java.util.UUID
            r16 = r3
            long r2 = r7.zzt()
            r17 = r4
            long r4 = r7.zzt()
            r14.<init>(r2, r4)
            r12[r13] = r14
            int r13 = r13 + 1
            r5 = r18
            r3 = r16
            r4 = r17
            goto L_0x00ad
        L_0x00cb:
            r16 = r3
            r17 = r4
            goto L_0x00d5
        L_0x00d0:
            r16 = r3
            r17 = r4
            r12 = 0
        L_0x00d5:
            int r2 = r7.zzp()
            int r3 = r7.zzb()
            if (r2 == r3) goto L_0x00fa
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Atom data size ("
            r4.<init>(r5)
            r4.append(r2)
            java.lang.String r2 = ") does not match the bytes left: "
            r4.append(r2)
            r4.append(r3)
            java.lang.String r2 = r4.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r11, r2)
            goto L_0x0036
        L_0x00fa:
            byte[] r3 = new byte[r2]
            r7.zzG(r3, r1, r2)
            com.google.android.gms.internal.ads.zzakl r2 = new com.google.android.gms.internal.ads.zzakl
            r2.<init>(r10, r8, r3, r12)
        L_0x0104:
            if (r2 != 0) goto L_0x0108
            r2 = 0
            goto L_0x010a
        L_0x0108:
            java.util.UUID r2 = r2.zza
        L_0x010a:
            if (r2 != 0) goto L_0x0116
            java.lang.String r2 = "FragmentedMp4Extractor"
            java.lang.String r3 = "Skipped pssh atom (failed to extract uuid)"
            com.google.android.gms.internal.ads.zzfk.zzf(r2, r3)
            r4 = r17
            goto L_0x0126
        L_0x0116:
            com.google.android.gms.internal.ads.zzad r3 = new com.google.android.gms.internal.ads.zzad
            java.lang.String r4 = "video/mp4"
            r5 = 0
            r3.<init>(r2, r5, r4, r6)
            r4 = r17
            r4.add(r3)
            goto L_0x0127
        L_0x0124:
            r16 = r3
        L_0x0126:
            r5 = 0
        L_0x0127:
            int r3 = r16 + 1
            goto L_0x0007
        L_0x012b:
            r5 = 0
            if (r4 != 0) goto L_0x012f
            return r5
        L_0x012f:
            com.google.android.gms.internal.ads.zzae r0 = new com.google.android.gms.internal.ads.zzae
            r0.<init>((java.util.List) r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzg(java.util.List):com.google.android.gms.internal.ads.zzae");
    }

    private final void zzh() {
        this.zzq = 0;
        this.zzt = 0;
    }

    private static void zzi(zzfu zzfu, int i, zzakr zzakr) throws zzch {
        zzfu.zzK(i + 8);
        int zzg2 = zzfu.zzg();
        if ((zzg2 & 1) == 0) {
            boolean z = (zzg2 & 2) != 0;
            int zzp2 = zzfu.zzp();
            if (zzp2 == 0) {
                Arrays.fill(zzakr.zzl, 0, zzakr.zze, false);
                return;
            }
            int i2 = zzakr.zze;
            if (zzp2 == i2) {
                Arrays.fill(zzakr.zzl, 0, zzp2, z);
                zzakr.zza(zzfu.zzb());
                zzfu zzfu2 = zzakr.zzn;
                zzfu.zzG(zzfu2.zzM(), 0, zzfu2.zze());
                zzakr.zzn.zzK(0);
                zzakr.zzo = false;
                return;
            }
            throw zzch.zza("Senc sample count " + zzp2 + " is different from fragment sample count" + i2, (Throwable) null);
        }
        throw zzch.zzc("Overriding TrackEncryptionBox parameters is unsupported.");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:110:0x030a, code lost:
        if (com.google.android.gms.internal.ads.zzgd.zzt(r29 + r8[0], 1000000, r5.zzd, java.math.RoundingMode.FLOOR) < r5.zze) goto L_0x0320;
     */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0338  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzj(long r45) throws com.google.android.gms.internal.ads.zzch {
        /*
            r44 = this;
            r0 = r44
        L_0x0002:
            java.util.ArrayDeque r1 = r0.zzn
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x06ed
            java.util.ArrayDeque r1 = r0.zzn
            java.lang.Object r1 = r1.peek()
            com.google.android.gms.internal.ads.zzajm r1 = (com.google.android.gms.internal.ads.zzajm) r1
            long r1 = r1.zza
            int r1 = (r1 > r45 ? 1 : (r1 == r45 ? 0 : -1))
            if (r1 != 0) goto L_0x06ed
            java.util.ArrayDeque r1 = r0.zzn
            java.lang.Object r1 = r1.pop()
            r2 = r1
            com.google.android.gms.internal.ads.zzajm r2 = (com.google.android.gms.internal.ads.zzajm) r2
            int r1 = r2.zzd
            r3 = 1836019574(0x6d6f6f76, float:4.631354E27)
            r6 = 12
            r7 = 8
            if (r1 != r3) goto L_0x013d
            java.util.List r1 = r2.zzb
            com.google.android.gms.internal.ads.zzae r1 = zzg(r1)
            r3 = 1836475768(0x6d766578, float:4.7659988E27)
            com.google.android.gms.internal.ads.zzajm r3 = r2.zza(r3)
            r3.getClass()
            android.util.SparseArray r12 = new android.util.SparseArray
            r12.<init>()
            java.util.List r8 = r3.zzb
            int r8 = r8.size()
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r9 = 0
        L_0x004d:
            if (r9 >= r8) goto L_0x00b8
            java.util.List r13 = r3.zzb
            java.lang.Object r13 = r13.get(r9)
            com.google.android.gms.internal.ads.zzajn r13 = (com.google.android.gms.internal.ads.zzajn) r13
            int r14 = r13.zzd
            r15 = 1953654136(0x74726578, float:7.6818474E31)
            if (r14 != r15) goto L_0x0096
            com.google.android.gms.internal.ads.zzfu r13 = r13.zza
            r13.zzK(r6)
            int r14 = r13.zzg()
            int r15 = r13.zzg()
            int r15 = r15 + -1
            int r10 = r13.zzg()
            int r6 = r13.zzg()
            int r13 = r13.zzg()
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            com.google.android.gms.internal.ads.zzajy r11 = new com.google.android.gms.internal.ads.zzajy
            r11.<init>(r15, r10, r6, r13)
            android.util.Pair r6 = android.util.Pair.create(r14, r11)
            java.lang.Object r10 = r6.first
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            java.lang.Object r6 = r6.second
            com.google.android.gms.internal.ads.zzajy r6 = (com.google.android.gms.internal.ads.zzajy) r6
            r12.put(r10, r6)
            goto L_0x00b3
        L_0x0096:
            r6 = 1835362404(0x6d656864, float:4.4373917E27)
            if (r14 != r6) goto L_0x00b3
            com.google.android.gms.internal.ads.zzfu r4 = r13.zza
            r4.zzK(r7)
            int r5 = r4.zzg()
            int r5 = com.google.android.gms.internal.ads.zzajo.zze(r5)
            if (r5 != 0) goto L_0x00af
            long r4 = r4.zzu()
            goto L_0x00b3
        L_0x00af:
            long r4 = r4.zzv()
        L_0x00b3:
            int r9 = r9 + 1
            r6 = 12
            goto L_0x004d
        L_0x00b8:
            com.google.android.gms.internal.ads.zzaej r3 = new com.google.android.gms.internal.ads.zzaej
            r3.<init>()
            com.google.android.gms.internal.ads.zzaka r9 = new com.google.android.gms.internal.ads.zzaka
            r9.<init>(r0)
            r7 = 0
            r8 = 0
            r6 = r1
            java.util.List r1 = com.google.android.gms.internal.ads.zzajw.zzd(r2, r3, r4, r6, r7, r8, r9)
            int r2 = r1.size()
            android.util.SparseArray r3 = r0.zzf
            int r3 = r3.size()
            if (r3 != 0) goto L_0x010e
            r10 = 0
        L_0x00d6:
            if (r10 >= r2) goto L_0x0107
            java.lang.Object r3 = r1.get(r10)
            com.google.android.gms.internal.ads.zzaks r3 = (com.google.android.gms.internal.ads.zzaks) r3
            com.google.android.gms.internal.ads.zzakp r4 = r3.zza
            com.google.android.gms.internal.ads.zzakc r5 = new com.google.android.gms.internal.ads.zzakc
            com.google.android.gms.internal.ads.zzadx r6 = r0.zzF
            int r7 = r4.zzb
            com.google.android.gms.internal.ads.zzafa r6 = r6.zzw(r10, r7)
            int r7 = r4.zza
            com.google.android.gms.internal.ads.zzajy r7 = zzk(r12, r7)
            r5.<init>(r6, r3, r7)
            android.util.SparseArray r3 = r0.zzf
            int r6 = r4.zza
            r3.put(r6, r5)
            long r5 = r0.zzy
            long r3 = r4.zze
            long r3 = java.lang.Math.max(r5, r3)
            r0.zzy = r3
            int r10 = r10 + 1
            goto L_0x00d6
        L_0x0107:
            com.google.android.gms.internal.ads.zzadx r1 = r0.zzF
            r1.zzD()
            goto L_0x0002
        L_0x010e:
            android.util.SparseArray r3 = r0.zzf
            int r3 = r3.size()
            if (r3 != r2) goto L_0x0118
            r11 = 1
            goto L_0x0119
        L_0x0118:
            r11 = 0
        L_0x0119:
            com.google.android.gms.internal.ads.zzeq.zzf(r11)
            r10 = 0
        L_0x011d:
            if (r10 >= r2) goto L_0x0002
            java.lang.Object r3 = r1.get(r10)
            com.google.android.gms.internal.ads.zzaks r3 = (com.google.android.gms.internal.ads.zzaks) r3
            com.google.android.gms.internal.ads.zzakp r4 = r3.zza
            android.util.SparseArray r5 = r0.zzf
            int r6 = r4.zza
            java.lang.Object r5 = r5.get(r6)
            com.google.android.gms.internal.ads.zzakc r5 = (com.google.android.gms.internal.ads.zzakc) r5
            int r4 = r4.zza
            com.google.android.gms.internal.ads.zzajy r4 = zzk(r12, r4)
            r5.zzh(r3, r4)
            int r10 = r10 + 1
            goto L_0x011d
        L_0x013d:
            r3 = 1836019558(0x6d6f6f66, float:4.6313494E27)
            if (r1 != r3) goto L_0x06d5
            android.util.SparseArray r1 = r0.zzf
            byte[] r3 = r0.zzj
            java.util.List r6 = r2.zzc
            int r6 = r6.size()
            r8 = 0
        L_0x014d:
            if (r8 >= r6) goto L_0x0640
            java.util.List r10 = r2.zzc
            java.lang.Object r10 = r10.get(r8)
            com.google.android.gms.internal.ads.zzajm r10 = (com.google.android.gms.internal.ads.zzajm) r10
            int r11 = r10.zzd
            r12 = 1953653094(0x74726166, float:7.6813435E31)
            if (r11 != r12) goto L_0x0623
            r11 = 1952868452(0x74666864, float:7.301914E31)
            com.google.android.gms.internal.ads.zzajn r11 = r10.zzb(r11)
            r11.getClass()
            com.google.android.gms.internal.ads.zzfu r11 = r11.zza
            r11.zzK(r7)
            int r12 = r11.zzg()
            int r13 = r11.zzg()
            java.lang.Object r13 = r1.get(r13)
            com.google.android.gms.internal.ads.zzakc r13 = (com.google.android.gms.internal.ads.zzakc) r13
            if (r13 != 0) goto L_0x017f
            r13 = 0
            goto L_0x01c6
        L_0x017f:
            r14 = r12 & 1
            if (r14 == 0) goto L_0x018d
            long r14 = r11.zzv()
            com.google.android.gms.internal.ads.zzakr r4 = r13.zzb
            r4.zzb = r14
            r4.zzc = r14
        L_0x018d:
            com.google.android.gms.internal.ads.zzajy r4 = r13.zze
            r5 = r12 & 2
            if (r5 == 0) goto L_0x019a
            int r5 = r11.zzg()
            int r5 = r5 + -1
            goto L_0x019c
        L_0x019a:
            int r5 = r4.zza
        L_0x019c:
            r14 = r12 & 8
            if (r14 == 0) goto L_0x01a5
            int r14 = r11.zzg()
            goto L_0x01a7
        L_0x01a5:
            int r14 = r4.zzb
        L_0x01a7:
            r15 = r12 & 16
            if (r15 == 0) goto L_0x01b0
            int r15 = r11.zzg()
            goto L_0x01b2
        L_0x01b0:
            int r15 = r4.zzc
        L_0x01b2:
            r12 = r12 & 32
            if (r12 == 0) goto L_0x01bb
            int r4 = r11.zzg()
            goto L_0x01bd
        L_0x01bb:
            int r4 = r4.zzd
        L_0x01bd:
            com.google.android.gms.internal.ads.zzakr r11 = r13.zzb
            com.google.android.gms.internal.ads.zzajy r12 = new com.google.android.gms.internal.ads.zzajy
            r12.<init>(r5, r14, r15, r4)
            r11.zza = r12
        L_0x01c6:
            if (r13 != 0) goto L_0x01ca
            goto L_0x0623
        L_0x01ca:
            com.google.android.gms.internal.ads.zzakr r4 = r13.zzb
            long r11 = r4.zzp
            boolean r5 = r4.zzq
            r13.zzi()
            r14 = 1
            r13.zzl = true
            r15 = 1952867444(0x74666474, float:7.3014264E31)
            com.google.android.gms.internal.ads.zzajn r15 = r10.zzb(r15)
            if (r15 == 0) goto L_0x01fd
            com.google.android.gms.internal.ads.zzfu r5 = r15.zza
            r5.zzK(r7)
            int r11 = r5.zzg()
            int r11 = com.google.android.gms.internal.ads.zzajo.zze(r11)
            if (r11 != r14) goto L_0x01f4
            long r11 = r5.zzv()
            goto L_0x01f8
        L_0x01f4:
            long r11 = r5.zzu()
        L_0x01f8:
            r4.zzp = r11
            r4.zzq = r14
            goto L_0x0201
        L_0x01fd:
            r4.zzp = r11
            r4.zzq = r5
        L_0x0201:
            java.util.List r5 = r10.zzb
            int r11 = r5.size()
            r12 = 0
            r14 = 0
            r15 = 0
        L_0x020a:
            r9 = 1953658222(0x7472756e, float:7.683823E31)
            if (r12 >= r11) goto L_0x0234
            java.lang.Object r16 = r5.get(r12)
            r7 = r16
            com.google.android.gms.internal.ads.zzajn r7 = (com.google.android.gms.internal.ads.zzajn) r7
            r16 = r1
            int r1 = r7.zzd
            if (r1 != r9) goto L_0x022d
            com.google.android.gms.internal.ads.zzfu r1 = r7.zza
            r7 = 12
            r1.zzK(r7)
            int r1 = r1.zzp()
            if (r1 <= 0) goto L_0x022d
            int r15 = r15 + r1
            int r14 = r14 + 1
        L_0x022d:
            int r12 = r12 + 1
            r1 = r16
            r7 = 8
            goto L_0x020a
        L_0x0234:
            r16 = r1
            r1 = 0
            r13.zzh = r1
            r13.zzg = r1
            r13.zzf = r1
            com.google.android.gms.internal.ads.zzakr r1 = r13.zzb
            r1.zzd = r14
            r1.zze = r15
            int[] r7 = r1.zzg
            int r7 = r7.length
            if (r7 >= r14) goto L_0x0250
            long[] r7 = new long[r14]
            r1.zzf = r7
            int[] r7 = new int[r14]
            r1.zzg = r7
        L_0x0250:
            int[] r7 = r1.zzh
            int r7 = r7.length
            if (r7 >= r15) goto L_0x0269
            int r15 = r15 * 125
            int r15 = r15 / 100
            int[] r7 = new int[r15]
            r1.zzh = r7
            long[] r7 = new long[r15]
            r1.zzi = r7
            boolean[] r7 = new boolean[r15]
            r1.zzj = r7
            boolean[] r7 = new boolean[r15]
            r1.zzl = r7
        L_0x0269:
            r1 = 0
            r7 = 0
            r12 = 0
        L_0x026c:
            if (r1 >= r11) goto L_0x03fa
            java.lang.Object r15 = r5.get(r1)
            com.google.android.gms.internal.ads.zzajn r15 = (com.google.android.gms.internal.ads.zzajn) r15
            int r14 = r15.zzd
            if (r14 != r9) goto L_0x03cf
            int r14 = r7 + 1
            com.google.android.gms.internal.ads.zzfu r15 = r15.zza
            r9 = 8
            r15.zzK(r9)
            int r9 = r15.zzg()
            r19 = r5
            com.google.android.gms.internal.ads.zzaks r5 = r13.zzd
            com.google.android.gms.internal.ads.zzakp r5 = r5.zza
            r20 = r6
            com.google.android.gms.internal.ads.zzakr r6 = r13.zzb
            r21 = r11
            com.google.android.gms.internal.ads.zzajy r11 = r6.zza
            int r22 = com.google.android.gms.internal.ads.zzgd.zza
            r22 = r14
            int[] r14 = r6.zzg
            int r23 = r15.zzp()
            r14[r7] = r23
            long[] r14 = r6.zzf
            r23 = r2
            r24 = r3
            long r2 = r6.zzb
            r14[r7] = r2
            r25 = r9 & 1
            if (r25 == 0) goto L_0x02b8
            int r0 = r15.zzg()
            r25 = r1
            long r0 = (long) r0
            long r2 = r2 + r0
            r14[r7] = r2
            goto L_0x02ba
        L_0x02b8:
            r25 = r1
        L_0x02ba:
            r0 = r9 & 4
            if (r0 == 0) goto L_0x02c0
            r0 = 1
            goto L_0x02c1
        L_0x02c0:
            r0 = 0
        L_0x02c1:
            int r1 = r11.zzd
            if (r0 == 0) goto L_0x02c9
            int r1 = r15.zzg()
        L_0x02c9:
            r2 = r9 & 256(0x100, float:3.59E-43)
            r3 = r9 & 512(0x200, float:7.175E-43)
            r14 = r9 & 1024(0x400, float:1.435E-42)
            r9 = r9 & 2048(0x800, float:2.87E-42)
            r26 = r1
            long[] r1 = r5.zzh
            if (r1 == 0) goto L_0x0319
            r27 = r8
            int r8 = r1.length
            r28 = r10
            r10 = 1
            if (r8 != r10) goto L_0x0315
            long[] r8 = r5.zzi
            if (r8 != 0) goto L_0x02e4
            goto L_0x0315
        L_0x02e4:
            r10 = 0
            r29 = r1[r10]
            r17 = 0
            int r1 = (r29 > r17 ? 1 : (r29 == r17 ? 0 : -1))
            if (r1 != 0) goto L_0x02f1
            r10 = r0
            r29 = r9
            goto L_0x030d
        L_0x02f1:
            r31 = r8[r10]
            long r33 = r29 + r31
            r1 = r9
            long r8 = r5.zzd
            java.math.RoundingMode r39 = java.math.RoundingMode.FLOOR
            r35 = 1000000(0xf4240, double:4.940656E-318)
            r37 = r8
            long r8 = com.google.android.gms.internal.ads.zzgd.zzt(r33, r35, r37, r39)
            r10 = r0
            r29 = r1
            long r0 = r5.zze
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x030d
            goto L_0x0320
        L_0x030d:
            long[] r0 = r5.zzi
            r1 = 0
            r8 = r0[r1]
            r17 = r8
            goto L_0x0322
        L_0x0315:
            r10 = r0
            r29 = r9
            goto L_0x0320
        L_0x0319:
            r27 = r8
            r29 = r9
            r28 = r10
            r10 = r0
        L_0x0320:
            r17 = 0
        L_0x0322:
            int[] r0 = r6.zzh
            long[] r1 = r6.zzi
            boolean[] r8 = r6.zzj
            int[] r9 = r6.zzg
            r7 = r9[r7]
            int r7 = r7 + r12
            r9 = r4
            long r4 = r5.zzc
            r37 = r8
            r38 = r9
            long r8 = r6.zzp
        L_0x0336:
            if (r12 >= r7) goto L_0x03c6
            if (r2 == 0) goto L_0x0343
            int r30 = r15.zzg()
            r39 = r2
            r2 = r30
            goto L_0x0347
        L_0x0343:
            r39 = r2
            int r2 = r11.zzb
        L_0x0347:
            zza(r2)
            if (r3 == 0) goto L_0x0355
            int r30 = r15.zzg()
            r40 = r3
            r3 = r30
            goto L_0x0359
        L_0x0355:
            r40 = r3
            int r3 = r11.zzc
        L_0x0359:
            zza(r3)
            if (r14 == 0) goto L_0x0367
            int r30 = r15.zzg()
            r41 = r7
            r7 = r30
            goto L_0x0374
        L_0x0367:
            r41 = r7
            if (r12 != 0) goto L_0x0372
            if (r10 == 0) goto L_0x0371
            r7 = r26
            r12 = 0
            goto L_0x0374
        L_0x0371:
            r12 = 0
        L_0x0372:
            int r7 = r11.zzd
        L_0x0374:
            if (r29 == 0) goto L_0x0381
            int r30 = r15.zzg()
            r42 = r10
            r43 = r11
            r10 = r30
            goto L_0x0386
        L_0x0381:
            r42 = r10
            r43 = r11
            r10 = 0
        L_0x0386:
            long r10 = (long) r10
            long r10 = r10 + r8
            long r30 = r10 - r17
            r32 = 1000000(0xf4240, double:4.940656E-318)
            java.math.RoundingMode r36 = java.math.RoundingMode.FLOOR
            r34 = r4
            long r10 = com.google.android.gms.internal.ads.zzgd.zzt(r30, r32, r34, r36)
            r1[r12] = r10
            r30 = r4
            boolean r4 = r6.zzq
            if (r4 != 0) goto L_0x03a4
            com.google.android.gms.internal.ads.zzaks r4 = r13.zzd
            long r4 = r4.zzh
            long r10 = r10 + r4
            r1[r12] = r10
        L_0x03a4:
            r0[r12] = r3
            r3 = 16
            int r4 = r7 >> 16
            r3 = 1
            r4 = r4 & r3
            r4 = r4 ^ r3
            if (r3 == r4) goto L_0x03b1
            r3 = 0
            goto L_0x03b2
        L_0x03b1:
            r3 = 1
        L_0x03b2:
            r37[r12] = r3
            long r2 = (long) r2
            long r8 = r8 + r2
            int r12 = r12 + 1
            r4 = r30
            r2 = r39
            r3 = r40
            r7 = r41
            r10 = r42
            r11 = r43
            goto L_0x0336
        L_0x03c6:
            r41 = r7
            r6.zzp = r8
            r7 = r22
            r12 = r41
            goto L_0x03e1
        L_0x03cf:
            r25 = r1
            r23 = r2
            r24 = r3
            r38 = r4
            r19 = r5
            r20 = r6
            r27 = r8
            r28 = r10
            r21 = r11
        L_0x03e1:
            int r1 = r25 + 1
            r0 = r44
            r5 = r19
            r6 = r20
            r11 = r21
            r2 = r23
            r3 = r24
            r8 = r27
            r10 = r28
            r4 = r38
            r9 = 1953658222(0x7472756e, float:7.683823E31)
            goto L_0x026c
        L_0x03fa:
            r23 = r2
            r24 = r3
            r38 = r4
            r20 = r6
            r27 = r8
            r28 = r10
            com.google.android.gms.internal.ads.zzaks r0 = r13.zzd
            com.google.android.gms.internal.ads.zzakp r0 = r0.zza
            r1 = r38
            com.google.android.gms.internal.ads.zzajy r2 = r1.zza
            r2.getClass()
            int r2 = r2.zza
            com.google.android.gms.internal.ads.zzakq r0 = r0.zza(r2)
            r2 = 1935763834(0x7361697a, float:1.785898E31)
            r10 = r28
            com.google.android.gms.internal.ads.zzajn r2 = r10.zzb(r2)
            if (r2 == 0) goto L_0x0494
            r0.getClass()
            com.google.android.gms.internal.ads.zzfu r2 = r2.zza
            r3 = 8
            r2.zzK(r3)
            int r4 = r2.zzg()
            r5 = 1
            r4 = r4 & r5
            if (r4 != r5) goto L_0x0437
            r2.zzL(r3)
        L_0x0437:
            int r3 = r2.zzm()
            int r4 = r2.zzp()
            int r5 = r1.zze
            if (r4 > r5) goto L_0x0478
            int r5 = r0.zzd
            if (r3 != 0) goto L_0x045e
            boolean[] r3 = r1.zzl
            r6 = 0
            r7 = 0
        L_0x044b:
            if (r6 >= r4) goto L_0x045c
            int r8 = r2.zzm()
            int r7 = r7 + r8
            if (r8 <= r5) goto L_0x0456
            r8 = 1
            goto L_0x0457
        L_0x0456:
            r8 = 0
        L_0x0457:
            r3[r6] = r8
            int r6 = r6 + 1
            goto L_0x044b
        L_0x045c:
            r5 = 0
            goto L_0x046b
        L_0x045e:
            if (r3 <= r5) goto L_0x0462
            r2 = 1
            goto L_0x0463
        L_0x0462:
            r2 = 0
        L_0x0463:
            int r7 = r3 * r4
            boolean[] r3 = r1.zzl
            r5 = 0
            java.util.Arrays.fill(r3, r5, r4, r2)
        L_0x046b:
            boolean[] r2 = r1.zzl
            int r3 = r1.zze
            java.util.Arrays.fill(r2, r4, r3, r5)
            if (r7 <= 0) goto L_0x0494
            r1.zza(r7)
            goto L_0x0494
        L_0x0478:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Saiz sample count "
            r0.<init>(r1)
            r0.append(r4)
            java.lang.String r1 = " is greater than fragment sample count"
            r0.append(r1)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            r1 = 0
            com.google.android.gms.internal.ads.zzch r0 = com.google.android.gms.internal.ads.zzch.zza(r0, r1)
            throw r0
        L_0x0494:
            r2 = 1935763823(0x7361696f, float:1.7858967E31)
            com.google.android.gms.internal.ads.zzajn r2 = r10.zzb(r2)
            if (r2 == 0) goto L_0x04df
            com.google.android.gms.internal.ads.zzfu r2 = r2.zza
            r3 = 8
            r2.zzK(r3)
            int r4 = r2.zzg()
            r5 = r4 & 1
            r6 = 1
            if (r5 != r6) goto L_0x04b0
            r2.zzL(r3)
        L_0x04b0:
            int r3 = r2.zzp()
            if (r3 != r6) goto L_0x04cb
            int r3 = com.google.android.gms.internal.ads.zzajo.zze(r4)
            long r4 = r1.zzc
            if (r3 != 0) goto L_0x04c3
            long r2 = r2.zzu()
            goto L_0x04c7
        L_0x04c3:
            long r2 = r2.zzv()
        L_0x04c7:
            long r4 = r4 + r2
            r1.zzc = r4
            goto L_0x04df
        L_0x04cb:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unexpected saio entry count: "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            r2 = 0
            com.google.android.gms.internal.ads.zzch r0 = com.google.android.gms.internal.ads.zzch.zza(r0, r2)
            throw r0
        L_0x04df:
            r2 = 0
            r3 = 1936027235(0x73656e63, float:1.8177412E31)
            com.google.android.gms.internal.ads.zzajn r3 = r10.zzb(r3)
            if (r3 == 0) goto L_0x04ef
            com.google.android.gms.internal.ads.zzfu r3 = r3.zza
            r4 = 0
            zzi(r3, r4, r1)
        L_0x04ef:
            if (r0 == 0) goto L_0x04f6
            java.lang.String r0 = r0.zzb
            r30 = r0
            goto L_0x04f8
        L_0x04f6:
            r30 = r2
        L_0x04f8:
            r0 = r2
            r3 = r0
            r4 = 0
        L_0x04fb:
            java.util.List r5 = r10.zzb
            int r5 = r5.size()
            if (r4 >= r5) goto L_0x0538
            java.util.List r5 = r10.zzb
            java.lang.Object r5 = r5.get(r4)
            com.google.android.gms.internal.ads.zzajn r5 = (com.google.android.gms.internal.ads.zzajn) r5
            com.google.android.gms.internal.ads.zzfu r6 = r5.zza
            int r5 = r5.zzd
            r7 = 1935828848(0x73626770, float:1.7937577E31)
            r8 = 1936025959(0x73656967, float:1.817587E31)
            if (r5 != r7) goto L_0x0524
            r7 = 12
            r6.zzK(r7)
            int r5 = r6.zzg()
            if (r5 != r8) goto L_0x0535
            r0 = r6
            goto L_0x0535
        L_0x0524:
            r7 = 12
            r9 = 1936158820(0x73677064, float:1.8336489E31)
            if (r5 != r9) goto L_0x0535
            r6.zzK(r7)
            int r5 = r6.zzg()
            if (r5 != r8) goto L_0x0535
            r3 = r6
        L_0x0535:
            int r4 = r4 + 1
            goto L_0x04fb
        L_0x0538:
            r7 = 12
            if (r0 == 0) goto L_0x05dd
            if (r3 != 0) goto L_0x0540
            goto L_0x05dd
        L_0x0540:
            r4 = 8
            r0.zzK(r4)
            int r5 = r0.zzg()
            int r5 = com.google.android.gms.internal.ads.zzajo.zze(r5)
            r6 = 4
            r0.zzL(r6)
            r8 = 1
            if (r5 != r8) goto L_0x0557
            r0.zzL(r6)
        L_0x0557:
            int r0 = r0.zzg()
            if (r0 != r8) goto L_0x05d6
            r3.zzK(r4)
            int r0 = r3.zzg()
            int r0 = com.google.android.gms.internal.ads.zzajo.zze(r0)
            r3.zzL(r6)
            if (r0 != r8) goto L_0x057f
            long r4 = r3.zzu()
            r8 = 0
            int r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x0578
            goto L_0x0585
        L_0x0578:
            java.lang.String r0 = "Variable length description in sgpd found (unsupported)"
            com.google.android.gms.internal.ads.zzch r0 = com.google.android.gms.internal.ads.zzch.zzc(r0)
            throw r0
        L_0x057f:
            r4 = 2
            if (r0 < r4) goto L_0x0585
            r3.zzL(r6)
        L_0x0585:
            long r4 = r3.zzu()
            r8 = 1
            int r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r0 != 0) goto L_0x05cf
            r0 = 1
            r3.zzL(r0)
            int r4 = r3.zzm()
            r5 = r4 & 240(0xf0, float:3.36E-43)
            int r33 = r5 >> 4
            r34 = r4 & 15
            int r4 = r3.zzm()
            if (r4 != r0) goto L_0x05de
            int r31 = r3.zzm()
            r4 = 16
            byte[] r5 = new byte[r4]
            r6 = 0
            r3.zzG(r5, r6, r4)
            if (r31 != 0) goto L_0x05bd
            int r2 = r3.zzm()
            byte[] r9 = new byte[r2]
            r3.zzG(r9, r6, r2)
            r35 = r9
            goto L_0x05bf
        L_0x05bd:
            r35 = r2
        L_0x05bf:
            r1.zzk = r0
            com.google.android.gms.internal.ads.zzakq r2 = new com.google.android.gms.internal.ads.zzakq
            r29 = 1
            r28 = r2
            r32 = r5
            r28.<init>(r29, r30, r31, r32, r33, r34, r35)
            r1.zzm = r2
            goto L_0x05de
        L_0x05cf:
            java.lang.String r0 = "Entry count in sgpd != 1 (unsupported)."
            com.google.android.gms.internal.ads.zzch r0 = com.google.android.gms.internal.ads.zzch.zzc(r0)
            throw r0
        L_0x05d6:
            java.lang.String r0 = "Entry count in sbgp != 1 (unsupported)."
            com.google.android.gms.internal.ads.zzch r0 = com.google.android.gms.internal.ads.zzch.zzc(r0)
            throw r0
        L_0x05dd:
            r0 = 1
        L_0x05de:
            java.util.List r2 = r10.zzb
            int r2 = r2.size()
            r3 = 0
        L_0x05e5:
            if (r3 >= r2) goto L_0x061d
            java.util.List r4 = r10.zzb
            java.lang.Object r4 = r4.get(r3)
            com.google.android.gms.internal.ads.zzajn r4 = (com.google.android.gms.internal.ads.zzajn) r4
            int r5 = r4.zzd
            r6 = 1970628964(0x75756964, float:3.1109627E32)
            if (r5 != r6) goto L_0x0611
            com.google.android.gms.internal.ads.zzfu r4 = r4.zza
            r5 = 8
            r4.zzK(r5)
            r8 = r24
            r6 = 0
            r9 = 16
            r4.zzG(r8, r6, r9)
            byte[] r11 = zzb
            boolean r11 = java.util.Arrays.equals(r8, r11)
            if (r11 == 0) goto L_0x0618
            zzi(r4, r9, r1)
            goto L_0x0618
        L_0x0611:
            r8 = r24
            r5 = 8
            r6 = 0
            r9 = 16
        L_0x0618:
            int r3 = r3 + 1
            r24 = r8
            goto L_0x05e5
        L_0x061d:
            r8 = r24
            r5 = 8
            r6 = 0
            goto L_0x0631
        L_0x0623:
            r16 = r1
            r23 = r2
            r20 = r6
            r5 = r7
            r27 = r8
            r0 = 1
            r6 = 0
            r7 = 12
            r8 = r3
        L_0x0631:
            int r1 = r27 + 1
            r0 = r44
            r7 = r5
            r3 = r8
            r6 = r20
            r2 = r23
            r8 = r1
            r1 = r16
            goto L_0x014d
        L_0x0640:
            r1 = r2
            r2 = 0
            r6 = 0
            java.util.List r0 = r1.zzb
            com.google.android.gms.internal.ads.zzae r0 = zzg(r0)
            r3 = r44
            if (r0 == 0) goto L_0x0691
            android.util.SparseArray r1 = r3.zzf
            int r1 = r1.size()
            r4 = r6
        L_0x0654:
            if (r4 >= r1) goto L_0x0691
            android.util.SparseArray r5 = r3.zzf
            java.lang.Object r5 = r5.valueAt(r4)
            com.google.android.gms.internal.ads.zzakc r5 = (com.google.android.gms.internal.ads.zzakc) r5
            com.google.android.gms.internal.ads.zzaks r7 = r5.zzd
            com.google.android.gms.internal.ads.zzakp r7 = r7.zza
            com.google.android.gms.internal.ads.zzakr r8 = r5.zzb
            com.google.android.gms.internal.ads.zzajy r8 = r8.zza
            int r9 = com.google.android.gms.internal.ads.zzgd.zza
            int r8 = r8.zza
            com.google.android.gms.internal.ads.zzakq r7 = r7.zza(r8)
            if (r7 == 0) goto L_0x0673
            java.lang.String r7 = r7.zzb
            goto L_0x0674
        L_0x0673:
            r7 = r2
        L_0x0674:
            com.google.android.gms.internal.ads.zzae r7 = r0.zzb(r7)
            com.google.android.gms.internal.ads.zzaks r8 = r5.zzd
            com.google.android.gms.internal.ads.zzakp r8 = r8.zza
            com.google.android.gms.internal.ads.zzan r8 = r8.zzf
            com.google.android.gms.internal.ads.zzal r8 = r8.zzb()
            r8.zzE(r7)
            com.google.android.gms.internal.ads.zzan r7 = r8.zzad()
            com.google.android.gms.internal.ads.zzafa r5 = r5.zza
            r5.zzl(r7)
            int r4 = r4 + 1
            goto L_0x0654
        L_0x0691:
            long r0 = r3.zzx
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x06ea
            android.util.SparseArray r0 = r3.zzf
            int r0 = r0.size()
            r10 = r6
        L_0x06a3:
            if (r10 >= r0) goto L_0x06cd
            android.util.SparseArray r1 = r3.zzf
            java.lang.Object r1 = r1.valueAt(r10)
            com.google.android.gms.internal.ads.zzakc r1 = (com.google.android.gms.internal.ads.zzakc) r1
            long r4 = r3.zzx
            int r2 = r1.zzf
        L_0x06b1:
            com.google.android.gms.internal.ads.zzakr r6 = r1.zzb
            int r7 = r6.zze
            if (r2 >= r7) goto L_0x06ca
            long[] r7 = r6.zzi
            r8 = r7[r2]
            int r7 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r7 > 0) goto L_0x06ca
            boolean[] r6 = r6.zzj
            boolean r6 = r6[r2]
            if (r6 == 0) goto L_0x06c7
            r1.zzi = r2
        L_0x06c7:
            int r2 = r2 + 1
            goto L_0x06b1
        L_0x06ca:
            int r10 = r10 + 1
            goto L_0x06a3
        L_0x06cd:
            r1 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r3.zzx = r1
            goto L_0x06ea
        L_0x06d5:
            r3 = r0
            r1 = r2
            java.util.ArrayDeque r0 = r3.zzn
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x06ea
            java.util.ArrayDeque r0 = r3.zzn
            java.lang.Object r0 = r0.peek()
            com.google.android.gms.internal.ads.zzajm r0 = (com.google.android.gms.internal.ads.zzajm) r0
            r0.zzc(r1)
        L_0x06ea:
            r0 = r3
            goto L_0x0002
        L_0x06ed:
            r3 = r0
            r44.zzh()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzj(long):void");
    }

    private static final zzajy zzk(SparseArray sparseArray, int i) {
        if (sparseArray.size() == 1) {
            return (zzajy) sparseArray.valueAt(0);
        }
        zzajy zzajy = (zzajy) sparseArray.get(i);
        zzajy.getClass();
        return zzajy;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v55, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v20, resolved type: com.google.android.gms.internal.ads.zzakc} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v64, resolved type: com.google.android.gms.internal.ads.zzakc} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzb(com.google.android.gms.internal.ads.zzadv r34, com.google.android.gms.internal.ads.zzaeq r35) throws java.io.IOException {
        /*
            r33 = this;
            r0 = r33
            r1 = r34
        L_0x0004:
            int r2 = r0.zzq
            r3 = 1701671783(0x656d7367, float:7.0083103E22)
            r4 = 1936286840(0x73696478, float:1.8491255E31)
            r5 = 2
            r7 = 0
            r8 = 1
            r9 = 0
            if (r2 == 0) goto L_0x0543
            java.lang.String r10 = "FragmentedMp4Extractor"
            if (r2 == r8) goto L_0x02fa
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r12 = 3
            if (r2 == r5) goto L_0x029a
            com.google.android.gms.internal.ads.zzakc r2 = r0.zzA
            if (r2 != 0) goto L_0x0099
            android.util.SparseArray r2 = r0.zzf
            int r13 = r2.size()
            r14 = r3
            r3 = r7
            r4 = r9
        L_0x002b:
            if (r4 >= r13) goto L_0x0061
            java.lang.Object r16 = r2.valueAt(r4)
            r5 = r16
            com.google.android.gms.internal.ads.zzakc r5 = (com.google.android.gms.internal.ads.zzakc) r5
            boolean r16 = r5.zzl
            if (r16 != 0) goto L_0x0043
            int r11 = r5.zzf
            com.google.android.gms.internal.ads.zzaks r6 = r5.zzd
            int r6 = r6.zzb
            if (r11 == r6) goto L_0x005d
        L_0x0043:
            boolean r6 = r5.zzl
            if (r6 == 0) goto L_0x0052
            int r6 = r5.zzh
            com.google.android.gms.internal.ads.zzakr r11 = r5.zzb
            int r11 = r11.zzd
            if (r6 != r11) goto L_0x0052
            goto L_0x005d
        L_0x0052:
            long r18 = r5.zzd()
            int r6 = (r18 > r14 ? 1 : (r18 == r14 ? 0 : -1))
            if (r6 >= 0) goto L_0x005d
            r3 = r5
            r14 = r18
        L_0x005d:
            int r4 = r4 + 1
            r5 = 2
            goto L_0x002b
        L_0x0061:
            if (r3 != 0) goto L_0x007e
            long r2 = r0.zzv
            long r4 = r34.zzf()
            long r2 = r2 - r4
            int r2 = (int) r2
            if (r2 < 0) goto L_0x0077
            r3 = r1
            com.google.android.gms.internal.ads.zzadi r3 = (com.google.android.gms.internal.ads.zzadi) r3
            r3.zzo(r2, r9)
            r33.zzh()
            goto L_0x0004
        L_0x0077:
            java.lang.String r1 = "Offset to end of mdat was negative."
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zza(r1, r7)
            throw r1
        L_0x007e:
            long r4 = r3.zzd()
            long r13 = r34.zzf()
            long r4 = r4 - r13
            int r2 = (int) r4
            if (r2 >= 0) goto L_0x0090
            java.lang.String r2 = "Ignoring negative offset to sample data."
            com.google.android.gms.internal.ads.zzfk.zzf(r10, r2)
            r2 = r9
        L_0x0090:
            r4 = r1
            com.google.android.gms.internal.ads.zzadi r4 = (com.google.android.gms.internal.ads.zzadi) r4
            r4.zzo(r2, r9)
            r0.zzA = r3
            r2 = r3
        L_0x0099:
            int r3 = r0.zzq
            r4 = 6
            if (r3 != r12) goto L_0x0131
            int r3 = r2.zzb()
            r0.zzB = r3
            int r5 = r2.zzf
            int r6 = r2.zzi
            if (r5 >= r6) goto L_0x00de
            com.google.android.gms.internal.ads.zzadi r1 = (com.google.android.gms.internal.ads.zzadi) r1
            r1.zzo(r3, r9)
            com.google.android.gms.internal.ads.zzakq r1 = r2.zzf()
            if (r1 != 0) goto L_0x00b6
            goto L_0x00d3
        L_0x00b6:
            com.google.android.gms.internal.ads.zzakr r3 = r2.zzb
            com.google.android.gms.internal.ads.zzfu r3 = r3.zzn
            int r1 = r1.zzd
            if (r1 == 0) goto L_0x00c1
            r3.zzL(r1)
        L_0x00c1:
            com.google.android.gms.internal.ads.zzakr r1 = r2.zzb
            int r5 = r2.zzf
            boolean r1 = r1.zzb(r5)
            if (r1 == 0) goto L_0x00d3
            int r1 = r3.zzq()
            int r1 = r1 * r4
            r3.zzL(r1)
        L_0x00d3:
            boolean r1 = r2.zzk()
            if (r1 != 0) goto L_0x00db
            r0.zzA = r7
        L_0x00db:
            r1 = r12
            goto L_0x0296
        L_0x00de:
            com.google.android.gms.internal.ads.zzaks r5 = r2.zzd
            com.google.android.gms.internal.ads.zzakp r5 = r5.zza
            int r5 = r5.zzg
            if (r5 != r8) goto L_0x00f2
            int r3 = r3 + -8
            r0.zzB = r3
            r3 = r1
            com.google.android.gms.internal.ads.zzadi r3 = (com.google.android.gms.internal.ads.zzadi) r3
            r5 = 8
            r3.zzo(r5, r9)
        L_0x00f2:
            com.google.android.gms.internal.ads.zzaks r3 = r2.zzd
            com.google.android.gms.internal.ads.zzakp r3 = r3.zza
            com.google.android.gms.internal.ads.zzan r3 = r3.zzf
            java.lang.String r5 = "audio/ac4"
            java.lang.String r3 = r3.zzn
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x011f
            int r3 = r0.zzB
            r5 = 7
            int r3 = r2.zzc(r3, r5)
            r0.zzC = r3
            int r3 = r0.zzB
            com.google.android.gms.internal.ads.zzfu r6 = r0.zzk
            com.google.android.gms.internal.ads.zzacw.zzb(r3, r6)
            com.google.android.gms.internal.ads.zzafa r3 = r2.zza
            com.google.android.gms.internal.ads.zzfu r6 = r0.zzk
            r3.zzq(r6, r5)
            int r3 = r0.zzC
            int r3 = r3 + r5
            r0.zzC = r3
            goto L_0x0127
        L_0x011f:
            int r3 = r0.zzB
            int r3 = r2.zzc(r3, r9)
            r0.zzC = r3
        L_0x0127:
            int r5 = r0.zzB
            int r5 = r5 + r3
            r0.zzB = r5
            r3 = 4
            r0.zzq = r3
            r0.zzD = r9
        L_0x0131:
            com.google.android.gms.internal.ads.zzaks r3 = r2.zzd
            com.google.android.gms.internal.ads.zzakp r3 = r3.zza
            com.google.android.gms.internal.ads.zzafa r5 = r2.zza
            long r10 = r2.zze()
            int r6 = r3.zzj
            if (r6 != 0) goto L_0x0150
        L_0x013f:
            int r3 = r0.zzC
            int r4 = r0.zzB
            if (r3 >= r4) goto L_0x0234
            int r4 = r4 - r3
            int r3 = r5.zzf(r1, r4, r9)
            int r4 = r0.zzC
            int r4 = r4 + r3
            r0.zzC = r4
            goto L_0x013f
        L_0x0150:
            com.google.android.gms.internal.ads.zzfu r13 = r0.zzh
            byte[] r13 = r13.zzM()
            r13[r9] = r9
            r13[r8] = r9
            r14 = 2
            r13[r14] = r9
            int r14 = r6 + 1
            r15 = 4
            int r6 = 4 - r6
        L_0x0162:
            int r15 = r0.zzC
            int r12 = r0.zzB
            if (r15 >= r12) goto L_0x0234
            int r12 = r0.zzD
            java.lang.String r15 = "video/hevc"
            if (r12 != 0) goto L_0x01d5
            r12 = r1
            com.google.android.gms.internal.ads.zzadi r12 = (com.google.android.gms.internal.ads.zzadi) r12
            r12.zzn(r13, r6, r14, r9)
            com.google.android.gms.internal.ads.zzfu r12 = r0.zzh
            r12.zzK(r9)
            com.google.android.gms.internal.ads.zzfu r12 = r0.zzh
            int r12 = r12.zzg()
            if (r12 <= 0) goto L_0x01ce
            int r12 = r12 + -1
            r0.zzD = r12
            com.google.android.gms.internal.ads.zzfu r12 = r0.zzg
            r12.zzK(r9)
            com.google.android.gms.internal.ads.zzfu r12 = r0.zzg
            r9 = 4
            r5.zzq(r12, r9)
            com.google.android.gms.internal.ads.zzfu r12 = r0.zzh
            r5.zzq(r12, r8)
            com.google.android.gms.internal.ads.zzafa[] r12 = r0.zzH
            int r12 = r12.length
            if (r12 <= 0) goto L_0x01bf
            com.google.android.gms.internal.ads.zzan r12 = r3.zzf
            java.lang.String r12 = r12.zzn
            byte r17 = r13[r9]
            byte[] r9 = com.google.android.gms.internal.ads.zzgr.zza
            java.lang.String r9 = "video/avc"
            boolean r9 = r9.equals(r12)
            if (r9 == 0) goto L_0x01b1
            r9 = r17 & 31
            if (r9 == r4) goto L_0x01af
            goto L_0x01b1
        L_0x01af:
            r9 = r8
            goto L_0x01c0
        L_0x01b1:
            boolean r9 = r15.equals(r12)
            if (r9 == 0) goto L_0x01bf
            r9 = r17 & 126(0x7e, float:1.77E-43)
            int r9 = r9 >> r8
            r12 = 39
            if (r9 != r12) goto L_0x01bf
            goto L_0x01af
        L_0x01bf:
            r9 = 0
        L_0x01c0:
            r0.zzE = r9
            int r9 = r0.zzC
            int r9 = r9 + 5
            r0.zzC = r9
            int r9 = r0.zzB
            int r9 = r9 + r6
            r0.zzB = r9
            goto L_0x0230
        L_0x01ce:
            java.lang.String r1 = "Invalid NAL length"
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zza(r1, r7)
            throw r1
        L_0x01d5:
            boolean r9 = r0.zzE
            if (r9 == 0) goto L_0x021e
            com.google.android.gms.internal.ads.zzfu r9 = r0.zzi
            r9.zzH(r12)
            com.google.android.gms.internal.ads.zzfu r9 = r0.zzi
            byte[] r9 = r9.zzM()
            int r12 = r0.zzD
            r4 = r1
            com.google.android.gms.internal.ads.zzadi r4 = (com.google.android.gms.internal.ads.zzadi) r4
            r8 = 0
            r4.zzn(r9, r8, r12, r8)
            com.google.android.gms.internal.ads.zzfu r4 = r0.zzi
            int r8 = r0.zzD
            r5.zzq(r4, r8)
            int r4 = r0.zzD
            com.google.android.gms.internal.ads.zzfu r8 = r0.zzi
            byte[] r9 = r8.zzM()
            int r8 = r8.zze()
            int r8 = com.google.android.gms.internal.ads.zzgr.zzb(r9, r8)
            com.google.android.gms.internal.ads.zzfu r9 = r0.zzi
            com.google.android.gms.internal.ads.zzan r12 = r3.zzf
            java.lang.String r12 = r12.zzn
            boolean r12 = r15.equals(r12)
            r9.zzK(r12)
            com.google.android.gms.internal.ads.zzfu r9 = r0.zzi
            r9.zzJ(r8)
            com.google.android.gms.internal.ads.zzfu r8 = r0.zzi
            com.google.android.gms.internal.ads.zzafa[] r9 = r0.zzH
            com.google.android.gms.internal.ads.zzadf.zza(r10, r8, r9)
            goto L_0x0224
        L_0x021e:
            r4 = 0
            int r8 = r5.zzf(r1, r12, r4)
            r4 = r8
        L_0x0224:
            int r8 = r0.zzC
            int r8 = r8 + r4
            r0.zzC = r8
            int r8 = r0.zzD
            int r8 = r8 - r4
            r0.zzD = r8
            r4 = 6
            r8 = 1
        L_0x0230:
            r9 = 0
            r12 = 3
            goto L_0x0162
        L_0x0234:
            int r20 = r2.zza()
            com.google.android.gms.internal.ads.zzakq r1 = r2.zzf()
            if (r1 == 0) goto L_0x0243
            com.google.android.gms.internal.ads.zzaez r1 = r1.zzc
            r23 = r1
            goto L_0x0245
        L_0x0243:
            r23 = r7
        L_0x0245:
            int r1 = r0.zzB
            r22 = 0
            r17 = r5
            r18 = r10
            r21 = r1
            r17.zzs(r18, r20, r21, r22, r23)
        L_0x0252:
            java.util.ArrayDeque r1 = r0.zzo
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x028d
            java.util.ArrayDeque r1 = r0.zzo
            java.lang.Object r1 = r1.removeFirst()
            com.google.android.gms.internal.ads.zzakb r1 = (com.google.android.gms.internal.ads.zzakb) r1
            int r3 = r0.zzw
            int r4 = r1.zzc
            int r3 = r3 - r4
            r0.zzw = r3
            long r3 = r1.zza
            boolean r5 = r1.zzb
            if (r5 == 0) goto L_0x0270
            long r3 = r3 + r10
        L_0x0270:
            com.google.android.gms.internal.ads.zzafa[] r5 = r0.zzG
            int r6 = r5.length
            r8 = 0
        L_0x0274:
            if (r8 >= r6) goto L_0x0252
            r12 = r5[r8]
            int r9 = r1.zzc
            int r15 = r0.zzw
            r18 = 0
            r16 = 1
            r13 = r3
            r17 = r15
            r15 = r16
            r16 = r9
            r12.zzs(r13, r15, r16, r17, r18)
            int r8 = r8 + 1
            goto L_0x0274
        L_0x028d:
            boolean r1 = r2.zzk()
            if (r1 != 0) goto L_0x0295
            r0.zzA = r7
        L_0x0295:
            r1 = 3
        L_0x0296:
            r0.zzq = r1
            r1 = 0
            return r1
        L_0x029a:
            android.util.SparseArray r2 = r0.zzf
            int r2 = r2.size()
            r6 = r7
            r5 = 0
        L_0x02a2:
            if (r5 >= r2) goto L_0x02c5
            android.util.SparseArray r8 = r0.zzf
            java.lang.Object r8 = r8.valueAt(r5)
            com.google.android.gms.internal.ads.zzakc r8 = (com.google.android.gms.internal.ads.zzakc) r8
            com.google.android.gms.internal.ads.zzakr r8 = r8.zzb
            boolean r9 = r8.zzo
            if (r9 == 0) goto L_0x02c2
            long r8 = r8.zzc
            int r10 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r10 >= 0) goto L_0x02c2
            android.util.SparseArray r3 = r0.zzf
            java.lang.Object r3 = r3.valueAt(r5)
            r6 = r3
            com.google.android.gms.internal.ads.zzakc r6 = (com.google.android.gms.internal.ads.zzakc) r6
            r3 = r8
        L_0x02c2:
            int r5 = r5 + 1
            goto L_0x02a2
        L_0x02c5:
            if (r6 != 0) goto L_0x02cc
            r2 = 3
            r0.zzq = r2
            goto L_0x0004
        L_0x02cc:
            long r8 = r34.zzf()
            long r3 = r3 - r8
            int r2 = (int) r3
            if (r2 < 0) goto L_0x02f3
            r3 = r1
            com.google.android.gms.internal.ads.zzadi r3 = (com.google.android.gms.internal.ads.zzadi) r3
            r4 = 0
            r3.zzo(r2, r4)
            com.google.android.gms.internal.ads.zzakr r2 = r6.zzb
            com.google.android.gms.internal.ads.zzfu r5 = r2.zzn
            byte[] r6 = r5.zzM()
            int r5 = r5.zze()
            r3.zzn(r6, r4, r5, r4)
            com.google.android.gms.internal.ads.zzfu r3 = r2.zzn
            r3.zzK(r4)
            r2.zzo = r4
            goto L_0x0004
        L_0x02f3:
            java.lang.String r1 = "Offset to encryption data was negative."
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zza(r1, r7)
            throw r1
        L_0x02fa:
            long r5 = r0.zzs
            int r2 = (int) r5
            int r5 = r0.zzt
            int r2 = r2 - r5
            com.google.android.gms.internal.ads.zzfu r5 = r0.zzu
            if (r5 == 0) goto L_0x0533
            byte[] r6 = r5.zzM()
            r8 = r1
            com.google.android.gms.internal.ads.zzadi r8 = (com.google.android.gms.internal.ads.zzadi) r8
            r9 = 8
            r11 = 0
            r8.zzn(r6, r9, r2, r11)
            com.google.android.gms.internal.ads.zzajn r2 = new com.google.android.gms.internal.ads.zzajn
            int r6 = r0.zzr
            r2.<init>(r6, r5)
            long r5 = r34.zzf()
            java.util.ArrayDeque r8 = r0.zzn
            boolean r8 = r8.isEmpty()
            if (r8 != 0) goto L_0x0331
            java.util.ArrayDeque r3 = r0.zzn
            java.lang.Object r3 = r3.peek()
            com.google.android.gms.internal.ads.zzajm r3 = (com.google.android.gms.internal.ads.zzajm) r3
            r3.zzd(r2)
            goto L_0x053a
        L_0x0331:
            int r8 = r2.zzd
            if (r8 != r4) goto L_0x0405
            com.google.android.gms.internal.ads.zzfu r2 = r2.zza
            r3 = 8
            r2.zzK(r3)
            int r3 = r2.zzg()
            int r3 = com.google.android.gms.internal.ads.zzajo.zze(r3)
            r4 = 4
            r2.zzL(r4)
            long r19 = r2.zzu()
            if (r3 != 0) goto L_0x0357
            long r3 = r2.zzu()
            long r8 = r2.zzu()
            goto L_0x035f
        L_0x0357:
            long r3 = r2.zzv()
            long r8 = r2.zzv()
        L_0x035f:
            long r5 = r5 + r8
            r10 = 1000000(0xf4240, double:4.940656E-318)
            java.math.RoundingMode r14 = java.math.RoundingMode.FLOOR
            r8 = r3
            r12 = r19
            long r21 = com.google.android.gms.internal.ads.zzgd.zzt(r8, r10, r12, r14)
            r8 = 2
            r2.zzL(r8)
            int r15 = r2.zzq()
            int[] r14 = new int[r15]
            long[] r12 = new long[r15]
            long[] r13 = new long[r15]
            long[] r10 = new long[r15]
            r8 = r21
            r11 = 0
        L_0x037f:
            if (r11 >= r15) goto L_0x03d9
            int r17 = r2.zzg()
            r23 = -2147483648(0xffffffff80000000, float:-0.0)
            r23 = r17 & r23
            if (r23 != 0) goto L_0x03d1
            long r23 = r2.zzu()
            r25 = 2147483647(0x7fffffff, float:NaN)
            r17 = r17 & r25
            r14[r11] = r17
            r12[r11] = r5
            r10[r11] = r8
            long r3 = r3 + r23
            r23 = 1000000(0xf4240, double:4.940656E-318)
            java.math.RoundingMode r17 = java.math.RoundingMode.FLOOR
            r8 = r3
            r7 = r10
            r26 = r11
            r10 = r23
            r27 = r12
            r28 = r13
            r12 = r19
            r23 = r3
            r3 = r14
            r14 = r17
            long r8 = com.google.android.gms.internal.ads.zzgd.zzt(r8, r10, r12, r14)
            r10 = r7[r26]
            long r10 = r8 - r10
            r4 = r28
            r4[r26] = r10
            r10 = 4
            r2.zzL(r10)
            r11 = r3[r26]
            long r11 = (long) r11
            long r5 = r5 + r11
            int r11 = r26 + 1
            r14 = r3
            r13 = r4
            r10 = r7
            r3 = r23
            r12 = r27
            r7 = 0
            goto L_0x037f
        L_0x03d1:
            java.lang.String r1 = "Unhandled indirect reference"
            r2 = 0
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zza(r1, r2)
            throw r1
        L_0x03d9:
            r7 = r10
            r27 = r12
            r4 = r13
            r3 = r14
            java.lang.Long r2 = java.lang.Long.valueOf(r21)
            com.google.android.gms.internal.ads.zzadg r5 = new com.google.android.gms.internal.ads.zzadg
            r6 = r27
            r5.<init>(r3, r6, r4, r7)
            android.util.Pair r2 = android.util.Pair.create(r2, r5)
            java.lang.Object r3 = r2.first
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            r0.zzz = r3
            com.google.android.gms.internal.ads.zzadx r3 = r0.zzF
            java.lang.Object r2 = r2.second
            com.google.android.gms.internal.ads.zzaet r2 = (com.google.android.gms.internal.ads.zzaet) r2
            r3.zzO(r2)
            r2 = 1
            r0.zzI = r2
            goto L_0x053a
        L_0x0405:
            if (r8 != r3) goto L_0x053a
            com.google.android.gms.internal.ads.zzfu r2 = r2.zza
            com.google.android.gms.internal.ads.zzafa[] r3 = r0.zzG
            int r3 = r3.length
            if (r3 == 0) goto L_0x053a
            r3 = 8
            r2.zzK(r3)
            int r3 = r2.zzg()
            int r3 = com.google.android.gms.internal.ads.zzajo.zze(r3)
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r3 == 0) goto L_0x0474
            r6 = 1
            if (r3 == r6) goto L_0x0438
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "Skipping unsupported emsg version: "
            r2.<init>(r4)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r10, r2)
            goto L_0x053a
        L_0x0438:
            long r6 = r2.zzu()
            long r11 = r2.zzv()
            r13 = 1000000(0xf4240, double:4.940656E-318)
            java.math.RoundingMode r17 = java.math.RoundingMode.FLOOR
            r15 = r6
            long r8 = com.google.android.gms.internal.ads.zzgd.zzt(r11, r13, r15, r17)
            long r11 = r2.zzu()
            r13 = 1000(0x3e8, double:4.94E-321)
            java.math.RoundingMode r17 = java.math.RoundingMode.FLOOR
            long r6 = com.google.android.gms.internal.ads.zzgd.zzt(r11, r13, r15, r17)
            long r10 = r2.zzu()
            r3 = 0
            java.lang.String r12 = r2.zzx(r3)
            r12.getClass()
            java.lang.String r13 = r2.zzx(r3)
            r13.getClass()
            r28 = r6
            r30 = r10
            r26 = r12
            r27 = r13
            r13 = r8
            r8 = r4
            goto L_0x04ba
        L_0x0474:
            r3 = 0
            java.lang.String r12 = r2.zzx(r3)
            r12.getClass()
            java.lang.String r13 = r2.zzx(r3)
            r13.getClass()
            long r6 = r2.zzu()
            long r25 = r2.zzu()
            r27 = 1000000(0xf4240, double:4.940656E-318)
            java.math.RoundingMode r31 = java.math.RoundingMode.FLOOR
            r29 = r6
            long r8 = com.google.android.gms.internal.ads.zzgd.zzt(r25, r27, r29, r31)
            long r10 = r0.zzz
            int r3 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r3 == 0) goto L_0x049e
            long r10 = r10 + r8
            goto L_0x049f
        L_0x049e:
            r10 = r4
        L_0x049f:
            long r25 = r2.zzu()
            r27 = 1000(0x3e8, double:4.94E-321)
            java.math.RoundingMode r31 = java.math.RoundingMode.FLOOR
            r29 = r6
            long r6 = com.google.android.gms.internal.ads.zzgd.zzt(r25, r27, r29, r31)
            long r14 = r2.zzu()
            r28 = r6
            r26 = r12
            r27 = r13
            r30 = r14
            r13 = r10
        L_0x04ba:
            int r3 = r2.zzb()
            byte[] r3 = new byte[r3]
            int r6 = r2.zzb()
            r7 = 0
            r2.zzG(r3, r7, r6)
            com.google.android.gms.internal.ads.zzagt r2 = new com.google.android.gms.internal.ads.zzagt
            r25 = r2
            r32 = r3
            r25.<init>(r26, r27, r28, r30, r32)
            com.google.android.gms.internal.ads.zzagu r3 = r0.zzl
            com.google.android.gms.internal.ads.zzfu r6 = new com.google.android.gms.internal.ads.zzfu
            byte[] r2 = r3.zza(r2)
            r6.<init>((byte[]) r2)
            int r2 = r6.zzb()
            com.google.android.gms.internal.ads.zzafa[] r3 = r0.zzG
            int r7 = r3.length
            r10 = 0
        L_0x04e4:
            if (r10 >= r7) goto L_0x04f2
            r11 = r3[r10]
            r12 = 0
            r6.zzK(r12)
            r11.zzq(r6, r2)
            int r10 = r10 + 1
            goto L_0x04e4
        L_0x04f2:
            int r3 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r3 != 0) goto L_0x0507
            java.util.ArrayDeque r3 = r0.zzo
            com.google.android.gms.internal.ads.zzakb r4 = new com.google.android.gms.internal.ads.zzakb
            r5 = 1
            r4.<init>(r8, r5, r2)
            r3.addLast(r4)
            int r3 = r0.zzw
            int r3 = r3 + r2
            r0.zzw = r3
            goto L_0x053a
        L_0x0507:
            java.util.ArrayDeque r3 = r0.zzo
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x0520
            java.util.ArrayDeque r3 = r0.zzo
            com.google.android.gms.internal.ads.zzakb r4 = new com.google.android.gms.internal.ads.zzakb
            r5 = 0
            r4.<init>(r13, r5, r2)
            r3.addLast(r4)
            int r3 = r0.zzw
            int r3 = r3 + r2
            r0.zzw = r3
            goto L_0x053a
        L_0x0520:
            com.google.android.gms.internal.ads.zzafa[] r3 = r0.zzG
            int r4 = r3.length
            r5 = 0
        L_0x0524:
            if (r5 >= r4) goto L_0x053a
            r6 = r3[r5]
            r11 = 0
            r12 = 0
            r9 = 1
            r7 = r13
            r10 = r2
            r6.zzs(r7, r9, r10, r11, r12)
            int r5 = r5 + 1
            goto L_0x0524
        L_0x0533:
            r3 = r1
            com.google.android.gms.internal.ads.zzadi r3 = (com.google.android.gms.internal.ads.zzadi) r3
            r5 = 0
            r3.zzo(r2, r5)
        L_0x053a:
            long r2 = r34.zzf()
            r0.zzj(r2)
            goto L_0x0004
        L_0x0543:
            r5 = r9
            int r2 = r0.zzt
            if (r2 != 0) goto L_0x0570
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzm
            byte[] r2 = r2.zzM()
            r6 = 8
            r7 = 1
            boolean r2 = r1.zzn(r2, r5, r6, r7)
            if (r2 != 0) goto L_0x0559
            r1 = -1
            return r1
        L_0x0559:
            r0.zzt = r6
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzm
            r2.zzK(r5)
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzm
            long r5 = r2.zzu()
            r0.zzs = r5
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzm
            int r2 = r2.zzg()
            r0.zzr = r2
        L_0x0570:
            long r5 = r0.zzs
            r7 = 1
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x0595
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzm
            byte[] r2 = r2.zzM()
            r5 = r1
            com.google.android.gms.internal.ads.zzadi r5 = (com.google.android.gms.internal.ads.zzadi) r5
            r6 = 8
            r7 = 0
            r5.zzn(r2, r6, r6, r7)
            int r2 = r0.zzt
            int r2 = r2 + r6
            r0.zzt = r2
            com.google.android.gms.internal.ads.zzfu r2 = r0.zzm
            long r5 = r2.zzv()
            r0.zzs = r5
            goto L_0x05c8
        L_0x0595:
            r7 = 0
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x05c8
            long r5 = r34.zzd()
            r7 = -1
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x05b9
            java.util.ArrayDeque r2 = r0.zzn
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x05b8
            java.util.ArrayDeque r2 = r0.zzn
            java.lang.Object r2 = r2.peek()
            com.google.android.gms.internal.ads.zzajm r2 = (com.google.android.gms.internal.ads.zzajm) r2
            long r5 = r2.zza
            goto L_0x05b9
        L_0x05b8:
            r5 = r7
        L_0x05b9:
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 == 0) goto L_0x05c8
            long r7 = r34.zzf()
            long r5 = r5 - r7
            int r2 = r0.zzt
            long r7 = (long) r2
            long r5 = r5 + r7
            r0.zzs = r5
        L_0x05c8:
            long r5 = r0.zzs
            int r2 = r0.zzt
            long r7 = (long) r2
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 < 0) goto L_0x074d
            long r5 = r34.zzf()
            long r5 = r5 - r7
            int r2 = r0.zzr
            r7 = 1835295092(0x6d646174, float:4.4175247E27)
            r8 = 1836019558(0x6d6f6f66, float:4.6313494E27)
            if (r2 == r8) goto L_0x05e2
            if (r2 != r7) goto L_0x05f5
        L_0x05e2:
            boolean r2 = r0.zzI
            if (r2 != 0) goto L_0x05f5
            com.google.android.gms.internal.ads.zzadx r2 = r0.zzF
            com.google.android.gms.internal.ads.zzaes r9 = new com.google.android.gms.internal.ads.zzaes
            long r10 = r0.zzy
            r9.<init>(r10, r5)
            r2.zzO(r9)
            r2 = 1
            r0.zzI = r2
        L_0x05f5:
            int r2 = r0.zzr
            if (r2 != r8) goto L_0x0613
            android.util.SparseArray r2 = r0.zzf
            int r2 = r2.size()
            r9 = 0
        L_0x0600:
            if (r9 >= r2) goto L_0x0613
            android.util.SparseArray r10 = r0.zzf
            java.lang.Object r10 = r10.valueAt(r9)
            com.google.android.gms.internal.ads.zzakc r10 = (com.google.android.gms.internal.ads.zzakc) r10
            com.google.android.gms.internal.ads.zzakr r10 = r10.zzb
            r10.zzc = r5
            r10.zzb = r5
            int r9 = r9 + 1
            goto L_0x0600
        L_0x0613:
            int r2 = r0.zzr
            if (r2 != r7) goto L_0x0624
            r7 = 0
            r0.zzA = r7
            long r2 = r0.zzs
            long r5 = r5 + r2
            r0.zzv = r5
            r2 = 2
            r0.zzq = r2
            goto L_0x0004
        L_0x0624:
            r5 = 1836019574(0x6d6f6f76, float:4.631354E27)
            if (r2 == r5) goto L_0x0726
            r5 = 1953653099(0x7472616b, float:7.681346E31)
            if (r2 == r5) goto L_0x0726
            r5 = 1835297121(0x6d646961, float:4.4181236E27)
            if (r2 == r5) goto L_0x0726
            r5 = 1835626086(0x6d696e66, float:4.515217E27)
            if (r2 == r5) goto L_0x0726
            r5 = 1937007212(0x7374626c, float:1.9362132E31)
            if (r2 == r5) goto L_0x0726
            if (r2 == r8) goto L_0x0726
            r5 = 1953653094(0x74726166, float:7.6813435E31)
            if (r2 == r5) goto L_0x0726
            r5 = 1836475768(0x6d766578, float:4.7659988E27)
            if (r2 == r5) goto L_0x0726
            r5 = 1701082227(0x65647473, float:6.742798E22)
            if (r2 != r5) goto L_0x0650
            goto L_0x0726
        L_0x0650:
            r5 = 1751411826(0x68646c72, float:4.3148E24)
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            if (r2 == r5) goto L_0x06ef
            r5 = 1835296868(0x6d646864, float:4.418049E27)
            if (r2 == r5) goto L_0x06ef
            r5 = 1836476516(0x6d766864, float:4.7662196E27)
            if (r2 == r5) goto L_0x06ef
            if (r2 == r4) goto L_0x06ef
            r4 = 1937011556(0x73747364, float:1.9367383E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1937011827(0x73747473, float:1.9367711E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1668576371(0x63747473, float:4.5093966E21)
            if (r2 == r4) goto L_0x06ef
            r4 = 1937011555(0x73747363, float:1.9367382E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1937011578(0x7374737a, float:1.936741E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1937013298(0x73747a32, float:1.9369489E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1937007471(0x7374636f, float:1.9362445E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1668232756(0x636f3634, float:4.4126776E21)
            if (r2 == r4) goto L_0x06ef
            r4 = 1937011571(0x73747373, float:1.9367401E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1952867444(0x74666474, float:7.3014264E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1952868452(0x74666864, float:7.301914E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1953196132(0x746b6864, float:7.46037E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1953654136(0x74726578, float:7.6818474E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1953658222(0x7472756e, float:7.683823E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1886614376(0x70737368, float:3.013775E29)
            if (r2 == r4) goto L_0x06ef
            r4 = 1935763834(0x7361697a, float:1.785898E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1935763823(0x7361696f, float:1.7858967E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1936027235(0x73656e63, float:1.8177412E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1970628964(0x75756964, float:3.1109627E32)
            if (r2 == r4) goto L_0x06ef
            r4 = 1935828848(0x73626770, float:1.7937577E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1936158820(0x73677064, float:1.8336489E31)
            if (r2 == r4) goto L_0x06ef
            r4 = 1701606260(0x656c7374, float:6.9788014E22)
            if (r2 == r4) goto L_0x06ef
            r4 = 1835362404(0x6d656864, float:4.4373917E27)
            if (r2 == r4) goto L_0x06ef
            if (r2 != r3) goto L_0x06da
            goto L_0x06ef
        L_0x06da:
            long r2 = r0.zzs
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 > 0) goto L_0x06e8
            r2 = 0
            r0.zzu = r2
            r2 = 1
            r0.zzq = r2
            goto L_0x0004
        L_0x06e8:
            java.lang.String r1 = "Skipping atom with length > 2147483647 (unsupported)."
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zzc(r1)
            throw r1
        L_0x06ef:
            int r2 = r0.zzt
            r3 = 8
            if (r2 != r3) goto L_0x071f
            long r4 = r0.zzs
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 > 0) goto L_0x0718
            com.google.android.gms.internal.ads.zzfu r2 = new com.google.android.gms.internal.ads.zzfu
            long r4 = r0.zzs
            int r4 = (int) r4
            r2.<init>((int) r4)
            com.google.android.gms.internal.ads.zzfu r4 = r0.zzm
            byte[] r4 = r4.zzM()
            byte[] r5 = r2.zzM()
            r6 = 0
            java.lang.System.arraycopy(r4, r6, r5, r6, r3)
            r0.zzu = r2
            r2 = 1
            r0.zzq = r2
            goto L_0x0004
        L_0x0718:
            java.lang.String r1 = "Leaf atom with length > 2147483647 (unsupported)."
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zzc(r1)
            throw r1
        L_0x071f:
            java.lang.String r1 = "Leaf atom defines extended atom size (unsupported)."
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zzc(r1)
            throw r1
        L_0x0726:
            long r3 = r34.zzf()
            long r5 = r0.zzs
            long r3 = r3 + r5
            java.util.ArrayDeque r5 = r0.zzn
            com.google.android.gms.internal.ads.zzajm r6 = new com.google.android.gms.internal.ads.zzajm
            r7 = -8
            long r3 = r3 + r7
            r6.<init>(r2, r3)
            r5.push(r6)
            long r5 = r0.zzs
            int r2 = r0.zzt
            long r7 = (long) r2
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x0748
            r0.zzj(r3)
            goto L_0x0004
        L_0x0748:
            r33.zzh()
            goto L_0x0004
        L_0x074d:
            java.lang.String r1 = "Atom size less than header length (unsupported)."
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zzc(r1)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakd.zzb(com.google.android.gms.internal.ads.zzadv, com.google.android.gms.internal.ads.zzaeq):int");
    }

    public final /* synthetic */ List zzc() {
        return this.zzp;
    }

    public final void zzd(zzadx zzadx) {
        this.zzF = zzadx;
        zzh();
        zzafa[] zzafaArr = new zzafa[2];
        this.zzG = zzafaArr;
        int i = 0;
        zzafa[] zzafaArr2 = (zzafa[]) zzgd.zzP(zzafaArr, 0);
        this.zzG = zzafaArr2;
        for (zzafa zzl2 : zzafaArr2) {
            zzl2.zzl(zzc);
        }
        this.zzH = new zzafa[this.zze.size()];
        int i2 = 100;
        while (i < this.zzH.length) {
            int i3 = i2 + 1;
            zzafa zzw2 = this.zzF.zzw(i2, 3);
            zzw2.zzl((zzan) this.zze.get(i));
            this.zzH[i] = zzw2;
            i++;
            i2 = i3;
        }
    }

    public final void zze(long j, long j2) {
        int size = this.zzf.size();
        for (int i = 0; i < size; i++) {
            ((zzakc) this.zzf.valueAt(i)).zzi();
        }
        this.zzo.clear();
        this.zzw = 0;
        this.zzx = j2;
        this.zzn.clear();
        zzh();
    }

    public final boolean zzf(zzadv zzadv) throws IOException {
        zzaex zza2 = zzako.zza(zzadv);
        this.zzp = zza2 != null ? zzgbc.zzn(zza2) : zzgbc.zzm();
        return zza2 == null;
    }

    public zzakd(zzalt zzalt, int i) {
        this(zzalt, 32, (zzgb) null, (zzakp) null, zzgbc.zzm(), (zzafa) null);
    }

    public zzakd(zzalt zzalt, int i, zzgb zzgb, zzakp zzakp, List list, zzafa zzafa) {
        this.zzd = zzalt;
        this.zze = Collections.unmodifiableList(list);
        this.zzl = new zzagu();
        this.zzm = new zzfu(16);
        this.zzg = new zzfu(zzgr.zza);
        this.zzh = new zzfu(5);
        this.zzi = new zzfu();
        byte[] bArr = new byte[16];
        this.zzj = bArr;
        this.zzk = new zzfu(bArr);
        this.zzn = new ArrayDeque();
        this.zzo = new ArrayDeque();
        this.zzf = new SparseArray();
        this.zzp = zzgbc.zzm();
        this.zzy = C.TIME_UNSET;
        this.zzx = C.TIME_UNSET;
        this.zzz = C.TIME_UNSET;
        this.zzF = zzadx.zza;
        this.zzG = new zzafa[0];
        this.zzH = new zzafa[0];
    }
}
