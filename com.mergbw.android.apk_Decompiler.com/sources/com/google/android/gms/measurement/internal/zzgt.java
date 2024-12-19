package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.collection.LruCache;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zzff;
import com.google.android.gms.internal.measurement.zzfi;
import com.google.android.gms.internal.measurement.zzft;
import com.google.android.gms.internal.measurement.zzjk;
import com.google.android.gms.internal.measurement.zzjs;
import com.google.android.gms.internal.measurement.zzv;
import com.google.android.gms.measurement.internal.zzin;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final class zzgt extends zzmx implements zzai {
    final LruCache<String, zzb> zza = new zzgz(this, 20);
    final zzv zzb = new zzgy(this);
    /* access modifiers changed from: private */
    public final Map<String, Map<String, String>> zzc = new ArrayMap();
    private final Map<String, Set<String>> zzd = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zze = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzg = new ArrayMap();
    private final Map<String, zzfi.zzd> zzh = new ArrayMap();
    private final Map<String, Map<String, Integer>> zzi = new ArrayMap();
    private final Map<String, String> zzj = new ArrayMap();
    private final Map<String, String> zzk = new ArrayMap();
    private final Map<String, String> zzl = new ArrayMap();

    /* access modifiers changed from: package-private */
    public final int zzb(String str, String str2) {
        Integer num;
        zzt();
        zzv(str);
        Map map = this.zzi.get(str);
        if (map == null || (num = (Integer) map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final long zza(String str) {
        String zza2 = zza(str, "measurement.account.time_zone_offset_minutes");
        if (TextUtils.isEmpty(zza2)) {
            return 0;
        }
        try {
            return Long.parseLong(zza2);
        } catch (NumberFormatException e) {
            zzj().zzu().zza("Unable to parse timezone offset. appId", zzfw.zza(str), e);
            return 0;
        }
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    static /* synthetic */ zzb zza(zzgt zzgt, String str) {
        zzgt.zzal();
        Preconditions.checkNotEmpty(str);
        if (!zzgt.zzl(str)) {
            return null;
        }
        if (!zzgt.zzh.containsKey(str) || zzgt.zzh.get(str) == null) {
            zzgt.zzv(str);
        } else {
            zzgt.zza(str, zzgt.zzh.get(str));
        }
        return zzgt.zza.snapshot().get(str);
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

    /* access modifiers changed from: package-private */
    public final zzim zza(String str, zzin.zza zza2) {
        zzt();
        zzv(str);
        zzfi.zza zzb2 = zzb(str);
        if (zzb2 == null) {
            return zzim.UNINITIALIZED;
        }
        for (zzfi.zza.C0008zza next : zzb2.zzf()) {
            if (zza(next.zzc()) == zza2) {
                int i = zzha.zzc[next.zzb().ordinal()];
                if (i == 1) {
                    return zzim.DENIED;
                }
                if (i != 2) {
                    return zzim.UNINITIALIZED;
                }
                return zzim.GRANTED;
            }
        }
        return zzim.UNINITIALIZED;
    }

    /* access modifiers changed from: package-private */
    public final zzin.zza zzb(String str, zzin.zza zza2) {
        zzt();
        zzv(str);
        zzfi.zza zzb2 = zzb(str);
        if (zzb2 == null) {
            return null;
        }
        for (zzfi.zza.zzc next : zzb2.zze()) {
            if (zza2 == zza(next.zzc())) {
                return zza(next.zzb());
            }
        }
        return null;
    }

    private static zzin.zza zza(zzfi.zza.zze zze2) {
        int i = zzha.zzb[zze2.ordinal()];
        if (i == 1) {
            return zzin.zza.AD_STORAGE;
        }
        if (i == 2) {
            return zzin.zza.ANALYTICS_STORAGE;
        }
        if (i == 3) {
            return zzin.zza.AD_USER_DATA;
        }
        if (i != 4) {
            return null;
        }
        return zzin.zza.AD_PERSONALIZATION;
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
    public final zzfi.zza zzb(String str) {
        zzt();
        zzv(str);
        zzfi.zzd zzc2 = zzc(str);
        if (zzc2 == null || !zzc2.zzp()) {
            return null;
        }
        return zzc2.zzd();
    }

    /* access modifiers changed from: protected */
    public final zzfi.zzd zzc(String str) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        zzv(str);
        return this.zzh.get(str);
    }

    private final zzfi.zzd zza(String str, byte[] bArr) {
        if (bArr == null) {
            return zzfi.zzd.zzg();
        }
        try {
            zzfi.zzd zzd2 = (zzfi.zzd) ((zzjk) ((zzfi.zzd.zza) zznl.zza(zzfi.zzd.zze(), bArr)).zzai());
            zzfy zzp = zzj().zzp();
            String str2 = null;
            Long valueOf = zzd2.zzs() ? Long.valueOf(zzd2.zzc()) : null;
            if (zzd2.zzq()) {
                str2 = zzd2.zzi();
            }
            zzp.zza("Parsed config. version, gmp_app_id", valueOf, str2);
            return zzd2;
        } catch (zzjs e) {
            zzj().zzu().zza("Unable to merge remote config. appId", zzfw.zza(str), e);
            return zzfi.zzd.zzg();
        } catch (RuntimeException e2) {
            zzj().zzu().zza("Unable to merge remote config. appId", zzfw.zza(str), e2);
            return zzfi.zzd.zzg();
        }
    }

    public final String zza(String str, String str2) {
        zzt();
        zzv(str);
        Map map = this.zzc.get(str);
        if (map != null) {
            return (String) map.get(str2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final String zzd(String str) {
        zzt();
        return this.zzl.get(str);
    }

    /* access modifiers changed from: protected */
    public final String zze(String str) {
        zzt();
        return this.zzk.get(str);
    }

    /* access modifiers changed from: package-private */
    public final String zzf(String str) {
        zzt();
        zzv(str);
        return this.zzj.get(str);
    }

    private static Map<String, String> zza(zzfi.zzd zzd2) {
        ArrayMap arrayMap = new ArrayMap();
        if (zzd2 != null) {
            for (zzfi.zzg next : zzd2.zzn()) {
                arrayMap.put(next.zzb(), next.zzc());
            }
        }
        return arrayMap;
    }

    /* access modifiers changed from: package-private */
    public final Set<String> zzg(String str) {
        zzt();
        zzv(str);
        return this.zzd.get(str);
    }

    /* access modifiers changed from: package-private */
    public final SortedSet<String> zzh(String str) {
        zzt();
        zzv(str);
        TreeSet treeSet = new TreeSet();
        zzfi.zza zzb2 = zzb(str);
        if (zzb2 == null) {
            return treeSet;
        }
        for (zzfi.zza.zzf zzb3 : zzb2.zzc()) {
            treeSet.add(zzb3.zzb());
        }
        return treeSet;
    }

    zzgt(zznc zznc) {
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

    /* access modifiers changed from: protected */
    public final void zzi(String str) {
        zzt();
        this.zzk.put(str, (Object) null);
    }

    private final void zza(String str, zzfi.zzd.zza zza2) {
        HashSet hashSet = new HashSet();
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (zza2 != null) {
            for (zzfi.zzb zzb2 : zza2.zze()) {
                hashSet.add(zzb2.zzb());
            }
            for (int i = 0; i < zza2.zza(); i++) {
                zzjk.zzb zzcc = zza2.zza(i).zzcc();
                zzjk.zzb zzb3 = zzcc;
                zzfi.zzc.zza zza3 = (zzfi.zzc.zza) zzcc;
                if (zza3.zzb().isEmpty()) {
                    zzj().zzu().zza("EventConfig contained null event name");
                } else {
                    String zzb4 = zza3.zzb();
                    String zzb5 = zziq.zzb(zza3.zzb());
                    if (!TextUtils.isEmpty(zzb5)) {
                        zza3 = zza3.zza(zzb5);
                        zza2.zza(i, zza3);
                    }
                    if (zza3.zze() && zza3.zzc()) {
                        arrayMap.put(zzb4, true);
                    }
                    if (zza3.zzf() && zza3.zzd()) {
                        arrayMap2.put(zza3.zzb(), true);
                    }
                    if (zza3.zzg()) {
                        if (zza3.zza() < 2 || zza3.zza() > 65535) {
                            zzj().zzu().zza("Invalid sampling rate. Event name, sample rate", zza3.zzb(), Integer.valueOf(zza3.zza()));
                        } else {
                            arrayMap3.put(zza3.zzb(), Integer.valueOf(zza3.zza()));
                        }
                    }
                }
            }
        }
        this.zzd.put(str, hashSet);
        this.zze.put(str, arrayMap);
        this.zzg.put(str, arrayMap2);
        this.zzi.put(str, arrayMap3);
    }

    private final void zzv(String str) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        if (this.zzh.get(str) == null) {
            zzan zzf = zzh().zzf(str);
            if (zzf == null) {
                this.zzc.put(str, (Object) null);
                this.zze.put(str, (Object) null);
                this.zzd.put(str, (Object) null);
                this.zzg.put(str, (Object) null);
                this.zzh.put(str, (Object) null);
                this.zzj.put(str, (Object) null);
                this.zzk.put(str, (Object) null);
                this.zzl.put(str, (Object) null);
                this.zzi.put(str, (Object) null);
                return;
            }
            zzjk.zzb zzcc = zza(str, zzf.zza).zzcc();
            zzjk.zzb zzb2 = zzcc;
            zzfi.zzd.zza zza2 = (zzfi.zzd.zza) zzcc;
            zza(str, zza2);
            this.zzc.put(str, zza((zzfi.zzd) ((zzjk) zza2.zzai())));
            this.zzh.put(str, (zzfi.zzd) ((zzjk) zza2.zzai()));
            zza(str, (zzfi.zzd) ((zzjk) zza2.zzai()));
            this.zzj.put(str, zza2.zzc());
            this.zzk.put(str, zzf.zzb);
            this.zzl.put(str, zzf.zzc);
        }
    }

    private final void zza(String str, zzfi.zzd zzd2) {
        if (zzd2.zza() == 0) {
            this.zza.remove(str);
            return;
        }
        zzj().zzp().zza("EES programs found", Integer.valueOf(zzd2.zza()));
        zzft.zzc zzc2 = zzd2.zzm().get(0);
        try {
            zzb zzb2 = new zzb();
            zzb2.zza("internal.remoteConfig", new zzgu(this, str));
            zzb2.zza("internal.appMetadata", new zzgx(this, str));
            zzb2.zza("internal.logger", new zzgw(this));
            zzb2.zza(zzc2);
            this.zza.put(str, zzb2);
            zzj().zzp().zza("EES program loaded for appId, activities", str, Integer.valueOf(zzc2.zza().zza()));
            for (zzft.zzb zzb3 : zzc2.zza().zzd()) {
                zzj().zzp().zza("EES program activity", zzb3.zzb());
            }
        } catch (zzc unused) {
            zzj().zzg().zza("Failed to load EES program. appId", str);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzj(String str) {
        zzt();
        this.zzh.remove(str);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzk(String str) {
        zzt();
        zzfi.zzd zzc2 = zzc(str);
        if (zzc2 == null) {
            return false;
        }
        return zzc2.zzo();
    }

    public final boolean zzl(String str) {
        zzfi.zzd zzd2;
        if (TextUtils.isEmpty(str) || (zzd2 = this.zzh.get(str)) == null || zzd2.zza() == 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzm(String str) {
        return IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(zza(str, "measurement.upload.blacklist_internal"));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc(String str, zzin.zza zza2) {
        zzt();
        zzv(str);
        zzfi.zza zzb2 = zzb(str);
        if (zzb2 == null) {
            return false;
        }
        Iterator<zzfi.zza.C0008zza> it = zzb2.zzd().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            zzfi.zza.C0008zza next = it.next();
            if (zza2 == zza(next.zzc())) {
                if (next.zzb() == zzfi.zza.zzd.GRANTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzn(String str) {
        zzt();
        zzv(str);
        zzfi.zza zzb2 = zzb(str);
        if (zzb2 != null && zzb2.zzh() && !zzb2.zzg()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc(String str, String str2) {
        Boolean bool;
        zzt();
        zzv(str);
        if ("ecommerce_purchase".equals(str2) || FirebaseAnalytics.Event.PURCHASE.equals(str2) || FirebaseAnalytics.Event.REFUND.equals(str2)) {
            return true;
        }
        Map map = this.zzg.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzd(String str, String str2) {
        Boolean bool;
        zzt();
        zzv(str);
        if (zzm(str) && zznp.zzg(str2)) {
            return true;
        }
        if (zzo(str) && zznp.zzh(str2)) {
            return true;
        }
        Map map = this.zze.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzo(String str) {
        return IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(zza(str, "measurement.upload.blacklist_public"));
    }

    /* access modifiers changed from: protected */
    public final boolean zza(String str, byte[] bArr, String str2, String str3) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        zzjk.zzb zzcc = zza(str, bArr).zzcc();
        zzjk.zzb zzb2 = zzcc;
        zzfi.zzd.zza zza2 = (zzfi.zzd.zza) zzcc;
        if (zza2 == null) {
            return false;
        }
        zza(str, zza2);
        zza(str, (zzfi.zzd) ((zzjk) zza2.zzai()));
        this.zzh.put(str, (zzfi.zzd) ((zzjk) zza2.zzai()));
        this.zzj.put(str, zza2.zzc());
        this.zzk.put(str, str2);
        this.zzl.put(str, str3);
        this.zzc.put(str, zza((zzfi.zzd) ((zzjk) zza2.zzai())));
        zzh().zza(str, (List<zzff.zza>) new ArrayList(zza2.zzd()));
        try {
            zza2.zzb();
            bArr = ((zzfi.zzd) ((zzjk) zza2.zzai())).zzbz();
        } catch (RuntimeException e) {
            zzj().zzu().zza("Unable to serialize reduced-size config. Storing full config instead. appId", zzfw.zza(str), e);
        }
        zzal zzh2 = zzh();
        Preconditions.checkNotEmpty(str);
        zzh2.zzt();
        zzh2.zzal();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        contentValues.put("config_last_modified_time", str2);
        contentValues.put("e_tag", str3);
        try {
            if (((long) zzh2.e_().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                zzh2.zzj().zzg().zza("Failed to update remote config (got 0). appId", zzfw.zza(str));
            }
        } catch (SQLiteException e2) {
            zzh2.zzj().zzg().zza("Error storing remote config. appId", zzfw.zza(str), e2);
        }
        this.zzh.put(str, (zzfi.zzd) ((zzjk) zza2.zzai()));
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzp(String str) {
        zzt();
        zzv(str);
        return this.zzd.get(str) != null && this.zzd.get(str).contains("app_instance_id");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzq(String str) {
        zzt();
        zzv(str);
        if (this.zzd.get(str) != null) {
            return this.zzd.get(str).contains("device_model") || this.zzd.get(str).contains("device_info");
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzr(String str) {
        zzt();
        zzv(str);
        return this.zzd.get(str) != null && this.zzd.get(str).contains("enhanced_user_id");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzs(String str) {
        zzt();
        zzv(str);
        return this.zzd.get(str) != null && this.zzd.get(str).contains("google_signals");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzt(String str) {
        zzt();
        zzv(str);
        if (this.zzd.get(str) != null) {
            return this.zzd.get(str).contains("os_version") || this.zzd.get(str).contains("device_info");
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzu(String str) {
        zzt();
        zzv(str);
        return this.zzd.get(str) != null && this.zzd.get(str).contains("user_id");
    }
}
