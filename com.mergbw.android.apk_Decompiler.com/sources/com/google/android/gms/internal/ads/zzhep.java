package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzhep extends RuntimeException {
    public zzhep(zzhde zzhde) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzhcd zza() {
        return new zzhcd(getMessage());
    }
}
