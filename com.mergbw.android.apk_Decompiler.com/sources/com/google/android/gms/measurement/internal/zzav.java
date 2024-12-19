package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.internal.zzin;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzav {
    private static final zzav zza = new zzav((Boolean) null, 100);
    private final int zzb;
    private final String zzc;
    private final Boolean zzd;
    private final String zze;
    private final EnumMap<zzin.zza, zzim> zzf;

    public final int zza() {
        return this.zzb;
    }

    public final int hashCode() {
        int i;
        int i2;
        Boolean bool = this.zzd;
        if (bool == null) {
            i = 3;
        } else {
            i = bool == Boolean.TRUE ? 7 : 13;
        }
        String str = this.zze;
        if (str == null) {
            i2 = 17;
        } else {
            i2 = str.hashCode();
        }
        return this.zzc.hashCode() + (i * 29) + (i2 * 137);
    }

    public final Bundle zzb() {
        Bundle bundle = new Bundle();
        for (Map.Entry next : this.zzf.entrySet()) {
            String zzb2 = zzin.zzb((zzim) next.getValue());
            if (zzb2 != null) {
                bundle.putString(((zzin.zza) next.getKey()).zze, zzb2);
            }
        }
        Boolean bool = this.zzd;
        if (bool != null) {
            bundle.putString("is_dma_region", bool.toString());
        }
        String str = this.zze;
        if (str != null) {
            bundle.putString("cps_display_str", str);
        }
        return bundle;
    }

    public static zzav zza(Bundle bundle, int i) {
        Boolean bool = null;
        if (bundle == null) {
            return new zzav((Boolean) null, i);
        }
        EnumMap enumMap = new EnumMap(zzin.zza.class);
        for (zzin.zza zza2 : zzio.DMA.zza()) {
            enumMap.put(zza2, zzin.zza(bundle.getString(zza2.zze)));
        }
        if (bundle.containsKey("is_dma_region")) {
            bool = Boolean.valueOf(bundle.getString("is_dma_region"));
        }
        return new zzav((EnumMap<zzin.zza, zzim>) enumMap, i, bool, bundle.getString("cps_display_str"));
    }

    static zzav zza(zzim zzim, int i) {
        EnumMap enumMap = new EnumMap(zzin.zza.class);
        enumMap.put(zzin.zza.AD_USER_DATA, zzim);
        return new zzav((EnumMap<zzin.zza, zzim>) enumMap, -10, (Boolean) null, (String) null);
    }

    public static zzav zza(String str) {
        if (str == null || str.length() <= 0) {
            return zza;
        }
        String[] split = str.split(":");
        int parseInt = Integer.parseInt(split[0]);
        EnumMap enumMap = new EnumMap(zzin.zza.class);
        zzin.zza[] zza2 = zzio.DMA.zza();
        int length = zza2.length;
        int i = 1;
        int i2 = 0;
        while (i2 < length) {
            enumMap.put(zza2[i2], zzin.zza(split[i].charAt(0)));
            i2++;
            i++;
        }
        return new zzav((EnumMap<zzin.zza, zzim>) enumMap, parseInt, (Boolean) null, (String) null);
    }

    public final zzim zzc() {
        zzim zzim = this.zzf.get(zzin.zza.AD_USER_DATA);
        return zzim == null ? zzim.UNINITIALIZED : zzim;
    }

    public static Boolean zza(Bundle bundle) {
        zzim zza2;
        if (bundle == null || (zza2 = zzin.zza(bundle.getString("ad_personalization"))) == null) {
            return null;
        }
        int i = zzay.zza[zza2.ordinal()];
        if (i == 3) {
            return false;
        }
        if (i != 4) {
            return null;
        }
        return true;
    }

    public final Boolean zzd() {
        return this.zzd;
    }

    private final String zzh() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.zzb);
        for (zzin.zza zza2 : zzio.DMA.zza()) {
            sb.append(":");
            sb.append(zzin.zza(this.zzf.get(zza2)));
        }
        return sb.toString();
    }

    public final String zze() {
        return this.zze;
    }

    public final String zzf() {
        return this.zzc;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("source=");
        sb.append(zzin.zza(this.zzb));
        for (zzin.zza zza2 : zzio.DMA.zza()) {
            sb.append(",");
            sb.append(zza2.zze);
            sb.append("=");
            zzim zzim = this.zzf.get(zza2);
            if (zzim == null) {
                sb.append("uninitialized");
            } else {
                int i = zzay.zza[zzim.ordinal()];
                if (i == 1) {
                    sb.append("uninitialized");
                } else if (i == 2) {
                    sb.append("eu_consent_policy");
                } else if (i == 3) {
                    sb.append("denied");
                } else if (i == 4) {
                    sb.append("granted");
                }
            }
        }
        if (this.zzd != null) {
            sb.append(",isDmaRegion=");
            sb.append(this.zzd);
        }
        if (this.zze != null) {
            sb.append(",cpsDisplayStr=");
            sb.append(this.zze);
        }
        return sb.toString();
    }

    zzav(Boolean bool, int i) {
        this(bool, i, (Boolean) null, (String) null);
    }

    zzav(Boolean bool, int i, Boolean bool2, String str) {
        EnumMap<zzin.zza, zzim> enumMap = new EnumMap<>(zzin.zza.class);
        this.zzf = enumMap;
        enumMap.put(zzin.zza.AD_USER_DATA, zzin.zza(bool));
        this.zzb = i;
        this.zzc = zzh();
        this.zzd = bool2;
        this.zze = str;
    }

    private zzav(EnumMap<zzin.zza, zzim> enumMap, int i, Boolean bool, String str) {
        EnumMap<zzin.zza, zzim> enumMap2 = new EnumMap<>(zzin.zza.class);
        this.zzf = enumMap2;
        enumMap2.putAll(enumMap);
        this.zzb = i;
        this.zzc = zzh();
        this.zzd = bool;
        this.zze = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzav)) {
            return false;
        }
        zzav zzav = (zzav) obj;
        if (this.zzc.equalsIgnoreCase(zzav.zzc) && Objects.equals(this.zzd, zzav.zzd)) {
            return Objects.equals(this.zze, zzav.zze);
        }
        return false;
    }

    public final boolean zzg() {
        for (zzim zzim : this.zzf.values()) {
            if (zzim != zzim.UNINITIALIZED) {
                return true;
            }
        }
        return false;
    }
}
