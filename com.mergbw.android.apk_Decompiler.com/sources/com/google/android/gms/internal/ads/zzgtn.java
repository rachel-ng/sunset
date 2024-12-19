package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgtn {
    @Nullable
    private ArrayList zza = new ArrayList();
    private zzgtk zzb = zzgtk.zza;
    @Nullable
    private Integer zzc = null;

    public final zzgtn zza(zzghk zzghk, int i, String str, String str2) {
        ArrayList arrayList = this.zza;
        if (arrayList != null) {
            arrayList.add(new zzgtp(zzghk, i, str, str2, (zzgto) null));
            return this;
        }
        throw new IllegalStateException("addEntry cannot be called after build()");
    }

    public final zzgtn zzb(zzgtk zzgtk) {
        if (this.zza != null) {
            this.zzb = zzgtk;
            return this;
        }
        throw new IllegalStateException("setAnnotations cannot be called after build()");
    }

    public final zzgtn zzc(int i) {
        if (this.zza != null) {
            this.zzc = Integer.valueOf(i);
            return this;
        }
        throw new IllegalStateException("setPrimaryKeyId cannot be called after build()");
    }

    public final zzgtr zzd() throws GeneralSecurityException {
        if (this.zza != null) {
            Integer num = this.zzc;
            if (num != null) {
                int intValue = num.intValue();
                ArrayList arrayList = this.zza;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    int zza2 = ((zzgtp) arrayList.get(i)).zza();
                    i++;
                    if (zza2 == intValue) {
                    }
                }
                throw new GeneralSecurityException("primary key ID is not present in entries");
            }
            zzgtr zzgtr = new zzgtr(this.zzb, Collections.unmodifiableList(this.zza), this.zzc, (zzgtq) null);
            this.zza = null;
            return zzgtr;
        }
        throw new IllegalStateException("cannot call build() twice");
    }
}
