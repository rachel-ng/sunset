package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfre {
    public static final /* synthetic */ int zza = 0;
    private static volatile zzatc zzb = zzatc.UNKNOWN;
    private final Context zzc;
    private final Executor zzd;
    private final Task zze;
    private final boolean zzf;

    zzfre(Context context, Executor executor, Task task, boolean z) {
        this.zzc = context;
        this.zzd = executor;
        this.zze = task;
        this.zzf = z;
    }

    public static zzfre zza(Context context, Executor executor, boolean z) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (z) {
            executor.execute(new zzfrc(context, taskCompletionSource));
        } else {
            executor.execute(new zzfrd(taskCompletionSource));
        }
        return new zzfre(context, executor, taskCompletionSource.getTask(), z);
    }

    static void zzg(zzatc zzatc) {
        zzb = zzatc;
    }

    private final Task zzh(int i, long j, Exception exc, String str, Map map, String str2) {
        if (!this.zzf) {
            return this.zze.continueWith(this.zzd, new zzfra());
        }
        Context context = this.zzc;
        zzasx zza2 = zzatd.zza();
        zza2.zza(context.getPackageName());
        zza2.zzf(j);
        zza2.zze(zzb);
        if (exc != null) {
            StringWriter stringWriter = new StringWriter();
            exc.printStackTrace(new PrintWriter(stringWriter));
            zza2.zzg(stringWriter.toString());
            zza2.zzd(exc.getClass().getName());
        }
        if (str2 != null) {
            zza2.zzb(str2);
        }
        if (str != null) {
            zza2.zzc(str);
        }
        return this.zze.continueWith(this.zzd, new zzfrb(zza2, i));
    }

    public final Task zzb(int i, String str) {
        return zzh(i, 0, (Exception) null, (String) null, (Map) null, str);
    }

    public final Task zzc(int i, long j, Exception exc) {
        return zzh(i, j, exc, (String) null, (Map) null, (String) null);
    }

    public final Task zzd(int i, long j) {
        return zzh(i, j, (Exception) null, (String) null, (Map) null, (String) null);
    }

    public final Task zze(int i, long j, String str) {
        return zzh(i, j, (Exception) null, (String) null, (Map) null, str);
    }

    public final Task zzf(int i, long j, String str, Map map) {
        return zzh(i, j, (Exception) null, str, (Map) null, (String) null);
    }
}
