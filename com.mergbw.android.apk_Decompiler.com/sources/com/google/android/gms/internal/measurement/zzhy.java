package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzhy extends zzhu<Boolean> implements zzjt<Boolean>, zzlf, RandomAccess {
    private boolean[] zza;
    private int zzb;

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            i = (i * 31) + zzjm.zza(this.zza[i2]);
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Boolean)) {
            return -1;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.zza[i] == booleanValue) {
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
            return new zzhy(Arrays.copyOf(this.zza, i), this.zzb, true);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Boolean.valueOf(zzb(i));
    }

    public final /* synthetic */ Object remove(int i) {
        zza();
        zzd(i);
        boolean[] zArr = this.zza;
        boolean z = zArr[i];
        int i2 = this.zzb;
        if (i < i2 - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (i2 - i) - 1);
        }
        this.zzb--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zza();
        zzd(i);
        boolean[] zArr = this.zza;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    private final String zzc(int i) {
        int i2 = this.zzb;
        return "Index:" + i + ", Size:" + i2;
    }

    static {
        new zzhy(new boolean[0], 0, false);
    }

    zzhy() {
        this(new boolean[10], 0, true);
    }

    private zzhy(boolean[] zArr, int i, boolean z) {
        super(z);
        this.zza = zArr;
        this.zzb = i;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zza();
        if (i < 0 || i > (i2 = this.zzb)) {
            throw new IndexOutOfBoundsException(zzc(i));
        }
        boolean[] zArr = this.zza;
        if (i2 < zArr.length) {
            System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
        } else {
            boolean[] zArr2 = new boolean[(((i2 * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            System.arraycopy(this.zza, i, zArr2, i + 1, this.zzb - i);
            this.zza = zArr2;
        }
        this.zza[i] = booleanValue;
        this.zzb++;
        this.modCount++;
    }

    public final void zza(boolean z) {
        zza();
        int i = this.zzb;
        boolean[] zArr = this.zza;
        if (i == zArr.length) {
            boolean[] zArr2 = new boolean[(((i * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            this.zza = zArr2;
        }
        boolean[] zArr3 = this.zza;
        int i2 = this.zzb;
        this.zzb = i2 + 1;
        zArr3[i2] = z;
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
            boolean[] zArr = this.zza;
            System.arraycopy(zArr, i2, zArr, i, this.zzb - i2);
            this.zzb -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ boolean add(Object obj) {
        zza(((Boolean) obj).booleanValue());
        return true;
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zza();
        zzjm.zza(collection);
        if (!(collection instanceof zzhy)) {
            return super.addAll(collection);
        }
        zzhy zzhy = (zzhy) collection;
        int i = zzhy.zzb;
        if (i == 0) {
            return false;
        }
        int i2 = this.zzb;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            boolean[] zArr = this.zza;
            if (i3 > zArr.length) {
                this.zza = Arrays.copyOf(zArr, i3);
            }
            System.arraycopy(zzhy.zza, 0, this.zza, this.zzb, zzhy.zzb);
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
        if (!(obj instanceof zzhy)) {
            return super.equals(obj);
        }
        zzhy zzhy = (zzhy) obj;
        if (this.zzb != zzhy.zzb) {
            return false;
        }
        boolean[] zArr = zzhy.zza;
        for (int i = 0; i < this.zzb; i++) {
            if (this.zza[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final boolean zzb(int i) {
        zzd(i);
        return this.zza[i];
    }
}
