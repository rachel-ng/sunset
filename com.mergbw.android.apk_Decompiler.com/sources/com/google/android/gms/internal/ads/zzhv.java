package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.PlaybackException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public class zzhv extends zzhc {
    public final zzhh zzb;
    public final int zzc;

    public zzhv(zzhh zzhh, int i, int i2) {
        super(zzb(2008, 1));
        this.zzb = zzhh;
        this.zzc = 1;
    }

    public static zzhv zza(IOException iOException, zzhh zzhh, int i) {
        int i2;
        String message = iOException.getMessage();
        if (iOException instanceof SocketTimeoutException) {
            i2 = PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT;
        } else if (iOException instanceof InterruptedIOException) {
            i2 = 1004;
        } else {
            i2 = (message == null || !zzfxm.zza(message).matches("cleartext.*not permitted.*")) ? 2001 : 2007;
        }
        if (i2 == 2007) {
            return new zzhu(iOException, zzhh);
        }
        return new zzhv(iOException, zzhh, i2, i);
    }

    private static int zzb(int i, int i2) {
        if (i != 2000) {
            return i;
        }
        if (i2 != 1) {
            return 2000;
        }
        return PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED;
    }

    public zzhv(IOException iOException, zzhh zzhh, int i, int i2) {
        super((Throwable) iOException, zzb(i, i2));
        this.zzb = zzhh;
        this.zzc = i2;
    }

    public zzhv(String str, zzhh zzhh, int i, int i2) {
        super(str, zzb(i, i2));
        this.zzb = zzhh;
        this.zzc = i2;
    }

    public zzhv(String str, IOException iOException, zzhh zzhh, int i, int i2) {
        super(str, iOException, zzb(i, i2));
        this.zzb = zzhh;
        this.zzc = i2;
    }
}
