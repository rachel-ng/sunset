package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzheb {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zzheq zzc;
    private static final zzheq zzd = new zzhes();

    static {
        Class<?> cls;
        Class<?> cls2;
        zzheq zzheq = null;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zzb = cls;
        try {
            cls2 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused2) {
            cls2 = null;
        }
        if (cls2 != null) {
            try {
                zzheq = (zzheq) cls2.getConstructor((Class[]) null).newInstance((Object[]) null);
            } catch (Throwable unused3) {
            }
        }
        zzc = zzheq;
    }

    public static void zzA(int i, List list, zzhfi zzhfi, zzhdz zzhdz) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((zzhau) zzhfi).zzq(i, list.get(i2), zzhdz);
            }
        }
    }

    public static void zzB(int i, List list, zzhfi zzhfi, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzs(i, list, z);
        }
    }

    public static void zzC(int i, List list, zzhfi zzhfi, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzu(i, list, z);
        }
    }

    public static void zzD(int i, List list, zzhfi zzhfi, zzhdz zzhdz) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((zzhau) zzhfi).zzv(i, list.get(i2), zzhdz);
            }
        }
    }

    public static void zzE(int i, List list, zzhfi zzhfi, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzy(i, list, z);
        }
    }

    public static void zzF(int i, List list, zzhfi zzhfi, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzA(i, list, z);
        }
    }

    public static void zzG(int i, List list, zzhfi zzhfi, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzC(i, list, z);
        }
    }

    public static void zzH(int i, List list, zzhfi zzhfi, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzE(i, list, z);
        }
    }

    public static void zzI(int i, List list, zzhfi zzhfi) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzH(i, list);
        }
    }

    public static void zzJ(int i, List list, zzhfi zzhfi, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzJ(i, list, z);
        }
    }

    public static void zzK(int i, List list, zzhfi zzhfi, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzL(i, list, z);
        }
    }

    static boolean zzL(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null) {
            return obj.equals(obj2);
        }
        return false;
    }

    static int zza(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            i = 0;
            while (i2 < size) {
                i += zzhat.zzE((long) zzhbp.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhat.zzE((long) ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzb(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzhat.zzD(i << 3) + 4);
    }

    static int zzc(List list) {
        return list.size() * 4;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzhat.zzD(i << 3) + 8);
    }

    static int zze(List list) {
        return list.size() * 8;
    }

    static int zzf(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            i = 0;
            while (i2 < size) {
                i += zzhat.zzE((long) zzhbp.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhat.zzE((long) ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzg(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhct) {
            zzhct zzhct = (zzhct) list;
            i = 0;
            while (i2 < size) {
                i += zzhat.zzE(zzhct.zza(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhat.zzE(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzh(int i, Object obj, zzhdz zzhdz) {
        int i2 = i << 3;
        if (!(obj instanceof zzhck)) {
            return zzhat.zzD(i2) + zzhat.zzA((zzhde) obj, zzhdz);
        }
        int zzD = zzhat.zzD(i2);
        int zza2 = ((zzhck) obj).zza();
        return zzD + zzhat.zzD(zza2) + zza2;
    }

    static int zzi(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            i = 0;
            while (i2 < size) {
                int zzd2 = zzhbp.zzd(i2);
                i += zzhat.zzD((zzd2 >> 31) ^ (zzd2 + zzd2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i3 = i + zzhat.zzD((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    static int zzj(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhct) {
            zzhct zzhct = (zzhct) list;
            i = 0;
            while (i2 < size) {
                long zza2 = zzhct.zza(i2);
                i += zzhat.zzE((zza2 >> 63) ^ (zza2 + zza2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i3 = i + zzhat.zzE((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    static int zzk(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            i = 0;
            while (i2 < size) {
                i += zzhat.zzD(zzhbp.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhat.zzD(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzl(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhct) {
            zzhct zzhct = (zzhct) list;
            i = 0;
            while (i2 < size) {
                i += zzhat.zzE(zzhct.zza(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhat.zzE(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzheq zzm() {
        return zzc;
    }

    public static zzheq zzn() {
        return zzd;
    }

    static Object zzo(Object obj, int i, List list, zzhbu zzhbu, Object obj2, zzheq zzheq) {
        if (zzhbu == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                Integer num = (Integer) list.get(i3);
                int intValue = num.intValue();
                if (zzhbu.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, num);
                    }
                    i2++;
                } else {
                    obj2 = zzp(obj, i, intValue, obj2, zzheq);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return obj2;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zzhbu.zza(intValue2)) {
                    obj2 = zzp(obj, i, intValue2, obj2, zzheq);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    static Object zzp(Object obj, int i, int i2, Object obj2, zzheq zzheq) {
        if (obj2 == null) {
            obj2 = zzheq.zzc(obj);
        }
        zzheq.zzl(obj2, i, (long) i2);
        return obj2;
    }

    static void zzq(zzhaz zzhaz, Object obj, Object obj2) {
        zzhbd zzb2 = zzhaz.zzb(obj2);
        if (!zzb2.zza.isEmpty()) {
            zzhaz.zzc(obj).zzj(zzb2);
        }
    }

    static void zzr(zzheq zzheq, Object obj, Object obj2) {
        zzheq.zzo(obj, zzheq.zze(zzheq.zzd(obj), zzheq.zzd(obj2)));
    }

    public static void zzs(Class cls) {
        Class cls2;
        if (!zzhbo.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzt(int i, List list, zzhfi zzhfi, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzc(i, list, z);
        }
    }

    public static void zzu(int i, List list, zzhfi zzhfi) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zze(i, list);
        }
    }

    public static void zzv(int i, List list, zzhfi zzhfi, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzg(i, list, z);
        }
    }

    public static void zzw(int i, List list, zzhfi zzhfi, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzj(i, list, z);
        }
    }

    public static void zzx(int i, List list, zzhfi zzhfi, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzl(i, list, z);
        }
    }

    public static void zzy(int i, List list, zzhfi zzhfi, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzn(i, list, z);
        }
    }

    public static void zzz(int i, List list, zzhfi zzhfi, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhfi.zzp(i, list, z);
        }
    }
}
