package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzfvn extends zzbae implements zzfvo {
    public static zzfvo zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.play.core.lmd.protocol.ILmdOverlayService");
        return queryLocalInterface instanceof zzfvo ? (zzfvo) queryLocalInterface : new zzfvm(iBinder);
    }
}
