package com.google.android.gms.measurement.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzjk;
import com.google.android.gms.internal.measurement.zzoc;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzw {
    private String zza;
    private boolean zzb;
    private zzfn.zzm zzc;
    /* access modifiers changed from: private */
    public BitSet zzd;
    private BitSet zze;
    private Map<Integer, Long> zzf;
    private Map<Integer, List<Long>> zzg;
    private final /* synthetic */ zzu zzh;

    /* access modifiers changed from: package-private */
    public final zzfn.zzd zza(int i) {
        ArrayList arrayList;
        List list;
        zzfn.zzd.zza zzb2 = zzfn.zzd.zzb();
        zzb2.zza(i);
        zzb2.zza(this.zzb);
        zzfn.zzm zzm = this.zzc;
        if (zzm != null) {
            zzb2.zza(zzm);
        }
        zzfn.zzm.zza zzd2 = zzfn.zzm.zze().zzb(zznl.zza(this.zzd)).zzd(zznl.zza(this.zze));
        if (this.zzf == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(this.zzf.size());
            for (Integer next : this.zzf.keySet()) {
                int intValue = next.intValue();
                Long l = this.zzf.get(next);
                if (l != null) {
                    arrayList.add((zzfn.zze) ((zzjk) zzfn.zze.zzc().zza(intValue).zza(l.longValue()).zzai()));
                }
            }
        }
        if (arrayList != null) {
            zzd2.zza(arrayList);
        }
        if (this.zzg == null) {
            list = Collections.emptyList();
        } else {
            ArrayList arrayList2 = new ArrayList(this.zzg.size());
            for (Integer next2 : this.zzg.keySet()) {
                zzfn.zzn.zza zza2 = zzfn.zzn.zzc().zza(next2.intValue());
                List list2 = this.zzg.get(next2);
                if (list2 != null) {
                    Collections.sort(list2);
                    zza2.zza((Iterable<? extends Long>) list2);
                }
                arrayList2.add((zzfn.zzn) ((zzjk) zza2.zzai()));
            }
            list = arrayList2;
        }
        zzd2.zzc(list);
        zzb2.zza(zzd2);
        return (zzfn.zzd) ((zzjk) zzb2.zzai());
    }

    private zzw(zzu zzu, String str) {
        this.zzh = zzu;
        this.zza = str;
        this.zzb = true;
        this.zzd = new BitSet();
        this.zze = new BitSet();
        this.zzf = new ArrayMap();
        this.zzg = new ArrayMap();
    }

    private zzw(zzu zzu, String str, zzfn.zzm zzm, BitSet bitSet, BitSet bitSet2, Map<Integer, Long> map, Map<Integer, Long> map2) {
        this.zzh = zzu;
        this.zza = str;
        this.zzd = bitSet;
        this.zze = bitSet2;
        this.zzf = map;
        this.zzg = new ArrayMap();
        if (map2 != null) {
            for (Integer next : map2.keySet()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(map2.get(next));
                this.zzg.put(next, arrayList);
            }
        }
        this.zzb = false;
        this.zzc = zzm;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzz zzz) {
        int zza2 = zzz.zza();
        if (zzz.zzc != null) {
            this.zze.set(zza2, zzz.zzc.booleanValue());
        }
        if (zzz.zzd != null) {
            this.zzd.set(zza2, zzz.zzd.booleanValue());
        }
        if (zzz.zze != null) {
            Long l = this.zzf.get(Integer.valueOf(zza2));
            long longValue = zzz.zze.longValue() / 1000;
            if (l == null || longValue > l.longValue()) {
                this.zzf.put(Integer.valueOf(zza2), Long.valueOf(longValue));
            }
        }
        if (zzz.zzf != null) {
            List list = this.zzg.get(Integer.valueOf(zza2));
            if (list == null) {
                list = new ArrayList();
                this.zzg.put(Integer.valueOf(zza2), list);
            }
            if (zzz.zzc()) {
                list.clear();
            }
            if (zzoc.zza() && this.zzh.zze().zzf(this.zza, zzbf.zzbk) && zzz.zzb()) {
                list.clear();
            }
            if (!zzoc.zza() || !this.zzh.zze().zzf(this.zza, zzbf.zzbk)) {
                list.add(Long.valueOf(zzz.zzf.longValue() / 1000));
                return;
            }
            long longValue2 = zzz.zzf.longValue() / 1000;
            if (!list.contains(Long.valueOf(longValue2))) {
                list.add(Long.valueOf(longValue2));
            }
        }
    }
}
