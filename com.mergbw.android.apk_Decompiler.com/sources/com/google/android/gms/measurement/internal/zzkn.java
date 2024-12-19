package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzjk;
import com.google.android.gms.internal.measurement.zzoj;
import com.google.android.gms.internal.measurement.zzph;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzkn extends zzmx {
    private static String zza(String str, String str2) {
        throw new SecurityException("This implementation should not be used.");
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        return false;
    }

    public zzkn(zznc zznc) {
        super(zznc);
    }

    public final byte[] zza(zzbd zzbd, String str) {
        zznq zznq;
        zzfn.zzj.zzb zzb;
        zzfn.zzk.zza zza;
        byte[] bArr;
        zzg zzg;
        Bundle bundle;
        zzaz zzaz;
        long j;
        zzbd zzbd2 = zzbd;
        String str2 = str;
        zzt();
        this.zzu.zzy();
        Preconditions.checkNotNull(zzbd);
        Preconditions.checkNotEmpty(str);
        if (!zze().zze(str2, zzbf.zzbg)) {
            zzj().zzc().zza("Generating ScionPayload disabled. packageName", str2);
            return new byte[0];
        } else if ("_iap".equals(zzbd2.zza) || "_iapx".equals(zzbd2.zza)) {
            zzfn.zzj.zzb zzb2 = zzfn.zzj.zzb();
            zzh().zzp();
            zzg zze = zzh().zze(str2);
            if (zze == null) {
                zzj().zzc().zza("Log and bundle not available. package_name", str2);
                byte[] bArr2 = new byte[0];
                zzh().zzu();
                return bArr2;
            } else if (!zze.zzar()) {
                zzj().zzc().zza("Log and bundle disabled. package_name", str2);
                byte[] bArr3 = new byte[0];
                zzh().zzu();
                return bArr3;
            } else {
                zzfn.zzk.zza zzp = zzfn.zzk.zzw().zzh(1).zzp("android");
                if (!TextUtils.isEmpty(zze.zzac())) {
                    zzp.zzb(zze.zzac());
                }
                if (!TextUtils.isEmpty(zze.zzae())) {
                    zzp.zzd((String) Preconditions.checkNotNull(zze.zzae()));
                }
                if (!TextUtils.isEmpty(zze.zzaf())) {
                    zzp.zze((String) Preconditions.checkNotNull(zze.zzaf()));
                }
                if (zze.zze() != -2147483648L) {
                    zzp.zze((int) zze.zze());
                }
                zzp.zzf(zze.zzq()).zzd(zze.zzo());
                String zzah = zze.zzah();
                String zzaa = zze.zzaa();
                if (!TextUtils.isEmpty(zzah)) {
                    zzp.zzm(zzah);
                } else if (!TextUtils.isEmpty(zzaa)) {
                    zzp.zza(zzaa);
                }
                zzp.zzj(zze.zzw());
                zzin zzb3 = this.zzf.zzb(str2);
                zzp.zzc(zze.zzn());
                if (this.zzu.zzac() && zze().zzj(zzp.zzt()) && zzb3.zzi() && !TextUtils.isEmpty((CharSequence) null)) {
                    zzp.zzj((String) null);
                }
                zzp.zzg(zzb3.zzg());
                if (zzb3.zzi() && zze.zzaq()) {
                    Pair<String, Boolean> zza2 = zzn().zza(zze.zzac(), zzb3);
                    if (zze.zzaq() && zza2 != null && !TextUtils.isEmpty((CharSequence) zza2.first)) {
                        try {
                            zzp.zzq(zza((String) zza2.first, Long.toString(zzbd2.zzd)));
                            if (zza2.second != null) {
                                zzp.zzc(((Boolean) zza2.second).booleanValue());
                            }
                        } catch (SecurityException e) {
                            zzj().zzc().zza("Resettable device id encryption failed", e.getMessage());
                            return new byte[0];
                        } finally {
                            zzh().zzu();
                        }
                    }
                }
                zzf().zzac();
                zzfn.zzk.zza zzi = zzp.zzi(Build.MODEL);
                zzf().zzac();
                zzi.zzo(Build.VERSION.RELEASE).zzj((int) zzf().zzg()).zzs(zzf().zzh());
                try {
                    if (zzb3.zzj() && zze.zzad() != null) {
                        zzp.zzc(zza((String) Preconditions.checkNotNull(zze.zzad()), Long.toString(zzbd2.zzd)));
                    }
                    if (!TextUtils.isEmpty(zze.zzag())) {
                        zzp.zzl((String) Preconditions.checkNotNull(zze.zzag()));
                    }
                    String zzac = zze.zzac();
                    List<zznq> zzk = zzh().zzk(zzac);
                    Iterator<zznq> it = zzk.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            zznq = null;
                            break;
                        }
                        zznq = it.next();
                        if ("_lte".equals(zznq.zzc)) {
                            break;
                        }
                    }
                    if (zznq == null || zznq.zze == null) {
                        zznq zznq2 = new zznq(zzac, "auto", "_lte", zzb().currentTimeMillis(), 0L);
                        zzk.add(zznq2);
                        zzh().zza(zznq2);
                    }
                    zzfn.zzo[] zzoArr = new zzfn.zzo[zzk.size()];
                    for (int i = 0; i < zzk.size(); i++) {
                        zzfn.zzo.zza zzb4 = zzfn.zzo.zze().zza(zzk.get(i).zzc).zzb(zzk.get(i).zzd);
                        g_().zza(zzb4, zzk.get(i).zze);
                        zzoArr[i] = (zzfn.zzo) ((zzjk) zzb4.zzai());
                    }
                    zzp.zze((Iterable<? extends zzfn.zzo>) Arrays.asList(zzoArr));
                    g_().zza(zzp);
                    this.zzf.zza(zze, zzp);
                    if (zzoj.zza() && zze().zza(zzbf.zzcm)) {
                        this.zzf.zzb(zze, zzp);
                    }
                    zzga zza3 = zzga.zza(zzbd);
                    zzq().zza(zza3.zzb, zzh().zzd(str2));
                    zzq().zza(zza3, zze().zzb(str2));
                    Bundle bundle2 = zza3.zzb;
                    bundle2.putLong("_c", 1);
                    zzj().zzc().zza("Marking in-app purchase as real-time");
                    bundle2.putLong("_r", 1);
                    bundle2.putString("_o", zzbd2.zzc);
                    if (zzq().zzd(zzp.zzt(), zze.zzam())) {
                        zzq().zza(bundle2, "_dbg", (Object) 1L);
                        zzq().zza(bundle2, "_r", (Object) 1L);
                    }
                    zzaz zzd = zzh().zzd(str2, zzbd2.zza);
                    if (zzd == null) {
                        zza = zzp;
                        bundle = bundle2;
                        zzb = zzb2;
                        zzg = zze;
                        bArr = null;
                        zzaz = new zzaz(str, zzbd2.zza, 0, 0, zzbd2.zzd, 0, (Long) null, (Long) null, (Long) null, (Boolean) null);
                        j = 0;
                    } else {
                        zza = zzp;
                        bundle = bundle2;
                        zzb = zzb2;
                        zzg = zze;
                        bArr = null;
                        j = zzd.zzf;
                        zzaz = zzd.zza(zzbd2.zzd);
                    }
                    zzh().zza(zzaz);
                    zzba zzba = new zzba(this.zzu, zzbd2.zzc, str, zzbd2.zza, zzbd2.zzd, j, bundle);
                    zzfn.zzf.zza zza4 = zzfn.zzf.zze().zzb(zzba.zzc).zza(zzba.zzb).zza(zzba.zzd);
                    Iterator<String> it2 = zzba.zze.iterator();
                    while (it2.hasNext()) {
                        String next = it2.next();
                        zzfn.zzh.zza zza5 = zzfn.zzh.zze().zza(next);
                        Object zzc = zzba.zze.zzc(next);
                        if (zzc != null) {
                            g_().zza(zza5, zzc);
                            zza4.zza(zza5);
                        }
                    }
                    zzfn.zzk.zza zza6 = zza;
                    zza6.zza(zza4).zza(zzfn.zzl.zza().zza(zzfn.zzg.zza().zza(zzaz.zzc).zza(zzbd2.zza)));
                    zza6.zza((Iterable<? extends zzfn.zzd>) zzg().zza(zzg.zzac(), Collections.emptyList(), zza6.zzab(), Long.valueOf(zza4.zzc()), Long.valueOf(zza4.zzc())));
                    if (zza4.zzg()) {
                        zza6.zzi(zza4.zzc()).zze(zza4.zzc());
                    }
                    long zzs = zzg.zzs();
                    int i2 = (zzs > 0 ? 1 : (zzs == 0 ? 0 : -1));
                    if (i2 != 0) {
                        zza6.zzg(zzs);
                    }
                    long zzu = zzg.zzu();
                    if (zzu != 0) {
                        zza6.zzh(zzu);
                    } else if (i2 != 0) {
                        zza6.zzh(zzs);
                    }
                    String zzal = zzg.zzal();
                    if (zzph.zza()) {
                        if (zze().zze(str, zzbf.zzbr) && zzal != null) {
                            zza6.zzr(zzal);
                        }
                    } else {
                        String str3 = str;
                    }
                    zzg.zzap();
                    zza6.zzf((int) zzg.zzt()).zzl(97001).zzk(zzb().currentTimeMillis()).zzd(Boolean.TRUE.booleanValue());
                    this.zzf.zza(zza6.zzt(), zza6);
                    zzfn.zzj.zzb zzb5 = zzb;
                    zzb5.zza(zza6);
                    zzg zzg2 = zzg;
                    zzg2.zzr(zza6.zzf());
                    zzg2.zzp(zza6.zze());
                    zzh().zza(zzg2, false, false);
                    zzh().zzw();
                    try {
                        return g_().zzb(((zzfn.zzj) ((zzjk) zzb5.zzai())).zzbz());
                    } catch (IOException e2) {
                        zzj().zzg().zza("Data loss. Failed to bundle and serialize. appId", zzfw.zza(str), e2);
                        return bArr;
                    }
                } catch (SecurityException e3) {
                    zzj().zzc().zza("app instance id encryption failed", e3.getMessage());
                    byte[] bArr4 = new byte[0];
                    zzh().zzu();
                    return bArr4;
                }
            }
        } else {
            zzj().zzc().zza("Generating a payload for this event is not available. package_name, event_name", str2, zzbd2.zza);
            return null;
        }
    }
}
