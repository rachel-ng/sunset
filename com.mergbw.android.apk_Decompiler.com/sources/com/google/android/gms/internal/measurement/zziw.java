package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zziw implements zzna {
    private final zzit zza;

    public static zziw zza(zzit zzit) {
        if (zzit.zza != null) {
            return zzit.zza;
        }
        return new zziw(zzit);
    }

    public final int zza() {
        return 1;
    }

    private zziw(zzit zzit) {
        zzit zzit2 = (zzit) zzjm.zza(zzit, "output");
        this.zza = zzit2;
        zzit2.zza = this;
    }

    public final void zza(int i, boolean z) throws IOException {
        this.zza.zza(i, z);
    }

    public final void zza(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzhy) {
            zzhy zzhy = (zzhy) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzhy.size(); i4++) {
                    i3 += zzit.zza(zzhy.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzhy.size()) {
                    this.zza.zzb(zzhy.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzhy.size()) {
                this.zza.zza(i, zzhy.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzit.zza(list.get(i6).booleanValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).booleanValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).booleanValue());
                i2++;
            }
        }
    }

    public final void zza(int i, zzia zzia) throws IOException {
        this.zza.zza(i, zzia);
    }

    public final void zza(int i, List<zzia> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zza(i, list.get(i2));
        }
    }

    public final void zza(int i, double d) throws IOException {
        this.zza.zzb(i, d);
    }

    public final void zzb(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zziv) {
            zziv zziv = (zziv) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zziv.size(); i4++) {
                    i3 += zzit.zza(zziv.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zziv.size()) {
                    this.zza.zzb(zziv.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zziv.size()) {
                this.zza.zzb(i, zziv.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzit.zza(list.get(i6).doubleValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).doubleValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).doubleValue());
                i2++;
            }
        }
    }

    @Deprecated
    public final void zza(int i) throws IOException {
        this.zza.zzc(i, 4);
    }

    public final void zza(int i, int i2) throws IOException {
        this.zza.zzb(i, i2);
    }

    public final void zzc(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzjn) {
            zzjn zzjn = (zzjn) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzjn.size(); i4++) {
                    i3 += zzit.zzd(zzjn.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzjn.size()) {
                    this.zza.zzb(zzjn.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzjn.size()) {
                this.zza.zzb(i, zzjn.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzit.zzd(list.get(i6).intValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zzb(int i, int i2) throws IOException {
        this.zza.zza(i, i2);
    }

    public final void zzd(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzjn) {
            zzjn zzjn = (zzjn) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzjn.size(); i4++) {
                    i3 += zzit.zze(zzjn.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzjn.size()) {
                    this.zza.zza(zzjn.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzjn.size()) {
                this.zza.zza(i, zzjn.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzit.zze(list.get(i6).intValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zza(int i, long j) throws IOException {
        this.zza.zza(i, j);
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzke) {
            zzke zzke = (zzke) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzke.size(); i4++) {
                    i3 += zzit.zzc(zzke.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzke.size()) {
                    this.zza.zza(zzke.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzke.size()) {
                this.zza.zza(i, zzke.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzit.zzc(list.get(i6).longValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).longValue());
                i2++;
            }
        }
    }

    public final void zza(int i, float f) throws IOException {
        this.zza.zzb(i, f);
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzjj) {
            zzjj zzjj = (zzjj) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzjj.size(); i4++) {
                    i3 += zzit.zza(zzjj.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzjj.size()) {
                    this.zza.zzb(zzjj.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzjj.size()) {
                this.zza.zzb(i, zzjj.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzit.zza(list.get(i6).floatValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).floatValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).floatValue());
                i2++;
            }
        }
    }

    public final void zza(int i, Object obj, zzll zzll) throws IOException {
        zzit zzit = this.zza;
        zzit.zzc(i, 3);
        zzll.zza((zzkt) obj, (zzna) zzit.zza);
        zzit.zzc(i, 4);
    }

    public final void zza(int i, List<?> list, zzll zzll) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, (Object) list.get(i2), zzll);
        }
    }

    public final void zzc(int i, int i2) throws IOException {
        this.zza.zzb(i, i2);
    }

    public final void zzg(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzjn) {
            zzjn zzjn = (zzjn) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzjn.size(); i4++) {
                    i3 += zzit.zzf(zzjn.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzjn.size()) {
                    this.zza.zzb(zzjn.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzjn.size()) {
                this.zza.zzb(i, zzjn.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzit.zzf(list.get(i6).intValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zzb(int i, long j) throws IOException {
        this.zza.zzb(i, j);
    }

    public final void zzh(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzke) {
            zzke zzke = (zzke) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzke.size(); i4++) {
                    i3 += zzit.zzd(zzke.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzke.size()) {
                    this.zza.zzb(zzke.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzke.size()) {
                this.zza.zzb(i, zzke.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzit.zzd(list.get(i6).longValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).longValue());
                i2++;
            }
        }
    }

    public final <K, V> void zza(int i, zzkk<K, V> zzkk, Map<K, V> map) throws IOException {
        for (Map.Entry next : map.entrySet()) {
            this.zza.zzc(i, 2);
            this.zza.zzc(zzkl.zza(zzkk, next.getKey(), next.getValue()));
            zzkl.zza(this.zza, zzkk, next.getKey(), next.getValue());
        }
    }

    public final void zzb(int i, Object obj, zzll zzll) throws IOException {
        this.zza.zza(i, (zzkt) obj, zzll);
    }

    public final void zzb(int i, List<?> list, zzll zzll) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, (Object) list.get(i2), zzll);
        }
    }

    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzia) {
            this.zza.zzb(i, (zzia) obj);
        } else {
            this.zza.zza(i, (zzkt) obj);
        }
    }

    public final void zzd(int i, int i2) throws IOException {
        this.zza.zza(i, i2);
    }

    public final void zzi(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzjn) {
            zzjn zzjn = (zzjn) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzjn.size(); i4++) {
                    i3 += zzit.zzg(zzjn.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzjn.size()) {
                    this.zza.zza(zzjn.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzjn.size()) {
                this.zza.zza(i, zzjn.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzit.zzg(list.get(i6).intValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zzc(int i, long j) throws IOException {
        this.zza.zza(i, j);
    }

    public final void zzj(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzke) {
            zzke zzke = (zzke) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzke.size(); i4++) {
                    i3 += zzit.zze(zzke.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzke.size()) {
                    this.zza.zza(zzke.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzke.size()) {
                this.zza.zza(i, zzke.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzit.zze(list.get(i6).longValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).longValue());
                i2++;
            }
        }
    }

    public final void zze(int i, int i2) throws IOException {
        this.zza.zzk(i, i2);
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzjn) {
            zzjn zzjn = (zzjn) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzjn.size(); i4++) {
                    i3 += zzit.zzh(zzjn.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzjn.size()) {
                    this.zza.zzk(zzjn.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzjn.size()) {
                this.zza.zzk(i, zzjn.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzit.zzh(list.get(i6).intValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzk(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzk(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zzd(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzke) {
            zzke zzke = (zzke) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzke.size(); i4++) {
                    i3 += zzit.zzf(zzke.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzke.size()) {
                    this.zza.zzh(zzke.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzke.size()) {
                this.zza.zzh(i, zzke.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzit.zzf(list.get(i6).longValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzh(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, list.get(i2).longValue());
                i2++;
            }
        }
    }

    @Deprecated
    public final void zzb(int i) throws IOException {
        this.zza.zzc(i, 3);
    }

    public final void zza(int i, String str) throws IOException {
        this.zza.zza(i, str);
    }

    public final void zzb(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzka) {
            zzka zzka = (zzka) list;
            while (i2 < list.size()) {
                Object zza2 = zzka.zza(i2);
                if (zza2 instanceof String) {
                    this.zza.zza(i, (String) zza2);
                } else {
                    this.zza.zza(i, (zzia) zza2);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zza(i, list.get(i2));
            i2++;
        }
    }

    public final void zzf(int i, int i2) throws IOException {
        this.zza.zzd(i, i2);
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzjn) {
            zzjn zzjn = (zzjn) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzjn.size(); i4++) {
                    i3 += zzit.zzj(zzjn.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzjn.size()) {
                    this.zza.zzc(zzjn.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzjn.size()) {
                this.zza.zzd(i, zzjn.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzit.zzj(list.get(i6).intValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzc(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzd(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zze(int i, long j) throws IOException {
        this.zza.zzb(i, j);
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzke) {
            zzke zzke = (zzke) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzke.size(); i4++) {
                    i3 += zzit.zzg(zzke.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzke.size()) {
                    this.zza.zzb(zzke.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzke.size()) {
                this.zza.zzb(i, zzke.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzit.zzg(list.get(i6).longValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).longValue());
                i2++;
            }
        }
    }
}
