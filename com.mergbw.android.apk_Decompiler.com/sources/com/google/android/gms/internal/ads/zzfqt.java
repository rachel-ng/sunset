package com.google.android.gms.internal.ads;

import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfqt extends zzfqp {
    public zzfqt(zzfqi zzfqi, HashSet hashSet, JSONObject jSONObject, long j) {
        super(zzfqi, hashSet, jSONObject, j);
    }

    private final void zzc(String str) {
        zzfpe zza = zzfpe.zza();
        if (zza != null) {
            for (zzfon zzfon : zza.zzc()) {
                if (this.zza.contains(zzfon.zzh())) {
                    zzfon.zzg().zzd(str, this.zzc);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        return this.zzb.toString();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        zzc(str);
        super.onPostExecute(str);
    }

    /* access modifiers changed from: protected */
    public final void zza(String str) {
        zzc(str);
        super.onPostExecute(str);
    }
}
