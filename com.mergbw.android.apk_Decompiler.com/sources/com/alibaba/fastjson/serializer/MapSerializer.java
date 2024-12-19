package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class MapSerializer extends SerializeFilterable implements ObjectSerializer {
    private static final int NON_STRINGKEY_AS_STRING = SerializerFeature.of(new SerializerFeature[]{SerializerFeature.BrowserCompatible, SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.BrowserSecure});
    public static MapSerializer instance = new MapSerializer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: java.util.TreeMap} */
    /* JADX WARNING: type inference failed for: r0v8, types: [boolean] */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: type inference failed for: r0v16 */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x023c, code lost:
        if (com.alibaba.fastjson.serializer.SerializerFeature.isEnabled(r12.features, r11, com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue) == false) goto L_0x023e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00e2, code lost:
        if (applyName(r9, r0, (java.lang.String) r1) == false) goto L_0x00e4;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0234 A[Catch:{ all -> 0x030a }] */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x024c A[Catch:{ all -> 0x030a }] */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0263 A[Catch:{ all -> 0x030a }] */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x028e A[Catch:{ all -> 0x030a }] */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x0298 A[Catch:{ all -> 0x030a }] */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x0304  */
    /* JADX WARNING: Removed duplicated region for block: B:218:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a3 A[Catch:{ all -> 0x030a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(com.alibaba.fastjson.serializer.JSONSerializer r24, java.lang.Object r25, java.lang.Object r26, java.lang.reflect.Type r27, int r28, boolean r29) throws java.io.IOException {
        /*
            r23 = this;
            r8 = r23
            r9 = r24
            r0 = r25
            r10 = r27
            r11 = r28
            com.alibaba.fastjson.serializer.SerializeWriter r12 = r9.out
            if (r0 != 0) goto L_0x0012
            r12.writeNull()
            return
        L_0x0012:
            r1 = r0
            java.util.Map r1 = (java.util.Map) r1
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.MapSortField
            int r2 = r2.mask
            int r3 = r12.features
            r3 = r3 & r2
            if (r3 != 0) goto L_0x0024
            r2 = r2 & r11
            if (r2 == 0) goto L_0x0022
            goto L_0x0024
        L_0x0022:
            r13 = r1
            goto L_0x003c
        L_0x0024:
            boolean r2 = r1 instanceof com.alibaba.fastjson.JSONObject
            if (r2 == 0) goto L_0x002e
            com.alibaba.fastjson.JSONObject r1 = (com.alibaba.fastjson.JSONObject) r1
            java.util.Map r1 = r1.getInnerMap()
        L_0x002e:
            boolean r2 = r1 instanceof java.util.SortedMap
            if (r2 != 0) goto L_0x0022
            boolean r2 = r1 instanceof java.util.LinkedHashMap
            if (r2 != 0) goto L_0x0022
            java.util.TreeMap r2 = new java.util.TreeMap     // Catch:{ Exception -> 0x0022 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0022 }
            r13 = r2
        L_0x003c:
            boolean r1 = r24.containsReference(r25)
            if (r1 == 0) goto L_0x0046
            r24.writeReference(r25)
            return
        L_0x0046:
            com.alibaba.fastjson.serializer.SerialContext r14 = r9.context
            r15 = 0
            r1 = r26
            r9.setContext(r14, r0, r1, r15)
            if (r29 != 0) goto L_0x0055
            r1 = 123(0x7b, float:1.72E-43)
            r12.write((int) r1)     // Catch:{ all -> 0x030a }
        L_0x0055:
            r24.incrementIndent()     // Catch:{ all -> 0x030a }
            com.alibaba.fastjson.serializer.SerializerFeature r1 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ all -> 0x030a }
            boolean r1 = r12.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r1)     // Catch:{ all -> 0x030a }
            r7 = 1
            if (r1 == 0) goto L_0x008c
            com.alibaba.fastjson.serializer.SerializeConfig r1 = r9.config     // Catch:{ all -> 0x030a }
            java.lang.String r1 = r1.typeKey     // Catch:{ all -> 0x030a }
            java.lang.Class r2 = r13.getClass()     // Catch:{ all -> 0x030a }
            java.lang.Class<com.alibaba.fastjson.JSONObject> r3 = com.alibaba.fastjson.JSONObject.class
            if (r2 == r3) goto L_0x0075
            java.lang.Class<java.util.HashMap> r3 = java.util.HashMap.class
            if (r2 == r3) goto L_0x0075
            java.lang.Class<java.util.LinkedHashMap> r3 = java.util.LinkedHashMap.class
            if (r2 != r3) goto L_0x007c
        L_0x0075:
            boolean r2 = r13.containsKey(r1)     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x007c
            goto L_0x008c
        L_0x007c:
            r12.writeFieldName(r1)     // Catch:{ all -> 0x030a }
            java.lang.Class r1 = r25.getClass()     // Catch:{ all -> 0x030a }
            java.lang.String r1 = r1.getName()     // Catch:{ all -> 0x030a }
            r12.writeString((java.lang.String) r1)     // Catch:{ all -> 0x030a }
            r1 = r15
            goto L_0x008d
        L_0x008c:
            r1 = r7
        L_0x008d:
            java.util.Set r2 = r13.entrySet()     // Catch:{ all -> 0x030a }
            java.util.Iterator r16 = r2.iterator()     // Catch:{ all -> 0x030a }
            r17 = 0
            r18 = r1
            r6 = r17
            r19 = r6
        L_0x009d:
            boolean r1 = r16.hasNext()     // Catch:{ all -> 0x030a }
            if (r1 == 0) goto L_0x02ec
            java.lang.Object r1 = r16.next()     // Catch:{ all -> 0x030a }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x030a }
            java.lang.Object r5 = r1.getValue()     // Catch:{ all -> 0x030a }
            java.lang.Object r1 = r1.getKey()     // Catch:{ all -> 0x030a }
            java.util.List r2 = r9.propertyPreFilters     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x00e9
            int r2 = r2.size()     // Catch:{ all -> 0x030a }
            if (r2 <= 0) goto L_0x00e9
            if (r1 == 0) goto L_0x00db
            boolean r2 = r1 instanceof java.lang.String     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x00c2
            goto L_0x00db
        L_0x00c2:
            java.lang.Class r2 = r1.getClass()     // Catch:{ all -> 0x030a }
            boolean r2 = r2.isPrimitive()     // Catch:{ all -> 0x030a }
            if (r2 != 0) goto L_0x00d0
            boolean r2 = r1 instanceof java.lang.Number     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x00e9
        L_0x00d0:
            java.lang.String r2 = com.alibaba.fastjson.JSON.toJSONString(r1)     // Catch:{ all -> 0x030a }
            boolean r2 = r8.applyName(r9, r0, r2)     // Catch:{ all -> 0x030a }
            if (r2 != 0) goto L_0x00e9
            goto L_0x00e4
        L_0x00db:
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x030a }
            boolean r2 = r8.applyName(r9, r0, r2)     // Catch:{ all -> 0x030a }
            if (r2 != 0) goto L_0x00e9
        L_0x00e4:
            r22 = r6
            r0 = r7
            goto L_0x023e
        L_0x00e9:
            java.util.List r2 = r8.propertyPreFilters     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x011d
            int r2 = r2.size()     // Catch:{ all -> 0x030a }
            if (r2 <= 0) goto L_0x011d
            if (r1 == 0) goto L_0x0113
            boolean r2 = r1 instanceof java.lang.String     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x00fa
            goto L_0x0113
        L_0x00fa:
            java.lang.Class r2 = r1.getClass()     // Catch:{ all -> 0x030a }
            boolean r2 = r2.isPrimitive()     // Catch:{ all -> 0x030a }
            if (r2 != 0) goto L_0x0108
            boolean r2 = r1 instanceof java.lang.Number     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x011d
        L_0x0108:
            java.lang.String r2 = com.alibaba.fastjson.JSON.toJSONString(r1)     // Catch:{ all -> 0x030a }
            boolean r2 = r8.applyName(r9, r0, r2)     // Catch:{ all -> 0x030a }
            if (r2 != 0) goto L_0x011d
            goto L_0x00e4
        L_0x0113:
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x030a }
            boolean r2 = r8.applyName(r9, r0, r2)     // Catch:{ all -> 0x030a }
            if (r2 != 0) goto L_0x011d
            goto L_0x00e4
        L_0x011d:
            java.util.List r2 = r9.propertyFilters     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x0151
            int r2 = r2.size()     // Catch:{ all -> 0x030a }
            if (r2 <= 0) goto L_0x0151
            if (r1 == 0) goto L_0x0147
            boolean r2 = r1 instanceof java.lang.String     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x012e
            goto L_0x0147
        L_0x012e:
            java.lang.Class r2 = r1.getClass()     // Catch:{ all -> 0x030a }
            boolean r2 = r2.isPrimitive()     // Catch:{ all -> 0x030a }
            if (r2 != 0) goto L_0x013c
            boolean r2 = r1 instanceof java.lang.Number     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x0151
        L_0x013c:
            java.lang.String r2 = com.alibaba.fastjson.JSON.toJSONString(r1)     // Catch:{ all -> 0x030a }
            boolean r2 = r8.apply(r9, r0, r2, r5)     // Catch:{ all -> 0x030a }
            if (r2 != 0) goto L_0x0151
            goto L_0x00e4
        L_0x0147:
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x030a }
            boolean r2 = r8.apply(r9, r0, r2, r5)     // Catch:{ all -> 0x030a }
            if (r2 != 0) goto L_0x0151
            goto L_0x00e4
        L_0x0151:
            java.util.List r2 = r8.propertyFilters     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x0187
            int r2 = r2.size()     // Catch:{ all -> 0x030a }
            if (r2 <= 0) goto L_0x0187
            if (r1 == 0) goto L_0x017c
            boolean r2 = r1 instanceof java.lang.String     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x0162
            goto L_0x017c
        L_0x0162:
            java.lang.Class r2 = r1.getClass()     // Catch:{ all -> 0x030a }
            boolean r2 = r2.isPrimitive()     // Catch:{ all -> 0x030a }
            if (r2 != 0) goto L_0x0170
            boolean r2 = r1 instanceof java.lang.Number     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x0187
        L_0x0170:
            java.lang.String r2 = com.alibaba.fastjson.JSON.toJSONString(r1)     // Catch:{ all -> 0x030a }
            boolean r2 = r8.apply(r9, r0, r2, r5)     // Catch:{ all -> 0x030a }
            if (r2 != 0) goto L_0x0187
            goto L_0x00e4
        L_0x017c:
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x030a }
            boolean r2 = r8.apply(r9, r0, r2, r5)     // Catch:{ all -> 0x030a }
            if (r2 != 0) goto L_0x0187
            goto L_0x00e4
        L_0x0187:
            java.util.List r2 = r9.nameFilters     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x01b5
            int r2 = r2.size()     // Catch:{ all -> 0x030a }
            if (r2 <= 0) goto L_0x01b5
            if (r1 == 0) goto L_0x01af
            boolean r2 = r1 instanceof java.lang.String     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x0198
            goto L_0x01af
        L_0x0198:
            java.lang.Class r2 = r1.getClass()     // Catch:{ all -> 0x030a }
            boolean r2 = r2.isPrimitive()     // Catch:{ all -> 0x030a }
            if (r2 != 0) goto L_0x01a6
            boolean r2 = r1 instanceof java.lang.Number     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x01b5
        L_0x01a6:
            java.lang.String r1 = com.alibaba.fastjson.JSON.toJSONString(r1)     // Catch:{ all -> 0x030a }
            java.lang.String r1 = r8.processKey(r9, r0, r1, r5)     // Catch:{ all -> 0x030a }
            goto L_0x01b5
        L_0x01af:
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x030a }
            java.lang.String r1 = r8.processKey(r9, r0, r1, r5)     // Catch:{ all -> 0x030a }
        L_0x01b5:
            java.util.List r2 = r8.nameFilters     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x01e3
            int r2 = r2.size()     // Catch:{ all -> 0x030a }
            if (r2 <= 0) goto L_0x01e3
            if (r1 == 0) goto L_0x01dd
            boolean r2 = r1 instanceof java.lang.String     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x01c6
            goto L_0x01dd
        L_0x01c6:
            java.lang.Class r2 = r1.getClass()     // Catch:{ all -> 0x030a }
            boolean r2 = r2.isPrimitive()     // Catch:{ all -> 0x030a }
            if (r2 != 0) goto L_0x01d4
            boolean r2 = r1 instanceof java.lang.Number     // Catch:{ all -> 0x030a }
            if (r2 == 0) goto L_0x01e3
        L_0x01d4:
            java.lang.String r1 = com.alibaba.fastjson.JSON.toJSONString(r1)     // Catch:{ all -> 0x030a }
            java.lang.String r1 = r8.processKey(r9, r0, r1, r5)     // Catch:{ all -> 0x030a }
            goto L_0x01e3
        L_0x01dd:
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x030a }
            java.lang.String r1 = r8.processKey(r9, r0, r1, r5)     // Catch:{ all -> 0x030a }
        L_0x01e3:
            r4 = r1
            if (r4 == 0) goto L_0x0219
            boolean r1 = r4 instanceof java.lang.String     // Catch:{ all -> 0x030a }
            if (r1 == 0) goto L_0x01eb
            goto L_0x0219
        L_0x01eb:
            boolean r1 = r4 instanceof java.util.Map     // Catch:{ all -> 0x030a }
            if (r1 != 0) goto L_0x0210
            boolean r1 = r4 instanceof java.util.Collection     // Catch:{ all -> 0x030a }
            if (r1 == 0) goto L_0x01f4
            goto L_0x0210
        L_0x01f4:
            java.lang.String r20 = com.alibaba.fastjson.JSON.toJSONString(r4)     // Catch:{ all -> 0x030a }
            r3 = 0
            r1 = r23
            r2 = r24
            r15 = r4
            r4 = r25
            r21 = r5
            r5 = r20
            r22 = r6
            r6 = r21
            r0 = r7
            r7 = r28
            java.lang.Object r1 = r1.processValue(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x030a }
            goto L_0x0231
        L_0x0210:
            r15 = r4
            r21 = r5
            r22 = r6
            r0 = r7
            r3 = r21
            goto L_0x0232
        L_0x0219:
            r15 = r4
            r21 = r5
            r22 = r6
            r0 = r7
            r5 = r15
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x030a }
            r3 = 0
            r1 = r23
            r2 = r24
            r4 = r25
            r6 = r21
            r7 = r28
            java.lang.Object r1 = r1.processValue(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x030a }
        L_0x0231:
            r3 = r1
        L_0x0232:
            if (r3 != 0) goto L_0x0246
            int r1 = r12.features     // Catch:{ all -> 0x030a }
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ all -> 0x030a }
            boolean r1 = com.alibaba.fastjson.serializer.SerializerFeature.isEnabled(r1, r11, r2)     // Catch:{ all -> 0x030a }
            if (r1 != 0) goto L_0x0246
        L_0x023e:
            r7 = r0
            r6 = r22
            r15 = 0
        L_0x0242:
            r0 = r25
            goto L_0x009d
        L_0x0246:
            boolean r1 = r15 instanceof java.lang.String     // Catch:{ all -> 0x030a }
            r2 = 44
            if (r1 == 0) goto L_0x0263
            r4 = r15
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x030a }
            if (r18 != 0) goto L_0x0254
            r12.write((int) r2)     // Catch:{ all -> 0x030a }
        L_0x0254:
            com.alibaba.fastjson.serializer.SerializerFeature r1 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ all -> 0x030a }
            boolean r1 = r12.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r1)     // Catch:{ all -> 0x030a }
            if (r1 == 0) goto L_0x025f
            r24.println()     // Catch:{ all -> 0x030a }
        L_0x025f:
            r12.writeFieldName(r4, r0)     // Catch:{ all -> 0x030a }
            goto L_0x028c
        L_0x0263:
            if (r18 != 0) goto L_0x0268
            r12.write((int) r2)     // Catch:{ all -> 0x030a }
        L_0x0268:
            int r1 = NON_STRINGKEY_AS_STRING     // Catch:{ all -> 0x030a }
            boolean r1 = r12.isEnabled((int) r1)     // Catch:{ all -> 0x030a }
            if (r1 != 0) goto L_0x0278
            com.alibaba.fastjson.serializer.SerializerFeature r1 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNonStringKeyAsString     // Catch:{ all -> 0x030a }
            boolean r1 = com.alibaba.fastjson.serializer.SerializerFeature.isEnabled(r11, r1)     // Catch:{ all -> 0x030a }
            if (r1 == 0) goto L_0x0284
        L_0x0278:
            boolean r1 = r15 instanceof java.lang.Enum     // Catch:{ all -> 0x030a }
            if (r1 != 0) goto L_0x0284
            java.lang.String r1 = com.alibaba.fastjson.JSON.toJSONString(r15)     // Catch:{ all -> 0x030a }
            r9.write((java.lang.String) r1)     // Catch:{ all -> 0x030a }
            goto L_0x0287
        L_0x0284:
            r9.write((java.lang.Object) r15)     // Catch:{ all -> 0x030a }
        L_0x0287:
            r1 = 58
            r12.write((int) r1)     // Catch:{ all -> 0x030a }
        L_0x028c:
            if (r3 != 0) goto L_0x0298
            r12.writeNull()     // Catch:{ all -> 0x030a }
            r7 = r0
            r6 = r22
            r15 = 0
            r18 = 0
            goto L_0x0242
        L_0x0298:
            java.lang.Class r1 = r3.getClass()     // Catch:{ all -> 0x030a }
            r2 = r22
            if (r1 == r2) goto L_0x02a6
            com.alibaba.fastjson.serializer.ObjectSerializer r19 = r9.getObjectWriter(r1)     // Catch:{ all -> 0x030a }
            r7 = r1
            goto L_0x02a7
        L_0x02a6:
            r7 = r2
        L_0x02a7:
            r6 = r19
            com.alibaba.fastjson.serializer.SerializerFeature r1 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ all -> 0x030a }
            boolean r1 = com.alibaba.fastjson.serializer.SerializerFeature.isEnabled(r11, r1)     // Catch:{ all -> 0x030a }
            if (r1 == 0) goto L_0x02d8
            boolean r1 = r6 instanceof com.alibaba.fastjson.serializer.JavaBeanSerializer     // Catch:{ all -> 0x030a }
            if (r1 == 0) goto L_0x02d8
            boolean r1 = r10 instanceof java.lang.reflect.ParameterizedType     // Catch:{ all -> 0x030a }
            if (r1 == 0) goto L_0x02c8
            r1 = r10
            java.lang.reflect.ParameterizedType r1 = (java.lang.reflect.ParameterizedType) r1     // Catch:{ all -> 0x030a }
            java.lang.reflect.Type[] r1 = r1.getActualTypeArguments()     // Catch:{ all -> 0x030a }
            int r2 = r1.length     // Catch:{ all -> 0x030a }
            r4 = 2
            if (r2 != r4) goto L_0x02c8
            r1 = r1[r0]     // Catch:{ all -> 0x030a }
            r5 = r1
            goto L_0x02ca
        L_0x02c8:
            r5 = r17
        L_0x02ca:
            r1 = r6
            com.alibaba.fastjson.serializer.JavaBeanSerializer r1 = (com.alibaba.fastjson.serializer.JavaBeanSerializer) r1     // Catch:{ all -> 0x030a }
            r2 = r24
            r4 = r15
            r19 = r6
            r6 = r28
            r1.writeNoneASM(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x030a }
            goto L_0x02e5
        L_0x02d8:
            r19 = r6
            r5 = 0
            r1 = r19
            r2 = r24
            r4 = r15
            r6 = r28
            r1.write(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x030a }
        L_0x02e5:
            r6 = r7
            r15 = 0
            r18 = 0
            r7 = r0
            goto L_0x0242
        L_0x02ec:
            r9.context = r14
            r24.decrementIdent()
            com.alibaba.fastjson.serializer.SerializerFeature r0 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat
            boolean r0 = r12.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r0)
            if (r0 == 0) goto L_0x0302
            int r0 = r13.size()
            if (r0 <= 0) goto L_0x0302
            r24.println()
        L_0x0302:
            if (r29 != 0) goto L_0x0309
            r0 = 125(0x7d, float:1.75E-43)
            r12.write((int) r0)
        L_0x0309:
            return
        L_0x030a:
            r0 = move-exception
            r9.context = r14
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.MapSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type, int, boolean):void");
    }
}
