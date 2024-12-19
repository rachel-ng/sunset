package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory;
import com.alibaba.fastjson.parser.deserializer.ArrayListTypeFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.EnumDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JSONPDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.MapDeserializer;
import com.alibaba.fastjson.parser.deserializer.NumberDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.SqlDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.StackTraceElementDeserializer;
import com.alibaba.fastjson.parser.deserializer.TimeDeserializer;
import com.alibaba.fastjson.serializer.AtomicCodec;
import com.alibaba.fastjson.serializer.BigDecimalCodec;
import com.alibaba.fastjson.serializer.BigIntegerCodec;
import com.alibaba.fastjson.serializer.BooleanCodec;
import com.alibaba.fastjson.serializer.CalendarCodec;
import com.alibaba.fastjson.serializer.CharArrayCodec;
import com.alibaba.fastjson.serializer.CharacterCodec;
import com.alibaba.fastjson.serializer.CollectionCodec;
import com.alibaba.fastjson.serializer.DateCodec;
import com.alibaba.fastjson.serializer.FloatCodec;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.MiscCodec;
import com.alibaba.fastjson.serializer.ReferenceCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.spi.Module;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.AccessControlException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import javax.xml.datatype.XMLGregorianCalendar;

public class ParserConfig {
    public static final String AUTOTYPE_ACCEPT = "fastjson.parser.autoTypeAccept";
    public static final String AUTOTYPE_SUPPORT_PROPERTY = "fastjson.parser.autoTypeSupport";
    public static final boolean AUTO_SUPPORT = "true".equals(IOUtils.getStringProperty(AUTOTYPE_SUPPORT_PROPERTY));
    private static final String[] AUTO_TYPE_ACCEPT_LIST;
    public static final String[] DENYS = splitItemsFormProperty(IOUtils.getStringProperty(DENY_PROPERTY));
    public static final String[] DENYS_INTERNAL = splitItemsFormProperty(IOUtils.getStringProperty(DENY_PROPERTY_INTERNAL));
    public static final String DENY_PROPERTY = "fastjson.parser.deny";
    public static final String DENY_PROPERTY_INTERNAL = "fastjson.parser.deny.internal";
    private static final long[] INTERNAL_WHITELIST_HASHCODES = {-9013707057526259810L, -8773806119481270567L, -8421588593326113468L, -8070393259084821111L, -7858127399773263546L, -7043543676283957292L, -6976602508726000783L, -6293031534589903644L, -6081111809668363619L, -5779433778261875721L, -5399450433995651784L, -4540135604787511831L, -4207865850564917696L, -3950343444501679205L, -3714900953609113456L, -3393714734093696063L, -3378497329992063044L, -2631228350337215662L, -2551988546877734201L, -2473987886800209058L, -2265617974881722705L, -1759511109484434299L, -1477946458560579955L, -816725787720647462L, -520183782617964618L, 59775428743665658L, 484499585846206473L, 532945107123976213L, 711449177569584898L, 829148494126372070L, 956883420092542580L, 1233162291719202522L, 1696465274354442213L, 1863557081881630420L, 2238472697200138595L, 2380202963256720577L, 2643099543618286743L, 2793877891138577121L, 3804572268889088203L, 4567982875926242015L, 4784070066737926537L, 4960004821520561233L, 5348524593377618456L, 5454920836284873808L, 5695987590363189151L, 6073645722991901167L, 6114875255374330593L, 6137737446243999215L, 6160752908990493848L, 6939315124833099497L, 7048426940343117278L, 7267793227937552092L, 8331868837379820532L, 8357451534615459155L, 8890227807433646566L, 9166532985682478006L, 9215131087512669423L};
    public static final boolean SAFE_MODE = "true".equals(IOUtils.getStringProperty(SAFE_MODE_PROPERTY));
    public static final String SAFE_MODE_PROPERTY = "fastjson.parser.safeMode";
    private static boolean awtError = false;
    public static ParserConfig global = new ParserConfig();
    private static boolean guavaError = false;
    private static boolean jdk8Error = false;
    private static boolean jodaError = false;
    private long[] acceptHashCodes;
    private boolean asmEnable;
    protected ASMDeserializerFactory asmFactory;
    private volatile List<AutoTypeCheckHandler> autoTypeCheckHandlers;
    private boolean autoTypeSupport;
    public boolean compatibleWithJavaBean;
    protected ClassLoader defaultClassLoader;
    private long[] denyHashCodes;
    private final IdentityHashMap<Type, ObjectDeserializer> deserializers;
    public final boolean fieldBased;
    private long[] internalDenyHashCodes;
    private boolean jacksonCompatible;
    private final IdentityHashMap<Type, IdentityHashMap<Type, ObjectDeserializer>> mixInDeserializers;
    private List<Module> modules;
    public PropertyNamingStrategy propertyNamingStrategy;
    private boolean safeMode;
    public final SymbolTable symbolTable;
    private final ConcurrentMap<String, Class<?>> typeMapping;

    public interface AutoTypeCheckHandler {
        Class<?> handler(String str, Class<?> cls, int i);
    }

    static {
        String[] splitItemsFormProperty = splitItemsFormProperty(IOUtils.getStringProperty(AUTOTYPE_ACCEPT));
        if (splitItemsFormProperty == null) {
            splitItemsFormProperty = new String[0];
        }
        AUTO_TYPE_ACCEPT_LIST = splitItemsFormProperty;
    }

    public static ParserConfig getGlobalInstance() {
        return global;
    }

    public ParserConfig() {
        this(false);
    }

    public ParserConfig(boolean z) {
        this((ASMDeserializerFactory) null, (ClassLoader) null, z);
    }

    public ParserConfig(ClassLoader classLoader) {
        this((ASMDeserializerFactory) null, classLoader, false);
    }

    public ParserConfig(ASMDeserializerFactory aSMDeserializerFactory) {
        this(aSMDeserializerFactory, (ClassLoader) null, false);
    }

