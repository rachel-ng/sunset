package com.google.android.gms.internal.ads;

import java.util.ArrayDeque;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhdu {
    private final ArrayDeque zza = new ArrayDeque();

    private zzhdu() {
    }

    static /* bridge */ /* synthetic */ zzhac zza(zzhdu zzhdu, zzhac zzhac, zzhac zzhac2) {
        zzhdu.zzb(zzhac);
        zzhdu.zzb(zzhac2);
        zzhac zzhac3 = (zzhac) zzhdu.zza.pop();
        while (!zzhdu.zza.isEmpty()) {
            zzhac3 = new zzhdy((zzhac) zzhdu.zza.pop(), zzhac3);
        }
        return zzhac3;
    }

    private final void zzb(zzhac zzhac) {
        if (zzhac.zzh()) {
            int zzc = zzc(zzhac.zzd());
            ArrayDeque arrayDeque = this.zza;
            int zzc2 = zzhdy.zzc(zzc + 1);
            if (arrayDeque.isEmpty() || ((zzhac) this.zza.peek()).zzd() >= zzc2) {
                this.zza.push(zzhac);
                return;
            }
            int zzc3 = zzhdy.zzc(zzc);
            zzhac zzhac2 = (zzhac) this.zza.pop();
            while (!this.zza.isEmpty() && ((zzhac) this.zza.peek()).zzd() < zzc3) {
                zzhac2 = new zzhdy((zzhac) this.zza.pop(), zzhac2);
            }
            zzhdy zzhdy = new zzhdy(zzhac2, zzhac);
            while (!this.zza.isEmpty()) {
                ArrayDeque arrayDeque2 = this.zza;
                if (((zzhac) arrayDeque2.peek()).zzd() >= zzhdy.zzc(zzc(zzhdy.zzd()) + 1)) {
                    break;
                }
                zzhdy = new zzhdy((zzhac) this.zza.pop(), zzhdy);
            }
            this.zza.push(zzhdy);
        } else if (zzhac instanceof zzhdy) {
            zzhdy zzhdy2 = (zzhdy) zzhac;
            zzb(zzhdy2.zzd);
            zzb(zzhdy2.zze);
        } else {
            throw new IllegalArgumentException("Has a new type of ByteString been created? Found ".concat(String.valueOf(String.valueOf(zzhac.getClass()))));
        }
    }

    private static final int zzc(int i) {
        int binarySearch = Arrays.binarySearch(zzhdy.zza, i);
        return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
    }

    /* synthetic */ zzhdu(zzhdt zzhdt) {
    }
}
