package com.google.android.gms.internal.ads;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzgbc<E> extends zzgax<E> implements List<E>, RandomAccess {
    private static final zzgdj zza = new zzgba(zzgco.zza, 0);
    public static final /* synthetic */ int zzd = 0;

    zzgbc() {
    }

    public static zzgaz zzi(int i) {
        return new zzgaz(i);
    }

    static zzgbc zzj(Object[] objArr, int i) {
        if (i == 0) {
            return zzgco.zza;
        }
        return new zzgco(objArr, i);
    }

    public static zzgbc zzk(Collection collection) {
        if (collection instanceof zzgax) {
            zzgbc zzd2 = ((zzgax) collection).zzd();
            if (!zzd2.zzf()) {
                return zzd2;
            }
            Object[] array = zzd2.toArray();
            return zzj(array, array.length);
        }
        Object[] array2 = collection.toArray();
        int length = array2.length;
        zzgcm.zzb(array2, length);
        return zzj(array2, length);
    }

    public static zzgbc zzl(Object[] objArr) {
        if (objArr.length == 0) {
            return zzgco.zza;
        }
        Object[] objArr2 = (Object[]) objArr.clone();
        int length = objArr2.length;
        zzgcm.zzb(objArr2, length);
        return zzj(objArr2, length);
    }

    public static zzgbc zzm() {
        return zzgco.zza;
    }

    public static zzgbc zzn(Object obj) {
        Object[] objArr = {obj};
        zzgcm.zzb(objArr, 1);
        return zzj(objArr, 1);
    }

    public static zzgbc zzo(Object obj, Object obj2) {
        Object[] objArr = {obj, obj2};
        zzgcm.zzb(objArr, 2);
        return zzj(objArr, 2);
    }

    public static zzgbc zzp(Object obj, Object obj2, Object obj3) {
        Object[] objArr = {obj, obj2, obj3};
        zzgcm.zzb(objArr, 3);
        return zzj(objArr, 3);
    }

    public static zzgbc zzq(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        Object[] objArr = {obj, obj2, obj3, obj4, obj5};
        zzgcm.zzb(objArr, 5);
        return zzj(objArr, 5);
    }

    public static zzgbc zzr(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Object[] objArr = {"3010", "3008", "1005", "1009", "2011", "2007"};
        zzgcm.zzb(objArr, 6);
        return zzj(objArr, 6);
    }

    public static zzgbc zzs(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        Object[] objArr = {obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9};
        zzgcm.zzb(objArr, 9);
        return zzj(objArr, 9);
    }

    public static zzgbc zzt(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        Object[] objArr = {obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10};
        zzgcm.zzb(objArr, 10);
        return zzj(objArr, 10);
    }

    @Deprecated
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean contains(@CheckForNull Object obj) {
        return indexOf(obj) >= 0;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    int i = 0;
                    while (i < size) {
                        if (zzfya.zza(get(i), list.get(i))) {
                            i++;
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it2.hasNext()) {
                            if (!zzfya.zza(it.next(), it2.next())) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public final int indexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    public final /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    public final int lastIndexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    public final /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Deprecated
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public int zza(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }

    @Deprecated
    public final zzgbc zzd() {
        return this;
    }

    public final zzgdi zze() {
        return listIterator(0);
    }

    /* renamed from: zzh */
    public zzgbc subList(int i, int i2) {
        zzfyg.zzi(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return zzgco.zza;
        }
        return new zzgbb(this, i, i3);
    }

    /* renamed from: zzu */
    public final zzgdj listIterator(int i) {
        zzfyg.zzb(i, size(), FirebaseAnalytics.Param.INDEX);
        if (isEmpty()) {
            return zza;
        }
        return new zzgba(this, i);
    }
}
