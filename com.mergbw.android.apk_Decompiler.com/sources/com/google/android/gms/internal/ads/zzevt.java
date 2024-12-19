package com.google.android.gms.internal.ads;

import android.content.pm.PackageInfo;
import com.google.android.gms.ads.internal.util.zzg;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzevt implements zzexw {
    private final zzgge zza;
    private final zzfho zzb;
    private final PackageInfo zzc;
    private final zzg zzd;

    public zzevt(zzgge zzgge, zzfho zzfho, PackageInfo packageInfo, zzg zzg) {
        this.zza = zzgge;
        this.zzb = zzfho;
        this.zzc = packageInfo;
        this.zzd = zzg;
    }

    public static /* synthetic */ zzevu zzc(zzevt zzevt) {
        ArrayList arrayList = zzevt.zzb.zzg;
        if (arrayList == null) {
            return new zzevq();
        }
        return arrayList.isEmpty() ? new zzevr() : new zzevs(zzevt, arrayList);
    }

    public final int zza() {
        return 26;
    }

    public final ListenableFuture zzb() {
        return this.zza.zzb(new zzevp(this));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00fc, code lost:
        if (r9 == 3) goto L_0x011c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zzd(java.util.ArrayList r9, android.os.Bundle r10) {
        /*
            r8 = this;
            java.lang.String r0 = "native_version"
            r1 = 3
            r10.putInt(r0, r1)
            java.lang.String r0 = "native_templates"
            r10.putStringArrayList(r0, r9)
            com.google.android.gms.internal.ads.zzfho r9 = r8.zzb
            java.lang.String r0 = "native_custom_templates"
            java.util.ArrayList r9 = r9.zzh
            r10.putStringArrayList(r0, r9)
            com.google.android.gms.internal.ads.zzfho r9 = r8.zzb
            com.google.android.gms.internal.ads.zzbhk r9 = r9.zzi
            int r9 = r9.zza
            java.lang.String r0 = "landscape"
            java.lang.String r2 = "portrait"
            java.lang.String r3 = "any"
            java.lang.String r4 = "unknown"
            r5 = 2
            r6 = 1
            if (r9 <= r1) goto L_0x004f
            java.lang.String r9 = "enable_native_media_orientation"
            r10.putBoolean(r9, r6)
            com.google.android.gms.internal.ads.zzfho r9 = r8.zzb
            com.google.android.gms.internal.ads.zzbhk r9 = r9.zzi
            int r9 = r9.zzh
            if (r9 == r6) goto L_0x0043
            if (r9 == r5) goto L_0x0041
            if (r9 == r1) goto L_0x003f
            r7 = 4
            if (r9 == r7) goto L_0x003c
            r9 = r4
            goto L_0x0044
        L_0x003c:
            java.lang.String r9 = "square"
            goto L_0x0044
        L_0x003f:
            r9 = r2
            goto L_0x0044
        L_0x0041:
            r9 = r0
            goto L_0x0044
        L_0x0043:
            r9 = r3
        L_0x0044:
            boolean r7 = r4.equals(r9)
            if (r7 != 0) goto L_0x004f
            java.lang.String r7 = "native_media_orientation"
            r10.putString(r7, r9)
        L_0x004f:
            com.google.android.gms.internal.ads.zzfho r9 = r8.zzb
            com.google.android.gms.internal.ads.zzbhk r9 = r9.zzi
            int r9 = r9.zzc
            if (r9 == 0) goto L_0x005f
            if (r9 == r6) goto L_0x005d
            if (r9 == r5) goto L_0x0060
            r0 = r4
            goto L_0x0060
        L_0x005d:
            r0 = r2
            goto L_0x0060
        L_0x005f:
            r0 = r3
        L_0x0060:
            boolean r9 = r4.equals(r0)
            if (r9 != 0) goto L_0x006b
            java.lang.String r9 = "native_image_orientation"
            r10.putString(r9, r0)
        L_0x006b:
            com.google.android.gms.internal.ads.zzfho r9 = r8.zzb
            com.google.android.gms.internal.ads.zzbhk r9 = r9.zzi
            java.lang.String r0 = "native_multiple_images"
            boolean r9 = r9.zzd
            r10.putBoolean(r0, r9)
            com.google.android.gms.internal.ads.zzfho r9 = r8.zzb
            com.google.android.gms.internal.ads.zzbhk r9 = r9.zzi
            java.lang.String r0 = "use_custom_mute"
            boolean r9 = r9.zzg
            r10.putBoolean(r0, r9)
            com.google.android.gms.internal.ads.zzfho r9 = r8.zzb
            com.google.android.gms.internal.ads.zzbhk r9 = r9.zzi
            int r0 = r9.zzi
            if (r0 == 0) goto L_0x009b
            boolean r9 = r9.zzj
            java.lang.String r0 = "sccg_tap"
            r10.putBoolean(r0, r9)
            com.google.android.gms.internal.ads.zzfho r9 = r8.zzb
            com.google.android.gms.internal.ads.zzbhk r9 = r9.zzi
            java.lang.String r0 = "sccg_dir"
            int r9 = r9.zzi
            r10.putInt(r0, r9)
        L_0x009b:
            android.content.pm.PackageInfo r9 = r8.zzc
            if (r9 != 0) goto L_0x00a1
            r9 = 0
            goto L_0x00a3
        L_0x00a1:
            int r9 = r9.versionCode
        L_0x00a3:
            com.google.android.gms.ads.internal.util.zzg r0 = r8.zzd
            int r0 = r0.zza()
            if (r9 <= r0) goto L_0x00b5
            com.google.android.gms.ads.internal.util.zzg r0 = r8.zzd
            r0.zzt()
            com.google.android.gms.ads.internal.util.zzg r0 = r8.zzd
            r0.zzw(r9)
        L_0x00b5:
            com.google.android.gms.ads.internal.util.zzg r9 = r8.zzd
            org.json.JSONObject r9 = r9.zzq()
            r0 = 0
            if (r9 == 0) goto L_0x00cc
            com.google.android.gms.internal.ads.zzfho r2 = r8.zzb
            java.lang.String r2 = r2.zzf
            org.json.JSONArray r9 = r9.optJSONArray(r2)
            if (r9 == 0) goto L_0x00cc
            java.lang.String r0 = r9.toString()
        L_0x00cc:
            boolean r9 = android.text.TextUtils.isEmpty(r0)
            if (r9 != 0) goto L_0x00d7
            java.lang.String r9 = "native_advanced_settings"
            r10.putString(r9, r0)
        L_0x00d7:
            com.google.android.gms.internal.ads.zzfho r9 = r8.zzb
            int r9 = r9.zzk
            if (r9 <= r6) goto L_0x00e2
            java.lang.String r0 = "max_num_ads"
            r10.putInt(r0, r9)
        L_0x00e2:
            com.google.android.gms.internal.ads.zzfho r9 = r8.zzb
            com.google.android.gms.internal.ads.zzbnz r9 = r9.zzb
            if (r9 == 0) goto L_0x012e
            java.lang.String r0 = r9.zzc
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0122
            int r0 = r9.zza
            java.lang.String r2 = "p"
            java.lang.String r3 = "l"
            if (r0 < r5) goto L_0x00ff
            int r9 = r9.zzd
            if (r9 == r5) goto L_0x011b
            if (r9 == r1) goto L_0x011c
            goto L_0x011b
        L_0x00ff:
            int r9 = r9.zzb
            if (r9 == r6) goto L_0x011b
            if (r9 == r5) goto L_0x011c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Instream ad video aspect ratio "
            r0.<init>(r1)
            r0.append(r9)
            java.lang.String r9 = " is wrong."
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            com.google.android.gms.ads.internal.util.client.zzm.zzg(r9)
        L_0x011b:
            r2 = r3
        L_0x011c:
            java.lang.String r9 = "ia_var"
            r10.putString(r9, r2)
            goto L_0x0129
        L_0x0122:
            java.lang.String r9 = r9.zzc
            java.lang.String r0 = "ad_tag"
            r10.putString(r0, r9)
        L_0x0129:
            java.lang.String r9 = "instr"
            r10.putBoolean(r9, r6)
        L_0x012e:
            com.google.android.gms.internal.ads.zzfho r9 = r8.zzb
            com.google.android.gms.internal.ads.zzbjm r9 = r9.zza()
            if (r9 == 0) goto L_0x013b
            java.lang.String r9 = "has_delayed_banner_listener"
            r10.putBoolean(r9, r6)
        L_0x013b:
            com.google.android.gms.internal.ads.zzbeg r9 = com.google.android.gms.internal.ads.zzbep.zzlO
            com.google.android.gms.internal.ads.zzben r0 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r9 = r0.zza(r9)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x019c
            com.google.android.gms.internal.ads.zzfho r9 = r8.zzb
            com.google.android.gms.internal.ads.zzbhk r9 = r9.zzi
            com.google.android.gms.ads.internal.client.zzfk r9 = r9.zzf
            if (r9 == 0) goto L_0x0186
            android.os.Bundle r9 = new android.os.Bundle
            r9.<init>()
            com.google.android.gms.internal.ads.zzfho r0 = r8.zzb
            com.google.android.gms.internal.ads.zzbhk r0 = r0.zzi
            com.google.android.gms.ads.internal.client.zzfk r0 = r0.zzf
            boolean r0 = r0.zza
            java.lang.String r1 = "startMuted"
            r9.putBoolean(r1, r0)
            com.google.android.gms.internal.ads.zzfho r0 = r8.zzb
            com.google.android.gms.internal.ads.zzbhk r0 = r0.zzi
            com.google.android.gms.ads.internal.client.zzfk r0 = r0.zzf
            boolean r0 = r0.zzc
            java.lang.String r1 = "clickToExpandRequested"
            r9.putBoolean(r1, r0)
            com.google.android.gms.internal.ads.zzfho r0 = r8.zzb
            com.google.android.gms.internal.ads.zzbhk r0 = r0.zzi
            com.google.android.gms.ads.internal.client.zzfk r0 = r0.zzf
            boolean r0 = r0.zzb
            java.lang.String r1 = "customControlsRequested"
            r9.putBoolean(r1, r0)
            java.lang.String r0 = "video"
            r10.putBundle(r0, r9)
        L_0x0186:
            com.google.android.gms.internal.ads.zzfho r9 = r8.zzb
            com.google.android.gms.internal.ads.zzbhk r9 = r9.zzi
            java.lang.String r0 = "disable_image_loading"
            boolean r9 = r9.zzb
            r10.putBoolean(r0, r9)
            com.google.android.gms.internal.ads.zzfho r9 = r8.zzb
            com.google.android.gms.internal.ads.zzbhk r9 = r9.zzi
            java.lang.String r0 = "preferred_ad_choices_position"
            int r9 = r9.zze
            r10.putInt(r0, r9)
        L_0x019c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzevt.zzd(java.util.ArrayList, android.os.Bundle):void");
    }
}
