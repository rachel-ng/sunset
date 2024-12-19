package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
public abstract class zzit extends zzhx {
    private static final Logger zzb = Logger.getLogger(zzit.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzc = zzmg.zzc();
    zziw zza;

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
    private static class zza extends zzit {
        private final byte[] zzb;
        private final int zzc;
        private int zzd;

        public final int zza() {
            return this.zzc - this.zzd;
        }

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            } else if (((bArr.length - i2) | i2) >= 0) {
                this.zzb = bArr;
                this.zzd = 0;
                this.zzc = i2;
            } else {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), 0, Integer.valueOf(i2)}));
            }
        }

        public final void zza(byte b2) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i = this.zzd;
                this.zzd = i + 1;
                bArr[i] = b2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e);
            }
        }

        private final void zzc(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.zzb, this.zzd, i2);
                this.zzd += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), Integer.valueOf(i2)}), e);
            }
        }

        public final void zza(int i, boolean z) throws IOException {
            zzc(i, 0);
            zza(z ? (byte) 1 : 0);
        }

        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            zzc(i2);
            zzc(bArr, 0, i2);
        }

        public final void zza(int i, zzia zzia) throws IOException {
            zzc(i, 2);
            zza(zzia);
        }

        public final void zza(zzia zzia) throws IOException {
            zzc(zzia.zzb());
            zzia.zza((zzhx) this);
        }

        public final void zza(int i, int i2) throws IOException {
            zzc(i, 5);
            zza(i2);
        }

        public final void zza(int i) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i2 = this.zzd;
                int i3 = i2 + 1;
                this.zzd = i3;
                bArr[i2] = (byte) i;
                int i4 = i2 + 2;
                this.zzd = i4;
                bArr[i3] = (byte) (i >> 8);
                int i5 = i2 + 3;
                this.zzd = i5;
                bArr[i4] = (byte) (i >> 16);
                this.zzd = i2 + 4;
                bArr[i5] = (byte) (i >>> 24);
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e);
            }
        }

        public final void zza(int i, long j) throws IOException {
            zzc(i, 1);
            zza(j);
        }

        public final void zza(long j) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i = this.zzd;
                int i2 = i + 1;
                this.zzd = i2;
                bArr[i] = (byte) ((int) j);
                int i3 = i + 2;
                this.zzd = i3;
                bArr[i2] = (byte) ((int) (j >> 8));
                int i4 = i + 3;
                this.zzd = i4;
                bArr[i3] = (byte) ((int) (j >> 16));
                int i5 = i + 4;
                this.zzd = i5;
                bArr[i4] = (byte) ((int) (j >> 24));
                int i6 = i + 5;
                this.zzd = i6;
                bArr[i5] = (byte) ((int) (j >> 32));
                int i7 = i + 6;
                this.zzd = i7;
                bArr[i6] = (byte) ((int) (j >> 40));
                int i8 = i + 7;
                this.zzd = i8;
                bArr[i7] = (byte) ((int) (j >> 48));
                this.zzd = i + 8;
                bArr[i8] = (byte) ((int) (j >> 56));
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e);
            }
        }

        public final void zzb(int i, int i2) throws IOException {
            zzc(i, 0);
            zzb(i2);
        }

        public final void zzb(int i) throws IOException {
            if (i >= 0) {
                zzc(i);
            } else {
                zzb((long) i);
            }
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            zzc(bArr, i, i2);
        }

        /* access modifiers changed from: package-private */
        public final void zza(int i, zzkt zzkt, zzll zzll) throws IOException {
            zzc(i, 2);
            zzc(((zzhq) zzkt).zza(zzll));
            zzll.zza(zzkt, (zzna) this.zza);
        }

        public final void zza(zzkt zzkt) throws IOException {
            zzc(zzkt.zzca());
            zzkt.zza(this);
        }

        public final void zza(int i, zzkt zzkt) throws IOException {
            zzc(1, 3);
            zzd(2, i);
            zzc(3, 2);
            zza(zzkt);
            zzc(1, 4);
        }

        public final void zzb(int i, zzia zzia) throws IOException {
            zzc(1, 3);
            zzd(2, i);
            zza(3, zzia);
            zzc(1, 4);
        }

        public final void zza(int i, String str) throws IOException {
            zzc(i, 2);
            zza(str);
        }

        public final void zza(String str) throws IOException {
            int i = this.zzd;
            try {
                int zzj = zzj(str.length() * 3);
                int zzj2 = zzj(str.length());
                if (zzj2 == zzj) {
                    int i2 = i + zzj2;
                    this.zzd = i2;
                    int zza = zzmk.zza(str, this.zzb, i2, zza());
                    this.zzd = i;
                    zzc((zza - i) - zzj2);
                    this.zzd = zza;
                    return;
                }
                zzc(zzmk.zza(str));
                this.zzd = zzmk.zza(str, this.zzb, this.zzd, zza());
            } catch (zzmo e) {
                this.zzd = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(e2);
            }
        }

        public final void zzc(int i, int i2) throws IOException {
            zzc((i << 3) | i2);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzc(i, 0);
            zzc(i2);
        }

        public final void zzc(int i) throws IOException {
            while ((i & -128) != 0) {
                byte[] bArr = this.zzb;
                int i2 = this.zzd;
                this.zzd = i2 + 1;
                bArr[i2] = (byte) (i | 128);
                i >>>= 7;
            }
            try {
                byte[] bArr2 = this.zzb;
                int i3 = this.zzd;
                this.zzd = i3 + 1;
                bArr2[i3] = (byte) i;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e);
            }
        }

        public final void zzb(int i, long j) throws IOException {
            zzc(i, 0);
            zzb(j);
        }

        public final void zzb(long j) throws IOException {
            if (!zzit.zzc || zza() < 10) {
                while ((j & -128) != 0) {
                    byte[] bArr = this.zzb;
                    int i = this.zzd;
                    this.zzd = i + 1;
                    bArr[i] = (byte) (((int) j) | 128);
                    j >>>= 7;
                }
                try {
                    byte[] bArr2 = this.zzb;
                    int i2 = this.zzd;
                    this.zzd = i2 + 1;
                    bArr2[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.zzb;
                    int i3 = this.zzd;
                    this.zzd = i3 + 1;
                    zzmg.zza(bArr3, (long) i3, (byte) (((int) j) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.zzb;
                int i4 = this.zzd;
                this.zzd = i4 + 1;
                zzmg.zza(bArr4, (long) i4, (byte) ((int) j));
            }
        }
    }

    public static int zza(double d) {
        return 8;
    }

    public static int zza(float f) {
        return 4;
    }

    public static int zza(boolean z) {
        return 1;
    }

    public static int zzc(long j) {
        return 8;
    }

    public static int zze(int i) {
        return 4;
    }

    public static int zze(long j) {
        return 8;
    }

    public static int zzg(int i) {
        return 4;
    }

    private static long zzi(long j) {
        return (j >> 63) ^ (j << 1);
    }

    private static int zzl(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public abstract int zza();

    public abstract void zza(byte b2) throws IOException;

    public abstract void zza(int i) throws IOException;

    public abstract void zza(int i, int i2) throws IOException;

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzia zzia) throws IOException;

    public abstract void zza(int i, zzkt zzkt) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zza(int i, zzkt zzkt, zzll zzll) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(int i, boolean z) throws IOException;

    public abstract void zza(long j) throws IOException;

    public abstract void zza(zzia zzia) throws IOException;

    public abstract void zza(zzkt zzkt) throws IOException;

    public abstract void zza(String str) throws IOException;

    public abstract void zzb(int i) throws IOException;

    public abstract void zzb(int i, int i2) throws IOException;

    public abstract void zzb(int i, long j) throws IOException;

    public abstract void zzb(int i, zzia zzia) throws IOException;

    public abstract void zzb(long j) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zzb(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzc(int i) throws IOException;

    public abstract void zzc(int i, int i2) throws IOException;

    public abstract void zzd(int i, int i2) throws IOException;

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        zzb(String str, Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + str, th);
        }
    }

    public static int zzb(int i, boolean z) {
        return zzj(i << 3) + 1;
    }

    public static int zza(byte[] bArr) {
        int length = bArr.length;
        return zzj(length) + length;
    }

    public static int zzc(int i, zzia zzia) {
        int zzj = zzj(i << 3);
        int zzb2 = zzia.zzb();
        return zzj + zzj(zzb2) + zzb2;
    }

    public static int zzb(zzia zzia) {
        int zzb2 = zzia.zzb();
        return zzj(zzb2) + zzb2;
    }

    public static int zza(int i, double d) {
        return zzj(i << 3) + 8;
    }

    public static int zze(int i, int i2) {
        return zzj(i << 3) + zzg((long) i2);
    }

    public static int zzd(int i) {
        return zzg((long) i);
    }

    public static int zzf(int i, int i2) {
        return zzj(i << 3) + 4;
    }

    public static int zzc(int i, long j) {
        return zzj(i << 3) + 8;
    }

    public static int zza(int i, float f) {
        return zzj(i << 3) + 4;
    }

    @Deprecated
    static int zzb(int i, zzkt zzkt, zzll zzll) {
        return (zzj(i << 3) << 1) + ((zzhq) zzkt).zza(zzll);
    }

    @Deprecated
    public static int zzb(zzkt zzkt) {
        return zzkt.zzca();
    }

    public static int zzg(int i, int i2) {
        return zzj(i << 3) + zzg((long) i2);
    }

    public static int zzf(int i) {
        return zzg((long) i);
    }

    public static int zzd(int i, long j) {
        return zzj(i << 3) + zzg(j);
    }

    public static int zzd(long j) {
        return zzg(j);
    }

    public static int zza(int i, zzkb zzkb) {
        return (zzj(8) << 1) + zzj(2, i) + zzb(3, zzkb);
    }

    public static int zzb(int i, zzkb zzkb) {
        int zzj = zzj(i << 3);
        int zza2 = zzkb.zza();
        return zzj + zzj(zza2) + zza2;
    }

    public static int zza(zzkb zzkb) {
        int zza2 = zzkb.zza();
        return zzj(zza2) + zza2;
    }

    public static int zzb(int i, zzkt zzkt) {
        return (zzj(8) << 1) + zzj(2, i) + zzj(24) + zzc(zzkt);
    }

    static int zzc(int i, zzkt zzkt, zzll zzll) {
        return zzj(i << 3) + zza(zzkt, zzll);
    }

    public static int zzc(zzkt zzkt) {
        int zzca = zzkt.zzca();
        return zzj(zzca) + zzca;
    }

    static int zza(zzkt zzkt, zzll zzll) {
        int zza2 = ((zzhq) zzkt).zza(zzll);
        return zzj(zza2) + zza2;
    }

    public static int zzd(int i, zzia zzia) {
        return (zzj(8) << 1) + zzj(2, i) + zzc(3, zzia);
    }

    public static int zzh(int i, int i2) {
        return zzj(i << 3) + 4;
    }

    public static int zze(int i, long j) {
        return zzj(i << 3) + 8;
    }

    public static int zzi(int i, int i2) {
        return zzj(i << 3) + zzj(zzl(i2));
    }

    public static int zzh(int i) {
        return zzj(zzl(i));
    }

    public static int zzf(int i, long j) {
        return zzj(i << 3) + zzg(zzi(j));
    }

    public static int zzf(long j) {
        return zzg(zzi(j));
    }

    public static int zzb(int i, String str) {
        return zzj(i << 3) + zzb(str);
    }

    public static int zzb(String str) {
        int i;
        try {
            i = zzmk.zza(str);
        } catch (zzmo unused) {
            i = str.getBytes(zzjm.zza).length;
        }
        return zzj(i) + i;
    }

    public static int zzi(int i) {
        return zzj(i << 3);
    }

    public static int zzj(int i, int i2) {
        return zzj(i << 3) + zzj(i2);
    }

    public static int zzj(int i) {
        return (352 - (Integer.numberOfLeadingZeros(i) * 9)) >>> 6;
    }

    public static int zzg(int i, long j) {
        return zzj(i << 3) + zzg(j);
    }

    public static int zzg(long j) {
        return (640 - (Long.numberOfLeadingZeros(j) * 9)) >>> 6;
    }

    public static zzit zzb(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    private zzit() {
    }

    public final void zzb() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, zzmo zzmo) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzmo);
        byte[] bytes = str.getBytes(zzjm.zza);
        try {
            zzc(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzb(e);
        }
    }

    public final void zzb(boolean z) throws IOException {
        zza(z ? (byte) 1 : 0);
    }

    public final void zzb(int i, double d) throws IOException {
        zza(i, Double.doubleToRawLongBits(d));
    }

    public final void zzb(double d) throws IOException {
        zza(Double.doubleToRawLongBits(d));
    }

    public final void zzb(int i, float f) throws IOException {
        zza(i, Float.floatToRawIntBits(f));
    }

    public final void zzb(float f) throws IOException {
        zza(Float.floatToRawIntBits(f));
    }

    public final void zzk(int i, int i2) throws IOException {
        zzd(i, zzl(i2));
    }

    public final void zzk(int i) throws IOException {
        zzc(zzl(i));
    }

    public final void zzh(int i, long j) throws IOException {
        zzb(i, zzi(j));
    }

    public final void zzh(long j) throws IOException {
        zzb(zzi(j));
    }
}
