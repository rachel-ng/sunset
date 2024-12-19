package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzhjy extends zzhjw implements zzasr {
    private int zza;

    protected zzhjy(String str) {
        super("mvhd");
    }

    public final int zzh() {
        if (!this.zzc) {
            zzg();
        }
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public final long zzi(ByteBuffer byteBuffer) {
        this.zza = zzasq.zzc(byteBuffer.get());
        zzasq.zzd(byteBuffer);
        byteBuffer.get();
        return 4;
    }
}
