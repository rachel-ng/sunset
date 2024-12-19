package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzoj implements Supplier<zzom> {
    private static zzoj zza = new zzoj();
    private final Supplier<zzom> zzb = Suppliers.ofInstance(new zzol());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzom) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzom) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzom) zza.get()).zzc();
    }
}
