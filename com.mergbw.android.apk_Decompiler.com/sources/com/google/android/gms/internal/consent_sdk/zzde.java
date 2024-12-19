package com.google.android.gms.internal.consent_sdk;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public abstract class zzde extends zzda implements Set {
    @CheckForNull
    private transient zzdd zza;

    zzde() {
    }

    static int zzf(int i) {
        int max = Math.max(i, 2);
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            do {
                highestOneBit += highestOneBit;
            } while (((double) highestOneBit) * 0.7d < ((double) max));
            return highestOneBit;
        } else if (max < 1073741824) {
            return 1073741824;
        } else {
            throw new IllegalArgumentException("collection too large");
        }
    }

    public static zzde zzi() {
        return zzdh.zza;
    }

    public static zzde zzj(Object obj, Object obj2, Object obj3, Object obj4) {
        return zzl(4, "IABTCF_TCString", "IABGPP_HDR_GppString", "IABGPP_GppSID", "IABUSPrivacy_String");
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzde) && zzk() && ((zzde) obj).zzk() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size() && containsAll(set)) {
                    return true;
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public int hashCode() {
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i += next != null ? next.hashCode() : 0;
        }
        return i;
    }

    /* renamed from: zzd */
    public abstract zzdj iterator();

    public final zzdd zzg() {
        zzdd zzdd = this.zza;
        if (zzdd != null) {
            return zzdd;
        }
        zzdd zzh = zzh();
        this.zza = zzh;
        return zzh;
    }

    /* access modifiers changed from: package-private */
    public zzdd zzh() {
        Object[] array = toArray();
        int i = zzdd.zzd;
        return zzdd.zzg(array, array.length);
    }

    /* access modifiers changed from: package-private */
    public boolean zzk() {
        return false;
    }

    private static zzde zzl(int i, Object... objArr) {
        if (i == 0) {
            return zzdh.zza;
        }
        if (i == 1) {
            return new zzdi(Objects.requireNonNull(objArr[0]));
        }
        int zzf = zzf(i);
        Object[] objArr2 = new Object[zzf];
        int i2 = zzf - 1;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < i) {
            Object obj = objArr[i3];
            if (obj != null) {
                int hashCode = obj.hashCode();
                int zza2 = zzcz.zza(hashCode);
                while (true) {
                    int i6 = zza2 & i2;
                    Object obj2 = objArr2[i6];
                    if (obj2 != null) {
                        if (obj2.equals(obj)) {
                            break;
                        }
                        zza2++;
                    } else {
                        objArr[i5] = obj;
                        objArr2[i6] = obj;
                        i4 += hashCode;
                        i5++;
                        break;
                    }
                }
                i3++;
            } else {
                throw new NullPointerException("at index " + i3);
            }
        }
        Arrays.fill(objArr, i5, i, (Object) null);
        if (i5 == 1) {
            return new zzdi(Objects.requireNonNull(objArr[0]));
        }
        if (zzf(i5) < zzf / 2) {
            return zzl(i5, objArr);
        }
        if (i5 < 3) {
            objArr = Arrays.copyOf(objArr, i5);
        }
        return new zzdh(objArr, i4, objArr2, i2, i5);
    }
}
