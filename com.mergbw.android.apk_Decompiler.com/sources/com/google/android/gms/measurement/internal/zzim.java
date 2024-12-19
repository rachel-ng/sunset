package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public enum zzim {
    UNINITIALIZED("uninitialized"),
    POLICY("eu_consent_policy"),
    DENIED("denied"),
    GRANTED("granted");
    
    private final String zzf;

    public final String toString() {
        return this.zzf;
    }

    private zzim(String str) {
        this.zzf = str;
    }
}
