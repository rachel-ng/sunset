package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.util.zze;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfec implements zzfxu {
    final /* synthetic */ zzfeg zza;

    zzfec(zzfeg zzfeg) {
        this.zza = zzfeg;
    }

    @NullableDecl
    public final /* bridge */ /* synthetic */ Object apply(@NullableDecl Object obj) {
        zzm.zzh("", (zzebh) obj);
        zze.zza("Failed to get a cache key, reverting to legacy flow.");
        zzfeg zzfeg = this.zza;
        zzfeg.zzd = new zzfef((zzbxu) null, zzfeg.zze(), (zzfee) null);
        return this.zza.zzd;
    }
}
