package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.Clock;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final class zzmc extends zzmx {
    public final zzgm zza;
    public final zzgm zzb;
    public final zzgm zzc;
    public final zzgm zzd;
    public final zzgm zze;
    private final Map<String, zzmb> zzg = new HashMap();

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        return false;
    }

    @Deprecated
    private final Pair<String, Boolean> zza(String str) {
        zzmb zzmb;
        AdvertisingIdClient.Info info;
        zzt();
        long elapsedRealtime = zzb().elapsedRealtime();
        zzmb zzmb2 = this.zzg.get(str);
        if (zzmb2 != null && elapsedRealtime < zzmb2.zzc) {
            return new Pair<>(zzmb2.zza, Boolean.valueOf(zzmb2.zzb));
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        long zzd2 = zze().zzd(str) + elapsedRealtime;
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(zza());
        } catch (PackageManager.NameNotFoundException unused) {
            if (zzmb2 != null) {
                try {
                    if (elapsedRealtime < zzmb2.zzc + zze().zzc(str, zzbf.zzb)) {
                        return new Pair<>(zzmb2.zza, Boolean.valueOf(zzmb2.zzb));
                    }
                } catch (Exception e) {
                    zzj().zzc().zza("Unable to get advertising id", e);
                    zzmb = new zzmb("", false, zzd2);
                }
            }
            info = null;
        }
        if (info == null) {
            return new Pair<>("00000000-0000-0000-0000-000000000000", false);
        }
        String id = info.getId();
        if (id != null) {
            zzmb = new zzmb(id, info.isLimitAdTrackingEnabled(), zzd2);
        } else {
            zzmb = new zzmb("", info.isLimitAdTrackingEnabled(), zzd2);
        }
        this.zzg.put(str, zzmb);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(zzmb.zza, Boolean.valueOf(zzmb.zzb));
    }

    /* access modifiers changed from: package-private */
    public final Pair<String, Boolean> zza(String str, zzin zzin) {
        if (zzin.zzi()) {
            return zza(str);
        }
        return new Pair<>("", false);
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

    /* access modifiers changed from: package-private */
    @Deprecated
    public final String zza(String str, boolean z) {
        String str2;
        zzt();
        if (z) {
            str2 = (String) zza(str).first;
        } else {
            str2 = "00000000-0000-0000-0000-000000000000";
        }
        MessageDigest zzu = zznp.zzu();
        if (zzu == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzu.digest(str2.getBytes()))});
    }

    zzmc(zznc zznc) {
        super(zznc);
        zzgh zzk = zzk();
        Objects.requireNonNull(zzk);
        this.zza = new zzgm(zzk, "last_delete_stale", 0);
        zzgh zzk2 = zzk();
        Objects.requireNonNull(zzk2);
        this.zzb = new zzgm(zzk2, "backoff", 0);
        zzgh zzk3 = zzk();
        Objects.requireNonNull(zzk3);
        this.zzc = new zzgm(zzk3, "last_upload", 0);
        zzgh zzk4 = zzk();
        Objects.requireNonNull(zzk4);
        this.zzd = new zzgm(zzk4, "last_upload_attempt", 0);
        zzgh zzk5 = zzk();
        Objects.requireNonNull(zzk5);
        this.zze = new zzgm(zzk5, "midnight_offset", 0);
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
