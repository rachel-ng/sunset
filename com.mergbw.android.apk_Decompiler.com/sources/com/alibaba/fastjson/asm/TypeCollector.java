package com.alibaba.fastjson.asm;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.ASMUtils;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import okhttp3.HttpUrl;

public class TypeCollector {
    private static String JSONType = ASMUtils.desc((Class<?>) JSONType.class);
    private static final Map<String, String> primitives = new HashMap<String, String>() {
        {
            put("int", "I");
            put(TypedValues.Custom.S_BOOLEAN, "Z");
            put("byte", "B");
            put("char", "C");
            put("short", ExifInterface.LATITUDE_SOUTH);
            put(TypedValues.Custom.S_FLOAT, "F");
            put("long", "J");
            put("double", "D");
        }
    };
    protected MethodCollector collector = null;
    protected boolean jsonType;
    private final String methodName;
    private final Class<?>[] parameterTypes;

    public TypeCollector(String str, Class<?>[] clsArr) {
        this.methodName = str;
        this.parameterTypes = clsArr;
    }

    /* access modifiers changed from: protected */
    public MethodCollector visitMethod(int i, String str, String str2) {
        if (this.collector != null || !str.equals(this.methodName)) {
            return null;
        }
        Type[] argumentTypes = Type.getArgumentTypes(str2);
        int i2 = 0;
        for (Type className : argumentTypes) {
            String className2 = className.getClassName();
            if (className2.equals("long") || className2.equals("double")) {
                i2++;
            }
        }
        if (argumentTypes.length != this.parameterTypes.length) {
            return null;
        }
        for (int i3 = 0; i3 < argumentTypes.length; i3++) {
            if (!correctTypeName(argumentTypes[i3], this.parameterTypes[i3].getName())) {
                return null;
            }
        }
        MethodCollector methodCollector = new MethodCollector(Modifier.isStatic(i) ^ true ? 1 : 0, argumentTypes.length + i2);
        this.collector = methodCollector;
        return methodCollector;
    }

    public void visitAnnotation(String str) {
        if (JSONType.equals(str)) {
            this.jsonType = true;
        }
    }

    private boolean correctTypeName(Type type, String str) {
        String className = type.getClassName();
        StringBuilder sb = new StringBuilder();
        while (className.endsWith(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI)) {
            sb.append('[');
            className = className.substring(0, className.length() - 2);
        }
        if (sb.length() != 0) {
            Map<String, String> map = primitives;
            if (map.containsKey(className)) {
                sb.append(map.get(className));
                className = sb.toString();
            } else {
                sb.append('L');
                sb.append(className);
                sb.append(';');
                className = sb.toString();
            }
        }
        return className.equals(str);
    }

    public String[] getParameterNamesForMethod() {
        MethodCollector methodCollector = this.collector;
        if (methodCollector == null || !methodCollector.debugInfoPresent) {
            return new String[0];
        }
        return this.collector.getResult().split(",");
    }

    public boolean matched() {
        return this.collector != null;
    }

    public boolean hasJsonType() {
        return this.jsonType;
    }
}
