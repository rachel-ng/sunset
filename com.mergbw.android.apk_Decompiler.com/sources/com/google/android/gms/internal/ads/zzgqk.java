package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgqk {
    private final Map zza;
    private final List zzb;
    private final zzgqi zzc;
    private final Class zzd;
    private final zzgtk zze;

    /* synthetic */ zzgqk(Map map, List list, zzgqi zzgqi, zzgtk zzgtk, Class cls, zzgqj zzgqj) {
        this.zza = map;
        this.zzb = list;
        this.zzc = zzgqi;
        this.zzd = cls;
        this.zze = zzgtk;
    }

    public static zzgqg zza(Class cls) {
        return new zzgqg(cls, (zzgqf) null);
    }

    @Nullable
    public final zzgqi zzb() {
        return this.zzc;
    }

    public final zzgtk zzc() {
        return this.zze;
    }

    public final Class zzd() {
        return this.zzd;
    }

    public final Collection zze() {
        return this.zza.values();
    }

    public final List zzf(byte[] bArr) {
        List list = (List) this.zza.get(zzgze.zzb(bArr));
        if (list != null) {
            return list;
        }
        return Collections.emptyList();
    }

    public final boolean zzg() {
        return !this.zze.zza().isEmpty();
    }
}
