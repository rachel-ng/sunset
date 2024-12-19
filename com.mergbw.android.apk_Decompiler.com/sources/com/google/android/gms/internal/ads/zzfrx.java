package com.google.android.gms.internal.ads;

import android.content.Context;
import android.util.Base64;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfrx {
    private final Context zza;
    private final Executor zzb;
    private final zzfre zzc;
    private final zzfrg zzd;
    private final zzfrw zze;
    private final zzfrw zzf;
    private Task zzg;
    private Task zzh;

    zzfrx(Context context, Executor executor, zzfre zzfre, zzfrg zzfrg, zzfru zzfru, zzfrv zzfrv) {
        this.zza = context;
        this.zzb = executor;
        this.zzc = zzfre;
        this.zzd = zzfrg;
        this.zze = zzfru;
        this.zzf = zzfrv;
    }

    public static zzfrx zze(Context context, Executor executor, zzfre zzfre, zzfrg zzfrg) {
        zzfrx zzfrx = new zzfrx(context, executor, zzfre, zzfrg, new zzfru(), new zzfrv());
        if (zzfrx.zzd.zzd()) {
            zzfrx.zzg = zzfrx.zzh(new zzfrr(zzfrx));
        } else {
            zzfrx.zzg = Tasks.forResult(zzfrx.zze.zza());
        }
        zzfrx.zzh = zzfrx.zzh(new zzfrs(zzfrx));
        return zzfrx;
    }

    private static zzaus zzg(Task task, zzaus zzaus) {
        if (!task.isSuccessful()) {
            return zzaus;
        }
        return (zzaus) task.getResult();
    }

    private final Task zzh(Callable callable) {
        return Tasks.call(this.zzb, callable).addOnFailureListener(this.zzb, (OnFailureListener) new zzfrt(this));
    }

    public final zzaus zza() {
        return zzg(this.zzg, this.zze.zza());
    }

    public final zzaus zzb() {
        return zzg(this.zzh, this.zzf.zza());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzaus zzc() throws Exception {
        zzatp zza2 = zzaus.zza();
        AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.zza);
        String id = advertisingIdInfo.getId();
        if (id != null && id.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$")) {
            UUID fromString = UUID.fromString(id);
            byte[] bArr = new byte[16];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.putLong(fromString.getMostSignificantBits());
            wrap.putLong(fromString.getLeastSignificantBits());
            id = Base64.encodeToString(bArr, 11);
        }
        if (id != null) {
            zza2.zzt(id);
            zza2.zzs(advertisingIdInfo.isLimitAdTrackingEnabled());
            zza2.zzu(zzatx.DEVICE_IDENTIFIER_ANDROID_AD_ID);
        }
        return (zzaus) zza2.zzbr();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzaus zzd() throws Exception {
        Context context = this.zza;
        return zzfrm.zza(context, context.getPackageName(), Integer.toString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(Exception exc) {
        if (exc instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        }
        this.zzc.zzc(2025, -1, exc);
    }
}
