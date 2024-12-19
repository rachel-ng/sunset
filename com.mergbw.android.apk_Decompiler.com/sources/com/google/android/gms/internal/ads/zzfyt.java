package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzfyt {
    /* access modifiers changed from: private */
    public final zzfxr zza;
    private final zzfys zzb;

    private zzfyt(zzfys zzfys) {
        zzfxr zzfxr = zzfxq.zza;
        this.zzb = zzfys;
        this.zza = zzfxr;
    }

    public static zzfyt zzb(int i) {
        return new zzfyt(new zzfyp(4000));
    }

    public static zzfyt zzc(zzfxr zzfxr) {
        return new zzfyt(new zzfyn(zzfxr));
    }

    /* access modifiers changed from: private */
    public final Iterator zzg(CharSequence charSequence) {
        return this.zzb.zza(this, charSequence);
    }

    public final Iterable zzd(CharSequence charSequence) {
        charSequence.getClass();
        return new zzfyq(this, charSequence);
    }

    public final List zzf(CharSequence charSequence) {
        charSequence.getClass();
        Iterator zzg = zzg(charSequence);
        ArrayList arrayList = new ArrayList();
        while (zzg.hasNext()) {
            arrayList.add((String) zzg.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
