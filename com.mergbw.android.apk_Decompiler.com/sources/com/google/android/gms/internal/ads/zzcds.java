package com.google.android.gms.internal.ads;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.util.zze;
import com.google.android.gms.ads.internal.util.zzt;
import com.google.android.gms.ads.internal.zzu;
import java.util.HashMap;
import java.util.Objects;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzcds extends FrameLayout implements zzcdj {
    final zzceg zza;
    private final zzcee zzb;
    private final FrameLayout zzc;
    private final View zzd;
    private final zzbfe zze;
    private final long zzf;
    private final zzcdk zzg;
    private boolean zzh;
    private boolean zzi;
    private boolean zzj;
    private boolean zzk;
    private long zzl;
    private long zzm;
    private String zzn;
    private String[] zzo;
    private Bitmap zzp;
    private final ImageView zzq;
    private boolean zzr;

    /* JADX WARNING: type inference failed for: r13v0, types: [com.google.android.gms.internal.ads.zzcdk, android.view.View] */
    /* JADX WARNING: type inference failed for: r1v27, types: [com.google.android.gms.internal.ads.zzcdi] */
    /* JADX WARNING: type inference failed for: r1v28, types: [com.google.android.gms.internal.ads.zzcew] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzcds(android.content.Context r16, com.google.android.gms.internal.ads.zzcee r17, int r18, boolean r19, com.google.android.gms.internal.ads.zzbfe r20, com.google.android.gms.internal.ads.zzced r21) {
        /*
            r15 = this;
            r0 = r15
            r8 = r16
            r9 = r20
            r15.<init>(r16)
            r7 = r17
            r0.zzb = r7
            r0.zze = r9
            android.widget.FrameLayout r10 = new android.widget.FrameLayout
            r10.<init>(r8)
            r0.zzc = r10
            android.widget.FrameLayout$LayoutParams r1 = new android.widget.FrameLayout$LayoutParams
            r11 = -1
            r1.<init>(r11, r11)
            r15.addView(r10, r1)
            com.google.android.gms.ads.internal.zza r1 = r17.zzj()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r1)
            com.google.android.gms.ads.internal.zza r1 = r17.zzj()
            com.google.android.gms.internal.ads.zzcdl r1 = r1.zza
            com.google.android.gms.internal.ads.zzcef r12 = new com.google.android.gms.internal.ads.zzcef
            com.google.android.gms.ads.internal.util.client.VersionInfoParcel r3 = r17.zzn()
            java.lang.String r4 = r17.zzdi()
            com.google.android.gms.internal.ads.zzbfb r6 = r17.zzk()
            r1 = r12
            r2 = r16
            r5 = r20
            r1.<init>(r2, r3, r4, r5, r6)
            r1 = 2
            r2 = r18
            if (r2 != r1) goto L_0x005a
            com.google.android.gms.internal.ads.zzcew r13 = new com.google.android.gms.internal.ads.zzcew
            boolean r6 = com.google.android.gms.internal.ads.zzcdw.zza(r17)
            r1 = r13
            r2 = r16
            r3 = r12
            r4 = r17
            r5 = r19
            r7 = r21
            r1.<init>(r2, r3, r4, r5, r6, r7)
            goto L_0x0082
        L_0x005a:
            com.google.android.gms.internal.ads.zzcdi r13 = new com.google.android.gms.internal.ads.zzcdi
            boolean r12 = com.google.android.gms.internal.ads.zzcdw.zza(r17)
            com.google.android.gms.internal.ads.zzcef r14 = new com.google.android.gms.internal.ads.zzcef
            com.google.android.gms.ads.internal.util.client.VersionInfoParcel r3 = r17.zzn()
            java.lang.String r4 = r17.zzdi()
            com.google.android.gms.internal.ads.zzbfb r6 = r17.zzk()
            r1 = r14
            r2 = r16
            r5 = r20
            r1.<init>(r2, r3, r4, r5, r6)
            r1 = r13
            r3 = r17
            r4 = r19
            r5 = r12
            r6 = r21
            r7 = r14
            r1.<init>(r2, r3, r4, r5, r6, r7)
        L_0x0082:
            r0.zzg = r13
            android.view.View r1 = new android.view.View
            r1.<init>(r8)
            r0.zzd = r1
            r2 = 0
            r1.setBackgroundColor(r2)
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r3 = 17
            r2.<init>(r11, r11, r3)
            r10.addView(r13, r2)
            com.google.android.gms.internal.ads.zzbeg r2 = com.google.android.gms.internal.ads.zzbep.zzF
            com.google.android.gms.internal.ads.zzben r3 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r2 = r3.zza(r2)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x00b6
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r2.<init>(r11, r11)
            r10.addView(r1, r2)
            r10.bringChildToFront(r1)
        L_0x00b6:
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzC
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r1 = r2.zza(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x00cb
            r15.zzn()
        L_0x00cb:
            android.widget.ImageView r1 = new android.widget.ImageView
            r1.<init>(r8)
            r0.zzq = r1
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzH
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r1 = r2.zza(r1)
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            r0.zzf = r1
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzE
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r1 = r2.zza(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            r0.zzk = r1
            if (r9 == 0) goto L_0x0105
            r2 = 1
            if (r2 == r1) goto L_0x00fe
            java.lang.String r1 = "0"
            goto L_0x0100
        L_0x00fe:
            java.lang.String r1 = "1"
        L_0x0100:
            java.lang.String r2 = "spinner_used"
            r9.zzd(r2, r1)
        L_0x0105:
            com.google.android.gms.internal.ads.zzceg r1 = new com.google.android.gms.internal.ads.zzceg
            r1.<init>(r15)
            r0.zza = r1
            r13.zzr(r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcds.<init>(android.content.Context, com.google.android.gms.internal.ads.zzcee, int, boolean, com.google.android.gms.internal.ads.zzbfe, com.google.android.gms.internal.ads.zzced):void");
    }

    private final void zzJ() {
        if (this.zzb.zzi() != null && this.zzi && !this.zzj) {
            this.zzb.zzi().getWindow().clearFlags(128);
            this.zzi = false;
        }
    }

    /* access modifiers changed from: private */
    public final void zzK(String str, String... strArr) {
        HashMap hashMap = new HashMap();
        Integer zzl2 = zzl();
        if (zzl2 != null) {
            hashMap.put("playerId", zzl2.toString());
        }
        hashMap.put(NotificationCompat.CATEGORY_EVENT, str);
        String str2 = null;
        for (String str3 : strArr) {
            if (str2 == null) {
                str2 = str3;
            } else {
                hashMap.put(str2, str3);
                str2 = null;
            }
        }
        this.zzb.zzd("onVideoEvent", hashMap);
    }

    private final boolean zzL() {
        return this.zzq.getParent() != null;
    }

    public final void finalize() throws Throwable {
        try {
            this.zza.zza();
            zzcdk zzcdk = this.zzg;
            if (zzcdk != null) {
                zzgge zzgge = zzcci.zze;
                Objects.requireNonNull(zzcdk);
                zzgge.execute(new zzcdm(zzcdk));
            }
        } finally {
            super.finalize();
        }
    }

    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            this.zza.zzb();
        } else {
            this.zza.zza();
            this.zzm = this.zzl;
        }
        zzt.zza.post(new zzcdo(this, z));
    }

    public final void onWindowVisibilityChanged(int i) {
        boolean z;
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            this.zza.zzb();
            z = true;
        } else {
            this.zza.zza();
            this.zzm = this.zzl;
            z = false;
        }
        zzt.zza.post(new zzcdr(this, z));
    }

    public final void zzA(int i) {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            zzcdk.zzz(i);
        }
    }

    public final void zzB(int i) {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            zzcdk.zzA(i);
        }
    }

    public final void zzC(int i) {
        if (((Boolean) zzba.zzc().zza(zzbep.zzF)).booleanValue()) {
            this.zzc.setBackgroundColor(i);
            this.zzd.setBackgroundColor(i);
        }
    }

    public final void zzD(int i) {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            zzcdk.zzB(i);
        }
    }

    public final void zzE(String str, String[] strArr) {
        this.zzn = str;
        this.zzo = strArr;
    }

    public final void zzF(int i, int i2, int i3, int i4) {
        if (zze.zzc()) {
            zze.zza("Set video bounds to x:" + i + ";y:" + i2 + ";w:" + i3 + ";h:" + i4);
        }
        if (i3 != 0 && i4 != 0) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i3, i4);
            layoutParams.setMargins(i, i2, 0, 0);
            this.zzc.setLayoutParams(layoutParams);
            requestLayout();
        }
    }

    public final void zzG(float f) {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            zzcdk.zzb.zze(f);
            zzcdk.zzn();
        }
    }

    public final void zzH(float f, float f2) {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            zzcdk.zzu(f, f2);
        }
    }

    public final void zzI() {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            zzcdk.zzb.zzd(false);
            zzcdk.zzn();
        }
    }

    public final void zza() {
        if (((Boolean) zzba.zzc().zza(zzbep.zzbT)).booleanValue()) {
            this.zza.zza();
        }
        zzK("ended", new String[0]);
        zzJ();
    }

    public final void zzb(String str, String str2) {
        zzK("error", "what", str, "extra", str2);
    }

    public final void zzc(String str, String str2) {
        zzK("exception", "what", "ExoPlayerAdapter exception", "extra", str2);
    }

    public final void zzd() {
        zzK("pause", new String[0]);
        zzJ();
        this.zzh = false;
    }

    public final void zze() {
        if (((Boolean) zzba.zzc().zza(zzbep.zzbT)).booleanValue()) {
            this.zza.zzb();
        }
        if (this.zzb.zzi() != null && !this.zzi) {
            boolean z = (this.zzb.zzi().getWindow().getAttributes().flags & 128) != 0;
            this.zzj = z;
            if (!z) {
                this.zzb.zzi().getWindow().addFlags(128);
                this.zzi = true;
            }
        }
        this.zzh = true;
    }

    public final void zzf() {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null && this.zzm == 0) {
            zzcdk zzcdk2 = this.zzg;
            zzK("canplaythrough", TypedValues.TransitionType.S_DURATION, String.valueOf(((float) zzcdk.zzc()) / 1000.0f), "videoWidth", String.valueOf(zzcdk2.zze()), "videoHeight", String.valueOf(zzcdk2.zzd()));
        }
    }

    public final void zzg() {
        this.zzd.setVisibility(4);
        zzt.zza.post(new zzcdn(this));
    }

    public final void zzh() {
        this.zza.zzb();
        zzt.zza.post(new zzcdp(this));
    }

    public final void zzi() {
        if (this.zzr && this.zzp != null && !zzL()) {
            this.zzq.setImageBitmap(this.zzp);
            this.zzq.invalidate();
            this.zzc.addView(this.zzq, new FrameLayout.LayoutParams(-1, -1));
            this.zzc.bringChildToFront(this.zzq);
        }
        this.zza.zza();
        this.zzm = this.zzl;
        zzt.zza.post(new zzcdq(this));
    }

    public final void zzj(int i, int i2) {
        if (this.zzk) {
            int max = Math.max(i / ((Integer) zzba.zzc().zza(zzbep.zzG)).intValue(), 1);
            int max2 = Math.max(i2 / ((Integer) zzba.zzc().zza(zzbep.zzG)).intValue(), 1);
            Bitmap bitmap = this.zzp;
            if (bitmap == null || bitmap.getWidth() != max || this.zzp.getHeight() != max2) {
                this.zzp = Bitmap.createBitmap(max, max2, Bitmap.Config.ARGB_8888);
                this.zzr = false;
            }
        }
    }

    public final void zzk() {
        if (this.zzh && zzL()) {
            this.zzc.removeView(this.zzq);
        }
        if (this.zzg != null && this.zzp != null) {
            long elapsedRealtime = zzu.zzB().elapsedRealtime();
            if (this.zzg.getBitmap(this.zzp) != null) {
                this.zzr = true;
            }
            long elapsedRealtime2 = zzu.zzB().elapsedRealtime() - elapsedRealtime;
            if (zze.zzc()) {
                zze.zza("Spinner frame grab took " + elapsedRealtime2 + "ms");
            }
            if (elapsedRealtime2 > this.zzf) {
                zzm.zzj("Spinner frame grab crossed jank threshold! Suspending spinner.");
                this.zzk = false;
                this.zzp = null;
                zzbfe zzbfe = this.zze;
                if (zzbfe != null) {
                    zzbfe.zzd("spinner_jank", Long.toString(elapsedRealtime2));
                }
            }
        }
    }

    public final Integer zzl() {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            return zzcdk.zzw();
        }
        return null;
    }

    public final void zzn() {
        String str;
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            TextView textView = new TextView(zzcdk.getContext());
            Resources zze2 = zzu.zzo().zze();
            if (zze2 == null) {
                str = "AdMob - ";
            } else {
                str = zze2.getString(R.string.watermark_label_prefix);
            }
            textView.setText(String.valueOf(str).concat(this.zzg.zzj()));
            textView.setTextColor(SupportMenu.CATEGORY_MASK);
            textView.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
            this.zzc.addView(textView, new FrameLayout.LayoutParams(-2, -2, 17));
            this.zzc.bringChildToFront(textView);
        }
    }

    public final void zzo() {
        this.zza.zza();
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            zzcdk.zzt();
        }
        zzJ();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzp() {
        zzK("firstFrameRendered", new String[0]);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzq(boolean z) {
        zzK("windowFocusChanged", "hasWindowFocus", String.valueOf(z));
    }

    public final void zzr(Integer num) {
        if (this.zzg != null) {
            if (!TextUtils.isEmpty(this.zzn)) {
                this.zzg.zzC(this.zzn, this.zzo, num);
            } else {
                zzK("no_src", new String[0]);
            }
        }
    }

    public final void zzs() {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            zzcdk.zzb.zzd(true);
            zzcdk.zzn();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzt() {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            long zza2 = (long) zzcdk.zza();
            if (this.zzl != zza2 && zza2 > 0) {
                float f = ((float) zza2) / 1000.0f;
                if (((Boolean) zzba.zzc().zza(zzbep.zzbR)).booleanValue()) {
                    zzK("timeupdate", "time", String.valueOf(f), "totalBytes", String.valueOf(this.zzg.zzh()), "qoeCachedBytes", String.valueOf(this.zzg.zzf()), "qoeLoadedBytes", String.valueOf(this.zzg.zzg()), "droppedFrames", String.valueOf(this.zzg.zzb()), "reportTime", String.valueOf(zzu.zzB().currentTimeMillis()));
                } else {
                    zzK("timeupdate", "time", String.valueOf(f));
                }
                this.zzl = zza2;
            }
        }
    }

    public final void zzu() {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            zzcdk.zzo();
        }
    }

    public final void zzv() {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            zzcdk.zzp();
        }
    }

    public final void zzw(int i) {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            zzcdk.zzq(i);
        }
    }

    public final void zzx(MotionEvent motionEvent) {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            zzcdk.dispatchTouchEvent(motionEvent);
        }
    }

    public final void zzy(int i) {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            zzcdk.zzx(i);
        }
    }

    public final void zzz(int i) {
        zzcdk zzcdk = this.zzg;
        if (zzcdk != null) {
            zzcdk.zzy(i);
        }
    }
}
