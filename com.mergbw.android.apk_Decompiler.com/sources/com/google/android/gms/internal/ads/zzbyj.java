package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class zzbyj extends zzbae implements zzbyk {
    public zzbyj() {
        super("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: com.google.android.gms.internal.ads.zzbyi} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.gms.internal.ads.zzbyn] */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzdF(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) throws android.os.RemoteException {
        /*
            r2 = this;
            r6 = 1
            if (r3 == r6) goto L_0x014d
            r0 = 2
            if (r3 == r0) goto L_0x0146
            r0 = 3
            r1 = 0
            if (r3 == r0) goto L_0x0122
            r0 = 34
            if (r3 == r0) goto L_0x0114
            switch(r3) {
                case 5: goto L_0x0107;
                case 6: goto L_0x0100;
                case 7: goto L_0x00f9;
                case 8: goto L_0x00f2;
                case 9: goto L_0x00df;
                case 10: goto L_0x00cc;
                case 11: goto L_0x00b9;
                case 12: goto L_0x00ad;
                case 13: goto L_0x009e;
                case 14: goto L_0x008b;
                case 15: goto L_0x007f;
                case 16: goto L_0x005a;
                case 17: goto L_0x004f;
                case 18: goto L_0x003c;
                case 19: goto L_0x002d;
                case 20: goto L_0x001f;
                case 21: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            r3 = 0
            return r3
        L_0x0013:
            com.google.android.gms.ads.internal.client.zzdn r3 = r2.zzc()
            r5.writeNoException()
            com.google.android.gms.internal.ads.zzbaf.zzf(r5, r3)
            goto L_0x015e
        L_0x001f:
            boolean r3 = r2.zzt()
            r5.writeNoException()
            int r4 = com.google.android.gms.internal.ads.zzbaf.zza
            r5.writeInt(r3)
            goto L_0x015e
        L_0x002d:
            java.lang.String r3 = r4.readString()
            com.google.android.gms.internal.ads.zzbaf.zzc(r4)
            r2.zzm(r3)
            r5.writeNoException()
            goto L_0x015e
        L_0x003c:
            android.os.IBinder r3 = r4.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            com.google.android.gms.internal.ads.zzbaf.zzc(r4)
            r2.zzr(r3)
            r5.writeNoException()
            goto L_0x015e
        L_0x004f:
            r4.readString()
            com.google.android.gms.internal.ads.zzbaf.zzc(r4)
            r5.writeNoException()
            goto L_0x015e
        L_0x005a:
            android.os.IBinder r3 = r4.readStrongBinder()
            if (r3 != 0) goto L_0x0061
            goto L_0x0074
        L_0x0061:
            java.lang.String r0 = "com.google.android.gms.ads.internal.reward.client.IRewardedAdSkuListener"
            android.os.IInterface r0 = r3.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.ads.zzbyi
            if (r1 == 0) goto L_0x006f
            r1 = r0
            com.google.android.gms.internal.ads.zzbyi r1 = (com.google.android.gms.internal.ads.zzbyi) r1
            goto L_0x0074
        L_0x006f:
            com.google.android.gms.internal.ads.zzbyi r1 = new com.google.android.gms.internal.ads.zzbyi
            r1.<init>(r3)
        L_0x0074:
            com.google.android.gms.internal.ads.zzbaf.zzc(r4)
            r2.zzu(r1)
            r5.writeNoException()
            goto L_0x015e
        L_0x007f:
            android.os.Bundle r3 = r2.zzb()
            r5.writeNoException()
            com.google.android.gms.internal.ads.zzbaf.zze(r5, r3)
            goto L_0x015e
        L_0x008b:
            android.os.IBinder r3 = r4.readStrongBinder()
            com.google.android.gms.ads.internal.client.zzby r3 = com.google.android.gms.ads.internal.client.zzbx.zzb(r3)
            com.google.android.gms.internal.ads.zzbaf.zzc(r4)
            r2.zzl(r3)
            r5.writeNoException()
            goto L_0x015e
        L_0x009e:
            java.lang.String r3 = r4.readString()
            com.google.android.gms.internal.ads.zzbaf.zzc(r4)
            r2.zzp(r3)
            r5.writeNoException()
            goto L_0x015e
        L_0x00ad:
            java.lang.String r3 = r2.zzd()
            r5.writeNoException()
            r5.writeString(r3)
            goto L_0x015e
        L_0x00b9:
            android.os.IBinder r3 = r4.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            com.google.android.gms.internal.ads.zzbaf.zzc(r4)
            r2.zzf(r3)
            r5.writeNoException()
            goto L_0x015e
        L_0x00cc:
            android.os.IBinder r3 = r4.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            com.google.android.gms.internal.ads.zzbaf.zzc(r4)
            r2.zzk(r3)
            r5.writeNoException()
            goto L_0x015e
        L_0x00df:
            android.os.IBinder r3 = r4.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            com.google.android.gms.internal.ads.zzbaf.zzc(r4)
            r2.zzi(r3)
            r5.writeNoException()
            goto L_0x015e
        L_0x00f2:
            r2.zze()
            r5.writeNoException()
            goto L_0x015e
        L_0x00f9:
            r2.zzj()
            r5.writeNoException()
            goto L_0x015e
        L_0x0100:
            r2.zzh()
            r5.writeNoException()
            goto L_0x015e
        L_0x0107:
            boolean r3 = r2.zzs()
            r5.writeNoException()
            int r4 = com.google.android.gms.internal.ads.zzbaf.zza
            r5.writeInt(r3)
            goto L_0x015e
        L_0x0114:
            boolean r3 = com.google.android.gms.internal.ads.zzbaf.zzg(r4)
            com.google.android.gms.internal.ads.zzbaf.zzc(r4)
            r2.zzn(r3)
            r5.writeNoException()
            goto L_0x015e
        L_0x0122:
            android.os.IBinder r3 = r4.readStrongBinder()
            if (r3 != 0) goto L_0x0129
            goto L_0x013c
        L_0x0129:
            java.lang.String r0 = "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener"
            android.os.IInterface r0 = r3.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.ads.zzbyn
            if (r1 == 0) goto L_0x0137
            r1 = r0
            com.google.android.gms.internal.ads.zzbyn r1 = (com.google.android.gms.internal.ads.zzbyn) r1
            goto L_0x013c
        L_0x0137:
            com.google.android.gms.internal.ads.zzbyl r1 = new com.google.android.gms.internal.ads.zzbyl
            r1.<init>(r3)
        L_0x013c:
            com.google.android.gms.internal.ads.zzbaf.zzc(r4)
            r2.zzo(r1)
            r5.writeNoException()
            goto L_0x015e
        L_0x0146:
            r2.zzq()
            r5.writeNoException()
            goto L_0x015e
        L_0x014d:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzbyo> r3 = com.google.android.gms.internal.ads.zzbyo.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.ads.zzbaf.zza(r4, r3)
            com.google.android.gms.internal.ads.zzbyo r3 = (com.google.android.gms.internal.ads.zzbyo) r3
            com.google.android.gms.internal.ads.zzbaf.zzc(r4)
            r2.zzg(r3)
            r5.writeNoException()
        L_0x015e:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbyj.zzdF(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
