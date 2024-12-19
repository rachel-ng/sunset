package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zznf implements Supplier<zzni> {
    private static zznf zza = new zznf();
    private final Supplier<zzni> zzb = Suppliers.ofInstance(new zznh());

    @SideEffectFree
    public static long zza() {
        return ((zzni) zza.get()).zza();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
