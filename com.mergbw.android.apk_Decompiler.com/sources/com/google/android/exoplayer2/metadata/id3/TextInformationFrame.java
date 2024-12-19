package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

public final class TextInformationFrame extends Id3Frame {
    public static final Parcelable.Creator<TextInformationFrame> CREATOR = new Parcelable.Creator<TextInformationFrame>() {
        public TextInformationFrame createFromParcel(Parcel parcel) {
            return new TextInformationFrame(parcel);
        }

        public TextInformationFrame[] newArray(int i) {
            return new TextInformationFrame[i];
        }
    };
    public final String description;
    @Deprecated
    public final String value;
    public final ImmutableList<String> values;

    public TextInformationFrame(String str, String str2, List<String> list) {
        super(str);
        Assertions.checkArgument(!list.isEmpty());
        this.description = str2;
        ImmutableList<String> copyOf = ImmutableList.copyOf(list);
        this.values = copyOf;
        this.value = (String) copyOf.get(0);
    }

    @Deprecated
    public TextInformationFrame(String str, String str2, String str3) {
        this(str, str2, (List<String>) ImmutableList.of(str3));
    }

    private TextInformationFrame(Parcel parcel) {
        this((String) Assertions.checkNotNull(parcel.readString()), parcel.readString(), (List<String>) ImmutableList.copyOf((E[]) (String[]) Assertions.checkNotNull(parcel.createStringArray())));
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void populateMediaMetadata(com.google.android.exoplayer2.MediaMetadata.Builder r9) {
        /*
            r8 = this;
            java.lang.String r0 = r8.id
            r0.hashCode()
            int r1 = r0.hashCode()
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            r7 = -1
            switch(r1) {
                case 82815: goto L_0x0125;
                case 82878: goto L_0x011a;
                case 82897: goto L_0x010f;
                case 83253: goto L_0x0104;
                case 83254: goto L_0x00f9;
                case 83255: goto L_0x00ee;
                case 83341: goto L_0x00e3;
                case 83378: goto L_0x00d8;
                case 83536: goto L_0x00ca;
                case 83552: goto L_0x00bc;
                case 2567331: goto L_0x00ae;
                case 2569357: goto L_0x00a0;
                case 2569891: goto L_0x0092;
                case 2570401: goto L_0x0084;
                case 2570410: goto L_0x0076;
                case 2571565: goto L_0x0068;
                case 2575251: goto L_0x005a;
                case 2581512: goto L_0x004c;
                case 2581513: goto L_0x003e;
                case 2581514: goto L_0x0030;
                case 2583398: goto L_0x0022;
                case 2590194: goto L_0x0014;
                default: goto L_0x0012;
            }
        L_0x0012:
            goto L_0x012f
        L_0x0014:
            java.lang.String r1 = "TYER"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x001e
            goto L_0x012f
        L_0x001e:
            r7 = 21
            goto L_0x012f
        L_0x0022:
            java.lang.String r1 = "TRCK"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x002c
            goto L_0x012f
        L_0x002c:
            r7 = 20
            goto L_0x012f
        L_0x0030:
            java.lang.String r1 = "TPE3"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x003a
            goto L_0x012f
        L_0x003a:
            r7 = 19
            goto L_0x012f
        L_0x003e:
            java.lang.String r1 = "TPE2"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0048
            goto L_0x012f
        L_0x0048:
            r7 = 18
            goto L_0x012f
        L_0x004c:
            java.lang.String r1 = "TPE1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0056
            goto L_0x012f
        L_0x0056:
            r7 = 17
            goto L_0x012f
        L_0x005a:
            java.lang.String r1 = "TIT2"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0064
            goto L_0x012f
        L_0x0064:
            r7 = 16
            goto L_0x012f
        L_0x0068:
            java.lang.String r1 = "TEXT"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0072
            goto L_0x012f
        L_0x0072:
            r7 = 15
            goto L_0x012f
        L_0x0076:
            java.lang.String r1 = "TDRL"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0080
            goto L_0x012f
        L_0x0080:
            r7 = 14
            goto L_0x012f
        L_0x0084:
            java.lang.String r1 = "TDRC"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x008e
            goto L_0x012f
        L_0x008e:
            r7 = 13
            goto L_0x012f
        L_0x0092:
            java.lang.String r1 = "TDAT"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x009c
            goto L_0x012f
        L_0x009c:
            r7 = 12
            goto L_0x012f
        L_0x00a0:
            java.lang.String r1 = "TCOM"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00aa
            goto L_0x012f
        L_0x00aa:
            r7 = 11
            goto L_0x012f
        L_0x00ae:
            java.lang.String r1 = "TALB"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00b8
            goto L_0x012f
        L_0x00b8:
            r7 = 10
            goto L_0x012f
        L_0x00bc:
            java.lang.String r1 = "TYE"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00c6
            goto L_0x012f
        L_0x00c6:
            r7 = 9
            goto L_0x012f
        L_0x00ca:
            java.lang.String r1 = "TXT"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00d4
            goto L_0x012f
        L_0x00d4:
            r7 = 8
            goto L_0x012f
        L_0x00d8:
            java.lang.String r1 = "TT2"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00e1
            goto L_0x012f
        L_0x00e1:
            r7 = 7
            goto L_0x012f
        L_0x00e3:
            java.lang.String r1 = "TRK"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00ec
            goto L_0x012f
        L_0x00ec:
            r7 = 6
            goto L_0x012f
        L_0x00ee:
            java.lang.String r1 = "TP3"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00f7
            goto L_0x012f
        L_0x00f7:
            r7 = 5
            goto L_0x012f
        L_0x00f9:
            java.lang.String r1 = "TP2"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0102
            goto L_0x012f
        L_0x0102:
            r7 = r2
            goto L_0x012f
        L_0x0104:
            java.lang.String r1 = "TP1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x010d
            goto L_0x012f
        L_0x010d:
            r7 = r3
            goto L_0x012f
        L_0x010f:
            java.lang.String r1 = "TDA"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0118
            goto L_0x012f
        L_0x0118:
            r7 = r4
            goto L_0x012f
        L_0x011a:
            java.lang.String r1 = "TCM"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0123
            goto L_0x012f
        L_0x0123:
            r7 = r5
            goto L_0x012f
        L_0x0125:
            java.lang.String r1 = "TAL"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x012e
            goto L_0x012f
        L_0x012e:
            r7 = r6
        L_0x012f:
            switch(r7) {
                case 0: goto L_0x0254;
                case 1: goto L_0x0248;
                case 2: goto L_0x0220;
                case 3: goto L_0x0214;
                case 4: goto L_0x0208;
                case 5: goto L_0x01fc;
                case 6: goto L_0x01cd;
                case 7: goto L_0x01c0;
                case 8: goto L_0x01b3;
                case 9: goto L_0x019e;
                case 10: goto L_0x0254;
                case 11: goto L_0x0248;
                case 12: goto L_0x0220;
                case 13: goto L_0x0169;
                case 14: goto L_0x0134;
                case 15: goto L_0x01b3;
                case 16: goto L_0x01c0;
                case 17: goto L_0x0214;
                case 18: goto L_0x0208;
                case 19: goto L_0x01fc;
                case 20: goto L_0x01cd;
                case 21: goto L_0x019e;
                default: goto L_0x0132;
            }
        L_0x0132:
            goto L_0x025f
        L_0x0134:
            com.google.common.collect.ImmutableList<java.lang.String> r0 = r8.values
            java.lang.Object r0 = r0.get(r6)
            java.lang.String r0 = (java.lang.String) r0
            java.util.List r0 = parseId3v2point4TimestampFrameForDate(r0)
            int r1 = r0.size()
            if (r1 == r5) goto L_0x015e
            if (r1 == r4) goto L_0x0155
            if (r1 == r3) goto L_0x014c
            goto L_0x025f
        L_0x014c:
            java.lang.Object r1 = r0.get(r4)
            java.lang.Integer r1 = (java.lang.Integer) r1
            r9.setReleaseDay(r1)
        L_0x0155:
            java.lang.Object r1 = r0.get(r5)
            java.lang.Integer r1 = (java.lang.Integer) r1
            r9.setReleaseMonth(r1)
        L_0x015e:
            java.lang.Object r0 = r0.get(r6)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r9.setReleaseYear(r0)
            goto L_0x025f
        L_0x0169:
            com.google.common.collect.ImmutableList<java.lang.String> r0 = r8.values
            java.lang.Object r0 = r0.get(r6)
            java.lang.String r0 = (java.lang.String) r0
            java.util.List r0 = parseId3v2point4TimestampFrameForDate(r0)
            int r1 = r0.size()
            if (r1 == r5) goto L_0x0193
            if (r1 == r4) goto L_0x018a
            if (r1 == r3) goto L_0x0181
            goto L_0x025f
        L_0x0181:
            java.lang.Object r1 = r0.get(r4)
            java.lang.Integer r1 = (java.lang.Integer) r1
            r9.setRecordingDay(r1)
        L_0x018a:
            java.lang.Object r1 = r0.get(r5)
            java.lang.Integer r1 = (java.lang.Integer) r1
            r9.setRecordingMonth(r1)
        L_0x0193:
            java.lang.Object r0 = r0.get(r6)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r9.setRecordingYear(r0)
            goto L_0x025f
        L_0x019e:
            com.google.common.collect.ImmutableList<java.lang.String> r0 = r8.values     // Catch:{  }
            java.lang.Object r0 = r0.get(r6)     // Catch:{  }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{  }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{  }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{  }
            r9.setRecordingYear(r0)     // Catch:{  }
            goto L_0x025f
        L_0x01b3:
            com.google.common.collect.ImmutableList<java.lang.String> r0 = r8.values
            java.lang.Object r0 = r0.get(r6)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.setWriter(r0)
            goto L_0x025f
        L_0x01c0:
            com.google.common.collect.ImmutableList<java.lang.String> r0 = r8.values
            java.lang.Object r0 = r0.get(r6)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.setTitle(r0)
            goto L_0x025f
        L_0x01cd:
            com.google.common.collect.ImmutableList<java.lang.String> r0 = r8.values
            java.lang.Object r0 = r0.get(r6)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "/"
            java.lang.String[] r0 = com.google.android.exoplayer2.util.Util.split(r0, r1)
            r1 = r0[r6]     // Catch:{  }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{  }
            int r2 = r0.length     // Catch:{  }
            if (r2 <= r5) goto L_0x01ef
            r0 = r0[r5]     // Catch:{  }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{  }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{  }
            goto L_0x01f0
        L_0x01ef:
            r0 = 0
        L_0x01f0:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{  }
            com.google.android.exoplayer2.MediaMetadata$Builder r9 = r9.setTrackNumber(r1)     // Catch:{  }
            r9.setTotalTrackCount(r0)     // Catch:{  }
            goto L_0x025f
        L_0x01fc:
            com.google.common.collect.ImmutableList<java.lang.String> r0 = r8.values
            java.lang.Object r0 = r0.get(r6)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.setConductor(r0)
            goto L_0x025f
        L_0x0208:
            com.google.common.collect.ImmutableList<java.lang.String> r0 = r8.values
            java.lang.Object r0 = r0.get(r6)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.setAlbumArtist(r0)
            goto L_0x025f
        L_0x0214:
            com.google.common.collect.ImmutableList<java.lang.String> r0 = r8.values
            java.lang.Object r0 = r0.get(r6)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.setArtist(r0)
            goto L_0x025f
        L_0x0220:
            com.google.common.collect.ImmutableList<java.lang.String> r0 = r8.values     // Catch:{ NumberFormatException -> 0x025f }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ NumberFormatException -> 0x025f }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NumberFormatException -> 0x025f }
            java.lang.String r1 = r0.substring(r4, r2)     // Catch:{ NumberFormatException -> 0x025f }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x025f }
            java.lang.String r0 = r0.substring(r6, r4)     // Catch:{ NumberFormatException -> 0x025f }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x025f }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ NumberFormatException -> 0x025f }
            com.google.android.exoplayer2.MediaMetadata$Builder r9 = r9.setRecordingMonth(r1)     // Catch:{ NumberFormatException -> 0x025f }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ NumberFormatException -> 0x025f }
            r9.setRecordingDay(r0)     // Catch:{ NumberFormatException -> 0x025f }
            goto L_0x025f
        L_0x0248:
            com.google.common.collect.ImmutableList<java.lang.String> r0 = r8.values
            java.lang.Object r0 = r0.get(r6)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.setComposer(r0)
            goto L_0x025f
        L_0x0254:
            com.google.common.collect.ImmutableList<java.lang.String> r0 = r8.values
            java.lang.Object r0 = r0.get(r6)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.setAlbumTitle(r0)
        L_0x025f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.id3.TextInformationFrame.populateMediaMetadata(com.google.android.exoplayer2.MediaMetadata$Builder):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TextInformationFrame textInformationFrame = (TextInformationFrame) obj;
        if (!Util.areEqual(this.id, textInformationFrame.id) || !Util.areEqual(this.description, textInformationFrame.description) || !this.values.equals(textInformationFrame.values)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (527 + this.id.hashCode()) * 31;
        String str = this.description;
        return ((hashCode + (str != null ? str.hashCode() : 0)) * 31) + this.values.hashCode();
    }

    public String toString() {
        return this.id + ": description=" + this.description + ": values=" + this.values;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.description);
        parcel.writeStringArray((String[]) this.values.toArray(new String[0]));
    }

    private static List<Integer> parseId3v2point4TimestampFrameForDate(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            if (str.length() >= 10) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(5, 7))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(8, 10))));
            } else if (str.length() >= 7) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(5, 7))));
            } else if (str.length() >= 4) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
            }
            return arrayList;
        } catch (NumberFormatException unused) {
            return new ArrayList();
        }
    }
}
