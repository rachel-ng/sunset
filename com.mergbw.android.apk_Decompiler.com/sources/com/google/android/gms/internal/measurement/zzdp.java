package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.measurement.zzdq;
import com.google.android.gms.measurement.internal.zzhd;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
final class zzdp extends zzdq.zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Context zze;
    private final /* synthetic */ Bundle zzf;
    private final /* synthetic */ zzdq zzg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdp(zzdq zzdq, String str, String str2, Context context, Bundle bundle) {
        super(zzdq);
        this.zzc = str;
        this.zzd = str2;
        this.zze = context;
        this.zzf = bundle;
        this.zzg = zzdq;
    }

    public final void zza() {
        String str;
        String str2;
        String str3;
        try {
            if (this.zzg.zzc(this.zzc, this.zzd)) {
                str = this.zzd;
                str2 = this.zzc;
                str3 = this.zzg.zzc;
            } else {
                str3 = null;
                str2 = null;
                str = null;
            }
            Preconditions.checkNotNull(this.zze);
            zzdq zzdq = this.zzg;
            zzdq.zzj = zzdq.zza(this.zze, true);
            if (this.zzg.zzj == null) {
                Log.w(this.zzg.zzc, "Failed to connect to measurement client.");
                return;
            }
            int localVersion = DynamiteModule.getLocalVersion(this.zze, ModuleDescriptor.MODULE_ID);
            int remoteVersion = DynamiteModule.getRemoteVersion(this.zze, ModuleDescriptor.MODULE_ID);
            ((zzdb) Preconditions.checkNotNull(this.zzg.zzj)).initialize(ObjectWrapper.wrap(this.zze), new zzdo(97001, (long) Math.max(localVersion, remoteVersion), remoteVersion < localVersion, str3, str2, str, this.zzf, zzhd.zza(this.zze)), this.zza);
        } catch (Exception e) {
            this.zzg.zza(e, true, false);
        }
    }
}
