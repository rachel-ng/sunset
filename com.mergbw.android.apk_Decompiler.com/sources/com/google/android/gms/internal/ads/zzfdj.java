package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzfdj implements zzgfa {
    public final /* synthetic */ zzfdl zza;
    public final /* synthetic */ zzfjl zzb;
    public final /* synthetic */ zzcvx zzc;

    public /* synthetic */ zzfdj(zzfdl zzfdl, zzfjl zzfjl, zzcvx zzcvx) {
        this.zza = zzfdl;
        this.zzb = zzfjl;
        this.zzc = zzcvx;
    }

    public final ListenableFuture zza(Object obj) {
        zzfhf zzfhf = (zzfhf) obj;
        this.zzb.zzb = zzfhf;
        Iterator it = zzfhf.zzb.zza.iterator();
        boolean z = false;
        loop0:
        while (true) {
            if (it.hasNext()) {
                Iterator it2 = ((zzfgt) it.next()).zza.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (!((String) it2.next()).contains("FirstPartyRenderer")) {
                            break loop0;
                        }
                        z = true;
                    }
                }
            } else if (z) {
                return this.zzc.zzi(zzgft.zzh(zzfhf));
            }
        }
        return zzgft.zzh((Object) null);
    }
}
