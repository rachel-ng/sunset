package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zznx implements Supplier<zzoa> {
    private static zznx zza = new zznx();
    private final Supplier<zzoa> zzb = Suppliers.ofInstance(new zznz());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzoa) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzoa) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzoa) zza.get()).zzc();
    }
}
