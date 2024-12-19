package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzghl {
    public static final zzghx zza(zzghx zzghx) throws GeneralSecurityException {
        return zzghx != null ? zzghx : zzgie.zza(zzb((zzghx) null).zzaV());
    }

    static final zzgwm zzb(zzghx zzghx) {
        try {
            return ((zzgqm) zzgpl.zzc().zze((zzghx) null, zzgqm.class)).zzc();
        } catch (GeneralSecurityException e) {
            throw new zzgqz("Parsing parameters failed in getProto(). You probably want to call some Tink register function for ".concat("null"), e);
        }
    }
}
