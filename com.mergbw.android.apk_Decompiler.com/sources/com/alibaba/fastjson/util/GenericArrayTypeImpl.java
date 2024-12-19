package com.alibaba.fastjson.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import okhttp3.HttpUrl;

public class GenericArrayTypeImpl implements GenericArrayType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Type genericComponentType;

    public GenericArrayTypeImpl(Type type) {
        this.genericComponentType = type;
    }

    public Type getGenericComponentType() {
        return this.genericComponentType;
    }

    public String toString() {
        Type genericComponentType2 = getGenericComponentType();
        StringBuilder sb = new StringBuilder();
        if (genericComponentType2 instanceof Class) {
            sb.append(((Class) genericComponentType2).getName());
        } else {
            sb.append(genericComponentType2.toString());
        }
        sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof GenericArrayType) {
            return this.genericComponentType.equals(((GenericArrayType) obj).getGenericComponentType());
        }
        return false;
    }

    public int hashCode() {
        return this.genericComponentType.hashCode();
    }
}
