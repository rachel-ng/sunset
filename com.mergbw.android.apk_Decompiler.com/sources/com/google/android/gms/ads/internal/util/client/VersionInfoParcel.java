package com.google.android.gms.ads.internal.util.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class VersionInfoParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<VersionInfoParcel> CREATOR = new zzs();
    public String afmaVersion;
    public int buddyApkVersion;
    public int clientJarVersion;
    public boolean isClientJar;
    public boolean isLiteSdk;

    public VersionInfoParcel(int i, int i2, boolean z) {
        this(i, i2, z, false, false);
    }

    public static VersionInfoParcel forPackage() {
        return new VersionInfoParcel(GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE, true);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.afmaVersion, false);
        SafeParcelWriter.writeInt(parcel, 3, this.buddyApkVersion);
        SafeParcelWriter.writeInt(parcel, 4, this.clientJarVersion);
        SafeParcelWriter.writeBoolean(parcel, 5, this.isClientJar);
        SafeParcelWriter.writeBoolean(parcel, 6, this.isLiteSdk);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public VersionInfoParcel(int i, int i2, boolean z, boolean z2) {
        this(i, i2, z, false, z2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public VersionInfoParcel(int r9, int r10, boolean r11, boolean r12, boolean r13) {
        /*
            r8 = this;
            if (r11 == 0) goto L_0x0005
            java.lang.String r12 = "0"
            goto L_0x000c
        L_0x0005:
            if (r12 == 0) goto L_0x000a
            java.lang.String r12 = "2"
            goto L_0x000c
        L_0x000a:
            java.lang.String r12 = "1"
        L_0x000c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "afma-sdk-a-v"
            r0.<init>(r1)
            r0.append(r9)
            java.lang.String r1 = "."
            r0.append(r1)
            r0.append(r10)
            r0.append(r1)
            r0.append(r12)
            java.lang.String r3 = r0.toString()
            r2 = r8
            r4 = r9
            r5 = r10
            r6 = r11
            r7 = r13
            r2.<init>((java.lang.String) r3, (int) r4, (int) r5, (boolean) r6, (boolean) r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.util.client.VersionInfoParcel.<init>(int, int, boolean, boolean, boolean):void");
    }

    VersionInfoParcel(String str, int i, int i2, boolean z, boolean z2) {
        this.afmaVersion = str;
        this.buddyApkVersion = i;
        this.clientJarVersion = i2;
        this.isClientJar = z;
        this.isLiteSdk = z2;
    }
}
