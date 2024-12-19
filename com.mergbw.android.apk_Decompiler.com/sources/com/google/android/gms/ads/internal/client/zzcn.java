package com.google.android.gms.ads.internal.client;

import com.google.android.gms.internal.ads.zzbae;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class zzcn extends zzbae implements zzco {
    public zzcn() {
        super("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [android.os.IInterface] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzdF(int r2, android.os.Parcel r3, android.os.Parcel r4, int r5) throws android.os.RemoteException {
        /*
            r1 = this;
            switch(r2) {
                case 1: goto L_0x0120;
                case 2: goto L_0x0112;
                case 3: goto L_0x0104;
                case 4: goto L_0x00f6;
                case 5: goto L_0x00e0;
                case 6: goto L_0x00ca;
                case 7: goto L_0x00bf;
                case 8: goto L_0x00b2;
                case 9: goto L_0x00a6;
                case 10: goto L_0x0097;
                case 11: goto L_0x0084;
                case 12: goto L_0x0071;
                case 13: goto L_0x0065;
                case 14: goto L_0x0052;
                case 15: goto L_0x004a;
                case 16: goto L_0x0023;
                case 17: goto L_0x0014;
                case 18: goto L_0x0005;
                default: goto L_0x0003;
            }
        L_0x0003:
            r2 = 0
            return r2
        L_0x0005:
            java.lang.String r2 = r3.readString()
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzt(r2)
            r4.writeNoException()
            goto L_0x0126
        L_0x0014:
            boolean r2 = com.google.android.gms.internal.ads.zzbaf.zzg(r3)
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzj(r2)
            r4.writeNoException()
            goto L_0x0126
        L_0x0023:
            android.os.IBinder r2 = r3.readStrongBinder()
            if (r2 != 0) goto L_0x002b
            r2 = 0
            goto L_0x003f
        L_0x002b:
            java.lang.String r5 = "com.google.android.gms.ads.internal.client.IOnAdInspectorClosedListener"
            android.os.IInterface r5 = r2.queryLocalInterface(r5)
            boolean r0 = r5 instanceof com.google.android.gms.ads.internal.client.zzda
            if (r0 == 0) goto L_0x0039
            r2 = r5
            com.google.android.gms.ads.internal.client.zzda r2 = (com.google.android.gms.ads.internal.client.zzda) r2
            goto L_0x003f
        L_0x0039:
            com.google.android.gms.ads.internal.client.zzcy r5 = new com.google.android.gms.ads.internal.client.zzcy
            r5.<init>(r2)
            r2 = r5
        L_0x003f:
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzm(r2)
            r4.writeNoException()
            goto L_0x0126
        L_0x004a:
            r1.zzi()
            r4.writeNoException()
            goto L_0x0126
        L_0x0052:
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.client.zzff> r2 = com.google.android.gms.ads.internal.client.zzff.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.ads.zzbaf.zza(r3, r2)
            com.google.android.gms.ads.internal.client.zzff r2 = (com.google.android.gms.ads.internal.client.zzff) r2
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzu(r2)
            r4.writeNoException()
            goto L_0x0126
        L_0x0065:
            java.util.List r2 = r1.zzg()
            r4.writeNoException()
            r4.writeTypedList(r2)
            goto L_0x0126
        L_0x0071:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.internal.ads.zzbnu r2 = com.google.android.gms.internal.ads.zzbnt.zzc(r2)
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzs(r2)
            r4.writeNoException()
            goto L_0x0126
        L_0x0084:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.internal.ads.zzbrf r2 = com.google.android.gms.internal.ads.zzbre.zzf(r2)
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzo(r2)
            r4.writeNoException()
            goto L_0x0126
        L_0x0097:
            java.lang.String r2 = r3.readString()
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzh(r2)
            r4.writeNoException()
            goto L_0x0126
        L_0x00a6:
            java.lang.String r2 = r1.zzf()
            r4.writeNoException()
            r4.writeString(r2)
            goto L_0x0126
        L_0x00b2:
            boolean r2 = r1.zzv()
            r4.writeNoException()
            int r3 = com.google.android.gms.internal.ads.zzbaf.zza
            r4.writeInt(r2)
            goto L_0x0126
        L_0x00bf:
            float r2 = r1.zze()
            r4.writeNoException()
            r4.writeFloat(r2)
            goto L_0x0126
        L_0x00ca:
            java.lang.String r2 = r3.readString()
            android.os.IBinder r5 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r5)
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzl(r2, r5)
            r4.writeNoException()
            goto L_0x0126
        L_0x00e0:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            java.lang.String r5 = r3.readString()
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzn(r2, r5)
            r4.writeNoException()
            goto L_0x0126
        L_0x00f6:
            boolean r2 = com.google.android.gms.internal.ads.zzbaf.zzg(r3)
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzp(r2)
            r4.writeNoException()
            goto L_0x0126
        L_0x0104:
            java.lang.String r2 = r3.readString()
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzr(r2)
            r4.writeNoException()
            goto L_0x0126
        L_0x0112:
            float r2 = r3.readFloat()
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzq(r2)
            r4.writeNoException()
            goto L_0x0126
        L_0x0120:
            r1.zzk()
            r4.writeNoException()
        L_0x0126:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.client.zzcn.zzdF(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
