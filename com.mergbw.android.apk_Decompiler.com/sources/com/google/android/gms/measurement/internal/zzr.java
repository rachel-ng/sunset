package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzr {
    private final zzhj zza;

    public zzr(zzhj zzhj) {
        this.zza = zzhj;
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, Bundle bundle) {
        String str2;
        this.zza.zzl().zzt();
        if (!this.zza.zzac()) {
            if (bundle == null || bundle.isEmpty()) {
                str2 = null;
            } else {
                if (str == null || str.isEmpty()) {
                    str = "auto";
                }
                Uri.Builder builder = new Uri.Builder();
                builder.path(str);
                for (String str3 : bundle.keySet()) {
                    builder.appendQueryParameter(str3, bundle.getString(str3));
                }
                str2 = builder.build().toString();
            }
            if (!TextUtils.isEmpty(str2)) {
                this.zza.zzn().zzr.zza(str2);
                this.zza.zzn().zzs.zza(this.zza.zzb().currentTimeMillis());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        String str;
        this.zza.zzl().zzt();
        if (zzc()) {
            if (zzd()) {
                this.zza.zzn().zzr.zza((String) null);
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.SOURCE, "(not set)");
                bundle.putString(FirebaseAnalytics.Param.MEDIUM, "(not set)");
                bundle.putString("_cis", "intent");
                bundle.putLong("_cc", 1);
                this.zza.zzp().zzc("auto", "_cmpx", bundle);
            } else {
                String zza2 = this.zza.zzn().zzr.zza();
                if (TextUtils.isEmpty(zza2)) {
                    this.zza.zzj().zzm().zza("Cache still valid but referrer not found");
                } else {
                    long zza3 = ((this.zza.zzn().zzs.zza() / 3600000) - 1) * 3600000;
                    Uri parse = Uri.parse(zza2);
                    Bundle bundle2 = new Bundle();
                    Pair pair = new Pair(parse.getPath(), bundle2);
                    for (String next : parse.getQueryParameterNames()) {
                        bundle2.putString(next, parse.getQueryParameter(next));
                    }
                    ((Bundle) pair.second).putLong("_cc", zza3);
                    if (pair.first == null) {
                        str = "app";
                    } else {
                        str = (String) pair.first;
                    }
                    this.zza.zzp().zzc(str, "_cmp", (Bundle) pair.second);
                }
                this.zza.zzn().zzr.zza((String) null);
            }
            this.zza.zzn().zzs.zza(0);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb() {
        if (zzc() && zzd()) {
            this.zza.zzn().zzr.zza((String) null);
        }
    }

    private final boolean zzc() {
        return this.zza.zzn().zzs.zza() > 0;
    }

    private final boolean zzd() {
        if (zzc() && this.zza.zzb().currentTimeMillis() - this.zza.zzn().zzs.zza() > this.zza.zzf().zzc((String) null, zzbf.zzat)) {
            return true;
        }
        return false;
    }
}
