package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzq> CREATOR = new zzr();
    public final String zza;
    public final int zzb;
    public final int zzc;
    public final boolean zzd;
    public final int zze;
    public final int zzf;
    public final zzq[] zzg;
    public final boolean zzh;
    public final boolean zzi;
    public boolean zzj;
    public boolean zzk;
    public boolean zzl;
    public boolean zzm;
    public boolean zzn;
    public boolean zzo;

    public zzq() {
        this("interstitial_mb", 0, 0, true, 0, 0, (zzq[]) null, false, false, false, false, false, false, false, false);
    }

    public static int zza(DisplayMetrics displayMetrics) {
        return (int) (((float) zzf(displayMetrics)) * displayMetrics.density);
    }

    public static zzq zzb() {
        return new zzq("interstitial_mb", 0, 0, false, 0, 0, (zzq[]) null, false, false, false, false, true, false, false, false);
    }

    public static zzq zzc() {
        return new zzq("320x50_mb", 0, 0, false, 0, 0, (zzq[]) null, true, false, false, false, false, false, false, false);
    }

    public static zzq zzd() {
        return new zzq("reward_mb", 0, 0, true, 0, 0, (zzq[]) null, false, false, false, false, false, false, false, false);
    }

    public static zzq zze() {
        return new zzq("invalid", 0, 0, false, 0, 0, (zzq[]) null, false, false, false, true, false, false, false, false);
    }

    private static int zzf(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        if (i <= 400) {
            return 32;
        }
        return i <= 720 ? 50 : 90;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, str, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzb);
        SafeParcelWriter.writeInt(parcel, 4, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzd);
        SafeParcelWriter.writeInt(parcel, 6, this.zze);
        SafeParcelWriter.writeInt(parcel, 7, this.zzf);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzg, i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzh);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzi);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzj);
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzk);
        SafeParcelWriter.writeBoolean(parcel, 13, this.zzl);
        SafeParcelWriter.writeBoolean(parcel, 14, this.zzm);
        SafeParcelWriter.writeBoolean(parcel, 15, this.zzn);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzo);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzq(Context context, AdSize adSize) {
        this(context, new AdSize[]{adSize});
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ff  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzq(android.content.Context r13, com.google.android.gms.ads.AdSize[] r14) {
        /*
            r12 = this;
            r12.<init>()
            r0 = 0
            r1 = r14[r0]
            r12.zzd = r0
            boolean r2 = r1.isFluid()
            r12.zzi = r2
            boolean r3 = com.google.android.gms.ads.zzb.zzf(r1)
            r12.zzm = r3
            boolean r3 = com.google.android.gms.ads.zzb.zzg(r1)
            r12.zzn = r3
            boolean r3 = com.google.android.gms.ads.zzb.zzh(r1)
            r12.zzo = r3
            if (r2 == 0) goto L_0x0033
            com.google.android.gms.ads.AdSize r3 = com.google.android.gms.ads.AdSize.BANNER
            int r3 = r3.getWidth()
            r12.zze = r3
            com.google.android.gms.ads.AdSize r3 = com.google.android.gms.ads.AdSize.BANNER
            int r3 = r3.getHeight()
            r12.zzb = r3
            goto L_0x005f
        L_0x0033:
            boolean r4 = r12.zzn
            if (r4 == 0) goto L_0x0044
            int r3 = r1.getWidth()
            r12.zze = r3
            int r3 = com.google.android.gms.ads.zzb.zza(r1)
            r12.zzb = r3
            goto L_0x005f
        L_0x0044:
            if (r3 == 0) goto L_0x0053
            int r3 = r1.getWidth()
            r12.zze = r3
            int r3 = com.google.android.gms.ads.zzb.zzb(r1)
            r12.zzb = r3
            goto L_0x005f
        L_0x0053:
            int r3 = r1.getWidth()
            r12.zze = r3
            int r3 = r1.getHeight()
            r12.zzb = r3
        L_0x005f:
            int r4 = r12.zze
            r5 = -1
            r6 = 1
            if (r4 != r5) goto L_0x0067
            r4 = r6
            goto L_0x0068
        L_0x0067:
            r4 = r0
        L_0x0068:
            r5 = -2
            if (r3 != r5) goto L_0x006d
            r3 = r6
            goto L_0x006e
        L_0x006d:
            r3 = r0
        L_0x006e:
            android.content.res.Resources r5 = r13.getResources()
            android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
            if (r4 == 0) goto L_0x0102
            com.google.android.gms.ads.internal.client.zzay.zzb()
            android.content.res.Resources r7 = r13.getResources()
            android.content.res.Configuration r7 = r7.getConfiguration()
            int r7 = r7.orientation
            r8 = 2
            if (r7 == r8) goto L_0x0089
            goto L_0x00ea
        L_0x0089:
            android.content.res.Resources r7 = r13.getResources()
            android.util.DisplayMetrics r7 = r7.getDisplayMetrics()
            int r8 = r7.heightPixels
            float r8 = (float) r8
            float r7 = r7.density
            float r8 = r8 / r7
            int r7 = (int) r8
            r8 = 600(0x258, float:8.41E-43)
            if (r7 >= r8) goto L_0x00ea
            com.google.android.gms.ads.internal.client.zzay.zzb()
            android.content.res.Resources r7 = r13.getResources()
            android.util.DisplayMetrics r7 = r7.getDisplayMetrics()
            java.lang.String r8 = "window"
            java.lang.Object r8 = r13.getSystemService(r8)
            android.view.WindowManager r8 = (android.view.WindowManager) r8
            if (r8 == 0) goto L_0x00ea
            android.view.Display r8 = r8.getDefaultDisplay()
            r8.getRealMetrics(r7)
            int r9 = r7.heightPixels
            int r10 = r7.widthPixels
            r8.getMetrics(r7)
            int r8 = r7.heightPixels
            int r7 = r7.widthPixels
            if (r8 != r9) goto L_0x00ea
            if (r7 != r10) goto L_0x00ea
            int r7 = r5.widthPixels
            com.google.android.gms.ads.internal.client.zzay.zzb()
            android.content.res.Resources r8 = r13.getResources()
            java.lang.String r9 = "dimen"
            java.lang.String r10 = "android"
            java.lang.String r11 = "navigation_bar_width"
            int r8 = r8.getIdentifier(r11, r9, r10)
            if (r8 <= 0) goto L_0x00e5
            android.content.res.Resources r9 = r13.getResources()
            int r8 = r9.getDimensionPixelSize(r8)
            goto L_0x00e6
        L_0x00e5:
            r8 = r0
        L_0x00e6:
            int r7 = r7 - r8
            r12.zzf = r7
            goto L_0x00ee
        L_0x00ea:
            int r7 = r5.widthPixels
            r12.zzf = r7
        L_0x00ee:
            float r8 = r5.density
            float r7 = (float) r7
            float r7 = r7 / r8
            double r7 = (double) r7
            int r9 = (int) r7
            double r10 = (double) r9
            double r7 = r7 - r10
            r10 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            int r7 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r7 < 0) goto L_0x010f
            int r9 = r9 + 1
            goto L_0x010f
        L_0x0102:
            int r9 = r12.zze
            com.google.android.gms.ads.internal.client.zzay.zzb()
            int r7 = r12.zze
            int r7 = com.google.android.gms.ads.internal.util.client.zzf.zzq(r5, r7)
            r12.zzf = r7
        L_0x010f:
            if (r3 == 0) goto L_0x0116
            int r7 = zzf(r5)
            goto L_0x0118
        L_0x0116:
            int r7 = r12.zzb
        L_0x0118:
            com.google.android.gms.ads.internal.client.zzay.zzb()
            int r5 = com.google.android.gms.ads.internal.util.client.zzf.zzq(r5, r7)
            r12.zzc = r5
            java.lang.String r5 = "_as"
            java.lang.String r8 = "x"
            if (r4 != 0) goto L_0x015b
            if (r3 == 0) goto L_0x012a
            goto L_0x015b
        L_0x012a:
            boolean r3 = r12.zzn
            if (r3 != 0) goto L_0x013f
            boolean r3 = r12.zzo
            if (r3 == 0) goto L_0x0133
            goto L_0x013f
        L_0x0133:
            if (r2 == 0) goto L_0x0138
            java.lang.String r1 = "320x50_mb"
            goto L_0x0158
        L_0x0138:
            java.lang.String r1 = r1.toString()
            r12.zza = r1
            goto L_0x0172
        L_0x013f:
            int r1 = r12.zze
            int r2 = r12.zzb
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            r3.append(r8)
            r3.append(r2)
            r3.append(r5)
            java.lang.String r1 = r3.toString()
        L_0x0158:
            r12.zza = r1
            goto L_0x0172
        L_0x015b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r9)
            r1.append(r8)
            r1.append(r7)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            r12.zza = r1
        L_0x0172:
            int r1 = r14.length
            if (r1 <= r6) goto L_0x018b
            com.google.android.gms.ads.internal.client.zzq[] r1 = new com.google.android.gms.ads.internal.client.zzq[r1]
            r12.zzg = r1
            r1 = r0
        L_0x017a:
            int r2 = r14.length
            if (r1 >= r2) goto L_0x018e
            com.google.android.gms.ads.internal.client.zzq[] r2 = r12.zzg
            com.google.android.gms.ads.internal.client.zzq r3 = new com.google.android.gms.ads.internal.client.zzq
            r4 = r14[r1]
            r3.<init>((android.content.Context) r13, (com.google.android.gms.ads.AdSize) r4)
            r2[r1] = r3
            int r1 = r1 + 1
            goto L_0x017a
        L_0x018b:
            r13 = 0
            r12.zzg = r13
        L_0x018e:
            r12.zzh = r0
            r12.zzj = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.client.zzq.<init>(android.content.Context, com.google.android.gms.ads.AdSize[]):void");
    }

    zzq(String str, int i, int i2, boolean z, int i3, int i4, zzq[] zzqArr, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9) {
        this.zza = str;
        this.zzb = i;
        this.zzc = i2;
        this.zzd = z;
        this.zze = i3;
        this.zzf = i4;
        this.zzg = zzqArr;
        this.zzh = z2;
        this.zzi = z3;
        this.zzj = z4;
        this.zzk = z5;
        this.zzl = z6;
        this.zzm = z7;
        this.zzn = z8;
        this.zzo = z9;
    }
}
