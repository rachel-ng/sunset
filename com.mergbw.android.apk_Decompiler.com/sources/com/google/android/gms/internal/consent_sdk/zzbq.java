package com.google.android.gms.internal.consent_sdk;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final /* synthetic */ class zzbq implements Runnable {
    public final /* synthetic */ zzbu zza;
    public final /* synthetic */ String zzb;

    public /* synthetic */ zzbq(zzbu zzbu, String str) {
        this.zza = zzbu;
        this.zzb = str;
    }

    public final void run() {
        zzcs.zza(this.zza, this.zzb);
    }
}
