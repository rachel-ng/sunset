package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.PlaybackException;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhu extends zzhv {
    public zzhu(IOException iOException, zzhh zzhh) {
        super("Cleartext HTTP traffic not permitted. See https://developer.android.com/guide/topics/media/issues/cleartext-not-permitted", iOException, zzhh, PlaybackException.ERROR_CODE_IO_CLEARTEXT_NOT_PERMITTED, 1);
    }
}
