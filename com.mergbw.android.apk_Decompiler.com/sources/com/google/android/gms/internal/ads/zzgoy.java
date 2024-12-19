package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgoy {
    public static final zzgtl zza = new zzgox((zzgow) null);

    public static zzgtr zza(zzgqk zzgqk) {
        zzghk zzghk;
        zzgtn zzgtn = new zzgtn();
        zzgtn.zzb(zzgqk.zzc());
        for (List it : zzgqk.zze()) {
            Iterator it2 = it.iterator();
            while (true) {
                if (it2.hasNext()) {
                    zzgqi zzgqi = (zzgqi) it2.next();
                    zzgwj zzb = zzgqi.zzb();
                    zzgwj zzgwj = zzgwj.UNKNOWN_STATUS;
                    int ordinal = zzb.ordinal();
                    if (ordinal == 1) {
                        zzghk = zzghk.zza;
                    } else if (ordinal == 2) {
                        zzghk = zzghk.zzb;
                    } else if (ordinal == 3) {
                        zzghk = zzghk.zzc;
                    } else {
                        throw new IllegalStateException("Unknown key status");
                    }
                    int zza2 = zzgqi.zza();
                    String zzf = zzgqi.zzf();
                    if (zzf.startsWith("type.googleapis.com/google.crypto.")) {
                        zzf = zzf.substring(34);
                    }
                    zzgtn.zza(zzghk, zza2, zzf, zzgqi.zzc().name());
                }
            }
        }
        if (zzgqk.zzb() != null) {
            zzgtn.zzc(zzgqk.zzb().zza());
        }
        try {
            return zzgtn.zzd();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
