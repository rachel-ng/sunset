package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec;
import com.alibaba.fastjson.parser.deserializer.OptionalCodec;
import com.alibaba.fastjson.spi.Module;
import com.alibaba.fastjson.support.moneta.MonetaCodec;
import com.alibaba.fastjson.support.springfox.SwaggerJsonSerializer;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.ServiceLoader;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.File;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import javax.xml.datatype.XMLGregorianCalendar;
import org.w3c.dom.Node;

public class SerializeConfig {
    private static boolean awtError = false;
    public static final SerializeConfig globalInstance = new SerializeConfig();
    private static boolean guavaError = false;
    private static boolean jdk8Error = false;
    private static boolean jodaError = false;
    private static boolean jsonnullError = false;
    private static boolean jsonobjectError = false;
    private static boolean oracleJdbcError = false;
    private static boolean springfoxError = false;
    private boolean asm;
    private ASMSerializerFactory asmFactory;
    private long[] denyClasses;
    private final boolean fieldBased;
    private final IdentityHashMap<Type, IdentityHashMap<Type, ObjectSerializer>> mixInSerializers;
    private List<Module> modules;
    public PropertyNamingStrategy propertyNamingStrategy;
    private final IdentityHashMap<Type, ObjectSerializer> serializers;
    protected String typeKey;

    public String getTypeKey() {
        return this.typeKey;
    }

    public void setTypeKey(String str) {
        this.typeKey = str;
    }

    private final JavaBeanSerializer createASMSerializer(SerializeBeanInfo serializeBeanInfo) throws Exception {
        JavaBeanSerializer createJavaBeanSerializer = this.asmFactory.createJavaBeanSerializer(serializeBeanInfo);
        for (FieldSerializer fieldSerializer : createJavaBeanSerializer.sortedGetters) {
            Class<?> cls = fieldSerializer.fieldInfo.fieldClass;
            if (cls.isEnum() && !(getObjectWriter(cls) instanceof EnumSerializer)) {
                createJavaBeanSerializer.writeDirect = false;
            }
        }
        return createJavaBeanSerializer;
    }

