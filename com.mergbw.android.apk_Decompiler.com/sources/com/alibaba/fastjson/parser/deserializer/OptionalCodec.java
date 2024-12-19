package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.util.TypeUtils;
import com.bumptech.glide.disklrucache.DiskLruCache$$ExternalSyntheticApiModelOutline0;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class OptionalCodec implements ObjectSerializer, ObjectDeserializer {
    public static OptionalCodec instance = new OptionalCodec();

    public int getFastMatchToken() {
        return 12;
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (type == DiskLruCache$$ExternalSyntheticApiModelOutline0.m$1()) {
            Integer castToInt = TypeUtils.castToInt(defaultJSONParser.parseObject(Integer.class));
            if (castToInt == null) {
                return DiskLruCache$$ExternalSyntheticApiModelOutline0.m();
            }
            return DiskLruCache$$ExternalSyntheticApiModelOutline0.m(castToInt.intValue());
        } else if (type == DiskLruCache$$ExternalSyntheticApiModelOutline0.m$2()) {
            Long castToLong = TypeUtils.castToLong(defaultJSONParser.parseObject(Long.class));
            if (castToLong == null) {
                return DiskLruCache$$ExternalSyntheticApiModelOutline0.m();
            }
            return OptionalLong.of(castToLong.longValue());
        } else if (type == DiskLruCache$$ExternalSyntheticApiModelOutline0.m$3()) {
            Double castToDouble = TypeUtils.castToDouble(defaultJSONParser.parseObject(Double.class));
            if (castToDouble == null) {
                return DiskLruCache$$ExternalSyntheticApiModelOutline0.m();
            }
            return OptionalDouble.of(castToDouble.doubleValue());
        } else {
            Object parseObject = defaultJSONParser.parseObject(TypeUtils.unwrapOptional(type));
            if (parseObject == null) {
                return DiskLruCache$$ExternalSyntheticApiModelOutline0.m();
            }
            return DiskLruCache$$ExternalSyntheticApiModelOutline0.m(parseObject);
        }
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        if (obj == null) {
            jSONSerializer.writeNull();
        } else if (DiskLruCache$$ExternalSyntheticApiModelOutline0.m$2(obj)) {
            Optional m$1 = DiskLruCache$$ExternalSyntheticApiModelOutline0.m$1(obj);
            jSONSerializer.write(DiskLruCache$$ExternalSyntheticApiModelOutline0.m(m$1) ? DiskLruCache$$ExternalSyntheticApiModelOutline0.m(m$1) : null);
        } else if (DiskLruCache$$ExternalSyntheticApiModelOutline0.m(obj)) {
            OptionalDouble m = DiskLruCache$$ExternalSyntheticApiModelOutline0.m(obj);
            if (DiskLruCache$$ExternalSyntheticApiModelOutline0.m(m)) {
                jSONSerializer.write((Object) Double.valueOf(DiskLruCache$$ExternalSyntheticApiModelOutline0.m(m)));
            } else {
                jSONSerializer.writeNull();
            }
        } else if (DiskLruCache$$ExternalSyntheticApiModelOutline0.m$1(obj)) {
            OptionalInt m2 = DiskLruCache$$ExternalSyntheticApiModelOutline0.m(obj);
            if (DiskLruCache$$ExternalSyntheticApiModelOutline0.m(m2)) {
                jSONSerializer.out.writeInt(DiskLruCache$$ExternalSyntheticApiModelOutline0.m(m2));
            } else {
                jSONSerializer.writeNull();
            }
        } else if (DiskLruCache$$ExternalSyntheticApiModelOutline0.m$3(obj)) {
            OptionalLong m3 = DiskLruCache$$ExternalSyntheticApiModelOutline0.m(obj);
            if (DiskLruCache$$ExternalSyntheticApiModelOutline0.m(m3)) {
                jSONSerializer.out.writeLong(DiskLruCache$$ExternalSyntheticApiModelOutline0.m(m3));
            } else {
                jSONSerializer.writeNull();
            }
        } else {
            throw new JSONException("not support optional : " + obj.getClass());
        }
    }
}
