package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzov implements Supplier<zzoy> {
    private static zzov zza = new zzov();
    private final Supplier<zzoy> zzb = Suppliers.ofInstance(new zzox());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzoy) zza.get()).zza();
    }
}
