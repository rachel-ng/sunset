package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfpe {
    private static final zzfpe zza = new zzfpe();
    private final ArrayList zzb = new ArrayList();
    private final ArrayList zzc = new ArrayList();

    private zzfpe() {
    }

    public static zzfpe zza() {
        return zza;
    }

    public final Collection zzb() {
        return Collections.unmodifiableCollection(this.zzc);
    }

    public final Collection zzc() {
        return Collections.unmodifiableCollection(this.zzb);
    }

    public final void zzd(zzfon zzfon) {
        this.zzb.add(zzfon);
    }

    public final void zze(zzfon zzfon) {
        ArrayList arrayList = this.zzb;
        boolean zzg = zzg();
        arrayList.remove(zzfon);
        this.zzc.remove(zzfon);
        if (zzg && !zzg()) {
            zzfpm.zzb().zzg();
        }
    }

    public final void zzf(zzfon zzfon) {
        ArrayList arrayList = this.zzc;
        boolean zzg = zzg();
        arrayList.add(zzfon);
        if (!zzg) {
            zzfpm.zzb().zzf();
        }
    }

    public final boolean zzg() {
        return this.zzc.size() > 0;
    }
}
