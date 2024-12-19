package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfqu extends zzfqp {
    public zzfqu(zzfqi zzfqi, HashSet hashSet, JSONObject jSONObject, long j) {
        super(zzfqi, hashSet, jSONObject, j);
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        if (zzfpy.zzg(this.zzb, this.zzd.zza())) {
            return null;
        }
        this.zzd.zze(this.zzb);
        return this.zzb.toString();
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        onPostExecute((String) obj);
    }

    /* access modifiers changed from: protected */
    public final void zza(String str) {
        zzfpe zza;
        if (!TextUtils.isEmpty(str) && (zza = zzfpe.zza()) != null) {
            for (zzfon zzfon : zza.zzc()) {
                if (this.zza.contains(zzfon.zzh())) {
                    zzfon.zzg().zzh(str, this.zzc);
                }
            }
        }
        super.onPostExecute(str);
    }
}
