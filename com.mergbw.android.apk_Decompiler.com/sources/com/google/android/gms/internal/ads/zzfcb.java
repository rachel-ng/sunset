package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zze;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzfcb implements zzeps {
    protected final zzcjd zza;
    private final Context zzb;
    /* access modifiers changed from: private */
    public final Executor zzc;
    /* access modifiers changed from: private */
    public final zzfcr zzd;
    /* access modifiers changed from: private */
    public final zzfek zze;
    private final VersionInfoParcel zzf;
    private final ViewGroup zzg;
    /* access modifiers changed from: private */
    public final zzfmq zzh;
    private final zzfhm zzi;
    /* access modifiers changed from: private */
    @Nullable
    public ListenableFuture zzj;

    protected zzfcb(Context context, Executor executor, zzcjd zzcjd, zzfek zzfek, zzfcr zzfcr, zzfhm zzfhm, VersionInfoParcel versionInfoParcel) {
        this.zzb = context;
        this.zzc = executor;
        this.zza = zzcjd;
        this.zze = zzfek;
        this.zzd = zzfcr;
        this.zzi = zzfhm;
        this.zzf = versionInfoParcel;
        this.zzg = new FrameLayout(context);
        this.zzh = zzcjd.zzz();
    }

    /* access modifiers changed from: private */
    public final synchronized zzcyr zzm(zzfei zzfei) {
        zzfca zzfca = (zzfca) zzfei;
        if (((Boolean) zzba.zzc().zza(zzbep.zzil)).booleanValue()) {
            zzcsc zzcsc = new zzcsc(this.zzg);
            zzcyt zzcyt = new zzcyt();
            zzcyt.zze(this.zzb);
            zzcyt.zzi(zzfca.zza);
            zzcyv zzj2 = zzcyt.zzj();
            zzdfa zzdfa = new zzdfa();
            zzdfa.zzc(this.zzd, this.zzc);
            zzdfa.zzl(this.zzd, this.zzc);
            return zze(zzcsc, zzj2, zzdfa.zzn());
        }
        zzfcr zzi2 = zzfcr.zzi(this.zzd);
        zzdfa zzdfa2 = new zzdfa();
        zzdfa2.zzb(zzi2, this.zzc);
        zzdfa2.zzg(zzi2, this.zzc);
        zzdfa2.zzh(zzi2, this.zzc);
        zzdfa2.zzi(zzi2, this.zzc);
        zzdfa2.zzc(zzi2, this.zzc);
        zzdfa2.zzl(zzi2, this.zzc);
        zzdfa2.zzm(zzi2);
        zzcsc zzcsc2 = new zzcsc(this.zzg);
        zzcyt zzcyt2 = new zzcyt();
        zzcyt2.zze(this.zzb);
        zzcyt2.zzi(zzfca.zza);
        return zze(zzcsc2, zzcyt2.zzj(), zzdfa2.zzn());
    }

    public final boolean zza() {
        ListenableFuture listenableFuture = this.zzj;
        return listenableFuture != null && !listenableFuture.isDone();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0056 A[SYNTHETIC, Splitter:B:16:0x0056] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean zzb(com.google.android.gms.ads.internal.client.zzl r9, java.lang.String r10, com.google.android.gms.internal.ads.zzepq r11, com.google.android.gms.internal.ads.zzepr r12) throws android.os.RemoteException {
        /*
            r8 = this;
            monitor-enter(r8)
            com.google.android.gms.internal.ads.zzbfv r11 = com.google.android.gms.internal.ads.zzbgi.zzd     // Catch:{ all -> 0x0132 }
            java.lang.Object r11 = r11.zze()     // Catch:{ all -> 0x0132 }
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch:{ all -> 0x0132 }
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x0132 }
            r0 = 1
            r1 = 0
            if (r11 == 0) goto L_0x0025
            com.google.android.gms.internal.ads.zzbeg r11 = com.google.android.gms.internal.ads.zzbep.zzlg     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x0132 }
            java.lang.Object r11 = r2.zza(r11)     // Catch:{ all -> 0x0132 }
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch:{ all -> 0x0132 }
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x0132 }
            if (r11 == 0) goto L_0x0025
            r11 = r0
            goto L_0x0026
        L_0x0025:
            r11 = r1
        L_0x0026:
            com.google.android.gms.ads.internal.util.client.VersionInfoParcel r2 = r8.zzf     // Catch:{ all -> 0x0132 }
            int r2 = r2.clientJarVersion     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzbeg r3 = com.google.android.gms.internal.ads.zzbep.zzlh     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzben r4 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x0132 }
            java.lang.Object r3 = r4.zza(r3)     // Catch:{ all -> 0x0132 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ all -> 0x0132 }
            int r3 = r3.intValue()     // Catch:{ all -> 0x0132 }
            if (r2 < r3) goto L_0x003e
            if (r11 != 0) goto L_0x0043
        L_0x003e:
            java.lang.String r11 = "loadAd must be called on the main UI thread."
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r11)     // Catch:{ all -> 0x0132 }
        L_0x0043:
            if (r10 != 0) goto L_0x0056
            java.lang.String r9 = "Ad unit ID should not be null for app open ad."
            com.google.android.gms.ads.internal.util.client.zzm.zzg(r9)     // Catch:{ all -> 0x0132 }
            java.util.concurrent.Executor r9 = r8.zzc     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzfbv r10 = new com.google.android.gms.internal.ads.zzfbv     // Catch:{ all -> 0x0132 }
            r10.<init>(r8)     // Catch:{ all -> 0x0132 }
            r9.execute(r10)     // Catch:{ all -> 0x0132 }
            monitor-exit(r8)
            return r1
        L_0x0056:
            com.google.common.util.concurrent.ListenableFuture r11 = r8.zzj     // Catch:{ all -> 0x0132 }
            if (r11 == 0) goto L_0x005c
            monitor-exit(r8)
            return r1
        L_0x005c:
            com.google.android.gms.internal.ads.zzbfv r11 = com.google.android.gms.internal.ads.zzbgd.zzc     // Catch:{ all -> 0x0132 }
            java.lang.Object r11 = r11.zze()     // Catch:{ all -> 0x0132 }
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch:{ all -> 0x0132 }
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x0132 }
            r2 = 0
            if (r11 == 0) goto L_0x008e
            com.google.android.gms.internal.ads.zzfek r11 = r8.zze     // Catch:{ all -> 0x0132 }
            java.lang.Object r3 = r11.zzd()     // Catch:{ all -> 0x0132 }
            if (r3 == 0) goto L_0x008e
            java.lang.Object r11 = r11.zzd()     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzcrp r11 = (com.google.android.gms.internal.ads.zzcrp) r11     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzfmn r11 = r11.zzh()     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzfmw r3 = com.google.android.gms.internal.ads.zzfmw.FORMAT_APP_OPEN     // Catch:{ all -> 0x0132 }
            r11.zzd(r3)     // Catch:{ all -> 0x0132 }
            java.lang.String r3 = r9.zzp     // Catch:{ all -> 0x0132 }
            r11.zzb(r3)     // Catch:{ all -> 0x0132 }
            android.os.Bundle r3 = r9.zzm     // Catch:{ all -> 0x0132 }
            r11.zzg(r3)     // Catch:{ all -> 0x0132 }
            r4 = r11
            goto L_0x008f
        L_0x008e:
            r4 = r2
        L_0x008f:
            android.content.Context r11 = r8.zzb     // Catch:{ all -> 0x0132 }
            boolean r3 = r9.zzf     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzfil.zza(r11, r3)     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzbeg r11 = com.google.android.gms.internal.ads.zzbep.zziU     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzben r3 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x0132 }
            java.lang.Object r11 = r3.zza(r11)     // Catch:{ all -> 0x0132 }
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch:{ all -> 0x0132 }
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x0132 }
            if (r11 == 0) goto L_0x00b5
            boolean r11 = r9.zzf     // Catch:{ all -> 0x0132 }
            if (r11 == 0) goto L_0x00b5
            com.google.android.gms.internal.ads.zzcjd r11 = r8.zza     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzdya r11 = r11.zzl()     // Catch:{ all -> 0x0132 }
            r11.zzo(r0)     // Catch:{ all -> 0x0132 }
        L_0x00b5:
            android.util.Pair r11 = new android.util.Pair     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzdul r3 = com.google.android.gms.internal.ads.zzdul.PUBLIC_API_CALL     // Catch:{ all -> 0x0132 }
            java.lang.String r3 = r3.zza()     // Catch:{ all -> 0x0132 }
            long r5 = r9.zzz     // Catch:{ all -> 0x0132 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0132 }
            r11.<init>(r3, r5)     // Catch:{ all -> 0x0132 }
            android.util.Pair r3 = new android.util.Pair     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzdul r5 = com.google.android.gms.internal.ads.zzdul.DYNAMITE_ENTER     // Catch:{ all -> 0x0132 }
            java.lang.String r5 = r5.zza()     // Catch:{ all -> 0x0132 }
            com.google.android.gms.common.util.Clock r6 = com.google.android.gms.ads.internal.zzu.zzB()     // Catch:{ all -> 0x0132 }
            long r6 = r6.currentTimeMillis()     // Catch:{ all -> 0x0132 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0132 }
            r3.<init>(r5, r6)     // Catch:{ all -> 0x0132 }
            r5 = 2
            android.util.Pair[] r5 = new android.util.Pair[r5]     // Catch:{ all -> 0x0132 }
            r5[r1] = r11     // Catch:{ all -> 0x0132 }
            r5[r0] = r3     // Catch:{ all -> 0x0132 }
            android.os.Bundle r11 = com.google.android.gms.internal.ads.zzdun.zza(r5)     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzfhm r1 = r8.zzi     // Catch:{ all -> 0x0132 }
            r1.zzt(r10)     // Catch:{ all -> 0x0132 }
            com.google.android.gms.ads.internal.client.zzq r10 = com.google.android.gms.ads.internal.client.zzq.zzb()     // Catch:{ all -> 0x0132 }
            r1.zzs(r10)     // Catch:{ all -> 0x0132 }
            r1.zzH(r9)     // Catch:{ all -> 0x0132 }
            r1.zzA(r11)     // Catch:{ all -> 0x0132 }
            android.content.Context r10 = r8.zzb     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzfho r11 = r1.zzJ()     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzfmu r1 = com.google.android.gms.internal.ads.zzfmm.zza(r11)     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzfmw r3 = com.google.android.gms.internal.ads.zzfmw.FORMAT_APP_OPEN     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzfmc r5 = com.google.android.gms.internal.ads.zzfmb.zzb(r10, r1, r3, r9)     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzfca r6 = new com.google.android.gms.internal.ads.zzfca     // Catch:{ all -> 0x0132 }
            r6.<init>(r2)     // Catch:{ all -> 0x0132 }
            r6.zza = r11     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzfek r9 = r8.zze     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzfel r10 = new com.google.android.gms.internal.ads.zzfel     // Catch:{ all -> 0x0132 }
            r10.<init>(r6, r2)     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzfbw r11 = new com.google.android.gms.internal.ads.zzfbw     // Catch:{ all -> 0x0132 }
            r11.<init>(r8)     // Catch:{ all -> 0x0132 }
            com.google.common.util.concurrent.ListenableFuture r9 = r9.zzc(r10, r11, r2)     // Catch:{ all -> 0x0132 }
            r8.zzj = r9     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzfby r10 = new com.google.android.gms.internal.ads.zzfby     // Catch:{ all -> 0x0132 }
            r1 = r10
            r2 = r8
            r3 = r12
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0132 }
            java.util.concurrent.Executor r11 = r8.zzc     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.ads.zzgft.zzr(r9, r10, r11)     // Catch:{ all -> 0x0132 }
            monitor-exit(r8)
            return r0
        L_0x0132:
            r9 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0132 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfcb.zzb(com.google.android.gms.ads.internal.client.zzl, java.lang.String, com.google.android.gms.internal.ads.zzepq, com.google.android.gms.internal.ads.zzepr):boolean");
    }

    /* access modifiers changed from: protected */
    public abstract zzcyr zze(zzcsc zzcsc, zzcyv zzcyv, zzdfc zzdfc);

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzk() {
        this.zzd.zzdB(zzfiq.zzd(6, (String) null, (zze) null));
    }

    public final void zzl(zzw zzw) {
        this.zzi.zzu(zzw);
    }
}
