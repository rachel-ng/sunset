package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.SparseArray;
import androidx.core.view.ViewCompat;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzamh implements zzalv {
    private static final byte[] zza = {0, 7, 8, 15};
    private static final byte[] zzb = {0, 119, -120, -1};
    private static final byte[] zzc = {0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1};
    private final Paint zzd;
    private final Paint zze;
    private final Canvas zzf = new Canvas();
    private final zzama zzg = new zzama(AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, 575, 0, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, 0, 575);
    private final zzalz zzh = new zzalz(0, zzg(), zzh(), zzi());
    private final zzamg zzi;
    private Bitmap zzj;

    public zzamh(List list) {
        zzfu zzfu = new zzfu((byte[]) list.get(0));
        int zzq = zzfu.zzq();
        int zzq2 = zzfu.zzq();
        Paint paint = new Paint();
        this.zzd = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        paint.setPathEffect((PathEffect) null);
        Paint paint2 = new Paint();
        this.zze = paint2;
        paint2.setStyle(Paint.Style.FILL);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        paint2.setPathEffect((PathEffect) null);
        this.zzi = new zzamg(zzq, zzq2);
    }

    private static int zzb(int i, int i2, int i3, int i4) {
        return (i << 24) | (i2 << 16) | (i3 << 8) | i4;
    }

    private static zzalz zzc(zzft zzft, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        zzft zzft2 = zzft;
        int i6 = 8;
        int zzd2 = zzft2.zzd(8);
        zzft2.zzm(8);
        int[] zzg2 = zzg();
        int[] zzh2 = zzh();
        int[] zzi2 = zzi();
        int i7 = i - 2;
        while (i7 > 0) {
            int zzd3 = zzft2.zzd(i6);
            int zzd4 = zzft2.zzd(i6);
            int[] iArr = (zzd4 & 128) != 0 ? zzg2 : (zzd4 & 64) != 0 ? zzh2 : zzi2;
            if ((zzd4 & 1) != 0) {
                i5 = zzft2.zzd(i6);
                i4 = zzft2.zzd(i6);
                i3 = zzft2.zzd(i6);
                i2 = zzft2.zzd(i6);
                i7 -= 6;
            } else {
                int zzd5 = zzft2.zzd(2) << 6;
                i7 -= 4;
                i3 = zzft2.zzd(4) << 4;
                i2 = zzd5;
                i5 = zzft2.zzd(6) << 2;
                i4 = zzft2.zzd(4) << 4;
            }
            if (i5 == 0) {
                i2 = 255;
            }
            if (i5 == 0) {
                i3 = 0;
            }
            if (i5 == 0) {
                i4 = 0;
            }
            double d = (double) i5;
            double d2 = (double) (i4 - 128);
            double d3 = (double) (i3 - 128);
            iArr[zzd3] = zzb((byte) (255 - (i2 & 255)), Math.max(0, Math.min((int) (d + (1.402d * d2)), 255)), Math.max(0, Math.min((int) ((d - (0.34414d * d3)) - (d2 * 0.71414d)), 255)), Math.max(0, Math.min((int) (d + (d3 * 1.772d)), 255)));
            zzd2 = zzd2;
            i6 = 8;
        }
        return new zzalz(zzd2, zzg2, zzh2, zzi2);
    }

    private static zzamb zzd(zzft zzft) {
        int zzd2 = zzft.zzd(16);
        zzft.zzm(4);
        int zzd3 = zzft.zzd(2);
        boolean zzo = zzft.zzo();
        zzft.zzm(1);
        byte[] bArr = zzgd.zzf;
        byte[] bArr2 = zzgd.zzf;
        if (zzd3 == 1) {
            zzft.zzm(zzft.zzd(8) * 16);
        } else if (zzd3 == 0) {
            int zzd4 = zzft.zzd(16);
            int zzd5 = zzft.zzd(16);
            if (zzd4 > 0) {
                bArr = new byte[zzd4];
                zzft.zzh(bArr, 0, zzd4);
            }
            if (zzd5 > 0) {
                bArr2 = new byte[zzd5];
                zzft.zzh(bArr2, 0, zzd5);
            } else {
                bArr2 = bArr;
            }
        }
        return new zzamb(zzd2, zzo, bArr, bArr2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v44, resolved type: byte} */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r2v12, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r3v16, types: [byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01ef A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0228 A[LOOP:3: B:85:0x0172->B:116:0x0228, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0222 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0181  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zze(byte[] r24, int[] r25, int r26, int r27, int r28, android.graphics.Paint r29, android.graphics.Canvas r30) {
        /*
            r0 = r24
            r1 = r26
            r8 = r29
            com.google.android.gms.internal.ads.zzft r9 = new com.google.android.gms.internal.ads.zzft
            int r2 = r0.length
            r9.<init>(r0, r2)
            r2 = r27
            r10 = r28
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x0013:
            int r3 = r9.zza()
            if (r3 == 0) goto L_0x0233
            r14 = 8
            int r3 = r9.zzd(r14)
            r4 = 240(0xf0, float:3.36E-43)
            if (r3 == r4) goto L_0x022d
            r15 = 3
            r7 = 4
            r6 = 1
            r5 = 2
            r16 = 0
            switch(r3) {
                case 16: goto L_0x0155;
                case 17: goto L_0x009e;
                case 18: goto L_0x0041;
                default: goto L_0x002c;
            }
        L_0x002c:
            switch(r3) {
                case 32: goto L_0x003c;
                case 33: goto L_0x0037;
                case 34: goto L_0x0030;
                default: goto L_0x002f;
            }
        L_0x002f:
            goto L_0x0013
        L_0x0030:
            r3 = 16
            byte[] r12 = zzf(r3, r14, r9)
            goto L_0x0013
        L_0x0037:
            byte[] r11 = zzf(r7, r14, r9)
            goto L_0x0013
        L_0x003c:
            byte[] r13 = zzf(r7, r7, r9)
            goto L_0x0013
        L_0x0041:
            r15 = r2
            r2 = r16
        L_0x0044:
            int r3 = r9.zzd(r14)
            if (r3 == 0) goto L_0x004f
            r17 = r2
            r18 = r6
            goto L_0x0077
        L_0x004f:
            boolean r3 = r9.zzo()
            r4 = 7
            if (r3 != 0) goto L_0x006a
            int r3 = r9.zzd(r4)
            if (r3 == 0) goto L_0x0063
            r17 = r2
            r18 = r3
            r3 = r16
            goto L_0x0077
        L_0x0063:
            r17 = r6
            r3 = r16
            r18 = r3
            goto L_0x0077
        L_0x006a:
            int r3 = r9.zzd(r4)
            int r4 = r9.zzd(r14)
            r17 = r2
            r18 = r3
            r3 = r4
        L_0x0077:
            if (r18 == 0) goto L_0x0092
            if (r8 == 0) goto L_0x0092
            int r2 = r10 + 1
            float r4 = (float) r10
            r3 = r25[r3]
            r8.setColor(r3)
            float r3 = (float) r15
            int r5 = r15 + r18
            float r5 = (float) r5
            float r7 = (float) r2
            r2 = r30
            r0 = r6
            r6 = r7
            r7 = r29
            r2.drawRect(r3, r4, r5, r6, r7)
            goto L_0x0093
        L_0x0092:
            r0 = r6
        L_0x0093:
            int r15 = r15 + r18
            if (r17 != 0) goto L_0x009b
            r6 = r0
            r2 = r17
            goto L_0x0044
        L_0x009b:
            r2 = r15
            goto L_0x0013
        L_0x009e:
            r0 = r6
            if (r1 != r15) goto L_0x00ab
            if (r12 != 0) goto L_0x00a8
            byte[] r3 = zzc
            r17 = r3
            goto L_0x00ad
        L_0x00a8:
            r17 = r12
            goto L_0x00ad
        L_0x00ab:
            r17 = 0
        L_0x00ad:
            r6 = r2
            r2 = r16
        L_0x00b0:
            int r3 = r9.zzd(r7)
            if (r3 == 0) goto L_0x00bc
            r19 = r0
            r18 = r2
            goto L_0x011c
        L_0x00bc:
            boolean r3 = r9.zzo()
            if (r3 != 0) goto L_0x00d8
            int r3 = r9.zzd(r15)
            if (r3 == 0) goto L_0x00d1
            int r3 = r3 + 2
            r18 = r2
            r19 = r3
        L_0x00ce:
            r3 = r16
            goto L_0x011c
        L_0x00d1:
            r18 = r0
        L_0x00d3:
            r3 = r16
            r19 = r3
            goto L_0x011c
        L_0x00d8:
            boolean r3 = r9.zzo()
            if (r3 != 0) goto L_0x00ed
            int r3 = r9.zzd(r5)
            int r3 = r3 + r7
            int r4 = r9.zzd(r7)
        L_0x00e7:
            r18 = r2
            r19 = r3
            r3 = r4
            goto L_0x011c
        L_0x00ed:
            int r3 = r9.zzd(r5)
            if (r3 == 0) goto L_0x0117
            if (r3 == r0) goto L_0x0112
            if (r3 == r5) goto L_0x0107
            if (r3 == r15) goto L_0x00fc
            r18 = r2
            goto L_0x00d3
        L_0x00fc:
            int r3 = r9.zzd(r14)
            int r3 = r3 + 25
            int r4 = r9.zzd(r7)
            goto L_0x00e7
        L_0x0107:
            int r3 = r9.zzd(r7)
            int r3 = r3 + 9
            int r4 = r9.zzd(r7)
            goto L_0x00e7
        L_0x0112:
            r18 = r2
            r19 = r5
            goto L_0x00ce
        L_0x0117:
            r19 = r0
            r18 = r2
            goto L_0x00ce
        L_0x011c:
            if (r19 == 0) goto L_0x0140
            if (r8 == 0) goto L_0x0140
            int r2 = r10 + 1
            float r4 = (float) r10
            if (r17 == 0) goto L_0x0127
            byte r3 = r17[r3]
        L_0x0127:
            float r2 = (float) r2
            r3 = r25[r3]
            r8.setColor(r3)
            float r3 = (float) r6
            int r5 = r6 + r19
            float r5 = (float) r5
            r20 = r2
            r2 = r30
            r14 = 2
            r22 = r6
            r6 = r20
            r7 = r29
            r2.drawRect(r3, r4, r5, r6, r7)
            goto L_0x0143
        L_0x0140:
            r14 = r5
            r22 = r6
        L_0x0143:
            int r6 = r22 + r19
            if (r18 == 0) goto L_0x014d
            r9.zze()
            r2 = r6
            goto L_0x0013
        L_0x014d:
            r5 = r14
            r2 = r18
            r7 = 4
            r14 = 8
            goto L_0x00b0
        L_0x0155:
            r14 = r5
            r0 = r6
            if (r1 != r15) goto L_0x0163
            if (r11 != 0) goto L_0x0160
            byte[] r3 = zzb
        L_0x015d:
            r17 = r3
            goto L_0x016f
        L_0x0160:
            r17 = r11
            goto L_0x016f
        L_0x0163:
            if (r1 != r14) goto L_0x016d
            if (r13 != 0) goto L_0x016a
            byte[] r3 = zza
            goto L_0x015d
        L_0x016a:
            r17 = r13
            goto L_0x016f
        L_0x016d:
            r17 = 0
        L_0x016f:
            r7 = r2
            r6 = r16
        L_0x0172:
            int r2 = r9.zzd(r14)
            if (r2 == 0) goto L_0x0181
            r18 = r0
        L_0x017a:
            r19 = r6
        L_0x017c:
            r4 = 4
            r5 = 8
            goto L_0x01ed
        L_0x0181:
            boolean r2 = r9.zzo()
            if (r2 == 0) goto L_0x0194
            int r2 = r9.zzd(r15)
            int r2 = r2 + r15
            int r3 = r9.zzd(r14)
            r18 = r2
            r2 = r3
            goto L_0x017a
        L_0x0194:
            boolean r2 = r9.zzo()
            if (r2 == 0) goto L_0x01a1
            r18 = r0
            r19 = r6
            r2 = r16
            goto L_0x017c
        L_0x01a1:
            int r2 = r9.zzd(r14)
            if (r2 == 0) goto L_0x01e4
            if (r2 == r0) goto L_0x01da
            if (r2 == r14) goto L_0x01c7
            if (r2 == r15) goto L_0x01b4
            r19 = r6
            r2 = r16
            r18 = r2
            goto L_0x017c
        L_0x01b4:
            r5 = 8
            int r2 = r9.zzd(r5)
            int r2 = r2 + 29
            int r3 = r9.zzd(r14)
            r18 = r2
            r2 = r3
            r19 = r6
            r4 = 4
            goto L_0x01ed
        L_0x01c7:
            r4 = 4
            r5 = 8
            int r2 = r9.zzd(r4)
            int r2 = r2 + 12
            int r3 = r9.zzd(r14)
            r18 = r2
            r2 = r3
            r19 = r6
            goto L_0x01ed
        L_0x01da:
            r4 = 4
            r5 = 8
            r19 = r6
            r18 = r14
            r2 = r16
            goto L_0x01ed
        L_0x01e4:
            r4 = 4
            r5 = 8
            r19 = r0
            r2 = r16
            r18 = r2
        L_0x01ed:
            if (r18 == 0) goto L_0x0219
            if (r8 == 0) goto L_0x0219
            int r3 = r10 + 1
            float r6 = (float) r10
            if (r17 == 0) goto L_0x01f8
            byte r2 = r17[r2]
        L_0x01f8:
            float r3 = (float) r3
            r2 = r25[r2]
            r8.setColor(r2)
            float r2 = (float) r7
            int r0 = r7 + r18
            float r0 = (float) r0
            r21 = r2
            r2 = r30
            r22 = r3
            r3 = r21
            r21 = r4
            r4 = r6
            r23 = r5
            r5 = r0
            r6 = r22
            r0 = r7
            r7 = r29
            r2.drawRect(r3, r4, r5, r6, r7)
            goto L_0x021e
        L_0x0219:
            r21 = r4
            r23 = r5
            r0 = r7
        L_0x021e:
            int r7 = r0 + r18
            if (r19 == 0) goto L_0x0228
            r9.zze()
            r2 = r7
            goto L_0x0013
        L_0x0228:
            r6 = r19
            r0 = 1
            goto L_0x0172
        L_0x022d:
            int r10 = r10 + 2
            r2 = r27
            goto L_0x0013
        L_0x0233:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzamh.zze(byte[], int[], int, int, int, android.graphics.Paint, android.graphics.Canvas):void");
    }

    private static byte[] zzf(int i, int i2, zzft zzft) {
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) zzft.zzd(i2);
        }
        return bArr;
    }

    private static int[] zzg() {
        return new int[]{0, -1, ViewCompat.MEASURED_STATE_MASK, -8421505};
    }

    private static int[] zzh() {
        int[] iArr = new int[16];
        iArr[0] = 0;
        for (int i = 1; i < 16; i++) {
            int i2 = i & 4;
            int i3 = i & 2;
            int i4 = i & 1;
            if (i < 8) {
                iArr[i] = zzb(255, 1 != i4 ? 0 : 255, i3 != 0 ? 255 : 0, i2 != 0 ? 255 : 0);
            } else {
                int i5 = 127;
                int i6 = 1 != i4 ? 0 : 127;
                int i7 = i3 != 0 ? 127 : 0;
                if (i2 == 0) {
                    i5 = 0;
                }
                iArr[i] = zzb(255, i6, i7, i5);
            }
        }
        return iArr;
    }

    private static int[] zzi() {
        int[] iArr = new int[256];
        iArr[0] = 0;
        for (int i = 0; i < 256; i++) {
            int i2 = 255;
            if (i < 8) {
                int i3 = i & 2;
                int i4 = i & 4;
                int i5 = 1 != (i & 1) ? 0 : 255;
                int i6 = i3 != 0 ? 255 : 0;
                if (i4 == 0) {
                    i2 = 0;
                }
                iArr[i] = zzb(63, i5, i6, i2);
            } else {
                int i7 = i & 136;
                int i8 = 170;
                int i9 = 85;
                if (i7 == 0) {
                    int i10 = i & 16;
                    int i11 = i & 32;
                    int i12 = i & 2;
                    int i13 = i & 64;
                    int i14 = i & 4;
                    int i15 = 1 != (i & 1) ? 0 : 85;
                    int i16 = i10 != 0 ? 170 : 0;
                    int i17 = i12 != 0 ? 85 : 0;
                    int i18 = i11 != 0 ? 170 : 0;
                    if (i14 == 0) {
                        i9 = 0;
                    }
                    if (i13 == 0) {
                        i8 = 0;
                    }
                    iArr[i] = zzb(255, i15 + i16, i17 + i18, i9 + i8);
                } else if (i7 != 8) {
                    int i19 = 43;
                    if (i7 == 128) {
                        int i20 = i & 16;
                        int i21 = i & 32;
                        int i22 = i & 2;
                        int i23 = i & 64;
                        int i24 = i & 4;
                        int i25 = (1 != (i & 1) ? 0 : 43) + 127;
                        int i26 = i20 != 0 ? 85 : 0;
                        int i27 = (i22 != 0 ? 43 : 0) + 127;
                        int i28 = i21 != 0 ? 85 : 0;
                        if (i24 == 0) {
                            i19 = 0;
                        }
                        int i29 = i19 + 127;
                        if (i23 == 0) {
                            i9 = 0;
                        }
                        iArr[i] = zzb(255, i25 + i26, i27 + i28, i29 + i9);
                    } else if (i7 == 136) {
                        int i30 = i & 16;
                        int i31 = i & 32;
                        int i32 = i & 2;
                        int i33 = i & 64;
                        int i34 = i & 4;
                        int i35 = 1 != (i & 1) ? 0 : 43;
                        int i36 = i30 != 0 ? 85 : 0;
                        int i37 = i32 != 0 ? 43 : 0;
                        int i38 = i31 != 0 ? 85 : 0;
                        if (i34 == 0) {
                            i19 = 0;
                        }
                        if (i33 == 0) {
                            i9 = 0;
                        }
                        iArr[i] = zzb(255, i35 + i36, i37 + i38, i19 + i9);
                    }
                } else {
                    int i39 = i & 16;
                    int i40 = i & 32;
                    int i41 = i & 2;
                    int i42 = i & 64;
                    int i43 = i & 4;
                    int i44 = 1 != (i & 1) ? 0 : 85;
                    int i45 = i39 != 0 ? 170 : 0;
                    int i46 = i41 != 0 ? 85 : 0;
                    int i47 = i40 != 0 ? 170 : 0;
                    if (i43 == 0) {
                        i9 = 0;
                    }
                    if (i42 == 0) {
                        i8 = 0;
                    }
                    iArr[i] = zzb(127, i44 + i45, i46 + i47, i9 + i8);
                }
            }
        }
        return iArr;
    }

    public final void zza(byte[] bArr, int i, int i2, zzalu zzalu, zzev zzev) {
        zzaln zzaln;
        char c2;
        char c3;
        int i3;
        int i4;
        SparseArray sparseArray;
        SparseArray sparseArray2;
        Paint paint;
        int[] iArr;
        zzame zzame;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12 = i;
        zzft zzft = new zzft(bArr, i12 + i2);
        zzft.zzk(i12);
        while (zzft.zza() >= 48 && zzft.zzd(8) == 15) {
            zzamg zzamg = this.zzi;
            int zzd2 = zzft.zzd(8);
            int zzd3 = zzft.zzd(16);
            int zzd4 = zzft.zzd(16);
            int zzb2 = zzft.zzb() + zzd4;
            if (zzd4 * 8 > zzft.zza()) {
                zzfk.zzf("DvbParser", "Data field length exceeds limit");
                zzft.zzm(zzft.zza());
            } else {
                switch (zzd2) {
                    case 16:
                        if (zzd3 == zzamg.zza) {
                            zzamc zzamc = zzamg.zzi;
                            int zzd5 = zzft.zzd(8);
                            int zzd6 = zzft.zzd(4);
                            int zzd7 = zzft.zzd(2);
                            zzft.zzm(2);
                            SparseArray sparseArray3 = new SparseArray();
                            for (int i13 = zzd4 - 2; i13 > 0; i13 -= 6) {
                                int zzd8 = zzft.zzd(8);
                                zzft.zzm(8);
                                sparseArray3.put(zzd8, new zzamd(zzft.zzd(16), zzft.zzd(16)));
                            }
                            zzamc zzamc2 = new zzamc(zzd5, zzd6, zzd7, sparseArray3);
                            if (zzamc2.zzb == 0) {
                                if (!(zzamc == null || zzamc.zza == zzamc2.zza)) {
                                    zzamg.zzi = zzamc2;
                                    break;
                                }
                            } else {
                                zzamg.zzi = zzamc2;
                                zzamg.zzc.clear();
                                zzamg.zzd.clear();
                                zzamg.zze.clear();
                                break;
                            }
                        }
                        break;
                    case 17:
                        zzamc zzamc3 = zzamg.zzi;
                        if (zzd3 == zzamg.zza && zzamc3 != null) {
                            int zzd9 = zzft.zzd(8);
                            zzft.zzm(4);
                            boolean zzo = zzft.zzo();
                            zzft.zzm(3);
                            int zzd10 = zzft.zzd(16);
                            int zzd11 = zzft.zzd(16);
                            int zzd12 = zzft.zzd(3);
                            int zzd13 = zzft.zzd(3);
                            zzft.zzm(2);
                            int zzd14 = zzft.zzd(8);
                            int zzd15 = zzft.zzd(8);
                            int zzd16 = zzft.zzd(4);
                            int zzd17 = zzft.zzd(2);
                            zzft.zzm(2);
                            int i14 = zzd4 - 10;
                            SparseArray sparseArray4 = new SparseArray();
                            while (i14 > 0) {
                                int zzd18 = zzft.zzd(16);
                                int zzd19 = zzft.zzd(2);
                                int zzd20 = zzft.zzd(2);
                                int zzd21 = zzft.zzd(12);
                                zzft.zzm(4);
                                int zzd22 = zzft.zzd(12);
                                int i15 = i14 - 6;
                                if (zzd19 != 1) {
                                    if (zzd19 == 2) {
                                        zzd19 = 2;
                                    } else {
                                        i7 = zzd19;
                                        i14 = i15;
                                        i6 = 0;
                                        i5 = 0;
                                        sparseArray4.put(zzd18, new zzamf(i7, zzd20, zzd21, zzd22, i6, i5));
                                    }
                                }
                                i14 -= 8;
                                i7 = zzd19;
                                i6 = zzft.zzd(8);
                                i5 = zzft.zzd(8);
                                sparseArray4.put(zzd18, new zzamf(i7, zzd20, zzd21, zzd22, i6, i5));
                            }
                            zzame zzame2 = new zzame(zzd9, zzo, zzd10, zzd11, zzd12, zzd13, zzd14, zzd15, zzd16, zzd17, sparseArray4);
                            if (zzamc3.zzb == 0 && (zzame = (zzame) zzamg.zzc.get(zzame2.zza)) != null) {
                                int i16 = 0;
                                while (true) {
                                    SparseArray sparseArray5 = zzame.zzj;
                                    if (i16 < sparseArray5.size()) {
                                        zzame2.zzj.put(sparseArray5.keyAt(i16), (zzamf) sparseArray5.valueAt(i16));
                                        i16++;
                                    }
                                }
                            }
                            zzamg.zzc.put(zzame2.zza, zzame2);
                            break;
                        }
                    case 18:
                        if (zzd3 != zzamg.zza) {
                            if (zzd3 == zzamg.zzb) {
                                zzalz zzc2 = zzc(zzft, zzd4);
                                zzamg.zzf.put(zzc2.zza, zzc2);
                                break;
                            }
                        } else {
                            zzalz zzc3 = zzc(zzft, zzd4);
                            zzamg.zzd.put(zzc3.zza, zzc3);
                            break;
                        }
                        break;
                    case 19:
                        if (zzd3 != zzamg.zza) {
                            if (zzd3 == zzamg.zzb) {
                                zzamb zzd23 = zzd(zzft);
                                zzamg.zzg.put(zzd23.zza, zzd23);
                                break;
                            }
                        } else {
                            zzamb zzd24 = zzd(zzft);
                            zzamg.zze.put(zzd24.zza, zzd24);
                            break;
                        }
                        break;
                    case 20:
                        if (zzd3 == zzamg.zza) {
                            zzft.zzm(4);
                            boolean zzo2 = zzft.zzo();
                            zzft.zzm(3);
                            int zzd25 = zzft.zzd(16);
                            int zzd26 = zzft.zzd(16);
                            if (zzo2) {
                                int zzd27 = zzft.zzd(16);
                                i10 = zzft.zzd(16);
                                i9 = zzft.zzd(16);
                                i8 = zzft.zzd(16);
                                i11 = zzd27;
                            } else {
                                i10 = zzd25;
                                i8 = zzd26;
                                i11 = 0;
                                i9 = 0;
                            }
                            zzamg.zzh = new zzama(zzd25, zzd26, i11, i10, i9, i8);
                            break;
                        }
                        break;
                }
                zzft.zzn(zzb2 - zzft.zzb());
            }
        }
        zzamg zzamg2 = this.zzi;
        zzamc zzamc4 = zzamg2.zzi;
        if (zzamc4 == null) {
            zzaln = new zzaln(zzgbc.zzm(), C.TIME_UNSET, C.TIME_UNSET);
        } else {
            zzama zzama = zzamg2.zzh;
            if (zzama == null) {
                zzama = this.zzg;
            }
            Bitmap bitmap = this.zzj;
            if (!(bitmap != null && zzama.zza + 1 == bitmap.getWidth() && zzama.zzb + 1 == this.zzj.getHeight())) {
                Bitmap createBitmap = Bitmap.createBitmap(zzama.zza + 1, zzama.zzb + 1, Bitmap.Config.ARGB_8888);
                this.zzj = createBitmap;
                this.zzf.setBitmap(createBitmap);
            }
            ArrayList arrayList = new ArrayList();
            SparseArray sparseArray6 = zzamc4.zzc;
            int i17 = 0;
            while (i17 < sparseArray6.size()) {
                this.zzf.save();
                zzamd zzamd = (zzamd) sparseArray6.valueAt(i17);
                zzame zzame3 = (zzame) this.zzi.zzc.get(sparseArray6.keyAt(i17));
                int i18 = zzamd.zza + zzama.zzc;
                int i19 = zzamd.zzb + zzama.zze;
                this.zzf.clipRect(i18, i19, Math.min(zzame3.zzc + i18, zzama.zzd), Math.min(zzame3.zzd + i19, zzama.zzf));
                zzalz zzalz = (zzalz) this.zzi.zzd.get(zzame3.zzf);
                if (zzalz == null) {
                    zzalz = (zzalz) this.zzi.zzf.get(zzame3.zzf);
                    if (zzalz == null) {
                        zzalz = this.zzh;
                    }
                }
                SparseArray sparseArray7 = zzame3.zzj;
                int i20 = 0;
                while (i20 < sparseArray7.size()) {
                    int keyAt = sparseArray7.keyAt(i20);
                    zzamf zzamf = (zzamf) sparseArray7.valueAt(i20);
                    zzamb zzamb = (zzamb) this.zzi.zze.get(keyAt);
                    if (zzamb == null) {
                        zzamb = (zzamb) this.zzi.zzg.get(keyAt);
                    }
                    if (zzamb != null) {
                        if (zzamb.zzb) {
                            paint = null;
                        } else {
                            paint = this.zzd;
                        }
                        int i21 = zzame3.zze;
                        int i22 = zzamf.zza + i18;
                        int i23 = zzamf.zzb + i19;
                        sparseArray2 = sparseArray6;
                        Canvas canvas = this.zzf;
                        sparseArray = sparseArray7;
                        if (i21 == 3) {
                            iArr = zzalz.zzd;
                        } else if (i21 == 2) {
                            iArr = zzalz.zzc;
                        } else {
                            iArr = zzalz.zzb;
                        }
                        i4 = i17;
                        int[] iArr2 = iArr;
                        int i24 = i21;
                        int i25 = i22;
                        Paint paint2 = paint;
                        Canvas canvas2 = canvas;
                        zze(zzamb.zzc, iArr2, i24, i25, i23, paint2, canvas2);
                        zze(zzamb.zzd, iArr2, i24, i25, i23 + 1, paint2, canvas2);
                    } else {
                        sparseArray2 = sparseArray6;
                        i4 = i17;
                        sparseArray = sparseArray7;
                    }
                    i20++;
                    sparseArray6 = sparseArray2;
                    sparseArray7 = sparseArray;
                    i17 = i4;
                }
                SparseArray sparseArray8 = sparseArray6;
                int i26 = i17;
                float f = (float) i19;
                float f2 = (float) i18;
                if (zzame3.zzb) {
                    int i27 = zzame3.zze;
                    c3 = 3;
                    if (i27 == 3) {
                        i3 = zzalz.zzd[zzame3.zzg];
                        c2 = 2;
                    } else {
                        c2 = 2;
                        if (i27 == 2) {
                            i3 = zzalz.zzc[zzame3.zzh];
                        } else {
                            i3 = zzalz.zzb[zzame3.zzi];
                        }
                    }
                    this.zze.setColor(i3);
                    this.zzf.drawRect(f2, f, (float) (zzame3.zzc + i18), (float) (zzame3.zzd + i19), this.zze);
                } else {
                    c3 = 3;
                    c2 = 2;
                }
                zzeg zzeg = new zzeg();
                zzeg.zzc(Bitmap.createBitmap(this.zzj, i18, i19, zzame3.zzc, zzame3.zzd));
                zzeg.zzh(f2 / ((float) zzama.zza));
                zzeg.zzi(0);
                zzeg.zze(f / ((float) zzama.zzb), 0);
                zzeg.zzf(0);
                zzeg.zzk(((float) zzame3.zzc) / ((float) zzama.zza));
                zzeg.zzd(((float) zzame3.zzd) / ((float) zzama.zzb));
                arrayList.add(zzeg.zzp());
                this.zzf.drawColor(0, PorterDuff.Mode.CLEAR);
                this.zzf.restore();
                i17 = i26 + 1;
                char c4 = c3;
                char c5 = c2;
                sparseArray6 = sparseArray8;
            }
            zzaln = new zzaln(arrayList, C.TIME_UNSET, C.TIME_UNSET);
        }
        zzev.zza(zzaln);
    }
}
