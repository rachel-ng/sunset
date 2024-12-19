package com.google.android.gms.internal.ads;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzagu {
    private final ByteArrayOutputStream zza;
    private final DataOutputStream zzb;

    public zzagu() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        this.zza = byteArrayOutputStream;
        this.zzb = new DataOutputStream(byteArrayOutputStream);
    }

    private static void zzb(DataOutputStream dataOutputStream, String str) throws IOException {
        dataOutputStream.writeBytes(str);
        dataOutputStream.writeByte(0);
    }

    public final byte[] zza(zzagt zzagt) {
        this.zza.reset();
        try {
            zzb(this.zzb, zzagt.zza);
            String str = zzagt.zzb;
            if (str == null) {
                str = "";
            }
            zzb(this.zzb, str);
            this.zzb.writeLong(zzagt.zzc);
            this.zzb.writeLong(zzagt.zzd);
            this.zzb.write(zzagt.zze);
            this.zzb.flush();
            return this.zza.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
