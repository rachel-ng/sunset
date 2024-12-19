package com.google.android.gms.internal.ads;

import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzfxy {
    private final String zza;
    private final zzfxw zzb;
    private zzfxw zzc;

    /* synthetic */ zzfxy(String str, zzfxx zzfxx) {
        zzfxw zzfxw = new zzfxw();
        this.zzb = zzfxw;
        this.zzc = zzfxw;
        str.getClass();
        this.zza = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append('{');
        zzfxw zzfxw = this.zzb.zzb;
        String str = "";
        while (zzfxw != null) {
            Object obj = zzfxw.zza;
            sb.append(str);
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append(deepToString, 1, deepToString.length() - 1);
            }
            zzfxw = zzfxw.zzb;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzfxy zza(@CheckForNull Object obj) {
        zzfxw zzfxw = new zzfxw();
        this.zzc.zzb = zzfxw;
        this.zzc = zzfxw;
        zzfxw.zza = obj;
        return this;
    }
}