    public final ObjectSerializer createJavaBeanSerializer(Class<?> cls) {
        String name = cls.getName();
        if (Arrays.binarySearch(this.denyClasses, TypeUtils.fnv1a_64(name)) < 0) {
            SerializeBeanInfo buildBeanInfo = TypeUtils.buildBeanInfo(cls, (Map<String, String>) null, this.propertyNamingStrategy, this.fieldBased);
            if (buildBeanInfo.fields.length != 0 || !Iterable.class.isAssignableFrom(cls)) {
                return createJavaBeanSerializer(buildBeanInfo);
            }
            return MiscCodec.instance;
        }
        throw new JSONException("not support class : " + name);
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x014d A[SYNTHETIC, Splitter:B:107:0x014d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.alibaba.fastjson.serializer.ObjectSerializer createJavaBeanSerializer(com.alibaba.fastjson.serializer.SerializeBeanInfo r14) {
        /*
            r13 = this;
            com.alibaba.fastjson.annotation.JSONType r0 = r14.jsonType
            boolean r1 = r13.asm
            r2 = 0
            if (r1 == 0) goto L_0x000d
            boolean r1 = r13.fieldBased
            if (r1 != 0) goto L_0x000d
            r1 = 1
            goto L_0x000e
        L_0x000d:
            r1 = r2
        L_0x000e:
            if (r0 == 0) goto L_0x0055
            java.lang.Class r3 = r0.serializer()
            java.lang.Class<java.lang.Void> r4 = java.lang.Void.class
            if (r3 == r4) goto L_0x0023
            java.lang.Object r3 = r3.newInstance()     // Catch:{ all -> 0x0023 }
            boolean r4 = r3 instanceof com.alibaba.fastjson.serializer.ObjectSerializer     // Catch:{ all -> 0x0023 }
            if (r4 == 0) goto L_0x0023
            com.alibaba.fastjson.serializer.ObjectSerializer r3 = (com.alibaba.fastjson.serializer.ObjectSerializer) r3     // Catch:{ all -> 0x0023 }
            return r3
        L_0x0023:
            boolean r3 = r0.asm()
            if (r3 != 0) goto L_0x002a
            r1 = r2
        L_0x002a:
            if (r1 == 0) goto L_0x004b
            com.alibaba.fastjson.serializer.SerializerFeature[] r3 = r0.serialzeFeatures()
            int r4 = r3.length
            r5 = r2
        L_0x0032:
            if (r5 >= r4) goto L_0x004b
            r6 = r3[r5]
            com.alibaba.fastjson.serializer.SerializerFeature r7 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNonStringValueAsString
            if (r7 == r6) goto L_0x004a
            com.alibaba.fastjson.serializer.SerializerFeature r7 = com.alibaba.fastjson.serializer.SerializerFeature.WriteEnumUsingToString
            if (r7 == r6) goto L_0x004a
            com.alibaba.fastjson.serializer.SerializerFeature r7 = com.alibaba.fastjson.serializer.SerializerFeature.NotWriteDefaultValue
            if (r7 == r6) goto L_0x004a
            com.alibaba.fastjson.serializer.SerializerFeature r7 = com.alibaba.fastjson.serializer.SerializerFeature.BrowserCompatible
            if (r7 != r6) goto L_0x0047
            goto L_0x004a
        L_0x0047:
            int r5 = r5 + 1
            goto L_0x0032
        L_0x004a:
            r1 = r2
        L_0x004b:
            if (r1 == 0) goto L_0x0055
            java.lang.Class[] r0 = r0.serialzeFilters()
            int r0 = r0.length
            if (r0 == 0) goto L_0x0055
            r1 = r2
        L_0x0055:
            java.lang.Class<?> r0 = r14.beanType
            java.lang.Class<?> r3 = r14.beanType
            int r3 = r3.getModifiers()
            boolean r3 = java.lang.reflect.Modifier.isPublic(r3)
            if (r3 != 0) goto L_0x0069
            com.alibaba.fastjson.serializer.JavaBeanSerializer r0 = new com.alibaba.fastjson.serializer.JavaBeanSerializer
            r0.<init>((com.alibaba.fastjson.serializer.SerializeBeanInfo) r14)
            return r0
        L_0x0069:
            if (r1 == 0) goto L_0x0075
            com.alibaba.fastjson.serializer.ASMSerializerFactory r3 = r13.asmFactory
            com.alibaba.fastjson.util.ASMClassLoader r3 = r3.classLoader
            boolean r3 = r3.isExternalClass(r0)
            if (r3 != 0) goto L_0x007d
        L_0x0075:
            java.lang.Class<java.io.Serializable> r3 = java.io.Serializable.class
            if (r0 == r3) goto L_0x007d
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            if (r0 != r3) goto L_0x007e
        L_0x007d:
            r1 = r2
        L_0x007e:
            if (r1 == 0) goto L_0x008b
            java.lang.String r3 = r0.getSimpleName()
            boolean r3 = com.alibaba.fastjson.util.ASMUtils.checkName(r3)
            if (r3 != 0) goto L_0x008b
            r1 = r2
        L_0x008b:
            if (r1 == 0) goto L_0x0096
            java.lang.Class<?> r3 = r14.beanType
            boolean r3 = r3.isInterface()
            if (r3 == 0) goto L_0x0096
            r1 = r2
        L_0x0096:
            if (r1 == 0) goto L_0x014a
            com.alibaba.fastjson.util.FieldInfo[] r3 = r14.fields
            int r4 = r3.length
            r5 = r2
        L_0x009c:
            if (r5 >= r4) goto L_0x014a
            r6 = r3[r5]
            java.lang.reflect.Field r7 = r6.field
            if (r7 == 0) goto L_0x00b2
            java.lang.Class r7 = r7.getType()
            java.lang.Class<?> r8 = r6.fieldClass
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L_0x00b2
            goto L_0x014b
        L_0x00b2:
            java.lang.reflect.Method r7 = r6.method
            if (r7 == 0) goto L_0x00c4
            java.lang.Class r8 = r7.getReturnType()
            java.lang.Class<?> r9 = r6.fieldClass
            boolean r8 = r8.equals(r9)
            if (r8 != 0) goto L_0x00c4
            goto L_0x014b
        L_0x00c4:
            com.alibaba.fastjson.annotation.JSONField r8 = r6.getAnnotation()
            if (r8 != 0) goto L_0x00cc
            goto L_0x0146
        L_0x00cc:
            java.lang.String r9 = r8.format()
            int r10 = r9.length()
            if (r10 == 0) goto L_0x00e4
            java.lang.Class<?> r6 = r6.fieldClass
            java.lang.Class<java.lang.String> r10 = java.lang.String.class
            if (r6 != r10) goto L_0x014b
            java.lang.String r6 = "trim"
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto L_0x014b
        L_0x00e4:
            java.lang.String r6 = r8.name()
            boolean r6 = com.alibaba.fastjson.util.ASMUtils.checkName(r6)
            if (r6 == 0) goto L_0x014b
            boolean r6 = r8.jsonDirect()
            if (r6 != 0) goto L_0x014b
            java.lang.Class r6 = r8.serializeUsing()
            java.lang.Class<java.lang.Void> r9 = java.lang.Void.class
            if (r6 != r9) goto L_0x014b
            boolean r6 = r8.unwrapped()
            if (r6 == 0) goto L_0x0103
            goto L_0x014b
        L_0x0103:
            com.alibaba.fastjson.serializer.SerializerFeature[] r6 = r8.serialzeFeatures()
            int r9 = r6.length
            r10 = r2
        L_0x0109:
            if (r10 >= r9) goto L_0x0126
            r11 = r6[r10]
            com.alibaba.fastjson.serializer.SerializerFeature r12 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNonStringValueAsString
            if (r12 == r11) goto L_0x0125
            com.alibaba.fastjson.serializer.SerializerFeature r12 = com.alibaba.fastjson.serializer.SerializerFeature.WriteEnumUsingToString
            if (r12 == r11) goto L_0x0125
            com.alibaba.fastjson.serializer.SerializerFeature r12 = com.alibaba.fastjson.serializer.SerializerFeature.NotWriteDefaultValue
            if (r12 == r11) goto L_0x0125
            com.alibaba.fastjson.serializer.SerializerFeature r12 = com.alibaba.fastjson.serializer.SerializerFeature.BrowserCompatible
            if (r12 == r11) goto L_0x0125
            com.alibaba.fastjson.serializer.SerializerFeature r12 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName
            if (r12 != r11) goto L_0x0122
            goto L_0x0125
        L_0x0122:
            int r10 = r10 + 1
            goto L_0x0109
        L_0x0125:
            r1 = r2
        L_0x0126:
            boolean r6 = com.alibaba.fastjson.util.TypeUtils.isAnnotationPresentOneToMany(r7)
            if (r6 != 0) goto L_0x014b
            boolean r6 = com.alibaba.fastjson.util.TypeUtils.isAnnotationPresentManyToMany(r7)
            if (r6 == 0) goto L_0x0133
            goto L_0x014b
        L_0x0133:
            java.lang.String r6 = r8.defaultValue()
            if (r6 == 0) goto L_0x0146
            java.lang.String r6 = ""
            java.lang.String r7 = r8.defaultValue()
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x0146
            goto L_0x014b
        L_0x0146:
            int r5 = r5 + 1
            goto L_0x009c
        L_0x014a:
            r2 = r1
        L_0x014b:
            if (r2 == 0) goto L_0x0179
            com.alibaba.fastjson.serializer.JavaBeanSerializer r0 = r13.createASMSerializer(r14)     // Catch:{ ClassCastException | ClassFormatError | ClassNotFoundException -> 0x0179, OutOfMemoryError -> 0x0169, all -> 0x0154 }
            if (r0 == 0) goto L_0x0179
            return r0
        L_0x0154:
            r14 = move-exception
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "create asm serializer error, verson 1.2.69, class "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0, r14)
            throw r1
        L_0x0169:
            r0 = move-exception
            java.lang.String r1 = r0.getMessage()
            java.lang.String r2 = "Metaspace"
            int r1 = r1.indexOf(r2)
            r2 = -1
            if (r1 != r2) goto L_0x0178
            goto L_0x0179
        L_0x0178:
            throw r0
        L_0x0179:
            com.alibaba.fastjson.serializer.JavaBeanSerializer r0 = new com.alibaba.fastjson.serializer.JavaBeanSerializer
            r0.<init>((com.alibaba.fastjson.serializer.SerializeBeanInfo) r14)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.SerializeConfig.createJavaBeanSerializer(com.alibaba.fastjson.serializer.SerializeBeanInfo):com.alibaba.fastjson.serializer.ObjectSerializer");
    }

    public boolean isAsmEnable() {
        return this.asm;
    }

    public void setAsmEnable(boolean z) {
        if (!ASMUtils.IS_ANDROID) {
            this.asm = z;
        }
    }

    public static SerializeConfig getGlobalInstance() {
        return globalInstance;
    }

    public SerializeConfig() {
        this(8192);
    }

    public SerializeConfig(boolean z) {
        this(8192, z);
    }

    public SerializeConfig(int i) {
        this(i, false);
    }

    public SerializeConfig(int i, boolean z) {
        this.asm = !ASMUtils.IS_ANDROID;
        this.typeKey = JSON.DEFAULT_TYPE_KEY;
        this.denyClasses = new long[]{4165360493669296979L, 4446674157046724083L};
        this.modules = new ArrayList();
        this.fieldBased = z;
        this.serializers = new IdentityHashMap<>(i);
        this.mixInSerializers = new IdentityHashMap<>(16);
        try {
            if (this.asm) {
                this.asmFactory = new ASMSerializerFactory();
            }
        } catch (Throwable unused) {
            this.asm = false;
        }
        initSerializers();
    }

    private void initSerializers() {
        put((Type) Boolean.class, (ObjectSerializer) BooleanCodec.instance);
        put((Type) Character.class, (ObjectSerializer) CharacterCodec.instance);
        put((Type) Byte.class, (ObjectSerializer) IntegerCodec.instance);
        put((Type) Short.class, (ObjectSerializer) IntegerCodec.instance);
        put((Type) Integer.class, (ObjectSerializer) IntegerCodec.instance);
        put((Type) Long.class, (ObjectSerializer) LongCodec.instance);
        put((Type) Float.class, (ObjectSerializer) FloatCodec.instance);
        put((Type) Double.class, (ObjectSerializer) DoubleSerializer.instance);
        put((Type) BigDecimal.class, (ObjectSerializer) BigDecimalCodec.instance);
        put((Type) BigInteger.class, (ObjectSerializer) BigIntegerCodec.instance);
        put((Type) String.class, (ObjectSerializer) StringCodec.instance);
        put((Type) byte[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) short[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) int[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) long[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) float[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) double[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) boolean[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) char[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put((Type) Object[].class, (ObjectSerializer) ObjectArrayCodec.instance);
        put((Type) Class.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) SimpleDateFormat.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) Currency.class, (ObjectSerializer) new MiscCodec());
        put((Type) TimeZone.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) InetAddress.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) Inet4Address.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) Inet6Address.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) InetSocketAddress.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) File.class, (ObjectSerializer) MiscCodec.instance);
        put((Type) Appendable.class, (ObjectSerializer) AppendableSerializer.instance);
        put((Type) StringBuffer.class, (ObjectSerializer) AppendableSerializer.instance);
        put((Type) StringBuilder.class, (ObjectSerializer) AppendableSerializer.instance);
        put((Type) Charset.class, (ObjectSerializer) ToStringSerializer.instance);
        put((Type) Pattern.class, (ObjectSerializer) ToStringSerializer.instance);
        put((Type) Locale.class, (ObjectSerializer) ToStringSerializer.instance);
        put((Type) URI.class, (ObjectSerializer) ToStringSerializer.instance);
        put((Type) URL.class, (ObjectSerializer) ToStringSerializer.instance);
        put((Type) UUID.class, (ObjectSerializer) ToStringSerializer.instance);
        put((Type) AtomicBoolean.class, (ObjectSerializer) AtomicCodec.instance);
        put((Type) AtomicInteger.class, (ObjectSerializer) AtomicCodec.instance);
        put((Type) AtomicLong.class, (ObjectSerializer) AtomicCodec.instance);
        put((Type) AtomicReference.class, (ObjectSerializer) ReferenceCodec.instance);
        put((Type) AtomicIntegerArray.class, (ObjectSerializer) AtomicCodec.instance);
        put((Type) AtomicLongArray.class, (ObjectSerializer) AtomicCodec.instance);
        put((Type) WeakReference.class, (ObjectSerializer) ReferenceCodec.instance);
        put((Type) SoftReference.class, (ObjectSerializer) ReferenceCodec.instance);
        put((Type) LinkedList.class, (ObjectSerializer) CollectionCodec.instance);
    }

    public void addFilter(Class<?> cls, SerializeFilter serializeFilter) {
        ObjectSerializer objectWriter = getObjectWriter(cls);
        if (objectWriter instanceof SerializeFilterable) {
            SerializeFilterable serializeFilterable = (SerializeFilterable) objectWriter;
            if (this == globalInstance || serializeFilterable != MapSerializer.instance) {
                serializeFilterable.addFilter(serializeFilter);
                return;
            }
            MapSerializer mapSerializer = new MapSerializer();
            put((Type) cls, (ObjectSerializer) mapSerializer);
            mapSerializer.addFilter(serializeFilter);
        }
    }

    public void config(Class<?> cls, SerializerFeature serializerFeature, boolean z) {
        ObjectSerializer objectWriter = getObjectWriter(cls, false);
        if (objectWriter == null) {
            SerializeBeanInfo buildBeanInfo = TypeUtils.buildBeanInfo(cls, (Map<String, String>) null, this.propertyNamingStrategy);
            if (z) {
                buildBeanInfo.features = serializerFeature.mask | buildBeanInfo.features;
            } else {
                buildBeanInfo.features = (~serializerFeature.mask) & buildBeanInfo.features;
            }
            put((Type) cls, createJavaBeanSerializer(buildBeanInfo));
        } else if (objectWriter instanceof JavaBeanSerializer) {
            SerializeBeanInfo serializeBeanInfo = ((JavaBeanSerializer) objectWriter).beanInfo;
            int i = serializeBeanInfo.features;
            if (z) {
                serializeBeanInfo.features = serializerFeature.mask | serializeBeanInfo.features;
            } else {
                serializeBeanInfo.features = (~serializerFeature.mask) & serializeBeanInfo.features;
            }
            if (i != serializeBeanInfo.features && objectWriter.getClass() != JavaBeanSerializer.class) {
                put((Type) cls, createJavaBeanSerializer(serializeBeanInfo));
            }
        }
    }

    public ObjectSerializer getObjectWriter(Class<?> cls) {
        return getObjectWriter(cls, true);
    }

    public ObjectSerializer getObjectWriter(Class<?> cls, boolean z) {
        ClassLoader classLoader;
        Class<?> cls2 = cls;
        ObjectSerializer objectSerializer = get(cls);
        if (objectSerializer != null) {
            return objectSerializer;
        }
        try {
            for (AutowiredObjectSerializer next : ServiceLoader.load(AutowiredObjectSerializer.class, Thread.currentThread().getContextClassLoader())) {
                if (next instanceof AutowiredObjectSerializer) {
                    AutowiredObjectSerializer autowiredObjectSerializer = next;
                    for (Type put : autowiredObjectSerializer.getAutowiredFor()) {
                        put(put, (ObjectSerializer) autowiredObjectSerializer);
                    }
                }
            }
        } catch (ClassCastException unused) {
        }
        ObjectSerializer objectSerializer2 = get(cls);
        if (objectSerializer2 == null && (classLoader = JSON.class.getClassLoader()) != Thread.currentThread().getContextClassLoader()) {
            try {
                for (AutowiredObjectSerializer next2 : ServiceLoader.load(AutowiredObjectSerializer.class, classLoader)) {
                    if (next2 instanceof AutowiredObjectSerializer) {
                        AutowiredObjectSerializer autowiredObjectSerializer2 = next2;
                        for (Type put2 : autowiredObjectSerializer2.getAutowiredFor()) {
                            put(put2, (ObjectSerializer) autowiredObjectSerializer2);
                        }
                    }
                }
            } catch (ClassCastException unused2) {
            }
            objectSerializer2 = get(cls);
        }
        for (Module createSerializer : this.modules) {
            objectSerializer2 = createSerializer.createSerializer(this, cls2);
            if (objectSerializer2 != null) {
                put((Type) cls2, objectSerializer2);
                return objectSerializer2;
            }
        }
        if (objectSerializer2 != null) {
            return objectSerializer2;
        }
        String name = cls.getName();
        if (Map.class.isAssignableFrom(cls2)) {
            objectSerializer2 = MapSerializer.instance;
            put((Type) cls2, objectSerializer2);
        } else if (List.class.isAssignableFrom(cls2)) {
            objectSerializer2 = ListSerializer.instance;
            put((Type) cls2, objectSerializer2);
        } else if (Collection.class.isAssignableFrom(cls2)) {
            objectSerializer2 = CollectionCodec.instance;
            put((Type) cls2, objectSerializer2);
        } else if (Date.class.isAssignableFrom(cls2)) {
            objectSerializer2 = DateCodec.instance;
            put((Type) cls2, objectSerializer2);
        } else if (JSONAware.class.isAssignableFrom(cls2)) {
            objectSerializer2 = JSONAwareSerializer.instance;
            put((Type) cls2, objectSerializer2);
        } else if (JSONSerializable.class.isAssignableFrom(cls2)) {
            objectSerializer2 = JSONSerializableSerializer.instance;
            put((Type) cls2, objectSerializer2);
        } else if (JSONStreamAware.class.isAssignableFrom(cls2)) {
            objectSerializer2 = MiscCodec.instance;
            put((Type) cls2, objectSerializer2);
        } else if (cls.isEnum()) {
            JSONType jSONType = (JSONType) TypeUtils.getAnnotation(cls2, JSONType.class);
            if (jSONType == null || !jSONType.serializeEnumAsJavaBean()) {
                objectSerializer2 = getEnumSerializer();
                put((Type) cls2, objectSerializer2);
            } else {
                objectSerializer2 = createJavaBeanSerializer(cls);
                put((Type) cls2, objectSerializer2);
            }
        } else {
            Class<? super Object> superclass = cls.getSuperclass();
            if (superclass != null && superclass.isEnum()) {
                JSONType jSONType2 = (JSONType) TypeUtils.getAnnotation((Class<?>) superclass, JSONType.class);
                if (jSONType2 == null || !jSONType2.serializeEnumAsJavaBean()) {
                    objectSerializer2 = getEnumSerializer();
                    put((Type) cls2, objectSerializer2);
                } else {
                    objectSerializer2 = createJavaBeanSerializer(cls);
                    put((Type) cls2, objectSerializer2);
                }
            } else if (cls.isArray()) {
                Class<?> componentType = cls.getComponentType();
                ArraySerializer arraySerializer = new ArraySerializer(componentType, getObjectWriter(componentType));
                put((Type) cls2, (ObjectSerializer) arraySerializer);
                objectSerializer2 = arraySerializer;
            } else {
                Class cls3 = null;
                if (Throwable.class.isAssignableFrom(cls2)) {
                    SerializeBeanInfo buildBeanInfo = TypeUtils.buildBeanInfo(cls2, (Map<String, String>) null, this.propertyNamingStrategy);
                    buildBeanInfo.features |= SerializerFeature.WriteClassName.mask;
                    JavaBeanSerializer javaBeanSerializer = new JavaBeanSerializer(buildBeanInfo);
                    put((Type) cls2, (ObjectSerializer) javaBeanSerializer);
                    objectSerializer2 = javaBeanSerializer;
                } else if (TimeZone.class.isAssignableFrom(cls2) || Map.Entry.class.isAssignableFrom(cls2)) {
                    objectSerializer2 = MiscCodec.instance;
                    put((Type) cls2, objectSerializer2);
                } else if (Appendable.class.isAssignableFrom(cls2)) {
                    objectSerializer2 = AppendableSerializer.instance;
                    put((Type) cls2, objectSerializer2);
                } else if (Charset.class.isAssignableFrom(cls2)) {
                    objectSerializer2 = ToStringSerializer.instance;
                    put((Type) cls2, objectSerializer2);
                } else if (Enumeration.class.isAssignableFrom(cls2)) {
                    objectSerializer2 = EnumerationSerializer.instance;
                    put((Type) cls2, objectSerializer2);
                } else if (Calendar.class.isAssignableFrom(cls2) || XMLGregorianCalendar.class.isAssignableFrom(cls2)) {
                    objectSerializer2 = CalendarCodec.instance;
                    put((Type) cls2, objectSerializer2);
                } else if (TypeUtils.isClob(cls)) {
                    objectSerializer2 = ClobSeriliazer.instance;
                    put((Type) cls2, objectSerializer2);
                } else if (TypeUtils.isPath(cls)) {
                    objectSerializer2 = ToStringSerializer.instance;
                    put((Type) cls2, objectSerializer2);
                } else if (Iterator.class.isAssignableFrom(cls2)) {
                    objectSerializer2 = MiscCodec.instance;
                    put((Type) cls2, objectSerializer2);
                } else if (Node.class.isAssignableFrom(cls2)) {
                    objectSerializer2 = MiscCodec.instance;
                    put((Type) cls2, objectSerializer2);
                } else {
                    int i = 0;
                    if (name.startsWith("java.awt.") && AwtCodec.support(cls) && !awtError) {
                        try {
                            String[] strArr = {"java.awt.Color", "java.awt.Font", "java.awt.Point", "java.awt.Rectangle"};
                            for (int i2 = 0; i2 < 4; i2++) {
                                String str = strArr[i2];
                                if (str.equals(name)) {
                                    Class<?> cls4 = Class.forName(str);
                                    AwtCodec awtCodec = AwtCodec.instance;
                                    put((Type) cls4, (ObjectSerializer) awtCodec);
                                    return awtCodec;
                                }
                            }
                        } catch (Throwable unused3) {
                            awtError = true;
                        }
                    }
                    if (!jdk8Error && (name.startsWith("java.time.") || name.startsWith("java.util.Optional") || name.equals("java.util.concurrent.atomic.LongAdder") || name.equals("java.util.concurrent.atomic.DoubleAdder"))) {
                        try {
                            String[] strArr2 = {"java.time.LocalDateTime", "java.time.LocalDate", "java.time.LocalTime", "java.time.ZonedDateTime", "java.time.OffsetDateTime", "java.time.OffsetTime", "java.time.ZoneOffset", "java.time.ZoneRegion", "java.time.Period", "java.time.Duration", "java.time.Instant"};
                            for (int i3 = 0; i3 < 11; i3++) {
                                String str2 = strArr2[i3];
                                if (str2.equals(name)) {
                                    Class<?> cls5 = Class.forName(str2);
                                    Jdk8DateCodec jdk8DateCodec = Jdk8DateCodec.instance;
                                    put((Type) cls5, (ObjectSerializer) jdk8DateCodec);
                                    return jdk8DateCodec;
                                }
                            }
                            String[] strArr3 = {"java.util.Optional", "java.util.OptionalDouble", "java.util.OptionalInt", "java.util.OptionalLong"};
                            for (int i4 = 0; i4 < 4; i4++) {
                                String str3 = strArr3[i4];
                                if (str3.equals(name)) {
                                    Class<?> cls6 = Class.forName(str3);
                                    OptionalCodec optionalCodec = OptionalCodec.instance;
                                    put((Type) cls6, (ObjectSerializer) optionalCodec);
                                    return optionalCodec;
                                }
                            }
                            String[] strArr4 = {"java.util.concurrent.atomic.LongAdder", "java.util.concurrent.atomic.DoubleAdder"};
                            for (int i5 = 0; i5 < 2; i5++) {
                                String str4 = strArr4[i5];
                                if (str4.equals(name)) {
                                    Class<?> cls7 = Class.forName(str4);
                                    AdderSerializer adderSerializer = AdderSerializer.instance;
                                    put((Type) cls7, (ObjectSerializer) adderSerializer);
                                    return adderSerializer;
                                }
                            }
                        } catch (Throwable unused4) {
                            jdk8Error = true;
                        }
                    }
                    if (!oracleJdbcError && name.startsWith("oracle.sql.")) {
                        try {
                            String[] strArr5 = {"oracle.sql.DATE", "oracle.sql.TIMESTAMP"};
                            for (int i6 = 0; i6 < 2; i6++) {
                                String str5 = strArr5[i6];
                                if (str5.equals(name)) {
                                    Class<?> cls8 = Class.forName(str5);
                                    DateCodec dateCodec = DateCodec.instance;
                                    put((Type) cls8, (ObjectSerializer) dateCodec);
                                    return dateCodec;
                                }
                            }
                        } catch (Throwable unused5) {
                            oracleJdbcError = true;
                        }
                    }
                    if (!springfoxError && name.equals("springfox.documentation.spring.web.json.Json")) {
                        try {
                            Class<?> cls9 = Class.forName("springfox.documentation.spring.web.json.Json");
                            objectSerializer2 = SwaggerJsonSerializer.instance;
                            put((Type) cls9, objectSerializer2);
                            return objectSerializer2;
                        } catch (ClassNotFoundException unused6) {
                            springfoxError = true;
                        }
                    }
                    if (!guavaError && name.startsWith("com.google.common.collect.")) {
                        try {
                            String[] strArr6 = {"com.google.common.collect.HashMultimap", "com.google.common.collect.LinkedListMultimap", "com.google.common.collect.LinkedHashMultimap", "com.google.common.collect.ArrayListMultimap", "com.google.common.collect.TreeMultimap"};
                            for (int i7 = 0; i7 < 5; i7++) {
                                String str6 = strArr6[i7];
                                if (str6.equals(name)) {
                                    Class<?> cls10 = Class.forName(str6);
                                    GuavaCodec guavaCodec = GuavaCodec.instance;
                                    put((Type) cls10, (ObjectSerializer) guavaCodec);
                                    return guavaCodec;
                                }
                            }
                        } catch (ClassNotFoundException unused7) {
                            guavaError = true;
                        }
                    }
                    if (!jsonnullError && name.equals("net.sf.json.JSONNull")) {
                        try {
                            Class<?> cls11 = Class.forName("net.sf.json.JSONNull");
                            objectSerializer2 = MiscCodec.instance;
                            put((Type) cls11, objectSerializer2);
                            return objectSerializer2;
                        } catch (ClassNotFoundException unused8) {
                            jsonnullError = true;
                        }
                    }
                    if (!jsonobjectError && name.equals("org.json.JSONObject")) {
                        try {
                            Class<?> cls12 = Class.forName("org.json.JSONObject");
                            objectSerializer2 = JSONObjectCodec.instance;
                            put((Type) cls12, objectSerializer2);
                            return objectSerializer2;
                        } catch (ClassNotFoundException unused9) {
                            jsonobjectError = true;
                        }
                    }
                    if (!jodaError && name.startsWith("org.joda.")) {
                        try {
                            String[] strArr7 = {"org.joda.time.LocalDate", "org.joda.time.LocalDateTime", "org.joda.time.LocalTime", "org.joda.time.Instant", "org.joda.time.DateTime", "org.joda.time.Period", "org.joda.time.Duration", "org.joda.time.DateTimeZone", "org.joda.time.UTCDateTimeZone", "org.joda.time.tz.CachedDateTimeZone", "org.joda.time.tz.FixedDateTimeZone"};
                            for (int i8 = 0; i8 < 11; i8++) {
                                String str7 = strArr7[i8];
                                if (str7.equals(name)) {
                                    Class<?> cls13 = Class.forName(str7);
                                    JodaCodec jodaCodec = JodaCodec.instance;
                                    put((Type) cls13, (ObjectSerializer) jodaCodec);
                                    return jodaCodec;
                                }
                            }
                        } catch (ClassNotFoundException unused10) {
                            jodaError = true;
                        }
                    }
                    if ("java.nio.HeapByteBuffer".equals(name)) {
                        ByteBufferCodec byteBufferCodec = ByteBufferCodec.instance;
                        put((Type) cls2, (ObjectSerializer) byteBufferCodec);
                        return byteBufferCodec;
                    } else if ("org.javamoney.moneta.Money".equals(name)) {
                        MonetaCodec monetaCodec = MonetaCodec.instance;
                        put((Type) cls2, (ObjectSerializer) monetaCodec);
                        return monetaCodec;
                    } else {
                        Class[] interfaces = cls.getInterfaces();
                        if (interfaces.length == 1 && interfaces[0].isAnnotation()) {
                            put((Type) cls2, (ObjectSerializer) AnnotationSerializer.instance);
                            return AnnotationSerializer.instance;
                        } else if (TypeUtils.isProxy(cls)) {
                            ObjectSerializer objectWriter = getObjectWriter(cls.getSuperclass());
                            put((Type) cls2, objectWriter);
                            return objectWriter;
                        } else {
                            if (Proxy.isProxyClass(cls)) {
                                if (interfaces.length != 2) {
                                    int length = interfaces.length;
                                    Class cls14 = null;
                                    while (true) {
                                        if (i >= length) {
                                            cls3 = cls14;
                                            break;
                                        }
                                        Class cls15 = interfaces[i];
                                        if (!cls15.getName().startsWith("org.springframework.aop.")) {
                                            if (cls14 != null) {
                                                break;
                                            }
                                            cls14 = cls15;
                                        }
                                        i++;
                                    }
                                } else {
                                    cls3 = interfaces[1];
                                }
                                if (cls3 != null) {
                                    ObjectSerializer objectWriter2 = getObjectWriter(cls3);
                                    put((Type) cls2, objectWriter2);
                                    return objectWriter2;
                                }
                            }
                            if (z) {
                                objectSerializer2 = createJavaBeanSerializer(cls);
                                put((Type) cls2, objectSerializer2);
                            }
                        }
                    }
                }
            }
        }
        return objectSerializer2 == null ? get(cls) : objectSerializer2;
    }

    /* access modifiers changed from: protected */
    public ObjectSerializer getEnumSerializer() {
        return EnumSerializer.instance;
    }

    public final ObjectSerializer get(Type type) {
        Type mixInAnnotations = JSON.getMixInAnnotations(type);
        if (mixInAnnotations == null) {
            return this.serializers.get(type);
        }
        IdentityHashMap identityHashMap = this.mixInSerializers.get(type);
        if (identityHashMap == null) {
            return null;
        }
        return (ObjectSerializer) identityHashMap.get(mixInAnnotations);
    }

    public boolean put(Object obj, Object obj2) {
        return put((Type) obj, (ObjectSerializer) obj2);
    }

    public boolean put(Type type, ObjectSerializer objectSerializer) {
        Type mixInAnnotations = JSON.getMixInAnnotations(type);
        if (mixInAnnotations == null) {
            return this.serializers.put(type, objectSerializer);
        }
        IdentityHashMap identityHashMap = this.mixInSerializers.get(type);
        if (identityHashMap == null) {
            identityHashMap = new IdentityHashMap(4);
            this.mixInSerializers.put(type, identityHashMap);
        }
        return identityHashMap.put(mixInAnnotations, objectSerializer);
    }

    public void configEnumAsJavaBean(Class<? extends Enum>... clsArr) {
        for (Class<? extends Enum> cls : clsArr) {
            put((Type) cls, createJavaBeanSerializer((Class<?>) cls));
        }
    }

    public void setPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy2) {
        this.propertyNamingStrategy = propertyNamingStrategy2;
    }

    public void clearSerializers() {
        this.serializers.clear();
        initSerializers();
    }

    public void register(Module module) {
        this.modules.add(module);
    }
}
