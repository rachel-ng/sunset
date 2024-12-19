package com.google.android.gms.internal.ads;

import android.os.AsyncTask;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzfqq extends AsyncTask {
    private zzfqr zza;
    protected final zzfqi zzd;

    public zzfqq(zzfqi zzfqi) {
        this.zzd = zzfqi;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zza */
    public void onPostExecute(String str) {
        zzfqr zzfqr = this.zza;
        if (zzfqr != null) {
            zzfqr.zza(this);
        }
    }

    public final void zzb(zzfqr zzfqr) {
        this.zza = zzfqr;
    }
}
