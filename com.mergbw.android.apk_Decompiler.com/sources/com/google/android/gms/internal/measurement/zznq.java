package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zznq implements Supplier<zznp> {
    private static zznq zza = new zznq();
    private final Supplier<zznp> zzb = Suppliers.ofInstance(new zzns());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zznp) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zznp) zza.get()).zzb();
    }
}
