package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzhjw implements zzasr {
    private static final zzhkh zza = zzhkh.zzb(zzhjw.class);
    protected final String zzb;
    boolean zzc;
    boolean zzd;
    long zze;
    long zzf = -1;
    zzhkb zzg;
    private zzass zzh;
    private ByteBuffer zzi;
    private ByteBuffer zzj = null;

    protected zzhjw(String str) {
        this.zzb = str;
        this.zzd = true;
        this.zzc = true;
    }

    private final synchronized void zzd() {
        String str;
        if (!this.zzd) {
            try {
                zzhkh zzhkh = zza;
                String str2 = this.zzb;
                if (str2.length() != 0) {
                    str = "mem mapping ".concat(str2);
                } else {
                    str = new String("mem mapping ");
                }
                zzhkh.zza(str);
                this.zzi = this.zzg.zzd(this.zze, this.zzf);
                this.zzd = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public final String zza() {
        return this.zzb;
    }

    public final void zzb(zzhkb zzhkb, ByteBuffer byteBuffer, long j, zzaso zzaso) throws IOException {
        this.zze = zzhkb.zzb();
        byteBuffer.remaining();
        this.zzf = j;
        this.zzg = zzhkb;
        zzhkb.zze(zzhkb.zzb() + j);
        this.zzd = false;
        this.zzc = false;
        zzg();
    }

    public final void zzc(zzass zzass) {
        this.zzh = zzass;
    }

    /* access modifiers changed from: protected */
    public abstract void zzf(ByteBuffer byteBuffer);

    public final synchronized void zzg() {
        String str;
        zzd();
        zzhkh zzhkh = zza;
        String str2 = this.zzb;
        if (str2.length() != 0) {
            str = "parsing details of ".concat(str2);
        } else {
            str = new String("parsing details of ");
        }
        zzhkh.zza(str);
        ByteBuffer byteBuffer = this.zzi;
        if (byteBuffer != null) {
            this.zzc = true;
            byteBuffer.rewind();
            zzf(byteBuffer);
            if (byteBuffer.remaining() > 0) {
                this.zzj = byteBuffer.slice();
            }
            this.zzi = null;
        }
    }
}
