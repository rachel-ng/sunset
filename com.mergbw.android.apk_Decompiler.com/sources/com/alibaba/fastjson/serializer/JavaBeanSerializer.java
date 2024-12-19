package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JavaBeanSerializer extends SerializeFilterable implements ObjectSerializer {
    protected SerializeBeanInfo beanInfo;
    protected final FieldSerializer[] getters;
    private volatile transient long[] hashArray;
    private volatile transient short[] hashArrayMapping;
    protected final FieldSerializer[] sortedGetters;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JavaBeanSerializer(Class<?> cls) {
        this(cls, (Map<String, String>) null);
        Map map = null;
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, createAliasMap(strArr));
    }

    static Map<String, String> createAliasMap(String... strArr) {
        HashMap hashMap = new HashMap();
        for (String str : strArr) {
            hashMap.put(str, str);
        }
        return hashMap;
    }

    public Class<?> getType() {
        return this.beanInfo.beanType;
    }

    public JavaBeanSerializer(Class<?> cls, Map<String, String> map) {
        this(TypeUtils.buildBeanInfo(cls, map, (PropertyNamingStrategy) null));
    }

    public JavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) {
        this.beanInfo = serializeBeanInfo;
        this.sortedGetters = new FieldSerializer[serializeBeanInfo.sortedFields.length];
        int i = 0;
        while (true) {
            FieldSerializer[] fieldSerializerArr = this.sortedGetters;
            if (i >= fieldSerializerArr.length) {
                break;
            }
            fieldSerializerArr[i] = new FieldSerializer(serializeBeanInfo.beanType, serializeBeanInfo.sortedFields[i]);
            i++;
        }
        if (serializeBeanInfo.fields == serializeBeanInfo.sortedFields) {
            this.getters = this.sortedGetters;
        } else {
            this.getters = new FieldSerializer[serializeBeanInfo.fields.length];
            int i2 = 0;
            while (true) {
                if (i2 >= this.getters.length) {
                    break;
                }
                FieldSerializer fieldSerializer = getFieldSerializer(serializeBeanInfo.fields[i2].name);
                if (fieldSerializer == null) {
                    FieldSerializer[] fieldSerializerArr2 = this.sortedGetters;
                    System.arraycopy(fieldSerializerArr2, 0, this.getters, 0, fieldSerializerArr2.length);
                    break;
                }
                this.getters[i2] = fieldSerializer;
                i2++;
            }
        }
        if (serializeBeanInfo.jsonType != null) {
            for (Class constructor : serializeBeanInfo.jsonType.serialzeFilters()) {
                try {
                    addFilter((SerializeFilter) constructor.getConstructor((Class[]) null).newInstance((Object[]) null));
                } catch (Exception unused) {
                }
            }
        }
    }

    public void writeDirectNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArray(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArrayNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    public void writeNoneASM(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v46, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v47, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v48, resolved type: java.lang.Exception} */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.Throwable] */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0137, code lost:
        if (r9.isWriteClassName(r12, r10) != false) goto L_0x0157;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005d, code lost:
        r1 = r0;
        r2 = r7;
        r4 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x03a0, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:375:0x04c8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:0x04ca, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:377:0x04cb, code lost:
        r31 = r7;
        r4 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x04d3, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x04d4, code lost:
        r25 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:381:0x04d6, code lost:
        r31 = r7;
        r4 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:395:0x0509, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:407:0x0521, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:408:0x0522, code lost:
        r25 = r1;
        r2 = r7;
        r4 = r9;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:411:0x052b, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:412:0x052c, code lost:
        r2 = r7;
        r4 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:420:?, code lost:
        r5 = r5 + ", class " + r34.getClass().getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:424:?, code lost:
        r5 = r5 + ", fieldName : " + r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:428:0x0577, code lost:
        r6 = r12.fieldInfo;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:429:0x057b, code lost:
        if (r6.method != null) goto L_0x057d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:430:0x057d, code lost:
        r5 = r5 + ", method : " + r6.method.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:431:0x0598, code lost:
        r5 = r5 + ", fieldName : " + r12.fieldInfo.name;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:434:0x05b4, code lost:
        r5 = r5 + ", " + r3.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:437:0x05d0, code lost:
        r17 = r3.getCause();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:440:0x05d7, code lost:
        r3 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:443:0x05df, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0092, code lost:
        if (r9.isWriteClassName(r12, r10) == false) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0100, code lost:
        if (r13.fieldTransient != false) goto L_0x0157;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0103, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0104, code lost:
        r3 = r0;
        r2 = r7;
        r4 = r9;
        r1 = r10;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x013f A[SYNTHETIC, Splitter:B:104:0x013f] */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0162 A[Catch:{ Exception -> 0x04c8, all -> 0x04ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005c A[ExcHandler: all (r0v26 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r7 r9 
      PHI: (r7v31 com.alibaba.fastjson.serializer.SerialContext) = (r7v4 com.alibaba.fastjson.serializer.SerialContext), (r7v4 com.alibaba.fastjson.serializer.SerialContext), (r7v4 com.alibaba.fastjson.serializer.SerialContext), (r7v4 com.alibaba.fastjson.serializer.SerialContext), (r7v4 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext) binds: [B:119:0x0168, B:97:0x0129, B:88:0x0115, B:78:0x00fe, B:79:?, B:48:0x009a, B:40:0x0087, B:32:0x006c, B:23:0x0058, B:24:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r9v15 com.alibaba.fastjson.serializer.JSONSerializer) = (r9v2 com.alibaba.fastjson.serializer.JSONSerializer), (r9v2 com.alibaba.fastjson.serializer.JSONSerializer), (r9v2 com.alibaba.fastjson.serializer.JSONSerializer), (r9v2 com.alibaba.fastjson.serializer.JSONSerializer), (r9v2 com.alibaba.fastjson.serializer.JSONSerializer), (r9v0 com.alibaba.fastjson.serializer.JSONSerializer), (r9v0 com.alibaba.fastjson.serializer.JSONSerializer), (r9v0 com.alibaba.fastjson.serializer.JSONSerializer), (r9v0 com.alibaba.fastjson.serializer.JSONSerializer), (r9v0 com.alibaba.fastjson.serializer.JSONSerializer) binds: [B:119:0x0168, B:97:0x0129, B:88:0x0115, B:78:0x00fe, B:79:?, B:48:0x009a, B:40:0x0087, B:32:0x006c, B:23:0x0058, B:24:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:23:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x03f7 A[Catch:{ Exception -> 0x03a0, all -> 0x0509 }] */
    /* JADX WARNING: Removed duplicated region for block: B:347:0x0468 A[Catch:{ Exception -> 0x03a0, all -> 0x0509 }] */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x047b A[Catch:{ Exception -> 0x03a0, all -> 0x0509 }] */
    /* JADX WARNING: Removed duplicated region for block: B:355:0x047c A[Catch:{ Exception -> 0x03a0, all -> 0x0509 }] */
    /* JADX WARNING: Removed duplicated region for block: B:376:0x04ca A[ExcHandler: all (th java.lang.Throwable), Splitter:B:67:0x00d3] */
    /* JADX WARNING: Removed duplicated region for block: B:387:0x04f1  */
    /* JADX WARNING: Removed duplicated region for block: B:391:0x04fa A[SYNTHETIC, Splitter:B:391:0x04fa] */
    /* JADX WARNING: Removed duplicated region for block: B:395:0x0509 A[Catch:{ Exception -> 0x050b, all -> 0x0509 }, ExcHandler: all (th java.lang.Throwable), PHI: r4 r31 
      PHI: (r4v11 com.alibaba.fastjson.serializer.JSONSerializer) = (r4v9 com.alibaba.fastjson.serializer.JSONSerializer), (r4v30 com.alibaba.fastjson.serializer.JSONSerializer), (r4v105 com.alibaba.fastjson.serializer.JSONSerializer), (r4v105 com.alibaba.fastjson.serializer.JSONSerializer) binds: [B:391:0x04fa, B:288:0x0399, B:368:0x04a7, B:369:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r31v2 com.alibaba.fastjson.serializer.SerialContext) = (r31v0 com.alibaba.fastjson.serializer.SerialContext), (r31v11 com.alibaba.fastjson.serializer.SerialContext), (r31v14 com.alibaba.fastjson.serializer.SerialContext), (r31v14 com.alibaba.fastjson.serializer.SerialContext) binds: [B:391:0x04fa, B:288:0x0399, B:368:0x04a7, B:369:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:288:0x0399] */
    /* JADX WARNING: Removed duplicated region for block: B:400:0x050f A[Catch:{ Exception -> 0x050b, all -> 0x0509 }] */
    /* JADX WARNING: Removed duplicated region for block: B:411:0x052b A[ExcHandler: all (th java.lang.Throwable), PHI: r7 r9 
      PHI: (r7v3 com.alibaba.fastjson.serializer.SerialContext) = (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v4 com.alibaba.fastjson.serializer.SerialContext), (r7v4 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext) binds: [B:29:0x0069, B:30:?, B:36:0x007a, B:37:?, B:57:0x00b0, B:64:0x00d0, B:65:?, B:45:0x0094] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r9v1 com.alibaba.fastjson.serializer.JSONSerializer) = (r9v0 com.alibaba.fastjson.serializer.JSONSerializer), (r9v0 com.alibaba.fastjson.serializer.JSONSerializer), (r9v0 com.alibaba.fastjson.serializer.JSONSerializer), (r9v0 com.alibaba.fastjson.serializer.JSONSerializer), (r9v0 com.alibaba.fastjson.serializer.JSONSerializer), (r9v2 com.alibaba.fastjson.serializer.JSONSerializer), (r9v2 com.alibaba.fastjson.serializer.JSONSerializer), (r9v0 com.alibaba.fastjson.serializer.JSONSerializer) binds: [B:29:0x0069, B:30:?, B:36:0x007a, B:37:?, B:57:0x00b0, B:64:0x00d0, B:65:?, B:45:0x0094] A[DONT_GENERATE, DONT_INLINE], Splitter:B:29:0x0069] */
    /* JADX WARNING: Removed duplicated region for block: B:419:0x053c A[SYNTHETIC, Splitter:B:419:0x053c] */
    /* JADX WARNING: Removed duplicated region for block: B:423:0x055e A[SYNTHETIC, Splitter:B:423:0x055e] */
    /* JADX WARNING: Removed duplicated region for block: B:425:0x0571 A[ADDED_TO_REGION, Catch:{ all -> 0x05df }] */
    /* JADX WARNING: Removed duplicated region for block: B:434:0x05b4 A[Catch:{ all -> 0x05df }] */
    /* JADX WARNING: Removed duplicated region for block: B:437:0x05d0 A[Catch:{ all -> 0x05df }] */
    /* JADX WARNING: Removed duplicated region for block: B:439:0x05d6 A[Catch:{ all -> 0x05df }] */
    /* JADX WARNING: Removed duplicated region for block: B:440:0x05d7 A[Catch:{ all -> 0x05df }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a6 A[Catch:{ Exception -> 0x0062, all -> 0x005c }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00bc A[Catch:{ Exception -> 0x0531, all -> 0x052b }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00be A[Catch:{ Exception -> 0x0531, all -> 0x052b }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00d3 A[SYNTHETIC, Splitter:B:67:0x00d3] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0129 A[SYNTHETIC, Splitter:B:97:0x0129] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(com.alibaba.fastjson.serializer.JSONSerializer r33, java.lang.Object r34, java.lang.Object r35, java.lang.reflect.Type r36, int r37, boolean r38) throws java.io.IOException {
        /*
            r32 = this;
            r8 = r32
            r9 = r33
            r10 = r34
            r11 = r35
            r12 = r36
            r13 = r37
            com.alibaba.fastjson.serializer.SerializeWriter r14 = r9.out
            if (r10 != 0) goto L_0x0014
            r14.writeNull()
            return
        L_0x0014:
            boolean r1 = r8.writeReference(r9, r10, r13)
            if (r1 == 0) goto L_0x001b
            return
        L_0x001b:
            boolean r1 = r14.sortField
            if (r1 == 0) goto L_0x0022
            com.alibaba.fastjson.serializer.FieldSerializer[] r1 = r8.sortedGetters
            goto L_0x0024
        L_0x0022:
            com.alibaba.fastjson.serializer.FieldSerializer[] r1 = r8.getters
        L_0x0024:
            r15 = r1
            com.alibaba.fastjson.serializer.SerialContext r7 = r9.context
            com.alibaba.fastjson.serializer.SerializeBeanInfo r1 = r8.beanInfo
            java.lang.Class<?> r1 = r1.beanType
            boolean r1 = r1.isEnum()
            if (r1 != 0) goto L_0x0041
            com.alibaba.fastjson.serializer.SerializeBeanInfo r1 = r8.beanInfo
            int r5 = r1.features
            r1 = r33
            r2 = r7
            r3 = r34
            r4 = r35
            r6 = r37
            r1.setContext(r2, r3, r4, r5, r6)
        L_0x0041:
            boolean r16 = r8.isWriteAsArray(r9, r13)
            if (r16 == 0) goto L_0x004a
            r1 = 91
            goto L_0x004c
        L_0x004a:
            r1 = 123(0x7b, float:1.72E-43)
        L_0x004c:
            if (r16 == 0) goto L_0x0051
            r2 = 93
            goto L_0x0053
        L_0x0051:
            r2 = 125(0x7d, float:1.75E-43)
        L_0x0053:
            r6 = r2
            r17 = 0
            if (r38 != 0) goto L_0x0069
            r14.append((char) r1)     // Catch:{ Exception -> 0x0062, all -> 0x005c }
            goto L_0x0069
        L_0x005c:
            r0 = move-exception
            r1 = r0
            r2 = r7
            r4 = r9
            goto L_0x05e2
        L_0x0062:
            r0 = move-exception
            r3 = r0
            r2 = r7
            r4 = r9
            r1 = r10
            goto L_0x0536
        L_0x0069:
            int r1 = r15.length     // Catch:{ Exception -> 0x0531, all -> 0x052b }
            if (r1 <= 0) goto L_0x007a
            com.alibaba.fastjson.serializer.SerializerFeature r1 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ Exception -> 0x0062, all -> 0x005c }
            boolean r1 = r14.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r1)     // Catch:{ Exception -> 0x0062, all -> 0x005c }
            if (r1 == 0) goto L_0x007a
            r33.incrementIndent()     // Catch:{ Exception -> 0x0062, all -> 0x005c }
            r33.println()     // Catch:{ Exception -> 0x0062, all -> 0x005c }
        L_0x007a:
            com.alibaba.fastjson.serializer.SerializeBeanInfo r1 = r8.beanInfo     // Catch:{ Exception -> 0x0531, all -> 0x052b }
            int r1 = r1.features     // Catch:{ Exception -> 0x0531, all -> 0x052b }
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ Exception -> 0x0531, all -> 0x052b }
            int r2 = r2.mask     // Catch:{ Exception -> 0x0531, all -> 0x052b }
            r1 = r1 & r2
            r5 = 44
            if (r1 != 0) goto L_0x0094
            com.alibaba.fastjson.serializer.SerializerFeature r1 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ Exception -> 0x0062, all -> 0x005c }
            int r1 = r1.mask     // Catch:{ Exception -> 0x0062, all -> 0x005c }
            r1 = r1 & r13
            if (r1 != 0) goto L_0x0094
            boolean r1 = r9.isWriteClassName(r12, r10)     // Catch:{ Exception -> 0x0062, all -> 0x005c }
            if (r1 == 0) goto L_0x00af
        L_0x0094:
            java.lang.Class r1 = r34.getClass()     // Catch:{ Exception -> 0x0531, all -> 0x052b }
            if (r1 == r12) goto L_0x00a3
            boolean r2 = r12 instanceof java.lang.reflect.WildcardType     // Catch:{ Exception -> 0x0062, all -> 0x005c }
            if (r2 == 0) goto L_0x00a3
            java.lang.Class r2 = com.alibaba.fastjson.util.TypeUtils.getClass(r36)     // Catch:{ Exception -> 0x0062, all -> 0x005c }
            goto L_0x00a4
        L_0x00a3:
            r2 = r12
        L_0x00a4:
            if (r1 == r2) goto L_0x00af
            com.alibaba.fastjson.serializer.SerializeBeanInfo r1 = r8.beanInfo     // Catch:{ Exception -> 0x0062, all -> 0x005c }
            java.lang.String r1 = r1.typeKey     // Catch:{ Exception -> 0x0062, all -> 0x005c }
            r8.writeClassName(r9, r1, r10)     // Catch:{ Exception -> 0x0062, all -> 0x005c }
            r1 = r5
            goto L_0x00b0
        L_0x00af:
            r1 = 0
        L_0x00b0:
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ Exception -> 0x0531, all -> 0x052b }
            boolean r18 = r14.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r2)     // Catch:{ Exception -> 0x0531, all -> 0x052b }
            char r1 = r8.writeBefore(r9, r10, r1)     // Catch:{ Exception -> 0x0531, all -> 0x052b }
            if (r1 != r5) goto L_0x00be
            r1 = 1
            goto L_0x00bf
        L_0x00be:
            r1 = 0
        L_0x00bf:
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.SkipTransientField     // Catch:{ Exception -> 0x0531, all -> 0x052b }
            boolean r19 = r14.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r2)     // Catch:{ Exception -> 0x0531, all -> 0x052b }
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.IgnoreNonFieldGetter     // Catch:{ Exception -> 0x0531, all -> 0x052b }
            boolean r20 = r14.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r2)     // Catch:{ Exception -> 0x0531, all -> 0x052b }
            r21 = r1
            r1 = r17
            r2 = 0
        L_0x00d0:
            int r3 = r15.length     // Catch:{ Exception -> 0x0521, all -> 0x052b }
            if (r2 >= r3) goto L_0x04e2
            r3 = r15[r2]     // Catch:{ Exception -> 0x04d3, all -> 0x04ca }
            com.alibaba.fastjson.util.FieldInfo r4 = r3.fieldInfo     // Catch:{ Exception -> 0x04d3, all -> 0x04ca }
            java.lang.reflect.Field r4 = r4.field     // Catch:{ Exception -> 0x04d3, all -> 0x04ca }
            com.alibaba.fastjson.util.FieldInfo r13 = r3.fieldInfo     // Catch:{ Exception -> 0x04d3, all -> 0x04ca }
            java.lang.String r11 = r13.name     // Catch:{ Exception -> 0x04d3, all -> 0x04ca }
            r23 = r15
            java.lang.Class<?> r15 = r13.fieldClass     // Catch:{ Exception -> 0x04d3, all -> 0x04ca }
            int r5 = r14.features     // Catch:{ Exception -> 0x04d3, all -> 0x04ca }
            r25 = r1
            int r1 = r13.serialzeFeatures     // Catch:{ Exception -> 0x04c8, all -> 0x04ca }
            r26 = r2
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.UseSingleQuotes     // Catch:{ Exception -> 0x04c8, all -> 0x04ca }
            boolean r27 = com.alibaba.fastjson.serializer.SerializerFeature.isEnabled(r5, r1, r2)     // Catch:{ Exception -> 0x04c8, all -> 0x04ca }
            boolean r1 = r14.quoteFieldNames     // Catch:{ Exception -> 0x04c8, all -> 0x04ca }
            if (r1 == 0) goto L_0x00f8
            if (r27 != 0) goto L_0x00f8
            r28 = 1
            goto L_0x00fa
        L_0x00f8:
            r28 = 0
        L_0x00fa:
            if (r19 == 0) goto L_0x010a
            if (r13 == 0) goto L_0x010a
            boolean r1 = r13.fieldTransient     // Catch:{ Exception -> 0x0103, all -> 0x005c }
            if (r1 == 0) goto L_0x010a
            goto L_0x010e
        L_0x0103:
            r0 = move-exception
            r3 = r0
            r2 = r7
            r4 = r9
            r1 = r10
            goto L_0x0528
        L_0x010a:
            if (r20 == 0) goto L_0x010f
            if (r4 != 0) goto L_0x010f
        L_0x010e:
            goto L_0x0157
        L_0x010f:
            boolean r1 = r8.applyName(r9, r10, r11)     // Catch:{ Exception -> 0x04c8, all -> 0x04ca }
            if (r1 == 0) goto L_0x0120
            java.lang.String r1 = r13.label     // Catch:{ Exception -> 0x0103, all -> 0x005c }
            boolean r1 = r8.applyLabel(r9, r1)     // Catch:{ Exception -> 0x0103, all -> 0x005c }
            if (r1 != 0) goto L_0x011e
            goto L_0x0120
        L_0x011e:
            r1 = 0
            goto L_0x0123
        L_0x0120:
            if (r16 == 0) goto L_0x0157
            r1 = 1
        L_0x0123:
            com.alibaba.fastjson.serializer.SerializeBeanInfo r2 = r8.beanInfo     // Catch:{ Exception -> 0x04c8, all -> 0x04ca }
            java.lang.String r2 = r2.typeKey     // Catch:{ Exception -> 0x04c8, all -> 0x04ca }
            if (r2 == 0) goto L_0x013a
            com.alibaba.fastjson.serializer.SerializeBeanInfo r2 = r8.beanInfo     // Catch:{ Exception -> 0x0103, all -> 0x005c }
            java.lang.String r2 = r2.typeKey     // Catch:{ Exception -> 0x0103, all -> 0x005c }
            boolean r2 = r11.equals(r2)     // Catch:{ Exception -> 0x0103, all -> 0x005c }
            if (r2 == 0) goto L_0x013a
            boolean r2 = r9.isWriteClassName(r12, r10)     // Catch:{ Exception -> 0x0103, all -> 0x005c }
            if (r2 == 0) goto L_0x013a
            goto L_0x010e
        L_0x013a:
            if (r1 == 0) goto L_0x013f
        L_0x013c:
            r1 = r17
            goto L_0x0151
        L_0x013f:
            java.lang.Object r1 = r3.getPropertyValueDirect(r10)     // Catch:{ InvocationTargetException -> 0x0144 }
            goto L_0x0151
        L_0x0144:
            r0 = move-exception
            r1 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.IgnoreErrorGetter     // Catch:{ Exception -> 0x04aa, all -> 0x04ca }
            boolean r2 = r14.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r2)     // Catch:{ Exception -> 0x04aa, all -> 0x04ca }
            if (r2 == 0) goto L_0x04a3
            r25 = r3
            goto L_0x013c
        L_0x0151:
            boolean r2 = r8.apply(r9, r10, r11, r1)     // Catch:{ Exception -> 0x04c8, all -> 0x04ca }
            if (r2 != 0) goto L_0x0162
        L_0x0157:
            r30 = r6
            r31 = r7
            r4 = r9
            r3 = 1
            r5 = 44
            r9 = 0
            goto L_0x04b3
        L_0x0162:
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            if (r15 != r2) goto L_0x0178
            java.lang.String r2 = "trim"
            java.lang.String r4 = r13.format     // Catch:{ Exception -> 0x0103, all -> 0x005c }
            boolean r2 = r2.equals(r4)     // Catch:{ Exception -> 0x0103, all -> 0x005c }
            if (r2 == 0) goto L_0x0178
            if (r1 == 0) goto L_0x0178
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0103, all -> 0x005c }
            java.lang.String r1 = r1.trim()     // Catch:{ Exception -> 0x0103, all -> 0x005c }
        L_0x0178:
            r5 = r1
            java.lang.String r4 = r8.processKey(r9, r10, r11, r5)     // Catch:{ Exception -> 0x04c8, all -> 0x04ca }
            com.alibaba.fastjson.serializer.BeanContext r2 = r3.fieldContext     // Catch:{ Exception -> 0x04c8, all -> 0x04ca }
            r1 = r32
            r29 = r2
            r2 = r33
            r12 = r3
            r10 = 1
            r3 = r29
            r10 = r4
            r22 = 0
            r4 = r34
            r24 = r5
            r9 = 44
            r5 = r11
            r30 = r6
            r6 = r24
            r31 = r7
            r7 = r37
            java.lang.Object r1 = r1.processValue(r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            java.lang.String r2 = ""
            if (r1 != 0) goto L_0x02bb
            int r3 = r13.serialzeFeatures     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            com.alibaba.fastjson.annotation.JSONField r4 = r13.getAnnotation()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            com.alibaba.fastjson.serializer.SerializeBeanInfo r5 = r8.beanInfo     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            com.alibaba.fastjson.annotation.JSONType r5 = r5.jsonType     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r5 == 0) goto L_0x01bc
            com.alibaba.fastjson.serializer.SerializeBeanInfo r5 = r8.beanInfo     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            com.alibaba.fastjson.annotation.JSONType r5 = r5.jsonType     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            com.alibaba.fastjson.serializer.SerializerFeature[] r5 = r5.serialzeFeatures()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r5 = com.alibaba.fastjson.serializer.SerializerFeature.of(r5)     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r3 = r3 | r5
        L_0x01bc:
            if (r4 == 0) goto L_0x01ce
            java.lang.String r5 = r4.defaultValue()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            boolean r5 = r2.equals(r5)     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r5 != 0) goto L_0x01ce
            java.lang.String r1 = r4.defaultValue()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            goto L_0x02bb
        L_0x01ce:
            java.lang.Class<java.lang.Boolean> r4 = java.lang.Boolean.class
            if (r15 != r4) goto L_0x0204
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullBooleanAsFalse     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            com.alibaba.fastjson.serializer.SerializerFeature r5 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r5 = r5.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r5 = r5 | r4
            if (r16 != 0) goto L_0x01e8
            r6 = r3 & r5
            if (r6 != 0) goto L_0x01e8
            int r6 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r5 = r5 & r6
            if (r5 != 0) goto L_0x01e8
            goto L_0x0376
        L_0x01e8:
            r5 = r3 & r4
            if (r5 == 0) goto L_0x01f2
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r22)     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            goto L_0x02bb
        L_0x01f2:
            int r5 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r4 = r4 & r5
            if (r4 == 0) goto L_0x02bb
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r3 = r3 & r4
            if (r3 != 0) goto L_0x02bb
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r22)     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            goto L_0x02bb
        L_0x0204:
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            if (r15 != r4) goto L_0x0232
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            com.alibaba.fastjson.serializer.SerializerFeature r5 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r5 = r5.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r5 = r5 | r4
            if (r16 != 0) goto L_0x021e
            r6 = r3 & r5
            if (r6 != 0) goto L_0x021e
            int r6 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r5 = r5 & r6
            if (r5 != 0) goto L_0x021e
            goto L_0x0376
        L_0x021e:
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0225
        L_0x0222:
            r1 = r2
            goto L_0x02bb
        L_0x0225:
            int r5 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r4 = r4 & r5
            if (r4 == 0) goto L_0x02bb
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r3 = r3 & r4
            if (r3 != 0) goto L_0x02bb
            goto L_0x0222
        L_0x0232:
            java.lang.Class<java.lang.Number> r4 = java.lang.Number.class
            boolean r4 = r4.isAssignableFrom(r15)     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r4 == 0) goto L_0x026a
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullNumberAsZero     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            com.alibaba.fastjson.serializer.SerializerFeature r5 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r5 = r5.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r5 = r5 | r4
            if (r16 != 0) goto L_0x0250
            r6 = r3 & r5
            if (r6 != 0) goto L_0x0250
            int r6 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r5 = r5 & r6
            if (r5 != 0) goto L_0x0250
            goto L_0x0376
        L_0x0250:
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0259
            java.lang.Integer r1 = java.lang.Integer.valueOf(r22)     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            goto L_0x02bb
        L_0x0259:
            int r5 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r4 = r4 & r5
            if (r4 == 0) goto L_0x02bb
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r3 = r3 & r4
            if (r3 != 0) goto L_0x02bb
            java.lang.Integer r1 = java.lang.Integer.valueOf(r22)     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            goto L_0x02bb
        L_0x026a:
            java.lang.Class<java.util.Collection> r4 = java.util.Collection.class
            boolean r4 = r4.isAssignableFrom(r15)     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r4 == 0) goto L_0x02a2
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            com.alibaba.fastjson.serializer.SerializerFeature r5 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r5 = r5.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r5 = r5 | r4
            if (r16 != 0) goto L_0x0288
            r6 = r3 & r5
            if (r6 != 0) goto L_0x0288
            int r6 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r5 = r5 & r6
            if (r5 != 0) goto L_0x0288
            goto L_0x0376
        L_0x0288:
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0291
            java.util.List r1 = java.util.Collections.emptyList()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            goto L_0x02bb
        L_0x0291:
            int r5 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r4 = r4 & r5
            if (r4 == 0) goto L_0x02bb
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r3 = r3 & r4
            if (r3 != 0) goto L_0x02bb
            java.util.List r1 = java.util.Collections.emptyList()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            goto L_0x02bb
        L_0x02a2:
            if (r16 != 0) goto L_0x02bb
            boolean r4 = r12.writeNull     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r4 != 0) goto L_0x02bb
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            boolean r4 = r14.isEnabled((int) r4)     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r4 != 0) goto L_0x02bb
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r3 = r3 & r4
            if (r3 != 0) goto L_0x02bb
            goto L_0x0376
        L_0x02bb:
            if (r1 == 0) goto L_0x0363
            boolean r3 = r14.notWriteDefaultValue     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r3 != 0) goto L_0x02d5
            int r3 = r13.serialzeFeatures     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.NotWriteDefaultValue     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r3 = r3 & r4
            if (r3 != 0) goto L_0x02d5
            com.alibaba.fastjson.serializer.SerializeBeanInfo r3 = r8.beanInfo     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r3 = r3.features     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.NotWriteDefaultValue     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0363
        L_0x02d5:
            java.lang.Class<?> r3 = r13.fieldClass     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            java.lang.Class r4 = java.lang.Byte.TYPE     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r3 != r4) goto L_0x02ea
            boolean r4 = r1 instanceof java.lang.Byte     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r4 == 0) goto L_0x02ea
            r4 = r1
            java.lang.Byte r4 = (java.lang.Byte) r4     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            byte r4 = r4.byteValue()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r4 != 0) goto L_0x02ea
            goto L_0x0376
        L_0x02ea:
            java.lang.Class r4 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r3 != r4) goto L_0x02fd
            boolean r4 = r1 instanceof java.lang.Short     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r4 == 0) goto L_0x02fd
            r4 = r1
            java.lang.Short r4 = (java.lang.Short) r4     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            short r4 = r4.shortValue()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r4 != 0) goto L_0x02fd
            goto L_0x0376
        L_0x02fd:
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r3 != r4) goto L_0x0310
            boolean r4 = r1 instanceof java.lang.Integer     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r4 == 0) goto L_0x0310
            r4 = r1
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r4 = r4.intValue()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r4 != 0) goto L_0x0310
            goto L_0x0376
        L_0x0310:
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r3 != r4) goto L_0x0326
            boolean r4 = r1 instanceof java.lang.Long     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r4 == 0) goto L_0x0326
            r4 = r1
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            long r4 = r4.longValue()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0326
            goto L_0x0376
        L_0x0326:
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r3 != r4) goto L_0x033b
            boolean r4 = r1 instanceof java.lang.Float     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r4 == 0) goto L_0x033b
            r4 = r1
            java.lang.Float r4 = (java.lang.Float) r4     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            float r4 = r4.floatValue()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 != 0) goto L_0x033b
            goto L_0x0376
        L_0x033b:
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r3 != r4) goto L_0x0351
            boolean r4 = r1 instanceof java.lang.Double     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r4 == 0) goto L_0x0351
            r4 = r1
            java.lang.Double r4 = (java.lang.Double) r4     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            double r4 = r4.doubleValue()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0351
            goto L_0x0376
        L_0x0351:
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r3 != r4) goto L_0x0363
            boolean r3 = r1 instanceof java.lang.Boolean     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r3 == 0) goto L_0x0363
            r3 = r1
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            boolean r3 = r3.booleanValue()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r3 != 0) goto L_0x0363
            goto L_0x0376
        L_0x0363:
            if (r21 == 0) goto L_0x038c
            boolean r3 = r13.unwrapped     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r3 == 0) goto L_0x037e
            boolean r3 = r1 instanceof java.util.Map     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r3 == 0) goto L_0x037e
            r3 = r1
            java.util.Map r3 = (java.util.Map) r3     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            int r3 = r3.size()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r3 != 0) goto L_0x037e
        L_0x0376:
            r4 = r33
            r5 = r9
            r9 = r22
            r3 = 1
            goto L_0x04b3
        L_0x037e:
            r14.write((int) r9)     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            com.alibaba.fastjson.serializer.SerializerFeature r3 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            boolean r3 = r14.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r3)     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            if (r3 == 0) goto L_0x038c
            r33.println()     // Catch:{ Exception -> 0x049f, all -> 0x049b }
        L_0x038c:
            if (r10 == r11) goto L_0x03a3
            if (r16 != 0) goto L_0x0395
            r3 = 1
            r14.writeFieldName(r10, r3)     // Catch:{ Exception -> 0x049f, all -> 0x049b }
            goto L_0x0396
        L_0x0395:
            r3 = 1
        L_0x0396:
            r4 = r33
            r5 = r9
            r4.write((java.lang.Object) r1)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
        L_0x039c:
            r9 = r22
            goto L_0x046b
        L_0x03a0:
            r0 = move-exception
            goto L_0x04d9
        L_0x03a3:
            r4 = r33
            r5 = r9
            r6 = r24
            r3 = 1
            if (r6 == r1) goto L_0x03b4
            if (r16 != 0) goto L_0x03b0
            r12.writePrefix(r4)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
        L_0x03b0:
            r4.write((java.lang.Object) r1)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            goto L_0x039c
        L_0x03b4:
            if (r16 != 0) goto L_0x03f3
            java.lang.Class<java.util.Map> r6 = java.util.Map.class
            boolean r6 = r6.isAssignableFrom(r15)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            boolean r7 = r15.isPrimitive()     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            if (r7 != 0) goto L_0x03ce
            java.lang.String r7 = r15.getName()     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            java.lang.String r9 = "java."
            boolean r7 = r7.startsWith(r9)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            if (r7 == 0) goto L_0x03d2
        L_0x03ce:
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            if (r15 != r7) goto L_0x03d4
        L_0x03d2:
            r7 = r3
            goto L_0x03d6
        L_0x03d4:
            r7 = r22
        L_0x03d6:
            if (r18 != 0) goto L_0x03e0
            boolean r9 = r13.unwrapped     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            if (r9 == 0) goto L_0x03e0
            if (r6 != 0) goto L_0x03f3
            if (r7 != 0) goto L_0x03f3
        L_0x03e0:
            if (r28 == 0) goto L_0x03ed
            char[] r6 = r13.name_chars     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            char[] r7 = r13.name_chars     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            int r7 = r7.length     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            r9 = r22
            r14.write((char[]) r6, (int) r9, (int) r7)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            goto L_0x03f5
        L_0x03ed:
            r9 = r22
            r12.writePrefix(r4)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            goto L_0x03f5
        L_0x03f3:
            r9 = r22
        L_0x03f5:
            if (r16 != 0) goto L_0x0468
            com.alibaba.fastjson.annotation.JSONField r6 = r13.getAnnotation()     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            if (r15 != r7) goto L_0x0450
            if (r6 == 0) goto L_0x0409
            java.lang.Class r6 = r6.serializeUsing()     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            java.lang.Class<java.lang.Void> r7 = java.lang.Void.class
            if (r6 != r7) goto L_0x0450
        L_0x0409:
            if (r1 != 0) goto L_0x0443
            int r6 = r12.features     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            com.alibaba.fastjson.serializer.SerializeBeanInfo r7 = r8.beanInfo     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            com.alibaba.fastjson.annotation.JSONType r7 = r7.jsonType     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            if (r7 == 0) goto L_0x0420
            com.alibaba.fastjson.serializer.SerializeBeanInfo r7 = r8.beanInfo     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            com.alibaba.fastjson.annotation.JSONType r7 = r7.jsonType     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            com.alibaba.fastjson.serializer.SerializerFeature[] r7 = r7.serialzeFeatures()     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            int r7 = com.alibaba.fastjson.serializer.SerializerFeature.of(r7)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            r6 = r6 | r7
        L_0x0420:
            int r7 = r14.features     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            com.alibaba.fastjson.serializer.SerializerFeature r10 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            int r10 = r10.mask     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            r7 = r7 & r10
            if (r7 == 0) goto L_0x0434
            com.alibaba.fastjson.serializer.SerializerFeature r7 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            int r7 = r7.mask     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            r7 = r7 & r6
            if (r7 != 0) goto L_0x0434
            r14.writeString((java.lang.String) r2)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            goto L_0x046b
        L_0x0434:
            com.alibaba.fastjson.serializer.SerializerFeature r7 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            int r7 = r7.mask     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            r6 = r6 & r7
            if (r6 == 0) goto L_0x043f
            r14.writeString((java.lang.String) r2)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            goto L_0x046b
        L_0x043f:
            r14.writeNull()     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            goto L_0x046b
        L_0x0443:
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            if (r27 == 0) goto L_0x044c
            r14.writeStringWithSingleQuote((java.lang.String) r2)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            goto L_0x046b
        L_0x044c:
            r14.writeStringWithDoubleQuote((java.lang.String) r2, (char) r9)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            goto L_0x046b
        L_0x0450:
            boolean r2 = r13.unwrapped     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            if (r2 == 0) goto L_0x0464
            boolean r2 = r1 instanceof java.util.Map     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            if (r2 == 0) goto L_0x0464
            r2 = r1
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            int r2 = r2.size()     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            if (r2 != 0) goto L_0x0464
            r21 = r9
            goto L_0x04b3
        L_0x0464:
            r12.writeValue(r4, r1)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            goto L_0x046b
        L_0x0468:
            r12.writeValue(r4, r1)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
        L_0x046b:
            boolean r2 = r13.unwrapped     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            if (r2 == 0) goto L_0x0498
            boolean r2 = r1 instanceof java.util.Map     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            if (r2 == 0) goto L_0x0498
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            int r2 = r1.size()     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            if (r2 != 0) goto L_0x047c
            goto L_0x04b3
        L_0x047c:
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            boolean r2 = r4.isEnabled(r2)     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            if (r2 != 0) goto L_0x0498
            java.util.Collection r1 = r1.values()     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
        L_0x048c:
            boolean r2 = r1.hasNext()     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            if (r2 == 0) goto L_0x04b3
            java.lang.Object r2 = r1.next()     // Catch:{ Exception -> 0x03a0, all -> 0x0509 }
            if (r2 == 0) goto L_0x048c
        L_0x0498:
            r21 = r3
            goto L_0x04b3
        L_0x049b:
            r0 = move-exception
            r4 = r33
            goto L_0x04ce
        L_0x049f:
            r0 = move-exception
            r4 = r33
            goto L_0x04d9
        L_0x04a3:
            r12 = r3
            r31 = r7
            r4 = r9
            throw r1     // Catch:{ Exception -> 0x04a8, all -> 0x0509 }
        L_0x04a8:
            r0 = move-exception
            goto L_0x04af
        L_0x04aa:
            r0 = move-exception
            r12 = r3
            r31 = r7
            r4 = r9
        L_0x04af:
            r1 = r34
            r3 = r0
            goto L_0x04de
        L_0x04b3:
            r1 = r25
            int r2 = r26 + 1
            r10 = r34
            r11 = r35
            r12 = r36
            r13 = r37
            r9 = r4
            r15 = r23
            r6 = r30
            r7 = r31
            goto L_0x00d0
        L_0x04c8:
            r0 = move-exception
            goto L_0x04d6
        L_0x04ca:
            r0 = move-exception
            r31 = r7
            r4 = r9
        L_0x04ce:
            r1 = r0
            r2 = r31
            goto L_0x05e2
        L_0x04d3:
            r0 = move-exception
            r25 = r1
        L_0x04d6:
            r31 = r7
            r4 = r9
        L_0x04d9:
            r1 = r34
        L_0x04db:
            r3 = r0
            r12 = r25
        L_0x04de:
            r2 = r31
            goto L_0x0538
        L_0x04e2:
            r25 = r1
            r30 = r6
            r31 = r7
            r4 = r9
            r23 = r15
            r9 = 0
            r1 = r34
            if (r21 == 0) goto L_0x04f1
            goto L_0x04f2
        L_0x04f1:
            r5 = r9
        L_0x04f2:
            r8.writeAfter(r4, r1, r5)     // Catch:{ Exception -> 0x051d, all -> 0x0519 }
            r2 = r23
            int r2 = r2.length     // Catch:{ Exception -> 0x051d, all -> 0x0519 }
            if (r2 <= 0) goto L_0x050d
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ Exception -> 0x050b, all -> 0x0509 }
            boolean r2 = r14.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r2)     // Catch:{ Exception -> 0x050b, all -> 0x0509 }
            if (r2 == 0) goto L_0x050d
            r33.decrementIdent()     // Catch:{ Exception -> 0x050b, all -> 0x0509 }
            r33.println()     // Catch:{ Exception -> 0x050b, all -> 0x0509 }
            goto L_0x050d
        L_0x0509:
            r0 = move-exception
            goto L_0x04ce
        L_0x050b:
            r0 = move-exception
            goto L_0x04db
        L_0x050d:
            if (r38 != 0) goto L_0x0514
            r2 = r30
            r14.append((char) r2)     // Catch:{ Exception -> 0x050b, all -> 0x0509 }
        L_0x0514:
            r2 = r31
            r4.context = r2
            return
        L_0x0519:
            r0 = move-exception
            r2 = r31
            goto L_0x052e
        L_0x051d:
            r0 = move-exception
            r2 = r31
            goto L_0x0527
        L_0x0521:
            r0 = move-exception
            r25 = r1
            r2 = r7
            r4 = r9
            r1 = r10
        L_0x0527:
            r3 = r0
        L_0x0528:
            r12 = r25
            goto L_0x0538
        L_0x052b:
            r0 = move-exception
            r2 = r7
            r4 = r9
        L_0x052e:
            r1 = r0
            goto L_0x05e2
        L_0x0531:
            r0 = move-exception
            r2 = r7
            r4 = r9
            r1 = r10
            r3 = r0
        L_0x0536:
            r12 = r17
        L_0x0538:
            java.lang.String r5 = "write javaBean error, fastjson version 1.2.69"
            if (r1 == 0) goto L_0x0558
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x05df }
            r6.<init>()     // Catch:{ all -> 0x05df }
            r6.append(r5)     // Catch:{ all -> 0x05df }
            java.lang.String r5 = ", class "
            r6.append(r5)     // Catch:{ all -> 0x05df }
            java.lang.Class r1 = r34.getClass()     // Catch:{ all -> 0x05df }
            java.lang.String r1 = r1.getName()     // Catch:{ all -> 0x05df }
            r6.append(r1)     // Catch:{ all -> 0x05df }
            java.lang.String r5 = r6.toString()     // Catch:{ all -> 0x05df }
        L_0x0558:
            java.lang.String r1 = ", fieldName : "
            r6 = r35
            if (r6 == 0) goto L_0x0571
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x05df }
            r7.<init>()     // Catch:{ all -> 0x05df }
            r7.append(r5)     // Catch:{ all -> 0x05df }
            r7.append(r1)     // Catch:{ all -> 0x05df }
            r7.append(r6)     // Catch:{ all -> 0x05df }
            java.lang.String r5 = r7.toString()     // Catch:{ all -> 0x05df }
            goto L_0x05ae
        L_0x0571:
            if (r12 == 0) goto L_0x05ae
            com.alibaba.fastjson.util.FieldInfo r6 = r12.fieldInfo     // Catch:{ all -> 0x05df }
            if (r6 == 0) goto L_0x05ae
            com.alibaba.fastjson.util.FieldInfo r6 = r12.fieldInfo     // Catch:{ all -> 0x05df }
            java.lang.reflect.Method r7 = r6.method     // Catch:{ all -> 0x05df }
            if (r7 == 0) goto L_0x0598
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x05df }
            r1.<init>()     // Catch:{ all -> 0x05df }
            r1.append(r5)     // Catch:{ all -> 0x05df }
            java.lang.String r5 = ", method : "
            r1.append(r5)     // Catch:{ all -> 0x05df }
            java.lang.reflect.Method r5 = r6.method     // Catch:{ all -> 0x05df }
            java.lang.String r5 = r5.getName()     // Catch:{ all -> 0x05df }
            r1.append(r5)     // Catch:{ all -> 0x05df }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x05df }
            goto L_0x05ae
        L_0x0598:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x05df }
            r6.<init>()     // Catch:{ all -> 0x05df }
            r6.append(r5)     // Catch:{ all -> 0x05df }
            r6.append(r1)     // Catch:{ all -> 0x05df }
            com.alibaba.fastjson.util.FieldInfo r1 = r12.fieldInfo     // Catch:{ all -> 0x05df }
            java.lang.String r1 = r1.name     // Catch:{ all -> 0x05df }
            r6.append(r1)     // Catch:{ all -> 0x05df }
            java.lang.String r5 = r6.toString()     // Catch:{ all -> 0x05df }
        L_0x05ae:
            java.lang.String r1 = r3.getMessage()     // Catch:{ all -> 0x05df }
            if (r1 == 0) goto L_0x05cc
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x05df }
            r1.<init>()     // Catch:{ all -> 0x05df }
            r1.append(r5)     // Catch:{ all -> 0x05df }
            java.lang.String r5 = ", "
            r1.append(r5)     // Catch:{ all -> 0x05df }
            java.lang.String r5 = r3.getMessage()     // Catch:{ all -> 0x05df }
            r1.append(r5)     // Catch:{ all -> 0x05df }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x05df }
        L_0x05cc:
            boolean r1 = r3 instanceof java.lang.reflect.InvocationTargetException     // Catch:{ all -> 0x05df }
            if (r1 == 0) goto L_0x05d4
            java.lang.Throwable r17 = r3.getCause()     // Catch:{ all -> 0x05df }
        L_0x05d4:
            if (r17 != 0) goto L_0x05d7
            goto L_0x05d9
        L_0x05d7:
            r3 = r17
        L_0x05d9:
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x05df }
            r1.<init>(r5, r3)     // Catch:{ all -> 0x05df }
            throw r1     // Catch:{ all -> 0x05df }
        L_0x05df:
            r0 = move-exception
            goto L_0x052e
        L_0x05e2:
            r4.context = r2
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.JavaBeanSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type, int, boolean):void");
    }

    /* access modifiers changed from: protected */
    public void writeClassName(JSONSerializer jSONSerializer, String str, Object obj) {
        if (str == null) {
            str = jSONSerializer.config.typeKey;
        }
        jSONSerializer.out.writeFieldName(str, false);
        String str2 = this.beanInfo.typeName;
        if (str2 == null) {
            Class cls = obj.getClass();
            if (TypeUtils.isProxy(cls)) {
                cls = cls.getSuperclass();
            }
            str2 = cls.getName();
        }
        jSONSerializer.write(str2);
    }

    public boolean writeReference(JSONSerializer jSONSerializer, Object obj, int i) {
        SerialContext serialContext = jSONSerializer.context;
        int i2 = SerializerFeature.DisableCircularReferenceDetect.mask;
        if (serialContext == null || (serialContext.features & i2) != 0 || (i & i2) != 0 || jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj)) {
            return false;
        }
        jSONSerializer.writeReference(obj);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isWriteAsArray(JSONSerializer jSONSerializer) {
        return isWriteAsArray(jSONSerializer, 0);
    }

    /* access modifiers changed from: protected */
    public boolean isWriteAsArray(JSONSerializer jSONSerializer, int i) {
        int i2 = SerializerFeature.BeanToArray.mask;
        return ((this.beanInfo.features & i2) == 0 && !jSONSerializer.out.beanToArray && (i & i2) == 0) ? false : true;
    }

    public Object getFieldValue(Object obj, String str) {
        FieldSerializer fieldSerializer = getFieldSerializer(str);
        if (fieldSerializer != null) {
            try {
                return fieldSerializer.getPropertyValue(obj);
            } catch (InvocationTargetException e) {
                throw new JSONException("getFieldValue error." + str, e);
            } catch (IllegalAccessException e2) {
                throw new JSONException("getFieldValue error." + str, e2);
            }
        } else {
            throw new JSONException("field not found. " + str);
        }
    }

    public Object getFieldValue(Object obj, String str, long j, boolean z) {
        FieldSerializer fieldSerializer = getFieldSerializer(j);
        if (fieldSerializer != null) {
            try {
                return fieldSerializer.getPropertyValue(obj);
            } catch (InvocationTargetException e) {
                throw new JSONException("getFieldValue error." + str, e);
            } catch (IllegalAccessException e2) {
                throw new JSONException("getFieldValue error." + str, e2);
            }
        } else if (!z) {
            return null;
        } else {
            throw new JSONException("field not found. " + str);
        }
    }

    public FieldSerializer getFieldSerializer(String str) {
        if (str == null) {
            return null;
        }
        int length = this.sortedGetters.length - 1;
        int i = 0;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int compareTo = this.sortedGetters[i2].fieldInfo.name.compareTo(str);
            if (compareTo < 0) {
                i = i2 + 1;
            } else if (compareTo <= 0) {
                return this.sortedGetters[i2];
            } else {
                length = i2 - 1;
            }
        }
        return null;
    }

    public FieldSerializer getFieldSerializer(long j) {
        PropertyNamingStrategy[] propertyNamingStrategyArr;
        int binarySearch;
        if (this.hashArray == null) {
            propertyNamingStrategyArr = PropertyNamingStrategy.values();
            long[] jArr = new long[(this.sortedGetters.length * propertyNamingStrategyArr.length)];
            int i = 0;
            int i2 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr = this.sortedGetters;
                if (i >= fieldSerializerArr.length) {
                    break;
                }
                String str = fieldSerializerArr[i].fieldInfo.name;
                jArr[i2] = TypeUtils.fnv1a_64(str);
                i2++;
                for (PropertyNamingStrategy translate : propertyNamingStrategyArr) {
                    String translate2 = translate.translate(str);
                    if (!str.equals(translate2)) {
                        jArr[i2] = TypeUtils.fnv1a_64(translate2);
                        i2++;
                    }
                }
                i++;
            }
            Arrays.sort(jArr, 0, i2);
            this.hashArray = new long[i2];
            System.arraycopy(jArr, 0, this.hashArray, 0, i2);
        } else {
            propertyNamingStrategyArr = null;
        }
        int binarySearch2 = Arrays.binarySearch(this.hashArray, j);
        if (binarySearch2 < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            if (propertyNamingStrategyArr == null) {
                propertyNamingStrategyArr = PropertyNamingStrategy.values();
            }
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, -1);
            int i3 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr2 = this.sortedGetters;
                if (i3 >= fieldSerializerArr2.length) {
                    break;
                }
                String str2 = fieldSerializerArr2[i3].fieldInfo.name;
                int binarySearch3 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(str2));
                if (binarySearch3 >= 0) {
                    sArr[binarySearch3] = (short) i3;
                }
                for (PropertyNamingStrategy translate3 : propertyNamingStrategyArr) {
                    String translate4 = translate3.translate(str2);
                    if (!str2.equals(translate4) && (binarySearch = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(translate4))) >= 0) {
                        sArr[binarySearch] = (short) i3;
                    }
                }
                i3++;
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[binarySearch2];
        if (s != -1) {
            return this.sortedGetters[s];
        }
        return null;
    }

    public List<Object> getFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer propertyValue : this.sortedGetters) {
            arrayList.add(propertyValue.getPropertyValue(obj));
        }
        return arrayList;
    }

    public List<Object> getObjectFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            Class<?> cls = fieldSerializer.fieldInfo.fieldClass;
            if (!cls.isPrimitive() && !cls.getName().startsWith("java.lang.")) {
                arrayList.add(fieldSerializer.getPropertyValue(obj));
            }
        }
        return arrayList;
    }

    public int getSize(Object obj) throws Exception {
        int i = 0;
        for (FieldSerializer propertyValueDirect : this.sortedGetters) {
            if (propertyValueDirect.getPropertyValueDirect(obj) != null) {
                i++;
            }
        }
        return i;
    }

    public Set<String> getFieldNames(Object obj) throws Exception {
        HashSet hashSet = new HashSet();
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            if (fieldSerializer.getPropertyValueDirect(obj) != null) {
                hashSet.add(fieldSerializer.fieldInfo.name);
            }
        }
        return hashSet;
    }

    public Map<String, Object> getFieldValuesMap(Object obj) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            boolean isEnabled = SerializerFeature.isEnabled(fieldSerializer.features, SerializerFeature.SkipTransientField);
            FieldInfo fieldInfo = fieldSerializer.fieldInfo;
            if (!isEnabled || fieldInfo == null || !fieldInfo.fieldTransient) {
                if (fieldSerializer.fieldInfo.unwrapped) {
                    Object json = JSON.toJSON(fieldSerializer.getPropertyValue(obj));
                    if (json instanceof Map) {
                        linkedHashMap.putAll((Map) json);
                    } else {
                        linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
                    }
                } else {
                    linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
                }
            }
        }
        return linkedHashMap;
    }

    /* access modifiers changed from: protected */
    public BeanContext getBeanContext(int i) {
        return this.sortedGetters[i].fieldContext;
    }

    /* access modifiers changed from: protected */
    public Type getFieldType(int i) {
        return this.sortedGetters[i].fieldInfo.fieldType;
    }

    /* access modifiers changed from: protected */
    public char writeBefore(JSONSerializer jSONSerializer, Object obj, char c2) {
        if (jSONSerializer.beforeFilters != null) {
            for (BeforeFilter writeBefore : jSONSerializer.beforeFilters) {
                c2 = writeBefore.writeBefore(jSONSerializer, obj, c2);
            }
        }
        if (this.beforeFilters != null) {
            for (BeforeFilter writeBefore2 : this.beforeFilters) {
                c2 = writeBefore2.writeBefore(jSONSerializer, obj, c2);
            }
        }
        return c2;
    }

    /* access modifiers changed from: protected */
    public char writeAfter(JSONSerializer jSONSerializer, Object obj, char c2) {
        if (jSONSerializer.afterFilters != null) {
            for (AfterFilter writeAfter : jSONSerializer.afterFilters) {
                c2 = writeAfter.writeAfter(jSONSerializer, obj, c2);
            }
        }
        if (this.afterFilters != null) {
            for (AfterFilter writeAfter2 : this.afterFilters) {
                c2 = writeAfter2.writeAfter(jSONSerializer, obj, c2);
            }
        }
        return c2;
    }

    /* access modifiers changed from: protected */
    public boolean applyLabel(JSONSerializer jSONSerializer, String str) {
        if (jSONSerializer.labelFilters != null) {
            for (LabelFilter apply : jSONSerializer.labelFilters) {
                if (!apply.apply(str)) {
                    return false;
                }
            }
        }
        if (this.labelFilters == null) {
            return true;
        }
        for (LabelFilter apply2 : this.labelFilters) {
            if (!apply2.apply(str)) {
                return false;
            }
        }
        return true;
    }
}
