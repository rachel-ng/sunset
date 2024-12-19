package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhbd {
    private static final zzhbd zzb = new zzhbd(true);
    final zzhem zza = new zzhec(16);
    private boolean zzc;
    private boolean zzd;

    private zzhbd() {
    }

    static int zza(zzhfg zzhfg, int i, Object obj) {
        int zzD = zzhat.zzD(i << 3);
        if (zzhfg == zzhfg.GROUP) {
            zzhde zzhde = (zzhde) obj;
            byte[] bArr = zzhcb.zzd;
            if (!(zzhde instanceof zzgzj)) {
                zzD += zzD;
            } else {
                zzgzj zzgzj = (zzgzj) zzhde;
                throw null;
            }
        }
        return zzD + zzb(zzhfg, obj);
    }

    static int zzb(zzhfg zzhfg, Object obj) {
        int zzd2;
        int zzD;
        zzhfg zzhfg2 = zzhfg.DOUBLE;
        zzhfh zzhfh = zzhfh.INT;
        switch (zzhfg.ordinal()) {
            case 0:
                ((Double) obj).doubleValue();
                int i = zzhat.zzf;
                return 8;
            case 1:
                ((Float) obj).floatValue();
                int i2 = zzhat.zzf;
                return 4;
            case 2:
                return zzhat.zzE(((Long) obj).longValue());
            case 3:
                return zzhat.zzE(((Long) obj).longValue());
            case 4:
                return zzhat.zzE((long) ((Integer) obj).intValue());
            case 5:
                ((Long) obj).longValue();
                int i3 = zzhat.zzf;
                return 8;
            case 6:
                ((Integer) obj).intValue();
                int i4 = zzhat.zzf;
                return 4;
            case 7:
                ((Boolean) obj).booleanValue();
                int i5 = zzhat.zzf;
                return 1;
            case 8:
                if (obj instanceof zzhac) {
                    int i6 = zzhat.zzf;
                    zzd2 = ((zzhac) obj).zzd();
                    zzD = zzhat.zzD(zzd2);
                    break;
                } else {
                    return zzhat.zzC((String) obj);
                }
            case 9:
                int i7 = zzhat.zzf;
                return ((zzhde) obj).zzaY();
            case 10:
                if (obj instanceof zzhcj) {
                    int i8 = zzhat.zzf;
                    zzd2 = ((zzhcj) obj).zza();
                    zzD = zzhat.zzD(zzd2);
                    break;
                } else {
                    return zzhat.zzz((zzhde) obj);
                }
            case 11:
                if (!(obj instanceof zzhac)) {
                    int i9 = zzhat.zzf;
                    zzd2 = ((byte[]) obj).length;
                    zzD = zzhat.zzD(zzd2);
                    break;
                } else {
                    int i10 = zzhat.zzf;
                    zzd2 = ((zzhac) obj).zzd();
                    zzD = zzhat.zzD(zzd2);
                    break;
                }
            case 12:
                return zzhat.zzD(((Integer) obj).intValue());
            case 13:
                if (obj instanceof zzhbs) {
                    return zzhat.zzE((long) ((zzhbs) obj).zza());
                }
                return zzhat.zzE((long) ((Integer) obj).intValue());
            case 14:
                ((Integer) obj).intValue();
                int i11 = zzhat.zzf;
                return 4;
            case 15:
                ((Long) obj).longValue();
                int i12 = zzhat.zzf;
                return 8;
            case 16:
                int intValue = ((Integer) obj).intValue();
                return zzhat.zzD((intValue >> 31) ^ (intValue + intValue));
            case 17:
                long longValue = ((Long) obj).longValue();
                return zzhat.zzE((longValue >> 63) ^ (longValue + longValue));
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
        return zzD + zzd2;
    }

    public static int zzc(zzhbc zzhbc, Object obj) {
        zzhfg zzd2 = zzhbc.zzd();
        int zza2 = zzhbc.zza();
        if (!zzhbc.zzg()) {
            return zza(zzd2, zza2, obj);
        }
        List<Object> list = (List) obj;
        int i = 0;
        if (!zzhbc.zzf()) {
            for (Object zza3 : list) {
                i += zza(zzd2, zza2, zza3);
            }
            return i;
        } else if (list.isEmpty()) {
            return 0;
        } else {
            for (Object zzb2 : list) {
                i += zzb(zzd2, zzb2);
            }
            return zzhat.zzD(zza2 << 3) + i + zzhat.zzD(i);
        }
    }

    public static zzhbd zze() {
        return zzb;
    }

    private static Object zzm(Object obj) {
        if (obj instanceof zzhdj) {
            return ((zzhdj) obj).zzc();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    private final void zzn(Map.Entry entry) {
        Object obj;
        zzhbc zzhbc = (zzhbc) entry.getKey();
        Object value = entry.getValue();
        boolean z = value instanceof zzhcj;
        if (zzhbc.zzg()) {
            if (!z) {
                Object zzf = zzf(zzhbc);
                if (zzf == null) {
                    zzf = new ArrayList();
                }
                for (Object zzm : (List) value) {
                    ((List) zzf).add(zzm(zzm));
                }
                this.zza.put(zzhbc, zzf);
                return;
            }
            throw new IllegalStateException("Lazy fields can not be repeated");
        } else if (zzhbc.zze() == zzhfh.MESSAGE) {
            Object zzf2 = zzf(zzhbc);
            if (zzf2 == null) {
                this.zza.put(zzhbc, zzm(value));
                if (z) {
                    this.zzd = true;
                }
            } else if (!z) {
                if (zzf2 instanceof zzhdj) {
                    obj = zzhbc.zzc((zzhdj) zzf2, (zzhdj) value);
                } else {
                    zzhdd zzcZ = ((zzhde) zzf2).zzcZ();
                    zzhbc.zzb(zzcZ, (zzhde) value);
                    obj = zzcZ.zzbr();
                }
                this.zza.put(zzhbc, obj);
            } else {
                zzhcj zzhcj = (zzhcj) value;
                throw null;
            }
        } else if (!z) {
            this.zza.put(zzhbc, zzm(value));
        } else {
            throw new IllegalStateException("Lazy fields must be message-valued");
        }
    }

    private static boolean zzo(Map.Entry entry) {
        zzhbc zzhbc = (zzhbc) entry.getKey();
        if (zzhbc.zze() != zzhfh.MESSAGE) {
            return true;
        }
        if (!zzhbc.zzg()) {
            return zzp(entry.getValue());
        }
        for (Object zzp : (List) entry.getValue()) {
            if (!zzp(zzp)) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzp(Object obj) {
        if (obj instanceof zzhdf) {
            return ((zzhdf) obj).zzbw();
        }
        if (obj instanceof zzhcj) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    private static final int zzq(Map.Entry entry) {
        int i;
        int zzD;
        int zzD2;
        zzhbc zzhbc = (zzhbc) entry.getKey();
        Object value = entry.getValue();
        if (zzhbc.zze() != zzhfh.MESSAGE || zzhbc.zzg() || zzhbc.zzf()) {
            return zzc(zzhbc, value);
        }
        if (value instanceof zzhcj) {
            int zza2 = ((zzhbc) entry.getKey()).zza();
            int zzD3 = zzhat.zzD(8);
            i = zzD3 + zzD3;
            zzD = zzhat.zzD(16) + zzhat.zzD(zza2);
            int zzD4 = zzhat.zzD(24);
            int zza3 = ((zzhcj) value).zza();
            zzD2 = zzD4 + zzhat.zzD(zza3) + zza3;
        } else {
            int zza4 = ((zzhbc) entry.getKey()).zza();
            int zzD5 = zzhat.zzD(8);
            i = zzD5 + zzD5;
            zzD = zzhat.zzD(16) + zzhat.zzD(zza4);
            zzD2 = zzhat.zzD(24) + zzhat.zzz((zzhde) value);
        }
        return i + zzD + zzD2;
    }

    private static final void zzr(zzhbc zzhbc, Object obj) {
        boolean z;
        zzhfg zzd2 = zzhbc.zzd();
        byte[] bArr = zzhcb.zzd;
        obj.getClass();
        zzhfg zzhfg = zzhfg.DOUBLE;
        zzhfh zzhfh = zzhfh.INT;
        switch (zzd2.zza().ordinal()) {
            case 0:
                z = obj instanceof Integer;
                break;
            case 1:
                z = obj instanceof Long;
                break;
            case 2:
                z = obj instanceof Float;
                break;
            case 3:
                z = obj instanceof Double;
                break;
            case 4:
                z = obj instanceof Boolean;
                break;
            case 5:
                z = obj instanceof String;
                break;
            case 6:
                if ((obj instanceof zzhac) || (obj instanceof byte[])) {
                    return;
                }
            case 7:
                if ((obj instanceof Integer) || (obj instanceof zzhbs)) {
                    return;
                }
            case 8:
                if ((obj instanceof zzhde) || (obj instanceof zzhcj)) {
                    return;
                }
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new Object[]{Integer.valueOf(zzhbc.zza()), zzhbc.zzd().zza(), obj.getClass().getName()}));
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzhbd zzhbd = new zzhbd();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            zzhbd.zzk((zzhbc) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzhbd.zzk((zzhbc) entry.getKey(), entry.getValue());
        }
        zzhbd.zzd = this.zzd;
        return zzhbd;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzhbd)) {
            return false;
        }
        return this.zza.equals(((zzhbd) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzd() {
        int i = 0;
        for (int i2 = 0; i2 < this.zza.zzb(); i2++) {
            i += zzq(this.zza.zzg(i2));
        }
        for (Map.Entry zzq : this.zza.zzc()) {
            i += zzq(zzq);
        }
        return i;
    }

    public final Object zzf(zzhbc zzhbc) {
        Object obj = this.zza.get(zzhbc);
        if (!(obj instanceof zzhcj)) {
            return obj;
        }
        zzhcj zzhcj = (zzhcj) obj;
        throw null;
    }

    public final Iterator zzg() {
        if (this.zzd) {
            return new zzhci(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    public final void zzh(zzhbc zzhbc, Object obj) {
        List list;
        if (((zzhbl) zzhbc).zzd) {
            zzr(zzhbc, obj);
            Object zzf = zzf(zzhbc);
            if (zzf == null) {
                list = new ArrayList();
                this.zza.put(zzhbc, list);
            } else {
                list = (List) zzf;
            }
            list.add(obj);
            return;
        }
        throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
    }

    public final void zzi() {
        if (!this.zzc) {
            for (int i = 0; i < this.zza.zzb(); i++) {
                Map.Entry zzg = this.zza.zzg(i);
                if (zzg.getValue() instanceof zzhbo) {
                    ((zzhbo) zzg.getValue()).zzbV();
                }
            }
            this.zza.zza();
            this.zzc = true;
        }
    }

    public final void zzj(zzhbd zzhbd) {
        for (int i = 0; i < zzhbd.zza.zzb(); i++) {
            zzn(zzhbd.zza.zzg(i));
        }
        for (Map.Entry zzn : zzhbd.zza.zzc()) {
            zzn(zzn);
        }
    }

    public final void zzk(zzhbc zzhbc, Object obj) {
        if (!zzhbc.zzg()) {
            zzr(zzhbc, obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zzr(zzhbc, arrayList.get(i));
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzhcj) {
            this.zzd = true;
        }
        this.zza.put(zzhbc, obj);
    }

    public final boolean zzl() {
        for (int i = 0; i < this.zza.zzb(); i++) {
            if (!zzo(this.zza.zzg(i))) {
                return false;
            }
        }
        for (Map.Entry zzo : this.zza.zzc()) {
            if (!zzo(zzo)) {
                return false;
            }
        }
        return true;
    }

    private zzhbd(boolean z) {
        zzi();
        zzi();
    }
}
