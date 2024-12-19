package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgqg {
    private final Class zza;
    private Map zzb = new HashMap();
    private final List zzc = new ArrayList();
    private zzgqi zzd;
    private zzgtk zze;

    /* synthetic */ zzgqg(Class cls, zzgqf zzgqf) {
        this.zza = cls;
        this.zze = zzgtk.zza;
    }

    private final zzgqg zze(Object obj, zzghi zzghi, zzgwt zzgwt, boolean z) throws GeneralSecurityException {
        byte[] bArr;
        if (this.zzb == null) {
            throw new IllegalStateException("addEntry cannot be called after build");
        } else if (zzgwt.zzd() == zzgwj.ENABLED) {
            zzgxn zzgxn = zzgxn.UNKNOWN_PREFIX;
            int ordinal = zzgwt.zzg().ordinal();
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal == 3) {
                        bArr = zzghd.zza;
                    } else if (ordinal != 4) {
                        throw new GeneralSecurityException("unknown output prefix type");
                    }
                }
                bArr = zzgpm.zza(zzgwt.zza()).zzc();
            } else {
                bArr = zzgpm.zzb(zzgwt.zza()).zzc();
            }
            zzgqi zzgqi = new zzgqi(obj, zzgze.zzb(bArr), zzgwt.zzd(), zzgwt.zzg(), zzgwt.zza(), zzgwt.zzc().zzg(), zzghi, (zzgqh) null);
            Map map = this.zzb;
            List list = this.zzc;
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzgqi);
            List list2 = (List) map.put(zzgqi.zzb, Collections.unmodifiableList(arrayList));
            if (list2 != null) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(list2);
                arrayList2.add(zzgqi);
                map.put(zzgqi.zzb, Collections.unmodifiableList(arrayList2));
            }
            list.add(zzgqi);
            if (z) {
                if (this.zzd == null) {
                    this.zzd = zzgqi;
                } else {
                    throw new IllegalStateException("you cannot set two primary primitives");
                }
            }
            return this;
        } else {
            throw new GeneralSecurityException("only ENABLED key is allowed");
        }
    }

    public final zzgqg zza(Object obj, zzghi zzghi, zzgwt zzgwt) throws GeneralSecurityException {
        zze(obj, zzghi, zzgwt, false);
        return this;
    }

    public final zzgqg zzb(Object obj, zzghi zzghi, zzgwt zzgwt) throws GeneralSecurityException {
        zze(obj, zzghi, zzgwt, true);
        return this;
    }

    public final zzgqg zzc(zzgtk zzgtk) {
        if (this.zzb != null) {
            this.zze = zzgtk;
            return this;
        }
        throw new IllegalStateException("setAnnotations cannot be called after build");
    }

    public final zzgqk zzd() throws GeneralSecurityException {
        Map map = this.zzb;
        if (map != null) {
            zzgqk zzgqk = new zzgqk(map, this.zzc, this.zzd, this.zze, this.zza, (zzgqj) null);
            this.zzb = null;
            return zzgqk;
        }
        throw new IllegalStateException("build cannot be called twice");
    }
}
