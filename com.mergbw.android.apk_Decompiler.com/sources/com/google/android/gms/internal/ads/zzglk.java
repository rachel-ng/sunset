package com.google.android.gms.internal.ads;

import com.alibaba.android.arouter.utils.Consts;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzglk {
    @Nullable
    private zzglm zza;
    @Nullable
    private String zzb;
    @Nullable
    private zzgll zzc;
    @Nullable
    private zzgii zzd;

    private zzglk() {
        throw null;
    }

    /* synthetic */ zzglk(zzglj zzglj) {
    }

    public final zzglk zza(zzgii zzgii) {
        this.zzd = zzgii;
        return this;
    }

    public final zzglk zzb(zzgll zzgll) {
        this.zzc = zzgll;
        return this;
    }

    public final zzglk zzc(String str) {
        this.zzb = str;
        return this;
    }

    public final zzglk zzd(zzglm zzglm) {
        this.zza = zzglm;
        return this;
    }

    public final zzglo zze() throws GeneralSecurityException {
        if (this.zza == null) {
            this.zza = zzglm.zzb;
        }
        if (this.zzb != null) {
            zzgll zzgll = this.zzc;
            if (zzgll != null) {
                zzgii zzgii = this.zzd;
                if (zzgii == null) {
                    throw new GeneralSecurityException("dekParametersForNewKeys must be set");
                } else if (zzgii.zza()) {
                    throw new GeneralSecurityException("dekParametersForNewKeys must not have ID Requirements");
                } else if ((zzgll.equals(zzgll.zza) && (zzgii instanceof zzgjz)) || ((zzgll.equals(zzgll.zzc) && (zzgii instanceof zzgks)) || ((zzgll.equals(zzgll.zzb) && (zzgii instanceof zzgmh)) || ((zzgll.equals(zzgll.zzd) && (zzgii instanceof zzgja)) || ((zzgll.equals(zzgll.zze) && (zzgii instanceof zzgjm)) || (zzgll.equals(zzgll.zzf) && (zzgii instanceof zzgkm))))))) {
                    return new zzglo(this.zza, this.zzb, this.zzc, this.zzd, (zzgln) null);
                } else {
                    String zzgll2 = this.zzc.toString();
                    String valueOf = String.valueOf(this.zzd);
                    throw new GeneralSecurityException("Cannot use parsing strategy " + zzgll2 + " when new keys are picked according to " + valueOf + Consts.DOT);
                }
            } else {
                throw new GeneralSecurityException("dekParsingStrategy must be set");
            }
        } else {
            throw new GeneralSecurityException("kekUri must be set");
        }
    }
}
