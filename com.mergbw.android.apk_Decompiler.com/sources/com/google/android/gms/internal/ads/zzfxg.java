package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfxg {
    private static final Map zza = new HashMap();
    /* access modifiers changed from: private */
    public final Context zzb;
    /* access modifiers changed from: private */
    public final zzfwv zzc;
    private final String zzd;
    /* access modifiers changed from: private */
    public final List zze = new ArrayList();
    private final Set zzf = new HashSet();
    /* access modifiers changed from: private */
    public final Object zzg = new Object();
    /* access modifiers changed from: private */
    public boolean zzh;
    private final Intent zzi;
    private final WeakReference zzj;
    private final IBinder.DeathRecipient zzk = new zzfwx(this);
    /* access modifiers changed from: private */
    public final AtomicInteger zzl = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public ServiceConnection zzm;
    /* access modifiers changed from: private */
    public IInterface zzn;
    private final zzfwi zzo;

    public zzfxg(Context context, zzfwv zzfwv, String str, Intent intent, zzfwi zzfwi, zzfxb zzfxb) {
        this.zzb = context;
        this.zzc = zzfwv;
        this.zzd = "OverlayDisplayService";
        this.zzi = intent;
        this.zzo = zzfwi;
        this.zzj = new WeakReference((Object) null);
    }

    public static /* synthetic */ void zzj(zzfxg zzfxg) {
        zzfxg.zzc.zzc("reportBinderDeath", new Object[0]);
        zzfxb zzfxb = (zzfxb) zzfxg.zzj.get();
        if (zzfxb != null) {
            zzfxg.zzc.zzc("calling onBinderDied", new Object[0]);
            zzfxb.zza();
        } else {
            zzfxg.zzc.zzc("%s : Binder has died.", zzfxg.zzd);
            for (zzfww zzc2 : zzfxg.zze) {
                zzc2.zzc(zzfxg.zzv());
            }
            zzfxg.zze.clear();
        }
        synchronized (zzfxg.zzg) {
            zzfxg.zzw();
        }
    }

    static /* bridge */ /* synthetic */ void zzn(zzfxg zzfxg, TaskCompletionSource taskCompletionSource) {
        zzfxg.zzf.add(taskCompletionSource);
        taskCompletionSource.getTask().addOnCompleteListener(new zzfwy(zzfxg, taskCompletionSource));
    }

    static /* bridge */ /* synthetic */ void zzq(zzfxg zzfxg) {
        zzfxg.zzc.zzc("linkToDeath", new Object[0]);
        try {
            zzfxg.zzn.asBinder().linkToDeath(zzfxg.zzk, 0);
        } catch (RemoteException e) {
            zzfxg.zzc.zzb(e, "linkToDeath failed", new Object[0]);
        }
    }

    static /* bridge */ /* synthetic */ void zzr(zzfxg zzfxg) {
        zzfxg.zzc.zzc("unlinkToDeath", new Object[0]);
        zzfxg.zzn.asBinder().unlinkToDeath(zzfxg.zzk, 0);
    }

    private final RemoteException zzv() {
        return new RemoteException(String.valueOf(this.zzd).concat(" : Binder has died."));
    }

    /* access modifiers changed from: private */
    public final void zzw() {
        for (TaskCompletionSource trySetException : this.zzf) {
            trySetException.trySetException(zzv());
        }
        this.zzf.clear();
    }

    public final Handler zzc() {
        Handler handler;
        Map map = zza;
        synchronized (map) {
            if (!map.containsKey(this.zzd)) {
                HandlerThread handlerThread = new HandlerThread(this.zzd, 10);
                handlerThread.start();
                map.put(this.zzd, new Handler(handlerThread.getLooper()));
            }
            handler = (Handler) map.get(this.zzd);
        }
        return handler;
    }

    public final IInterface zze() {
        return this.zzn;
    }

    public final void zzs(zzfww zzfww, TaskCompletionSource taskCompletionSource) {
        zzc().post(new zzfwz(this, zzfww.zzb(), taskCompletionSource, zzfww));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzt(TaskCompletionSource taskCompletionSource, Task task) {
        synchronized (this.zzg) {
            this.zzf.remove(taskCompletionSource);
        }
    }

    public final void zzu() {
        zzc().post(new zzfxa(this));
    }

    static /* bridge */ /* synthetic */ void zzp(zzfxg zzfxg, zzfww zzfww) {
        if (zzfxg.zzn == null && !zzfxg.zzh) {
            zzfxg.zzc.zzc("Initiate binding to the service.", new Object[0]);
            zzfxg.zze.add(zzfww);
            zzfxf zzfxf = new zzfxf(zzfxg, (zzfxe) null);
            zzfxg.zzm = zzfxf;
            zzfxg.zzh = true;
            if (!zzfxg.zzb.bindService(zzfxg.zzi, zzfxf, 1)) {
                zzfxg.zzc.zzc("Failed to bind to the service.", new Object[0]);
                zzfxg.zzh = false;
                for (zzfww zzc2 : zzfxg.zze) {
                    zzc2.zzc(new zzfxh());
                }
                zzfxg.zze.clear();
            }
        } else if (zzfxg.zzh) {
            zzfxg.zzc.zzc("Waiting to bind to the service.", new Object[0]);
            zzfxg.zze.add(zzfww);
        } else {
            zzfww.run();
        }
    }
}
