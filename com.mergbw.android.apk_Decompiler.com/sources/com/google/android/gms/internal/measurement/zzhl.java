package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final /* synthetic */ class zzhl implements SharedPreferences.OnSharedPreferenceChangeListener {
    private /* synthetic */ zzhi zza;

    public /* synthetic */ zzhl(zzhi zzhi) {
        this.zza = zzhi;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zza.zza(sharedPreferences, str);
    }
}
