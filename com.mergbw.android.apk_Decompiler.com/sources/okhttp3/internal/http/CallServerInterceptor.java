package okhttp3.internal.http;

import kotlin.Metadata;
import okhttp3.Interceptor;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lokhttp3/internal/http/CallServerInterceptor;", "Lokhttp3/Interceptor;", "forWebSocket", "", "(Z)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "okhttp"}, k = 1, mv = {1, 4, 1})
/* compiled from: CallServerInterceptor.kt */
public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean z) {
        this.forWebSocket = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0098, code lost:
        if (r4.isDuplex() == false) goto L_0x009a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b0 A[SYNTHETIC, Splitter:B:40:0x00b0] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e5 A[Catch:{ IOException -> 0x01a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0120 A[Catch:{ IOException -> 0x01a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x012f A[Catch:{ IOException -> 0x01a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0169 A[Catch:{ IOException -> 0x01a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x016e A[Catch:{ IOException -> 0x01a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0176 A[Catch:{ IOException -> 0x01a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r15) throws java.io.IOException {
        /*
            r14 = this;
            java.lang.String r0 = "Connection"
            java.lang.String r1 = "close"
            java.lang.String r2 = "HTTP "
            java.lang.String r3 = "chain"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r3)
            okhttp3.internal.http.RealInterceptorChain r15 = (okhttp3.internal.http.RealInterceptorChain) r15
            okhttp3.internal.connection.Exchange r3 = r15.getExchange$okhttp()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            okhttp3.Request r15 = r15.getRequest$okhttp()
            okhttp3.RequestBody r4 = r15.body()
            long r5 = java.lang.System.currentTimeMillis()
            r7 = 0
            r8 = r7
            okhttp3.Response$Builder r8 = (okhttp3.Response.Builder) r8
            r8 = r7
            java.io.IOException r8 = (java.io.IOException) r8
            r8 = 0
            r9 = 1
            r3.writeRequestHeaders(r15)     // Catch:{ IOException -> 0x00a1 }
            java.lang.String r10 = r15.method()     // Catch:{ IOException -> 0x00a1 }
            boolean r10 = okhttp3.internal.http.HttpMethod.permitsRequestBody(r10)     // Catch:{ IOException -> 0x00a1 }
            if (r10 == 0) goto L_0x008d
            if (r4 == 0) goto L_0x008d
            java.lang.String r10 = "100-continue"
            java.lang.String r11 = "Expect"
            java.lang.String r11 = r15.header(r11)     // Catch:{ IOException -> 0x00a1 }
            boolean r10 = kotlin.text.StringsKt.equals(r10, r11, r9)     // Catch:{ IOException -> 0x00a1 }
            if (r10 == 0) goto L_0x0054
            r3.flushRequest()     // Catch:{ IOException -> 0x00a1 }
            okhttp3.Response$Builder r10 = r3.readResponseHeaders(r9)     // Catch:{ IOException -> 0x00a1 }
            r3.responseHeadersStart()     // Catch:{ IOException -> 0x0052 }
            r11 = r8
            goto L_0x0056
        L_0x0052:
            r4 = move-exception
            goto L_0x00a3
        L_0x0054:
            r10 = r7
            r11 = r9
        L_0x0056:
            if (r10 != 0) goto L_0x007c
            boolean r12 = r4.isDuplex()     // Catch:{ IOException -> 0x009f }
            if (r12 == 0) goto L_0x006d
            r3.flushRequest()     // Catch:{ IOException -> 0x009f }
            okio.Sink r12 = r3.createRequestBody(r15, r9)     // Catch:{ IOException -> 0x009f }
            okio.BufferedSink r12 = okio.Okio.buffer((okio.Sink) r12)     // Catch:{ IOException -> 0x009f }
            r4.writeTo(r12)     // Catch:{ IOException -> 0x009f }
            goto L_0x0092
        L_0x006d:
            okio.Sink r12 = r3.createRequestBody(r15, r8)     // Catch:{ IOException -> 0x009f }
            okio.BufferedSink r12 = okio.Okio.buffer((okio.Sink) r12)     // Catch:{ IOException -> 0x009f }
            r4.writeTo(r12)     // Catch:{ IOException -> 0x009f }
            r12.close()     // Catch:{ IOException -> 0x009f }
            goto L_0x0092
        L_0x007c:
            r3.noRequestBody()     // Catch:{ IOException -> 0x009f }
            okhttp3.internal.connection.RealConnection r12 = r3.getConnection$okhttp()     // Catch:{ IOException -> 0x009f }
            boolean r12 = r12.isMultiplexed$okhttp()     // Catch:{ IOException -> 0x009f }
            if (r12 != 0) goto L_0x0092
            r3.noNewExchangesOnConnection()     // Catch:{ IOException -> 0x009f }
            goto L_0x0092
        L_0x008d:
            r3.noRequestBody()     // Catch:{ IOException -> 0x00a1 }
            r10 = r7
            r11 = r9
        L_0x0092:
            if (r4 == 0) goto L_0x009a
            boolean r4 = r4.isDuplex()     // Catch:{ IOException -> 0x009f }
            if (r4 != 0) goto L_0x009d
        L_0x009a:
            r3.finishRequest()     // Catch:{ IOException -> 0x009f }
        L_0x009d:
            r4 = r7
            goto L_0x00ae
        L_0x009f:
            r4 = move-exception
            goto L_0x00a4
        L_0x00a1:
            r4 = move-exception
            r10 = r7
        L_0x00a3:
            r11 = r9
        L_0x00a4:
            boolean r12 = r4 instanceof okhttp3.internal.http2.ConnectionShutdownException
            if (r12 != 0) goto L_0x01b2
            boolean r12 = r3.getHasFailure$okhttp()
            if (r12 == 0) goto L_0x01af
        L_0x00ae:
            if (r10 != 0) goto L_0x00bd
            okhttp3.Response$Builder r10 = r3.readResponseHeaders(r8)     // Catch:{ IOException -> 0x01a1 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)     // Catch:{ IOException -> 0x01a1 }
            if (r11 == 0) goto L_0x00bd
            r3.responseHeadersStart()     // Catch:{ IOException -> 0x01a1 }
            r11 = r8
        L_0x00bd:
            okhttp3.Response$Builder r10 = r10.request(r15)     // Catch:{ IOException -> 0x01a1 }
            okhttp3.internal.connection.RealConnection r12 = r3.getConnection$okhttp()     // Catch:{ IOException -> 0x01a1 }
            okhttp3.Handshake r12 = r12.handshake()     // Catch:{ IOException -> 0x01a1 }
            okhttp3.Response$Builder r10 = r10.handshake(r12)     // Catch:{ IOException -> 0x01a1 }
            okhttp3.Response$Builder r10 = r10.sentRequestAtMillis(r5)     // Catch:{ IOException -> 0x01a1 }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x01a1 }
            okhttp3.Response$Builder r10 = r10.receivedResponseAtMillis(r12)     // Catch:{ IOException -> 0x01a1 }
            okhttp3.Response r10 = r10.build()     // Catch:{ IOException -> 0x01a1 }
            int r12 = r10.code()     // Catch:{ IOException -> 0x01a1 }
            r13 = 100
            if (r12 != r13) goto L_0x0115
            okhttp3.Response$Builder r8 = r3.readResponseHeaders(r8)     // Catch:{ IOException -> 0x01a1 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)     // Catch:{ IOException -> 0x01a1 }
            if (r11 == 0) goto L_0x00f1
            r3.responseHeadersStart()     // Catch:{ IOException -> 0x01a1 }
        L_0x00f1:
            okhttp3.Response$Builder r15 = r8.request(r15)     // Catch:{ IOException -> 0x01a1 }
            okhttp3.internal.connection.RealConnection r8 = r3.getConnection$okhttp()     // Catch:{ IOException -> 0x01a1 }
            okhttp3.Handshake r8 = r8.handshake()     // Catch:{ IOException -> 0x01a1 }
            okhttp3.Response$Builder r15 = r15.handshake(r8)     // Catch:{ IOException -> 0x01a1 }
            okhttp3.Response$Builder r15 = r15.sentRequestAtMillis(r5)     // Catch:{ IOException -> 0x01a1 }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x01a1 }
            okhttp3.Response$Builder r15 = r15.receivedResponseAtMillis(r5)     // Catch:{ IOException -> 0x01a1 }
            okhttp3.Response r10 = r15.build()     // Catch:{ IOException -> 0x01a1 }
            int r12 = r10.code()     // Catch:{ IOException -> 0x01a1 }
        L_0x0115:
            r3.responseHeadersEnd(r10)     // Catch:{ IOException -> 0x01a1 }
            boolean r15 = r14.forWebSocket     // Catch:{ IOException -> 0x01a1 }
            if (r15 == 0) goto L_0x012f
            r15 = 101(0x65, float:1.42E-43)
            if (r12 != r15) goto L_0x012f
            okhttp3.Response$Builder r15 = r10.newBuilder()     // Catch:{ IOException -> 0x01a1 }
            okhttp3.ResponseBody r5 = okhttp3.internal.Util.EMPTY_RESPONSE     // Catch:{ IOException -> 0x01a1 }
            okhttp3.Response$Builder r15 = r15.body(r5)     // Catch:{ IOException -> 0x01a1 }
            okhttp3.Response r15 = r15.build()     // Catch:{ IOException -> 0x01a1 }
            goto L_0x013f
        L_0x012f:
            okhttp3.Response$Builder r15 = r10.newBuilder()     // Catch:{ IOException -> 0x01a1 }
            okhttp3.ResponseBody r5 = r3.openResponseBody(r10)     // Catch:{ IOException -> 0x01a1 }
            okhttp3.Response$Builder r15 = r15.body(r5)     // Catch:{ IOException -> 0x01a1 }
            okhttp3.Response r15 = r15.build()     // Catch:{ IOException -> 0x01a1 }
        L_0x013f:
            okhttp3.Request r5 = r15.request()     // Catch:{ IOException -> 0x01a1 }
            java.lang.String r5 = r5.header(r0)     // Catch:{ IOException -> 0x01a1 }
            boolean r5 = kotlin.text.StringsKt.equals(r1, r5, r9)     // Catch:{ IOException -> 0x01a1 }
            if (r5 != 0) goto L_0x0158
            r5 = 2
            java.lang.String r0 = okhttp3.Response.header$default(r15, r0, r7, r5, r7)     // Catch:{ IOException -> 0x01a1 }
            boolean r0 = kotlin.text.StringsKt.equals(r1, r0, r9)     // Catch:{ IOException -> 0x01a1 }
            if (r0 == 0) goto L_0x015b
        L_0x0158:
            r3.noNewExchangesOnConnection()     // Catch:{ IOException -> 0x01a1 }
        L_0x015b:
            r0 = 204(0xcc, float:2.86E-43)
            if (r12 == r0) goto L_0x0163
            r0 = 205(0xcd, float:2.87E-43)
            if (r12 != r0) goto L_0x01a0
        L_0x0163:
            okhttp3.ResponseBody r0 = r15.body()     // Catch:{ IOException -> 0x01a1 }
            if (r0 == 0) goto L_0x016e
            long r0 = r0.contentLength()     // Catch:{ IOException -> 0x01a1 }
            goto L_0x0170
        L_0x016e:
            r0 = -1
        L_0x0170:
            r5 = 0
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x01a0
            java.net.ProtocolException r0 = new java.net.ProtocolException     // Catch:{ IOException -> 0x01a1 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01a1 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x01a1 }
            r1.append(r12)     // Catch:{ IOException -> 0x01a1 }
            java.lang.String r2 = " had non-zero Content-Length: "
            r1.append(r2)     // Catch:{ IOException -> 0x01a1 }
            okhttp3.ResponseBody r15 = r15.body()     // Catch:{ IOException -> 0x01a1 }
            if (r15 == 0) goto L_0x0193
            long r2 = r15.contentLength()     // Catch:{ IOException -> 0x01a1 }
            java.lang.Long r7 = java.lang.Long.valueOf(r2)     // Catch:{ IOException -> 0x01a1 }
        L_0x0193:
            r1.append(r7)     // Catch:{ IOException -> 0x01a1 }
            java.lang.String r15 = r1.toString()     // Catch:{ IOException -> 0x01a1 }
            r0.<init>(r15)     // Catch:{ IOException -> 0x01a1 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ IOException -> 0x01a1 }
            throw r0     // Catch:{ IOException -> 0x01a1 }
        L_0x01a0:
            return r15
        L_0x01a1:
            r15 = move-exception
            if (r4 == 0) goto L_0x01ac
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            kotlin.ExceptionsKt.addSuppressed(r4, r15)
            throw r4
        L_0x01ac:
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            throw r15
        L_0x01af:
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            throw r4
        L_0x01b2:
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.CallServerInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }
}
