package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzgbh<E> extends zzgax<E> implements Set<E> {
    @CheckForNull
    private transient zzgbc zza;

    zzgbh() {
    }

    static int zzh(int i) {
        int max = Math.max(i, 2);
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            do {
                highestOneBit += highestOneBit;
            } while (((double) highestOneBit) * 0.7d < ((double) max));
            return highestOneBit;
        }
        zzfyg.zzf(max < 1073741824, "collection too large");
        return 1073741824;
    }

    public static zzgbg zzj(int i) {
        return new zzgbg(i);
    }

    public static zzgbh zzl(Collection collection) {
        if ((collection instanceof zzgbh) && !(collection instanceof SortedSet)) {
            zzgbh zzgbh = (zzgbh) collection;
            if (!zzgbh.zzf()) {
                return zzgbh;
            }
        }
        Object[] array = collection.toArray();
        return zzv(array.length, array);
    }

    public static zzgbh zzm(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return zzgcu.zza;
        }
        if (length != 1) {
            return zzv(length, (Object[]) objArr.clone());
        }
        return new zzgdf(objArr[0]);
    }

    public static zzgbh zzn() {
        return zzgcu.zza;
    }

    public static zzgbh zzo(Object obj) {
        return new zzgdf(obj);
    }

    public static zzgbh zzp(Object obj, Object obj2) {
        return zzv(2, obj, obj2);
    }

    public static zzgbh zzq(Object obj, Object obj2, Object obj3) {
        return zzv(3, obj, obj2, obj3);
    }

    public static zzgbh zzr(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return zzv(5, obj, obj2, obj3, obj4, obj5);
    }

    @SafeVarargs
    public static zzgbh zzs(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object... objArr) {
        Object[] objArr2 = new Object[12];
        objArr2[0] = obj;
        objArr2[1] = obj2;
        objArr2[2] = obj3;
        objArr2[3] = obj4;
        objArr2[4] = obj5;
        objArr2[5] = obj6;
        System.arraycopy(objArr, 0, objArr2, 6, 6);
        return zzv(12, objArr2);
    }

    /* access modifiers changed from: private */
    public static boolean zzw(int i, int i2) {
        return i < (i2 >> 1) + (i2 >> 2);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgbh) || !zzu() || !((zzgbh) obj).zzu() || hashCode() == obj.hashCode()) {
            return zzgde.zzd(this, obj);
        }
        return false;
    }

    public int hashCode() {
        return zzgde.zza(this);
    }

    public zzgbc zzd() {
        zzgbc zzgbc = this.zza;
        if (zzgbc != null) {
            return zzgbc;
        }
        zzgbc zzi = zzi();
        this.zza = zzi;
        return zzi;
    }

    /* renamed from: zze */
    public abstract zzgdi iterator();

    /* access modifiers changed from: package-private */
    public zzgbc zzi() {
        Object[] array = toArray();
        int i = zzgbc.zzd;
        return zzgbc.zzj(array, array.length);
    }

    /* access modifiers changed from: package-private */
    public boolean zzu() {
        return false;
    }

    /* access modifiers changed from: private */
    public static zzgbh zzv(int i, Object... objArr) {
        if (i == 0) {
            return zzgcu.zza;
        }
        if (i == 1) {
            return new zzgdf(Objects.requireNonNull(objArr[0]));
        }
        int zzh = zzh(i);
        Object[] objArr2 = new Object[zzh];
        int i2 = zzh - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            Object obj = objArr[i5];
            zzgcm.zza(obj, i5);
            int hashCode = obj.hashCode();
            int zza2 = zzgau.zza(hashCode);
            while (true) {
                int i6 = zza2 & i2;
                Object obj2 = objArr2[i6];
                if (obj2 != null) {
                    if (obj2.equals(obj)) {
                        break;
                    }
                    zza2++;
                } else {
                    objArr[i4] = obj;
                    objArr2[i6] = obj;
                    i3 += hashCode;
                    i4++;
                    break;
                }
            }
        }
        Arrays.fill(objArr, i4, i, (Object) null);
        if (i4 == 1) {
            return new zzgdf(Objects.requireNonNull(objArr[0]));
        }
        if (zzh(i4) < zzh / 2) {
            return zzv(i4, objArr);
        }
        if (zzw(i4, objArr.length)) {
            objArr = Arrays.copyOf(objArr, i4);
        }
        return new zzgcu(objArr, i3, objArr2, i2, i4);
    }
}
