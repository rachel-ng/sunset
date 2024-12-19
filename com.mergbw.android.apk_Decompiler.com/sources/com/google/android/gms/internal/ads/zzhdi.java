package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhdi implements zzhdz {
    private final zzhde zza;
    private final zzheq zzb;
    private final boolean zzc;
    private final zzhaz zzd;

    private zzhdi(zzheq zzheq, zzhaz zzhaz, zzhde zzhde) {
        this.zzb = zzheq;
        this.zzc = zzhaz.zzj(zzhde);
        this.zzd = zzhaz;
        this.zza = zzhde;
    }

    static zzhdi zzc(zzheq zzheq, zzhaz zzhaz, zzhde zzhde) {
        return new zzhdi(zzheq, zzhaz, zzhde);
    }

    public final int zza(Object obj) {
        zzheq zzheq = this.zzb;
        int zzb2 = zzheq.zzb(zzheq.zzd(obj));
        return this.zzc ? zzb2 + this.zzd.zzb(obj).zzd() : zzb2;
    }

    public final int zzb(Object obj) {
        int hashCode = this.zzb.zzd(obj).hashCode();
        return this.zzc ? (hashCode * 53) + this.zzd.zzb(obj).zza.hashCode() : hashCode;
    }

    public final Object zze() {
        zzhde zzhde = this.zza;
        if (zzhde instanceof zzhbo) {
            return ((zzhbo) zzhde).zzbj();
        }
        return zzhde.zzcY().zzbs();
    }

    public final void zzf(Object obj) {
        this.zzb.zzm(obj);
        this.zzd.zzf(obj);
    }

    public final void zzg(Object obj, Object obj2) {
        zzheb.zzr(this.zzb, obj, obj2);
        if (this.zzc) {
            zzheb.zzq(this.zzd, obj, obj2);
        }
    }

    public final void zzh(Object obj, zzhdr zzhdr, zzhay zzhay) throws IOException {
        boolean z;
        zzheq zzheq = this.zzb;
        Object zzc2 = zzheq.zzc(obj);
        zzhaz zzhaz = this.zzd;
        zzhbd zzc3 = zzhaz.zzc(obj);
        while (true) {
            if (zzhdr.zzc() == Integer.MAX_VALUE) {
                break;
            }
            int zzd2 = zzhdr.zzd();
            if (zzd2 != 11) {
                if ((zzd2 & 7) == 2) {
                    Object zzd3 = zzhaz.zzd(zzhay, this.zza, zzd2 >>> 3);
                    if (zzd3 != null) {
                        zzhaz.zzg(zzhdr, zzd3, zzhay, zzc3);
                    } else {
                        z = zzheq.zzr(zzc2, zzhdr);
                    }
                } else {
                    z = zzhdr.zzQ();
                }
                if (!z) {
                    break;
                }
            } else {
                Object obj2 = null;
                int i = 0;
                zzhac zzhac = null;
                while (true) {
                    try {
                        if (zzhdr.zzc() == Integer.MAX_VALUE) {
                            break;
                        }
                        int zzd4 = zzhdr.zzd();
                        if (zzd4 == 16) {
                            i = zzhdr.zzj();
                            obj2 = zzhaz.zzd(zzhay, this.zza, i);
                        } else if (zzd4 == 26) {
                            if (obj2 != null) {
                                zzhaz.zzg(zzhdr, obj2, zzhay, zzc3);
                            } else {
                                zzhac = zzhdr.zzp();
                            }
                        } else if (!zzhdr.zzQ()) {
                            break;
                        }
                    } catch (Throwable th) {
                        zzheq.zzn(obj, zzc2);
                        throw th;
                    }
                }
                if (zzhdr.zzd() != 12) {
                    throw zzhcd.zzb();
                } else if (zzhac != null) {
                    if (obj2 != null) {
                        zzhaz.zzh(zzhac, obj2, zzhay, zzc3);
                    } else {
                        zzheq.zzk(zzc2, i, zzhac);
                    }
                }
            }
        }
        zzheq.zzn(obj, zzc2);
    }

    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzgzn zzgzn) throws IOException {
        zzhbo zzhbo = (zzhbo) obj;
        if (zzhbo.zzt == zzher.zzc()) {
            zzhbo.zzt = zzher.zzf();
        }
        zzhbk zzhbk = (zzhbk) obj;
        throw null;
    }

    public final void zzj(Object obj, zzhfi zzhfi) throws IOException {
        Iterator zzg = this.zzd.zzb(obj).zzg();
        while (zzg.hasNext()) {
            Map.Entry entry = (Map.Entry) zzg.next();
            zzhbc zzhbc = (zzhbc) entry.getKey();
            if (zzhbc.zze() != zzhfh.MESSAGE || zzhbc.zzg() || zzhbc.zzf()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzhch) {
                zzhfi.zzw(zzhbc.zza(), ((zzhch) entry).zza().zzb());
            } else {
                zzhfi.zzw(zzhbc.zza(), entry.getValue());
            }
        }
        zzheq zzheq = this.zzb;
        zzheq.zzp(zzheq.zzd(obj), zzhfi);
    }

    public final boolean zzk(Object obj, Object obj2) {
        zzheq zzheq = this.zzb;
        if (!zzheq.zzd(obj).equals(zzheq.zzd(obj2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zzb(obj).equals(this.zzd.zzb(obj2));
        }
        return true;
    }

    public final boolean zzl(Object obj) {
        return this.zzd.zzb(obj).zzl();
    }
}
