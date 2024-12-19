package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzop implements Supplier<zzos> {
    private static zzop zza = new zzop();
    private final Supplier<zzos> zzb = Suppliers.ofInstance(new zzor());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzos) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzos) zza.get()).zzb();
    }
}
