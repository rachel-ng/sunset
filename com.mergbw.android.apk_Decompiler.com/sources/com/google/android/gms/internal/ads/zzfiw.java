package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.zze;
import java.util.HashSet;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfiw implements zzczo {
    private final HashSet zza = new HashSet();
    private final Context zzb;
    private final zzccc zzc;

    public zzfiw(Context context, zzccc zzccc) {
        this.zzb = context;
        this.zzc = zzccc;
    }

    public final Bundle zzb() {
        return this.zzc.zzn(this.zzb, this);
    }

    public final synchronized void zzc(HashSet hashSet) {
        this.zza.clear();
        this.zza.addAll(hashSet);
    }

    public final synchronized void zzdB(zze zze) {
        if (zze.zza != 3) {
            this.zzc.zzl(this.zza);
        }
    }
}
