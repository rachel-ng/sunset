package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.IBinder;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zze;
import com.google.android.gms.ads.internal.zzu;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfme implements zzfmc {
    private final Context zza;
    /* access modifiers changed from: private */
    public final zzfmu zzb;
    /* access modifiers changed from: private */
    public long zzc = 0;
    /* access modifiers changed from: private */
    public long zzd = -1;
    /* access modifiers changed from: private */
    public boolean zze = false;
    /* access modifiers changed from: private */
    public zzfmw zzf = zzfmw.FORMAT_UNKNOWN;
    /* access modifiers changed from: private */
    public zzfmy zzg = zzfmy.ORIENTATION_UNKNOWN;
    /* access modifiers changed from: private */
    public int zzh = 0;
    /* access modifiers changed from: private */
    public String zzi = "";
    /* access modifiers changed from: private */
    public String zzj = "";
    /* access modifiers changed from: private */
    public String zzk = "";
    /* access modifiers changed from: private */
    public String zzl = "";
    /* access modifiers changed from: private */
    public zzfnc zzm = zzfnc.SCAR_REQUEST_TYPE_UNSPECIFIED;
    /* access modifiers changed from: private */
    public String zzn = "";
    /* access modifiers changed from: private */
    public String zzo = "";
    /* access modifiers changed from: private */
    public String zzp = "";
    private boolean zzq = false;
    private boolean zzr = false;

    zzfme(Context context, zzfmu zzfmu) {
        this.zza = context;
        this.zzb = zzfmu;
    }

    public final synchronized zzfme zzA() {
        zzfmy zzfmy;
        this.zzh = zzu.zzq().zzm(this.zza);
        Resources resources = this.zza.getResources();
        if (resources == null) {
            zzfmy = zzfmy.ORIENTATION_UNKNOWN;
        } else {
            Configuration configuration = resources.getConfiguration();
            if (configuration == null) {
                zzfmy = zzfmy.ORIENTATION_UNKNOWN;
            } else if (configuration.orientation == 2) {
                zzfmy = zzfmy.ORIENTATION_LANDSCAPE;
            } else {
                zzfmy = zzfmy.ORIENTATION_PORTRAIT;
            }
        }
        this.zzg = zzfmy;
        this.zzc = zzu.zzB().elapsedRealtime();
        this.zzr = true;
        return this;
    }

    public final synchronized zzfme zzB() {
        this.zzd = zzu.zzB().elapsedRealtime();
        return this;
    }

    public final /* bridge */ /* synthetic */ zzfmc zza(zze zze2) {
        zzr(zze2);
        return this;
    }

    public final /* bridge */ /* synthetic */ zzfmc zzb(zzfhe zzfhe) {
        zzs(zzfhe);
        return this;
    }

    public final /* bridge */ /* synthetic */ zzfmc zzc(String str) {
        zzt(str);
        return this;
    }

    public final /* bridge */ /* synthetic */ zzfmc zzd(zzfmw zzfmw) {
        zzu(zzfmw);
        return this;
    }

    public final /* bridge */ /* synthetic */ zzfmc zze(String str) {
        zzv(str);
        return this;
    }

    public final /* bridge */ /* synthetic */ zzfmc zzf(String str) {
        zzw(str);
        return this;
    }

    public final /* bridge */ /* synthetic */ zzfmc zzg(zzfnc zzfnc) {
        zzx(zzfnc);
        return this;
    }

    public final /* bridge */ /* synthetic */ zzfmc zzh(boolean z) {
        zzy(z);
        return this;
    }

    public final /* bridge */ /* synthetic */ zzfmc zzi(Throwable th) {
        zzz(th);
        return this;
    }

    public final /* bridge */ /* synthetic */ zzfmc zzj() {
        zzA();
        return this;
    }

    public final /* bridge */ /* synthetic */ zzfmc zzk() {
        zzB();
        return this;
    }

    public final synchronized boolean zzl() {
        return this.zzr;
    }

    public final boolean zzm() {
        return !TextUtils.isEmpty(this.zzk);
    }

    public final synchronized zzfmg zzn() {
        if (this.zzq) {
            return null;
        }
        this.zzq = true;
        if (!this.zzr) {
            zzA();
        }
        if (this.zzd < 0) {
            zzB();
        }
        return new zzfmg(this, (zzfmf) null);
    }

    public final synchronized zzfme zzr(zze zze2) {
        IBinder iBinder = zze2.zze;
        if (iBinder != null) {
            zzcze zzcze = (zzcze) iBinder;
            String zzk2 = zzcze.zzk();
            if (!TextUtils.isEmpty(zzk2)) {
                this.zzi = zzk2;
            }
            String zzi2 = zzcze.zzi();
            if (!TextUtils.isEmpty(zzi2)) {
                this.zzj = zzi2;
            }
        }
        return this;
    }

    public final synchronized zzfme zzs(zzfhe zzfhe) {
        if (!TextUtils.isEmpty(zzfhe.zzb.zzb)) {
            this.zzi = zzfhe.zzb.zzb;
        }
        Iterator it = zzfhe.zza.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            zzfgt zzfgt = (zzfgt) it.next();
            if (!TextUtils.isEmpty(zzfgt.zzac)) {
                this.zzj = zzfgt.zzac;
                break;
            }
        }
        return this;
    }

    public final synchronized zzfme zzt(String str) {
        if (((Boolean) zzba.zzc().zza(zzbep.zziP)).booleanValue()) {
            this.zzp = str;
        }
        return this;
    }

    public final synchronized zzfme zzu(zzfmw zzfmw) {
        this.zzf = zzfmw;
        return this;
    }

    public final synchronized zzfme zzv(String str) {
        this.zzk = str;
        return this;
    }

    public final synchronized zzfme zzw(String str) {
        this.zzl = str;
        return this;
    }

    public final synchronized zzfme zzx(zzfnc zzfnc) {
        this.zzm = zzfnc;
        return this;
    }

    public final synchronized zzfme zzy(boolean z) {
        this.zze = z;
        return this;
    }

    public final synchronized zzfme zzz(Throwable th) {
        if (((Boolean) zzba.zzc().zza(zzbep.zziP)).booleanValue()) {
            this.zzo = zzbwj.zzf(th);
            this.zzn = (String) zzfyt.zzc(zzfxr.zzc(10)).zzd(zzbwj.zze(th)).iterator().next();
        }
        return this;
    }
}
