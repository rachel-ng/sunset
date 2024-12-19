package com.google.android.gms.internal.consent_sdk;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzdo implements zzdn {
    private static final zzdo zza = new zzdo((Object) null);
    private final Object zzb;

    private zzdo(Object obj) {
        this.zzb = obj;
    }

    public static zzdn zzb(Object obj) {
        if (obj != null) {
            return new zzdo(obj);
        }
        throw new NullPointerException("instance cannot be null");
    }

    public final Object zza() {
        return this.zzb;
    }
}
