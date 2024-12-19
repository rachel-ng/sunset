package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfok {
    private final zzfov zza;
    private final zzfov zzb;
    private final boolean zzc;
    private final zzfoo zzd;
    private final zzfor zze;

    private zzfok(zzfoo zzfoo, zzfor zzfor, zzfov zzfov, zzfov zzfov2, boolean z) {
        this.zzd = zzfoo;
        this.zze = zzfor;
        this.zza = zzfov;
        if (zzfov2 == null) {
            this.zzb = zzfov.NONE;
        } else {
            this.zzb = zzfov2;
        }
        this.zzc = z;
    }

    public static zzfok zza(zzfoo zzfoo, zzfor zzfor, zzfov zzfov, zzfov zzfov2, boolean z) {
        zzfqd.zzc(zzfoo, "CreativeType is null");
        zzfqd.zzc(zzfor, "ImpressionType is null");
        zzfqd.zzc(zzfov, "Impression owner is null");
        if (zzfov == zzfov.NONE) {
            throw new IllegalArgumentException("Impression owner is none");
        } else if (zzfoo == zzfoo.DEFINED_BY_JAVASCRIPT && zzfov == zzfov.NATIVE) {
            throw new IllegalArgumentException("ImpressionType/CreativeType can only be defined as DEFINED_BY_JAVASCRIPT if Impression Owner is JavaScript");
        } else if (zzfor != zzfor.DEFINED_BY_JAVASCRIPT || zzfov != zzfov.NATIVE) {
            return new zzfok(zzfoo, zzfor, zzfov, zzfov2, z);
        } else {
            throw new IllegalArgumentException("ImpressionType/CreativeType can only be defined as DEFINED_BY_JAVASCRIPT if Impression Owner is JavaScript");
        }
    }

    public final JSONObject zzb() {
        JSONObject jSONObject = new JSONObject();
        zzfpy.zze(jSONObject, "impressionOwner", this.zza);
        zzfpy.zze(jSONObject, "mediaEventsOwner", this.zzb);
        zzfpy.zze(jSONObject, "creativeType", this.zzd);
        zzfpy.zze(jSONObject, "impressionType", this.zze);
        zzfpy.zze(jSONObject, "isolateVerificationScripts", Boolean.valueOf(this.zzc));
        return jSONObject;
    }
}
