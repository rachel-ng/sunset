package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.util.client.zzr;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfoe {
    private final Context zza;
    private final Executor zzb;
    private final zzr zzc;
    private final zzfmq zzd;

    zzfoe(Context context, Executor executor, zzr zzr, zzfmq zzfmq) {
        this.zza = context;
        this.zzb = executor;
        this.zzc = zzr;
        this.zzd = zzfmq;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(String str) {
        this.zzc.zza(str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(String str, zzfmn zzfmn) {
        zzfmc zza2 = zzfmb.zza(this.zza, zzfmu.CUI_NAME_PING);
        zza2.zzj();
        zza2.zzh(this.zzc.zza(str));
        if (zzfmn == null) {
            this.zzd.zzb(zza2.zzn());
            return;
        }
        zzfmn.zza(zza2);
        zzfmn.zzi();
    }

    public final void zzc(String str, zzfmn zzfmn) {
        if (!zzfmq.zza() || !((Boolean) zzbgd.zzd.zze()).booleanValue()) {
            this.zzb.execute(new zzfoc(this, str));
        } else {
            this.zzb.execute(new zzfod(this, str, zzfmn));
        }
    }

    public final void zzd(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzc((String) it.next(), (zzfmn) null);
        }
    }
}
