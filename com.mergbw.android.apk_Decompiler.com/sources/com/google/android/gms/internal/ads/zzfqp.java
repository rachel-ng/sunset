package com.google.android.gms.internal.ads;

import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzfqp extends zzfqq {
    protected final HashSet zza;
    protected final JSONObject zzb;
    protected final long zzc;

    public zzfqp(zzfqi zzfqi, HashSet hashSet, JSONObject jSONObject, long j) {
        super(zzfqi);
        this.zza = new HashSet(hashSet);
        this.zzb = jSONObject;
        this.zzc = j;
    }
}
