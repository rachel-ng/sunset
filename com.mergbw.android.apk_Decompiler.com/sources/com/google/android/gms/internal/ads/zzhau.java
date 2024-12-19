package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhau implements zzhfi {
    private final zzhat zza;

    private zzhau(zzhat zzhat) {
        zzhcb.zzc(zzhat, "output");
        this.zza = zzhat;
        zzhat.zze = this;
    }

    public static zzhau zza(zzhat zzhat) {
        zzhau zzhau = zzhat.zze;
        if (zzhau != null) {
            return zzhau;
        }
        return new zzhau(zzhat);
    }

    public final void zzB(int i, int i2) throws IOException {
        this.zza.zzt(i, (i2 >> 31) ^ (i2 + i2));
    }

    public final void zzD(int i, long j) throws IOException {
        this.zza.zzv(i, (j >> 63) ^ (j + j));
    }

    @Deprecated
    public final void zzF(int i) throws IOException {
        this.zza.zzs(i, 3);
    }

    public final void zzG(int i, String str) throws IOException {
        this.zza.zzq(i, str);
    }

    public final void zzI(int i, int i2) throws IOException {
        this.zza.zzt(i, i2);
    }

    public final void zzK(int i, long j) throws IOException {
        this.zza.zzv(i, j);
    }

    public final void zzb(int i, boolean z) throws IOException {
        this.zza.zzN(i, z);
    }

    public final void zzd(int i, zzhac zzhac) throws IOException {
        this.zza.zzO(i, zzhac);
    }

    public final void zze(int i, List list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zzO(i, (zzhac) list.get(i2));
        }
    }

    public final void zzf(int i, double d) throws IOException {
        this.zza.zzj(i, Double.doubleToRawLongBits(d));
    }

    @Deprecated
    public final void zzh(int i) throws IOException {
        this.zza.zzs(i, 4);
    }

    public final void zzi(int i, int i2) throws IOException {
        this.zza.zzl(i, i2);
    }

    public final void zzk(int i, int i2) throws IOException {
        this.zza.zzh(i, i2);
    }

    public final void zzm(int i, long j) throws IOException {
        this.zza.zzj(i, j);
    }

    public final void zzo(int i, float f) throws IOException {
        this.zza.zzh(i, Float.floatToRawIntBits(f));
    }

    public final void zzq(int i, Object obj, zzhdz zzhdz) throws IOException {
        zzhat zzhat = this.zza;
        zzhat.zzs(i, 3);
        zzhdz.zzj((zzhde) obj, zzhat.zze);
        zzhat.zzs(i, 4);
    }

    public final void zzr(int i, int i2) throws IOException {
        this.zza.zzl(i, i2);
    }

    public final void zzt(int i, long j) throws IOException {
        this.zza.zzv(i, j);
    }

    public final void zzv(int i, Object obj, zzhdz zzhdz) throws IOException {
        this.zza.zzn(i, (zzhde) obj, zzhdz);
    }

    public final void zzw(int i, Object obj) throws IOException {
        if (obj instanceof zzhac) {
            this.zza.zzp(i, (zzhac) obj);
        } else {
            this.zza.zzo(i, (zzhde) obj);
        }
    }

    public final void zzx(int i, int i2) throws IOException {
        this.zza.zzh(i, i2);
    }

    public final void zzz(int i, long j) throws IOException {
        this.zza.zzj(i, j);
    }

    public final void zzH(int i, List list) throws IOException {
        int i2 = 0;
        if (list instanceof zzhcm) {
            zzhcm zzhcm = (zzhcm) list;
            while (i2 < list.size()) {
                Object zze = zzhcm.zze(i2);
                if (zze instanceof String) {
                    this.zza.zzq(i, (String) zze);
                } else {
                    this.zza.zzO(i, (zzhac) zze);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzq(i, (String) list.get(i2));
            i2++;
        }
    }

    public final void zzj(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzs(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzhat.zzE((long) ((Integer) list.get(i4)).intValue());
            }
            this.zza.zzu(i3);
            while (i2 < list.size()) {
                this.zza.zzm(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzl(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzJ(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            if (z) {
                this.zza.zzs(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzhbp.size(); i4++) {
                    i3 += zzhat.zzD(zzhbp.zzd(i4));
                }
                this.zza.zzu(i3);
                while (i2 < zzhbp.size()) {
                    this.zza.zzu(zzhbp.zzd(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzhbp.size()) {
                this.zza.zzt(i, zzhbp.zzd(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzs(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzhat.zzD(((Integer) list.get(i6)).intValue());
            }
            this.zza.zzu(i5);
            while (i2 < list.size()) {
                this.zza.zzu(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzt(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzL(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzhct) {
            zzhct zzhct = (zzhct) list;
            if (z) {
                this.zza.zzs(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzhct.size(); i4++) {
                    i3 += zzhat.zzE(zzhct.zza(i4));
                }
                this.zza.zzu(i3);
                while (i2 < zzhct.size()) {
                    this.zza.zzw(zzhct.zza(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzhct.size()) {
                this.zza.zzv(i, zzhct.zza(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzs(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzhat.zzE(((Long) list.get(i6)).longValue());
            }
            this.zza.zzu(i5);
            while (i2 < list.size()) {
                this.zza.zzw(((Long) list.get(i2)).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzv(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    public final void zzl(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            if (z) {
                this.zza.zzs(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzhbp.size(); i4++) {
                    zzhbp.zzd(i4);
                    i3 += 4;
                }
                this.zza.zzu(i3);
                while (i2 < zzhbp.size()) {
                    this.zza.zzi(zzhbp.zzd(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzhbp.size()) {
                this.zza.zzh(i, zzhbp.zzd(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzs(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Integer) list.get(i6)).intValue();
                i5 += 4;
            }
            this.zza.zzu(i5);
            while (i2 < list.size()) {
                this.zza.zzi(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzn(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzhct) {
            zzhct zzhct = (zzhct) list;
            if (z) {
                this.zza.zzs(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzhct.size(); i4++) {
                    zzhct.zza(i4);
                    i3 += 8;
                }
                this.zza.zzu(i3);
                while (i2 < zzhct.size()) {
                    this.zza.zzk(zzhct.zza(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzhct.size()) {
                this.zza.zzj(i, zzhct.zza(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzs(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Long) list.get(i6)).longValue();
                i5 += 8;
            }
            this.zza.zzu(i5);
            while (i2 < list.size()) {
                this.zza.zzk(((Long) list.get(i2)).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzj(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    public final void zzc(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzgzp) {
            zzgzp zzgzp = (zzgzp) list;
            if (z) {
                this.zza.zzs(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzgzp.size(); i4++) {
                    zzgzp.zzh(i4);
                    i3++;
                }
                this.zza.zzu(i3);
                while (i2 < zzgzp.size()) {
                    this.zza.zzM(zzgzp.zzh(i2) ? (byte) 1 : 0);
                    i2++;
                }
                return;
            }
            while (i2 < zzgzp.size()) {
                this.zza.zzN(i, zzgzp.zzh(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzs(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Boolean) list.get(i6)).booleanValue();
                i5++;
            }
            this.zza.zzu(i5);
            while (i2 < list.size()) {
                this.zza.zzM(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : 0);
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzN(i, ((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
        }
    }

    public final void zzs(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            if (z) {
                this.zza.zzs(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzhbp.size(); i4++) {
                    i3 += zzhat.zzE((long) zzhbp.zzd(i4));
                }
                this.zza.zzu(i3);
                while (i2 < zzhbp.size()) {
                    this.zza.zzm(zzhbp.zzd(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzhbp.size()) {
                this.zza.zzl(i, zzhbp.zzd(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzs(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzhat.zzE((long) ((Integer) list.get(i6)).intValue());
            }
            this.zza.zzu(i5);
            while (i2 < list.size()) {
                this.zza.zzm(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzl(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzA(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzhct) {
            zzhct zzhct = (zzhct) list;
            if (z) {
                this.zza.zzs(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzhct.size(); i4++) {
                    zzhct.zza(i4);
                    i3 += 8;
                }
                this.zza.zzu(i3);
                while (i2 < zzhct.size()) {
                    this.zza.zzk(zzhct.zza(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzhct.size()) {
                this.zza.zzj(i, zzhct.zza(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzs(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Long) list.get(i6)).longValue();
                i5 += 8;
            }
            this.zza.zzu(i5);
            while (i2 < list.size()) {
                this.zza.zzk(((Long) list.get(i2)).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzj(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    public final void zzg(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzhav) {
            zzhav zzhav = (zzhav) list;
            if (z) {
                this.zza.zzs(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzhav.size(); i4++) {
                    zzhav.zzd(i4);
                    i3 += 8;
                }
                this.zza.zzu(i3);
                while (i2 < zzhav.size()) {
                    this.zza.zzk(Double.doubleToRawLongBits(zzhav.zzd(i2)));
                    i2++;
                }
                return;
            }
            while (i2 < zzhav.size()) {
                this.zza.zzj(i, Double.doubleToRawLongBits(zzhav.zzd(i2)));
                i2++;
            }
        } else if (z) {
            this.zza.zzs(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Double) list.get(i6)).doubleValue();
                i5 += 8;
            }
            this.zza.zzu(i5);
            while (i2 < list.size()) {
                this.zza.zzk(Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzj(i, Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                i2++;
            }
        }
    }

    public final void zzp(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzhbf) {
            zzhbf zzhbf = (zzhbf) list;
            if (z) {
                this.zza.zzs(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzhbf.size(); i4++) {
                    zzhbf.zzd(i4);
                    i3 += 4;
                }
                this.zza.zzu(i3);
                while (i2 < zzhbf.size()) {
                    this.zza.zzi(Float.floatToRawIntBits(zzhbf.zzd(i2)));
                    i2++;
                }
                return;
            }
            while (i2 < zzhbf.size()) {
                this.zza.zzh(i, Float.floatToRawIntBits(zzhbf.zzd(i2)));
                i2++;
            }
        } else if (z) {
            this.zza.zzs(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Float) list.get(i6)).floatValue();
                i5 += 4;
            }
            this.zza.zzu(i5);
            while (i2 < list.size()) {
                this.zza.zzi(Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                i2++;
            }
        }
    }

    public final void zzy(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            if (z) {
                this.zza.zzs(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzhbp.size(); i4++) {
                    zzhbp.zzd(i4);
                    i3 += 4;
                }
                this.zza.zzu(i3);
                while (i2 < zzhbp.size()) {
                    this.zza.zzi(zzhbp.zzd(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzhbp.size()) {
                this.zza.zzh(i, zzhbp.zzd(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzs(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Integer) list.get(i6)).intValue();
                i5 += 4;
            }
            this.zza.zzu(i5);
            while (i2 < list.size()) {
                this.zza.zzi(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzC(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            if (z) {
                this.zza.zzs(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzhbp.size(); i4++) {
                    int zzd = zzhbp.zzd(i4);
                    i3 += zzhat.zzD((zzd >> 31) ^ (zzd + zzd));
                }
                this.zza.zzu(i3);
                while (i2 < zzhbp.size()) {
                    zzhat zzhat = this.zza;
                    int zzd2 = zzhbp.zzd(i2);
                    zzhat.zzu((zzd2 >> 31) ^ (zzd2 + zzd2));
                    i2++;
                }
                return;
            }
            while (i2 < zzhbp.size()) {
                zzhat zzhat2 = this.zza;
                int zzd3 = zzhbp.zzd(i2);
                zzhat2.zzt(i, (zzd3 >> 31) ^ (zzd3 + zzd3));
                i2++;
            }
        } else if (z) {
            this.zza.zzs(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                int intValue = ((Integer) list.get(i6)).intValue();
                i5 += zzhat.zzD((intValue >> 31) ^ (intValue + intValue));
            }
            this.zza.zzu(i5);
            while (i2 < list.size()) {
                zzhat zzhat3 = this.zza;
                int intValue2 = ((Integer) list.get(i2)).intValue();
                zzhat3.zzu((intValue2 >> 31) ^ (intValue2 + intValue2));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                zzhat zzhat4 = this.zza;
                int intValue3 = ((Integer) list.get(i2)).intValue();
                zzhat4.zzt(i, (intValue3 >> 31) ^ (intValue3 + intValue3));
                i2++;
            }
        }
    }

    public final void zzE(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzhct) {
            zzhct zzhct = (zzhct) list;
            if (z) {
                this.zza.zzs(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzhct.size(); i4++) {
                    long zza2 = zzhct.zza(i4);
                    i3 += zzhat.zzE((zza2 >> 63) ^ (zza2 + zza2));
                }
                this.zza.zzu(i3);
                while (i2 < zzhct.size()) {
                    zzhat zzhat = this.zza;
                    long zza3 = zzhct.zza(i2);
                    zzhat.zzw((zza3 >> 63) ^ (zza3 + zza3));
                    i2++;
                }
                return;
            }
            while (i2 < zzhct.size()) {
                zzhat zzhat2 = this.zza;
                long zza4 = zzhct.zza(i2);
                zzhat2.zzv(i, (zza4 >> 63) ^ (zza4 + zza4));
                i2++;
            }
        } else if (z) {
            this.zza.zzs(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                long longValue = ((Long) list.get(i6)).longValue();
                i5 += zzhat.zzE((longValue >> 63) ^ (longValue + longValue));
            }
            this.zza.zzu(i5);
            while (i2 < list.size()) {
                zzhat zzhat3 = this.zza;
                long longValue2 = ((Long) list.get(i2)).longValue();
                zzhat3.zzw((longValue2 >> 63) ^ (longValue2 + longValue2));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                zzhat zzhat4 = this.zza;
                long longValue3 = ((Long) list.get(i2)).longValue();
                zzhat4.zzv(i, (longValue3 >> 63) ^ (longValue3 + longValue3));
                i2++;
            }
        }
    }

    public final void zzu(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzhct) {
            zzhct zzhct = (zzhct) list;
            if (z) {
                this.zza.zzs(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzhct.size(); i4++) {
                    i3 += zzhat.zzE(zzhct.zza(i4));
                }
                this.zza.zzu(i3);
                while (i2 < zzhct.size()) {
                    this.zza.zzw(zzhct.zza(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzhct.size()) {
                this.zza.zzv(i, zzhct.zza(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzs(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzhat.zzE(((Long) list.get(i6)).longValue());
            }
            this.zza.zzu(i5);
            while (i2 < list.size()) {
                this.zza.zzw(((Long) list.get(i2)).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzv(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }
}
