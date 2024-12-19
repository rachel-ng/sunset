package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzadn implements zzaea {
    private static final int[] zzb = {5, 4, 12, 8, 3, 10, 9, 11, 6, 2, 0, 1, 7, 16, 15, 14, 17, 18, 19, 20};
    private static final zzadm zzc = new zzadm(new zzadj());
    private static final zzadm zzd = new zzadm(new zzadk());
    private zzgbc zze;
    private final zzalt zzf = new zzalo();

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01a8, code lost:
        r3 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01a9, code lost:
        switch(r3) {
            case 0: goto L_0x01dd;
            case 1: goto L_0x01dd;
            case 2: goto L_0x01dd;
            case 3: goto L_0x01da;
            case 4: goto L_0x01d8;
            case 5: goto L_0x01d8;
            case 6: goto L_0x01d8;
            case 7: goto L_0x01d6;
            case 8: goto L_0x01d4;
            case 9: goto L_0x01d1;
            case 10: goto L_0x01ce;
            case 11: goto L_0x01ce;
            case 12: goto L_0x01ce;
            case 13: goto L_0x01ce;
            case 14: goto L_0x01ce;
            case 15: goto L_0x01cc;
            case 16: goto L_0x01c9;
            case 17: goto L_0x01c9;
            case 18: goto L_0x01c9;
            case 19: goto L_0x01c6;
            case 20: goto L_0x01c3;
            case 21: goto L_0x01c0;
            case 22: goto L_0x01be;
            case 23: goto L_0x01bc;
            case 24: goto L_0x01ba;
            case 25: goto L_0x01b8;
            case 26: goto L_0x01b5;
            case 27: goto L_0x01b2;
            case 28: goto L_0x01b0;
            case 29: goto L_0x01ae;
            default: goto L_0x01ac;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01ae, code lost:
        r3 = 20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01b0, code lost:
        r3 = 19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01b2, code lost:
        r3 = 18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x01b5, code lost:
        r3 = 17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x01b8, code lost:
        r3 = 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01ba, code lost:
        r3 = 14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x01bc, code lost:
        r3 = 13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01be, code lost:
        r3 = 12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x01c0, code lost:
        r3 = 11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x01c3, code lost:
        r3 = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x01c6, code lost:
        r3 = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x01c9, code lost:
        r3 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x01cc, code lost:
        r3 = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x01ce, code lost:
        r3 = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x01d1, code lost:
        r3 = 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x01d4, code lost:
        r3 = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x01d6, code lost:
        r3 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x01d8, code lost:
        r3 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x01da, code lost:
        r3 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x01dd, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01e0  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x01ec  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:248:0x0399  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x03b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.google.android.gms.internal.ads.zzadu[] zza(android.net.Uri r25, java.util.Map r26) {
        /*
            r24 = this;
            r1 = r24
            monitor-enter(r24)
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x03bf }
            r2 = 20
            r0.<init>(r2)     // Catch:{ all -> 0x03bf }
            java.lang.String r3 = "Content-Type"
            r4 = r26
            java.lang.Object r3 = r4.get(r3)     // Catch:{ all -> 0x03bf }
            java.util.List r3 = (java.util.List) r3     // Catch:{ all -> 0x03bf }
            r4 = 0
            if (r3 == 0) goto L_0x0025
            boolean r5 = r3.isEmpty()     // Catch:{ all -> 0x03bf }
            if (r5 == 0) goto L_0x001e
            goto L_0x0025
        L_0x001e:
            java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x03bf }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x03bf }
            goto L_0x0026
        L_0x0025:
            r3 = 0
        L_0x0026:
            r7 = 16
            r8 = 7
            r9 = 5
            r10 = 4
            r11 = 3
            r12 = 19
            r13 = 14
            r14 = 13
            r15 = 12
            r16 = 11
            r17 = 9
            r18 = 6
            r19 = 15
            r20 = 1
            r21 = 10
            r22 = 8
            r4 = -1
            if (r3 != 0) goto L_0x0048
        L_0x0045:
            r3 = r4
            goto L_0x01de
        L_0x0048:
            java.lang.String r3 = com.google.android.gms.internal.ads.zzcg.zze(r3)     // Catch:{ all -> 0x03bf }
            int r23 = r3.hashCode()     // Catch:{ all -> 0x03bf }
            switch(r23) {
                case -2123537834: goto L_0x019e;
                case -1662384011: goto L_0x0194;
                case -1662384007: goto L_0x0189;
                case -1662095187: goto L_0x017f;
                case -1606874997: goto L_0x0174;
                case -1487464690: goto L_0x0169;
                case -1487394660: goto L_0x015e;
                case -1487018032: goto L_0x0153;
                case -1248337486: goto L_0x0148;
                case -1079884372: goto L_0x013c;
                case -1004728940: goto L_0x0130;
                case -879272239: goto L_0x0124;
                case -879258763: goto L_0x0118;
                case -387023398: goto L_0x010c;
                case -43467528: goto L_0x0101;
                case 13915911: goto L_0x00f5;
                case 187078296: goto L_0x00ea;
                case 187078297: goto L_0x00df;
                case 187078669: goto L_0x00d4;
                case 187090232: goto L_0x00c8;
                case 187091926: goto L_0x00bd;
                case 187099443: goto L_0x00b1;
                case 1331848029: goto L_0x00a6;
                case 1503095341: goto L_0x009b;
                case 1504578661: goto L_0x008f;
                case 1504619009: goto L_0x0084;
                case 1504824762: goto L_0x0078;
                case 1504831518: goto L_0x006c;
                case 1505118770: goto L_0x0061;
                case 2039520277: goto L_0x0055;
                default: goto L_0x0053;
            }     // Catch:{ all -> 0x03bf }
        L_0x0053:
            goto L_0x01a8
        L_0x0055:
            java.lang.String r5 = "video/x-matroska"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r21
            goto L_0x01a9
        L_0x0061:
            java.lang.String r5 = "audio/webm"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r14
            goto L_0x01a9
        L_0x006c:
            java.lang.String r5 = "audio/mpeg"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r19
            goto L_0x01a9
        L_0x0078:
            java.lang.String r5 = "audio/midi"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r17
            goto L_0x01a9
        L_0x0084:
            java.lang.String r5 = "audio/flac"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r8
            goto L_0x01a9
        L_0x008f:
            java.lang.String r5 = "audio/eac3"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r20
            goto L_0x01a9
        L_0x009b:
            java.lang.String r5 = "audio/3gpp"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r9
            goto L_0x01a9
        L_0x00a6:
            java.lang.String r5 = "video/mp4"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r7
            goto L_0x01a9
        L_0x00b1:
            java.lang.String r5 = "audio/wav"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = 22
            goto L_0x01a9
        L_0x00bd:
            java.lang.String r5 = "audio/ogg"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r12
            goto L_0x01a9
        L_0x00c8:
            java.lang.String r5 = "audio/mp4"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = 17
            goto L_0x01a9
        L_0x00d4:
            java.lang.String r5 = "audio/amr"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r10
            goto L_0x01a9
        L_0x00df:
            java.lang.String r5 = "audio/ac4"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r11
            goto L_0x01a9
        L_0x00ea:
            java.lang.String r5 = "audio/ac3"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = 0
            goto L_0x01a9
        L_0x00f5:
            java.lang.String r5 = "video/x-flv"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r22
            goto L_0x01a9
        L_0x0101:
            java.lang.String r5 = "application/webm"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r13
            goto L_0x01a9
        L_0x010c:
            java.lang.String r5 = "audio/x-matroska"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r16
            goto L_0x01a9
        L_0x0118:
            java.lang.String r5 = "image/png"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = 26
            goto L_0x01a9
        L_0x0124:
            java.lang.String r5 = "image/bmp"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = 28
            goto L_0x01a9
        L_0x0130:
            java.lang.String r5 = "text/vtt"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = 23
            goto L_0x01a9
        L_0x013c:
            java.lang.String r5 = "video/x-msvideo"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = 25
            goto L_0x01a9
        L_0x0148:
            java.lang.String r5 = "application/mp4"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = 18
            goto L_0x01a9
        L_0x0153:
            java.lang.String r5 = "image/webp"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = 27
            goto L_0x01a9
        L_0x015e:
            java.lang.String r5 = "image/jpeg"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = 24
            goto L_0x01a9
        L_0x0169:
            java.lang.String r5 = "image/heif"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = 29
            goto L_0x01a9
        L_0x0174:
            java.lang.String r5 = "audio/amr-wb"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r18
            goto L_0x01a9
        L_0x017f:
            java.lang.String r5 = "video/webm"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r15
            goto L_0x01a9
        L_0x0189:
            java.lang.String r5 = "video/mp2t"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = 21
            goto L_0x01a9
        L_0x0194:
            java.lang.String r5 = "video/mp2p"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = r2
            goto L_0x01a9
        L_0x019e:
            java.lang.String r5 = "audio/eac3-joc"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x03bf }
            if (r3 == 0) goto L_0x01a8
            r3 = 2
            goto L_0x01a9
        L_0x01a8:
            r3 = r4
        L_0x01a9:
            switch(r3) {
                case 0: goto L_0x01dd;
                case 1: goto L_0x01dd;
                case 2: goto L_0x01dd;
                case 3: goto L_0x01da;
                case 4: goto L_0x01d8;
                case 5: goto L_0x01d8;
                case 6: goto L_0x01d8;
                case 7: goto L_0x01d6;
                case 8: goto L_0x01d4;
                case 9: goto L_0x01d1;
                case 10: goto L_0x01ce;
                case 11: goto L_0x01ce;
                case 12: goto L_0x01ce;
                case 13: goto L_0x01ce;
                case 14: goto L_0x01ce;
                case 15: goto L_0x01cc;
                case 16: goto L_0x01c9;
                case 17: goto L_0x01c9;
                case 18: goto L_0x01c9;
                case 19: goto L_0x01c6;
                case 20: goto L_0x01c3;
                case 21: goto L_0x01c0;
                case 22: goto L_0x01be;
                case 23: goto L_0x01bc;
                case 24: goto L_0x01ba;
                case 25: goto L_0x01b8;
                case 26: goto L_0x01b5;
                case 27: goto L_0x01b2;
                case 28: goto L_0x01b0;
                case 29: goto L_0x01ae;
                default: goto L_0x01ac;
            }     // Catch:{ all -> 0x03bf }
        L_0x01ac:
            goto L_0x0045
        L_0x01ae:
            r3 = r2
            goto L_0x01de
        L_0x01b0:
            r3 = r12
            goto L_0x01de
        L_0x01b2:
            r3 = 18
            goto L_0x01de
        L_0x01b5:
            r3 = 17
            goto L_0x01de
        L_0x01b8:
            r3 = r7
            goto L_0x01de
        L_0x01ba:
            r3 = r13
            goto L_0x01de
        L_0x01bc:
            r3 = r14
            goto L_0x01de
        L_0x01be:
            r3 = r15
            goto L_0x01de
        L_0x01c0:
            r3 = r16
            goto L_0x01de
        L_0x01c3:
            r3 = r21
            goto L_0x01de
        L_0x01c6:
            r3 = r17
            goto L_0x01de
        L_0x01c9:
            r3 = r22
            goto L_0x01de
        L_0x01cc:
            r3 = r8
            goto L_0x01de
        L_0x01ce:
            r3 = r18
            goto L_0x01de
        L_0x01d1:
            r3 = r19
            goto L_0x01de
        L_0x01d4:
            r3 = r9
            goto L_0x01de
        L_0x01d6:
            r3 = r10
            goto L_0x01de
        L_0x01d8:
            r3 = r11
            goto L_0x01de
        L_0x01da:
            r3 = r20
            goto L_0x01de
        L_0x01dd:
            r3 = 0
        L_0x01de:
            if (r3 == r4) goto L_0x01e3
            r1.zzb(r3, r0)     // Catch:{ all -> 0x03bf }
        L_0x01e3:
            java.lang.String r5 = r25.getLastPathSegment()     // Catch:{ all -> 0x03bf }
            if (r5 != 0) goto L_0x01ec
        L_0x01e9:
            r5 = r4
            goto L_0x038d
        L_0x01ec:
            java.lang.String r6 = ".ac3"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x038c
            java.lang.String r6 = ".ec3"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x01fe
            goto L_0x038c
        L_0x01fe:
            java.lang.String r6 = ".ac4"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x020a
            r5 = r20
            goto L_0x038d
        L_0x020a:
            java.lang.String r6 = ".adts"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x038a
            java.lang.String r6 = ".aac"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x021c
            goto L_0x038a
        L_0x021c:
            java.lang.String r6 = ".amr"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x0227
            r5 = r11
            goto L_0x038d
        L_0x0227:
            java.lang.String r6 = ".flac"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x0232
            r5 = r10
            goto L_0x038d
        L_0x0232:
            java.lang.String r6 = ".flv"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x023d
            r5 = r9
            goto L_0x038d
        L_0x023d:
            java.lang.String r6 = ".mid"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x0387
            java.lang.String r6 = ".midi"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x0387
            java.lang.String r6 = ".smf"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x0257
            goto L_0x0387
        L_0x0257:
            int r6 = r5.length()     // Catch:{ all -> 0x03bf }
            java.lang.String r9 = ".mk"
            int r6 = r6 + -4
            boolean r6 = r5.startsWith(r9, r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x0384
            java.lang.String r6 = ".webm"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x026f
            goto L_0x0384
        L_0x026f:
            java.lang.String r6 = ".mp3"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x027a
            r5 = r8
            goto L_0x038d
        L_0x027a:
            java.lang.String r6 = ".mp4"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x0381
            int r6 = r5.length()     // Catch:{ all -> 0x03bf }
            int r6 = r6 + -4
            java.lang.String r8 = ".m4"
            boolean r6 = r5.startsWith(r8, r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x0381
            int r6 = r5.length()     // Catch:{ all -> 0x03bf }
            java.lang.String r8 = ".mp4"
            int r6 = r6 + -5
            boolean r6 = r5.startsWith(r8, r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x0381
            int r6 = r5.length()     // Catch:{ all -> 0x03bf }
            int r6 = r6 + -5
            java.lang.String r8 = ".cmf"
            boolean r6 = r5.startsWith(r8, r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x02ae
            goto L_0x0381
        L_0x02ae:
            int r6 = r5.length()     // Catch:{ all -> 0x03bf }
            int r6 = r6 + -4
            java.lang.String r8 = ".og"
            boolean r6 = r5.startsWith(r8, r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x037e
            java.lang.String r6 = ".opus"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x02c6
            goto L_0x037e
        L_0x02c6:
            java.lang.String r6 = ".ps"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x037b
            java.lang.String r6 = ".mpeg"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x037b
            java.lang.String r6 = ".mpg"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x037b
            java.lang.String r6 = ".m2p"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x02e8
            goto L_0x037b
        L_0x02e8:
            java.lang.String r6 = ".ts"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x0378
            int r6 = r5.length()     // Catch:{ all -> 0x03bf }
            int r6 = r6 + -4
            java.lang.String r8 = ".ts"
            boolean r6 = r5.startsWith(r8, r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x0300
            goto L_0x0378
        L_0x0300:
            java.lang.String r6 = ".wav"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x0376
            java.lang.String r6 = ".wave"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x0312
            goto L_0x0376
        L_0x0312:
            java.lang.String r6 = ".vtt"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x0374
            java.lang.String r6 = ".webvtt"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x0323
            goto L_0x0374
        L_0x0323:
            java.lang.String r6 = ".jpg"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x0372
            java.lang.String r6 = ".jpeg"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x0334
            goto L_0x0372
        L_0x0334:
            java.lang.String r6 = ".avi"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x033f
            r5 = r7
            goto L_0x038d
        L_0x033f:
            java.lang.String r6 = ".png"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x034a
            r5 = 17
            goto L_0x038d
        L_0x034a:
            java.lang.String r6 = ".webp"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x0355
            r5 = 18
            goto L_0x038d
        L_0x0355:
            java.lang.String r6 = ".bmp"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 != 0) goto L_0x0370
            java.lang.String r6 = ".dib"
            boolean r6 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r6 == 0) goto L_0x0366
            goto L_0x0370
        L_0x0366:
            java.lang.String r6 = ".heic"
            boolean r5 = r5.endsWith(r6)     // Catch:{ all -> 0x03bf }
            if (r5 == 0) goto L_0x01e9
            r5 = r2
            goto L_0x038d
        L_0x0370:
            r5 = r12
            goto L_0x038d
        L_0x0372:
            r5 = r13
            goto L_0x038d
        L_0x0374:
            r5 = r14
            goto L_0x038d
        L_0x0376:
            r5 = r15
            goto L_0x038d
        L_0x0378:
            r5 = r16
            goto L_0x038d
        L_0x037b:
            r5 = r21
            goto L_0x038d
        L_0x037e:
            r5 = r17
            goto L_0x038d
        L_0x0381:
            r5 = r22
            goto L_0x038d
        L_0x0384:
            r5 = r18
            goto L_0x038d
        L_0x0387:
            r5 = r19
            goto L_0x038d
        L_0x038a:
            r5 = 2
            goto L_0x038d
        L_0x038c:
            r5 = 0
        L_0x038d:
            if (r5 == r4) goto L_0x0394
            if (r5 == r3) goto L_0x0394
            r1.zzb(r5, r0)     // Catch:{ all -> 0x03bf }
        L_0x0394:
            int[] r4 = zzb     // Catch:{ all -> 0x03bf }
            r6 = 0
        L_0x0397:
            if (r6 >= r2) goto L_0x03a5
            r7 = r4[r6]     // Catch:{ all -> 0x03bf }
            if (r7 == r3) goto L_0x03a2
            if (r7 == r5) goto L_0x03a2
            r1.zzb(r7, r0)     // Catch:{ all -> 0x03bf }
        L_0x03a2:
            int r6 = r6 + 1
            goto L_0x0397
        L_0x03a5:
            int r2 = r0.size()     // Catch:{ all -> 0x03bf }
            com.google.android.gms.internal.ads.zzadu[] r2 = new com.google.android.gms.internal.ads.zzadu[r2]     // Catch:{ all -> 0x03bf }
            r4 = 0
        L_0x03ac:
            int r3 = r0.size()     // Catch:{ all -> 0x03bf }
            if (r4 >= r3) goto L_0x03bd
            java.lang.Object r3 = r0.get(r4)     // Catch:{ all -> 0x03bf }
            com.google.android.gms.internal.ads.zzadu r3 = (com.google.android.gms.internal.ads.zzadu) r3     // Catch:{ all -> 0x03bf }
            r2[r4] = r3     // Catch:{ all -> 0x03bf }
            int r4 = r4 + 1
            goto L_0x03ac
        L_0x03bd:
            monitor-exit(r24)
            return r2
        L_0x03bf:
            r0 = move-exception
            monitor-exit(r24)     // Catch:{ all -> 0x03bf }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzadn.zza(android.net.Uri, java.util.Map):com.google.android.gms.internal.ads.zzadu[]");
    }

    private final void zzb(int i, List list) {
        switch (i) {
            case 0:
                list.add(new zzanr());
                return;
            case 1:
                list.add(new zzanu());
                return;
            case 2:
                list.add(new zzanx(0));
                return;
            case 3:
                list.add(new zzafi(0));
                return;
            case 4:
                zzadu zza = zzc.zza(0);
                if (zza != null) {
                    list.add(zza);
                    return;
                } else {
                    list.add(new zzaga(0));
                    return;
                }
            case 5:
                list.add(new zzagd());
                return;
            case 6:
                list.add(new zzaiy(this.zzf, 2));
                return;
            case 7:
                list.add(new zzajg(0));
                return;
            case 8:
                list.add(new zzakd(this.zzf, 32));
                list.add(new zzakj(this.zzf, 16));
                return;
            case 9:
                list.add(new zzala());
                return;
            case 10:
                list.add(new zzapa());
                return;
            case 11:
                if (this.zze == null) {
                    this.zze = zzgbc.zzm();
                }
                list.add(new zzapk(1, 1, this.zzf, new zzgb(0), new zzanz(0, this.zze), TsExtractor.DEFAULT_TIMESTAMP_SEARCH_BYTES));
                return;
            case 12:
                list.add(new zzapw());
                return;
            case 14:
                list.add(new zzagj(0));
                return;
            case 15:
                zzadu zza2 = zzd.zza(new Object[0]);
                if (zza2 != null) {
                    list.add(zza2);
                    return;
                }
                return;
            case 16:
                list.add(new zzafn(1, this.zzf));
                return;
            case 17:
                list.add(new zzall());
                return;
            case 18:
                list.add(new zzaqb());
                return;
            case 19:
                list.add(new zzafu());
                return;
            case 20:
                list.add(new zzagi());
                return;
            default:
                return;
        }
    }
}
