package com.google.android.gms.internal.ads;

import android.content.Context;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfvh extends zzfve {
    private static zzfvh zzc;

    private zzfvh(Context context) {
        super(context, "paidv2_id", "paidv2_creation_time", "PaidV2LifecycleImpl");
    }

    public static final zzfvh zzi(Context context) {
        zzfvh zzfvh;
        synchronized (zzfvh.class) {
            if (zzc == null) {
                zzc = new zzfvh(context);
            }
            zzfvh = zzc;
        }
        return zzfvh;
    }

    public final zzfvd zzh(long j, boolean z) throws IOException {
        synchronized (zzfvh.class) {
            if (!zzo()) {
                zzfvd zzfvd = new zzfvd();
                return zzfvd;
            }
            zzfvd zzb = zzb((String) null, (String) null, j, z);
            return zzb;
        }
    }

    public final void zzj() throws IOException {
        synchronized (zzfvh.class) {
            if (zzg(false)) {
                zzf(false);
            }
        }
    }

    public final void zzk() throws IOException {
        this.zzb.zze("paidv2_publisher_option");
    }

    public final void zzl() throws IOException {
        this.zzb.zze("paidv2_user_option");
    }

    public final void zzm(boolean z) throws IOException {
        this.zzb.zzd("paidv2_user_option", Boolean.valueOf(z));
    }

    public final void zzn(boolean z) throws IOException {
        this.zzb.zzd("paidv2_publisher_option", Boolean.valueOf(z));
        if (!z) {
            zzj();
        }
    }

    public final boolean zzo() {
        return this.zzb.zzf("paidv2_publisher_option", true);
    }

    public final boolean zzp() {
        return this.zzb.zzf("paidv2_user_option", true);
    }
}
