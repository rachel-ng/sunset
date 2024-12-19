package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzou implements Supplier<zzot> {
    private static zzou zza = new zzou();
    private final Supplier<zzot> zzb = Suppliers.ofInstance(new zzow());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzot) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzot) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzot) zza.get()).zzc();
    }
}
