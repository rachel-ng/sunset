package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgfb extends zzgfd {
    zzgfb(zzgax zzgax, boolean z) {
        super(zzgax, z);
        zzv();
    }

    public final /* bridge */ /* synthetic */ Object zzG(List list) {
        ArrayList zza = zzgbs.zza(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzgfc zzgfc = (zzgfc) it.next();
            zza.add(zzgfc != null ? zzgfc.zza : null);
        }
        return Collections.unmodifiableList(zza);
    }
}
