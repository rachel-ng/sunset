package com.google.android.gms.internal.ads;

import android.os.Parcelable;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.ads.zzbdv;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfjd implements zzfjc {
    private final ConcurrentHashMap zza;
    private final zzfjj zzb;
    private final zzfjf zzc = new zzfjf();

    public zzfjd(zzfjj zzfjj) {
        this.zza = new ConcurrentHashMap(zzfjj.zzd);
        this.zzb = zzfjj;
    }

    private final void zzf() {
        Parcelable.Creator<zzfjj> creator = zzfjj.CREATOR;
        if (((Boolean) zzba.zzc().zza(zzbep.zzgB)).booleanValue()) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.zzb.zzb);
            sb.append(" PoolCollection");
            sb.append(this.zzc.zzb());
            int i = 0;
            for (Map.Entry entry : this.zza.entrySet()) {
                i++;
                sb.append(i);
                sb.append(". ");
                sb.append(entry.getValue());
                sb.append("#");
                sb.append(((zzfjm) entry.getKey()).hashCode());
                sb.append("    ");
                for (int i2 = 0; i2 < ((zzfjb) entry.getValue()).zzb(); i2++) {
                    sb.append("[O]");
                }
                for (int zzb2 = ((zzfjb) entry.getValue()).zzb(); zzb2 < this.zzb.zzd; zzb2++) {
                    sb.append("[ ]");
                }
                sb.append("\n");
                sb.append(((zzfjb) entry.getValue()).zzg());
                sb.append("\n");
            }
            while (i < this.zzb.zzc) {
                i++;
                sb.append(i);
                sb.append(".\n");
            }
            zzm.zze(sb.toString());
        }
    }

    public final zzfjj zza() {
        return this.zzb;
    }

    public final synchronized zzfjl zzb(zzfjm zzfjm) {
        zzfjl zzfjl;
        zzfjb zzfjb = (zzfjb) this.zza.get(zzfjm);
        if (zzfjb != null) {
            zzfjl = zzfjb.zze();
            if (zzfjl == null) {
                this.zzc.zze();
            }
            zzfjz zzf = zzfjb.zzf();
            if (zzfjl != null) {
                zzbdv.zzb.zzc zzd = zzbdv.zzb.zzd();
                zzbdv.zzb.zza.C0004zza zza2 = zzbdv.zzb.zza.zza();
                zza2.zzf(zzbdv.zzb.zzd.IN_MEMORY);
                zzbdv.zzb.zze.zza zzc2 = zzbdv.zzb.zze.zzc();
                zzc2.zzd(zzf.zza);
                zzc2.zze(zzf.zzb);
                zza2.zzg(zzc2);
                zzd.zzd(zza2);
                zzfjl.zza.zzb().zzc().zzi((zzbdv.zzb) zzd.zzbr());
            }
            zzf();
        } else {
            this.zzc.zzf();
            zzf();
            zzfjl = null;
        }
        return zzfjl;
    }

    @Deprecated
    public final zzfjm zzc(zzl zzl, String str, zzw zzw) {
        return new zzfjn(zzl, str, new zzbxy(this.zzb.zza).zza().zzk, this.zzb.zzf, zzw);
    }

    public final synchronized boolean zzd(zzfjm zzfjm, zzfjl zzfjl) {
        boolean zzh;
        zzfjb zzfjb = (zzfjb) this.zza.get(zzfjm);
        zzfjl.zzd = zzu.zzB().currentTimeMillis();
        if (zzfjb == null) {
            zzfjj zzfjj = this.zzb;
            zzfjb zzfjb2 = new zzfjb(zzfjj.zzd, zzfjj.zze * 1000);
            if (this.zza.size() == this.zzb.zzc) {
                int i = this.zzb.zzg;
                int i2 = i - 1;
                zzfjm zzfjm2 = null;
                if (i != 0) {
                    long j = Long.MAX_VALUE;
                    if (i2 == 0) {
                        for (Map.Entry entry : this.zza.entrySet()) {
                            if (((zzfjb) entry.getValue()).zzc() < j) {
                                j = ((zzfjb) entry.getValue()).zzc();
                                zzfjm2 = (zzfjm) entry.getKey();
                            }
                        }
                        if (zzfjm2 != null) {
                            this.zza.remove(zzfjm2);
                        }
                    } else if (i2 == 1) {
                        for (Map.Entry entry2 : this.zza.entrySet()) {
                            if (((zzfjb) entry2.getValue()).zzd() < j) {
                                j = ((zzfjb) entry2.getValue()).zzd();
                                zzfjm2 = (zzfjm) entry2.getKey();
                            }
                        }
                        if (zzfjm2 != null) {
                            this.zza.remove(zzfjm2);
                        }
                    } else if (i2 == 2) {
                        int i3 = Integer.MAX_VALUE;
                        for (Map.Entry entry3 : this.zza.entrySet()) {
                            if (((zzfjb) entry3.getValue()).zza() < i3) {
                                i3 = ((zzfjb) entry3.getValue()).zza();
                                zzfjm2 = (zzfjm) entry3.getKey();
                            }
                        }
                        if (zzfjm2 != null) {
                            this.zza.remove(zzfjm2);
                        }
                    }
                    this.zzc.zzg();
                } else {
                    throw null;
                }
            }
            this.zza.put(zzfjm, zzfjb2);
            this.zzc.zzd();
            zzfjb = zzfjb2;
        }
        zzh = zzfjb.zzh(zzfjl);
        this.zzc.zzc();
        zzfje zza2 = this.zzc.zza();
        zzfjz zzf = zzfjb.zzf();
        if (zzfjl != null) {
            zzbdv.zzb.zzc zzd = zzbdv.zzb.zzd();
            zzbdv.zzb.zza.C0004zza zza3 = zzbdv.zzb.zza.zza();
            zza3.zzf(zzbdv.zzb.zzd.IN_MEMORY);
            zzbdv.zzb.zzg.zza zzc2 = zzbdv.zzb.zzg.zzc();
            zzc2.zze(zza2.zza);
            zzc2.zzf(zza2.zzb);
            zzc2.zzg(zzf.zzb);
            zza3.zzi(zzc2);
            zzd.zzd(zza3);
            zzfjl.zza.zzb().zzc().zzj((zzbdv.zzb) zzd.zzbr());
        }
        zzf();
        return zzh;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0015, code lost:
        if (r3.zzb() >= r1.zzd) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0018, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean zze(com.google.android.gms.internal.ads.zzfjm r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.concurrent.ConcurrentHashMap r0 = r2.zza     // Catch:{ all -> 0x001c }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.ads.zzfjb r3 = (com.google.android.gms.internal.ads.zzfjb) r3     // Catch:{ all -> 0x001c }
            r0 = 1
            if (r3 == 0) goto L_0x001a
            com.google.android.gms.internal.ads.zzfjj r1 = r2.zzb     // Catch:{ all -> 0x001c }
            int r3 = r3.zzb()     // Catch:{ all -> 0x001c }
            int r1 = r1.zzd     // Catch:{ all -> 0x001c }
            monitor-exit(r2)
            if (r3 >= r1) goto L_0x0018
            return r0
        L_0x0018:
            r3 = 0
            return r3
        L_0x001a:
            monitor-exit(r2)
            return r0
        L_0x001c:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x001c }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfjd.zze(com.google.android.gms.internal.ads.zzfjm):boolean");
    }
}
