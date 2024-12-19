package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzghp {
    private final List zza = new ArrayList();
    private final zzgtk zzb = zzgtk.zza;
    private boolean zzc = false;

    /* access modifiers changed from: private */
    public final void zzd() {
        for (zzghn zzi : this.zza) {
            zzi.zza = false;
        }
    }

    public final zzghp zza(zzghn zzghn) {
        if (zzghn.zzf == null) {
            if (zzghn.zza) {
                zzd();
            }
            zzghn.zzf = this;
            this.zza.add(zzghn);
            return this;
        }
        throw new IllegalStateException("Entry has already been added to a KeysetHandle.Builder");
    }

    public final zzght zzb() throws GeneralSecurityException {
        byte b2;
        zzgwj zzgwj;
        if (!this.zzc) {
            char c2 = 1;
            this.zzc = true;
            List list = this.zza;
            zzgwr zzd = zzgwu.zzd();
            ArrayList arrayList = new ArrayList(list.size());
            List list2 = this.zza;
            byte b3 = 0;
            int i = 0;
            while (i < list2.size() - 1) {
                int i2 = i + 1;
                if (((zzghn) list2.get(i)).zze != zzgho.zza || ((zzghn) list2.get(i2)).zze == zzgho.zza) {
                    i = i2;
                } else {
                    throw new GeneralSecurityException("Entries with 'withRandomId()' may only be followed by other entries with 'withRandomId()'.");
                }
            }
            HashSet hashSet = new HashSet();
            Integer num = null;
            for (zzghn zzghn : this.zza) {
                zzghk unused = zzghn.zzb;
                if (zzghn.zze != null) {
                    if (zzghn.zze == zzgho.zza) {
                        b2 = b3;
                        while (true) {
                            if (b2 != 0 && !hashSet.contains(Integer.valueOf(b2))) {
                                break;
                            }
                            SecureRandom secureRandom = new SecureRandom();
                            byte[] bArr = new byte[4];
                            byte b4 = b3;
                            while (b4 == 0) {
                                secureRandom.nextBytes(bArr);
                                b4 = ((bArr[b3] & 255) << Ascii.CAN) | ((bArr[c2] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
                            }
                            b2 = b4;
                        }
                    } else {
                        zzgho unused2 = zzghn.zze;
                        b2 = b3;
                    }
                    Integer valueOf = Integer.valueOf(b2);
                    if (!hashSet.contains(valueOf)) {
                        hashSet.add(valueOf);
                        zzghi unused3 = zzghn.zzc;
                        zzghi zza2 = zzgpb.zzb().zza(zzghn.zzd, c2 != zzghn.zzd.zza() ? null : valueOf);
                        zzghr zzghr = r13;
                        zzghr zzghr2 = new zzghr(zza2, zzghn.zzb, b2, zzghn.zza, (zzghq) null);
                        zzghk zzb2 = zzghn.zzb;
                        zzgql zzgql = (zzgql) zzgpl.zzc().zzd(zza2, zzgql.class, zzgic.zza());
                        Integer zzf = zzgql.zzf();
                        if (zzf == null || zzf.intValue() == b2) {
                            if (zzghk.zza.equals(zzb2)) {
                                zzgwj = zzgwj.ENABLED;
                            } else if (zzghk.zzb.equals(zzb2)) {
                                zzgwj = zzgwj.DISABLED;
                            } else if (zzghk.zzc.equals(zzb2)) {
                                zzgwj = zzgwj.DESTROYED;
                            } else {
                                throw new IllegalStateException("Unknown key status");
                            }
                            zzgws zze = zzgwt.zze();
                            zzgwe zza3 = zzgwh.zza();
                            zza3.zzb(zzgql.zzg());
                            zza3.zzc(zzgql.zze());
                            zza3.zza(zzgql.zzb());
                            zze.zza(zza3);
                            zze.zzd(zzgwj);
                            zze.zzb(b2);
                            zze.zzc(zzgql.zzc());
                            zzd.zza((zzgwt) zze.zzbr());
                            if (zzghn.zza) {
                                if (num != null) {
                                    throw new GeneralSecurityException("Two primaries were set");
                                } else if (zzghn.zzb == zzghk.zza) {
                                    num = valueOf;
                                } else {
                                    throw new GeneralSecurityException("Primary key is not enabled");
                                }
                            }
                            arrayList.add(zzghr);
                            c2 = 1;
                            b3 = 0;
                        } else {
                            throw new GeneralSecurityException("Wrong ID set for key with ID requirement");
                        }
                    } else {
                        throw new GeneralSecurityException("Id " + b2 + " is used twice in the keyset");
                    }
                } else {
                    throw new GeneralSecurityException("No ID was set (with withFixedId or withRandomId)");
                }
            }
            if (num != null) {
                zzd.zzb(num.intValue());
                zzgwu zzgwu = (zzgwu) zzd.zzbr();
                zzght.zzh(zzgwu);
                return new zzght(zzgwu, arrayList, this.zzb, (zzghs) null);
            }
            throw new GeneralSecurityException("No primary was set");
        }
        throw new GeneralSecurityException("KeysetHandle.Builder#build must only be called once");
    }
}
