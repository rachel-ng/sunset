package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JavaBeanInfo {
    public final Method buildMethod;
    public final Class<?> builderClass;
    public final Class<?> clazz;
    public final Constructor<?> creatorConstructor;
    public Type[] creatorConstructorParameterTypes;
    public String[] creatorConstructorParameters;
    public final Constructor<?> defaultConstructor;
    public final int defaultConstructorParameterSize;
    public final Method factoryMethod;
    public final FieldInfo[] fields;
    public final JSONType jsonType;

    /* renamed from: kotlin  reason: collision with root package name */
    public boolean f40kotlin;
    public Constructor<?> kotlinDefaultConstructor;
    public String[] orders;
    public final int parserFeatures;
    public final FieldInfo[] sortedFields;
    public final String typeKey;
    public final String typeName;

    public JavaBeanInfo(Class<?> cls, Class<?> cls2, Constructor<?> constructor, Constructor<?> constructor2, Method method, Method method2, JSONType jSONType, List<FieldInfo> list) {
        JSONField jSONField;
        this.clazz = cls;
        this.builderClass = cls2;
        this.defaultConstructor = constructor;
        this.creatorConstructor = constructor2;
        this.factoryMethod = method;
        this.parserFeatures = TypeUtils.getParserFeatures(cls);
        this.buildMethod = method2;
        this.jsonType = jSONType;
        if (jSONType != null) {
            String typeName2 = jSONType.typeName();
            String typeKey2 = jSONType.typeKey();
            this.typeKey = typeKey2.length() <= 0 ? null : typeKey2;
            if (typeName2.length() != 0) {
                this.typeName = typeName2;
            } else {
                this.typeName = cls.getName();
            }
            String[] orders2 = jSONType.orders();
            this.orders = orders2.length == 0 ? null : orders2;
        } else {
            this.typeName = cls.getName();
            this.typeKey = null;
            this.orders = null;
        }
        FieldInfo[] fieldInfoArr = new FieldInfo[list.size()];
        this.fields = fieldInfoArr;
        list.toArray(fieldInfoArr);
        FieldInfo[] fieldInfoArr2 = new FieldInfo[fieldInfoArr.length];
        int i = 0;
        if (this.orders != null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
            for (FieldInfo fieldInfo : fieldInfoArr) {
                linkedHashMap.put(fieldInfo.name, fieldInfo);
            }
            int i2 = 0;
            for (String str : this.orders) {
                FieldInfo fieldInfo2 = (FieldInfo) linkedHashMap.get(str);
                if (fieldInfo2 != null) {
                    fieldInfoArr2[i2] = fieldInfo2;
                    linkedHashMap.remove(str);
                    i2++;
                }
            }
            for (FieldInfo fieldInfo3 : linkedHashMap.values()) {
                fieldInfoArr2[i2] = fieldInfo3;
                i2++;
            }
        } else {
            System.arraycopy(fieldInfoArr, 0, fieldInfoArr2, 0, fieldInfoArr.length);
            Arrays.sort(fieldInfoArr2);
        }
        this.sortedFields = Arrays.equals(this.fields, fieldInfoArr2) ? this.fields : fieldInfoArr2;
        if (constructor != null) {
            this.defaultConstructorParameterSize = constructor.getParameterTypes().length;
        } else if (method != null) {
            this.defaultConstructorParameterSize = method.getParameterTypes().length;
        } else {
            this.defaultConstructorParameterSize = 0;
        }
        if (constructor2 != null) {
            this.creatorConstructorParameterTypes = constructor2.getParameterTypes();
            boolean isKotlin = TypeUtils.isKotlin(cls);
            this.f40kotlin = isKotlin;
            if (isKotlin) {
                this.creatorConstructorParameters = TypeUtils.getKoltinConstructorParameters(cls);
                try {
                    this.kotlinDefaultConstructor = cls.getConstructor((Class[]) null);
                } catch (Throwable unused) {
                }
                Annotation[][] parameterAnnotations = TypeUtils.getParameterAnnotations((Constructor) constructor2);
                int i3 = 0;
                while (i3 < this.creatorConstructorParameters.length && i3 < parameterAnnotations.length) {
                    Annotation[] annotationArr = parameterAnnotations[i3];
                    int length = annotationArr.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length) {
                            jSONField = null;
                            break;
                        }
                        Annotation annotation = annotationArr[i4];
                        if (annotation instanceof JSONField) {
                            jSONField = (JSONField) annotation;
                            break;
                        }
                        i4++;
                    }
                    if (jSONField != null) {
                        String name = jSONField.name();
                        if (name.length() > 0) {
                            this.creatorConstructorParameters[i3] = name;
                        }
                    }
                    i3++;
                }
                return;
            }
            if (this.creatorConstructorParameterTypes.length == this.fields.length) {
                while (true) {
                    Type[] typeArr = this.creatorConstructorParameterTypes;
                    if (i >= typeArr.length) {
                        return;
                    }
                    if (typeArr[i] != this.fields[i].fieldClass) {
                        break;
                    }
                    i++;
                }
            }
            this.creatorConstructorParameters = ASMUtils.lookupParameterNames(constructor2);
        }
    }

    private static FieldInfo getField(List<FieldInfo> list, String str) {
        for (FieldInfo next : list) {
            if (next.name.equals(str)) {
                return next;
            }
            Field field = next.field;
            if (field != null && next.getAnnotation() != null && field.getName().equals(str)) {
                return next;
            }
        }
        return null;
    }

    static boolean add(List<FieldInfo> list, FieldInfo fieldInfo) {
        int size = list.size() - 1;
        while (size >= 0) {
            FieldInfo fieldInfo2 = list.get(size);
            if (!fieldInfo2.name.equals(fieldInfo.name) || (fieldInfo2.getOnly && !fieldInfo.getOnly)) {
                size--;
            } else if (fieldInfo2.fieldClass.isAssignableFrom(fieldInfo.fieldClass)) {
                list.set(size, fieldInfo);
                return true;
            } else if (fieldInfo2.compareTo(fieldInfo) >= 0) {
                return false;
            } else {
                list.set(size, fieldInfo);
                return true;
            }
        }
        list.add(fieldInfo);
        return true;
    }

    public static JavaBeanInfo build(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy) {
        return build(cls, type, propertyNamingStrategy, false, TypeUtils.compatibleWithJavaBean, false);
    }

    private static Map<TypeVariable, Type> buildGenericInfo(Class<?> cls) {
        Class<? super Object> superclass = cls.getSuperclass();
        HashMap hashMap = null;
        if (superclass == null) {
            return null;
        }
        while (true) {
            Class<? super Object> cls2 = superclass;
            Class<?> cls3 = cls;
            cls = cls2;
            if (cls == null || cls == Object.class) {
                return hashMap;
            }
            if (cls3.getGenericSuperclass() instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) cls3.getGenericSuperclass()).getActualTypeArguments();
                TypeVariable[] typeParameters = cls.getTypeParameters();
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    if (hashMap.containsKey(actualTypeArguments[i])) {
                        hashMap.put(typeParameters[i], hashMap.get(actualTypeArguments[i]));
                    } else {
                        hashMap.put(typeParameters[i], actualTypeArguments[i]);
                    }
                }
            }
            superclass = cls.getSuperclass();
        }
        return hashMap;
    }

    public static JavaBeanInfo build(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy, boolean z, boolean z2) {
        return build(cls, type, propertyNamingStrategy, z, z2, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0011, code lost:
        r0 = r15.naming();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:455:0x0a98, code lost:
        if (r1.deserialize() == false) goto L_0x0aa8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0318  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x031e  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x0325  */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x03f4  */
    /* JADX WARNING: Removed duplicated region for block: B:286:0x05e9  */
    /* JADX WARNING: Removed duplicated region for block: B:393:0x08a2  */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x08af  */
    /* JADX WARNING: Removed duplicated region for block: B:399:0x08d0  */
    /* JADX WARNING: Removed duplicated region for block: B:401:0x08d3  */
    /* JADX WARNING: Removed duplicated region for block: B:412:0x0968  */
    /* JADX WARNING: Removed duplicated region for block: B:415:0x097f  */
    /* JADX WARNING: Removed duplicated region for block: B:420:0x09ce  */
    /* JADX WARNING: Removed duplicated region for block: B:465:0x0b0f  */
    /* JADX WARNING: Removed duplicated region for block: B:513:0x09ac A[EDGE_INSN: B:513:0x09ac->B:418:0x09ac ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.alibaba.fastjson.util.JavaBeanInfo build(java.lang.Class<?> r50, java.lang.reflect.Type r51, com.alibaba.fastjson.PropertyNamingStrategy r52, boolean r53, boolean r54, boolean r55) {
        /*
            r13 = r50
            r14 = r51
            r9 = r55
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r0 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r0 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.Class<?>) r13, r0)
            r15 = r0
            com.alibaba.fastjson.annotation.JSONType r15 = (com.alibaba.fastjson.annotation.JSONType) r15
            if (r15 == 0) goto L_0x001d
            com.alibaba.fastjson.PropertyNamingStrategy r0 = r15.naming()
            if (r0 == 0) goto L_0x001d
            com.alibaba.fastjson.PropertyNamingStrategy r1 = com.alibaba.fastjson.PropertyNamingStrategy.CamelCase
            if (r0 == r1) goto L_0x001d
            r12 = r0
            goto L_0x001f
        L_0x001d:
            r12 = r52
        L_0x001f:
            java.lang.Class r11 = getBuilderClass(r13, r15)
            java.lang.reflect.Field[] r10 = r50.getDeclaredFields()
            java.lang.reflect.Method[] r8 = r50.getMethods()
            java.util.Map r16 = buildGenericInfo(r50)
            boolean r17 = com.alibaba.fastjson.util.TypeUtils.isKotlin(r50)
            java.lang.reflect.Constructor[] r0 = r50.getDeclaredConstructors()
            r6 = 1
            if (r17 == 0) goto L_0x0041
            int r1 = r0.length
            if (r1 != r6) goto L_0x003e
            goto L_0x0041
        L_0x003e:
            r18 = 0
            goto L_0x0052
        L_0x0041:
            if (r11 != 0) goto L_0x0048
            java.lang.reflect.Constructor r1 = getDefaultConstructor(r13, r0)
            goto L_0x0050
        L_0x0048:
            java.lang.reflect.Constructor[] r1 = r11.getDeclaredConstructors()
            java.lang.reflect.Constructor r1 = getDefaultConstructor(r11, r1)
        L_0x0050:
            r18 = r1
        L_0x0052:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r19 = 0
            r20 = 0
            if (r53 == 0) goto L_0x0084
            r0 = r13
        L_0x005e:
            if (r0 == 0) goto L_0x006c
            java.lang.reflect.Field[] r1 = r0.getDeclaredFields()
            computeFields(r13, r14, r12, r5, r1)
            java.lang.Class r0 = r0.getSuperclass()
            goto L_0x005e
        L_0x006c:
            if (r18 == 0) goto L_0x0071
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r18)
        L_0x0071:
            com.alibaba.fastjson.util.JavaBeanInfo r9 = new com.alibaba.fastjson.util.JavaBeanInfo
            r4 = 0
            r0 = r9
            r1 = r50
            r2 = r11
            r3 = r18
            r8 = r5
            r5 = r20
            r6 = r19
            r7 = r15
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x0084:
            boolean r1 = r50.isInterface()
            if (r1 != 0) goto L_0x0097
            int r1 = r50.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isAbstract(r1)
            if (r1 == 0) goto L_0x0095
            goto L_0x0097
        L_0x0095:
            r1 = 0
            goto L_0x0098
        L_0x0097:
            r1 = r6
        L_0x0098:
            if (r18 != 0) goto L_0x009c
            if (r11 == 0) goto L_0x009e
        L_0x009c:
            if (r1 == 0) goto L_0x040d
        L_0x009e:
            java.lang.reflect.Constructor r21 = getCreatorConstructor(r0)
            if (r21 == 0) goto L_0x0175
            if (r1 != 0) goto L_0x0175
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r21)
            java.lang.Class[] r9 = r21.getParameterTypes()
            int r0 = r9.length
            if (r0 <= 0) goto L_0x016b
            java.lang.annotation.Annotation[][] r1 = com.alibaba.fastjson.util.TypeUtils.getParameterAnnotations((java.lang.reflect.Constructor) r21)
            r0 = 0
            r22 = 0
        L_0x00b7:
            int r2 = r9.length
            if (r0 >= r2) goto L_0x016b
            int r2 = r1.length
            if (r0 >= r2) goto L_0x016b
            r2 = r1[r0]
            int r3 = r2.length
            r4 = 0
        L_0x00c1:
            if (r4 >= r3) goto L_0x00d0
            r6 = r2[r4]
            boolean r7 = r6 instanceof com.alibaba.fastjson.annotation.JSONField
            if (r7 == 0) goto L_0x00cc
            com.alibaba.fastjson.annotation.JSONField r6 = (com.alibaba.fastjson.annotation.JSONField) r6
            goto L_0x00d1
        L_0x00cc:
            int r4 = r4 + 1
            r6 = 1
            goto L_0x00c1
        L_0x00d0:
            r6 = 0
        L_0x00d1:
            r3 = r9[r0]
            java.lang.reflect.Type[] r2 = r21.getGenericParameterTypes()
            r4 = r2[r0]
            if (r6 == 0) goto L_0x00fc
            java.lang.String r2 = r6.name()
            java.lang.reflect.Field r2 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r2, r10)
            int r7 = r6.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r26 = r6.serialzeFeatures()
            int r26 = com.alibaba.fastjson.serializer.SerializerFeature.of(r26)
            com.alibaba.fastjson.parser.Feature[] r27 = r6.parseFeatures()
            int r27 = com.alibaba.fastjson.parser.Feature.of(r27)
            java.lang.String r6 = r6.name()
            goto L_0x0103
        L_0x00fc:
            r2 = 0
            r6 = 0
            r7 = 0
            r26 = 0
            r27 = 0
        L_0x0103:
            if (r6 == 0) goto L_0x010b
            int r28 = r6.length()
            if (r28 != 0) goto L_0x0113
        L_0x010b:
            if (r22 != 0) goto L_0x0111
            java.lang.String[] r22 = com.alibaba.fastjson.util.ASMUtils.lookupParameterNames(r21)
        L_0x0111:
            r6 = r22[r0]
        L_0x0113:
            if (r2 != 0) goto L_0x0137
            if (r22 != 0) goto L_0x0122
            if (r17 == 0) goto L_0x011e
            java.lang.String[] r22 = com.alibaba.fastjson.util.TypeUtils.getKoltinConstructorParameters(r50)
            goto L_0x0122
        L_0x011e:
            java.lang.String[] r22 = com.alibaba.fastjson.util.ASMUtils.lookupParameterNames(r21)
        L_0x0122:
            r55 = r1
            r28 = r2
            r1 = r22
            int r2 = r1.length
            if (r2 <= r0) goto L_0x0134
            r2 = r1[r0]
            java.lang.reflect.Field r2 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r2, r10)
            r22 = r1
            goto L_0x0139
        L_0x0134:
            r22 = r1
            goto L_0x013b
        L_0x0137:
            r55 = r1
        L_0x0139:
            r28 = r2
        L_0x013b:
            com.alibaba.fastjson.util.FieldInfo r2 = new com.alibaba.fastjson.util.FieldInfo
            r29 = r0
            r0 = r2
            r30 = r55
            r1 = r6
            r6 = r2
            r14 = 3
            r2 = r50
            r14 = 2
            r14 = r5
            r5 = r28
            r23 = r12
            r12 = r6
            r6 = r7
            r7 = r26
            r25 = r15
            r15 = r8
            r8 = r27
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            add(r14, r12)
            int r0 = r29 + 1
            r5 = r14
            r8 = r15
            r12 = r23
            r15 = r25
            r1 = r30
            r6 = 1
            r14 = r51
            goto L_0x00b7
        L_0x016b:
            r14 = r5
            r23 = r12
            r25 = r15
            r15 = r8
            r24 = 1
            goto L_0x0417
        L_0x0175:
            r14 = r5
            r23 = r12
            r25 = r15
            r15 = r8
            java.lang.reflect.Method r12 = getFactoryMethod(r13, r15, r9)
            if (r12 == 0) goto L_0x0233
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r12)
            java.lang.Class[] r8 = r12.getParameterTypes()
            int r0 = r8.length
            if (r0 <= 0) goto L_0x0408
            java.lang.annotation.Annotation[][] r15 = com.alibaba.fastjson.util.TypeUtils.getParameterAnnotations((java.lang.reflect.Method) r12)
            r0 = 0
            r7 = 0
        L_0x0191:
            int r1 = r8.length
            if (r7 >= r1) goto L_0x0222
            r1 = r15[r7]
            int r2 = r1.length
            r4 = 0
        L_0x0198:
            if (r4 >= r2) goto L_0x01a7
            r3 = r1[r4]
            boolean r5 = r3 instanceof com.alibaba.fastjson.annotation.JSONField
            if (r5 == 0) goto L_0x01a4
            r1 = r3
            com.alibaba.fastjson.annotation.JSONField r1 = (com.alibaba.fastjson.annotation.JSONField) r1
            goto L_0x01a8
        L_0x01a4:
            int r4 = r4 + 1
            goto L_0x0198
        L_0x01a7:
            r1 = 0
        L_0x01a8:
            if (r1 != 0) goto L_0x01bb
            if (r9 == 0) goto L_0x01b3
            boolean r2 = com.alibaba.fastjson.util.TypeUtils.isJacksonCreator(r12)
            if (r2 == 0) goto L_0x01b3
            goto L_0x01bb
        L_0x01b3:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.String r1 = "illegal json creator"
            r0.<init>(r1)
            throw r0
        L_0x01bb:
            if (r1 == 0) goto L_0x01db
            java.lang.String r2 = r1.name()
            int r3 = r1.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r4 = r1.serialzeFeatures()
            int r4 = com.alibaba.fastjson.serializer.SerializerFeature.of(r4)
            com.alibaba.fastjson.parser.Feature[] r1 = r1.parseFeatures()
            int r1 = com.alibaba.fastjson.parser.Feature.of(r1)
            r17 = r1
            r6 = r3
            r16 = r4
            goto L_0x01e1
        L_0x01db:
            r2 = 0
            r6 = 0
            r16 = 0
            r17 = 0
        L_0x01e1:
            if (r2 == 0) goto L_0x01ee
            int r1 = r2.length()
            if (r1 != 0) goto L_0x01ea
            goto L_0x01ee
        L_0x01ea:
            r18 = r0
            r1 = r2
            goto L_0x01f8
        L_0x01ee:
            if (r0 != 0) goto L_0x01f4
            java.lang.String[] r0 = com.alibaba.fastjson.util.ASMUtils.lookupParameterNames(r12)
        L_0x01f4:
            r1 = r0[r7]
            r18 = r0
        L_0x01f8:
            r3 = r8[r7]
            java.lang.reflect.Type[] r0 = r12.getGenericParameterTypes()
            r4 = r0[r7]
            java.lang.reflect.Field r5 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r1, r10)
            com.alibaba.fastjson.util.FieldInfo r2 = new com.alibaba.fastjson.util.FieldInfo
            r0 = r2
            r9 = r2
            r2 = r50
            r19 = r7
            r7 = r16
            r16 = r8
            r8 = r17
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            add(r14, r9)
            int r7 = r19 + 1
            r9 = r55
            r8 = r16
            r0 = r18
            goto L_0x0191
        L_0x0222:
            com.alibaba.fastjson.util.JavaBeanInfo r9 = new com.alibaba.fastjson.util.JavaBeanInfo
            r4 = 0
            r6 = 0
            r3 = 0
            r0 = r9
            r1 = r50
            r2 = r11
            r5 = r12
            r7 = r25
            r8 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x0233:
            if (r1 != 0) goto L_0x0408
            java.lang.String r9 = r50.getName()
            if (r17 == 0) goto L_0x024e
            int r1 = r0.length
            if (r1 <= 0) goto L_0x024e
            java.lang.String[] r1 = com.alibaba.fastjson.util.TypeUtils.getKoltinConstructorParameters(r50)
            java.lang.reflect.Constructor r0 = com.alibaba.fastjson.util.TypeUtils.getKotlinConstructor(r0, r1)
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r0)
            r21 = r0
            r7 = r1
            goto L_0x0313
        L_0x024e:
            int r1 = r0.length
            r4 = 0
            r7 = 0
        L_0x0251:
            if (r4 >= r1) goto L_0x0313
            r2 = r0[r4]
            java.lang.Class[] r3 = r2.getParameterTypes()
            java.lang.String r5 = "org.springframework.security.web.authentication.WebAuthenticationDetails"
            boolean r5 = r9.equals(r5)
            if (r5 == 0) goto L_0x028c
            int r5 = r3.length
            r6 = 2
            if (r5 != r6) goto L_0x0287
            r8 = 0
            r5 = r3[r8]
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            if (r5 != r6) goto L_0x02c6
            r5 = 1
            r3 = r3[r5]
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            if (r3 != r6) goto L_0x0282
            r2.setAccessible(r5)
            java.lang.String[] r1 = com.alibaba.fastjson.util.ASMUtils.lookupParameterNames(r2)
            r7 = r1
            r21 = r2
            r20 = r8
            r8 = 1
            goto L_0x0316
        L_0x0282:
            r20 = r8
            r8 = r5
            goto L_0x030f
        L_0x0287:
            r8 = 1
        L_0x0288:
            r20 = 0
            goto L_0x030f
        L_0x028c:
            r8 = 0
            java.lang.String r5 = "org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken"
            boolean r5 = r9.equals(r5)
            if (r5 == 0) goto L_0x02ca
            int r5 = r3.length
            r6 = 3
            if (r5 != r6) goto L_0x02c6
            r5 = r3[r8]
            java.lang.Class<java.lang.Object> r8 = java.lang.Object.class
            if (r5 != r8) goto L_0x0287
            r5 = 1
            r8 = r3[r5]
            java.lang.Class<java.lang.Object> r6 = java.lang.Object.class
            if (r8 != r6) goto L_0x02c4
            r6 = 2
            r3 = r3[r6]
            java.lang.Class<java.util.Collection> r8 = java.util.Collection.class
            if (r3 != r8) goto L_0x02c4
            r2.setAccessible(r5)
            r0 = 3
            java.lang.String[] r1 = new java.lang.String[r0]
            java.lang.String r0 = "principal"
            r3 = 0
            r1[r3] = r0
            java.lang.String r0 = "credentials"
            r1[r5] = r0
            java.lang.String r0 = "authorities"
            r1[r6] = r0
            r7 = r1
            r21 = r2
            goto L_0x0313
        L_0x02c4:
            r8 = r5
            goto L_0x0288
        L_0x02c6:
            r20 = r8
            r8 = 1
            goto L_0x030f
        L_0x02ca:
            java.lang.String r5 = "org.springframework.security.core.authority.SimpleGrantedAuthority"
            boolean r5 = r9.equals(r5)
            if (r5 == 0) goto L_0x02e8
            int r5 = r3.length
            r8 = 1
            r20 = 0
            if (r5 != r8) goto L_0x030f
            r3 = r3[r20]
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            if (r3 != r5) goto L_0x030f
            java.lang.String[] r1 = new java.lang.String[r8]
            java.lang.String r0 = "authority"
            r1[r20] = r0
            r7 = r1
            r21 = r2
            goto L_0x0316
        L_0x02e8:
            r8 = 1
            r20 = 0
            int r3 = r2.getModifiers()
            r3 = r3 & r8
            if (r3 == 0) goto L_0x02f4
            r6 = r8
            goto L_0x02f6
        L_0x02f4:
            r6 = r20
        L_0x02f6:
            if (r6 != 0) goto L_0x02f9
            goto L_0x030f
        L_0x02f9:
            java.lang.String[] r3 = com.alibaba.fastjson.util.ASMUtils.lookupParameterNames(r2)
            if (r3 == 0) goto L_0x030f
            int r5 = r3.length
            if (r5 != 0) goto L_0x0303
            goto L_0x030f
        L_0x0303:
            if (r21 == 0) goto L_0x030c
            if (r7 == 0) goto L_0x030c
            int r5 = r3.length
            int r6 = r7.length
            if (r5 > r6) goto L_0x030c
            goto L_0x030f
        L_0x030c:
            r21 = r2
            r7 = r3
        L_0x030f:
            int r4 = r4 + 1
            goto L_0x0251
        L_0x0313:
            r8 = 1
            r20 = 0
        L_0x0316:
            if (r7 == 0) goto L_0x031e
            java.lang.Class[] r0 = r21.getParameterTypes()
            r6 = r0
            goto L_0x031f
        L_0x031e:
            r6 = 0
        L_0x031f:
            if (r7 == 0) goto L_0x03f4
            int r0 = r6.length
            int r1 = r7.length
            if (r0 != r1) goto L_0x03f4
            java.lang.annotation.Annotation[][] r22 = com.alibaba.fastjson.util.TypeUtils.getParameterAnnotations((java.lang.reflect.Constructor) r21)
            r5 = r20
        L_0x032b:
            int r0 = r6.length
            if (r5 >= r0) goto L_0x03d2
            r0 = r22[r5]
            r1 = r7[r5]
            int r2 = r0.length
            r4 = r20
        L_0x0335:
            if (r4 >= r2) goto L_0x0345
            r3 = r0[r4]
            boolean r8 = r3 instanceof com.alibaba.fastjson.annotation.JSONField
            if (r8 == 0) goto L_0x0341
            r0 = r3
            com.alibaba.fastjson.annotation.JSONField r0 = (com.alibaba.fastjson.annotation.JSONField) r0
            goto L_0x0346
        L_0x0341:
            int r4 = r4 + 1
            r8 = 1
            goto L_0x0335
        L_0x0345:
            r0 = 0
        L_0x0346:
            r3 = r6[r5]
            java.lang.reflect.Type[] r2 = r21.getGenericParameterTypes()
            r4 = r2[r5]
            java.lang.reflect.Field r8 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r1, r10)
            if (r8 == 0) goto L_0x035e
            if (r0 != 0) goto L_0x035e
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r0 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r0 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Field) r8, r0)
            com.alibaba.fastjson.annotation.JSONField r0 = (com.alibaba.fastjson.annotation.JSONField) r0
        L_0x035e:
            if (r0 != 0) goto L_0x0382
            java.lang.String r0 = "org.springframework.security.core.userdetails.User"
            boolean r0 = r0.equals(r9)
            if (r0 == 0) goto L_0x037b
            java.lang.String r0 = "password"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x037b
            com.alibaba.fastjson.parser.Feature r0 = com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty
            int r0 = r0.mask
            r28 = r0
            r26 = r20
            r27 = r26
            goto L_0x03a7
        L_0x037b:
            r26 = r20
            r27 = r26
            r28 = r27
            goto L_0x03a7
        L_0x0382:
            java.lang.String r2 = r0.name()
            int r26 = r2.length()
            if (r26 == 0) goto L_0x038d
            r1 = r2
        L_0x038d:
            int r2 = r0.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r26 = r0.serialzeFeatures()
            int r26 = com.alibaba.fastjson.serializer.SerializerFeature.of(r26)
            com.alibaba.fastjson.parser.Feature[] r0 = r0.parseFeatures()
            int r0 = com.alibaba.fastjson.parser.Feature.of(r0)
            r28 = r0
            r27 = r26
            r26 = r2
        L_0x03a7:
            com.alibaba.fastjson.util.FieldInfo r2 = new com.alibaba.fastjson.util.FieldInfo
            r0 = r2
            r55 = r9
            r9 = r2
            r2 = r50
            r29 = r5
            r5 = r8
            r30 = r6
            r6 = r26
            r26 = r7
            r7 = r27
            r24 = 1
            r8 = r28
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            add(r14, r9)
            int r5 = r29 + 1
            r9 = r55
            r8 = r24
            r7 = r26
            r6 = r30
            r20 = 0
            goto L_0x032b
        L_0x03d2:
            r24 = r8
            if (r17 != 0) goto L_0x040a
            java.lang.String r0 = r50.getName()
            java.lang.String r1 = "javax.servlet.http.Cookie"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x040a
            com.alibaba.fastjson.util.JavaBeanInfo r9 = new com.alibaba.fastjson.util.JavaBeanInfo
            r5 = 0
            r6 = 0
            r3 = 0
            r0 = r9
            r1 = r50
            r2 = r11
            r4 = r21
            r7 = r25
            r8 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x03f4:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "default constructor not found. "
            r1.<init>(r2)
            r1.append(r13)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0408:
            r24 = 1
        L_0x040a:
            r20 = r12
            goto L_0x0417
        L_0x040d:
            r14 = r5
            r24 = r6
            r23 = r12
            r25 = r15
            r15 = r8
            r21 = 0
        L_0x0417:
            if (r18 == 0) goto L_0x041c
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r18)
        L_0x041c:
            java.lang.String r12 = "set"
            if (r11 == 0) goto L_0x05db
            java.lang.Class<com.alibaba.fastjson.annotation.JSONPOJOBuilder> r0 = com.alibaba.fastjson.annotation.JSONPOJOBuilder.class
            java.lang.annotation.Annotation r0 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.Class<?>) r11, r0)
            com.alibaba.fastjson.annotation.JSONPOJOBuilder r0 = (com.alibaba.fastjson.annotation.JSONPOJOBuilder) r0
            if (r0 == 0) goto L_0x042f
            java.lang.String r7 = r0.withPrefix()
            goto L_0x0430
        L_0x042f:
            r7 = 0
        L_0x0430:
            if (r7 != 0) goto L_0x0434
            java.lang.String r7 = "with"
        L_0x0434:
            r9 = r7
            java.lang.reflect.Method[] r8 = r11.getMethods()
            int r7 = r8.length
            r6 = 0
        L_0x043b:
            if (r6 >= r7) goto L_0x059e
            r2 = r8[r6]
            int r0 = r2.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
            if (r0 == 0) goto L_0x045a
        L_0x0449:
            r31 = r6
            r35 = r7
            r36 = r8
            r22 = r9
            r37 = r10
            r38 = r11
            r13 = r12
            r39 = r23
            goto L_0x0589
        L_0x045a:
            java.lang.Class r0 = r2.getReturnType()
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0465
            goto L_0x0449
        L_0x0465:
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r0 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r0 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Method) r2, r0)
            com.alibaba.fastjson.annotation.JSONField r0 = (com.alibaba.fastjson.annotation.JSONField) r0
            if (r0 != 0) goto L_0x0473
            com.alibaba.fastjson.annotation.JSONField r0 = com.alibaba.fastjson.util.TypeUtils.getSuperMethodAnnotation(r13, r2)
        L_0x0473:
            r22 = r0
            if (r22 == 0) goto L_0x04ed
            boolean r0 = r22.deserialize()
            if (r0 != 0) goto L_0x047e
            goto L_0x0449
        L_0x047e:
            int r26 = r22.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r0 = r22.serialzeFeatures()
            int r27 = com.alibaba.fastjson.serializer.SerializerFeature.of(r0)
            com.alibaba.fastjson.parser.Feature[] r0 = r22.parseFeatures()
            int r28 = com.alibaba.fastjson.parser.Feature.of(r0)
            java.lang.String r0 = r22.name()
            int r0 = r0.length()
            if (r0 == 0) goto L_0x04d7
            java.lang.String r1 = r22.name()
            com.alibaba.fastjson.util.FieldInfo r5 = new com.alibaba.fastjson.util.FieldInfo
            r29 = 0
            r30 = 0
            r3 = 0
            r0 = r5
            r4 = r50
            r34 = r5
            r5 = r51
            r31 = r6
            r6 = r26
            r35 = r7
            r7 = r27
            r36 = r8
            r8 = r28
            r55 = r9
            r9 = r22
            r37 = r10
            r10 = r29
            r38 = r11
            r11 = r30
            r13 = r12
            r39 = r23
            r12 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r0 = r34
            add(r14, r0)
            r22 = r55
            goto L_0x0589
        L_0x04d7:
            r31 = r6
            r35 = r7
            r36 = r8
            r55 = r9
            r37 = r10
            r38 = r11
            r13 = r12
            r39 = r23
            r6 = r26
            r7 = r27
            r8 = r28
            goto L_0x04ff
        L_0x04ed:
            r31 = r6
            r35 = r7
            r36 = r8
            r55 = r9
            r37 = r10
            r38 = r11
            r13 = r12
            r39 = r23
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x04ff:
            java.lang.String r0 = r2.getName()
            boolean r1 = r0.startsWith(r13)
            if (r1 == 0) goto L_0x051a
            int r1 = r0.length()
            r3 = 3
            if (r1 <= r3) goto L_0x051a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = r0.substring(r3)
            r1.<init>(r0)
            goto L_0x0525
        L_0x051a:
            int r1 = r55.length()
            if (r1 != 0) goto L_0x0529
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
        L_0x0525:
            r12 = r55
        L_0x0527:
            r11 = 0
            goto L_0x054d
        L_0x0529:
            r12 = r55
            boolean r1 = r0.startsWith(r12)
            if (r1 != 0) goto L_0x0534
        L_0x0531:
            r22 = r12
            goto L_0x0589
        L_0x0534:
            int r1 = r0.length()
            int r3 = r12.length()
            if (r1 > r3) goto L_0x053f
            goto L_0x0531
        L_0x053f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r3 = r12.length()
            java.lang.String r0 = r0.substring(r3)
            r1.<init>(r0)
            goto L_0x0527
        L_0x054d:
            char r0 = r1.charAt(r11)
            int r3 = r12.length()
            if (r3 == 0) goto L_0x055e
            boolean r3 = java.lang.Character.isUpperCase(r0)
            if (r3 != 0) goto L_0x055e
            goto L_0x0531
        L_0x055e:
            char r0 = java.lang.Character.toLowerCase(r0)
            r1.setCharAt(r11, r0)
            java.lang.String r1 = r1.toString()
            com.alibaba.fastjson.util.FieldInfo r10 = new com.alibaba.fastjson.util.FieldInfo
            r23 = 0
            r24 = 0
            r3 = 0
            r0 = r10
            r4 = r50
            r5 = r51
            r9 = r22
            r40 = r10
            r10 = r23
            r11 = r24
            r22 = r12
            r12 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r0 = r40
            add(r14, r0)
        L_0x0589:
            int r6 = r31 + 1
            r12 = r13
            r9 = r22
            r7 = r35
            r8 = r36
            r10 = r37
            r11 = r38
            r23 = r39
            r24 = 1
            r13 = r50
            goto L_0x043b
        L_0x059e:
            r37 = r10
            r13 = r12
            r39 = r23
            r12 = r11
            if (r12 == 0) goto L_0x05e1
            java.lang.Class<com.alibaba.fastjson.annotation.JSONPOJOBuilder> r0 = com.alibaba.fastjson.annotation.JSONPOJOBuilder.class
            java.lang.annotation.Annotation r0 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.Class<?>) r12, r0)
            com.alibaba.fastjson.annotation.JSONPOJOBuilder r0 = (com.alibaba.fastjson.annotation.JSONPOJOBuilder) r0
            if (r0 == 0) goto L_0x05b5
            java.lang.String r7 = r0.buildMethod()
            goto L_0x05b6
        L_0x05b5:
            r7 = 0
        L_0x05b6:
            if (r7 == 0) goto L_0x05be
            int r0 = r7.length()
            if (r0 != 0) goto L_0x05c0
        L_0x05be:
            java.lang.String r7 = "build"
        L_0x05c0:
            r11 = 0
            java.lang.reflect.Method r19 = r12.getMethod(r7, r11)     // Catch:{ NoSuchMethodException | SecurityException -> 0x05c5 }
        L_0x05c5:
            if (r19 != 0) goto L_0x05cd
            java.lang.String r0 = "create"
            java.lang.reflect.Method r19 = r12.getMethod(r0, r11)     // Catch:{ NoSuchMethodException | SecurityException -> 0x05cd }
        L_0x05cd:
            if (r19 == 0) goto L_0x05d3
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r19)
            goto L_0x05e2
        L_0x05d3:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.String r1 = "buildMethod not found."
            r0.<init>(r1)
            throw r0
        L_0x05db:
            r37 = r10
            r13 = r12
            r39 = r23
            r12 = r11
        L_0x05e1:
            r11 = 0
        L_0x05e2:
            int r10 = r15.length
            r9 = 0
        L_0x05e4:
            java.lang.String r8 = "get"
            r7 = 4
            if (r9 >= r10) goto L_0x09ac
            r2 = r15[r9]
            java.lang.String r0 = r2.getName()
            int r1 = r2.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isStatic(r1)
            if (r1 == 0) goto L_0x0613
        L_0x05f9:
            r41 = r9
            r30 = r10
            r27 = r11
            r28 = r12
            r35 = r13
            r34 = r15
            r46 = r39
            r29 = 2
        L_0x0609:
            r31 = 0
            r32 = 3
            r33 = 1
        L_0x060f:
            r13 = r50
            goto L_0x099c
        L_0x0613:
            java.lang.Class r1 = r2.getReturnType()
            java.lang.Class r3 = java.lang.Void.TYPE
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x062a
            java.lang.Class r3 = r2.getDeclaringClass()
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x062a
            goto L_0x05f9
        L_0x062a:
            java.lang.Class r1 = r2.getDeclaringClass()
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            if (r1 != r3) goto L_0x0633
            goto L_0x05f9
        L_0x0633:
            java.lang.Class[] r1 = r2.getParameterTypes()
            int r3 = r1.length
            if (r3 == 0) goto L_0x05f9
            int r3 = r1.length
            r6 = 2
            if (r3 <= r6) goto L_0x064f
            r29 = r6
            r41 = r9
            r30 = r10
            r27 = r11
            r28 = r12
            r35 = r13
            r34 = r15
            r46 = r39
            goto L_0x0609
        L_0x064f:
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r3 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r3 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Method) r2, r3)
            r22 = r3
            com.alibaba.fastjson.annotation.JSONField r22 = (com.alibaba.fastjson.annotation.JSONField) r22
            r23 = 0
            r24 = 0
            r26 = 0
            if (r22 == 0) goto L_0x06b1
            int r3 = r1.length
            if (r3 != r6) goto L_0x06b1
            r5 = 0
            r3 = r1[r5]
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            if (r3 != r4) goto L_0x06b1
            r4 = 1
            r3 = r1[r4]
            java.lang.Class<java.lang.Object> r4 = java.lang.Object.class
            if (r3 != r4) goto L_0x06b1
            com.alibaba.fastjson.util.FieldInfo r8 = new com.alibaba.fastjson.util.FieldInfo
            r27 = 0
            r28 = 0
            java.lang.String r1 = ""
            r3 = 0
            r0 = r8
            r7 = 1
            r4 = r50
            r5 = r51
            r29 = r6
            r6 = r23
            r7 = r24
            r42 = r8
            r8 = r26
            r41 = r9
            r9 = r22
            r30 = r10
            r10 = r27
            r27 = r11
            r11 = r28
            r28 = r12
            r12 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r0 = r42
            add(r14, r0)
            r31 = 0
            r32 = 3
            r33 = 1
            r35 = r13
            r34 = r15
            r46 = r39
            goto L_0x060f
        L_0x06b1:
            r29 = r6
            r41 = r9
            r30 = r10
            r27 = r11
            r28 = r12
            int r3 = r1.length
            r12 = 1
            if (r3 == r12) goto L_0x06cd
            r33 = r12
            r35 = r13
        L_0x06c3:
            r34 = r15
            r46 = r39
        L_0x06c7:
            r31 = 0
            r32 = 3
            goto L_0x060f
        L_0x06cd:
            r11 = r13
            r13 = r50
            if (r22 != 0) goto L_0x06d8
            com.alibaba.fastjson.annotation.JSONField r3 = com.alibaba.fastjson.util.TypeUtils.getSuperMethodAnnotation(r13, r2)
            r9 = r3
            goto L_0x06da
        L_0x06d8:
            r9 = r22
        L_0x06da:
            if (r9 != 0) goto L_0x06f0
            int r3 = r0.length()
            if (r3 >= r7) goto L_0x06f0
        L_0x06e2:
            r35 = r11
            r33 = r12
        L_0x06e6:
            r34 = r15
            r46 = r39
            r31 = 0
            r32 = 3
            goto L_0x099c
        L_0x06f0:
            if (r9 == 0) goto L_0x0751
            boolean r3 = r9.deserialize()
            if (r3 != 0) goto L_0x06f9
            goto L_0x06e2
        L_0x06f9:
            int r6 = r9.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r3 = r9.serialzeFeatures()
            int r10 = com.alibaba.fastjson.serializer.SerializerFeature.of(r3)
            com.alibaba.fastjson.parser.Feature[] r3 = r9.parseFeatures()
            int r22 = com.alibaba.fastjson.parser.Feature.of(r3)
            java.lang.String r3 = r9.name()
            int r3 = r3.length()
            if (r3 == 0) goto L_0x0747
            java.lang.String r1 = r9.name()
            com.alibaba.fastjson.util.FieldInfo r8 = new com.alibaba.fastjson.util.FieldInfo
            r23 = 0
            r24 = 0
            r3 = 0
            r0 = r8
            r4 = r50
            r5 = r51
            r7 = r10
            r10 = r8
            r8 = r22
            r43 = r10
            r10 = r23
            r44 = r11
            r11 = r24
            r13 = r12
            r12 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r0 = r43
            add(r14, r0)
            r33 = r13
            r34 = r15
            r46 = r39
            r35 = r44
            goto L_0x06c7
        L_0x0747:
            r44 = r11
            r13 = r12
            r23 = r6
            r24 = r10
            r26 = r22
            goto L_0x0754
        L_0x0751:
            r44 = r11
            r13 = r12
        L_0x0754:
            r12 = r44
            if (r9 != 0) goto L_0x0765
            boolean r3 = r0.startsWith(r12)
            if (r3 == 0) goto L_0x075f
            goto L_0x0765
        L_0x075f:
            r35 = r12
            r33 = r13
            goto L_0x06c3
        L_0x0765:
            if (r28 == 0) goto L_0x0768
            goto L_0x075f
        L_0x0768:
            r3 = 3
            char r4 = r0.charAt(r3)
            if (r17 == 0) goto L_0x0790
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r5 = 0
        L_0x0775:
            int r6 = r15.length
            if (r5 >= r6) goto L_0x0792
            r6 = r15[r5]
            java.lang.String r6 = r6.getName()
            boolean r6 = r6.startsWith(r8)
            if (r6 == 0) goto L_0x078d
            r6 = r15[r5]
            java.lang.String r6 = r6.getName()
            r3.add(r6)
        L_0x078d:
            int r5 = r5 + 1
            goto L_0x0775
        L_0x0790:
            r3 = r27
        L_0x0792:
            boolean r5 = java.lang.Character.isUpperCase(r4)
            java.lang.String r6 = "g"
            java.lang.String r8 = "is"
            if (r5 != 0) goto L_0x0840
            r5 = 512(0x200, float:7.175E-43)
            if (r4 <= r5) goto L_0x07a2
            goto L_0x0840
        L_0x07a2:
            r5 = 95
            if (r4 != r5) goto L_0x0801
            if (r17 == 0) goto L_0x07df
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r6)
            java.lang.String r5 = r0.substring(r13)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            boolean r3 = r3.contains(r4)
            if (r3 == 0) goto L_0x07c4
            r3 = 3
            java.lang.String r0 = r0.substring(r3)
            goto L_0x07d5
        L_0x07c4:
            r3 = 3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r8)
            java.lang.String r0 = r0.substring(r3)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
        L_0x07d5:
            r10 = r13
            r11 = r37
            r13 = r50
            java.lang.reflect.Field r7 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r0, r11)
            goto L_0x07fa
        L_0x07df:
            r10 = r13
            r11 = r37
            r3 = 3
            r13 = r50
            java.lang.String r4 = r0.substring(r7)
            java.lang.reflect.Field r7 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r4, r11)
            if (r7 != 0) goto L_0x07fd
            java.lang.String r0 = r0.substring(r3)
            java.lang.reflect.Field r7 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r0, r11)
            if (r7 != 0) goto L_0x07fa
            r0 = r4
        L_0x07fa:
            r6 = r3
            goto L_0x08a0
        L_0x07fd:
            r6 = r3
            r0 = r4
            goto L_0x08a0
        L_0x0801:
            r10 = r13
            r11 = r37
            r3 = 3
            r13 = r50
            r5 = 102(0x66, float:1.43E-43)
            if (r4 != r5) goto L_0x0812
            java.lang.String r0 = r0.substring(r3)
        L_0x080f:
            r6 = r3
            goto L_0x089e
        L_0x0812:
            int r4 = r0.length()
            r5 = 5
            if (r4 < r5) goto L_0x082c
            char r4 = r0.charAt(r7)
            boolean r4 = java.lang.Character.isUpperCase(r4)
            if (r4 == 0) goto L_0x082c
            java.lang.String r0 = r0.substring(r3)
            java.lang.String r0 = com.alibaba.fastjson.util.TypeUtils.decapitalize(r0)
            goto L_0x080f
        L_0x082c:
            java.lang.String r0 = r0.substring(r3)
            java.lang.reflect.Field r7 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r0, r11)
            if (r7 != 0) goto L_0x083e
            r33 = r10
            r37 = r11
            r35 = r12
            goto L_0x06e6
        L_0x083e:
            r6 = 3
            goto L_0x08a0
        L_0x0840:
            r10 = r13
            r11 = r37
            r13 = r50
            if (r17 == 0) goto L_0x0875
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r6)
            java.lang.String r0 = r0.substring(r10)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            boolean r3 = r3.contains(r0)
            if (r3 == 0) goto L_0x0863
            r6 = 3
            java.lang.String r0 = r0.substring(r6)
            goto L_0x089e
        L_0x0863:
            r6 = 3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r8)
            java.lang.String r0 = r0.substring(r6)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            goto L_0x089e
        L_0x0875:
            r6 = 3
            boolean r3 = com.alibaba.fastjson.util.TypeUtils.compatibleWithJavaBean
            if (r3 == 0) goto L_0x0883
            java.lang.String r0 = r0.substring(r6)
            java.lang.String r0 = com.alibaba.fastjson.util.TypeUtils.decapitalize(r0)
            goto L_0x089e
        L_0x0883:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            char r4 = r0.charAt(r6)
            char r4 = java.lang.Character.toLowerCase(r4)
            r3.append(r4)
            java.lang.String r0 = r0.substring(r7)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
        L_0x089e:
            r7 = r27
        L_0x08a0:
            if (r7 != 0) goto L_0x08a6
            java.lang.reflect.Field r7 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r0, r11)
        L_0x08a6:
            r5 = 0
            if (r7 != 0) goto L_0x08d0
            r1 = r1[r5]
            java.lang.Class r3 = java.lang.Boolean.TYPE
            if (r1 != r3) goto L_0x08d0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r8)
            char r3 = r0.charAt(r5)
            char r3 = java.lang.Character.toUpperCase(r3)
            r1.append(r3)
            java.lang.String r3 = r0.substring(r10)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.reflect.Field r1 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r1, r11)
            r3 = r1
            goto L_0x08d1
        L_0x08d0:
            r3 = r7
        L_0x08d1:
            if (r3 == 0) goto L_0x0968
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r1 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r1 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Field) r3, r1)
            r22 = r1
            com.alibaba.fastjson.annotation.JSONField r22 = (com.alibaba.fastjson.annotation.JSONField) r22
            if (r22 == 0) goto L_0x0954
            boolean r1 = r22.deserialize()
            if (r1 != 0) goto L_0x08f5
            r31 = r5
            r32 = r6
            r33 = r10
            r37 = r11
            r35 = r12
            r34 = r15
        L_0x08f1:
            r46 = r39
            goto L_0x099c
        L_0x08f5:
            int r7 = r22.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r1 = r22.serialzeFeatures()
            int r8 = com.alibaba.fastjson.serializer.SerializerFeature.of(r1)
            com.alibaba.fastjson.parser.Feature[] r1 = r22.parseFeatures()
            int r23 = com.alibaba.fastjson.parser.Feature.of(r1)
            java.lang.String r1 = r22.name()
            int r1 = r1.length()
            if (r1 == 0) goto L_0x0942
            java.lang.String r1 = r22.name()
            com.alibaba.fastjson.util.FieldInfo r4 = new com.alibaba.fastjson.util.FieldInfo
            r24 = 0
            r0 = r4
            r45 = r4
            r4 = r50
            r31 = r5
            r5 = r51
            r32 = r6
            r6 = r7
            r7 = r8
            r8 = r23
            r33 = r10
            r10 = r22
            r34 = r15
            r15 = r11
            r11 = r24
            r35 = r12
            r12 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r0 = r45
            add(r14, r0)
            r37 = r15
            goto L_0x08f1
        L_0x0942:
            r31 = r5
            r32 = r6
            r33 = r10
            r35 = r12
            r34 = r15
            r15 = r11
            r6 = r7
            r7 = r8
            r10 = r22
            r8 = r23
            goto L_0x097b
        L_0x0954:
            r31 = r5
            r32 = r6
            r33 = r10
            r35 = r12
            r34 = r15
            r15 = r11
            r10 = r22
            r6 = r23
            r7 = r24
            r8 = r26
            goto L_0x097b
        L_0x0968:
            r31 = r5
            r32 = r6
            r33 = r10
            r35 = r12
            r34 = r15
            r15 = r11
            r6 = r23
            r7 = r24
            r8 = r26
            r10 = r27
        L_0x097b:
            r12 = r39
            if (r12 == 0) goto L_0x0983
            java.lang.String r0 = r12.translate(r0)
        L_0x0983:
            r1 = r0
            com.alibaba.fastjson.util.FieldInfo r11 = new com.alibaba.fastjson.util.FieldInfo
            r22 = 0
            r0 = r11
            r4 = r50
            r5 = r51
            r37 = r15
            r15 = r11
            r11 = r22
            r46 = r12
            r12 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            add(r14, r15)
        L_0x099c:
            int r9 = r41 + 1
            r11 = r27
            r12 = r28
            r10 = r30
            r15 = r34
            r13 = r35
            r39 = r46
            goto L_0x05e4
        L_0x09ac:
            r13 = r50
            r28 = r12
            r46 = r39
            r31 = 0
            r32 = 3
            r33 = 1
            java.lang.reflect.Field[] r0 = r50.getFields()
            r15 = r51
            r11 = r32
            r12 = r46
            computeFields(r13, r15, r12, r14, r0)
            java.lang.reflect.Method[] r10 = r50.getMethods()
            int r9 = r10.length
            r6 = r31
        L_0x09cc:
            if (r6 >= r9) goto L_0x0b05
            r2 = r10[r6]
            java.lang.String r0 = r2.getName()
            int r1 = r0.length()
            if (r1 >= r7) goto L_0x09ec
        L_0x09da:
            r31 = r6
            r24 = r7
            r26 = r8
            r27 = r9
            r17 = r10
            r22 = r11
            r49 = r12
            r48 = r37
            goto L_0x0af3
        L_0x09ec:
            int r1 = r2.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isStatic(r1)
            if (r1 == 0) goto L_0x09f7
            goto L_0x09da
        L_0x09f7:
            if (r28 != 0) goto L_0x09da
            boolean r1 = r0.startsWith(r8)
            if (r1 == 0) goto L_0x09da
            char r1 = r0.charAt(r11)
            boolean r1 = java.lang.Character.isUpperCase(r1)
            if (r1 == 0) goto L_0x09da
            java.lang.Class[] r1 = r2.getParameterTypes()
            int r1 = r1.length
            if (r1 == 0) goto L_0x0a11
            goto L_0x09da
        L_0x0a11:
            java.lang.Class<java.util.Collection> r1 = java.util.Collection.class
            java.lang.Class r3 = r2.getReturnType()
            boolean r1 = r1.isAssignableFrom(r3)
            if (r1 != 0) goto L_0x0a41
            java.lang.Class<java.util.Map> r1 = java.util.Map.class
            java.lang.Class r3 = r2.getReturnType()
            boolean r1 = r1.isAssignableFrom(r3)
            if (r1 != 0) goto L_0x0a41
            java.lang.Class<java.util.concurrent.atomic.AtomicBoolean> r1 = java.util.concurrent.atomic.AtomicBoolean.class
            java.lang.Class r3 = r2.getReturnType()
            if (r1 == r3) goto L_0x0a41
            java.lang.Class<java.util.concurrent.atomic.AtomicInteger> r1 = java.util.concurrent.atomic.AtomicInteger.class
            java.lang.Class r3 = r2.getReturnType()
            if (r1 == r3) goto L_0x0a41
            java.lang.Class<java.util.concurrent.atomic.AtomicLong> r1 = java.util.concurrent.atomic.AtomicLong.class
            java.lang.Class r3 = r2.getReturnType()
            if (r1 != r3) goto L_0x09da
        L_0x0a41:
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r1 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r1 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Method) r2, r1)
            r17 = r1
            com.alibaba.fastjson.annotation.JSONField r17 = (com.alibaba.fastjson.annotation.JSONField) r17
            if (r17 == 0) goto L_0x0a54
            boolean r1 = r17.deserialize()
            if (r1 == 0) goto L_0x0a54
            goto L_0x09da
        L_0x0a54:
            if (r17 == 0) goto L_0x0a67
            java.lang.String r1 = r17.name()
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x0a67
            java.lang.String r0 = r17.name()
            r5 = r37
            goto L_0x0a9b
        L_0x0a67:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            char r3 = r0.charAt(r11)
            char r3 = java.lang.Character.toLowerCase(r3)
            r1.append(r3)
            java.lang.String r0 = r0.substring(r7)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r5 = r37
            java.lang.reflect.Field r1 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r0, r5)
            if (r1 == 0) goto L_0x0a9b
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r3 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r1 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Field) r1, r3)
            com.alibaba.fastjson.annotation.JSONField r1 = (com.alibaba.fastjson.annotation.JSONField) r1
            if (r1 == 0) goto L_0x0a9b
            boolean r1 = r1.deserialize()
            if (r1 != 0) goto L_0x0a9b
            goto L_0x0aa8
        L_0x0a9b:
            if (r12 == 0) goto L_0x0aa1
            java.lang.String r0 = r12.translate(r0)
        L_0x0aa1:
            r1 = r0
            com.alibaba.fastjson.util.FieldInfo r0 = getField(r14, r1)
            if (r0 == 0) goto L_0x0ab9
        L_0x0aa8:
            r48 = r5
            r31 = r6
            r24 = r7
            r26 = r8
            r27 = r9
            r17 = r10
            r22 = r11
            r49 = r12
            goto L_0x0af3
        L_0x0ab9:
            com.alibaba.fastjson.util.FieldInfo r4 = new com.alibaba.fastjson.util.FieldInfo
            r22 = 0
            r23 = 0
            r3 = 0
            r24 = 0
            r26 = 0
            r27 = 0
            r0 = r4
            r47 = r4
            r4 = r50
            r48 = r5
            r5 = r51
            r31 = r6
            r6 = r24
            r24 = r7
            r7 = r26
            r26 = r8
            r8 = r27
            r27 = r9
            r9 = r17
            r17 = r10
            r10 = r22
            r22 = r11
            r11 = r23
            r49 = r12
            r12 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r0 = r47
            add(r14, r0)
        L_0x0af3:
            int r6 = r31 + 1
            r10 = r17
            r11 = r22
            r7 = r24
            r8 = r26
            r9 = r27
            r37 = r48
            r12 = r49
            goto L_0x09cc
        L_0x0b05:
            r49 = r12
            r48 = r37
            int r0 = r14.size()
            if (r0 != 0) goto L_0x0b29
            boolean r0 = com.alibaba.fastjson.util.TypeUtils.isXmlField(r50)
            if (r0 == 0) goto L_0x0b16
            goto L_0x0b18
        L_0x0b16:
            r33 = r53
        L_0x0b18:
            if (r33 == 0) goto L_0x0b29
            r0 = r13
        L_0x0b1b:
            if (r0 == 0) goto L_0x0b29
            r1 = r48
            r2 = r49
            computeFields(r13, r15, r2, r14, r1)
            java.lang.Class r0 = r0.getSuperclass()
            goto L_0x0b1b
        L_0x0b29:
            com.alibaba.fastjson.util.JavaBeanInfo r9 = new com.alibaba.fastjson.util.JavaBeanInfo
            r0 = r9
            r1 = r50
            r2 = r28
            r3 = r18
            r4 = r21
            r5 = r20
            r6 = r19
            r7 = r25
            r8 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.JavaBeanInfo.build(java.lang.Class, java.lang.reflect.Type, com.alibaba.fastjson.PropertyNamingStrategy, boolean, boolean, boolean):com.alibaba.fastjson.util.JavaBeanInfo");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        if (java.util.concurrent.atomic.AtomicBoolean.class.equals(r2) == false) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void computeFields(java.lang.Class<?> r20, java.lang.reflect.Type r21, com.alibaba.fastjson.PropertyNamingStrategy r22, java.util.List<com.alibaba.fastjson.util.FieldInfo> r23, java.lang.reflect.Field[] r24) {
        /*
            r0 = r22
            r1 = r24
            java.util.Map r15 = buildGenericInfo(r20)
            int r14 = r1.length
            r16 = 0
            r13 = r16
        L_0x000d:
            if (r13 >= r14) goto L_0x00dc
            r5 = r1[r13]
            int r2 = r5.getModifiers()
            r3 = r2 & 8
            if (r3 == 0) goto L_0x0021
        L_0x0019:
            r2 = r23
            r17 = r13
            r18 = r14
            goto L_0x00d6
        L_0x0021:
            r2 = r2 & 16
            if (r2 == 0) goto L_0x0051
            java.lang.Class r2 = r5.getType()
            java.lang.Class<java.util.Map> r3 = java.util.Map.class
            boolean r3 = r3.isAssignableFrom(r2)
            if (r3 != 0) goto L_0x0051
            java.lang.Class<java.util.Collection> r3 = java.util.Collection.class
            boolean r3 = r3.isAssignableFrom(r2)
            if (r3 != 0) goto L_0x0051
            java.lang.Class<java.util.concurrent.atomic.AtomicLong> r3 = java.util.concurrent.atomic.AtomicLong.class
            boolean r3 = r3.equals(r2)
            if (r3 != 0) goto L_0x0051
            java.lang.Class<java.util.concurrent.atomic.AtomicInteger> r3 = java.util.concurrent.atomic.AtomicInteger.class
            boolean r3 = r3.equals(r2)
            if (r3 != 0) goto L_0x0051
            java.lang.Class<java.util.concurrent.atomic.AtomicBoolean> r3 = java.util.concurrent.atomic.AtomicBoolean.class
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0019
        L_0x0051:
            java.util.Iterator r2 = r23.iterator()
        L_0x0055:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x006e
            java.lang.Object r3 = r2.next()
            com.alibaba.fastjson.util.FieldInfo r3 = (com.alibaba.fastjson.util.FieldInfo) r3
            java.lang.String r3 = r3.name
            java.lang.String r4 = r5.getName()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0055
            goto L_0x0019
        L_0x006e:
            java.lang.String r2 = r5.getName()
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r3 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r3 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Field) r5, r3)
            r12 = r3
            com.alibaba.fastjson.annotation.JSONField r12 = (com.alibaba.fastjson.annotation.JSONField) r12
            if (r12 == 0) goto L_0x00aa
            boolean r3 = r12.deserialize()
            if (r3 != 0) goto L_0x0084
            goto L_0x0019
        L_0x0084:
            int r3 = r12.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r4 = r12.serialzeFeatures()
            int r4 = com.alibaba.fastjson.serializer.SerializerFeature.of(r4)
            com.alibaba.fastjson.parser.Feature[] r6 = r12.parseFeatures()
            int r6 = com.alibaba.fastjson.parser.Feature.of(r6)
            java.lang.String r7 = r12.name()
            int r7 = r7.length()
            if (r7 == 0) goto L_0x00a6
            java.lang.String r2 = r12.name()
        L_0x00a6:
            r8 = r3
            r9 = r4
            r10 = r6
            goto L_0x00ae
        L_0x00aa:
            r8 = r16
            r9 = r8
            r10 = r9
        L_0x00ae:
            if (r0 == 0) goto L_0x00b4
            java.lang.String r2 = r0.translate(r2)
        L_0x00b4:
            r3 = r2
            com.alibaba.fastjson.util.FieldInfo r11 = new com.alibaba.fastjson.util.FieldInfo
            r17 = 0
            r18 = 0
            r4 = 0
            r2 = r11
            r6 = r20
            r7 = r21
            r19 = r11
            r11 = r17
            r17 = r13
            r13 = r18
            r18 = r14
            r14 = r15
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            r2 = r23
            r3 = r19
            add(r2, r3)
        L_0x00d6:
            int r13 = r17 + 1
            r14 = r18
            goto L_0x000d
        L_0x00dc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.JavaBeanInfo.computeFields(java.lang.Class, java.lang.reflect.Type, com.alibaba.fastjson.PropertyNamingStrategy, java.util.List, java.lang.reflect.Field[]):void");
    }

    static Constructor<?> getDefaultConstructor(Class<?> cls, Constructor<?>[] constructorArr) {
        Constructor<?> constructor = null;
        if (Modifier.isAbstract(cls.getModifiers())) {
            return null;
        }
        int length = constructorArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Constructor<?> constructor2 = constructorArr[i];
            if (constructor2.getParameterTypes().length == 0) {
                constructor = constructor2;
                break;
            }
            i++;
        }
        if (constructor != null || !cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
            return constructor;
        }
        for (Constructor<?> constructor3 : constructorArr) {
            Class[] parameterTypes = constructor3.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].equals(cls.getDeclaringClass())) {
                return constructor3;
            }
        }
        return constructor;
    }

    public static Constructor<?> getCreatorConstructor(Constructor[] constructorArr) {
        Constructor<?> constructor = null;
        for (Constructor<?> constructor2 : constructorArr) {
            if (((JSONCreator) constructor2.getAnnotation(JSONCreator.class)) != null) {
                if (constructor == null) {
                    constructor = constructor2;
                } else {
                    throw new JSONException("multi-JSONCreator");
                }
            }
        }
        if (constructor != null) {
            return constructor;
        }
        for (Constructor<?> constructor3 : constructorArr) {
            Annotation[][] parameterAnnotations = TypeUtils.getParameterAnnotations((Constructor) constructor3);
            if (parameterAnnotations.length != 0) {
                int length = parameterAnnotations.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        Annotation[] annotationArr = parameterAnnotations[i];
                        int length2 = annotationArr.length;
                        int i2 = 0;
                        while (i2 < length2) {
                            if (annotationArr[i2] instanceof JSONField) {
                                i++;
                            } else {
                                i2++;
                            }
                        }
                        break;
                    } else if (constructor == null) {
                        constructor = constructor3;
                    } else {
                        throw new JSONException("multi-JSONCreator");
                    }
                }
            }
        }
        return constructor;
    }

    private static Method getFactoryMethod(Class<?> cls, Method[] methodArr, boolean z) {
        Method method = null;
        for (Method method2 : methodArr) {
            if (Modifier.isStatic(method2.getModifiers()) && cls.isAssignableFrom(method2.getReturnType()) && ((JSONCreator) TypeUtils.getAnnotation(method2, JSONCreator.class)) != null) {
                if (method == null) {
                    method = method2;
                } else {
                    throw new JSONException("multi-JSONCreator");
                }
            }
        }
        if (method != null || !z) {
            return method;
        }
        for (Method method3 : methodArr) {
            if (TypeUtils.isJacksonCreator(method3)) {
                return method3;
            }
        }
        return method;
    }

    public static Class<?> getBuilderClass(JSONType jSONType) {
        return getBuilderClass((Class<?>) null, jSONType);
    }

    public static Class<?> getBuilderClass(Class<?> cls, JSONType jSONType) {
        Class<?> builder;
        if (cls != null && cls.getName().equals("org.springframework.security.web.savedrequest.DefaultSavedRequest")) {
            return TypeUtils.loadClass("org.springframework.security.web.savedrequest.DefaultSavedRequest$Builder");
        }
        if (jSONType == null || (builder = jSONType.builder()) == Void.class) {
            return null;
        }
        return builder;
    }
}
