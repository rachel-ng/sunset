package com.google.android.exoplayer2.metadata.icy;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public final class IcyHeaders implements Metadata.Entry {
    public static final Parcelable.Creator<IcyHeaders> CREATOR = new Parcelable.Creator<IcyHeaders>() {
        public IcyHeaders createFromParcel(Parcel parcel) {
            return new IcyHeaders(parcel);
        }

        public IcyHeaders[] newArray(int i) {
            return new IcyHeaders[i];
        }
    };
    public static final String REQUEST_HEADER_ENABLE_METADATA_NAME = "Icy-MetaData";
    public static final String REQUEST_HEADER_ENABLE_METADATA_VALUE = "1";
    private static final String RESPONSE_HEADER_BITRATE = "icy-br";
    private static final String RESPONSE_HEADER_GENRE = "icy-genre";
    private static final String RESPONSE_HEADER_METADATA_INTERVAL = "icy-metaint";
    private static final String RESPONSE_HEADER_NAME = "icy-name";
    private static final String RESPONSE_HEADER_PUB = "icy-pub";
    private static final String RESPONSE_HEADER_URL = "icy-url";
    private static final String TAG = "IcyHeaders";
    public final int bitrate;
    public final String genre;
    public final boolean isPublic;
    public final int metadataInterval;
    public final String name;
    public final String url;

    public int describeContents() {
        return 0;
    }

    public /* synthetic */ byte[] getWrappedMetadataBytes() {
        return Metadata.Entry.CC.$default$getWrappedMetadataBytes(this);
    }

    public /* synthetic */ Format getWrappedMetadataFormat() {
        return Metadata.Entry.CC.$default$getWrappedMetadataFormat(this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.metadata.icy.IcyHeaders parse(java.util.Map<java.lang.String, java.util.List<java.lang.String>> r14) {
        /*
            java.lang.String r0 = "Invalid metadata interval: "
            java.lang.String r1 = "Invalid bitrate: "
            java.lang.String r2 = "icy-br"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            java.lang.String r3 = "IcyHeaders"
            r4 = 1
            r5 = 0
            r6 = -1
            if (r2 == 0) goto L_0x004a
            java.lang.Object r2 = r2.get(r5)
            java.lang.String r2 = (java.lang.String) r2
            int r7 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0035 }
            int r7 = r7 * 1000
            if (r7 <= 0) goto L_0x0023
            r1 = r4
            goto L_0x0048
        L_0x0023:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0036 }
            r8.<init>(r1)     // Catch:{ NumberFormatException -> 0x0036 }
            r8.append(r2)     // Catch:{ NumberFormatException -> 0x0036 }
            java.lang.String r1 = r8.toString()     // Catch:{ NumberFormatException -> 0x0036 }
            com.google.android.exoplayer2.util.Log.w(r3, r1)     // Catch:{ NumberFormatException -> 0x0036 }
            r1 = r5
            r7 = r6
            goto L_0x0048
        L_0x0035:
            r7 = r6
        L_0x0036:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r8 = "Invalid bitrate header: "
            r1.<init>(r8)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.google.android.exoplayer2.util.Log.w(r3, r1)
            r1 = r5
        L_0x0048:
            r8 = r7
            goto L_0x004c
        L_0x004a:
            r1 = r5
            r8 = r6
        L_0x004c:
            java.lang.String r2 = "icy-genre"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            r7 = 0
            if (r2 == 0) goto L_0x0060
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            r9 = r1
            r1 = r4
            goto L_0x0061
        L_0x0060:
            r9 = r7
        L_0x0061:
            java.lang.String r2 = "icy-name"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x0074
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            r10 = r1
            r1 = r4
            goto L_0x0075
        L_0x0074:
            r10 = r7
        L_0x0075:
            java.lang.String r2 = "icy-url"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x0088
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            r11 = r1
            r1 = r4
            goto L_0x0089
        L_0x0088:
            r11 = r7
        L_0x0089:
            java.lang.String r2 = "icy-pub"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x00a2
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "1"
            boolean r1 = r1.equals(r2)
            r12 = r1
            r1 = r4
            goto L_0x00a3
        L_0x00a2:
            r12 = r5
        L_0x00a3:
            java.lang.String r2 = "icy-metaint"
            java.lang.Object r14 = r14.get(r2)
            java.util.List r14 = (java.util.List) r14
            if (r14 == 0) goto L_0x00dd
            java.lang.Object r14 = r14.get(r5)
            java.lang.String r14 = (java.lang.String) r14
            int r2 = java.lang.Integer.parseInt(r14)     // Catch:{ NumberFormatException -> 0x00ce }
            if (r2 <= 0) goto L_0x00bb
            r6 = r2
            goto L_0x00cb
        L_0x00bb:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x00cd }
            r4.<init>(r0)     // Catch:{ NumberFormatException -> 0x00cd }
            r4.append(r14)     // Catch:{ NumberFormatException -> 0x00cd }
            java.lang.String r4 = r4.toString()     // Catch:{ NumberFormatException -> 0x00cd }
            com.google.android.exoplayer2.util.Log.w(r3, r4)     // Catch:{ NumberFormatException -> 0x00cd }
            r4 = r1
        L_0x00cb:
            r1 = r4
            goto L_0x00dd
        L_0x00cd:
            r6 = r2
        L_0x00ce:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r0)
            r2.append(r14)
            java.lang.String r14 = r2.toString()
            com.google.android.exoplayer2.util.Log.w(r3, r14)
        L_0x00dd:
            r13 = r6
            if (r1 == 0) goto L_0x00e6
            com.google.android.exoplayer2.metadata.icy.IcyHeaders r14 = new com.google.android.exoplayer2.metadata.icy.IcyHeaders
            r7 = r14
            r7.<init>(r8, r9, r10, r11, r12, r13)
        L_0x00e6:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.icy.IcyHeaders.parse(java.util.Map):com.google.android.exoplayer2.metadata.icy.IcyHeaders");
    }

    public IcyHeaders(int i, String str, String str2, String str3, boolean z, int i2) {
        Assertions.checkArgument(i2 == -1 || i2 > 0);
        this.bitrate = i;
        this.genre = str;
        this.name = str2;
        this.url = str3;
        this.isPublic = z;
        this.metadataInterval = i2;
    }

    IcyHeaders(Parcel parcel) {
        this.bitrate = parcel.readInt();
        this.genre = parcel.readString();
        this.name = parcel.readString();
        this.url = parcel.readString();
        this.isPublic = Util.readBoolean(parcel);
        this.metadataInterval = parcel.readInt();
    }

    public void populateMediaMetadata(MediaMetadata.Builder builder) {
        String str = this.name;
        if (str != null) {
            builder.setStation(str);
        }
        String str2 = this.genre;
        if (str2 != null) {
            builder.setGenre(str2);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IcyHeaders icyHeaders = (IcyHeaders) obj;
        if (this.bitrate != icyHeaders.bitrate || !Util.areEqual(this.genre, icyHeaders.genre) || !Util.areEqual(this.name, icyHeaders.name) || !Util.areEqual(this.url, icyHeaders.url) || this.isPublic != icyHeaders.isPublic || this.metadataInterval != icyHeaders.metadataInterval) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = (527 + this.bitrate) * 31;
        String str = this.genre;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.name;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.url;
        if (str3 != null) {
            i2 = str3.hashCode();
        }
        return ((((hashCode2 + i2) * 31) + (this.isPublic ? 1 : 0)) * 31) + this.metadataInterval;
    }

    public String toString() {
        return "IcyHeaders: name=\"" + this.name + "\", genre=\"" + this.genre + "\", bitrate=" + this.bitrate + ", metadataInterval=" + this.metadataInterval;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.bitrate);
        parcel.writeString(this.genre);
        parcel.writeString(this.name);
        parcel.writeString(this.url);
        Util.writeBoolean(parcel, this.isPublic);
        parcel.writeInt(this.metadataInterval);
    }
}
