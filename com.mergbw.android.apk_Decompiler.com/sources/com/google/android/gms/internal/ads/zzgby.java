package com.google.android.gms.internal.ads;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
abstract class zzgby extends AbstractMap {
    @CheckForNull
    private transient Set zza;
    @CheckForNull
    private transient Set zzb;
    @CheckForNull
    private transient Collection zzc;

    zzgby() {
    }

    public final Set entrySet() {
        Set set = this.zza;
        if (set != null) {
            return set;
        }
        Set zzb2 = zzb();
        this.zza = zzb2;
        return zzb2;
    }

    public Set keySet() {
        Set set = this.zzb;
        if (set != null) {
            return set;
        }
        Set zze = zze();
        this.zzb = zze;
        return zze;
    }

    public final Collection values() {
        Collection collection = this.zzc;
        if (collection != null) {
            return collection;
        }
        zzgbx zzgbx = new zzgbx(this);
        this.zzc = zzgbx;
        return zzgbx;
    }

    /* access modifiers changed from: package-private */
    public abstract Set zzb();

    /* access modifiers changed from: package-private */
    public Set zze() {
        return new zzgbw(this);
    }
}
