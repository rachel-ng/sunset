package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
abstract class zzmx extends zzmy {
    private boolean zza;

    zzmx(zznc zznc) {
        super(zznc);
        this.zzf.zzu();
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzc();

    /* access modifiers changed from: protected */
    public final void zzal() {
        if (!zzan()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzam() {
        if (!this.zza) {
            zzc();
            this.zzf.zzt();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzan() {
        return this.zza;
    }
}
