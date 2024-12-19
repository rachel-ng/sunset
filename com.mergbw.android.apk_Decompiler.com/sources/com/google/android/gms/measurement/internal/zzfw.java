package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzoi;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzfw extends zzii {
    /* access modifiers changed from: private */
    public char zza = 0;
    /* access modifiers changed from: private */
    public long zzb = -1;
    private String zzc;
    private final zzfy zzd = new zzfy(this, 6, false, false);
    private final zzfy zze = new zzfy(this, 6, true, false);
    private final zzfy zzf = new zzfy(this, 6, false, true);
    private final zzfy zzg = new zzfy(this, 5, false, false);
    private final zzfy zzh = new zzfy(this, 5, true, false);
    private final zzfy zzi = new zzfy(this, 5, false, true);
    private final zzfy zzj = new zzfy(this, 4, false, false);
    private final zzfy zzk = new zzfy(this, 3, false, false);
    private final zzfy zzl = new zzfy(this, 2, false, false);

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    /* access modifiers changed from: protected */
    public final boolean zzo() {
        return false;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzax zzf() {
        return super.zzf();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzfr zzi() {
        return super.zzi();
    }

    public final zzfy zzc() {
        return this.zzk;
    }

    public final zzfy zzg() {
        return this.zzd;
    }

    public final zzfy zzh() {
        return this.zzf;
    }

    public final zzfy zzm() {
        return this.zze;
    }

    public final zzfy zzn() {
        return this.zzj;
    }

    public final zzfy zzp() {
        return this.zzl;
    }

    public final zzfy zzu() {
        return this.zzg;
    }

    public final zzfy zzv() {
        return this.zzi;
    }

    public final zzfy zzw() {
        return this.zzh;
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzfw zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhc zzl() {
        return super.zzl();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zznp zzq() {
        return super.zzq();
    }

    protected static Object zza(String str) {
        if (str == null) {
            return null;
        }
        return new zzfx(str);
    }

    static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        String zza2 = zza(z, obj);
        String zza3 = zza(z, obj2);
        String zza4 = zza(z, obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(zza2)) {
            sb.append(str2);
            sb.append(zza2);
            str2 = str3;
        }
        if (!TextUtils.isEmpty(zza3)) {
            sb.append(str2);
            sb.append(zza3);
        } else {
            str3 = str2;
        }
        if (!TextUtils.isEmpty(zza4)) {
            sb.append(str3);
            sb.append(zza4);
        }
        return sb.toString();
    }

    private static String zza(boolean z, Object obj) {
        String className;
        String str = "";
        if (obj == null) {
            return str;
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf((long) ((Integer) obj).intValue());
        }
        int i = 0;
        if (obj instanceof Long) {
            if (!z) {
                return String.valueOf(obj);
            }
            Long l = (Long) obj;
            if (Math.abs(l.longValue()) < 100) {
                return String.valueOf(obj);
            }
            if (String.valueOf(obj).charAt(0) == '-') {
                str = "-";
            }
            String valueOf = String.valueOf(Math.abs(l.longValue()));
            long round = Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1)));
            long round2 = Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d);
            return str + round + "..." + str + round2;
        } else if (obj instanceof Boolean) {
            return String.valueOf(obj);
        } else {
            if (obj instanceof Throwable) {
                Throwable th = (Throwable) obj;
                StringBuilder sb = new StringBuilder(z ? th.getClass().getName() : th.toString());
                String zzb2 = zzb(zzhj.class.getCanonicalName());
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTrace[i];
                    if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null && zzb(className).equals(zzb2)) {
                        sb.append(": ");
                        sb.append(stackTraceElement);
                        break;
                    }
                    i++;
                }
                return sb.toString();
            } else if (obj instanceof zzfx) {
                return ((zzfx) obj).zza;
            } else {
                if (z) {
                    return "-";
                }
                return String.valueOf(obj);
            }
        }
    }

    private static String zzb(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf != -1) {
            return str.substring(0, lastIndexOf);
        }
        if (!zzoi.zza() || !zzbf.zzbw.zza(null).booleanValue()) {
            return str;
        }
        return "";
    }

    public final String zzx() {
        Pair<String, Long> zza2;
        if (zzk().zzb == null || (zza2 = zzk().zzb.zza()) == null || zza2 == zzgh.zza) {
            return null;
        }
        String valueOf = String.valueOf(zza2.second);
        return valueOf + ":" + ((String) zza2.first);
    }

    @EnsuresNonNull({"logTagDoNotUseDirectly"})
    private final String zzy() {
        String str;
        String str2;
        synchronized (this) {
            if (this.zzc == null) {
                if (this.zzu.zzw() != null) {
                    str2 = this.zzu.zzw();
                } else {
                    str2 = "FA";
                }
                this.zzc = str2;
            }
            Preconditions.checkNotNull(this.zzc);
            str = this.zzc;
        }
        return str;
    }

    zzfw(zzhj zzhj) {
        super(zzhj);
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && zza(i)) {
            zza(i, zza(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            Preconditions.checkNotNull(str);
            zzhc zzo = this.zzu.zzo();
            if (zzo == null) {
                zza(6, "Scheduler not set. Not logging error/warn");
            } else if (!zzo.zzaf()) {
                zza(6, "Scheduler not initialized. Not logging error/warn");
            } else {
                if (i < 0) {
                    i = 0;
                }
                if (i >= 9) {
                    i = 8;
                }
                zzo.zzb((Runnable) new zzfv(this, i, str, obj, obj2, obj3));
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, String str) {
        Log.println(i, zzy(), str);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i) {
        return Log.isLoggable(zzy(), i);
    }
}
