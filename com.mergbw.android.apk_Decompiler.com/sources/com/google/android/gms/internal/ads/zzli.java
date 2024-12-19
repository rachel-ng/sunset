package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzli extends RuntimeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzli(int i) {
        super(i != 1 ? i != 2 ? "Detaching surface timed out." : "Setting foreground mode timed out." : "Player release timed out.");
    }
}
