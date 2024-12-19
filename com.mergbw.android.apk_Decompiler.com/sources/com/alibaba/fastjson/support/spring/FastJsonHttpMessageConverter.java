package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.nio.charset.Charset;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class FastJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> implements GenericHttpMessageConverter<Object> {
    public static final MediaType APPLICATION_JAVASCRIPT = new MediaType(MimeTypes.BASE_TYPE_APPLICATION, "javascript");
    @Deprecated
    protected String dateFormat;
    private FastJsonConfig fastJsonConfig = new FastJsonConfig();
    @Deprecated
    protected SerializerFeature[] features = new SerializerFeature[0];
    @Deprecated
    protected SerializeFilter[] filters = new SerializeFilter[0];

    /* access modifiers changed from: protected */
    public boolean supports(Class<?> cls) {
        return true;
    }

    public FastJsonConfig getFastJsonConfig() {
        return this.fastJsonConfig;
    }

    public void setFastJsonConfig(FastJsonConfig fastJsonConfig2) {
        this.fastJsonConfig = fastJsonConfig2;
    }

    public FastJsonHttpMessageConverter() {
        super(MediaType.ALL);
    }

    @Deprecated
    public Charset getCharset() {
        return this.fastJsonConfig.getCharset();
    }

    @Deprecated
    public void setCharset(Charset charset) {
        this.fastJsonConfig.setCharset(charset);
    }

    @Deprecated
    public String getDateFormat() {
        return this.fastJsonConfig.getDateFormat();
    }

    @Deprecated
    public void setDateFormat(String str) {
        this.fastJsonConfig.setDateFormat(str);
    }

    @Deprecated
    public SerializerFeature[] getFeatures() {
        return this.fastJsonConfig.getSerializerFeatures();
    }

    @Deprecated
    public void setFeatures(SerializerFeature... serializerFeatureArr) {
        this.fastJsonConfig.setSerializerFeatures(serializerFeatureArr);
    }

    @Deprecated
    public SerializeFilter[] getFilters() {
        return this.fastJsonConfig.getSerializeFilters();
    }

    @Deprecated
    public void setFilters(SerializeFilter... serializeFilterArr) {
        this.fastJsonConfig.setSerializeFilters(serializeFilterArr);
    }

    @Deprecated
    public void addSerializeFilter(SerializeFilter serializeFilter) {
        if (serializeFilter != null) {
            int length = this.fastJsonConfig.getSerializeFilters().length;
            SerializeFilter[] serializeFilterArr = new SerializeFilter[(length + 1)];
            System.arraycopy(this.fastJsonConfig.getSerializeFilters(), 0, serializeFilterArr, 0, length);
            serializeFilterArr[length] = serializeFilter;
            this.fastJsonConfig.setSerializeFilters(serializeFilterArr);
        }
    }

    public boolean canRead(Type type, Class<?> cls, MediaType mediaType) {
        return FastJsonHttpMessageConverter.super.canRead(cls, mediaType);
    }

    public boolean canWrite(Type type, Class<?> cls, MediaType mediaType) {
        return FastJsonHttpMessageConverter.super.canWrite(cls, mediaType);
    }

    public Object read(Type type, Class<?> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readType(getType(type, cls), httpInputMessage);
    }

    public void write(Object obj, Type type, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        FastJsonHttpMessageConverter.super.write(obj, mediaType, httpOutputMessage);
    }

    /* access modifiers changed from: protected */
    public Object readInternal(Class<? extends Object> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readType(getType(cls, (Class<?>) null), httpInputMessage);
    }

    private Object readType(Type type, HttpInputMessage httpInputMessage) {
        try {
            return JSON.parseObject(httpInputMessage.getBody(), this.fastJsonConfig.getCharset(), type, this.fastJsonConfig.getParserConfig(), this.fastJsonConfig.getParseProcess(), JSON.DEFAULT_PARSER_FEATURE, this.fastJsonConfig.getFeatures());
        } catch (JSONException e) {
            throw new HttpMessageNotReadableException("JSON parse error: " + e.getMessage(), e);
        } catch (IOException e2) {
            throw new HttpMessageNotReadableException("I/O error while reading input message", e2);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004a, code lost:
        if ((r3 instanceof com.alibaba.fastjson.JSONPObject) != false) goto L_0x004c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0083 A[Catch:{ JSONException -> 0x00a1, all -> 0x009f }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0090 A[Catch:{ JSONException -> 0x00a1, all -> 0x009f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeInternal(java.lang.Object r13, org.springframework.http.HttpOutputMessage r14) throws java.io.IOException, org.springframework.http.converter.HttpMessageNotWritableException {
        /*
            r12 = this;
            java.lang.String r0 = "Could not write JSON: "
            java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream
            r9.<init>()
            org.springframework.http.HttpHeaders r10 = r14.getHeaders()     // Catch:{ JSONException -> 0x00a1 }
            com.alibaba.fastjson.support.config.FastJsonConfig r1 = r12.fastJsonConfig     // Catch:{ JSONException -> 0x00a1 }
            com.alibaba.fastjson.serializer.SerializeFilter[] r1 = r1.getSerializeFilters()     // Catch:{ JSONException -> 0x00a1 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ JSONException -> 0x00a1 }
            java.util.List r1 = java.util.Arrays.asList(r1)     // Catch:{ JSONException -> 0x00a1 }
            r2.<init>(r1)     // Catch:{ JSONException -> 0x00a1 }
            java.lang.Object r13 = r12.strangeCodeForJackson(r13)     // Catch:{ JSONException -> 0x00a1 }
            boolean r1 = r13 instanceof com.alibaba.fastjson.support.spring.FastJsonContainer     // Catch:{ JSONException -> 0x00a1 }
            if (r1 == 0) goto L_0x0033
            com.alibaba.fastjson.support.spring.FastJsonContainer r13 = (com.alibaba.fastjson.support.spring.FastJsonContainer) r13     // Catch:{ JSONException -> 0x00a1 }
            com.alibaba.fastjson.support.spring.PropertyPreFilters r1 = r13.getFilters()     // Catch:{ JSONException -> 0x00a1 }
            java.util.List r1 = r1.getFilters()     // Catch:{ JSONException -> 0x00a1 }
            r2.addAll(r1)     // Catch:{ JSONException -> 0x00a1 }
            java.lang.Object r13 = r13.getValue()     // Catch:{ JSONException -> 0x00a1 }
        L_0x0033:
            r3 = r13
            boolean r13 = r3 instanceof com.alibaba.fastjson.support.spring.MappingFastJsonValue     // Catch:{ JSONException -> 0x00a1 }
            r1 = 1
            if (r13 == 0) goto L_0x0048
            r13 = r3
            com.alibaba.fastjson.support.spring.MappingFastJsonValue r13 = (com.alibaba.fastjson.support.spring.MappingFastJsonValue) r13     // Catch:{ JSONException -> 0x00a1 }
            java.lang.String r13 = r13.getJsonpFunction()     // Catch:{ JSONException -> 0x00a1 }
            boolean r13 = org.springframework.util.StringUtils.isEmpty(r13)     // Catch:{ JSONException -> 0x00a1 }
            if (r13 != 0) goto L_0x004d
            goto L_0x004c
        L_0x0048:
            boolean r13 = r3 instanceof com.alibaba.fastjson.JSONPObject     // Catch:{ JSONException -> 0x00a1 }
            if (r13 == 0) goto L_0x004d
        L_0x004c:
            goto L_0x004e
        L_0x004d:
            r1 = 0
        L_0x004e:
            r13 = r1
            com.alibaba.fastjson.support.config.FastJsonConfig r1 = r12.fastJsonConfig     // Catch:{ JSONException -> 0x00a1 }
            java.nio.charset.Charset r4 = r1.getCharset()     // Catch:{ JSONException -> 0x00a1 }
            com.alibaba.fastjson.support.config.FastJsonConfig r1 = r12.fastJsonConfig     // Catch:{ JSONException -> 0x00a1 }
            com.alibaba.fastjson.serializer.SerializeConfig r5 = r1.getSerializeConfig()     // Catch:{ JSONException -> 0x00a1 }
            int r1 = r2.size()     // Catch:{ JSONException -> 0x00a1 }
            com.alibaba.fastjson.serializer.SerializeFilter[] r1 = new com.alibaba.fastjson.serializer.SerializeFilter[r1]     // Catch:{ JSONException -> 0x00a1 }
            java.lang.Object[] r1 = r2.toArray(r1)     // Catch:{ JSONException -> 0x00a1 }
            r6 = r1
            com.alibaba.fastjson.serializer.SerializeFilter[] r6 = (com.alibaba.fastjson.serializer.SerializeFilter[]) r6     // Catch:{ JSONException -> 0x00a1 }
            com.alibaba.fastjson.support.config.FastJsonConfig r1 = r12.fastJsonConfig     // Catch:{ JSONException -> 0x00a1 }
            java.lang.String r7 = r1.getDateFormat()     // Catch:{ JSONException -> 0x00a1 }
            int r8 = com.alibaba.fastjson.JSON.DEFAULT_GENERATE_FEATURE     // Catch:{ JSONException -> 0x00a1 }
            com.alibaba.fastjson.support.config.FastJsonConfig r1 = r12.fastJsonConfig     // Catch:{ JSONException -> 0x00a1 }
            com.alibaba.fastjson.serializer.SerializerFeature[] r11 = r1.getSerializerFeatures()     // Catch:{ JSONException -> 0x00a1 }
            r1 = r9
            r2 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r11
            int r1 = com.alibaba.fastjson.JSON.writeJSONString(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ JSONException -> 0x00a1 }
            if (r13 == 0) goto L_0x0088
            org.springframework.http.MediaType r13 = APPLICATION_JAVASCRIPT     // Catch:{ JSONException -> 0x00a1 }
            r10.setContentType(r13)     // Catch:{ JSONException -> 0x00a1 }
        L_0x0088:
            com.alibaba.fastjson.support.config.FastJsonConfig r13 = r12.fastJsonConfig     // Catch:{ JSONException -> 0x00a1 }
            boolean r13 = r13.isWriteContentLength()     // Catch:{ JSONException -> 0x00a1 }
            if (r13 == 0) goto L_0x0094
            long r1 = (long) r1     // Catch:{ JSONException -> 0x00a1 }
            r10.setContentLength(r1)     // Catch:{ JSONException -> 0x00a1 }
        L_0x0094:
            java.io.OutputStream r13 = r14.getBody()     // Catch:{ JSONException -> 0x00a1 }
            r9.writeTo(r13)     // Catch:{ JSONException -> 0x00a1 }
            r9.close()
            return
        L_0x009f:
            r13 = move-exception
            goto L_0x00b8
        L_0x00a1:
            r13 = move-exception
            org.springframework.http.converter.HttpMessageNotWritableException r14 = new org.springframework.http.converter.HttpMessageNotWritableException     // Catch:{ all -> 0x009f }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x009f }
            r1.<init>(r0)     // Catch:{ all -> 0x009f }
            java.lang.String r0 = r13.getMessage()     // Catch:{ all -> 0x009f }
            r1.append(r0)     // Catch:{ all -> 0x009f }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x009f }
            r14.<init>(r0, r13)     // Catch:{ all -> 0x009f }
            throw r14     // Catch:{ all -> 0x009f }
        L_0x00b8:
            r9.close()
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter.writeInternal(java.lang.Object, org.springframework.http.HttpOutputMessage):void");
    }

    private Object strangeCodeForJackson(Object obj) {
        return (obj == null || !"com.fasterxml.jackson.databind.node.ObjectNode".equals(obj.getClass().getName())) ? obj : obj.toString();
    }

    /* access modifiers changed from: protected */
    public Type getType(Type type, Class<?> cls) {
        return Spring4TypeResolvableHelper.isSupport() ? Spring4TypeResolvableHelper.getType(type, cls) : type;
    }

    private static class Spring4TypeResolvableHelper {
        private static boolean hasClazzResolvableType;

        private Spring4TypeResolvableHelper() {
        }

        static {
            try {
                Class.forName("org.springframework.core.ResolvableType");
                hasClazzResolvableType = true;
            } catch (ClassNotFoundException unused) {
                hasClazzResolvableType = false;
            }
        }

        /* access modifiers changed from: private */
        public static boolean isSupport() {
            return hasClazzResolvableType;
        }

        /* access modifiers changed from: private */
        public static Type getType(Type type, Class<?> cls) {
            if (cls == null) {
                return type;
            }
            ResolvableType forType = ResolvableType.forType(type);
            if (type instanceof TypeVariable) {
                ResolvableType resolveVariable = resolveVariable((TypeVariable) type, ResolvableType.forClass(cls));
                if (resolveVariable != ResolvableType.NONE) {
                    return resolveVariable.resolve();
                }
                return type;
            } else if (!(type instanceof ParameterizedType) || !forType.hasUnresolvableGenerics()) {
                return type;
            } else {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class[] clsArr = new Class[parameterizedType.getActualTypeArguments().length];
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    Type type2 = actualTypeArguments[i];
                    if (type2 instanceof TypeVariable) {
                        ResolvableType resolveVariable2 = resolveVariable((TypeVariable) type2, ResolvableType.forClass(cls));
                        if (resolveVariable2 != ResolvableType.NONE) {
                            clsArr[i] = resolveVariable2.resolve();
                        } else {
                            clsArr[i] = ResolvableType.forType(type2).resolve();
                        }
                    } else {
                        clsArr[i] = ResolvableType.forType(type2).resolve();
                    }
                }
                return ResolvableType.forClassWithGenerics(forType.getRawClass(), clsArr).getType();
            }
        }

        private static ResolvableType resolveVariable(TypeVariable<?> typeVariable, ResolvableType resolvableType) {
            if (resolvableType.hasGenerics()) {
                ResolvableType forType = ResolvableType.forType(typeVariable, resolvableType);
                if (forType.resolve() != null) {
                    return forType;
                }
            }
            ResolvableType superType = resolvableType.getSuperType();
            if (superType != ResolvableType.NONE) {
                ResolvableType resolveVariable = resolveVariable(typeVariable, superType);
                if (resolveVariable.resolve() != null) {
                    return resolveVariable;
                }
            }
            for (ResolvableType resolveVariable2 : resolvableType.getInterfaces()) {
                ResolvableType resolveVariable3 = resolveVariable(typeVariable, resolveVariable2);
                if (resolveVariable3.resolve() != null) {
                    return resolveVariable3;
                }
            }
            return ResolvableType.NONE;
        }
    }
}
