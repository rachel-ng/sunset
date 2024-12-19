package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzod implements Supplier<zzog> {
    private static zzod zza = new zzod();
    private final Supplier<zzog> zzb = Suppliers.ofInstance(new zzof());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzog) zza.get()).zza();
    }
}
