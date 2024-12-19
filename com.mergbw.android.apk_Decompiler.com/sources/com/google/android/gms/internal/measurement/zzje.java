package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
public enum zzje {
    DOUBLE(0, zzjg.SCALAR, zzju.DOUBLE),
    FLOAT(1, zzjg.SCALAR, zzju.FLOAT),
    INT64(2, zzjg.SCALAR, zzju.LONG),
    UINT64(3, zzjg.SCALAR, zzju.LONG),
    INT32(4, zzjg.SCALAR, zzju.INT),
    FIXED64(5, zzjg.SCALAR, zzju.LONG),
    FIXED32(6, zzjg.SCALAR, zzju.INT),
    BOOL(7, zzjg.SCALAR, zzju.BOOLEAN),
    STRING(8, zzjg.SCALAR, zzju.STRING),
    MESSAGE(9, zzjg.SCALAR, zzju.MESSAGE),
    BYTES(10, zzjg.SCALAR, zzju.BYTE_STRING),
    UINT32(11, zzjg.SCALAR, zzju.INT),
    ENUM(12, zzjg.SCALAR, zzju.ENUM),
    SFIXED32(13, zzjg.SCALAR, zzju.INT),
    SFIXED64(14, zzjg.SCALAR, zzju.LONG),
    SINT32(15, zzjg.SCALAR, zzju.INT),
    SINT64(16, zzjg.SCALAR, zzju.LONG),
    GROUP(17, zzjg.SCALAR, zzju.MESSAGE),
    DOUBLE_LIST(18, zzjg.VECTOR, zzju.DOUBLE),
    FLOAT_LIST(19, zzjg.VECTOR, zzju.FLOAT),
    INT64_LIST(20, zzjg.VECTOR, zzju.LONG),
    UINT64_LIST(21, zzjg.VECTOR, zzju.LONG),
    INT32_LIST(22, zzjg.VECTOR, zzju.INT),
    FIXED64_LIST(23, zzjg.VECTOR, zzju.LONG),
    FIXED32_LIST(24, zzjg.VECTOR, zzju.INT),
    BOOL_LIST(25, zzjg.VECTOR, zzju.BOOLEAN),
    STRING_LIST(26, zzjg.VECTOR, zzju.STRING),
    MESSAGE_LIST(27, zzjg.VECTOR, zzju.MESSAGE),
    BYTES_LIST(28, zzjg.VECTOR, zzju.BYTE_STRING),
    UINT32_LIST(29, zzjg.VECTOR, zzju.INT),
    ENUM_LIST(30, zzjg.VECTOR, zzju.ENUM),
    SFIXED32_LIST(31, zzjg.VECTOR, zzju.INT),
    SFIXED64_LIST(32, zzjg.VECTOR, zzju.LONG),
    SINT32_LIST(33, zzjg.VECTOR, zzju.INT),
    SINT64_LIST(34, zzjg.VECTOR, zzju.LONG),
    DOUBLE_LIST_PACKED(35, zzjg.PACKED_VECTOR, zzju.DOUBLE),
    FLOAT_LIST_PACKED(36, zzjg.PACKED_VECTOR, zzju.FLOAT),
    INT64_LIST_PACKED(37, zzjg.PACKED_VECTOR, zzju.LONG),
    UINT64_LIST_PACKED(38, zzjg.PACKED_VECTOR, zzju.LONG),
    INT32_LIST_PACKED(39, zzjg.PACKED_VECTOR, zzju.INT),
    FIXED64_LIST_PACKED(40, zzjg.PACKED_VECTOR, zzju.LONG),
    FIXED32_LIST_PACKED(41, zzjg.PACKED_VECTOR, zzju.INT),
    BOOL_LIST_PACKED(42, zzjg.PACKED_VECTOR, zzju.BOOLEAN),
    UINT32_LIST_PACKED(43, zzjg.PACKED_VECTOR, zzju.INT),
    ENUM_LIST_PACKED(44, zzjg.PACKED_VECTOR, zzju.ENUM),
    SFIXED32_LIST_PACKED(45, zzjg.PACKED_VECTOR, zzju.INT),
    SFIXED64_LIST_PACKED(46, zzjg.PACKED_VECTOR, zzju.LONG),
    SINT32_LIST_PACKED(47, zzjg.PACKED_VECTOR, zzju.INT),
    SINT64_LIST_PACKED(48, zzjg.PACKED_VECTOR, zzju.LONG),
    GROUP_LIST(49, zzjg.VECTOR, zzju.MESSAGE),
    MAP(50, zzjg.MAP, zzju.VOID);
    
    private static final zzje[] zzaz = null;
    private final int zzbb;

    public final int zza() {
        return this.zzbb;
    }

    static {
        zzje[] values = values();
        zzaz = new zzje[values.length];
        for (zzje zzje : values) {
            zzaz[zzje.zzbb] = zzje;
        }
    }

    private zzje(int i, zzjg zzjg, zzju zzju) {
        this.zzbb = i;
        int ordinal = zzjg.ordinal();
        if (ordinal == 1) {
            zzju.zza();
        } else if (ordinal == 3) {
            zzju.zza();
        }
        if (zzjg == zzjg.SCALAR) {
            int i2 = zzjh.zza[zzju.ordinal()];
        }
    }
}
