package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzoc implements Supplier<zzob> {
    private static zzoc zza = new zzoc();
    private final Supplier<zzob> zzb = Suppliers.ofInstance(new zzoe());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzob) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzob) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzob) zza.get()).zzc();
    }

    @SideEffectFree
    public static boolean zzd() {
        return ((zzob) zza.get()).zzd();
    }
}
