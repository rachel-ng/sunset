package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONPathException;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.ResolveFieldDeserializer;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DefaultJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    private static final Set<Class<?>> primitiveClasses = new HashSet();
    private String[] autoTypeAccept;
    private boolean autoTypeEnable;
    protected ParserConfig config;
    protected ParseContext context;
    private ParseContext[] contextArray;
    private int contextArrayIndex;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    private List<ExtraProcessor> extraProcessors;
    private List<ExtraTypeProvider> extraTypeProviders;
    protected FieldTypeResolver fieldTypeResolver;
    public final Object input;
    protected transient BeanContext lastBeanContext;
    public final JSONLexer lexer;
    private int objectKeyLevel;
    public int resolveStatus;
    private List<ResolveTask> resolveTaskList;
    public final SymbolTable symbolTable;

    static {
        Class[] clsArr = {Boolean.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, BigInteger.class, BigDecimal.class, String.class};
        for (int i = 0; i < 17; i++) {
            primitiveClasses.add(clsArr[i]);
        }
    }

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormatPattern, this.lexer.getLocale());
            this.dateFormat = simpleDateFormat;
            simpleDateFormat.setTimeZone(this.lexer.getTimeZone());
        }
        return this.dateFormat;
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        this.dateFormat = null;
    }

    public void setDateFomrat(DateFormat dateFormat2) {
        this.dateFormat = dateFormat2;
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.getGlobalInstance(), JSON.DEFAULT_PARSER_FEATURE);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this((Object) str, (JSONLexer) new JSONScanner(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i) {
        this((Object) str, (JSONLexer) new JSONScanner(str, i), parserConfig);
    }

    public DefaultJSONParser(char[] cArr, int i, ParserConfig parserConfig, int i2) {
        this((Object) cArr, (JSONLexer) new JSONScanner(cArr, i, i2), parserConfig);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.getGlobalInstance());
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this((Object) null, jSONLexer, parserConfig);
    }

    public DefaultJSONParser(Object obj, JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.fieldTypeResolver = null;
        this.objectKeyLevel = 0;
        this.autoTypeAccept = null;
        this.lexer = jSONLexer;
        this.input = obj;
        this.config = parserConfig;
        this.symbolTable = parserConfig.symbolTable;
        char current = jSONLexer.getCurrent();
        if (current == '{') {
            jSONLexer.next();
            ((JSONLexerBase) jSONLexer).token = 12;
        } else if (current == '[') {
            jSONLexer.next();
            ((JSONLexerBase) jSONLexer).token = 14;
        } else {
            jSONLexer.nextToken();
        }
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public String getInput() {
        Object obj = this.input;
        if (obj instanceof char[]) {
            return new String((char[]) obj);
        }
        return obj.toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v22, resolved type: com.alibaba.fastjson.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v25, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v26, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v27, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v28, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v60, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v63, resolved type: com.alibaba.fastjson.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v64, resolved type: com.alibaba.fastjson.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v65, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v66, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v67, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v68, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v69, resolved type: java.util.Date} */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x026c, code lost:
        r12 = null;
        r7 = r1.config.checkAutoType(r6, (java.lang.Class<?>) null, r4.getFeatures());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0282, code lost:
        r4.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x028d, code lost:
        if (r4.token() != 13) goto L_0x02e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x028f, code lost:
        r4.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x029a, code lost:
        if ((r1.config.getDeserializer((java.lang.reflect.Type) r7) instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) == false) goto L_0x02a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x029c, code lost:
        r0 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r0, r7, r1.config);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x02a3, code lost:
        r0 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x02a4, code lost:
        if (r0 != null) goto L_0x02d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x02a8, code lost:
        if (r7 != java.lang.Cloneable.class) goto L_0x02b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x02aa, code lost:
        r0 = new java.util.HashMap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x02b6, code lost:
        if ("java.util.Collections$EmptyMap".equals(r6) == false) goto L_0x02bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x02b8, code lost:
        r0 = java.util.Collections.emptyMap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x02c3, code lost:
        if ("java.util.Collections$UnmodifiableMap".equals(r6) == false) goto L_0x02cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x02c5, code lost:
        r0 = java.util.Collections.unmodifiableMap(new java.util.HashMap());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x02cf, code lost:
        r0 = r7.newInstance();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x02d3, code lost:
        setContext(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x02d6, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x02e0, code lost:
        setResolveStatus(2);
        r3 = r1.context;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x02e6, code lost:
        if (r3 == null) goto L_0x02f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x02e8, code lost:
        if (r2 == null) goto L_0x02f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x02ec, code lost:
        if ((r2 instanceof java.lang.Integer) != false) goto L_0x02f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x02f2, code lost:
        if ((r3.fieldName instanceof java.lang.Integer) != false) goto L_0x02f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x02f4, code lost:
        popContext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x02fb, code lost:
        if (r17.size() <= 0) goto L_0x030e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x02fd, code lost:
        r0 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r0, r7, r1.config);
        setResolveStatus(0);
        parseObject((java.lang.Object) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x030a, code lost:
        setContext(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x030d, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:?, code lost:
        r0 = r1.config.getDeserializer((java.lang.reflect.Type) r7);
        r3 = r0.getClass();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x031e, code lost:
        if (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.class.isAssignableFrom(r3) == false) goto L_0x032d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0322, code lost:
        if (r3 == com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.class) goto L_0x032d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0326, code lost:
        if (r3 == com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer.class) goto L_0x032d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0328, code lost:
        setResolveStatus(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x032f, code lost:
        if ((r0 instanceof com.alibaba.fastjson.parser.deserializer.MapDeserializer) == false) goto L_0x0335;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0331, code lost:
        setResolveStatus(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0335, code lost:
        r0 = r0.deserialze(r1, r7, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x0339, code lost:
        setContext(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x033c, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x034a, code lost:
        if (r17.size() == 0) goto L_0x034c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x0380, code lost:
        if ("@".equals(r6) == false) goto L_0x039c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x0382, code lost:
        r0 = r1.context;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0384, code lost:
        if (r0 == null) goto L_0x03e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0386, code lost:
        r2 = r0.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x038a, code lost:
        if ((r2 instanceof java.lang.Object[]) != false) goto L_0x039a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x038e, code lost:
        if ((r2 instanceof java.util.Collection) == false) goto L_0x0391;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0393, code lost:
        if (r0.parent == null) goto L_0x03e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0395, code lost:
        r7 = r0.parent.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x039a, code lost:
        r7 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x03a2, code lost:
        if ("..".equals(r6) == false) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x03a6, code lost:
        if (r5.object == null) goto L_0x03ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x03a8, code lost:
        r7 = r5.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x03ab, code lost:
        addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r5, r6));
        setResolveStatus(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x03bd, code lost:
        if ("$".equals(r6) == false) goto L_0x03db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x03bf, code lost:
        r0 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x03c2, code lost:
        if (r0.parent == null) goto L_0x03c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x03c4, code lost:
        r0 = r0.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x03c9, code lost:
        if (r0.object == null) goto L_0x03cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x03cb, code lost:
        r7 = r0.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x03cf, code lost:
        addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r0, r6));
        setResolveStatus(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x03db, code lost:
        addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r5, r6));
        setResolveStatus(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x03e6, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x03ed, code lost:
        if (r4.token() != 13) goto L_0x03f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x03ef, code lost:
        r4.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x03f4, code lost:
        setContext(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x03f7, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x0412, code lost:
        throw new com.alibaba.fastjson.JSONException("syntax error, " + r4.info());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x04c7, code lost:
        if (r8 != '}') goto L_0x04d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:293:0x04c9, code lost:
        r4.next();
        r4.resetStringPosition();
        r4.nextToken();
        setContext(r6, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:294:0x04d5, code lost:
        setContext(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x04d8, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:298:0x04f7, code lost:
        throw new com.alibaba.fastjson.JSONException("syntax error, position at " + r4.pos() + ", name " + r14);
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0212 A[Catch:{ Exception -> 0x02d7, NumberFormatException -> 0x019c, all -> 0x0676 }] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x022b A[Catch:{ Exception -> 0x02d7, NumberFormatException -> 0x019c, all -> 0x0676 }] */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x033d  */
    /* JADX WARNING: Removed duplicated region for block: B:348:0x05c0 A[Catch:{ Exception -> 0x02d7, NumberFormatException -> 0x019c, all -> 0x0676 }] */
    /* JADX WARNING: Removed duplicated region for block: B:353:0x05cc A[Catch:{ Exception -> 0x02d7, NumberFormatException -> 0x019c, all -> 0x0676 }] */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x05d8 A[Catch:{ Exception -> 0x02d7, NumberFormatException -> 0x019c, all -> 0x0676 }] */
    /* JADX WARNING: Removed duplicated region for block: B:362:0x05ed A[SYNTHETIC, Splitter:B:362:0x05ed] */
    /* JADX WARNING: Removed duplicated region for block: B:406:0x0183 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:409:0x05e3 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0181 A[Catch:{ Exception -> 0x02d7, NumberFormatException -> 0x019c, all -> 0x0676 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object parseObject(java.util.Map r17, java.lang.Object r18) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            r2 = r18
            java.lang.String r3 = "parse number key error"
            com.alibaba.fastjson.parser.JSONLexer r4 = r1.lexer
            int r5 = r4.token()
            r6 = 8
            r7 = 0
            if (r5 != r6) goto L_0x0017
            r4.nextToken()
            return r7
        L_0x0017:
            int r5 = r4.token()
            r6 = 13
            if (r5 != r6) goto L_0x0023
            r4.nextToken()
            return r0
        L_0x0023:
            int r5 = r4.token()
            r8 = 4
            if (r5 != r8) goto L_0x0038
            java.lang.String r5 = r4.stringVal()
            int r5 = r5.length()
            if (r5 != 0) goto L_0x0038
            r4.nextToken()
            return r0
        L_0x0038:
            int r5 = r4.token()
            r9 = 12
            r10 = 16
            if (r5 == r9) goto L_0x006d
            int r5 = r4.token()
            if (r5 != r10) goto L_0x0049
            goto L_0x006d
        L_0x0049:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "syntax error, expect {, actual "
            r2.<init>(r3)
            java.lang.String r3 = r4.tokenName()
            r2.append(r3)
            java.lang.String r3 = ", "
            r2.append(r3)
            java.lang.String r3 = r4.info()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x006d:
            com.alibaba.fastjson.parser.ParseContext r5 = r1.context
            boolean r9 = r0 instanceof com.alibaba.fastjson.JSONObject     // Catch:{ all -> 0x0676 }
            if (r9 == 0) goto L_0x007b
            r11 = r0
            com.alibaba.fastjson.JSONObject r11 = (com.alibaba.fastjson.JSONObject) r11     // Catch:{ all -> 0x0676 }
            java.util.Map r11 = r11.getInnerMap()     // Catch:{ all -> 0x0676 }
            goto L_0x007c
        L_0x007b:
            r11 = r0
        L_0x007c:
            r13 = 0
        L_0x007d:
            r4.skipWhitespace()     // Catch:{ all -> 0x0676 }
            char r14 = r4.getCurrent()     // Catch:{ all -> 0x0676 }
            com.alibaba.fastjson.parser.Feature r15 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x0676 }
            boolean r15 = r4.isEnabled((com.alibaba.fastjson.parser.Feature) r15)     // Catch:{ all -> 0x0676 }
            r8 = 44
            if (r15 == 0) goto L_0x009b
        L_0x008e:
            if (r14 != r8) goto L_0x009b
            r4.next()     // Catch:{ all -> 0x0676 }
            r4.skipWhitespace()     // Catch:{ all -> 0x0676 }
            char r14 = r4.getCurrent()     // Catch:{ all -> 0x0676 }
            goto L_0x008e
        L_0x009b:
            java.lang.String r7 = ", name "
            java.lang.String r12 = "expect ':' at "
            r15 = 58
            r6 = 34
            java.lang.String r8 = "syntax error"
            r10 = 1
            if (r14 != r6) goto L_0x00d9
            com.alibaba.fastjson.parser.SymbolTable r14 = r1.symbolTable     // Catch:{ all -> 0x0676 }
            java.lang.String r14 = r4.scanSymbol(r14, r6)     // Catch:{ all -> 0x0676 }
            r4.skipWhitespace()     // Catch:{ all -> 0x0676 }
            char r6 = r4.getCurrent()     // Catch:{ all -> 0x0676 }
            if (r6 != r15) goto L_0x00ba
        L_0x00b7:
            r6 = 0
            goto L_0x0210
        L_0x00ba:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0676 }
            r2.<init>()     // Catch:{ all -> 0x0676 }
            r2.append(r12)     // Catch:{ all -> 0x0676 }
            int r3 = r4.pos()     // Catch:{ all -> 0x0676 }
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            r2.append(r7)     // Catch:{ all -> 0x0676 }
            r2.append(r14)     // Catch:{ all -> 0x0676 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0676 }
            r0.<init>(r2)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x00d9:
            r6 = 125(0x7d, float:1.75E-43)
            if (r14 != r6) goto L_0x0104
            r4.next()     // Catch:{ all -> 0x0676 }
            r4.resetStringPosition()     // Catch:{ all -> 0x0676 }
            r4.nextToken()     // Catch:{ all -> 0x0676 }
            if (r13 != 0) goto L_0x0100
            com.alibaba.fastjson.parser.ParseContext r3 = r1.context     // Catch:{ all -> 0x0676 }
            if (r3 == 0) goto L_0x00f9
            java.lang.Object r3 = r3.fieldName     // Catch:{ all -> 0x0676 }
            if (r2 != r3) goto L_0x00f9
            com.alibaba.fastjson.parser.ParseContext r3 = r1.context     // Catch:{ all -> 0x0676 }
            java.lang.Object r3 = r3.object     // Catch:{ all -> 0x0676 }
            if (r0 != r3) goto L_0x00f9
            com.alibaba.fastjson.parser.ParseContext r5 = r1.context     // Catch:{ all -> 0x0676 }
            goto L_0x0100
        L_0x00f9:
            com.alibaba.fastjson.parser.ParseContext r2 = r16.setContext(r17, r18)     // Catch:{ all -> 0x0676 }
            if (r5 != 0) goto L_0x0100
            r5 = r2
        L_0x0100:
            r1.setContext(r5)
            return r0
        L_0x0104:
            r6 = 39
            if (r14 != r6) goto L_0x013f
            com.alibaba.fastjson.parser.Feature r14 = com.alibaba.fastjson.parser.Feature.AllowSingleQuotes     // Catch:{ all -> 0x0676 }
            boolean r14 = r4.isEnabled((com.alibaba.fastjson.parser.Feature) r14)     // Catch:{ all -> 0x0676 }
            if (r14 == 0) goto L_0x0139
            com.alibaba.fastjson.parser.SymbolTable r14 = r1.symbolTable     // Catch:{ all -> 0x0676 }
            java.lang.String r14 = r4.scanSymbol(r14, r6)     // Catch:{ all -> 0x0676 }
            r4.skipWhitespace()     // Catch:{ all -> 0x0676 }
            char r6 = r4.getCurrent()     // Catch:{ all -> 0x0676 }
            if (r6 != r15) goto L_0x0120
            goto L_0x00b7
        L_0x0120:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0676 }
            r2.<init>()     // Catch:{ all -> 0x0676 }
            r2.append(r12)     // Catch:{ all -> 0x0676 }
            int r3 = r4.pos()     // Catch:{ all -> 0x0676 }
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0676 }
            r0.<init>(r2)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x0139:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            r0.<init>(r8)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x013f:
            r6 = 26
            if (r14 == r6) goto L_0x0670
            r6 = 44
            if (r14 == r6) goto L_0x066a
            r6 = 48
            if (r14 < r6) goto L_0x014f
            r6 = 57
            if (r14 <= r6) goto L_0x0153
        L_0x014f:
            r6 = 45
            if (r14 != r6) goto L_0x01b5
        L_0x0153:
            r4.resetStringPosition()     // Catch:{ all -> 0x0676 }
            r4.scanNumber()     // Catch:{ all -> 0x0676 }
            int r6 = r4.token()     // Catch:{ NumberFormatException -> 0x019c }
            r12 = 2
            if (r6 != r12) goto L_0x0165
            java.lang.Number r6 = r4.integerValue()     // Catch:{ NumberFormatException -> 0x019c }
            goto L_0x0169
        L_0x0165:
            java.lang.Number r6 = r4.decimalValue(r10)     // Catch:{ NumberFormatException -> 0x019c }
        L_0x0169:
            com.alibaba.fastjson.parser.Feature r12 = com.alibaba.fastjson.parser.Feature.NonStringKeyAsString     // Catch:{ NumberFormatException -> 0x019c }
            boolean r12 = r4.isEnabled((com.alibaba.fastjson.parser.Feature) r12)     // Catch:{ NumberFormatException -> 0x019c }
            if (r12 != 0) goto L_0x0176
            if (r9 == 0) goto L_0x0174
            goto L_0x0176
        L_0x0174:
            r14 = r6
            goto L_0x017b
        L_0x0176:
            java.lang.String r6 = r6.toString()     // Catch:{ NumberFormatException -> 0x019c }
            goto L_0x0174
        L_0x017b:
            char r6 = r4.getCurrent()     // Catch:{ all -> 0x0676 }
            if (r6 != r15) goto L_0x0183
            goto L_0x00b7
        L_0x0183:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0676 }
            r2.<init>()     // Catch:{ all -> 0x0676 }
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            java.lang.String r3 = r4.info()     // Catch:{ all -> 0x0676 }
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0676 }
            r0.<init>(r2)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x019c:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0676 }
            r2.<init>()     // Catch:{ all -> 0x0676 }
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            java.lang.String r3 = r4.info()     // Catch:{ all -> 0x0676 }
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0676 }
            r0.<init>(r2)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x01b5:
            r6 = 123(0x7b, float:1.72E-43)
            if (r14 == r6) goto L_0x01fe
            r6 = 91
            if (r14 != r6) goto L_0x01be
            goto L_0x01fe
        L_0x01be:
            com.alibaba.fastjson.parser.Feature r6 = com.alibaba.fastjson.parser.Feature.AllowUnQuotedFieldNames     // Catch:{ all -> 0x0676 }
            boolean r6 = r4.isEnabled((com.alibaba.fastjson.parser.Feature) r6)     // Catch:{ all -> 0x0676 }
            if (r6 == 0) goto L_0x01f8
            com.alibaba.fastjson.parser.SymbolTable r6 = r1.symbolTable     // Catch:{ all -> 0x0676 }
            java.lang.String r14 = r4.scanSymbolUnQuoted(r6)     // Catch:{ all -> 0x0676 }
            r4.skipWhitespace()     // Catch:{ all -> 0x0676 }
            char r6 = r4.getCurrent()     // Catch:{ all -> 0x0676 }
            if (r6 != r15) goto L_0x01d7
            goto L_0x00b7
        L_0x01d7:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0676 }
            r2.<init>()     // Catch:{ all -> 0x0676 }
            r2.append(r12)     // Catch:{ all -> 0x0676 }
            int r3 = r4.pos()     // Catch:{ all -> 0x0676 }
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            java.lang.String r3 = ", actual "
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            r2.append(r6)     // Catch:{ all -> 0x0676 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0676 }
            r0.<init>(r2)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x01f8:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            r0.<init>(r8)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x01fe:
            int r6 = r1.objectKeyLevel     // Catch:{ all -> 0x0676 }
            int r12 = r6 + 1
            r1.objectKeyLevel = r12     // Catch:{ all -> 0x0676 }
            r12 = 512(0x200, float:7.175E-43)
            if (r6 > r12) goto L_0x0662
            r4.nextToken()     // Catch:{ all -> 0x0676 }
            java.lang.Object r14 = r16.parse()     // Catch:{ all -> 0x0676 }
            r6 = r10
        L_0x0210:
            if (r6 != 0) goto L_0x0218
            r4.next()     // Catch:{ all -> 0x0676 }
            r4.skipWhitespace()     // Catch:{ all -> 0x0676 }
        L_0x0218:
            char r6 = r4.getCurrent()     // Catch:{ all -> 0x0676 }
            r4.resetStringPosition()     // Catch:{ all -> 0x0676 }
            java.lang.String r12 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x0676 }
            if (r14 != r12) goto L_0x033d
            com.alibaba.fastjson.parser.Feature r12 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x0676 }
            boolean r12 = r4.isEnabled((com.alibaba.fastjson.parser.Feature) r12)     // Catch:{ all -> 0x0676 }
            if (r12 != 0) goto L_0x033d
            com.alibaba.fastjson.parser.SymbolTable r6 = r1.symbolTable     // Catch:{ all -> 0x0676 }
            r7 = 34
            java.lang.String r6 = r4.scanSymbol(r6, r7)     // Catch:{ all -> 0x0676 }
            com.alibaba.fastjson.parser.Feature r7 = com.alibaba.fastjson.parser.Feature.IgnoreAutoType     // Catch:{ all -> 0x0676 }
            boolean r7 = r4.isEnabled((com.alibaba.fastjson.parser.Feature) r7)     // Catch:{ all -> 0x0676 }
            if (r7 == 0) goto L_0x023f
        L_0x023b:
            r12 = 4
            r15 = 0
            goto L_0x0372
        L_0x023f:
            if (r0 == 0) goto L_0x0255
            java.lang.Class r7 = r17.getClass()     // Catch:{ all -> 0x0676 }
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x0676 }
            boolean r7 = r7.equals(r6)     // Catch:{ all -> 0x0676 }
            if (r7 == 0) goto L_0x0255
            java.lang.Class r7 = r17.getClass()     // Catch:{ all -> 0x0676 }
            r12 = 0
            goto L_0x027a
        L_0x0255:
            r7 = 0
        L_0x0256:
            int r8 = r6.length()     // Catch:{ all -> 0x0676 }
            if (r7 >= r8) goto L_0x0278
            char r8 = r6.charAt(r7)     // Catch:{ all -> 0x0676 }
            r10 = 48
            if (r8 < r10) goto L_0x026c
            r10 = 57
            if (r8 <= r10) goto L_0x0269
            goto L_0x026c
        L_0x0269:
            int r7 = r7 + 1
            goto L_0x0256
        L_0x026c:
            com.alibaba.fastjson.parser.ParserConfig r7 = r1.config     // Catch:{ all -> 0x0676 }
            int r8 = r4.getFeatures()     // Catch:{ all -> 0x0676 }
            r12 = 0
            java.lang.Class r7 = r7.checkAutoType(r6, r12, r8)     // Catch:{ all -> 0x0676 }
            goto L_0x027a
        L_0x0278:
            r12 = 0
            r7 = r12
        L_0x027a:
            if (r7 != 0) goto L_0x0282
            java.lang.String r7 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x0676 }
            r11.put(r7, r6)     // Catch:{ all -> 0x0676 }
            goto L_0x023b
        L_0x0282:
            r3 = 16
            r4.nextToken(r3)     // Catch:{ all -> 0x0676 }
            int r8 = r4.token()     // Catch:{ all -> 0x0676 }
            r9 = 13
            if (r8 != r9) goto L_0x02e0
            r4.nextToken(r3)     // Catch:{ all -> 0x0676 }
            com.alibaba.fastjson.parser.ParserConfig r2 = r1.config     // Catch:{ Exception -> 0x02d7 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r2 = r2.getDeserializer((java.lang.reflect.Type) r7)     // Catch:{ Exception -> 0x02d7 }
            boolean r2 = r2 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer     // Catch:{ Exception -> 0x02d7 }
            if (r2 == 0) goto L_0x02a3
            com.alibaba.fastjson.parser.ParserConfig r2 = r1.config     // Catch:{ Exception -> 0x02d7 }
            java.lang.Object r0 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r0, r7, (com.alibaba.fastjson.parser.ParserConfig) r2)     // Catch:{ Exception -> 0x02d7 }
            goto L_0x02a4
        L_0x02a3:
            r0 = r12
        L_0x02a4:
            if (r0 != 0) goto L_0x02d3
            java.lang.Class<java.lang.Cloneable> r0 = java.lang.Cloneable.class
            if (r7 != r0) goto L_0x02b0
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x02d7 }
            r0.<init>()     // Catch:{ Exception -> 0x02d7 }
            goto L_0x02d3
        L_0x02b0:
            java.lang.String r0 = "java.util.Collections$EmptyMap"
            boolean r0 = r0.equals(r6)     // Catch:{ Exception -> 0x02d7 }
            if (r0 == 0) goto L_0x02bd
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ Exception -> 0x02d7 }
            goto L_0x02d3
        L_0x02bd:
            java.lang.String r0 = "java.util.Collections$UnmodifiableMap"
            boolean r0 = r0.equals(r6)     // Catch:{ Exception -> 0x02d7 }
            if (r0 == 0) goto L_0x02cf
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x02d7 }
            r0.<init>()     // Catch:{ Exception -> 0x02d7 }
            java.util.Map r0 = java.util.Collections.unmodifiableMap(r0)     // Catch:{ Exception -> 0x02d7 }
            goto L_0x02d3
        L_0x02cf:
            java.lang.Object r0 = r7.newInstance()     // Catch:{ Exception -> 0x02d7 }
        L_0x02d3:
            r1.setContext(r5)
            return r0
        L_0x02d7:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            java.lang.String r3 = "create instance error"
            r2.<init>(r3, r0)     // Catch:{ all -> 0x0676 }
            throw r2     // Catch:{ all -> 0x0676 }
        L_0x02e0:
            r3 = 2
            r1.setResolveStatus(r3)     // Catch:{ all -> 0x0676 }
            com.alibaba.fastjson.parser.ParseContext r3 = r1.context     // Catch:{ all -> 0x0676 }
            if (r3 == 0) goto L_0x02f7
            if (r2 == 0) goto L_0x02f7
            boolean r4 = r2 instanceof java.lang.Integer     // Catch:{ all -> 0x0676 }
            if (r4 != 0) goto L_0x02f7
            java.lang.Object r3 = r3.fieldName     // Catch:{ all -> 0x0676 }
            boolean r3 = r3 instanceof java.lang.Integer     // Catch:{ all -> 0x0676 }
            if (r3 != 0) goto L_0x02f7
            r16.popContext()     // Catch:{ all -> 0x0676 }
        L_0x02f7:
            int r3 = r17.size()     // Catch:{ all -> 0x0676 }
            if (r3 <= 0) goto L_0x030e
            com.alibaba.fastjson.parser.ParserConfig r2 = r1.config     // Catch:{ all -> 0x0676 }
            java.lang.Object r0 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r0, r7, (com.alibaba.fastjson.parser.ParserConfig) r2)     // Catch:{ all -> 0x0676 }
            r2 = 0
            r1.setResolveStatus(r2)     // Catch:{ all -> 0x0676 }
            r1.parseObject((java.lang.Object) r0)     // Catch:{ all -> 0x0676 }
            r1.setContext(r5)
            return r0
        L_0x030e:
            com.alibaba.fastjson.parser.ParserConfig r0 = r1.config     // Catch:{ all -> 0x0676 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r0 = r0.getDeserializer((java.lang.reflect.Type) r7)     // Catch:{ all -> 0x0676 }
            java.lang.Class r3 = r0.getClass()     // Catch:{ all -> 0x0676 }
            java.lang.Class<com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer> r4 = com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.class
            boolean r4 = r4.isAssignableFrom(r3)     // Catch:{ all -> 0x0676 }
            if (r4 == 0) goto L_0x032d
            java.lang.Class<com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer> r4 = com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.class
            if (r3 == r4) goto L_0x032d
            java.lang.Class<com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer> r4 = com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer.class
            if (r3 == r4) goto L_0x032d
            r3 = 0
            r1.setResolveStatus(r3)     // Catch:{ all -> 0x0676 }
            goto L_0x0335
        L_0x032d:
            boolean r3 = r0 instanceof com.alibaba.fastjson.parser.deserializer.MapDeserializer     // Catch:{ all -> 0x0676 }
            if (r3 == 0) goto L_0x0335
            r15 = 0
            r1.setResolveStatus(r15)     // Catch:{ all -> 0x0676 }
        L_0x0335:
            java.lang.Object r0 = r0.deserialze(r1, r7, r2)     // Catch:{ all -> 0x0676 }
            r1.setContext(r5)
            return r0
        L_0x033d:
            r15 = 0
            java.lang.String r12 = "$ref"
            if (r14 != r12) goto L_0x0432
            if (r5 == 0) goto L_0x0432
            if (r0 == 0) goto L_0x034c
            int r12 = r17.size()     // Catch:{ all -> 0x0676 }
            if (r12 != 0) goto L_0x0432
        L_0x034c:
            com.alibaba.fastjson.parser.Feature r12 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x0676 }
            boolean r12 = r4.isEnabled((com.alibaba.fastjson.parser.Feature) r12)     // Catch:{ all -> 0x0676 }
            if (r12 != 0) goto L_0x0432
            r12 = 4
            r4.nextToken(r12)     // Catch:{ all -> 0x0676 }
            int r6 = r4.token()     // Catch:{ all -> 0x0676 }
            if (r6 != r12) goto L_0x0413
            java.lang.String r6 = r4.stringVal()     // Catch:{ all -> 0x0676 }
            r7 = 13
            r4.nextToken(r7)     // Catch:{ all -> 0x0676 }
            int r7 = r4.token()     // Catch:{ all -> 0x0676 }
            r8 = 16
            if (r7 != r8) goto L_0x037a
            r11.put(r14, r6)     // Catch:{ all -> 0x0676 }
        L_0x0372:
            r8 = r12
            r6 = 13
            r7 = 0
            r10 = 16
            goto L_0x007d
        L_0x037a:
            java.lang.String r0 = "@"
            boolean r0 = r0.equals(r6)     // Catch:{ all -> 0x0676 }
            if (r0 == 0) goto L_0x039c
            com.alibaba.fastjson.parser.ParseContext r0 = r1.context     // Catch:{ all -> 0x0676 }
            if (r0 == 0) goto L_0x03e6
            java.lang.Object r2 = r0.object     // Catch:{ all -> 0x0676 }
            boolean r3 = r2 instanceof java.lang.Object[]     // Catch:{ all -> 0x0676 }
            if (r3 != 0) goto L_0x039a
            boolean r3 = r2 instanceof java.util.Collection     // Catch:{ all -> 0x0676 }
            if (r3 == 0) goto L_0x0391
            goto L_0x039a
        L_0x0391:
            com.alibaba.fastjson.parser.ParseContext r2 = r0.parent     // Catch:{ all -> 0x0676 }
            if (r2 == 0) goto L_0x03e6
            com.alibaba.fastjson.parser.ParseContext r0 = r0.parent     // Catch:{ all -> 0x0676 }
            java.lang.Object r7 = r0.object     // Catch:{ all -> 0x0676 }
            goto L_0x03e7
        L_0x039a:
            r7 = r2
            goto L_0x03e7
        L_0x039c:
            java.lang.String r0 = ".."
            boolean r0 = r0.equals(r6)     // Catch:{ all -> 0x0676 }
            if (r0 == 0) goto L_0x03b7
            java.lang.Object r0 = r5.object     // Catch:{ all -> 0x0676 }
            if (r0 == 0) goto L_0x03ab
            java.lang.Object r7 = r5.object     // Catch:{ all -> 0x0676 }
            goto L_0x03e7
        L_0x03ab:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r0 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0676 }
            r0.<init>(r5, r6)     // Catch:{ all -> 0x0676 }
            r1.addResolveTask(r0)     // Catch:{ all -> 0x0676 }
            r1.setResolveStatus(r10)     // Catch:{ all -> 0x0676 }
            goto L_0x03e6
        L_0x03b7:
            java.lang.String r0 = "$"
            boolean r0 = r0.equals(r6)     // Catch:{ all -> 0x0676 }
            if (r0 == 0) goto L_0x03db
            r0 = r5
        L_0x03c0:
            com.alibaba.fastjson.parser.ParseContext r2 = r0.parent     // Catch:{ all -> 0x0676 }
            if (r2 == 0) goto L_0x03c7
            com.alibaba.fastjson.parser.ParseContext r0 = r0.parent     // Catch:{ all -> 0x0676 }
            goto L_0x03c0
        L_0x03c7:
            java.lang.Object r2 = r0.object     // Catch:{ all -> 0x0676 }
            if (r2 == 0) goto L_0x03cf
            java.lang.Object r0 = r0.object     // Catch:{ all -> 0x0676 }
            r7 = r0
            goto L_0x03e7
        L_0x03cf:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r2 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0676 }
            r2.<init>(r0, r6)     // Catch:{ all -> 0x0676 }
            r1.addResolveTask(r2)     // Catch:{ all -> 0x0676 }
            r1.setResolveStatus(r10)     // Catch:{ all -> 0x0676 }
            goto L_0x03e6
        L_0x03db:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r0 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0676 }
            r0.<init>(r5, r6)     // Catch:{ all -> 0x0676 }
            r1.addResolveTask(r0)     // Catch:{ all -> 0x0676 }
            r1.setResolveStatus(r10)     // Catch:{ all -> 0x0676 }
        L_0x03e6:
            r7 = 0
        L_0x03e7:
            int r0 = r4.token()     // Catch:{ all -> 0x0676 }
            r2 = 13
            if (r0 != r2) goto L_0x03f8
            r0 = 16
            r4.nextToken(r0)     // Catch:{ all -> 0x0676 }
            r1.setContext(r5)
            return r7
        L_0x03f8:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0676 }
            r2.<init>()     // Catch:{ all -> 0x0676 }
            java.lang.String r3 = "syntax error, "
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            java.lang.String r3 = r4.info()     // Catch:{ all -> 0x0676 }
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0676 }
            r0.<init>(r2)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x0413:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0676 }
            r2.<init>()     // Catch:{ all -> 0x0676 }
            java.lang.String r3 = "illegal ref, "
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            int r3 = r4.token()     // Catch:{ all -> 0x0676 }
            java.lang.String r3 = com.alibaba.fastjson.parser.JSONToken.name(r3)     // Catch:{ all -> 0x0676 }
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0676 }
            r0.<init>(r2)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x0432:
            r12 = 4
            if (r13 != 0) goto L_0x044e
            com.alibaba.fastjson.parser.ParseContext r10 = r1.context     // Catch:{ all -> 0x0676 }
            if (r10 == 0) goto L_0x0446
            java.lang.Object r10 = r10.fieldName     // Catch:{ all -> 0x0676 }
            if (r2 != r10) goto L_0x0446
            com.alibaba.fastjson.parser.ParseContext r10 = r1.context     // Catch:{ all -> 0x0676 }
            java.lang.Object r10 = r10.object     // Catch:{ all -> 0x0676 }
            if (r0 != r10) goto L_0x0446
            com.alibaba.fastjson.parser.ParseContext r5 = r1.context     // Catch:{ all -> 0x0676 }
            goto L_0x044e
        L_0x0446:
            com.alibaba.fastjson.parser.ParseContext r10 = r16.setContext(r17, r18)     // Catch:{ all -> 0x0676 }
            if (r5 != 0) goto L_0x044d
            r5 = r10
        L_0x044d:
            r13 = 1
        L_0x044e:
            java.lang.Class r10 = r17.getClass()     // Catch:{ all -> 0x0676 }
            java.lang.Class<com.alibaba.fastjson.JSONObject> r12 = com.alibaba.fastjson.JSONObject.class
            if (r10 != r12) goto L_0x045a
            if (r14 != 0) goto L_0x045a
            java.lang.String r14 = "null"
        L_0x045a:
            java.lang.String r10 = "syntax error, position at "
            r12 = 34
            if (r6 != r12) goto L_0x0489
            r4.scanString()     // Catch:{ all -> 0x0676 }
            java.lang.String r6 = r4.stringVal()     // Catch:{ all -> 0x0676 }
            com.alibaba.fastjson.parser.Feature r8 = com.alibaba.fastjson.parser.Feature.AllowISO8601DateFormat     // Catch:{ all -> 0x0676 }
            boolean r8 = r4.isEnabled((com.alibaba.fastjson.parser.Feature) r8)     // Catch:{ all -> 0x0676 }
            if (r8 == 0) goto L_0x0485
            com.alibaba.fastjson.parser.JSONScanner r8 = new com.alibaba.fastjson.parser.JSONScanner     // Catch:{ all -> 0x0676 }
            r8.<init>(r6)     // Catch:{ all -> 0x0676 }
            boolean r12 = r8.scanISO8601DateIfMatch()     // Catch:{ all -> 0x0676 }
            if (r12 == 0) goto L_0x0482
            java.util.Calendar r6 = r8.getCalendar()     // Catch:{ all -> 0x0676 }
            java.util.Date r6 = r6.getTime()     // Catch:{ all -> 0x0676 }
        L_0x0482:
            r8.close()     // Catch:{ all -> 0x0676 }
        L_0x0485:
            r11.put(r14, r6)     // Catch:{ all -> 0x0676 }
            goto L_0x04b1
        L_0x0489:
            r12 = 48
            if (r6 < r12) goto L_0x0491
            r12 = 57
            if (r6 <= r12) goto L_0x0495
        L_0x0491:
            r12 = 45
            if (r6 != r12) goto L_0x04f8
        L_0x0495:
            r4.scanNumber()     // Catch:{ all -> 0x0676 }
            int r6 = r4.token()     // Catch:{ all -> 0x0676 }
            r8 = 2
            if (r6 != r8) goto L_0x04a4
            java.lang.Number r6 = r4.integerValue()     // Catch:{ all -> 0x0676 }
            goto L_0x04ae
        L_0x04a4:
            com.alibaba.fastjson.parser.Feature r6 = com.alibaba.fastjson.parser.Feature.UseBigDecimal     // Catch:{ all -> 0x0676 }
            boolean r6 = r4.isEnabled((com.alibaba.fastjson.parser.Feature) r6)     // Catch:{ all -> 0x0676 }
            java.lang.Number r6 = r4.decimalValue(r6)     // Catch:{ all -> 0x0676 }
        L_0x04ae:
            r11.put(r14, r6)     // Catch:{ all -> 0x0676 }
        L_0x04b1:
            r4.skipWhitespace()     // Catch:{ all -> 0x0676 }
            char r8 = r4.getCurrent()     // Catch:{ all -> 0x0676 }
            r12 = 44
            if (r8 != r12) goto L_0x04c5
            r4.next()     // Catch:{ all -> 0x0676 }
        L_0x04bf:
            r8 = 13
            r12 = 16
            goto L_0x063d
        L_0x04c5:
            r2 = 125(0x7d, float:1.75E-43)
            if (r8 != r2) goto L_0x04d9
            r4.next()     // Catch:{ all -> 0x0676 }
            r4.resetStringPosition()     // Catch:{ all -> 0x0676 }
            r4.nextToken()     // Catch:{ all -> 0x0676 }
            r1.setContext(r6, r14)     // Catch:{ all -> 0x0676 }
            r1.setContext(r5)
            return r0
        L_0x04d9:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0676 }
            r2.<init>()     // Catch:{ all -> 0x0676 }
            r2.append(r10)     // Catch:{ all -> 0x0676 }
            int r3 = r4.pos()     // Catch:{ all -> 0x0676 }
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            r2.append(r7)     // Catch:{ all -> 0x0676 }
            r2.append(r14)     // Catch:{ all -> 0x0676 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0676 }
            r0.<init>(r2)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x04f8:
            r12 = 91
            if (r6 != r12) goto L_0x0545
            r4.nextToken()     // Catch:{ all -> 0x0676 }
            com.alibaba.fastjson.JSONArray r6 = new com.alibaba.fastjson.JSONArray     // Catch:{ all -> 0x0676 }
            r6.<init>()     // Catch:{ all -> 0x0676 }
            if (r2 == 0) goto L_0x050c
            java.lang.Class r7 = r18.getClass()     // Catch:{ all -> 0x0676 }
            java.lang.Class<java.lang.Integer> r10 = java.lang.Integer.class
        L_0x050c:
            if (r2 != 0) goto L_0x0511
            r1.setContext(r5)     // Catch:{ all -> 0x0676 }
        L_0x0511:
            r1.parseArray((java.util.Collection) r6, (java.lang.Object) r14)     // Catch:{ all -> 0x0676 }
            com.alibaba.fastjson.parser.Feature r7 = com.alibaba.fastjson.parser.Feature.UseObjectArray     // Catch:{ all -> 0x0676 }
            boolean r7 = r4.isEnabled((com.alibaba.fastjson.parser.Feature) r7)     // Catch:{ all -> 0x0676 }
            if (r7 == 0) goto L_0x0520
            java.lang.Object[] r6 = r6.toArray()     // Catch:{ all -> 0x0676 }
        L_0x0520:
            r11.put(r14, r6)     // Catch:{ all -> 0x0676 }
            int r6 = r4.token()     // Catch:{ all -> 0x0676 }
            r7 = 13
            if (r6 != r7) goto L_0x0532
            r4.nextToken()     // Catch:{ all -> 0x0676 }
            r1.setContext(r5)
            return r0
        L_0x0532:
            int r6 = r4.token()     // Catch:{ all -> 0x0676 }
            r7 = 16
            if (r6 != r7) goto L_0x053f
            r12 = r7
            r8 = 13
            goto L_0x063d
        L_0x053f:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            r0.<init>(r8)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x0545:
            r8 = 123(0x7b, float:1.72E-43)
            if (r6 != r8) goto L_0x061c
            r4.nextToken()     // Catch:{ all -> 0x0676 }
            if (r2 == 0) goto L_0x0558
            java.lang.Class r6 = r18.getClass()     // Catch:{ all -> 0x0676 }
            java.lang.Class<java.lang.Integer> r7 = java.lang.Integer.class
            if (r6 != r7) goto L_0x0558
            r6 = 1
            goto L_0x0559
        L_0x0558:
            r6 = r15
        L_0x0559:
            com.alibaba.fastjson.parser.Feature r7 = com.alibaba.fastjson.parser.Feature.CustomMapDeserializer     // Catch:{ all -> 0x0676 }
            boolean r7 = r4.isEnabled((com.alibaba.fastjson.parser.Feature) r7)     // Catch:{ all -> 0x0676 }
            if (r7 == 0) goto L_0x0588
            com.alibaba.fastjson.parser.ParserConfig r7 = r1.config     // Catch:{ all -> 0x0676 }
            java.lang.Class<java.util.Map> r8 = java.util.Map.class
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r7 = r7.getDeserializer((java.lang.reflect.Type) r8)     // Catch:{ all -> 0x0676 }
            com.alibaba.fastjson.parser.deserializer.MapDeserializer r7 = (com.alibaba.fastjson.parser.deserializer.MapDeserializer) r7     // Catch:{ all -> 0x0676 }
            int r8 = r4.getFeatures()     // Catch:{ all -> 0x0676 }
            com.alibaba.fastjson.parser.Feature r10 = com.alibaba.fastjson.parser.Feature.OrderedField     // Catch:{ all -> 0x0676 }
            int r10 = r10.mask     // Catch:{ all -> 0x0676 }
            r8 = r8 & r10
            if (r8 == 0) goto L_0x0581
            java.lang.Class<java.util.Map> r8 = java.util.Map.class
            int r10 = r4.getFeatures()     // Catch:{ all -> 0x0676 }
            java.util.Map r7 = r7.createMap(r8, r10)     // Catch:{ all -> 0x0676 }
            goto L_0x0593
        L_0x0581:
            java.lang.Class<java.util.Map> r8 = java.util.Map.class
            java.util.Map r7 = r7.createMap(r8)     // Catch:{ all -> 0x0676 }
            goto L_0x0593
        L_0x0588:
            com.alibaba.fastjson.JSONObject r7 = new com.alibaba.fastjson.JSONObject     // Catch:{ all -> 0x0676 }
            com.alibaba.fastjson.parser.Feature r8 = com.alibaba.fastjson.parser.Feature.OrderedField     // Catch:{ all -> 0x0676 }
            boolean r8 = r4.isEnabled((com.alibaba.fastjson.parser.Feature) r8)     // Catch:{ all -> 0x0676 }
            r7.<init>((boolean) r8)     // Catch:{ all -> 0x0676 }
        L_0x0593:
            if (r6 != 0) goto L_0x059a
            com.alibaba.fastjson.parser.ParseContext r8 = r1.setContext(r5, r7, r14)     // Catch:{ all -> 0x0676 }
            goto L_0x059b
        L_0x059a:
            r8 = 0
        L_0x059b:
            com.alibaba.fastjson.parser.deserializer.FieldTypeResolver r10 = r1.fieldTypeResolver     // Catch:{ all -> 0x0676 }
            if (r10 == 0) goto L_0x05bc
            if (r14 == 0) goto L_0x05a6
            java.lang.String r10 = r14.toString()     // Catch:{ all -> 0x0676 }
            goto L_0x05a7
        L_0x05a6:
            r10 = 0
        L_0x05a7:
            com.alibaba.fastjson.parser.deserializer.FieldTypeResolver r12 = r1.fieldTypeResolver     // Catch:{ all -> 0x0676 }
            java.lang.reflect.Type r10 = r12.resolve(r0, r10)     // Catch:{ all -> 0x0676 }
            if (r10 == 0) goto L_0x05bc
            com.alibaba.fastjson.parser.ParserConfig r12 = r1.config     // Catch:{ all -> 0x0676 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r12 = r12.getDeserializer((java.lang.reflect.Type) r10)     // Catch:{ all -> 0x0676 }
            java.lang.Object r10 = r12.deserialze(r1, r10, r14)     // Catch:{ all -> 0x0676 }
            r12 = r10
            r10 = 1
            goto L_0x05be
        L_0x05bc:
            r10 = r15
            r12 = 0
        L_0x05be:
            if (r10 != 0) goto L_0x05c4
            java.lang.Object r12 = r1.parseObject((java.util.Map) r7, (java.lang.Object) r14)     // Catch:{ all -> 0x0676 }
        L_0x05c4:
            if (r8 == 0) goto L_0x05ca
            if (r7 == r12) goto L_0x05ca
            r8.object = r0     // Catch:{ all -> 0x0676 }
        L_0x05ca:
            if (r14 == 0) goto L_0x05d3
            java.lang.String r7 = r14.toString()     // Catch:{ all -> 0x0676 }
            r1.checkMapResolve(r0, r7)     // Catch:{ all -> 0x0676 }
        L_0x05d3:
            r11.put(r14, r12)     // Catch:{ all -> 0x0676 }
            if (r6 == 0) goto L_0x05db
            r1.setContext(r12, r14)     // Catch:{ all -> 0x0676 }
        L_0x05db:
            int r7 = r4.token()     // Catch:{ all -> 0x0676 }
            r8 = 13
            if (r7 != r8) goto L_0x05ed
            r4.nextToken()     // Catch:{ all -> 0x0676 }
            r1.setContext(r5)     // Catch:{ all -> 0x0676 }
            r1.setContext(r5)
            return r0
        L_0x05ed:
            int r7 = r4.token()     // Catch:{ all -> 0x0676 }
            r8 = 16
            if (r7 != r8) goto L_0x0601
            if (r6 == 0) goto L_0x05fc
            r16.popContext()     // Catch:{ all -> 0x0676 }
            goto L_0x04bf
        L_0x05fc:
            r1.setContext(r5)     // Catch:{ all -> 0x0676 }
            goto L_0x04bf
        L_0x0601:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0676 }
            r2.<init>()     // Catch:{ all -> 0x0676 }
            java.lang.String r3 = "syntax error, "
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            java.lang.String r3 = r4.tokenName()     // Catch:{ all -> 0x0676 }
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0676 }
            r0.<init>(r2)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x061c:
            r4.nextToken()     // Catch:{ all -> 0x0676 }
            java.lang.Object r6 = r16.parse()     // Catch:{ all -> 0x0676 }
            r11.put(r14, r6)     // Catch:{ all -> 0x0676 }
            int r6 = r4.token()     // Catch:{ all -> 0x0676 }
            r8 = 13
            if (r6 != r8) goto L_0x0635
            r4.nextToken()     // Catch:{ all -> 0x0676 }
            r1.setContext(r5)
            return r0
        L_0x0635:
            int r6 = r4.token()     // Catch:{ all -> 0x0676 }
            r12 = 16
            if (r6 != r12) goto L_0x0643
        L_0x063d:
            r6 = r8
            r10 = r12
            r7 = 0
            r8 = 4
            goto L_0x007d
        L_0x0643:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0676 }
            r2.<init>()     // Catch:{ all -> 0x0676 }
            r2.append(r10)     // Catch:{ all -> 0x0676 }
            int r3 = r4.pos()     // Catch:{ all -> 0x0676 }
            r2.append(r3)     // Catch:{ all -> 0x0676 }
            r2.append(r7)     // Catch:{ all -> 0x0676 }
            r2.append(r14)     // Catch:{ all -> 0x0676 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0676 }
            r0.<init>(r2)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x0662:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            java.lang.String r2 = "object key level > 512"
            r0.<init>(r2)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x066a:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            r0.<init>(r8)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x0670:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0676 }
            r0.<init>(r8)     // Catch:{ all -> 0x0676 }
            throw r0     // Catch:{ all -> 0x0676 }
        L_0x0676:
            r0 = move-exception
            r1.setContext(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public ParserConfig getConfig() {
        return this.config;
    }

    public void setConfig(ParserConfig parserConfig) {
        this.config = parserConfig;
    }

    public <T> T parseObject(Class<T> cls) {
        return parseObject((Type) cls, (Object) null);
    }

    public <T> T parseObject(Type type) {
        return parseObject(type, (Object) null);
    }

    public <T> T parseObject(Type type, Object obj) {
        int i = this.lexer.token();
        if (i == 8) {
            this.lexer.nextToken();
            return null;
        }
        if (i == 4) {
            if (type == byte[].class) {
                Object bytesValue = this.lexer.bytesValue();
                this.lexer.nextToken();
                return bytesValue;
            } else if (type == char[].class) {
                String stringVal = this.lexer.stringVal();
                this.lexer.nextToken();
                return stringVal.toCharArray();
            }
        }
        ObjectDeserializer deserializer = this.config.getDeserializer(type);
        try {
            if (deserializer.getClass() != JavaBeanDeserializer.class) {
                return deserializer.deserialze(this, type, obj);
            }
            if (this.lexer.token() != 12) {
                if (this.lexer.token() != 14) {
                    throw new JSONException("syntax error,except start with { or [,but actually start with " + this.lexer.tokenName());
                }
            }
            return ((JavaBeanDeserializer) deserializer).deserialze(this, type, obj, 0);
        } catch (JSONException e) {
            throw e;
        } catch (Throwable th) {
            throw new JSONException(th.getMessage(), th);
        }
    }

    public <T> List<T> parseArray(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        parseArray((Class<?>) cls, (Collection) arrayList);
        return arrayList;
    }

    public void parseArray(Class<?> cls, Collection collection) {
        parseArray((Type) cls, collection);
    }

    public void parseArray(Type type, Collection collection) {
        parseArray(type, collection, (Object) null);
    }

    /* JADX INFO: finally extract failed */
    public void parseArray(Type type, Collection collection, Object obj) {
        ObjectDeserializer objectDeserializer;
        int i = this.lexer.token();
        if (i == 21 || i == 22) {
            this.lexer.nextToken();
            i = this.lexer.token();
        }
        if (i == 14) {
            if (Integer.TYPE == type) {
                objectDeserializer = IntegerCodec.instance;
                this.lexer.nextToken(2);
            } else if (String.class == type) {
                objectDeserializer = StringCodec.instance;
                this.lexer.nextToken(4);
            } else {
                objectDeserializer = this.config.getDeserializer(type);
                this.lexer.nextToken(objectDeserializer.getFastMatchToken());
            }
            ParseContext parseContext = this.context;
            setContext(collection, obj);
            int i2 = 0;
            while (true) {
                try {
                    if (this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                        while (this.lexer.token() == 16) {
                            this.lexer.nextToken();
                        }
                    }
                    if (this.lexer.token() == 15) {
                        setContext(parseContext);
                        this.lexer.nextToken(16);
                        return;
                    }
                    Object obj2 = null;
                    if (Integer.TYPE == type) {
                        collection.add(IntegerCodec.instance.deserialze(this, (Type) null, (Object) null));
                    } else if (String.class == type) {
                        if (this.lexer.token() == 4) {
                            obj2 = this.lexer.stringVal();
                            this.lexer.nextToken(16);
                        } else {
                            Object parse = parse();
                            if (parse != null) {
                                obj2 = parse.toString();
                            }
                        }
                        collection.add(obj2);
                    } else {
                        if (this.lexer.token() == 8) {
                            this.lexer.nextToken();
                        } else {
                            obj2 = objectDeserializer.deserialze(this, type, Integer.valueOf(i2));
                        }
                        collection.add(obj2);
                        checkListResolve(collection);
                    }
                    if (this.lexer.token() == 16) {
                        this.lexer.nextToken(objectDeserializer.getFastMatchToken());
                    }
                    i2++;
                } catch (Throwable th) {
                    setContext(parseContext);
                    throw th;
                }
            }
        } else {
            throw new JSONException("expect '[', but " + JSONToken.name(i) + ", " + this.lexer.info());
        }
    }

    public Object[] parseArray(Type[] typeArr) {
        Object obj;
        Class<?> cls;
        boolean z;
        Class<char[]> cls2;
        Type[] typeArr2 = typeArr;
        int i = 8;
        if (this.lexer.token() == 8) {
            this.lexer.nextToken(16);
            return null;
        }
        int i2 = 14;
        if (this.lexer.token() == 14) {
            Object[] objArr = new Object[typeArr2.length];
            if (typeArr2.length == 0) {
                this.lexer.nextToken(15);
                if (this.lexer.token() == 15) {
                    this.lexer.nextToken(16);
                    return new Object[0];
                }
                throw new JSONException("syntax error");
            }
            this.lexer.nextToken(2);
            int i3 = 0;
            while (i3 < typeArr2.length) {
                if (this.lexer.token() == i) {
                    this.lexer.nextToken(16);
                    obj = null;
                } else {
                    Type type = typeArr2[i3];
                    if (type == Integer.TYPE || type == Integer.class) {
                        if (this.lexer.token() == 2) {
                            obj = Integer.valueOf(this.lexer.intValue());
                            this.lexer.nextToken(16);
                        } else {
                            obj = TypeUtils.cast(parse(), type, this.config);
                        }
                    } else if (type != String.class) {
                        if (i3 != typeArr2.length - 1 || !(type instanceof Class) || (((cls2 = (Class) type) == byte[].class || cls2 == char[].class) && this.lexer.token() == 4)) {
                            cls = null;
                            z = false;
                        } else {
                            z = cls2.isArray();
                            cls = cls2.getComponentType();
                        }
                        if (!z || this.lexer.token() == i2) {
                            obj = this.config.getDeserializer(type).deserialze(this, type, Integer.valueOf(i3));
                        } else {
                            ArrayList arrayList = new ArrayList();
                            ObjectDeserializer deserializer = this.config.getDeserializer((Type) cls);
                            int fastMatchToken = deserializer.getFastMatchToken();
                            if (this.lexer.token() != 15) {
                                while (true) {
                                    arrayList.add(deserializer.deserialze(this, type, (Object) null));
                                    if (this.lexer.token() != 16) {
                                        break;
                                    }
                                    this.lexer.nextToken(fastMatchToken);
                                }
                                if (this.lexer.token() != 15) {
                                    throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                                }
                            }
                            obj = TypeUtils.cast((Object) arrayList, type, this.config);
                        }
                    } else if (this.lexer.token() == 4) {
                        obj = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        obj = TypeUtils.cast(parse(), type, this.config);
                    }
                }
                objArr[i3] = obj;
                if (this.lexer.token() == 15) {
                    break;
                } else if (this.lexer.token() == 16) {
                    if (i3 == typeArr2.length - 1) {
                        this.lexer.nextToken(15);
                    } else {
                        this.lexer.nextToken(2);
                    }
                    i3++;
                    i = 8;
                    i2 = 14;
                } else {
                    throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                }
            }
            if (this.lexer.token() == 15) {
                this.lexer.nextToken(16);
                return objArr;
            }
            throw new JSONException("syntax error");
        }
        throw new JSONException("syntax error : " + this.lexer.tokenName());
    }

    public void parseObject(Object obj) {
        Object obj2;
        Class<?> cls = obj.getClass();
        ObjectDeserializer deserializer = this.config.getDeserializer((Type) cls);
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        if (this.lexer.token() == 12 || this.lexer.token() == 16) {
            while (true) {
                String scanSymbol = this.lexer.scanSymbol(this.symbolTable);
                if (scanSymbol == null) {
                    if (this.lexer.token() == 13) {
                        this.lexer.nextToken(16);
                        return;
                    } else if (this.lexer.token() == 16 && this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    }
                }
                FieldDeserializer fieldDeserializer = javaBeanDeserializer != null ? javaBeanDeserializer.getFieldDeserializer(scanSymbol) : null;
                if (fieldDeserializer != null) {
                    Class<?> cls2 = fieldDeserializer.fieldInfo.fieldClass;
                    Type type = fieldDeserializer.fieldInfo.fieldType;
                    if (cls2 == Integer.TYPE) {
                        this.lexer.nextTokenWithColon(2);
                        obj2 = IntegerCodec.instance.deserialze(this, type, (Object) null);
                    } else if (cls2 == String.class) {
                        this.lexer.nextTokenWithColon(4);
                        obj2 = StringCodec.deserialze(this);
                    } else if (cls2 == Long.TYPE) {
                        this.lexer.nextTokenWithColon(2);
                        obj2 = LongCodec.instance.deserialze(this, type, (Object) null);
                    } else {
                        ObjectDeserializer deserializer2 = this.config.getDeserializer(cls2, type);
                        this.lexer.nextTokenWithColon(deserializer2.getFastMatchToken());
                        obj2 = deserializer2.deserialze(this, type, (Object) null);
                    }
                    fieldDeserializer.setValue(obj, obj2);
                    if (this.lexer.token() != 16 && this.lexer.token() == 13) {
                        this.lexer.nextToken(16);
                        return;
                    }
                } else if (this.lexer.isEnabled(Feature.IgnoreNotMatch)) {
                    this.lexer.nextTokenWithColon();
                    parse();
                    if (this.lexer.token() == 13) {
                        this.lexer.nextToken();
                        return;
                    }
                } else {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + scanSymbol);
                }
            }
        } else {
            throw new JSONException("syntax error, expect {, actual " + this.lexer.tokenName());
        }
    }

    public Object parseArrayWithType(Type type) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length == 1) {
            Type type2 = actualTypeArguments[0];
            if (type2 instanceof Class) {
                ArrayList arrayList = new ArrayList();
                parseArray((Class<?>) (Class) type2, (Collection) arrayList);
                return arrayList;
            } else if (type2 instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type2;
                Type type3 = wildcardType.getUpperBounds()[0];
                if (!Object.class.equals(type3)) {
                    ArrayList arrayList2 = new ArrayList();
                    parseArray((Class<?>) (Class) type3, (Collection) arrayList2);
                    return arrayList2;
                } else if (wildcardType.getLowerBounds().length == 0) {
                    return parse();
                } else {
                    throw new JSONException("not support type : " + type);
                }
            } else {
                if (type2 instanceof TypeVariable) {
                    TypeVariable typeVariable = (TypeVariable) type2;
                    Type[] bounds = typeVariable.getBounds();
                    if (bounds.length == 1) {
                        Type type4 = bounds[0];
                        if (type4 instanceof Class) {
                            ArrayList arrayList3 = new ArrayList();
                            parseArray((Class<?>) (Class) type4, (Collection) arrayList3);
                            return arrayList3;
                        }
                    } else {
                        throw new JSONException("not support : " + typeVariable);
                    }
                }
                if (type2 instanceof ParameterizedType) {
                    ArrayList arrayList4 = new ArrayList();
                    parseArray((Type) (ParameterizedType) type2, (Collection) arrayList4);
                    return arrayList4;
                }
                throw new JSONException("TODO : " + type);
            }
        } else {
            throw new JSONException("not support type " + type);
        }
    }

    public void acceptType(String str) {
        JSONLexer jSONLexer = this.lexer;
        jSONLexer.nextTokenWithColon();
        if (jSONLexer.token() != 4) {
            throw new JSONException("type not match error");
        } else if (str.equals(jSONLexer.stringVal())) {
            jSONLexer.nextToken();
            if (jSONLexer.token() == 16) {
                jSONLexer.nextToken();
            }
        } else {
            throw new JSONException("type not match error");
        }
    }

    public int getResolveStatus() {
        return this.resolveStatus;
    }

    public void setResolveStatus(int i) {
        this.resolveStatus = i;
    }

    public Object getObject(String str) {
        for (int i = 0; i < this.contextArrayIndex; i++) {
            if (str.equals(this.contextArray[i].toString())) {
                return this.contextArray[i].object;
            }
        }
        return null;
    }

    public void checkListResolve(Collection collection) {
        if (this.resolveStatus != 1) {
            return;
        }
        if (collection instanceof List) {
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = new ResolveFieldDeserializer(this, (List) collection, collection.size() - 1);
            lastResolveTask.ownerContext = this.context;
            setResolveStatus(0);
            return;
        }
        ResolveTask lastResolveTask2 = getLastResolveTask();
        lastResolveTask2.fieldDeserializer = new ResolveFieldDeserializer(collection);
        lastResolveTask2.ownerContext = this.context;
        setResolveStatus(0);
    }

    public void checkMapResolve(Map map, Object obj) {
        if (this.resolveStatus == 1) {
            ResolveFieldDeserializer resolveFieldDeserializer = new ResolveFieldDeserializer(map, obj);
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = resolveFieldDeserializer;
            lastResolveTask.ownerContext = this.context;
            setResolveStatus(0);
        }
    }

    public Object parseObject(Map map) {
        return parseObject(map, (Object) null);
    }

    public JSONObject parseObject() {
        Object parseObject = parseObject((Map) new JSONObject(this.lexer.isEnabled(Feature.OrderedField)));
        if (parseObject instanceof JSONObject) {
            return (JSONObject) parseObject;
        }
        if (parseObject == null) {
            return null;
        }
        return new JSONObject((Map<String, Object>) (Map) parseObject);
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, (Object) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v15, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v20, resolved type: com.alibaba.fastjson.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v21, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void parseArray(java.util.Collection r10, java.lang.Object r11) {
        /*
            r9 = this;
            com.alibaba.fastjson.parser.JSONLexer r0 = r9.lexer
            int r1 = r0.token()
            r2 = 21
            if (r1 == r2) goto L_0x0012
            int r1 = r0.token()
            r2 = 22
            if (r1 != r2) goto L_0x0015
        L_0x0012:
            r0.nextToken()
        L_0x0015:
            int r1 = r0.token()
            r2 = 14
            if (r1 != r2) goto L_0x012d
            r1 = 4
            r0.nextToken(r1)
            com.alibaba.fastjson.parser.ParseContext r3 = r9.context
            if (r3 == 0) goto L_0x0034
            int r3 = r3.level
            r4 = 512(0x200, float:7.175E-43)
            if (r3 > r4) goto L_0x002c
            goto L_0x0034
        L_0x002c:
            com.alibaba.fastjson.JSONException r10 = new com.alibaba.fastjson.JSONException
            java.lang.String r11 = "array level > 512"
            r10.<init>(r11)
            throw r10
        L_0x0034:
            com.alibaba.fastjson.parser.ParseContext r3 = r9.context
            r9.setContext(r10, r11)
            r11 = 0
            r4 = r11
        L_0x003b:
            com.alibaba.fastjson.parser.Feature r5 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x0128 }
            boolean r5 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r5)     // Catch:{ all -> 0x0128 }
            r6 = 16
            if (r5 == 0) goto L_0x004f
        L_0x0045:
            int r5 = r0.token()     // Catch:{ all -> 0x0128 }
            if (r5 != r6) goto L_0x004f
            r0.nextToken()     // Catch:{ all -> 0x0128 }
            goto L_0x0045
        L_0x004f:
            int r5 = r0.token()     // Catch:{ all -> 0x0128 }
            r7 = 2
            if (r5 == r7) goto L_0x010e
            r7 = 3
            if (r5 == r7) goto L_0x00f7
            if (r5 == r1) goto L_0x00d0
            r7 = 6
            if (r5 == r7) goto L_0x00ca
            r7 = 7
            if (r5 == r7) goto L_0x00c4
            r7 = 8
            r8 = 0
            if (r5 == r7) goto L_0x00c0
            r7 = 12
            if (r5 == r7) goto L_0x00ac
            r7 = 20
            if (r5 == r7) goto L_0x00a4
            r7 = 23
            if (r5 == r7) goto L_0x009f
            if (r5 == r2) goto L_0x0085
            r7 = 15
            if (r5 == r7) goto L_0x007e
            java.lang.Object r8 = r9.parse()     // Catch:{ all -> 0x0128 }
            goto L_0x0115
        L_0x007e:
            r0.nextToken(r6)     // Catch:{ all -> 0x0128 }
            r9.setContext(r3)
            return
        L_0x0085:
            com.alibaba.fastjson.JSONArray r8 = new com.alibaba.fastjson.JSONArray     // Catch:{ all -> 0x0128 }
            r8.<init>()     // Catch:{ all -> 0x0128 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0128 }
            r9.parseArray((java.util.Collection) r8, (java.lang.Object) r5)     // Catch:{ all -> 0x0128 }
            com.alibaba.fastjson.parser.Feature r5 = com.alibaba.fastjson.parser.Feature.UseObjectArray     // Catch:{ all -> 0x0128 }
            boolean r5 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r5)     // Catch:{ all -> 0x0128 }
            if (r5 == 0) goto L_0x0115
            java.lang.Object[] r8 = r8.toArray()     // Catch:{ all -> 0x0128 }
            goto L_0x0115
        L_0x009f:
            r0.nextToken(r1)     // Catch:{ all -> 0x0128 }
            goto L_0x0115
        L_0x00a4:
            com.alibaba.fastjson.JSONException r10 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0128 }
            java.lang.String r11 = "unclosed jsonArray"
            r10.<init>(r11)     // Catch:{ all -> 0x0128 }
            throw r10     // Catch:{ all -> 0x0128 }
        L_0x00ac:
            com.alibaba.fastjson.JSONObject r5 = new com.alibaba.fastjson.JSONObject     // Catch:{ all -> 0x0128 }
            com.alibaba.fastjson.parser.Feature r7 = com.alibaba.fastjson.parser.Feature.OrderedField     // Catch:{ all -> 0x0128 }
            boolean r7 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r7)     // Catch:{ all -> 0x0128 }
            r5.<init>((boolean) r7)     // Catch:{ all -> 0x0128 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0128 }
            java.lang.Object r8 = r9.parseObject((java.util.Map) r5, (java.lang.Object) r7)     // Catch:{ all -> 0x0128 }
            goto L_0x0115
        L_0x00c0:
            r0.nextToken(r1)     // Catch:{ all -> 0x0128 }
            goto L_0x0115
        L_0x00c4:
            java.lang.Boolean r8 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0128 }
            r0.nextToken(r6)     // Catch:{ all -> 0x0128 }
            goto L_0x0115
        L_0x00ca:
            java.lang.Boolean r8 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0128 }
            r0.nextToken(r6)     // Catch:{ all -> 0x0128 }
            goto L_0x0115
        L_0x00d0:
            java.lang.String r8 = r0.stringVal()     // Catch:{ all -> 0x0128 }
            r0.nextToken(r6)     // Catch:{ all -> 0x0128 }
            com.alibaba.fastjson.parser.Feature r5 = com.alibaba.fastjson.parser.Feature.AllowISO8601DateFormat     // Catch:{ all -> 0x0128 }
            boolean r5 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r5)     // Catch:{ all -> 0x0128 }
            if (r5 == 0) goto L_0x0115
            com.alibaba.fastjson.parser.JSONScanner r5 = new com.alibaba.fastjson.parser.JSONScanner     // Catch:{ all -> 0x0128 }
            r5.<init>(r8)     // Catch:{ all -> 0x0128 }
            boolean r7 = r5.scanISO8601DateIfMatch()     // Catch:{ all -> 0x0128 }
            if (r7 == 0) goto L_0x00f3
            java.util.Calendar r7 = r5.getCalendar()     // Catch:{ all -> 0x0128 }
            java.util.Date r7 = r7.getTime()     // Catch:{ all -> 0x0128 }
            r8 = r7
        L_0x00f3:
            r5.close()     // Catch:{ all -> 0x0128 }
            goto L_0x0115
        L_0x00f7:
            com.alibaba.fastjson.parser.Feature r5 = com.alibaba.fastjson.parser.Feature.UseBigDecimal     // Catch:{ all -> 0x0128 }
            boolean r5 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r5)     // Catch:{ all -> 0x0128 }
            if (r5 == 0) goto L_0x0105
            r5 = 1
            java.lang.Number r5 = r0.decimalValue(r5)     // Catch:{ all -> 0x0128 }
            goto L_0x0109
        L_0x0105:
            java.lang.Number r5 = r0.decimalValue(r11)     // Catch:{ all -> 0x0128 }
        L_0x0109:
            r8 = r5
            r0.nextToken(r6)     // Catch:{ all -> 0x0128 }
            goto L_0x0115
        L_0x010e:
            java.lang.Number r8 = r0.integerValue()     // Catch:{ all -> 0x0128 }
            r0.nextToken(r6)     // Catch:{ all -> 0x0128 }
        L_0x0115:
            r10.add(r8)     // Catch:{ all -> 0x0128 }
            r9.checkListResolve(r10)     // Catch:{ all -> 0x0128 }
            int r5 = r0.token()     // Catch:{ all -> 0x0128 }
            if (r5 != r6) goto L_0x0124
            r0.nextToken(r1)     // Catch:{ all -> 0x0128 }
        L_0x0124:
            int r4 = r4 + 1
            goto L_0x003b
        L_0x0128:
            r10 = move-exception
            r9.setContext(r3)
            throw r10
        L_0x012d:
            com.alibaba.fastjson.JSONException r10 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "syntax error, expect [, actual "
            r1.<init>(r2)
            int r2 = r0.token()
            java.lang.String r2 = com.alibaba.fastjson.parser.JSONToken.name(r2)
            r1.append(r2)
            java.lang.String r2 = ", pos "
            r1.append(r2)
            int r0 = r0.pos()
            r1.append(r0)
            java.lang.String r0 = ", fieldName "
            r1.append(r0)
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseArray(java.util.Collection, java.lang.Object):void");
    }

    public ParseContext getContext() {
        return this.context;
    }

    public List<ResolveTask> getResolveTaskList() {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        return this.resolveTaskList;
    }

    public void addResolveTask(ResolveTask resolveTask) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(resolveTask);
    }

    public ResolveTask getLastResolveTask() {
        List<ResolveTask> list = this.resolveTaskList;
        return list.get(list.size() - 1);
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    public FieldTypeResolver getFieldTypeResolver() {
        return this.fieldTypeResolver;
    }

    public void setFieldTypeResolver(FieldTypeResolver fieldTypeResolver2) {
        this.fieldTypeResolver = fieldTypeResolver2;
    }

    public void setContext(ParseContext parseContext) {
        if (!this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            this.context = parseContext;
        }
    }

    public void popContext() {
        if (!this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            this.context = this.context.parent;
            int i = this.contextArrayIndex;
            if (i > 0) {
                int i2 = i - 1;
                this.contextArrayIndex = i2;
                this.contextArray[i2] = null;
            }
        }
    }

    public ParseContext setContext(Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        return setContext(this.context, obj, obj2);
    }

    public ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        ParseContext parseContext2 = new ParseContext(parseContext, obj, obj2);
        this.context = parseContext2;
        addContext(parseContext2);
        return this.context;
    }

    private void addContext(ParseContext parseContext) {
        int i = this.contextArrayIndex;
        this.contextArrayIndex = i + 1;
        ParseContext[] parseContextArr = this.contextArray;
        if (parseContextArr == null) {
            this.contextArray = new ParseContext[8];
        } else if (i >= parseContextArr.length) {
            ParseContext[] parseContextArr2 = new ParseContext[((parseContextArr.length * 3) / 2)];
            System.arraycopy(parseContextArr, 0, parseContextArr2, 0, parseContextArr.length);
            this.contextArray = parseContextArr2;
        }
        this.contextArray[i] = parseContext;
    }

    public Object parse() {
        return parse((Object) null);
    }

    public Object parseKey() {
        if (this.lexer.token() != 18) {
            return parse((Object) null);
        }
        String stringVal = this.lexer.stringVal();
        this.lexer.nextToken(16);
        return stringVal;
    }

    public Object parse(Object obj) {
        JSONLexer jSONLexer = this.lexer;
        int i = jSONLexer.token();
        if (i == 2) {
            Number integerValue = jSONLexer.integerValue();
            jSONLexer.nextToken();
            return integerValue;
        } else if (i == 3) {
            Number decimalValue = jSONLexer.decimalValue(jSONLexer.isEnabled(Feature.UseBigDecimal));
            jSONLexer.nextToken();
            return decimalValue;
        } else if (i == 4) {
            String stringVal = jSONLexer.stringVal();
            jSONLexer.nextToken(16);
            if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                JSONScanner jSONScanner = new JSONScanner(stringVal);
                try {
                    if (jSONScanner.scanISO8601DateIfMatch()) {
                        return jSONScanner.getCalendar().getTime();
                    }
                    jSONScanner.close();
                } finally {
                    jSONScanner.close();
                }
            }
            return stringVal;
        } else if (i == 12) {
            return parseObject((Map) new JSONObject(jSONLexer.isEnabled(Feature.OrderedField)), obj);
        } else {
            if (i == 14) {
                JSONArray jSONArray = new JSONArray();
                parseArray((Collection) jSONArray, obj);
                return jSONLexer.isEnabled(Feature.UseObjectArray) ? jSONArray.toArray() : jSONArray;
            } else if (i != 18) {
                if (i != 26) {
                    switch (i) {
                        case 6:
                            jSONLexer.nextToken();
                            return Boolean.TRUE;
                        case 7:
                            jSONLexer.nextToken();
                            return Boolean.FALSE;
                        case 8:
                            jSONLexer.nextToken();
                            return null;
                        case 9:
                            jSONLexer.nextToken(18);
                            if (jSONLexer.token() == 18) {
                                jSONLexer.nextToken(10);
                                accept(10);
                                long longValue = jSONLexer.integerValue().longValue();
                                accept(2);
                                accept(11);
                                return new Date(longValue);
                            }
                            throw new JSONException("syntax error");
                        default:
                            switch (i) {
                                case 20:
                                    if (jSONLexer.isBlankInput()) {
                                        return null;
                                    }
                                    throw new JSONException("unterminated json string, " + jSONLexer.info());
                                case 21:
                                    jSONLexer.nextToken();
                                    HashSet hashSet = new HashSet();
                                    parseArray((Collection) hashSet, obj);
                                    return hashSet;
                                case 22:
                                    jSONLexer.nextToken();
                                    TreeSet treeSet = new TreeSet();
                                    parseArray((Collection) treeSet, obj);
                                    return treeSet;
                                case 23:
                                    jSONLexer.nextToken();
                                    return null;
                                default:
                                    throw new JSONException("syntax error, " + jSONLexer.info());
                            }
                    }
                } else {
                    byte[] bytesValue = jSONLexer.bytesValue();
                    jSONLexer.nextToken();
                    return bytesValue;
                }
            } else if ("NaN".equals(jSONLexer.stringVal())) {
                jSONLexer.nextToken();
                return null;
            } else {
                throw new JSONException("syntax error, " + jSONLexer.info());
            }
        }
    }

    public void config(Feature feature, boolean z) {
        this.lexer.config(feature, z);
    }

    public boolean isEnabled(Feature feature) {
        return this.lexer.isEnabled(feature);
    }

    public JSONLexer getLexer() {
        return this.lexer;
    }

    public final void accept(int i) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i) {
            jSONLexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(jSONLexer.token()));
    }

    public final void accept(int i, int i2) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i) {
            jSONLexer.nextToken(i2);
        } else {
            throwException(i);
        }
    }

    public void throwException(int i) {
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(this.lexer.token()));
    }

    public void close() {
        JSONLexer jSONLexer = this.lexer;
        try {
            if (jSONLexer.isEnabled(Feature.AutoCloseSource)) {
                if (jSONLexer.token() != 20) {
                    throw new JSONException("not close json text, token : " + JSONToken.name(jSONLexer.token()));
                }
            }
        } finally {
            jSONLexer.close();
        }
    }

    public Object resolveReference(String str) {
        if (this.contextArray == null) {
            return null;
        }
        int i = 0;
        while (true) {
            ParseContext[] parseContextArr = this.contextArray;
            if (i >= parseContextArr.length || i >= this.contextArrayIndex) {
                return null;
            }
            ParseContext parseContext = parseContextArr[i];
            if (parseContext.toString().equals(str)) {
                return parseContext.object;
            }
            i++;
        }
        return null;
    }

    public void handleResovleTask(Object obj) {
        Object obj2;
        List<ResolveTask> list = this.resolveTaskList;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ResolveTask resolveTask = this.resolveTaskList.get(i);
                String str = resolveTask.referenceValue;
                Object obj3 = resolveTask.ownerContext != null ? resolveTask.ownerContext.object : null;
                if (str.startsWith("$")) {
                    obj2 = getObject(str);
                    if (obj2 == null) {
                        try {
                            JSONPath compile = JSONPath.compile(str);
                            if (compile.isRef()) {
                                obj2 = compile.eval(obj);
                            }
                        } catch (JSONPathException unused) {
                        }
                    }
                } else {
                    obj2 = resolveTask.context.object;
                }
                FieldDeserializer fieldDeserializer = resolveTask.fieldDeserializer;
                if (fieldDeserializer != null) {
                    if (obj2 != null && obj2.getClass() == JSONObject.class && fieldDeserializer.fieldInfo != null && !Map.class.isAssignableFrom(fieldDeserializer.fieldInfo.fieldClass)) {
                        Object obj4 = this.contextArray[0].object;
                        JSONPath compile2 = JSONPath.compile(str);
                        if (compile2.isRef()) {
                            obj2 = compile2.eval(obj4);
                        }
                    }
                    fieldDeserializer.setValue(obj3, obj2);
                }
            }
        }
    }

    public static class ResolveTask {
        public final ParseContext context;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;
        public final String referenceValue;

        public ResolveTask(ParseContext parseContext, String str) {
            this.context = parseContext;
            this.referenceValue = str;
        }
    }

    public void parseExtra(Object obj, String str) {
        Object obj2;
        this.lexer.nextTokenWithColon();
        List<ExtraTypeProvider> list = this.extraTypeProviders;
        Type type = null;
        if (list != null) {
            for (ExtraTypeProvider extraType : list) {
                type = extraType.getExtraType(obj, str);
            }
        }
        if (type == null) {
            obj2 = parse();
        } else {
            obj2 = parseObject(type);
        }
        if (obj instanceof ExtraProcessable) {
            ((ExtraProcessable) obj).processExtra(str, obj2);
            return;
        }
        List<ExtraProcessor> list2 = this.extraProcessors;
        if (list2 != null) {
            for (ExtraProcessor processExtra : list2) {
                processExtra.processExtra(obj, str, obj2);
            }
        }
        if (this.resolveStatus == 1) {
            this.resolveStatus = 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        r11 = r10.config.getDeserializer((java.lang.reflect.Type) r1);
        r10.lexer.nextToken(16);
        setResolveStatus(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01d4, code lost:
        if (r0 == null) goto L_0x01dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01d8, code lost:
        if ((r12 instanceof java.lang.Integer) != false) goto L_0x01dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01da, code lost:
        popContext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01dd, code lost:
        r11 = (java.util.Map) r11.deserialze(r10, r1, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01e3, code lost:
        setContext(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01e6, code lost:
        return r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object parse(com.alibaba.fastjson.parser.deserializer.PropertyProcessable r11, java.lang.Object r12) {
        /*
            r10 = this;
            com.alibaba.fastjson.parser.JSONLexer r0 = r10.lexer
            int r0 = r0.token()
            r1 = 12
            r2 = 0
            if (r0 == r1) goto L_0x0089
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r0 = "syntax error, expect {, actual "
            r11.<init>(r0)
            com.alibaba.fastjson.parser.JSONLexer r0 = r10.lexer
            java.lang.String r0 = r0.tokenName()
            r11.append(r0)
            java.lang.String r11 = r11.toString()
            boolean r0 = r12 instanceof java.lang.String
            if (r0 == 0) goto L_0x0043
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r11)
            java.lang.String r11 = ", fieldName "
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r11)
            r0.append(r12)
            java.lang.String r11 = r0.toString()
        L_0x0043:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r11)
            java.lang.String r11 = ", "
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r11)
            com.alibaba.fastjson.parser.JSONLexer r11 = r10.lexer
            java.lang.String r11 = r11.info()
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            com.alibaba.fastjson.JSONArray r0 = new com.alibaba.fastjson.JSONArray
            r0.<init>()
            r10.parseArray((java.util.Collection) r0, (java.lang.Object) r12)
            int r12 = r0.size()
            r1 = 1
            if (r12 != r1) goto L_0x0083
            java.lang.Object r12 = r0.get(r2)
            boolean r0 = r12 instanceof com.alibaba.fastjson.JSONObject
            if (r0 == 0) goto L_0x0083
            com.alibaba.fastjson.JSONObject r12 = (com.alibaba.fastjson.JSONObject) r12
            return r12
        L_0x0083:
            com.alibaba.fastjson.JSONException r12 = new com.alibaba.fastjson.JSONException
            r12.<init>(r11)
            throw r12
        L_0x0089:
            com.alibaba.fastjson.parser.ParseContext r0 = r10.context
        L_0x008b:
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            r1.skipWhitespace()     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            char r1 = r1.getCurrent()     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r3 = r10.lexer     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.Feature r4 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x025d }
            boolean r3 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r4)     // Catch:{ all -> 0x025d }
            if (r3 == 0) goto L_0x00b5
        L_0x00a0:
            r3 = 44
            if (r1 != r3) goto L_0x00b5
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            r1.next()     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            r1.skipWhitespace()     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            char r1 = r1.getCurrent()     // Catch:{ all -> 0x025d }
            goto L_0x00a0
        L_0x00b5:
            java.lang.String r3 = "expect ':' at "
            r4 = 58
            r5 = 34
            r6 = 16
            if (r1 != r5) goto L_0x00f1
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.SymbolTable r7 = r10.symbolTable     // Catch:{ all -> 0x025d }
            java.lang.String r1 = r1.scanSymbol(r7, r5)     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r7 = r10.lexer     // Catch:{ all -> 0x025d }
            r7.skipWhitespace()     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r7 = r10.lexer     // Catch:{ all -> 0x025d }
            char r7 = r7.getCurrent()     // Catch:{ all -> 0x025d }
            if (r7 != r4) goto L_0x00d6
            goto L_0x016e
        L_0x00d6:
            com.alibaba.fastjson.JSONException r11 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x025d }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x025d }
            r12.<init>()     // Catch:{ all -> 0x025d }
            r12.append(r3)     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            int r1 = r1.pos()     // Catch:{ all -> 0x025d }
            r12.append(r1)     // Catch:{ all -> 0x025d }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x025d }
            r11.<init>(r12)     // Catch:{ all -> 0x025d }
            throw r11     // Catch:{ all -> 0x025d }
        L_0x00f1:
            r7 = 125(0x7d, float:1.75E-43)
            if (r1 != r7) goto L_0x0108
            com.alibaba.fastjson.parser.JSONLexer r12 = r10.lexer     // Catch:{ all -> 0x025d }
            r12.next()     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r12 = r10.lexer     // Catch:{ all -> 0x025d }
            r12.resetStringPosition()     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r12 = r10.lexer     // Catch:{ all -> 0x025d }
            r12.nextToken(r6)     // Catch:{ all -> 0x025d }
            r10.setContext(r0)
            return r11
        L_0x0108:
            java.lang.String r7 = "syntax error"
            r8 = 39
            if (r1 != r8) goto L_0x014f
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.Feature r9 = com.alibaba.fastjson.parser.Feature.AllowSingleQuotes     // Catch:{ all -> 0x025d }
            boolean r1 = r1.isEnabled((com.alibaba.fastjson.parser.Feature) r9)     // Catch:{ all -> 0x025d }
            if (r1 == 0) goto L_0x0149
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.SymbolTable r7 = r10.symbolTable     // Catch:{ all -> 0x025d }
            java.lang.String r1 = r1.scanSymbol(r7, r8)     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r7 = r10.lexer     // Catch:{ all -> 0x025d }
            r7.skipWhitespace()     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r7 = r10.lexer     // Catch:{ all -> 0x025d }
            char r7 = r7.getCurrent()     // Catch:{ all -> 0x025d }
            if (r7 != r4) goto L_0x012e
            goto L_0x016e
        L_0x012e:
            com.alibaba.fastjson.JSONException r11 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x025d }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x025d }
            r12.<init>()     // Catch:{ all -> 0x025d }
            r12.append(r3)     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            int r1 = r1.pos()     // Catch:{ all -> 0x025d }
            r12.append(r1)     // Catch:{ all -> 0x025d }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x025d }
            r11.<init>(r12)     // Catch:{ all -> 0x025d }
            throw r11     // Catch:{ all -> 0x025d }
        L_0x0149:
            com.alibaba.fastjson.JSONException r11 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x025d }
            r11.<init>(r7)     // Catch:{ all -> 0x025d }
            throw r11     // Catch:{ all -> 0x025d }
        L_0x014f:
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.Feature r8 = com.alibaba.fastjson.parser.Feature.AllowUnQuotedFieldNames     // Catch:{ all -> 0x025d }
            boolean r1 = r1.isEnabled((com.alibaba.fastjson.parser.Feature) r8)     // Catch:{ all -> 0x025d }
            if (r1 == 0) goto L_0x0257
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.SymbolTable r7 = r10.symbolTable     // Catch:{ all -> 0x025d }
            java.lang.String r1 = r1.scanSymbolUnQuoted(r7)     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r7 = r10.lexer     // Catch:{ all -> 0x025d }
            r7.skipWhitespace()     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r7 = r10.lexer     // Catch:{ all -> 0x025d }
            char r7 = r7.getCurrent()     // Catch:{ all -> 0x025d }
            if (r7 != r4) goto L_0x0234
        L_0x016e:
            com.alibaba.fastjson.parser.JSONLexer r3 = r10.lexer     // Catch:{ all -> 0x025d }
            r3.next()     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r3 = r10.lexer     // Catch:{ all -> 0x025d }
            r3.skipWhitespace()     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r3 = r10.lexer     // Catch:{ all -> 0x025d }
            r3.getCurrent()     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r3 = r10.lexer     // Catch:{ all -> 0x025d }
            r3.resetStringPosition()     // Catch:{ all -> 0x025d }
            java.lang.String r3 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x025d }
            r4 = 13
            r7 = 0
            if (r1 != r3) goto L_0x01e7
            com.alibaba.fastjson.parser.JSONLexer r3 = r10.lexer     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.Feature r8 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x025d }
            boolean r3 = r3.isEnabled((com.alibaba.fastjson.parser.Feature) r8)     // Catch:{ all -> 0x025d }
            if (r3 != 0) goto L_0x01e7
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.SymbolTable r3 = r10.symbolTable     // Catch:{ all -> 0x025d }
            java.lang.String r1 = r1.scanSymbol(r3, r5)     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.ParserConfig r3 = r10.config     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r5 = r10.lexer     // Catch:{ all -> 0x025d }
            int r5 = r5.getFeatures()     // Catch:{ all -> 0x025d }
            java.lang.Class r1 = r3.checkAutoType(r1, r7, r5)     // Catch:{ all -> 0x025d }
            java.lang.Class<java.util.Map> r3 = java.util.Map.class
            boolean r3 = r3.isAssignableFrom(r1)     // Catch:{ all -> 0x025d }
            if (r3 == 0) goto L_0x01c5
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            r1.nextToken(r6)     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            int r1 = r1.token()     // Catch:{ all -> 0x025d }
            if (r1 != r4) goto L_0x022c
            com.alibaba.fastjson.parser.JSONLexer r12 = r10.lexer     // Catch:{ all -> 0x025d }
            r12.nextToken(r6)     // Catch:{ all -> 0x025d }
            r10.setContext(r0)
            return r11
        L_0x01c5:
            com.alibaba.fastjson.parser.ParserConfig r11 = r10.config     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r11 = r11.getDeserializer((java.lang.reflect.Type) r1)     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r2 = r10.lexer     // Catch:{ all -> 0x025d }
            r2.nextToken(r6)     // Catch:{ all -> 0x025d }
            r2 = 2
            r10.setResolveStatus(r2)     // Catch:{ all -> 0x025d }
            if (r0 == 0) goto L_0x01dd
            boolean r2 = r12 instanceof java.lang.Integer     // Catch:{ all -> 0x025d }
            if (r2 != 0) goto L_0x01dd
            r10.popContext()     // Catch:{ all -> 0x025d }
        L_0x01dd:
            java.lang.Object r11 = r11.deserialze(r10, r1, r12)     // Catch:{ all -> 0x025d }
            java.util.Map r11 = (java.util.Map) r11     // Catch:{ all -> 0x025d }
            r10.setContext(r0)
            return r11
        L_0x01e7:
            com.alibaba.fastjson.parser.JSONLexer r3 = r10.lexer     // Catch:{ all -> 0x025d }
            r3.nextToken()     // Catch:{ all -> 0x025d }
            if (r2 == 0) goto L_0x01f1
            r10.setContext(r0)     // Catch:{ all -> 0x025d }
        L_0x01f1:
            java.lang.reflect.Type r3 = r11.getType(r1)     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r5 = r10.lexer     // Catch:{ all -> 0x025d }
            int r5 = r5.token()     // Catch:{ all -> 0x025d }
            r6 = 8
            if (r5 != r6) goto L_0x0205
            com.alibaba.fastjson.parser.JSONLexer r3 = r10.lexer     // Catch:{ all -> 0x025d }
            r3.nextToken()     // Catch:{ all -> 0x025d }
            goto L_0x0209
        L_0x0205:
            java.lang.Object r7 = r10.parseObject((java.lang.reflect.Type) r3, (java.lang.Object) r1)     // Catch:{ all -> 0x025d }
        L_0x0209:
            r11.apply(r1, r7)     // Catch:{ all -> 0x025d }
            r10.setContext(r0, r7, r1)     // Catch:{ all -> 0x025d }
            r10.setContext(r0)     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            int r1 = r1.token()     // Catch:{ all -> 0x025d }
            r3 = 20
            if (r1 == r3) goto L_0x0230
            r3 = 15
            if (r1 != r3) goto L_0x0221
            goto L_0x0230
        L_0x0221:
            if (r1 != r4) goto L_0x022c
            com.alibaba.fastjson.parser.JSONLexer r12 = r10.lexer     // Catch:{ all -> 0x025d }
            r12.nextToken()     // Catch:{ all -> 0x025d }
            r10.setContext(r0)
            return r11
        L_0x022c:
            int r2 = r2 + 1
            goto L_0x008b
        L_0x0230:
            r10.setContext(r0)
            return r11
        L_0x0234:
            com.alibaba.fastjson.JSONException r11 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x025d }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x025d }
            r12.<init>()     // Catch:{ all -> 0x025d }
            r12.append(r3)     // Catch:{ all -> 0x025d }
            com.alibaba.fastjson.parser.JSONLexer r1 = r10.lexer     // Catch:{ all -> 0x025d }
            int r1 = r1.pos()     // Catch:{ all -> 0x025d }
            r12.append(r1)     // Catch:{ all -> 0x025d }
            java.lang.String r1 = ", actual "
            r12.append(r1)     // Catch:{ all -> 0x025d }
            r12.append(r7)     // Catch:{ all -> 0x025d }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x025d }
            r11.<init>(r12)     // Catch:{ all -> 0x025d }
            throw r11     // Catch:{ all -> 0x025d }
        L_0x0257:
            com.alibaba.fastjson.JSONException r11 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x025d }
            r11.<init>(r7)     // Catch:{ all -> 0x025d }
            throw r11     // Catch:{ all -> 0x025d }
        L_0x025d:
            r11 = move-exception
            r10.setContext(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parse(com.alibaba.fastjson.parser.deserializer.PropertyProcessable, java.lang.Object):java.lang.Object");
    }
}
