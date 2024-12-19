package com.google.android.gms.internal.ads;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzfyq implements Iterable {
    final /* synthetic */ CharSequence zza;
    final /* synthetic */ zzfyt zzb;

    zzfyq(zzfyt zzfyt, CharSequence charSequence) {
        this.zza = charSequence;
        this.zzb = zzfyt;
    }

    public final Iterator iterator() {
        return this.zzb.zzg(this.zza);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        zzfxv.zzb(sb, this, ", ");
        sb.append(']');
        return sb.toString();
    }
}
