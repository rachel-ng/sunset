package com.google.android.gms.internal.ads;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzfyl extends zzfyb {
    private final Object zza;

    zzfyl(Object obj) {
        this.zza = obj;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzfyl) {
            return this.zza.equals(((zzfyl) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        String obj = this.zza.toString();
        return "Optional.of(" + obj + ")";
    }

    public final zzfyb zza(zzfxu zzfxu) {
        Object apply = zzfxu.apply(this.zza);
        zzfyg.zzc(apply, "the Function passed to Optional.transform() must not return null.");
        return new zzfyl(apply);
    }

    public final Object zzb(Object obj) {
        return this.zza;
    }
}
