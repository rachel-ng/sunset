package com.google.android.gms.internal.ads;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
abstract class zzgah implements Iterator {
    int zzb;
    int zzc;
    int zzd = -1;
    final /* synthetic */ zzgal zze;

    /* synthetic */ zzgah(zzgal zzgal, zzgag zzgag) {
        this.zze = zzgal;
        this.zzb = zzgal.zzf;
        this.zzc = zzgal.zze();
    }

    private final void zzb() {
        if (this.zze.zzf != this.zzb) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean hasNext() {
        return this.zzc >= 0;
    }

    public final Object next() {
        zzb();
        if (hasNext()) {
            int i = this.zzc;
            this.zzd = i;
            Object zza = zza(i);
            this.zzc = this.zze.zzf(this.zzc);
            return zza;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        zzb();
        zzfyg.zzk(this.zzd >= 0, "no calls to next() since the last call to remove()");
        this.zzb += 32;
        int i = this.zzd;
        zzgal zzgal = this.zze;
        zzgal.remove(zzgal.zzg(zzgal, i));
        this.zzc--;
        this.zzd = -1;
    }

    /* access modifiers changed from: package-private */
    public abstract Object zza(int i);
}
