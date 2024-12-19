package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzght {
    private final zzgwu zza;
    private final List zzb;
    private final zzgtk zzc;

    private zzght(zzgwu zzgwu, List list) {
        this.zza = zzgwu;
        this.zzb = list;
        this.zzc = zzgtk.zza;
    }

    /* synthetic */ zzght(zzgwu zzgwu, List list, zzgtk zzgtk, zzghs zzghs) {
        this.zza = zzgwu;
        this.zzb = list;
        this.zzc = zzgtk;
    }

    static final zzght zza(zzgwu zzgwu) throws GeneralSecurityException {
        zzh(zzgwu);
        return new zzght(zzgwu, zzg(zzgwu));
    }

    public static final zzght zzb(zzghx zzghx) throws GeneralSecurityException {
        zzghp zzghp = new zzghp();
        zzghn zzghn = new zzghn(zzghx, (zzghm) null);
        zzghn.zzd();
        zzghn.zzc();
        zzghp.zza(zzghn);
        return zzghp.zzb();
    }

    private final Object zzf(zzgoi zzgoi, Class cls, Class cls2) throws GeneralSecurityException {
        Charset charset = zzgif.zza;
        zzgwu zzgwu = this.zza;
        int zzc2 = zzgwu.zzc();
        int i = 0;
        boolean z = false;
        boolean z2 = true;
        for (zzgwt zzgwt : zzgwu.zzh()) {
            if (zzgwt.zzd() == zzgwj.ENABLED) {
                if (!zzgwt.zzl()) {
                    throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(zzgwt.zza())}));
                } else if (zzgwt.zzg() == zzgxn.UNKNOWN_PREFIX) {
                    throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(zzgwt.zza())}));
                } else if (zzgwt.zzd() != zzgwj.UNKNOWN_STATUS) {
                    if (zzgwt.zza() == zzc2) {
                        if (!z) {
                            z = true;
                        } else {
                            throw new GeneralSecurityException("keyset contains multiple primary keys");
                        }
                    }
                    z2 &= zzgwt.zzc().zzc() == zzgwg.ASYMMETRIC_PUBLIC;
                    i++;
                } else {
                    throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(zzgwt.zza())}));
                }
            }
        }
        if (i == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        } else if (z || z2) {
            zzgqg zza2 = zzgqk.zza(cls2);
            zza2.zzc(this.zzc);
            for (int i2 = 0; i2 < this.zzb.size(); i2++) {
                zzgwt zze = this.zza.zze(i2);
                if (zze.zzd().equals(zzgwj.ENABLED)) {
                    zzghr zzghr = (zzghr) this.zzb.get(i2);
                    if (zzghr != null) {
                        zzghi zza3 = zzghr.zza();
                        try {
                            Object zzc3 = zzgpi.zza().zzc(zza3, cls2);
                            if (zze.zza() == this.zza.zzc()) {
                                zza2.zzb(zzc3, zza3, zze);
                            } else {
                                zza2.zza(zzc3, zza3, zze);
                            }
                        } catch (GeneralSecurityException e) {
                            throw new GeneralSecurityException("Unable to get primitive " + cls2.toString() + " for key of type " + zze.zzc().zzg() + ", see https://developers.google.com/tink/faq/registration_errors", e);
                        }
                    } else {
                        throw new GeneralSecurityException("Key parsing of key with index " + i2 + " and type_url " + zze.zzc().zzg() + " failed, unable to get primitive");
                    }
                }
            }
            zzgqk zzd = zza2.zzd();
            int i3 = zzgib.zza;
            return zzgpi.zza().zzd(zzd, cls);
        } else {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }

    private static List zzg(zzgwu zzgwu) {
        zzghi zzghi;
        zzghk zzghk;
        ArrayList arrayList = new ArrayList(zzgwu.zza());
        for (zzgwt zzgwt : zzgwu.zzh()) {
            int zza2 = zzgwt.zza();
            try {
                zzgql zza3 = zzgql.zza(zzgwt.zzc().zzg(), zzgwt.zzc().zzf(), zzgwt.zzc().zzc(), zzgwt.zzg(), zzgwt.zzg() == zzgxn.RAW ? null : Integer.valueOf(zzgwt.zza()));
                zzgpl zzc2 = zzgpl.zzc();
                zzgic zza4 = zzgic.zza();
                if (!zzc2.zzj(zza3)) {
                    zzghi = new zzgou(zza3, zza4);
                } else {
                    zzghi = zzc2.zza(zza3, zza4);
                }
                zzgwj zzd = zzgwt.zzd();
                zzgwj zzgwj = zzgwj.UNKNOWN_STATUS;
                int ordinal = zzd.ordinal();
                if (ordinal == 1) {
                    zzghk = zzghk.zza;
                } else if (ordinal == 2) {
                    zzghk = zzghk.zzb;
                } else if (ordinal == 3) {
                    zzghk = zzghk.zzc;
                } else {
                    throw new GeneralSecurityException("Unknown key status");
                }
                arrayList.add(new zzghr(zzghi, zzghk, zza2, zza2 == zzgwu.zzc(), (zzghq) null));
            } catch (GeneralSecurityException unused) {
                arrayList.add((Object) null);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* access modifiers changed from: private */
    public static void zzh(zzgwu zzgwu) throws GeneralSecurityException {
        if (zzgwu == null || zzgwu.zza() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    public final String toString() {
        Charset charset = zzgif.zza;
        zzgww zza2 = zzgwz.zza();
        zzgwu zzgwu = this.zza;
        zza2.zzb(zzgwu.zzc());
        for (zzgwt zzgwt : zzgwu.zzh()) {
            zzgwx zza3 = zzgwy.zza();
            zza3.zzd(zzgwt.zzc().zzg());
            zza3.zzc(zzgwt.zzd());
            zza3.zzb(zzgwt.zzg());
            zza3.zza(zzgwt.zza());
            zza2.zza((zzgwy) zza3.zzbr());
        }
        return ((zzgwz) zza2.zzbr()).toString();
    }

    /* access modifiers changed from: package-private */
    public final zzgwu zzc() {
        return this.zza;
    }

    public final Object zzd(zzghc zzghc, Class cls) throws GeneralSecurityException {
        Class zza2 = zzgib.zza(cls);
        if (zza2 != null) {
            return zzf((zzgoi) zzghc, cls, zza2);
        }
        throw new GeneralSecurityException("No wrapper found for ".concat(String.valueOf(cls.getName())));
    }
}
