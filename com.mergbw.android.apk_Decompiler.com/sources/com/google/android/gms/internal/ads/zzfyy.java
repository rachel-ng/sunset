package com.google.android.gms.internal.ads;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzfyy implements zzfyw {
    private static final zzfyw zza = new zzfyx();
    private volatile zzfyw zzb;
    @CheckForNull
    private Object zzc;

    zzfyy(zzfyw zzfyw) {
        this.zzb = zzfyw;
    }

    public final String toString() {
        Object obj = this.zzb;
        if (obj == zza) {
            obj = "<supplier that returned " + String.valueOf(this.zzc) + ">";
        }
        return "Suppliers.memoize(" + String.valueOf(obj) + ")";
    }

    public final Object zza() {
        zzfyw zzfyw = this.zzb;
        zzfyw zzfyw2 = zza;
        if (zzfyw != zzfyw2) {
            synchronized (this) {
                if (this.zzb != zzfyw2) {
                    Object zza2 = this.zzb.zza();
                    this.zzc = zza2;
                    this.zzb = zzfyw2;
                    return zza2;
                }
            }
        }
        return this.zzc;
    }
}
