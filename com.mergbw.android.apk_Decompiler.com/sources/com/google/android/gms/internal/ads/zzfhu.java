package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.client.zzq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfhu {
    public static zzq zza(Context context, List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzfgu zzfgu = (zzfgu) it.next();
            if (zzfgu.zzc) {
                arrayList.add(AdSize.FLUID);
            } else {
                arrayList.add(new AdSize(zzfgu.zza, zzfgu.zzb));
            }
        }
        return new zzq(context, (AdSize[]) arrayList.toArray(new AdSize[arrayList.size()]));
    }

    public static zzfgu zzb(zzq zzq) {
        return zzq.zzi ? new zzfgu(-3, 0, true) : new zzfgu(zzq.zze, zzq.zzb, false);
    }
}
