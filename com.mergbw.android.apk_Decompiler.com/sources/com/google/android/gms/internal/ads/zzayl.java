package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzayl implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, Application.ActivityLifecycleCallbacks {
    private static final Handler zza = new Handler(Looper.getMainLooper());
    private final Context zzb;
    private Application zzc;
    private final PowerManager zzd;
    private final KeyguardManager zze;
    private BroadcastReceiver zzf;
    private final zzaxx zzg;
    private WeakReference zzh;
    private WeakReference zzi;
    private zzaxt zzj;
    private byte zzk = -1;
    private int zzl = -1;
    private long zzm = -3;

    public zzayl(Context context, zzaxx zzaxx) {
        Context applicationContext = context.getApplicationContext();
        this.zzb = applicationContext;
        this.zzg = zzaxx;
        this.zzd = (PowerManager) applicationContext.getSystemService("power");
        this.zze = (KeyguardManager) applicationContext.getSystemService("keyguard");
        if (applicationContext instanceof Application) {
            Application application = (Application) applicationContext;
            this.zzc = application;
            this.zzj = new zzaxt(application, this);
        }
        zzd((View) null);
    }

    private final void zze(Activity activity, int i) {
        Window window;
        if (this.zzi != null && (window = activity.getWindow()) != null) {
            View peekDecorView = window.peekDecorView();
            View zzb2 = zzb();
            if (zzb2 != null && peekDecorView != null && zzb2.getRootView() == peekDecorView.getRootView()) {
                this.zzl = i;
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0080, code lost:
        if ((r8.flags & 524288) != 0) goto L_0x0084;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzf() {
        /*
            r9 = this;
            java.lang.ref.WeakReference r0 = r9.zzi
            if (r0 != 0) goto L_0x0006
            goto L_0x00be
        L_0x0006:
            android.view.View r0 = r9.zzb()
            r1 = -1
            r2 = -3
            if (r0 != 0) goto L_0x0014
            r9.zzm = r2
            r9.zzk = r1
            return
        L_0x0014:
            int r4 = r0.getVisibility()
            r5 = 0
            if (r4 == 0) goto L_0x001d
            r4 = 1
            goto L_0x001e
        L_0x001d:
            r4 = r5
        L_0x001e:
            boolean r6 = r0.isShown()
            if (r6 != 0) goto L_0x0026
            r4 = r4 | 2
        L_0x0026:
            android.os.PowerManager r6 = r9.zzd
            if (r6 == 0) goto L_0x0032
            boolean r6 = r6.isScreenOn()
            if (r6 != 0) goto L_0x0032
            r4 = r4 | 4
        L_0x0032:
            com.google.android.gms.internal.ads.zzaxx r6 = r9.zzg
            boolean r6 = r6.zza()
            if (r6 != 0) goto L_0x0084
            android.app.KeyguardManager r6 = r9.zze
            if (r6 == 0) goto L_0x0082
            boolean r6 = r6.inKeyguardRestrictedInputMode()
            if (r6 == 0) goto L_0x0082
            int r6 = com.google.android.gms.internal.ads.zzayh.zza
            android.view.View r6 = r0.getRootView()
            if (r6 != 0) goto L_0x004d
            r6 = r0
        L_0x004d:
            android.content.Context r6 = r6.getContext()
        L_0x0051:
            boolean r7 = r6 instanceof android.content.ContextWrapper
            r8 = 0
            if (r7 == 0) goto L_0x006a
            r7 = 10
            if (r5 >= r7) goto L_0x006a
            boolean r7 = r6 instanceof android.app.Activity
            if (r7 == 0) goto L_0x0061
            android.app.Activity r6 = (android.app.Activity) r6
            goto L_0x006b
        L_0x0061:
            android.content.ContextWrapper r6 = (android.content.ContextWrapper) r6
            android.content.Context r6 = r6.getBaseContext()
            int r5 = r5 + 1
            goto L_0x0051
        L_0x006a:
            r6 = r8
        L_0x006b:
            if (r6 != 0) goto L_0x006e
            goto L_0x0082
        L_0x006e:
            android.view.Window r5 = r6.getWindow()
            if (r5 != 0) goto L_0x0075
            goto L_0x0079
        L_0x0075:
            android.view.WindowManager$LayoutParams r8 = r5.getAttributes()
        L_0x0079:
            if (r8 == 0) goto L_0x0082
            int r5 = r8.flags
            r6 = 524288(0x80000, float:7.34684E-40)
            r5 = r5 & r6
            if (r5 != 0) goto L_0x0084
        L_0x0082:
            r4 = r4 | 8
        L_0x0084:
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>()
            boolean r5 = r0.getGlobalVisibleRect(r5)
            if (r5 != 0) goto L_0x0091
            r4 = r4 | 16
        L_0x0091:
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>()
            boolean r5 = r0.getLocalVisibleRect(r5)
            if (r5 != 0) goto L_0x009e
            r4 = r4 | 32
        L_0x009e:
            int r0 = r0.getWindowVisibility()
            int r5 = r9.zzl
            if (r5 == r1) goto L_0x00a7
            r0 = r5
        L_0x00a7:
            if (r0 == 0) goto L_0x00ab
            r4 = r4 | 64
        L_0x00ab:
            byte r0 = r9.zzk
            if (r0 == r4) goto L_0x00be
            byte r0 = (byte) r4
            r9.zzk = r0
            if (r4 != 0) goto L_0x00b9
            long r0 = android.os.SystemClock.elapsedRealtime()
            goto L_0x00bc
        L_0x00b9:
            long r0 = (long) r4
            long r0 = r2 - r0
        L_0x00bc:
            r9.zzm = r0
        L_0x00be:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayl.zzf():void");
    }

    private final void zzg() {
        zza.post(new zzayj(this));
    }

    private final void zzh(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zzh = new WeakReference(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zzf == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            zzayk zzayk = new zzayk(this);
            this.zzf = zzayk;
            this.zzb.registerReceiver(zzayk, intentFilter);
        }
        Application application = this.zzc;
        if (application != null) {
            try {
                application.registerActivityLifecycleCallbacks(this.zzj);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001b */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0025 A[Catch:{ Exception -> 0x002b }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x002f A[SYNTHETIC, Splitter:B:17:0x002f] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003a A[SYNTHETIC, Splitter:B:23:0x003a] */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzi(android.view.View r4) {
        /*
            r3 = this;
            r0 = 0
            java.lang.ref.WeakReference r1 = r3.zzh     // Catch:{ Exception -> 0x001b }
            if (r1 == 0) goto L_0x001b
            java.lang.Object r1 = r1.get()     // Catch:{ Exception -> 0x001b }
            android.view.ViewTreeObserver r1 = (android.view.ViewTreeObserver) r1     // Catch:{ Exception -> 0x001b }
            if (r1 == 0) goto L_0x0019
            boolean r2 = r1.isAlive()     // Catch:{ Exception -> 0x001b }
            if (r2 == 0) goto L_0x0019
            r1.removeOnScrollChangedListener(r3)     // Catch:{ Exception -> 0x001b }
            r1.removeGlobalOnLayoutListener(r3)     // Catch:{ Exception -> 0x001b }
        L_0x0019:
            r3.zzh = r0     // Catch:{ Exception -> 0x001b }
        L_0x001b:
            android.view.ViewTreeObserver r4 = r4.getViewTreeObserver()     // Catch:{ Exception -> 0x002b }
            boolean r1 = r4.isAlive()     // Catch:{ Exception -> 0x002b }
            if (r1 == 0) goto L_0x002b
            r4.removeOnScrollChangedListener(r3)     // Catch:{ Exception -> 0x002b }
            r4.removeGlobalOnLayoutListener(r3)     // Catch:{ Exception -> 0x002b }
        L_0x002b:
            android.content.BroadcastReceiver r4 = r3.zzf
            if (r4 == 0) goto L_0x0036
            android.content.Context r1 = r3.zzb     // Catch:{ Exception -> 0x0034 }
            r1.unregisterReceiver(r4)     // Catch:{ Exception -> 0x0034 }
        L_0x0034:
            r3.zzf = r0
        L_0x0036:
            android.app.Application r4 = r3.zzc
            if (r4 == 0) goto L_0x003f
            com.google.android.gms.internal.ads.zzaxt r0 = r3.zzj     // Catch:{ Exception -> 0x003f }
            r4.unregisterActivityLifecycleCallbacks(r0)     // Catch:{ Exception -> 0x003f }
        L_0x003f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayl.zzi(android.view.View):void");
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zze(activity, 0);
        zzf();
    }

    public final void onActivityDestroyed(Activity activity) {
        zzf();
    }

    public final void onActivityPaused(Activity activity) {
        zze(activity, 4);
        zzf();
    }

    public final void onActivityResumed(Activity activity) {
        zze(activity, 0);
        zzf();
        zzg();
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzf();
    }

    public final void onActivityStarted(Activity activity) {
        zze(activity, 0);
        zzf();
    }

    public final void onActivityStopped(Activity activity) {
        zzf();
    }

    public final void onGlobalLayout() {
        zzf();
    }

    public final void onScrollChanged() {
        zzf();
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzl = -1;
        zzh(view);
        zzf();
    }

    public final void onViewDetachedFromWindow(View view) {
        this.zzl = -1;
        zzf();
        zzg();
        zzi(view);
    }

    public final long zza() {
        if (this.zzm <= -2 && zzb() == null) {
            this.zzm = -3;
        }
        return this.zzm;
    }

    /* access modifiers changed from: package-private */
    public final View zzb() {
        WeakReference weakReference = this.zzi;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(View view) {
        long j;
        View zzb2 = zzb();
        if (zzb2 != null) {
            zzb2.removeOnAttachStateChangeListener(this);
            zzi(zzb2);
        }
        this.zzi = new WeakReference(view);
        if (view != null) {
            if (!(view.getWindowToken() == null && view.getWindowVisibility() == 8)) {
                zzh(view);
            }
            view.addOnAttachStateChangeListener(this);
            j = -2;
        } else {
            j = -3;
        }
        this.zzm = j;
    }
}
