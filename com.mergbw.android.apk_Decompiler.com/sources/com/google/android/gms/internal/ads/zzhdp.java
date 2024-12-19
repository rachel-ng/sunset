package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhdp extends zzgzl implements RandomAccess {
    private static final zzhdp zza = new zzhdp(new Object[0], 0, false);
    private Object[] zzb;
    private int zzc;

    zzhdp() {
        this(new Object[10], 0, true);
    }

    public static zzhdp zzd() {
        return zza;
    }

    private final String zze(int i) {
        int i2 = this.zzc;
        return "Index:" + i + ", Size:" + i2;
    }

    private final void zzg(int i) {
        if (i < 0 || i >= this.zzc) {
            throw new IndexOutOfBoundsException(zze(i));
        }
    }

    public final void add(int i, Object obj) {
        int i2;
        zzdJ();
        if (i < 0 || i > (i2 = this.zzc)) {
            throw new IndexOutOfBoundsException(zze(i));
        }
        int i3 = i + 1;
        Object[] objArr = this.zzb;
        if (i2 < objArr.length) {
            System.arraycopy(objArr, i, objArr, i3, i2 - i);
        } else {
            Object[] objArr2 = new Object[(((i2 * 3) / 2) + 1)];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(this.zzb, i, objArr2, i3, this.zzc - i);
            this.zzb = objArr2;
        }
        this.zzb[i] = obj;
        this.zzc++;
        this.modCount++;
    }

    public final Object get(int i) {
        zzg(i);
        return this.zzb[i];
    }

    public final Object remove(int i) {
        zzdJ();
        zzg(i);
        Object[] objArr = this.zzb;
        Object obj = objArr[i];
        int i2 = this.zzc;
        if (i < i2 - 1) {
            System.arraycopy(objArr, i + 1, objArr, i, (i2 - i) - 1);
        }
        this.zzc--;
        this.modCount++;
        return obj;
    }

    public final Object set(int i, Object obj) {
        zzdJ();
        zzg(i);
        Object[] objArr = this.zzb;
        Object obj2 = objArr[i];
        objArr[i] = obj;
        this.modCount++;
        return obj2;
    }

    public final int size() {
        return this.zzc;
    }

    public final /* bridge */ /* synthetic */ zzhca zzf(int i) {
        if (i >= this.zzc) {
            return new zzhdp(Arrays.copyOf(this.zzb, i), this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    private zzhdp(Object[] objArr, int i, boolean z) {
        super(z);
        this.zzb = objArr;
        this.zzc = i;
    }

    public final boolean add(Object obj) {
        zzdJ();
        int i = this.zzc;
        Object[] objArr = this.zzb;
        if (i == objArr.length) {
            this.zzb = Arrays.copyOf(objArr, ((i * 3) / 2) + 1);
        }
        Object[] objArr2 = this.zzb;
        int i2 = this.zzc;
        this.zzc = i2 + 1;
        objArr2[i2] = obj;
        this.modCount++;
        return true;
    }
}
