package com.google.android.gms.internal.ads;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzfrb implements Continuation {
    public final /* synthetic */ zzasx zza;
    public final /* synthetic */ int zzb;

    public /* synthetic */ zzfrb(zzasx zzasx, int i) {
        this.zza = zzasx;
        this.zzb = i;
    }

    public final Object then(Task task) {
        int i = zzfre.zza;
        if (!task.isSuccessful()) {
            return false;
        }
        int i2 = this.zzb;
        zzftj zza2 = ((zzftk) task.getResult()).zza(((zzatd) this.zza.zzbr()).zzaV());
        zza2.zza(i2);
        zza2.zzc();
        return true;
    }
}
