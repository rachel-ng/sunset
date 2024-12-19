package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zzba;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzdxv {
    private final zzdxf zza;
    private final zzdsq zzb;
    private final Object zzc = new Object();
    private final List zzd;
    private boolean zze;

    zzdxv(zzdxf zzdxf, zzdsq zzdsq) {
        this.zza = zzdxf;
        this.zzb = zzdsq;
        this.zzd = new ArrayList();
    }

    /* access modifiers changed from: private */
    public final void zzd(List list) {
        String str;
        boolean z;
        synchronized (this.zzc) {
            if (!this.zze) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    zzbnn zzbnn = (zzbnn) it.next();
                    if (((Boolean) zzba.zzc().zza(zzbep.zzjq)).booleanValue()) {
                        zzdsp zza2 = this.zzb.zza(zzbnn.zza);
                        if (zza2 != null) {
                            zzbtt zzbtt = zza2.zzc;
                            if (zzbtt != null) {
                                str = zzbtt.toString();
                            }
                        }
                        str = "";
                    } else {
                        str = "";
                    }
                    String str2 = str;
                    if (((Boolean) zzba.zzc().zza(zzbep.zzjr)).booleanValue()) {
                        zzdsp zza3 = this.zzb.zza(zzbnn.zza);
                        if (zza3 != null) {
                            if (zza3.zzd) {
                                z = true;
                                List list2 = this.zzd;
                                String str3 = zzbnn.zza;
                                list2.add(new zzdxu(str3, str2, this.zzb.zzb(str3), zzbnn.zzb ? 1 : 0, zzbnn.zzd, zzbnn.zzc, z));
                            }
                        }
                    }
                    z = false;
                    List list22 = this.zzd;
                    String str32 = zzbnn.zza;
                    list22.add(new zzdxu(str32, str2, this.zzb.zzb(str32), zzbnn.zzb ? 1 : 0, zzbnn.zzd, zzbnn.zzc, z));
                }
                this.zze = true;
            }
        }
    }

    public final JSONArray zza() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        synchronized (this.zzc) {
            if (!this.zze) {
                if (this.zza.zzt()) {
                    zzd(this.zza.zzg());
                } else {
                    zzc();
                }
            }
            for (zzdxu zza2 : this.zzd) {
                jSONArray.put(zza2.zza());
            }
        }
        return jSONArray;
    }

    public final void zzc() {
        this.zza.zzs(new zzdxt(this));
    }
}
