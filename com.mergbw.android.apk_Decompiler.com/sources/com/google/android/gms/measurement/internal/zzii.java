package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
abstract class zzii extends zzij {
    private boolean zza;

    zzii(zzhj zzhj) {
        super(zzhj);
        this.zzu.zzaa();
    }

    /* access modifiers changed from: protected */
    public void zzaa() {
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzo();

    /* access modifiers changed from: protected */
    public final void zzac() {
        if (!zzaf()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzad() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzo()) {
            this.zzu.zzz();
            this.zza = true;
        }
    }

    public final void zzae() {
        if (!this.zza) {
            zzaa();
            this.zzu.zzz();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaf() {
        return this.zza;
    }
}
