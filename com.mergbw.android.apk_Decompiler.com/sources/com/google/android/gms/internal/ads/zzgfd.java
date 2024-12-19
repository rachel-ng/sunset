package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
abstract class zzgfd extends zzges {
    @CheckForNull
    private List zza;

    zzgfd(zzgax zzgax, boolean z) {
        super(zzgax, z, true);
        List list;
        if (zzgax.isEmpty()) {
            list = Collections.emptyList();
        } else {
            list = zzgbs.zza(zzgax.size());
        }
        for (int i = 0; i < zzgax.size(); i++) {
            list.add((Object) null);
        }
        this.zza = list;
    }

    /* access modifiers changed from: package-private */
    public abstract Object zzG(List list);

    /* access modifiers changed from: package-private */
    public final void zzf(int i, Object obj) {
        List list = this.zza;
        if (list != null) {
            list.set(i, new zzgfc(obj));
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzu() {
        List list = this.zza;
        if (list != null) {
            zzc(zzG(list));
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzy(int i) {
        super.zzy(i);
        this.zza = null;
    }
}
