package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzpg implements Supplier<zzpf> {
    private static zzpg zza = new zzpg();
    private final Supplier<zzpf> zzb = Suppliers.ofInstance(new zzpi());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzpf) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzpf) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzpf) zza.get()).zzc();
    }

    @SideEffectFree
    public static boolean zzd() {
        return ((zzpf) zza.get()).zzd();
    }

    @SideEffectFree
    public static boolean zze() {
        return ((zzpf) zza.get()).zze();
    }

    @SideEffectFree
    public static boolean zzf() {
        return ((zzpf) zza.get()).zzf();
    }

    @SideEffectFree
    public static boolean zzg() {
        return ((zzpf) zza.get()).zzg();
    }

    @SideEffectFree
    public static boolean zzh() {
        return ((zzpf) zza.get()).zzh();
    }

    @SideEffectFree
    public static boolean zzi() {
        return ((zzpf) zza.get()).zzi();
    }
}
