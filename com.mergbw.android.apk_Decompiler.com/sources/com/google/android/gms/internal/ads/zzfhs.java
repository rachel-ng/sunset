package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfhs {
    private final zzaxd zza;

    public zzfhs(zzaxd zzaxd) {
        this.zza = zzaxd;
    }

    public final Uri zza(Uri uri, Context context, View view, Activity activity) throws zzaxe {
        try {
            return zzb(uri, this.zza.zzc().zzf(context, uri.getQueryParameter("ai"), view, activity));
        } catch (UnsupportedOperationException unused) {
            throw new zzaxe("Provided Uri is not in a valid state");
        }
    }

    private static final Uri zzb(Uri uri, String str) throws zzaxe {
        if (uri != null) {
            try {
                String host = uri.getHost();
                String path = uri.getPath();
                if (host != null && host.equals("ad.doubleclick.net") && path != null && path.contains(";")) {
                    try {
                        if (!uri.toString().contains("dc_ms=")) {
                            String uri2 = uri.toString();
                            int indexOf = uri2.indexOf(";adurl");
                            if (indexOf != -1) {
                                int i = indexOf + 1;
                                StringBuilder sb = new StringBuilder(uri2.substring(0, i));
                                sb.append("dc_ms=");
                                sb.append(str);
                                sb.append(";");
                                sb.append(uri2, i, uri2.length());
                                return Uri.parse(sb.toString());
                            }
                            String encodedPath = uri.getEncodedPath();
                            if (encodedPath != null) {
                                int indexOf2 = uri2.indexOf(encodedPath);
                                StringBuilder sb2 = new StringBuilder(uri2.substring(0, encodedPath.length() + indexOf2));
                                sb2.append(";dc_ms=");
                                sb2.append(str);
                                sb2.append(";");
                                sb2.append(uri2, indexOf2 + encodedPath.length(), uri2.length());
                                return Uri.parse(sb2.toString());
                            }
                            throw new UnsupportedOperationException();
                        }
                        throw new zzaxe("Parameter already exists: dc_ms");
                    } catch (UnsupportedOperationException unused) {
                        throw new zzaxe("Provided Uri is not in a valid state");
                    }
                }
            } catch (NullPointerException unused2) {
            }
        }
        if (uri.getQueryParameter("ms") != null) {
            throw new zzaxe("Query parameter already exists: ms");
        } else if (uri != null) {
            String uri3 = uri.toString();
            int indexOf3 = uri3.indexOf("&adurl");
            if (indexOf3 == -1) {
                indexOf3 = uri3.indexOf("?adurl");
            }
            if (indexOf3 == -1) {
                return uri.buildUpon().appendQueryParameter("ms", str).build();
            }
            int i2 = indexOf3 + 1;
            StringBuilder sb3 = new StringBuilder(uri3.substring(0, i2));
            sb3.append("ms=");
            sb3.append(str);
            sb3.append("&");
            sb3.append(uri3, i2, uri3.length());
            return Uri.parse(sb3.toString());
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
