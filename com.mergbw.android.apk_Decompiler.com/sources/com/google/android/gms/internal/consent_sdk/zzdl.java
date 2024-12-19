package com.google.android.gms.internal.consent_sdk;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzdl implements zzdn {
    private zzdq zza;

    public static void zzb(zzdq zzdq, zzdq zzdq2) {
        zzdl zzdl = (zzdl) zzdq;
        if (zzdl.zza == null) {
            zzdl.zza = zzdq2;
            return;
        }
        throw new IllegalStateException();
    }

    public final Object zza() {
        zzdq zzdq = this.zza;
        if (zzdq != null) {
            return zzdq.zza();
        }
        throw new IllegalStateException();
    }
}
