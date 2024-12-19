package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzki implements Application.ActivityLifecycleCallbacks {
    private final /* synthetic */ zziv zza;

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ad A[SYNTHETIC, Splitter:B:38:0x00ad] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0114 A[Catch:{ RuntimeException -> 0x019f }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0115 A[Catch:{ RuntimeException -> 0x019f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void zza(com.google.android.gms.measurement.internal.zzki r17, boolean r18, android.net.Uri r19, java.lang.String r20, java.lang.String r21) {
        /*
            r1 = r17
            r0 = r20
            r2 = r21
            com.google.android.gms.measurement.internal.zziv r3 = r1.zza
            r3.zzt()
            com.google.android.gms.measurement.internal.zziv r3 = r1.zza     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zznp r3 = r3.zzq()     // Catch:{ RuntimeException -> 0x019f }
            boolean r4 = com.google.android.gms.internal.measurement.zzoj.zza()     // Catch:{ RuntimeException -> 0x019f }
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L_0x0029
            com.google.android.gms.measurement.internal.zziv r4 = r1.zza     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzag r4 = r4.zze()     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbf.zzcl     // Catch:{ RuntimeException -> 0x019f }
            boolean r4 = r4.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r7)     // Catch:{ RuntimeException -> 0x019f }
            if (r4 == 0) goto L_0x0029
            r4 = r6
            goto L_0x002a
        L_0x0029:
            r4 = r5
        L_0x002a:
            boolean r7 = android.text.TextUtils.isEmpty(r21)     // Catch:{ RuntimeException -> 0x019f }
            java.lang.String r8 = "Activity created with data 'referrer' without required params"
            java.lang.String r9 = "utm_medium"
            java.lang.String r10 = "_cis"
            java.lang.String r11 = "utm_source"
            java.lang.String r12 = "utm_campaign"
            java.lang.String r14 = "gclid"
            if (r7 == 0) goto L_0x003e
        L_0x003c:
            r3 = 0
            goto L_0x00a9
        L_0x003e:
            boolean r7 = r2.contains(r14)     // Catch:{ RuntimeException -> 0x019f }
            if (r7 != 0) goto L_0x008c
            if (r4 == 0) goto L_0x004e
            java.lang.String r7 = "gbraid"
            boolean r7 = r2.contains(r7)     // Catch:{ RuntimeException -> 0x019f }
            if (r7 != 0) goto L_0x008c
        L_0x004e:
            boolean r7 = r2.contains(r12)     // Catch:{ RuntimeException -> 0x019f }
            if (r7 != 0) goto L_0x008c
            boolean r7 = r2.contains(r11)     // Catch:{ RuntimeException -> 0x019f }
            if (r7 != 0) goto L_0x008c
            boolean r7 = r2.contains(r9)     // Catch:{ RuntimeException -> 0x019f }
            if (r7 != 0) goto L_0x008c
            java.lang.String r7 = "utm_id"
            boolean r7 = r2.contains(r7)     // Catch:{ RuntimeException -> 0x019f }
            if (r7 != 0) goto L_0x008c
            java.lang.String r7 = "dclid"
            boolean r7 = r2.contains(r7)     // Catch:{ RuntimeException -> 0x019f }
            if (r7 != 0) goto L_0x008c
            java.lang.String r7 = "srsltid"
            boolean r7 = r2.contains(r7)     // Catch:{ RuntimeException -> 0x019f }
            if (r7 != 0) goto L_0x008c
            java.lang.String r7 = "sfmc_id"
            boolean r7 = r2.contains(r7)     // Catch:{ RuntimeException -> 0x019f }
            if (r7 != 0) goto L_0x008c
            com.google.android.gms.measurement.internal.zzfw r3 = r3.zzj()     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzc()     // Catch:{ RuntimeException -> 0x019f }
            r3.zza(r8)     // Catch:{ RuntimeException -> 0x019f }
            goto L_0x003c
        L_0x008c:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x019f }
            java.lang.String r15 = "https://google.com/search?"
            r7.<init>(r15)     // Catch:{ RuntimeException -> 0x019f }
            r7.append(r2)     // Catch:{ RuntimeException -> 0x019f }
            java.lang.String r7 = r7.toString()     // Catch:{ RuntimeException -> 0x019f }
            android.net.Uri r7 = android.net.Uri.parse(r7)     // Catch:{ RuntimeException -> 0x019f }
            android.os.Bundle r3 = r3.zza((android.net.Uri) r7, (boolean) r4)     // Catch:{ RuntimeException -> 0x019f }
            if (r3 == 0) goto L_0x00a9
            java.lang.String r4 = "referrer"
            r3.putString(r10, r4)     // Catch:{ RuntimeException -> 0x019f }
        L_0x00a9:
            java.lang.String r4 = "_cmp"
            if (r18 == 0) goto L_0x010c
            com.google.android.gms.measurement.internal.zziv r7 = r1.zza     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zznp r7 = r7.zzq()     // Catch:{ RuntimeException -> 0x019f }
            boolean r15 = com.google.android.gms.internal.measurement.zzoj.zza()     // Catch:{ RuntimeException -> 0x019f }
            if (r15 == 0) goto L_0x00cb
            com.google.android.gms.measurement.internal.zziv r15 = r1.zza     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzag r15 = r15.zze()     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r13 = com.google.android.gms.measurement.internal.zzbf.zzcl     // Catch:{ RuntimeException -> 0x019f }
            boolean r13 = r15.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r13)     // Catch:{ RuntimeException -> 0x019f }
            if (r13 == 0) goto L_0x00cb
            r13 = r19
            r15 = r6
            goto L_0x00ce
        L_0x00cb:
            r13 = r19
            r15 = r5
        L_0x00ce:
            android.os.Bundle r7 = r7.zza((android.net.Uri) r13, (boolean) r15)     // Catch:{ RuntimeException -> 0x019f }
            if (r7 == 0) goto L_0x010c
            java.lang.String r13 = "intent"
            r7.putString(r10, r13)     // Catch:{ RuntimeException -> 0x019f }
            boolean r10 = r7.containsKey(r14)     // Catch:{ RuntimeException -> 0x019f }
            if (r10 != 0) goto L_0x00fd
            if (r3 == 0) goto L_0x00fd
            boolean r10 = r3.containsKey(r14)     // Catch:{ RuntimeException -> 0x019f }
            if (r10 == 0) goto L_0x00fd
            java.lang.String r10 = "_cer"
            java.lang.String r13 = "gclid=%s"
            java.lang.String r15 = r3.getString(r14)     // Catch:{ RuntimeException -> 0x019f }
            r16 = r8
            java.lang.Object[] r8 = new java.lang.Object[r6]     // Catch:{ RuntimeException -> 0x019f }
            r8[r5] = r15     // Catch:{ RuntimeException -> 0x019f }
            java.lang.String r5 = java.lang.String.format(r13, r8)     // Catch:{ RuntimeException -> 0x019f }
            r7.putString(r10, r5)     // Catch:{ RuntimeException -> 0x019f }
            goto L_0x00ff
        L_0x00fd:
            r16 = r8
        L_0x00ff:
            com.google.android.gms.measurement.internal.zziv r5 = r1.zza     // Catch:{ RuntimeException -> 0x019f }
            r5.zzc(r0, r4, r7)     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zziv r5 = r1.zza     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzr r5 = r5.zza     // Catch:{ RuntimeException -> 0x019f }
            r5.zza(r0, r7)     // Catch:{ RuntimeException -> 0x019f }
            goto L_0x010e
        L_0x010c:
            r16 = r8
        L_0x010e:
            boolean r5 = android.text.TextUtils.isEmpty(r21)     // Catch:{ RuntimeException -> 0x019f }
            if (r5 == 0) goto L_0x0115
            return
        L_0x0115:
            com.google.android.gms.measurement.internal.zziv r5 = r1.zza     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzfw r5 = r5.zzj()     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzfy r5 = r5.zzc()     // Catch:{ RuntimeException -> 0x019f }
            java.lang.String r7 = "Activity created with referrer"
            r5.zza(r7, r2)     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zziv r5 = r1.zza     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzag r5 = r5.zze()     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbf.zzbl     // Catch:{ RuntimeException -> 0x019f }
            boolean r5 = r5.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r7)     // Catch:{ RuntimeException -> 0x019f }
            java.lang.String r7 = "_ldl"
            java.lang.String r8 = "auto"
            if (r5 == 0) goto L_0x015b
            if (r3 == 0) goto L_0x0145
            com.google.android.gms.measurement.internal.zziv r2 = r1.zza     // Catch:{ RuntimeException -> 0x019f }
            r2.zzc(r0, r4, r3)     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zziv r2 = r1.zza     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzr r2 = r2.zza     // Catch:{ RuntimeException -> 0x019f }
            r2.zza(r0, r3)     // Catch:{ RuntimeException -> 0x019f }
            goto L_0x0154
        L_0x0145:
            com.google.android.gms.measurement.internal.zziv r0 = r1.zza     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzfw r0 = r0.zzj()     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzc()     // Catch:{ RuntimeException -> 0x019f }
            java.lang.String r3 = "Referrer does not contain valid parameters"
            r0.zza(r3, r2)     // Catch:{ RuntimeException -> 0x019f }
        L_0x0154:
            com.google.android.gms.measurement.internal.zziv r0 = r1.zza     // Catch:{ RuntimeException -> 0x019f }
            r2 = 0
            r0.zza((java.lang.String) r8, (java.lang.String) r7, (java.lang.Object) r2, (boolean) r6)     // Catch:{ RuntimeException -> 0x019f }
            return
        L_0x015b:
            boolean r0 = r2.contains(r14)     // Catch:{ RuntimeException -> 0x019f }
            if (r0 == 0) goto L_0x018f
            boolean r0 = r2.contains(r12)     // Catch:{ RuntimeException -> 0x019f }
            if (r0 != 0) goto L_0x0183
            boolean r0 = r2.contains(r11)     // Catch:{ RuntimeException -> 0x019f }
            if (r0 != 0) goto L_0x0183
            boolean r0 = r2.contains(r9)     // Catch:{ RuntimeException -> 0x019f }
            if (r0 != 0) goto L_0x0183
            java.lang.String r0 = "utm_term"
            boolean r0 = r2.contains(r0)     // Catch:{ RuntimeException -> 0x019f }
            if (r0 != 0) goto L_0x0183
            java.lang.String r0 = "utm_content"
            boolean r0 = r2.contains(r0)     // Catch:{ RuntimeException -> 0x019f }
            if (r0 == 0) goto L_0x018f
        L_0x0183:
            boolean r0 = android.text.TextUtils.isEmpty(r21)     // Catch:{ RuntimeException -> 0x019f }
            if (r0 != 0) goto L_0x018e
            com.google.android.gms.measurement.internal.zziv r0 = r1.zza     // Catch:{ RuntimeException -> 0x019f }
            r0.zza((java.lang.String) r8, (java.lang.String) r7, (java.lang.Object) r2, (boolean) r6)     // Catch:{ RuntimeException -> 0x019f }
        L_0x018e:
            return
        L_0x018f:
            com.google.android.gms.measurement.internal.zziv r0 = r1.zza     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzfw r0 = r0.zzj()     // Catch:{ RuntimeException -> 0x019f }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzc()     // Catch:{ RuntimeException -> 0x019f }
            r2 = r16
            r0.zza(r2)     // Catch:{ RuntimeException -> 0x019f }
            return
        L_0x019f:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zziv r1 = r1.zza
            com.google.android.gms.measurement.internal.zzfw r1 = r1.zzj()
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzg()
            java.lang.String r2 = "Throwable caught in handleReferrerForOnActivityCreated"
            r1.zza(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzki.zza(com.google.android.gms.measurement.internal.zzki, boolean, android.net.Uri, java.lang.String, java.lang.String):void");
    }

    zzki(zziv zziv) {
        this.zza = zziv;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        String str;
        try {
            this.zza.zzj().zzp().zza("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent != null) {
                Uri data = intent.getData();
                if (data == null || !data.isHierarchical()) {
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        String string = extras.getString("com.android.vending.referral_url");
                        if (!TextUtils.isEmpty(string)) {
                            data = Uri.parse(string);
                        }
                    }
                    data = null;
                }
                Uri uri = data;
                if (uri != null) {
                    if (uri.isHierarchical()) {
                        this.zza.zzq();
                        if (zznp.zza(intent)) {
                            str = "gs";
                        } else {
                            str = "auto";
                        }
                        this.zza.zzl().zzb((Runnable) new zzkh(this, bundle == null, uri, str, uri.getQueryParameter("referrer")));
                        this.zza.zzn().zza(activity, bundle);
                        return;
                    }
                }
                this.zza.zzn().zza(activity, bundle);
            }
        } catch (RuntimeException e) {
            this.zza.zzj().zzg().zza("Throwable caught in onActivityCreated", e);
        } finally {
            this.zza.zzn().zza(activity, bundle);
        }
    }

    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzn().zza(activity);
    }

    public final void onActivityPaused(Activity activity) {
        this.zza.zzn().zzb(activity);
        zzmh zzp = this.zza.zzp();
        zzp.zzl().zzb((Runnable) new zzmj(zzp, zzp.zzb().elapsedRealtime()));
    }

    public final void onActivityResumed(Activity activity) {
        zzmh zzp = this.zza.zzp();
        zzp.zzl().zzb((Runnable) new zzmk(zzp, zzp.zzb().elapsedRealtime()));
        this.zza.zzn().zzc(activity);
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zza.zzn().zzb(activity, bundle);
    }
}
