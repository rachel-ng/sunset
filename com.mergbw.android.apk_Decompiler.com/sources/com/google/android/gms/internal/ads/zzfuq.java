package com.google.android.gms.internal.ads;

import android.net.Network;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzfuq extends zzfue {
    private zzfyw<Integer> zza;
    private zzfyw<Integer> zzb;
    private zzfup zzc;
    private HttpURLConnection zzd;

    zzfuq() {
        this(new zzfug(), new zzfuh(), (zzfup) null);
    }

    zzfuq(zzfyw<Integer> zzfyw, zzfyw<Integer> zzfyw2, zzfup zzfup) {
        this.zza = zzfyw;
        this.zzb = zzfyw2;
        this.zzc = zzfup;
    }

    static /* synthetic */ Integer zzf() {
        return -1;
    }

    static /* synthetic */ Integer zzg() {
        return -1;
    }

    public static void zzs(HttpURLConnection httpURLConnection) {
        zzfuf.zza();
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public void close() {
        zzs(this.zzd);
    }

    public HttpURLConnection zzm() throws IOException {
        zzfuf.zzb(((Integer) this.zza.zza()).intValue(), ((Integer) this.zzb.zza()).intValue());
        zzfup zzfup = this.zzc;
        zzfup.getClass();
        HttpURLConnection httpURLConnection = (HttpURLConnection) zzfup.zza();
        this.zzd = httpURLConnection;
        return httpURLConnection;
    }

    public HttpURLConnection zzn(zzfup zzfup, int i, int i2) throws IOException {
        this.zza = new zzfui(i);
        this.zzb = new zzfuj(i2);
        this.zzc = zzfup;
        return zzm();
    }

    public HttpURLConnection zzo(Network network, URL url, int i, int i2) throws IOException {
        this.zza = new zzfuk(i);
        this.zzb = new zzful(i2);
        this.zzc = new zzfum(network, url);
        return zzm();
    }

    public URLConnection zzr(URL url, int i) throws IOException {
        this.zza = new zzfun(i);
        this.zzc = new zzfuo(url);
        return zzm();
    }
}
