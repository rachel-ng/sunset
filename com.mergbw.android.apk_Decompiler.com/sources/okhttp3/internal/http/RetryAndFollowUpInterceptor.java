package okhttp3.internal.http;

import androidx.browser.trusted.sharing.ShareTarget;
import com.google.common.net.HttpHeaders;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealCall;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0002J(\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0012H\u0002J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0006H\u0002J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor;", "Lokhttp3/Interceptor;", "client", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "buildRedirectRequest", "Lokhttp3/Request;", "userResponse", "Lokhttp3/Response;", "method", "", "followUpRequest", "exchange", "Lokhttp3/internal/connection/Exchange;", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "isRecoverable", "", "e", "Ljava/io/IOException;", "requestSendStarted", "recover", "call", "Lokhttp3/internal/connection/RealCall;", "userRequest", "requestIsOneShot", "retryAfter", "", "defaultDelay", "Companion", "okhttp"}, k = 1, mv = {1, 4, 1})
/* compiled from: RetryAndFollowUpInterceptor.kt */
public final class RetryAndFollowUpInterceptor implements Interceptor {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MAX_FOLLOW_UPS = 20;
    private final OkHttpClient client;

    public RetryAndFollowUpInterceptor(OkHttpClient okHttpClient) {
        Intrinsics.checkNotNullParameter(okHttpClient, "client");
        this.client = okHttpClient;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0 = r0.newBuilder().priorResponse(r7.newBuilder().body((okhttp3.ResponseBody) null).build()).build();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0043, code lost:
        r7 = r0;
        r0 = r1.getInterceptorScopedExchange$okhttp();
        r6 = followUpRequest(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004c, code lost:
        if (r6 != null) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004e, code lost:
        if (r0 == null) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0054, code lost:
        if (r0.isDuplex$okhttp() == false) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0056, code lost:
        r1.timeoutEarlyExit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0059, code lost:
        r1.exitNetworkInterceptorExchange$okhttp(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005c, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r0 = r6.body();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0061, code lost:
        if (r0 == null) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0067, code lost:
        if (r0.isOneShot() == false) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0069, code lost:
        r1.exitNetworkInterceptorExchange$okhttp(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006c, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r0 = r7.body();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0071, code lost:
        if (r0 == null) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0073, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0078, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007c, code lost:
        if (r8 > 20) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009b, code lost:
        throw new java.net.ProtocolException("Too many follow-up requests: " + r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r7 == null) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r11) throws java.io.IOException {
        /*
            r10 = this;
            java.lang.String r0 = "chain"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            okhttp3.internal.http.RealInterceptorChain r11 = (okhttp3.internal.http.RealInterceptorChain) r11
            okhttp3.Request r0 = r11.getRequest$okhttp()
            okhttp3.internal.connection.RealCall r1 = r11.getCall$okhttp()
            r2 = 0
            r3 = r2
            okhttp3.Response r3 = (okhttp3.Response) r3
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
            r4 = 0
            r5 = 1
            r7 = r2
            r8 = r4
        L_0x001b:
            r6 = r5
        L_0x001c:
            r1.enterNetworkInterceptorExchange(r0, r6)
            boolean r6 = r1.isCanceled()     // Catch:{ all -> 0x00e4 }
            if (r6 != 0) goto L_0x00da
            okhttp3.Response r0 = r11.proceed(r0)     // Catch:{ RouteException -> 0x00b4, IOException -> 0x009c }
            if (r7 == 0) goto L_0x0043
            okhttp3.Response$Builder r0 = r0.newBuilder()     // Catch:{ all -> 0x00e4 }
            okhttp3.Response$Builder r6 = r7.newBuilder()     // Catch:{ all -> 0x00e4 }
            okhttp3.Response$Builder r6 = r6.body(r2)     // Catch:{ all -> 0x00e4 }
            okhttp3.Response r6 = r6.build()     // Catch:{ all -> 0x00e4 }
            okhttp3.Response$Builder r0 = r0.priorResponse(r6)     // Catch:{ all -> 0x00e4 }
            okhttp3.Response r0 = r0.build()     // Catch:{ all -> 0x00e4 }
        L_0x0043:
            r7 = r0
            okhttp3.internal.connection.Exchange r0 = r1.getInterceptorScopedExchange$okhttp()     // Catch:{ all -> 0x00e4 }
            okhttp3.Request r6 = r10.followUpRequest(r7, r0)     // Catch:{ all -> 0x00e4 }
            if (r6 != 0) goto L_0x005d
            if (r0 == 0) goto L_0x0059
            boolean r11 = r0.isDuplex$okhttp()     // Catch:{ all -> 0x00e4 }
            if (r11 == 0) goto L_0x0059
            r1.timeoutEarlyExit()     // Catch:{ all -> 0x00e4 }
        L_0x0059:
            r1.exitNetworkInterceptorExchange$okhttp(r4)
            return r7
        L_0x005d:
            okhttp3.RequestBody r0 = r6.body()     // Catch:{ all -> 0x00e4 }
            if (r0 == 0) goto L_0x006d
            boolean r0 = r0.isOneShot()     // Catch:{ all -> 0x00e4 }
            if (r0 == 0) goto L_0x006d
            r1.exitNetworkInterceptorExchange$okhttp(r4)
            return r7
        L_0x006d:
            okhttp3.ResponseBody r0 = r7.body()     // Catch:{ all -> 0x00e4 }
            if (r0 == 0) goto L_0x0078
            java.io.Closeable r0 = (java.io.Closeable) r0     // Catch:{ all -> 0x00e4 }
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0)     // Catch:{ all -> 0x00e4 }
        L_0x0078:
            int r8 = r8 + 1
            r0 = 20
            if (r8 > r0) goto L_0x0083
            r1.exitNetworkInterceptorExchange$okhttp(r5)
            r0 = r6
            goto L_0x001b
        L_0x0083:
            java.net.ProtocolException r11 = new java.net.ProtocolException     // Catch:{ all -> 0x00e4 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e4 }
            r0.<init>()     // Catch:{ all -> 0x00e4 }
            java.lang.String r2 = "Too many follow-up requests: "
            r0.append(r2)     // Catch:{ all -> 0x00e4 }
            r0.append(r8)     // Catch:{ all -> 0x00e4 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00e4 }
            r11.<init>(r0)     // Catch:{ all -> 0x00e4 }
            java.lang.Throwable r11 = (java.lang.Throwable) r11     // Catch:{ all -> 0x00e4 }
            throw r11     // Catch:{ all -> 0x00e4 }
        L_0x009c:
            r6 = move-exception
            boolean r9 = r6 instanceof okhttp3.internal.http2.ConnectionShutdownException     // Catch:{ all -> 0x00e4 }
            r9 = r9 ^ r5
            boolean r9 = r10.recover(r6, r1, r0, r9)     // Catch:{ all -> 0x00e4 }
            if (r9 == 0) goto L_0x00ad
            java.util.Collection r3 = (java.util.Collection) r3     // Catch:{ all -> 0x00e4 }
            java.util.List r3 = kotlin.collections.CollectionsKt.plus(r3, r6)     // Catch:{ all -> 0x00e4 }
            goto L_0x00c9
        L_0x00ad:
            java.lang.Exception r6 = (java.lang.Exception) r6     // Catch:{ all -> 0x00e4 }
            java.lang.Throwable r11 = okhttp3.internal.Util.withSuppressed(r6, r3)     // Catch:{ all -> 0x00e4 }
            throw r11     // Catch:{ all -> 0x00e4 }
        L_0x00b4:
            r6 = move-exception
            java.io.IOException r9 = r6.getLastConnectException()     // Catch:{ all -> 0x00e4 }
            boolean r9 = r10.recover(r9, r1, r0, r4)     // Catch:{ all -> 0x00e4 }
            if (r9 == 0) goto L_0x00cf
            java.util.Collection r3 = (java.util.Collection) r3     // Catch:{ all -> 0x00e4 }
            java.io.IOException r6 = r6.getFirstConnectException()     // Catch:{ all -> 0x00e4 }
            java.util.List r3 = kotlin.collections.CollectionsKt.plus(r3, r6)     // Catch:{ all -> 0x00e4 }
        L_0x00c9:
            r1.exitNetworkInterceptorExchange$okhttp(r5)
            r6 = r4
            goto L_0x001c
        L_0x00cf:
            java.io.IOException r11 = r6.getFirstConnectException()     // Catch:{ all -> 0x00e4 }
            java.lang.Exception r11 = (java.lang.Exception) r11     // Catch:{ all -> 0x00e4 }
            java.lang.Throwable r11 = okhttp3.internal.Util.withSuppressed(r11, r3)     // Catch:{ all -> 0x00e4 }
            throw r11     // Catch:{ all -> 0x00e4 }
        L_0x00da:
            java.io.IOException r11 = new java.io.IOException     // Catch:{ all -> 0x00e4 }
            java.lang.String r0 = "Canceled"
            r11.<init>(r0)     // Catch:{ all -> 0x00e4 }
            java.lang.Throwable r11 = (java.lang.Throwable) r11     // Catch:{ all -> 0x00e4 }
            throw r11     // Catch:{ all -> 0x00e4 }
        L_0x00e4:
            r11 = move-exception
            r1.exitNetworkInterceptorExchange$okhttp(r5)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    private final boolean recover(IOException iOException, RealCall realCall, Request request, boolean z) {
        if (!this.client.retryOnConnectionFailure()) {
            return false;
        }
        if ((!z || !requestIsOneShot(iOException, request)) && isRecoverable(iOException, z) && realCall.retryAfterFailure()) {
            return true;
        }
        return false;
    }

    private final boolean requestIsOneShot(IOException iOException, Request request) {
        RequestBody body = request.body();
        return (body != null && body.isOneShot()) || (iOException instanceof FileNotFoundException);
    }

    private final boolean isRecoverable(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            if (!(iOException instanceof SocketTimeoutException) || z) {
                return false;
            }
            return true;
        } else if ((!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException)) {
            return true;
        } else {
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r1 = r7.getConnection$okhttp();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final okhttp3.Request followUpRequest(okhttp3.Response r6, okhttp3.internal.connection.Exchange r7) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            if (r7 == 0) goto L_0x000e
            okhttp3.internal.connection.RealConnection r1 = r7.getConnection$okhttp()
            if (r1 == 0) goto L_0x000e
            okhttp3.Route r1 = r1.route()
            goto L_0x000f
        L_0x000e:
            r1 = r0
        L_0x000f:
            int r2 = r6.code()
            okhttp3.Request r3 = r6.request()
            java.lang.String r3 = r3.method()
            r4 = 307(0x133, float:4.3E-43)
            if (r2 == r4) goto L_0x00e1
            r4 = 308(0x134, float:4.32E-43)
            if (r2 == r4) goto L_0x00e1
            r4 = 401(0x191, float:5.62E-43)
            if (r2 == r4) goto L_0x00d6
            r4 = 421(0x1a5, float:5.9E-43)
            if (r2 == r4) goto L_0x00af
            r7 = 503(0x1f7, float:7.05E-43)
            if (r2 == r7) goto L_0x0093
            r7 = 407(0x197, float:5.7E-43)
            if (r2 == r7) goto L_0x006f
            r7 = 408(0x198, float:5.72E-43)
            if (r2 == r7) goto L_0x003b
            switch(r2) {
                case 300: goto L_0x00e1;
                case 301: goto L_0x00e1;
                case 302: goto L_0x00e1;
                case 303: goto L_0x00e1;
                default: goto L_0x003a;
            }
        L_0x003a:
            return r0
        L_0x003b:
            okhttp3.OkHttpClient r1 = r5.client
            boolean r1 = r1.retryOnConnectionFailure()
            if (r1 != 0) goto L_0x0044
            return r0
        L_0x0044:
            okhttp3.Request r1 = r6.request()
            okhttp3.RequestBody r1 = r1.body()
            if (r1 == 0) goto L_0x0055
            boolean r1 = r1.isOneShot()
            if (r1 == 0) goto L_0x0055
            return r0
        L_0x0055:
            okhttp3.Response r1 = r6.priorResponse()
            if (r1 == 0) goto L_0x0062
            int r1 = r1.code()
            if (r1 != r7) goto L_0x0062
            return r0
        L_0x0062:
            r7 = 0
            int r7 = r5.retryAfter(r6, r7)
            if (r7 <= 0) goto L_0x006a
            return r0
        L_0x006a:
            okhttp3.Request r6 = r6.request()
            return r6
        L_0x006f:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.net.Proxy r7 = r1.proxy()
            java.net.Proxy$Type r7 = r7.type()
            java.net.Proxy$Type r0 = java.net.Proxy.Type.HTTP
            if (r7 != r0) goto L_0x0089
            okhttp3.OkHttpClient r7 = r5.client
            okhttp3.Authenticator r7 = r7.proxyAuthenticator()
            okhttp3.Request r6 = r7.authenticate(r1, r6)
            return r6
        L_0x0089:
            java.net.ProtocolException r6 = new java.net.ProtocolException
            java.lang.String r7 = "Received HTTP_PROXY_AUTH (407) code while not using proxy"
            r6.<init>(r7)
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            throw r6
        L_0x0093:
            okhttp3.Response r1 = r6.priorResponse()
            if (r1 == 0) goto L_0x00a0
            int r1 = r1.code()
            if (r1 != r7) goto L_0x00a0
            return r0
        L_0x00a0:
            r7 = 2147483647(0x7fffffff, float:NaN)
            int r7 = r5.retryAfter(r6, r7)
            if (r7 != 0) goto L_0x00ae
            okhttp3.Request r6 = r6.request()
            return r6
        L_0x00ae:
            return r0
        L_0x00af:
            okhttp3.Request r1 = r6.request()
            okhttp3.RequestBody r1 = r1.body()
            if (r1 == 0) goto L_0x00c0
            boolean r1 = r1.isOneShot()
            if (r1 == 0) goto L_0x00c0
            return r0
        L_0x00c0:
            if (r7 == 0) goto L_0x00d5
            boolean r1 = r7.isCoalescedConnection$okhttp()
            if (r1 != 0) goto L_0x00c9
            goto L_0x00d5
        L_0x00c9:
            okhttp3.internal.connection.RealConnection r7 = r7.getConnection$okhttp()
            r7.noCoalescedConnections$okhttp()
            okhttp3.Request r6 = r6.request()
            return r6
        L_0x00d5:
            return r0
        L_0x00d6:
            okhttp3.OkHttpClient r7 = r5.client
            okhttp3.Authenticator r7 = r7.authenticator()
            okhttp3.Request r6 = r7.authenticate(r1, r6)
            return r6
        L_0x00e1:
            okhttp3.Request r6 = r5.buildRedirectRequest(r6, r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.RetryAndFollowUpInterceptor.followUpRequest(okhttp3.Response, okhttp3.internal.connection.Exchange):okhttp3.Request");
    }

    private final Request buildRedirectRequest(Response response, String str) {
        String header$default;
        HttpUrl resolve;
        RequestBody requestBody = null;
        if (!this.client.followRedirects() || (header$default = Response.header$default(response, "Location", (String) null, 2, (Object) null)) == null || (resolve = response.request().url().resolve(header$default)) == null) {
            return null;
        }
        if (!Intrinsics.areEqual((Object) resolve.scheme(), (Object) response.request().url().scheme()) && !this.client.followSslRedirects()) {
            return null;
        }
        Request.Builder newBuilder = response.request().newBuilder();
        if (HttpMethod.permitsRequestBody(str)) {
            int code = response.code();
            boolean z = HttpMethod.INSTANCE.redirectsWithBody(str) || code == 308 || code == 307;
            if (!HttpMethod.INSTANCE.redirectsToGet(str) || code == 308 || code == 307) {
                if (z) {
                    requestBody = response.request().body();
                }
                newBuilder.method(str, requestBody);
            } else {
                newBuilder.method(ShareTarget.METHOD_GET, (RequestBody) null);
            }
            if (!z) {
                newBuilder.removeHeader(HttpHeaders.TRANSFER_ENCODING);
                newBuilder.removeHeader("Content-Length");
                newBuilder.removeHeader("Content-Type");
            }
        }
        if (!Util.canReuseConnectionFor(response.request().url(), resolve)) {
            newBuilder.removeHeader("Authorization");
        }
        return newBuilder.url(resolve).build();
    }

    private final int retryAfter(Response response, int i) {
        String header$default = Response.header$default(response, HttpHeaders.RETRY_AFTER, (String) null, 2, (Object) null);
        if (header$default == null) {
            return i;
        }
        if (!new Regex("\\d+").matches(header$default)) {
            return Integer.MAX_VALUE;
        }
        Integer valueOf = Integer.valueOf(header$default);
        Intrinsics.checkNotNullExpressionValue(valueOf, "Integer.valueOf(header)");
        return valueOf.intValue();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor$Companion;", "", "()V", "MAX_FOLLOW_UPS", "", "okhttp"}, k = 1, mv = {1, 4, 1})
    /* compiled from: RetryAndFollowUpInterceptor.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
