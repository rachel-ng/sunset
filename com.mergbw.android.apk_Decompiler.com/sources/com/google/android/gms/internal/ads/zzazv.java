package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzazv implements zzhbt {
    zzazv() {
    }

    public final /* synthetic */ zzhbs zza(int i) {
        zzazw zzazw = zzazw.UNSUPPORTED;
        if (i == 0) {
            return zzazw.UNSUPPORTED;
        }
        if (i == 2) {
            return zzazw.ARM7;
        }
        if (i == 999) {
            return zzazw.UNKNOWN;
        }
        if (i == 4) {
            return zzazw.X86;
        }
        if (i == 5) {
            return zzazw.ARM64;
        }
        if (i == 6) {
            return zzazw.X86_64;
        }
        if (i != 7) {
            return null;
        }
        return zzazw.RISCV64;
    }
}
