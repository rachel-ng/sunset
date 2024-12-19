package okhttp3.internal.tls;

import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okio.Utf8;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0002J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\f\u0010\u0017\u001a\u00020\b*\u00020\bH\u0002J\f\u0010\u0018\u001a\u00020\u000e*\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lokhttp3/internal/tls/OkHostnameVerifier;", "Ljavax/net/ssl/HostnameVerifier;", "()V", "ALT_DNS_NAME", "", "ALT_IPA_NAME", "allSubjectAltNames", "", "", "certificate", "Ljava/security/cert/X509Certificate;", "getSubjectAltNames", "type", "verify", "", "host", "session", "Ljavax/net/ssl/SSLSession;", "verifyHostname", "hostname", "pattern", "verifyIpAddress", "ipAddress", "asciiToLowercase", "isAscii", "okhttp"}, k = 1, mv = {1, 4, 1})
/* compiled from: OkHostnameVerifier.kt */
public final class OkHostnameVerifier implements HostnameVerifier {
    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;
    public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();

    private OkHostnameVerifier() {
    }

    public boolean verify(String str, SSLSession sSLSession) {
        Intrinsics.checkNotNullParameter(str, "host");
        Intrinsics.checkNotNullParameter(sSLSession, "session");
        if (!isAscii(str)) {
            return false;
        }
        try {
            Certificate certificate = sSLSession.getPeerCertificates()[0];
            if (certificate != null) {
                return verify(str, (X509Certificate) certificate);
            }
            throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
        } catch (SSLException unused) {
            return false;
        }
    }

    public final boolean verify(String str, X509Certificate x509Certificate) {
        Intrinsics.checkNotNullParameter(str, "host");
        Intrinsics.checkNotNullParameter(x509Certificate, "certificate");
        if (Util.canParseAsIpAddress(str)) {
            return verifyIpAddress(str, x509Certificate);
        }
        return verifyHostname(str, x509Certificate);
    }

    private final boolean verifyIpAddress(String str, X509Certificate x509Certificate) {
        String canonicalHost = HostnamesKt.toCanonicalHost(str);
        Iterable<String> subjectAltNames = getSubjectAltNames(x509Certificate, 7);
        if ((subjectAltNames instanceof Collection) && ((Collection) subjectAltNames).isEmpty()) {
            return false;
        }
        for (String canonicalHost2 : subjectAltNames) {
            if (Intrinsics.areEqual((Object) canonicalHost, (Object) HostnamesKt.toCanonicalHost(canonicalHost2))) {
                return true;
            }
        }
        return false;
    }

    private final boolean verifyHostname(String str, X509Certificate x509Certificate) {
        String asciiToLowercase = asciiToLowercase(str);
        Iterable<String> subjectAltNames = getSubjectAltNames(x509Certificate, 2);
        if ((subjectAltNames instanceof Collection) && ((Collection) subjectAltNames).isEmpty()) {
            return false;
        }
        for (String verifyHostname : subjectAltNames) {
            if (INSTANCE.verifyHostname(asciiToLowercase, verifyHostname)) {
                return true;
            }
        }
        return false;
    }

