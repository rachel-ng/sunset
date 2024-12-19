package com.google.android.gms.internal.ads;

import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.AudioProfile;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzpm {
    public static zzpp zza(AudioManager audioManager, zzk zzk) {
        List m$1 = audioManager.getDirectProfilesForAttributes(zzk.zza().zza);
        HashMap hashMap = new HashMap();
        hashMap.put(2, new HashSet(zzgea.zzf(12)));
        for (int i = 0; i < m$1.size(); i++) {
            AudioProfile m = Chip$$ExternalSyntheticApiModelOutline0.m(m$1.get(i));
            if (Chip$$ExternalSyntheticApiModelOutline0.m(m) != 1) {
                int m$12 = m.getFormat();
                if (zzgd.zzK(m$12) || zzpp.zzb.containsKey(Integer.valueOf(m$12))) {
                    Integer valueOf = Integer.valueOf(m$12);
                    if (hashMap.containsKey(valueOf)) {
                        Set set = (Set) hashMap.get(valueOf);
                        set.getClass();
                        set.addAll(zzgea.zzf(Chip$$ExternalSyntheticApiModelOutline0.m(m)));
                    } else {
                        hashMap.put(valueOf, new HashSet(zzgea.zzf(Chip$$ExternalSyntheticApiModelOutline0.m(m))));
                    }
                }
            }
        }
        zzgaz zzgaz = new zzgaz();
        for (Map.Entry entry : hashMap.entrySet()) {
            zzgaz.zzf(new zzpn(((Integer) entry.getKey()).intValue(), (Set) entry.getValue()));
        }
        return new zzpp(zzgaz.zzi());
    }

    public static zzpx zzb(AudioManager audioManager, zzk zzk) {
        if (audioManager != null) {
            try {
                List m = audioManager.getAudioDevicesForAttributes(zzk.zza().zza);
                if (!m.isEmpty()) {
                    return new zzpx((AudioDeviceInfo) m.get(0));
                }
            } catch (RuntimeException unused) {
            }
        } else {
            throw null;
        }
        return null;
    }
}
