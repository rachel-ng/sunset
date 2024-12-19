package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
enum zzak {
    UNSET('0'),
    REMOTE_DEFAULT('1'),
    REMOTE_DELEGATION('2'),
    MANIFEST('3'),
    INITIALIZATION('4'),
    API('5'),
    CHILD_ACCOUNT('6'),
    TCF('7'),
    REMOTE_ENFORCED_DEFAULT('8'),
    FAILSAFE('9');
    
    /* access modifiers changed from: private */
    public final char zzl;

    public static zzak zza(char c2) {
        for (zzak zzak : values()) {
            if (zzak.zzl == c2) {
                return zzak;
            }
        }
        return UNSET;
    }

    private zzak(char c2) {
        this.zzl = c2;
    }
}
