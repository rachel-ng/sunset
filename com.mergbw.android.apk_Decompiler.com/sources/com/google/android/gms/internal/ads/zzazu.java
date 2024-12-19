package com.google.android.gms.internal.ads;

import android.content.pm.ApkChecksum;
import android.content.pm.PackageManager$OnChecksumsReadyListener;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzazu implements PackageManager$OnChecksumsReadyListener {
    public final /* synthetic */ zzggm zza;

    public /* synthetic */ zzazu(zzggm zzggm) {
        this.zza = zzggm;
    }

    public final void onChecksumsReady(List list) {
        zzggm zzggm = this.zza;
        if (list == null) {
            zzggm.zzc((Object) null);
            return;
        }
        try {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ApkChecksum m = Chip$$ExternalSyntheticApiModelOutline0.m(list.get(i));
                if (Chip$$ExternalSyntheticApiModelOutline0.m(m) == 8) {
                    zzggm.zzc(zzayh.zzb(Chip$$ExternalSyntheticApiModelOutline0.m(m)));
                    return;
                }
            }
            zzggm.zzc((Object) null);
        } catch (Throwable unused) {
            zzggm.zzc((Object) null);
        }
    }
}
