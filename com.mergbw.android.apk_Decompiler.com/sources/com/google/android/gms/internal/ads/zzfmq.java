package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.zzt;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfmq implements Runnable {
    public static final Object zza = new Object();
    public static Boolean zzb;
    private static final Object zzc = new Object();
    private static final Object zzd = new Object();
    private final Context zze;
    private final VersionInfoParcel zzf;
    private final zzfnh zzg = zzfnk.zzc();
    private String zzh = "";
    private int zzi;
    private final zzdsq zzj;
    private final List zzk;
    private boolean zzl = false;
    private final zzeef zzm;
    private final zzbyd zzn;

    public zzfmq(Context context, VersionInfoParcel versionInfoParcel, zzdsq zzdsq, zzeef zzeef, zzbyd zzbyd) {
        this.zze = context;
        this.zzf = versionInfoParcel;
        this.zzj = zzdsq;
        this.zzm = zzeef;
        this.zzn = zzbyd;
        if (((Boolean) zzba.zzc().zza(zzbep.zziQ)).booleanValue()) {
            this.zzk = zzt.zzd();
        } else {
            this.zzk = zzgbc.zzm();
        }
    }

    public static boolean zza() {
        boolean booleanValue;
        synchronized (zza) {
            if (zzb == null) {
                boolean z = false;
                if (!((Boolean) zzbgd.zzb.zze()).booleanValue()) {
                    zzb = false;
                } else {
                    if (Math.random() < ((Double) zzbgd.zza.zze()).doubleValue()) {
                        z = true;
                    }
                    zzb = Boolean.valueOf(z);
                }
            }
            booleanValue = zzb.booleanValue();
        }
        return booleanValue;
    }

    public final void run() {
        byte[] zzaV;
        if (zza()) {
            Object obj = zzc;
            synchronized (obj) {
                if (this.zzg.zza() != 0) {
                    try {
                        synchronized (obj) {
                            zzaV = ((zzfnk) this.zzg.zzbr()).zzaV();
                            this.zzg.zzc();
                        }
                        new zzeee(this.zze, this.zzf.afmaVersion, this.zzn, Binder.getCallingUid()).zza(new zzeec((String) zzba.zzc().zza(zzbep.zziK), 60000, new HashMap(), zzaV, "application/x-protobuf", false));
                    } catch (Exception e) {
                        if (!(e instanceof zzdzd) || ((zzdzd) e).zza() != 3) {
                            zzu.zzo().zzv(e, "CuiMonitor.sendCuiPing");
                        }
                    }
                }
            }
        }
    }

    public final void zzb(zzfmg zzfmg) {
        zzcci.zza.zza(new zzfmp(this, zzfmg));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzfmg zzfmg) {
        synchronized (zzd) {
            if (!this.zzl) {
                this.zzl = true;
                if (zza()) {
                    try {
                        zzu.zzp();
                        this.zzh = zzt.zzp(this.zze);
                    } catch (RemoteException e) {
                        zzu.zzo().zzw(e, "CuiMonitor.gettingAppIdFromManifest");
                    }
                    this.zzi = GoogleApiAvailabilityLight.getInstance().getApkVersion(this.zze);
                    int intValue = ((Integer) zzba.zzc().zza(zzbep.zziL)).intValue();
                    if (((Boolean) zzba.zzc().zza(zzbep.zzlP)).booleanValue()) {
                        long j = (long) intValue;
                        zzcci.zzd.scheduleWithFixedDelay(this, j, j, TimeUnit.MILLISECONDS);
                    } else {
                        long j2 = (long) intValue;
                        zzcci.zzd.scheduleAtFixedRate(this, j2, j2, TimeUnit.MILLISECONDS);
                    }
                }
            }
        }
        if (zza() && zzfmg != null) {
            synchronized (zzc) {
                if (this.zzg.zza() < ((Integer) zzba.zzc().zza(zzbep.zziM)).intValue()) {
                    zzfms zza2 = zzfnf.zza();
                    zza2.zzk(zzfmg.zzd());
                    zza2.zzu(zzfmg.zzo());
                    zza2.zzh(zzfmg.zzb());
                    zza2.zzn(zzfna.OS_ANDROID);
                    zza2.zzr(this.zzf.afmaVersion);
                    zza2.zzb(this.zzh);
                    zza2.zzo(Build.VERSION.RELEASE);
                    zza2.zzv(Build.VERSION.SDK_INT);
                    zza2.zzm(zzfmg.zzf());
                    zza2.zzl(zzfmg.zza());
                    zza2.zzf((long) this.zzi);
                    zza2.zze(zzfmg.zze());
                    zza2.zzc(zzfmg.zzh());
                    zza2.zzg(zzfmg.zzj());
                    zza2.zzi(zzfmg.zzk());
                    zza2.zzj(this.zzj.zzb(zzfmg.zzk()));
                    zza2.zzp(zzfmg.zzl());
                    zza2.zzq(zzfmg.zzg());
                    zza2.zzd(zzfmg.zzi());
                    zza2.zzw(zzfmg.zzn());
                    zza2.zzs(zzfmg.zzm());
                    zza2.zzt(zzfmg.zzc());
                    if (((Boolean) zzba.zzc().zza(zzbep.zziQ)).booleanValue()) {
                        zza2.zza(this.zzk);
                    }
                    zzfnh zzfnh = this.zzg;
                    zzfni zza3 = zzfnj.zza();
                    zza3.zza(zza2);
                    zzfnh.zzb(zza3);
                }
            }
        }
    }
}
