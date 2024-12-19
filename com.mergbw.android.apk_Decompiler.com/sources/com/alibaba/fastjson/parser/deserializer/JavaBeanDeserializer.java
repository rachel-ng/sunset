package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class JavaBeanDeserializer implements ObjectDeserializer {
    private final Map<String, FieldDeserializer> alterNameFieldDeserializers;
    public final JavaBeanInfo beanInfo;
    protected final Class<?> clazz;
    private ConcurrentMap<String, Object> extraFieldDeserializers;
    private Map<String, FieldDeserializer> fieldDeserializerMap;
    private final FieldDeserializer[] fieldDeserializers;
    private transient long[] hashArray;
    private transient short[] hashArrayMapping;
    private transient long[] smartMatchHashArray;
    private transient short[] smartMatchHashArrayMapping;
    protected final FieldDeserializer[] sortedFieldDeserializers;

    public int getFastMatchToken() {
        return 12;
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls) {
        this(parserConfig, cls, cls);
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        this(parserConfig, JavaBeanInfo.build(cls, type, parserConfig.propertyNamingStrategy, parserConfig.fieldBased, parserConfig.compatibleWithJavaBean, parserConfig.isJacksonCompatible()));
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo) {
        this.clazz = javaBeanInfo.clazz;
        this.beanInfo = javaBeanInfo;
        this.sortedFieldDeserializers = new FieldDeserializer[javaBeanInfo.sortedFields.length];
        int length = javaBeanInfo.sortedFields.length;
        HashMap hashMap = null;
        for (int i = 0; i < length; i++) {
            FieldInfo fieldInfo = javaBeanInfo.sortedFields[i];
            FieldDeserializer createFieldDeserializer = parserConfig.createFieldDeserializer(parserConfig, javaBeanInfo, fieldInfo);
            this.sortedFieldDeserializers[i] = createFieldDeserializer;
            if (length > 128) {
                if (this.fieldDeserializerMap == null) {
                    this.fieldDeserializerMap = new HashMap();
                }
                this.fieldDeserializerMap.put(fieldInfo.name, createFieldDeserializer);
            }
            for (String str : fieldInfo.alternateNames) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                hashMap.put(str, createFieldDeserializer);
            }
        }
        this.alterNameFieldDeserializers = hashMap;
        this.fieldDeserializers = new FieldDeserializer[javaBeanInfo.fields.length];
        int length2 = javaBeanInfo.fields.length;
        for (int i2 = 0; i2 < length2; i2++) {
            this.fieldDeserializers[i2] = getFieldDeserializer(javaBeanInfo.fields[i2].name);
        }
    }

    public FieldDeserializer getFieldDeserializer(String str) {
        return getFieldDeserializer(str, (int[]) null);
    }

    public FieldDeserializer getFieldDeserializer(String str, int[] iArr) {
        FieldDeserializer fieldDeserializer;
        if (str == null) {
            return null;
        }
        Map<String, FieldDeserializer> map = this.fieldDeserializerMap;
        if (map != null && (fieldDeserializer = map.get(str)) != null) {
            return fieldDeserializer;
        }
        int length = this.sortedFieldDeserializers.length - 1;
        int i = 0;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int compareTo = this.sortedFieldDeserializers[i2].fieldInfo.name.compareTo(str);
            if (compareTo < 0) {
                i = i2 + 1;
            } else if (compareTo > 0) {
                length = i2 - 1;
            } else if (isSetFlag(i2, iArr)) {
                return null;
            } else {
                return this.sortedFieldDeserializers[i2];
            }
        }
        Map<String, FieldDeserializer> map2 = this.alterNameFieldDeserializers;
        if (map2 != null) {
            return map2.get(str);
        }
        return null;
    }

    public FieldDeserializer getFieldDeserializer(long j) {
        int i = 0;
        if (this.hashArray == null) {
            long[] jArr = new long[this.sortedFieldDeserializers.length];
            int i2 = 0;
            while (true) {
                FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                if (i2 >= fieldDeserializerArr.length) {
                    break;
                }
                jArr[i2] = TypeUtils.fnv1a_64(fieldDeserializerArr[i2].fieldInfo.name);
                i2++;
            }
            Arrays.sort(jArr);
            this.hashArray = jArr;
        }
        int binarySearch = Arrays.binarySearch(this.hashArray, j);
        if (binarySearch < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, -1);
            while (true) {
                FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                if (i >= fieldDeserializerArr2.length) {
                    break;
                }
                int binarySearch2 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(fieldDeserializerArr2[i].fieldInfo.name));
                if (binarySearch2 >= 0) {
                    sArr[binarySearch2] = (short) i;
                }
                i++;
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[binarySearch];
        if (s != -1) {
            return this.sortedFieldDeserializers[s];
        }
        return null;
    }

    static boolean isSetFlag(int i, int[] iArr) {
        if (iArr == null) {
            return false;
        }
        int i2 = i / 32;
        int i3 = i % 32;
        if (i2 < iArr.length) {
            if (((1 << i3) & iArr[i2]) != 0) {
                return true;
            }
        }
        return false;
    }

    public Object createInstance(DefaultJSONParser defaultJSONParser, Type type) {
        Object obj;
        if (!(type instanceof Class) || !this.clazz.isInterface()) {
            Object obj2 = null;
            if (this.beanInfo.defaultConstructor == null && this.beanInfo.factoryMethod == null) {
                return null;
            }
            if (this.beanInfo.factoryMethod != null && this.beanInfo.defaultConstructorParameterSize > 0) {
                return null;
            }
            try {
                Constructor<?> constructor = this.beanInfo.defaultConstructor;
                if (this.beanInfo.defaultConstructorParameterSize != 0) {
                    ParseContext context = defaultJSONParser.getContext();
                    if (context != null) {
                        if (context.object != null) {
                            if (type instanceof Class) {
                                String name = ((Class) type).getName();
                                String substring = name.substring(0, name.lastIndexOf(36));
                                Object obj3 = context.object;
                                String name2 = obj3.getClass().getName();
                                if (!name2.equals(substring)) {
                                    ParseContext parseContext = context.parent;
                                    if (parseContext == null || parseContext.object == null || (!"java.util.ArrayList".equals(name2) && !"java.util.List".equals(name2) && !"java.util.Collection".equals(name2) && !"java.util.Map".equals(name2) && !"java.util.HashMap".equals(name2))) {
                                        obj2 = obj3;
                                    } else if (parseContext.object.getClass().getName().equals(substring)) {
                                        obj2 = parseContext.object;
                                    }
                                    obj3 = obj2;
                                }
                                if (obj3 == null || ((obj3 instanceof Collection) && ((Collection) obj3).isEmpty())) {
                                    throw new JSONException("can't create non-static inner class instance.");
                                }
                                obj = constructor.newInstance(new Object[]{obj3});
                            } else {
                                throw new JSONException("can't create non-static inner class instance.");
                            }
                        }
                    }
                    throw new JSONException("can't create non-static inner class instance.");
                } else if (constructor != null) {
                    obj = constructor.newInstance((Object[]) null);
                } else {
                    obj = this.beanInfo.factoryMethod.invoke((Object) null, (Object[]) null);
                }
                if (defaultJSONParser != null && defaultJSONParser.lexer.isEnabled(Feature.InitStringFieldAsEmpty)) {
                    for (FieldInfo fieldInfo : this.beanInfo.fields) {
                        if (fieldInfo.fieldClass == String.class) {
                            try {
                                fieldInfo.set(obj, "");
                            } catch (Exception e) {
                                throw new JSONException("create instance error, class " + this.clazz.getName(), e);
                            }
                        }
                    }
                }
                return obj;
            } catch (JSONException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new JSONException("create instance error, class " + this.clazz.getName(), e3);
            }
        } else {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{(Class) type}, new JSONObject());
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return deserialze(defaultJSONParser, type, obj, 0);
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, int i) {
        return deserialze(defaultJSONParser, type, obj, (Object) null, i, (int[]) null);
    }

    public <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        Enum<?> enumR;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 14) {
            String scanTypeName = jSONLexer.scanTypeName(defaultJSONParser.symbolTable);
            if (scanTypeName != null) {
                Object seeAlso = getSeeAlso(defaultJSONParser.getConfig(), this.beanInfo, scanTypeName);
                if (seeAlso == null) {
                    seeAlso = defaultJSONParser.getConfig().getDeserializer((Type) defaultJSONParser.getConfig().checkAutoType(scanTypeName, TypeUtils.getClass(type), jSONLexer.getFeatures()));
                }
                if (seeAlso instanceof JavaBeanDeserializer) {
                    return ((JavaBeanDeserializer) seeAlso).deserialzeArrayMapping(defaultJSONParser, type, obj, obj2);
                }
            }
            T createInstance = createInstance(defaultJSONParser, type);
            int length = this.sortedFieldDeserializers.length;
            int i = 0;
            while (true) {
                int i2 = 16;
                if (i >= length) {
                    break;
                }
                char c2 = i == length + -1 ? ']' : ',';
                FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
                Class<?> cls = fieldDeserializer.fieldInfo.fieldClass;
                if (cls == Integer.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, jSONLexer.scanInt(c2));
                } else if (cls == String.class) {
                    fieldDeserializer.setValue((Object) createInstance, jSONLexer.scanString(c2));
                } else if (cls == Long.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, jSONLexer.scanLong(c2));
                } else if (cls.isEnum()) {
                    char current = jSONLexer.getCurrent();
                    if (current == '\"' || current == 'n') {
                        enumR = jSONLexer.scanEnum(cls, defaultJSONParser.getSymbolTable(), c2);
                    } else if (current < '0' || current > '9') {
                        enumR = scanEnum(jSONLexer, c2);
                    } else {
                        enumR = ((EnumDeserializer) ((DefaultFieldDeserializer) fieldDeserializer).getFieldValueDeserilizer(defaultJSONParser.getConfig())).valueOf(jSONLexer.scanInt(c2));
                    }
                    fieldDeserializer.setValue((Object) createInstance, (Object) enumR);
                } else if (cls == Boolean.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, jSONLexer.scanBoolean(c2));
                } else if (cls == Float.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, (Object) Float.valueOf(jSONLexer.scanFloat(c2)));
                } else if (cls == Double.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, (Object) Double.valueOf(jSONLexer.scanDouble(c2)));
                } else if (cls == Date.class && jSONLexer.getCurrent() == '1') {
                    fieldDeserializer.setValue((Object) createInstance, (Object) new Date(jSONLexer.scanLong(c2)));
                } else if (cls == BigDecimal.class) {
                    fieldDeserializer.setValue((Object) createInstance, (Object) jSONLexer.scanDecimal(c2));
                } else {
                    jSONLexer.nextToken(14);
                    fieldDeserializer.setValue((Object) createInstance, defaultJSONParser.parseObject(fieldDeserializer.fieldInfo.fieldType, (Object) fieldDeserializer.fieldInfo.name));
                    if (jSONLexer.token() == 15) {
                        break;
                    }
                    if (c2 == ']') {
                        i2 = 15;
                    }
                    check(jSONLexer, i2);
                }
                i++;
            }
            jSONLexer.nextToken(16);
            return createInstance;
        }
        throw new JSONException("error");
    }

    /* access modifiers changed from: protected */
    public void check(JSONLexer jSONLexer, int i) {
        if (jSONLexer.token() != i) {
            throw new JSONException("syntax error");
        }
    }

    /* access modifiers changed from: protected */
    public Enum<?> scanEnum(JSONLexer jSONLexer, char c2) {
        throw new JSONException("illegal enum. " + jSONLexer.info());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: com.alibaba.fastjson.parser.deserializer.FieldDeserializer[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v2, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v5, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v6, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v7, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v8, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v28, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v36, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v50, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v12, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v13, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v3, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v67, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v4, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v68, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v5, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v74, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v28, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v7, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v8, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v9, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v87, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v11, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v88, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v17, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v19, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v90, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v99, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v104, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v107, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v113, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v121, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v29, resolved type: com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v189, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v191, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v192, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v193, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v194, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v195, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v196, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v197, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v198, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v199, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v200, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v201, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v202, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v203, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v204, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v205, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v206, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v207, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v212, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v213, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v216, resolved type: T} */
    /* JADX WARNING: type inference failed for: r1v125 */
    /* JADX WARNING: type inference failed for: r1v169 */
    /* JADX WARNING: type inference failed for: r14v31, types: [com.alibaba.fastjson.parser.deserializer.FieldDeserializer] */
    /* JADX WARNING: type inference failed for: r1v187 */
    /* JADX WARNING: type inference failed for: r1v188 */
    /* JADX WARNING: type inference failed for: r1v209 */
    /* JADX WARNING: type inference failed for: r1v214 */
    /* JADX WARNING: type inference failed for: r1v215 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        r1 = r36;
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:308:0x0380, code lost:
        if (r12.matchStat == -2) goto L_0x0382;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:333:0x03e6, code lost:
        r12.nextTokenWithColon(4);
        r2 = r12.token();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:334:0x03ee, code lost:
        if (r2 != 4) goto L_0x04a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:335:0x03f0, code lost:
        r1 = r12.stringVal();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x03fa, code lost:
        if ("@".equals(r1) == false) goto L_0x0402;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:337:0x03fc, code lost:
        r1 = r7.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x0408, code lost:
        if ("..".equals(r1) == false) goto L_0x041f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:341:0x040a, code lost:
        r2 = r7.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:342:0x040e, code lost:
        if (r2.object == null) goto L_0x0413;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:343:0x0410, code lost:
        r1 = r2.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x0413, code lost:
        r9.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r2, r1));
        r9.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:346:0x0425, code lost:
        if ("$".equals(r1) == false) goto L_0x0442;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:347:0x0427, code lost:
        r2 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:349:0x042a, code lost:
        if (r2.parent == null) goto L_0x042f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:350:0x042c, code lost:
        r2 = r2.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:352:0x0431, code lost:
        if (r2.object == null) goto L_0x0436;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:353:0x0433, code lost:
        r1 = r2.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:0x0436, code lost:
        r9.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r2, r1));
        r9.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:0x0448, code lost:
        if (r1.indexOf(92) <= 0) goto L_0x046c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x044a, code lost:
        r3 = new java.lang.StringBuilder();
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x0454, code lost:
        if (r4 >= r1.length()) goto L_0x0468;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:360:0x0456, code lost:
        r5 = r1.charAt(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:0x045a, code lost:
        if (r5 != '\\') goto L_0x0462;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:362:0x045c, code lost:
        r4 = r4 + 1;
        r5 = r1.charAt(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:0x0462, code lost:
        r3.append(r5);
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:364:0x0468, code lost:
        r1 = r3.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:0x046c, code lost:
        r2 = r9.resolveReference(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:366:0x0470, code lost:
        if (r2 == null) goto L_0x0474;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x0472, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:0x0474, code lost:
        r9.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r7, r1));
        r9.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:0x047f, code lost:
        r1 = r38;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:0x0483, code lost:
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:?, code lost:
        r12.nextToken(13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:0x048a, code lost:
        if (r12.token() != 13) goto L_0x049c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:0x048c, code lost:
        r12.nextToken(16);
        r9.setContext(r7, r1, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x0494, code lost:
        if (r6 == null) goto L_0x0498;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:375:0x0496, code lost:
        r6.object = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:0x0498, code lost:
        r9.setContext(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:377:0x049b, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:378:0x049c, code lost:
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x04a3, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x04be, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref, " + com.alibaba.fastjson.parser.JSONToken.name(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:496:0x06a8, code lost:
        r1 = r18;
        r2 = r21;
        r6 = r30;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:619:0x0822, code lost:
        r1 = r8.beanInfo.fields[r1].fieldClass;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:620:0x082c, code lost:
        if (r1 != java.lang.String.class) goto L_0x0863;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:621:0x082e, code lost:
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:623:0x0832, code lost:
        if (r8.beanInfo.kotlinDefaultConstructor == null) goto L_0x0863;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:624:0x0834, code lost:
        r1 = r8.beanInfo.kotlinDefaultConstructor.newInstance((java.lang.Object[]) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:625:0x083d, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:628:0x083f, code lost:
        if (r5 >= r7.length) goto L_0x086b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:629:0x0841, code lost:
        r10 = r7[r5];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:630:0x0843, code lost:
        if (r10 == null) goto L_0x085b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:632:0x0849, code lost:
        if (r8.beanInfo.fields == null) goto L_0x085b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:634:0x0850, code lost:
        if (r5 >= r8.beanInfo.fields.length) goto L_0x085b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:635:0x0852, code lost:
        r8.beanInfo.fields[r5].set(r1, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:636:0x085b, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:637:0x085e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:649:0x0895, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:650:0x0896, code lost:
        r1 = r37;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:652:0x08bf, code lost:
        throw new com.alibaba.fastjson.JSONException("create instance error, " + r4 + ", " + r8.beanInfo.creatorConstructor.toGenericString(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:685:0x0921, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:687:0x0923, code lost:
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:689:0x092a, code lost:
        throw new com.alibaba.fastjson.JSONException("build object error", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:690:0x092b, code lost:
        r0 = th;
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:699:0x0973, code lost:
        throw new com.alibaba.fastjson.JSONException("syntax error, unexpect token " + com.alibaba.fastjson.parser.JSONToken.name(r12.token()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:754:0x086b, code lost:
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x011b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x011c, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0126, code lost:
        throw new com.alibaba.fastjson.JSONException(r1.getMessage(), r1);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:14:0x0039, B:77:0x00e0] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:503:0x06ba, B:621:0x082e, B:679:0x0915] */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int[], code=java.lang.Object, for r38v0, types: [int[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x0302 A[Catch:{ all -> 0x039b, all -> 0x056a }] */
    /* JADX WARNING: Removed duplicated region for block: B:318:0x03ad A[Catch:{ all -> 0x039b, all -> 0x056a }] */
    /* JADX WARNING: Removed duplicated region for block: B:424:0x056f A[Catch:{ all -> 0x01a6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:426:0x057a A[ADDED_TO_REGION, Catch:{ all -> 0x01a6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:438:0x05b3  */
    /* JADX WARNING: Removed duplicated region for block: B:467:0x062b A[Catch:{ all -> 0x063c }] */
    /* JADX WARNING: Removed duplicated region for block: B:492:0x069a A[Catch:{ all -> 0x0980 }] */
    /* JADX WARNING: Removed duplicated region for block: B:493:0x069f A[Catch:{ all -> 0x0980 }] */
    /* JADX WARNING: Removed duplicated region for block: B:642:0x086d A[SYNTHETIC, Splitter:B:642:0x086d] */
    /* JADX WARNING: Removed duplicated region for block: B:712:0x0995  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r33, java.lang.reflect.Type r34, java.lang.Object r35, java.lang.Object r36, int r37, java.lang.Object r38) {
        /*
            r32 = this;
            r8 = r32
            r9 = r33
            r10 = r34
            r11 = r35
            java.lang.Class<com.alibaba.fastjson.JSON> r1 = com.alibaba.fastjson.JSON.class
            if (r10 == r1) goto L_0x099b
            java.lang.Class<com.alibaba.fastjson.JSONObject> r1 = com.alibaba.fastjson.JSONObject.class
            if (r10 != r1) goto L_0x0012
            goto L_0x099b
        L_0x0012:
            com.alibaba.fastjson.parser.JSONLexer r1 = r9.lexer
            r12 = r1
            com.alibaba.fastjson.parser.JSONLexerBase r12 = (com.alibaba.fastjson.parser.JSONLexerBase) r12
            com.alibaba.fastjson.parser.ParserConfig r13 = r33.getConfig()
            int r1 = r12.token()
            r2 = 8
            r14 = 16
            r15 = 0
            if (r1 != r2) goto L_0x002a
            r12.nextToken(r14)
            return r15
        L_0x002a:
            com.alibaba.fastjson.parser.ParseContext r2 = r33.getContext()
            if (r36 == 0) goto L_0x0034
            if (r2 == 0) goto L_0x0034
            com.alibaba.fastjson.parser.ParseContext r2 = r2.parent
        L_0x0034:
            r7 = r2
            r6 = 13
            if (r1 != r6) goto L_0x0050
            r12.nextToken(r14)     // Catch:{ all -> 0x0049 }
            if (r36 != 0) goto L_0x0043
            java.lang.Object r1 = r32.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r33, (java.lang.reflect.Type) r34)     // Catch:{ all -> 0x0049 }
            goto L_0x0045
        L_0x0043:
            r1 = r36
        L_0x0045:
            r9.setContext(r7)
            return r1
        L_0x0049:
            r0 = move-exception
            r1 = r36
            r2 = r0
        L_0x004d:
            r3 = r7
            goto L_0x0993
        L_0x0050:
            r2 = 14
            if (r1 != r2) goto L_0x0073
            com.alibaba.fastjson.parser.Feature r3 = com.alibaba.fastjson.parser.Feature.SupportArrayToBean     // Catch:{ all -> 0x0049 }
            int r3 = r3.mask     // Catch:{ all -> 0x0049 }
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r8.beanInfo     // Catch:{ all -> 0x0049 }
            int r4 = r4.parserFeatures     // Catch:{ all -> 0x0049 }
            r4 = r4 & r3
            if (r4 != 0) goto L_0x006b
            com.alibaba.fastjson.parser.Feature r4 = com.alibaba.fastjson.parser.Feature.SupportArrayToBean     // Catch:{ all -> 0x0049 }
            boolean r4 = r12.isEnabled((com.alibaba.fastjson.parser.Feature) r4)     // Catch:{ all -> 0x0049 }
            if (r4 != 0) goto L_0x006b
            r3 = r37 & r3
            if (r3 == 0) goto L_0x0073
        L_0x006b:
            java.lang.Object r1 = r32.deserialzeArrayMapping(r33, r34, r35, r36)     // Catch:{ all -> 0x0049 }
            r9.setContext(r7)
            return r1
        L_0x0073:
            r3 = 12
            r5 = 4
            if (r1 == r3) goto L_0x0164
            if (r1 == r14) goto L_0x0164
            boolean r3 = r12.isBlankInput()     // Catch:{ all -> 0x0049 }
            if (r3 == 0) goto L_0x0084
            r9.setContext(r7)
            return r15
        L_0x0084:
            if (r1 != r5) goto L_0x00be
            java.lang.String r3 = r12.stringVal()     // Catch:{ all -> 0x0049 }
            int r10 = r3.length()     // Catch:{ all -> 0x0049 }
            if (r10 != 0) goto L_0x0097
            r12.nextToken()     // Catch:{ all -> 0x0049 }
            r9.setContext(r7)
            return r15
        L_0x0097:
            com.alibaba.fastjson.util.JavaBeanInfo r10 = r8.beanInfo     // Catch:{ all -> 0x0049 }
            com.alibaba.fastjson.annotation.JSONType r10 = r10.jsonType     // Catch:{ all -> 0x0049 }
            if (r10 == 0) goto L_0x00be
            com.alibaba.fastjson.util.JavaBeanInfo r10 = r8.beanInfo     // Catch:{ all -> 0x0049 }
            com.alibaba.fastjson.annotation.JSONType r10 = r10.jsonType     // Catch:{ all -> 0x0049 }
            java.lang.Class[] r10 = r10.seeAlso()     // Catch:{ all -> 0x0049 }
            int r14 = r10.length     // Catch:{ all -> 0x0049 }
            r5 = 0
        L_0x00a7:
            if (r5 >= r14) goto L_0x00be
            r6 = r10[r5]     // Catch:{ all -> 0x0049 }
            java.lang.Class<java.lang.Enum> r4 = java.lang.Enum.class
            boolean r4 = r4.isAssignableFrom(r6)     // Catch:{ all -> 0x0049 }
            if (r4 == 0) goto L_0x00bb
            java.lang.Enum r1 = java.lang.Enum.valueOf(r6, r3)     // Catch:{ IllegalArgumentException -> 0x00bb }
            r9.setContext(r7)
            return r1
        L_0x00bb:
            int r5 = r5 + 1
            goto L_0x00a7
        L_0x00be:
            if (r1 != r2) goto L_0x00d2
            char r2 = r12.getCurrent()     // Catch:{ all -> 0x0049 }
            r3 = 93
            if (r2 != r3) goto L_0x00d2
            r12.next()     // Catch:{ all -> 0x0049 }
            r12.nextToken()     // Catch:{ all -> 0x0049 }
            r9.setContext(r7)
            return r15
        L_0x00d2:
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x0049 }
            java.lang.reflect.Method r2 = r2.factoryMethod     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x0127
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x0049 }
            com.alibaba.fastjson.util.FieldInfo[] r2 = r2.fields     // Catch:{ all -> 0x0049 }
            int r2 = r2.length     // Catch:{ all -> 0x0049 }
            r3 = 1
            if (r2 != r3) goto L_0x0127
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ Exception -> 0x011b }
            com.alibaba.fastjson.util.FieldInfo[] r2 = r2.fields     // Catch:{ Exception -> 0x011b }
            r3 = 0
            r2 = r2[r3]     // Catch:{ Exception -> 0x011b }
            java.lang.Class<?> r3 = r2.fieldClass     // Catch:{ Exception -> 0x011b }
            java.lang.Class<java.lang.Integer> r4 = java.lang.Integer.class
            if (r3 != r4) goto L_0x0103
            r3 = 2
            if (r1 != r3) goto L_0x0127
            int r1 = r12.intValue()     // Catch:{ Exception -> 0x011b }
            r12.nextToken()     // Catch:{ Exception -> 0x011b }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x011b }
            java.lang.Object r1 = r8.createFactoryInstance(r13, r1)     // Catch:{ Exception -> 0x011b }
            r9.setContext(r7)
            return r1
        L_0x0103:
            java.lang.Class<?> r2 = r2.fieldClass     // Catch:{ Exception -> 0x011b }
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            if (r2 != r3) goto L_0x0127
            r2 = 4
            if (r1 != r2) goto L_0x0127
            java.lang.String r1 = r12.stringVal()     // Catch:{ Exception -> 0x011b }
            r12.nextToken()     // Catch:{ Exception -> 0x011b }
            java.lang.Object r1 = r8.createFactoryInstance(r13, r1)     // Catch:{ Exception -> 0x011b }
            r9.setContext(r7)
            return r1
        L_0x011b:
            r0 = move-exception
            r1 = r0
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0049 }
            java.lang.String r3 = r1.getMessage()     // Catch:{ all -> 0x0049 }
            r2.<init>(r3, r1)     // Catch:{ all -> 0x0049 }
            throw r2     // Catch:{ all -> 0x0049 }
        L_0x0127:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0049 }
            r1.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = "syntax error, expect {, actual "
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = r12.tokenName()     // Catch:{ all -> 0x0049 }
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = ", pos "
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            int r2 = r12.pos()     // Catch:{ all -> 0x0049 }
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            boolean r2 = r11 instanceof java.lang.String     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x0150
            java.lang.String r2 = ", fieldName "
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            r1.append(r11)     // Catch:{ all -> 0x0049 }
        L_0x0150:
            java.lang.String r2 = ", fastjson-version "
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = "1.2.69"
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0049 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0049 }
            r2.<init>(r1)     // Catch:{ all -> 0x0049 }
            throw r2     // Catch:{ all -> 0x0049 }
        L_0x0164:
            int r1 = r9.resolveStatus     // Catch:{ all -> 0x098d }
            r2 = 2
            if (r1 != r2) goto L_0x016d
            r6 = 0
            r9.resolveStatus = r6     // Catch:{ all -> 0x0049 }
            goto L_0x016e
        L_0x016d:
            r6 = 0
        L_0x016e:
            com.alibaba.fastjson.util.JavaBeanInfo r1 = r8.beanInfo     // Catch:{ all -> 0x098d }
            java.lang.String r5 = r1.typeKey     // Catch:{ all -> 0x098d }
            r1 = r36
            r2 = r38
            r3 = r6
            r4 = r3
            r6 = r15
            r20 = r6
        L_0x017b:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r15 = r8.sortedFieldDeserializers     // Catch:{ all -> 0x0988 }
            int r14 = r15.length     // Catch:{ all -> 0x0988 }
            if (r4 >= r14) goto L_0x01a9
            r14 = 16
            if (r3 >= r14) goto L_0x01a9
            r14 = r15[r4]     // Catch:{ all -> 0x01a6 }
            com.alibaba.fastjson.util.FieldInfo r15 = r14.fieldInfo     // Catch:{ all -> 0x01a6 }
            r36 = r4
            java.lang.Class<?> r4 = r15.fieldClass     // Catch:{ all -> 0x01a6 }
            com.alibaba.fastjson.annotation.JSONField r21 = r15.getAnnotation()     // Catch:{ all -> 0x01a6 }
            if (r21 == 0) goto L_0x01a0
            r37 = r4
            boolean r4 = r14 instanceof com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer     // Catch:{ all -> 0x01a6 }
            if (r4 == 0) goto L_0x01a2
            r4 = r14
            com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer r4 = (com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer) r4     // Catch:{ all -> 0x01a6 }
            boolean r4 = r4.customDeserilizer     // Catch:{ all -> 0x01a6 }
            r10 = r37
            goto L_0x01b1
        L_0x01a0:
            r37 = r4
        L_0x01a2:
            r10 = r37
            r4 = 0
            goto L_0x01b1
        L_0x01a6:
            r0 = move-exception
            goto L_0x039e
        L_0x01a9:
            r36 = r4
            r4 = 0
            r10 = 0
            r14 = 0
            r15 = 0
            r21 = 0
        L_0x01b1:
            r22 = 0
            r24 = 0
            r25 = 0
            if (r14 == 0) goto L_0x03a2
            r37 = r2
            char[] r2 = r15.name_chars     // Catch:{ all -> 0x039b }
            if (r4 == 0) goto L_0x01cd
            boolean r4 = r12.matchField((char[]) r2)     // Catch:{ all -> 0x01a6 }
            if (r4 == 0) goto L_0x01cd
            r38 = r1
        L_0x01c7:
            r28 = r10
            r1 = 0
            r2 = 1
            goto L_0x03aa
        L_0x01cd:
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ all -> 0x039b }
            r38 = r1
            r1 = -2
            if (r10 == r4) goto L_0x0365
            java.lang.Class<java.lang.Integer> r4 = java.lang.Integer.class
            if (r10 != r4) goto L_0x01da
            goto L_0x0365
        L_0x01da:
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ all -> 0x056a }
            if (r10 == r4) goto L_0x0345
            java.lang.Class<java.lang.Long> r4 = java.lang.Long.class
            if (r10 != r4) goto L_0x01e4
            goto L_0x0345
        L_0x01e4:
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            if (r10 != r4) goto L_0x0202
            java.lang.String r2 = r12.scanFieldString(r2)     // Catch:{ all -> 0x056a }
            int r4 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r4 <= 0) goto L_0x01f7
        L_0x01f0:
            r1 = r2
        L_0x01f1:
            r28 = r10
        L_0x01f3:
            r2 = 1
            r4 = 1
            goto L_0x03ab
        L_0x01f7:
            int r4 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r4 != r1) goto L_0x01fd
            goto L_0x0382
        L_0x01fd:
            r1 = r2
        L_0x01fe:
            r28 = r10
            goto L_0x03a9
        L_0x0202:
            java.lang.Class<java.util.Date> r4 = java.util.Date.class
            if (r10 != r4) goto L_0x0219
            java.lang.String r4 = r15.format     // Catch:{ all -> 0x056a }
            if (r4 != 0) goto L_0x0219
            java.util.Date r2 = r12.scanFieldDate(r2)     // Catch:{ all -> 0x056a }
            int r4 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r4 <= 0) goto L_0x0213
            goto L_0x01f0
        L_0x0213:
            int r4 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r4 != r1) goto L_0x01fd
            goto L_0x0382
        L_0x0219:
            java.lang.Class<java.math.BigDecimal> r4 = java.math.BigDecimal.class
            if (r10 != r4) goto L_0x022c
            java.math.BigDecimal r2 = r12.scanFieldDecimal(r2)     // Catch:{ all -> 0x056a }
            int r4 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r4 <= 0) goto L_0x0226
            goto L_0x01f0
        L_0x0226:
            int r4 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r4 != r1) goto L_0x01fd
            goto L_0x0382
        L_0x022c:
            java.lang.Class<java.math.BigInteger> r4 = java.math.BigInteger.class
            if (r10 != r4) goto L_0x023f
            java.math.BigInteger r2 = r12.scanFieldBigInteger(r2)     // Catch:{ all -> 0x056a }
            int r4 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r4 <= 0) goto L_0x0239
            goto L_0x01f0
        L_0x0239:
            int r4 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r4 != r1) goto L_0x01fd
            goto L_0x0382
        L_0x023f:
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x056a }
            if (r10 == r4) goto L_0x0329
            java.lang.Class<java.lang.Boolean> r4 = java.lang.Boolean.class
            if (r10 != r4) goto L_0x0249
            goto L_0x0329
        L_0x0249:
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ all -> 0x056a }
            if (r10 == r4) goto L_0x0309
            java.lang.Class<java.lang.Float> r4 = java.lang.Float.class
            if (r10 != r4) goto L_0x0253
            goto L_0x0309
        L_0x0253:
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ all -> 0x056a }
            if (r10 == r4) goto L_0x02e5
            java.lang.Class<java.lang.Double> r4 = java.lang.Double.class
            if (r10 != r4) goto L_0x025d
            goto L_0x02e5
        L_0x025d:
            boolean r4 = r10.isEnum()     // Catch:{ all -> 0x056a }
            if (r4 == 0) goto L_0x029a
            com.alibaba.fastjson.parser.ParserConfig r4 = r33.getConfig()     // Catch:{ all -> 0x056a }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r4 = r4.getDeserializer((java.lang.reflect.Type) r10)     // Catch:{ all -> 0x056a }
            boolean r4 = r4 instanceof com.alibaba.fastjson.parser.deserializer.EnumDeserializer     // Catch:{ all -> 0x056a }
            if (r4 == 0) goto L_0x029a
            if (r21 == 0) goto L_0x0279
            java.lang.Class r4 = r21.deserializeUsing()     // Catch:{ all -> 0x056a }
            java.lang.Class<java.lang.Void> r1 = java.lang.Void.class
            if (r4 != r1) goto L_0x029a
        L_0x0279:
            boolean r1 = r14 instanceof com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer     // Catch:{ all -> 0x056a }
            if (r1 == 0) goto L_0x03a6
            r1 = r14
            com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer r1 = (com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer) r1     // Catch:{ all -> 0x056a }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r1 = r1.fieldValueDeserilizer     // Catch:{ all -> 0x056a }
            java.lang.Enum r1 = r8.scanEnum(r12, r2, r1)     // Catch:{ all -> 0x056a }
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r2 <= 0) goto L_0x028d
            r2 = 1
            r4 = 1
            goto L_0x0296
        L_0x028d:
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            r4 = -2
            if (r2 != r4) goto L_0x0294
            goto L_0x0382
        L_0x0294:
            r2 = 0
            r4 = 0
        L_0x0296:
            r28 = r10
            goto L_0x03ab
        L_0x029a:
            java.lang.Class<int[]> r1 = int[].class
            if (r10 != r1) goto L_0x02af
            int[] r1 = r12.scanFieldIntArray(r2)     // Catch:{ all -> 0x056a }
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r2 <= 0) goto L_0x02a8
        L_0x02a6:
            goto L_0x01f1
        L_0x02a8:
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            r4 = -2
            if (r2 != r4) goto L_0x01fe
            goto L_0x0382
        L_0x02af:
            java.lang.Class<float[]> r1 = float[].class
            if (r10 != r1) goto L_0x02c3
            float[] r1 = r12.scanFieldFloatArray(r2)     // Catch:{ all -> 0x056a }
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r2 <= 0) goto L_0x02bc
            goto L_0x02a6
        L_0x02bc:
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            r4 = -2
            if (r2 != r4) goto L_0x01fe
            goto L_0x0382
        L_0x02c3:
            java.lang.Class<float[][]> r1 = float[][].class
            if (r10 != r1) goto L_0x02d7
            float[][] r1 = r12.scanFieldFloatArray2(r2)     // Catch:{ all -> 0x056a }
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r2 <= 0) goto L_0x02d0
            goto L_0x02a6
        L_0x02d0:
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            r4 = -2
            if (r2 != r4) goto L_0x01fe
            goto L_0x0382
        L_0x02d7:
            boolean r1 = r12.matchField((char[]) r2)     // Catch:{ all -> 0x056a }
            if (r1 == 0) goto L_0x02df
            goto L_0x01c7
        L_0x02df:
            r1 = r38
            r21 = r3
            goto L_0x03d8
        L_0x02e5:
            double r1 = r12.scanFieldDouble(r2)     // Catch:{ all -> 0x056a }
            int r4 = (r1 > r22 ? 1 : (r1 == r22 ? 0 : -1))
            if (r4 != 0) goto L_0x02f6
            int r4 = r12.matchStat     // Catch:{ all -> 0x056a }
            r28 = r10
            r10 = 5
            if (r4 != r10) goto L_0x02f8
            r1 = 0
            goto L_0x02fc
        L_0x02f6:
            r28 = r10
        L_0x02f8:
            java.lang.Double r1 = java.lang.Double.valueOf(r1)     // Catch:{ all -> 0x056a }
        L_0x02fc:
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r2 <= 0) goto L_0x0302
        L_0x0300:
            goto L_0x01f3
        L_0x0302:
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            r4 = -2
            if (r2 != r4) goto L_0x03a9
            goto L_0x0382
        L_0x0309:
            r28 = r10
            float r1 = r12.scanFieldFloat(r2)     // Catch:{ all -> 0x056a }
            int r2 = (r1 > r24 ? 1 : (r1 == r24 ? 0 : -1))
            if (r2 != 0) goto L_0x031a
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            r4 = 5
            if (r2 != r4) goto L_0x031a
            r1 = 0
            goto L_0x031e
        L_0x031a:
            java.lang.Float r1 = java.lang.Float.valueOf(r1)     // Catch:{ all -> 0x056a }
        L_0x031e:
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r2 <= 0) goto L_0x0323
            goto L_0x0300
        L_0x0323:
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            r4 = -2
            if (r2 != r4) goto L_0x03a9
            goto L_0x0382
        L_0x0329:
            r28 = r10
            boolean r1 = r12.scanFieldBoolean(r2)     // Catch:{ all -> 0x056a }
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            r4 = 5
            if (r2 != r4) goto L_0x0336
            r1 = 0
            goto L_0x033a
        L_0x0336:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x056a }
        L_0x033a:
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r2 <= 0) goto L_0x033f
            goto L_0x0300
        L_0x033f:
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            r4 = -2
            if (r2 != r4) goto L_0x03a9
            goto L_0x0382
        L_0x0345:
            r28 = r10
            long r1 = r12.scanFieldLong(r2)     // Catch:{ all -> 0x056a }
            int r4 = (r1 > r25 ? 1 : (r1 == r25 ? 0 : -1))
            if (r4 != 0) goto L_0x0356
            int r4 = r12.matchStat     // Catch:{ all -> 0x056a }
            r10 = 5
            if (r4 != r10) goto L_0x0356
            r1 = 0
            goto L_0x035a
        L_0x0356:
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x056a }
        L_0x035a:
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r2 <= 0) goto L_0x035f
            goto L_0x0300
        L_0x035f:
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            r4 = -2
            if (r2 != r4) goto L_0x03a9
            goto L_0x0382
        L_0x0365:
            r28 = r10
            int r1 = r12.scanFieldInt(r2)     // Catch:{ all -> 0x056a }
            if (r1 != 0) goto L_0x0374
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            r4 = 5
            if (r2 != r4) goto L_0x0374
            r1 = 0
            goto L_0x0378
        L_0x0374:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x056a }
        L_0x0378:
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            if (r2 <= 0) goto L_0x037d
            goto L_0x0300
        L_0x037d:
            int r2 = r12.matchStat     // Catch:{ all -> 0x056a }
            r4 = -2
            if (r2 != r4) goto L_0x03a9
        L_0x0382:
            int r3 = r3 + 1
            r10 = r36
            r18 = r38
            r16 = r3
            r15 = r5
            r30 = r6
            r3 = r7
            r21 = r20
            r1 = 0
            r2 = 16
            r4 = 0
            r6 = 1
            r14 = 13
            r27 = 4
            goto L_0x0557
        L_0x039b:
            r0 = move-exception
            r38 = r1
        L_0x039e:
            r2 = r0
            r15 = r6
            goto L_0x004d
        L_0x03a2:
            r38 = r1
            r37 = r2
        L_0x03a6:
            r28 = r10
            r1 = 0
        L_0x03a9:
            r2 = 0
        L_0x03aa:
            r4 = 0
        L_0x03ab:
            if (r2 != 0) goto L_0x056f
            com.alibaba.fastjson.parser.SymbolTable r10 = r9.symbolTable     // Catch:{ all -> 0x056a }
            java.lang.String r10 = r12.scanSymbol(r10)     // Catch:{ all -> 0x056a }
            if (r10 != 0) goto L_0x03dc
            r21 = r3
            int r3 = r12.token()     // Catch:{ all -> 0x056a }
            r27 = r1
            r1 = 13
            if (r3 != r1) goto L_0x03ca
            r1 = 16
            r12.nextToken(r1)     // Catch:{ all -> 0x056a }
            r1 = r38
            goto L_0x053b
        L_0x03ca:
            r1 = 16
            if (r3 != r1) goto L_0x03e0
            com.alibaba.fastjson.parser.Feature r1 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x056a }
            boolean r1 = r12.isEnabled((com.alibaba.fastjson.parser.Feature) r1)     // Catch:{ all -> 0x056a }
            if (r1 == 0) goto L_0x03e0
            r1 = r38
        L_0x03d8:
            r3 = 13
            goto L_0x0543
        L_0x03dc:
            r27 = r1
            r21 = r3
        L_0x03e0:
            java.lang.String r1 = "$ref"
            if (r1 != r10) goto L_0x04bf
            if (r7 == 0) goto L_0x04bf
            r1 = 4
            r12.nextTokenWithColon(r1)     // Catch:{ all -> 0x056a }
            int r2 = r12.token()     // Catch:{ all -> 0x056a }
            if (r2 != r1) goto L_0x04a4
            java.lang.String r1 = r12.stringVal()     // Catch:{ all -> 0x056a }
            java.lang.String r2 = "@"
            boolean r2 = r2.equals(r1)     // Catch:{ all -> 0x056a }
            if (r2 == 0) goto L_0x0402
            java.lang.Object r1 = r7.object     // Catch:{ all -> 0x056a }
        L_0x03fe:
            r2 = 13
            goto L_0x0483
        L_0x0402:
            java.lang.String r2 = ".."
            boolean r2 = r2.equals(r1)     // Catch:{ all -> 0x056a }
            if (r2 == 0) goto L_0x041f
            com.alibaba.fastjson.parser.ParseContext r2 = r7.parent     // Catch:{ all -> 0x056a }
            java.lang.Object r3 = r2.object     // Catch:{ all -> 0x056a }
            if (r3 == 0) goto L_0x0413
            java.lang.Object r1 = r2.object     // Catch:{ all -> 0x056a }
            goto L_0x03fe
        L_0x0413:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r3 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x056a }
            r3.<init>(r2, r1)     // Catch:{ all -> 0x056a }
            r9.addResolveTask(r3)     // Catch:{ all -> 0x056a }
            r1 = 1
            r9.resolveStatus = r1     // Catch:{ all -> 0x056a }
            goto L_0x047f
        L_0x041f:
            java.lang.String r2 = "$"
            boolean r2 = r2.equals(r1)     // Catch:{ all -> 0x056a }
            if (r2 == 0) goto L_0x0442
            r2 = r7
        L_0x0428:
            com.alibaba.fastjson.parser.ParseContext r3 = r2.parent     // Catch:{ all -> 0x056a }
            if (r3 == 0) goto L_0x042f
            com.alibaba.fastjson.parser.ParseContext r2 = r2.parent     // Catch:{ all -> 0x056a }
            goto L_0x0428
        L_0x042f:
            java.lang.Object r3 = r2.object     // Catch:{ all -> 0x056a }
            if (r3 == 0) goto L_0x0436
            java.lang.Object r1 = r2.object     // Catch:{ all -> 0x056a }
            goto L_0x03fe
        L_0x0436:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r3 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x056a }
            r3.<init>(r2, r1)     // Catch:{ all -> 0x056a }
            r9.addResolveTask(r3)     // Catch:{ all -> 0x056a }
            r1 = 1
            r9.resolveStatus = r1     // Catch:{ all -> 0x056a }
            goto L_0x047f
        L_0x0442:
            r2 = 92
            int r3 = r1.indexOf(r2)     // Catch:{ all -> 0x056a }
            if (r3 <= 0) goto L_0x046c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x056a }
            r3.<init>()     // Catch:{ all -> 0x056a }
            r4 = 0
        L_0x0450:
            int r5 = r1.length()     // Catch:{ all -> 0x056a }
            if (r4 >= r5) goto L_0x0468
            char r5 = r1.charAt(r4)     // Catch:{ all -> 0x056a }
            if (r5 != r2) goto L_0x0462
            int r4 = r4 + 1
            char r5 = r1.charAt(r4)     // Catch:{ all -> 0x056a }
        L_0x0462:
            r3.append(r5)     // Catch:{ all -> 0x056a }
            r5 = 1
            int r4 = r4 + r5
            goto L_0x0450
        L_0x0468:
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x056a }
        L_0x046c:
            java.lang.Object r2 = r9.resolveReference(r1)     // Catch:{ all -> 0x056a }
            if (r2 == 0) goto L_0x0474
            r1 = r2
            goto L_0x03fe
        L_0x0474:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r2 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x056a }
            r2.<init>(r7, r1)     // Catch:{ all -> 0x056a }
            r9.addResolveTask(r2)     // Catch:{ all -> 0x056a }
            r1 = 1
            r9.resolveStatus = r1     // Catch:{ all -> 0x056a }
        L_0x047f:
            r1 = r38
            goto L_0x03fe
        L_0x0483:
            r12.nextToken(r2)     // Catch:{ all -> 0x01a6 }
            int r3 = r12.token()     // Catch:{ all -> 0x01a6 }
            if (r3 != r2) goto L_0x049c
            r2 = 16
            r12.nextToken(r2)     // Catch:{ all -> 0x01a6 }
            r9.setContext(r7, r1, r11)     // Catch:{ all -> 0x01a6 }
            if (r6 == 0) goto L_0x0498
            r6.object = r1
        L_0x0498:
            r9.setContext(r7)
            return r1
        L_0x049c:
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x01a6 }
            java.lang.String r3 = "illegal ref"
            r2.<init>(r3)     // Catch:{ all -> 0x01a6 }
            throw r2     // Catch:{ all -> 0x01a6 }
        L_0x04a4:
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x056a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x056a }
            r3.<init>()     // Catch:{ all -> 0x056a }
            java.lang.String r4 = "illegal ref, "
            r3.append(r4)     // Catch:{ all -> 0x056a }
            java.lang.String r2 = com.alibaba.fastjson.parser.JSONToken.name(r2)     // Catch:{ all -> 0x056a }
            r3.append(r2)     // Catch:{ all -> 0x056a }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x056a }
            r1.<init>(r2)     // Catch:{ all -> 0x056a }
            throw r1     // Catch:{ all -> 0x056a }
        L_0x04bf:
            if (r5 == 0) goto L_0x04c7
            boolean r1 = r5.equals(r10)     // Catch:{ all -> 0x056a }
            if (r1 != 0) goto L_0x04cb
        L_0x04c7:
            java.lang.String r1 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x056a }
            if (r1 != r10) goto L_0x0565
        L_0x04cb:
            r1 = 4
            r12.nextTokenWithColon(r1)     // Catch:{ all -> 0x056a }
            int r2 = r12.token()     // Catch:{ all -> 0x056a }
            if (r2 != r1) goto L_0x055b
            java.lang.String r1 = r12.stringVal()     // Catch:{ all -> 0x056a }
            r2 = 16
            r12.nextToken(r2)     // Catch:{ all -> 0x056a }
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x056a }
            java.lang.String r2 = r2.typeName     // Catch:{ all -> 0x056a }
            boolean r2 = r1.equals(r2)     // Catch:{ all -> 0x056a }
            if (r2 != 0) goto L_0x052e
            com.alibaba.fastjson.parser.Feature r2 = com.alibaba.fastjson.parser.Feature.IgnoreAutoType     // Catch:{ all -> 0x056a }
            boolean r2 = r9.isEnabled(r2)     // Catch:{ all -> 0x056a }
            if (r2 == 0) goto L_0x04f1
            goto L_0x052e
        L_0x04f1:
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x056a }
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r2 = getSeeAlso(r13, r2, r1)     // Catch:{ all -> 0x056a }
            if (r2 != 0) goto L_0x050e
            java.lang.Class r2 = com.alibaba.fastjson.util.TypeUtils.getClass(r34)     // Catch:{ all -> 0x056a }
            int r3 = r12.getFeatures()     // Catch:{ all -> 0x056a }
            java.lang.Class r15 = r13.checkAutoType(r1, r2, r3)     // Catch:{ all -> 0x056a }
            com.alibaba.fastjson.parser.ParserConfig r2 = r33.getConfig()     // Catch:{ all -> 0x056a }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r2 = r2.getDeserializer((java.lang.reflect.Type) r15)     // Catch:{ all -> 0x056a }
            goto L_0x050f
        L_0x050e:
            r15 = 0
        L_0x050f:
            java.lang.Object r3 = r2.deserialze(r9, r15, r11)     // Catch:{ all -> 0x056a }
            boolean r4 = r2 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer     // Catch:{ all -> 0x056a }
            if (r4 == 0) goto L_0x0524
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r2 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r2     // Catch:{ all -> 0x056a }
            if (r5 == 0) goto L_0x0524
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r2 = r2.getFieldDeserializer((java.lang.String) r5)     // Catch:{ all -> 0x056a }
            if (r2 == 0) goto L_0x0524
            r2.setValue((java.lang.Object) r3, (java.lang.String) r1)     // Catch:{ all -> 0x056a }
        L_0x0524:
            if (r6 == 0) goto L_0x052a
            r1 = r38
            r6.object = r1
        L_0x052a:
            r9.setContext(r7)
            return r3
        L_0x052e:
            r1 = r38
            int r2 = r12.token()     // Catch:{ all -> 0x01a6 }
            r3 = 13
            if (r2 != r3) goto L_0x0543
            r12.nextToken()     // Catch:{ all -> 0x01a6 }
        L_0x053b:
            r31 = r7
            r2 = r20
            r36 = 0
            goto L_0x06ae
        L_0x0543:
            r10 = r36
            r18 = r1
            r14 = r3
            r15 = r5
            r30 = r6
            r3 = r7
            r16 = r21
            r1 = 0
            r2 = 16
            r4 = 0
            r6 = 1
            r27 = 4
            r21 = r20
        L_0x0557:
            r20 = r37
            goto L_0x0941
        L_0x055b:
            r1 = r38
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x01a6 }
            java.lang.String r3 = "syntax error"
            r2.<init>(r3)     // Catch:{ all -> 0x01a6 }
            throw r2     // Catch:{ all -> 0x01a6 }
        L_0x0565:
            r1 = r38
            r3 = 13
            goto L_0x0578
        L_0x056a:
            r0 = move-exception
            r1 = r38
            goto L_0x039e
        L_0x056f:
            r27 = r1
            r21 = r3
            r3 = 13
            r1 = r38
            r10 = 0
        L_0x0578:
            if (r1 != 0) goto L_0x05a8
            if (r20 != 0) goto L_0x05a8
            java.lang.Object r1 = r32.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r33, (java.lang.reflect.Type) r34)     // Catch:{ all -> 0x01a6 }
            if (r1 != 0) goto L_0x058f
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ all -> 0x01a6 }
            r29 = r5
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r5 = r8.fieldDeserializers     // Catch:{ all -> 0x01a6 }
            int r5 = r5.length     // Catch:{ all -> 0x01a6 }
            r3.<init>(r5)     // Catch:{ all -> 0x01a6 }
            r20 = r3
            goto L_0x0591
        L_0x058f:
            r29 = r5
        L_0x0591:
            com.alibaba.fastjson.parser.ParseContext r6 = r9.setContext(r7, r1, r11)     // Catch:{ all -> 0x01a6 }
            if (r37 != 0) goto L_0x05aa
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r3 = r8.fieldDeserializers     // Catch:{ all -> 0x01a6 }
            int r3 = r3.length     // Catch:{ all -> 0x01a6 }
            int r3 = r3 / 32
            r5 = 1
            int r3 = r3 + r5
            int[] r3 = new int[r3]     // Catch:{ all -> 0x01a6 }
            r30 = r6
            r5 = r20
            r6 = r1
            r20 = r3
            goto L_0x05b1
        L_0x05a8:
            r29 = r5
        L_0x05aa:
            r30 = r6
            r5 = r20
            r20 = r37
            r6 = r1
        L_0x05b1:
            if (r2 == 0) goto L_0x062b
            if (r4 != 0) goto L_0x05ce
            r4 = r34
            r14.parseField(r9, r6, r4, r5)     // Catch:{ all -> 0x063c }
            r10 = r36
            r18 = r6
            r31 = r7
            r16 = r21
            r15 = r29
            r36 = 0
            r14 = 13
            r27 = 4
        L_0x05ca:
            r21 = r5
            goto L_0x0692
        L_0x05ce:
            r4 = r34
            r1 = r28
            if (r6 != 0) goto L_0x05dc
            java.lang.String r1 = r15.name     // Catch:{ all -> 0x063c }
            r2 = r27
            r5.put(r1, r2)     // Catch:{ all -> 0x063c }
            goto L_0x05fb
        L_0x05dc:
            r2 = r27
            if (r2 != 0) goto L_0x05f8
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch:{ all -> 0x063c }
            if (r1 == r3) goto L_0x05fb
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ all -> 0x063c }
            if (r1 == r3) goto L_0x05fb
            java.lang.Class r3 = java.lang.Float.TYPE     // Catch:{ all -> 0x063c }
            if (r1 == r3) goto L_0x05fb
            java.lang.Class r3 = java.lang.Double.TYPE     // Catch:{ all -> 0x063c }
            if (r1 == r3) goto L_0x05fb
            java.lang.Class r3 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x063c }
            if (r1 == r3) goto L_0x05fb
            r14.setValue((java.lang.Object) r6, (java.lang.Object) r2)     // Catch:{ all -> 0x063c }
            goto L_0x05fb
        L_0x05f8:
            r14.setValue((java.lang.Object) r6, (java.lang.Object) r2)     // Catch:{ all -> 0x063c }
        L_0x05fb:
            if (r20 == 0) goto L_0x060a
            int r1 = r36 / 32
            int r2 = r36 % 32
            r3 = r20[r1]     // Catch:{ all -> 0x063c }
            r14 = 1
            int r2 = r14 << r2
            r2 = r2 | r3
            r20[r1] = r2     // Catch:{ all -> 0x063c }
            goto L_0x060b
        L_0x060a:
            r14 = 1
        L_0x060b:
            int r1 = r12.matchStat     // Catch:{ all -> 0x063c }
            r15 = 4
            if (r1 != r15) goto L_0x061a
            r21 = r5
            r18 = r6
            r31 = r7
            r36 = 0
            goto L_0x06a8
        L_0x061a:
            r10 = r36
            r18 = r6
            r31 = r7
            r27 = r15
            r16 = r21
            r15 = r29
            r36 = 0
            r14 = 13
            goto L_0x05ca
        L_0x062b:
            r4 = r34
            r14 = 1
            r15 = 4
            if (r5 != 0) goto L_0x0644
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ all -> 0x063c }
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r2 = r8.fieldDeserializers     // Catch:{ all -> 0x063c }
            int r2 = r2.length     // Catch:{ all -> 0x063c }
            r1.<init>(r2)     // Catch:{ all -> 0x063c }
            r17 = r1
            goto L_0x0646
        L_0x063c:
            r0 = move-exception
            r2 = r0
            r1 = r6
            r3 = r7
        L_0x0640:
            r15 = r30
            goto L_0x0993
        L_0x0644:
            r17 = r5
        L_0x0646:
            r1 = r32
            r2 = r33
            r16 = r21
            r19 = 13
            r3 = r10
            r10 = r36
            r4 = r6
            r21 = r5
            r27 = r15
            r15 = r29
            r5 = r34
            r18 = r6
            r14 = r19
            r36 = 0
            r6 = r17
            r31 = r7
            r7 = r20
            boolean r1 = r1.parseField(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0980 }
            if (r1 != 0) goto L_0x068a
            int r1 = r12.token()     // Catch:{ all -> 0x0680 }
            if (r1 != r14) goto L_0x0676
            r12.nextToken()     // Catch:{ all -> 0x0680 }
            goto L_0x06a8
        L_0x0676:
            r1 = r36
            r3 = r31
            r2 = 16
        L_0x067c:
            r4 = 0
            r6 = 1
            goto L_0x0941
        L_0x0680:
            r0 = move-exception
            r2 = r0
            r1 = r18
            r15 = r30
            r3 = r31
            goto L_0x0993
        L_0x068a:
            int r1 = r12.token()     // Catch:{ all -> 0x0980 }
            r2 = 17
            if (r1 == r2) goto L_0x0974
        L_0x0692:
            int r1 = r12.token()     // Catch:{ all -> 0x0980 }
            r2 = 16
            if (r1 != r2) goto L_0x069f
            r1 = r36
            r3 = r31
            goto L_0x067c
        L_0x069f:
            int r1 = r12.token()     // Catch:{ all -> 0x0980 }
            if (r1 != r14) goto L_0x092d
            r12.nextToken(r2)     // Catch:{ all -> 0x0980 }
        L_0x06a8:
            r1 = r18
            r2 = r21
            r6 = r30
        L_0x06ae:
            if (r1 != 0) goto L_0x0902
            if (r2 != 0) goto L_0x06ce
            java.lang.Object r1 = r32.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r33, (java.lang.reflect.Type) r34)     // Catch:{ all -> 0x06c9 }
            if (r6 != 0) goto L_0x06bf
            r3 = r31
            com.alibaba.fastjson.parser.ParseContext r6 = r9.setContext(r3, r1, r11)     // Catch:{ all -> 0x092b }
            goto L_0x06c1
        L_0x06bf:
            r3 = r31
        L_0x06c1:
            if (r6 == 0) goto L_0x06c5
            r6.object = r1
        L_0x06c5:
            r9.setContext(r3)
            return r1
        L_0x06c9:
            r0 = move-exception
            r3 = r31
            goto L_0x098a
        L_0x06ce:
            r3 = r31
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r8.beanInfo     // Catch:{ all -> 0x08fd }
            java.lang.String[] r4 = r4.creatorConstructorParameters     // Catch:{ all -> 0x08fd }
            java.lang.String r5 = ""
            if (r4 == 0) goto L_0x078b
            int r7 = r4.length     // Catch:{ all -> 0x092b }
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x092b }
            r10 = r36
        L_0x06dd:
            int r11 = r4.length     // Catch:{ all -> 0x092b }
            if (r10 >= r11) goto L_0x0787
            r11 = r4[r10]     // Catch:{ all -> 0x092b }
            java.lang.Object r11 = r2.remove(r11)     // Catch:{ all -> 0x092b }
            if (r11 != 0) goto L_0x0742
            com.alibaba.fastjson.util.JavaBeanInfo r12 = r8.beanInfo     // Catch:{ all -> 0x092b }
            java.lang.reflect.Type[] r12 = r12.creatorConstructorParameterTypes     // Catch:{ all -> 0x092b }
            r12 = r12[r10]     // Catch:{ all -> 0x092b }
            com.alibaba.fastjson.util.JavaBeanInfo r13 = r8.beanInfo     // Catch:{ all -> 0x092b }
            com.alibaba.fastjson.util.FieldInfo[] r13 = r13.fields     // Catch:{ all -> 0x092b }
            r13 = r13[r10]     // Catch:{ all -> 0x092b }
            java.lang.Class r14 = java.lang.Byte.TYPE     // Catch:{ all -> 0x092b }
            if (r12 != r14) goto L_0x06fd
            java.lang.Byte r11 = java.lang.Byte.valueOf(r36)     // Catch:{ all -> 0x092b }
            goto L_0x073f
        L_0x06fd:
            java.lang.Class r14 = java.lang.Short.TYPE     // Catch:{ all -> 0x092b }
            if (r12 != r14) goto L_0x0706
            java.lang.Short r11 = java.lang.Short.valueOf(r36)     // Catch:{ all -> 0x092b }
            goto L_0x073f
        L_0x0706:
            java.lang.Class r14 = java.lang.Integer.TYPE     // Catch:{ all -> 0x092b }
            if (r12 != r14) goto L_0x070f
            java.lang.Integer r11 = java.lang.Integer.valueOf(r36)     // Catch:{ all -> 0x092b }
            goto L_0x073f
        L_0x070f:
            java.lang.Class r14 = java.lang.Long.TYPE     // Catch:{ all -> 0x092b }
            if (r12 != r14) goto L_0x0718
            java.lang.Long r11 = java.lang.Long.valueOf(r25)     // Catch:{ all -> 0x092b }
            goto L_0x073f
        L_0x0718:
            java.lang.Class r14 = java.lang.Float.TYPE     // Catch:{ all -> 0x092b }
            if (r12 != r14) goto L_0x0721
            java.lang.Float r11 = java.lang.Float.valueOf(r24)     // Catch:{ all -> 0x092b }
            goto L_0x073f
        L_0x0721:
            java.lang.Class r14 = java.lang.Double.TYPE     // Catch:{ all -> 0x092b }
            if (r12 != r14) goto L_0x072a
            java.lang.Double r11 = java.lang.Double.valueOf(r22)     // Catch:{ all -> 0x092b }
            goto L_0x073f
        L_0x072a:
            java.lang.Class r14 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x092b }
            if (r12 != r14) goto L_0x0731
            java.lang.Boolean r11 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x092b }
            goto L_0x073f
        L_0x0731:
            java.lang.Class<java.lang.String> r14 = java.lang.String.class
            if (r12 != r14) goto L_0x073f
            int r12 = r13.parserFeatures     // Catch:{ all -> 0x092b }
            com.alibaba.fastjson.parser.Feature r13 = com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty     // Catch:{ all -> 0x092b }
            int r13 = r13.mask     // Catch:{ all -> 0x092b }
            r12 = r12 & r13
            if (r12 == 0) goto L_0x073f
            r11 = r5
        L_0x073f:
            r14 = r36
            goto L_0x077f
        L_0x0742:
            com.alibaba.fastjson.util.JavaBeanInfo r12 = r8.beanInfo     // Catch:{ all -> 0x092b }
            java.lang.reflect.Type[] r12 = r12.creatorConstructorParameterTypes     // Catch:{ all -> 0x092b }
            if (r12 == 0) goto L_0x073f
            com.alibaba.fastjson.util.JavaBeanInfo r12 = r8.beanInfo     // Catch:{ all -> 0x092b }
            java.lang.reflect.Type[] r12 = r12.creatorConstructorParameterTypes     // Catch:{ all -> 0x092b }
            int r12 = r12.length     // Catch:{ all -> 0x092b }
            if (r10 >= r12) goto L_0x073f
            com.alibaba.fastjson.util.JavaBeanInfo r12 = r8.beanInfo     // Catch:{ all -> 0x092b }
            java.lang.reflect.Type[] r12 = r12.creatorConstructorParameterTypes     // Catch:{ all -> 0x092b }
            r12 = r12[r10]     // Catch:{ all -> 0x092b }
            boolean r13 = r12 instanceof java.lang.Class     // Catch:{ all -> 0x092b }
            if (r13 == 0) goto L_0x073f
            java.lang.Class r12 = (java.lang.Class) r12     // Catch:{ all -> 0x092b }
            boolean r13 = r12.isInstance(r11)     // Catch:{ all -> 0x092b }
            if (r13 != 0) goto L_0x073f
            boolean r13 = r11 instanceof java.util.List     // Catch:{ all -> 0x092b }
            if (r13 == 0) goto L_0x073f
            r13 = r11
            java.util.List r13 = (java.util.List) r13     // Catch:{ all -> 0x092b }
            int r14 = r13.size()     // Catch:{ all -> 0x092b }
            r15 = 1
            if (r14 != r15) goto L_0x073f
            r14 = r36
            java.lang.Object r15 = r13.get(r14)     // Catch:{ all -> 0x092b }
            boolean r12 = r12.isInstance(r15)     // Catch:{ all -> 0x092b }
            if (r12 == 0) goto L_0x077f
            java.lang.Object r11 = r13.get(r14)     // Catch:{ all -> 0x092b }
        L_0x077f:
            r7[r10] = r11     // Catch:{ all -> 0x092b }
            int r10 = r10 + 1
            r36 = r14
            goto L_0x06dd
        L_0x0787:
            r37 = r1
            goto L_0x0801
        L_0x078b:
            r14 = r36
            com.alibaba.fastjson.util.JavaBeanInfo r7 = r8.beanInfo     // Catch:{ all -> 0x08fd }
            com.alibaba.fastjson.util.FieldInfo[] r7 = r7.fields     // Catch:{ all -> 0x08fd }
            int r10 = r7.length     // Catch:{ all -> 0x08fd }
            java.lang.Object[] r11 = new java.lang.Object[r10]     // Catch:{ all -> 0x08fd }
            r12 = r14
        L_0x0795:
            if (r12 >= r10) goto L_0x07fe
            r13 = r7[r12]     // Catch:{ all -> 0x08fd }
            java.lang.String r15 = r13.name     // Catch:{ all -> 0x08fd }
            java.lang.Object r15 = r2.get(r15)     // Catch:{ all -> 0x08fd }
            if (r15 != 0) goto L_0x07f4
            java.lang.reflect.Type r14 = r13.fieldType     // Catch:{ all -> 0x08fd }
            r37 = r1
            java.lang.Class r1 = java.lang.Byte.TYPE     // Catch:{ all -> 0x08f8 }
            if (r14 != r1) goto L_0x07af
            r1 = 0
            java.lang.Byte r15 = java.lang.Byte.valueOf(r1)     // Catch:{ all -> 0x08f8 }
            goto L_0x07f6
        L_0x07af:
            java.lang.Class r1 = java.lang.Short.TYPE     // Catch:{ all -> 0x08f8 }
            if (r14 != r1) goto L_0x07b9
            r1 = 0
            java.lang.Short r15 = java.lang.Short.valueOf(r1)     // Catch:{ all -> 0x08f8 }
            goto L_0x07f6
        L_0x07b9:
            java.lang.Class r1 = java.lang.Integer.TYPE     // Catch:{ all -> 0x08f8 }
            if (r14 != r1) goto L_0x07c3
            r1 = 0
            java.lang.Integer r15 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x08f8 }
            goto L_0x07f6
        L_0x07c3:
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x08f8 }
            if (r14 != r1) goto L_0x07cc
            java.lang.Long r15 = java.lang.Long.valueOf(r25)     // Catch:{ all -> 0x08f8 }
            goto L_0x07f6
        L_0x07cc:
            java.lang.Class r1 = java.lang.Float.TYPE     // Catch:{ all -> 0x08f8 }
            if (r14 != r1) goto L_0x07d5
            java.lang.Float r15 = java.lang.Float.valueOf(r24)     // Catch:{ all -> 0x08f8 }
            goto L_0x07f6
        L_0x07d5:
            java.lang.Class r1 = java.lang.Double.TYPE     // Catch:{ all -> 0x08f8 }
            if (r14 != r1) goto L_0x07de
            java.lang.Double r15 = java.lang.Double.valueOf(r22)     // Catch:{ all -> 0x08f8 }
            goto L_0x07f6
        L_0x07de:
            java.lang.Class r1 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x08f8 }
            if (r14 != r1) goto L_0x07e5
            java.lang.Boolean r15 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x08f8 }
            goto L_0x07f6
        L_0x07e5:
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            if (r14 != r1) goto L_0x07f6
            int r1 = r13.parserFeatures     // Catch:{ all -> 0x08f8 }
            com.alibaba.fastjson.parser.Feature r13 = com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty     // Catch:{ all -> 0x08f8 }
            int r13 = r13.mask     // Catch:{ all -> 0x08f8 }
            r1 = r1 & r13
            if (r1 == 0) goto L_0x07f6
            r15 = r5
            goto L_0x07f6
        L_0x07f4:
            r37 = r1
        L_0x07f6:
            r11[r12] = r15     // Catch:{ all -> 0x08f8 }
            int r12 = r12 + 1
            r1 = r37
            r14 = 0
            goto L_0x0795
        L_0x07fe:
            r37 = r1
            r7 = r11
        L_0x0801:
            com.alibaba.fastjson.util.JavaBeanInfo r1 = r8.beanInfo     // Catch:{ all -> 0x08f8 }
            java.lang.reflect.Constructor<?> r1 = r1.creatorConstructor     // Catch:{ all -> 0x08f8 }
            if (r1 == 0) goto L_0x08c0
            com.alibaba.fastjson.util.JavaBeanInfo r1 = r8.beanInfo     // Catch:{ all -> 0x08f8 }
            boolean r1 = r1.f40kotlin     // Catch:{ all -> 0x08f8 }
            if (r1 == 0) goto L_0x0863
            r1 = 0
        L_0x080e:
            int r5 = r7.length     // Catch:{ all -> 0x08f8 }
            if (r1 >= r5) goto L_0x0863
            r5 = r7[r1]     // Catch:{ all -> 0x08f8 }
            if (r5 != 0) goto L_0x0860
            com.alibaba.fastjson.util.JavaBeanInfo r5 = r8.beanInfo     // Catch:{ all -> 0x08f8 }
            com.alibaba.fastjson.util.FieldInfo[] r5 = r5.fields     // Catch:{ all -> 0x08f8 }
            if (r5 == 0) goto L_0x0860
            com.alibaba.fastjson.util.JavaBeanInfo r5 = r8.beanInfo     // Catch:{ all -> 0x08f8 }
            com.alibaba.fastjson.util.FieldInfo[] r5 = r5.fields     // Catch:{ all -> 0x08f8 }
            int r5 = r5.length     // Catch:{ all -> 0x08f8 }
            if (r1 >= r5) goto L_0x0860
            com.alibaba.fastjson.util.JavaBeanInfo r5 = r8.beanInfo     // Catch:{ all -> 0x08f8 }
            com.alibaba.fastjson.util.FieldInfo[] r5 = r5.fields     // Catch:{ all -> 0x08f8 }
            r1 = r5[r1]     // Catch:{ all -> 0x08f8 }
            java.lang.Class<?> r1 = r1.fieldClass     // Catch:{ all -> 0x08f8 }
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            if (r1 != r5) goto L_0x0863
            com.alibaba.fastjson.util.JavaBeanInfo r1 = r8.beanInfo     // Catch:{ Exception -> 0x0895 }
            java.lang.reflect.Constructor<?> r1 = r1.kotlinDefaultConstructor     // Catch:{ Exception -> 0x0895 }
            if (r1 == 0) goto L_0x0863
            com.alibaba.fastjson.util.JavaBeanInfo r1 = r8.beanInfo     // Catch:{ Exception -> 0x0895 }
            java.lang.reflect.Constructor<?> r1 = r1.kotlinDefaultConstructor     // Catch:{ Exception -> 0x0895 }
            r5 = 0
            java.lang.Object r1 = r1.newInstance(r5)     // Catch:{ Exception -> 0x0895 }
            r5 = 0
        L_0x083e:
            int r10 = r7.length     // Catch:{ Exception -> 0x085e }
            if (r5 >= r10) goto L_0x086b
            r10 = r7[r5]     // Catch:{ Exception -> 0x085e }
            if (r10 == 0) goto L_0x085b
            com.alibaba.fastjson.util.JavaBeanInfo r11 = r8.beanInfo     // Catch:{ Exception -> 0x085e }
            com.alibaba.fastjson.util.FieldInfo[] r11 = r11.fields     // Catch:{ Exception -> 0x085e }
            if (r11 == 0) goto L_0x085b
            com.alibaba.fastjson.util.JavaBeanInfo r11 = r8.beanInfo     // Catch:{ Exception -> 0x085e }
            com.alibaba.fastjson.util.FieldInfo[] r11 = r11.fields     // Catch:{ Exception -> 0x085e }
            int r11 = r11.length     // Catch:{ Exception -> 0x085e }
            if (r5 >= r11) goto L_0x085b
            com.alibaba.fastjson.util.JavaBeanInfo r11 = r8.beanInfo     // Catch:{ Exception -> 0x085e }
            com.alibaba.fastjson.util.FieldInfo[] r11 = r11.fields     // Catch:{ Exception -> 0x085e }
            r11 = r11[r5]     // Catch:{ Exception -> 0x085e }
            r11.set(r1, r10)     // Catch:{ Exception -> 0x085e }
        L_0x085b:
            int r5 = r5 + 1
            goto L_0x083e
        L_0x085e:
            r0 = move-exception
            goto L_0x0898
        L_0x0860:
            int r1 = r1 + 1
            goto L_0x080e
        L_0x0863:
            com.alibaba.fastjson.util.JavaBeanInfo r1 = r8.beanInfo     // Catch:{ Exception -> 0x0895 }
            java.lang.reflect.Constructor<?> r1 = r1.creatorConstructor     // Catch:{ Exception -> 0x0895 }
            java.lang.Object r1 = r1.newInstance(r7)     // Catch:{ Exception -> 0x0895 }
        L_0x086b:
            if (r4 == 0) goto L_0x08f3
            java.util.Set r2 = r2.entrySet()     // Catch:{ all -> 0x092b }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x092b }
        L_0x0875:
            boolean r4 = r2.hasNext()     // Catch:{ all -> 0x092b }
            if (r4 == 0) goto L_0x08f3
            java.lang.Object r4 = r2.next()     // Catch:{ all -> 0x092b }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ all -> 0x092b }
            java.lang.Object r5 = r4.getKey()     // Catch:{ all -> 0x092b }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x092b }
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r5 = r8.getFieldDeserializer((java.lang.String) r5)     // Catch:{ all -> 0x092b }
            if (r5 == 0) goto L_0x0875
            java.lang.Object r4 = r4.getValue()     // Catch:{ all -> 0x092b }
            r5.setValue((java.lang.Object) r1, (java.lang.Object) r4)     // Catch:{ all -> 0x092b }
            goto L_0x0875
        L_0x0895:
            r0 = move-exception
            r1 = r37
        L_0x0898:
            r2 = r0
            com.alibaba.fastjson.JSONException r5 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x092b }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x092b }
            r7.<init>()     // Catch:{ all -> 0x092b }
            java.lang.String r10 = "create instance error, "
            r7.append(r10)     // Catch:{ all -> 0x092b }
            r7.append(r4)     // Catch:{ all -> 0x092b }
            java.lang.String r4 = ", "
            r7.append(r4)     // Catch:{ all -> 0x092b }
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r8.beanInfo     // Catch:{ all -> 0x092b }
            java.lang.reflect.Constructor<?> r4 = r4.creatorConstructor     // Catch:{ all -> 0x092b }
            java.lang.String r4 = r4.toGenericString()     // Catch:{ all -> 0x092b }
            r7.append(r4)     // Catch:{ all -> 0x092b }
            java.lang.String r4 = r7.toString()     // Catch:{ all -> 0x092b }
            r5.<init>(r4, r2)     // Catch:{ all -> 0x092b }
            throw r5     // Catch:{ all -> 0x092b }
        L_0x08c0:
            com.alibaba.fastjson.util.JavaBeanInfo r1 = r8.beanInfo     // Catch:{ all -> 0x08f8 }
            java.lang.reflect.Method r1 = r1.factoryMethod     // Catch:{ all -> 0x08f8 }
            if (r1 == 0) goto L_0x08f1
            com.alibaba.fastjson.util.JavaBeanInfo r1 = r8.beanInfo     // Catch:{ Exception -> 0x08d0 }
            java.lang.reflect.Method r1 = r1.factoryMethod     // Catch:{ Exception -> 0x08d0 }
            r2 = 0
            java.lang.Object r1 = r1.invoke(r2, r7)     // Catch:{ Exception -> 0x08d0 }
            goto L_0x08f3
        L_0x08d0:
            r0 = move-exception
            r1 = r0
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x08f8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x08f8 }
            r4.<init>()     // Catch:{ all -> 0x08f8 }
            java.lang.String r5 = "create factory method error, "
            r4.append(r5)     // Catch:{ all -> 0x08f8 }
            com.alibaba.fastjson.util.JavaBeanInfo r5 = r8.beanInfo     // Catch:{ all -> 0x08f8 }
            java.lang.reflect.Method r5 = r5.factoryMethod     // Catch:{ all -> 0x08f8 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x08f8 }
            r4.append(r5)     // Catch:{ all -> 0x08f8 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x08f8 }
            r2.<init>(r4, r1)     // Catch:{ all -> 0x08f8 }
            throw r2     // Catch:{ all -> 0x08f8 }
        L_0x08f1:
            r1 = r37
        L_0x08f3:
            if (r6 == 0) goto L_0x0906
            r6.object = r1     // Catch:{ all -> 0x092b }
            goto L_0x0906
        L_0x08f8:
            r0 = move-exception
            r1 = r37
            goto L_0x098a
        L_0x08fd:
            r0 = move-exception
            r37 = r1
            goto L_0x098a
        L_0x0902:
            r37 = r1
            r3 = r31
        L_0x0906:
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x092b }
            java.lang.reflect.Method r2 = r2.buildMethod     // Catch:{ all -> 0x092b }
            if (r2 != 0) goto L_0x0914
            if (r6 == 0) goto L_0x0910
            r6.object = r1
        L_0x0910:
            r9.setContext(r3)
            return r1
        L_0x0914:
            r4 = 0
            java.lang.Object r2 = r2.invoke(r1, r4)     // Catch:{ Exception -> 0x0921 }
            if (r6 == 0) goto L_0x091d
            r6.object = r1
        L_0x091d:
            r9.setContext(r3)
            return r2
        L_0x0921:
            r0 = move-exception
            r2 = r0
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x092b }
            java.lang.String r5 = "build object error"
            r4.<init>(r5, r2)     // Catch:{ all -> 0x092b }
            throw r4     // Catch:{ all -> 0x092b }
        L_0x092b:
            r0 = move-exception
            goto L_0x098a
        L_0x092d:
            r1 = r36
            r3 = r31
            r4 = 0
            int r5 = r12.token()     // Catch:{ all -> 0x097e }
            r6 = 18
            if (r5 == r6) goto L_0x0955
            int r5 = r12.token()     // Catch:{ all -> 0x097e }
            r6 = 1
            if (r5 == r6) goto L_0x0955
        L_0x0941:
            int r5 = r10 + 1
            r10 = r34
            r14 = r2
            r7 = r3
            r4 = r5
            r5 = r15
            r3 = r16
            r1 = r18
            r2 = r20
            r20 = r21
            r6 = r30
            goto L_0x017b
        L_0x0955:
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x097e }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x097e }
            r2.<init>()     // Catch:{ all -> 0x097e }
            java.lang.String r4 = "syntax error, unexpect token "
            r2.append(r4)     // Catch:{ all -> 0x097e }
            int r4 = r12.token()     // Catch:{ all -> 0x097e }
            java.lang.String r4 = com.alibaba.fastjson.parser.JSONToken.name(r4)     // Catch:{ all -> 0x097e }
            r2.append(r4)     // Catch:{ all -> 0x097e }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x097e }
            r1.<init>(r2)     // Catch:{ all -> 0x097e }
            throw r1     // Catch:{ all -> 0x097e }
        L_0x0974:
            r3 = r31
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x097e }
            java.lang.String r2 = "syntax error, unexpect token ':'"
            r1.<init>(r2)     // Catch:{ all -> 0x097e }
            throw r1     // Catch:{ all -> 0x097e }
        L_0x097e:
            r0 = move-exception
            goto L_0x0983
        L_0x0980:
            r0 = move-exception
            r3 = r31
        L_0x0983:
            r2 = r0
            r1 = r18
            goto L_0x0640
        L_0x0988:
            r0 = move-exception
            r3 = r7
        L_0x098a:
            r2 = r0
            r15 = r6
            goto L_0x0993
        L_0x098d:
            r0 = move-exception
            r3 = r7
            r4 = r15
            r1 = r36
            r2 = r0
        L_0x0993:
            if (r15 == 0) goto L_0x0997
            r15.object = r1
        L_0x0997:
            r9.setContext(r3)
            throw r2
        L_0x099b:
            java.lang.Object r1 = r33.parse()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object, int, int[]):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public Enum scanEnum(JSONLexerBase jSONLexerBase, char[] cArr, ObjectDeserializer objectDeserializer) {
        EnumDeserializer enumDeserializer = objectDeserializer instanceof EnumDeserializer ? (EnumDeserializer) objectDeserializer : null;
        if (enumDeserializer == null) {
            jSONLexerBase.matchStat = -1;
            return null;
        }
        long scanEnumSymbol = jSONLexerBase.scanEnumSymbol(cArr);
        if (jSONLexerBase.matchStat <= 0) {
            return null;
        }
        Enum enumByHashCode = enumDeserializer.getEnumByHashCode(scanEnumSymbol);
        if (enumByHashCode == null) {
            if (scanEnumSymbol == -3750763034362895579L) {
                return null;
            }
            if (jSONLexerBase.isEnabled(Feature.ErrorOnEnumNotMatch)) {
                throw new JSONException("not match enum value, " + enumDeserializer.enumClass);
            }
        }
        return enumByHashCode;
    }

    public boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        return parseField(defaultJSONParser, str, obj, type, map, (int[]) null);
    }

    /* JADX WARNING: type inference failed for: r19v0, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r19v5 */
    /* JADX WARNING: type inference failed for: r19v6 */
    /* JADX WARNING: type inference failed for: r19v13 */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0215  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x012a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean parseField(com.alibaba.fastjson.parser.DefaultJSONParser r22, java.lang.String r23, java.lang.Object r24, java.lang.reflect.Type r25, java.util.Map<java.lang.String, java.lang.Object> r26, int[] r27) {
        /*
            r21 = this;
            r1 = r21
            r0 = r22
            r11 = r23
            r12 = r24
            r13 = r25
            r14 = r26
            r15 = r27
            com.alibaba.fastjson.parser.JSONLexer r10 = r0.lexer
            com.alibaba.fastjson.parser.Feature r2 = com.alibaba.fastjson.parser.Feature.DisableFieldSmartMatch
            int r2 = r2.mask
            com.alibaba.fastjson.parser.Feature r3 = com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty
            int r3 = r3.mask
            boolean r4 = r10.isEnabled((int) r2)
            if (r4 != 0) goto L_0x003e
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r1.beanInfo
            int r4 = r4.parserFeatures
            r2 = r2 & r4
            if (r2 == 0) goto L_0x0026
            goto L_0x003e
        L_0x0026:
            boolean r2 = r10.isEnabled((int) r3)
            if (r2 != 0) goto L_0x0039
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r1.beanInfo
            int r2 = r2.parserFeatures
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0034
            goto L_0x0039
        L_0x0034:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r2 = r1.smartMatch(r11, r15)
            goto L_0x0042
        L_0x0039:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r2 = r1.smartMatch(r11)
            goto L_0x0042
        L_0x003e:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r2 = r1.getFieldDeserializer((java.lang.String) r11)
        L_0x0042:
            com.alibaba.fastjson.parser.Feature r3 = com.alibaba.fastjson.parser.Feature.SupportNonPublicField
            int r3 = r3.mask
            r16 = 0
            r9 = 1
            if (r2 != 0) goto L_0x0120
            boolean r4 = r10.isEnabled((int) r3)
            if (r4 != 0) goto L_0x0058
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r1.beanInfo
            int r4 = r4.parserFeatures
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0120
        L_0x0058:
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Object> r3 = r1.extraFieldDeserializers
            if (r3 != 0) goto L_0x00c9
            java.util.concurrent.ConcurrentHashMap r3 = new java.util.concurrent.ConcurrentHashMap
            r4 = 1061158912(0x3f400000, float:0.75)
            r3.<init>(r9, r4, r9)
            java.lang.Class<?> r4 = r1.clazz
        L_0x0065:
            if (r4 == 0) goto L_0x00c4
            java.lang.Class<java.lang.Object> r5 = java.lang.Object.class
            if (r4 == r5) goto L_0x00c4
            java.lang.reflect.Field[] r5 = r4.getDeclaredFields()
            int r6 = r5.length
            r7 = r16
        L_0x0072:
            if (r7 >= r6) goto L_0x00bc
            r8 = r5[r7]
            java.lang.String r9 = r8.getName()
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r18 = r1.getFieldDeserializer((java.lang.String) r9)
            if (r18 == 0) goto L_0x0081
            goto L_0x00b0
        L_0x0081:
            int r18 = r8.getModifiers()
            r19 = r18 & 16
            if (r19 != 0) goto L_0x00b0
            r18 = r18 & 8
            if (r18 == 0) goto L_0x008e
            goto L_0x00b0
        L_0x008e:
            r18 = r2
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r2 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r2 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Field) r8, r2)
            com.alibaba.fastjson.annotation.JSONField r2 = (com.alibaba.fastjson.annotation.JSONField) r2
            if (r2 == 0) goto L_0x00aa
            java.lang.String r2 = r2.name()
            r19 = r5
            java.lang.String r5 = ""
            boolean r5 = r5.equals(r2)
            if (r5 != 0) goto L_0x00ac
            r9 = r2
            goto L_0x00ac
        L_0x00aa:
            r19 = r5
        L_0x00ac:
            r3.put(r9, r8)
            goto L_0x00b4
        L_0x00b0:
            r18 = r2
            r19 = r5
        L_0x00b4:
            int r7 = r7 + 1
            r2 = r18
            r5 = r19
            r9 = 1
            goto L_0x0072
        L_0x00bc:
            r18 = r2
            java.lang.Class r4 = r4.getSuperclass()
            r9 = 1
            goto L_0x0065
        L_0x00c4:
            r18 = r2
            r1.extraFieldDeserializers = r3
            goto L_0x00cb
        L_0x00c9:
            r18 = r2
        L_0x00cb:
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Object> r2 = r1.extraFieldDeserializers
            java.lang.Object r2 = r2.get(r11)
            if (r2 == 0) goto L_0x011c
            boolean r3 = r2 instanceof com.alibaba.fastjson.parser.deserializer.FieldDeserializer
            if (r3 == 0) goto L_0x00dd
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r2 = (com.alibaba.fastjson.parser.deserializer.FieldDeserializer) r2
            r15 = r10
            r19 = 1
            goto L_0x0127
        L_0x00dd:
            r7 = r2
            java.lang.reflect.Field r7 = (java.lang.reflect.Field) r7
            r9 = 1
            r7.setAccessible(r9)
            com.alibaba.fastjson.util.FieldInfo r8 = new com.alibaba.fastjson.util.FieldInfo
            java.lang.Class r4 = r7.getDeclaringClass()
            java.lang.Class r5 = r7.getType()
            java.lang.reflect.Type r6 = r7.getGenericType()
            r17 = 0
            r18 = 0
            r19 = 0
            r2 = r8
            r3 = r23
            r20 = r8
            r8 = r19
            r19 = r9
            r9 = r17
            r15 = r10
            r10 = r18
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer r2 = new com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer
            com.alibaba.fastjson.parser.ParserConfig r3 = r22.getConfig()
            java.lang.Class<?> r4 = r1.clazz
            r5 = r20
            r2.<init>(r3, r4, r5)
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Object> r3 = r1.extraFieldDeserializers
            r3.put(r11, r2)
            goto L_0x0127
        L_0x011c:
            r15 = r10
            r19 = 1
            goto L_0x0125
        L_0x0120:
            r18 = r2
            r19 = r9
            r15 = r10
        L_0x0125:
            r2 = r18
        L_0x0127:
            r3 = -1
            if (r2 != 0) goto L_0x0215
            com.alibaba.fastjson.parser.Feature r2 = com.alibaba.fastjson.parser.Feature.IgnoreNotMatch
            boolean r2 = r15.isEnabled((com.alibaba.fastjson.parser.Feature) r2)
            if (r2 == 0) goto L_0x01f3
            r4 = r3
            r2 = r16
        L_0x0135:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r5 = r1.sortedFieldDeserializers
            int r6 = r5.length
            if (r2 >= r6) goto L_0x01dd
            r5 = r5[r2]
            com.alibaba.fastjson.util.FieldInfo r6 = r5.fieldInfo
            boolean r7 = r6.unwrapped
            if (r7 == 0) goto L_0x01d9
            boolean r7 = r5 instanceof com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer
            if (r7 == 0) goto L_0x01d9
            java.lang.reflect.Field r7 = r6.field
            java.lang.String r8 = "parse unwrapped field error."
            if (r7 == 0) goto L_0x01b4
            r7 = r5
            com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer r7 = (com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer) r7
            com.alibaba.fastjson.parser.ParserConfig r9 = r22.getConfig()
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r9 = r7.getFieldValueDeserilizer(r9)
            boolean r10 = r9 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer
            if (r10 == 0) goto L_0x0189
            r10 = r9
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r10 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r10
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r10 = r10.getFieldDeserializer((java.lang.String) r11)
            if (r10 == 0) goto L_0x01d9
            java.lang.reflect.Field r4 = r6.field     // Catch:{ Exception -> 0x0182 }
            java.lang.Object r4 = r4.get(r12)     // Catch:{ Exception -> 0x0182 }
            if (r4 != 0) goto L_0x0177
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r9 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r9     // Catch:{ Exception -> 0x0182 }
            java.lang.reflect.Type r4 = r6.fieldType     // Catch:{ Exception -> 0x0182 }
            java.lang.Object r4 = r9.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r0, (java.lang.reflect.Type) r4)     // Catch:{ Exception -> 0x0182 }
            r5.setValue((java.lang.Object) r12, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0182 }
        L_0x0177:
            int r5 = r7.getFastMatchToken()     // Catch:{ Exception -> 0x0182 }
            r15.nextTokenWithColon(r5)     // Catch:{ Exception -> 0x0182 }
            r10.parseField(r0, r4, r13, r14)     // Catch:{ Exception -> 0x0182 }
            goto L_0x01d0
        L_0x0182:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            r2.<init>(r8, r0)
            throw r2
        L_0x0189:
            boolean r7 = r9 instanceof com.alibaba.fastjson.parser.deserializer.MapDeserializer
            if (r7 == 0) goto L_0x01d9
            com.alibaba.fastjson.parser.deserializer.MapDeserializer r9 = (com.alibaba.fastjson.parser.deserializer.MapDeserializer) r9
            java.lang.reflect.Field r4 = r6.field     // Catch:{ Exception -> 0x01ad }
            java.lang.Object r4 = r4.get(r12)     // Catch:{ Exception -> 0x01ad }
            java.util.Map r4 = (java.util.Map) r4     // Catch:{ Exception -> 0x01ad }
            if (r4 != 0) goto L_0x01a2
            java.lang.reflect.Type r4 = r6.fieldType     // Catch:{ Exception -> 0x01ad }
            java.util.Map r4 = r9.createMap(r4)     // Catch:{ Exception -> 0x01ad }
            r5.setValue((java.lang.Object) r12, (java.lang.Object) r4)     // Catch:{ Exception -> 0x01ad }
        L_0x01a2:
            r15.nextTokenWithColon()     // Catch:{ Exception -> 0x01ad }
            java.lang.Object r5 = r22.parse(r23)     // Catch:{ Exception -> 0x01ad }
            r4.put(r11, r5)     // Catch:{ Exception -> 0x01ad }
            goto L_0x01d0
        L_0x01ad:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            r2.<init>(r8, r0)
            throw r2
        L_0x01b4:
            java.lang.reflect.Method r5 = r6.method
            java.lang.Class[] r5 = r5.getParameterTypes()
            int r5 = r5.length
            r7 = 2
            if (r5 != r7) goto L_0x01d9
            r15.nextTokenWithColon()
            java.lang.Object r4 = r22.parse(r23)
            java.lang.reflect.Method r5 = r6.method     // Catch:{ Exception -> 0x01d2 }
            java.lang.Object[] r6 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x01d2 }
            r6[r16] = r11     // Catch:{ Exception -> 0x01d2 }
            r6[r19] = r4     // Catch:{ Exception -> 0x01d2 }
            r5.invoke(r12, r6)     // Catch:{ Exception -> 0x01d2 }
        L_0x01d0:
            r4 = r2
            goto L_0x01d9
        L_0x01d2:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            r2.<init>(r8, r0)
            throw r2
        L_0x01d9:
            int r2 = r2 + 1
            goto L_0x0135
        L_0x01dd:
            if (r4 == r3) goto L_0x01ef
            r5 = r27
            if (r5 == 0) goto L_0x01ee
            int r0 = r4 / 32
            int r4 = r4 % 32
            r2 = r5[r0]
            int r3 = r19 << r4
            r2 = r2 | r3
            r5[r0] = r2
        L_0x01ee:
            return r19
        L_0x01ef:
            r0.parseExtra(r12, r11)
            return r16
        L_0x01f3:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "setter not found, class "
            r2.<init>(r3)
            java.lang.Class<?> r3 = r1.clazz
            java.lang.String r3 = r3.getName()
            r2.append(r3)
            java.lang.String r3 = ", property "
            r2.append(r3)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0215:
            r5 = r27
            r4 = r15
            r6 = r16
        L_0x021a:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r7 = r1.sortedFieldDeserializers
            int r8 = r7.length
            if (r6 >= r8) goto L_0x0227
            r7 = r7[r6]
            if (r7 != r2) goto L_0x0224
            goto L_0x0228
        L_0x0224:
            int r6 = r6 + 1
            goto L_0x021a
        L_0x0227:
            r6 = r3
        L_0x0228:
            if (r6 == r3) goto L_0x023e
            if (r5 == 0) goto L_0x023e
            java.lang.String r3 = "_"
            boolean r3 = r11.startsWith(r3)
            if (r3 == 0) goto L_0x023e
            boolean r3 = isSetFlag(r6, r5)
            if (r3 == 0) goto L_0x023e
            r0.parseExtra(r12, r11)
            return r16
        L_0x023e:
            int r3 = r2.getFastMatchToken()
            r4.nextTokenWithColon(r3)
            r2.parseField(r0, r12, r13, r14)
            if (r5 == 0) goto L_0x0255
            int r0 = r6 / 32
            int r6 = r6 % 32
            r2 = r5[r0]
            int r3 = r19 << r6
            r2 = r2 | r3
            r5[r0] = r2
        L_0x0255:
            return r19
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.parseField(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.String, java.lang.Object, java.lang.reflect.Type, java.util.Map, int[]):boolean");
    }

    public FieldDeserializer smartMatch(String str) {
        return smartMatch(str, (int[]) null);
    }

    public FieldDeserializer smartMatch(String str, int[] iArr) {
        boolean z;
        if (str == null) {
            return null;
        }
        FieldDeserializer fieldDeserializer = getFieldDeserializer(str, iArr);
        if (fieldDeserializer == null) {
            long fnv1a_64_lower = TypeUtils.fnv1a_64_lower(str);
            int i = 0;
            if (this.smartMatchHashArray == null) {
                long[] jArr = new long[this.sortedFieldDeserializers.length];
                int i2 = 0;
                while (true) {
                    FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                    if (i2 >= fieldDeserializerArr.length) {
                        break;
                    }
                    jArr[i2] = TypeUtils.fnv1a_64_lower(fieldDeserializerArr[i2].fieldInfo.name);
                    i2++;
                }
                Arrays.sort(jArr);
                this.smartMatchHashArray = jArr;
            }
            int binarySearch = Arrays.binarySearch(this.smartMatchHashArray, fnv1a_64_lower);
            if (binarySearch < 0) {
                z = str.startsWith("is");
                if (z) {
                    binarySearch = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_lower(str.substring(2)));
                }
            } else {
                z = false;
            }
            if (binarySearch >= 0) {
                if (this.smartMatchHashArrayMapping == null) {
                    short[] sArr = new short[this.smartMatchHashArray.length];
                    Arrays.fill(sArr, -1);
                    while (true) {
                        FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                        if (i >= fieldDeserializerArr2.length) {
                            break;
                        }
                        int binarySearch2 = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_lower(fieldDeserializerArr2[i].fieldInfo.name));
                        if (binarySearch2 >= 0) {
                            sArr[binarySearch2] = (short) i;
                        }
                        i++;
                    }
                    this.smartMatchHashArrayMapping = sArr;
                }
                short s = this.smartMatchHashArrayMapping[binarySearch];
                if (s != -1 && !isSetFlag(s, iArr)) {
                    fieldDeserializer = this.sortedFieldDeserializers[s];
                }
            }
            if (fieldDeserializer != null) {
                FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
                if ((fieldInfo.parserFeatures & Feature.DisableFieldSmartMatch.mask) != 0) {
                    return null;
                }
                Class<?> cls = fieldInfo.fieldClass;
                if (!(!z || cls == Boolean.TYPE || cls == Boolean.class)) {
                    return null;
                }
            }
        }
        return fieldDeserializer;
    }

    private Object createFactoryInstance(ParserConfig parserConfig, Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        return this.beanInfo.factoryMethod.invoke((Object) null, new Object[]{obj});
    }

    public Object createInstance(Map<String, Object> map, ParserConfig parserConfig) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Object obj;
        Integer num;
        Object obj2;
        double d;
        float f;
        if (this.beanInfo.creatorConstructor == null && this.beanInfo.factoryMethod == null) {
            Object createInstance = createInstance((DefaultJSONParser) null, (Type) this.clazz);
            for (Map.Entry next : map.entrySet()) {
                Object value = next.getValue();
                FieldDeserializer smartMatch = smartMatch((String) next.getKey());
                if (smartMatch != null) {
                    FieldInfo fieldInfo = smartMatch.fieldInfo;
                    Field field = smartMatch.fieldInfo.field;
                    Type type = fieldInfo.fieldType;
                    if (fieldInfo.declaringClass == null || fieldInfo.getAnnotation() == null || fieldInfo.getAnnotation().deserializeUsing() == Void.class || !fieldInfo.fieldClass.isInstance(value)) {
                        if (field != null) {
                            Class<?> type2 = field.getType();
                            if (type2 == Boolean.TYPE) {
                                if (value == Boolean.FALSE) {
                                    field.setBoolean(createInstance, false);
                                } else if (value == Boolean.TRUE) {
                                    field.setBoolean(createInstance, true);
                                }
                            } else if (type2 == Integer.TYPE) {
                                if (value instanceof Number) {
                                    field.setInt(createInstance, ((Number) value).intValue());
                                }
                            } else if (type2 == Long.TYPE) {
                                if (value instanceof Number) {
                                    field.setLong(createInstance, ((Number) value).longValue());
                                }
                            } else if (type2 == Float.TYPE) {
                                if (value instanceof Number) {
                                    field.setFloat(createInstance, ((Number) value).floatValue());
                                } else if (value instanceof String) {
                                    String str = (String) value;
                                    if (str.length() <= 10) {
                                        f = TypeUtils.parseFloat(str);
                                    } else {
                                        f = Float.parseFloat(str);
                                    }
                                    field.setFloat(createInstance, f);
                                }
                            } else if (type2 == Double.TYPE) {
                                if (value instanceof Number) {
                                    field.setDouble(createInstance, ((Number) value).doubleValue());
                                } else if (value instanceof String) {
                                    String str2 = (String) value;
                                    if (str2.length() <= 10) {
                                        d = TypeUtils.parseDouble(str2);
                                    } else {
                                        d = Double.parseDouble(str2);
                                    }
                                    field.setDouble(createInstance, d);
                                }
                            } else if (value != null && type == value.getClass()) {
                                field.set(createInstance, value);
                            }
                        }
                        String str3 = fieldInfo.format;
                        if (str3 != null && type == Date.class) {
                            obj2 = TypeUtils.castToDate(value, str3);
                        } else if (str3 != null && (type instanceof Class) && ((Class) type).getName().equals("java.time.LocalDateTime")) {
                            obj2 = TypeUtils.castToLocalDateTime(value, str3);
                        } else if (type instanceof ParameterizedType) {
                            obj2 = TypeUtils.cast(value, (ParameterizedType) type, parserConfig);
                        } else {
                            obj2 = TypeUtils.cast(value, type, parserConfig);
                        }
                        smartMatch.setValue(createInstance, obj2);
                    } else {
                        smartMatch.parseField(new DefaultJSONParser(JSON.toJSONString(value)), createInstance, type, (Map<String, Object>) null);
                    }
                }
            }
            if (this.beanInfo.buildMethod == null) {
                return createInstance;
            }
            try {
                return this.beanInfo.buildMethod.invoke(createInstance, (Object[]) null);
            } catch (Exception e) {
                throw new JSONException("build object error", e);
            }
        } else {
            FieldInfo[] fieldInfoArr = this.beanInfo.fields;
            int length = fieldInfoArr.length;
            Object[] objArr = new Object[length];
            HashMap hashMap = null;
            for (int i = 0; i < length; i++) {
                FieldInfo fieldInfo2 = fieldInfoArr[i];
                boolean z = map.get(fieldInfo2.name);
                if (z == null) {
                    Class<?> cls = fieldInfo2.fieldClass;
                    if (cls == Integer.TYPE) {
                        z = 0;
                    } else if (cls == Long.TYPE) {
                        z = 0L;
                    } else if (cls == Short.TYPE) {
                        z = (short) 0;
                    } else if (cls == Byte.TYPE) {
                        z = (byte) 0;
                    } else if (cls == Float.TYPE) {
                        z = Float.valueOf(0.0f);
                    } else if (cls == Double.TYPE) {
                        z = Double.valueOf(0.0d);
                    } else if (cls == Character.TYPE) {
                        z = '0';
                    } else if (cls == Boolean.TYPE) {
                        z = false;
                    }
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    hashMap.put(fieldInfo2.name, Integer.valueOf(i));
                }
                objArr[i] = z;
            }
            if (hashMap != null) {
                for (Map.Entry next2 : map.entrySet()) {
                    Object value2 = next2.getValue();
                    FieldDeserializer smartMatch2 = smartMatch((String) next2.getKey());
                    if (!(smartMatch2 == null || (num = (Integer) hashMap.get(smartMatch2.fieldInfo.name)) == null)) {
                        objArr[num.intValue()] = value2;
                    }
                }
            }
            if (this.beanInfo.creatorConstructor != null) {
                if (this.beanInfo.f40kotlin) {
                    int i2 = 0;
                    while (true) {
                        if (i2 < length) {
                            if (objArr[i2] != null || this.beanInfo.fields == null || i2 >= this.beanInfo.fields.length) {
                                i2++;
                            } else if (this.beanInfo.fields[i2].fieldClass == String.class && this.beanInfo.kotlinDefaultConstructor != null) {
                                try {
                                    obj = this.beanInfo.kotlinDefaultConstructor.newInstance((Object[]) null);
                                    for (int i3 = 0; i3 < length; i3++) {
                                        Object obj3 = objArr[i3];
                                        if (!(obj3 == null || this.beanInfo.fields == null || i3 >= this.beanInfo.fields.length)) {
                                            this.beanInfo.fields[i3].set(obj, obj3);
                                        }
                                    }
                                } catch (Exception e2) {
                                    throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e2);
                                }
                            }
                        }
                    }
                    return obj;
                }
                try {
                    obj = this.beanInfo.creatorConstructor.newInstance(objArr);
                    return obj;
                } catch (Exception e3) {
                    throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e3);
                }
            } else if (this.beanInfo.factoryMethod == null) {
                return null;
            } else {
                try {
                    return this.beanInfo.factoryMethod.invoke((Object) null, objArr);
                } catch (Exception e4) {
                    throw new JSONException("create factory method error, " + this.beanInfo.factoryMethod.toString(), e4);
                }
            }
        }
    }

    public Type getFieldType(int i) {
        return this.sortedFieldDeserializers[i].fieldInfo.fieldType;
    }

    /* access modifiers changed from: protected */
    public Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i) {
        return parseRest(defaultJSONParser, type, obj, obj2, i, new int[0]);
    }

    /* access modifiers changed from: protected */
    public Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i, int[] iArr) {
        return deserialze(defaultJSONParser, type, obj, obj2, i, iArr);
    }

    protected static JavaBeanDeserializer getSeeAlso(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, String str) {
        if (javaBeanInfo.jsonType == null) {
            return null;
        }
        for (Class deserializer : javaBeanInfo.jsonType.seeAlso()) {
            ObjectDeserializer deserializer2 = parserConfig.getDeserializer((Type) deserializer);
            if (deserializer2 instanceof JavaBeanDeserializer) {
                JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) deserializer2;
                JavaBeanInfo javaBeanInfo2 = javaBeanDeserializer.beanInfo;
                if (javaBeanInfo2.typeName.equals(str)) {
                    return javaBeanDeserializer;
                }
                JavaBeanDeserializer seeAlso = getSeeAlso(parserConfig, javaBeanInfo2, str);
                if (seeAlso != null) {
                    return seeAlso;
                }
            }
        }
        return null;
    }

    protected static void parseArray(Collection collection, ObjectDeserializer objectDeserializer, DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.lexer;
        int i = jSONLexerBase.token();
        if (i == 8) {
            jSONLexerBase.nextToken(16);
            jSONLexerBase.token();
            return;
        }
        if (i != 14) {
            defaultJSONParser.throwException(i);
        }
        if (jSONLexerBase.getCurrent() == '[') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(14);
        } else {
            jSONLexerBase.nextToken(14);
        }
        if (jSONLexerBase.token() == 15) {
            jSONLexerBase.nextToken();
            return;
        }
        int i2 = 0;
        while (true) {
            collection.add(objectDeserializer.deserialze(defaultJSONParser, type, Integer.valueOf(i2)));
            i2++;
            if (jSONLexerBase.token() != 16) {
                break;
            } else if (jSONLexerBase.getCurrent() == '[') {
                jSONLexerBase.next();
                jSONLexerBase.setToken(14);
            } else {
                jSONLexerBase.nextToken(14);
            }
        }
        int i3 = jSONLexerBase.token();
        if (i3 != 15) {
            defaultJSONParser.throwException(i3);
        }
        if (jSONLexerBase.getCurrent() == ',') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(16);
            return;
        }
        jSONLexerBase.nextToken(16);
    }
}
