package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final /* synthetic */ class zzjd implements SharedPreferences.OnSharedPreferenceChangeListener {
    private /* synthetic */ zziv zza;

    public /* synthetic */ zzjd(zziv zziv) {
        this.zza = zziv;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zza.zza(sharedPreferences, str);
    }
}
