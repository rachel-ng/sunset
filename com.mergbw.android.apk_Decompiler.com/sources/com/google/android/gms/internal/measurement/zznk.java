package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zznk implements Supplier<zznj> {
    private static zznk zza = new zznk();
    private final Supplier<zznj> zzb = Suppliers.ofInstance(new zznm());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zznj) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zznj) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zznj) zza.get()).zzc();
    }

    @SideEffectFree
    public static boolean zzd() {
        return ((zznj) zza.get()).zzd();
    }

    @SideEffectFree
    public static boolean zze() {
        return ((zznj) zza.get()).zze();
    }
}
