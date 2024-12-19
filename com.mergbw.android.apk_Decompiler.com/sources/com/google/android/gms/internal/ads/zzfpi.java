package com.google.android.gms.internal.ads;

import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfpi {
    private static final Pattern zza = Pattern.compile("^[a-zA-Z0-9 ]+$");
    private final List zzb = new ArrayList();

    public final List zza() {
        return this.zzb;
    }

    public final void zzb(View view, zzfoq zzfoq, String str) {
        zzfph zzfph;
        if (view == null) {
            throw new IllegalArgumentException("FriendlyObstruction is null");
        } else if (zza.matcher("Ad overlay").matches()) {
            Iterator it = this.zzb.iterator();
            while (true) {
                if (!it.hasNext()) {
                    zzfph = null;
                    break;
                }
                zzfph = (zzfph) it.next();
                if (zzfph.zzb().get() == view) {
                    break;
                }
            }
            if (zzfph == null) {
                this.zzb.add(new zzfph(view, zzfoq, "Ad overlay"));
            }
        } else {
            throw new IllegalArgumentException("FriendlyObstruction has detailed reason that contains characters not in [a-z][A-Z][0-9] or space");
        }
    }

    public final void zzc() {
        this.zzb.clear();
    }
}
