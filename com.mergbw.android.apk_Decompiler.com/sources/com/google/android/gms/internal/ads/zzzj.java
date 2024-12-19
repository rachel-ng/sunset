package com.google.android.gms.internal.ads;

import android.util.Pair;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzzj extends zzzm {
    private zzzi zza;

    /* access modifiers changed from: protected */
    public abstract Pair zzd(zzzi zzzi, int[][][] iArr, int[] iArr2, zzvo zzvo, zzdc zzdc) throws zzjh;

    public final zzzn zzp(zzmp[] zzmpArr, zzxr zzxr, zzvo zzvo, zzdc zzdc) throws zzjh {
        boolean z;
        int[] iArr;
        zzxr zzxr2 = zzxr;
        int[] iArr2 = new int[3];
        zzde[][] zzdeArr = new zzde[3][];
        int[][][] iArr3 = new int[3][][];
        for (int i = 0; i < 3; i++) {
            int i2 = zzxr2.zzc;
            zzdeArr[i] = new zzde[i2];
            iArr3[i] = new int[i2][];
        }
        int i3 = 2;
        int[] iArr4 = new int[2];
        for (int i4 = 0; i4 < 2; i4++) {
            iArr4[i4] = zzmpArr[i4].zze();
        }
        int i5 = 0;
        while (i5 < zzxr2.zzc) {
            zzde zzb = zzxr2.zzb(i5);
            int i6 = zzb.zzd;
            int i7 = i3;
            int i8 = 0;
            int i9 = 0;
            boolean z2 = true;
            while (i8 < i3) {
                zzmp zzmp = zzmpArr[i8];
                int i10 = 0;
                for (int i11 = 0; i11 < zzb.zzb; i11++) {
                    i10 = Math.max(i10, zzmp.zzY(zzb.zzb(i11)) & 7);
                }
                boolean z3 = iArr2[i8] == 0;
                if (i10 > i9) {
                    z2 = z3;
                    i7 = i8;
                    i9 = i10;
                } else if (i10 == i9 && i6 == 5 && !z2 && z3) {
                    i7 = i8;
                    i9 = i10;
                    z2 = true;
                }
                i8++;
                i3 = 2;
            }
            if (i7 == i3) {
                iArr = new int[zzb.zzb];
            } else {
                zzmp zzmp2 = zzmpArr[i7];
                int[] iArr5 = new int[zzb.zzb];
                for (int i12 = 0; i12 < zzb.zzb; i12++) {
                    iArr5[i12] = zzmp2.zzY(zzb.zzb(i12));
                }
                iArr = iArr5;
            }
            int i13 = iArr2[i7];
            zzdeArr[i7][i13] = zzb;
            iArr3[i7][i13] = iArr;
            iArr2[i7] = i13 + 1;
            i5++;
            i3 = 2;
        }
        int i14 = i3;
        boolean z4 = true;
        zzxr[] zzxrArr = new zzxr[i14];
        String[] strArr = new String[i14];
        int[] iArr6 = new int[i14];
        int i15 = 0;
        while (i15 < i14) {
            int i16 = iArr2[i15];
            zzxrArr[i15] = new zzxr((zzde[]) zzgd.zzP(zzdeArr[i15], i16));
            iArr3[i15] = (int[][]) zzgd.zzP(iArr3[i15], i16);
            strArr[i15] = zzmpArr[i15].zzU();
            iArr6[i15] = zzmpArr[i15].zzb();
            i15++;
            i14 = 2;
        }
        int i17 = i14;
        zzzi zzzi = new zzzi(strArr, iArr6, zzxrArr, iArr4, iArr3, new zzxr((zzde[]) zzgd.zzP(zzdeArr[i17], iArr2[i17])));
        Pair zzd = zzd(zzzi, iArr3, iArr4, zzvo, zzdc);
        zzzk[] zzzkArr = (zzzk[]) zzd.second;
        List[] listArr = new List[zzzkArr.length];
        for (int i18 = 0; i18 < zzzkArr.length; i18++) {
            zzzk zzzk = zzzkArr[i18];
            listArr[i18] = zzzk != null ? zzgbc.zzn(zzzk) : zzgbc.zzm();
        }
        zzgaz zzgaz = new zzgaz();
        int i19 = 0;
        while (i19 < 2) {
            zzxr zzd2 = zzzi.zzd(i19);
            List list = listArr[i19];
            int i20 = 0;
            while (i20 < zzd2.zzc) {
                zzde zzb2 = zzd2.zzb(i20);
                boolean z5 = zzzi.zza(i19, i20, false) != 0 ? z4 : false;
                int i21 = zzb2.zzb;
                int[] iArr7 = new int[i21];
                boolean[] zArr = new boolean[i21];
                for (int i22 = 0; i22 < zzb2.zzb; i22++) {
                    iArr7[i22] = zzzi.zzb(i19, i20, i22) & 7;
                    int i23 = 0;
                    while (true) {
                        if (i23 >= list.size()) {
                            z = false;
                            break;
                        }
                        zzzk zzzk2 = (zzzk) list.get(i23);
                        if (zzzk2.zze().equals(zzb2) && zzzk2.zzb(i22) != -1) {
                            z = true;
                            break;
                        }
                        i23++;
                    }
                    zArr[i22] = z;
                }
                zzgaz.zzf(new zzdo(zzb2, z5, iArr7, zArr));
                i20++;
                z4 = true;
            }
            i19++;
            z4 = true;
        }
        zzxr zze = zzzi.zze();
        for (int i24 = 0; i24 < zze.zzc; i24++) {
            zzde zzb3 = zze.zzb(i24);
            int[] iArr8 = new int[zzb3.zzb];
            Arrays.fill(iArr8, 0);
            zzgaz.zzf(new zzdo(zzb3, false, iArr8, new boolean[zzb3.zzb]));
        }
        return new zzzn((zzmq[]) zzd.first, (zzzg[]) zzd.second, new zzdp(zzgaz.zzi()), zzzi);
    }

    public final void zzq(Object obj) {
        this.zza = (zzzi) obj;
    }
}
