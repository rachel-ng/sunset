package com.alibaba.fastjson.serializer;

import com.bumptech.glide.disklrucache.DiskLruCache$$ExternalSyntheticApiModelOutline0;
import java.io.IOException;
import java.lang.reflect.Type;

public class AdderSerializer implements ObjectSerializer {
    public static final AdderSerializer instance = new AdderSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (DiskLruCache$$ExternalSyntheticApiModelOutline0.m$4(obj)) {
            serializeWriter.writeFieldValue('{', "value", DiskLruCache$$ExternalSyntheticApiModelOutline0.m(obj).longValue());
            serializeWriter.write(125);
        } else if (DiskLruCache$$ExternalSyntheticApiModelOutline0.m$5(obj)) {
            serializeWriter.writeFieldValue('{', "value", DiskLruCache$$ExternalSyntheticApiModelOutline0.m(obj).doubleValue());
            serializeWriter.write(125);
        }
    }
}
