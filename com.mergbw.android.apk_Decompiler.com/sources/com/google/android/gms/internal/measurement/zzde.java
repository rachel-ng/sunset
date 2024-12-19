package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
public abstract class zzde extends zzbx implements zzdb {
    public static zzdb asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
        if (queryLocalInterface instanceof zzdb) {
            return (zzdb) queryLocalInterface;
        }
        return new zzdd(iBinder);
    }

    public zzde() {
        super("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v9, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v14, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v22, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v28, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v33, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v38, types: [com.google.android.gms.internal.measurement.zzdm] */
    /* JADX WARNING: type inference failed for: r3v43, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v48, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v53, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v58, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v64, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v69, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v76, types: [com.google.android.gms.internal.measurement.zzdh] */
    /* JADX WARNING: type inference failed for: r3v81, types: [com.google.android.gms.internal.measurement.zzdh] */
    /* JADX WARNING: type inference failed for: r3v86, types: [com.google.android.gms.internal.measurement.zzdh] */
    /* JADX WARNING: type inference failed for: r3v91, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v96, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v101, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v106 */
    /* JADX WARNING: type inference failed for: r3v107 */
    /* JADX WARNING: type inference failed for: r3v108 */
    /* JADX WARNING: type inference failed for: r3v109 */
    /* JADX WARNING: type inference failed for: r3v110 */
    /* JADX WARNING: type inference failed for: r3v111 */
    /* JADX WARNING: type inference failed for: r3v112 */
    /* JADX WARNING: type inference failed for: r3v113 */
    /* JADX WARNING: type inference failed for: r3v114 */
    /* JADX WARNING: type inference failed for: r3v115 */
    /* JADX WARNING: type inference failed for: r3v116 */
    /* JADX WARNING: type inference failed for: r3v117 */
    /* JADX WARNING: type inference failed for: r3v118 */
    /* JADX WARNING: type inference failed for: r3v119 */
    /* JADX WARNING: type inference failed for: r3v120 */
    /* JADX WARNING: type inference failed for: r3v121 */
    /* JADX WARNING: type inference failed for: r3v122 */
    /* JADX WARNING: type inference failed for: r3v123 */
    /* JADX WARNING: type inference failed for: r3v124 */
    /* JADX WARNING: type inference failed for: r3v125 */
    /* JADX WARNING: type inference failed for: r3v126 */
    /* JADX WARNING: type inference failed for: r3v127 */
    /* JADX WARNING: type inference failed for: r3v128 */
    /* JADX WARNING: type inference failed for: r3v129 */
    /* JADX WARNING: type inference failed for: r3v130 */
    /* JADX WARNING: type inference failed for: r3v131 */
    /* JADX WARNING: type inference failed for: r3v132 */
    /* JADX WARNING: type inference failed for: r3v133 */
    /* JADX WARNING: type inference failed for: r3v134 */
    /* JADX WARNING: type inference failed for: r3v135 */
    /* JADX WARNING: type inference failed for: r3v136 */
    /* JADX WARNING: type inference failed for: r3v137 */
    /* JADX WARNING: type inference failed for: r3v138 */
    /* JADX WARNING: type inference failed for: r3v139 */
    /* JADX WARNING: type inference failed for: r3v140 */
    /* JADX WARNING: type inference failed for: r3v141 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r11, android.os.Parcel r12, android.os.Parcel r13, int r14) throws android.os.RemoteException {
        /*
            r10 = this;
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IEventHandlerProxy"
            java.lang.String r2 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            r3 = 0
            switch(r11) {
                case 1: goto L_0x04c0;
                case 2: goto L_0x049c;
                case 3: goto L_0x0463;
                case 4: goto L_0x0442;
                case 5: goto L_0x0416;
                case 6: goto L_0x03f2;
                case 7: goto L_0x03e2;
                case 8: goto L_0x03ce;
                case 9: goto L_0x03b6;
                case 10: goto L_0x038e;
                case 11: goto L_0x037e;
                case 12: goto L_0x0372;
                case 13: goto L_0x0366;
                case 14: goto L_0x035a;
                case 15: goto L_0x033d;
                case 16: goto L_0x031d;
                case 17: goto L_0x02fd;
                case 18: goto L_0x02db;
                case 19: goto L_0x02bb;
                case 20: goto L_0x029b;
                case 21: goto L_0x027b;
                case 22: goto L_0x025b;
                case 23: goto L_0x024b;
                case 24: goto L_0x023b;
                case 25: goto L_0x0227;
                case 26: goto L_0x0213;
                case 27: goto L_0x01f7;
                case 28: goto L_0x01e3;
                case 29: goto L_0x01cf;
                case 30: goto L_0x01bb;
                case 31: goto L_0x018f;
                case 32: goto L_0x0163;
                case 33: goto L_0x013a;
                case 34: goto L_0x011a;
                case 35: goto L_0x00fa;
                case 36: goto L_0x00da;
                case 37: goto L_0x00ce;
                case 38: goto L_0x00aa;
                case 39: goto L_0x009e;
                case 40: goto L_0x007e;
                case 41: goto L_0x0008;
                case 42: goto L_0x006e;
                case 43: goto L_0x0062;
                case 44: goto L_0x004e;
                case 45: goto L_0x003a;
                case 46: goto L_0x001a;
                case 47: goto L_0x0008;
                case 48: goto L_0x000a;
                default: goto L_0x0008;
            }
        L_0x0008:
            r0 = 0
            return r0
        L_0x000a:
            android.os.Parcelable$Creator r1 = android.content.Intent.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r12, r1)
            android.content.Intent r1 = (android.content.Intent) r1
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.setSgtmDebugInfo(r1)
            goto L_0x04da
        L_0x001a:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x0021
            goto L_0x0032
        L_0x0021:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x002d
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x0032
        L_0x002d:
            com.google.android.gms.internal.measurement.zzdi r3 = new com.google.android.gms.internal.measurement.zzdi
            r3.<init>(r1)
        L_0x0032:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.getSessionId(r3)
            goto L_0x04da
        L_0x003a:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r12, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.setConsentThirdParty(r1, r2)
            goto L_0x04da
        L_0x004e:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r12, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.setConsent(r1, r2)
            goto L_0x04da
        L_0x0062:
            long r1 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.clearMeasurementEnabled(r1)
            goto L_0x04da
        L_0x006e:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r12, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.setDefaultEventParameters(r1)
            goto L_0x04da
        L_0x007e:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x0085
            goto L_0x0096
        L_0x0085:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x0091
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x0096
        L_0x0091:
            com.google.android.gms.internal.measurement.zzdi r3 = new com.google.android.gms.internal.measurement.zzdi
            r3.<init>(r1)
        L_0x0096:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.isDataCollectionEnabled(r3)
            goto L_0x04da
        L_0x009e:
            boolean r1 = com.google.android.gms.internal.measurement.zzbw.zzc(r12)
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.setDataCollectionEnabled(r1)
            goto L_0x04da
        L_0x00aa:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x00b1
            goto L_0x00c2
        L_0x00b1:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x00bd
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x00c2
        L_0x00bd:
            com.google.android.gms.internal.measurement.zzdi r3 = new com.google.android.gms.internal.measurement.zzdi
            r3.<init>(r1)
        L_0x00c2:
            int r1 = r12.readInt()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.getTestFlag(r3, r1)
            goto L_0x04da
        L_0x00ce:
            java.util.HashMap r1 = com.google.android.gms.internal.measurement.zzbw.zza(r12)
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.initForTests(r1)
            goto L_0x04da
        L_0x00da:
            android.os.IBinder r2 = r12.readStrongBinder()
            if (r2 != 0) goto L_0x00e1
            goto L_0x00f2
        L_0x00e1:
            android.os.IInterface r1 = r2.queryLocalInterface(r1)
            boolean r3 = r1 instanceof com.google.android.gms.internal.measurement.zzdh
            if (r3 == 0) goto L_0x00ed
            r3 = r1
            com.google.android.gms.internal.measurement.zzdh r3 = (com.google.android.gms.internal.measurement.zzdh) r3
            goto L_0x00f2
        L_0x00ed:
            com.google.android.gms.internal.measurement.zzdj r3 = new com.google.android.gms.internal.measurement.zzdj
            r3.<init>(r2)
        L_0x00f2:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.unregisterOnMeasurementEventListener(r3)
            goto L_0x04da
        L_0x00fa:
            android.os.IBinder r2 = r12.readStrongBinder()
            if (r2 != 0) goto L_0x0101
            goto L_0x0112
        L_0x0101:
            android.os.IInterface r1 = r2.queryLocalInterface(r1)
            boolean r3 = r1 instanceof com.google.android.gms.internal.measurement.zzdh
            if (r3 == 0) goto L_0x010d
            r3 = r1
            com.google.android.gms.internal.measurement.zzdh r3 = (com.google.android.gms.internal.measurement.zzdh) r3
            goto L_0x0112
        L_0x010d:
            com.google.android.gms.internal.measurement.zzdj r3 = new com.google.android.gms.internal.measurement.zzdj
            r3.<init>(r2)
        L_0x0112:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.registerOnMeasurementEventListener(r3)
            goto L_0x04da
        L_0x011a:
            android.os.IBinder r2 = r12.readStrongBinder()
            if (r2 != 0) goto L_0x0121
            goto L_0x0132
        L_0x0121:
            android.os.IInterface r1 = r2.queryLocalInterface(r1)
            boolean r3 = r1 instanceof com.google.android.gms.internal.measurement.zzdh
            if (r3 == 0) goto L_0x012d
            r3 = r1
            com.google.android.gms.internal.measurement.zzdh r3 = (com.google.android.gms.internal.measurement.zzdh) r3
            goto L_0x0132
        L_0x012d:
            com.google.android.gms.internal.measurement.zzdj r3 = new com.google.android.gms.internal.measurement.zzdj
            r3.<init>(r2)
        L_0x0132:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.setEventInterceptor(r3)
            goto L_0x04da
        L_0x013a:
            int r1 = r12.readInt()
            java.lang.String r2 = r12.readString()
            android.os.IBinder r3 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            android.os.IBinder r4 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r4)
            android.os.IBinder r5 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r5)
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r0 = r10
            r0.logHealthData(r1, r2, r3, r4, r5)
            goto L_0x04da
        L_0x0163:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r12, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            android.os.IBinder r4 = r12.readStrongBinder()
            if (r4 != 0) goto L_0x0172
            goto L_0x0183
        L_0x0172:
            android.os.IInterface r2 = r4.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x017e
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x0183
        L_0x017e:
            com.google.android.gms.internal.measurement.zzdi r3 = new com.google.android.gms.internal.measurement.zzdi
            r3.<init>(r4)
        L_0x0183:
            long r4 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.performAction(r1, r3, r4)
            goto L_0x04da
        L_0x018f:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            android.os.IBinder r4 = r12.readStrongBinder()
            if (r4 != 0) goto L_0x019e
            goto L_0x01af
        L_0x019e:
            android.os.IInterface r2 = r4.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x01aa
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x01af
        L_0x01aa:
            com.google.android.gms.internal.measurement.zzdi r3 = new com.google.android.gms.internal.measurement.zzdi
            r3.<init>(r4)
        L_0x01af:
            long r4 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.onActivitySaveInstanceState(r1, r3, r4)
            goto L_0x04da
        L_0x01bb:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.onActivityResumed(r1, r2)
            goto L_0x04da
        L_0x01cf:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.onActivityPaused(r1, r2)
            goto L_0x04da
        L_0x01e3:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.onActivityDestroyed(r1, r2)
            goto L_0x04da
        L_0x01f7:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r12, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            long r3 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.onActivityCreated(r1, r2, r3)
            goto L_0x04da
        L_0x0213:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.onActivityStopped(r1, r2)
            goto L_0x04da
        L_0x0227:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.onActivityStarted(r1, r2)
            goto L_0x04da
        L_0x023b:
            java.lang.String r1 = r12.readString()
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.endAdUnitExposure(r1, r2)
            goto L_0x04da
        L_0x024b:
            java.lang.String r1 = r12.readString()
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.beginAdUnitExposure(r1, r2)
            goto L_0x04da
        L_0x025b:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x0262
            goto L_0x0273
        L_0x0262:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x026e
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x0273
        L_0x026e:
            com.google.android.gms.internal.measurement.zzdi r3 = new com.google.android.gms.internal.measurement.zzdi
            r3.<init>(r1)
        L_0x0273:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.generateEventId(r3)
            goto L_0x04da
        L_0x027b:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x0282
            goto L_0x0293
        L_0x0282:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x028e
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x0293
        L_0x028e:
            com.google.android.gms.internal.measurement.zzdi r3 = new com.google.android.gms.internal.measurement.zzdi
            r3.<init>(r1)
        L_0x0293:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.getGmpAppId(r3)
            goto L_0x04da
        L_0x029b:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x02a2
            goto L_0x02b3
        L_0x02a2:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x02ae
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x02b3
        L_0x02ae:
            com.google.android.gms.internal.measurement.zzdi r3 = new com.google.android.gms.internal.measurement.zzdi
            r3.<init>(r1)
        L_0x02b3:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.getAppInstanceId(r3)
            goto L_0x04da
        L_0x02bb:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x02c2
            goto L_0x02d3
        L_0x02c2:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x02ce
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x02d3
        L_0x02ce:
            com.google.android.gms.internal.measurement.zzdi r3 = new com.google.android.gms.internal.measurement.zzdi
            r3.<init>(r1)
        L_0x02d3:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.getCachedAppInstanceId(r3)
            goto L_0x04da
        L_0x02db:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x02e2
            goto L_0x02f5
        L_0x02e2:
            java.lang.String r2 = "com.google.android.gms.measurement.api.internal.IStringProvider"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdm
            if (r3 == 0) goto L_0x02f0
            r3 = r2
            com.google.android.gms.internal.measurement.zzdm r3 = (com.google.android.gms.internal.measurement.zzdm) r3
            goto L_0x02f5
        L_0x02f0:
            com.google.android.gms.internal.measurement.zzdl r3 = new com.google.android.gms.internal.measurement.zzdl
            r3.<init>(r1)
        L_0x02f5:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.setInstanceIdProvider(r3)
            goto L_0x04da
        L_0x02fd:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x0304
            goto L_0x0315
        L_0x0304:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x0310
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x0315
        L_0x0310:
            com.google.android.gms.internal.measurement.zzdi r3 = new com.google.android.gms.internal.measurement.zzdi
            r3.<init>(r1)
        L_0x0315:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.getCurrentScreenClass(r3)
            goto L_0x04da
        L_0x031d:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x0324
            goto L_0x0335
        L_0x0324:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x0330
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x0335
        L_0x0330:
            com.google.android.gms.internal.measurement.zzdi r3 = new com.google.android.gms.internal.measurement.zzdi
            r3.<init>(r1)
        L_0x0335:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.getCurrentScreenName(r3)
            goto L_0x04da
        L_0x033d:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            java.lang.String r2 = r12.readString()
            java.lang.String r3 = r12.readString()
            long r4 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r0 = r10
            r0.setCurrentScreen(r1, r2, r3, r4)
            goto L_0x04da
        L_0x035a:
            long r1 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.setSessionTimeoutDuration(r1)
            goto L_0x04da
        L_0x0366:
            long r1 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.setMinimumSessionDuration(r1)
            goto L_0x04da
        L_0x0372:
            long r1 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.resetAnalyticsData(r1)
            goto L_0x04da
        L_0x037e:
            boolean r1 = com.google.android.gms.internal.measurement.zzbw.zzc(r12)
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.setMeasurementEnabled(r1, r2)
            goto L_0x04da
        L_0x038e:
            java.lang.String r1 = r12.readString()
            java.lang.String r4 = r12.readString()
            android.os.IBinder r5 = r12.readStrongBinder()
            if (r5 != 0) goto L_0x039d
            goto L_0x03ae
        L_0x039d:
            android.os.IInterface r2 = r5.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x03a9
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x03ae
        L_0x03a9:
            com.google.android.gms.internal.measurement.zzdi r3 = new com.google.android.gms.internal.measurement.zzdi
            r3.<init>(r5)
        L_0x03ae:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.getConditionalUserProperties(r1, r4, r3)
            goto L_0x04da
        L_0x03b6:
            java.lang.String r1 = r12.readString()
            java.lang.String r2 = r12.readString()
            android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r12, r3)
            android.os.Bundle r3 = (android.os.Bundle) r3
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.clearConditionalUserProperty(r1, r2, r3)
            goto L_0x04da
        L_0x03ce:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r12, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.setConditionalUserProperty(r1, r2)
            goto L_0x04da
        L_0x03e2:
            java.lang.String r1 = r12.readString()
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.setUserId(r1, r2)
            goto L_0x04da
        L_0x03f2:
            java.lang.String r1 = r12.readString()
            android.os.IBinder r4 = r12.readStrongBinder()
            if (r4 != 0) goto L_0x03fd
            goto L_0x040e
        L_0x03fd:
            android.os.IInterface r2 = r4.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x0409
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x040e
        L_0x0409:
            com.google.android.gms.internal.measurement.zzdi r3 = new com.google.android.gms.internal.measurement.zzdi
            r3.<init>(r4)
        L_0x040e:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.getMaxUserProperties(r1, r3)
            goto L_0x04da
        L_0x0416:
            java.lang.String r1 = r12.readString()
            java.lang.String r4 = r12.readString()
            boolean r5 = com.google.android.gms.internal.measurement.zzbw.zzc(r12)
            android.os.IBinder r6 = r12.readStrongBinder()
            if (r6 != 0) goto L_0x0429
            goto L_0x043a
        L_0x0429:
            android.os.IInterface r2 = r6.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x0435
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x043a
        L_0x0435:
            com.google.android.gms.internal.measurement.zzdi r3 = new com.google.android.gms.internal.measurement.zzdi
            r3.<init>(r6)
        L_0x043a:
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.getUserProperties(r1, r4, r5, r3)
            goto L_0x04da
        L_0x0442:
            java.lang.String r1 = r12.readString()
            java.lang.String r2 = r12.readString()
            android.os.IBinder r3 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            boolean r4 = com.google.android.gms.internal.measurement.zzbw.zzc(r12)
            long r5 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r0 = r10
            r0.setUserProperty(r1, r2, r3, r4, r5)
            goto L_0x04da
        L_0x0463:
            java.lang.String r1 = r12.readString()
            java.lang.String r4 = r12.readString()
            android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
            android.os.Parcelable r5 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r12, r5)
            android.os.Bundle r5 = (android.os.Bundle) r5
            android.os.IBinder r6 = r12.readStrongBinder()
            if (r6 != 0) goto L_0x047b
            r6 = r3
            goto L_0x048c
        L_0x047b:
            android.os.IInterface r2 = r6.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x0486
            com.google.android.gms.internal.measurement.zzdg r2 = (com.google.android.gms.internal.measurement.zzdg) r2
            goto L_0x048b
        L_0x0486:
            com.google.android.gms.internal.measurement.zzdi r2 = new com.google.android.gms.internal.measurement.zzdi
            r2.<init>(r6)
        L_0x048b:
            r6 = r2
        L_0x048c:
            long r8 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r0 = r10
            r2 = r4
            r3 = r5
            r4 = r6
            r5 = r8
            r0.logEventAndBundle(r1, r2, r3, r4, r5)
            goto L_0x04da
        L_0x049c:
            java.lang.String r1 = r12.readString()
            java.lang.String r2 = r12.readString()
            android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r12, r3)
            android.os.Bundle r3 = (android.os.Bundle) r3
            boolean r4 = com.google.android.gms.internal.measurement.zzbw.zzc(r12)
            boolean r5 = com.google.android.gms.internal.measurement.zzbw.zzc(r12)
            long r6 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r0 = r10
            r0.logEvent(r1, r2, r3, r4, r5, r6)
            goto L_0x04da
        L_0x04c0:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzdo> r2 = com.google.android.gms.internal.measurement.zzdo.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r12, r2)
            com.google.android.gms.internal.measurement.zzdo r2 = (com.google.android.gms.internal.measurement.zzdo) r2
            long r3 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r12)
            r10.initialize(r1, r2, r3)
        L_0x04da:
            r13.writeNoException()
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzde.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
