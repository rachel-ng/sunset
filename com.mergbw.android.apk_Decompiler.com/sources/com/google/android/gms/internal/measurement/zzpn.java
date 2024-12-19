package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzpn implements Supplier<zzpq> {
    private static zzpn zza = new zzpn();
    private final Supplier<zzpq> zzb = Suppliers.ofInstance(new zzpp());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzpq) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzpq) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzpq) zza.get()).zzc();
    }

    @SideEffectFree
    public static boolean zzd() {
        return ((zzpq) zza.get()).zzd();
    }

    @SideEffectFree
    public static boolean zze() {
        return ((zzpq) zza.get()).zze();
    }
}
