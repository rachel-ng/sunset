package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzjk;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzy {
    private zzfn.zzf zza;
    private Long zzb;
    private long zzc;
    private final /* synthetic */ zzu zzd;

    /* access modifiers changed from: package-private */
    public final zzfn.zzf zza(String str, zzfn.zzf zzf) {
        String zzg = zzf.zzg();
        List<zzfn.zzh> zzh = zzf.zzh();
        this.zzd.g_();
        Long l = (Long) zznl.zzb(zzf, "_eid");
        boolean z = l != null;
        if (z && zzg.equals("_ep")) {
            Preconditions.checkNotNull(l);
            this.zzd.g_();
            zzg = (String) zznl.zzb(zzf, "_en");
            if (TextUtils.isEmpty(zzg)) {
                this.zzd.zzj().zzm().zza("Extra parameter without an event name. eventId", l);
                return null;
            }
            if (this.zza == null || this.zzb == null || l.longValue() != this.zzb.longValue()) {
                Pair<zzfn.zzf, Long> zza2 = this.zzd.zzh().zza(str, l);
                if (zza2 == null || zza2.first == null) {
                    this.zzd.zzj().zzm().zza("Extra parameter without existing main event. eventName, eventId", zzg, l);
                    return null;
                }
                this.zza = (zzfn.zzf) zza2.first;
                this.zzc = ((Long) zza2.second).longValue();
                this.zzd.g_();
                this.zzb = (Long) zznl.zzb(this.zza, "_eid");
            }
            long j = this.zzc - 1;
            this.zzc = j;
            if (j <= 0) {
                zzal zzh2 = this.zzd.zzh();
                zzh2.zzt();
                zzh2.zzj().zzp().zza("Clearing complex main event info. appId", str);
                try {
                    zzh2.e_().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                } catch (SQLiteException e) {
                    zzh2.zzj().zzg().zza("Error clearing complex main event", e);
                }
            } else {
                this.zzd.zzh().zza(str, l, this.zzc, this.zza);
            }
            ArrayList arrayList = new ArrayList();
            for (zzfn.zzh next : this.zza.zzh()) {
                this.zzd.g_();
                if (zznl.zza(zzf, next.zzg()) == null) {
                    arrayList.add(next);
                }
            }
            if (!arrayList.isEmpty()) {
                arrayList.addAll(zzh);
                zzh = arrayList;
            } else {
                this.zzd.zzj().zzm().zza("No unique parameters in main event. eventName", zzg);
            }
        } else if (z) {
            this.zzb = l;
            this.zza = zzf;
            this.zzd.g_();
            long longValue = ((Long) zznl.zza(zzf, "_epc", (Object) 0L)).longValue();
            this.zzc = longValue;
            if (longValue <= 0) {
                this.zzd.zzj().zzm().zza("Complex event with zero extra param count. eventName", zzg);
            } else {
                this.zzd.zzh().zza(str, (Long) Preconditions.checkNotNull(l), this.zzc, zzf);
            }
        }
        zzjk.zzb zzcc = zzf.zzcc();
        zzjk.zzb zzb2 = zzcc;
        return (zzfn.zzf) ((zzjk) ((zzfn.zzf.zza) zzcc).zza(zzg).zzd().zza((Iterable<? extends zzfn.zzh>) zzh).zzai());
    }

    private zzy(zzu zzu) {
        this.zzd = zzu;
    }
}
