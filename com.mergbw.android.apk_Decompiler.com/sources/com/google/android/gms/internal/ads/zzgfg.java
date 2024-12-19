package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgfg extends zzges {
    /* access modifiers changed from: private */
    @CheckForNull
    public zzgff zza;

    zzgfg(zzgax zzgax, boolean z, Executor executor, Callable callable) {
        super(zzgax, z, false);
        this.zza = new zzgfe(this, callable, executor);
        zzv();
    }

    /* access modifiers changed from: package-private */
    public final void zzf(int i, @CheckForNull Object obj) {
    }

    /* access modifiers changed from: protected */
    public final void zzq() {
        zzgff zzgff = this.zza;
        if (zzgff != null) {
            zzgff.zzh();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzu() {
        zzgff zzgff = this.zza;
        if (zzgff != null) {
            zzgff.zzf();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzy(int i) {
        super.zzy(i);
        if (i == 1) {
            this.zza = null;
        }
    }
}
