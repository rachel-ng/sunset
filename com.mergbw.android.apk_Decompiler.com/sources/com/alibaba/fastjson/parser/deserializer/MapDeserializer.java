package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapDeserializer implements ObjectDeserializer {
    public static MapDeserializer instance = new MapDeserializer();

    public int getFastMatchToken() {
        return 12;
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Map<Object, Object> map;
        if (type == JSONObject.class && defaultJSONParser.getFieldTypeResolver() == null) {
            return defaultJSONParser.parseObject();
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken(16);
            return null;
        }
        boolean z = (type instanceof Class) && "java.util.Collections$UnmodifiableMap".equals(((Class) type).getName());
        if ((jSONLexer.getFeatures() & Feature.OrderedField.mask) != 0) {
            map = createMap(type, jSONLexer.getFeatures());
        } else {
            map = createMap(type);
        }
        ParseContext context = defaultJSONParser.getContext();
        try {
            defaultJSONParser.setContext(context, map, obj);
            T deserialze = deserialze(defaultJSONParser, type, obj, map);
            if (z) {
                deserialze = Collections.unmodifiableMap((Map) deserialze);
            }
            return deserialze;
        } finally {
            defaultJSONParser.setContext(context);
        }
    }

    /* access modifiers changed from: protected */
    public Object deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, Map map) {
        Type type2;
        if (!(type instanceof ParameterizedType)) {
            return defaultJSONParser.parseObject(map, obj);
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type type3 = parameterizedType.getActualTypeArguments()[0];
        if (map.getClass().getName().equals("org.springframework.util.LinkedMultiValueMap")) {
            type2 = List.class;
        } else {
            type2 = parameterizedType.getActualTypeArguments()[1];
        }
        if (String.class == type3) {
            return parseMap(defaultJSONParser, map, type2, obj);
        }
        return parseMap(defaultJSONParser, map, type3, type2, obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        r11 = r4.getDeserializer((java.lang.reflect.Type) r2);
        r0.nextToken(16);
        r10.setResolveStatus(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x019b, code lost:
        if (r1 == null) goto L_0x01a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x019f, code lost:
        if ((r13 instanceof java.lang.Integer) != false) goto L_0x01a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01a1, code lost:
        r10.popContext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01a4, code lost:
        r11 = (java.util.Map) r11.deserialze(r10, r2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01aa, code lost:
        r10.setContext(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01ad, code lost:
        return r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map parseMap(com.alibaba.fastjson.parser.DefaultJSONParser r10, java.util.Map<java.lang.String, java.lang.Object> r11, java.lang.reflect.Type r12, java.lang.Object r13) {
        /*
            com.alibaba.fastjson.parser.JSONLexer r0 = r10.lexer
            int r1 = r0.token()
            r2 = 12
            r3 = 0
            if (r1 == r2) goto L_0x0088
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "syntax error, expect {, actual "
            r11.<init>(r12)
            java.lang.String r12 = r0.tokenName()
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            boolean r12 = r13 instanceof java.lang.String
            if (r12 == 0) goto L_0x0041
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r11)
            java.lang.String r11 = ", fieldName "
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r11)
            r12.append(r13)
            java.lang.String r11 = r12.toString()
        L_0x0041:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r11)
            java.lang.String r11 = ", "
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r11)
            java.lang.String r11 = r0.info()
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            r12 = 4
            if (r1 == r12) goto L_0x0082
            com.alibaba.fastjson.JSONArray r12 = new com.alibaba.fastjson.JSONArray
            r12.<init>()
            r10.parseArray((java.util.Collection) r12, (java.lang.Object) r13)
            int r10 = r12.size()
            r13 = 1
            if (r10 != r13) goto L_0x0082
            java.lang.Object r10 = r12.get(r3)
            boolean r12 = r10 instanceof com.alibaba.fastjson.JSONObject
            if (r12 == 0) goto L_0x0082
            com.alibaba.fastjson.JSONObject r10 = (com.alibaba.fastjson.JSONObject) r10
            return r10
        L_0x0082:
            com.alibaba.fastjson.JSONException r10 = new com.alibaba.fastjson.JSONException
            r10.<init>(r11)
            throw r10
        L_0x0088:
            com.alibaba.fastjson.parser.ParseContext r1 = r10.getContext()
        L_0x008c:
            r0.skipWhitespace()     // Catch:{ all -> 0x0217 }
            char r2 = r0.getCurrent()     // Catch:{ all -> 0x0217 }
            com.alibaba.fastjson.parser.Feature r4 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x0217 }
            boolean r4 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r4)     // Catch:{ all -> 0x0217 }
            if (r4 == 0) goto L_0x00aa
        L_0x009b:
            r4 = 44
            if (r2 != r4) goto L_0x00aa
            r0.next()     // Catch:{ all -> 0x0217 }
            r0.skipWhitespace()     // Catch:{ all -> 0x0217 }
            char r2 = r0.getCurrent()     // Catch:{ all -> 0x0217 }
            goto L_0x009b
        L_0x00aa:
            java.lang.String r4 = "expect ':' at "
            r5 = 58
            r6 = 34
            r7 = 16
            if (r2 != r6) goto L_0x00e0
            com.alibaba.fastjson.parser.SymbolTable r2 = r10.getSymbolTable()     // Catch:{ all -> 0x0217 }
            java.lang.String r2 = r0.scanSymbol(r2, r6)     // Catch:{ all -> 0x0217 }
            r0.skipWhitespace()     // Catch:{ all -> 0x0217 }
            char r8 = r0.getCurrent()     // Catch:{ all -> 0x0217 }
            if (r8 != r5) goto L_0x00c7
            goto L_0x0149
        L_0x00c7:
            com.alibaba.fastjson.JSONException r11 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0217 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0217 }
            r12.<init>()     // Catch:{ all -> 0x0217 }
            r12.append(r4)     // Catch:{ all -> 0x0217 }
            int r13 = r0.pos()     // Catch:{ all -> 0x0217 }
            r12.append(r13)     // Catch:{ all -> 0x0217 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0217 }
            r11.<init>(r12)     // Catch:{ all -> 0x0217 }
            throw r11     // Catch:{ all -> 0x0217 }
        L_0x00e0:
            r8 = 125(0x7d, float:1.75E-43)
            if (r2 != r8) goto L_0x00f1
            r0.next()     // Catch:{ all -> 0x0217 }
            r0.resetStringPosition()     // Catch:{ all -> 0x0217 }
            r0.nextToken(r7)     // Catch:{ all -> 0x0217 }
            r10.setContext(r1)
            return r11
        L_0x00f1:
            java.lang.String r8 = "syntax error"
            r9 = 39
            if (r2 != r9) goto L_0x0130
            com.alibaba.fastjson.parser.Feature r2 = com.alibaba.fastjson.parser.Feature.AllowSingleQuotes     // Catch:{ all -> 0x0217 }
            boolean r2 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r2)     // Catch:{ all -> 0x0217 }
            if (r2 == 0) goto L_0x012a
            com.alibaba.fastjson.parser.SymbolTable r2 = r10.getSymbolTable()     // Catch:{ all -> 0x0217 }
            java.lang.String r2 = r0.scanSymbol(r2, r9)     // Catch:{ all -> 0x0217 }
            r0.skipWhitespace()     // Catch:{ all -> 0x0217 }
            char r8 = r0.getCurrent()     // Catch:{ all -> 0x0217 }
            if (r8 != r5) goto L_0x0111
            goto L_0x0149
        L_0x0111:
            com.alibaba.fastjson.JSONException r11 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0217 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0217 }
            r12.<init>()     // Catch:{ all -> 0x0217 }
            r12.append(r4)     // Catch:{ all -> 0x0217 }
            int r13 = r0.pos()     // Catch:{ all -> 0x0217 }
            r12.append(r13)     // Catch:{ all -> 0x0217 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0217 }
            r11.<init>(r12)     // Catch:{ all -> 0x0217 }
            throw r11     // Catch:{ all -> 0x0217 }
        L_0x012a:
            com.alibaba.fastjson.JSONException r11 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0217 }
            r11.<init>(r8)     // Catch:{ all -> 0x0217 }
            throw r11     // Catch:{ all -> 0x0217 }
        L_0x0130:
            com.alibaba.fastjson.parser.Feature r2 = com.alibaba.fastjson.parser.Feature.AllowUnQuotedFieldNames     // Catch:{ all -> 0x0217 }
            boolean r2 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r2)     // Catch:{ all -> 0x0217 }
            if (r2 == 0) goto L_0x0211
            com.alibaba.fastjson.parser.SymbolTable r2 = r10.getSymbolTable()     // Catch:{ all -> 0x0217 }
            java.lang.String r2 = r0.scanSymbolUnQuoted(r2)     // Catch:{ all -> 0x0217 }
            r0.skipWhitespace()     // Catch:{ all -> 0x0217 }
            char r8 = r0.getCurrent()     // Catch:{ all -> 0x0217 }
            if (r8 != r5) goto L_0x01f0
        L_0x0149:
            r0.next()     // Catch:{ all -> 0x0217 }
            r0.skipWhitespace()     // Catch:{ all -> 0x0217 }
            r0.getCurrent()     // Catch:{ all -> 0x0217 }
            r0.resetStringPosition()     // Catch:{ all -> 0x0217 }
            java.lang.String r4 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x0217 }
            r5 = 13
            r8 = 0
            if (r2 != r4) goto L_0x01ae
            com.alibaba.fastjson.parser.Feature r4 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x0217 }
            boolean r4 = r0.isEnabled((com.alibaba.fastjson.parser.Feature) r4)     // Catch:{ all -> 0x0217 }
            if (r4 != 0) goto L_0x01ae
            com.alibaba.fastjson.parser.SymbolTable r2 = r10.getSymbolTable()     // Catch:{ all -> 0x0217 }
            java.lang.String r2 = r0.scanSymbol(r2, r6)     // Catch:{ all -> 0x0217 }
            com.alibaba.fastjson.parser.ParserConfig r4 = r10.getConfig()     // Catch:{ all -> 0x0217 }
            int r6 = r0.getFeatures()     // Catch:{ all -> 0x0217 }
            java.lang.Class r2 = r4.checkAutoType(r2, r8, r6)     // Catch:{ all -> 0x0217 }
            java.lang.Class<java.util.Map> r6 = java.util.Map.class
            boolean r6 = r6.isAssignableFrom(r2)     // Catch:{ all -> 0x0217 }
            if (r6 == 0) goto L_0x0190
            r0.nextToken(r7)     // Catch:{ all -> 0x0217 }
            int r2 = r0.token()     // Catch:{ all -> 0x0217 }
            if (r2 != r5) goto L_0x01e8
            r0.nextToken(r7)     // Catch:{ all -> 0x0217 }
            r10.setContext(r1)
            return r11
        L_0x0190:
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r11 = r4.getDeserializer((java.lang.reflect.Type) r2)     // Catch:{ all -> 0x0217 }
            r0.nextToken(r7)     // Catch:{ all -> 0x0217 }
            r12 = 2
            r10.setResolveStatus(r12)     // Catch:{ all -> 0x0217 }
            if (r1 == 0) goto L_0x01a4
            boolean r12 = r13 instanceof java.lang.Integer     // Catch:{ all -> 0x0217 }
            if (r12 != 0) goto L_0x01a4
            r10.popContext()     // Catch:{ all -> 0x0217 }
        L_0x01a4:
            java.lang.Object r11 = r11.deserialze(r10, r2, r13)     // Catch:{ all -> 0x0217 }
            java.util.Map r11 = (java.util.Map) r11     // Catch:{ all -> 0x0217 }
            r10.setContext(r1)
            return r11
        L_0x01ae:
            r0.nextToken()     // Catch:{ all -> 0x0217 }
            if (r3 == 0) goto L_0x01b6
            r10.setContext(r1)     // Catch:{ all -> 0x0217 }
        L_0x01b6:
            int r4 = r0.token()     // Catch:{ all -> 0x0217 }
            r6 = 8
            if (r4 != r6) goto L_0x01c2
            r0.nextToken()     // Catch:{ all -> 0x0217 }
            goto L_0x01c6
        L_0x01c2:
            java.lang.Object r8 = r10.parseObject((java.lang.reflect.Type) r12, (java.lang.Object) r2)     // Catch:{ all -> 0x0217 }
        L_0x01c6:
            r11.put(r2, r8)     // Catch:{ all -> 0x0217 }
            r10.checkMapResolve(r11, r2)     // Catch:{ all -> 0x0217 }
            r10.setContext(r1, r8, r2)     // Catch:{ all -> 0x0217 }
            r10.setContext(r1)     // Catch:{ all -> 0x0217 }
            int r2 = r0.token()     // Catch:{ all -> 0x0217 }
            r4 = 20
            if (r2 == r4) goto L_0x01ec
            r4 = 15
            if (r2 != r4) goto L_0x01df
            goto L_0x01ec
        L_0x01df:
            if (r2 != r5) goto L_0x01e8
            r0.nextToken()     // Catch:{ all -> 0x0217 }
            r10.setContext(r1)
            return r11
        L_0x01e8:
            int r3 = r3 + 1
            goto L_0x008c
        L_0x01ec:
            r10.setContext(r1)
            return r11
        L_0x01f0:
            com.alibaba.fastjson.JSONException r11 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0217 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0217 }
            r12.<init>()     // Catch:{ all -> 0x0217 }
            r12.append(r4)     // Catch:{ all -> 0x0217 }
            int r13 = r0.pos()     // Catch:{ all -> 0x0217 }
            r12.append(r13)     // Catch:{ all -> 0x0217 }
            java.lang.String r13 = ", actual "
            r12.append(r13)     // Catch:{ all -> 0x0217 }
            r12.append(r8)     // Catch:{ all -> 0x0217 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0217 }
            r11.<init>(r12)     // Catch:{ all -> 0x0217 }
            throw r11     // Catch:{ all -> 0x0217 }
        L_0x0211:
            com.alibaba.fastjson.JSONException r11 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0217 }
            r11.<init>(r8)     // Catch:{ all -> 0x0217 }
            throw r11     // Catch:{ all -> 0x0217 }
        L_0x0217:
            r11 = move-exception
            r10.setContext(r1)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.MapDeserializer.parseMap(com.alibaba.fastjson.parser.DefaultJSONParser, java.util.Map, java.lang.reflect.Type, java.lang.Object):java.util.Map");
    }

    public static Object parseMap(DefaultJSONParser defaultJSONParser, Map<Object, Object> map, Type type, Type type2, Object obj) {
        Object obj2;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 12 || jSONLexer.token() == 16) {
            ObjectDeserializer deserializer = defaultJSONParser.getConfig().getDeserializer(type);
            ObjectDeserializer deserializer2 = defaultJSONParser.getConfig().getDeserializer(type2);
            jSONLexer.nextToken(deserializer.getFastMatchToken());
            ParseContext context = defaultJSONParser.getContext();
            while (jSONLexer.token() != 13) {
                try {
                    Object obj3 = null;
                    if (jSONLexer.token() != 4 || !jSONLexer.isRef() || jSONLexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                        if (map.size() == 0 && jSONLexer.token() == 4 && JSON.DEFAULT_TYPE_KEY.equals(jSONLexer.stringVal()) && !jSONLexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                            jSONLexer.nextTokenWithColon(4);
                            jSONLexer.nextToken(16);
                            if (jSONLexer.token() == 13) {
                                jSONLexer.nextToken();
                                return map;
                            }
                            jSONLexer.nextToken(deserializer.getFastMatchToken());
                        }
                        if (jSONLexer.token() != 4 || !(deserializer instanceof JavaBeanDeserializer)) {
                            obj2 = deserializer.deserialze(defaultJSONParser, type, (Object) null);
                        } else {
                            String stringVal = jSONLexer.stringVal();
                            jSONLexer.nextToken();
                            DefaultJSONParser defaultJSONParser2 = new DefaultJSONParser(stringVal, defaultJSONParser.getConfig(), defaultJSONParser.getLexer().getFeatures());
                            defaultJSONParser2.setDateFormat(defaultJSONParser.getDateFomartPattern());
                            obj2 = deserializer.deserialze(defaultJSONParser2, type, (Object) null);
                        }
                        if (jSONLexer.token() == 17) {
                            jSONLexer.nextToken(deserializer2.getFastMatchToken());
                            Object deserialze = deserializer2.deserialze(defaultJSONParser, type2, obj2);
                            defaultJSONParser.checkMapResolve(map, obj2);
                            map.put(obj2, deserialze);
                            if (jSONLexer.token() == 16) {
                                jSONLexer.nextToken(deserializer.getFastMatchToken());
                            }
                        } else {
                            throw new JSONException("syntax error, expect :, actual " + jSONLexer.token());
                        }
                    } else {
                        jSONLexer.nextTokenWithColon(4);
                        if (jSONLexer.token() == 4) {
                            String stringVal2 = jSONLexer.stringVal();
                            if ("..".equals(stringVal2)) {
                                obj3 = context.parent.object;
                            } else if ("$".equals(stringVal2)) {
                                ParseContext parseContext = context;
                                while (parseContext.parent != null) {
                                    parseContext = parseContext.parent;
                                }
                                obj3 = parseContext.object;
                            } else {
                                defaultJSONParser.addResolveTask(new DefaultJSONParser.ResolveTask(context, stringVal2));
                                defaultJSONParser.setResolveStatus(1);
                            }
                            jSONLexer.nextToken(13);
                            if (jSONLexer.token() == 13) {
                                jSONLexer.nextToken(16);
                                defaultJSONParser.setContext(context);
                                return obj3;
                            }
                            throw new JSONException("illegal ref");
                        }
                        throw new JSONException("illegal ref, " + JSONToken.name(jSONLexer.token()));
                    }
                } finally {
                    defaultJSONParser.setContext(context);
                }
            }
            jSONLexer.nextToken(16);
            defaultJSONParser.setContext(context);
            return map;
        }
        throw new JSONException("syntax error, expect {, actual " + jSONLexer.tokenName());
    }

    public Map<Object, Object> createMap(Type type) {
        return createMap(type, JSON.DEFAULT_GENERATE_FEATURE);
    }

    public Map<Object, Object> createMap(Type type, int i) {
        if (type == Properties.class) {
            return new Properties();
        }
        if (type == Hashtable.class) {
            return new Hashtable();
        }
        if (type == IdentityHashMap.class) {
            return new IdentityHashMap();
        }
        if (type == SortedMap.class || type == TreeMap.class) {
            return new TreeMap();
        }
        if (type == ConcurrentMap.class || type == ConcurrentHashMap.class) {
            return new ConcurrentHashMap();
        }
        if (type == Map.class) {
            return (Feature.OrderedField.mask & i) != 0 ? new LinkedHashMap() : new HashMap();
        }
        if (type == HashMap.class) {
            return new HashMap();
        }
        if (type == LinkedHashMap.class) {
            return new LinkedHashMap();
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            if (EnumMap.class.equals(rawType)) {
                return new EnumMap((Class) parameterizedType.getActualTypeArguments()[0]);
            }
            return createMap(rawType, i);
        }
        Class cls = (Class) type;
        if (cls.isInterface()) {
            throw new JSONException("unsupport type " + type);
        } else if ("java.util.Collections$UnmodifiableMap".equals(cls.getName())) {
            return new HashMap();
        } else {
            try {
                return (Map) cls.newInstance();
            } catch (Exception e) {
                throw new JSONException("unsupport type " + type, e);
            }
        }
    }
}
