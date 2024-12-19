package com.google.android.exoplayer2.source;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import java.util.IdentityHashMap;

public final class ConcatenatingMediaSource2 extends CompositeMediaSource<Integer> {
    private static final int MSG_UPDATE_TIMELINE = 0;
    private final MediaItem mediaItem;
    private final IdentityHashMap<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod;
    private final ImmutableList<MediaSourceHolder> mediaSourceHolders;
    private Handler playbackThreadHandler;
    private boolean timelineUpdateScheduled;

    private static long getChildWindowSequenceNumber(long j, int i, int i2) {
        return (j * ((long) i)) + ((long) i2);
    }

    /* access modifiers changed from: protected */
    public void enableInternal() {
    }

    /* access modifiers changed from: protected */
    public int getWindowIndexForChildWindowIndex(Integer num, int i) {
        return 0;
    }

    public static final class Builder {
        private int index;
        private MediaItem mediaItem;
        private MediaSource.Factory mediaSourceFactory;
        private final ImmutableList.Builder<MediaSourceHolder> mediaSourceHoldersBuilder = ImmutableList.builder();

        public Builder useDefaultMediaSourceFactory(Context context) {
            return setMediaSourceFactory(new DefaultMediaSourceFactory(context));
        }

        public Builder setMediaSourceFactory(MediaSource.Factory factory) {
            this.mediaSourceFactory = (MediaSource.Factory) Assertions.checkNotNull(factory);
            return this;
        }

        public Builder setMediaItem(MediaItem mediaItem2) {
            this.mediaItem = mediaItem2;
            return this;
        }

        public Builder add(MediaItem mediaItem2) {
            return add(mediaItem2, (long) C.TIME_UNSET);
        }

        public Builder add(MediaItem mediaItem2, long j) {
            Assertions.checkNotNull(mediaItem2);
            Assertions.checkStateNotNull(this.mediaSourceFactory, "Must use useDefaultMediaSourceFactory or setMediaSourceFactory first.");
            return add(this.mediaSourceFactory.createMediaSource(mediaItem2), j);
        }

        public Builder add(MediaSource mediaSource) {
            return add(mediaSource, (long) C.TIME_UNSET);
        }

        public Builder add(MediaSource mediaSource, long j) {
            Assertions.checkNotNull(mediaSource);
            Assertions.checkState(!(mediaSource instanceof ProgressiveMediaSource) || j != C.TIME_UNSET, "Progressive media source must define an initial placeholder duration.");
            ImmutableList.Builder<MediaSourceHolder> builder = this.mediaSourceHoldersBuilder;
            int i = this.index;
            this.index = i + 1;
            builder.add((Object) new MediaSourceHolder(mediaSource, i, Util.msToUs(j)));
            return this;
        }

        public ConcatenatingMediaSource2 build() {
            Assertions.checkArgument(this.index > 0, "Must add at least one source to the concatenation.");
            if (this.mediaItem == null) {
                this.mediaItem = MediaItem.fromUri(Uri.EMPTY);
            }
            return new ConcatenatingMediaSource2(this.mediaItem, this.mediaSourceHoldersBuilder.build());
        }
    }

    private ConcatenatingMediaSource2(MediaItem mediaItem2, ImmutableList<MediaSourceHolder> immutableList) {
        this.mediaItem = mediaItem2;
        this.mediaSourceHolders = immutableList;
        this.mediaSourceByMediaPeriod = new IdentityHashMap<>();
    }

    public Timeline getInitialTimeline() {
        return maybeCreateConcatenatedTimeline();
    }

    public MediaItem getMediaItem() {
        return this.mediaItem;
    }

