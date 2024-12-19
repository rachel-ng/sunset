package com.google.android.gms.internal.ads;

import sun.misc.Unsafe;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class zzgek$$ExternalSyntheticBackportWithForwarding0 {
    public static /* synthetic */ boolean m(Unsafe unsafe, Object obj, long j, Object obj2, Object obj3) {
        while (!unsafe.compareAndSwapObject(obj, j, obj2, obj3)) {
            if (unsafe.getObject(obj, j) != obj2) {
                return false;
            }
        }
        return true;
    }
}
