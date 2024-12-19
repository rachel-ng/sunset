package com.google.android.gms.internal.ads;

import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzfym extends zzfyr {
    final /* synthetic */ zzfyn zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfym(zzfyn zzfyn, zzfyt zzfyt, CharSequence charSequence) {
        super(zzfyt, charSequence);
        this.zza = zzfyn;
    }

    /* access modifiers changed from: package-private */
    public final int zzc(int i) {
        return i + 1;
    }

    /* access modifiers changed from: package-private */
    public final int zzd(int i) {
        CharSequence charSequence = this.zzb;
        int length = charSequence.length();
        zzfyg.zzb(i, length, FirebaseAnalytics.Param.INDEX);
        while (i < length) {
            zzfyn zzfyn = this.zza;
            if (zzfyn.zza.zzb(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