    /* access modifiers changed from: protected */
    public void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        this.playbackThreadHandler = new Handler(new ConcatenatingMediaSource2$$ExternalSyntheticLambda0(this));
        for (int i = 0; i < this.mediaSourceHolders.size(); i++) {
            prepareChildSource(Integer.valueOf(i), ((MediaSourceHolder) this.mediaSourceHolders.get(i)).mediaSource);
        }
        scheduleTimelineUpdate();
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) this.mediaSourceHolders.get(getChildIndex(mediaPeriodId.periodUid));
        MediaSource.MediaPeriodId copyWithWindowSequenceNumber = mediaPeriodId.copyWithPeriodUid(getChildPeriodUid(mediaPeriodId.periodUid)).copyWithWindowSequenceNumber(getChildWindowSequenceNumber(mediaPeriodId.windowSequenceNumber, this.mediaSourceHolders.size(), mediaSourceHolder.index));
        enableChildSource(Integer.valueOf(mediaSourceHolder.index));
        mediaSourceHolder.activeMediaPeriods++;
        MaskingMediaPeriod createPeriod = mediaSourceHolder.mediaSource.createPeriod(copyWithWindowSequenceNumber, allocator, j);
        this.mediaSourceByMediaPeriod.put(createPeriod, mediaSourceHolder);
        disableUnusedMediaSources();
        return createPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) Assertions.checkNotNull(this.mediaSourceByMediaPeriod.remove(mediaPeriod));
        mediaSourceHolder.mediaSource.releasePeriod(mediaPeriod);
        mediaSourceHolder.activeMediaPeriods--;
        if (!this.mediaSourceByMediaPeriod.isEmpty()) {
            disableUnusedMediaSources();
        }
    }

    /* access modifiers changed from: protected */
    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        Handler handler = this.playbackThreadHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
            this.playbackThreadHandler = null;
        }
        this.timelineUpdateScheduled = false;
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(Integer num, MediaSource mediaSource, Timeline timeline) {
        scheduleTimelineUpdate();
    }

    /* access modifiers changed from: protected */
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Integer num, MediaSource.MediaPeriodId mediaPeriodId) {
        if (num.intValue() != getChildIndexFromChildWindowSequenceNumber(mediaPeriodId.windowSequenceNumber, this.mediaSourceHolders.size())) {
            return null;
        }
        return mediaPeriodId.copyWithPeriodUid(getPeriodUid(num.intValue(), mediaPeriodId.periodUid)).copyWithWindowSequenceNumber(getWindowSequenceNumberFromChildWindowSequenceNumber(mediaPeriodId.windowSequenceNumber, this.mediaSourceHolders.size()));
    }

    /* access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        if (message.what != 0) {
            return true;
        }
        updateTimeline();
        return true;
    }

    private void scheduleTimelineUpdate() {
        if (!this.timelineUpdateScheduled) {
            ((Handler) Assertions.checkNotNull(this.playbackThreadHandler)).obtainMessage(0).sendToTarget();
            this.timelineUpdateScheduled = true;
        }
    }

    private void updateTimeline() {
        this.timelineUpdateScheduled = false;
        ConcatenatedTimeline maybeCreateConcatenatedTimeline = maybeCreateConcatenatedTimeline();
        if (maybeCreateConcatenatedTimeline != null) {
            refreshSourceInfo(maybeCreateConcatenatedTimeline);
        }
    }

    private void disableUnusedMediaSources() {
        for (int i = 0; i < this.mediaSourceHolders.size(); i++) {
            MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) this.mediaSourceHolders.get(i);
            if (mediaSourceHolder.activeMediaPeriods == 0) {
                disableChildSource(Integer.valueOf(mediaSourceHolder.index));
            }
        }
    }

    private ConcatenatedTimeline maybeCreateConcatenatedTimeline() {
        Timeline.Period period;
        ImmutableList.Builder builder;
        long j;
        int i;
        Timeline.Window window = new Timeline.Window();
        Timeline.Period period2 = new Timeline.Period();
        ImmutableList.Builder builder2 = ImmutableList.builder();
        ImmutableList.Builder builder3 = ImmutableList.builder();
        ImmutableList.Builder builder4 = ImmutableList.builder();
        boolean z = true;
        boolean z2 = true;
        boolean z3 = true;
        int i2 = 0;
        Object obj = null;
        int i3 = 0;
        long j2 = 0;
        boolean z4 = false;
        long j3 = 0;
        long j4 = 0;
        boolean z5 = false;
        while (i2 < this.mediaSourceHolders.size()) {
            MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) this.mediaSourceHolders.get(i2);
            Timeline timeline = mediaSourceHolder.mediaSource.getTimeline();
            Assertions.checkArgument(timeline.isEmpty() ^ z, "Can't concatenate empty child Timeline.");
            builder2.add((Object) timeline);
            builder3.add((Object) Integer.valueOf(i3));
            i3 += timeline.getPeriodCount();
            int i4 = 0;
            while (i4 < timeline.getWindowCount()) {
                timeline.getWindow(i4, window);
                if (!z5) {
                    obj = window.manifest;
                    z5 = true;
                }
                if (!z2 || !Util.areEqual(obj, window.manifest)) {
                    i = i2;
                    z2 = false;
                } else {
                    i = i2;
                    z2 = true;
                }
                long j5 = window.durationUs;
                if (j5 == C.TIME_UNSET) {
                    if (mediaSourceHolder.initialPlaceholderDurationUs == C.TIME_UNSET) {
                        return null;
                    }
                    j5 = mediaSourceHolder.initialPlaceholderDurationUs;
                }
                j3 += j5;
                if (mediaSourceHolder.index == 0 && i4 == 0) {
                    j4 = window.defaultPositionUs;
                    j2 = -window.positionInFirstPeriodUs;
                } else {
                    Assertions.checkArgument(window.positionInFirstPeriodUs == 0, "Can't concatenate windows. A window has a non-zero offset in a period.");
                }
                z3 &= window.isSeekable || window.isPlaceholder;
                z4 |= window.isDynamic;
                i4++;
                i2 = i;
            }
            int i5 = i2;
            int periodCount = timeline.getPeriodCount();
            int i6 = 0;
            while (i6 < periodCount) {
                builder4.add((Object) Long.valueOf(j2));
                timeline.getPeriod(i6, period2);
                long j6 = period2.durationUs;
                if (j6 == C.TIME_UNSET) {
                    period = period2;
                    Assertions.checkArgument(periodCount == 1, "Can't concatenate multiple periods with unknown duration in one window.");
                    if (window.durationUs != C.TIME_UNSET) {
                        j = window.durationUs;
                    } else {
                        j = mediaSourceHolder.initialPlaceholderDurationUs;
                    }
                    builder = builder2;
                    j6 = j + window.positionInFirstPeriodUs;
                } else {
                    period = period2;
                    builder = builder2;
                }
                j2 += j6;
                i6++;
                builder2 = builder;
                period2 = period;
            }
            Timeline.Period period3 = period2;
            ImmutableList.Builder builder5 = builder2;
            i2 = i5 + 1;
            z = true;
        }
        return new ConcatenatedTimeline(this.mediaItem, builder2.build(), builder3.build(), builder4.build(), z3, z4, j3, j4, z2 ? obj : null);
    }

    /* access modifiers changed from: private */
    public static Object getPeriodUid(int i, Object obj) {
        return Pair.create(Integer.valueOf(i), obj);
    }

    /* access modifiers changed from: private */
    public static int getChildIndex(Object obj) {
        return ((Integer) ((Pair) obj).first).intValue();
    }

    /* access modifiers changed from: private */
    public static Object getChildPeriodUid(Object obj) {
        return ((Pair) obj).second;
    }

    private static int getChildIndexFromChildWindowSequenceNumber(long j, int i) {
        return (int) (j % ((long) i));
    }

    private static long getWindowSequenceNumberFromChildWindowSequenceNumber(long j, int i) {
        return j / ((long) i);
    }

    static final class MediaSourceHolder {
        public int activeMediaPeriods;
        public final int index;
        public final long initialPlaceholderDurationUs;
        public final MaskingMediaSource mediaSource;

        public MediaSourceHolder(MediaSource mediaSource2, int i, long j) {
            this.mediaSource = new MaskingMediaSource(mediaSource2, false);
            this.index = i;
            this.initialPlaceholderDurationUs = j;
        }
    }

    private static final class ConcatenatedTimeline extends Timeline {
        private final long defaultPositionUs;
        private final long durationUs;
        private final ImmutableList<Integer> firstPeriodIndices;
        private final boolean isDynamic;
        private final boolean isSeekable;
        private final Object manifest;
        private final MediaItem mediaItem;
        private final ImmutableList<Long> periodOffsetsInWindowUs;
        private final ImmutableList<Timeline> timelines;

        public int getWindowCount() {
            return 1;
        }

        public ConcatenatedTimeline(MediaItem mediaItem2, ImmutableList<Timeline> immutableList, ImmutableList<Integer> immutableList2, ImmutableList<Long> immutableList3, boolean z, boolean z2, long j, long j2, Object obj) {
            this.mediaItem = mediaItem2;
            this.timelines = immutableList;
            this.firstPeriodIndices = immutableList2;
            this.periodOffsetsInWindowUs = immutableList3;
            this.isSeekable = z;
            this.isDynamic = z2;
            this.durationUs = j;
            this.defaultPositionUs = j2;
            this.manifest = obj;
        }

        public int getPeriodCount() {
            return this.periodOffsetsInWindowUs.size();
        }

        public final Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            return window.set(Timeline.Window.SINGLE_WINDOW_UID, this.mediaItem, this.manifest, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, this.isSeekable, this.isDynamic, (MediaItem.LiveConfiguration) null, this.defaultPositionUs, this.durationUs, 0, getPeriodCount() - 1, -((Long) this.periodOffsetsInWindowUs.get(0)).longValue());
        }

        public final Timeline.Period getPeriodByUid(Object obj, Timeline.Period period) {
            int access$100 = ConcatenatingMediaSource2.getChildIndex(obj);
            Object access$200 = ConcatenatingMediaSource2.getChildPeriodUid(obj);
            Timeline timeline = (Timeline) this.timelines.get(access$100);
            timeline.getPeriodByUid(access$200, period);
            period.windowIndex = 0;
            period.positionInWindowUs = ((Long) this.periodOffsetsInWindowUs.get(((Integer) this.firstPeriodIndices.get(access$100)).intValue() + timeline.getIndexOfPeriod(access$200))).longValue();
            period.uid = obj;
            return period;
        }

        public final Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i);
            ((Timeline) this.timelines.get(childIndexByPeriodIndex)).getPeriod(i - ((Integer) this.firstPeriodIndices.get(childIndexByPeriodIndex)).intValue(), period, z);
            period.windowIndex = 0;
            period.positionInWindowUs = ((Long) this.periodOffsetsInWindowUs.get(i)).longValue();
            if (z) {
                period.uid = ConcatenatingMediaSource2.getPeriodUid(childIndexByPeriodIndex, Assertions.checkNotNull(period.uid));
            }
            return period;
        }

        public final int getIndexOfPeriod(Object obj) {
            if (!(obj instanceof Pair) || !(((Pair) obj).first instanceof Integer)) {
                return -1;
            }
            int access$100 = ConcatenatingMediaSource2.getChildIndex(obj);
            int indexOfPeriod = ((Timeline) this.timelines.get(access$100)).getIndexOfPeriod(ConcatenatingMediaSource2.getChildPeriodUid(obj));
            if (indexOfPeriod == -1) {
                return -1;
            }
            return ((Integer) this.firstPeriodIndices.get(access$100)).intValue() + indexOfPeriod;
        }

        public final Object getUidOfPeriod(int i) {
            int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i);
            return ConcatenatingMediaSource2.getPeriodUid(childIndexByPeriodIndex, ((Timeline) this.timelines.get(childIndexByPeriodIndex)).getUidOfPeriod(i - ((Integer) this.firstPeriodIndices.get(childIndexByPeriodIndex)).intValue()));
        }

        private int getChildIndexByPeriodIndex(int i) {
            return Util.binarySearchFloor(this.firstPeriodIndices, Integer.valueOf(i + 1), false, false);
        }
    }
}
