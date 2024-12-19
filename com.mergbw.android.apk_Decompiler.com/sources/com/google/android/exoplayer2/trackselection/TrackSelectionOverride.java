package com.google.android.exoplayer2.trackselection;

import android.os.Bundle;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.util.Collections;
import java.util.List;

public final class TrackSelectionOverride implements Bundleable {
    public static final Bundleable.Creator<TrackSelectionOverride> CREATOR = new TrackSelectionOverride$$ExternalSyntheticLambda0();
    private static final String FIELD_TRACKS = Util.intToStringMaxRadix(1);
    private static final String FIELD_TRACK_GROUP = Util.intToStringMaxRadix(0);
    public final TrackGroup mediaTrackGroup;
    public final ImmutableList<Integer> trackIndices;

    public TrackSelectionOverride(TrackGroup trackGroup, int i) {
        this(trackGroup, (List<Integer>) ImmutableList.of(Integer.valueOf(i)));
    }

    public TrackSelectionOverride(TrackGroup trackGroup, List<Integer> list) {
        if (list.isEmpty() || (((Integer) Collections.min(list)).intValue() >= 0 && ((Integer) Collections.max(list)).intValue() < trackGroup.length)) {
            this.mediaTrackGroup = trackGroup;
            this.trackIndices = ImmutableList.copyOf(list);
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public int getType() {
        return this.mediaTrackGroup.type;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TrackSelectionOverride trackSelectionOverride = (TrackSelectionOverride) obj;
        if (!this.mediaTrackGroup.equals(trackSelectionOverride.mediaTrackGroup) || !this.trackIndices.equals(trackSelectionOverride.trackIndices)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mediaTrackGroup.hashCode() + (this.trackIndices.hashCode() * 31);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putBundle(FIELD_TRACK_GROUP, this.mediaTrackGroup.toBundle());
        bundle.putIntArray(FIELD_TRACKS, Ints.toArray(this.trackIndices));
        return bundle;
    }

    static /* synthetic */ TrackSelectionOverride lambda$static$0(Bundle bundle) {
        return new TrackSelectionOverride(TrackGroup.CREATOR.fromBundle((Bundle) Assertions.checkNotNull(bundle.getBundle(FIELD_TRACK_GROUP))), Ints.asList((int[]) Assertions.checkNotNull(bundle.getIntArray(FIELD_TRACKS))));
    }
}
