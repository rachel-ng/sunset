package com.alibaba.fastjson.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.EnumDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.CalendarCodec;
import com.alibaba.fastjson.serializer.SerializeBeanInfo;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.Deque;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.math3.distribution.PoissonDistribution;

public class TypeUtils {
    private static final Pattern NUMBER_WITH_TRAILING_ZEROS_PATTERN = Pattern.compile("\\.0*$");
    private static volatile boolean classXmlAccessorType_error = false;
    private static volatile Class class_Clob = null;
    private static volatile boolean class_Clob_error = false;
    private static Class<? extends Annotation> class_JacksonCreator = null;
    private static boolean class_JacksonCreator_error = false;
    private static Class<? extends Annotation> class_ManyToMany = null;
    private static boolean class_ManyToMany_error = false;
    private static Class<? extends Annotation> class_OneToMany = null;
    private static boolean class_OneToMany_error = false;
    private static volatile Class class_XmlAccessType = null;
    private static volatile Class class_XmlAccessorType = null;
    public static boolean compatibleWithFieldName;
    public static boolean compatibleWithJavaBean;
    private static volatile Field field_XmlAccessType_FIELD = null;
    private static volatile Object field_XmlAccessType_FIELD_VALUE = null;
    private static volatile Map<Class, String[]> kotlinIgnores;
    private static volatile boolean kotlinIgnores_error;
    private static volatile boolean kotlin_class_klass_error;
    private static volatile boolean kotlin_error;
    private static volatile Constructor kotlin_kclass_constructor;
    private static volatile Method kotlin_kclass_getConstructors;
    private static volatile Method kotlin_kfunction_getParameters;
    private static volatile Method kotlin_kparameter_getName;
    private static volatile Class kotlin_metadata;
    private static volatile boolean kotlin_metadata_error;
    private static ConcurrentMap<String, Class<?>> mappings = new ConcurrentHashMap(256, 0.75f, 1);
    private static Method method_HibernateIsInitialized = null;
    private static boolean method_HibernateIsInitialized_error = false;
    private static volatile Method method_XmlAccessorType_value = null;
    private static Class<?> optionalClass;
    private static boolean optionalClassInited = false;
    private static Method oracleDateMethod;
    private static boolean oracleDateMethodInited = false;
    private static Method oracleTimestampMethod;
    private static boolean oracleTimestampMethodInited = false;
    private static Class<?> pathClass;
    private static boolean pathClass_error = false;
    private static boolean setAccessibleEnable = true;
    private static Class<? extends Annotation> transientClass;
    private static boolean transientClassInited = false;

    static int num(char c2, char c3) {
        if (c2 < '0' || c2 > '9' || c3 < '0' || c3 > '9') {
            return -1;
        }
        return ((c2 - '0') * 10) + (c3 - '0');
    }

    static int num(char c2, char c3, char c4, char c5) {
        if (c2 < '0' || c2 > '9' || c3 < '0' || c3 > '9' || c4 < '0' || c4 > '9' || c5 < '0' || c5 > '9') {
            return -1;
        }
        return ((c2 - '0') * 1000) + ((c3 - '0') * 100) + ((c4 - '0') * 10) + (c5 - '0');
    }

    static int num(char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9, char c10) {
        if (c2 < '0' || c2 > '9' || c3 < '0' || c3 > '9' || c4 < '0' || c4 > '9' || c5 < '0' || c5 > '9' || c6 < '0' || c6 > '9' || c7 < '0' || c7 > '9' || c8 < '0' || c8 > '9' || c9 < '0' || c9 > '9' || c10 < '0' || c10 > '9') {
            return -1;
        }
        return ((c2 - '0') * 100000000) + ((c3 - '0') * PoissonDistribution.DEFAULT_MAX_ITERATIONS) + ((c4 - '0') * 1000000) + ((c5 - '0') * AacUtil.AAC_LC_MAX_RATE_BYTES_PER_SECOND) + ((c6 - '0') * 10000) + ((c7 - '0') * 1000) + ((c8 - '0') * 100) + ((c9 - '0') * 10) + (c10 - '0');
    }

