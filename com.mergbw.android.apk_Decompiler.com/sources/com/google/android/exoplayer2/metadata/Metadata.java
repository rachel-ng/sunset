package com.google.android.exoplayer2.metadata;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.util.Util;
import com.google.common.primitives.Longs;
import java.util.Arrays;
import java.util.List;

public final class Metadata implements Parcelable {
    public static final Parcelable.Creator<Metadata> CREATOR = new Parcelable.Creator<Metadata>() {
        public Metadata createFromParcel(Parcel parcel) {
            return new Metadata(parcel);
        }

        public Metadata[] newArray(int i) {
            return new Metadata[i];
        }
    };
    private final Entry[] entries;
    public final long presentationTimeUs;

    public interface Entry extends Parcelable {

        /* renamed from: com.google.android.exoplayer2.metadata.Metadata$Entry$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static byte[] $default$getWrappedMetadataBytes(Entry _this) {
                return null;
            }

            public static Format $default$getWrappedMetadataFormat(Entry _this) {
                return null;
            }

            public static void $default$populateMediaMetadata(Entry _this, MediaMetadata.Builder builder) {
            }
        }

        byte[] getWrappedMetadataBytes();

        Format getWrappedMetadataFormat();

        void populateMediaMetadata(MediaMetadata.Builder builder);
    }

    public int describeContents() {
        return 0;
    }

    public Metadata(Entry... entryArr) {
        this((long) C.TIME_UNSET, entryArr);
    }

    public Metadata(long j, Entry... entryArr) {
        this.presentationTimeUs = j;
        this.entries = entryArr;
    }

    public Metadata(List<? extends Entry> list) {
        this((Entry[]) list.toArray(new Entry[0]));
    }

    public Metadata(long j, List<? extends Entry> list) {
        this(j, (Entry[]) list.toArray(new Entry[0]));
    }

    Metadata(Parcel parcel) {
        this.entries = new Entry[parcel.readInt()];
        int i = 0;
        while (true) {
            Entry[] entryArr = this.entries;
            if (i < entryArr.length) {
                entryArr[i] = (Entry) parcel.readParcelable(Entry.class.getClassLoader());
                i++;
            } else {
                this.presentationTimeUs = parcel.readLong();
                return;
            }
        }
    }

    public int length() {
        return this.entries.length;
    }

    public Entry get(int i) {
        return this.entries[i];
    }

    public Metadata copyWithAppendedEntriesFrom(Metadata metadata) {
        return metadata == null ? this : copyWithAppendedEntries(metadata.entries);
    }

    public Metadata copyWithAppendedEntries(Entry... entryArr) {
        if (entryArr.length == 0) {
            return this;
        }
        return new Metadata(this.presentationTimeUs, (Entry[]) Util.nullSafeArrayConcatenation(this.entries, entryArr));
    }

    public Metadata copyWithPresentationTimeUs(long j) {
        if (this.presentationTimeUs == j) {
            return this;
        }
        return new Metadata(j, this.entries);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Metadata metadata = (Metadata) obj;
        if (!Arrays.equals(this.entries, metadata.entries) || this.presentationTimeUs != metadata.presentationTimeUs) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Arrays.hashCode(this.entries) * 31) + Longs.hashCode(this.presentationTimeUs);
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder("entries=");
        sb.append(Arrays.toString(this.entries));
        if (this.presentationTimeUs == C.TIME_UNSET) {
            str = "";
        } else {
            str = ", presentationTimeUs=" + this.presentationTimeUs;
        }
        sb.append(str);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.entries.length);
        for (Entry writeParcelable : this.entries) {
            parcel.writeParcelable(writeParcelable, 0);
        }
        parcel.writeLong(this.presentationTimeUs);
    }
}
