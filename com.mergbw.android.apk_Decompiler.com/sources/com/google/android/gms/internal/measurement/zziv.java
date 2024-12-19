package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zziv extends zzhu<Double> implements zzjt<Double>, zzlf, RandomAccess {
    private double[] zza;
    private int zzb;

    public final double zzb(int i) {
        zzd(i);
        return this.zza[i];
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            i = (i * 31) + zzjm.zza(Double.doubleToLongBits(this.zza[i2]));
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double doubleValue = ((Double) obj).doubleValue();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.zza[i] == doubleValue) {
                return i;
            }
        }
        return -1;
    }

    public final int size() {
        return this.zzb;
    }

    public final /* synthetic */ zzjt zza(int i) {
        if (i >= this.zzb) {
            return new zziv(Arrays.copyOf(this.zza, i), this.zzb, true);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Double.valueOf(zzb(i));
    }

    public final /* synthetic */ Object remove(int i) {
        zza();
        zzd(i);
        double[] dArr = this.zza;
        double d = dArr[i];
        int i2 = this.zzb;
        if (i < i2 - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (i2 - i) - 1);
        }
        this.zzb--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zza();
        zzd(i);
        double[] dArr = this.zza;
        double d = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d);
    }

    private final String zzc(int i) {
        int i2 = this.zzb;
        return "Index:" + i + ", Size:" + i2;
    }

    static {
        new zziv(new double[0], 0, false);
    }

    zziv() {
        this(new double[10], 0, true);
    }

    private zziv(double[] dArr, int i, boolean z) {
        super(z);
        this.zza = dArr;
        this.zzb = i;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        double doubleValue = ((Double) obj).doubleValue();
        zza();
        if (i < 0 || i > (i2 = this.zzb)) {
            throw new IndexOutOfBoundsException(zzc(i));
        }
        double[] dArr = this.zza;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.zza, i, dArr2, i + 1, this.zzb - i);
            this.zza = dArr2;
        }
        this.zza[i] = doubleValue;
        this.zzb++;
        this.modCount++;
    }

    public final void zza(double d) {
        zza();
        int i = this.zzb;
        double[] dArr = this.zza;
        if (i == dArr.length) {
            double[] dArr2 = new double[(((i * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            this.zza = dArr2;
        }
        double[] dArr3 = this.zza;
        int i2 = this.zzb;
        this.zzb = i2 + 1;
        dArr3[i2] = d;
    }

    private final void zzd(int i) {
        if (i < 0 || i >= this.zzb) {
            throw new IndexOutOfBoundsException(zzc(i));
        }
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zza();
        if (i2 >= i) {
            double[] dArr = this.zza;
            System.arraycopy(dArr, i2, dArr, i, this.zzb - i2);
            this.zzb -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ boolean add(Object obj) {
        zza(((Double) obj).doubleValue());
        return true;
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zza();
        zzjm.zza(collection);
        if (!(collection instanceof zziv)) {
            return super.addAll(collection);
        }
        zziv zziv = (zziv) collection;
        int i = zziv.zzb;
        if (i == 0) {
            return false;
        }
        int i2 = this.zzb;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            double[] dArr = this.zza;
            if (i3 > dArr.length) {
                this.zza = Arrays.copyOf(dArr, i3);
            }
            System.arraycopy(zziv.zza, 0, this.zza, this.zzb, zziv.zzb);
            this.zzb = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zziv)) {
            return super.equals(obj);
        }
        zziv zziv = (zziv) obj;
        if (this.zzb != zziv.zzb) {
            return false;
        }
        double[] dArr = zziv.zza;
        for (int i = 0; i < this.zzb; i++) {
            if (Double.doubleToLongBits(this.zza[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }
}
