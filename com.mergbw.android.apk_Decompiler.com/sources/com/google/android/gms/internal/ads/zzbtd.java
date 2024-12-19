package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class zzbtd extends zzbae implements zzbte {
    public zzbtd() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    public static zzbte zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
        return queryLocalInterface instanceof zzbte ? (zzbte) queryLocalInterface : new zzbtc(iBinder);
    }

    /* JADX WARNING: type inference failed for: r2v4, types: [com.google.android.gms.internal.ads.zzbth] */
    /* JADX WARNING: type inference failed for: r2v8, types: [com.google.android.gms.internal.ads.zzbsq] */
    /* JADX WARNING: type inference failed for: r2v9, types: [com.google.android.gms.internal.ads.zzbss] */
    /* JADX WARNING: type inference failed for: r5v5, types: [com.google.android.gms.internal.ads.zzbss] */
    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbst] */
    /* JADX WARNING: type inference failed for: r2v15, types: [com.google.android.gms.internal.ads.zzbsv] */
    /* JADX WARNING: type inference failed for: r5v8, types: [com.google.android.gms.internal.ads.zzbsv] */
    /* JADX WARNING: type inference failed for: r2v19, types: [com.google.android.gms.internal.ads.zzbsz] */
    /* JADX WARNING: type inference failed for: r2v20, types: [com.google.android.gms.internal.ads.zzbtb] */
    /* JADX WARNING: type inference failed for: r5v10, types: [com.google.android.gms.internal.ads.zzbtb] */
    /* JADX WARNING: type inference failed for: r2v24, types: [com.google.android.gms.internal.ads.zzbsw] */
    /* JADX WARNING: type inference failed for: r2v25, types: [com.google.android.gms.internal.ads.zzbsy] */
    /* JADX WARNING: type inference failed for: r5v12, types: [com.google.android.gms.internal.ads.zzbsy] */
    /* JADX WARNING: type inference failed for: r2v29, types: [com.google.android.gms.internal.ads.zzbsz] */
    /* JADX WARNING: type inference failed for: r2v30, types: [com.google.android.gms.internal.ads.zzbtb] */
    /* JADX WARNING: type inference failed for: r5v14, types: [com.google.android.gms.internal.ads.zzbtb] */
    /* JADX WARNING: type inference failed for: r2v34, types: [com.google.android.gms.internal.ads.zzbsq] */
    /* JADX WARNING: type inference failed for: r2v35, types: [com.google.android.gms.internal.ads.zzbss] */
    /* JADX WARNING: type inference failed for: r5v16, types: [com.google.android.gms.internal.ads.zzbss] */
    /* JADX WARNING: type inference failed for: r2v39, types: [com.google.android.gms.internal.ads.zzbsw] */
    /* JADX WARNING: type inference failed for: r2v40, types: [com.google.android.gms.internal.ads.zzbsy] */
    /* JADX WARNING: type inference failed for: r5v18, types: [com.google.android.gms.internal.ads.zzbsy] */
    /* JADX WARNING: type inference failed for: r2v45, types: [com.google.android.gms.internal.ads.zzbsn] */
    /* JADX WARNING: type inference failed for: r2v46, types: [com.google.android.gms.internal.ads.zzbsp] */
    /* JADX WARNING: type inference failed for: r5v21, types: [com.google.android.gms.internal.ads.zzbsp] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzdF(int r15, android.os.Parcel r16, android.os.Parcel r17, int r18) throws android.os.RemoteException {
        /*
            r14 = this;
            r8 = r14
            r0 = r15
            r1 = r16
            r9 = r17
            r10 = 1
            r2 = 0
            if (r0 == r10) goto L_0x033e
            r3 = 2
            if (r0 == r3) goto L_0x0333
            r3 = 3
            if (r0 == r3) goto L_0x0328
            r3 = 5
            if (r0 == r3) goto L_0x031c
            r3 = 10
            if (r0 == r3) goto L_0x030d
            r3 = 11
            if (r0 == r3) goto L_0x02fa
            java.lang.String r3 = "com.google.android.gms.ads.internal.mediation.client.rtb.INativeCallback"
            java.lang.String r4 = "com.google.android.gms.ads.internal.mediation.client.rtb.IRewardedCallback"
            java.lang.String r5 = "com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback"
            switch(r0) {
                case 13: goto L_0x02a5;
                case 14: goto L_0x0257;
                case 15: goto L_0x0240;
                case 16: goto L_0x01f4;
                case 17: goto L_0x01dd;
                case 18: goto L_0x0191;
                case 19: goto L_0x0182;
                case 20: goto L_0x0136;
                case 21: goto L_0x00e1;
                case 22: goto L_0x008b;
                case 23: goto L_0x003d;
                case 24: goto L_0x0026;
                default: goto L_0x0024;
            }
        L_0x0024:
            r0 = 0
            return r0
        L_0x0026:
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            boolean r0 = r14.zzr(r0)
            r17.writeNoException()
            r9.writeInt(r0)
            goto L_0x0390
        L_0x003d:
            java.lang.String r3 = r16.readString()
            java.lang.String r4 = r16.readString()
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.client.zzl> r0 = com.google.android.gms.ads.internal.client.zzl.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzbaf.zza(r1, r0)
            r5 = r0
            com.google.android.gms.ads.internal.client.zzl r5 = (com.google.android.gms.ads.internal.client.zzl) r5
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r6 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r0 = r16.readStrongBinder()
            if (r0 != 0) goto L_0x005e
        L_0x005c:
            r7 = r2
            goto L_0x0071
        L_0x005e:
            java.lang.String r2 = "com.google.android.gms.ads.internal.mediation.client.rtb.IAppOpenCallback"
            android.os.IInterface r2 = r0.queryLocalInterface(r2)
            boolean r7 = r2 instanceof com.google.android.gms.internal.ads.zzbsp
            if (r7 == 0) goto L_0x006b
            com.google.android.gms.internal.ads.zzbsp r2 = (com.google.android.gms.internal.ads.zzbsp) r2
            goto L_0x005c
        L_0x006b:
            com.google.android.gms.internal.ads.zzbsn r2 = new com.google.android.gms.internal.ads.zzbsn
            r2.<init>(r0)
            goto L_0x005c
        L_0x0071:
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.internal.ads.zzbrl r11 = com.google.android.gms.internal.ads.zzbrk.zzb(r0)
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            r0 = r14
            r1 = r3
            r2 = r4
            r3 = r5
            r4 = r6
            r5 = r7
            r6 = r11
            r0.zzi(r1, r2, r3, r4, r5, r6)
            r17.writeNoException()
            goto L_0x0390
        L_0x008b:
            java.lang.String r4 = r16.readString()
            java.lang.String r5 = r16.readString()
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.client.zzl> r0 = com.google.android.gms.ads.internal.client.zzl.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzbaf.zza(r1, r0)
            r6 = r0
            com.google.android.gms.ads.internal.client.zzl r6 = (com.google.android.gms.ads.internal.client.zzl) r6
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r7 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r0 = r16.readStrongBinder()
            if (r0 != 0) goto L_0x00ac
        L_0x00aa:
            r11 = r2
            goto L_0x00bd
        L_0x00ac:
            android.os.IInterface r2 = r0.queryLocalInterface(r3)
            boolean r3 = r2 instanceof com.google.android.gms.internal.ads.zzbsy
            if (r3 == 0) goto L_0x00b7
            com.google.android.gms.internal.ads.zzbsy r2 = (com.google.android.gms.internal.ads.zzbsy) r2
            goto L_0x00aa
        L_0x00b7:
            com.google.android.gms.internal.ads.zzbsw r2 = new com.google.android.gms.internal.ads.zzbsw
            r2.<init>(r0)
            goto L_0x00aa
        L_0x00bd:
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.internal.ads.zzbrl r12 = com.google.android.gms.internal.ads.zzbrk.zzb(r0)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzbhk> r0 = com.google.android.gms.internal.ads.zzbhk.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzbaf.zza(r1, r0)
            r13 = r0
            com.google.android.gms.internal.ads.zzbhk r13 = (com.google.android.gms.internal.ads.zzbhk) r13
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            r0 = r14
            r1 = r4
            r2 = r5
            r3 = r6
            r4 = r7
            r5 = r11
            r6 = r12
            r7 = r13
            r0.zzn(r1, r2, r3, r4, r5, r6, r7)
            r17.writeNoException()
            goto L_0x0390
        L_0x00e1:
            java.lang.String r3 = r16.readString()
            java.lang.String r4 = r16.readString()
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.client.zzl> r0 = com.google.android.gms.ads.internal.client.zzl.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzbaf.zza(r1, r0)
            r6 = r0
            com.google.android.gms.ads.internal.client.zzl r6 = (com.google.android.gms.ads.internal.client.zzl) r6
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r7 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r0 = r16.readStrongBinder()
            if (r0 != 0) goto L_0x0102
        L_0x0100:
            r5 = r2
            goto L_0x0113
        L_0x0102:
            android.os.IInterface r2 = r0.queryLocalInterface(r5)
            boolean r5 = r2 instanceof com.google.android.gms.internal.ads.zzbss
            if (r5 == 0) goto L_0x010d
            com.google.android.gms.internal.ads.zzbss r2 = (com.google.android.gms.internal.ads.zzbss) r2
            goto L_0x0100
        L_0x010d:
            com.google.android.gms.internal.ads.zzbsq r2 = new com.google.android.gms.internal.ads.zzbsq
            r2.<init>(r0)
            goto L_0x0100
        L_0x0113:
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.internal.ads.zzbrl r11 = com.google.android.gms.internal.ads.zzbrk.zzb(r0)
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.client.zzq> r0 = com.google.android.gms.ads.internal.client.zzq.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzbaf.zza(r1, r0)
            r12 = r0
            com.google.android.gms.ads.internal.client.zzq r12 = (com.google.android.gms.ads.internal.client.zzq) r12
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            r0 = r14
            r1 = r3
            r2 = r4
            r3 = r6
            r4 = r7
            r6 = r11
            r7 = r12
            r0.zzk(r1, r2, r3, r4, r5, r6, r7)
            r17.writeNoException()
            goto L_0x0390
        L_0x0136:
            java.lang.String r3 = r16.readString()
            java.lang.String r5 = r16.readString()
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.client.zzl> r0 = com.google.android.gms.ads.internal.client.zzl.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzbaf.zza(r1, r0)
            r6 = r0
            com.google.android.gms.ads.internal.client.zzl r6 = (com.google.android.gms.ads.internal.client.zzl) r6
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r7 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r0 = r16.readStrongBinder()
            if (r0 != 0) goto L_0x0157
        L_0x0155:
            r11 = r2
            goto L_0x0168
        L_0x0157:
            android.os.IInterface r2 = r0.queryLocalInterface(r4)
            boolean r4 = r2 instanceof com.google.android.gms.internal.ads.zzbtb
            if (r4 == 0) goto L_0x0162
            com.google.android.gms.internal.ads.zzbtb r2 = (com.google.android.gms.internal.ads.zzbtb) r2
            goto L_0x0155
        L_0x0162:
            com.google.android.gms.internal.ads.zzbsz r2 = new com.google.android.gms.internal.ads.zzbsz
            r2.<init>(r0)
            goto L_0x0155
        L_0x0168:
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.internal.ads.zzbrl r12 = com.google.android.gms.internal.ads.zzbrk.zzb(r0)
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            r0 = r14
            r1 = r3
            r2 = r5
            r3 = r6
            r4 = r7
            r5 = r11
            r6 = r12
            r0.zzo(r1, r2, r3, r4, r5, r6)
            r17.writeNoException()
            goto L_0x0390
        L_0x0182:
            java.lang.String r0 = r16.readString()
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            r14.zzq(r0)
            r17.writeNoException()
            goto L_0x0390
        L_0x0191:
            java.lang.String r4 = r16.readString()
            java.lang.String r5 = r16.readString()
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.client.zzl> r0 = com.google.android.gms.ads.internal.client.zzl.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzbaf.zza(r1, r0)
            r6 = r0
            com.google.android.gms.ads.internal.client.zzl r6 = (com.google.android.gms.ads.internal.client.zzl) r6
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r7 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r0 = r16.readStrongBinder()
            if (r0 != 0) goto L_0x01b2
        L_0x01b0:
            r11 = r2
            goto L_0x01c3
        L_0x01b2:
            android.os.IInterface r2 = r0.queryLocalInterface(r3)
            boolean r3 = r2 instanceof com.google.android.gms.internal.ads.zzbsy
            if (r3 == 0) goto L_0x01bd
            com.google.android.gms.internal.ads.zzbsy r2 = (com.google.android.gms.internal.ads.zzbsy) r2
            goto L_0x01b0
        L_0x01bd:
            com.google.android.gms.internal.ads.zzbsw r2 = new com.google.android.gms.internal.ads.zzbsw
            r2.<init>(r0)
            goto L_0x01b0
        L_0x01c3:
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.internal.ads.zzbrl r12 = com.google.android.gms.internal.ads.zzbrk.zzb(r0)
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            r0 = r14
            r1 = r4
            r2 = r5
            r3 = r6
            r4 = r7
            r5 = r11
            r6 = r12
            r0.zzm(r1, r2, r3, r4, r5, r6)
            r17.writeNoException()
            goto L_0x0390
        L_0x01dd:
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            boolean r0 = r14.zzt(r0)
            r17.writeNoException()
            r9.writeInt(r0)
            goto L_0x0390
        L_0x01f4:
            java.lang.String r3 = r16.readString()
            java.lang.String r5 = r16.readString()
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.client.zzl> r0 = com.google.android.gms.ads.internal.client.zzl.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzbaf.zza(r1, r0)
            r6 = r0
            com.google.android.gms.ads.internal.client.zzl r6 = (com.google.android.gms.ads.internal.client.zzl) r6
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r7 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r0 = r16.readStrongBinder()
            if (r0 != 0) goto L_0x0215
        L_0x0213:
            r11 = r2
            goto L_0x0226
        L_0x0215:
            android.os.IInterface r2 = r0.queryLocalInterface(r4)
            boolean r4 = r2 instanceof com.google.android.gms.internal.ads.zzbtb
            if (r4 == 0) goto L_0x0220
            com.google.android.gms.internal.ads.zzbtb r2 = (com.google.android.gms.internal.ads.zzbtb) r2
            goto L_0x0213
        L_0x0220:
            com.google.android.gms.internal.ads.zzbsz r2 = new com.google.android.gms.internal.ads.zzbsz
            r2.<init>(r0)
            goto L_0x0213
        L_0x0226:
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.internal.ads.zzbrl r12 = com.google.android.gms.internal.ads.zzbrk.zzb(r0)
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            r0 = r14
            r1 = r3
            r2 = r5
            r3 = r6
            r4 = r7
            r5 = r11
            r6 = r12
            r0.zzp(r1, r2, r3, r4, r5, r6)
            r17.writeNoException()
            goto L_0x0390
        L_0x0240:
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            boolean r0 = r14.zzs(r0)
            r17.writeNoException()
            r9.writeInt(r0)
            goto L_0x0390
        L_0x0257:
            java.lang.String r3 = r16.readString()
            java.lang.String r4 = r16.readString()
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.client.zzl> r0 = com.google.android.gms.ads.internal.client.zzl.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzbaf.zza(r1, r0)
            r5 = r0
            com.google.android.gms.ads.internal.client.zzl r5 = (com.google.android.gms.ads.internal.client.zzl) r5
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r6 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r0 = r16.readStrongBinder()
            if (r0 != 0) goto L_0x0278
        L_0x0276:
            r7 = r2
            goto L_0x028b
        L_0x0278:
            java.lang.String r2 = "com.google.android.gms.ads.internal.mediation.client.rtb.IInterstitialCallback"
            android.os.IInterface r2 = r0.queryLocalInterface(r2)
            boolean r7 = r2 instanceof com.google.android.gms.internal.ads.zzbsv
            if (r7 == 0) goto L_0x0285
            com.google.android.gms.internal.ads.zzbsv r2 = (com.google.android.gms.internal.ads.zzbsv) r2
            goto L_0x0276
        L_0x0285:
            com.google.android.gms.internal.ads.zzbst r2 = new com.google.android.gms.internal.ads.zzbst
            r2.<init>(r0)
            goto L_0x0276
        L_0x028b:
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.internal.ads.zzbrl r11 = com.google.android.gms.internal.ads.zzbrk.zzb(r0)
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            r0 = r14
            r1 = r3
            r2 = r4
            r3 = r5
            r4 = r6
            r5 = r7
            r6 = r11
            r0.zzl(r1, r2, r3, r4, r5, r6)
            r17.writeNoException()
            goto L_0x0390
        L_0x02a5:
            java.lang.String r3 = r16.readString()
            java.lang.String r4 = r16.readString()
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.client.zzl> r0 = com.google.android.gms.ads.internal.client.zzl.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzbaf.zza(r1, r0)
            r6 = r0
            com.google.android.gms.ads.internal.client.zzl r6 = (com.google.android.gms.ads.internal.client.zzl) r6
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r7 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r0 = r16.readStrongBinder()
            if (r0 != 0) goto L_0x02c6
        L_0x02c4:
            r5 = r2
            goto L_0x02d7
        L_0x02c6:
            android.os.IInterface r2 = r0.queryLocalInterface(r5)
            boolean r5 = r2 instanceof com.google.android.gms.internal.ads.zzbss
            if (r5 == 0) goto L_0x02d1
            com.google.android.gms.internal.ads.zzbss r2 = (com.google.android.gms.internal.ads.zzbss) r2
            goto L_0x02c4
        L_0x02d1:
            com.google.android.gms.internal.ads.zzbsq r2 = new com.google.android.gms.internal.ads.zzbsq
            r2.<init>(r0)
            goto L_0x02c4
        L_0x02d7:
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.internal.ads.zzbrl r11 = com.google.android.gms.internal.ads.zzbrk.zzb(r0)
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.client.zzq> r0 = com.google.android.gms.ads.internal.client.zzq.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzbaf.zza(r1, r0)
            r12 = r0
            com.google.android.gms.ads.internal.client.zzq r12 = (com.google.android.gms.ads.internal.client.zzq) r12
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            r0 = r14
            r1 = r3
            r2 = r4
            r3 = r6
            r4 = r7
            r6 = r11
            r7 = r12
            r0.zzj(r1, r2, r3, r4, r5, r6, r7)
            r17.writeNoException()
            goto L_0x0390
        L_0x02fa:
            r16.createStringArray()
            android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
            java.lang.Object[] r0 = r1.createTypedArray(r0)
            android.os.Bundle[] r0 = (android.os.Bundle[]) r0
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            r17.writeNoException()
            goto L_0x0390
        L_0x030d:
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            r17.writeNoException()
            goto L_0x0390
        L_0x031c:
            com.google.android.gms.ads.internal.client.zzdq r0 = r14.zze()
            r17.writeNoException()
            com.google.android.gms.internal.ads.zzbaf.zzf(r9, r0)
            goto L_0x0390
        L_0x0328:
            com.google.android.gms.internal.ads.zzbtt r0 = r14.zzg()
            r17.writeNoException()
            com.google.android.gms.internal.ads.zzbaf.zze(r9, r0)
            goto L_0x0390
        L_0x0333:
            com.google.android.gms.internal.ads.zzbtt r0 = r14.zzf()
            r17.writeNoException()
            com.google.android.gms.internal.ads.zzbaf.zze(r9, r0)
            goto L_0x0390
        L_0x033e:
            android.os.IBinder r0 = r16.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            java.lang.String r4 = r16.readString()
            android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzbaf.zza(r1, r0)
            r5 = r0
            android.os.Bundle r5 = (android.os.Bundle) r5
            android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzbaf.zza(r1, r0)
            r6 = r0
            android.os.Bundle r6 = (android.os.Bundle) r6
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.client.zzq> r0 = com.google.android.gms.ads.internal.client.zzq.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzbaf.zza(r1, r0)
            r7 = r0
            com.google.android.gms.ads.internal.client.zzq r7 = (com.google.android.gms.ads.internal.client.zzq) r7
            android.os.IBinder r0 = r16.readStrongBinder()
            if (r0 != 0) goto L_0x036d
        L_0x036b:
            r11 = r2
            goto L_0x0380
        L_0x036d:
            java.lang.String r2 = "com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback"
            android.os.IInterface r2 = r0.queryLocalInterface(r2)
            boolean r11 = r2 instanceof com.google.android.gms.internal.ads.zzbth
            if (r11 == 0) goto L_0x037a
            com.google.android.gms.internal.ads.zzbth r2 = (com.google.android.gms.internal.ads.zzbth) r2
            goto L_0x036b
        L_0x037a:
            com.google.android.gms.internal.ads.zzbtf r2 = new com.google.android.gms.internal.ads.zzbtf
            r2.<init>(r0)
            goto L_0x036b
        L_0x0380:
            com.google.android.gms.internal.ads.zzbaf.zzc(r16)
            r0 = r14
            r1 = r3
            r2 = r4
            r3 = r5
            r4 = r6
            r5 = r7
            r6 = r11
            r0.zzh(r1, r2, r3, r4, r5, r6)
            r17.writeNoException()
        L_0x0390:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbtd.zzdF(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
