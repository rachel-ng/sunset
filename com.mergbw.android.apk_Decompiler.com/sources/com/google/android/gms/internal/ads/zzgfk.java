package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public class zzgfk extends zzgfu {
    zzgfk() {
    }

    public static zzgfk zzu(ListenableFuture listenableFuture) {
        if (listenableFuture instanceof zzgfk) {
            return (zzgfk) listenableFuture;
        }
        return new zzgfl(listenableFuture);
    }
}
