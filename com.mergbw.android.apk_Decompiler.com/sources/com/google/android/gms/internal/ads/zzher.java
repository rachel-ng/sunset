package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzher {
    private static final zzher zza = new zzher(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzher() {
        this(0, new int[8], new Object[8], true);
    }

    private zzher(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzher zzc() {
        return zza;
    }

    static zzher zze(zzher zzher, zzher zzher2) {
        int i = zzher.zzb + zzher2.zzb;
        int[] copyOf = Arrays.copyOf(zzher.zzc, i);
        System.arraycopy(zzher2.zzc, 0, copyOf, zzher.zzb, zzher2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzher.zzd, i);
        System.arraycopy(zzher2.zzd, 0, copyOf2, zzher.zzb, zzher2.zzb);
        return new zzher(i, copyOf, copyOf2, true);
    }

    static zzher zzf() {
        return new zzher();
    }

    private final void zzn(int i) {
        int[] iArr = this.zzc;
        if (i > iArr.length) {
            int i2 = this.zzb;
            int i3 = i2 + (i2 / 2);
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzher)) {
            return false;
        }
        zzher zzher = (zzher) obj;
        int i = this.zzb;
        if (i == zzher.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzher.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzher.zzd;
                    int i3 = this.zzb;
                    int i4 = 0;
                    while (i4 < i3) {
                        if (objArr[i4].equals(objArr2[i4])) {
                            i4++;
                        }
                    }
                    return true;
                } else if (iArr[i2] != iArr2[i2]) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = i + 527;
        int[] iArr = this.zzc;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = ((i2 * 31) + i4) * 31;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final int zza() {
        int i;
        int i2;
        int i3;
        int i4 = this.zze;
        if (i4 != -1) {
            return i4;
        }
        int i5 = 0;
        for (int i6 = 0; i6 < this.zzb; i6++) {
            int i7 = this.zzc[i6];
            int i8 = i7 >>> 3;
            int i9 = i7 & 7;
            if (i9 != 0) {
                if (i9 == 1) {
                    ((Long) this.zzd[i6]).longValue();
                    i = zzhat.zzD(i8 << 3) + 8;
                } else if (i9 == 2) {
                    int zzD = zzhat.zzD(i8 << 3);
                    int zzd2 = ((zzhac) this.zzd[i6]).zzd();
                    i = zzD + zzhat.zzD(zzd2) + zzd2;
                } else if (i9 == 3) {
                    int zzD2 = zzhat.zzD(i8 << 3);
                    i3 = zzD2 + zzD2;
                    i2 = ((zzher) this.zzd[i6]).zza();
                } else if (i9 == 5) {
                    ((Integer) this.zzd[i6]).intValue();
                    i = zzhat.zzD(i8 << 3) + 4;
                } else {
                    throw new IllegalStateException(zzhcd.zza());
                }
                i5 += i;
            } else {
                int i10 = i8 << 3;
                long longValue = ((Long) this.zzd[i6]).longValue();
                i3 = zzhat.zzD(i10);
                i2 = zzhat.zzE(longValue);
            }
            i = i3 + i2;
            i5 += i;
        }
        this.zze = i5;
        return i5;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int zzD = zzhat.zzD(8);
            int zzD2 = zzhat.zzD(16) + zzhat.zzD(this.zzc[i3] >>> 3);
            int zzD3 = zzhat.zzD(24);
            int zzd2 = ((zzhac) this.zzd[i3]).zzd();
            i2 += zzD + zzD + zzD2 + zzD3 + zzhat.zzD(zzd2) + zzd2;
        }
        this.zze = i2;
        return i2;
    }

    /* access modifiers changed from: package-private */
    public final zzher zzd(zzher zzher) {
        if (zzher.equals(zza)) {
            return this;
        }
        zzg();
        int i = this.zzb + zzher.zzb;
        zzn(i);
        System.arraycopy(zzher.zzc, 0, this.zzc, this.zzb, zzher.zzb);
        System.arraycopy(zzher.zzd, 0, this.zzd, this.zzb, zzher.zzb);
        this.zzb = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final void zzg() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzh() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzi(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzhdg.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzj(int i, Object obj) {
        zzg();
        zzn(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    /* access modifiers changed from: package-private */
    public final void zzk(zzhfi zzhfi) throws IOException {
        for (int i = 0; i < this.zzb; i++) {
            zzhfi.zzw(this.zzc[i] >>> 3, this.zzd[i]);
        }
    }

    public final void zzl(zzhfi zzhfi) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 & 7;
                int i4 = i2 >>> 3;
                if (i3 == 0) {
                    zzhfi.zzt(i4, ((Long) obj).longValue());
                } else if (i3 == 1) {
                    zzhfi.zzm(i4, ((Long) obj).longValue());
                } else if (i3 == 2) {
                    zzhfi.zzd(i4, (zzhac) obj);
                } else if (i3 == 3) {
                    zzhfi.zzF(i4);
                    ((zzher) obj).zzl(zzhfi);
                    zzhfi.zzh(i4);
                } else if (i3 == 5) {
                    zzhfi.zzk(i4, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(zzhcd.zza());
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzm(int i, zzham zzham) throws IOException {
        int zzm;
        zzg();
        int i2 = i & 7;
        if (i2 == 0) {
            zzj(i, Long.valueOf(zzham.zzp()));
            return true;
        } else if (i2 == 1) {
            zzj(i, Long.valueOf(zzham.zzo()));
            return true;
        } else if (i2 == 2) {
            zzj(i, zzham.zzw());
            return true;
        } else if (i2 == 3) {
            zzher zzher = new zzher();
            do {
                zzm = zzham.zzm();
                if (zzm == 0 || !zzher.zzm(zzm, zzham)) {
                    zzham.zzz(4 | ((i >>> 3) << 3));
                    zzj(i, zzher);
                }
                zzm = zzham.zzm();
                break;
            } while (!zzher.zzm(zzm, zzham));
            zzham.zzz(4 | ((i >>> 3) << 3));
            zzj(i, zzher);
            return true;
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zzj(i, Integer.valueOf(zzham.zzg()));
                return true;
            }
            throw zzhcd.zza();
        }
    }
}
