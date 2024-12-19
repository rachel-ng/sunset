package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.util.zzad;
import com.google.android.gms.ads.internal.util.zzg;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzcag implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final Context zza;
    private final SharedPreferences zzb;
    private final zzg zzc;
    private String zzd = "-1";
    private int zze = -1;

    zzcag(Context context, zzg zzg) {
        this.zzb = PreferenceManager.getDefaultSharedPreferences(context);
        this.zzc = zzg;
        this.zza = context;
    }

    private final void zzb() {
        this.zzc.zzI(true);
        zzad.zzc(this.zza);
    }

    private final void zzc(String str, int i) {
        Context context;
        boolean z = false;
        if (!((Boolean) zzba.zzc().zza(zzbep.zzax)).booleanValue() ? str.isEmpty() || str.charAt(0) != '1' : i == 0 || str.isEmpty() || (str.charAt(0) != '1' && !str.equals("-1"))) {
            z = true;
        }
        this.zzc.zzI(z);
        if (((Boolean) zzba.zzc().zza(zzbep.zzgs)).booleanValue() && z && (context = this.zza) != null) {
            context.deleteDatabase("OfflineUpload.db");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a5 A[Catch:{ all -> 0x00b9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onSharedPreferenceChanged(android.content.SharedPreferences r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.String r0 = "IABTCF_PurposeConsents"
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzaz     // Catch:{ all -> 0x00b9 }
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x00b9 }
            java.lang.Object r1 = r2.zza(r1)     // Catch:{ all -> 0x00b9 }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x00b9 }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x00b9 }
            java.lang.String r2 = "-1"
            r3 = -1
            java.lang.String r4 = "gad_has_consent_for_cookies"
            if (r1 == 0) goto L_0x0055
            boolean r0 = java.util.Objects.equals(r10, r4)     // Catch:{ all -> 0x00b9 }
            if (r0 == 0) goto L_0x0034
            int r9 = r9.getInt(r4, r3)     // Catch:{ all -> 0x00b9 }
            com.google.android.gms.ads.internal.util.zzg r10 = r8.zzc     // Catch:{ all -> 0x00b9 }
            int r10 = r10.zzb()     // Catch:{ all -> 0x00b9 }
            if (r9 == r10) goto L_0x002e
            r8.zzb()     // Catch:{ all -> 0x00b9 }
        L_0x002e:
            com.google.android.gms.ads.internal.util.zzg r10 = r8.zzc     // Catch:{ all -> 0x00b9 }
            r10.zzF(r9)     // Catch:{ all -> 0x00b9 }
            return
        L_0x0034:
            java.lang.String r0 = "IABTCF_TCString"
            boolean r0 = java.util.Objects.equals(r10, r0)     // Catch:{ all -> 0x00b9 }
            if (r0 == 0) goto L_0x00b8
            java.lang.String r9 = r9.getString(r10, r2)     // Catch:{ all -> 0x00b9 }
            com.google.android.gms.ads.internal.util.zzg r10 = r8.zzc     // Catch:{ all -> 0x00b9 }
            java.lang.String r10 = r10.zzp()     // Catch:{ all -> 0x00b9 }
            boolean r10 = java.util.Objects.equals(r9, r10)     // Catch:{ all -> 0x00b9 }
            if (r10 != 0) goto L_0x004f
            r8.zzb()     // Catch:{ all -> 0x00b9 }
        L_0x004f:
            com.google.android.gms.ads.internal.util.zzg r10 = r8.zzc     // Catch:{ all -> 0x00b9 }
            r10.zzO(r9)     // Catch:{ all -> 0x00b9 }
            return
        L_0x0055:
            java.lang.String r1 = r9.getString(r0, r2)     // Catch:{ all -> 0x00b9 }
            int r9 = r9.getInt(r4, r3)     // Catch:{ all -> 0x00b9 }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x00b9 }
            int r5 = r10.hashCode()     // Catch:{ all -> 0x00b9 }
            r6 = -2004976699(0xffffffff887e7bc5, float:-7.6580835E-34)
            r7 = 1
            if (r5 == r6) goto L_0x0079
            r0 = -527267622(0xffffffffe09288da, float:-8.447143E19)
            if (r5 == r0) goto L_0x0071
            goto L_0x0081
        L_0x0071:
            boolean r10 = r10.equals(r4)
            if (r10 == 0) goto L_0x0081
            r10 = r7
            goto L_0x0082
        L_0x0079:
            boolean r10 = r10.equals(r0)
            if (r10 == 0) goto L_0x0081
            r10 = 0
            goto L_0x0082
        L_0x0081:
            r10 = r3
        L_0x0082:
            if (r10 == 0) goto L_0x00a5
            if (r10 == r7) goto L_0x0087
            goto L_0x00b8
        L_0x0087:
            com.google.android.gms.internal.ads.zzbeg r10 = com.google.android.gms.internal.ads.zzbep.zzax     // Catch:{ all -> 0x00b9 }
            com.google.android.gms.internal.ads.zzben r0 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x00b9 }
            java.lang.Object r10 = r0.zza(r10)     // Catch:{ all -> 0x00b9 }
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ all -> 0x00b9 }
            boolean r10 = r10.booleanValue()     // Catch:{ all -> 0x00b9 }
            if (r10 == 0) goto L_0x00b8
            if (r9 == r3) goto L_0x00b8
            int r10 = r8.zze     // Catch:{ all -> 0x00b9 }
            if (r10 == r9) goto L_0x00b8
            r8.zze = r9     // Catch:{ all -> 0x00b9 }
            r8.zzc(r1, r9)     // Catch:{ all -> 0x00b9 }
            return
        L_0x00a5:
            boolean r10 = r1.equals(r2)     // Catch:{ all -> 0x00b9 }
            if (r10 != 0) goto L_0x00b8
            java.lang.String r10 = r8.zzd     // Catch:{ all -> 0x00b9 }
            boolean r10 = r10.equals(r1)     // Catch:{ all -> 0x00b9 }
            if (r10 != 0) goto L_0x00b8
            r8.zzd = r1     // Catch:{ all -> 0x00b9 }
            r8.zzc(r1, r9)     // Catch:{ all -> 0x00b9 }
        L_0x00b8:
            return
        L_0x00b9:
            r9 = move-exception
            java.lang.String r10 = "AdMobPlusIdlessListener.onSharedPreferenceChanged"
            com.google.android.gms.internal.ads.zzcby r0 = com.google.android.gms.ads.internal.zzu.zzo()
            r0.zzw(r9, r10)
            java.lang.String r10 = "onSharedPreferenceChanged, errorMessage = "
            com.google.android.gms.ads.internal.util.zze.zzb(r10, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcag.onSharedPreferenceChanged(android.content.SharedPreferences, java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zzb.registerOnSharedPreferenceChangeListener(this);
        onSharedPreferenceChanged(this.zzb, "gad_has_consent_for_cookies");
        if (((Boolean) zzba.zzc().zza(zzbep.zzaz)).booleanValue()) {
            onSharedPreferenceChanged(this.zzb, "IABTCF_TCString");
        } else {
            onSharedPreferenceChanged(this.zzb, "IABTCF_PurposeConsents");
        }
    }
}
