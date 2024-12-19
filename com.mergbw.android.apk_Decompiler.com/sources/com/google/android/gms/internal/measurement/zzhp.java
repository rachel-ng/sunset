package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhp;
import com.google.android.gms.internal.measurement.zzhq;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
public abstract class zzhp<MessageType extends zzhq<MessageType, BuilderType>, BuilderType extends zzhp<MessageType, BuilderType>> implements zzks {
    /* renamed from: zza */
    public abstract BuilderType zzb(zzio zzio, zzix zzix) throws IOException;

    /* renamed from: zzaf */
    public abstract BuilderType clone();

    public BuilderType zza(byte[] bArr, int i, int i2) throws zzjs {
        try {
            zzio zza = zzio.zza(bArr, 0, i2, false);
            zzb(zza, zzix.zza);
            zza.zzb(0);
            return this;
        } catch (zzjs e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException(zza("byte array"), e2);
        }
    }

    public BuilderType zza(byte[] bArr, int i, int i2, zzix zzix) throws zzjs {
        try {
            zzio zza = zzio.zza(bArr, 0, i2, false);
            zzb(zza, zzix);
            zza.zzb(0);
            return this;
        } catch (zzjs e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException(zza("byte array"), e2);
        }
    }

    public final /* synthetic */ zzks zza(byte[] bArr) throws zzjs {
        return zza(bArr, 0, bArr.length);
    }

    public final /* synthetic */ zzks zza(byte[] bArr, zzix zzix) throws zzjs {
        return zza(bArr, 0, bArr.length, zzix);
    }

    private final String zza(String str) {
        String name = getClass().getName();
        return "Reading " + name + " from a " + str + " threw an IOException (should never happen).";
    }
}
