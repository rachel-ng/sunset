package com.google.android.gms.internal.ads;

import android.util.Pair;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaix {
    public int zzA = -1;
    public int zzB = 1000;
    public int zzC = 200;
    public float zzD = -1.0f;
    public float zzE = -1.0f;
    public float zzF = -1.0f;
    public float zzG = -1.0f;
    public float zzH = -1.0f;
    public float zzI = -1.0f;
    public float zzJ = -1.0f;
    public float zzK = -1.0f;
    public float zzL = -1.0f;
    public float zzM = -1.0f;
    public byte[] zzN;
    public int zzO = 1;
    public int zzP = -1;
    public int zzQ = 8000;
    public long zzR = 0;
    public long zzS = 0;
    public zzafb zzT;
    public boolean zzU;
    public boolean zzV = true;
    public zzafa zzW;
    public int zzX;
    /* access modifiers changed from: private */
    public int zzY;
    /* access modifiers changed from: private */
    public String zzZ = "eng";
    public String zza;
    public String zzb;
    public int zzc;
    public int zzd;
    public int zze;
    public int zzf;
    public boolean zzg;
    public byte[] zzh;
    public zzaez zzi;
    public byte[] zzj;
    public zzae zzk;
    public int zzl = -1;
    public int zzm = -1;
    public int zzn = -1;
    public int zzo = -1;
    public int zzp = -1;
    public int zzq = 0;
    public int zzr = -1;
    public float zzs = 0.0f;
    public float zzt = 0.0f;
    public float zzu = 0.0f;
    public byte[] zzv = null;
    public int zzw = -1;
    public boolean zzx = false;
    public int zzy = -1;
    public int zzz = -1;

    protected zzaix() {
    }

    private static Pair zzf(zzfu zzfu) throws zzch {
        try {
            zzfu.zzL(16);
            long zzs2 = zzfu.zzs();
            if (zzs2 == 1482049860) {
                return new Pair(MimeTypes.VIDEO_DIVX, (Object) null);
            }
            if (zzs2 == 859189832) {
                return new Pair(MimeTypes.VIDEO_H263, (Object) null);
            }
            if (zzs2 == 826496599) {
                int zzd2 = zzfu.zzd() + 20;
                byte[] zzM2 = zzfu.zzM();
                while (true) {
                    int length = zzM2.length;
                    if (zzd2 < length - 4) {
                        int i = zzd2 + 1;
                        if (zzM2[zzd2] == 0 && zzM2[i] == 0 && zzM2[zzd2 + 2] == 1 && zzM2[zzd2 + 3] == 15) {
                            return new Pair(MimeTypes.VIDEO_VC1, Collections.singletonList(Arrays.copyOfRange(zzM2, zzd2, length)));
                        }
                        zzd2 = i;
                    } else {
                        throw zzch.zza("Failed to find FourCC VC1 initialization data", (Throwable) null);
                    }
                }
            } else {
                zzfk.zzf("MatroskaExtractor", "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair(MimeTypes.VIDEO_UNKNOWN, (Object) null);
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw zzch.zza("Error parsing FourCC private data", (Throwable) null);
        }
    }

    private static List zzg(byte[] bArr) throws zzch {
        byte b2;
        byte b3;
        try {
            if (bArr[0] == 2) {
                int i = 0;
                int i2 = 1;
                while (true) {
                    byte b4 = bArr[i2];
                    i2++;
                    b2 = b4 & 255;
                    if (b2 != 255) {
                        break;
                    }
                    i += 255;
                }
                int i3 = i + b2;
                int i4 = 0;
                while (true) {
                    byte b5 = bArr[i2];
                    i2++;
                    b3 = b5 & 255;
                    if (b3 != 255) {
                        break;
                    }
                    i4 += 255;
                }
                int i5 = i4 + b3;
                if (bArr[i2] == 1) {
                    byte[] bArr2 = new byte[i3];
                    System.arraycopy(bArr, i2, bArr2, 0, i3);
                    int i6 = i2 + i3;
                    if (bArr[i6] == 3) {
                        int i7 = i6 + i5;
                        if (bArr[i7] == 5) {
                            int length = bArr.length - i7;
                            byte[] bArr3 = new byte[length];
                            System.arraycopy(bArr, i7, bArr3, 0, length);
                            ArrayList arrayList = new ArrayList(2);
                            arrayList.add(bArr2);
                            arrayList.add(bArr3);
                            return arrayList;
                        }
                        throw zzch.zza("Error parsing vorbis codec private", (Throwable) null);
                    }
                    throw zzch.zza("Error parsing vorbis codec private", (Throwable) null);
                }
                throw zzch.zza("Error parsing vorbis codec private", (Throwable) null);
            }
            throw zzch.zza("Error parsing vorbis codec private", (Throwable) null);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw zzch.zza("Error parsing vorbis codec private", (Throwable) null);
        }
    }

    private static boolean zzh(zzfu zzfu) throws zzch {
        try {
            int zzk2 = zzfu.zzk();
            if (zzk2 == 1) {
                return true;
            }
            if (zzk2 == 65534) {
                zzfu.zzK(24);
                return zzfu.zzt() == zzaiy.zzf.getMostSignificantBits() && zzfu.zzt() == zzaiy.zzf.getLeastSignificantBits();
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw zzch.zza("Error parsing MS/ACM codec private", (Throwable) null);
        }
    }

    @EnsuresNonNull({"codecPrivate"})
    private final byte[] zzi(String str) throws zzch {
        byte[] bArr = this.zzj;
        if (bArr != null) {
            return bArr;
        }
        throw zzch.zza("Missing CodecPrivate for codec ".concat(String.valueOf(str)), (Throwable) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v16, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v42, resolved type: com.google.android.gms.internal.ads.zzgbc} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x024f, code lost:
        r1 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0287, code lost:
        r1 = -1;
        r10 = -1;
        r17 = com.google.android.exoplayer2.util.MimeTypes.AUDIO_UNKNOWN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x02b9, code lost:
        r10 = -1;
        r1 = 4096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x032b, code lost:
        r2 = r1;
        r10 = -1;
        r1 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x034e, code lost:
        r2 = r1;
        r1 = -1;
        r10 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0385, code lost:
        r3 = r1;
        r1 = -1;
        r10 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x03a1, code lost:
        r1 = -1;
        r10 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x03a3, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x03a4, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x03a7, code lost:
        if (r0.zzN == null) goto L_0x03ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x03a9, code lost:
        r4 = com.google.android.gms.internal.ads.zzado.zza(new com.google.android.gms.internal.ads.zzfu(r0.zzN));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x03b4, code lost:
        if (r4 == null) goto L_0x03ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x03b6, code lost:
        r3 = r4.zza;
        r17 = com.google.android.exoplayer2.util.MimeTypes.VIDEO_DOLBY_VISION;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x03ba, code lost:
        r4 = r17;
        r5 = r0.zzV;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x03c0, code lost:
        if (true == r0.zzU) goto L_0x03c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x03c2, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x03c4, code lost:
        r7 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x03c5, code lost:
        r5 = r5 | r7;
        r7 = new com.google.android.gms.internal.ads.zzal();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x03cf, code lost:
        if (com.google.android.gms.internal.ads.zzcg.zzg(r4) == false) goto L_0x03e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x03d1, code lost:
        r7.zzy(r0.zzO);
        r7.zzY(r0.zzQ);
        r7.zzR(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x03e4, code lost:
        if (com.google.android.gms.internal.ads.zzcg.zzh(r4) == false) goto L_0x0584;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x03e8, code lost:
        if (r0.zzq != 0) goto L_0x03fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x03ea, code lost:
        r6 = r0.zzo;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x03ec, code lost:
        if (r6 != -1) goto L_0x03f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x03ee, code lost:
        r6 = r0.zzl;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x03f0, code lost:
        r0.zzo = r6;
        r6 = r0.zzp;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x03f4, code lost:
        if (r6 != -1) goto L_0x03f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x03f6, code lost:
        r6 = r0.zzm;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x03f8, code lost:
        r0.zzp = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x03fa, code lost:
        r6 = r0.zzo;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x03fe, code lost:
        if (r6 == -1) goto L_0x040e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x0400, code lost:
        r9 = r0.zzp;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0402, code lost:
        if (r9 == -1) goto L_0x040e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x0404, code lost:
        r9 = ((float) (r0.zzm * r6)) / ((float) (r0.zzl * r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x040e, code lost:
        r9 = -1.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x0411, code lost:
        if (r0.zzx == false) goto L_0x04f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x0417, code lost:
        if (r0.zzD == -1.0f) goto L_0x04cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x041d, code lost:
        if (r0.zzE == -1.0f) goto L_0x04cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x0423, code lost:
        if (r0.zzF == -1.0f) goto L_0x04cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0429, code lost:
        if (r0.zzG == -1.0f) goto L_0x04cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x042f, code lost:
        if (r0.zzH == -1.0f) goto L_0x04cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x0435, code lost:
        if (r0.zzI == -1.0f) goto L_0x04cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x043b, code lost:
        if (r0.zzJ == -1.0f) goto L_0x04cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x0441, code lost:
        if (r0.zzK == -1.0f) goto L_0x04cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x0447, code lost:
        if (r0.zzL == -1.0f) goto L_0x04cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x044d, code lost:
        if (r0.zzM != -1.0f) goto L_0x0451;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0451, code lost:
        r6 = new byte[25];
        r8 = java.nio.ByteBuffer.wrap(r6).order(java.nio.ByteOrder.LITTLE_ENDIAN);
        r8.put((byte) 0);
        r8.putShort((short) ((int) ((r0.zzD * 50000.0f) + 0.5f)));
        r8.putShort((short) ((int) ((r0.zzE * 50000.0f) + 0.5f)));
        r8.putShort((short) ((int) ((r0.zzF * 50000.0f) + 0.5f)));
        r8.putShort((short) ((int) ((r0.zzG * 50000.0f) + 0.5f)));
        r8.putShort((short) ((int) ((r0.zzH * 50000.0f) + 0.5f)));
        r8.putShort((short) ((int) ((r0.zzI * 50000.0f) + 0.5f)));
        r8.putShort((short) ((int) ((r0.zzJ * 50000.0f) + 0.5f)));
        r8.putShort((short) ((int) ((r0.zzK * 50000.0f) + 0.5f)));
        r8.putShort((short) ((int) (r0.zzL + 0.5f)));
        r8.putShort((short) ((int) (r0.zzM + 0.5f)));
        r8.putShort((short) r0.zzB);
        r8.putShort((short) r0.zzC);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x04cc, code lost:
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x04cd, code lost:
        r8 = new com.google.android.gms.internal.ads.zzr();
        r8.zzc(r0.zzy);
        r8.zzb(r0.zzA);
        r8.zzd(r0.zzz);
        r8.zze(r6);
        r8.zzf(r0.zzn);
        r8.zza(r0.zzn);
        r6 = r8.zzg();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x04f3, code lost:
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x04f6, code lost:
        if (r0.zza == null) goto L_0x0514;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0502, code lost:
        if (com.google.android.gms.internal.ads.zzaiy.zzg.containsKey(r0.zza) == false) goto L_0x0514;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x0504, code lost:
        r13 = ((java.lang.Integer) com.google.android.gms.internal.ads.zzaiy.zzg.get(r0.zza)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x0516, code lost:
        if (r0.zzr != 0) goto L_0x0564;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x051f, code lost:
        if (java.lang.Float.compare(r0.zzs, 0.0f) != 0) goto L_0x0564;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x0527, code lost:
        if (java.lang.Float.compare(r0.zzt, 0.0f) != 0) goto L_0x0564;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x052f, code lost:
        if (java.lang.Float.compare(r0.zzu, 0.0f) != 0) goto L_0x0532;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x053a, code lost:
        if (java.lang.Float.compare(r0.zzu, 90.0f) != 0) goto L_0x053f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x053c, code lost:
        r11 = 90;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x0547, code lost:
        if (java.lang.Float.compare(r0.zzu, -180.0f) == 0) goto L_0x0561;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x0551, code lost:
        if (java.lang.Float.compare(r0.zzu, 180.0f) != 0) goto L_0x0554;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x055c, code lost:
        if (java.lang.Float.compare(r0.zzu, -90.0f) != 0) goto L_0x0564;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x055e, code lost:
        r11 = 270;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x0561, code lost:
        r11 = com.alibaba.fastjson.asm.Opcodes.GETFIELD;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x0564, code lost:
        r11 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x0565, code lost:
        r7.zzac(r0.zzl);
        r7.zzI(r0.zzm);
        r7.zzT(r9);
        r7.zzW(r11);
        r7.zzU(r0.zzv);
        r7.zzaa(r0.zzw);
        r7.zzA(r6);
        r6 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x0588, code lost:
        if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_SUBRIP.equals(r4) != false) goto L_0x05b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x058e, code lost:
        if (com.google.android.exoplayer2.util.MimeTypes.TEXT_SSA.equals(r4) != false) goto L_0x05b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x0596, code lost:
        if (com.google.android.exoplayer2.util.MimeTypes.TEXT_VTT.equals(r4) != false) goto L_0x05b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x059e, code lost:
        if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_VOBSUB.equals(r4) != false) goto L_0x05b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x05a6, code lost:
        if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_PGS.equals(r4) != false) goto L_0x05b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x05ae, code lost:
        if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_DVBSUBS.equals(r4) == false) goto L_0x05b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x05b8, code lost:
        throw com.google.android.gms.internal.ads.zzch.zza("Unexpected MIME type.", (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x05b9, code lost:
        r6 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x05bc, code lost:
        if (r0.zza == null) goto L_0x05cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x05c8, code lost:
        if (com.google.android.gms.internal.ads.zzaiy.zzg.containsKey(r0.zza) != false) goto L_0x05cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x05ca, code lost:
        r7.zzM(r0.zza);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x05cf, code lost:
        r7.zzJ(r21);
        r7.zzX(r4);
        r7.zzP(r1);
        r7.zzO(r0.zzZ);
        r7.zzZ(r5);
        r7.zzL(r2);
        r7.zzz(r3);
        r7.zzE(r0.zzk);
        r1 = r7.zzad();
        r2 = r20.zzw(r0.zzc, r6);
        r0.zzW = r2;
        r2.zzl(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x05fe, code lost:
        return;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"codecId"})
    @org.checkerframework.checker.nullness.qual.EnsuresNonNull({"this.output"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zze(com.google.android.gms.internal.ads.zzadx r20, int r21) throws com.google.android.gms.internal.ads.zzch {
        /*
            r19 = this;
            r0 = r19
            java.lang.String r1 = r0.zzb
            int r2 = r1.hashCode()
            r3 = 24
            r5 = 16
            r6 = 1
            r8 = 32
            r9 = 8
            r10 = 4
            r11 = 0
            r12 = 3
            r13 = -1
            switch(r2) {
                case -2095576542: goto L_0x0187;
                case -2095575984: goto L_0x017d;
                case -1985379776: goto L_0x0172;
                case -1784763192: goto L_0x0167;
                case -1730367663: goto L_0x015c;
                case -1482641358: goto L_0x0151;
                case -1482641357: goto L_0x0146;
                case -1373388978: goto L_0x013b;
                case -933872740: goto L_0x0131;
                case -538363189: goto L_0x0126;
                case -538363109: goto L_0x011b;
                case -425012669: goto L_0x010f;
                case -356037306: goto L_0x0103;
                case 62923557: goto L_0x00f7;
                case 62923603: goto L_0x00ec;
                case 62927045: goto L_0x00e0;
                case 82318131: goto L_0x00d5;
                case 82338133: goto L_0x00ca;
                case 82338134: goto L_0x00bf;
                case 99146302: goto L_0x00b3;
                case 444813526: goto L_0x00a7;
                case 542569478: goto L_0x009b;
                case 635596514: goto L_0x008f;
                case 725948237: goto L_0x0083;
                case 725957860: goto L_0x0078;
                case 738597099: goto L_0x006c;
                case 855502857: goto L_0x0061;
                case 1045209816: goto L_0x0055;
                case 1422270023: goto L_0x0049;
                case 1809237540: goto L_0x003e;
                case 1950749482: goto L_0x0032;
                case 1950789798: goto L_0x0026;
                case 1951062397: goto L_0x001a;
                default: goto L_0x0018;
            }
        L_0x0018:
            goto L_0x0191
        L_0x001a:
            java.lang.String r2 = "A_OPUS"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 12
            goto L_0x0192
        L_0x0026:
            java.lang.String r2 = "A_FLAC"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 22
            goto L_0x0192
        L_0x0032:
            java.lang.String r2 = "A_EAC3"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 17
            goto L_0x0192
        L_0x003e:
            java.lang.String r2 = "V_MPEG2"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = r12
            goto L_0x0192
        L_0x0049:
            java.lang.String r2 = "S_TEXT/UTF8"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 27
            goto L_0x0192
        L_0x0055:
            java.lang.String r2 = "S_TEXT/WEBVTT"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 29
            goto L_0x0192
        L_0x0061:
            java.lang.String r2 = "V_MPEGH/ISO/HEVC"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = r9
            goto L_0x0192
        L_0x006c:
            java.lang.String r2 = "S_TEXT/ASS"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 28
            goto L_0x0192
        L_0x0078:
            java.lang.String r2 = "A_PCM/INT/LIT"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = r3
            goto L_0x0192
        L_0x0083:
            java.lang.String r2 = "A_PCM/INT/BIG"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 25
            goto L_0x0192
        L_0x008f:
            java.lang.String r2 = "A_PCM/FLOAT/IEEE"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 26
            goto L_0x0192
        L_0x009b:
            java.lang.String r2 = "A_DTS/EXPRESS"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 20
            goto L_0x0192
        L_0x00a7:
            java.lang.String r2 = "V_THEORA"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 10
            goto L_0x0192
        L_0x00b3:
            java.lang.String r2 = "S_HDMV/PGS"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 31
            goto L_0x0192
        L_0x00bf:
            java.lang.String r2 = "V_VP9"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = r6
            goto L_0x0192
        L_0x00ca:
            java.lang.String r2 = "V_VP8"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = r11
            goto L_0x0192
        L_0x00d5:
            java.lang.String r2 = "V_AV1"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 2
            goto L_0x0192
        L_0x00e0:
            java.lang.String r2 = "A_DTS"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 19
            goto L_0x0192
        L_0x00ec:
            java.lang.String r2 = "A_AC3"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = r5
            goto L_0x0192
        L_0x00f7:
            java.lang.String r2 = "A_AAC"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 13
            goto L_0x0192
        L_0x0103:
            java.lang.String r2 = "A_DTS/LOSSLESS"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 21
            goto L_0x0192
        L_0x010f:
            java.lang.String r2 = "S_VOBSUB"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 30
            goto L_0x0192
        L_0x011b:
            java.lang.String r2 = "V_MPEG4/ISO/AVC"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 7
            goto L_0x0192
        L_0x0126:
            java.lang.String r2 = "V_MPEG4/ISO/ASP"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 5
            goto L_0x0192
        L_0x0131:
            java.lang.String r2 = "S_DVBSUB"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = r8
            goto L_0x0192
        L_0x013b:
            java.lang.String r2 = "V_MS/VFW/FOURCC"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 9
            goto L_0x0192
        L_0x0146:
            java.lang.String r2 = "A_MPEG/L3"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 15
            goto L_0x0192
        L_0x0151:
            java.lang.String r2 = "A_MPEG/L2"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 14
            goto L_0x0192
        L_0x015c:
            java.lang.String r2 = "A_VORBIS"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 11
            goto L_0x0192
        L_0x0167:
            java.lang.String r2 = "A_TRUEHD"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 18
            goto L_0x0192
        L_0x0172:
            java.lang.String r2 = "A_MS/ACM"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 23
            goto L_0x0192
        L_0x017d:
            java.lang.String r2 = "V_MPEG4/ISO/SP"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = r10
            goto L_0x0192
        L_0x0187:
            java.lang.String r2 = "V_MPEG4/ISO/AP"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0191
            r2 = 6
            goto L_0x0192
        L_0x0191:
            r2 = r13
        L_0x0192:
            java.lang.String r14 = "text/x-ssa"
            java.lang.String r15 = "application/x-subrip"
            r16 = 4096(0x1000, float:5.74E-42)
            java.lang.String r17 = "audio/raw"
            java.lang.String r18 = "audio/x-unknown"
            java.lang.String r7 = "MatroskaExtractor"
            java.lang.String r4 = ". Setting mimeType to audio/x-unknown"
            switch(r2) {
                case 0: goto L_0x039f;
                case 1: goto L_0x039c;
                case 2: goto L_0x0399;
                case 3: goto L_0x0396;
                case 4: goto L_0x0389;
                case 5: goto L_0x0389;
                case 6: goto L_0x0389;
                case 7: goto L_0x036c;
                case 8: goto L_0x0352;
                case 9: goto L_0x0335;
                case 10: goto L_0x0331;
                case 11: goto L_0x031f;
                case 12: goto L_0x02de;
                case 13: goto L_0x02be;
                case 14: goto L_0x02b7;
                case 15: goto L_0x02b4;
                case 16: goto L_0x02b0;
                case 17: goto L_0x02ac;
                case 18: goto L_0x02a1;
                case 19: goto L_0x029d;
                case 20: goto L_0x029d;
                case 21: goto L_0x0299;
                case 22: goto L_0x028d;
                case 23: goto L_0x0252;
                case 24: goto L_0x0230;
                case 25: goto L_0x0206;
                case 26: goto L_0x01eb;
                case 27: goto L_0x01e5;
                case 28: goto L_0x01d0;
                case 29: goto L_0x01cc;
                case 30: goto L_0x01c0;
                case 31: goto L_0x01bc;
                case 32: goto L_0x01ab;
                default: goto L_0x01a3;
            }
        L_0x01a3:
            java.lang.String r1 = "Unrecognized codec identifier."
            r2 = 0
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zza(r1, r2)
            throw r1
        L_0x01ab:
            byte[] r2 = new byte[r10]
            byte[] r1 = r0.zzi(r1)
            java.lang.System.arraycopy(r1, r11, r2, r11, r10)
            com.google.android.gms.internal.ads.zzgbc r1 = com.google.android.gms.internal.ads.zzgbc.zzn(r2)
            java.lang.String r17 = "application/dvbsubs"
            goto L_0x034e
        L_0x01bc:
            java.lang.String r17 = "application/pgs"
            goto L_0x03a1
        L_0x01c0:
            byte[] r1 = r0.zzi(r1)
            com.google.android.gms.internal.ads.zzgbc r1 = com.google.android.gms.internal.ads.zzgbc.zzn(r1)
            java.lang.String r17 = "application/vobsub"
            goto L_0x034e
        L_0x01cc:
            java.lang.String r17 = "text/vtt"
            goto L_0x03a1
        L_0x01d0:
            byte[] r1 = com.google.android.gms.internal.ads.zzaiy.zzc
            java.lang.String r2 = r0.zzb
            byte[] r2 = r0.zzi(r2)
            com.google.android.gms.internal.ads.zzgbc r1 = com.google.android.gms.internal.ads.zzgbc.zzo(r1, r2)
            r2 = r1
            r1 = r13
            r10 = r1
            r17 = r14
            goto L_0x03a4
        L_0x01e5:
            r1 = r13
            r10 = r1
            r17 = r15
            goto L_0x03a3
        L_0x01eb:
            int r1 = r0.zzP
            if (r1 != r8) goto L_0x01f0
            goto L_0x024f
        L_0x01f0:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unsupported floating point PCM bit depth: "
            r2.<init>(r3)
            r2.append(r1)
            r2.append(r4)
            java.lang.String r1 = r2.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r7, r1)
            goto L_0x0287
        L_0x0206:
            int r1 = r0.zzP
            if (r1 != r9) goto L_0x020c
            r10 = r12
            goto L_0x024f
        L_0x020c:
            if (r1 != r5) goto L_0x0211
            r10 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x024f
        L_0x0211:
            if (r1 != r3) goto L_0x0216
            r10 = 1342177280(0x50000000, float:8.5899346E9)
            goto L_0x024f
        L_0x0216:
            if (r1 != r8) goto L_0x021b
            r10 = 1610612736(0x60000000, float:3.6893488E19)
            goto L_0x024f
        L_0x021b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unsupported big endian PCM bit depth: "
            r2.<init>(r3)
            r2.append(r1)
            r2.append(r4)
            java.lang.String r1 = r2.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r7, r1)
            goto L_0x0287
        L_0x0230:
            int r1 = r0.zzP
            int r10 = com.google.android.gms.internal.ads.zzgd.zzl(r1)
            if (r10 != 0) goto L_0x024f
            int r1 = r0.zzP
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unsupported little endian PCM bit depth: "
            r2.<init>(r3)
            r2.append(r1)
            r2.append(r4)
            java.lang.String r1 = r2.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r7, r1)
            goto L_0x0287
        L_0x024f:
            r1 = r13
            goto L_0x03a3
        L_0x0252:
            com.google.android.gms.internal.ads.zzfu r1 = new com.google.android.gms.internal.ads.zzfu
            java.lang.String r2 = r0.zzb
            byte[] r2 = r0.zzi(r2)
            r1.<init>((byte[]) r2)
            boolean r1 = zzh(r1)
            if (r1 == 0) goto L_0x0282
            int r1 = r0.zzP
            int r10 = com.google.android.gms.internal.ads.zzgd.zzl(r1)
            if (r10 != 0) goto L_0x024f
            int r1 = r0.zzP
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unsupported PCM bit depth: "
            r2.<init>(r3)
            r2.append(r1)
            r2.append(r4)
            java.lang.String r1 = r2.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r7, r1)
            goto L_0x0287
        L_0x0282:
            java.lang.String r1 = "Non-PCM MS/ACM is unsupported. Setting mimeType to audio/x-unknown"
            com.google.android.gms.internal.ads.zzfk.zzf(r7, r1)
        L_0x0287:
            r1 = r13
            r10 = r1
            r17 = r18
            goto L_0x03a3
        L_0x028d:
            byte[] r1 = r0.zzi(r1)
            java.util.List r1 = java.util.Collections.singletonList(r1)
            java.lang.String r17 = "audio/flac"
            goto L_0x034e
        L_0x0299:
            java.lang.String r17 = "audio/vnd.dts.hd"
            goto L_0x03a1
        L_0x029d:
            java.lang.String r17 = "audio/vnd.dts"
            goto L_0x03a1
        L_0x02a1:
            com.google.android.gms.internal.ads.zzafb r1 = new com.google.android.gms.internal.ads.zzafb
            r1.<init>()
            r0.zzT = r1
            java.lang.String r17 = "audio/true-hd"
            goto L_0x03a1
        L_0x02ac:
            java.lang.String r17 = "audio/eac3"
            goto L_0x03a1
        L_0x02b0:
            java.lang.String r17 = "audio/ac3"
            goto L_0x03a1
        L_0x02b4:
            java.lang.String r17 = "audio/mpeg"
            goto L_0x02b9
        L_0x02b7:
            java.lang.String r17 = "audio/mpeg-L2"
        L_0x02b9:
            r10 = r13
            r1 = r16
            goto L_0x03a3
        L_0x02be:
            byte[] r1 = r0.zzi(r1)
            java.util.List r1 = java.util.Collections.singletonList(r1)
            byte[] r2 = r0.zzj
            com.google.android.gms.internal.ads.zzacp r2 = com.google.android.gms.internal.ads.zzacq.zza(r2)
            int r3 = r2.zza
            r0.zzQ = r3
            int r3 = r2.zzb
            r0.zzO = r3
            java.lang.String r2 = r2.zzc
            java.lang.String r17 = "audio/mp4a-latm"
            r3 = r2
            r10 = r13
            r2 = r1
            r1 = r10
            goto L_0x03a5
        L_0x02de:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r12)
            java.lang.String r2 = r0.zzb
            byte[] r2 = r0.zzi(r2)
            r1.add(r2)
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r9)
            java.nio.ByteOrder r3 = java.nio.ByteOrder.LITTLE_ENDIAN
            java.nio.ByteBuffer r2 = r2.order(r3)
            long r3 = r0.zzR
            java.nio.ByteBuffer r2 = r2.putLong(r3)
            byte[] r2 = r2.array()
            r1.add(r2)
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r9)
            java.nio.ByteOrder r3 = java.nio.ByteOrder.LITTLE_ENDIAN
            java.nio.ByteBuffer r2 = r2.order(r3)
            long r3 = r0.zzS
            java.nio.ByteBuffer r2 = r2.putLong(r3)
            byte[] r2 = r2.array()
            r1.add(r2)
            r16 = 5760(0x1680, float:8.071E-42)
            java.lang.String r17 = "audio/opus"
            goto L_0x032b
        L_0x031f:
            byte[] r1 = r0.zzi(r1)
            java.util.List r1 = zzg(r1)
            r16 = 8192(0x2000, float:1.14794E-41)
            java.lang.String r17 = "audio/vorbis"
        L_0x032b:
            r2 = r1
            r10 = r13
            r1 = r16
            goto L_0x03a4
        L_0x0331:
            java.lang.String r17 = "video/x-unknown"
            goto L_0x03a1
        L_0x0335:
            com.google.android.gms.internal.ads.zzfu r1 = new com.google.android.gms.internal.ads.zzfu
            java.lang.String r2 = r0.zzb
            byte[] r2 = r0.zzi(r2)
            r1.<init>((byte[]) r2)
            android.util.Pair r1 = zzf(r1)
            java.lang.Object r2 = r1.first
            r17 = r2
            java.lang.String r17 = (java.lang.String) r17
            java.lang.Object r1 = r1.second
            java.util.List r1 = (java.util.List) r1
        L_0x034e:
            r2 = r1
            r1 = r13
            r10 = r1
            goto L_0x03a4
        L_0x0352:
            com.google.android.gms.internal.ads.zzfu r1 = new com.google.android.gms.internal.ads.zzfu
            java.lang.String r2 = r0.zzb
            byte[] r2 = r0.zzi(r2)
            r1.<init>((byte[]) r2)
            com.google.android.gms.internal.ads.zzaek r1 = com.google.android.gms.internal.ads.zzaek.zza(r1)
            java.util.List r2 = r1.zza
            int r3 = r1.zzb
            r0.zzX = r3
            java.lang.String r1 = r1.zzi
            java.lang.String r17 = "video/hevc"
            goto L_0x0385
        L_0x036c:
            com.google.android.gms.internal.ads.zzfu r1 = new com.google.android.gms.internal.ads.zzfu
            java.lang.String r2 = r0.zzb
            byte[] r2 = r0.zzi(r2)
            r1.<init>((byte[]) r2)
            com.google.android.gms.internal.ads.zzacx r1 = com.google.android.gms.internal.ads.zzacx.zza(r1)
            java.util.List r2 = r1.zza
            int r3 = r1.zzb
            r0.zzX = r3
            java.lang.String r1 = r1.zzk
            java.lang.String r17 = "video/avc"
        L_0x0385:
            r3 = r1
            r1 = r13
            r10 = r1
            goto L_0x03a5
        L_0x0389:
            byte[] r1 = r0.zzj
            if (r1 != 0) goto L_0x038f
            r1 = 0
            goto L_0x0393
        L_0x038f:
            java.util.List r1 = java.util.Collections.singletonList(r1)
        L_0x0393:
            java.lang.String r17 = "video/mp4v-es"
            goto L_0x034e
        L_0x0396:
            java.lang.String r17 = "video/mpeg2"
            goto L_0x03a1
        L_0x0399:
            java.lang.String r17 = "video/av01"
            goto L_0x03a1
        L_0x039c:
            java.lang.String r17 = "video/x-vnd.on2.vp9"
            goto L_0x03a1
        L_0x039f:
            java.lang.String r17 = "video/x-vnd.on2.vp8"
        L_0x03a1:
            r1 = r13
            r10 = r1
        L_0x03a3:
            r2 = 0
        L_0x03a4:
            r3 = 0
        L_0x03a5:
            byte[] r4 = r0.zzN
            if (r4 == 0) goto L_0x03ba
            com.google.android.gms.internal.ads.zzfu r4 = new com.google.android.gms.internal.ads.zzfu
            byte[] r5 = r0.zzN
            r4.<init>((byte[]) r5)
            com.google.android.gms.internal.ads.zzado r4 = com.google.android.gms.internal.ads.zzado.zza(r4)
            if (r4 == 0) goto L_0x03ba
            java.lang.String r3 = r4.zza
            java.lang.String r17 = "video/dolby-vision"
        L_0x03ba:
            r4 = r17
            boolean r5 = r0.zzV
            boolean r7 = r0.zzU
            if (r6 == r7) goto L_0x03c4
            r7 = r11
            goto L_0x03c5
        L_0x03c4:
            r7 = 2
        L_0x03c5:
            r5 = r5 | r7
            com.google.android.gms.internal.ads.zzal r7 = new com.google.android.gms.internal.ads.zzal
            r7.<init>()
            boolean r8 = com.google.android.gms.internal.ads.zzcg.zzg(r4)
            if (r8 == 0) goto L_0x03e0
            int r8 = r0.zzO
            r7.zzy(r8)
            int r8 = r0.zzQ
            r7.zzY(r8)
            r7.zzR(r10)
            goto L_0x05ba
        L_0x03e0:
            boolean r6 = com.google.android.gms.internal.ads.zzcg.zzh(r4)
            if (r6 == 0) goto L_0x0584
            int r6 = r0.zzq
            if (r6 != 0) goto L_0x03fa
            int r6 = r0.zzo
            if (r6 != r13) goto L_0x03f0
            int r6 = r0.zzl
        L_0x03f0:
            r0.zzo = r6
            int r6 = r0.zzp
            if (r6 != r13) goto L_0x03f8
            int r6 = r0.zzm
        L_0x03f8:
            r0.zzp = r6
        L_0x03fa:
            int r6 = r0.zzo
            r8 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r6 == r13) goto L_0x040e
            int r9 = r0.zzp
            if (r9 == r13) goto L_0x040e
            int r10 = r0.zzm
            int r10 = r10 * r6
            int r6 = r0.zzl
            int r6 = r6 * r9
            float r9 = (float) r10
            float r6 = (float) r6
            float r9 = r9 / r6
            goto L_0x040f
        L_0x040e:
            r9 = r8
        L_0x040f:
            boolean r6 = r0.zzx
            if (r6 == 0) goto L_0x04f3
            float r6 = r0.zzD
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x04cc
            float r6 = r0.zzE
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x04cc
            float r6 = r0.zzF
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x04cc
            float r6 = r0.zzG
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x04cc
            float r6 = r0.zzH
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x04cc
            float r6 = r0.zzI
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x04cc
            float r6 = r0.zzJ
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x04cc
            float r6 = r0.zzK
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x04cc
            float r6 = r0.zzL
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x04cc
            float r6 = r0.zzM
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 != 0) goto L_0x0451
            goto L_0x04cc
        L_0x0451:
            r6 = 25
            byte[] r6 = new byte[r6]
            java.nio.ByteBuffer r8 = java.nio.ByteBuffer.wrap(r6)
            java.nio.ByteOrder r10 = java.nio.ByteOrder.LITTLE_ENDIAN
            java.nio.ByteBuffer r8 = r8.order(r10)
            r8.put(r11)
            float r10 = r0.zzD
            r12 = 1195593728(0x47435000, float:50000.0)
            float r10 = r10 * r12
            r14 = 1056964608(0x3f000000, float:0.5)
            float r10 = r10 + r14
            int r10 = (int) r10
            short r10 = (short) r10
            r8.putShort(r10)
            float r10 = r0.zzE
            float r10 = r10 * r12
            float r10 = r10 + r14
            int r10 = (int) r10
            short r10 = (short) r10
            r8.putShort(r10)
            float r10 = r0.zzF
            float r10 = r10 * r12
            float r10 = r10 + r14
            int r10 = (int) r10
            short r10 = (short) r10
            r8.putShort(r10)
            float r10 = r0.zzG
            float r10 = r10 * r12
            float r10 = r10 + r14
            int r10 = (int) r10
            short r10 = (short) r10
            r8.putShort(r10)
            float r10 = r0.zzH
            float r10 = r10 * r12
            float r10 = r10 + r14
            int r10 = (int) r10
            short r10 = (short) r10
            r8.putShort(r10)
            float r10 = r0.zzI
            float r10 = r10 * r12
            float r10 = r10 + r14
            int r10 = (int) r10
            short r10 = (short) r10
            r8.putShort(r10)
            float r10 = r0.zzJ
            float r10 = r10 * r12
            float r10 = r10 + r14
            int r10 = (int) r10
            short r10 = (short) r10
            r8.putShort(r10)
            float r10 = r0.zzK
            float r10 = r10 * r12
            float r10 = r10 + r14
            int r10 = (int) r10
            short r10 = (short) r10
            r8.putShort(r10)
            float r10 = r0.zzL
            float r10 = r10 + r14
            int r10 = (int) r10
            short r10 = (short) r10
            r8.putShort(r10)
            float r10 = r0.zzM
            float r10 = r10 + r14
            int r10 = (int) r10
            short r10 = (short) r10
            r8.putShort(r10)
            int r10 = r0.zzB
            short r10 = (short) r10
            r8.putShort(r10)
            int r10 = r0.zzC
            short r10 = (short) r10
            r8.putShort(r10)
            goto L_0x04cd
        L_0x04cc:
            r6 = 0
        L_0x04cd:
            com.google.android.gms.internal.ads.zzr r8 = new com.google.android.gms.internal.ads.zzr
            r8.<init>()
            int r10 = r0.zzy
            r8.zzc(r10)
            int r10 = r0.zzA
            r8.zzb(r10)
            int r10 = r0.zzz
            r8.zzd(r10)
            r8.zze(r6)
            int r6 = r0.zzn
            r8.zzf(r6)
            int r6 = r0.zzn
            r8.zza(r6)
            com.google.android.gms.internal.ads.zzt r6 = r8.zzg()
            goto L_0x04f4
        L_0x04f3:
            r6 = 0
        L_0x04f4:
            java.lang.String r8 = r0.zza
            if (r8 == 0) goto L_0x0514
            java.util.Map r8 = com.google.android.gms.internal.ads.zzaiy.zzg
            java.lang.String r10 = r0.zza
            boolean r8 = r8.containsKey(r10)
            if (r8 == 0) goto L_0x0514
            java.util.Map r8 = com.google.android.gms.internal.ads.zzaiy.zzg
            java.lang.String r10 = r0.zza
            java.lang.Object r8 = r8.get(r10)
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r13 = r8.intValue()
        L_0x0514:
            int r8 = r0.zzr
            if (r8 != 0) goto L_0x0564
            float r8 = r0.zzs
            r10 = 0
            int r8 = java.lang.Float.compare(r8, r10)
            if (r8 != 0) goto L_0x0564
            float r8 = r0.zzt
            int r8 = java.lang.Float.compare(r8, r10)
            if (r8 != 0) goto L_0x0564
            float r8 = r0.zzu
            int r8 = java.lang.Float.compare(r8, r10)
            if (r8 != 0) goto L_0x0532
            goto L_0x0565
        L_0x0532:
            float r8 = r0.zzu
            r10 = 1119092736(0x42b40000, float:90.0)
            int r8 = java.lang.Float.compare(r8, r10)
            if (r8 != 0) goto L_0x053f
            r11 = 90
            goto L_0x0565
        L_0x053f:
            float r8 = r0.zzu
            r10 = -1020002304(0xffffffffc3340000, float:-180.0)
            int r8 = java.lang.Float.compare(r8, r10)
            if (r8 == 0) goto L_0x0561
            float r8 = r0.zzu
            r10 = 1127481344(0x43340000, float:180.0)
            int r8 = java.lang.Float.compare(r8, r10)
            if (r8 != 0) goto L_0x0554
            goto L_0x0561
        L_0x0554:
            float r8 = r0.zzu
            r10 = -1028390912(0xffffffffc2b40000, float:-90.0)
            int r8 = java.lang.Float.compare(r8, r10)
            if (r8 != 0) goto L_0x0564
            r11 = 270(0x10e, float:3.78E-43)
            goto L_0x0565
        L_0x0561:
            r11 = 180(0xb4, float:2.52E-43)
            goto L_0x0565
        L_0x0564:
            r11 = r13
        L_0x0565:
            int r8 = r0.zzl
            r7.zzac(r8)
            int r8 = r0.zzm
            r7.zzI(r8)
            r7.zzT(r9)
            r7.zzW(r11)
            byte[] r8 = r0.zzv
            r7.zzU(r8)
            int r8 = r0.zzw
            r7.zzaa(r8)
            r7.zzA(r6)
            r6 = 2
            goto L_0x05ba
        L_0x0584:
            boolean r6 = r15.equals(r4)
            if (r6 != 0) goto L_0x05b9
            boolean r6 = r14.equals(r4)
            if (r6 != 0) goto L_0x05b9
            java.lang.String r6 = "text/vtt"
            boolean r6 = r6.equals(r4)
            if (r6 != 0) goto L_0x05b9
            java.lang.String r6 = "application/vobsub"
            boolean r6 = r6.equals(r4)
            if (r6 != 0) goto L_0x05b9
            java.lang.String r6 = "application/pgs"
            boolean r6 = r6.equals(r4)
            if (r6 != 0) goto L_0x05b9
            java.lang.String r6 = "application/dvbsubs"
            boolean r6 = r6.equals(r4)
            if (r6 == 0) goto L_0x05b1
            goto L_0x05b9
        L_0x05b1:
            java.lang.String r1 = "Unexpected MIME type."
            r2 = 0
            com.google.android.gms.internal.ads.zzch r1 = com.google.android.gms.internal.ads.zzch.zza(r1, r2)
            throw r1
        L_0x05b9:
            r6 = r12
        L_0x05ba:
            java.lang.String r8 = r0.zza
            if (r8 == 0) goto L_0x05cf
            java.util.Map r8 = com.google.android.gms.internal.ads.zzaiy.zzg
            java.lang.String r9 = r0.zza
            boolean r8 = r8.containsKey(r9)
            if (r8 != 0) goto L_0x05cf
            java.lang.String r8 = r0.zza
            r7.zzM(r8)
        L_0x05cf:
            r8 = r21
            r7.zzJ(r8)
            r7.zzX(r4)
            r7.zzP(r1)
            java.lang.String r1 = r0.zzZ
            r7.zzO(r1)
            r7.zzZ(r5)
            r7.zzL(r2)
            r7.zzz(r3)
            com.google.android.gms.internal.ads.zzae r1 = r0.zzk
            r7.zzE(r1)
            com.google.android.gms.internal.ads.zzan r1 = r7.zzad()
            int r2 = r0.zzc
            r3 = r20
            com.google.android.gms.internal.ads.zzafa r2 = r3.zzw(r2, r6)
            r0.zzW = r2
            r2.zzl(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaix.zze(com.google.android.gms.internal.ads.zzadx, int):void");
    }
}
