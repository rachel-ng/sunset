package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzaki {
    public final zzakp zza;
    public final zzaks zzb;
    public final zzafa zzc;
    public final zzafb zzd;
    public int zze;

    public zzaki(zzakp zzakp, zzaks zzaks, zzafa zzafa) {
        this.zza = zzakp;
        this.zzb = zzaks;
        this.zzc = zzafa;
        this.zzd = MimeTypes.AUDIO_TRUEHD.equals(zzakp.zzf.zzn) ? new zzafb() : null;
    }
}
