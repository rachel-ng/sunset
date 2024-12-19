package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class zzbjr extends zzbae implements zzbjs {
    public zzbjr() {
        super("com.google.android.gms.ads.internal.formats.client.IUnifiedNativeAd");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [android.os.IInterface] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzdF(int r2, android.os.Parcel r3, android.os.Parcel r4, int r5) throws android.os.RemoteException {
        /*
            r1 = this;
            switch(r2) {
                case 2: goto L_0x01a1;
                case 3: goto L_0x0196;
                case 4: goto L_0x018b;
                case 5: goto L_0x0180;
                case 6: goto L_0x0175;
                case 7: goto L_0x016a;
                case 8: goto L_0x015f;
                case 9: goto L_0x0154;
                case 10: goto L_0x0149;
                case 11: goto L_0x013e;
                case 12: goto L_0x0132;
                case 13: goto L_0x012a;
                case 14: goto L_0x011e;
                case 15: goto L_0x010b;
                case 16: goto L_0x00f4;
                case 17: goto L_0x00e1;
                case 18: goto L_0x00d5;
                case 19: goto L_0x00c9;
                case 20: goto L_0x00bd;
                case 21: goto L_0x0096;
                case 22: goto L_0x008e;
                case 23: goto L_0x0082;
                case 24: goto L_0x0074;
                case 25: goto L_0x0061;
                case 26: goto L_0x004e;
                case 27: goto L_0x0046;
                case 28: goto L_0x003e;
                case 29: goto L_0x0032;
                case 30: goto L_0x0024;
                case 31: goto L_0x0018;
                case 32: goto L_0x0005;
                default: goto L_0x0003;
            }
        L_0x0003:
            r2 = 0
            return r2
        L_0x0005:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.ads.internal.client.zzdg r2 = com.google.android.gms.ads.internal.client.zzdf.zzb(r2)
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzE(r2)
            r4.writeNoException()
            goto L_0x01ab
        L_0x0018:
            com.google.android.gms.ads.internal.client.zzdn r2 = r1.zzg()
            r4.writeNoException()
            com.google.android.gms.internal.ads.zzbaf.zzf(r4, r2)
            goto L_0x01ab
        L_0x0024:
            boolean r2 = r1.zzG()
            r4.writeNoException()
            int r3 = com.google.android.gms.internal.ads.zzbaf.zza
            r4.writeInt(r2)
            goto L_0x01ab
        L_0x0032:
            com.google.android.gms.internal.ads.zzbhs r2 = r1.zzj()
            r4.writeNoException()
            com.google.android.gms.internal.ads.zzbaf.zzf(r4, r2)
            goto L_0x01ab
        L_0x003e:
            r1.zzA()
            r4.writeNoException()
            goto L_0x01ab
        L_0x0046:
            r1.zzC()
            r4.writeNoException()
            goto L_0x01ab
        L_0x004e:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.ads.internal.client.zzcs r2 = com.google.android.gms.ads.internal.client.zzcr.zzb(r2)
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzD(r2)
            r4.writeNoException()
            goto L_0x01ab
        L_0x0061:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.ads.internal.client.zzcw r2 = com.google.android.gms.ads.internal.client.zzcv.zzb(r2)
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzy(r2)
            r4.writeNoException()
            goto L_0x01ab
        L_0x0074:
            boolean r2 = r1.zzH()
            r4.writeNoException()
            int r3 = com.google.android.gms.internal.ads.zzbaf.zza
            r4.writeInt(r2)
            goto L_0x01ab
        L_0x0082:
            java.util.List r2 = r1.zzv()
            r4.writeNoException()
            r4.writeList(r2)
            goto L_0x01ab
        L_0x008e:
            r1.zzw()
            r4.writeNoException()
            goto L_0x01ab
        L_0x0096:
            android.os.IBinder r2 = r3.readStrongBinder()
            if (r2 != 0) goto L_0x009e
            r2 = 0
            goto L_0x00b2
        L_0x009e:
            java.lang.String r5 = "com.google.android.gms.ads.internal.formats.client.IUnconfirmedClickListener"
            android.os.IInterface r5 = r2.queryLocalInterface(r5)
            boolean r0 = r5 instanceof com.google.android.gms.internal.ads.zzbjp
            if (r0 == 0) goto L_0x00ac
            r2 = r5
            com.google.android.gms.internal.ads.zzbjp r2 = (com.google.android.gms.internal.ads.zzbjp) r2
            goto L_0x00b2
        L_0x00ac:
            com.google.android.gms.internal.ads.zzbjn r5 = new com.google.android.gms.internal.ads.zzbjn
            r5.<init>(r2)
            r2 = r5
        L_0x00b2:
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzF(r2)
            r4.writeNoException()
            goto L_0x01ab
        L_0x00bd:
            android.os.Bundle r2 = r1.zzf()
            r4.writeNoException()
            com.google.android.gms.internal.ads.zzbaf.zze(r4, r2)
            goto L_0x01ab
        L_0x00c9:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzl()
            r4.writeNoException()
            com.google.android.gms.internal.ads.zzbaf.zzf(r4, r2)
            goto L_0x01ab
        L_0x00d5:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzm()
            r4.writeNoException()
            com.google.android.gms.internal.ads.zzbaf.zzf(r4, r2)
            goto L_0x01ab
        L_0x00e1:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.ads.zzbaf.zza(r3, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzB(r2)
            r4.writeNoException()
            goto L_0x01ab
        L_0x00f4:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.ads.zzbaf.zza(r3, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            boolean r2 = r1.zzI(r2)
            r4.writeNoException()
            r4.writeInt(r2)
            goto L_0x01ab
        L_0x010b:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.ads.zzbaf.zza(r3, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            com.google.android.gms.internal.ads.zzbaf.zzc(r3)
            r1.zzz(r2)
            r4.writeNoException()
            goto L_0x01ab
        L_0x011e:
            com.google.android.gms.internal.ads.zzbho r2 = r1.zzi()
            r4.writeNoException()
            com.google.android.gms.internal.ads.zzbaf.zzf(r4, r2)
            goto L_0x01ab
        L_0x012a:
            r1.zzx()
            r4.writeNoException()
            goto L_0x01ab
        L_0x0132:
            java.lang.String r2 = r1.zzr()
            r4.writeNoException()
            r4.writeString(r2)
            goto L_0x01ab
        L_0x013e:
            com.google.android.gms.ads.internal.client.zzdq r2 = r1.zzh()
            r4.writeNoException()
            com.google.android.gms.internal.ads.zzbaf.zzf(r4, r2)
            goto L_0x01ab
        L_0x0149:
            java.lang.String r2 = r1.zzs()
            r4.writeNoException()
            r4.writeString(r2)
            goto L_0x01ab
        L_0x0154:
            java.lang.String r2 = r1.zzt()
            r4.writeNoException()
            r4.writeString(r2)
            goto L_0x01ab
        L_0x015f:
            double r2 = r1.zze()
            r4.writeNoException()
            r4.writeDouble(r2)
            goto L_0x01ab
        L_0x016a:
            java.lang.String r2 = r1.zzn()
            r4.writeNoException()
            r4.writeString(r2)
            goto L_0x01ab
        L_0x0175:
            java.lang.String r2 = r1.zzp()
            r4.writeNoException()
            r4.writeString(r2)
            goto L_0x01ab
        L_0x0180:
            com.google.android.gms.internal.ads.zzbhv r2 = r1.zzk()
            r4.writeNoException()
            com.google.android.gms.internal.ads.zzbaf.zzf(r4, r2)
            goto L_0x01ab
        L_0x018b:
            java.lang.String r2 = r1.zzo()
            r4.writeNoException()
            r4.writeString(r2)
            goto L_0x01ab
        L_0x0196:
            java.util.List r2 = r1.zzu()
            r4.writeNoException()
            r4.writeList(r2)
            goto L_0x01ab
        L_0x01a1:
            java.lang.String r2 = r1.zzq()
            r4.writeNoException()
            r4.writeString(r2)
        L_0x01ab:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbjr.zzdF(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
