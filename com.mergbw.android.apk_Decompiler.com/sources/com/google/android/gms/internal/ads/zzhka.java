package com.google.android.gms.internal.ads;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public class zzhka implements Iterator, Closeable, zzass {
    private static final zzasr zza = new zzhjz("eof ");
    private static final zzhkh zzb = zzhkh.zzb(zzhka.class);
    protected zzaso zzc;
    protected zzhkb zzd;
    zzasr zze = null;
    long zzf = 0;
    long zzg = 0;
    private final List zzh = new ArrayList();

    public void close() throws IOException {
    }

    public final boolean hasNext() {
        zzasr zzasr = this.zze;
        if (zzasr == zza) {
            return false;
        }
        if (zzasr != null) {
            return true;
        }
        try {
            this.zze = next();
            return true;
        } catch (NoSuchElementException unused) {
            this.zze = zza;
            return false;
        }
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("[");
        for (int i = 0; i < this.zzh.size(); i++) {
            if (i > 0) {
                sb.append(";");
            }
            sb.append(((zzasr) this.zzh.get(i)).toString());
        }
        sb.append("]");
        return sb.toString();
    }

    /* renamed from: zzd */
    public final zzasr next() {
        zzasr zzb2;
        zzasr zzasr = this.zze;
        if (zzasr == null || zzasr == zza) {
            zzhkb zzhkb = this.zzd;
            if (zzhkb == null || this.zzf >= this.zzg) {
                this.zze = zza;
                throw new NoSuchElementException();
            }
            try {
                synchronized (zzhkb) {
                    this.zzd.zze(this.zzf);
                    zzb2 = this.zzc.zzb(this.zzd, this);
                    this.zzf = this.zzd.zzb();
                }
                return zzb2;
            } catch (EOFException unused) {
                throw new NoSuchElementException();
            } catch (IOException unused2) {
                throw new NoSuchElementException();
            }
        } else {
            this.zze = null;
            return zzasr;
        }
    }

    public final List zze() {
        return (this.zzd == null || this.zze == zza) ? this.zzh : new zzhkg(this.zzh, this);
    }

    public final void zzf(zzhkb zzhkb, long j, zzaso zzaso) throws IOException {
        this.zzd = zzhkb;
        this.zzf = zzhkb.zzb();
        zzhkb.zze(zzhkb.zzb() + j);
        this.zzg = zzhkb.zzb();
        this.zzc = zzaso;
    }
}