    private final String asciiToLowercase(String str) {
        if (!isAscii(str)) {
            return str;
        }
        Locale locale = Locale.US;
        Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
        if (str != null) {
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            return lowerCase;
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }

    private final boolean isAscii(String str) {
        return str.length() == ((int) Utf8.size$default(str, 0, 0, 3, (Object) null));
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CodeShrinkVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x0023: MOVE  (r5v2 java.lang.CharSequence) = (r14v0 java.lang.String)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.visit(CodeShrinkVisitor.java:35)
        */
    private final boolean verifyHostname(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r0 = r13
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r1 = 0
            if (r0 == 0) goto L_0x00dd
            int r0 = r0.length()
            if (r0 != 0) goto L_0x000e
            goto L_0x00dd
        L_0x000e:
            java.lang.String r0 = "."
            r2 = 2
            r3 = 0
            boolean r4 = kotlin.text.StringsKt.startsWith$default(r13, r0, r1, r2, r3)
            if (r4 != 0) goto L_0x00dd
            java.lang.String r4 = ".."
            boolean r5 = kotlin.text.StringsKt.endsWith$default(r13, r4, r1, r2, r3)
            if (r5 == 0) goto L_0x0022
            goto L_0x00dd
        L_0x0022:
            r5 = r14
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            if (r5 == 0) goto L_0x00dd
            int r5 = r5.length()
            if (r5 != 0) goto L_0x002f
            goto L_0x00dd
        L_0x002f:
            boolean r5 = kotlin.text.StringsKt.startsWith$default(r14, r0, r1, r2, r3)
            if (r5 != 0) goto L_0x00dd
            boolean r4 = kotlin.text.StringsKt.endsWith$default(r14, r4, r1, r2, r3)
            if (r4 == 0) goto L_0x003d
            goto L_0x00dd
        L_0x003d:
            boolean r4 = kotlin.text.StringsKt.endsWith$default(r13, r0, r1, r2, r3)
            if (r4 != 0) goto L_0x0052
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r13)
            r4.append(r0)
            java.lang.String r13 = r4.toString()
        L_0x0052:
            boolean r4 = kotlin.text.StringsKt.endsWith$default(r14, r0, r1, r2, r3)
            if (r4 != 0) goto L_0x0067
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r14)
            r4.append(r0)
            java.lang.String r14 = r4.toString()
        L_0x0067:
            java.lang.String r14 = r12.asciiToLowercase(r14)
            r4 = r14
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.String r0 = "*"
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            boolean r0 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r4, (java.lang.CharSequence) r0, (boolean) r1, (int) r2, (java.lang.Object) r3)
            if (r0 != 0) goto L_0x007d
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)
            return r13
        L_0x007d:
            java.lang.String r0 = "*."
            boolean r5 = kotlin.text.StringsKt.startsWith$default(r14, r0, r1, r2, r3)
            if (r5 == 0) goto L_0x00dd
            r8 = 4
            r9 = 0
            r5 = 42
            r6 = 1
            r7 = 0
            int r4 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r4, (char) r5, (int) r6, (boolean) r7, (int) r8, (java.lang.Object) r9)
            r5 = -1
            if (r4 == r5) goto L_0x0093
            goto L_0x00dd
        L_0x0093:
            int r4 = r13.length()
            int r6 = r14.length()
            if (r4 >= r6) goto L_0x009e
            return r1
        L_0x009e:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r14)
            if (r0 == 0) goto L_0x00a5
            return r1
        L_0x00a5:
            if (r14 == 0) goto L_0x00d5
            r0 = 1
            java.lang.String r14 = r14.substring(r0)
            java.lang.String r4 = "(this as java.lang.String).substring(startIndex)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r4)
            boolean r2 = kotlin.text.StringsKt.endsWith$default(r13, r14, r1, r2, r3)
            if (r2 != 0) goto L_0x00b8
            return r1
        L_0x00b8:
            int r2 = r13.length()
            int r14 = r14.length()
            int r2 = r2 - r14
            if (r2 <= 0) goto L_0x00d4
            r6 = r13
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            int r8 = r2 + -1
            r10 = 4
            r11 = 0
            r7 = 46
            r9 = 0
            int r13 = kotlin.text.StringsKt.lastIndexOf$default((java.lang.CharSequence) r6, (char) r7, (int) r8, (boolean) r9, (int) r10, (java.lang.Object) r11)
            if (r13 == r5) goto L_0x00d4
            return r1
        L_0x00d4:
            return r0
        L_0x00d5:
            java.lang.NullPointerException r13 = new java.lang.NullPointerException
            java.lang.String r14 = "null cannot be cast to non-null type java.lang.String"
            r13.<init>(r14)
            throw r13
        L_0x00dd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.tls.OkHostnameVerifier.verifyHostname(java.lang.String, java.lang.String):boolean");
    }

    public final List<String> allSubjectAltNames(X509Certificate x509Certificate) {
        Intrinsics.checkNotNullParameter(x509Certificate, "certificate");
        return CollectionsKt.plus(getSubjectAltNames(x509Certificate, 7), getSubjectAltNames(x509Certificate, 2));
    }

    private final List<String> getSubjectAltNames(X509Certificate x509Certificate, int i) {
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return CollectionsKt.emptyList();
            }
            List<String> arrayList = new ArrayList<>();
            for (List next : subjectAlternativeNames) {
                if (next != null) {
                    if (next.size() >= 2) {
                        if (Intrinsics.areEqual(next.get(0), (Object) Integer.valueOf(i))) {
                            Object obj = next.get(1);
                            if (obj == null) {
                                continue;
                            } else if (obj != null) {
                                arrayList.add((String) obj);
                            } else {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                        }
                    }
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return CollectionsKt.emptyList();
        }
    }
}
