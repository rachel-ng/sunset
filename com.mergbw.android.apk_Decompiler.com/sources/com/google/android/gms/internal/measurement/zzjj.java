package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzjj extends zzhu<Float> implements zzjt<Float>, zzlf, RandomAccess {
    private float[] zza;
    private int zzb;

    public final float zzb(int i) {
        zzd(i);
        return this.zza[i];
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.zza[i2]);
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float floatValue = ((Float) obj).floatValue();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.zza[i] == floatValue) {
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
            return new zzjj(Arrays.copyOf(this.zza, i), this.zzb, true);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Float.valueOf(zzb(i));
    }

    public final /* synthetic */ Object remove(int i) {
        zza();
        zzd(i);
        float[] fArr = this.zza;
        float f = fArr[i];
        int i2 = this.zzb;
        if (i < i2 - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (i2 - i) - 1);
        }
        this.zzb--;
        this.modCount++;
        return Float.valueOf(f);
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zza();
        zzd(i);
        float[] fArr = this.zza;
        float f = fArr[i];
        fArr[i] = floatValue;
        return Float.valueOf(f);
    }

    private final String zzc(int i) {
        int i2 = this.zzb;
        return "Index:" + i + ", Size:" + i2;
    }

    static {
        new zzjj(new float[0], 0, false);
    }

    zzjj() {
        this(new float[10], 0, true);
    }

    private zzjj(float[] fArr, int i, boolean z) {
        super(z);
        this.zza = fArr;
        this.zzb = i;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        float floatValue = ((Float) obj).floatValue();
        zza();
        if (i < 0 || i > (i2 = this.zzb)) {
            throw new IndexOutOfBoundsException(zzc(i));
        }
        float[] fArr = this.zza;
        if (i2 < fArr.length) {
            System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
        } else {
            float[] fArr2 = new float[(((i2 * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            System.arraycopy(this.zza, i, fArr2, i + 1, this.zzb - i);
            this.zza = fArr2;
        }
        this.zza[i] = floatValue;
        this.zzb++;
        this.modCount++;
    }

    public final void zza(float f) {
        zza();
        int i = this.zzb;
        float[] fArr = this.zza;
        if (i == fArr.length) {
            float[] fArr2 = new float[(((i * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            this.zza = fArr2;
        }
        float[] fArr3 = this.zza;
        int i2 = this.zzb;
        this.zzb = i2 + 1;
        fArr3[i2] = f;
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
            float[] fArr = this.zza;
            System.arraycopy(fArr, i2, fArr, i, this.zzb - i2);
            this.zzb -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ boolean add(Object obj) {
        zza(((Float) obj).floatValue());
        return true;
    }

    public final boolean addAll(Collection<? extends Float> collection) {
        zza();
        zzjm.zza(collection);
        if (!(collection instanceof zzjj)) {
            return super.addAll(collection);
        }
        zzjj zzjj = (zzjj) collection;
        int i = zzjj.zzb;
        if (i == 0) {
            return false;
        }
        int i2 = this.zzb;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            float[] fArr = this.zza;
            if (i3 > fArr.length) {
                this.zza = Arrays.copyOf(fArr, i3);
            }
            System.arraycopy(zzjj.zza, 0, this.zza, this.zzb, zzjj.zzb);
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
        if (!(obj instanceof zzjj)) {
            return super.equals(obj);
        }
        zzjj zzjj = (zzjj) obj;
        if (this.zzb != zzjj.zzb) {
            return false;
        }
        float[] fArr = zzjj.zza;
        for (int i = 0; i < this.zzb; i++) {
            if (Float.floatToIntBits(this.zza[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }
}
