package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.exoplayer2.PlaybackException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzftg {
    private static final HashMap zza = new HashMap();
    private final Context zzb;
    private final zzfth zzc;
    private final zzfre zzd;
    private final zzfqx zze;
    private zzfsv zzf;
    private final Object zzg = new Object();

    public zzftg(Context context, zzfth zzfth, zzfre zzfre, zzfqx zzfqx) {
        this.zzb = context;
        this.zzc = zzfth;
        this.zzd = zzfre;
        this.zze = zzfqx;
    }

    private final synchronized Class zzd(zzfsw zzfsw) throws zzftf {
        String zzk = zzfsw.zza().zzk();
        HashMap hashMap = zza;
        Class cls = (Class) hashMap.get(zzk);
        if (cls != null) {
            return cls;
        }
        try {
            if (this.zze.zza(zzfsw.zzc())) {
                File zzb2 = zzfsw.zzb();
                if (!zzb2.exists()) {
                    zzb2.mkdirs();
                }
                Class loadClass = new DexClassLoader(zzfsw.zzc().getAbsolutePath(), zzb2.getAbsolutePath(), (String) null, this.zzb.getClassLoader()).loadClass("com.google.ccc.abuse.droidguard.DroidGuard");
                hashMap.put(zzk, loadClass);
                return loadClass;
            }
            throw new zzftf(2026, "VM did not pass signature verification");
        } catch (GeneralSecurityException e) {
            throw new zzftf(2026, (Throwable) e);
        } catch (ClassNotFoundException | IllegalArgumentException | SecurityException e2) {
            throw new zzftf(2008, e2);
        }
    }

    public final zzfrh zza() {
        zzfsv zzfsv;
        synchronized (this.zzg) {
            zzfsv = this.zzf;
        }
        return zzfsv;
    }

    public final zzfsw zzb() {
        synchronized (this.zzg) {
            zzfsv zzfsv = this.zzf;
            if (zzfsv == null) {
                return null;
            }
            zzfsw zzf2 = zzfsv.zzf();
            return zzf2;
        }
    }

    public final boolean zzc(zzfsw zzfsw) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Class zzd2 = zzd(zzfsw);
            zzfsv zzfsv = new zzfsv(zzd2.getDeclaredConstructor(new Class[]{Context.class, String.class, byte[].class, Object.class, Bundle.class, Integer.TYPE}).newInstance(new Object[]{this.zzb, "msa-r", zzfsw.zze(), null, new Bundle(), 2}), zzfsw, this.zzc, this.zzd);
            if (zzfsv.zzh()) {
                int zze2 = zzfsv.zze();
                if (zze2 == 0) {
                    synchronized (this.zzg) {
                        zzfsv zzfsv2 = this.zzf;
                        if (zzfsv2 != null) {
                            try {
                                zzfsv2.zzg();
                            } catch (zzftf e) {
                                zzftf zzftf = e;
                                this.zzd.zzc(zzftf.zza(), -1, zzftf);
                            }
                        }
                        this.zzf = zzfsv;
                    }
                    this.zzd.zzd(3000, System.currentTimeMillis() - currentTimeMillis);
                    return true;
                }
                throw new zzftf((int) PlaybackException.ERROR_CODE_DECODER_INIT_FAILED, "ci: " + zze2);
            }
            throw new zzftf(4000, "init failed");
        } catch (Exception e2) {
            throw new zzftf((int) PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS, (Throwable) e2);
        } catch (zzftf e3) {
            this.zzd.zzc(e3.zza(), System.currentTimeMillis() - currentTimeMillis, e3);
            return false;
        } catch (Exception e4) {
            this.zzd.zzc(4010, System.currentTimeMillis() - currentTimeMillis, e4);
            return false;
        }
    }
}
