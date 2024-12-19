package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zznw implements Supplier<zznv> {
    private static zznw zza = new zznw();
    private final Supplier<zznv> zzb = Suppliers.ofInstance(new zzny());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zznv) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zznv) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zznv) zza.get()).zzc();
    }
}