    static {
        compatibleWithJavaBean = false;
        compatibleWithFieldName = false;
        try {
            compatibleWithJavaBean = "true".equals(IOUtils.getStringProperty(IOUtils.FASTJSON_COMPATIBLEWITHJAVABEAN));
            compatibleWithFieldName = "true".equals(IOUtils.getStringProperty(IOUtils.FASTJSON_COMPATIBLEWITHFIELDNAME));
        } catch (Throwable unused) {
        }
        addBaseClassMappings();
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x004e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isXmlField(java.lang.Class r5) {
        /*
            java.lang.Class r0 = class_XmlAccessorType
            r1 = 1
            if (r0 != 0) goto L_0x0014
            boolean r0 = classXmlAccessorType_error
            if (r0 != 0) goto L_0x0014
            java.lang.String r0 = "javax.xml.bind.annotation.XmlAccessorType"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ all -> 0x0012 }
            class_XmlAccessorType = r0     // Catch:{ all -> 0x0012 }
            goto L_0x0014
        L_0x0012:
            classXmlAccessorType_error = r1
        L_0x0014:
            java.lang.Class r0 = class_XmlAccessorType
            r2 = 0
            if (r0 != 0) goto L_0x001a
            return r2
        L_0x001a:
            java.lang.Class r0 = class_XmlAccessorType
            java.lang.annotation.Annotation r5 = getAnnotation((java.lang.Class<?>) r5, r0)
            if (r5 != 0) goto L_0x0023
            return r2
        L_0x0023:
            java.lang.reflect.Method r0 = method_XmlAccessorType_value
            r3 = 0
            if (r0 != 0) goto L_0x0039
            boolean r0 = classXmlAccessorType_error
            if (r0 != 0) goto L_0x0039
            java.lang.Class r0 = class_XmlAccessorType     // Catch:{ all -> 0x0037 }
            java.lang.String r4 = "value"
            java.lang.reflect.Method r0 = r0.getMethod(r4, r3)     // Catch:{ all -> 0x0037 }
            method_XmlAccessorType_value = r0     // Catch:{ all -> 0x0037 }
            goto L_0x0039
        L_0x0037:
            classXmlAccessorType_error = r1
        L_0x0039:
            java.lang.reflect.Method r0 = method_XmlAccessorType_value
            if (r0 != 0) goto L_0x003e
            return r2
        L_0x003e:
            boolean r0 = classXmlAccessorType_error
            if (r0 != 0) goto L_0x004b
            java.lang.reflect.Method r0 = method_XmlAccessorType_value     // Catch:{ all -> 0x0049 }
            java.lang.Object r5 = r0.invoke(r5, r3)     // Catch:{ all -> 0x0049 }
            goto L_0x004c
        L_0x0049:
            classXmlAccessorType_error = r1
        L_0x004b:
            r5 = r3
        L_0x004c:
            if (r5 != 0) goto L_0x004f
            return r2
        L_0x004f:
            java.lang.Class r0 = class_XmlAccessType
            if (r0 != 0) goto L_0x0074
            boolean r0 = classXmlAccessorType_error
            if (r0 != 0) goto L_0x0074
            java.lang.String r0 = "javax.xml.bind.annotation.XmlAccessType"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ all -> 0x0072 }
            class_XmlAccessType = r0     // Catch:{ all -> 0x0072 }
            java.lang.Class r0 = class_XmlAccessType     // Catch:{ all -> 0x0072 }
            java.lang.String r4 = "FIELD"
            java.lang.reflect.Field r0 = r0.getField(r4)     // Catch:{ all -> 0x0072 }
            field_XmlAccessType_FIELD = r0     // Catch:{ all -> 0x0072 }
            java.lang.reflect.Field r0 = field_XmlAccessType_FIELD     // Catch:{ all -> 0x0072 }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x0072 }
            field_XmlAccessType_FIELD_VALUE = r0     // Catch:{ all -> 0x0072 }
            goto L_0x0074
        L_0x0072:
            classXmlAccessorType_error = r1
        L_0x0074:
            java.lang.Object r0 = field_XmlAccessType_FIELD_VALUE
            if (r5 != r0) goto L_0x0079
            goto L_0x007a
        L_0x0079:
            r1 = r2
        L_0x007a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.TypeUtils.isXmlField(java.lang.Class):boolean");
    }

    public static Annotation getXmlAccessorType(Class cls) {
        if (class_XmlAccessorType == null && !classXmlAccessorType_error) {
            try {
                class_XmlAccessorType = Class.forName("javax.xml.bind.annotation.XmlAccessorType");
            } catch (Throwable unused) {
                classXmlAccessorType_error = true;
            }
        }
        if (class_XmlAccessorType == null) {
            return null;
        }
        return getAnnotation((Class<?>) cls, class_XmlAccessorType);
    }

    public static boolean isClob(Class cls) {
        if (class_Clob == null && !class_Clob_error) {
            try {
                class_Clob = Class.forName("java.sql.Clob");
            } catch (Throwable unused) {
                class_Clob_error = true;
            }
        }
        if (class_Clob == null) {
            return false;
        }
        return class_Clob.isAssignableFrom(cls);
    }

    public static String castToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static Byte castToByte(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return Byte.valueOf(byteValue((BigDecimal) obj));
        }
        if (obj instanceof Number) {
            return Byte.valueOf(((Number) obj).byteValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            return Byte.valueOf(Byte.parseByte(str));
        }
        throw new JSONException("can not cast to byte, value : " + obj);
    }

    public static Character castToChar(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Character) {
            return (Character) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            if (str.length() == 1) {
                return Character.valueOf(str.charAt(0));
            }
            throw new JSONException("can not cast to char, value : " + obj);
        }
        throw new JSONException("can not cast to char, value : " + obj);
    }

    public static Short castToShort(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return Short.valueOf(shortValue((BigDecimal) obj));
        }
        if (obj instanceof Number) {
            return Short.valueOf(((Number) obj).shortValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            return Short.valueOf(Short.parseShort(str));
        }
        throw new JSONException("can not cast to short, value : " + obj);
    }

    public static BigDecimal castToBigDecimal(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        if (obj instanceof BigInteger) {
            return new BigDecimal((BigInteger) obj);
        }
        String obj2 = obj.toString();
        if (obj2.length() == 0) {
            return null;
        }
        if (!(obj instanceof Map) || ((Map) obj).size() != 0) {
            return new BigDecimal(obj2);
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0018, code lost:
        r1 = (java.math.BigDecimal) r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.math.BigInteger castToBigInteger(java.lang.Object r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r4 instanceof java.math.BigInteger
            if (r1 == 0) goto L_0x000b
            java.math.BigInteger r4 = (java.math.BigInteger) r4
            return r4
        L_0x000b:
            boolean r1 = r4 instanceof java.lang.Float
            if (r1 != 0) goto L_0x004d
            boolean r1 = r4 instanceof java.lang.Double
            if (r1 == 0) goto L_0x0014
            goto L_0x004d
        L_0x0014:
            boolean r1 = r4 instanceof java.math.BigDecimal
            if (r1 == 0) goto L_0x002c
            r1 = r4
            java.math.BigDecimal r1 = (java.math.BigDecimal) r1
            int r2 = r1.scale()
            r3 = -1000(0xfffffffffffffc18, float:NaN)
            if (r2 <= r3) goto L_0x002c
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r2 >= r3) goto L_0x002c
            java.math.BigInteger r4 = r1.toBigInteger()
            return r4
        L_0x002c:
            java.lang.String r4 = r4.toString()
            int r1 = r4.length()
            if (r1 == 0) goto L_0x004c
            java.lang.String r1 = "null"
            boolean r1 = r1.equals(r4)
            if (r1 != 0) goto L_0x004c
            java.lang.String r1 = "NULL"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x0047
            goto L_0x004c
        L_0x0047:
            java.math.BigInteger r0 = new java.math.BigInteger
            r0.<init>(r4)
        L_0x004c:
            return r0
        L_0x004d:
            java.lang.Number r4 = (java.lang.Number) r4
            long r0 = r4.longValue()
            java.math.BigInteger r4 = java.math.BigInteger.valueOf(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.TypeUtils.castToBigInteger(java.lang.Object):java.math.BigInteger");
    }

    public static Float castToFloat(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Float.valueOf(((Number) obj).floatValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() == 0 || "null".equals(obj2) || "NULL".equals(obj2)) {
                return null;
            }
            if (obj2.indexOf(44) != -1) {
                obj2 = obj2.replaceAll(",", "");
            }
            return Float.valueOf(Float.parseFloat(obj2));
        }
        throw new JSONException("can not cast to float, value : " + obj);
    }

    public static Double castToDouble(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() == 0 || "null".equals(obj2) || "NULL".equals(obj2)) {
                return null;
            }
            if (obj2.indexOf(44) != -1) {
                obj2 = obj2.replaceAll(",", "");
            }
            return Double.valueOf(Double.parseDouble(obj2));
        }
        throw new JSONException("can not cast to double, value : " + obj);
    }

    public static Date castToDate(Object obj) {
        return castToDate(obj, (String) null);
    }

    public static Date castToDate(Object obj, String str) {
        long j;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Date) {
            return (Date) obj;
        }
        if (obj instanceof Calendar) {
            return ((Calendar) obj).getTime();
        }
        if (obj instanceof BigDecimal) {
            return new Date(longValue((BigDecimal) obj));
        }
        if (obj instanceof Number) {
            long longValue = ((Number) obj).longValue();
            if ("unixtime".equals(str)) {
                longValue *= 1000;
            }
            return new Date(longValue);
        }
        if (obj instanceof String) {
            String str2 = (String) obj;
            JSONScanner jSONScanner = new JSONScanner(str2);
            try {
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    return jSONScanner.getCalendar().getTime();
                }
                jSONScanner.close();
                if (str2.startsWith("/Date(") && str2.endsWith(")/")) {
                    str2 = str2.substring(6, str2.length() - 2);
                }
                if (str2.indexOf(45) > 0 || str2.indexOf(43) > 0) {
                    if (str == null) {
                        if (str2.length() == JSON.DEFFAULT_DATE_FORMAT.length() || (str2.length() == 22 && JSON.DEFFAULT_DATE_FORMAT.equals("yyyyMMddHHmmssSSSZ"))) {
                            str = JSON.DEFFAULT_DATE_FORMAT;
                        } else if (str2.length() == 10) {
                            str = "yyyy-MM-dd";
                        } else if (str2.length() == 19) {
                            str = "yyyy-MM-dd HH:mm:ss";
                        } else if (str2.length() == 29 && str2.charAt(26) == ':' && str2.charAt(28) == '0') {
                            str = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
                        } else if (str2.length() == 23 && str2.charAt(19) == ',') {
                            str = "yyyy-MM-dd HH:mm:ss,SSS";
                        } else {
                            str = "yyyy-MM-dd HH:mm:ss.SSS";
                        }
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, JSON.defaultLocale);
                    simpleDateFormat.setTimeZone(JSON.defaultTimeZone);
                    try {
                        return simpleDateFormat.parse(str2);
                    } catch (ParseException unused) {
                        throw new JSONException("can not cast to Date, value : " + str2);
                    }
                } else if (str2.length() == 0) {
                    return null;
                } else {
                    j = Long.parseLong(str2);
                }
            } finally {
                jSONScanner.close();
            }
        } else {
            j = -1;
        }
        if (j != -1) {
            return new Date(j);
        }
        Class<?> cls = obj.getClass();
        if ("oracle.sql.TIMESTAMP".equals(cls.getName())) {
            if (oracleTimestampMethod == null && !oracleTimestampMethodInited) {
                try {
                    oracleTimestampMethod = cls.getMethod("toJdbc", (Class[]) null);
                } catch (NoSuchMethodException unused2) {
                } catch (Throwable th) {
                    oracleTimestampMethodInited = true;
                    throw th;
                }
                oracleTimestampMethodInited = true;
            }
            try {
                return (Date) oracleTimestampMethod.invoke(obj, (Object[]) null);
            } catch (Exception e) {
                throw new JSONException("can not cast oracle.sql.TIMESTAMP to Date", e);
            }
        } else if ("oracle.sql.DATE".equals(cls.getName())) {
            if (oracleDateMethod == null && !oracleDateMethodInited) {
                try {
                    oracleDateMethod = cls.getMethod("toJdbc", (Class[]) null);
                } catch (NoSuchMethodException unused3) {
                } catch (Throwable th2) {
                    oracleDateMethodInited = true;
                    throw th2;
                }
                oracleDateMethodInited = true;
            }
            try {
                return (Date) oracleDateMethod.invoke(obj, (Object[]) null);
            } catch (Exception e2) {
                throw new JSONException("can not cast oracle.sql.DATE to Date", e2);
            }
        } else {
            throw new JSONException("can not cast to Date, value : " + obj);
        }
    }

    public static java.sql.Date castToSqlDate(Object obj) {
        long j;
        if (obj == null) {
            return null;
        }
        if (obj instanceof java.sql.Date) {
            return (java.sql.Date) obj;
        }
        if (obj instanceof Date) {
            return new java.sql.Date(((Date) obj).getTime());
        }
        if (obj instanceof Calendar) {
            return new java.sql.Date(((Calendar) obj).getTimeInMillis());
        }
        if (obj instanceof BigDecimal) {
            j = longValue((BigDecimal) obj);
        } else {
            j = obj instanceof Number ? ((Number) obj).longValue() : 0;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (isNumber(str)) {
                j = Long.parseLong(str);
            } else {
                JSONScanner jSONScanner = new JSONScanner(str);
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    j = jSONScanner.getCalendar().getTime().getTime();
                } else {
                    throw new JSONException("can not cast to Timestamp, value : " + str);
                }
            }
        }
        if (j > 0) {
            return new java.sql.Date(j);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static long longExtractValue(Number number) {
        if (number instanceof BigDecimal) {
            return ((BigDecimal) number).longValueExact();
        }
        return number.longValue();
    }

    public static Time castToSqlTime(Object obj) {
        long j;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Time) {
            return (Time) obj;
        }
        if (obj instanceof Date) {
            return new Time(((Date) obj).getTime());
        }
        if (obj instanceof Calendar) {
            return new Time(((Calendar) obj).getTimeInMillis());
        }
        if (obj instanceof BigDecimal) {
            j = longValue((BigDecimal) obj);
        } else {
            j = obj instanceof Number ? ((Number) obj).longValue() : 0;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equalsIgnoreCase(str)) {
                return null;
            }
            if (isNumber(str)) {
                j = Long.parseLong(str);
            } else {
                JSONScanner jSONScanner = new JSONScanner(str);
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    j = jSONScanner.getCalendar().getTime().getTime();
                } else {
                    throw new JSONException("can not cast to Timestamp, value : " + str);
                }
            }
        }
        if (j > 0) {
            return new Time(j);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static Timestamp castToTimestamp(Object obj) {
        long j;
        Object obj2 = obj;
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Calendar) {
            return new Timestamp(((Calendar) obj2).getTimeInMillis());
        }
        if (obj2 instanceof Timestamp) {
            return (Timestamp) obj2;
        }
        if (obj2 instanceof Date) {
            return new Timestamp(((Date) obj2).getTime());
        }
        if (obj2 instanceof BigDecimal) {
            j = longValue((BigDecimal) obj2);
        } else {
            j = obj2 instanceof Number ? ((Number) obj2).longValue() : 0;
        }
        if (obj2 instanceof String) {
            String str = (String) obj2;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (str.endsWith(".000000000")) {
                str = str.substring(0, str.length() - 10);
            } else if (str.endsWith(".000000")) {
                str = str.substring(0, str.length() - 7);
            }
            if (str.length() == 29 && str.charAt(4) == '-' && str.charAt(7) == '-' && str.charAt(10) == ' ' && str.charAt(13) == ':' && str.charAt(16) == ':' && str.charAt(19) == '.') {
                return new Timestamp(num(str.charAt(0), str.charAt(1), str.charAt(2), str.charAt(3)) - 1900, num(str.charAt(5), str.charAt(6)) - 1, num(str.charAt(8), str.charAt(9)), num(str.charAt(11), str.charAt(12)), num(str.charAt(14), str.charAt(15)), num(str.charAt(17), str.charAt(18)), num(str.charAt(20), str.charAt(21), str.charAt(22), str.charAt(23), str.charAt(24), str.charAt(25), str.charAt(26), str.charAt(27), str.charAt(28)));
            } else if (isNumber(str)) {
                j = Long.parseLong(str);
            } else {
                JSONScanner jSONScanner = new JSONScanner(str);
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    j = jSONScanner.getCalendar().getTime().getTime();
                } else {
                    throw new JSONException("can not cast to Timestamp, value : " + str);
                }
            }
        }
        if (j >= 0) {
            return new Timestamp(j);
        }
        throw new JSONException("can not cast to Timestamp, value : " + obj2);
    }

    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '+' || charAt == '-') {
                if (i != 0) {
                    return false;
                }
            } else if (charAt < '0' || charAt > '9') {
                return false;
            }
        }
        return true;
    }

    public static Long castToLong(Object obj) {
        Calendar calendar = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return Long.valueOf(longValue((BigDecimal) obj));
        }
        if (obj instanceof Number) {
            return Long.valueOf(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (str.indexOf(44) != -1) {
                str = str.replaceAll(",", "");
            }
            try {
                return Long.valueOf(Long.parseLong(str));
            } catch (NumberFormatException unused) {
                JSONScanner jSONScanner = new JSONScanner(str);
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    calendar = jSONScanner.getCalendar();
                }
                jSONScanner.close();
                if (calendar != null) {
                    return Long.valueOf(calendar.getTimeInMillis());
                }
            }
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.size() == 2 && map.containsKey("andIncrement") && map.containsKey("andDecrement")) {
                Iterator it = map.values().iterator();
                it.next();
                return castToLong(it.next());
            }
        }
        throw new JSONException("can not cast to long, value : " + obj);
    }

    public static byte byteValue(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return 0;
        }
        int scale = bigDecimal.scale();
        if (scale < -100 || scale > 100) {
            return bigDecimal.byteValueExact();
        }
        return bigDecimal.byteValue();
    }

    public static short shortValue(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return 0;
        }
        int scale = bigDecimal.scale();
        if (scale < -100 || scale > 100) {
            return bigDecimal.shortValueExact();
        }
        return bigDecimal.shortValue();
    }

    public static int intValue(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return 0;
        }
        int scale = bigDecimal.scale();
        if (scale < -100 || scale > 100) {
            return bigDecimal.intValueExact();
        }
        return bigDecimal.intValue();
    }

    public static long longValue(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return 0;
        }
        int scale = bigDecimal.scale();
        if (scale < -100 || scale > 100) {
            return bigDecimal.longValueExact();
        }
        return bigDecimal.longValue();
    }

    public static Integer castToInt(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof BigDecimal) {
            return Integer.valueOf(intValue((BigDecimal) obj));
        }
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (str.indexOf(44) != -1) {
                str = str.replaceAll(",", "");
            }
            Matcher matcher = NUMBER_WITH_TRAILING_ZEROS_PATTERN.matcher(str);
            if (matcher.find()) {
                str = matcher.replaceAll("");
            }
            return Integer.valueOf(Integer.parseInt(str));
        } else if (obj instanceof Boolean) {
            return Integer.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else {
            if (obj instanceof Map) {
                Map map = (Map) obj;
                if (map.size() == 2 && map.containsKey("andIncrement") && map.containsKey("andDecrement")) {
                    Iterator it = map.values().iterator();
                    it.next();
                    return castToInt(it.next());
                }
            }
            throw new JSONException("can not cast to int, value : " + obj);
        }
    }

    public static byte[] castToBytes(Object obj) {
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        if (obj instanceof String) {
            return IOUtils.decodeBase64((String) obj);
        }
        throw new JSONException("can not cast to byte[], value : " + obj);
    }

    public static Boolean castToBoolean(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        boolean z = false;
        if (obj instanceof BigDecimal) {
            if (intValue((BigDecimal) obj) == 1) {
                z = true;
            }
            return Boolean.valueOf(z);
        } else if (obj instanceof Number) {
            if (((Number) obj).intValue() == 1) {
                z = true;
            }
            return Boolean.valueOf(z);
        } else {
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                    return null;
                }
                if ("true".equalsIgnoreCase(str) || IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(str)) {
                    return Boolean.TRUE;
                }
                if ("false".equalsIgnoreCase(str) || SessionDescription.SUPPORTED_SDP_VERSION.equals(str)) {
                    return Boolean.FALSE;
                }
                if ("Y".equalsIgnoreCase(str) || "T".equals(str)) {
                    return Boolean.TRUE;
                }
                if ("F".equalsIgnoreCase(str) || "N".equals(str)) {
                    return Boolean.FALSE;
                }
            }
            throw new JSONException("can not cast to boolean, value : " + obj);
        }
    }

    public static <T> T castToJavaBean(Object obj, Class<T> cls) {
        return cast(obj, cls, ParserConfig.getGlobalInstance());
    }

    public static <T> T cast(Object obj, Class<T> cls, ParserConfig parserConfig) {
        T t;
        int i = 0;
        if (obj == null) {
            if (cls == Integer.TYPE) {
                return 0;
            }
            if (cls == Long.TYPE) {
                return 0L;
            }
            if (cls == Short.TYPE) {
                return (short) 0;
            }
            if (cls == Byte.TYPE) {
                return (byte) 0;
            }
            if (cls == Float.TYPE) {
                return Float.valueOf(0.0f);
            }
            if (cls == Double.TYPE) {
                return Double.valueOf(0.0d);
            }
            if (cls == Boolean.TYPE) {
                return Boolean.FALSE;
            }
            return null;
        } else if (cls == null) {
            throw new IllegalArgumentException("clazz is null");
        } else if (cls == obj.getClass()) {
            return obj;
        } else {
            if (!(obj instanceof Map)) {
                if (cls.isArray()) {
                    if (obj instanceof Collection) {
                        Collection<Object> collection = (Collection) obj;
                        T newInstance = Array.newInstance(cls.getComponentType(), collection.size());
                        for (Object cast : collection) {
                            Array.set(newInstance, i, cast(cast, cls.getComponentType(), parserConfig));
                            i++;
                        }
                        return newInstance;
                    } else if (cls == byte[].class) {
                        return castToBytes(obj);
                    }
                }
                if (cls.isAssignableFrom(obj.getClass())) {
                    return obj;
                }
                if (cls == Boolean.TYPE || cls == Boolean.class) {
                    return castToBoolean(obj);
                }
                if (cls == Byte.TYPE || cls == Byte.class) {
                    return castToByte(obj);
                }
                if (cls == Character.TYPE || cls == Character.class) {
                    return castToChar(obj);
                }
                if (cls == Short.TYPE || cls == Short.class) {
                    return castToShort(obj);
                }
                if (cls == Integer.TYPE || cls == Integer.class) {
                    return castToInt(obj);
                }
                if (cls == Long.TYPE || cls == Long.class) {
                    return castToLong(obj);
                }
                if (cls == Float.TYPE || cls == Float.class) {
                    return castToFloat(obj);
                }
                if (cls == Double.TYPE || cls == Double.class) {
                    return castToDouble(obj);
                }
                if (cls == String.class) {
                    return castToString(obj);
                }
                if (cls == BigDecimal.class) {
                    return castToBigDecimal(obj);
                }
                if (cls == BigInteger.class) {
                    return castToBigInteger(obj);
                }
                if (cls == Date.class) {
                    return castToDate(obj);
                }
                if (cls == java.sql.Date.class) {
                    return castToSqlDate(obj);
                }
                if (cls == Time.class) {
                    return castToSqlTime(obj);
                }
                if (cls == Timestamp.class) {
                    return castToTimestamp(obj);
                }
                if (cls.isEnum()) {
                    return castToEnum(obj, cls, parserConfig);
                }
                if (Calendar.class.isAssignableFrom(cls)) {
                    Date castToDate = castToDate(obj);
                    if (cls == Calendar.class) {
                        t = Calendar.getInstance(JSON.defaultTimeZone, JSON.defaultLocale);
                    } else {
                        try {
                            t = (Calendar) cls.newInstance();
                        } catch (Exception e) {
                            throw new JSONException("can not cast to : " + cls.getName(), e);
                        }
                    }
                    t.setTime(castToDate);
                    return t;
                }
                String name = cls.getName();
                if (name.equals("javax.xml.datatype.XMLGregorianCalendar")) {
                    Date castToDate2 = castToDate(obj);
                    Calendar instance = Calendar.getInstance(JSON.defaultTimeZone, JSON.defaultLocale);
                    instance.setTime(castToDate2);
                    return CalendarCodec.instance.createXMLGregorianCalendar(instance);
                }
                if (obj instanceof String) {
                    String str = (String) obj;
                    if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                        return null;
                    }
                    if (cls == Currency.class) {
                        return Currency.getInstance(str);
                    }
                    if (cls == Locale.class) {
                        return toLocale(str);
                    }
                    if (name.startsWith("java.time.")) {
                        return JSON.parseObject(JSON.toJSONString(str), cls);
                    }
                }
                if (parserConfig.get(cls) != null) {
                    return JSON.parseObject(JSON.toJSONString(obj), cls);
                }
                throw new JSONException("can not cast to : " + cls.getName());
            } else if (cls == Map.class) {
                return obj;
            } else {
                Map map = (Map) obj;
                if (cls != Object.class || map.containsKey(JSON.DEFAULT_TYPE_KEY)) {
                    return castToJavaBean(map, cls, parserConfig);
                }
                return obj;
            }
        }
    }

    public static Locale toLocale(String str) {
        String[] split = str.split("_");
        if (split.length == 1) {
            return new Locale(split[0]);
        }
        if (split.length == 2) {
            return new Locale(split[0], split[1]);
        }
        return new Locale(split[0], split[1], split[2]);
    }

    public static <T> T castToEnum(Object obj, Class<T> cls, ParserConfig parserConfig) {
        try {
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.length() == 0) {
                    return null;
                }
                if (parserConfig == null) {
                    parserConfig = ParserConfig.getGlobalInstance();
                }
                ObjectDeserializer deserializer = parserConfig.getDeserializer((Type) cls);
                if (deserializer instanceof EnumDeserializer) {
                    return ((EnumDeserializer) deserializer).getEnumByHashCode(fnv1a_64(str));
                }
                return Enum.valueOf(cls, str);
            }
            if (obj instanceof BigDecimal) {
                int intValue = intValue((BigDecimal) obj);
                T[] enumConstants = cls.getEnumConstants();
                if (intValue < enumConstants.length) {
                    return enumConstants[intValue];
                }
            }
            if (obj instanceof Number) {
                int intValue2 = ((Number) obj).intValue();
                T[] enumConstants2 = cls.getEnumConstants();
                if (intValue2 < enumConstants2.length) {
                    return enumConstants2[intValue2];
                }
            }
            throw new JSONException("can not cast to : " + cls.getName());
        } catch (Exception e) {
            throw new JSONException("can not cast to : " + cls.getName(), e);
        }
    }

    public static <T> T cast(Object obj, Type type, ParserConfig parserConfig) {
        if (obj == null) {
            return null;
        }
        if (type instanceof Class) {
            return cast(obj, (Class) type, parserConfig);
        }
        if (type instanceof ParameterizedType) {
            return cast(obj, (ParameterizedType) type, parserConfig);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
        }
        if (type instanceof TypeVariable) {
            return obj;
        }
        throw new JSONException("can not cast to : " + type);
    }

    public static <T> T cast(Object obj, ParameterizedType parameterizedType, ParserConfig parserConfig) {
        T t;
        Object obj2;
        Object obj3;
        Type rawType = parameterizedType.getRawType();
        if (rawType == List.class || rawType == ArrayList.class) {
            Type type = parameterizedType.getActualTypeArguments()[0];
            if (obj instanceof List) {
                List list = (List) obj;
                T arrayList = new ArrayList(list.size());
                for (int i = 0; i < list.size(); i++) {
                    Object obj4 = list.get(i);
                    if (!(type instanceof Class)) {
                        obj3 = cast(obj4, type, parserConfig);
                    } else if (obj4 == null || obj4.getClass() != JSONObject.class) {
                        obj3 = cast(obj4, (Class) type, parserConfig);
                    } else {
                        obj3 = ((JSONObject) obj4).toJavaObject((Class) type, parserConfig, 0);
                    }
                    arrayList.add(obj3);
                }
                return arrayList;
            }
        }
        if (rawType == Set.class || rawType == HashSet.class || rawType == TreeSet.class || rawType == Collection.class || rawType == List.class || rawType == ArrayList.class) {
            Type type2 = parameterizedType.getActualTypeArguments()[0];
            if (obj instanceof Iterable) {
                if (rawType == Set.class || rawType == HashSet.class) {
                    t = new HashSet();
                } else if (rawType == TreeSet.class) {
                    t = new TreeSet();
                } else {
                    t = new ArrayList();
                }
                for (Object next : (Iterable) obj) {
                    if (!(type2 instanceof Class)) {
                        obj2 = cast(next, type2, parserConfig);
                    } else if (next == null || next.getClass() != JSONObject.class) {
                        obj2 = cast(next, (Class) type2, parserConfig);
                    } else {
                        obj2 = ((JSONObject) next).toJavaObject((Class) type2, parserConfig, 0);
                    }
                    t.add(obj2);
                }
                return t;
            }
        }
        if (rawType == Map.class || rawType == HashMap.class) {
            Type type3 = parameterizedType.getActualTypeArguments()[0];
            Type type4 = parameterizedType.getActualTypeArguments()[1];
            if (obj instanceof Map) {
                T hashMap = new HashMap();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    hashMap.put(cast(entry.getKey(), type3, parserConfig), cast(entry.getValue(), type4, parserConfig));
                }
                return hashMap;
            }
        }
        if ((obj instanceof String) && ((String) obj).length() == 0) {
            return null;
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (actualTypeArguments.length == 1 && (parameterizedType.getActualTypeArguments()[0] instanceof WildcardType)) {
            return cast(obj, rawType, parserConfig);
        }
        if (rawType == Map.Entry.class && (obj instanceof Map)) {
            Map map = (Map) obj;
            if (map.size() == 1) {
                T t2 = (Map.Entry) map.entrySet().iterator().next();
                Object value = t2.getValue();
                if (actualTypeArguments.length == 2 && (value instanceof Map)) {
                    t2.setValue(cast(value, actualTypeArguments[1], parserConfig));
                }
                return t2;
            }
        }
        if (rawType instanceof Class) {
            if (parserConfig == null) {
                parserConfig = ParserConfig.global;
            }
            ObjectDeserializer deserializer = parserConfig.getDeserializer(rawType);
            if (deserializer != null) {
                return deserializer.deserialze(new DefaultJSONParser(JSON.toJSONString(obj), parserConfig), parameterizedType, (Object) null);
            }
        }
        throw new JSONException("can not cast to : " + parameterizedType);
    }

    /* JADX WARNING: type inference failed for: r1v10, types: [com.alibaba.fastjson.parser.deserializer.ObjectDeserializer] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T castToJavaBean(java.util.Map<java.lang.String, java.lang.Object> r5, java.lang.Class<T> r6, com.alibaba.fastjson.parser.ParserConfig r7) {
        /*
            java.lang.String r0 = "can not get javaBeanDeserializer. "
            java.lang.Class<java.lang.StackTraceElement> r1 = java.lang.StackTraceElement.class
            r2 = 0
            if (r6 != r1) goto L_0x003f
            java.lang.String r6 = "className"
            java.lang.Object r6 = r5.get(r6)     // Catch:{ Exception -> 0x014d }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x014d }
            java.lang.String r7 = "methodName"
            java.lang.Object r7 = r5.get(r7)     // Catch:{ Exception -> 0x014d }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x014d }
            java.lang.String r0 = "fileName"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ Exception -> 0x014d }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x014d }
            java.lang.String r1 = "lineNumber"
            java.lang.Object r5 = r5.get(r1)     // Catch:{ Exception -> 0x014d }
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ Exception -> 0x014d }
            if (r5 != 0) goto L_0x002a
            goto L_0x0039
        L_0x002a:
            boolean r1 = r5 instanceof java.math.BigDecimal     // Catch:{ Exception -> 0x014d }
            if (r1 == 0) goto L_0x0035
            java.math.BigDecimal r5 = (java.math.BigDecimal) r5     // Catch:{ Exception -> 0x014d }
            int r2 = r5.intValueExact()     // Catch:{ Exception -> 0x014d }
            goto L_0x0039
        L_0x0035:
            int r2 = r5.intValue()     // Catch:{ Exception -> 0x014d }
        L_0x0039:
            java.lang.StackTraceElement r5 = new java.lang.StackTraceElement     // Catch:{ Exception -> 0x014d }
            r5.<init>(r6, r7, r0, r2)     // Catch:{ Exception -> 0x014d }
            return r5
        L_0x003f:
            java.lang.String r1 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ Exception -> 0x014d }
            java.lang.Object r1 = r5.get(r1)     // Catch:{ Exception -> 0x014d }
            boolean r3 = r1 instanceof java.lang.String     // Catch:{ Exception -> 0x014d }
            r4 = 0
            if (r3 == 0) goto L_0x0078
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x014d }
            if (r7 != 0) goto L_0x0050
            com.alibaba.fastjson.parser.ParserConfig r7 = com.alibaba.fastjson.parser.ParserConfig.global     // Catch:{ Exception -> 0x014d }
        L_0x0050:
            java.lang.Class r3 = r7.checkAutoType(r1, r4)     // Catch:{ Exception -> 0x014d }
            if (r3 == 0) goto L_0x0061
            boolean r1 = r3.equals(r6)     // Catch:{ Exception -> 0x014d }
            if (r1 != 0) goto L_0x0078
            java.lang.Object r5 = castToJavaBean(r5, r3, r7)     // Catch:{ Exception -> 0x014d }
            return r5
        L_0x0061:
            java.lang.ClassNotFoundException r5 = new java.lang.ClassNotFoundException     // Catch:{ Exception -> 0x014d }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014d }
            r6.<init>()     // Catch:{ Exception -> 0x014d }
            r6.append(r1)     // Catch:{ Exception -> 0x014d }
            java.lang.String r7 = " not found"
            r6.append(r7)     // Catch:{ Exception -> 0x014d }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x014d }
            r5.<init>(r6)     // Catch:{ Exception -> 0x014d }
            throw r5     // Catch:{ Exception -> 0x014d }
        L_0x0078:
            boolean r1 = r6.isInterface()     // Catch:{ Exception -> 0x014d }
            if (r1 == 0) goto L_0x00b2
            boolean r0 = r5 instanceof com.alibaba.fastjson.JSONObject     // Catch:{ Exception -> 0x014d }
            if (r0 == 0) goto L_0x0085
            com.alibaba.fastjson.JSONObject r5 = (com.alibaba.fastjson.JSONObject) r5     // Catch:{ Exception -> 0x014d }
            goto L_0x008b
        L_0x0085:
            com.alibaba.fastjson.JSONObject r0 = new com.alibaba.fastjson.JSONObject     // Catch:{ Exception -> 0x014d }
            r0.<init>((java.util.Map<java.lang.String, java.lang.Object>) r5)     // Catch:{ Exception -> 0x014d }
            r5 = r0
        L_0x008b:
            if (r7 != 0) goto L_0x0091
            com.alibaba.fastjson.parser.ParserConfig r7 = com.alibaba.fastjson.parser.ParserConfig.getGlobalInstance()     // Catch:{ Exception -> 0x014d }
        L_0x0091:
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r7 = r7.get(r6)     // Catch:{ Exception -> 0x014d }
            if (r7 == 0) goto L_0x00a0
            java.lang.String r5 = com.alibaba.fastjson.JSON.toJSONString(r5)     // Catch:{ Exception -> 0x014d }
            java.lang.Object r5 = com.alibaba.fastjson.JSON.parseObject((java.lang.String) r5, r6)     // Catch:{ Exception -> 0x014d }
            return r5
        L_0x00a0:
            java.lang.Thread r7 = java.lang.Thread.currentThread()     // Catch:{ Exception -> 0x014d }
            java.lang.ClassLoader r7 = r7.getContextClassLoader()     // Catch:{ Exception -> 0x014d }
            r0 = 1
            java.lang.Class[] r0 = new java.lang.Class[r0]     // Catch:{ Exception -> 0x014d }
            r0[r2] = r6     // Catch:{ Exception -> 0x014d }
            java.lang.Object r5 = java.lang.reflect.Proxy.newProxyInstance(r7, r0, r5)     // Catch:{ Exception -> 0x014d }
            return r5
        L_0x00b2:
            java.lang.Class<java.util.Locale> r1 = java.util.Locale.class
            if (r6 != r1) goto L_0x00dc
            java.lang.String r1 = "language"
            java.lang.Object r1 = r5.get(r1)     // Catch:{ Exception -> 0x014d }
            java.lang.String r2 = "country"
            java.lang.Object r2 = r5.get(r2)     // Catch:{ Exception -> 0x014d }
            boolean r3 = r1 instanceof java.lang.String     // Catch:{ Exception -> 0x014d }
            if (r3 == 0) goto L_0x00dc
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x014d }
            boolean r3 = r2 instanceof java.lang.String     // Catch:{ Exception -> 0x014d }
            if (r3 == 0) goto L_0x00d4
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x014d }
            java.util.Locale r5 = new java.util.Locale     // Catch:{ Exception -> 0x014d }
            r5.<init>(r1, r2)     // Catch:{ Exception -> 0x014d }
            return r5
        L_0x00d4:
            if (r2 != 0) goto L_0x00dc
            java.util.Locale r5 = new java.util.Locale     // Catch:{ Exception -> 0x014d }
            r5.<init>(r1)     // Catch:{ Exception -> 0x014d }
            return r5
        L_0x00dc:
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            if (r6 != r1) goto L_0x00e9
            boolean r1 = r5 instanceof com.alibaba.fastjson.JSONObject     // Catch:{ Exception -> 0x014d }
            if (r1 == 0) goto L_0x00e9
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x014d }
            return r5
        L_0x00e9:
            java.lang.Class<com.alibaba.fastjson.JSON> r1 = com.alibaba.fastjson.JSON.class
            if (r6 != r1) goto L_0x00f2
            boolean r1 = r5 instanceof com.alibaba.fastjson.JSONObject     // Catch:{ Exception -> 0x014d }
            if (r1 == 0) goto L_0x00f2
            return r5
        L_0x00f2:
            java.lang.Class<java.util.LinkedHashMap> r1 = java.util.LinkedHashMap.class
            if (r6 != r1) goto L_0x010e
            boolean r1 = r5 instanceof com.alibaba.fastjson.JSONObject     // Catch:{ Exception -> 0x014d }
            if (r1 == 0) goto L_0x010e
            r1 = r5
            com.alibaba.fastjson.JSONObject r1 = (com.alibaba.fastjson.JSONObject) r1     // Catch:{ Exception -> 0x014d }
            java.util.Map r1 = r1.getInnerMap()     // Catch:{ Exception -> 0x014d }
            boolean r2 = r1 instanceof java.util.LinkedHashMap     // Catch:{ Exception -> 0x014d }
            if (r2 == 0) goto L_0x0106
            return r1
        L_0x0106:
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap     // Catch:{ Exception -> 0x014d }
            r2.<init>()     // Catch:{ Exception -> 0x014d }
            r2.putAll(r1)     // Catch:{ Exception -> 0x014d }
        L_0x010e:
            boolean r1 = r6.isInstance(r5)     // Catch:{ Exception -> 0x014d }
            if (r1 == 0) goto L_0x0115
            return r5
        L_0x0115:
            java.lang.Class<com.alibaba.fastjson.JSONObject> r1 = com.alibaba.fastjson.JSONObject.class
            if (r6 != r1) goto L_0x011f
            com.alibaba.fastjson.JSONObject r6 = new com.alibaba.fastjson.JSONObject     // Catch:{ Exception -> 0x014d }
            r6.<init>((java.util.Map<java.lang.String, java.lang.Object>) r5)     // Catch:{ Exception -> 0x014d }
            return r6
        L_0x011f:
            if (r7 != 0) goto L_0x0125
            com.alibaba.fastjson.parser.ParserConfig r7 = com.alibaba.fastjson.parser.ParserConfig.getGlobalInstance()     // Catch:{ Exception -> 0x014d }
        L_0x0125:
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r1 = r7.getDeserializer((java.lang.reflect.Type) r6)     // Catch:{ Exception -> 0x014d }
            boolean r2 = r1 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer     // Catch:{ Exception -> 0x014d }
            if (r2 == 0) goto L_0x0130
            r4 = r1
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r4 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r4     // Catch:{ Exception -> 0x014d }
        L_0x0130:
            if (r4 == 0) goto L_0x0137
            java.lang.Object r5 = r4.createInstance((java.util.Map<java.lang.String, java.lang.Object>) r5, (com.alibaba.fastjson.parser.ParserConfig) r7)     // Catch:{ Exception -> 0x014d }
            return r5
        L_0x0137:
            com.alibaba.fastjson.JSONException r5 = new com.alibaba.fastjson.JSONException     // Catch:{ Exception -> 0x014d }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014d }
            r7.<init>(r0)     // Catch:{ Exception -> 0x014d }
            java.lang.String r6 = r6.getName()     // Catch:{ Exception -> 0x014d }
            r7.append(r6)     // Catch:{ Exception -> 0x014d }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x014d }
            r5.<init>(r6)     // Catch:{ Exception -> 0x014d }
            throw r5     // Catch:{ Exception -> 0x014d }
        L_0x014d:
            r5 = move-exception
            com.alibaba.fastjson.JSONException r6 = new com.alibaba.fastjson.JSONException
            java.lang.String r7 = r5.getMessage()
            r6.<init>(r7, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.TypeUtils.castToJavaBean(java.util.Map, java.lang.Class, com.alibaba.fastjson.parser.ParserConfig):java.lang.Object");
    }

    private static void addBaseClassMappings() {
        mappings.put("byte", Byte.TYPE);
        mappings.put("short", Short.TYPE);
        mappings.put("int", Integer.TYPE);
        mappings.put("long", Long.TYPE);
        mappings.put(TypedValues.Custom.S_FLOAT, Float.TYPE);
        mappings.put("double", Double.TYPE);
        mappings.put(TypedValues.Custom.S_BOOLEAN, Boolean.TYPE);
        mappings.put("char", Character.TYPE);
        Class<byte[]> cls = byte[].class;
        mappings.put("[byte", cls);
        Class<short[]> cls2 = short[].class;
        mappings.put("[short", cls2);
        Class<int[]> cls3 = int[].class;
        mappings.put("[int", cls3);
        Class<long[]> cls4 = long[].class;
        mappings.put("[long", cls4);
        Class<float[]> cls5 = float[].class;
        mappings.put("[float", cls5);
        Class<double[]> cls6 = double[].class;
        mappings.put("[double", cls6);
        Class<boolean[]> cls7 = boolean[].class;
        mappings.put("[boolean", cls7);
        Class<char[]> cls8 = char[].class;
        mappings.put("[char", cls8);
        mappings.put("[B", cls);
        mappings.put("[S", cls2);
        mappings.put("[I", cls3);
        mappings.put("[J", cls4);
        mappings.put("[F", cls5);
        mappings.put("[D", cls6);
        mappings.put("[C", cls8);
        mappings.put("[Z", cls7);
        Class[] clsArr = {Object.class, Cloneable.class, loadClass("java.lang.AutoCloseable"), Exception.class, RuntimeException.class, IllegalAccessError.class, IllegalAccessException.class, IllegalArgumentException.class, IllegalMonitorStateException.class, IllegalStateException.class, IllegalThreadStateException.class, IndexOutOfBoundsException.class, InstantiationError.class, InstantiationException.class, InternalError.class, InterruptedException.class, LinkageError.class, NegativeArraySizeException.class, NoClassDefFoundError.class, NoSuchFieldError.class, NoSuchFieldException.class, NoSuchMethodError.class, NoSuchMethodException.class, NullPointerException.class, NumberFormatException.class, OutOfMemoryError.class, SecurityException.class, StackOverflowError.class, StringIndexOutOfBoundsException.class, TypeNotPresentException.class, VerifyError.class, StackTraceElement.class, HashMap.class, Hashtable.class, TreeMap.class, IdentityHashMap.class, WeakHashMap.class, LinkedHashMap.class, HashSet.class, LinkedHashSet.class, TreeSet.class, ArrayList.class, TimeUnit.class, ConcurrentHashMap.class, AtomicInteger.class, AtomicLong.class, Collections.EMPTY_MAP.getClass(), Boolean.class, Character.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Number.class, String.class, BigDecimal.class, BigInteger.class, BitSet.class, Calendar.class, Date.class, Locale.class, UUID.class, Time.class, java.sql.Date.class, Timestamp.class, SimpleDateFormat.class, JSONObject.class, JSONPObject.class, JSONArray.class};
        for (int i = 0; i < 71; i++) {
            Class cls9 = clsArr[i];
            if (cls9 != null) {
                mappings.put(cls9.getName(), cls9);
            }
        }
    }

    public static void clearClassMapping() {
        mappings.clear();
        addBaseClassMappings();
    }

    public static void addMapping(String str, Class<?> cls) {
        mappings.put(str, cls);
    }

    public static Class<?> loadClass(String str) {
        return loadClass(str, (ClassLoader) null);
    }

    public static boolean isPath(Class<?> cls) {
        if (pathClass == null && !pathClass_error) {
            try {
                pathClass = Class.forName("java.nio.file.Path");
            } catch (Throwable unused) {
                pathClass_error = true;
            }
        }
        Class<?> cls2 = pathClass;
        if (cls2 != null) {
            return cls2.isAssignableFrom(cls);
        }
        return false;
    }

    public static Class<?> getClassFromMapping(String str) {
        return (Class) mappings.get(str);
    }

    public static Class<?> loadClass(String str, ClassLoader classLoader) {
        return loadClass(str, classLoader, false);
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0083 */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0089 A[Catch:{ all -> 0x008e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Class<?> loadClass(java.lang.String r5, java.lang.ClassLoader r6, boolean r7) {
        /*
            if (r5 == 0) goto L_0x008f
            int r0 = r5.length()
            if (r0 == 0) goto L_0x008f
            int r0 = r5.length()
            r1 = 128(0x80, float:1.794E-43)
            if (r0 <= r1) goto L_0x0012
            goto L_0x008f
        L_0x0012:
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Class<?>> r0 = mappings
            java.lang.Object r0 = r0.get(r5)
            java.lang.Class r0 = (java.lang.Class) r0
            if (r0 == 0) goto L_0x001d
            return r0
        L_0x001d:
            r1 = 0
            char r2 = r5.charAt(r1)
            r3 = 91
            r4 = 1
            if (r2 != r3) goto L_0x0038
            java.lang.String r5 = r5.substring(r4)
            java.lang.Class r5 = loadClass(r5, r6)
            java.lang.Object r5 = java.lang.reflect.Array.newInstance(r5, r1)
            java.lang.Class r5 = r5.getClass()
            return r5
        L_0x0038:
            java.lang.String r1 = "L"
            boolean r1 = r5.startsWith(r1)
            if (r1 == 0) goto L_0x0056
            java.lang.String r1 = ";"
            boolean r1 = r5.endsWith(r1)
            if (r1 == 0) goto L_0x0056
            int r7 = r5.length()
            int r7 = r7 - r4
            java.lang.String r5 = r5.substring(r4, r7)
            java.lang.Class r5 = loadClass(r5, r6)
            return r5
        L_0x0056:
            if (r6 == 0) goto L_0x0068
            java.lang.Class r0 = r6.loadClass(r5)     // Catch:{ all -> 0x0064 }
            if (r7 == 0) goto L_0x0063
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Class<?>> r1 = mappings     // Catch:{ all -> 0x0064 }
            r1.put(r5, r0)     // Catch:{ all -> 0x0064 }
        L_0x0063:
            return r0
        L_0x0064:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0068:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0083 }
            java.lang.ClassLoader r1 = r1.getContextClassLoader()     // Catch:{ all -> 0x0083 }
            if (r1 == 0) goto L_0x0083
            if (r1 == r6) goto L_0x0083
            java.lang.Class r6 = r1.loadClass(r5)     // Catch:{ all -> 0x0083 }
            if (r7 == 0) goto L_0x0082
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Class<?>> r0 = mappings     // Catch:{ all -> 0x0080 }
            r0.put(r5, r6)     // Catch:{ all -> 0x0080 }
            goto L_0x0082
        L_0x0080:
            r0 = r6
            goto L_0x0083
        L_0x0082:
            return r6
        L_0x0083:
            java.lang.Class r0 = java.lang.Class.forName(r5)     // Catch:{ all -> 0x008e }
            if (r7 == 0) goto L_0x008e
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Class<?>> r6 = mappings     // Catch:{ all -> 0x008e }
            r6.put(r5, r0)     // Catch:{ all -> 0x008e }
        L_0x008e:
            return r0
        L_0x008f:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.TypeUtils.loadClass(java.lang.String, java.lang.ClassLoader, boolean):java.lang.Class");
    }

    public static SerializeBeanInfo buildBeanInfo(Class<?> cls, Map<String, String> map, PropertyNamingStrategy propertyNamingStrategy) {
        return buildBeanInfo(cls, map, propertyNamingStrategy, false);
    }

    /* JADX WARNING: type inference failed for: r16v0, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.alibaba.fastjson.serializer.SerializeBeanInfo buildBeanInfo(java.lang.Class<?> r16, java.util.Map<java.lang.String, java.lang.String> r17, com.alibaba.fastjson.PropertyNamingStrategy r18, boolean r19) {
        /*
            r6 = r16
            r7 = r17
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r0 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r0 = getAnnotation((java.lang.Class<?>) r6, r0)
            r8 = r0
            com.alibaba.fastjson.annotation.JSONType r8 = (com.alibaba.fastjson.annotation.JSONType) r8
            r0 = 0
            r1 = 0
            if (r8 == 0) goto L_0x008b
            java.lang.String[] r2 = r8.orders()
            java.lang.String r3 = r8.typeName()
            int r4 = r3.length()
            if (r4 != 0) goto L_0x0020
            r3 = r1
        L_0x0020:
            com.alibaba.fastjson.PropertyNamingStrategy r4 = r8.naming()
            com.alibaba.fastjson.PropertyNamingStrategy r5 = com.alibaba.fastjson.PropertyNamingStrategy.CamelCase
            if (r4 == r5) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r4 = r18
        L_0x002b:
            com.alibaba.fastjson.serializer.SerializerFeature[] r5 = r8.serialzeFeatures()
            int r5 = com.alibaba.fastjson.serializer.SerializerFeature.of(r5)
            java.lang.Class r9 = r16.getSuperclass()
            r10 = r1
        L_0x0038:
            if (r9 == 0) goto L_0x0059
            java.lang.Class<java.lang.Object> r11 = java.lang.Object.class
            if (r9 == r11) goto L_0x0059
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r11 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r11 = getAnnotation((java.lang.Class<?>) r9, r11)
            com.alibaba.fastjson.annotation.JSONType r11 = (com.alibaba.fastjson.annotation.JSONType) r11
            if (r11 != 0) goto L_0x0049
            goto L_0x0059
        L_0x0049:
            java.lang.String r10 = r11.typeKey()
            int r11 = r10.length()
            if (r11 == 0) goto L_0x0054
            goto L_0x0059
        L_0x0054:
            java.lang.Class r9 = r9.getSuperclass()
            goto L_0x0038
        L_0x0059:
            java.lang.Class[] r9 = r16.getInterfaces()
            int r11 = r9.length
            r12 = r0
        L_0x005f:
            if (r12 >= r11) goto L_0x007b
            r13 = r9[r12]
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r14 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r13 = getAnnotation((java.lang.Class<?>) r13, r14)
            com.alibaba.fastjson.annotation.JSONType r13 = (com.alibaba.fastjson.annotation.JSONType) r13
            if (r13 == 0) goto L_0x0078
            java.lang.String r10 = r13.typeKey()
            int r13 = r10.length()
            if (r13 == 0) goto L_0x0078
            goto L_0x007b
        L_0x0078:
            int r12 = r12 + 1
            goto L_0x005f
        L_0x007b:
            if (r10 == 0) goto L_0x0084
            int r9 = r10.length()
            if (r9 != 0) goto L_0x0084
            goto L_0x0085
        L_0x0084:
            r1 = r10
        L_0x0085:
            r11 = r1
            r9 = r2
            r10 = r3
            r12 = r4
            r13 = r5
            goto L_0x0091
        L_0x008b:
            r12 = r18
            r13 = r0
            r9 = r1
            r10 = r9
            r11 = r10
        L_0x0091:
            java.util.HashMap r14 = new java.util.HashMap
            r14.<init>()
            com.alibaba.fastjson.parser.ParserConfig.parserAllFieldToCache(r6, r14)
            if (r19 == 0) goto L_0x00a0
            java.util.List r0 = computeGettersWithFieldBase(r6, r7, r0, r12)
            goto L_0x00ac
        L_0x00a0:
            r4 = 0
            r0 = r16
            r1 = r8
            r2 = r17
            r3 = r14
            r5 = r12
            java.util.List r0 = computeGetters(r0, r1, r2, r3, r4, r5)
        L_0x00ac:
            int r1 = r0.size()
            com.alibaba.fastjson.util.FieldInfo[] r15 = new com.alibaba.fastjson.util.FieldInfo[r1]
            r0.toArray(r15)
            if (r9 == 0) goto L_0x00cf
            int r1 = r9.length
            if (r1 == 0) goto L_0x00cf
            if (r19 == 0) goto L_0x00c2
            r0 = 1
            java.util.List r0 = computeGettersWithFieldBase(r6, r7, r0, r12)
            goto L_0x00d8
        L_0x00c2:
            r4 = 1
            r0 = r16
            r1 = r8
            r2 = r17
            r3 = r14
            r5 = r12
            java.util.List r0 = computeGetters(r0, r1, r2, r3, r4, r5)
            goto L_0x00d8
        L_0x00cf:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r0)
            java.util.Collections.sort(r1)
            r0 = r1
        L_0x00d8:
            int r1 = r0.size()
            com.alibaba.fastjson.util.FieldInfo[] r1 = new com.alibaba.fastjson.util.FieldInfo[r1]
            r0.toArray(r1)
            boolean r0 = java.util.Arrays.equals(r1, r15)
            if (r0 == 0) goto L_0x00e9
            r7 = r15
            goto L_0x00ea
        L_0x00e9:
            r7 = r1
        L_0x00ea:
            com.alibaba.fastjson.serializer.SerializeBeanInfo r9 = new com.alibaba.fastjson.serializer.SerializeBeanInfo
            r0 = r9
            r1 = r16
            r2 = r8
            r3 = r10
            r4 = r11
            r5 = r13
            r6 = r15
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.TypeUtils.buildBeanInfo(java.lang.Class, java.util.Map, com.alibaba.fastjson.PropertyNamingStrategy, boolean):com.alibaba.fastjson.serializer.SerializeBeanInfo");
    }

    public static List<FieldInfo> computeGettersWithFieldBase(Class<?> cls, Map<String, String> map, boolean z, PropertyNamingStrategy propertyNamingStrategy) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            computeFields(cls2, map, propertyNamingStrategy, linkedHashMap, cls2.getDeclaredFields());
        }
        return getFieldInfos(cls, z, linkedHashMap);
    }

    public static List<FieldInfo> computeGetters(Class<?> cls, Map<String, String> map) {
        return computeGetters(cls, map, true);
    }

    public static List<FieldInfo> computeGetters(Class<?> cls, Map<String, String> map, boolean z) {
        HashMap hashMap = new HashMap();
        ParserConfig.parserAllFieldToCache(cls, hashMap);
        return computeGetters(cls, (JSONType) getAnnotation(cls, JSONType.class), map, hashMap, z, PropertyNamingStrategy.CamelCase);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:122:0x029d, code lost:
        if (r2 == null) goto L_0x022d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x042e, code lost:
        r0 = r15.substring(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x044d, code lost:
        if (r2 == null) goto L_0x0513;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x04a8, code lost:
        if (r0 == null) goto L_0x0515;
     */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x02d6  */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x0459  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.alibaba.fastjson.util.FieldInfo> computeGetters(java.lang.Class<?> r38, com.alibaba.fastjson.annotation.JSONType r39, java.util.Map<java.lang.String, java.lang.String> r40, java.util.Map<java.lang.String, java.lang.reflect.Field> r41, boolean r42, com.alibaba.fastjson.PropertyNamingStrategy r43) {
        /*
            r12 = r38
            r13 = r40
            r14 = r41
            r15 = r43
            java.util.LinkedHashMap r11 = new java.util.LinkedHashMap
            r11.<init>()
            boolean r16 = isKotlin(r38)
            r17 = 0
            r0 = r17
            java.lang.annotation.Annotation[][] r0 = (java.lang.annotation.Annotation[][]) r0
            java.lang.reflect.Method[] r10 = r38.getMethods()
            int r9 = r10.length
            r0 = r17
            r1 = r0
            r2 = r1
            r3 = r2
            r7 = 0
        L_0x0022:
            if (r7 >= r9) goto L_0x052d
            r6 = r10[r7]
            java.lang.String r5 = r6.getName()
            int r4 = r6.getModifiers()
            boolean r4 = java.lang.reflect.Modifier.isStatic(r4)
            if (r4 == 0) goto L_0x0035
            goto L_0x0085
        L_0x0035:
            java.lang.Class r4 = r6.getReturnType()
            java.lang.Class r8 = java.lang.Void.TYPE
            boolean r4 = r4.equals(r8)
            if (r4 == 0) goto L_0x0042
            goto L_0x0085
        L_0x0042:
            java.lang.Class[] r4 = r6.getParameterTypes()
            int r4 = r4.length
            if (r4 == 0) goto L_0x004a
            goto L_0x0085
        L_0x004a:
            java.lang.Class r4 = r6.getReturnType()
            java.lang.Class<java.lang.ClassLoader> r8 = java.lang.ClassLoader.class
            if (r4 != r8) goto L_0x0053
            goto L_0x0085
        L_0x0053:
            java.lang.String r4 = "getMetaClass"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x006c
            java.lang.Class r4 = r6.getReturnType()
            java.lang.String r4 = r4.getName()
            java.lang.String r8 = "groovy.lang.MetaClass"
            boolean r4 = r4.equals(r8)
            if (r4 == 0) goto L_0x006c
            goto L_0x0085
        L_0x006c:
            java.lang.String r4 = "getSuppressed"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x007d
            java.lang.Class r4 = r6.getDeclaringClass()
            java.lang.Class<java.lang.Throwable> r8 = java.lang.Throwable.class
            if (r4 != r8) goto L_0x007d
            goto L_0x0085
        L_0x007d:
            if (r16 == 0) goto L_0x0092
            boolean r4 = isKotlinIgnore(r12, r5)
            if (r4 == 0) goto L_0x0092
        L_0x0085:
            r21 = r0
            r23 = r7
            r32 = r9
            r33 = r10
            r0 = r11
            r29 = 0
            goto L_0x051c
        L_0x0092:
            r4 = 0
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r4)
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r4 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r4 = getAnnotation((java.lang.reflect.Method) r6, r4)
            com.alibaba.fastjson.annotation.JSONField r4 = (com.alibaba.fastjson.annotation.JSONField) r4
            if (r4 != 0) goto L_0x00a5
            com.alibaba.fastjson.annotation.JSONField r4 = getSuperMethodAnnotation(r12, r6)
        L_0x00a5:
            r18 = r1
            java.lang.String r1 = "get"
            r19 = r11
            if (r4 != 0) goto L_0x017b
            if (r16 == 0) goto L_0x017b
            if (r0 != 0) goto L_0x00f8
            java.lang.reflect.Constructor[] r0 = r38.getDeclaredConstructors()
            java.lang.reflect.Constructor r20 = getKotlinConstructor(r0)
            if (r20 == 0) goto L_0x00f6
            java.lang.annotation.Annotation[][] r3 = getParameterAnnotations((java.lang.reflect.Constructor) r20)
            java.lang.String[] r11 = getKoltinConstructorParameters(r38)
            if (r11 == 0) goto L_0x00ef
            int r2 = r11.length
            java.lang.String[] r2 = new java.lang.String[r2]
            r21 = r0
            int r0 = r11.length
            r18 = r3
            r3 = 0
            java.lang.System.arraycopy(r11, r3, r2, r3, r0)
            java.util.Arrays.sort(r2)
            int r0 = r11.length
            short[] r0 = new short[r0]
            r22 = r4
        L_0x00d9:
            int r4 = r11.length
            if (r3 >= r4) goto L_0x00e8
            r4 = r11[r3]
            int r4 = java.util.Arrays.binarySearch(r2, r4)
            r0[r4] = r3
            int r3 = r3 + 1
            short r3 = (short) r3
            goto L_0x00d9
        L_0x00e8:
            r11 = r2
            r3 = r18
            r2 = r0
            r0 = r21
            goto L_0x00fc
        L_0x00ef:
            r21 = r0
            r18 = r3
            r22 = r4
            goto L_0x00fc
        L_0x00f6:
            r21 = r0
        L_0x00f8:
            r22 = r4
            r11 = r18
        L_0x00fc:
            if (r11 == 0) goto L_0x016c
            if (r2 == 0) goto L_0x016c
            boolean r4 = r5.startsWith(r1)
            if (r4 == 0) goto L_0x016c
            r4 = 3
            java.lang.String r18 = r5.substring(r4)
            java.lang.String r4 = decapitalize(r18)
            int r18 = java.util.Arrays.binarySearch(r11, r4)
            r21 = r0
            r23 = r7
            if (r18 >= 0) goto L_0x012b
            r0 = 0
        L_0x011a:
            int r7 = r11.length
            if (r0 >= r7) goto L_0x012b
            r7 = r11[r0]
            boolean r7 = r4.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x0128
            r18 = r0
            goto L_0x012b
        L_0x0128:
            int r0 = r0 + 1
            goto L_0x011a
        L_0x012b:
            if (r18 < 0) goto L_0x0167
            short r0 = r2[r18]
            r0 = r3[r0]
            if (r0 == 0) goto L_0x014e
            int r7 = r0.length
            r18 = r2
            r2 = 0
        L_0x0137:
            r24 = r3
            if (r2 >= r7) goto L_0x0152
            r3 = r0[r2]
            r25 = r0
            boolean r0 = r3 instanceof com.alibaba.fastjson.annotation.JSONField
            if (r0 == 0) goto L_0x0147
            r0 = r3
            com.alibaba.fastjson.annotation.JSONField r0 = (com.alibaba.fastjson.annotation.JSONField) r0
            goto L_0x0154
        L_0x0147:
            int r2 = r2 + 1
            r3 = r24
            r0 = r25
            goto L_0x0137
        L_0x014e:
            r18 = r2
            r24 = r3
        L_0x0152:
            r0 = r22
        L_0x0154:
            if (r0 != 0) goto L_0x0164
            java.lang.reflect.Field r2 = com.alibaba.fastjson.parser.ParserConfig.getFieldFromCache(r4, r14)
            if (r2 == 0) goto L_0x0164
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r0 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r0 = getAnnotation((java.lang.reflect.Field) r2, r0)
            com.alibaba.fastjson.annotation.JSONField r0 = (com.alibaba.fastjson.annotation.JSONField) r0
        L_0x0164:
            r25 = r0
            goto L_0x0176
        L_0x0167:
            r18 = r2
            r24 = r3
            goto L_0x0174
        L_0x016c:
            r21 = r0
            r18 = r2
            r24 = r3
            r23 = r7
        L_0x0174:
            r25 = r22
        L_0x0176:
            r22 = r18
            r18 = r11
            goto L_0x0187
        L_0x017b:
            r22 = r4
            r23 = r7
            r21 = r0
            r24 = r3
            r25 = r22
            r22 = r2
        L_0x0187:
            r11 = 0
            if (r25 == 0) goto L_0x020e
            boolean r0 = r25.serialize()
            if (r0 != 0) goto L_0x019a
        L_0x0190:
            r32 = r9
            r33 = r10
            r0 = r19
            r29 = 0
            goto L_0x0516
        L_0x019a:
            int r7 = r25.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r0 = r25.serialzeFeatures()
            int r26 = com.alibaba.fastjson.serializer.SerializerFeature.of(r0)
            com.alibaba.fastjson.parser.Feature[] r0 = r25.parseFeatures()
            int r27 = com.alibaba.fastjson.parser.Feature.of(r0)
            java.lang.String r0 = r25.name()
            int r0 = r0.length()
            if (r0 == 0) goto L_0x01f7
            java.lang.String r0 = r25.name()
            if (r13 == 0) goto L_0x01c7
            java.lang.Object r0 = r13.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x01c7
            goto L_0x0190
        L_0x01c7:
            r8 = r0
            com.alibaba.fastjson.util.FieldInfo r5 = new com.alibaba.fastjson.util.FieldInfo
            r20 = 0
            r28 = 0
            r3 = 0
            r0 = r5
            r1 = r8
            r2 = r6
            r29 = 0
            r4 = r38
            r6 = r5
            r5 = r20
            r30 = r6
            r6 = r7
            r7 = r26
            r31 = r8
            r8 = r27
            r32 = r9
            r9 = r25
            r33 = r10
            r10 = r28
            r15 = r19
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r0 = r30
            r1 = r31
            r15.put(r1, r0)
            goto L_0x022d
        L_0x01f7:
            r32 = r9
            r33 = r10
            r15 = r19
            r29 = 0
            java.lang.String r0 = r25.label()
            int r0 = r0.length()
            if (r0 == 0) goto L_0x021c
            java.lang.String r11 = r25.label()
            goto L_0x021c
        L_0x020e:
            r32 = r9
            r33 = r10
            r15 = r19
            r29 = 0
            r7 = r29
            r26 = r7
            r27 = r26
        L_0x021c:
            boolean r0 = r5.startsWith(r1)
            r10 = 102(0x66, float:1.43E-43)
            r9 = 95
            if (r0 == 0) goto L_0x03bf
            int r0 = r5.length()
            r1 = 4
            if (r0 >= r1) goto L_0x0230
        L_0x022d:
            r0 = r15
            goto L_0x0516
        L_0x0230:
            java.lang.String r0 = "getClass"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0239
            goto L_0x022d
        L_0x0239:
            java.lang.String r0 = "getDeclaringClass"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0248
            boolean r0 = r38.isEnum()
            if (r0 == 0) goto L_0x0248
            goto L_0x022d
        L_0x0248:
            r0 = 3
            char r2 = r5.charAt(r0)
            boolean r3 = java.lang.Character.isUpperCase(r2)
            if (r3 != 0) goto L_0x02a0
            r3 = 512(0x200, float:7.175E-43)
            if (r2 <= r3) goto L_0x0258
            goto L_0x02a0
        L_0x0258:
            if (r2 != r9) goto L_0x0274
            java.lang.String r1 = r5.substring(r1)
            java.lang.Object r2 = r14.get(r1)
            java.lang.reflect.Field r2 = (java.lang.reflect.Field) r2
            if (r2 != 0) goto L_0x02ce
            java.lang.String r2 = r5.substring(r0)
            java.lang.reflect.Field r3 = com.alibaba.fastjson.parser.ParserConfig.getFieldFromCache(r2, r14)
            if (r3 != 0) goto L_0x0271
            goto L_0x0272
        L_0x0271:
            r1 = r2
        L_0x0272:
            r2 = r3
            goto L_0x02ce
        L_0x0274:
            if (r2 != r10) goto L_0x027b
            java.lang.String r1 = r5.substring(r0)
            goto L_0x02cc
        L_0x027b:
            int r2 = r5.length()
            r3 = 5
            if (r2 < r3) goto L_0x0295
            char r1 = r5.charAt(r1)
            boolean r1 = java.lang.Character.isUpperCase(r1)
            if (r1 == 0) goto L_0x0295
            java.lang.String r1 = r5.substring(r0)
            java.lang.String r1 = decapitalize(r1)
            goto L_0x02cc
        L_0x0295:
            java.lang.String r1 = r5.substring(r0)
            java.lang.reflect.Field r2 = com.alibaba.fastjson.parser.ParserConfig.getFieldFromCache(r1, r14)
            if (r2 != 0) goto L_0x02ce
            goto L_0x022d
        L_0x02a0:
            boolean r2 = compatibleWithJavaBean
            if (r2 == 0) goto L_0x02ad
            java.lang.String r1 = r5.substring(r0)
            java.lang.String r1 = decapitalize(r1)
            goto L_0x02c8
        L_0x02ad:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            char r3 = r5.charAt(r0)
            char r3 = java.lang.Character.toLowerCase(r3)
            r2.append(r3)
            java.lang.String r1 = r5.substring(r1)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
        L_0x02c8:
            java.lang.String r1 = getPropertyNameByCompatibleFieldName(r14, r5, r1, r0)
        L_0x02cc:
            r2 = r17
        L_0x02ce:
            boolean r0 = isJSONTypeIgnore(r12, r1)
            if (r0 == 0) goto L_0x02d6
            goto L_0x022d
        L_0x02d6:
            if (r2 != 0) goto L_0x02dc
            java.lang.reflect.Field r2 = com.alibaba.fastjson.parser.ParserConfig.getFieldFromCache(r1, r14)
        L_0x02dc:
            r0 = 1
            if (r2 != 0) goto L_0x02ff
            int r3 = r1.length()
            if (r3 <= r0) goto L_0x02ff
            char r3 = r1.charAt(r0)
            r4 = 65
            if (r3 < r4) goto L_0x02ff
            r4 = 90
            if (r3 > r4) goto L_0x02ff
            r4 = 3
            java.lang.String r2 = r5.substring(r4)
            java.lang.String r2 = decapitalize(r2)
            java.lang.reflect.Field r2 = com.alibaba.fastjson.parser.ParserConfig.getFieldFromCache(r2, r14)
            goto L_0x0300
        L_0x02ff:
            r4 = 3
        L_0x0300:
            r3 = r2
            if (r3 == 0) goto L_0x036a
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r2 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r2 = getAnnotation((java.lang.reflect.Field) r3, r2)
            com.alibaba.fastjson.annotation.JSONField r2 = (com.alibaba.fastjson.annotation.JSONField) r2
            if (r2 == 0) goto L_0x0364
            boolean r7 = r2.serialize()
            if (r7 != 0) goto L_0x0315
            goto L_0x022d
        L_0x0315:
            int r7 = r2.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r19 = r2.serialzeFeatures()
            int r19 = com.alibaba.fastjson.serializer.SerializerFeature.of(r19)
            com.alibaba.fastjson.parser.Feature[] r20 = r2.parseFeatures()
            int r20 = com.alibaba.fastjson.parser.Feature.of(r20)
            java.lang.String r26 = r2.name()
            int r26 = r26.length()
            if (r26 == 0) goto L_0x0348
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            java.lang.String r1 = r2.name()
            if (r13 == 0) goto L_0x0347
            java.lang.Object r1 = r13.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            if (r1 != 0) goto L_0x0347
            goto L_0x022d
        L_0x0347:
            r8 = r0
        L_0x0348:
            java.lang.String r0 = r2.label()
            int r0 = r0.length()
            if (r0 == 0) goto L_0x0360
            java.lang.String r0 = r2.label()
            r11 = r2
            r26 = r19
            r27 = r20
            r20 = r0
            r19 = r7
            goto L_0x0370
        L_0x0360:
            r26 = r19
            r27 = r20
        L_0x0364:
            r19 = r7
            r20 = r11
            r11 = r2
            goto L_0x0370
        L_0x036a:
            r19 = r7
            r20 = r11
            r11 = r17
        L_0x0370:
            if (r13 == 0) goto L_0x037d
            java.lang.Object r0 = r13.get(r1)
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
            if (r1 != 0) goto L_0x037d
            goto L_0x022d
        L_0x037d:
            r7 = r15
            r15 = r43
            if (r15 == 0) goto L_0x038c
            boolean r0 = r8.booleanValue()
            if (r0 != 0) goto L_0x038c
            java.lang.String r1 = r15.translate(r1)
        L_0x038c:
            r8 = r1
            com.alibaba.fastjson.util.FieldInfo r2 = new com.alibaba.fastjson.util.FieldInfo
            r28 = 0
            r0 = r2
            r1 = r8
            r34 = r2
            r2 = r6
            r30 = r4
            r4 = r38
            r15 = r5
            r5 = r28
            r28 = r6
            r6 = r19
            r35 = r7
            r7 = r26
            r36 = r8
            r8 = r27
            r9 = r25
            r10 = r11
            r13 = r30
            r11 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r0 = r34
            r10 = r35
            r1 = r36
            r10.put(r1, r0)
            r7 = r19
            goto L_0x03c4
        L_0x03bf:
            r28 = r6
            r10 = r15
            r13 = 3
            r15 = r5
        L_0x03c4:
            java.lang.String r0 = "is"
            boolean r0 = r15.startsWith(r0)
            if (r0 == 0) goto L_0x0513
            int r0 = r15.length()
            if (r0 >= r13) goto L_0x03d4
            goto L_0x0513
        L_0x03d4:
            java.lang.Class r0 = r28.getReturnType()
            java.lang.Class r1 = java.lang.Boolean.TYPE
            if (r0 == r1) goto L_0x03e6
            java.lang.Class r0 = r28.getReturnType()
            java.lang.Class<java.lang.Boolean> r1 = java.lang.Boolean.class
            if (r0 == r1) goto L_0x03e6
            goto L_0x0513
        L_0x03e6:
            r0 = 2
            char r1 = r15.charAt(r0)
            boolean r2 = java.lang.Character.isUpperCase(r1)
            if (r2 == 0) goto L_0x041e
            boolean r1 = compatibleWithJavaBean
            if (r1 == 0) goto L_0x03fe
            java.lang.String r1 = r15.substring(r0)
            java.lang.String r1 = decapitalize(r1)
            goto L_0x0419
        L_0x03fe:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            char r2 = r15.charAt(r0)
            char r2 = java.lang.Character.toLowerCase(r2)
            r1.append(r2)
            java.lang.String r2 = r15.substring(r13)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
        L_0x0419:
            java.lang.String r0 = getPropertyNameByCompatibleFieldName(r14, r15, r1, r0)
            goto L_0x0442
        L_0x041e:
            r2 = 95
            if (r1 != r2) goto L_0x043a
            java.lang.String r1 = r15.substring(r13)
            java.lang.Object r2 = r14.get(r1)
            java.lang.reflect.Field r2 = (java.lang.reflect.Field) r2
            if (r2 != 0) goto L_0x0438
            java.lang.String r0 = r15.substring(r0)
            java.lang.reflect.Field r2 = com.alibaba.fastjson.parser.ParserConfig.getFieldFromCache(r0, r14)
            if (r2 != 0) goto L_0x0451
        L_0x0438:
            r0 = r1
            goto L_0x0451
        L_0x043a:
            r2 = 102(0x66, float:1.43E-43)
            if (r1 != r2) goto L_0x0445
            java.lang.String r0 = r15.substring(r0)
        L_0x0442:
            r2 = r17
            goto L_0x0451
        L_0x0445:
            java.lang.String r0 = r15.substring(r0)
            java.lang.reflect.Field r2 = com.alibaba.fastjson.parser.ParserConfig.getFieldFromCache(r0, r14)
            if (r2 != 0) goto L_0x0451
            goto L_0x0513
        L_0x0451:
            boolean r1 = isJSONTypeIgnore(r12, r0)
            if (r1 == 0) goto L_0x0459
            goto L_0x0513
        L_0x0459:
            if (r2 != 0) goto L_0x045f
            java.lang.reflect.Field r2 = com.alibaba.fastjson.parser.ParserConfig.getFieldFromCache(r0, r14)
        L_0x045f:
            if (r2 != 0) goto L_0x0467
            java.lang.reflect.Field r1 = com.alibaba.fastjson.parser.ParserConfig.getFieldFromCache(r15, r14)
            r3 = r1
            goto L_0x0468
        L_0x0467:
            r3 = r2
        L_0x0468:
            if (r3 == 0) goto L_0x04d1
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r1 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r1 = getAnnotation((java.lang.reflect.Field) r3, r1)
            com.alibaba.fastjson.annotation.JSONField r1 = (com.alibaba.fastjson.annotation.JSONField) r1
            if (r1 == 0) goto L_0x04c7
            boolean r2 = r1.serialize()
            if (r2 != 0) goto L_0x047c
            goto L_0x0513
        L_0x047c:
            int r2 = r1.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r4 = r1.serialzeFeatures()
            int r4 = com.alibaba.fastjson.serializer.SerializerFeature.of(r4)
            com.alibaba.fastjson.parser.Feature[] r5 = r1.parseFeatures()
            int r5 = com.alibaba.fastjson.parser.Feature.of(r5)
            java.lang.String r6 = r1.name()
            int r6 = r6.length()
            if (r6 == 0) goto L_0x04ac
            java.lang.String r0 = r1.name()
            r13 = r40
            if (r13 == 0) goto L_0x04ae
            java.lang.Object r0 = r13.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x04ae
            goto L_0x04f5
        L_0x04ac:
            r13 = r40
        L_0x04ae:
            java.lang.String r6 = r1.label()
            int r6 = r6.length()
            if (r6 == 0) goto L_0x04c2
            java.lang.String r6 = r1.label()
            r11 = r1
            r7 = r4
            r8 = r5
            r15 = r6
            r6 = r2
            goto L_0x04db
        L_0x04c2:
            r6 = r2
            r7 = r4
            r8 = r5
            r15 = r11
            goto L_0x04cf
        L_0x04c7:
            r13 = r40
            r6 = r7
            r15 = r11
            r7 = r26
            r8 = r27
        L_0x04cf:
            r11 = r1
            goto L_0x04db
        L_0x04d1:
            r13 = r40
            r6 = r7
            r15 = r11
            r11 = r17
            r7 = r26
            r8 = r27
        L_0x04db:
            if (r13 == 0) goto L_0x04e6
            java.lang.Object r0 = r13.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x04e6
        L_0x04e5:
            goto L_0x0515
        L_0x04e6:
            r9 = r43
            if (r9 == 0) goto L_0x04ee
            java.lang.String r0 = r9.translate(r0)
        L_0x04ee:
            r5 = r0
            boolean r0 = r10.containsKey(r5)
            if (r0 == 0) goto L_0x04f6
        L_0x04f5:
            goto L_0x04e5
        L_0x04f6:
            com.alibaba.fastjson.util.FieldInfo r4 = new com.alibaba.fastjson.util.FieldInfo
            r19 = 0
            r0 = r4
            r1 = r5
            r2 = r28
            r14 = r4
            r4 = r38
            r12 = r5
            r5 = r19
            r9 = r25
            r37 = r10
            r10 = r11
            r11 = r15
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r0 = r37
            r0.put(r12, r14)
            goto L_0x0516
        L_0x0513:
            r13 = r40
        L_0x0515:
            r0 = r10
        L_0x0516:
            r1 = r18
            r2 = r22
            r3 = r24
        L_0x051c:
            int r7 = r23 + 1
            r12 = r38
            r14 = r41
            r15 = r43
            r11 = r0
            r0 = r21
            r9 = r32
            r10 = r33
            goto L_0x0022
        L_0x052d:
            r0 = r11
            java.lang.reflect.Field[] r1 = r38.getFields()
            r2 = r38
            r3 = r43
            computeFields(r2, r13, r3, r0, r1)
            r1 = r42
            java.util.List r0 = getFieldInfos(r2, r1, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.TypeUtils.computeGetters(java.lang.Class, com.alibaba.fastjson.annotation.JSONType, java.util.Map, java.util.Map, boolean, com.alibaba.fastjson.PropertyNamingStrategy):java.util.List");
    }

    private static List<FieldInfo> getFieldInfos(Class<?> cls, boolean z, Map<String, FieldInfo> map) {
        ArrayList arrayList = new ArrayList();
        JSONType jSONType = (JSONType) getAnnotation(cls, JSONType.class);
        String[] orders = jSONType != null ? jSONType.orders() : null;
        if (orders == null || orders.length <= 0) {
            for (FieldInfo add : map.values()) {
                arrayList.add(add);
            }
            if (z) {
                Collections.sort(arrayList);
            }
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap(arrayList.size());
            for (FieldInfo next : map.values()) {
                linkedHashMap.put(next.name, next);
            }
            for (String str : orders) {
                FieldInfo fieldInfo = (FieldInfo) linkedHashMap.get(str);
                if (fieldInfo != null) {
                    arrayList.add(fieldInfo);
                    linkedHashMap.remove(str);
                }
            }
            for (FieldInfo add2 : linkedHashMap.values()) {
                arrayList.add(add2);
            }
        }
        return arrayList;
    }

    private static void computeFields(Class<?> cls, Map<String, String> map, PropertyNamingStrategy propertyNamingStrategy, Map<String, FieldInfo> map2, Field[] fieldArr) {
        String str;
        int i;
        int i2;
        int i3;
        Map<String, String> map3 = map;
        PropertyNamingStrategy propertyNamingStrategy2 = propertyNamingStrategy;
        Map<String, FieldInfo> map4 = map2;
        for (Field field : fieldArr) {
            if (!Modifier.isStatic(field.getModifiers())) {
                JSONField jSONField = (JSONField) getAnnotation(field, JSONField.class);
                String name = field.getName();
                String str2 = null;
                if (jSONField == null) {
                    str = null;
                    i3 = 0;
                    i2 = 0;
                    i = 0;
                } else if (jSONField.serialize()) {
                    int ordinal = jSONField.ordinal();
                    int of = SerializerFeature.of(jSONField.serialzeFeatures());
                    int of2 = Feature.of(jSONField.parseFeatures());
                    if (jSONField.name().length() != 0) {
                        name = jSONField.name();
                    }
                    if (jSONField.label().length() != 0) {
                        str2 = jSONField.label();
                    }
                    str = str2;
                    i3 = ordinal;
                    i2 = of;
                    i = of2;
                }
                if (map3 == null || (name = map3.get(name)) != null) {
                    if (propertyNamingStrategy2 != null) {
                        name = propertyNamingStrategy2.translate(name);
                    }
                    String str3 = name;
                    if (!map4.containsKey(str3)) {
                        FieldInfo fieldInfo = r7;
                        FieldInfo fieldInfo2 = new FieldInfo(str3, (Method) null, field, cls, (Type) null, i3, i2, i, (JSONField) null, jSONField, str);
                        map4.put(str3, fieldInfo);
                    }
                }
            }
        }
    }

    private static String getPropertyNameByCompatibleFieldName(Map<String, Field> map, String str, String str2, int i) {
        if (!compatibleWithFieldName || map.containsKey(str2)) {
            return str2;
        }
        String substring = str.substring(i);
        return map.containsKey(substring) ? substring : str2;
    }

    public static JSONField getSuperMethodAnnotation(Class<?> cls, Method method) {
        Class[] interfaces = cls.getInterfaces();
        if (interfaces.length > 0) {
            Class[] parameterTypes = method.getParameterTypes();
            for (Class methods : interfaces) {
                for (Method method2 : methods.getMethods()) {
                    Class[] parameterTypes2 = method2.getParameterTypes();
                    if (parameterTypes2.length == parameterTypes.length && method2.getName().equals(method.getName())) {
                        int i = 0;
                        while (true) {
                            if (i >= parameterTypes.length) {
                                JSONField jSONField = (JSONField) getAnnotation(method2, JSONField.class);
                                if (jSONField != null) {
                                    return jSONField;
                                }
                            } else if (!parameterTypes2[i].equals(parameterTypes[i])) {
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                }
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null && Modifier.isAbstract(superclass.getModifiers())) {
            Class[] parameterTypes3 = method.getParameterTypes();
            for (Method method3 : superclass.getMethods()) {
                Class[] parameterTypes4 = method3.getParameterTypes();
                if (parameterTypes4.length == parameterTypes3.length && method3.getName().equals(method.getName())) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= parameterTypes3.length) {
                            JSONField jSONField2 = (JSONField) getAnnotation(method3, JSONField.class);
                            if (jSONField2 != null) {
                                return jSONField2;
                            }
                        } else if (!parameterTypes4[i2].equals(parameterTypes3[i2])) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
        }
        return null;
    }

    private static boolean isJSONTypeIgnore(Class<?> cls, String str) {
        JSONType jSONType = (JSONType) getAnnotation(cls, JSONType.class);
        if (jSONType != null) {
            String[] includes = jSONType.includes();
            if (includes.length > 0) {
                for (String equals : includes) {
                    if (str.equals(equals)) {
                        return false;
                    }
                }
                return true;
            }
            String[] ignores = jSONType.ignores();
            for (String equals2 : ignores) {
                if (str.equals(equals2)) {
                    return true;
                }
            }
        }
        if (cls.getSuperclass() == Object.class || cls.getSuperclass() == null) {
            return false;
        }
        return isJSONTypeIgnore(cls.getSuperclass(), str);
    }

    public static boolean isGenericParamType(Type type) {
        if (type instanceof ParameterizedType) {
            return true;
        }
        if (!(type instanceof Class)) {
            return false;
        }
        Type genericSuperclass = ((Class) type).getGenericSuperclass();
        if (genericSuperclass == Object.class || !isGenericParamType(genericSuperclass)) {
            return false;
        }
        return true;
    }

    public static Type getGenericParamType(Type type) {
        return (!(type instanceof ParameterizedType) && (type instanceof Class)) ? getGenericParamType(((Class) type).getGenericSuperclass()) : type;
    }

    public static Type unwrapOptional(Type type) {
        if (!optionalClassInited) {
            try {
                optionalClass = Class.forName("java.util.Optional");
            } catch (Exception unused) {
            } catch (Throwable th) {
                optionalClassInited = true;
                throw th;
            }
            optionalClassInited = true;
        }
        if (!(type instanceof ParameterizedType)) {
            return type;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        return parameterizedType.getRawType() == optionalClass ? parameterizedType.getActualTypeArguments()[0] : type;
    }

    public static Class<?> getClass(Type type) {
        if (type.getClass() == Class.class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        }
        if (type instanceof TypeVariable) {
            Type type2 = ((TypeVariable) type).getBounds()[0];
            if (type2 instanceof Class) {
                return (Class) type2;
            }
            return getClass(type2);
        } else if (!(type instanceof WildcardType)) {
            return Object.class;
        } else {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            if (upperBounds.length == 1) {
                return getClass(upperBounds[0]);
            }
            return Object.class;
        }
    }

    public static Field getField(Class<?> cls, String str, Field[] fieldArr) {
        char charAt;
        char charAt2;
        for (Field field : fieldArr) {
            String name = field.getName();
            if (str.equals(name)) {
                return field;
            }
            if (str.length() > 2 && (charAt = str.charAt(0)) >= 'a' && charAt <= 'z' && (charAt2 = str.charAt(1)) >= 'A' && charAt2 <= 'Z' && str.equalsIgnoreCase(name)) {
                return field;
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass == null || superclass == Object.class) {
            return null;
        }
        return getField(superclass, str, superclass.getDeclaredFields());
    }

    public static int getSerializeFeatures(Class<?> cls) {
        JSONType jSONType = (JSONType) getAnnotation(cls, JSONType.class);
        if (jSONType == null) {
            return 0;
        }
        return SerializerFeature.of(jSONType.serialzeFeatures());
    }

    public static int getParserFeatures(Class<?> cls) {
        JSONType jSONType = (JSONType) getAnnotation(cls, JSONType.class);
        if (jSONType == null) {
            return 0;
        }
        return Feature.of(jSONType.parseFeatures());
    }

    public static String decapitalize(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (str.length() > 1 && Character.isUpperCase(str.charAt(1)) && Character.isUpperCase(str.charAt(0))) {
            return str;
        }
        char[] charArray = str.toCharArray();
        charArray[0] = Character.toLowerCase(charArray[0]);
        return new String(charArray);
    }

    static void setAccessible(AccessibleObject accessibleObject) {
        if (setAccessibleEnable && !accessibleObject.isAccessible()) {
            try {
                accessibleObject.setAccessible(true);
            } catch (AccessControlException unused) {
                setAccessibleEnable = false;
            }
        }
    }

    public static Type getCollectionItemType(Type type) {
        if (type instanceof ParameterizedType) {
            return getCollectionItemType((ParameterizedType) type);
        }
        if (type instanceof Class) {
            return getCollectionItemType((Class<?>) (Class) type);
        }
        return Object.class;
    }

    private static Type getCollectionItemType(Class<?> cls) {
        if (cls.getName().startsWith("java.")) {
            return Object.class;
        }
        return getCollectionItemType(getCollectionSuperType(cls));
    }

    private static Type getCollectionItemType(ParameterizedType parameterizedType) {
        Type rawType = parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (rawType == Collection.class) {
            return getWildcardTypeUpperBounds(actualTypeArguments[0]);
        }
        Class cls = (Class) rawType;
        Map<TypeVariable, Type> createActualTypeMap = createActualTypeMap(cls.getTypeParameters(), actualTypeArguments);
        Type collectionSuperType = getCollectionSuperType(cls);
        if (!(collectionSuperType instanceof ParameterizedType)) {
            return getCollectionItemType((Class<?>) (Class) collectionSuperType);
        }
        Class<?> rawClass = getRawClass(collectionSuperType);
        Type[] actualTypeArguments2 = ((ParameterizedType) collectionSuperType).getActualTypeArguments();
        if (actualTypeArguments2.length > 0) {
            return getCollectionItemType(makeParameterizedType(rawClass, actualTypeArguments2, createActualTypeMap));
        }
        return getCollectionItemType(rawClass);
    }

    private static Type getCollectionSuperType(Class<?> cls) {
        Type type = null;
        for (Type type2 : cls.getGenericInterfaces()) {
            Class<?> rawClass = getRawClass(type2);
            if (rawClass == Collection.class) {
                return type2;
            }
            if (Collection.class.isAssignableFrom(rawClass)) {
                type = type2;
            }
        }
        return type == null ? cls.getGenericSuperclass() : type;
    }

    private static Map<TypeVariable, Type> createActualTypeMap(TypeVariable[] typeVariableArr, Type[] typeArr) {
        int length = typeVariableArr.length;
        HashMap hashMap = new HashMap(length);
        for (int i = 0; i < length; i++) {
            hashMap.put(typeVariableArr[i], typeArr[i]);
        }
        return hashMap;
    }

    private static ParameterizedType makeParameterizedType(Class<?> cls, Type[] typeArr, Map<TypeVariable, Type> map) {
        int length = typeArr.length;
        Type[] typeArr2 = new Type[length];
        for (int i = 0; i < length; i++) {
            typeArr2[i] = getActualType(typeArr[i], map);
        }
        return new ParameterizedTypeImpl(typeArr2, (Type) null, cls);
    }

    private static Type getActualType(Type type, Map<TypeVariable, Type> map) {
        if (type instanceof TypeVariable) {
            return map.get(type);
        }
        if (type instanceof ParameterizedType) {
            return makeParameterizedType(getRawClass(type), ((ParameterizedType) type).getActualTypeArguments(), map);
        }
        return type instanceof GenericArrayType ? new GenericArrayTypeImpl(getActualType(((GenericArrayType) type).getGenericComponentType(), map)) : type;
    }

    private static Type getWildcardTypeUpperBounds(Type type) {
        if (!(type instanceof WildcardType)) {
            return type;
        }
        Type[] upperBounds = ((WildcardType) type).getUpperBounds();
        return upperBounds.length > 0 ? upperBounds[0] : Object.class;
    }

    public static Class<?> getCollectionItemClass(Type type) {
        if (!(type instanceof ParameterizedType)) {
            return Object.class;
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        if (type2 instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type2).getUpperBounds();
            if (upperBounds.length == 1) {
                type2 = upperBounds[0];
            }
        }
        if (type2 instanceof Class) {
            Class<?> cls = (Class) type2;
            if (Modifier.isPublic(cls.getModifiers())) {
                return cls;
            }
            throw new JSONException("can not create ASMParser");
        }
        throw new JSONException("can not create ASMParser");
    }

    public static Type checkPrimitiveArray(GenericArrayType genericArrayType) {
        Type genericComponentType = genericArrayType.getGenericComponentType();
        String str = "[";
        while (genericComponentType instanceof GenericArrayType) {
            genericComponentType = ((GenericArrayType) genericComponentType).getGenericComponentType();
            str = str + str;
        }
        if (!(genericComponentType instanceof Class)) {
            return genericArrayType;
        }
        Class cls = (Class) genericComponentType;
        if (!cls.isPrimitive()) {
            return genericArrayType;
        }
        try {
            if (cls == Boolean.TYPE) {
                return Class.forName(str + "Z");
            } else if (cls == Character.TYPE) {
                return Class.forName(str + "C");
            } else if (cls == Byte.TYPE) {
                return Class.forName(str + "B");
            } else if (cls == Short.TYPE) {
                return Class.forName(str + ExifInterface.LATITUDE_SOUTH);
            } else if (cls == Integer.TYPE) {
                return Class.forName(str + "I");
            } else if (cls == Long.TYPE) {
                return Class.forName(str + "J");
            } else if (cls == Float.TYPE) {
                return Class.forName(str + "F");
            } else if (cls != Double.TYPE) {
                return genericArrayType;
            } else {
                return Class.forName(str + "D");
            }
        } catch (ClassNotFoundException unused) {
            return genericArrayType;
        }
    }

    public static Collection createCollection(Type type) {
        Type type2;
        Class<?> rawClass = getRawClass(type);
        if (rawClass == AbstractCollection.class || rawClass == Collection.class) {
            return new ArrayList();
        }
        if (rawClass.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (rawClass.isAssignableFrom(LinkedHashSet.class)) {
            return new LinkedHashSet();
        }
        if (rawClass.isAssignableFrom(TreeSet.class)) {
            return new TreeSet();
        }
        if (rawClass.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        if (rawClass.isAssignableFrom(EnumSet.class)) {
            if (type instanceof ParameterizedType) {
                type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                type2 = Object.class;
            }
            return EnumSet.noneOf((Class) type2);
        } else if (rawClass.isAssignableFrom(Queue.class) || rawClass.isAssignableFrom(Deque.class)) {
            return new LinkedList();
        } else {
            try {
                return (Collection) rawClass.newInstance();
            } catch (Exception unused) {
                throw new JSONException("create instance error, class " + rawClass.getName());
            }
        }
    }

    public static Class<?> getRawClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getRawClass(((ParameterizedType) type).getRawType());
        }
        if (type instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            if (upperBounds.length == 1) {
                return getRawClass(upperBounds[0]);
            }
            throw new JSONException("TODO");
        }
        throw new JSONException("TODO");
    }

    public static boolean isProxy(Class<?> cls) {
        for (Class name : cls.getInterfaces()) {
            String name2 = name.getName();
            if (name2.equals("net.sf.cglib.proxy.Factory") || name2.equals("org.springframework.cglib.proxy.Factory") || name2.equals("javassist.util.proxy.ProxyObject") || name2.equals("org.apache.ibatis.javassist.util.proxy.ProxyObject") || name2.equals("org.hibernate.proxy.HibernateProxy")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTransient(Method method) {
        if (method == null) {
            return false;
        }
        if (!transientClassInited) {
            try {
                transientClass = Class.forName("java.beans.Transient");
            } catch (Exception unused) {
            } catch (Throwable th) {
                transientClassInited = true;
                throw th;
            }
            transientClassInited = true;
        }
        Class<? extends Annotation> cls = transientClass;
        if (cls == null || getAnnotation(method, cls) == null) {
            return false;
        }
        return true;
    }

    public static boolean isAnnotationPresentOneToMany(Method method) {
        if (method == null) {
            return false;
        }
        if (class_OneToMany == null && !class_OneToMany_error) {
            try {
                class_OneToMany = Class.forName("javax.persistence.OneToMany");
            } catch (Throwable unused) {
                class_OneToMany_error = true;
            }
        }
        Class<? extends Annotation> cls = class_OneToMany;
        if (cls == null || !method.isAnnotationPresent(cls)) {
            return false;
        }
        return true;
    }

    public static boolean isAnnotationPresentManyToMany(Method method) {
        if (method == null) {
            return false;
        }
        if (class_ManyToMany == null && !class_ManyToMany_error) {
            try {
                class_ManyToMany = Class.forName("javax.persistence.ManyToMany");
            } catch (Throwable unused) {
                class_ManyToMany_error = true;
            }
        }
        if (class_ManyToMany == null) {
            return false;
        }
        if (method.isAnnotationPresent(class_OneToMany) || method.isAnnotationPresent(class_ManyToMany)) {
            return true;
        }
        return false;
    }

    public static boolean isHibernateInitialized(Object obj) {
        if (obj == null) {
            return false;
        }
        if (method_HibernateIsInitialized == null && !method_HibernateIsInitialized_error) {
            try {
                method_HibernateIsInitialized = Class.forName("org.hibernate.Hibernate").getMethod("isInitialized", new Class[]{Object.class});
            } catch (Throwable unused) {
                method_HibernateIsInitialized_error = true;
            }
        }
        Method method = method_HibernateIsInitialized;
        if (method != null) {
            try {
                return ((Boolean) method.invoke((Object) null, new Object[]{obj})).booleanValue();
            } catch (Throwable unused2) {
            }
        }
        return true;
    }

    public static double parseDouble(String str) {
        double d;
        double d2;
        int length = str.length();
        if (length > 10) {
            return Double.parseDouble(str);
        }
        long j = 0;
        boolean z = false;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '-' && i2 == 0) {
                z = true;
            } else if (charAt == '.') {
                if (i != 0) {
                    return Double.parseDouble(str);
                }
                i = (length - i2) - 1;
            } else if (charAt < '0' || charAt > '9') {
                return Double.parseDouble(str);
            } else {
                j = (j * 10) + ((long) (charAt - '0'));
            }
        }
        if (z) {
            j = -j;
        }
        switch (i) {
            case 0:
                return (double) j;
            case 1:
                d = (double) j;
                d2 = 10.0d;
                break;
            case 2:
                d = (double) j;
                d2 = 100.0d;
                break;
            case 3:
                d = (double) j;
                d2 = 1000.0d;
                break;
            case 4:
                d = (double) j;
                d2 = 10000.0d;
                break;
            case 5:
                d = (double) j;
                d2 = 100000.0d;
                break;
            case 6:
                d = (double) j;
                d2 = 1000000.0d;
                break;
            case 7:
                d = (double) j;
                d2 = 1.0E7d;
                break;
            case 8:
                d = (double) j;
                d2 = 1.0E8d;
                break;
            case 9:
                d = (double) j;
                d2 = 1.0E9d;
                break;
            default:
                return Double.parseDouble(str);
        }
        return d / d2;
    }

    public static float parseFloat(String str) {
        float f;
        float f2;
        int length = str.length();
        if (length >= 10) {
            return Float.parseFloat(str);
        }
        long j = 0;
        boolean z = false;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '-' && i2 == 0) {
                z = true;
            } else if (charAt == '.') {
                if (i != 0) {
                    return Float.parseFloat(str);
                }
                i = (length - i2) - 1;
            } else if (charAt < '0' || charAt > '9') {
                return Float.parseFloat(str);
            } else {
                j = (j * 10) + ((long) (charAt - '0'));
            }
        }
        if (z) {
            j = -j;
        }
        switch (i) {
            case 0:
                return (float) j;
            case 1:
                f = (float) j;
                f2 = 10.0f;
                break;
            case 2:
                f = (float) j;
                f2 = 100.0f;
                break;
            case 3:
                f = (float) j;
                f2 = 1000.0f;
                break;
            case 4:
                f = (float) j;
                f2 = 10000.0f;
                break;
            case 5:
                f = (float) j;
                f2 = 100000.0f;
                break;
            case 6:
                f = (float) j;
                f2 = 1000000.0f;
                break;
            case 7:
                f = (float) j;
                f2 = 1.0E7f;
                break;
            case 8:
                f = (float) j;
                f2 = 1.0E8f;
                break;
            case 9:
                f = (float) j;
                f2 = 1.0E9f;
                break;
            default:
                return Float.parseFloat(str);
        }
        return f / f2;
    }

    public static long fnv1a_64_lower(String str) {
        long j = -3750763034362895579L;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!(charAt == '_' || charAt == '-')) {
                if (charAt >= 'A' && charAt <= 'Z') {
                    charAt = (char) (charAt + ' ');
                }
                j = (j ^ ((long) charAt)) * 1099511628211L;
            }
        }
        return j;
    }

    public static long fnv1a_64(String str) {
        long j = -3750763034362895579L;
        for (int i = 0; i < str.length(); i++) {
            j = (j ^ ((long) str.charAt(i))) * 1099511628211L;
        }
        return j;
    }

    public static boolean isKotlin(Class cls) {
        if (kotlin_metadata == null && !kotlin_metadata_error) {
            try {
                kotlin_metadata = Class.forName("kotlin.Metadata");
            } catch (Throwable unused) {
                kotlin_metadata_error = true;
            }
        }
        if (kotlin_metadata == null || !cls.isAnnotationPresent(kotlin_metadata)) {
            return false;
        }
        return true;
    }

    public static Constructor getKotlinConstructor(Constructor[] constructorArr) {
        return getKotlinConstructor(constructorArr, (String[]) null);
    }

    public static Constructor getKotlinConstructor(Constructor[] constructorArr, String[] strArr) {
        Constructor constructor = null;
        for (Constructor constructor2 : constructorArr) {
            Class[] parameterTypes = constructor2.getParameterTypes();
            if ((strArr == null || parameterTypes.length == strArr.length) && ((parameterTypes.length <= 0 || !parameterTypes[parameterTypes.length - 1].getName().equals("kotlin.jvm.internal.DefaultConstructorMarker")) && (constructor == null || constructor.getParameterTypes().length < parameterTypes.length))) {
                constructor = constructor2;
            }
        }
        return constructor;
    }

    public static String[] getKoltinConstructorParameters(Class cls) {
        if (kotlin_kclass_constructor == null && !kotlin_class_klass_error) {
            try {
                kotlin_kclass_constructor = Class.forName("kotlin.reflect.jvm.internal.KClassImpl").getConstructor(new Class[]{Class.class});
            } catch (Throwable unused) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kclass_constructor == null) {
            return null;
        }
        if (kotlin_kclass_getConstructors == null && !kotlin_class_klass_error) {
            try {
                kotlin_kclass_getConstructors = Class.forName("kotlin.reflect.jvm.internal.KClassImpl").getMethod("getConstructors", (Class[]) null);
            } catch (Throwable unused2) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kfunction_getParameters == null && !kotlin_class_klass_error) {
            try {
                kotlin_kfunction_getParameters = Class.forName("kotlin.reflect.KFunction").getMethod("getParameters", (Class[]) null);
            } catch (Throwable unused3) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kparameter_getName == null && !kotlin_class_klass_error) {
            try {
                kotlin_kparameter_getName = Class.forName("kotlin.reflect.KParameter").getMethod("getName", (Class[]) null);
            } catch (Throwable unused4) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_error) {
            return null;
        }
        try {
            Iterator it = ((Iterable) kotlin_kclass_getConstructors.invoke(kotlin_kclass_constructor.newInstance(new Object[]{cls}), (Object[]) null)).iterator();
            Object obj = null;
            while (it.hasNext()) {
                Object next = it.next();
                List list = (List) kotlin_kfunction_getParameters.invoke(next, (Object[]) null);
                if (obj == null || list.size() != 0) {
                    obj = next;
                }
                it.hasNext();
            }
            if (obj == null) {
                return null;
            }
            List list2 = (List) kotlin_kfunction_getParameters.invoke(obj, (Object[]) null);
            String[] strArr = new String[list2.size()];
            for (int i = 0; i < list2.size(); i++) {
                strArr[i] = (String) kotlin_kparameter_getName.invoke(list2.get(i), (Object[]) null);
            }
            return strArr;
        } catch (Throwable th) {
            th.printStackTrace();
            kotlin_error = true;
            return null;
        }
    }

    private static boolean isKotlinIgnore(Class cls, String str) {
        if (kotlinIgnores == null && !kotlinIgnores_error) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put(Class.forName("kotlin.ranges.CharRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.IntRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.LongRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.ClosedFloatRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.ClosedDoubleRange"), new String[]{"getEndInclusive", "isEmpty"});
                kotlinIgnores = hashMap;
            } catch (Throwable unused) {
                kotlinIgnores_error = true;
            }
        }
        if (kotlinIgnores == null) {
            return false;
        }
        String[] strArr = kotlinIgnores.get(cls);
        if (strArr == null || Arrays.binarySearch(strArr, str) < 0) {
            return false;
        }
        return true;
    }

    public static <A extends Annotation> A getAnnotation(Class<?> cls, Class<A> cls2) {
        A annotation = cls.getAnnotation(cls2);
        Type mixInAnnotations = JSON.getMixInAnnotations(cls);
        Class cls3 = mixInAnnotations instanceof Class ? (Class) mixInAnnotations : null;
        if (cls3 != null) {
            A annotation2 = cls3.getAnnotation(cls2);
            if (annotation2 == null && cls3.getAnnotations().length > 0) {
                for (Annotation annotationType : cls3.getAnnotations()) {
                    annotation2 = annotationType.annotationType().getAnnotation(cls2);
                    if (annotation2 != null) {
                        break;
                    }
                }
            }
            if (annotation2 != null) {
                return annotation2;
            }
        }
        if (annotation == null && cls.getAnnotations().length > 0) {
            for (Annotation annotationType2 : cls.getAnnotations()) {
                annotation = annotationType2.annotationType().getAnnotation(cls2);
                if (annotation != null) {
                    break;
                }
            }
        }
        return annotation;
    }

    public static <A extends Annotation> A getAnnotation(Field field, Class<A> cls) {
        A annotation;
        A annotation2 = field.getAnnotation(cls);
        Type mixInAnnotations = JSON.getMixInAnnotations(field.getDeclaringClass());
        Field field2 = null;
        Class<? super Object> cls2 = mixInAnnotations instanceof Class ? (Class) mixInAnnotations : null;
        if (cls2 != null) {
            String name = field.getName();
            while (true) {
                if (cls2 == null || cls2 == Object.class) {
                    break;
                }
                try {
                    field2 = cls2.getDeclaredField(name);
                    break;
                } catch (NoSuchFieldException unused) {
                    cls2 = cls2.getSuperclass();
                }
            }
            if (!(field2 == null || (annotation = field2.getAnnotation(cls)) == null)) {
                return annotation;
            }
        }
        return annotation2;
    }

    public static <A extends Annotation> A getAnnotation(Method method, Class<A> cls) {
        A annotation;
        A annotation2 = method.getAnnotation(cls);
        Type mixInAnnotations = JSON.getMixInAnnotations(method.getDeclaringClass());
        Method method2 = null;
        Class<? super Object> cls2 = mixInAnnotations instanceof Class ? (Class) mixInAnnotations : null;
        if (cls2 != null) {
            String name = method.getName();
            Class[] parameterTypes = method.getParameterTypes();
            while (true) {
                if (cls2 == null || cls2 == Object.class) {
                    break;
                }
                try {
                    method2 = cls2.getDeclaredMethod(name, parameterTypes);
                    break;
                } catch (NoSuchMethodException unused) {
                    cls2 = cls2.getSuperclass();
                }
            }
            if (!(method2 == null || (annotation = method2.getAnnotation(cls)) == null)) {
                return annotation;
            }
        }
        return annotation2;
    }

    public static Annotation[][] getParameterAnnotations(Method method) {
        Annotation[][] parameterAnnotations;
        Annotation[][] parameterAnnotations2 = method.getParameterAnnotations();
        Type mixInAnnotations = JSON.getMixInAnnotations(method.getDeclaringClass());
        Method method2 = null;
        Class<? super Object> cls = mixInAnnotations instanceof Class ? (Class) mixInAnnotations : null;
        if (cls != null) {
            String name = method.getName();
            Class[] parameterTypes = method.getParameterTypes();
            while (true) {
                if (cls == null || cls == Object.class) {
                    break;
                }
                try {
                    method2 = cls.getDeclaredMethod(name, parameterTypes);
                    break;
                } catch (NoSuchMethodException unused) {
                    cls = cls.getSuperclass();
                }
            }
            if (!(method2 == null || (parameterAnnotations = method2.getParameterAnnotations()) == null)) {
                return parameterAnnotations;
            }
        }
        return parameterAnnotations2;
    }

    public static Annotation[][] getParameterAnnotations(Constructor constructor) {
        Annotation[][] parameterAnnotations;
        Constructor<Object> declaredConstructor;
        Annotation[][] parameterAnnotations2 = constructor.getParameterAnnotations();
        Type mixInAnnotations = JSON.getMixInAnnotations(constructor.getDeclaringClass());
        Constructor<Object> constructor2 = null;
        Class<Object> cls = mixInAnnotations instanceof Class ? (Class) mixInAnnotations : null;
        if (cls != null) {
            Class[] parameterTypes = constructor.getParameterTypes();
            ArrayList arrayList = new ArrayList(2);
            for (Class<?> enclosingClass = cls.getEnclosingClass(); enclosingClass != null; enclosingClass = enclosingClass.getEnclosingClass()) {
                arrayList.add(enclosingClass);
            }
            int size = arrayList.size();
            Class<? super Object> cls2 = cls;
            while (true) {
                if (cls2 != null && cls2 != Object.class) {
                    if (size == 0) {
                        declaredConstructor = cls.getDeclaredConstructor(parameterTypes);
                        break;
                    }
                    try {
                        Class[] clsArr = new Class[(parameterTypes.length + size)];
                        System.arraycopy(parameterTypes, 0, clsArr, size, parameterTypes.length);
                        for (int i = size; i > 0; i--) {
                            int i2 = i - 1;
                            clsArr[i2] = (Class) arrayList.get(i2);
                        }
                        declaredConstructor = cls.getDeclaredConstructor(clsArr);
                    } catch (NoSuchMethodException unused) {
                        size--;
                        cls2 = cls2.getSuperclass();
                    }
                } else {
                    break;
                }
            }
            constructor2 = declaredConstructor;
            if (!(constructor2 == null || (parameterAnnotations = constructor2.getParameterAnnotations()) == null)) {
                return parameterAnnotations;
            }
        }
        return parameterAnnotations2;
    }

    public static boolean isJacksonCreator(Method method) {
        if (method == null) {
            return false;
        }
        if (class_JacksonCreator == null && !class_JacksonCreator_error) {
            try {
                class_JacksonCreator = Class.forName("com.fasterxml.jackson.annotation.JsonCreator");
            } catch (Throwable unused) {
                class_JacksonCreator_error = true;
            }
        }
        Class<? extends Annotation> cls = class_JacksonCreator;
        if (cls == null || !method.isAnnotationPresent(cls)) {
            return false;
        }
        return true;
    }

    public static LocalDateTime castToLocalDateTime(Object obj, String str) {
        if (obj == null) {
            return null;
        }
        if (str == null) {
            str = "yyyy-MM-dd HH:mm:ss";
        }
        return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m((CharSequence) obj.toString(), DateTimeFormatter.ofPattern(str));
    }
}
