package com.google.android.gms.internal.ads;

import android.media.MediaCodec;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public class zzto extends zzil {
    public final zztp zza;
    public final String zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzto(Throwable th, zztp zztp) {
        super("Decoder failed: ".concat(String.valueOf(zztp == null ? null : zztp.zza)), th);
        String str = null;
        this.zza = zztp;
        int i = zzgd.zza;
        this.zzb = th instanceof MediaCodec.CodecException ? ((MediaCodec.CodecException) th).getDiagnosticInfo() : str;
    }
}
