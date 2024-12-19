package com.google.android.gms.internal.ads;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgaf extends AbstractSet {
    final /* synthetic */ zzgal zza;

    zzgaf(zzgal zzgal) {
        this.zza = zzgal;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final boolean contains(@CheckForNull Object obj) {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.entrySet().contains(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            int zzd = this.zza.zzw(entry.getKey());
            if (zzd == -1 || !zzfya.zza(zzgal.zzj(this.zza, zzd), entry.getValue())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final Iterator iterator() {
        zzgal zzgal = this.zza;
        Map zzl = zzgal.zzl();
        if (zzl != null) {
            return zzl.entrySet().iterator();
        }
        return new zzgad(zzgal);
    }

    public final boolean remove(@CheckForNull Object obj) {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.entrySet().remove(obj);
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        zzgal zzgal = this.zza;
        if (zzgal.zzr()) {
            return false;
        }
        int zzc = zzgal.zzv();
        Object key = entry.getKey();
        Object value = entry.getValue();
        zzgal zzgal2 = this.zza;
        int zzb = zzgam.zzb(key, value, zzc, Objects.requireNonNull(zzgal2.zze), zzgal2.zzA(), zzgal2.zzB(), zzgal2.zzC());
        if (zzb == -1) {
            return false;
        }
        this.zza.zzq(zzb, zzc);
        zzgal zzgal3 = this.zza;
        zzgal3.zzg = zzgal3.zzg - 1;
        this.zza.zzo();
        return true;
    }

    public final int size() {
        return this.zza.size();
    }
}
