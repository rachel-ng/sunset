package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
public final class zzme {
    private static final zzme zza = new zzme(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    public final int zza() {
        int i;
        int i2 = this.zze;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.zzb; i4++) {
            int i5 = this.zzc[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 == 0) {
                i = zzit.zzg(i6, ((Long) this.zzd[i4]).longValue());
            } else if (i7 == 1) {
                i = zzit.zzc(i6, ((Long) this.zzd[i4]).longValue());
            } else if (i7 == 2) {
                i = zzit.zzc(i6, (zzia) this.zzd[i4]);
            } else if (i7 == 3) {
                i = (zzit.zzi(i6) << 1) + ((zzme) this.zzd[i4]).zza();
            } else if (i7 == 5) {
                i = zzit.zzf(i6, ((Integer) this.zzd[i4]).intValue());
            } else {
                throw new IllegalStateException(zzjs.zza());
            }
            i3 += i;
        }
        this.zze = i3;
        return i3;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            i2 += zzit.zzd(this.zzc[i3] >>> 3, (zzia) this.zzd[i3]);
        }
        this.zze = i2;
        return i2;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzc;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public static zzme zzc() {
        return zza;
    }

    /* access modifiers changed from: package-private */
    public final zzme zza(zzme zzme) {
        if (zzme.equals(zza)) {
            return this;
        }
        zzf();
        int i = this.zzb + zzme.zzb;
        zza(i);
        System.arraycopy(zzme.zzc, 0, this.zzc, this.zzb, zzme.zzb);
        System.arraycopy(zzme.zzd, 0, this.zzd, this.zzb, zzme.zzb);
        this.zzb = i;
        return this;
    }

    static zzme zza(zzme zzme, zzme zzme2) {
        int i = zzme.zzb + zzme2.zzb;
        int[] copyOf = Arrays.copyOf(zzme.zzc, i);
        System.arraycopy(zzme2.zzc, 0, copyOf, zzme.zzb, zzme2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzme.zzd, i);
        System.arraycopy(zzme2.zzd, 0, copyOf2, zzme.zzb, zzme2.zzb);
        return new zzme(i, copyOf, copyOf2, true);
    }

    static zzme zzd() {
        return new zzme();
    }

    private zzme() {
        this(0, new int[8], new Object[8], true);
    }

    private zzme(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    private final void zzf() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    private final void zza(int i) {
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

    public final void zze() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzku.zza(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(int i, Object obj) {
        zzf();
        zza(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzna zzna) throws IOException {
        if (zzna.zza() == 2) {
            for (int i = this.zzb - 1; i >= 0; i--) {
                zzna.zza(this.zzc[i] >>> 3, this.zzd[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzna.zza(this.zzc[i2] >>> 3, this.zzd[i2]);
        }
    }

    private static void zza(int i, Object obj, zzna zzna) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zzna.zzb(i2, ((Long) obj).longValue());
        } else if (i3 == 1) {
            zzna.zza(i2, ((Long) obj).longValue());
        } else if (i3 == 2) {
            zzna.zza(i2, (zzia) obj);
        } else if (i3 != 3) {
            if (i3 == 5) {
                zzna.zzb(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzjs.zza());
        } else if (zzna.zza() == 1) {
            zzna.zzb(i2);
            ((zzme) obj).zzb(zzna);
            zzna.zza(i2);
        } else {
            zzna.zza(i2);
            ((zzme) obj).zzb(zzna);
            zzna.zzb(i2);
        }
    }

    public final void zzb(zzna zzna) throws IOException {
        if (this.zzb != 0) {
            if (zzna.zza() == 1) {
                for (int i = 0; i < this.zzb; i++) {
                    zza(this.zzc[i], this.zzd[i], zzna);
                }
                return;
            }
            for (int i2 = this.zzb - 1; i2 >= 0; i2--) {
                zza(this.zzc[i2], this.zzd[i2], zzna);
            }
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzme)) {
            return false;
        }
        zzme zzme = (zzme) obj;
        int i = this.zzb;
        if (i == zzme.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzme.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzme.zzd;
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
}
