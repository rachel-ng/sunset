package com.google.android.gms.internal.ads;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgex extends zzgeu {
    private zzgex() {
        throw null;
    }

    /* synthetic */ zzgex(zzgew zzgew) {
        super((zzget) null);
    }

    /* access modifiers changed from: package-private */
    public final int zza(zzgey zzgey) {
        int zzz;
        synchronized (zzgey) {
            zzz = zzgey.remaining - 1;
            zzgey.remaining = zzz;
        }
        return zzz;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzgey zzgey, @CheckForNull Set set, Set set2) {
        synchronized (zzgey) {
            if (zzgey.seenExceptions == null) {
                zzgey.seenExceptions = set2;
            }
        }
    }
}
