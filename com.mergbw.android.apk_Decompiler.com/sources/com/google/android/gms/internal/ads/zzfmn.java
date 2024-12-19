package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zze;
import com.google.android.gms.ads.nonagon.signalgeneration.zzp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfmn implements Runnable {
    private final List zza = new ArrayList();
    private final zzfmq zzb;
    private zzfmw zzc = zzfmw.FORMAT_UNKNOWN;
    private String zzd;
    private zzfnc zze = zzfnc.SCAR_REQUEST_TYPE_UNSPECIFIED;
    private String zzf;
    private zzfhe zzg;
    private zze zzh;
    private Future zzi;

    zzfmn(zzfmq zzfmq) {
        this.zzb = zzfmq;
    }

    public final synchronized void run() {
        zzi();
    }

    public final synchronized zzfmn zza(zzfmc zzfmc) {
        if (((Boolean) zzbgd.zzc.zze()).booleanValue()) {
            List list = this.zza;
            zzfmc.zzk();
            list.add(zzfmc);
            Future future = this.zzi;
            if (future != null) {
                future.cancel(false);
            }
            this.zzi = zzcci.zzd.schedule(this, (long) ((Integer) zzba.zzc().zza(zzbep.zziN)).intValue(), TimeUnit.MILLISECONDS);
        }
        return this;
    }

    public final synchronized zzfmn zzb(String str) {
        if (((Boolean) zzbgd.zzc.zze()).booleanValue() && zzfmm.zzf(str)) {
            this.zzd = str;
        }
        return this;
    }

    public final synchronized zzfmn zzc(zze zze2) {
        if (((Boolean) zzbgd.zzc.zze()).booleanValue()) {
            this.zzh = zze2;
        }
        return this;
    }

    public final synchronized zzfmn zzd(zzfmw zzfmw) {
        if (((Boolean) zzbgd.zzc.zze()).booleanValue()) {
            this.zzc = zzfmw;
        }
        return this;
    }

    public final synchronized zzfmn zze(ArrayList arrayList) {
        if (((Boolean) zzbgd.zzc.zze()).booleanValue()) {
            if (!arrayList.contains("banner")) {
                if (!arrayList.contains(AdFormat.BANNER.name())) {
                    if (!arrayList.contains("interstitial")) {
                        if (!arrayList.contains(AdFormat.INTERSTITIAL.name())) {
                            if (!arrayList.contains("native")) {
                                if (!arrayList.contains(AdFormat.NATIVE.name())) {
                                    if (!arrayList.contains("rewarded")) {
                                        if (!arrayList.contains(AdFormat.REWARDED.name())) {
                                            if (arrayList.contains("app_open_ad")) {
                                                this.zzc = zzfmw.FORMAT_APP_OPEN;
                                            } else if (arrayList.contains("rewarded_interstitial") || arrayList.contains(AdFormat.REWARDED_INTERSTITIAL.name())) {
                                                this.zzc = zzfmw.FORMAT_REWARDED_INTERSTITIAL;
                                            }
                                        }
                                    }
                                    this.zzc = zzfmw.FORMAT_REWARDED;
                                }
                            }
                            this.zzc = zzfmw.FORMAT_NATIVE;
                        }
                    }
                    this.zzc = zzfmw.FORMAT_INTERSTITIAL;
                }
            }
            this.zzc = zzfmw.FORMAT_BANNER;
        }
        return this;
    }

    public final synchronized zzfmn zzf(String str) {
        if (((Boolean) zzbgd.zzc.zze()).booleanValue()) {
            this.zzf = str;
        }
        return this;
    }

    public final synchronized zzfmn zzg(Bundle bundle) {
        if (((Boolean) zzbgd.zzc.zze()).booleanValue()) {
            this.zze = zzp.zza(bundle);
        }
        return this;
    }

    public final synchronized zzfmn zzh(zzfhe zzfhe) {
        if (((Boolean) zzbgd.zzc.zze()).booleanValue()) {
            this.zzg = zzfhe;
        }
        return this;
    }

    public final synchronized void zzi() {
        if (((Boolean) zzbgd.zzc.zze()).booleanValue()) {
            Future future = this.zzi;
            if (future != null) {
                future.cancel(false);
            }
            for (zzfmc zzfmc : this.zza) {
                if (this.zzc != zzfmw.FORMAT_UNKNOWN) {
                    zzfmc.zzd(this.zzc);
                }
                if (!TextUtils.isEmpty(this.zzd)) {
                    zzfmc.zzf(this.zzd);
                }
                if (!TextUtils.isEmpty(this.zzf) && !zzfmc.zzm()) {
                    zzfmc.zze(this.zzf);
                }
                zzfhe zzfhe = this.zzg;
                if (zzfhe != null) {
                    zzfmc.zzb(zzfhe);
                } else {
                    zze zze2 = this.zzh;
                    if (zze2 != null) {
                        zzfmc.zza(zze2);
                    }
                }
                zzfmc.zzg(this.zze);
                this.zzb.zzb(zzfmc.zzn());
            }
            this.zza.clear();
        }
    }
}
