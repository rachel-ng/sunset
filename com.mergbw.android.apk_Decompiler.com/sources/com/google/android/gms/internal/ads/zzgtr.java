package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgtr {
    private final zzgtk zza;
    private final List zzb;
    @Nullable
    private final Integer zzc;

    /* synthetic */ zzgtr(zzgtk zzgtk, List list, Integer num, zzgtq zzgtq) {
        this.zza = zzgtk;
        this.zzb = list;
        this.zzc = num;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzgtr)) {
            return false;
        }
        zzgtr zzgtr = (zzgtr) obj;
        if (!this.zza.equals(zzgtr.zza) || !this.zzb.equals(zzgtr.zzb) || !Objects.equals(this.zzc, zzgtr.zzc)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        return String.format("(annotations=%s, entries=%s, primaryKeyId=%s)", new Object[]{this.zza, this.zzb, this.zzc});
    }
}
