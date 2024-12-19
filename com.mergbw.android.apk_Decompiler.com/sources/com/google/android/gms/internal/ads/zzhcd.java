package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public class zzhcd extends IOException {
    private zzhde zza = null;
    private boolean zzb;

    public zzhcd(IOException iOException) {
        super(iOException.getMessage(), iOException);
    }

    static zzhcc zza() {
        return new zzhcc("Protocol message tag had invalid wire type.");
    }

    static zzhcd zzb() {
        return new zzhcd("Protocol message end-group tag did not match expected tag.");
    }

    static zzhcd zzc() {
        return new zzhcd("Protocol message contained an invalid tag (zero).");
    }

    static zzhcd zzd() {
        return new zzhcd("Protocol message had invalid UTF-8.");
    }

    static zzhcd zze() {
        return new zzhcd("CodedInputStream encountered a malformed varint.");
    }

    static zzhcd zzf() {
        return new zzhcd("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzhcd zzg() {
        return new zzhcd("Failed to parse the message.");
    }

    static zzhcd zzi() {
        return new zzhcd("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    static zzhcd zzj() {
        return new zzhcd("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzhcd zzh(zzhde zzhde) {
        this.zza = zzhde;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final void zzk() {
        this.zzb = true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzl() {
        return this.zzb;
    }

    public zzhcd(String str) {
        super(str);
    }
}
