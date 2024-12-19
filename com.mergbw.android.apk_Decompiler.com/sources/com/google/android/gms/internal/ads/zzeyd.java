package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzeyd implements zzexw {
    private final zzgge zza;
    private final Context zzb;

    public zzeyd(zzgge zzgge, Context context) {
        this.zza = zzgge;
        this.zzb = context;
    }

    private static ResolveInfo zzd(PackageManager packageManager, String str) {
        return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
    }

    public final int zza() {
        return 38;
    }

    public final ListenableFuture zzb() {
        return this.zza.zzb(new zzeyc(this));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00a1 A[Catch:{ Exception -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0184  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0186  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ com.google.android.gms.internal.ads.zzeyb zzc() throws java.lang.Exception {
        /*
            r25 = this;
            r0 = r25
            java.lang.String r1 = "com.google.unity.ads.UNITY_VERSION"
            android.content.Context r2 = r0.zzb
            android.content.pm.PackageManager r2 = r2.getPackageManager()
            java.util.Locale r3 = java.util.Locale.getDefault()
            java.lang.String r4 = "geo:0,0?q=donuts"
            android.content.pm.ResolveInfo r4 = zzd(r2, r4)
            java.lang.String r5 = "http://www.google.com"
            android.content.pm.ResolveInfo r5 = zzd(r2, r5)
            java.lang.String r9 = r3.getCountry()
            com.google.android.gms.ads.internal.zzu.zzp()
            com.google.android.gms.ads.internal.client.zzay.zzb()
            boolean r10 = com.google.android.gms.ads.internal.util.client.zzf.zzs()
            android.content.Context r6 = r0.zzb
            boolean r11 = com.google.android.gms.common.util.DeviceProperties.isLatchsky(r6)
            boolean r12 = com.google.android.gms.common.util.DeviceProperties.isSidewinder(r6)
            java.lang.String r13 = r3.getLanguage()
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            int r3 = android.os.Build.VERSION.SDK_INT
            r6 = 24
            r7 = 0
            if (r3 < r6) goto L_0x005b
            android.os.LocaleList r3 = com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0.m()
            r6 = r7
        L_0x0047:
            int r8 = androidx.core.graphics.ColorKt$$ExternalSyntheticApiModelOutline0.m((android.os.LocaleList) r3)
            if (r6 >= r8) goto L_0x005b
            java.util.Locale r8 = r3.get(r6)
            java.lang.String r8 = r8.getLanguage()
            r14.add(r8)
            int r6 = r6 + 1
            goto L_0x0047
        L_0x005b:
            android.content.Context r3 = r0.zzb
            java.lang.String r6 = "market://details?id=com.google.android.gms.ads"
            android.content.pm.ResolveInfo r6 = zzd(r2, r6)
            java.lang.String r8 = "."
            if (r6 != 0) goto L_0x0069
        L_0x0067:
            r15 = 0
            goto L_0x0091
        L_0x0069:
            android.content.pm.ActivityInfo r6 = r6.activityInfo
            if (r6 != 0) goto L_0x006e
            goto L_0x0067
        L_0x006e:
            com.google.android.gms.common.wrappers.PackageManagerWrapper r3 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r3)     // Catch:{ NameNotFoundException -> 0x0067 }
            java.lang.String r15 = r6.packageName     // Catch:{ NameNotFoundException -> 0x0067 }
            android.content.pm.PackageInfo r3 = r3.getPackageInfo(r15, r7)     // Catch:{ NameNotFoundException -> 0x0067 }
            if (r3 == 0) goto L_0x0067
            int r3 = r3.versionCode     // Catch:{ NameNotFoundException -> 0x0067 }
            java.lang.String r6 = r6.packageName     // Catch:{ NameNotFoundException -> 0x0067 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x0067 }
            r15.<init>()     // Catch:{ NameNotFoundException -> 0x0067 }
            r15.append(r3)     // Catch:{ NameNotFoundException -> 0x0067 }
            r15.append(r8)     // Catch:{ NameNotFoundException -> 0x0067 }
            r15.append(r6)     // Catch:{ NameNotFoundException -> 0x0067 }
            java.lang.String r3 = r15.toString()     // Catch:{ NameNotFoundException -> 0x0067 }
            r15 = r3
        L_0x0091:
            android.content.Context r3 = r0.zzb
            r6 = 128(0x80, float:1.794E-43)
            com.google.android.gms.common.wrappers.PackageManagerWrapper r3 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r3)     // Catch:{ Exception -> 0x00b8 }
            java.lang.String r7 = "com.android.vending"
            android.content.pm.PackageInfo r3 = r3.getPackageInfo(r7, r6)     // Catch:{ Exception -> 0x00b8 }
            if (r3 == 0) goto L_0x00b8
            int r7 = r3.versionCode     // Catch:{ Exception -> 0x00b8 }
            java.lang.String r3 = r3.packageName     // Catch:{ Exception -> 0x00b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b8 }
            r6.<init>()     // Catch:{ Exception -> 0x00b8 }
            r6.append(r7)     // Catch:{ Exception -> 0x00b8 }
            r6.append(r8)     // Catch:{ Exception -> 0x00b8 }
            r6.append(r3)     // Catch:{ Exception -> 0x00b8 }
            java.lang.String r3 = r6.toString()     // Catch:{ Exception -> 0x00b8 }
            goto L_0x00b9
        L_0x00b8:
            r3 = 0
        L_0x00b9:
            android.content.Context r6 = r0.zzb
            java.lang.String r20 = android.os.Build.FINGERPRINT
            if (r2 != 0) goto L_0x00c3
            r21 = r3
        L_0x00c1:
            r2 = 0
            goto L_0x0110
        L_0x00c3:
            android.content.Intent r7 = new android.content.Intent
            java.lang.String r8 = "android.intent.action.VIEW"
            java.lang.String r19 = "http://www.example.com"
            r21 = r3
            android.net.Uri r3 = android.net.Uri.parse(r19)
            r7.<init>(r8, r3)
            r3 = 0
            android.content.pm.ResolveInfo r8 = r2.resolveActivity(r7, r3)
            r3 = 65536(0x10000, float:9.18355E-41)
            java.util.List r2 = r2.queryIntentActivities(r7, r3)
            if (r2 == 0) goto L_0x00c1
            if (r8 == 0) goto L_0x00c1
            r3 = 0
        L_0x00e2:
            int r7 = r2.size()
            if (r3 >= r7) goto L_0x00c1
            java.lang.Object r7 = r2.get(r3)
            android.content.pm.ResolveInfo r7 = (android.content.pm.ResolveInfo) r7
            r19 = r2
            android.content.pm.ActivityInfo r2 = r8.activityInfo
            java.lang.String r2 = r2.name
            android.content.pm.ActivityInfo r7 = r7.activityInfo
            java.lang.String r7 = r7.name
            boolean r2 = r2.equals(r7)
            if (r2 == 0) goto L_0x010b
            android.content.pm.ActivityInfo r2 = r8.activityInfo
            java.lang.String r2 = r2.packageName
            java.lang.String r3 = com.google.android.gms.internal.ads.zzhlh.zza(r6)
            boolean r2 = r2.equals(r3)
            goto L_0x0110
        L_0x010b:
            int r3 = r3 + 1
            r2 = r19
            goto L_0x00e2
        L_0x0110:
            com.google.android.gms.ads.internal.zzu.zzp()
            android.os.StatFs r3 = new android.os.StatFs
            java.io.File r6 = android.os.Environment.getDataDirectory()
            java.lang.String r6 = r6.getAbsolutePath()
            r3.<init>(r6)
            long r6 = r3.getAvailableBytes()
            r22 = 1024(0x400, double:5.06E-321)
            long r22 = r6 / r22
            com.google.android.gms.internal.ads.zzbeg r3 = com.google.android.gms.internal.ads.zzbep.zzlq
            com.google.android.gms.internal.ads.zzben r6 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r3 = r6.zza(r3)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x0147
            com.google.android.gms.ads.internal.zzu.zzp()
            android.content.Context r3 = r0.zzb
            boolean r3 = com.google.android.gms.ads.internal.util.zzt.zzB(r3)
            if (r3 == 0) goto L_0x0147
            r3 = 1
            goto L_0x0148
        L_0x0147:
            r3 = 0
        L_0x0148:
            com.google.android.gms.internal.ads.zzbeg r7 = com.google.android.gms.internal.ads.zzbep.zzlu
            com.google.android.gms.internal.ads.zzben r8 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r7 = r8.zza(r7)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x017b
            android.content.Context r7 = r0.zzb
            com.google.android.gms.common.wrappers.PackageManagerWrapper r8 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r7)     // Catch:{ NameNotFoundException -> 0x0179 }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x0179 }
            r6 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r6 = r8.getApplicationInfo(r7, r6)     // Catch:{ NameNotFoundException -> 0x0179 }
            android.os.Bundle r6 = r6.metaData     // Catch:{ NameNotFoundException -> 0x0179 }
            if (r6 == 0) goto L_0x0179
            boolean r7 = r6.containsKey(r1)     // Catch:{ NameNotFoundException -> 0x0179 }
            if (r7 == 0) goto L_0x0179
            java.lang.String r1 = r6.getString(r1)     // Catch:{ NameNotFoundException -> 0x0179 }
            goto L_0x017d
        L_0x0179:
            r1 = 0
            goto L_0x017d
        L_0x017b:
            java.lang.String r1 = ""
        L_0x017d:
            if (r5 == 0) goto L_0x0181
            r8 = 1
            goto L_0x0182
        L_0x0181:
            r8 = 0
        L_0x0182:
            if (r4 == 0) goto L_0x0186
            r7 = 1
            goto L_0x0187
        L_0x0186:
            r7 = 0
        L_0x0187:
            com.google.android.gms.internal.ads.zzeyb r4 = new com.google.android.gms.internal.ads.zzeyb
            r6 = r4
            java.lang.String r19 = android.os.Build.MODEL
            int r24 = android.os.Build.VERSION.SDK_INT
            r16 = r21
            r17 = r20
            r18 = r2
            r20 = r22
            r22 = r3
            r23 = r1
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r22, r23, r24)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzeyd.zzc():com.google.android.gms.internal.ads.zzeyb");
    }
}
