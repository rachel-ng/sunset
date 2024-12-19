package com.google.android.gms.internal.ads;

import android.content.Context;
import com.alibaba.android.arouter.utils.Consts;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.util.client.zzf;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.util.zze;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzcfd implements zzblp {
    private boolean zza;

    private static int zzb(Context context, Map map, String str, int i) {
        String str2 = (String) map.get(str);
        if (str2 != null) {
            try {
                zzay.zzb();
                i = zzf.zzy(context, Integer.parseInt(str2));
            } catch (NumberFormatException unused) {
                zzm.zzj("Could not parse " + str + " in a video GMSG: " + str2);
            }
        }
        if (zze.zzc()) {
            zze.zza("Parse pixels for " + str + ", got string " + str2 + ", int " + i + Consts.DOT);
        }
        return i;
    }

    private static void zzc(zzcds zzcds, Map map) {
        String str = (String) map.get("minBufferMs");
        String str2 = (String) map.get("maxBufferMs");
        String str3 = (String) map.get("bufferForPlaybackMs");
        String str4 = (String) map.get("bufferForPlaybackAfterRebufferMs");
        String str5 = (String) map.get("socketReceiveBufferSize");
        if (str != null) {
            try {
                zzcds.zzB(Integer.parseInt(str));
            } catch (NumberFormatException unused) {
                zzm.zzj(String.format("Could not parse buffer parameters in loadControl video GMSG: (%s, %s)", new Object[]{str, str2}));
                return;
            }
        }
        if (str2 != null) {
            zzcds.zzA(Integer.parseInt(str2));
        }
        if (str3 != null) {
            zzcds.zzy(Integer.parseInt(str3));
        }
        if (str4 != null) {
            zzcds.zzz(Integer.parseInt(str4));
        }
        if (str5 != null) {
            zzcds.zzD(Integer.parseInt(str5));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:151:0x02c4 A[SYNTHETIC, Splitter:B:151:0x02c4] */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x02f0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ void zza(java.lang.Object r22, java.util.Map r23) {
        /*
            r21 = this;
            r0 = r21
            r1 = r23
            r2 = r22
            com.google.android.gms.internal.ads.zzcee r2 = (com.google.android.gms.internal.ads.zzcee) r2
            java.lang.String r3 = "action"
            java.lang.Object r3 = r1.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            if (r3 != 0) goto L_0x0018
            java.lang.String r1 = "Action missing from video GMSG."
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            return
        L_0x0018:
            java.lang.String r4 = "playerId"
            boolean r5 = r1.containsKey(r4)
            if (r5 == 0) goto L_0x002f
            java.lang.Object r4 = r1.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            int r4 = java.lang.Integer.parseInt(r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            goto L_0x0030
        L_0x002f:
            r4 = 0
        L_0x0030:
            com.google.android.gms.internal.ads.zzcdt r5 = r2.zzo()
            if (r5 == 0) goto L_0x003f
            com.google.android.gms.internal.ads.zzcdt r5 = r2.zzo()
            java.lang.Integer r5 = r5.zzb()
            goto L_0x0040
        L_0x003f:
            r5 = 0
        L_0x0040:
            java.lang.String r7 = "load"
            r8 = 1
            r9 = 0
            if (r4 == 0) goto L_0x0068
            if (r5 == 0) goto L_0x0068
            boolean r10 = r4.equals(r5)
            if (r10 != 0) goto L_0x0068
            boolean r10 = r3.equals(r7)
            if (r10 == 0) goto L_0x0055
            goto L_0x0068
        L_0x0055:
            java.util.Locale r1 = java.util.Locale.US
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r9] = r4
            r2[r8] = r5
            java.lang.String r3 = "Event intended for player %s, but sent to player %d - event ignored"
            java.lang.String r1 = java.lang.String.format(r1, r3, r2)
            com.google.android.gms.ads.internal.util.client.zzm.zzi(r1)
            return
        L_0x0068:
            r5 = 3
            boolean r5 = com.google.android.gms.ads.internal.util.zze.zzm(r5)
            if (r5 == 0) goto L_0x0096
            org.json.JSONObject r5 = new org.json.JSONObject
            r5.<init>(r1)
            java.lang.String r10 = "google.afma.Notify_dt"
            r5.remove(r10)
            java.lang.String r5 = r5.toString()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Video GMSG: "
            r10.<init>(r11)
            r10.append(r3)
            java.lang.String r11 = " "
            r10.append(r11)
            r10.append(r5)
            java.lang.String r5 = r10.toString()
            com.google.android.gms.ads.internal.util.client.zzm.zze(r5)
        L_0x0096:
            java.lang.String r5 = "background"
            boolean r5 = r3.equals(r5)
            java.lang.String r10 = "color"
            if (r5 == 0) goto L_0x00c0
            java.lang.Object r1 = r1.get(r10)
            java.lang.String r1 = (java.lang.String) r1
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 == 0) goto L_0x00b2
            java.lang.String r1 = "Color parameter missing from background video GMSG."
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            return
        L_0x00b2:
            int r1 = android.graphics.Color.parseColor(r1)     // Catch:{ IllegalArgumentException -> 0x00ba }
            r2.setBackgroundColor(r1)     // Catch:{ IllegalArgumentException -> 0x00ba }
            return
        L_0x00ba:
            java.lang.String r1 = "Invalid color parameter in background video GMSG."
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            return
        L_0x00c0:
            java.lang.String r5 = "playerBackground"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x00e8
            java.lang.Object r1 = r1.get(r10)
            java.lang.String r1 = (java.lang.String) r1
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 == 0) goto L_0x00da
            java.lang.String r1 = "Color parameter missing from playerBackground video GMSG."
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            return
        L_0x00da:
            int r1 = android.graphics.Color.parseColor(r1)     // Catch:{ IllegalArgumentException -> 0x00e2 }
            r2.zzB(r1)     // Catch:{ IllegalArgumentException -> 0x00e2 }
            return
        L_0x00e2:
            java.lang.String r1 = "Invalid color parameter in playerBackground video GMSG."
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            return
        L_0x00e8:
            java.lang.String r5 = "decoderProps"
            boolean r10 = r3.equals(r5)
            java.lang.String r11 = "onVideoEvent"
            java.lang.String r12 = "event"
            if (r10 == 0) goto L_0x0143
            java.lang.String r3 = "mimeTypes"
            java.lang.Object r1 = r1.get(r3)
            java.lang.String r1 = (java.lang.String) r1
            if (r1 != 0) goto L_0x0116
            java.lang.String r1 = "No MIME types specified for decoder properties inspection."
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r1.put(r12, r5)
            java.lang.String r3 = "error"
            java.lang.String r4 = "missingMimeTypes"
            r1.put(r3, r4)
            r2.zzd(r11, r1)
            return
        L_0x0116:
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            java.lang.String r6 = ","
            java.lang.String[] r1 = r1.split(r6)
            int r6 = r1.length
        L_0x0122:
            if (r9 >= r6) goto L_0x0134
            r7 = r1[r9]
            java.lang.String r8 = r7.trim()
            java.util.List r8 = com.google.android.gms.ads.internal.util.zzcn.zza(r8)
            r4.put(r7, r8)
            int r9 = r9 + 1
            goto L_0x0122
        L_0x0134:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r1.put(r12, r5)
            r1.put(r3, r4)
            r2.zzd(r11, r1)
            return
        L_0x0143:
            com.google.android.gms.internal.ads.zzcdt r5 = r2.zzo()
            if (r5 != 0) goto L_0x014f
            java.lang.String r1 = "Could not get underlay container for a video GMSG."
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            return
        L_0x014f:
            java.lang.String r10 = "new"
            boolean r10 = r3.equals(r10)
            java.lang.String r13 = "position"
            boolean r13 = r3.equals(r13)
            java.lang.String r14 = "y"
            java.lang.String r15 = "x"
            if (r10 != 0) goto L_0x0360
            if (r13 == 0) goto L_0x0165
            goto L_0x0360
        L_0x0165:
            com.google.android.gms.internal.ads.zzcif r10 = r2.zzq()
            java.lang.String r13 = "currentTime"
            if (r10 == 0) goto L_0x01a2
            java.lang.String r6 = "timeupdate"
            boolean r6 = r3.equals(r6)
            if (r6 == 0) goto L_0x0195
            java.lang.Object r1 = r1.get(r13)
            java.lang.String r1 = (java.lang.String) r1
            if (r1 != 0) goto L_0x0183
            java.lang.String r1 = "currentTime parameter missing from timeupdate video GMSG."
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            return
        L_0x0183:
            float r2 = java.lang.Float.parseFloat(r1)     // Catch:{ NumberFormatException -> 0x018b }
            r10.zzt(r2)     // Catch:{ NumberFormatException -> 0x018b }
            return
        L_0x018b:
            java.lang.String r2 = "Could not parse currentTime parameter from timeupdate video GMSG: "
            java.lang.String r1 = r2.concat(r1)
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            return
        L_0x0195:
            java.lang.String r6 = "skip"
            boolean r6 = r3.equals(r6)
            if (r6 != 0) goto L_0x019e
            goto L_0x01a2
        L_0x019e:
            r10.zzu()
            return
        L_0x01a2:
            com.google.android.gms.internal.ads.zzcds r5 = r5.zza()
            if (r5 != 0) goto L_0x01b6
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.lang.String r3 = "no_video_view"
            r1.put(r12, r3)
            r2.zzd(r11, r1)
            return
        L_0x01b6:
            java.lang.String r6 = "click"
            boolean r6 = r3.equals(r6)
            if (r6 == 0) goto L_0x01de
            android.content.Context r2 = r2.getContext()
            int r3 = zzb(r2, r1, r15, r9)
            int r1 = zzb(r2, r1, r14, r9)
            float r11 = (float) r3
            float r12 = (float) r1
            long r8 = android.os.SystemClock.uptimeMillis()
            r10 = 0
            r13 = 0
            r6 = r8
            android.view.MotionEvent r1 = android.view.MotionEvent.obtain(r6, r8, r10, r11, r12, r13)
            r5.zzx(r1)
            r1.recycle()
            return
        L_0x01de:
            boolean r6 = r3.equals(r13)
            if (r6 == 0) goto L_0x020a
            java.lang.String r2 = "time"
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            if (r1 != 0) goto L_0x01f4
            java.lang.String r1 = "Time parameter missing from currentTime video GMSG."
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            return
        L_0x01f4:
            float r2 = java.lang.Float.parseFloat(r1)     // Catch:{ NumberFormatException -> 0x0200 }
            r3 = 1148846080(0x447a0000, float:1000.0)
            float r2 = r2 * r3
            int r2 = (int) r2     // Catch:{ NumberFormatException -> 0x0200 }
            r5.zzw(r2)     // Catch:{ NumberFormatException -> 0x0200 }
            return
        L_0x0200:
            java.lang.String r2 = "Could not parse time parameter from currentTime video GMSG: "
            java.lang.String r1 = r2.concat(r1)
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            return
        L_0x020a:
            java.lang.String r6 = "hide"
            boolean r6 = r3.equals(r6)
            if (r6 == 0) goto L_0x0217
            r1 = 4
            r5.setVisibility(r1)
            return
        L_0x0217:
            java.lang.String r6 = "remove"
            boolean r6 = r3.equals(r6)
            if (r6 == 0) goto L_0x0225
            r1 = 8
            r5.setVisibility(r1)
            return
        L_0x0225:
            boolean r6 = r3.equals(r7)
            if (r6 == 0) goto L_0x022f
            r5.zzr(r4)
            return
        L_0x022f:
            java.lang.String r4 = "loadControl"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x023b
            zzc(r5, r1)
            return
        L_0x023b:
            java.lang.String r4 = "muted"
            boolean r6 = r3.equals(r4)
            if (r6 == 0) goto L_0x0257
            java.lang.Object r1 = r1.get(r4)
            java.lang.String r1 = (java.lang.String) r1
            boolean r1 = java.lang.Boolean.parseBoolean(r1)
            if (r1 == 0) goto L_0x0253
            r5.zzs()
            return
        L_0x0253:
            r5.zzI()
            return
        L_0x0257:
            java.lang.String r4 = "pause"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x0263
            r5.zzu()
            return
        L_0x0263:
            java.lang.String r4 = "play"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x026f
            r5.zzv()
            return
        L_0x026f:
            java.lang.String r4 = "show"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x027b
            r5.setVisibility(r9)
            return
        L_0x027b:
            java.lang.String r4 = "src"
            boolean r6 = r3.equals(r4)
            if (r6 == 0) goto L_0x02fb
            java.lang.Object r3 = r1.get(r4)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r4 = "periodicReportIntervalMs"
            boolean r6 = r1.containsKey(r4)
            if (r6 != 0) goto L_0x0293
        L_0x0291:
            r6 = 0
            goto L_0x02b6
        L_0x0293:
            java.lang.Object r6 = r1.get(r4)     // Catch:{ NumberFormatException -> 0x02a2 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ NumberFormatException -> 0x02a2 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ NumberFormatException -> 0x02a2 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ NumberFormatException -> 0x02a2 }
            goto L_0x02b6
        L_0x02a2:
            java.lang.Object r4 = r1.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r6 = "Video gmsg invalid numeric parameter 'periodicReportIntervalMs': "
            java.lang.String r4 = r6.concat(r4)
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r4)
            goto L_0x0291
        L_0x02b6:
            java.lang.String[] r4 = new java.lang.String[r8]
            r4[r9] = r3
            java.lang.String r7 = "demuxed"
            java.lang.Object r1 = r1.get(r7)
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto L_0x02ee
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ JSONException -> 0x02e1 }
            r4.<init>(r1)     // Catch:{ JSONException -> 0x02e1 }
            int r7 = r4.length()     // Catch:{ JSONException -> 0x02e1 }
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ JSONException -> 0x02e1 }
            r10 = r9
        L_0x02d0:
            int r11 = r4.length()     // Catch:{ JSONException -> 0x02e1 }
            if (r10 >= r11) goto L_0x02df
            java.lang.String r11 = r4.getString(r10)     // Catch:{ JSONException -> 0x02e1 }
            r7[r10] = r11     // Catch:{ JSONException -> 0x02e1 }
            int r10 = r10 + 1
            goto L_0x02d0
        L_0x02df:
            r4 = r7
            goto L_0x02ee
        L_0x02e1:
            java.lang.String r4 = "Malformed demuxed URL list for playback: "
            java.lang.String r1 = r4.concat(r1)
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            java.lang.String[] r4 = new java.lang.String[r8]
            r4[r9] = r3
        L_0x02ee:
            if (r6 == 0) goto L_0x02f7
            int r1 = r6.intValue()
            r2.zzA(r1)
        L_0x02f7:
            r5.zzE(r3, r4)
            return
        L_0x02fb:
            java.lang.String r4 = "touchMove"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x0322
            android.content.Context r3 = r2.getContext()
            java.lang.String r4 = "dx"
            int r4 = zzb(r3, r1, r4, r9)
            java.lang.String r6 = "dy"
            int r1 = zzb(r3, r1, r6, r9)
            float r3 = (float) r4
            float r1 = (float) r1
            r5.zzH(r3, r1)
            boolean r1 = r0.zza
            if (r1 != 0) goto L_0x0470
            r2.zzu()
            r0.zza = r8
            return
        L_0x0322:
            java.lang.String r2 = "volume"
            boolean r4 = r3.equals(r2)
            if (r4 == 0) goto L_0x034a
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            if (r1 != 0) goto L_0x0338
            java.lang.String r1 = "Level parameter missing from volume video GMSG."
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            return
        L_0x0338:
            float r2 = java.lang.Float.parseFloat(r1)     // Catch:{ NumberFormatException -> 0x0340 }
            r5.zzG(r2)     // Catch:{ NumberFormatException -> 0x0340 }
            return
        L_0x0340:
            java.lang.String r2 = "Could not parse volume parameter from volume video GMSG: "
            java.lang.String r1 = r2.concat(r1)
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            return
        L_0x034a:
            java.lang.String r1 = "watermark"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0356
            r5.zzn()
            return
        L_0x0356:
            java.lang.String r1 = "Unknown video action: "
            java.lang.String r1 = r1.concat(r3)
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r1)
            return
        L_0x0360:
            android.content.Context r3 = r2.getContext()
            int r4 = zzb(r3, r1, r15, r9)
            int r15 = zzb(r3, r1, r14, r9)
            java.lang.String r6 = "w"
            r7 = -1
            int r6 = zzb(r3, r1, r6, r7)
            com.google.android.gms.internal.ads.zzbeg r8 = com.google.android.gms.internal.ads.zzbep.zzdR
            com.google.android.gms.internal.ads.zzben r11 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r8 = r11.zza(r8)
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            java.lang.String r11 = "."
            if (r8 == 0) goto L_0x0397
            if (r6 != r7) goto L_0x038e
            int r6 = r2.zzh()
            goto L_0x03ce
        L_0x038e:
            int r8 = r2.zzh()
            int r6 = java.lang.Math.min(r6, r8)
            goto L_0x03ce
        L_0x0397:
            boolean r8 = com.google.android.gms.ads.internal.util.zze.zzc()
            if (r8 == 0) goto L_0x03c5
            int r8 = r2.zzh()
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "Calculate width with original width "
            r12.<init>(r13)
            r12.append(r6)
            java.lang.String r13 = ", videoHost.getVideoBoundingWidth() "
            r12.append(r13)
            r12.append(r8)
            java.lang.String r8 = ", x "
            r12.append(r8)
            r12.append(r4)
            r12.append(r11)
            java.lang.String r8 = r12.toString()
            com.google.android.gms.ads.internal.util.zze.zza(r8)
        L_0x03c5:
            int r8 = r2.zzh()
            int r8 = r8 - r4
            int r6 = java.lang.Math.min(r6, r8)
        L_0x03ce:
            java.lang.String r8 = "h"
            int r3 = zzb(r3, r1, r8, r7)
            com.google.android.gms.internal.ads.zzbeg r8 = com.google.android.gms.internal.ads.zzbep.zzdR
            com.google.android.gms.internal.ads.zzben r12 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r8 = r12.zza(r8)
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x03f6
            if (r3 != r7) goto L_0x03ed
            int r2 = r2.zzg()
            goto L_0x042d
        L_0x03ed:
            int r2 = r2.zzg()
            int r2 = java.lang.Math.min(r3, r2)
            goto L_0x042d
        L_0x03f6:
            boolean r7 = com.google.android.gms.ads.internal.util.zze.zzc()
            if (r7 == 0) goto L_0x0424
            int r7 = r2.zzg()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r12 = "Calculate height with original height "
            r8.<init>(r12)
            r8.append(r3)
            java.lang.String r12 = ", videoHost.getVideoBoundingHeight() "
            r8.append(r12)
            r8.append(r7)
            java.lang.String r7 = ", y "
            r8.append(r7)
            r8.append(r15)
            r8.append(r11)
            java.lang.String r7 = r8.toString()
            com.google.android.gms.ads.internal.util.zze.zza(r7)
        L_0x0424:
            int r2 = r2.zzg()
            int r2 = r2 - r15
            int r2 = java.lang.Math.min(r3, r2)
        L_0x042d:
            java.lang.String r3 = "player"
            java.lang.Object r3 = r1.get(r3)     // Catch:{ NumberFormatException -> 0x0439 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ NumberFormatException -> 0x0439 }
            int r9 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x0439 }
        L_0x0439:
            r18 = r9
            java.lang.String r3 = "spherical"
            java.lang.Object r3 = r1.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            boolean r19 = java.lang.Boolean.parseBoolean(r3)
            if (r10 == 0) goto L_0x0471
            com.google.android.gms.internal.ads.zzcds r3 = r5.zza()
            if (r3 != 0) goto L_0x0471
            com.google.android.gms.internal.ads.zzced r3 = new com.google.android.gms.internal.ads.zzced
            java.lang.String r7 = "flags"
            java.lang.Object r7 = r1.get(r7)
            java.lang.String r7 = (java.lang.String) r7
            r3.<init>(r7)
            r13 = r5
            r14 = r4
            r16 = r6
            r17 = r2
            r20 = r3
            r13.zzd(r14, r15, r16, r17, r18, r19, r20)
            com.google.android.gms.internal.ads.zzcds r2 = r5.zza()
            if (r2 == 0) goto L_0x0470
            zzc(r2, r1)
        L_0x0470:
            return
        L_0x0471:
            r5.zzc(r4, r15, r6, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcfd.zza(java.lang.Object, java.util.Map):void");
    }
}
