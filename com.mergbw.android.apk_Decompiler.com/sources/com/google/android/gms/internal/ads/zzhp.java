package com.google.android.gms.internal.ads;

import android.net.Uri;
import androidx.webkit.ProxyConfig;
import com.google.android.exoplayer2.PlaybackException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhp extends zzgv implements zzhz {
    private final boolean zzb;
    private final int zzc;
    private final int zzd;
    private final String zze;
    private final zzhy zzf;
    private final zzhy zzg;
    private zzhh zzh;
    private HttpURLConnection zzi;
    private InputStream zzj;
    private boolean zzk;
    private int zzl;
    private long zzm;
    private long zzn;

    @Deprecated
    public zzhp() {
        this((String) null, 8000, 8000, false, (zzhy) null, (zzfyh) null, false);
    }

    /* synthetic */ zzhp(String str, int i, int i2, boolean z, zzhy zzhy, zzfyh zzfyh, boolean z2, zzho zzho) {
        this(str, i, i2, z, zzhy, (zzfyh) null, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.net.HttpURLConnection zzk(java.net.URL r3, int r4, byte[] r5, long r6, long r8, boolean r10, boolean r11, java.util.Map r12) throws java.io.IOException {
        /*
            r2 = this;
            java.net.URLConnection r3 = r3.openConnection()
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3
            int r4 = r2.zzc
            r3.setConnectTimeout(r4)
            int r4 = r2.zzd
            r3.setReadTimeout(r4)
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            com.google.android.gms.internal.ads.zzhy r5 = r2.zzf
            if (r5 == 0) goto L_0x0020
            java.util.Map r5 = r5.zza()
            r4.putAll(r5)
        L_0x0020:
            com.google.android.gms.internal.ads.zzhy r5 = r2.zzg
            java.util.Map r5 = r5.zza()
            r4.putAll(r5)
            r4.putAll(r12)
            java.util.Set r4 = r4.entrySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x0034:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0050
            java.lang.Object r5 = r4.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r12 = r5.getKey()
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            r3.setRequestProperty(r12, r5)
            goto L_0x0034
        L_0x0050:
            r4 = 0
            int r12 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            r0 = -1
            if (r12 != 0) goto L_0x005f
            int r6 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r6 != 0) goto L_0x005e
            r4 = 0
            goto L_0x007b
        L_0x005e:
            r6 = r4
        L_0x005f:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "bytes="
            r4.<init>(r5)
            r4.append(r6)
            java.lang.String r5 = "-"
            r4.append(r5)
            int r5 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r5 == 0) goto L_0x0077
            long r6 = r6 + r8
            long r6 = r6 + r0
            r4.append(r6)
        L_0x0077:
            java.lang.String r4 = r4.toString()
        L_0x007b:
            if (r4 == 0) goto L_0x0082
            java.lang.String r5 = "Range"
            r3.setRequestProperty(r5, r4)
        L_0x0082:
            java.lang.String r4 = r2.zze
            if (r4 == 0) goto L_0x008b
            java.lang.String r5 = "User-Agent"
            r3.setRequestProperty(r5, r4)
        L_0x008b:
            r4 = 1
            if (r4 == r10) goto L_0x0091
            java.lang.String r4 = "identity"
            goto L_0x0093
        L_0x0091:
            java.lang.String r4 = "gzip"
        L_0x0093:
            java.lang.String r5 = "Accept-Encoding"
            r3.setRequestProperty(r5, r4)
            r3.setInstanceFollowRedirects(r11)
            r4 = 0
            r3.setDoOutput(r4)
            int r4 = com.google.android.gms.internal.ads.zzhh.zzh
            java.lang.String r4 = "GET"
            r3.setRequestMethod(r4)
            r3.connect()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhp.zzk(java.net.URL, int, byte[], long, long, boolean, boolean, java.util.Map):java.net.HttpURLConnection");
    }

    private final URL zzl(URL url, String str, zzhh zzhh) throws zzhv {
        if (str != null) {
            try {
                URL url2 = new URL(url, str);
                String protocol = url2.getProtocol();
                if (!ProxyConfig.MATCH_HTTPS.equals(protocol) && !ProxyConfig.MATCH_HTTP.equals(protocol)) {
                    throw new zzhv("Unsupported protocol redirect: ".concat(String.valueOf(protocol)), zzhh, (int) PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED, 1);
                } else if (this.zzb || protocol.equals(url.getProtocol())) {
                    return url2;
                } else {
                    String protocol2 = url.getProtocol();
                    throw new zzhv("Disallowed cross-protocol redirect (" + protocol2 + " to " + protocol + ")", zzhh, (int) PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED, 1);
                }
            } catch (MalformedURLException e) {
                throw new zzhv((IOException) e, zzhh, (int) PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED, 1);
            }
        } else {
            throw new zzhv("Null location redirect", zzhh, (int) PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED, 1);
        }
    }

    private final void zzm() {
        HttpURLConnection httpURLConnection = this.zzi;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
                zzfk.zzd("DefaultHttpDataSource", "Unexpected error while disconnecting", e);
            }
            this.zzi = null;
        }
    }

    public final int zza(byte[] bArr, int i, int i2) throws zzhv {
        if (i2 == 0) {
            return 0;
        }
        try {
            long j = this.zzm;
            if (j != -1) {
                long j2 = j - this.zzn;
                if (j2 != 0) {
                    i2 = (int) Math.min((long) i2, j2);
                }
                return -1;
            }
            InputStream inputStream = this.zzj;
            int i3 = zzgd.zza;
            int read = inputStream.read(bArr, i, i2);
            if (read == -1) {
                return -1;
            }
            this.zzn += (long) read;
            zzg(read);
            return read;
        } catch (IOException e) {
            zzhh zzhh = this.zzh;
            int i4 = zzgd.zza;
            throw zzhv.zza(e, zzhh, 2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b8, code lost:
        if (r8 == 0) goto L_0x00ba;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long zzb(com.google.android.gms.internal.ads.zzhh r25) throws com.google.android.gms.internal.ads.zzhv {
        /*
            r24 = this;
            r12 = r24
            r13 = r25
            r12.zzh = r13
            r14 = 0
            r12.zzn = r14
            r12.zzm = r14
            r24.zzi(r25)
            r11 = 1
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x01f6 }
            android.net.Uri r0 = r13.zza     // Catch:{ IOException -> 0x01f6 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x01f6 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x01f6 }
            int r0 = r13.zzb     // Catch:{ IOException -> 0x01f6 }
            byte[] r0 = r13.zzc     // Catch:{ IOException -> 0x01f6 }
            long r9 = r13.zze     // Catch:{ IOException -> 0x01f6 }
            long r7 = r13.zzf     // Catch:{ IOException -> 0x01f6 }
            boolean r0 = r13.zzb(r11)     // Catch:{ IOException -> 0x01f6 }
            boolean r1 = r12.zzb     // Catch:{ IOException -> 0x01f6 }
            r5 = 0
            if (r1 != 0) goto L_0x0045
            java.util.Map r6 = r13.zzd     // Catch:{ IOException -> 0x0041 }
            r3 = 1
            r4 = 0
            r16 = 1
            r1 = r24
            r17 = r6
            r5 = r9
            r9 = r0
            r10 = r16
            r11 = r17
            java.net.HttpURLConnection r0 = r1.zzk(r2, r3, r4, r5, r7, r9, r10, r11)     // Catch:{ IOException -> 0x0041 }
            goto L_0x008f
        L_0x0041:
            r0 = move-exception
            r5 = 1
            goto L_0x01f8
        L_0x0045:
            r11 = r2
            r5 = 0
        L_0x0047:
            int r6 = r5 + 1
            r1 = 20
            if (r5 > r1) goto L_0x01d1
            java.util.Map r5 = r13.zzd     // Catch:{ IOException -> 0x0041 }
            r3 = 1
            r4 = 0
            r16 = 0
            r1 = r24
            r2 = r11
            r17 = r5
            r18 = r6
            r5 = r9
            r19 = r7
            r21 = r9
            r9 = r0
            r10 = r16
            r23 = r11
            r11 = r17
            java.net.HttpURLConnection r1 = r1.zzk(r2, r3, r4, r5, r7, r9, r10, r11)     // Catch:{ IOException -> 0x0041 }
            int r2 = r1.getResponseCode()     // Catch:{ IOException -> 0x0041 }
            java.lang.String r3 = "Location"
            java.lang.String r3 = r1.getHeaderField(r3)     // Catch:{ IOException -> 0x0041 }
            r4 = 300(0x12c, float:4.2E-43)
            if (r2 == r4) goto L_0x01be
            r4 = 301(0x12d, float:4.22E-43)
            if (r2 == r4) goto L_0x01be
            r4 = 302(0x12e, float:4.23E-43)
            if (r2 == r4) goto L_0x01be
            r4 = 303(0x12f, float:4.25E-43)
            if (r2 == r4) goto L_0x01be
            r4 = 307(0x133, float:4.3E-43)
            if (r2 == r4) goto L_0x01be
            r4 = 308(0x134, float:4.32E-43)
            if (r2 != r4) goto L_0x008e
            goto L_0x01be
        L_0x008e:
            r0 = r1
        L_0x008f:
            r12.zzi = r0     // Catch:{ IOException -> 0x0041 }
            int r1 = r0.getResponseCode()     // Catch:{ IOException -> 0x0041 }
            r12.zzl = r1     // Catch:{ IOException -> 0x0041 }
            java.lang.String r3 = r0.getResponseMessage()     // Catch:{ IOException -> 0x0041 }
            int r1 = r12.zzl
            r2 = 2008(0x7d8, float:2.814E-42)
            java.lang.String r4 = "Content-Range"
            r5 = 200(0xc8, float:2.8E-43)
            r6 = -1
            if (r1 < r5) goto L_0x0166
            r8 = 299(0x12b, float:4.19E-43)
            if (r1 <= r8) goto L_0x00ad
            goto L_0x0166
        L_0x00ad:
            r0.getContentType()
            int r1 = r12.zzl
            if (r1 != r5) goto L_0x00ba
            long r8 = r13.zze
            int r1 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r1 != 0) goto L_0x00bb
        L_0x00ba:
            r8 = r14
        L_0x00bb:
            java.lang.String r1 = "Content-Encoding"
            java.lang.String r1 = r0.getHeaderField(r1)
            java.lang.String r3 = "gzip"
            boolean r1 = r3.equalsIgnoreCase(r1)
            if (r1 != 0) goto L_0x00e9
            long r10 = r13.zzf
            int r3 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r3 == 0) goto L_0x00d2
            r12.zzm = r10
            goto L_0x00ed
        L_0x00d2:
            java.lang.String r3 = "Content-Length"
            java.lang.String r3 = r0.getHeaderField(r3)
            java.lang.String r4 = r0.getHeaderField(r4)
            long r3 = com.google.android.gms.internal.ads.zzia.zza(r3, r4)
            int r5 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r5 == 0) goto L_0x00e6
            long r6 = r3 - r8
        L_0x00e6:
            r12.zzm = r6
            goto L_0x00ed
        L_0x00e9:
            long r3 = r13.zzf
            r12.zzm = r3
        L_0x00ed:
            r3 = 2000(0x7d0, float:2.803E-42)
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ IOException -> 0x015b }
            r12.zzj = r0     // Catch:{ IOException -> 0x015b }
            if (r1 == 0) goto L_0x0100
            java.util.zip.GZIPInputStream r0 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x015b }
            java.io.InputStream r1 = r12.zzj     // Catch:{ IOException -> 0x015b }
            r0.<init>(r1)     // Catch:{ IOException -> 0x015b }
            r12.zzj = r0     // Catch:{ IOException -> 0x015b }
        L_0x0100:
            r5 = 1
            r12.zzk = r5
            r24.zzj(r25)
            int r0 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r0 != 0) goto L_0x010b
            goto L_0x0147
        L_0x010b:
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x014a }
        L_0x010f:
            int r1 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r1 <= 0) goto L_0x0147
            r6 = 4096(0x1000, double:2.0237E-320)
            long r6 = java.lang.Math.min(r8, r6)     // Catch:{ IOException -> 0x014a }
            int r1 = (int) r6     // Catch:{ IOException -> 0x014a }
            java.io.InputStream r4 = r12.zzj     // Catch:{ IOException -> 0x014a }
            int r6 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ IOException -> 0x014a }
            r6 = 0
            int r1 = r4.read(r0, r6, r1)     // Catch:{ IOException -> 0x014a }
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch:{ IOException -> 0x014a }
            boolean r4 = r4.isInterrupted()     // Catch:{ IOException -> 0x014a }
            if (r4 != 0) goto L_0x013c
            r4 = -1
            if (r1 == r4) goto L_0x0136
            long r10 = (long) r1     // Catch:{ IOException -> 0x014a }
            long r8 = r8 - r10
            r12.zzg(r1)     // Catch:{ IOException -> 0x014a }
            goto L_0x010f
        L_0x0136:
            com.google.android.gms.internal.ads.zzhv r0 = new com.google.android.gms.internal.ads.zzhv     // Catch:{ IOException -> 0x014a }
            r0.<init>(r13, r2, r5)     // Catch:{ IOException -> 0x014a }
            throw r0     // Catch:{ IOException -> 0x014a }
        L_0x013c:
            com.google.android.gms.internal.ads.zzhv r0 = new com.google.android.gms.internal.ads.zzhv     // Catch:{ IOException -> 0x014a }
            java.io.InterruptedIOException r1 = new java.io.InterruptedIOException     // Catch:{ IOException -> 0x014a }
            r1.<init>()     // Catch:{ IOException -> 0x014a }
            r0.<init>((java.io.IOException) r1, (com.google.android.gms.internal.ads.zzhh) r13, (int) r3, (int) r5)     // Catch:{ IOException -> 0x014a }
            throw r0     // Catch:{ IOException -> 0x014a }
        L_0x0147:
            long r0 = r12.zzm
            return r0
        L_0x014a:
            r0 = move-exception
            r24.zzm()
            boolean r1 = r0 instanceof com.google.android.gms.internal.ads.zzhv
            if (r1 == 0) goto L_0x0155
            com.google.android.gms.internal.ads.zzhv r0 = (com.google.android.gms.internal.ads.zzhv) r0
            throw r0
        L_0x0155:
            com.google.android.gms.internal.ads.zzhv r1 = new com.google.android.gms.internal.ads.zzhv
            r1.<init>((java.io.IOException) r0, (com.google.android.gms.internal.ads.zzhh) r13, (int) r3, (int) r5)
            throw r1
        L_0x015b:
            r0 = move-exception
            r5 = 1
            r24.zzm()
            com.google.android.gms.internal.ads.zzhv r1 = new com.google.android.gms.internal.ads.zzhv
            r1.<init>((java.io.IOException) r0, (com.google.android.gms.internal.ads.zzhh) r13, (int) r3, (int) r5)
            throw r1
        L_0x0166:
            r5 = 1
            java.util.Map r8 = r0.getHeaderFields()
            int r1 = r12.zzl
            r9 = 416(0x1a0, float:5.83E-43)
            if (r1 != r9) goto L_0x018d
            java.lang.String r1 = r0.getHeaderField(r4)
            long r10 = com.google.android.gms.internal.ads.zzia.zzb(r1)
            r4 = r3
            long r2 = r13.zze
            int r2 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r2 != 0) goto L_0x018e
            r12.zzk = r5
            r24.zzj(r25)
            long r0 = r13.zzf
            int r2 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x018c
            return r0
        L_0x018c:
            return r14
        L_0x018d:
            r4 = r3
        L_0x018e:
            java.io.InputStream r0 = r0.getErrorStream()
            if (r0 == 0) goto L_0x0199
            byte[] r0 = com.google.android.gms.internal.ads.zzgdm.zzb(r0)     // Catch:{ IOException -> 0x019d }
            goto L_0x019b
        L_0x0199:
            byte[] r0 = com.google.android.gms.internal.ads.zzgd.zzf     // Catch:{ IOException -> 0x019d }
        L_0x019b:
            r7 = r0
            goto L_0x01a0
        L_0x019d:
            byte[] r0 = com.google.android.gms.internal.ads.zzgd.zzf
            goto L_0x019b
        L_0x01a0:
            r24.zzm()
            int r0 = r12.zzl
            if (r0 != r9) goto L_0x01af
            com.google.android.gms.internal.ads.zzhc r0 = new com.google.android.gms.internal.ads.zzhc
            r1 = 2008(0x7d8, float:2.814E-42)
            r0.<init>(r1)
            goto L_0x01b0
        L_0x01af:
            r0 = 0
        L_0x01b0:
            com.google.android.gms.internal.ads.zzhx r9 = new com.google.android.gms.internal.ads.zzhx
            int r2 = r12.zzl
            r1 = r9
            r3 = r4
            r4 = r0
            r5 = r8
            r6 = r25
            r1.<init>(r2, r3, r4, r5, r6, r7)
            throw r9
        L_0x01be:
            r5 = 1
            r6 = 0
            r1.disconnect()     // Catch:{ IOException -> 0x01f4 }
            r2 = r23
            java.net.URL r11 = r12.zzl(r2, r3, r13)     // Catch:{ IOException -> 0x01f4 }
            r5 = r18
            r7 = r19
            r9 = r21
            goto L_0x0047
        L_0x01d1:
            r18 = r6
            r5 = 1
            com.google.android.gms.internal.ads.zzhv r0 = new com.google.android.gms.internal.ads.zzhv     // Catch:{ IOException -> 0x01f4 }
            java.net.NoRouteToHostException r1 = new java.net.NoRouteToHostException     // Catch:{ IOException -> 0x01f4 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01f4 }
            r2.<init>()     // Catch:{ IOException -> 0x01f4 }
            java.lang.String r3 = "Too many redirects: "
            r2.append(r3)     // Catch:{ IOException -> 0x01f4 }
            r3 = r18
            r2.append(r3)     // Catch:{ IOException -> 0x01f4 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x01f4 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x01f4 }
            r2 = 2001(0x7d1, float:2.804E-42)
            r0.<init>((java.io.IOException) r1, (com.google.android.gms.internal.ads.zzhh) r13, (int) r2, (int) r5)     // Catch:{ IOException -> 0x01f4 }
            throw r0     // Catch:{ IOException -> 0x01f4 }
        L_0x01f4:
            r0 = move-exception
            goto L_0x01f8
        L_0x01f6:
            r0 = move-exception
            r5 = r11
        L_0x01f8:
            r24.zzm()
            com.google.android.gms.internal.ads.zzhv r0 = com.google.android.gms.internal.ads.zzhv.zza(r0, r13, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhp.zzb(com.google.android.gms.internal.ads.zzhh):long");
    }

    public final Uri zzc() {
        HttpURLConnection httpURLConnection = this.zzi;
        if (httpURLConnection == null) {
            return null;
        }
        return Uri.parse(httpURLConnection.getURL().toString());
    }

    public final void zzd() throws zzhv {
        try {
            InputStream inputStream = this.zzj;
            if (inputStream != null) {
                if (this.zzi != null) {
                    int i = zzgd.zza;
                }
                inputStream.close();
            }
            this.zzj = null;
            zzm();
            if (this.zzk) {
                this.zzk = false;
                zzh();
            }
        } catch (IOException e) {
            zzhh zzhh = this.zzh;
            int i2 = zzgd.zza;
            throw new zzhv(e, zzhh, 2000, 3);
        } catch (Throwable th) {
            this.zzj = null;
            zzm();
            if (this.zzk) {
                this.zzk = false;
                zzh();
            }
            throw th;
        }
    }

    public final Map zze() {
        HttpURLConnection httpURLConnection = this.zzi;
        return httpURLConnection == null ? zzgbf.zzd() : new zzhn(httpURLConnection.getHeaderFields());
    }

    private zzhp(String str, int i, int i2, boolean z, zzhy zzhy, zzfyh zzfyh, boolean z2) {
        super(true);
        this.zze = str;
        this.zzc = i;
        this.zzd = i2;
        this.zzb = z;
        this.zzf = zzhy;
        this.zzg = new zzhy();
    }
}