    private ParserConfig(ASMDeserializerFactory aSMDeserializerFactory, ClassLoader classLoader, boolean z) {
        this.deserializers = new IdentityHashMap<>();
        this.mixInDeserializers = new IdentityHashMap<>(16);
        this.typeMapping = new ConcurrentHashMap(16, 0.75f, 1);
        this.asmEnable = !ASMUtils.IS_ANDROID;
        this.symbolTable = new SymbolTable(4096);
        this.autoTypeSupport = AUTO_SUPPORT;
        this.jacksonCompatible = false;
        this.compatibleWithJavaBean = TypeUtils.compatibleWithJavaBean;
        this.modules = new ArrayList();
        this.safeMode = SAFE_MODE;
        this.denyHashCodes = new long[]{-9164606388214699518L, -8720046426850100497L, -8649961213709896794L, -8165637398350707645L, -8109300701639721088L, -7966123100503199569L, -7921218830998286408L, -7775351613326101303L, -7768608037458185275L, -7766605818834748097L, -6835437086156813536L, -6316154655839304624L, -6179589609550493385L, -6025144546313590215L, -5939269048541779808L, -5885964883385605994L, -5764804792063216819L, -5472097725414717105L, -5194641081268104286L, -4837536971810737970L, -4608341446948126581L, -4438775680185074100L, -4082057040235125754L, -3975378478825053783L, -3935185854875733362L, -3319207949486691020L, -3077205613010077203L, -3053747177772160511L, -2825378362173150292L, -2439930098895578154L, -2378990704010641148L, -2364987994247679115L, -2262244760619952081L, -2192804397019347313L, -2095516571388852610L, -1872417015366588117L, -1650485814983027158L, -1589194880214235129L, -905177026366752536L, -831789045734283466L, -582813228520337988L, -254670111376247151L, -190281065685395680L, -26639035867733124L, -9822483067882491L, 4750336058574309L, 33238344207745342L, 218512992947536312L, 313864100207897507L, 386461436234701831L, 823641066473609950L, 1073634739308289776L, 1153291637701043748L, 1203232727967308606L, 1459860845934817624L, 1502845958873959152L, 1534439610567445754L, 1698504441317515818L, 1818089308493370394L, 2078113382421334967L, 2164696723069287854L, 2653453629929770569L, 2660670623866180977L, 2731823439467737506L, 2836431254737891113L, 3089451460101527857L, 3114862868117605599L, 3256258368248066264L, 3547627781654598988L, 3637939656440441093L, 3688179072722109200L, 3718352661124136681L, 3730752432285826863L, 3794316665763266033L, 4046190361520671643L, 4147696707147271408L, 4254584350247334433L, 4814658433570175913L, 4841947709850912914L, 4904007817188630457L, 5100336081510080343L, 5274044858141538265L, 5347909877633654828L, 5450448828334921485L, 5474268165959054640L, 5596129856135573697L, 5688200883751798389L, 5751393439502795295L, 5944107969236155580L, 6007332606592876737L, 6280357960959217660L, 6456855723474196908L, 6511035576063254270L, 6534946468240507089L, 6734240326434096246L, 6742705432718011780L, 6854854816081053523L, 7123326897294507060L, 7179336928365889465L, 7375862386996623731L, 7442624256860549330L, 7658177784286215602L, 8055461369741094911L, 8389032537095247355L, 8409640769019589119L, 8488266005336625107L, 8537233257283452655L, 8838294710098435315L, 9140390920032557669L, 9140416208800006522L};
        long[] jArr = new long[AUTO_TYPE_ACCEPT_LIST.length];
        int i = 0;
        while (true) {
            String[] strArr = AUTO_TYPE_ACCEPT_LIST;
            if (i >= strArr.length) {
                break;
            }
            jArr[i] = TypeUtils.fnv1a_64(strArr[i]);
            i++;
        }
        Arrays.sort(jArr);
        this.acceptHashCodes = jArr;
        this.fieldBased = z;
        if (aSMDeserializerFactory == null && !ASMUtils.IS_ANDROID) {
            if (classLoader == null) {
                try {
                    aSMDeserializerFactory = new ASMDeserializerFactory(new ASMClassLoader());
                } catch (ExceptionInInitializerError | NoClassDefFoundError | AccessControlException unused) {
                }
            } else {
                aSMDeserializerFactory = new ASMDeserializerFactory(classLoader);
            }
        }
        this.asmFactory = aSMDeserializerFactory;
        if (aSMDeserializerFactory == null) {
            this.asmEnable = false;
        }
        initDeserializers();
        addItemsToDeny(DENYS);
        addItemsToDeny0(DENYS_INTERNAL);
        addItemsToAccept(AUTO_TYPE_ACCEPT_LIST);
    }

