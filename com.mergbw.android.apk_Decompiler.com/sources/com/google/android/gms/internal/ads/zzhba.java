package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhba extends zzhaz {
    zzhba() {
    }

    /* access modifiers changed from: package-private */
    public final int zza(Map.Entry entry) {
        return ((zzhbl) entry.getKey()).zzb;
    }

    /* access modifiers changed from: package-private */
    public final zzhbd zzb(Object obj) {
        return ((zzhbk) obj).zza;
    }

    /* access modifiers changed from: package-private */
    public final zzhbd zzc(Object obj) {
        zzhbk zzhbk = (zzhbk) obj;
        throw null;
    }

    /* access modifiers changed from: package-private */
    public final Object zzd(zzhay zzhay, zzhde zzhde, int i) {
        return zzhay.zzc(zzhde, i);
    }

    /* access modifiers changed from: package-private */
    public final void zzf(Object obj) {
        ((zzhbk) obj).zza.zzi();
    }

    /* access modifiers changed from: package-private */
    public final void zzg(zzhdr zzhdr, Object obj, zzhay zzhay, zzhbd zzhbd) throws IOException {
        zzhbm zzhbm = (zzhbm) obj;
        zzhbd.zzk(zzhbm.zzd, zzhdr.zzs(zzhbm.zzc.getClass(), zzhay));
    }

    /* access modifiers changed from: package-private */
    public final void zzh(zzhac zzhac, Object obj, zzhay zzhay, zzhbd zzhbd) throws IOException {
        zzhbm zzhbm = (zzhbm) obj;
        zzhdd zzcY = zzhbm.zzc.zzcY();
        zzham zzl = zzhac.zzl();
        zzcY.zzaW(zzl, zzhay);
        zzhbd.zzk(zzhbm.zzd, zzcY.zzbs());
        zzl.zzz(0);
    }

    /* access modifiers changed from: package-private */
    public final void zzi(zzhfi zzhfi, Map.Entry entry) throws IOException {
        zzhbl zzhbl = (zzhbl) entry.getKey();
        if (zzhbl.zzd) {
            zzhfg zzhfg = zzhfg.DOUBLE;
            switch (zzhbl.zzc.ordinal()) {
                case 0:
                    zzheb.zzv(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhbl.zze);
                    return;
                case 1:
                    zzheb.zzz(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhbl.zze);
                    return;
                case 2:
                    zzheb.zzC(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhbl.zze);
                    return;
                case 3:
                    zzheb.zzK(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhbl.zze);
                    return;
                case 4:
                    zzheb.zzB(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhbl.zze);
                    return;
                case 5:
                    zzheb.zzy(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhbl.zze);
                    return;
                case 6:
                    zzheb.zzx(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhbl.zze);
                    return;
                case 7:
                    zzheb.zzt(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhbl.zze);
                    return;
                case 8:
                    zzheb.zzI(zzhbl.zzb, (List) entry.getValue(), zzhfi);
                    return;
                case 9:
                    List list = (List) entry.getValue();
                    if (list != null && !list.isEmpty()) {
                        zzheb.zzA(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhdo.zza().zzb(list.get(0).getClass()));
                        return;
                    }
                    return;
                case 10:
                    List list2 = (List) entry.getValue();
                    if (list2 != null && !list2.isEmpty()) {
                        zzheb.zzD(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhdo.zza().zzb(list2.get(0).getClass()));
                        return;
                    }
                    return;
                case 11:
                    zzheb.zzu(zzhbl.zzb, (List) entry.getValue(), zzhfi);
                    return;
                case 12:
                    zzheb.zzJ(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhbl.zze);
                    return;
                case 13:
                    zzheb.zzB(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhbl.zze);
                    return;
                case 14:
                    zzheb.zzE(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhbl.zze);
                    return;
                case 15:
                    zzheb.zzF(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhbl.zze);
                    return;
                case 16:
                    zzheb.zzG(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhbl.zze);
                    return;
                case 17:
                    zzheb.zzH(zzhbl.zzb, (List) entry.getValue(), zzhfi, zzhbl.zze);
                    return;
                default:
                    return;
            }
        } else {
            zzhfg zzhfg2 = zzhfg.DOUBLE;
            switch (zzhbl.zzc.ordinal()) {
                case 0:
                    zzhfi.zzf(zzhbl.zzb, ((Double) entry.getValue()).doubleValue());
                    return;
                case 1:
                    zzhfi.zzo(zzhbl.zzb, ((Float) entry.getValue()).floatValue());
                    return;
                case 2:
                    zzhfi.zzt(zzhbl.zzb, ((Long) entry.getValue()).longValue());
                    return;
                case 3:
                    zzhfi.zzK(zzhbl.zzb, ((Long) entry.getValue()).longValue());
                    return;
                case 4:
                    zzhfi.zzr(zzhbl.zzb, ((Integer) entry.getValue()).intValue());
                    return;
                case 5:
                    zzhfi.zzm(zzhbl.zzb, ((Long) entry.getValue()).longValue());
                    return;
                case 6:
                    zzhfi.zzk(zzhbl.zzb, ((Integer) entry.getValue()).intValue());
                    return;
                case 7:
                    zzhfi.zzb(zzhbl.zzb, ((Boolean) entry.getValue()).booleanValue());
                    return;
                case 8:
                    zzhfi.zzG(zzhbl.zzb, (String) entry.getValue());
                    return;
                case 9:
                    zzhfi.zzq(zzhbl.zzb, entry.getValue(), zzhdo.zza().zzb(entry.getValue().getClass()));
                    return;
                case 10:
                    zzhfi.zzv(zzhbl.zzb, entry.getValue(), zzhdo.zza().zzb(entry.getValue().getClass()));
                    return;
                case 11:
                    zzhfi.zzd(zzhbl.zzb, (zzhac) entry.getValue());
                    return;
                case 12:
                    zzhfi.zzI(zzhbl.zzb, ((Integer) entry.getValue()).intValue());
                    return;
                case 13:
                    zzhfi.zzr(zzhbl.zzb, ((Integer) entry.getValue()).intValue());
                    return;
                case 14:
                    zzhfi.zzx(zzhbl.zzb, ((Integer) entry.getValue()).intValue());
                    return;
                case 15:
                    zzhfi.zzz(zzhbl.zzb, ((Long) entry.getValue()).longValue());
                    return;
                case 16:
                    zzhfi.zzB(zzhbl.zzb, ((Integer) entry.getValue()).intValue());
                    return;
                case 17:
                    zzhfi.zzD(zzhbl.zzb, ((Long) entry.getValue()).longValue());
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzj(zzhde zzhde) {
        return zzhde instanceof zzhbk;
    }

    /* access modifiers changed from: package-private */
    public final Object zze(Object obj, zzhdr zzhdr, Object obj2, zzhay zzhay, zzhbd zzhbd, Object obj3, zzheq zzheq) throws IOException {
        Object obj4;
        Object zzf;
        ArrayList arrayList;
        zzhbm zzhbm = (zzhbm) obj2;
        zzhbl zzhbl = zzhbm.zzd;
        boolean z = zzhbl.zzd;
        int i = zzhbl.zzb;
        if (!z || !zzhbl.zze) {
            if (zzhbl.zzc != zzhfg.ENUM) {
                switch (zzhbm.zzd.zzc.ordinal()) {
                    case 0:
                        obj4 = Double.valueOf(zzhdr.zza());
                        break;
                    case 1:
                        obj4 = Float.valueOf(zzhdr.zzb());
                        break;
                    case 2:
                        obj4 = Long.valueOf(zzhdr.zzl());
                        break;
                    case 3:
                        obj4 = Long.valueOf(zzhdr.zzo());
                        break;
                    case 4:
                        obj4 = Integer.valueOf(zzhdr.zzg());
                        break;
                    case 5:
                        obj4 = Long.valueOf(zzhdr.zzk());
                        break;
                    case 6:
                        obj4 = Integer.valueOf(zzhdr.zzf());
                        break;
                    case 7:
                        obj4 = Boolean.valueOf(zzhdr.zzP());
                        break;
                    case 8:
                        obj4 = zzhdr.zzt();
                        break;
                    case 9:
                        zzhbl zzhbl2 = zzhbm.zzd;
                        if (!zzhbl2.zzd) {
                            Object zzf2 = zzhbd.zzf(zzhbl2);
                            if (zzf2 instanceof zzhbo) {
                                zzhdz zzb = zzhdo.zza().zzb(zzf2.getClass());
                                if (!((zzhbo) zzf2).zzce()) {
                                    Object zze = zzb.zze();
                                    zzb.zzg(zze, zzf2);
                                    zzhbd.zzk(zzhbm.zzd, zze);
                                    zzf2 = zze;
                                }
                                zzhdr.zzv(zzf2, zzb, zzhay);
                                return obj3;
                            }
                        }
                        obj4 = zzhdr.zzr(zzhbm.zzc.getClass(), zzhay);
                        break;
                    case 10:
                        zzhbl zzhbl3 = zzhbm.zzd;
                        if (!zzhbl3.zzd) {
                            Object zzf3 = zzhbd.zzf(zzhbl3);
                            if (zzf3 instanceof zzhbo) {
                                zzhdz zzb2 = zzhdo.zza().zzb(zzf3.getClass());
                                if (!((zzhbo) zzf3).zzce()) {
                                    Object zze2 = zzb2.zze();
                                    zzb2.zzg(zze2, zzf3);
                                    zzhbd.zzk(zzhbm.zzd, zze2);
                                    zzf3 = zze2;
                                }
                                zzhdr.zzw(zzf3, zzb2, zzhay);
                                return obj3;
                            }
                        }
                        obj4 = zzhdr.zzs(zzhbm.zzc.getClass(), zzhay);
                        break;
                    case 11:
                        obj4 = zzhdr.zzp();
                        break;
                    case 12:
                        obj4 = Integer.valueOf(zzhdr.zzj());
                        break;
                    case 13:
                        throw new IllegalStateException("Shouldn't reach here.");
                    case 14:
                        obj4 = Integer.valueOf(zzhdr.zzh());
                        break;
                    case 15:
                        obj4 = Long.valueOf(zzhdr.zzm());
                        break;
                    case 16:
                        obj4 = Integer.valueOf(zzhdr.zzi());
                        break;
                    case 17:
                        obj4 = Long.valueOf(zzhdr.zzn());
                        break;
                    default:
                        obj4 = null;
                        break;
                }
            } else {
                int zzg = zzhdr.zzg();
                if (zzhbm.zzd.zza.zza(zzg) == null) {
                    return zzheb.zzp(obj, i, zzg, obj3, zzheq);
                }
                obj4 = Integer.valueOf(zzg);
            }
            zzhbl zzhbl4 = zzhbm.zzd;
            if (zzhbl4.zzd) {
                zzhbd.zzh(zzhbl4, obj4);
            } else {
                int ordinal = zzhbl4.zzc.ordinal();
                if ((ordinal == 9 || ordinal == 10) && (zzf = zzhbd.zzf(zzhbm.zzd)) != null) {
                    byte[] bArr = zzhcb.zzd;
                    obj4 = ((zzhde) zzf).zzcZ().zzaS((zzhde) obj4).zzbs();
                }
                zzhbd.zzk(zzhbm.zzd, obj4);
            }
        } else {
            zzhfg zzhfg = zzhfg.DOUBLE;
            switch (zzhbm.zzd.zzc.ordinal()) {
                case 0:
                    arrayList = new ArrayList();
                    zzhdr.zzz(arrayList);
                    break;
                case 1:
                    arrayList = new ArrayList();
                    zzhdr.zzD(arrayList);
                    break;
                case 2:
                    arrayList = new ArrayList();
                    zzhdr.zzG(arrayList);
                    break;
                case 3:
                    arrayList = new ArrayList();
                    zzhdr.zzO(arrayList);
                    break;
                case 4:
                    arrayList = new ArrayList();
                    zzhdr.zzF(arrayList);
                    break;
                case 5:
                    arrayList = new ArrayList();
                    zzhdr.zzC(arrayList);
                    break;
                case 6:
                    arrayList = new ArrayList();
                    zzhdr.zzB(arrayList);
                    break;
                case 7:
                    arrayList = new ArrayList();
                    zzhdr.zzx(arrayList);
                    break;
                case 12:
                    arrayList = new ArrayList();
                    zzhdr.zzN(arrayList);
                    break;
                case 13:
                    ArrayList arrayList2 = new ArrayList();
                    zzhdr.zzA(arrayList2);
                    zzhbl zzhbl5 = zzhbm.zzd;
                    int i2 = zzheb.zza;
                    zzhbt zzhbt = zzhbl5.zza;
                    if (zzhbt != null) {
                        int size = arrayList2.size();
                        int i3 = 0;
                        for (int i4 = 0; i4 < size; i4++) {
                            Integer num = (Integer) arrayList2.get(i4);
                            int intValue = num.intValue();
                            if (zzhbt.zza(intValue) != null) {
                                if (i4 != i3) {
                                    arrayList2.set(i3, num);
                                }
                                i3++;
                            } else {
                                obj3 = zzheb.zzp(obj, i, intValue, obj3, zzheq);
                            }
                        }
                        if (i3 != size) {
                            arrayList2.subList(i3, size).clear();
                        }
                    }
                    arrayList = arrayList2;
                    break;
                case 14:
                    arrayList = new ArrayList();
                    zzhdr.zzI(arrayList);
                    break;
                case 15:
                    arrayList = new ArrayList();
                    zzhdr.zzJ(arrayList);
                    break;
                case 16:
                    arrayList = new ArrayList();
                    zzhdr.zzK(arrayList);
                    break;
                case 17:
                    arrayList = new ArrayList();
                    zzhdr.zzL(arrayList);
                    break;
                default:
                    throw new IllegalStateException("Type cannot be packed: ".concat(String.valueOf(String.valueOf(zzhbm.zzd.zzc))));
            }
            zzhbd.zzk(zzhbm.zzd, arrayList);
        }
        return obj3;
    }
}
