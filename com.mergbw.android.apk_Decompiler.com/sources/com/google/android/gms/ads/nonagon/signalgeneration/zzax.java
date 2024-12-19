package com.google.android.gms.ads.nonagon.signalgeneration;

import android.os.Bundle;
import com.google.android.gms.internal.ads.zzbxu;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzax {
    public final String zza;
    public String zzb;
    public zzbxu zzc;
    public Bundle zzd = new Bundle();
    private long zze = -1;
    private long zzf = -1;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzax(android.util.JsonReader r8, com.google.android.gms.internal.ads.zzbxu r9) throws java.io.IOException {
        /*
            r7 = this;
            r7.<init>()
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            r7.zzd = r0
            r0 = -1
            r7.zze = r0
            r7.zzf = r0
            r7.zzc = r9
            java.util.HashMap r9 = new java.util.HashMap
            r9.<init>()
            r8.beginObject()
            java.lang.String r0 = ""
            r1 = r0
        L_0x001d:
            boolean r2 = r8.hasNext()
            if (r2 == 0) goto L_0x009b
            java.lang.String r2 = r8.nextName()
            if (r2 != 0) goto L_0x002a
            r2 = r0
        L_0x002a:
            int r3 = r2.hashCode()
            r4 = 3
            r5 = 2
            r6 = 1
            switch(r3) {
                case -1573145462: goto L_0x0053;
                case -995427962: goto L_0x0049;
                case -271442291: goto L_0x003f;
                case 1725551537: goto L_0x0035;
                default: goto L_0x0034;
            }
        L_0x0034:
            goto L_0x005d
        L_0x0035:
            java.lang.String r3 = "end_time"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x005d
            r2 = r4
            goto L_0x005e
        L_0x003f:
            java.lang.String r3 = "signal_dictionary"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x005d
            r2 = r6
            goto L_0x005e
        L_0x0049:
            java.lang.String r3 = "params"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x005d
            r2 = 0
            goto L_0x005e
        L_0x0053:
            java.lang.String r3 = "start_time"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x005d
            r2 = r5
            goto L_0x005e
        L_0x005d:
            r2 = -1
        L_0x005e:
            if (r2 == 0) goto L_0x0096
            if (r2 == r6) goto L_0x0078
            if (r2 == r5) goto L_0x0071
            if (r2 == r4) goto L_0x006a
            r8.skipValue()
            goto L_0x001d
        L_0x006a:
            long r2 = r8.nextLong()
            r7.zzf = r2
            goto L_0x001d
        L_0x0071:
            long r2 = r8.nextLong()
            r7.zze = r2
            goto L_0x001d
        L_0x0078:
            java.util.HashMap r9 = new java.util.HashMap
            r9.<init>()
            r8.beginObject()
        L_0x0080:
            boolean r2 = r8.hasNext()
            if (r2 == 0) goto L_0x0092
            java.lang.String r2 = r8.nextName()
            java.lang.String r3 = r8.nextString()
            r9.put(r2, r3)
            goto L_0x0080
        L_0x0092:
            r8.endObject()
            goto L_0x001d
        L_0x0096:
            java.lang.String r1 = r8.nextString()
            goto L_0x001d
        L_0x009b:
            r7.zza = r1
            r8.endObject()
            java.util.Set r8 = r9.entrySet()
            java.util.Iterator r8 = r8.iterator()
        L_0x00a8:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x00d2
            java.lang.Object r9 = r8.next()
            java.util.Map$Entry r9 = (java.util.Map.Entry) r9
            java.lang.Object r0 = r9.getKey()
            if (r0 == 0) goto L_0x00a8
            java.lang.Object r0 = r9.getValue()
            if (r0 == 0) goto L_0x00a8
            android.os.Bundle r0 = r7.zzd
            java.lang.Object r1 = r9.getKey()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r9 = r9.getValue()
            java.lang.String r9 = (java.lang.String) r9
            r0.putString(r1, r9)
            goto L_0x00a8
        L_0x00d2:
            com.google.android.gms.internal.ads.zzbeg r8 = com.google.android.gms.internal.ads.zzbep.zzcd
            com.google.android.gms.internal.ads.zzben r9 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r8 = r9.zza(r8)
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x0106
            com.google.android.gms.internal.ads.zzbxu r8 = r7.zzc
            if (r8 == 0) goto L_0x0106
            android.os.Bundle r8 = r8.zzm
            if (r8 == 0) goto L_0x0106
            com.google.android.gms.internal.ads.zzdul r9 = com.google.android.gms.internal.ads.zzdul.GET_SIGNALS_SDKCORE_START
            java.lang.String r9 = r9.zza()
            long r0 = r7.zze
            r8.putLong(r9, r0)
            com.google.android.gms.internal.ads.zzbxu r8 = r7.zzc
            android.os.Bundle r8 = r8.zzm
            com.google.android.gms.internal.ads.zzdul r9 = com.google.android.gms.internal.ads.zzdul.GET_SIGNALS_SDKCORE_END
            java.lang.String r9 = r9.zza()
            long r0 = r7.zzf
            r8.putLong(r9, r0)
        L_0x0106:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.nonagon.signalgeneration.zzax.<init>(android.util.JsonReader, com.google.android.gms.internal.ads.zzbxu):void");
    }
}