    private void initDeserializers() {
        this.deserializers.put(SimpleDateFormat.class, MiscCodec.instance);
        this.deserializers.put(Timestamp.class, SqlDateDeserializer.instance_timestamp);
        this.deserializers.put(Date.class, SqlDateDeserializer.instance);
        this.deserializers.put(Time.class, TimeDeserializer.instance);
        this.deserializers.put(java.util.Date.class, DateCodec.instance);
        this.deserializers.put(Calendar.class, CalendarCodec.instance);
        this.deserializers.put(XMLGregorianCalendar.class, CalendarCodec.instance);
        this.deserializers.put(JSONObject.class, MapDeserializer.instance);
        this.deserializers.put(JSONArray.class, CollectionCodec.instance);
        this.deserializers.put(Map.class, MapDeserializer.instance);
        this.deserializers.put(HashMap.class, MapDeserializer.instance);
        this.deserializers.put(LinkedHashMap.class, MapDeserializer.instance);
        this.deserializers.put(TreeMap.class, MapDeserializer.instance);
        this.deserializers.put(ConcurrentMap.class, MapDeserializer.instance);
        this.deserializers.put(ConcurrentHashMap.class, MapDeserializer.instance);
        this.deserializers.put(Collection.class, CollectionCodec.instance);
        this.deserializers.put(List.class, CollectionCodec.instance);
        this.deserializers.put(ArrayList.class, CollectionCodec.instance);
        this.deserializers.put(Object.class, JavaObjectDeserializer.instance);
        this.deserializers.put(String.class, StringCodec.instance);
        this.deserializers.put(StringBuffer.class, StringCodec.instance);
        this.deserializers.put(StringBuilder.class, StringCodec.instance);
        this.deserializers.put(Character.TYPE, CharacterCodec.instance);
        this.deserializers.put(Character.class, CharacterCodec.instance);
        this.deserializers.put(Byte.TYPE, NumberDeserializer.instance);
        this.deserializers.put(Byte.class, NumberDeserializer.instance);
        this.deserializers.put(Short.TYPE, NumberDeserializer.instance);
        this.deserializers.put(Short.class, NumberDeserializer.instance);
        this.deserializers.put(Integer.TYPE, IntegerCodec.instance);
        this.deserializers.put(Integer.class, IntegerCodec.instance);
        this.deserializers.put(Long.TYPE, LongCodec.instance);
        this.deserializers.put(Long.class, LongCodec.instance);
        this.deserializers.put(BigInteger.class, BigIntegerCodec.instance);
        this.deserializers.put(BigDecimal.class, BigDecimalCodec.instance);
        this.deserializers.put(Float.TYPE, FloatCodec.instance);
        this.deserializers.put(Float.class, FloatCodec.instance);
        this.deserializers.put(Double.TYPE, NumberDeserializer.instance);
        this.deserializers.put(Double.class, NumberDeserializer.instance);
        this.deserializers.put(Boolean.TYPE, BooleanCodec.instance);
        this.deserializers.put(Boolean.class, BooleanCodec.instance);
        this.deserializers.put(Class.class, MiscCodec.instance);
        this.deserializers.put(char[].class, new CharArrayCodec());
        this.deserializers.put(AtomicBoolean.class, BooleanCodec.instance);
        this.deserializers.put(AtomicInteger.class, IntegerCodec.instance);
        this.deserializers.put(AtomicLong.class, LongCodec.instance);
        this.deserializers.put(AtomicReference.class, ReferenceCodec.instance);
        this.deserializers.put(WeakReference.class, ReferenceCodec.instance);
        this.deserializers.put(SoftReference.class, ReferenceCodec.instance);
        this.deserializers.put(UUID.class, MiscCodec.instance);
        this.deserializers.put(TimeZone.class, MiscCodec.instance);
        this.deserializers.put(Locale.class, MiscCodec.instance);
        this.deserializers.put(Currency.class, MiscCodec.instance);
        this.deserializers.put(Inet4Address.class, MiscCodec.instance);
        this.deserializers.put(Inet6Address.class, MiscCodec.instance);
        this.deserializers.put(InetSocketAddress.class, MiscCodec.instance);
        this.deserializers.put(File.class, MiscCodec.instance);
        this.deserializers.put(URI.class, MiscCodec.instance);
        this.deserializers.put(URL.class, MiscCodec.instance);
        this.deserializers.put(Pattern.class, MiscCodec.instance);
        this.deserializers.put(Charset.class, MiscCodec.instance);
        this.deserializers.put(JSONPath.class, MiscCodec.instance);
        this.deserializers.put(Number.class, NumberDeserializer.instance);
        this.deserializers.put(AtomicIntegerArray.class, AtomicCodec.instance);
        this.deserializers.put(AtomicLongArray.class, AtomicCodec.instance);
        this.deserializers.put(StackTraceElement.class, StackTraceElementDeserializer.instance);
        this.deserializers.put(Serializable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(Cloneable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(Comparable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(Closeable.class, JavaObjectDeserializer.instance);
        this.deserializers.put(JSONPObject.class, new JSONPDeserializer());
    }

    private static String[] splitItemsFormProperty(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        return str.split(",");
    }

    public void configFromPropety(Properties properties) {
        addItemsToDeny(splitItemsFormProperty(properties.getProperty(DENY_PROPERTY)));
        addItemsToAccept(splitItemsFormProperty(properties.getProperty(AUTOTYPE_ACCEPT)));
        String property = properties.getProperty(AUTOTYPE_SUPPORT_PROPERTY);
        if ("true".equals(property)) {
            this.autoTypeSupport = true;
        } else if ("false".equals(property)) {
            this.autoTypeSupport = false;
        }
    }

    private void addItemsToDeny0(String[] strArr) {
        if (strArr != null) {
            for (String addDenyInternal : strArr) {
                addDenyInternal(addDenyInternal);
            }
        }
    }

    private void addItemsToDeny(String[] strArr) {
        if (strArr != null) {
            for (String addDeny : strArr) {
                addDeny(addDeny);
            }
        }
    }

    private void addItemsToAccept(String[] strArr) {
        if (strArr != null) {
            for (String addAccept : strArr) {
                addAccept(addAccept);
            }
        }
    }

    public boolean isSafeMode() {
        return this.safeMode;
    }

    public void setSafeMode(boolean z) {
        this.safeMode = z;
    }

    public boolean isAutoTypeSupport() {
        return this.autoTypeSupport;
    }

    public void setAutoTypeSupport(boolean z) {
        this.autoTypeSupport = z;
    }

    public boolean isAsmEnable() {
        return this.asmEnable;
    }

    public void setAsmEnable(boolean z) {
        this.asmEnable = z;
    }

    public IdentityHashMap<Type, ObjectDeserializer> getDerializers() {
        return this.deserializers;
    }

    public IdentityHashMap<Type, ObjectDeserializer> getDeserializers() {
        return this.deserializers;
    }

    public ObjectDeserializer getDeserializer(Type type) {
        ObjectDeserializer objectDeserializer = get(type);
        if (objectDeserializer != null) {
            return objectDeserializer;
        }
        if (type instanceof Class) {
            return getDeserializer((Class) type, type);
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            if (rawType instanceof Class) {
                return getDeserializer((Class) rawType, type);
            }
            return getDeserializer(rawType);
        }
        if (type instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            if (upperBounds.length == 1) {
                return getDeserializer(upperBounds[0]);
            }
        }
        return JavaObjectDeserializer.instance;
    }

    /* JADX WARNING: type inference failed for: r24v0, types: [java.lang.reflect.Type] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.alibaba.fastjson.parser.deserializer.ObjectDeserializer getDeserializer(java.lang.Class<?> r23, java.lang.reflect.Type r24) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            r2 = r24
            java.lang.String r3 = "java.util.Optional"
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r4 = r0.get(r2)
            if (r4 == 0) goto L_0x000f
            return r4
        L_0x000f:
            if (r2 != 0) goto L_0x0012
            r2 = r1
        L_0x0012:
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r4 = r0.get(r2)
            if (r4 == 0) goto L_0x0019
            return r4
        L_0x0019:
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r5 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r5 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.Class<?>) r1, r5)
            com.alibaba.fastjson.annotation.JSONType r5 = (com.alibaba.fastjson.annotation.JSONType) r5
            if (r5 == 0) goto L_0x0030
            java.lang.Class r5 = r5.mappingTo()
            java.lang.Class<java.lang.Void> r6 = java.lang.Void.class
            if (r5 == r6) goto L_0x0030
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r1 = r0.getDeserializer(r5, r5)
            return r1
        L_0x0030:
            boolean r5 = r2 instanceof java.lang.reflect.WildcardType
            if (r5 != 0) goto L_0x003c
            boolean r5 = r2 instanceof java.lang.reflect.TypeVariable
            if (r5 != 0) goto L_0x003c
            boolean r5 = r2 instanceof java.lang.reflect.ParameterizedType
            if (r5 == 0) goto L_0x0040
        L_0x003c:
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r4 = r22.get(r23)
        L_0x0040:
            if (r4 == 0) goto L_0x0043
            return r4
        L_0x0043:
            java.util.List<com.alibaba.fastjson.spi.Module> r5 = r0.modules
            java.util.Iterator r5 = r5.iterator()
        L_0x0049:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x005f
            java.lang.Object r4 = r5.next()
            com.alibaba.fastjson.spi.Module r4 = (com.alibaba.fastjson.spi.Module) r4
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r4 = r4.createDeserializer(r0, r1)
            if (r4 == 0) goto L_0x0049
            r0.putDeserializer(r2, r4)
            return r4
        L_0x005f:
            java.lang.String r5 = r23.getName()
            r6 = 36
            r7 = 46
            java.lang.String r5 = r5.replace(r6, r7)
            java.lang.String r6 = "java.awt."
            boolean r6 = r5.startsWith(r6)
            r7 = 4
            r8 = 0
            r9 = 1
            if (r6 == 0) goto L_0x00a8
            boolean r6 = com.alibaba.fastjson.serializer.AwtCodec.support(r23)
            if (r6 == 0) goto L_0x00a8
            boolean r6 = awtError
            if (r6 != 0) goto L_0x00a8
            java.lang.String r4 = "java.awt.Rectangle"
            java.lang.String r6 = "java.awt.Color"
            java.lang.String r10 = "java.awt.Point"
            java.lang.String r11 = "java.awt.Font"
            java.lang.String[] r4 = new java.lang.String[]{r10, r11, r4, r6}
            r6 = r8
        L_0x008d:
            if (r6 >= r7) goto L_0x00a6
            r10 = r4[r6]     // Catch:{ all -> 0x00a4 }
            boolean r11 = r10.equals(r5)     // Catch:{ all -> 0x00a4 }
            if (r11 == 0) goto L_0x00a1
            java.lang.Class r4 = java.lang.Class.forName(r10)     // Catch:{ all -> 0x00a4 }
            com.alibaba.fastjson.serializer.AwtCodec r6 = com.alibaba.fastjson.serializer.AwtCodec.instance     // Catch:{ all -> 0x00a4 }
            r0.putDeserializer(r4, r6)     // Catch:{ all -> 0x00a4 }
            return r6
        L_0x00a1:
            int r6 = r6 + 1
            goto L_0x008d
        L_0x00a4:
            awtError = r9
        L_0x00a6:
            com.alibaba.fastjson.serializer.AwtCodec r4 = com.alibaba.fastjson.serializer.AwtCodec.instance
        L_0x00a8:
            boolean r6 = jdk8Error
            if (r6 != 0) goto L_0x0114
            java.lang.String r6 = "java.time."
            boolean r6 = r5.startsWith(r6)     // Catch:{ all -> 0x0112 }
            if (r6 == 0) goto L_0x00ea
            java.lang.String r10 = "java.time.LocalDateTime"
            java.lang.String r11 = "java.time.LocalDate"
            java.lang.String r12 = "java.time.LocalTime"
            java.lang.String r13 = "java.time.ZonedDateTime"
            java.lang.String r14 = "java.time.OffsetDateTime"
            java.lang.String r15 = "java.time.OffsetTime"
            java.lang.String r16 = "java.time.ZoneOffset"
            java.lang.String r17 = "java.time.ZoneRegion"
            java.lang.String r18 = "java.time.ZoneId"
            java.lang.String r19 = "java.time.Period"
            java.lang.String r20 = "java.time.Duration"
            java.lang.String r21 = "java.time.Instant"
            java.lang.String[] r3 = new java.lang.String[]{r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21}     // Catch:{ all -> 0x0112 }
            r6 = r8
        L_0x00d1:
            r7 = 12
            if (r6 >= r7) goto L_0x0114
            r7 = r3[r6]     // Catch:{ all -> 0x0112 }
            boolean r10 = r7.equals(r5)     // Catch:{ all -> 0x0112 }
            if (r10 == 0) goto L_0x00e7
            java.lang.Class r3 = java.lang.Class.forName(r7)     // Catch:{ all -> 0x0112 }
            com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec r4 = com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec.instance     // Catch:{ all -> 0x0112 }
            r0.putDeserializer(r3, r4)     // Catch:{ all -> 0x0112 }
            return r4
        L_0x00e7:
            int r6 = r6 + 1
            goto L_0x00d1
        L_0x00ea:
            boolean r6 = r5.startsWith(r3)     // Catch:{ all -> 0x0112 }
            if (r6 == 0) goto L_0x0114
            java.lang.String r6 = "java.util.OptionalDouble"
            java.lang.String r10 = "java.util.OptionalInt"
            java.lang.String r11 = "java.util.OptionalLong"
            java.lang.String[] r3 = new java.lang.String[]{r3, r6, r10, r11}     // Catch:{ all -> 0x0112 }
            r6 = r8
        L_0x00fb:
            if (r6 >= r7) goto L_0x0114
            r10 = r3[r6]     // Catch:{ all -> 0x0112 }
            boolean r11 = r10.equals(r5)     // Catch:{ all -> 0x0112 }
            if (r11 == 0) goto L_0x010f
            java.lang.Class r3 = java.lang.Class.forName(r10)     // Catch:{ all -> 0x0112 }
            com.alibaba.fastjson.parser.deserializer.OptionalCodec r4 = com.alibaba.fastjson.parser.deserializer.OptionalCodec.instance     // Catch:{ all -> 0x0112 }
            r0.putDeserializer(r3, r4)     // Catch:{ all -> 0x0112 }
            return r4
        L_0x010f:
            int r6 = r6 + 1
            goto L_0x00fb
        L_0x0112:
            jdk8Error = r9
        L_0x0114:
            boolean r3 = jodaError
            if (r3 != 0) goto L_0x0152
            java.lang.String r3 = "org.joda.time."
            boolean r3 = r5.startsWith(r3)     // Catch:{ all -> 0x0150 }
            if (r3 == 0) goto L_0x0152
            java.lang.String r10 = "org.joda.time.DateTime"
            java.lang.String r11 = "org.joda.time.LocalDate"
            java.lang.String r12 = "org.joda.time.LocalDateTime"
            java.lang.String r13 = "org.joda.time.LocalTime"
            java.lang.String r14 = "org.joda.time.Instant"
            java.lang.String r15 = "org.joda.time.Period"
            java.lang.String r16 = "org.joda.time.Duration"
            java.lang.String r17 = "org.joda.time.DateTimeZone"
            java.lang.String r18 = "org.joda.time.format.DateTimeFormatter"
            java.lang.String[] r3 = new java.lang.String[]{r10, r11, r12, r13, r14, r15, r16, r17, r18}     // Catch:{ all -> 0x0150 }
            r6 = r8
        L_0x0137:
            r7 = 9
            if (r6 >= r7) goto L_0x0152
            r7 = r3[r6]     // Catch:{ all -> 0x0150 }
            boolean r10 = r7.equals(r5)     // Catch:{ all -> 0x0150 }
            if (r10 == 0) goto L_0x014d
            java.lang.Class r3 = java.lang.Class.forName(r7)     // Catch:{ all -> 0x0150 }
            com.alibaba.fastjson.serializer.JodaCodec r4 = com.alibaba.fastjson.serializer.JodaCodec.instance     // Catch:{ all -> 0x0150 }
            r0.putDeserializer(r3, r4)     // Catch:{ all -> 0x0150 }
            return r4
        L_0x014d:
            int r6 = r6 + 1
            goto L_0x0137
        L_0x0150:
            jodaError = r9
        L_0x0152:
            boolean r3 = guavaError
            if (r3 != 0) goto L_0x0187
            java.lang.String r3 = "com.google.common.collect."
            boolean r3 = r5.startsWith(r3)
            if (r3 == 0) goto L_0x0187
            java.lang.String r3 = "com.google.common.collect.HashMultimap"
            java.lang.String r6 = "com.google.common.collect.LinkedListMultimap"
            java.lang.String r7 = "com.google.common.collect.LinkedHashMultimap"
            java.lang.String r10 = "com.google.common.collect.ArrayListMultimap"
            java.lang.String r11 = "com.google.common.collect.TreeMultimap"
            java.lang.String[] r3 = new java.lang.String[]{r3, r6, r7, r10, r11}     // Catch:{ ClassNotFoundException -> 0x0185 }
            r6 = r8
        L_0x016d:
            r7 = 5
            if (r6 >= r7) goto L_0x0187
            r7 = r3[r6]     // Catch:{ ClassNotFoundException -> 0x0185 }
            boolean r10 = r7.equals(r5)     // Catch:{ ClassNotFoundException -> 0x0185 }
            if (r10 == 0) goto L_0x0182
            java.lang.Class r3 = java.lang.Class.forName(r7)     // Catch:{ ClassNotFoundException -> 0x0185 }
            com.alibaba.fastjson.serializer.GuavaCodec r4 = com.alibaba.fastjson.serializer.GuavaCodec.instance     // Catch:{ ClassNotFoundException -> 0x0185 }
            r0.putDeserializer(r3, r4)     // Catch:{ ClassNotFoundException -> 0x0185 }
            return r4
        L_0x0182:
            int r6 = r6 + 1
            goto L_0x016d
        L_0x0185:
            guavaError = r9
        L_0x0187:
            java.lang.String r3 = "java.nio.ByteBuffer"
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x0194
            com.alibaba.fastjson.serializer.ByteBufferCodec r4 = com.alibaba.fastjson.serializer.ByteBufferCodec.instance
            r0.putDeserializer(r1, r4)
        L_0x0194:
            java.lang.String r3 = "java.nio.file.Path"
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x01a1
            com.alibaba.fastjson.serializer.MiscCodec r4 = com.alibaba.fastjson.serializer.MiscCodec.instance
            r0.putDeserializer(r1, r4)
        L_0x01a1:
            java.lang.Class<java.util.Map$Entry> r3 = java.util.Map.Entry.class
            if (r1 != r3) goto L_0x01aa
            com.alibaba.fastjson.serializer.MiscCodec r4 = com.alibaba.fastjson.serializer.MiscCodec.instance
            r0.putDeserializer(r1, r4)
        L_0x01aa:
            java.lang.String r3 = "org.javamoney.moneta.Money"
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x01b7
            com.alibaba.fastjson.support.moneta.MonetaCodec r4 = com.alibaba.fastjson.support.moneta.MonetaCodec.instance
            r0.putDeserializer(r1, r4)
        L_0x01b7:
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            java.lang.ClassLoader r3 = r3.getContextClassLoader()
            java.lang.Class<com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer> r5 = com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer.class
            java.util.Set r3 = com.alibaba.fastjson.util.ServiceLoader.load(r5, (java.lang.ClassLoader) r3)     // Catch:{ Exception -> 0x01ed }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Exception -> 0x01ed }
        L_0x01c9:
            boolean r5 = r3.hasNext()     // Catch:{ Exception -> 0x01ed }
            if (r5 == 0) goto L_0x01ed
            java.lang.Object r5 = r3.next()     // Catch:{ Exception -> 0x01ed }
            com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer r5 = (com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer) r5     // Catch:{ Exception -> 0x01ed }
            java.util.Set r6 = r5.getAutowiredFor()     // Catch:{ Exception -> 0x01ed }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x01ed }
        L_0x01dd:
            boolean r7 = r6.hasNext()     // Catch:{ Exception -> 0x01ed }
            if (r7 == 0) goto L_0x01c9
            java.lang.Object r7 = r6.next()     // Catch:{ Exception -> 0x01ed }
            java.lang.reflect.Type r7 = (java.lang.reflect.Type) r7     // Catch:{ Exception -> 0x01ed }
            r0.putDeserializer(r7, r5)     // Catch:{ Exception -> 0x01ed }
            goto L_0x01dd
        L_0x01ed:
            if (r4 != 0) goto L_0x01f3
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r4 = r0.get(r2)
        L_0x01f3:
            if (r4 == 0) goto L_0x01f6
            return r4
        L_0x01f6:
            boolean r3 = r23.isEnum()
            if (r3 == 0) goto L_0x0237
            boolean r3 = r0.jacksonCompatible
            if (r3 == 0) goto L_0x021a
            java.lang.reflect.Method[] r3 = r23.getMethods()
            int r4 = r3.length
        L_0x0205:
            if (r8 >= r4) goto L_0x021a
            r5 = r3[r8]
            boolean r5 = com.alibaba.fastjson.util.TypeUtils.isJacksonCreator(r5)
            if (r5 == 0) goto L_0x0217
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r1 = r0.createJavaBeanDeserializer(r1, r2)
            r0.putDeserializer(r2, r1)
            return r1
        L_0x0217:
            int r8 = r8 + 1
            goto L_0x0205
        L_0x021a:
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r3 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r3 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.Class<?>) r1, r3)
            com.alibaba.fastjson.annotation.JSONType r3 = (com.alibaba.fastjson.annotation.JSONType) r3
            if (r3 == 0) goto L_0x0232
            java.lang.Class r3 = r3.deserializer()
            java.lang.Object r3 = r3.newInstance()     // Catch:{ all -> 0x0232 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r3 = (com.alibaba.fastjson.parser.deserializer.ObjectDeserializer) r3     // Catch:{ all -> 0x0232 }
            r0.putDeserializer(r1, r3)     // Catch:{ all -> 0x0232 }
            return r3
        L_0x0232:
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r1 = r22.getEnumDeserializer(r23)
            goto L_0x0296
        L_0x0237:
            boolean r3 = r23.isArray()
            if (r3 == 0) goto L_0x0240
            com.alibaba.fastjson.serializer.ObjectArrayCodec r1 = com.alibaba.fastjson.serializer.ObjectArrayCodec.instance
            goto L_0x0296
        L_0x0240:
            java.lang.Class<java.util.Set> r3 = java.util.Set.class
            if (r1 == r3) goto L_0x0294
            java.lang.Class<java.util.HashSet> r3 = java.util.HashSet.class
            if (r1 == r3) goto L_0x0294
            java.lang.Class<java.util.Collection> r3 = java.util.Collection.class
            if (r1 == r3) goto L_0x0294
            java.lang.Class<java.util.List> r3 = java.util.List.class
            if (r1 == r3) goto L_0x0294
            java.lang.Class<java.util.ArrayList> r3 = java.util.ArrayList.class
            if (r1 != r3) goto L_0x0255
            goto L_0x0294
        L_0x0255:
            java.lang.Class<java.util.Collection> r3 = java.util.Collection.class
            boolean r3 = r3.isAssignableFrom(r1)
            if (r3 == 0) goto L_0x0260
            com.alibaba.fastjson.serializer.CollectionCodec r1 = com.alibaba.fastjson.serializer.CollectionCodec.instance
            goto L_0x0296
        L_0x0260:
            java.lang.Class<java.util.Map> r3 = java.util.Map.class
            boolean r3 = r3.isAssignableFrom(r1)
            if (r3 == 0) goto L_0x026b
            com.alibaba.fastjson.parser.deserializer.MapDeserializer r1 = com.alibaba.fastjson.parser.deserializer.MapDeserializer.instance
            goto L_0x0296
        L_0x026b:
            java.lang.Class<java.lang.Throwable> r3 = java.lang.Throwable.class
            boolean r3 = r3.isAssignableFrom(r1)
            if (r3 == 0) goto L_0x027a
            com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer r3 = new com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer
            r3.<init>(r0, r1)
        L_0x0278:
            r1 = r3
            goto L_0x0296
        L_0x027a:
            java.lang.Class<com.alibaba.fastjson.parser.deserializer.PropertyProcessable> r3 = com.alibaba.fastjson.parser.deserializer.PropertyProcessable.class
            boolean r3 = r3.isAssignableFrom(r1)
            if (r3 == 0) goto L_0x0288
            com.alibaba.fastjson.parser.deserializer.PropertyProcessableDeserializer r3 = new com.alibaba.fastjson.parser.deserializer.PropertyProcessableDeserializer
            r3.<init>(r1)
            goto L_0x0278
        L_0x0288:
            java.lang.Class<java.net.InetAddress> r3 = java.net.InetAddress.class
            if (r1 != r3) goto L_0x028f
            com.alibaba.fastjson.serializer.MiscCodec r1 = com.alibaba.fastjson.serializer.MiscCodec.instance
            goto L_0x0296
        L_0x028f:
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r1 = r0.createJavaBeanDeserializer(r1, r2)
            goto L_0x0296
        L_0x0294:
            com.alibaba.fastjson.serializer.CollectionCodec r1 = com.alibaba.fastjson.serializer.CollectionCodec.instance
        L_0x0296:
            r0.putDeserializer(r2, r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.ParserConfig.getDeserializer(java.lang.Class, java.lang.reflect.Type):com.alibaba.fastjson.parser.deserializer.ObjectDeserializer");
    }

    /* access modifiers changed from: protected */
    public ObjectDeserializer getEnumDeserializer(Class<?> cls) {
        return new EnumDeserializer(cls);
    }

    public void initJavaBeanDeserializers(Class<?>... clsArr) {
        if (clsArr != null) {
            for (Class<?> cls : clsArr) {
                if (cls != null) {
                    putDeserializer(cls, createJavaBeanDeserializer(cls, cls));
                }
            }
        }
    }

    public ObjectDeserializer createJavaBeanDeserializer(Class<?> cls, Type type) {
        JSONField annotation;
        ASMDeserializerFactory aSMDeserializerFactory;
        boolean z = this.asmEnable & (!this.fieldBased);
        boolean z2 = false;
        if (z) {
            JSONType jSONType = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class);
            if (jSONType != null) {
                Class<?> deserializer = jSONType.deserializer();
                if (deserializer != Void.class) {
                    try {
                        Object newInstance = deserializer.newInstance();
                        if (newInstance instanceof ObjectDeserializer) {
                            return (ObjectDeserializer) newInstance;
                        }
                    } catch (Throwable unused) {
                    }
                }
                z = jSONType.asm();
            }
            if (z) {
                Class<?> builderClass = JavaBeanInfo.getBuilderClass(cls, jSONType);
                if (builderClass == null) {
                    builderClass = cls;
                }
                while (true) {
                    if (Modifier.isPublic(builderClass.getModifiers())) {
                        builderClass = builderClass.getSuperclass();
                        if (builderClass != Object.class) {
                            if (builderClass == null) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
            }
        }
        if (cls.getTypeParameters().length != 0) {
            z = false;
        }
        if (z && (aSMDeserializerFactory = this.asmFactory) != null && aSMDeserializerFactory.classLoader.isExternalClass(cls)) {
            z = false;
        }
        if (z) {
            z = ASMUtils.checkName(cls.getSimpleName());
        }
        if (z) {
            if (cls.isInterface()) {
                z = false;
            }
            JavaBeanInfo build = JavaBeanInfo.build(cls, type, this.propertyNamingStrategy, false, TypeUtils.compatibleWithJavaBean, this.jacksonCompatible);
            if (z && build.fields.length > 200) {
                z = false;
            }
            Constructor<?> constructor = build.defaultConstructor;
            if (z && constructor == null && !cls.isInterface()) {
                z = false;
            }
            FieldInfo[] fieldInfoArr = build.fields;
            int length = fieldInfoArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                FieldInfo fieldInfo = fieldInfoArr[i];
                if (!fieldInfo.getOnly) {
                    Class<?> cls2 = fieldInfo.fieldClass;
                    if (!Modifier.isPublic(cls2.getModifiers()) || ((cls2.isMemberClass() && !Modifier.isStatic(cls2.getModifiers())) || ((fieldInfo.getMember() != null && !ASMUtils.checkName(fieldInfo.getMember().getName())) || (((annotation = fieldInfo.getAnnotation()) != null && (!ASMUtils.checkName(annotation.name()) || annotation.format().length() != 0 || annotation.deserializeUsing() != Void.class || annotation.parseFeatures().length != 0 || annotation.unwrapped())) || ((fieldInfo.method != null && fieldInfo.method.getParameterTypes().length > 1) || (cls2.isEnum() && !(getDeserializer((Type) cls2) instanceof EnumDeserializer))))))) {
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
            z = false;
        }
        if (z && cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers())) {
            z = false;
        }
        if (!z || !TypeUtils.isXmlField(cls)) {
            z2 = z;
        }
        if (!z2) {
            return new JavaBeanDeserializer(this, cls, type);
        }
        JavaBeanInfo build2 = JavaBeanInfo.build(cls, type, this.propertyNamingStrategy);
        try {
            return this.asmFactory.createJavaBeanDeserializer(this, build2);
        } catch (NoSuchMethodException unused2) {
            return new JavaBeanDeserializer(this, cls, type);
        } catch (JSONException unused3) {
            return new JavaBeanDeserializer(this, build2);
        } catch (Exception e) {
            throw new JSONException("create asm deserializer error, " + cls.getName(), e);
        }
    }

    public FieldDeserializer createFieldDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, FieldInfo fieldInfo) {
        Class<?> deserializeUsing;
        Class<?> cls = javaBeanInfo.clazz;
        Class<?> cls2 = fieldInfo.fieldClass;
        JSONField annotation = fieldInfo.getAnnotation();
        Class<?> cls3 = null;
        if (!(annotation == null || (deserializeUsing = annotation.deserializeUsing()) == Void.class)) {
            cls3 = deserializeUsing;
        }
        if (cls3 == null && (cls2 == List.class || cls2 == ArrayList.class)) {
            return new ArrayListTypeFieldDeserializer(parserConfig, cls, fieldInfo);
        }
        return new DefaultFieldDeserializer(parserConfig, cls, fieldInfo);
    }

    public void putDeserializer(Type type, ObjectDeserializer objectDeserializer) {
        Type mixInAnnotations = JSON.getMixInAnnotations(type);
        if (mixInAnnotations != null) {
            IdentityHashMap identityHashMap = this.mixInDeserializers.get(type);
            if (identityHashMap == null) {
                identityHashMap = new IdentityHashMap(4);
                this.mixInDeserializers.put(type, identityHashMap);
            }
            identityHashMap.put(mixInAnnotations, objectDeserializer);
            return;
        }
        this.deserializers.put(type, objectDeserializer);
    }

    public ObjectDeserializer get(Type type) {
        Type mixInAnnotations = JSON.getMixInAnnotations(type);
        if (mixInAnnotations == null) {
            return this.deserializers.get(type);
        }
        IdentityHashMap identityHashMap = this.mixInDeserializers.get(type);
        if (identityHashMap == null) {
            return null;
        }
        return (ObjectDeserializer) identityHashMap.get(mixInAnnotations);
    }

    public ObjectDeserializer getDeserializer(FieldInfo fieldInfo) {
        return getDeserializer(fieldInfo.fieldClass, fieldInfo.fieldType);
    }

    public boolean isPrimitive(Class<?> cls) {
        return isPrimitive2(cls);
    }

    public static boolean isPrimitive2(Class<?> cls) {
        return cls.isPrimitive() || cls == Boolean.class || cls == Character.class || cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == BigInteger.class || cls == BigDecimal.class || cls == String.class || cls == java.util.Date.class || cls == Date.class || cls == Time.class || cls == Timestamp.class || cls.isEnum();
    }

    public static void parserAllFieldToCache(Class<?> cls, Map<String, Field> map) {
        for (Field field : cls.getDeclaredFields()) {
            String name = field.getName();
            if (!map.containsKey(name)) {
                map.put(name, field);
            }
        }
        if (cls.getSuperclass() != null && cls.getSuperclass() != Object.class) {
            parserAllFieldToCache(cls.getSuperclass(), map);
        }
    }

    public static Field getFieldFromCache(String str, Map<String, Field> map) {
        Field field = map.get(str);
        if (field == null) {
            field = map.get("_" + str);
        }
        if (field == null) {
            field = map.get("m_" + str);
        }
        if (field != null) {
            return field;
        }
        char charAt = str.charAt(0);
        if (charAt >= 'a' && charAt <= 'z') {
            char[] charArray = str.toCharArray();
            charArray[0] = (char) (charArray[0] - ' ');
            field = map.get(new String(charArray));
        }
        if (str.length() <= 2) {
            return field;
        }
        char charAt2 = str.charAt(1);
        if (str.length() <= 2 || charAt < 'a' || charAt > 'z' || charAt2 < 'A' || charAt2 > 'Z') {
            return field;
        }
        for (Map.Entry next : map.entrySet()) {
            if (str.equalsIgnoreCase((String) next.getKey())) {
                return (Field) next.getValue();
            }
        }
        return field;
    }

    public ClassLoader getDefaultClassLoader() {
        return this.defaultClassLoader;
    }

    public void setDefaultClassLoader(ClassLoader classLoader) {
        this.defaultClassLoader = classLoader;
    }

    public void addDenyInternal(String str) {
        if (str != null && str.length() != 0) {
            long fnv1a_64 = TypeUtils.fnv1a_64(str);
            long[] jArr = this.internalDenyHashCodes;
            if (jArr == null) {
                this.internalDenyHashCodes = new long[]{fnv1a_64};
            } else if (Arrays.binarySearch(jArr, fnv1a_64) < 0) {
                long[] jArr2 = this.internalDenyHashCodes;
                int length = jArr2.length;
                long[] jArr3 = new long[(length + 1)];
                jArr3[length] = fnv1a_64;
                System.arraycopy(jArr2, 0, jArr3, 0, jArr2.length);
                Arrays.sort(jArr3);
                this.internalDenyHashCodes = jArr3;
            }
        }
    }

    public void addDeny(String str) {
        if (str != null && str.length() != 0) {
            long fnv1a_64 = TypeUtils.fnv1a_64(str);
            if (Arrays.binarySearch(this.denyHashCodes, fnv1a_64) < 0) {
                long[] jArr = this.denyHashCodes;
                int length = jArr.length;
                long[] jArr2 = new long[(length + 1)];
                jArr2[length] = fnv1a_64;
                System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
                Arrays.sort(jArr2);
                this.denyHashCodes = jArr2;
            }
        }
    }

    public void addAccept(String str) {
        if (str != null && str.length() != 0) {
            long fnv1a_64 = TypeUtils.fnv1a_64(str);
            if (Arrays.binarySearch(this.acceptHashCodes, fnv1a_64) < 0) {
                long[] jArr = this.acceptHashCodes;
                int length = jArr.length;
                long[] jArr2 = new long[(length + 1)];
                jArr2[length] = fnv1a_64;
                System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
                Arrays.sort(jArr2);
                this.acceptHashCodes = jArr2;
            }
        }
    }

    public Class<?> checkAutoType(Class cls) {
        if (get(cls) != null) {
            return cls;
        }
        return checkAutoType(cls.getName(), (Class<?>) null, JSON.DEFAULT_PARSER_FEATURE);
    }

    public Class<?> checkAutoType(String str, Class<?> cls) {
        return checkAutoType(str, cls, JSON.DEFAULT_PARSER_FEATURE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:135:0x02b5, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x02b6, code lost:
        r4 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02b8, code lost:
        r14 = false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x02b5 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:130:0x029e] */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02e0  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02e2  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x02f6  */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x036f  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0375  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x039b  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Class<?> checkAutoType(java.lang.String r25, java.lang.Class<?> r26, int r27) {
        /*
            r24 = this;
            r1 = r24
            r0 = r25
            r2 = r26
            r3 = r27
            r4 = 0
            if (r0 != 0) goto L_0x000c
            return r4
        L_0x000c:
            java.util.List<com.alibaba.fastjson.parser.ParserConfig$AutoTypeCheckHandler> r5 = r1.autoTypeCheckHandlers
            if (r5 == 0) goto L_0x0029
            java.util.List<com.alibaba.fastjson.parser.ParserConfig$AutoTypeCheckHandler> r5 = r1.autoTypeCheckHandlers
            java.util.Iterator r5 = r5.iterator()
        L_0x0016:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0029
            java.lang.Object r6 = r5.next()
            com.alibaba.fastjson.parser.ParserConfig$AutoTypeCheckHandler r6 = (com.alibaba.fastjson.parser.ParserConfig.AutoTypeCheckHandler) r6
            java.lang.Class r6 = r6.handler(r0, r2, r3)
            if (r6 == 0) goto L_0x0016
            return r6
        L_0x0029:
            com.alibaba.fastjson.parser.Feature r5 = com.alibaba.fastjson.parser.Feature.SafeMode
            int r5 = r5.mask
            boolean r6 = r1.safeMode
            if (r6 != 0) goto L_0x03c1
            r6 = r3 & r5
            if (r6 != 0) goto L_0x03c1
            int r6 = com.alibaba.fastjson.JSON.DEFAULT_PARSER_FEATURE
            r5 = r5 & r6
            if (r5 != 0) goto L_0x03c1
            int r5 = r25.length()
            r6 = 192(0xc0, float:2.69E-43)
            java.lang.String r7 = "autoType is not support. "
            if (r5 >= r6) goto L_0x03ae
            int r5 = r25.length()
            r6 = 3
            if (r5 < r6) goto L_0x03ae
            r5 = 0
            r8 = 1
            if (r2 != 0) goto L_0x0051
        L_0x004f:
            r9 = r5
            goto L_0x00b5
        L_0x0051:
            java.lang.String r9 = r26.getName()
            long r9 = com.alibaba.fastjson.util.TypeUtils.fnv1a_64(r9)
            r11 = -8024746738719829346(0x90a25f5baa21529e, double:-1.514751450580626E-228)
            int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x004f
            r11 = 3247277300971823414(0x2d10a5801b9d6136, double:1.2768618085266423E-91)
            int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x004f
            r11 = -5811778396720452501(0xaf586a571e302c6b, double:-1.2869594668238042E-80)
            int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x004f
            r11 = -1368967840069965882(0xed007300a7b227c6, double:-1.1341028219519378E217)
            int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x004f
            r11 = 2980334044947851925(0x295c4605fd1eaa95, double:1.8810554767322845E-109)
            int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x004f
            r11 = 5183404141909004468(0x47ef269aadc650b4, double:3.312520992710671E38)
            int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x004f
            r11 = 7222019943667248779(0x6439c4dff712ae8b, double:6.373467611436065E174)
            int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x004f
            r11 = -2027296626235911549(0xe3dd9875a2dc5283, double:-1.1437309411088266E173)
            int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x004f
            r11 = -2114196234051346931(0xe2a8ddba03e69e0d, double:-1.8328867399748285E167)
            int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x004f
            r11 = -2939497380989775398(0xd734ceb4c3e9d1da, double:-1.2509996135591577E112)
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 != 0) goto L_0x00b4
            goto L_0x004f
        L_0x00b4:
            r9 = r8
        L_0x00b5:
            r10 = 36
            r11 = 46
            java.lang.String r10 = r0.replace(r10, r11)
            char r12 = r10.charAt(r5)
            long r12 = (long) r12
            r14 = -3750763034362895579(0xcbf29ce484222325, double:-7.302176725335867E57)
            long r12 = r12 ^ r14
            r16 = 1099511628211(0x100000001b3, double:5.43230922702E-312)
            long r12 = r12 * r16
            r18 = -5808493101479473382(0xaf64164c86024f1a, double:-2.1176223865607047E-80)
            int r18 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1))
            if (r18 == 0) goto L_0x039b
            int r18 = r10.length()
            int r4 = r18 + -1
            char r4 = r10.charAt(r4)
            r18 = r7
            long r6 = (long) r4
            long r6 = r6 ^ r12
            long r6 = r6 * r16
            r12 = 655701488918567152(0x9198507b5af98f0, double:7.914409534561175E-265)
            int r4 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r4 == 0) goto L_0x0387
            char r4 = r10.charAt(r5)
            long r6 = (long) r4
            long r6 = r6 ^ r14
            long r6 = r6 * r16
            char r4 = r10.charAt(r8)
            long r12 = (long) r4
            long r6 = r6 ^ r12
            long r6 = r6 * r16
            r4 = 2
            char r4 = r10.charAt(r4)
            long r12 = (long) r4
            long r6 = r6 ^ r12
            long r6 = r6 * r16
            long r12 = com.alibaba.fastjson.util.TypeUtils.fnv1a_64(r10)
            long[] r4 = INTERNAL_WHITELIST_HASHCODES
            int r4 = java.util.Arrays.binarySearch(r4, r12)
            if (r4 < 0) goto L_0x0118
            r4 = r8
            goto L_0x0119
        L_0x0118:
            r4 = r5
        L_0x0119:
            long[] r14 = r1.internalDenyHashCodes
            if (r14 == 0) goto L_0x0155
            r20 = r6
            r14 = 3
        L_0x0120:
            int r15 = r10.length()
            if (r14 >= r15) goto L_0x0155
            char r15 = r10.charAt(r14)
            r22 = r6
            long r5 = (long) r15
            long r5 = r20 ^ r5
            long r5 = r5 * r16
            long[] r7 = r1.internalDenyHashCodes
            int r7 = java.util.Arrays.binarySearch(r7, r5)
            if (r7 >= 0) goto L_0x0141
            int r14 = r14 + 1
            r20 = r5
            r6 = r22
            r5 = 0
            goto L_0x0120
        L_0x0141:
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r5 = r18
            r3.<init>(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x0155:
            r22 = r6
            r5 = r18
            if (r4 != 0) goto L_0x01b8
            boolean r6 = r1.autoTypeSupport
            if (r6 != 0) goto L_0x0161
            if (r9 == 0) goto L_0x01b8
        L_0x0161:
            r14 = r22
            r6 = 3
        L_0x0164:
            int r7 = r10.length()
            if (r6 >= r7) goto L_0x01b8
            char r7 = r10.charAt(r6)
            r20 = r12
            long r11 = (long) r7
            long r11 = r11 ^ r14
            long r14 = r11 * r16
            long[] r7 = r1.acceptHashCodes
            int r7 = java.util.Arrays.binarySearch(r7, r14)
            if (r7 < 0) goto L_0x0185
            java.lang.ClassLoader r7 = r1.defaultClassLoader
            java.lang.Class r7 = com.alibaba.fastjson.util.TypeUtils.loadClass(r0, r7, r8)
            if (r7 == 0) goto L_0x0185
            return r7
        L_0x0185:
            long[] r7 = r1.denyHashCodes
            int r7 = java.util.Arrays.binarySearch(r7, r14)
            if (r7 < 0) goto L_0x01b0
            java.lang.Class r7 = com.alibaba.fastjson.util.TypeUtils.getClassFromMapping(r25)
            if (r7 != 0) goto L_0x01b0
            long[] r7 = r1.acceptHashCodes
            r11 = r20
            int r7 = java.util.Arrays.binarySearch(r7, r11)
            if (r7 < 0) goto L_0x019e
            goto L_0x01b2
        L_0x019e:
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x01b0:
            r11 = r20
        L_0x01b2:
            int r6 = r6 + 1
            r12 = r11
            r11 = 46
            goto L_0x0164
        L_0x01b8:
            java.lang.Class r6 = com.alibaba.fastjson.util.TypeUtils.getClassFromMapping(r25)
            if (r6 != 0) goto L_0x01c4
            com.alibaba.fastjson.util.IdentityHashMap<java.lang.reflect.Type, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer> r6 = r1.deserializers
            java.lang.Class r6 = r6.findClass(r0)
        L_0x01c4:
            if (r6 != 0) goto L_0x01ce
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Class<?>> r6 = r1.typeMapping
            java.lang.Object r6 = r6.get(r0)
            java.lang.Class r6 = (java.lang.Class) r6
        L_0x01ce:
            if (r4 == 0) goto L_0x01d6
            java.lang.ClassLoader r4 = r1.defaultClassLoader
            java.lang.Class r6 = com.alibaba.fastjson.util.TypeUtils.loadClass(r0, r4, r8)
        L_0x01d6:
            java.lang.String r4 = " -> "
            java.lang.String r7 = "type not match. "
            if (r6 == 0) goto L_0x0206
            if (r2 == 0) goto L_0x0205
            java.lang.Class<java.util.HashMap> r3 = java.util.HashMap.class
            if (r6 == r3) goto L_0x0205
            boolean r3 = r2.isAssignableFrom(r6)
            if (r3 == 0) goto L_0x01e9
            goto L_0x0205
        L_0x01e9:
            com.alibaba.fastjson.JSONException r3 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r7)
            r5.append(r0)
            r5.append(r4)
            java.lang.String r0 = r26.getName()
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r3.<init>(r0)
            throw r3
        L_0x0205:
            return r6
        L_0x0206:
            boolean r11 = r1.autoTypeSupport
            if (r11 != 0) goto L_0x0270
            r11 = 3
        L_0x020b:
            int r12 = r10.length()
            if (r11 >= r12) goto L_0x0270
            char r12 = r10.charAt(r11)
            long r12 = (long) r12
            long r12 = r22 ^ r12
            long r12 = r12 * r16
            long[] r14 = r1.denyHashCodes
            int r14 = java.util.Arrays.binarySearch(r14, r12)
            if (r14 >= 0) goto L_0x025e
            long[] r14 = r1.acceptHashCodes
            int r14 = java.util.Arrays.binarySearch(r14, r12)
            if (r14 < 0) goto L_0x0259
            java.lang.ClassLoader r3 = r1.defaultClassLoader
            java.lang.Class r3 = com.alibaba.fastjson.util.TypeUtils.loadClass(r0, r3, r8)
            if (r3 != 0) goto L_0x0233
            return r2
        L_0x0233:
            if (r2 == 0) goto L_0x0258
            boolean r5 = r2.isAssignableFrom(r3)
            if (r5 != 0) goto L_0x023c
            goto L_0x0258
        L_0x023c:
            com.alibaba.fastjson.JSONException r3 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r7)
            r5.append(r0)
            r5.append(r4)
            java.lang.String r0 = r26.getName()
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r3.<init>(r0)
            throw r3
        L_0x0258:
            return r3
        L_0x0259:
            int r11 = r11 + 1
            r22 = r12
            goto L_0x020b
        L_0x025e:
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x0270:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02c8, all -> 0x02c2 }
            r10.<init>()     // Catch:{ Exception -> 0x02c8, all -> 0x02c2 }
            r11 = 47
            r12 = 46
            java.lang.String r11 = r0.replace(r12, r11)     // Catch:{ Exception -> 0x02c8, all -> 0x02c2 }
            r10.append(r11)     // Catch:{ Exception -> 0x02c8, all -> 0x02c2 }
            java.lang.String r11 = ".class"
            r10.append(r11)     // Catch:{ Exception -> 0x02c8, all -> 0x02c2 }
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x02c8, all -> 0x02c2 }
            java.lang.ClassLoader r11 = r1.defaultClassLoader     // Catch:{ Exception -> 0x02c8, all -> 0x02c2 }
            if (r11 == 0) goto L_0x0292
            java.io.InputStream r10 = r11.getResourceAsStream(r10)     // Catch:{ Exception -> 0x02c8, all -> 0x02c2 }
            goto L_0x029c
        L_0x0292:
            java.lang.Class<com.alibaba.fastjson.parser.ParserConfig> r11 = com.alibaba.fastjson.parser.ParserConfig.class
            java.lang.ClassLoader r11 = r11.getClassLoader()     // Catch:{ Exception -> 0x02c8, all -> 0x02c2 }
            java.io.InputStream r10 = r11.getResourceAsStream(r10)     // Catch:{ Exception -> 0x02c8, all -> 0x02c2 }
        L_0x029c:
            if (r10 == 0) goto L_0x02bc
            com.alibaba.fastjson.asm.ClassReader r11 = new com.alibaba.fastjson.asm.ClassReader     // Catch:{ Exception -> 0x02b8, all -> 0x02b5 }
            r11.<init>(r10, r8)     // Catch:{ Exception -> 0x02b8, all -> 0x02b5 }
            com.alibaba.fastjson.asm.TypeCollector r12 = new com.alibaba.fastjson.asm.TypeCollector     // Catch:{ Exception -> 0x02b8, all -> 0x02b5 }
            java.lang.String r13 = "<clinit>"
            r14 = 0
            java.lang.Class[] r15 = new java.lang.Class[r14]     // Catch:{ Exception -> 0x02b9, all -> 0x02b5 }
            r12.<init>(r13, r15)     // Catch:{ Exception -> 0x02b9, all -> 0x02b5 }
            r11.accept(r12)     // Catch:{ Exception -> 0x02b9, all -> 0x02b5 }
            boolean r11 = r12.hasJsonType()     // Catch:{ Exception -> 0x02b9, all -> 0x02b5 }
            goto L_0x02be
        L_0x02b5:
            r0 = move-exception
            r4 = r10
            goto L_0x02c4
        L_0x02b8:
            r14 = 0
        L_0x02b9:
            r19 = r10
            goto L_0x02cb
        L_0x02bc:
            r14 = 0
            r11 = r14
        L_0x02be:
            com.alibaba.fastjson.util.IOUtils.close(r10)
            goto L_0x02cf
        L_0x02c2:
            r0 = move-exception
            r4 = 0
        L_0x02c4:
            com.alibaba.fastjson.util.IOUtils.close(r4)
            throw r0
        L_0x02c8:
            r14 = 0
            r19 = 0
        L_0x02cb:
            com.alibaba.fastjson.util.IOUtils.close(r19)
            r11 = r14
        L_0x02cf:
            com.alibaba.fastjson.parser.Feature r10 = com.alibaba.fastjson.parser.Feature.SupportAutoType
            int r10 = r10.mask
            boolean r12 = r1.autoTypeSupport
            if (r12 != 0) goto L_0x02e2
            r3 = r3 & r10
            if (r3 != 0) goto L_0x02e2
            int r3 = com.alibaba.fastjson.JSON.DEFAULT_PARSER_FEATURE
            r3 = r3 & r10
            if (r3 == 0) goto L_0x02e0
            goto L_0x02e2
        L_0x02e0:
            r3 = r14
            goto L_0x02e3
        L_0x02e2:
            r3 = r8
        L_0x02e3:
            if (r3 != 0) goto L_0x02e9
            if (r11 != 0) goto L_0x02e9
            if (r9 == 0) goto L_0x02f4
        L_0x02e9:
            if (r3 != 0) goto L_0x02ed
            if (r11 == 0) goto L_0x02ee
        L_0x02ed:
            r14 = r8
        L_0x02ee:
            java.lang.ClassLoader r6 = r1.defaultClassLoader
            java.lang.Class r6 = com.alibaba.fastjson.util.TypeUtils.loadClass(r0, r6, r14)
        L_0x02f4:
            if (r6 == 0) goto L_0x036d
            if (r11 == 0) goto L_0x02fc
            com.alibaba.fastjson.util.TypeUtils.addMapping(r0, r6)
            return r6
        L_0x02fc:
            java.lang.Class<java.lang.ClassLoader> r8 = java.lang.ClassLoader.class
            boolean r8 = r8.isAssignableFrom(r6)
            if (r8 != 0) goto L_0x035b
            java.lang.Class<javax.sql.DataSource> r8 = javax.sql.DataSource.class
            boolean r8 = r8.isAssignableFrom(r6)
            if (r8 != 0) goto L_0x035b
            java.lang.Class<javax.sql.RowSet> r8 = javax.sql.RowSet.class
            boolean r8 = r8.isAssignableFrom(r6)
            if (r8 != 0) goto L_0x035b
            if (r2 == 0) goto L_0x033c
            boolean r3 = r2.isAssignableFrom(r6)
            if (r3 == 0) goto L_0x0320
            com.alibaba.fastjson.util.TypeUtils.addMapping(r0, r6)
            return r6
        L_0x0320:
            com.alibaba.fastjson.JSONException r3 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r7)
            r5.append(r0)
            r5.append(r4)
            java.lang.String r0 = r26.getName()
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r3.<init>(r0)
            throw r3
        L_0x033c:
            com.alibaba.fastjson.PropertyNamingStrategy r2 = r1.propertyNamingStrategy
            com.alibaba.fastjson.util.JavaBeanInfo r2 = com.alibaba.fastjson.util.JavaBeanInfo.build(r6, r6, r2)
            java.lang.reflect.Constructor<?> r2 = r2.creatorConstructor
            if (r2 == 0) goto L_0x036d
            if (r3 != 0) goto L_0x0349
            goto L_0x036d
        L_0x0349:
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x035b:
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x036d:
            if (r3 == 0) goto L_0x0375
            if (r6 == 0) goto L_0x0374
            com.alibaba.fastjson.util.TypeUtils.addMapping(r0, r6)
        L_0x0374:
            return r6
        L_0x0375:
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x0387:
            r5 = r18
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x039b:
            r5 = r7
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x03ae:
            r5 = r7
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x03c1:
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "safeMode not support autoType : "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.ParserConfig.checkAutoType(java.lang.String, java.lang.Class, int):java.lang.Class");
    }

    public void clearDeserializers() {
        this.deserializers.clear();
        initDeserializers();
    }

    public boolean isJacksonCompatible() {
        return this.jacksonCompatible;
    }

    public void setJacksonCompatible(boolean z) {
        this.jacksonCompatible = z;
    }

    public void register(String str, Class cls) {
        this.typeMapping.putIfAbsent(str, cls);
    }

    public void register(Module module) {
        this.modules.add(module);
    }

    public void addAutoTypeCheckHandler(AutoTypeCheckHandler autoTypeCheckHandler) {
        List list = this.autoTypeCheckHandlers;
        if (list == null) {
            list = new CopyOnWriteArrayList();
            this.autoTypeCheckHandlers = list;
        }
        list.add(autoTypeCheckHandler);
    }
}
