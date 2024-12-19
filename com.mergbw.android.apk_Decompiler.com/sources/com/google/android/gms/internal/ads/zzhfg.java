package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public enum zzhfg {
    DOUBLE(zzhfh.DOUBLE, 1),
    FLOAT(zzhfh.FLOAT, 5),
    INT64(zzhfh.LONG, 0),
    UINT64(zzhfh.LONG, 0),
    INT32(zzhfh.INT, 0),
    FIXED64(zzhfh.LONG, 1),
    FIXED32(zzhfh.INT, 5),
    BOOL(zzhfh.BOOLEAN, 0),
    STRING(zzhfh.STRING, 2),
    GROUP(zzhfh.MESSAGE, 3),
    MESSAGE(zzhfh.MESSAGE, 2),
    BYTES(zzhfh.BYTE_STRING, 2),
    UINT32(zzhfh.INT, 0),
    ENUM(zzhfh.ENUM, 0),
    SFIXED32(zzhfh.INT, 5),
    SFIXED64(zzhfh.LONG, 1),
    SINT32(zzhfh.INT, 0),
    SINT64(zzhfh.LONG, 0);
    
    private final zzhfh zzt;

    private zzhfg(zzhfh zzhfh, int i) {
        this.zzt = zzhfh;
    }

    public final zzhfh zza() {
        return this.zzt;
    }
}
