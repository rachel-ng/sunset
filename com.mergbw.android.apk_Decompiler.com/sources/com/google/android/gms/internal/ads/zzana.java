package com.google.android.gms.internal.ads;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import com.google.android.exoplayer2.C;
import com.google.common.base.Ascii;
import java.nio.charset.Charset;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzana implements zzalv {
    private final zzfu zza = new zzfu();
    private final boolean zzb;
    private final int zzc;
    private final int zzd;
    private final String zze;
    private final float zzf;
    private final int zzg;

    public zzana(List list) {
        int size = list.size();
        String str = C.SANS_SERIF_NAME;
        boolean z = false;
        if (size == 1 && (((byte[]) list.get(0)).length == 48 || ((byte[]) list.get(0)).length == 53)) {
            byte[] bArr = (byte[]) list.get(0);
            this.zzc = bArr[24];
            this.zzd = ((bArr[26] & 255) << Ascii.CAN) | ((bArr[27] & 255) << 16) | ((bArr[28] & 255) << 8) | (bArr[29] & 255);
            this.zze = true == "Serif".equals(zzgd.zzB(bArr, 43, bArr.length + -43)) ? C.SERIF_NAME : str;
            int i = bArr[25] * Ascii.DC4;
            this.zzg = i;
            z = (bArr[0] & 32) != 0 ? true : z;
            this.zzb = z;
            if (z) {
                this.zzf = Math.max(0.0f, Math.min(((float) ((bArr[11] & 255) | ((bArr[10] & 255) << 8))) / ((float) i), 0.95f));
            } else {
                this.zzf = 0.85f;
            }
        } else {
            this.zzc = 0;
            this.zzd = -1;
            this.zze = str;
            this.zzb = false;
            this.zzf = 0.85f;
            this.zzg = -1;
        }
    }

    private static void zzb(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3, int i4, int i5) {
        if (i != i2) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan((i >>> 8) | ((i & 255) << 24)), i3, i4, i5 | 33);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zzc(android.text.SpannableStringBuilder r4, int r5, int r6, int r7, int r8, int r9) {
        /*
            if (r5 == r6) goto L_0x0047
            r6 = r9 | 33
            r9 = r5 & 1
            r0 = r5 & 2
            r1 = 0
            r2 = 1
            if (r9 == 0) goto L_0x0021
            if (r0 == 0) goto L_0x0018
            android.text.style.StyleSpan r0 = new android.text.style.StyleSpan
            r3 = 3
            r0.<init>(r3)
            r4.setSpan(r0, r7, r8, r6)
            goto L_0x002e
        L_0x0018:
            android.text.style.StyleSpan r0 = new android.text.style.StyleSpan
            r0.<init>(r2)
            r4.setSpan(r0, r7, r8, r6)
            goto L_0x002d
        L_0x0021:
            if (r0 == 0) goto L_0x002d
            android.text.style.StyleSpan r0 = new android.text.style.StyleSpan
            r3 = 2
            r0.<init>(r3)
            r4.setSpan(r0, r7, r8, r6)
            goto L_0x002e
        L_0x002d:
            r2 = r1
        L_0x002e:
            r5 = r5 & 4
            if (r5 != 0) goto L_0x003f
            if (r9 != 0) goto L_0x0047
            if (r2 != 0) goto L_0x0047
            android.text.style.StyleSpan r5 = new android.text.style.StyleSpan
            r5.<init>(r1)
            r4.setSpan(r5, r7, r8, r6)
            return
        L_0x003f:
            android.text.style.UnderlineSpan r5 = new android.text.style.UnderlineSpan
            r5.<init>()
            r4.setSpan(r5, r7, r8, r6)
        L_0x0047:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzana.zzc(android.text.SpannableStringBuilder, int, int, int, int, int):void");
    }

    public final void zza(byte[] bArr, int i, int i2, zzalu zzalu, zzev zzev) {
        String str;
        int i3;
        int i4;
        int i5;
        int i6 = i;
        zzev zzev2 = zzev;
        this.zza.zzI(bArr, i6 + i2);
        this.zza.zzK(i6);
        zzfu zzfu = this.zza;
        boolean z = true;
        boolean z2 = false;
        int i7 = 2;
        zzeq.zzd(zzfu.zzb() >= 2);
        int zzq = zzfu.zzq();
        if (zzq == 0) {
            str = "";
        } else {
            int zzd2 = zzfu.zzd();
            Charset zzB = zzfu.zzB();
            int zzd3 = zzfu.zzd() - zzd2;
            if (zzB == null) {
                zzB = zzfxs.zzc;
            }
            str = zzfu.zzA(zzq - zzd3, zzB);
        }
        if (str.isEmpty()) {
            zzev2.zza(new zzaln(zzgbc.zzm(), C.TIME_UNSET, C.TIME_UNSET));
            return;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        zzc(spannableStringBuilder2, this.zzc, 0, 0, spannableStringBuilder.length(), 16711680);
        zzb(spannableStringBuilder2, this.zzd, -1, 0, spannableStringBuilder.length(), 16711680);
        String str2 = this.zze;
        int length = spannableStringBuilder.length();
        if (str2 != C.SANS_SERIF_NAME) {
            spannableStringBuilder.setSpan(new TypefaceSpan(str2), 0, length, 16711713);
        }
        float f = this.zzf;
        while (true) {
            zzfu zzfu2 = this.zza;
            if (zzfu2.zzb() >= 8) {
                int zzd4 = zzfu2.zzd();
                int zzg2 = zzfu2.zzg();
                int zzg3 = this.zza.zzg();
                if (zzg3 == 1937013100) {
                    zzeq.zzd(this.zza.zzb() >= i7 ? z : z2);
                    int zzq2 = this.zza.zzq();
                    int i8 = z2;
                    while (i8 < zzq2) {
                        zzfu zzfu3 = this.zza;
                        zzeq.zzd(zzfu3.zzb() >= 12 ? z : z2);
                        int zzq3 = zzfu3.zzq();
                        int zzq4 = zzfu3.zzq();
                        zzfu3.zzL(i7);
                        int zzm = zzfu3.zzm();
                        zzfu3.zzL(z ? 1 : 0);
                        int zzg4 = zzfu3.zzg();
                        if (zzq4 > spannableStringBuilder.length()) {
                            int length2 = spannableStringBuilder.length();
                            i4 = zzq2;
                            zzfk.zzf("Tx3gParser", "Truncating styl end (" + zzq4 + ") to cueText.length() (" + length2 + ").");
                            i5 = spannableStringBuilder.length();
                        } else {
                            i4 = zzq2;
                            i5 = zzq4;
                        }
                        if (zzq3 >= i5) {
                            zzfk.zzf("Tx3gParser", "Ignoring styl with start (" + zzq3 + ") >= end (" + i5 + ").");
                        } else {
                            SpannableStringBuilder spannableStringBuilder3 = spannableStringBuilder;
                            int i9 = zzq3;
                            int i10 = i5;
                            zzc(spannableStringBuilder3, zzm, this.zzc, i9, i10, 0);
                            zzb(spannableStringBuilder3, zzg4, this.zzd, i9, i10, 0);
                        }
                        i8++;
                        zzq2 = i4;
                        z = true;
                        z2 = false;
                        i7 = 2;
                    }
                    i3 = i7;
                } else if (zzg3 != 1952608120 || !this.zzb) {
                    i3 = 2;
                } else {
                    i3 = 2;
                    zzeq.zzd(this.zza.zzb() >= 2);
                    f = Math.max(0.0f, Math.min(((float) this.zza.zzq()) / ((float) this.zzg), 0.95f));
                }
                this.zza.zzK(zzd4 + zzg2);
                i7 = i3;
                z = true;
                z2 = false;
            } else {
                zzeg zzeg = new zzeg();
                zzeg.zzl(spannableStringBuilder);
                zzeg.zze(f, 0);
                zzeg.zzf(0);
                zzev2.zza(new zzaln(zzgbc.zzn(zzeg.zzp()), C.TIME_UNSET, C.TIME_UNSET));
                return;
            }
        }
    }
}
