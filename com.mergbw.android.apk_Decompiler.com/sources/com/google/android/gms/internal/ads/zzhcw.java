package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhcw implements zzhea {
    private static final zzhdc zza = new zzhcu();
    private final zzhdc zzb;

    public zzhcw() {
        zzhdc zzhdc;
        zzhdc[] zzhdcArr = new zzhdc[2];
        zzhdcArr[0] = zzhbh.zza();
        try {
            zzhdc = (zzhdc) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (Exception unused) {
            zzhdc = zza;
        }
        zzhdcArr[1] = zzhdc;
        zzhcv zzhcv = new zzhcv(zzhdcArr);
        byte[] bArr = zzhcb.zzd;
        this.zzb = zzhcv;
    }

    private static boolean zzb(zzhdb zzhdb) {
        return zzhdb.zzc() + -1 != 1;
    }

    public final zzhdz zza(Class cls) {
        zzheb.zzs(cls);
        zzhdb zzb2 = this.zzb.zzb(cls);
        if (zzb2.zzb()) {
            if (zzhbo.class.isAssignableFrom(cls)) {
                return zzhdi.zzc(zzheb.zzn(), zzhbb.zzb(), zzb2.zza());
            }
            return zzhdi.zzc(zzheb.zzm(), zzhbb.zza(), zzb2.zza());
        } else if (zzhbo.class.isAssignableFrom(cls)) {
            if (zzb(zzb2)) {
                return zzhdh.zzm(cls, zzb2, zzhdl.zzb(), zzhcs.zze(), zzheb.zzn(), zzhbb.zzb(), zzhda.zzb());
            }
            return zzhdh.zzm(cls, zzb2, zzhdl.zzb(), zzhcs.zze(), zzheb.zzn(), (zzhaz) null, zzhda.zzb());
        } else if (zzb(zzb2)) {
            return zzhdh.zzm(cls, zzb2, zzhdl.zza(), zzhcs.zzd(), zzheb.zzm(), zzhbb.zza(), zzhda.zza());
        } else {
            return zzhdh.zzm(cls, zzb2, zzhdl.zza(), zzhcs.zzd(), zzheb.zzm(), (zzhaz) null, zzhda.zza());
        }
    }
}
