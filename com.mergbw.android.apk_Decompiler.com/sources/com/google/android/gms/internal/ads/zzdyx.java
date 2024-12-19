package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.ViewGroup;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.internal.client.zzdi;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzdyx extends zzdi {
    final Map zza = new HashMap();
    private final Context zzb;
    private final WeakReference zzc;
    /* access modifiers changed from: private */
    public final zzdyl zzd;
    private final zzgge zze;
    private final zzdyy zzf;
    private zzdya zzg;

    zzdyx(Context context, WeakReference weakReference, zzdyl zzdyl, zzdyy zzdyy, zzgge zzgge) {
        this.zzb = context;
        this.zzc = weakReference;
        this.zzd = zzdyl;
        this.zze = zzgge;
        this.zzf = zzdyy;
    }

    private final Context zzj() {
        Context context = (Context) this.zzc.get();
        return context == null ? this.zzb : context;
    }

    private static AdRequest zzk() {
        Bundle bundle = new Bundle();
        bundle.putString("request_origin", "inspector_ooct");
        return ((AdRequest.Builder) new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, bundle)).build();
    }

    /* access modifiers changed from: private */
    public static String zzl(Object obj) {
        ResponseInfo responseInfo;
        zzdn zzc2;
        if (obj instanceof LoadAdError) {
            responseInfo = ((LoadAdError) obj).getResponseInfo();
        } else if (obj instanceof AppOpenAd) {
            responseInfo = ((AppOpenAd) obj).getResponseInfo();
        } else if (obj instanceof InterstitialAd) {
            responseInfo = ((InterstitialAd) obj).getResponseInfo();
        } else if (obj instanceof RewardedAd) {
            responseInfo = ((RewardedAd) obj).getResponseInfo();
        } else if (obj instanceof RewardedInterstitialAd) {
            responseInfo = ((RewardedInterstitialAd) obj).getResponseInfo();
        } else if (obj instanceof AdView) {
            responseInfo = ((AdView) obj).getResponseInfo();
        } else if (!(obj instanceof NativeAd)) {
            return "";
        } else {
            responseInfo = ((NativeAd) obj).getResponseInfo();
        }
        if (responseInfo == null || (zzc2 = responseInfo.zzc()) == null) {
            return "";
        }
        try {
            return zzc2.zzh();
        } catch (RemoteException unused) {
            return "";
        }
    }

    /* access modifiers changed from: private */
    public final synchronized void zzm(String str, String str2) {
        try {
            zzgft.zzr(this.zzg.zzb(str), new zzdyv(this, str2), this.zze);
        } catch (NullPointerException e) {
            zzu.zzo().zzw(e, "OutOfContextTester.setAdAsOutOfContext");
            this.zzd.zzk(str2);
        }
    }

    private final synchronized void zzn(String str, String str2) {
        try {
            zzgft.zzr(this.zzg.zzb(str), new zzdyw(this, str2), this.zze);
        } catch (NullPointerException e) {
            zzu.zzo().zzw(e, "OutOfContextTester.setAdAsShown");
            this.zzd.zzk(str2);
        }
    }

    public final void zze(String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        ViewGroup viewGroup = (ViewGroup) ObjectWrapper.unwrap(iObjectWrapper2);
        if (context != null && viewGroup != null) {
            Object obj = this.zza.get(str);
            if (obj != null) {
                this.zza.remove(str);
            }
            if (obj instanceof AdView) {
                zzdyy.zza(context, viewGroup, (AdView) obj);
            } else if (obj instanceof NativeAd) {
                zzdyy.zzb(context, viewGroup, (NativeAd) obj);
            }
        }
    }

    public final void zzf(zzdya zzdya) {
        this.zzg = zzdya;
    }

    /* access modifiers changed from: protected */
    public final synchronized void zzg(String str, Object obj, String str2) {
        this.zza.put(str, obj);
        zzm(zzl(obj), str2);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004a, code lost:
        r8 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004b, code lost:
        if (r8 == 0) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004d, code lost:
        if (r8 == 1) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004f, code lost:
        if (r8 == 2) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0051, code lost:
        if (r8 == 3) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0053, code lost:
        if (r8 == 4) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0055, code lost:
        if (r8 == 5) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0058, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd.load(zzj(), r7, zzk(), (com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback) new com.google.android.gms.internal.ads.zzdyt(r6, r7, r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        com.google.android.gms.ads.rewarded.RewardedAd.load(zzj(), r7, zzk(), (com.google.android.gms.ads.rewarded.RewardedAdLoadCallback) new com.google.android.gms.internal.ads.zzdys(r6, r7, r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r8 = new com.google.android.gms.ads.AdLoader.Builder(zzj(), r7);
        r8.forNativeAd(new com.google.android.gms.internal.ads.zzdym(r6, r7, r9));
        r8.withAdListener(new com.google.android.gms.internal.ads.zzdyu(r6, r9));
        r8.build().loadAd(zzk());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a2, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        com.google.android.gms.ads.interstitial.InterstitialAd.load(zzj(), r7, zzk(), new com.google.android.gms.internal.ads.zzdyr(r6, r7, r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b4, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r8 = new com.google.android.gms.ads.AdView(zzj());
        r8.setAdSize(com.google.android.gms.ads.AdSize.BANNER);
        r8.setAdUnitId(r7);
        r8.setAdListener(new com.google.android.gms.internal.ads.zzdyq(r6, r7, r8, r9));
        r8.loadAd(zzk());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00d6, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        com.google.android.gms.ads.appopen.AppOpenAd.load(zzj(), r7, zzk(), 1, (com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback) new com.google.android.gms.internal.ads.zzdyp(r6, r7, r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e8, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzh(java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            r6 = this;
            monitor-enter(r6)
            int r0 = r8.hashCode()     // Catch:{ all -> 0x00e9 }
            r1 = 5
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            switch(r0) {
                case -1999289321: goto L_0x0040;
                case -1372958932: goto L_0x0036;
                case -428325382: goto L_0x002c;
                case 543046670: goto L_0x0022;
                case 1854800829: goto L_0x0018;
                case 1951953708: goto L_0x000e;
                default: goto L_0x000d;
            }     // Catch:{ all -> 0x00e9 }
        L_0x000d:
            goto L_0x004a
        L_0x000e:
            java.lang.String r0 = "BANNER"
            boolean r8 = r8.equals(r0)     // Catch:{ all -> 0x00e9 }
            if (r8 == 0) goto L_0x004a
            r8 = r5
            goto L_0x004b
        L_0x0018:
            java.lang.String r0 = "REWARDED_INTERSTITIAL"
            boolean r8 = r8.equals(r0)     // Catch:{ all -> 0x00e9 }
            if (r8 == 0) goto L_0x004a
            r8 = r1
            goto L_0x004b
        L_0x0022:
            java.lang.String r0 = "REWARDED"
            boolean r8 = r8.equals(r0)     // Catch:{ all -> 0x00e9 }
            if (r8 == 0) goto L_0x004a
            r8 = r2
            goto L_0x004b
        L_0x002c:
            java.lang.String r0 = "APP_OPEN_AD"
            boolean r8 = r8.equals(r0)     // Catch:{ all -> 0x00e9 }
            if (r8 == 0) goto L_0x004a
            r8 = 0
            goto L_0x004b
        L_0x0036:
            java.lang.String r0 = "INTERSTITIAL"
            boolean r8 = r8.equals(r0)     // Catch:{ all -> 0x00e9 }
            if (r8 == 0) goto L_0x004a
            r8 = r4
            goto L_0x004b
        L_0x0040:
            java.lang.String r0 = "NATIVE"
            boolean r8 = r8.equals(r0)     // Catch:{ all -> 0x00e9 }
            if (r8 == 0) goto L_0x004a
            r8 = r3
            goto L_0x004b
        L_0x004a:
            r8 = -1
        L_0x004b:
            if (r8 == 0) goto L_0x00d7
            if (r8 == r5) goto L_0x00b5
            if (r8 == r4) goto L_0x00a3
            if (r8 == r3) goto L_0x007d
            if (r8 == r2) goto L_0x006b
            if (r8 == r1) goto L_0x0059
            monitor-exit(r6)
            return
        L_0x0059:
            android.content.Context r8 = r6.zzj()     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.ads.AdRequest r0 = zzk()     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.internal.ads.zzdyt r1 = new com.google.android.gms.internal.ads.zzdyt     // Catch:{ all -> 0x00e9 }
            r1.<init>(r6, r7, r9)     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd.load((android.content.Context) r8, (java.lang.String) r7, (com.google.android.gms.ads.AdRequest) r0, (com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback) r1)     // Catch:{ all -> 0x00e9 }
            monitor-exit(r6)
            return
        L_0x006b:
            android.content.Context r8 = r6.zzj()     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.ads.AdRequest r0 = zzk()     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.internal.ads.zzdys r1 = new com.google.android.gms.internal.ads.zzdys     // Catch:{ all -> 0x00e9 }
            r1.<init>(r6, r7, r9)     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.ads.rewarded.RewardedAd.load((android.content.Context) r8, (java.lang.String) r7, (com.google.android.gms.ads.AdRequest) r0, (com.google.android.gms.ads.rewarded.RewardedAdLoadCallback) r1)     // Catch:{ all -> 0x00e9 }
            monitor-exit(r6)
            return
        L_0x007d:
            com.google.android.gms.ads.AdLoader$Builder r8 = new com.google.android.gms.ads.AdLoader$Builder     // Catch:{ all -> 0x00e9 }
            android.content.Context r0 = r6.zzj()     // Catch:{ all -> 0x00e9 }
            r8.<init>(r0, r7)     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.internal.ads.zzdym r0 = new com.google.android.gms.internal.ads.zzdym     // Catch:{ all -> 0x00e9 }
            r0.<init>(r6, r7, r9)     // Catch:{ all -> 0x00e9 }
            r8.forNativeAd(r0)     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.internal.ads.zzdyu r7 = new com.google.android.gms.internal.ads.zzdyu     // Catch:{ all -> 0x00e9 }
            r7.<init>(r6, r9)     // Catch:{ all -> 0x00e9 }
            r8.withAdListener(r7)     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.ads.AdLoader r7 = r8.build()     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.ads.AdRequest r8 = zzk()     // Catch:{ all -> 0x00e9 }
            r7.loadAd((com.google.android.gms.ads.AdRequest) r8)     // Catch:{ all -> 0x00e9 }
            monitor-exit(r6)
            return
        L_0x00a3:
            android.content.Context r8 = r6.zzj()     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.ads.AdRequest r0 = zzk()     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.internal.ads.zzdyr r1 = new com.google.android.gms.internal.ads.zzdyr     // Catch:{ all -> 0x00e9 }
            r1.<init>(r6, r7, r9)     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.ads.interstitial.InterstitialAd.load(r8, r7, r0, r1)     // Catch:{ all -> 0x00e9 }
            monitor-exit(r6)
            return
        L_0x00b5:
            com.google.android.gms.ads.AdView r8 = new com.google.android.gms.ads.AdView     // Catch:{ all -> 0x00e9 }
            android.content.Context r0 = r6.zzj()     // Catch:{ all -> 0x00e9 }
            r8.<init>(r0)     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.ads.AdSize r0 = com.google.android.gms.ads.AdSize.BANNER     // Catch:{ all -> 0x00e9 }
            r8.setAdSize(r0)     // Catch:{ all -> 0x00e9 }
            r8.setAdUnitId(r7)     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.internal.ads.zzdyq r0 = new com.google.android.gms.internal.ads.zzdyq     // Catch:{ all -> 0x00e9 }
            r0.<init>(r6, r7, r8, r9)     // Catch:{ all -> 0x00e9 }
            r8.setAdListener(r0)     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.ads.AdRequest r7 = zzk()     // Catch:{ all -> 0x00e9 }
            r8.loadAd(r7)     // Catch:{ all -> 0x00e9 }
            monitor-exit(r6)
            return
        L_0x00d7:
            android.content.Context r8 = r6.zzj()     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.ads.AdRequest r0 = zzk()     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.internal.ads.zzdyp r1 = new com.google.android.gms.internal.ads.zzdyp     // Catch:{ all -> 0x00e9 }
            r1.<init>(r6, r7, r9)     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.ads.appopen.AppOpenAd.load((android.content.Context) r8, (java.lang.String) r7, (com.google.android.gms.ads.AdRequest) r0, (int) r5, (com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback) r1)     // Catch:{ all -> 0x00e9 }
            monitor-exit(r6)
            return
        L_0x00e9:
            r7 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x00e9 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdyx.zzh(java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ad, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzi(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            com.google.android.gms.internal.ads.zzdyl r0 = r4.zzd     // Catch:{ all -> 0x00ae }
            android.app.Activity r0 = r0.zzg()     // Catch:{ all -> 0x00ae }
            if (r0 != 0) goto L_0x000b
            goto L_0x00ac
        L_0x000b:
            java.util.Map r1 = r4.zza     // Catch:{ all -> 0x00ae }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x00ae }
            if (r1 == 0) goto L_0x00ac
            com.google.android.gms.internal.ads.zzbeg r2 = com.google.android.gms.internal.ads.zzbep.zzjt     // Catch:{ all -> 0x00ae }
            com.google.android.gms.internal.ads.zzben r3 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x00ae }
            java.lang.Object r2 = r3.zza(r2)     // Catch:{ all -> 0x00ae }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x00ae }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x00ae }
            if (r2 == 0) goto L_0x0035
            boolean r2 = r1 instanceof com.google.android.gms.ads.appopen.AppOpenAd     // Catch:{ all -> 0x00ae }
            if (r2 != 0) goto L_0x0035
            boolean r2 = r1 instanceof com.google.android.gms.ads.interstitial.InterstitialAd     // Catch:{ all -> 0x00ae }
            if (r2 != 0) goto L_0x0035
            boolean r2 = r1 instanceof com.google.android.gms.ads.rewarded.RewardedAd     // Catch:{ all -> 0x00ae }
            if (r2 != 0) goto L_0x0035
            boolean r2 = r1 instanceof com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd     // Catch:{ all -> 0x00ae }
            if (r2 == 0) goto L_0x003a
        L_0x0035:
            java.util.Map r2 = r4.zza     // Catch:{ all -> 0x00ae }
            r2.remove(r5)     // Catch:{ all -> 0x00ae }
        L_0x003a:
            java.lang.String r2 = zzl(r1)     // Catch:{ all -> 0x00ae }
            r4.zzn(r2, r6)     // Catch:{ all -> 0x00ae }
            boolean r6 = r1 instanceof com.google.android.gms.ads.appopen.AppOpenAd     // Catch:{ all -> 0x00ae }
            if (r6 == 0) goto L_0x004c
            com.google.android.gms.ads.appopen.AppOpenAd r1 = (com.google.android.gms.ads.appopen.AppOpenAd) r1     // Catch:{ all -> 0x00ae }
            r1.show(r0)     // Catch:{ all -> 0x00ae }
            monitor-exit(r4)
            return
        L_0x004c:
            boolean r6 = r1 instanceof com.google.android.gms.ads.interstitial.InterstitialAd     // Catch:{ all -> 0x00ae }
            if (r6 == 0) goto L_0x0057
            com.google.android.gms.ads.interstitial.InterstitialAd r1 = (com.google.android.gms.ads.interstitial.InterstitialAd) r1     // Catch:{ all -> 0x00ae }
            r1.show(r0)     // Catch:{ all -> 0x00ae }
            monitor-exit(r4)
            return
        L_0x0057:
            boolean r6 = r1 instanceof com.google.android.gms.ads.rewarded.RewardedAd     // Catch:{ all -> 0x00ae }
            if (r6 == 0) goto L_0x0067
            com.google.android.gms.ads.rewarded.RewardedAd r1 = (com.google.android.gms.ads.rewarded.RewardedAd) r1     // Catch:{ all -> 0x00ae }
            com.google.android.gms.internal.ads.zzdyn r5 = new com.google.android.gms.internal.ads.zzdyn     // Catch:{ all -> 0x00ae }
            r5.<init>()     // Catch:{ all -> 0x00ae }
            r1.show(r0, r5)     // Catch:{ all -> 0x00ae }
            monitor-exit(r4)
            return
        L_0x0067:
            boolean r6 = r1 instanceof com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd     // Catch:{ all -> 0x00ae }
            if (r6 == 0) goto L_0x0077
            com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd r1 = (com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd) r1     // Catch:{ all -> 0x00ae }
            com.google.android.gms.internal.ads.zzdyo r5 = new com.google.android.gms.internal.ads.zzdyo     // Catch:{ all -> 0x00ae }
            r5.<init>()     // Catch:{ all -> 0x00ae }
            r1.show(r0, r5)     // Catch:{ all -> 0x00ae }
            monitor-exit(r4)
            return
        L_0x0077:
            com.google.android.gms.internal.ads.zzbeg r6 = com.google.android.gms.internal.ads.zzbep.zzjt     // Catch:{ all -> 0x00ae }
            com.google.android.gms.internal.ads.zzben r0 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x00ae }
            java.lang.Object r6 = r0.zza(r6)     // Catch:{ all -> 0x00ae }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x00ae }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x00ae }
            if (r6 == 0) goto L_0x00ac
            boolean r6 = r1 instanceof com.google.android.gms.ads.AdView     // Catch:{ all -> 0x00ae }
            if (r6 != 0) goto L_0x0091
            boolean r6 = r1 instanceof com.google.android.gms.ads.nativead.NativeAd     // Catch:{ all -> 0x00ae }
            if (r6 == 0) goto L_0x00ac
        L_0x0091:
            android.content.Intent r6 = new android.content.Intent     // Catch:{ all -> 0x00ae }
            r6.<init>()     // Catch:{ all -> 0x00ae }
            android.content.Context r0 = r4.zzj()     // Catch:{ all -> 0x00ae }
            java.lang.String r1 = "com.google.android.gms.ads.OutOfContextTestingActivity"
            r6.setClassName(r0, r1)     // Catch:{ all -> 0x00ae }
            java.lang.String r1 = "adUnit"
            r6.putExtra(r1, r5)     // Catch:{ all -> 0x00ae }
            com.google.android.gms.ads.internal.zzu.zzp()     // Catch:{ all -> 0x00ae }
            com.google.android.gms.ads.internal.util.zzt.zzT(r0, r6)     // Catch:{ all -> 0x00ae }
            monitor-exit(r4)
            return
        L_0x00ac:
            monitor-exit(r4)
            return
        L_0x00ae:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00ae }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdyx.zzi(java.lang.String, java.lang.String):void");
    }
}
