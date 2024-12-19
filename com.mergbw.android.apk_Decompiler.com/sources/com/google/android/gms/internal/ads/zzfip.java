package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfip {
    public static ParcelFileDescriptor zza(InputStream inputStream) throws IOException {
        ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor parcelFileDescriptor = createPipe[0];
        zzcci.zza.execute(new zzfio(inputStream, createPipe[1]));
        return parcelFileDescriptor;
    }
}
