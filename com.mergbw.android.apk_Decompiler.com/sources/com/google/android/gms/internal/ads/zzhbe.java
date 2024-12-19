package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public enum zzhbe {
    DOUBLE(0, 1, zzhcf.DOUBLE),
    FLOAT(1, 1, zzhcf.FLOAT),
    INT64(2, 1, zzhcf.LONG),
    UINT64(3, 1, zzhcf.LONG),
    INT32(4, 1, zzhcf.INT),
    FIXED64(5, 1, zzhcf.LONG),
    FIXED32(6, 1, zzhcf.INT),
    BOOL(7, 1, zzhcf.BOOLEAN),
    STRING(8, 1, zzhcf.STRING),
    MESSAGE(9, 1, zzhcf.MESSAGE),
    BYTES(10, 1, zzhcf.BYTE_STRING),
    UINT32(11, 1, zzhcf.INT),
    ENUM(12, 1, zzhcf.ENUM),
    SFIXED32(13, 1, zzhcf.INT),
    SFIXED64(14, 1, zzhcf.LONG),
    SINT32(15, 1, zzhcf.INT),
    SINT64(16, 1, zzhcf.LONG),
    GROUP(17, 1, zzhcf.MESSAGE),
    DOUBLE_LIST(18, 2, zzhcf.DOUBLE),
    FLOAT_LIST(19, 2, zzhcf.FLOAT),
    INT64_LIST(20, 2, zzhcf.LONG),
    UINT64_LIST(21, 2, zzhcf.LONG),
    INT32_LIST(22, 2, zzhcf.INT),
    FIXED64_LIST(23, 2, zzhcf.LONG),
    FIXED32_LIST(24, 2, zzhcf.INT),
    BOOL_LIST(25, 2, zzhcf.BOOLEAN),
    STRING_LIST(26, 2, zzhcf.STRING),
    MESSAGE_LIST(27, 2, zzhcf.MESSAGE),
    BYTES_LIST(28, 2, zzhcf.BYTE_STRING),
    UINT32_LIST(29, 2, zzhcf.INT),
    ENUM_LIST(30, 2, zzhcf.ENUM),
    SFIXED32_LIST(31, 2, zzhcf.INT),
    SFIXED64_LIST(32, 2, zzhcf.LONG),
    SINT32_LIST(33, 2, zzhcf.INT),
    SINT64_LIST(34, 2, zzhcf.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzhcf.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzhcf.FLOAT),
    INT64_LIST_PACKED(37, 3, zzhcf.LONG),
    UINT64_LIST_PACKED(38, 3, zzhcf.LONG),
    INT32_LIST_PACKED(39, 3, zzhcf.INT),
    FIXED64_LIST_PACKED(40, 3, zzhcf.LONG),
    FIXED32_LIST_PACKED(41, 3, zzhcf.INT),
    BOOL_LIST_PACKED(42, 3, zzhcf.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzhcf.INT),
    ENUM_LIST_PACKED(44, 3, zzhcf.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzhcf.INT),
    SFIXED64_LIST_PACKED(46, 3, zzhcf.LONG),
    SINT32_LIST_PACKED(47, 3, zzhcf.INT),
    SINT64_LIST_PACKED(48, 3, zzhcf.LONG),
    GROUP_LIST(49, 2, zzhcf.MESSAGE),
    MAP(50, 4, zzhcf.VOID);
    
    private static final zzhbe[] zzZ = null;
    private final zzhcf zzab;
    private final int zzac;
    private final Class zzad;

    static {
        zzZ = new zzhbe[r1];
        for (zzhbe zzhbe : values()) {
            zzZ[zzhbe.zzac] = zzhbe;
        }
    }

    private zzhbe(int i, int i2, zzhcf zzhcf) {
        this.zzac = i;
        this.zzab = zzhcf;
        int i3 = i2 - 1;
        if (i3 == 1) {
            this.zzad = zzhcf.zza();
        } else if (i3 != 3) {
            this.zzad = null;
        } else {
            this.zzad = zzhcf.zza();
        }
        if (i2 == 1) {
            zzhcf zzhcf2 = zzhcf.VOID;
            zzhcf.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
