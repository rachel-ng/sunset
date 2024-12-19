package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FieldSerializer implements Comparable<FieldSerializer> {
    protected boolean browserCompatible;
    protected boolean disableCircularReferenceDetect = false;
    private final String double_quoted_fieldPrefix;
    protected int features;
    protected BeanContext fieldContext;
    public final FieldInfo fieldInfo;
    private String format;
    protected boolean persistenceXToMany = false;
    private RuntimeSerializerInfo runtimeInfo;
    protected boolean serializeUsing = false;
    private String single_quoted_fieldPrefix;
    private String un_quoted_fieldPrefix;
    protected boolean writeEnumUsingName = false;
    protected boolean writeEnumUsingToString = false;
    protected final boolean writeNull;

    public FieldSerializer(Class<?> cls, FieldInfo fieldInfo2) {
        boolean z;
        JSONType jSONType;
        boolean z2 = false;
        this.fieldInfo = fieldInfo2;
        this.fieldContext = new BeanContext(cls, fieldInfo2);
        if (!(cls == null || (jSONType = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class)) == null)) {
            for (SerializerFeature serializerFeature : jSONType.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.WriteEnumUsingToString) {
                    this.writeEnumUsingToString = true;
                } else if (serializerFeature == SerializerFeature.WriteEnumUsingName) {
                    this.writeEnumUsingName = true;
                } else if (serializerFeature == SerializerFeature.DisableCircularReferenceDetect) {
                    this.disableCircularReferenceDetect = true;
                } else if (serializerFeature == SerializerFeature.BrowserCompatible) {
                    this.features |= SerializerFeature.BrowserCompatible.mask;
                    this.browserCompatible = true;
                } else if (serializerFeature == SerializerFeature.WriteMapNullValue) {
                    this.features |= SerializerFeature.WriteMapNullValue.mask;
                }
            }
        }
        fieldInfo2.setAccessible();
        this.double_quoted_fieldPrefix = "\"" + fieldInfo2.name + "\":";
        JSONField annotation = fieldInfo2.getAnnotation();
        if (annotation != null) {
            SerializerFeature[] serialzeFeatures = annotation.serialzeFeatures();
            int length = serialzeFeatures.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                } else if ((serialzeFeatures[i].getMask() & SerializerFeature.WRITE_MAP_NULL_FEATURES) != 0) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            String format2 = annotation.format();
            this.format = format2;
            if (format2.trim().length() == 0) {
                this.format = null;
            }
            for (SerializerFeature serializerFeature2 : annotation.serialzeFeatures()) {
                if (serializerFeature2 == SerializerFeature.WriteEnumUsingToString) {
                    this.writeEnumUsingToString = true;
                } else if (serializerFeature2 == SerializerFeature.WriteEnumUsingName) {
                    this.writeEnumUsingName = true;
                } else if (serializerFeature2 == SerializerFeature.DisableCircularReferenceDetect) {
                    this.disableCircularReferenceDetect = true;
                } else if (serializerFeature2 == SerializerFeature.BrowserCompatible) {
                    this.browserCompatible = true;
                }
            }
            this.features = SerializerFeature.of(annotation.serialzeFeatures()) | this.features;
        } else {
            z = false;
        }
        this.writeNull = z;
        this.persistenceXToMany = (TypeUtils.isAnnotationPresentOneToMany(fieldInfo2.method) || TypeUtils.isAnnotationPresentManyToMany(fieldInfo2.method)) ? true : z2;
    }

    public void writePrefix(JSONSerializer jSONSerializer) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (!serializeWriter.quoteFieldNames) {
            if (this.un_quoted_fieldPrefix == null) {
                this.un_quoted_fieldPrefix = this.fieldInfo.name + ":";
            }
            serializeWriter.write(this.un_quoted_fieldPrefix);
        } else if (SerializerFeature.isEnabled(serializeWriter.features, this.fieldInfo.serialzeFeatures, SerializerFeature.UseSingleQuotes)) {
            if (this.single_quoted_fieldPrefix == null) {
                this.single_quoted_fieldPrefix = "'" + this.fieldInfo.name + "':";
            }
            serializeWriter.write(this.single_quoted_fieldPrefix);
        } else {
            serializeWriter.write(this.double_quoted_fieldPrefix);
        }
    }

    public Object getPropertyValueDirect(Object obj) throws InvocationTargetException, IllegalAccessException {
        Object obj2 = this.fieldInfo.get(obj);
        if (!this.persistenceXToMany || TypeUtils.isHibernateInitialized(obj2)) {
            return obj2;
        }
        return null;
    }

    public Object getPropertyValue(Object obj) throws InvocationTargetException, IllegalAccessException {
        Object obj2 = this.fieldInfo.get(obj);
        if (this.format == null || obj2 == null) {
            return obj2;
        }
        if (this.fieldInfo.fieldClass != Date.class && this.fieldInfo.fieldClass != java.sql.Date.class) {
            return obj2;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.format, JSON.defaultLocale);
        simpleDateFormat.setTimeZone(JSON.defaultTimeZone);
        return simpleDateFormat.format(obj2);
    }

    public int compareTo(FieldSerializer fieldSerializer) {
        return this.fieldInfo.compareTo(fieldSerializer.fieldInfo);
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0085  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeValue(com.alibaba.fastjson.serializer.JSONSerializer r11, java.lang.Object r12) throws java.lang.Exception {
        /*
            r10 = this;
            com.alibaba.fastjson.serializer.FieldSerializer$RuntimeSerializerInfo r0 = r10.runtimeInfo
            if (r0 != 0) goto L_0x0090
            if (r12 != 0) goto L_0x003b
            com.alibaba.fastjson.util.FieldInfo r0 = r10.fieldInfo
            java.lang.Class<?> r0 = r0.fieldClass
            java.lang.Class r1 = java.lang.Byte.TYPE
            if (r0 != r1) goto L_0x0011
            java.lang.Class<java.lang.Byte> r0 = java.lang.Byte.class
            goto L_0x003f
        L_0x0011:
            java.lang.Class r1 = java.lang.Short.TYPE
            if (r0 != r1) goto L_0x0018
            java.lang.Class<java.lang.Short> r0 = java.lang.Short.class
            goto L_0x003f
        L_0x0018:
            java.lang.Class r1 = java.lang.Integer.TYPE
            if (r0 != r1) goto L_0x001f
            java.lang.Class<java.lang.Integer> r0 = java.lang.Integer.class
            goto L_0x003f
        L_0x001f:
            java.lang.Class r1 = java.lang.Long.TYPE
            if (r0 != r1) goto L_0x0026
            java.lang.Class<java.lang.Long> r0 = java.lang.Long.class
            goto L_0x003f
        L_0x0026:
            java.lang.Class r1 = java.lang.Float.TYPE
            if (r0 != r1) goto L_0x002d
            java.lang.Class<java.lang.Float> r0 = java.lang.Float.class
            goto L_0x003f
        L_0x002d:
            java.lang.Class r1 = java.lang.Double.TYPE
            if (r0 != r1) goto L_0x0034
            java.lang.Class<java.lang.Double> r0 = java.lang.Double.class
            goto L_0x003f
        L_0x0034:
            java.lang.Class r1 = java.lang.Boolean.TYPE
            if (r0 != r1) goto L_0x003f
            java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
            goto L_0x003f
        L_0x003b:
            java.lang.Class r0 = r12.getClass()
        L_0x003f:
            com.alibaba.fastjson.util.FieldInfo r1 = r10.fieldInfo
            com.alibaba.fastjson.annotation.JSONField r1 = r1.getAnnotation()
            if (r1 == 0) goto L_0x005d
            java.lang.Class r2 = r1.serializeUsing()
            java.lang.Class<java.lang.Void> r3 = java.lang.Void.class
            if (r2 == r3) goto L_0x005d
            java.lang.Class r1 = r1.serializeUsing()
            java.lang.Object r1 = r1.newInstance()
            com.alibaba.fastjson.serializer.ObjectSerializer r1 = (com.alibaba.fastjson.serializer.ObjectSerializer) r1
            r2 = 1
            r10.serializeUsing = r2
            goto L_0x0089
        L_0x005d:
            java.lang.String r1 = r10.format
            if (r1 == 0) goto L_0x0082
            java.lang.Class r1 = java.lang.Double.TYPE
            if (r0 == r1) goto L_0x007a
            java.lang.Class<java.lang.Double> r1 = java.lang.Double.class
            if (r0 != r1) goto L_0x006a
            goto L_0x007a
        L_0x006a:
            java.lang.Class r1 = java.lang.Float.TYPE
            if (r0 == r1) goto L_0x0072
            java.lang.Class<java.lang.Float> r1 = java.lang.Float.class
            if (r0 != r1) goto L_0x0082
        L_0x0072:
            com.alibaba.fastjson.serializer.FloatCodec r1 = new com.alibaba.fastjson.serializer.FloatCodec
            java.lang.String r2 = r10.format
            r1.<init>((java.lang.String) r2)
            goto L_0x0083
        L_0x007a:
            com.alibaba.fastjson.serializer.DoubleSerializer r1 = new com.alibaba.fastjson.serializer.DoubleSerializer
            java.lang.String r2 = r10.format
            r1.<init>((java.lang.String) r2)
            goto L_0x0083
        L_0x0082:
            r1 = 0
        L_0x0083:
            if (r1 != 0) goto L_0x0089
            com.alibaba.fastjson.serializer.ObjectSerializer r1 = r11.getObjectWriter(r0)
        L_0x0089:
            com.alibaba.fastjson.serializer.FieldSerializer$RuntimeSerializerInfo r2 = new com.alibaba.fastjson.serializer.FieldSerializer$RuntimeSerializerInfo
            r2.<init>(r1, r0)
            r10.runtimeInfo = r2
        L_0x0090:
            com.alibaba.fastjson.serializer.FieldSerializer$RuntimeSerializerInfo r0 = r10.runtimeInfo
            boolean r1 = r10.disableCircularReferenceDetect
            if (r1 == 0) goto L_0x00a0
            com.alibaba.fastjson.util.FieldInfo r1 = r10.fieldInfo
            int r1 = r1.serialzeFeatures
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.DisableCircularReferenceDetect
            int r2 = r2.mask
            r1 = r1 | r2
            goto L_0x00a4
        L_0x00a0:
            com.alibaba.fastjson.util.FieldInfo r1 = r10.fieldInfo
            int r1 = r1.serialzeFeatures
        L_0x00a4:
            int r2 = r10.features
            r8 = r1 | r2
            if (r12 != 0) goto L_0x0129
            com.alibaba.fastjson.serializer.SerializeWriter r12 = r11.out
            com.alibaba.fastjson.util.FieldInfo r1 = r10.fieldInfo
            java.lang.Class<?> r1 = r1.fieldClass
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            if (r1 != r2) goto L_0x00c0
            int r1 = com.alibaba.fastjson.serializer.SerializerFeature.WRITE_MAP_NULL_FEATURES
            boolean r1 = r12.isEnabled((int) r1)
            if (r1 == 0) goto L_0x00c0
            r12.writeNull()
            return
        L_0x00c0:
            java.lang.Class<?> r1 = r0.runtimeFieldClass
            java.lang.Class<java.lang.Number> r2 = java.lang.Number.class
            boolean r2 = r2.isAssignableFrom(r1)
            if (r2 == 0) goto L_0x00d4
            int r11 = r10.features
            com.alibaba.fastjson.serializer.SerializerFeature r0 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullNumberAsZero
            int r0 = r0.mask
            r12.writeNull(r11, r0)
            return
        L_0x00d4:
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            if (r2 != r1) goto L_0x00e2
            int r11 = r10.features
            com.alibaba.fastjson.serializer.SerializerFeature r0 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty
            int r0 = r0.mask
            r12.writeNull(r11, r0)
            return
        L_0x00e2:
            java.lang.Class<java.lang.Boolean> r2 = java.lang.Boolean.class
            if (r2 != r1) goto L_0x00f0
            int r11 = r10.features
            com.alibaba.fastjson.serializer.SerializerFeature r0 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullBooleanAsFalse
            int r0 = r0.mask
            r12.writeNull(r11, r0)
            return
        L_0x00f0:
            java.lang.Class<java.util.Collection> r2 = java.util.Collection.class
            boolean r2 = r2.isAssignableFrom(r1)
            if (r2 != 0) goto L_0x011f
            boolean r1 = r1.isArray()
            if (r1 == 0) goto L_0x00ff
            goto L_0x011f
        L_0x00ff:
            com.alibaba.fastjson.serializer.ObjectSerializer r3 = r0.fieldSerializer
            int r0 = com.alibaba.fastjson.serializer.SerializerFeature.WRITE_MAP_NULL_FEATURES
            boolean r0 = r12.isEnabled((int) r0)
            if (r0 == 0) goto L_0x0111
            boolean r0 = r3 instanceof com.alibaba.fastjson.serializer.JavaBeanSerializer
            if (r0 == 0) goto L_0x0111
            r12.writeNull()
            return
        L_0x0111:
            com.alibaba.fastjson.util.FieldInfo r12 = r10.fieldInfo
            java.lang.String r6 = r12.name
            com.alibaba.fastjson.util.FieldInfo r12 = r10.fieldInfo
            java.lang.reflect.Type r7 = r12.fieldType
            r5 = 0
            r4 = r11
            r3.write(r4, r5, r6, r7, r8)
            return
        L_0x011f:
            int r11 = r10.features
            com.alibaba.fastjson.serializer.SerializerFeature r0 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty
            int r0 = r0.mask
            r12.writeNull(r11, r0)
            return
        L_0x0129:
            com.alibaba.fastjson.util.FieldInfo r1 = r10.fieldInfo
            boolean r1 = r1.isEnum
            if (r1 == 0) goto L_0x014f
            boolean r1 = r10.writeEnumUsingName
            if (r1 == 0) goto L_0x013f
            com.alibaba.fastjson.serializer.SerializeWriter r11 = r11.out
            java.lang.Enum r12 = (java.lang.Enum) r12
            java.lang.String r12 = r12.name()
            r11.writeString((java.lang.String) r12)
            return
        L_0x013f:
            boolean r1 = r10.writeEnumUsingToString
            if (r1 == 0) goto L_0x014f
            com.alibaba.fastjson.serializer.SerializeWriter r11 = r11.out
            java.lang.Enum r12 = (java.lang.Enum) r12
            java.lang.String r12 = r12.toString()
            r11.writeString((java.lang.String) r12)
            return
        L_0x014f:
            java.lang.Class r1 = r12.getClass()
            java.lang.Class<?> r2 = r0.runtimeFieldClass
            if (r1 == r2) goto L_0x0161
            boolean r2 = r10.serializeUsing
            if (r2 == 0) goto L_0x015c
            goto L_0x0161
        L_0x015c:
            com.alibaba.fastjson.serializer.ObjectSerializer r0 = r11.getObjectWriter(r1)
            goto L_0x0163
        L_0x0161:
            com.alibaba.fastjson.serializer.ObjectSerializer r0 = r0.fieldSerializer
        L_0x0163:
            r3 = r0
            java.lang.String r0 = r10.format
            if (r0 == 0) goto L_0x0180
            boolean r2 = r3 instanceof com.alibaba.fastjson.serializer.DoubleSerializer
            if (r2 != 0) goto L_0x0180
            boolean r2 = r3 instanceof com.alibaba.fastjson.serializer.FloatCodec
            if (r2 != 0) goto L_0x0180
            boolean r1 = r3 instanceof com.alibaba.fastjson.serializer.ContextObjectSerializer
            if (r1 == 0) goto L_0x017c
            com.alibaba.fastjson.serializer.ContextObjectSerializer r3 = (com.alibaba.fastjson.serializer.ContextObjectSerializer) r3
            com.alibaba.fastjson.serializer.BeanContext r0 = r10.fieldContext
            r3.write(r11, r12, r0)
            goto L_0x017f
        L_0x017c:
            r11.writeWithFormat(r12, r0)
        L_0x017f:
            return
        L_0x0180:
            com.alibaba.fastjson.util.FieldInfo r0 = r10.fieldInfo
            boolean r0 = r0.unwrapped
            if (r0 == 0) goto L_0x01b0
            boolean r0 = r3 instanceof com.alibaba.fastjson.serializer.JavaBeanSerializer
            if (r0 == 0) goto L_0x019b
            com.alibaba.fastjson.serializer.JavaBeanSerializer r3 = (com.alibaba.fastjson.serializer.JavaBeanSerializer) r3
            com.alibaba.fastjson.util.FieldInfo r0 = r10.fieldInfo
            java.lang.String r6 = r0.name
            com.alibaba.fastjson.util.FieldInfo r0 = r10.fieldInfo
            java.lang.reflect.Type r7 = r0.fieldType
            r9 = 1
            r4 = r11
            r5 = r12
            r3.write(r4, r5, r6, r7, r8, r9)
            return
        L_0x019b:
            boolean r0 = r3 instanceof com.alibaba.fastjson.serializer.MapSerializer
            if (r0 == 0) goto L_0x01b0
            com.alibaba.fastjson.serializer.MapSerializer r3 = (com.alibaba.fastjson.serializer.MapSerializer) r3
            com.alibaba.fastjson.util.FieldInfo r0 = r10.fieldInfo
            java.lang.String r6 = r0.name
            com.alibaba.fastjson.util.FieldInfo r0 = r10.fieldInfo
            java.lang.reflect.Type r7 = r0.fieldType
            r9 = 1
            r4 = r11
            r5 = r12
            r3.write(r4, r5, r6, r7, r8, r9)
            return
        L_0x01b0:
            int r0 = r10.features
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName
            int r2 = r2.mask
            r0 = r0 & r2
            if (r0 == 0) goto L_0x01d4
            com.alibaba.fastjson.util.FieldInfo r0 = r10.fieldInfo
            java.lang.Class<?> r0 = r0.fieldClass
            if (r1 == r0) goto L_0x01d4
            boolean r0 = r3 instanceof com.alibaba.fastjson.serializer.JavaBeanSerializer
            if (r0 == 0) goto L_0x01d4
            com.alibaba.fastjson.serializer.JavaBeanSerializer r3 = (com.alibaba.fastjson.serializer.JavaBeanSerializer) r3
            com.alibaba.fastjson.util.FieldInfo r0 = r10.fieldInfo
            java.lang.String r6 = r0.name
            com.alibaba.fastjson.util.FieldInfo r0 = r10.fieldInfo
            java.lang.reflect.Type r7 = r0.fieldType
            r9 = 0
            r4 = r11
            r5 = r12
            r3.write(r4, r5, r6, r7, r8, r9)
            return
        L_0x01d4:
            boolean r0 = r10.browserCompatible
            if (r0 == 0) goto L_0x020d
            com.alibaba.fastjson.util.FieldInfo r0 = r10.fieldInfo
            java.lang.Class<?> r0 = r0.fieldClass
            java.lang.Class r1 = java.lang.Long.TYPE
            if (r0 == r1) goto L_0x01e8
            com.alibaba.fastjson.util.FieldInfo r0 = r10.fieldInfo
            java.lang.Class<?> r0 = r0.fieldClass
            java.lang.Class<java.lang.Long> r1 = java.lang.Long.class
            if (r0 != r1) goto L_0x020d
        L_0x01e8:
            r0 = r12
            java.lang.Long r0 = (java.lang.Long) r0
            long r0 = r0.longValue()
            r4 = 9007199254740991(0x1fffffffffffff, double:4.4501477170144023E-308)
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 > 0) goto L_0x0201
            r4 = -9007199254740991(0xffe0000000000001, double:-8.988465674311582E307)
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x020d
        L_0x0201:
            com.alibaba.fastjson.serializer.SerializeWriter r11 = r11.getWriter()
            java.lang.String r12 = java.lang.Long.toString(r0)
            r11.writeString((java.lang.String) r12)
            return
        L_0x020d:
            com.alibaba.fastjson.util.FieldInfo r0 = r10.fieldInfo
            java.lang.String r6 = r0.name
            com.alibaba.fastjson.util.FieldInfo r0 = r10.fieldInfo
            java.lang.reflect.Type r7 = r0.fieldType
            r4 = r11
            r5 = r12
            r3.write(r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.FieldSerializer.writeValue(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object):void");
    }

    static class RuntimeSerializerInfo {
        final ObjectSerializer fieldSerializer;
        final Class<?> runtimeFieldClass;

        public RuntimeSerializerInfo(ObjectSerializer objectSerializer, Class<?> cls) {
            this.fieldSerializer = objectSerializer;
            this.runtimeFieldClass = cls;
        }
    }
}
