package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Trace;
import android.view.Surface;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzabj extends zztv implements zzabp {
    private static final int[] zzb = {1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    private static boolean zzc;
    private static boolean zzd;
    private zzdv zzA;
    private boolean zzB;
    private boolean zzC;
    private int zzD;
    private zzabn zzE;
    private zzacm zzF;
    private final Context zze;
    private final zzacn zzf;
    private final zzach zzg;
    private final boolean zzh;
    private final zzabq zzi;
    private final zzabo zzj;
    private zzabi zzk;
    private boolean zzl;
    private boolean zzm;
    /* access modifiers changed from: private */
    public Surface zzn;
    private zzfv zzo;
    private zzabm zzp;
    private boolean zzq;
    private int zzr;
    private long zzs;
    private int zzt;
    private int zzu;
    private int zzv;
    private long zzw;
    private int zzx;
    private long zzy;
    private zzdv zzz;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzabj(Context context, zztl zztl, zztx zztx, long j, boolean z, Handler handler, zzaci zzaci, int i, float f) {
        super(2, zztl, zztx, false, 30.0f);
        Context applicationContext = context.getApplicationContext();
        this.zze = applicationContext;
        Handler handler2 = handler;
        zzaci zzaci2 = zzaci;
        this.zzg = new zzach(handler, zzaci);
        zzabc zzc2 = new zzaar(applicationContext).zzc();
        if (zzc2.zzc() == null) {
            zzc2.zzs(new zzabq(applicationContext, this, 0));
        }
        this.zzf = zzc2;
        zzabq zzc3 = zzc2.zzc();
        zzeq.zzb(zzc3);
        this.zzi = zzc3;
        this.zzj = new zzabo();
        this.zzh = "NVIDIA".equals(zzgd.zzc);
        this.zzr = 1;
        this.zzz = zzdv.zza;
        this.zzD = 0;
        this.zzA = null;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x04c3, code lost:
        if (r13.equals("deb") != false) goto L_0x078b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:508:0x079e, code lost:
        if (r0.equals("JSN-L21") == false) goto L_0x07a2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static final boolean zzaQ(java.lang.String r17) {
        /*
            java.lang.String r0 = "OMX.google"
            r1 = r17
            boolean r0 = r1.startsWith(r0)
            r1 = 0
            if (r0 == 0) goto L_0x000c
            return r1
        L_0x000c:
            java.lang.Class<com.google.android.gms.internal.ads.zzabj> r2 = com.google.android.gms.internal.ads.zzabj.class
            monitor-enter(r2)
            boolean r0 = zzc     // Catch:{ all -> 0x07aa }
            if (r0 != 0) goto L_0x07a6
            int r0 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ all -> 0x07aa }
            r3 = 28
            r4 = 5
            r5 = 6
            r6 = 4
            r7 = 7
            r8 = 2
            r9 = 3
            r10 = -1
            r11 = 1
            if (r0 > r3) goto L_0x0083
            java.lang.String r0 = com.google.android.gms.internal.ads.zzgd.zzb     // Catch:{ all -> 0x07aa }
            int r12 = r0.hashCode()     // Catch:{ all -> 0x07aa }
            switch(r12) {
                case -1339091551: goto L_0x0071;
                case -1220081023: goto L_0x0067;
                case -1220066608: goto L_0x005d;
                case -1012436106: goto L_0x0053;
                case -760312546: goto L_0x0049;
                case -64886864: goto L_0x003f;
                case 3415681: goto L_0x0035;
                case 825323514: goto L_0x002b;
                default: goto L_0x002a;
            }
        L_0x002a:
            goto L_0x007b
        L_0x002b:
            java.lang.String r12 = "machuca"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x007b
            r0 = r4
            goto L_0x007c
        L_0x0035:
            java.lang.String r12 = "once"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x007b
            r0 = r5
            goto L_0x007c
        L_0x003f:
            java.lang.String r12 = "magnolia"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x007b
            r0 = r6
            goto L_0x007c
        L_0x0049:
            java.lang.String r12 = "aquaman"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x007b
            r0 = r1
            goto L_0x007c
        L_0x0053:
            java.lang.String r12 = "oneday"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x007b
            r0 = r7
            goto L_0x007c
        L_0x005d:
            java.lang.String r12 = "dangalUHD"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x007b
            r0 = r8
            goto L_0x007c
        L_0x0067:
            java.lang.String r12 = "dangalFHD"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x007b
            r0 = r9
            goto L_0x007c
        L_0x0071:
            java.lang.String r12 = "dangal"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x007b
            r0 = r11
            goto L_0x007c
        L_0x007b:
            r0 = r10
        L_0x007c:
            switch(r0) {
                case 0: goto L_0x0080;
                case 1: goto L_0x0080;
                case 2: goto L_0x0080;
                case 3: goto L_0x0080;
                case 4: goto L_0x0080;
                case 5: goto L_0x0080;
                case 6: goto L_0x0080;
                case 7: goto L_0x0080;
                default: goto L_0x007f;
            }
        L_0x007f:
            goto L_0x0083
        L_0x0080:
            r1 = r11
            goto L_0x07a2
        L_0x0083:
            int r0 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ all -> 0x07aa }
            r12 = 27
            if (r0 > r12) goto L_0x0094
            java.lang.String r0 = "HWEML"
            java.lang.String r13 = com.google.android.gms.internal.ads.zzgd.zzb     // Catch:{ all -> 0x07aa }
            boolean r0 = r0.equals(r13)     // Catch:{ all -> 0x07aa }
            if (r0 == 0) goto L_0x0094
            goto L_0x0080
        L_0x0094:
            java.lang.String r0 = com.google.android.gms.internal.ads.zzgd.zzd     // Catch:{ all -> 0x07aa }
            int r13 = r0.hashCode()     // Catch:{ all -> 0x07aa }
            r14 = 8
            switch(r13) {
                case -349662828: goto L_0x00f1;
                case -321033677: goto L_0x00e7;
                case 2006354: goto L_0x00dd;
                case 2006367: goto L_0x00d3;
                case 2006371: goto L_0x00c9;
                case 1785421873: goto L_0x00bf;
                case 1785421876: goto L_0x00b5;
                case 1798172390: goto L_0x00ab;
                case 2119412532: goto L_0x00a1;
                default: goto L_0x009f;
            }
        L_0x009f:
            goto L_0x00fb
        L_0x00a1:
            java.lang.String r13 = "AFTEUFF014"
            boolean r13 = r0.equals(r13)
            if (r13 == 0) goto L_0x00fb
            r13 = r4
            goto L_0x00fc
        L_0x00ab:
            java.lang.String r13 = "AFTSO001"
            boolean r13 = r0.equals(r13)
            if (r13 == 0) goto L_0x00fb
            r13 = r14
            goto L_0x00fc
        L_0x00b5:
            java.lang.String r13 = "AFTEU014"
            boolean r13 = r0.equals(r13)
            if (r13 == 0) goto L_0x00fb
            r13 = r6
            goto L_0x00fc
        L_0x00bf:
            java.lang.String r13 = "AFTEU011"
            boolean r13 = r0.equals(r13)
            if (r13 == 0) goto L_0x00fb
            r13 = r9
            goto L_0x00fc
        L_0x00c9:
            java.lang.String r13 = "AFTR"
            boolean r13 = r0.equals(r13)
            if (r13 == 0) goto L_0x00fb
            r13 = r8
            goto L_0x00fc
        L_0x00d3:
            java.lang.String r13 = "AFTN"
            boolean r13 = r0.equals(r13)
            if (r13 == 0) goto L_0x00fb
            r13 = r11
            goto L_0x00fc
        L_0x00dd:
            java.lang.String r13 = "AFTA"
            boolean r13 = r0.equals(r13)
            if (r13 == 0) goto L_0x00fb
            r13 = r1
            goto L_0x00fc
        L_0x00e7:
            java.lang.String r13 = "AFTKMST12"
            boolean r13 = r0.equals(r13)
            if (r13 == 0) goto L_0x00fb
            r13 = r7
            goto L_0x00fc
        L_0x00f1:
            java.lang.String r13 = "AFTJMST12"
            boolean r13 = r0.equals(r13)
            if (r13 == 0) goto L_0x00fb
            r13 = r5
            goto L_0x00fc
        L_0x00fb:
            r13 = r10
        L_0x00fc:
            switch(r13) {
                case 0: goto L_0x0080;
                case 1: goto L_0x0080;
                case 2: goto L_0x0080;
                case 3: goto L_0x0080;
                case 4: goto L_0x0080;
                case 5: goto L_0x0080;
                case 6: goto L_0x0080;
                case 7: goto L_0x0080;
                case 8: goto L_0x0080;
                default: goto L_0x00ff;
            }
        L_0x00ff:
            int r13 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ all -> 0x07aa }
            r15 = 26
            if (r13 > r15) goto L_0x07a2
            java.lang.String r13 = com.google.android.gms.internal.ads.zzgd.zzb     // Catch:{ all -> 0x07aa }
            int r16 = r13.hashCode()     // Catch:{ all -> 0x07aa }
            switch(r16) {
                case -2144781245: goto L_0x077f;
                case -2144781185: goto L_0x0774;
                case -2144781160: goto L_0x0769;
                case -2097309513: goto L_0x075e;
                case -2022874474: goto L_0x0753;
                case -1978993182: goto L_0x0748;
                case -1978990237: goto L_0x073d;
                case -1936688988: goto L_0x0732;
                case -1936688066: goto L_0x0727;
                case -1936688065: goto L_0x071b;
                case -1931988508: goto L_0x070f;
                case -1885099851: goto L_0x0703;
                case -1696512866: goto L_0x06f7;
                case -1680025915: goto L_0x06eb;
                case -1615810839: goto L_0x06df;
                case -1600724499: goto L_0x06d3;
                case -1554255044: goto L_0x06c7;
                case -1481772737: goto L_0x06bb;
                case -1481772730: goto L_0x06af;
                case -1481772729: goto L_0x06a3;
                case -1320080169: goto L_0x0697;
                case -1217592143: goto L_0x068b;
                case -1180384755: goto L_0x067f;
                case -1139198265: goto L_0x0673;
                case -1052835013: goto L_0x0667;
                case -993250464: goto L_0x065c;
                case -993250458: goto L_0x0651;
                case -965403638: goto L_0x0645;
                case -958336948: goto L_0x0639;
                case -879245230: goto L_0x062d;
                case -842500323: goto L_0x0621;
                case -821392978: goto L_0x0615;
                case -797483286: goto L_0x0609;
                case -794946968: goto L_0x05fd;
                case -788334647: goto L_0x05f1;
                case -782144577: goto L_0x05e5;
                case -575125681: goto L_0x05d9;
                case -521118391: goto L_0x05cd;
                case -430914369: goto L_0x05c1;
                case -290434366: goto L_0x05b5;
                case -282781963: goto L_0x05a9;
                case -277133239: goto L_0x059d;
                case -173639913: goto L_0x0591;
                case -56598463: goto L_0x0585;
                case 2126: goto L_0x0579;
                case 2564: goto L_0x056d;
                case 2715: goto L_0x0561;
                case 2719: goto L_0x0555;
                case 3091: goto L_0x0549;
                case 3483: goto L_0x053d;
                case 73405: goto L_0x0531;
                case 75537: goto L_0x0525;
                case 75739: goto L_0x0519;
                case 76779: goto L_0x050d;
                case 78669: goto L_0x0501;
                case 79305: goto L_0x04f5;
                case 80618: goto L_0x04e9;
                case 88274: goto L_0x04dd;
                case 98846: goto L_0x04d2;
                case 98848: goto L_0x04c7;
                case 99329: goto L_0x04bd;
                case 101481: goto L_0x04b1;
                case 1513190: goto L_0x04a6;
                case 1514184: goto L_0x049b;
                case 1514185: goto L_0x0490;
                case 2133089: goto L_0x0484;
                case 2133091: goto L_0x0478;
                case 2133120: goto L_0x046c;
                case 2133151: goto L_0x0460;
                case 2133182: goto L_0x0454;
                case 2133184: goto L_0x0448;
                case 2436959: goto L_0x043c;
                case 2463773: goto L_0x0430;
                case 2464648: goto L_0x0424;
                case 2689555: goto L_0x0418;
                case 3154429: goto L_0x040c;
                case 3284551: goto L_0x0400;
                case 3351335: goto L_0x03f4;
                case 3386211: goto L_0x03e8;
                case 41325051: goto L_0x03dc;
                case 51349633: goto L_0x03d1;
                case 51350594: goto L_0x03c6;
                case 55178625: goto L_0x03ba;
                case 61542055: goto L_0x03af;
                case 65355429: goto L_0x03a3;
                case 66214468: goto L_0x0397;
                case 66214470: goto L_0x038b;
                case 66214473: goto L_0x037f;
                case 66215429: goto L_0x0373;
                case 66215431: goto L_0x0367;
                case 66215433: goto L_0x035b;
                case 66216390: goto L_0x034f;
                case 76402249: goto L_0x0343;
                case 76404105: goto L_0x0337;
                case 76404911: goto L_0x032b;
                case 80963634: goto L_0x031f;
                case 82882791: goto L_0x0313;
                case 98715550: goto L_0x0307;
                case 101370885: goto L_0x02fb;
                case 102844228: goto L_0x02ef;
                case 165221241: goto L_0x02e4;
                case 182191441: goto L_0x02d8;
                case 245388979: goto L_0x02cc;
                case 287431619: goto L_0x02c0;
                case 307593612: goto L_0x02b4;
                case 308517133: goto L_0x02a8;
                case 316215098: goto L_0x029c;
                case 316215116: goto L_0x0290;
                case 316246811: goto L_0x0284;
                case 316246818: goto L_0x0278;
                case 407160593: goto L_0x026c;
                case 507412548: goto L_0x0260;
                case 793982701: goto L_0x0254;
                case 794038622: goto L_0x0248;
                case 794040393: goto L_0x023c;
                case 835649806: goto L_0x0230;
                case 917340916: goto L_0x0224;
                case 958008161: goto L_0x0218;
                case 1060579533: goto L_0x020c;
                case 1150207623: goto L_0x0200;
                case 1176899427: goto L_0x01f4;
                case 1280332038: goto L_0x01e8;
                case 1306947716: goto L_0x01dc;
                case 1349174697: goto L_0x01d0;
                case 1522194893: goto L_0x01c4;
                case 1691543273: goto L_0x01b8;
                case 1691544261: goto L_0x01ac;
                case 1709443163: goto L_0x01a0;
                case 1865889110: goto L_0x0194;
                case 1906253259: goto L_0x0188;
                case 1977196784: goto L_0x017c;
                case 2006372676: goto L_0x0170;
                case 2019281702: goto L_0x0164;
                case 2029784656: goto L_0x0158;
                case 2030379515: goto L_0x014c;
                case 2033393791: goto L_0x0140;
                case 2047190025: goto L_0x0134;
                case 2047252157: goto L_0x0128;
                case 2048319463: goto L_0x011c;
                case 2048855701: goto L_0x0110;
                default: goto L_0x010e;
            }
        L_0x010e:
            goto L_0x078a
        L_0x0110:
            java.lang.String r3 = "HWWAS-H"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 66
            goto L_0x078b
        L_0x011c:
            java.lang.String r3 = "HWVNS-H"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 65
            goto L_0x078b
        L_0x0128:
            java.lang.String r3 = "ELUGA_Prim"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 33
            goto L_0x078b
        L_0x0134:
            java.lang.String r3 = "ELUGA_Note"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 32
            goto L_0x078b
        L_0x0140:
            java.lang.String r3 = "ASUS_X00AD_2"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 14
            goto L_0x078b
        L_0x014c:
            java.lang.String r3 = "HWCAM-H"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 64
            goto L_0x078b
        L_0x0158:
            java.lang.String r3 = "HWBLN-H"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 63
            goto L_0x078b
        L_0x0164:
            java.lang.String r3 = "DM-01K"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 29
            goto L_0x078b
        L_0x0170:
            java.lang.String r3 = "BRAVIA_ATV3_4K"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 19
            goto L_0x078b
        L_0x017c:
            java.lang.String r3 = "Infinix-X572"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 69
            goto L_0x078b
        L_0x0188:
            java.lang.String r3 = "PB2-670M"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 100
            goto L_0x078b
        L_0x0194:
            java.lang.String r3 = "santoni"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 117(0x75, float:1.64E-43)
            goto L_0x078b
        L_0x01a0:
            java.lang.String r3 = "iball8735_9806"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 68
            goto L_0x078b
        L_0x01ac:
            java.lang.String r3 = "CPH1715"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 24
            goto L_0x078b
        L_0x01b8:
            java.lang.String r3 = "CPH1609"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 23
            goto L_0x078b
        L_0x01c4:
            java.lang.String r3 = "woods_f"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 133(0x85, float:1.86E-43)
            goto L_0x078b
        L_0x01d0:
            java.lang.String r3 = "htc_e56ml_dtul"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 61
            goto L_0x078b
        L_0x01dc:
            java.lang.String r3 = "EverStar_S"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 35
            goto L_0x078b
        L_0x01e8:
            java.lang.String r3 = "hwALE-H"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 62
            goto L_0x078b
        L_0x01f4:
            java.lang.String r3 = "itel_S41"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 71
            goto L_0x078b
        L_0x0200:
            java.lang.String r3 = "LS-5017"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 78
            goto L_0x078b
        L_0x020c:
            java.lang.String r3 = "panell_d"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 96
            goto L_0x078b
        L_0x0218:
            java.lang.String r3 = "j2xlteins"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 72
            goto L_0x078b
        L_0x0224:
            java.lang.String r3 = "A7000plus"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 10
            goto L_0x078b
        L_0x0230:
            java.lang.String r3 = "manning"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 81
            goto L_0x078b
        L_0x023c:
            java.lang.String r3 = "GIONEE_WBL7519"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 59
            goto L_0x078b
        L_0x0248:
            java.lang.String r3 = "GIONEE_WBL7365"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 58
            goto L_0x078b
        L_0x0254:
            java.lang.String r3 = "GIONEE_WBL5708"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 57
            goto L_0x078b
        L_0x0260:
            java.lang.String r3 = "QM16XE_U"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 114(0x72, float:1.6E-43)
            goto L_0x078b
        L_0x026c:
            java.lang.String r3 = "Pixi5-10_4G"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 106(0x6a, float:1.49E-43)
            goto L_0x078b
        L_0x0278:
            java.lang.String r3 = "TB3-850M"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 125(0x7d, float:1.75E-43)
            goto L_0x078b
        L_0x0284:
            java.lang.String r3 = "TB3-850F"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 124(0x7c, float:1.74E-43)
            goto L_0x078b
        L_0x0290:
            java.lang.String r3 = "TB3-730X"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 123(0x7b, float:1.72E-43)
            goto L_0x078b
        L_0x029c:
            java.lang.String r3 = "TB3-730F"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 122(0x7a, float:1.71E-43)
            goto L_0x078b
        L_0x02a8:
            java.lang.String r3 = "A7020a48"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 12
            goto L_0x078b
        L_0x02b4:
            java.lang.String r3 = "A7010a48"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 11
            goto L_0x078b
        L_0x02c0:
            java.lang.String r3 = "griffin"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 60
            goto L_0x078b
        L_0x02cc:
            java.lang.String r3 = "marino_f"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 82
            goto L_0x078b
        L_0x02d8:
            java.lang.String r3 = "CPY83_I00"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 25
            goto L_0x078b
        L_0x02e4:
            java.lang.String r3 = "A2016a40"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = r14
            goto L_0x078b
        L_0x02ef:
            java.lang.String r3 = "le_x6"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 77
            goto L_0x078b
        L_0x02fb:
            java.lang.String r3 = "l5460"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 76
            goto L_0x078b
        L_0x0307:
            java.lang.String r3 = "i9031"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 67
            goto L_0x078b
        L_0x0313:
            java.lang.String r3 = "X3_HK"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 135(0x87, float:1.89E-43)
            goto L_0x078b
        L_0x031f:
            java.lang.String r3 = "V23GB"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 128(0x80, float:1.794E-43)
            goto L_0x078b
        L_0x032b:
            java.lang.String r3 = "Q4310"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 112(0x70, float:1.57E-43)
            goto L_0x078b
        L_0x0337:
            java.lang.String r3 = "Q4260"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 110(0x6e, float:1.54E-43)
            goto L_0x078b
        L_0x0343:
            java.lang.String r3 = "PRO7S"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 108(0x6c, float:1.51E-43)
            goto L_0x078b
        L_0x034f:
            java.lang.String r3 = "F3311"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 48
            goto L_0x078b
        L_0x035b:
            java.lang.String r3 = "F3215"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 47
            goto L_0x078b
        L_0x0367:
            java.lang.String r3 = "F3213"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 46
            goto L_0x078b
        L_0x0373:
            java.lang.String r3 = "F3211"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 45
            goto L_0x078b
        L_0x037f:
            java.lang.String r3 = "F3116"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 44
            goto L_0x078b
        L_0x038b:
            java.lang.String r3 = "F3113"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 43
            goto L_0x078b
        L_0x0397:
            java.lang.String r3 = "F3111"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 42
            goto L_0x078b
        L_0x03a3:
            java.lang.String r3 = "E5643"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 30
            goto L_0x078b
        L_0x03af:
            java.lang.String r3 = "A1601"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = r7
            goto L_0x078b
        L_0x03ba:
            java.lang.String r3 = "Aura_Note_2"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 15
            goto L_0x078b
        L_0x03c6:
            java.lang.String r3 = "602LV"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = r6
            goto L_0x078b
        L_0x03d1:
            java.lang.String r3 = "601LV"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = r9
            goto L_0x078b
        L_0x03dc:
            java.lang.String r3 = "MEIZU_M5"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 83
            goto L_0x078b
        L_0x03e8:
            java.lang.String r3 = "p212"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 92
            goto L_0x078b
        L_0x03f4:
            java.lang.String r3 = "mido"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 85
            goto L_0x078b
        L_0x0400:
            java.lang.String r3 = "kate"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 75
            goto L_0x078b
        L_0x040c:
            java.lang.String r3 = "fugu"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 50
            goto L_0x078b
        L_0x0418:
            java.lang.String r3 = "XE2X"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 136(0x88, float:1.9E-43)
            goto L_0x078b
        L_0x0424:
            java.lang.String r3 = "Q427"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 111(0x6f, float:1.56E-43)
            goto L_0x078b
        L_0x0430:
            java.lang.String r3 = "Q350"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 109(0x6d, float:1.53E-43)
            goto L_0x078b
        L_0x043c:
            java.lang.String r3 = "P681"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 93
            goto L_0x078b
        L_0x0448:
            java.lang.String r3 = "F04J"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 41
            goto L_0x078b
        L_0x0454:
            java.lang.String r3 = "F04H"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 40
            goto L_0x078b
        L_0x0460:
            java.lang.String r3 = "F03H"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 39
            goto L_0x078b
        L_0x046c:
            java.lang.String r3 = "F02H"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 38
            goto L_0x078b
        L_0x0478:
            java.lang.String r3 = "F01J"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 37
            goto L_0x078b
        L_0x0484:
            java.lang.String r3 = "F01H"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 36
            goto L_0x078b
        L_0x0490:
            java.lang.String r3 = "1714"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = r8
            goto L_0x078b
        L_0x049b:
            java.lang.String r3 = "1713"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = r11
            goto L_0x078b
        L_0x04a6:
            java.lang.String r3 = "1601"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = r1
            goto L_0x078b
        L_0x04b1:
            java.lang.String r3 = "flo"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 49
            goto L_0x078b
        L_0x04bd:
            java.lang.String r4 = "deb"
            boolean r4 = r13.equals(r4)
            if (r4 == 0) goto L_0x078a
            goto L_0x078b
        L_0x04c7:
            java.lang.String r3 = "cv3"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = r12
            goto L_0x078b
        L_0x04d2:
            java.lang.String r3 = "cv1"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = r15
            goto L_0x078b
        L_0x04dd:
            java.lang.String r3 = "Z80"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 139(0x8b, float:1.95E-43)
            goto L_0x078b
        L_0x04e9:
            java.lang.String r3 = "QX1"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 115(0x73, float:1.61E-43)
            goto L_0x078b
        L_0x04f5:
            java.lang.String r3 = "PLE"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 107(0x6b, float:1.5E-43)
            goto L_0x078b
        L_0x0501:
            java.lang.String r3 = "P85"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 94
            goto L_0x078b
        L_0x050d:
            java.lang.String r3 = "MX6"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 86
            goto L_0x078b
        L_0x0519:
            java.lang.String r3 = "M5c"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 80
            goto L_0x078b
        L_0x0525:
            java.lang.String r3 = "M04"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 79
            goto L_0x078b
        L_0x0531:
            java.lang.String r3 = "JGZ"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 73
            goto L_0x078b
        L_0x053d:
            java.lang.String r3 = "mh"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 84
            goto L_0x078b
        L_0x0549:
            java.lang.String r3 = "b5"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 16
            goto L_0x078b
        L_0x0555:
            java.lang.String r3 = "V5"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 129(0x81, float:1.81E-43)
            goto L_0x078b
        L_0x0561:
            java.lang.String r3 = "V1"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 127(0x7f, float:1.78E-43)
            goto L_0x078b
        L_0x056d:
            java.lang.String r3 = "Q5"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 113(0x71, float:1.58E-43)
            goto L_0x078b
        L_0x0579:
            java.lang.String r3 = "C1"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 20
            goto L_0x078b
        L_0x0585:
            java.lang.String r3 = "woods_fn"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 134(0x86, float:1.88E-43)
            goto L_0x078b
        L_0x0591:
            java.lang.String r3 = "ELUGA_A3_Pro"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 31
            goto L_0x078b
        L_0x059d:
            java.lang.String r3 = "Z12_PRO"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 138(0x8a, float:1.93E-43)
            goto L_0x078b
        L_0x05a9:
            java.lang.String r3 = "BLACK-1X"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 17
            goto L_0x078b
        L_0x05b5:
            java.lang.String r3 = "taido_row"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 121(0x79, float:1.7E-43)
            goto L_0x078b
        L_0x05c1:
            java.lang.String r3 = "Pixi4-7_3G"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 105(0x69, float:1.47E-43)
            goto L_0x078b
        L_0x05cd:
            java.lang.String r3 = "GIONEE_GBL7360"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 53
            goto L_0x078b
        L_0x05d9:
            java.lang.String r3 = "GiONEE_CBL7513"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 51
            goto L_0x078b
        L_0x05e5:
            java.lang.String r3 = "OnePlus5T"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 91
            goto L_0x078b
        L_0x05f1:
            java.lang.String r3 = "whyred"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 132(0x84, float:1.85E-43)
            goto L_0x078b
        L_0x05fd:
            java.lang.String r3 = "watson"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 131(0x83, float:1.84E-43)
            goto L_0x078b
        L_0x0609:
            java.lang.String r3 = "SVP-DTV15"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 119(0x77, float:1.67E-43)
            goto L_0x078b
        L_0x0615:
            java.lang.String r3 = "A7000-a"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 9
            goto L_0x078b
        L_0x0621:
            java.lang.String r3 = "nicklaus_f"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 88
            goto L_0x078b
        L_0x062d:
            java.lang.String r3 = "tcl_eu"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 126(0x7e, float:1.77E-43)
            goto L_0x078b
        L_0x0639:
            java.lang.String r3 = "ELUGA_Ray_X"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 34
            goto L_0x078b
        L_0x0645:
            java.lang.String r3 = "s905x018"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 120(0x78, float:1.68E-43)
            goto L_0x078b
        L_0x0651:
            java.lang.String r3 = "A10-70L"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = r5
            goto L_0x078b
        L_0x065c:
            java.lang.String r3 = "A10-70F"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = r4
            goto L_0x078b
        L_0x0667:
            java.lang.String r3 = "namath"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 87
            goto L_0x078b
        L_0x0673:
            java.lang.String r3 = "Slate_Pro"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 118(0x76, float:1.65E-43)
            goto L_0x078b
        L_0x067f:
            java.lang.String r3 = "iris60"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 70
            goto L_0x078b
        L_0x068b:
            java.lang.String r3 = "BRAVIA_ATV2"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 18
            goto L_0x078b
        L_0x0697:
            java.lang.String r3 = "GiONEE_GBL7319"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 52
            goto L_0x078b
        L_0x06a3:
            java.lang.String r3 = "panell_dt"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 99
            goto L_0x078b
        L_0x06af:
            java.lang.String r3 = "panell_ds"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 98
            goto L_0x078b
        L_0x06bb:
            java.lang.String r3 = "panell_dl"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 97
            goto L_0x078b
        L_0x06c7:
            java.lang.String r3 = "vernee_M5"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 130(0x82, float:1.82E-43)
            goto L_0x078b
        L_0x06d3:
            java.lang.String r3 = "pacificrim"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 95
            goto L_0x078b
        L_0x06df:
            java.lang.String r3 = "Phantom6"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 104(0x68, float:1.46E-43)
            goto L_0x078b
        L_0x06eb:
            java.lang.String r3 = "ComioS1"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 21
            goto L_0x078b
        L_0x06f7:
            java.lang.String r3 = "XT1663"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 137(0x89, float:1.92E-43)
            goto L_0x078b
        L_0x0703:
            java.lang.String r3 = "RAIJIN"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 116(0x74, float:1.63E-43)
            goto L_0x078b
        L_0x070f:
            java.lang.String r3 = "AquaPowerM"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 13
            goto L_0x078b
        L_0x071b:
            java.lang.String r3 = "PGN611"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 103(0x67, float:1.44E-43)
            goto L_0x078b
        L_0x0727:
            java.lang.String r3 = "PGN610"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 102(0x66, float:1.43E-43)
            goto L_0x078b
        L_0x0732:
            java.lang.String r3 = "PGN528"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 101(0x65, float:1.42E-43)
            goto L_0x078b
        L_0x073d:
            java.lang.String r3 = "NX573J"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 90
            goto L_0x078b
        L_0x0748:
            java.lang.String r3 = "NX541J"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 89
            goto L_0x078b
        L_0x0753:
            java.lang.String r3 = "CP8676_I02"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 22
            goto L_0x078b
        L_0x075e:
            java.lang.String r3 = "K50a40"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 74
            goto L_0x078b
        L_0x0769:
            java.lang.String r3 = "GIONEE_SWW1631"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 56
            goto L_0x078b
        L_0x0774:
            java.lang.String r3 = "GIONEE_SWW1627"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 55
            goto L_0x078b
        L_0x077f:
            java.lang.String r3 = "GIONEE_SWW1609"
            boolean r3 = r13.equals(r3)
            if (r3 == 0) goto L_0x078a
            r3 = 54
            goto L_0x078b
        L_0x078a:
            r3 = r10
        L_0x078b:
            switch(r3) {
                case 0: goto L_0x0080;
                case 1: goto L_0x0080;
                case 2: goto L_0x0080;
                case 3: goto L_0x0080;
                case 4: goto L_0x0080;
                case 5: goto L_0x0080;
                case 6: goto L_0x0080;
                case 7: goto L_0x0080;
                case 8: goto L_0x0080;
                case 9: goto L_0x0080;
                case 10: goto L_0x0080;
                case 11: goto L_0x0080;
                case 12: goto L_0x0080;
                case 13: goto L_0x0080;
                case 14: goto L_0x0080;
                case 15: goto L_0x0080;
                case 16: goto L_0x0080;
                case 17: goto L_0x0080;
                case 18: goto L_0x0080;
                case 19: goto L_0x0080;
                case 20: goto L_0x0080;
                case 21: goto L_0x0080;
                case 22: goto L_0x0080;
                case 23: goto L_0x0080;
                case 24: goto L_0x0080;
                case 25: goto L_0x0080;
                case 26: goto L_0x0080;
                case 27: goto L_0x0080;
                case 28: goto L_0x0080;
                case 29: goto L_0x0080;
                case 30: goto L_0x0080;
                case 31: goto L_0x0080;
                case 32: goto L_0x0080;
                case 33: goto L_0x0080;
                case 34: goto L_0x0080;
                case 35: goto L_0x0080;
                case 36: goto L_0x0080;
                case 37: goto L_0x0080;
                case 38: goto L_0x0080;
                case 39: goto L_0x0080;
                case 40: goto L_0x0080;
                case 41: goto L_0x0080;
                case 42: goto L_0x0080;
                case 43: goto L_0x0080;
                case 44: goto L_0x0080;
                case 45: goto L_0x0080;
                case 46: goto L_0x0080;
                case 47: goto L_0x0080;
                case 48: goto L_0x0080;
                case 49: goto L_0x0080;
                case 50: goto L_0x0080;
                case 51: goto L_0x0080;
                case 52: goto L_0x0080;
                case 53: goto L_0x0080;
                case 54: goto L_0x0080;
                case 55: goto L_0x0080;
                case 56: goto L_0x0080;
                case 57: goto L_0x0080;
                case 58: goto L_0x0080;
                case 59: goto L_0x0080;
                case 60: goto L_0x0080;
                case 61: goto L_0x0080;
                case 62: goto L_0x0080;
                case 63: goto L_0x0080;
                case 64: goto L_0x0080;
                case 65: goto L_0x0080;
                case 66: goto L_0x0080;
                case 67: goto L_0x0080;
                case 68: goto L_0x0080;
                case 69: goto L_0x0080;
                case 70: goto L_0x0080;
                case 71: goto L_0x0080;
                case 72: goto L_0x0080;
                case 73: goto L_0x0080;
                case 74: goto L_0x0080;
                case 75: goto L_0x0080;
                case 76: goto L_0x0080;
                case 77: goto L_0x0080;
                case 78: goto L_0x0080;
                case 79: goto L_0x0080;
                case 80: goto L_0x0080;
                case 81: goto L_0x0080;
                case 82: goto L_0x0080;
                case 83: goto L_0x0080;
                case 84: goto L_0x0080;
                case 85: goto L_0x0080;
                case 86: goto L_0x0080;
                case 87: goto L_0x0080;
                case 88: goto L_0x0080;
                case 89: goto L_0x0080;
                case 90: goto L_0x0080;
                case 91: goto L_0x0080;
                case 92: goto L_0x0080;
                case 93: goto L_0x0080;
                case 94: goto L_0x0080;
                case 95: goto L_0x0080;
                case 96: goto L_0x0080;
                case 97: goto L_0x0080;
                case 98: goto L_0x0080;
                case 99: goto L_0x0080;
                case 100: goto L_0x0080;
                case 101: goto L_0x0080;
                case 102: goto L_0x0080;
                case 103: goto L_0x0080;
                case 104: goto L_0x0080;
                case 105: goto L_0x0080;
                case 106: goto L_0x0080;
                case 107: goto L_0x0080;
                case 108: goto L_0x0080;
                case 109: goto L_0x0080;
                case 110: goto L_0x0080;
                case 111: goto L_0x0080;
                case 112: goto L_0x0080;
                case 113: goto L_0x0080;
                case 114: goto L_0x0080;
                case 115: goto L_0x0080;
                case 116: goto L_0x0080;
                case 117: goto L_0x0080;
                case 118: goto L_0x0080;
                case 119: goto L_0x0080;
                case 120: goto L_0x0080;
                case 121: goto L_0x0080;
                case 122: goto L_0x0080;
                case 123: goto L_0x0080;
                case 124: goto L_0x0080;
                case 125: goto L_0x0080;
                case 126: goto L_0x0080;
                case 127: goto L_0x0080;
                case 128: goto L_0x0080;
                case 129: goto L_0x0080;
                case 130: goto L_0x0080;
                case 131: goto L_0x0080;
                case 132: goto L_0x0080;
                case 133: goto L_0x0080;
                case 134: goto L_0x0080;
                case 135: goto L_0x0080;
                case 136: goto L_0x0080;
                case 137: goto L_0x0080;
                case 138: goto L_0x0080;
                case 139: goto L_0x0080;
                default: goto L_0x078e;
            }
        L_0x078e:
            int r3 = r0.hashCode()     // Catch:{ all -> 0x07aa }
            r4 = -594534941(0xffffffffdc901de3, float:-3.2452206E17)
            if (r3 == r4) goto L_0x0798
            goto L_0x07a2
        L_0x0798:
            java.lang.String r3 = "JSN-L21"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x07a2
            goto L_0x0080
        L_0x07a2:
            zzd = r1     // Catch:{ all -> 0x07aa }
            zzc = r11     // Catch:{ all -> 0x07aa }
        L_0x07a6:
            monitor-exit(r2)     // Catch:{ all -> 0x07aa }
            boolean r0 = zzd
            return r0
        L_0x07aa:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x07aa }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzabj.zzaQ(java.lang.String):boolean");
    }

    private static List zzaR(Context context, zztx zztx, zzan zzan, boolean z, boolean z2) throws zzud {
        if (zzan.zzn == null) {
            return zzgbc.zzm();
        }
        if (zzgd.zza >= 26 && MimeTypes.VIDEO_DOLBY_VISION.equals(zzan.zzn) && !zzabh.zza(context)) {
            List zzd2 = zzuj.zzd(zztx, zzan, z, z2);
            if (!zzd2.isEmpty()) {
                return zzd2;
            }
        }
        return zzuj.zzf(zztx, zzan, z, z2);
    }

    private final void zzaS() {
        zzdv zzdv = this.zzA;
        if (zzdv != null) {
            this.zzg.zzt(zzdv);
        }
    }

    /* access modifiers changed from: private */
    @RequiresNonNull({"displaySurface"})
    public final void zzaT() {
        this.zzg.zzq(this.zzn);
        this.zzq = true;
    }

    private final void zzaU() {
        Surface surface = this.zzn;
        zzabm zzabm = this.zzp;
        if (surface == zzabm) {
            this.zzn = null;
        }
        if (zzabm != null) {
            zzabm.release();
            this.zzp = null;
        }
    }

    private final boolean zzaV(zztp zztp) {
        if (zzgd.zza < 23 || zzaQ(zztp.zza)) {
            return false;
        }
        if (!zztp.zzf) {
            return true;
        }
        if (!zzabm.zzb(this.zze)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0075, code lost:
        if (r3.equals(com.google.android.exoplayer2.util.MimeTypes.VIDEO_AV1) != false) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00d0, code lost:
        return ((r0 * r1) * 3) / r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0024, code lost:
        r11 = ((java.lang.Integer) r11.first).intValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zzad(com.google.android.gms.internal.ads.zztp r10, com.google.android.gms.internal.ads.zzan r11) {
        /*
            int r0 = r11.zzs
            int r1 = r11.zzt
            r2 = -1
            if (r0 == r2) goto L_0x00d1
            if (r1 != r2) goto L_0x000b
            goto L_0x00d1
        L_0x000b:
            java.lang.String r3 = r11.zzn
            r3.getClass()
            java.lang.String r4 = "video/dolby-vision"
            boolean r4 = r4.equals(r3)
            r5 = 2
            r6 = 1
            java.lang.String r7 = "video/avc"
            java.lang.String r8 = "video/hevc"
            if (r4 == 0) goto L_0x0037
            android.util.Pair r11 = com.google.android.gms.internal.ads.zzuj.zza(r11)
            if (r11 == 0) goto L_0x0036
            java.lang.Object r11 = r11.first
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r3 = 512(0x200, float:7.175E-43)
            if (r11 == r3) goto L_0x0034
            if (r11 == r6) goto L_0x0034
            if (r11 != r5) goto L_0x0036
        L_0x0034:
            r3 = r7
            goto L_0x0037
        L_0x0036:
            r3 = r8
        L_0x0037:
            int r11 = r3.hashCode()
            r4 = 3
            r9 = 4
            switch(r11) {
                case -1664118616: goto L_0x0078;
                case -1662735862: goto L_0x006f;
                case -1662541442: goto L_0x0067;
                case 1187890754: goto L_0x005d;
                case 1331836730: goto L_0x0055;
                case 1599127256: goto L_0x004b;
                case 1599127257: goto L_0x0041;
                default: goto L_0x0040;
            }
        L_0x0040:
            goto L_0x0082
        L_0x0041:
            java.lang.String r11 = "video/x-vnd.on2.vp9"
            boolean r11 = r3.equals(r11)
            if (r11 == 0) goto L_0x0082
            r5 = 6
            goto L_0x0083
        L_0x004b:
            java.lang.String r11 = "video/x-vnd.on2.vp8"
            boolean r11 = r3.equals(r11)
            if (r11 == 0) goto L_0x0082
            r5 = r4
            goto L_0x0083
        L_0x0055:
            boolean r11 = r3.equals(r7)
            if (r11 == 0) goto L_0x0082
            r5 = 5
            goto L_0x0083
        L_0x005d:
            java.lang.String r11 = "video/mp4v-es"
            boolean r11 = r3.equals(r11)
            if (r11 == 0) goto L_0x0082
            r5 = r6
            goto L_0x0083
        L_0x0067:
            boolean r11 = r3.equals(r8)
            if (r11 == 0) goto L_0x0082
            r5 = r9
            goto L_0x0083
        L_0x006f:
            java.lang.String r11 = "video/av01"
            boolean r11 = r3.equals(r11)
            if (r11 == 0) goto L_0x0082
            goto L_0x0083
        L_0x0078:
            java.lang.String r11 = "video/3gpp"
            boolean r11 = r3.equals(r11)
            if (r11 == 0) goto L_0x0082
            r5 = 0
            goto L_0x0083
        L_0x0082:
            r5 = r2
        L_0x0083:
            switch(r5) {
                case 0: goto L_0x00cd;
                case 1: goto L_0x00cd;
                case 2: goto L_0x00cd;
                case 3: goto L_0x00cd;
                case 4: goto L_0x00c3;
                case 5: goto L_0x008a;
                case 6: goto L_0x0087;
                default: goto L_0x0086;
            }
        L_0x0086:
            goto L_0x00d1
        L_0x0087:
            r9 = 8
            goto L_0x00cd
        L_0x008a:
            java.lang.String r11 = "BRAVIA 4K 2015"
            java.lang.String r3 = com.google.android.gms.internal.ads.zzgd.zzd
            boolean r11 = r11.equals(r3)
            if (r11 != 0) goto L_0x00d1
            java.lang.String r11 = "Amazon"
            java.lang.String r3 = com.google.android.gms.internal.ads.zzgd.zzc
            boolean r11 = r11.equals(r3)
            if (r11 == 0) goto L_0x00b6
            java.lang.String r11 = "KFSOWI"
            java.lang.String r3 = com.google.android.gms.internal.ads.zzgd.zzd
            boolean r11 = r11.equals(r3)
            if (r11 != 0) goto L_0x00d1
            java.lang.String r11 = "AFTS"
            java.lang.String r3 = com.google.android.gms.internal.ads.zzgd.zzd
            boolean r11 = r11.equals(r3)
            if (r11 == 0) goto L_0x00b6
            boolean r10 = r10.zzf
            if (r10 != 0) goto L_0x00d1
        L_0x00b6:
            int r0 = r0 + 15
            int r1 = r1 + 15
            int r0 = r0 / 16
            int r1 = r1 / 16
            int r0 = r0 * r1
            int r0 = r0 * 768
            int r0 = r0 / r9
            return r0
        L_0x00c3:
            int r0 = r0 * r1
            int r0 = r0 * r4
            int r0 = r0 / r9
            r10 = 2097152(0x200000, float:2.938736E-39)
            int r10 = java.lang.Math.max(r10, r0)
            return r10
        L_0x00cd:
            int r0 = r0 * r1
            int r0 = r0 * r4
            int r0 = r0 / r9
            return r0
        L_0x00d1:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzabj.zzad(com.google.android.gms.internal.ads.zztp, com.google.android.gms.internal.ads.zzan):int");
    }

    protected static int zzae(zztp zztp, zzan zzan) {
        if (zzan.zzo == -1) {
            return zzad(zztp, zzan);
        }
        int size = zzan.zzp.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += ((byte[]) zzan.zzp.get(i2)).length;
        }
        return zzan.zzo + i;
    }

    /* access modifiers changed from: protected */
    public final void zzA() {
        if (this.zzF != null) {
            this.zzf.zzn();
        }
    }

    /* access modifiers changed from: protected */
    public final void zzC() {
        try {
            super.zzC();
            this.zzC = false;
            if (this.zzp != null) {
                zzaU();
            }
        } catch (Throwable th) {
            this.zzC = false;
            if (this.zzp != null) {
                zzaU();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzD() {
        this.zzt = 0;
        zzh();
        this.zzs = SystemClock.elapsedRealtime();
        this.zzw = 0;
        this.zzx = 0;
        this.zzi.zzg();
    }

    /* access modifiers changed from: protected */
    public final void zzE() {
        if (this.zzt > 0) {
            zzh();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.zzg.zzd(this.zzt, elapsedRealtime - this.zzs);
            this.zzt = 0;
            this.zzs = elapsedRealtime;
        }
        int i = this.zzx;
        if (i != 0) {
            this.zzg.zzr(this.zzw, i);
            this.zzw = 0;
            this.zzx = 0;
        }
        this.zzi.zzh();
    }

    public final void zzM(float f, float f2) throws zzjh {
        super.zzM(f, f2);
        this.zzi.zzn(f);
        zzacm zzacm = this.zzF;
        if (zzacm != null) {
            zzabc.zzi(((zzaba) zzacm).zza, f);
        }
    }

    public final String zzU() {
        return "MediaCodecVideoRenderer";
    }

    public final void zzV(long j, long j2) throws zzjh {
        super.zzV(j, j2);
        zzacm zzacm = this.zzF;
        if (zzacm != null) {
            try {
                zzacm.zzh(j, j2);
            } catch (zzacl e) {
                throw zzi(e, e.zza, false, 7001);
            }
        }
    }

    public final boolean zzW() {
        return super.zzW() && this.zzF == null;
    }

    public final boolean zzX() {
        zzabm zzabm;
        boolean z = false;
        if (super.zzX() && this.zzF == null) {
            z = true;
        }
        if (!z || (((zzabm = this.zzp) == null || this.zzn != zzabm) && zzaw() != null)) {
            return this.zzi.zzo(z);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final float zzZ(float f, zzan zzan, zzan[] zzanArr) {
        float f2 = -1.0f;
        for (zzan zzan2 : zzanArr) {
            float f3 = zzan2.zzu;
            if (f3 != -1.0f) {
                f2 = Math.max(f2, f3);
            }
        }
        if (f2 == -1.0f) {
            return -1.0f;
        }
        return f2 * f;
    }

    /* access modifiers changed from: protected */
    public final void zzaA(long j) {
        super.zzaA(j);
        this.zzv--;
    }

    /* access modifiers changed from: protected */
    public final void zzaB(zzin zzin) throws zzjh {
        this.zzv++;
        int i = zzgd.zza;
    }

    /* access modifiers changed from: protected */
    public final void zzaC(zzan zzan) throws zzjh {
        zzfv zzfv;
        if (this.zzB && !this.zzC) {
            zzacm zzd2 = this.zzf.zzd();
            this.zzF = zzd2;
            try {
                zzd2.zzf(zzan, zzh());
                this.zzF.zzi(new zzabg(this), zzggk.zzb());
                zzabn zzabn = this.zzE;
                if (zzabn != null) {
                    ((zzaba) this.zzF).zza.zzj = zzabn;
                }
                this.zzF.zzj(zzau());
                Surface surface = this.zzn;
                if (!(surface == null || (zzfv = this.zzo) == null)) {
                    this.zzf.zzq(surface, zzfv);
                }
            } catch (zzacl e) {
                throw zzi(e, zzan, false, AacUtil.AAC_HE_V2_MAX_RATE_BYTES_PER_SECOND);
            }
        }
        this.zzC = true;
    }

    /* access modifiers changed from: protected */
    public final void zzaE() {
        super.zzaE();
        this.zzv = 0;
    }

    /* access modifiers changed from: protected */
    public final boolean zzaK(zztp zztp) {
        return this.zzn != null || zzaV(zztp);
    }

    /* access modifiers changed from: protected */
    public final void zzaM(zztm zztm, int i, long j) {
        Trace.beginSection("skipVideoBuffer");
        zztm.zzn(i, false);
        Trace.endSection();
        this.zza.zzf++;
    }

    /* access modifiers changed from: protected */
    public final void zzaN(int i, int i2) {
        zzix zzix = this.zza;
        zzix.zzh += i;
        int i3 = i + i2;
        zzix.zzg += i3;
        this.zzt += i3;
        int i4 = this.zzu + i3;
        this.zzu = i4;
        zzix.zzi = Math.max(i4, zzix.zzi);
    }

    /* access modifiers changed from: protected */
    public final void zzaO(long j) {
        zzix zzix = this.zza;
        zzix.zzk += j;
        zzix.zzl++;
        this.zzw += j;
        this.zzx++;
    }

    /* access modifiers changed from: protected */
    public final boolean zzaP(long j, boolean z) throws zzjh {
        int zzd2 = zzd(j);
        if (zzd2 == 0) {
            return false;
        }
        if (z) {
            zzix zzix = this.zza;
            zzix.zzd += zzd2;
            zzix.zzf += this.zzv;
        } else {
            this.zza.zzj++;
            zzaN(zzd2, this.zzv);
        }
        zzaG();
        zzacm zzacm = this.zzF;
        if (zzacm != null) {
            zzacm.zze();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final int zzaa(zztx zztx, zzan zzan) throws zzud {
        boolean z;
        int i = 128;
        if (!zzcg.zzh(zzan.zzn)) {
            return 128;
        }
        int i2 = 1;
        int i3 = 0;
        boolean z2 = zzan.zzq != null;
        List zzaR = zzaR(this.zze, zztx, zzan, z2, false);
        if (z2 && zzaR.isEmpty()) {
            zzaR = zzaR(this.zze, zztx, zzan, false, false);
        }
        if (!zzaR.isEmpty()) {
            if (!zzaL(zzan)) {
                i2 = 2;
            } else {
                zztp zztp = (zztp) zzaR.get(0);
                boolean zze2 = zztp.zze(zzan);
                if (!zze2) {
                    int i4 = 1;
                    while (true) {
                        if (i4 >= zzaR.size()) {
                            break;
                        }
                        zztp zztp2 = (zztp) zzaR.get(i4);
                        if (zztp2.zze(zzan)) {
                            zze2 = true;
                            z = false;
                            zztp = zztp2;
                            break;
                        }
                        i4++;
                    }
                }
                z = true;
                int i5 = true != zze2 ? 3 : 4;
                int i6 = true != zztp.zzf(zzan) ? 8 : 16;
                int i7 = true != zztp.zzg ? 0 : 64;
                if (true != z) {
                    i = 0;
                }
                if (zzgd.zza >= 26 && MimeTypes.VIDEO_DOLBY_VISION.equals(zzan.zzn) && !zzabh.zza(this.zze)) {
                    i = 256;
                }
                if (zze2) {
                    List zzaR2 = zzaR(this.zze, zztx, zzan, z2, true);
                    if (!zzaR2.isEmpty()) {
                        zztp zztp3 = (zztp) zzuj.zzg(zzaR2, zzan).get(0);
                        if (zztp3.zze(zzan) && zztp3.zzf(zzan)) {
                            i3 = 32;
                        }
                    }
                }
                return i5 | i6 | i3 | i7 | i;
            }
        }
        return i2 | 128;
    }

    /* access modifiers changed from: protected */
    public final zziy zzab(zztp zztp, zzan zzan, zzan zzan2) {
        int i;
        int i2;
        zziy zzb2 = zztp.zzb(zzan, zzan2);
        int i3 = zzb2.zze;
        zzabi zzabi = this.zzk;
        zzabi.getClass();
        if (zzan2.zzs > zzabi.zza || zzan2.zzt > zzabi.zzb) {
            i3 |= 256;
        }
        if (zzae(zztp, zzan2) > zzabi.zzc) {
            i3 |= 64;
        }
        String str = zztp.zza;
        if (i3 != 0) {
            i = i3;
            i2 = 0;
        } else {
            i2 = zzb2.zzd;
            i = 0;
        }
        return new zziy(str, zzan, zzan2, i2, i);
    }

    /* access modifiers changed from: protected */
    public final zziy zzac(zzlj zzlj) throws zzjh {
        zziy zzac = super.zzac(zzlj);
        zzan zzan = zzlj.zza;
        zzan.getClass();
        this.zzg.zzf(zzan, zzac);
        return zzac;
    }

    /* JADX WARNING: type inference failed for: r6v15, types: [com.google.android.gms.internal.ads.zzds, java.lang.Throwable, java.lang.Object] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x022a  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0232  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01d2  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01e5  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x021e  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0225  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zztk zzaf(com.google.android.gms.internal.ads.zztp r20, com.google.android.gms.internal.ads.zzan r21, android.media.MediaCrypto r22, float r23) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r23
            com.google.android.gms.internal.ads.zzabm r4 = r0.zzp
            if (r4 == 0) goto L_0x0015
            boolean r5 = r1.zzf
            boolean r4 = r4.zza
            if (r4 == r5) goto L_0x0015
            r19.zzaU()
        L_0x0015:
            java.lang.String r4 = r1.zzc
            com.google.android.gms.internal.ads.zzan[] r5 = r19.zzT()
            int r6 = r2.zzs
            int r7 = r2.zzt
            int r8 = zzae(r20, r21)
            int r9 = r5.length
            r10 = -1
            r13 = 1
            if (r9 != r13) goto L_0x003d
            if (r8 == r10) goto L_0x0039
            int r5 = zzad(r20, r21)
            if (r5 == r10) goto L_0x0039
            float r8 = (float) r8
            r9 = 1069547520(0x3fc00000, float:1.5)
            float r8 = r8 * r9
            int r8 = (int) r8
            int r8 = java.lang.Math.min(r8, r5)
        L_0x0039:
            r16 = r4
            goto L_0x0138
        L_0x003d:
            r14 = 0
            r15 = 0
        L_0x003f:
            if (r14 >= r9) goto L_0x0083
            r11 = r5[r14]
            com.google.android.gms.internal.ads.zzt r12 = r2.zzz
            if (r12 == 0) goto L_0x0058
            com.google.android.gms.internal.ads.zzt r12 = r11.zzz
            if (r12 != 0) goto L_0x0058
            com.google.android.gms.internal.ads.zzal r11 = r11.zzb()
            com.google.android.gms.internal.ads.zzt r12 = r2.zzz
            r11.zzA(r12)
            com.google.android.gms.internal.ads.zzan r11 = r11.zzad()
        L_0x0058:
            com.google.android.gms.internal.ads.zziy r12 = r1.zzb(r2, r11)
            int r12 = r12.zzd
            if (r12 == 0) goto L_0x007f
            int r12 = r11.zzs
            if (r12 == r10) goto L_0x006b
            int r13 = r11.zzt
            if (r13 != r10) goto L_0x0069
            goto L_0x006b
        L_0x0069:
            r13 = 0
            goto L_0x006c
        L_0x006b:
            r13 = 1
        L_0x006c:
            r15 = r15 | r13
            int r6 = java.lang.Math.max(r6, r12)
            int r12 = r11.zzt
            int r7 = java.lang.Math.max(r7, r12)
            int r11 = zzae(r1, r11)
            int r8 = java.lang.Math.max(r8, r11)
        L_0x007f:
            int r14 = r14 + 1
            r13 = 1
            goto L_0x003f
        L_0x0083:
            if (r15 == 0) goto L_0x0039
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r9 = "Resolutions unknown. Codec max resolution: "
            r5.<init>(r9)
            r5.append(r6)
            java.lang.String r9 = "x"
            r5.append(r9)
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            java.lang.String r10 = "MediaCodecVideoRenderer"
            com.google.android.gms.internal.ads.zzfk.zzf(r10, r5)
            int r5 = r2.zzt
            int r11 = r2.zzs
            if (r5 <= r11) goto L_0x00a8
            r12 = 1
            goto L_0x00a9
        L_0x00a8:
            r12 = 0
        L_0x00a9:
            if (r12 == 0) goto L_0x00ad
            r13 = r5
            goto L_0x00ae
        L_0x00ad:
            r13 = r11
        L_0x00ae:
            r14 = 1
            if (r14 != r12) goto L_0x00b2
            r5 = r11
        L_0x00b2:
            int[] r11 = zzb
            r14 = 0
        L_0x00b5:
            r15 = 9
            if (r14 >= r15) goto L_0x00fa
            float r15 = (float) r5
            float r3 = (float) r13
            r16 = r4
            r4 = r11[r14]
            r17 = r11
            float r11 = (float) r4
            if (r4 <= r13) goto L_0x00fc
            float r15 = r15 / r3
            float r11 = r11 * r15
            int r3 = (int) r11
            if (r3 > r5) goto L_0x00ca
            goto L_0x00fc
        L_0x00ca:
            int r11 = com.google.android.gms.internal.ads.zzgd.zza
            r11 = 1
            if (r11 == r12) goto L_0x00d1
            r15 = r4
            goto L_0x00d2
        L_0x00d1:
            r15 = r3
        L_0x00d2:
            if (r11 != r12) goto L_0x00d5
            goto L_0x00d6
        L_0x00d5:
            r4 = r3
        L_0x00d6:
            android.graphics.Point r3 = r1.zza(r15, r4)
            float r4 = r2.zzu
            if (r3 == 0) goto L_0x00ed
            r11 = r5
            double r4 = (double) r4
            int r15 = r3.x
            r18 = r11
            int r11 = r3.y
            boolean r4 = r1.zzg(r15, r11, r4)
            if (r4 == 0) goto L_0x00ef
            goto L_0x00fd
        L_0x00ed:
            r18 = r5
        L_0x00ef:
            int r14 = r14 + 1
            r3 = r23
            r4 = r16
            r11 = r17
            r5 = r18
            goto L_0x00b5
        L_0x00fa:
            r16 = r4
        L_0x00fc:
            r3 = 0
        L_0x00fd:
            if (r3 == 0) goto L_0x0138
            int r4 = r3.x
            int r6 = java.lang.Math.max(r6, r4)
            int r3 = r3.y
            int r7 = java.lang.Math.max(r7, r3)
            com.google.android.gms.internal.ads.zzal r3 = r21.zzb()
            r3.zzac(r6)
            r3.zzI(r7)
            com.google.android.gms.internal.ads.zzan r3 = r3.zzad()
            int r3 = zzad(r1, r3)
            int r8 = java.lang.Math.max(r8, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Codec max resolution adjusted to: "
            r3.<init>(r4)
            r3.append(r6)
            r3.append(r9)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r10, r3)
        L_0x0138:
            com.google.android.gms.internal.ads.zzabi r3 = new com.google.android.gms.internal.ads.zzabi
            r3.<init>(r6, r7, r8)
            r0.zzk = r3
            boolean r4 = r0.zzh
            android.media.MediaFormat r5 = new android.media.MediaFormat
            r5.<init>()
            java.lang.String r6 = "mime"
            r7 = r16
            r5.setString(r6, r7)
            int r6 = r2.zzs
            java.lang.String r7 = "width"
            r5.setInteger(r7, r6)
            int r6 = r2.zzt
            java.lang.String r7 = "height"
            r5.setInteger(r7, r6)
            java.util.List r6 = r2.zzp
            com.google.android.gms.internal.ads.zzfn.zzb(r5, r6)
            float r6 = r2.zzu
            r7 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r8 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r8 == 0) goto L_0x016d
            java.lang.String r8 = "frame-rate"
            r5.setFloat(r8, r6)
        L_0x016d:
            int r6 = r2.zzv
            java.lang.String r8 = "rotation-degrees"
            com.google.android.gms.internal.ads.zzfn.zza(r5, r8, r6)
            com.google.android.gms.internal.ads.zzt r6 = r2.zzz
            if (r6 == 0) goto L_0x019a
            java.lang.String r8 = "color-transfer"
            int r9 = r6.zzf
            com.google.android.gms.internal.ads.zzfn.zza(r5, r8, r9)
            java.lang.String r8 = "color-standard"
            int r9 = r6.zzd
            com.google.android.gms.internal.ads.zzfn.zza(r5, r8, r9)
            java.lang.String r8 = "color-range"
            int r9 = r6.zze
            com.google.android.gms.internal.ads.zzfn.zza(r5, r8, r9)
            byte[] r6 = r6.zzg
            if (r6 == 0) goto L_0x019a
            java.lang.String r8 = "hdr-static-info"
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.wrap(r6)
            r5.setByteBuffer(r8, r6)
        L_0x019a:
            java.lang.String r6 = r2.zzn
            java.lang.String r8 = "video/dolby-vision"
            boolean r6 = r8.equals(r6)
            if (r6 == 0) goto L_0x01b7
            android.util.Pair r6 = com.google.android.gms.internal.ads.zzuj.zza(r21)
            if (r6 == 0) goto L_0x01b7
            java.lang.Object r6 = r6.first
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            java.lang.String r8 = "profile"
            com.google.android.gms.internal.ads.zzfn.zza(r5, r8, r6)
        L_0x01b7:
            int r6 = r3.zza
            java.lang.String r8 = "max-width"
            r5.setInteger(r8, r6)
            int r6 = r3.zzb
            java.lang.String r8 = "max-height"
            r5.setInteger(r8, r6)
            int r3 = r3.zzc
            java.lang.String r6 = "max-input-size"
            com.google.android.gms.internal.ads.zzfn.zza(r5, r6, r3)
            int r3 = com.google.android.gms.internal.ads.zzgd.zza
            r6 = 23
            if (r3 < r6) goto L_0x01e3
            java.lang.String r3 = "priority"
            r6 = 0
            r5.setInteger(r3, r6)
            r3 = r23
            int r6 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r6 == 0) goto L_0x01e3
            java.lang.String r6 = "operating-rate"
            r5.setFloat(r6, r3)
        L_0x01e3:
            if (r4 == 0) goto L_0x01f1
            java.lang.String r3 = "no-post-process"
            r4 = 1
            r5.setInteger(r3, r4)
            java.lang.String r3 = "auto-frc"
            r4 = 0
            r5.setInteger(r3, r4)
        L_0x01f1:
            android.view.Surface r3 = r0.zzn
            if (r3 != 0) goto L_0x0214
            boolean r3 = r19.zzaV(r20)
            if (r3 == 0) goto L_0x020e
            com.google.android.gms.internal.ads.zzabm r3 = r0.zzp
            if (r3 != 0) goto L_0x0209
            android.content.Context r3 = r0.zze
            boolean r4 = r1.zzf
            com.google.android.gms.internal.ads.zzabm r3 = com.google.android.gms.internal.ads.zzabm.zza(r3, r4)
            r0.zzp = r3
        L_0x0209:
            com.google.android.gms.internal.ads.zzabm r3 = r0.zzp
            r0.zzn = r3
            goto L_0x0214
        L_0x020e:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x0214:
            com.google.android.gms.internal.ads.zzacm r3 = r0.zzF
            if (r3 == 0) goto L_0x0225
            boolean r3 = r3.zzl()
            if (r3 != 0) goto L_0x0225
            java.lang.String r3 = "allow-frame-drop"
            r4 = 0
            r5.setInteger(r3, r4)
            goto L_0x0226
        L_0x0225:
            r4 = 0
        L_0x0226:
            com.google.android.gms.internal.ads.zzacm r3 = r0.zzF
            if (r3 != 0) goto L_0x0232
            android.view.Surface r3 = r0.zzn
            r6 = 0
            com.google.android.gms.internal.ads.zztk r1 = com.google.android.gms.internal.ads.zztk.zzb(r1, r5, r2, r3, r6)
            return r1
        L_0x0232:
            r6 = 0
            com.google.android.gms.internal.ads.zzeq.zzf(r4)
            com.google.android.gms.internal.ads.zzeq.zzb(r6)
            r6.zzb()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzabj.zzaf(com.google.android.gms.internal.ads.zztp, com.google.android.gms.internal.ads.zzan, android.media.MediaCrypto, float):com.google.android.gms.internal.ads.zztk");
    }

    /* access modifiers changed from: protected */
    public final List zzag(zztx zztx, zzan zzan, boolean z) throws zzud {
        return zzuj.zzg(zzaR(this.zze, zztx, zzan, false, false), zzan);
    }

    /* access modifiers changed from: protected */
    public final void zzaj(zzin zzin) throws zzjh {
        if (this.zzm) {
            ByteBuffer byteBuffer = zzin.zzf;
            byteBuffer.getClass();
            if (byteBuffer.remaining() >= 7) {
                byte b2 = byteBuffer.get();
                short s = byteBuffer.getShort();
                short s2 = byteBuffer.getShort();
                byte b3 = byteBuffer.get();
                byte b4 = byteBuffer.get();
                byteBuffer.position(0);
                if (b2 != -75 || s != 60 || s2 != 1 || b3 != 4) {
                    return;
                }
                if (b4 == 0 || b4 == 1) {
                    byte[] bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr);
                    byteBuffer.position(0);
                    zztm zzaw = zzaw();
                    zzaw.getClass();
                    Bundle bundle = new Bundle();
                    bundle.putByteArray("hdr10-plus-info", bArr);
                    zzaw.zzp(bundle);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzak(Exception exc) {
        zzfk.zzd("MediaCodecVideoRenderer", "Video codec error", exc);
        this.zzg.zzs(exc);
    }

    /* access modifiers changed from: protected */
    public final void zzal(String str, zztk zztk, long j, long j2) {
        this.zzg.zza(str, j, j2);
        this.zzl = zzaQ(str);
        zztp zzay = zzay();
        zzay.getClass();
        boolean z = false;
        if (zzgd.zza >= 29 && MimeTypes.VIDEO_VP9.equals(zzay.zzb)) {
            MediaCodecInfo.CodecProfileLevel[] zzh2 = zzay.zzh();
            int length = zzh2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (zzh2[i].profile == 16384) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
        }
        this.zzm = z;
    }

    /* access modifiers changed from: protected */
    public final void zzam(String str) {
        this.zzg.zzb(str);
    }

    /* access modifiers changed from: protected */
    public final void zzan(zzan zzan, MediaFormat mediaFormat) {
        int i;
        int i2;
        zztm zzaw = zzaw();
        if (zzaw != null) {
            zzaw.zzq(this.zzr);
        }
        mediaFormat.getClass();
        boolean z = mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top");
        if (z) {
            i = (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1;
        } else {
            i = mediaFormat.getInteger("width");
        }
        if (z) {
            i2 = (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1;
        } else {
            i2 = mediaFormat.getInteger("height");
        }
        float f = zzan.zzw;
        int i3 = zzgd.zza;
        int i4 = zzan.zzv;
        if (i4 == 90 || i4 == 270) {
            f = 1.0f / f;
            int i5 = i2;
            i2 = i;
            i = i5;
        }
        this.zzz = new zzdv(i, i2, 0, f);
        this.zzi.zzl(zzan.zzu);
        zzacm zzacm = this.zzF;
        if (zzacm != null) {
            zzal zzb2 = zzan.zzb();
            zzb2.zzac(i);
            zzb2.zzI(i2);
            zzb2.zzW(0);
            zzb2.zzT(f);
            zzacm.zzg(1, zzb2.zzad());
        }
    }

    /* access modifiers changed from: protected */
    public final void zzao(zztm zztm, int i, long j, long j2) {
        Trace.beginSection("releaseOutputBuffer");
        zztm.zzm(i, j2);
        Trace.endSection();
        this.zza.zze++;
        this.zzu = 0;
        if (this.zzF == null) {
            zzdv zzdv = this.zzz;
            if (!zzdv.equals(zzdv.zza) && !zzdv.equals(this.zzA)) {
                this.zzA = zzdv;
                this.zzg.zzt(zzdv);
            }
            if (this.zzi.zzp() && this.zzn != null) {
                zzaT();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzap() {
        this.zzi.zzf();
        this.zzf.zzd().zzj(zzau());
    }

    /* access modifiers changed from: protected */
    public final int zzat(zzin zzin) {
        int i = zzgd.zza;
        return 0;
    }

    /* access modifiers changed from: protected */
    public final zzto zzax(Throwable th, zztp zztp) {
        return new zzabf(th, zztp, this.zzn);
    }

    public final void zzs() {
        this.zzi.zzb();
    }

    /* access modifiers changed from: protected */
    public final void zzw() {
        this.zzA = null;
        this.zzi.zzd();
        this.zzq = false;
        try {
            super.zzw();
        } finally {
            this.zzg.zzc(this.zza);
            this.zzg.zzt(zzdv.zza);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzx(boolean z, boolean z2) throws zzjh {
        super.zzx(z, z2);
        zzm();
        this.zzg.zze(this.zza);
        this.zzi.zze(z2);
    }

    /* access modifiers changed from: protected */
    public final void zzy() {
        this.zzi.zzk(zzh());
    }

    /* access modifiers changed from: protected */
    public final void zzz(long j, boolean z) throws zzjh {
        this.zzf.zzd().zze();
        super.zzz(j, z);
        this.zzi.zzi();
        if (z) {
            this.zzi.zzc(false);
        }
        this.zzu = 0;
    }

    /* JADX WARNING: Failed to insert additional move for type inference */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzt(int r6, java.lang.Object r7) throws com.google.android.gms.internal.ads.zzjh {
        /*
            r5 = this;
            r0 = 1
            if (r6 == r0) goto L_0x0092
            r1 = 7
            if (r6 == r1) goto L_0x007f
            r1 = 10
            if (r6 == r1) goto L_0x006f
            r1 = 4
            if (r6 == r1) goto L_0x005a
            r1 = 5
            if (r6 == r1) goto L_0x004b
            r1 = 13
            if (r6 == r1) goto L_0x003e
            r0 = 14
            if (r6 == r0) goto L_0x001a
            goto L_0x012b
        L_0x001a:
            r7.getClass()
            com.google.android.gms.internal.ads.zzfv r7 = (com.google.android.gms.internal.ads.zzfv) r7
            r5.zzo = r7
            com.google.android.gms.internal.ads.zzacm r6 = r5.zzF
            if (r6 == 0) goto L_0x012b
            r7.getClass()
            int r6 = r7.zzb()
            if (r6 == 0) goto L_0x012b
            int r6 = r7.zza()
            if (r6 == 0) goto L_0x012b
            android.view.Surface r6 = r5.zzn
            if (r6 == 0) goto L_0x012b
            com.google.android.gms.internal.ads.zzacn r0 = r5.zzf
            r0.zzq(r6, r7)
            return
        L_0x003e:
            r7.getClass()
            com.google.android.gms.internal.ads.zzacn r6 = r5.zzf
            java.util.List r7 = (java.util.List) r7
            r6.zzr(r7)
            r5.zzB = r0
            return
        L_0x004b:
            com.google.android.gms.internal.ads.zzabq r6 = r5.zzi
            r7.getClass()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r6.zzj(r7)
            return
        L_0x005a:
            r7.getClass()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r6 = r7.intValue()
            r5.zzr = r6
            com.google.android.gms.internal.ads.zztm r7 = r5.zzaw()
            if (r7 == 0) goto L_0x012b
            r7.zzq(r6)
            return
        L_0x006f:
            r7.getClass()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r6 = r7.intValue()
            int r7 = r5.zzD
            if (r7 == r6) goto L_0x012b
            r5.zzD = r6
            return
        L_0x007f:
            r7.getClass()
            com.google.android.gms.internal.ads.zzabn r7 = (com.google.android.gms.internal.ads.zzabn) r7
            r5.zzE = r7
            com.google.android.gms.internal.ads.zzacm r6 = r5.zzF
            if (r6 == 0) goto L_0x012b
            com.google.android.gms.internal.ads.zzaba r6 = (com.google.android.gms.internal.ads.zzaba) r6
            com.google.android.gms.internal.ads.zzabc r6 = r6.zza
            r6.zzj = r7
            return
        L_0x0092:
            boolean r6 = r7 instanceof android.view.Surface
            r1 = 0
            if (r6 == 0) goto L_0x009a
            android.view.Surface r7 = (android.view.Surface) r7
            goto L_0x009b
        L_0x009a:
            r7 = r1
        L_0x009b:
            if (r7 != 0) goto L_0x00b9
            com.google.android.gms.internal.ads.zzabm r6 = r5.zzp
            if (r6 == 0) goto L_0x00a3
            r7 = r6
            goto L_0x00b9
        L_0x00a3:
            com.google.android.gms.internal.ads.zztp r6 = r5.zzay()
            if (r6 == 0) goto L_0x00b9
            boolean r2 = r5.zzaV(r6)
            if (r2 == 0) goto L_0x00b9
            android.content.Context r7 = r5.zze
            boolean r6 = r6.zzf
            com.google.android.gms.internal.ads.zzabm r7 = com.google.android.gms.internal.ads.zzabm.zza(r7, r6)
            r5.zzp = r7
        L_0x00b9:
            android.view.Surface r6 = r5.zzn
            if (r6 == r7) goto L_0x0115
            r5.zzn = r7
            com.google.android.gms.internal.ads.zzabq r6 = r5.zzi
            r6.zzm(r7)
            r6 = 0
            r5.zzq = r6
            int r6 = r5.zzcU()
            com.google.android.gms.internal.ads.zztm r2 = r5.zzaw()
            if (r2 == 0) goto L_0x00ec
            com.google.android.gms.internal.ads.zzacm r3 = r5.zzF
            if (r3 != 0) goto L_0x00ec
            int r3 = com.google.android.gms.internal.ads.zzgd.zza
            r4 = 23
            if (r3 < r4) goto L_0x00e6
            if (r7 == 0) goto L_0x00e5
            boolean r3 = r5.zzl
            if (r3 != 0) goto L_0x00e6
            r2.zzo(r7)
            goto L_0x00ec
        L_0x00e5:
            r7 = r1
        L_0x00e6:
            r5.zzaD()
            r5.zzaz()
        L_0x00ec:
            if (r7 == 0) goto L_0x0109
            com.google.android.gms.internal.ads.zzabm r2 = r5.zzp
            if (r7 == r2) goto L_0x0109
            r5.zzaS()
            r1 = 2
            if (r6 != r1) goto L_0x00fd
            com.google.android.gms.internal.ads.zzabq r6 = r5.zzi
            r6.zzc(r0)
        L_0x00fd:
            com.google.android.gms.internal.ads.zzacm r6 = r5.zzF
            if (r6 == 0) goto L_0x012b
            com.google.android.gms.internal.ads.zzacn r6 = r5.zzf
            com.google.android.gms.internal.ads.zzfv r0 = com.google.android.gms.internal.ads.zzfv.zza
            r6.zzq(r7, r0)
            return
        L_0x0109:
            r5.zzA = r1
            com.google.android.gms.internal.ads.zzacm r6 = r5.zzF
            if (r6 == 0) goto L_0x012b
            com.google.android.gms.internal.ads.zzacn r6 = r5.zzf
            r6.zzk()
            return
        L_0x0115:
            if (r7 == 0) goto L_0x012b
            com.google.android.gms.internal.ads.zzabm r6 = r5.zzp
            if (r7 == r6) goto L_0x012b
            r5.zzaS()
            android.view.Surface r6 = r5.zzn
            if (r6 == 0) goto L_0x012b
            boolean r7 = r5.zzq
            if (r7 == 0) goto L_0x012b
            com.google.android.gms.internal.ads.zzach r7 = r5.zzg
            r7.zzq(r6)
        L_0x012b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzabj.zzt(int, java.lang.Object):void");
    }

    /* access modifiers changed from: protected */
    public final boolean zzar(long j, long j2, zztm zztm, ByteBuffer byteBuffer, int i, int i2, int i3, long j3, boolean z, boolean z2, zzan zzan) throws zzjh {
        zztm zztm2 = zztm;
        int i4 = i;
        boolean z3 = z2;
        zztm.getClass();
        long zzau = j3 - zzau();
        int zza = this.zzi.zza(j3, j, j2, zzav(), z2, this.zzj);
        if (!z || z3) {
            long j4 = zzau;
            if (this.zzn != this.zzp || this.zzF != null) {
                zzacm zzacm = this.zzF;
                if (zzacm != null) {
                    try {
                        zzacm.zzh(j, j2);
                        long zzd2 = this.zzF.zzd(j4, z3);
                        if (zzd2 != C.TIME_UNSET) {
                            int i5 = zzgd.zza;
                            zzao(zztm, i, j4, zzd2);
                            return true;
                        }
                    } catch (zzacl e) {
                        zzacl zzacl = e;
                        throw zzi(zzacl, zzacl.zza, false, 7001);
                    }
                } else if (zza == 0) {
                    zzh();
                    long nanoTime = System.nanoTime();
                    int i6 = zzgd.zza;
                    zzao(zztm, i, j4, nanoTime);
                    zzaO(this.zzj.zzc());
                    return true;
                } else if (zza == 1) {
                    zzabo zzabo = this.zzj;
                    long zzd3 = zzabo.zzd();
                    long zzc2 = zzabo.zzc();
                    int i7 = zzgd.zza;
                    if (zzd3 == this.zzy) {
                        zzaM(zztm2, i4, j4);
                    } else {
                        zzao(zztm, i, j4, zzd3);
                    }
                    zzaO(zzc2);
                    this.zzy = zzd3;
                    return true;
                } else if (zza == 2) {
                    Trace.beginSection("dropVideoBuffer");
                    zztm2.zzn(i4, false);
                    Trace.endSection();
                    zzaN(0, 1);
                    zzaO(this.zzj.zzc());
                    return true;
                } else if (zza == 3) {
                    zzaM(zztm2, i4, j4);
                    zzaO(this.zzj.zzc());
                    return true;
                }
            } else if (this.zzj.zzc() < 30000) {
                zzaM(zztm2, i4, j4);
                zzaO(this.zzj.zzc());
                return true;
            }
            return false;
        }
        zzaM(zztm2, i4, zzau);
        return true;
    }
}
