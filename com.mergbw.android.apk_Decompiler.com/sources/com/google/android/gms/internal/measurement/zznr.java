package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zznr implements Supplier<zznu> {
    private static zznr zza = new zznr();
    private final Supplier<zznu> zzb = Suppliers.ofInstance(new zznt());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zznu) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zznu) zza.get()).zzb();
    }
}
