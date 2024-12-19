package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.alibaba.android.arouter.utils.Consts;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzfi;
import com.google.android.gms.internal.measurement.zzpn;
import java.util.HashMap;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final class zzna extends zzmy {
    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zzu zzg() {
        return super.zzg();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    public final /* bridge */ /* synthetic */ zzal zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzax zzf() {
        return super.zzf();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzfr zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzfw zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzk() {
        return super.zzk();
    }

    public final /* bridge */ /* synthetic */ zzgt zzm() {
        return super.zzm();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhc zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zzmc zzn() {
        return super.zzn();
    }

    public final zzmz zza(String str) {
        zzg zze;
        if (zzpn.zza() && zze().zza(zzbf.zzbs)) {
            zzq();
            if (zznp.zzf(str)) {
                zzj().zzp().zza("sgtm feature flag enabled.");
                zzg zze2 = zzh().zze(str);
                if (zze2 == null) {
                    return new zzmz(zzb(str), 1);
                }
                String zzad = zze2.zzad();
                zzfi.zzd zzc = zzm().zzc(str);
                if (zzc == null || (zze = zzh().zze(str)) == null || ((!zzc.zzr() || zzc.zzh().zza() != 100) && !zzq().zzd(str, zze.zzam()) && (TextUtils.isEmpty(zzad) || zzad.hashCode() % 100 >= zzc.zzh().zza()))) {
                    return new zzmz(zzb(str), 1);
                }
                zzmz zzmz = null;
                if (zze2.zzat()) {
                    zzj().zzp().zza("sgtm upload enabled in manifest.");
                    zzfi.zzd zzc2 = zzm().zzc(zze2.zzac());
                    if (zzc2 != null && zzc2.zzr()) {
                        String zze3 = zzc2.zzh().zze();
                        if (!TextUtils.isEmpty(zze3)) {
                            String zzd = zzc2.zzh().zzd();
                            zzj().zzp().zza("sgtm configured with upload_url, server_info", zze3, TextUtils.isEmpty(zzd) ? "Y" : "N");
                            if (TextUtils.isEmpty(zzd)) {
                                zzmz = new zzmz(zze3, 3);
                            } else {
                                HashMap hashMap = new HashMap();
                                hashMap.put("x-sgtm-server-info", zzd);
                                if (!TextUtils.isEmpty(zze2.zzam())) {
                                    hashMap.put("x-gtm-server-preview", zze2.zzam());
                                }
                                zzmz = new zzmz(zze3, hashMap, 3);
                            }
                        }
                    }
                }
                if (zzmz != null) {
                    return zzmz;
                }
            }
        }
        return new zzmz(zzb(str), 1);
    }

    public final /* bridge */ /* synthetic */ zzna zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zznl g_() {
        return super.g_();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zznp zzq() {
        return super.zzq();
    }

    private final String zzb(String str) {
        String zzf = zzm().zzf(str);
        if (TextUtils.isEmpty(zzf)) {
            return zzbf.zzq.zza(null);
        }
        Uri parse = Uri.parse(zzbf.zzq.zza(null));
        Uri.Builder buildUpon = parse.buildUpon();
        String authority = parse.getAuthority();
        buildUpon.authority(zzf + Consts.DOT + authority);
        return buildUpon.build().toString();
    }

    zzna(zznc zznc) {
        super(zznc);
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }
}
