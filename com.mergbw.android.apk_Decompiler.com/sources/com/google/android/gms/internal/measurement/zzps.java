package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzps implements Supplier<zzpr> {
    private static zzps zza = new zzps();
    private final Supplier<zzpr> zzb = Suppliers.ofInstance(new zzpu());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzpr) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzpr) zza.get()).zzb();
    }
}
