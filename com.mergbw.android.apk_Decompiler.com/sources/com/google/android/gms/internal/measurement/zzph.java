package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzph implements Supplier<zzpk> {
    private static zzph zza = new zzph();
    private final Supplier<zzpk> zzb = Suppliers.ofInstance(new zzpj());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzpk) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzpk) zza.get()).zzb();
    }
}
