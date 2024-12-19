package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzlv implements Iterator<Map.Entry<K, V>> {
    private int zza;
    private boolean zzb;
    private Iterator<Map.Entry<K, V>> zzc;
    private final /* synthetic */ zzlm zzd;

    public final /* synthetic */ Object next() {
        this.zzb = true;
        int i = this.zza + 1;
        this.zza = i;
        if (i < this.zzd.zza.size()) {
            return (Map.Entry) this.zzd.zza.get(this.zza);
        }
        return (Map.Entry) zza().next();
    }

    private final Iterator<Map.Entry<K, V>> zza() {
        if (this.zzc == null) {
            this.zzc = this.zzd.zzb.entrySet().iterator();
        }
        return this.zzc;
    }

    private zzlv(zzlm zzlm) {
        this.zzd = zzlm;
        this.zza = -1;
    }

    public final void remove() {
        if (this.zzb) {
            this.zzb = false;
            this.zzd.zzg();
            if (this.zza < this.zzd.zza.size()) {
                zzlm zzlm = this.zzd;
                int i = this.zza;
                this.zza = i - 1;
                Object unused = zzlm.zzb(i);
                return;
            }
            zza().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }

    public final boolean hasNext() {
        if (this.zza + 1 < this.zzd.zza.size() || (!this.zzd.zzb.isEmpty() && zza().hasNext())) {
            return true;
        }
        return false;
    }
}
