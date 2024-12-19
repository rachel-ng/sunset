package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.internal.client.zzfk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzbhk extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbhk> CREATOR = new zzbhl();
    public final int zza;
    public final boolean zzb;
    public final int zzc;
    public final boolean zzd;
    public final int zze;
    public final zzfk zzf;
    public final boolean zzg;
    public final int zzh;
    public final int zzi;
    public final boolean zzj;
    public final int zzk;

    public zzbhk(int i, boolean z, int i2, boolean z2, int i3, zzfk zzfk, boolean z3, int i4, int i5, boolean z4, int i6) {
        this.zza = i;
        this.zzb = z;
        this.zzc = i2;
        this.zzd = z2;
        this.zze = i3;
        this.zzf = zzfk;
        this.zzg = z3;
        this.zzh = i4;
        this.zzj = z4;
        this.zzi = i5;
        this.zzk = i6;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        if (r1 == 1) goto L_0x0036;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.ads.nativead.NativeAdOptions zza(com.google.android.gms.internal.ads.zzbhk r5) {
        /*
            com.google.android.gms.ads.nativead.NativeAdOptions$Builder r0 = new com.google.android.gms.ads.nativead.NativeAdOptions$Builder
            r0.<init>()
            if (r5 != 0) goto L_0x000c
            com.google.android.gms.ads.nativead.NativeAdOptions r5 = r0.build()
            return r5
        L_0x000c:
            int r1 = r5.zza
            r2 = 2
            if (r1 == r2) goto L_0x0045
            r3 = 3
            if (r1 == r3) goto L_0x0039
            r4 = 4
            if (r1 == r4) goto L_0x0018
            goto L_0x004a
        L_0x0018:
            boolean r1 = r5.zzg
            r0.setRequestCustomMuteThisAd(r1)
            int r1 = r5.zzh
            r0.setMediaAspectRatio(r1)
            int r1 = r5.zzi
            boolean r4 = r5.zzj
            r0.enableCustomClickGestureDirection(r1, r4)
            int r1 = r5.zzk
            r4 = 1
            if (r1 != 0) goto L_0x0030
        L_0x002e:
            r2 = r4
            goto L_0x0036
        L_0x0030:
            if (r1 != r2) goto L_0x0034
            r2 = r3
            goto L_0x0036
        L_0x0034:
            if (r1 != r4) goto L_0x002e
        L_0x0036:
            r0.zzi(r2)
        L_0x0039:
            com.google.android.gms.ads.internal.client.zzfk r1 = r5.zzf
            if (r1 == 0) goto L_0x0045
            com.google.android.gms.ads.VideoOptions r2 = new com.google.android.gms.ads.VideoOptions
            r2.<init>(r1)
            r0.setVideoOptions(r2)
        L_0x0045:
            int r1 = r5.zze
            r0.setAdChoicesPlacement(r1)
        L_0x004a:
            boolean r1 = r5.zzb
            r0.setReturnUrlsForImageAssets(r1)
            boolean r5 = r5.zzd
            r0.setRequestMultipleImages(r5)
            com.google.android.gms.ads.nativead.NativeAdOptions r5 = r0.build()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbhk.zza(com.google.android.gms.internal.ads.zzbhk):com.google.android.gms.ads.nativead.NativeAdOptions");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i2);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzb);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzd);
        SafeParcelWriter.writeInt(parcel, 5, this.zze);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzf, i, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzg);
        SafeParcelWriter.writeInt(parcel, 8, this.zzh);
        SafeParcelWriter.writeInt(parcel, 9, this.zzi);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzj);
        SafeParcelWriter.writeInt(parcel, 11, this.zzk);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public zzbhk(NativeAdOptions nativeAdOptions) {
        this(4, nativeAdOptions.shouldReturnUrlsForImageAssets(), nativeAdOptions.getImageOrientation(), nativeAdOptions.shouldRequestMultipleImages(), nativeAdOptions.getAdChoicesPlacement(), nativeAdOptions.getVideoOptions() != null ? new zzfk(nativeAdOptions.getVideoOptions()) : null, nativeAdOptions.zza(), nativeAdOptions.getMediaAspectRatio(), 0, false, 0);
    }
}
