package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzln {
    private static final zzmf<?, ?> zza = new zzmh();

    static int zza(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzit.zzb(i, true);
    }

    static int zza(List<?> list) {
        return list.size();
    }

    static int zza(int i, List<zzia> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzi = size * zzit.zzi(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzi += zzit.zzb(list.get(i2));
        }
        return zzi;
    }

    static int zzb(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzit.zzi(i));
    }

    static int zzb(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjn) {
            zzjn zzjn = (zzjn) list;
            i = 0;
            while (i2 < size) {
                i += zzit.zzd(zzjn.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzit.zzd(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzc(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzit.zzf(i, 0);
    }

    static int zzc(List<?> list) {
        return list.size() << 2;
    }

    static int zzd(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzit.zzc(i, 0);
    }

    static int zzd(List<?> list) {
        return list.size() << 3;
    }

    static int zza(int i, List<zzkt> list, zzll zzll) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzit.zzb(i, list.get(i3), zzll);
        }
        return i2;
    }

    static int zze(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzit.zzi(i));
    }

    static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjn) {
            zzjn zzjn = (zzjn) list;
            i = 0;
            while (i2 < size) {
                i += zzit.zzf(zzjn.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzit.zzf(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzf(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzf(list) + (list.size() * zzit.zzi(i));
    }

    static int zzf(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzke) {
            zzke zzke = (zzke) list;
            i = 0;
            while (i2 < size) {
                i += zzit.zzd(zzke.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzit.zzd(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zza(int i, Object obj, zzll zzll) {
        if (obj instanceof zzkb) {
            return zzit.zzb(i, (zzkb) obj);
        }
        return zzit.zzc(i, (zzkt) obj, zzll);
    }

    static int zzb(int i, List<?> list, zzll zzll) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzi = zzit.zzi(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzkb) {
                i2 = zzit.zza((zzkb) obj);
            } else {
                i2 = zzit.zza((zzkt) obj, zzll);
            }
            zzi += i2;
        }
        return zzi;
    }

    static int zzg(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzit.zzi(i));
    }

    static int zzg(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjn) {
            zzjn zzjn = (zzjn) list;
            i = 0;
            while (i2 < size) {
                i += zzit.zzh(zzjn.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzit.zzh(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzh(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzh(list) + (size * zzit.zzi(i));
    }

    static int zzh(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzke) {
            zzke zzke = (zzke) list;
            i = 0;
            while (i2 < size) {
                i += zzit.zzf(zzke.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzit.zzf(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzb(int i, List<?> list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzi = zzit.zzi(i) * size;
        if (list instanceof zzka) {
            zzka zzka = (zzka) list;
            while (i4 < size) {
                Object zza2 = zzka.zza(i4);
                if (zza2 instanceof zzia) {
                    i3 = zzit.zzb((zzia) zza2);
                } else {
                    i3 = zzit.zzb((String) zza2);
                }
                zzi += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzia) {
                    i2 = zzit.zzb((zzia) obj);
                } else {
                    i2 = zzit.zzb((String) obj);
                }
                zzi += i2;
                i4++;
            }
        }
        return zzi;
    }

    static int zzi(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzi(list) + (size * zzit.zzi(i));
    }

    static int zzi(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjn) {
            zzjn zzjn = (zzjn) list;
            i = 0;
            while (i2 < size) {
                i += zzit.zzj(zzjn.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzit.zzj(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzj(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzj(list) + (size * zzit.zzi(i));
    }

    static int zzj(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzke) {
            zzke zzke = (zzke) list;
            i = 0;
            while (i2 < size) {
                i += zzit.zzg(zzke.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzit.zzg(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzmf<?, ?> zza() {
        return zza;
    }

    static <UT, UB> UB zza(Object obj, int i, List<Integer> list, zzjo zzjo, UB ub, zzmf<UT, UB> zzmf) {
        if (zzjo == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                Integer num = list.get(i3);
                int intValue = num.intValue();
                if (zzjo.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, num);
                    }
                    i2++;
                } else {
                    ub = zza(obj, i, intValue, ub, zzmf);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzjo.zza(intValue2)) {
                    ub = zza(obj, i, intValue2, ub, zzmf);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB zza(Object obj, int i, int i2, UB ub, zzmf<UT, UB> zzmf) {
        if (ub == null) {
            ub = zzmf.zzc(obj);
        }
        zzmf.zzb(ub, i, (long) i2);
        return ub;
    }

    static <T, FT extends zzjf<FT>> void zza(zziz<FT> zziz, T t, T t2) {
        zzjd<FT> zza2 = zziz.zza((Object) t2);
        if (!zza2.zza.isEmpty()) {
            zziz.zzb(t).zza(zza2);
        }
    }

    static <T> void zza(zzkm zzkm, T t, T t2, long j) {
        zzmg.zza((Object) t, j, zzkm.zza(zzmg.zze(t, j), zzmg.zze(t2, j)));
    }

    static <T, UT, UB> void zza(zzmf<UT, UB> zzmf, T t, T t2) {
        zzmf.zzc(t, zzmf.zza(zzmf.zzd(t), zzmf.zzd(t2)));
    }

    public static void zza(Class<?> cls) {
        zzjk.class.isAssignableFrom(cls);
    }

    public static void zza(int i, List<Boolean> list, zzna zzna, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zza(i, list, z);
        }
    }

    public static void zza(int i, List<zzia> list, zzna zzna) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zza(i, list);
        }
    }

    public static void zzb(int i, List<Double> list, zzna zzna, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zzb(i, list, z);
        }
    }

    public static void zzc(int i, List<Integer> list, zzna zzna, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Integer> list, zzna zzna, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzna zzna, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zze(i, list, z);
        }
    }

    public static void zzf(int i, List<Float> list, zzna zzna, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zzf(i, list, z);
        }
    }

    public static void zza(int i, List<?> list, zzna zzna, zzll zzll) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zza(i, list, zzll);
        }
    }

    public static void zzg(int i, List<Integer> list, zzna zzna, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zzg(i, list, z);
        }
    }

    public static void zzh(int i, List<Long> list, zzna zzna, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zzh(i, list, z);
        }
    }

    public static void zzb(int i, List<?> list, zzna zzna, zzll zzll) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zzb(i, list, zzll);
        }
    }

    public static void zzi(int i, List<Integer> list, zzna zzna, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zzi(i, list, z);
        }
    }

    public static void zzj(int i, List<Long> list, zzna zzna, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zzj(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzna zzna, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zzk(i, list, z);
        }
    }

    public static void zzl(int i, List<Long> list, zzna zzna, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zzl(i, list, z);
        }
    }

    public static void zzb(int i, List<String> list, zzna zzna) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zzb(i, list);
        }
    }

    public static void zzm(int i, List<Integer> list, zzna zzna, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zzm(i, list, z);
        }
    }

    public static void zzn(int i, List<Long> list, zzna zzna, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzna.zzn(i, list, z);
        }
    }

    static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }
}
