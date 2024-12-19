package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzne implements Supplier<zznd> {
    private static zzne zza = new zzne();
    private final Supplier<zznd> zzb = Suppliers.ofInstance(new zzng());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zznd) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zznd) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zznd) zza.get()).zzc();
    }
}
