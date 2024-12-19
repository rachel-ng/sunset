package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhbl implements zzhbc {
    final zzhbt zza;
    final int zzb;
    final zzhfg zzc;
    final boolean zzd;
    final boolean zze;

    zzhbl(zzhbt zzhbt, int i, zzhfg zzhfg, boolean z, boolean z2) {
        this.zza = zzhbt;
        this.zzb = i;
        this.zzc = zzhfg;
        this.zzd = z;
        this.zze = z2;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return this.zzb - ((zzhbl) obj).zzb;
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzhdd zzb(zzhdd zzhdd, zzhde zzhde) {
        ((zzhbi) zzhdd).zzbj((zzhbo) zzhde);
        return zzhdd;
    }

    public final zzhdj zzc(zzhdj zzhdj, zzhdj zzhdj2) {
        throw new UnsupportedOperationException();
    }

    public final zzhfg zzd() {
        return this.zzc;
    }

    public final zzhfh zze() {
        return this.zzc.zza();
    }

    public final boolean zzf() {
        return this.zze;
    }

    public final boolean zzg() {
        return this.zzd;
    }
}
