package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ads.AdPlaybackState;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Util;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class MaskingMediaSource extends WrappingMediaSource {
    private boolean hasRealTimeline;
    private boolean hasStartedPreparing;
    private boolean isPrepared;
    private final Timeline.Period period;
    private MaskingTimeline timeline;
    private MaskingMediaPeriod unpreparedMaskingMediaPeriod;
    private final boolean useLazyPreparation;
    private final Timeline.Window window;

    public void maybeThrowSourceInfoRefreshError() {
    }

    public MaskingMediaSource(MediaSource mediaSource, boolean z) {
        super(mediaSource);
        this.useLazyPreparation = z && mediaSource.isSingleWindow();
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        Timeline initialTimeline = mediaSource.getInitialTimeline();
        if (initialTimeline != null) {
            this.timeline = MaskingTimeline.createWithRealTimeline(initialTimeline, (Object) null, (Object) null);
            this.hasRealTimeline = true;
            return;
        }
        this.timeline = MaskingTimeline.createWithPlaceholderTimeline(mediaSource.getMediaItem());
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    public void prepareSourceInternal() {
        if (!this.useLazyPreparation) {
            this.hasStartedPreparing = true;
            prepareChildSource();
        }
    }

    public MaskingMediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MaskingMediaPeriod maskingMediaPeriod = new MaskingMediaPeriod(mediaPeriodId, allocator, j);
        maskingMediaPeriod.setMediaSource(this.mediaSource);
        if (this.isPrepared) {
            maskingMediaPeriod.createPeriod(mediaPeriodId.copyWithPeriodUid(getInternalPeriodUid(mediaPeriodId.periodUid)));
        } else {
            this.unpreparedMaskingMediaPeriod = maskingMediaPeriod;
            if (!this.hasStartedPreparing) {
                this.hasStartedPreparing = true;
                prepareChildSource();
            }
        }
        return maskingMediaPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((MaskingMediaPeriod) mediaPeriod).releasePeriod();
        if (mediaPeriod == this.unpreparedMaskingMediaPeriod) {
            this.unpreparedMaskingMediaPeriod = null;
        }
    }

    public void releaseSourceInternal() {
        this.isPrepared = false;
        this.hasStartedPreparing = false;
        super.releaseSourceInternal();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onChildSourceInfoRefreshed(com.google.android.exoplayer2.Timeline r15) {
        /*
            r14 = this;
            boolean r0 = r14.isPrepared
            if (r0 == 0) goto L_0x0019
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r0 = r14.timeline
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r15 = r0.cloneWithUpdatedTimeline(r15)
            r14.timeline = r15
            com.google.android.exoplayer2.source.MaskingMediaPeriod r15 = r14.unpreparedMaskingMediaPeriod
            if (r15 == 0) goto L_0x00b0
            long r0 = r15.getPreparePositionOverrideUs()
            r14.setPreparePositionOverrideToUnpreparedMaskingPeriod(r0)
            goto L_0x00b0
        L_0x0019:
            boolean r0 = r15.isEmpty()
            if (r0 == 0) goto L_0x0036
            boolean r0 = r14.hasRealTimeline
            if (r0 == 0) goto L_0x002a
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r0 = r14.timeline
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r15 = r0.cloneWithUpdatedTimeline(r15)
            goto L_0x0032
        L_0x002a:
            java.lang.Object r0 = com.google.android.exoplayer2.Timeline.Window.SINGLE_WINDOW_UID
            java.lang.Object r1 = com.google.android.exoplayer2.source.MaskingMediaSource.MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r15 = com.google.android.exoplayer2.source.MaskingMediaSource.MaskingTimeline.createWithRealTimeline(r15, r0, r1)
        L_0x0032:
            r14.timeline = r15
            goto L_0x00b0
        L_0x0036:
            com.google.android.exoplayer2.Timeline$Window r0 = r14.window
            r1 = 0
            r15.getWindow(r1, r0)
            com.google.android.exoplayer2.Timeline$Window r0 = r14.window
            long r2 = r0.getDefaultPositionUs()
            com.google.android.exoplayer2.Timeline$Window r0 = r14.window
            java.lang.Object r0 = r0.uid
            com.google.android.exoplayer2.source.MaskingMediaPeriod r4 = r14.unpreparedMaskingMediaPeriod
            if (r4 == 0) goto L_0x0074
            long r4 = r4.getPreparePositionUs()
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r6 = r14.timeline
            com.google.android.exoplayer2.source.MaskingMediaPeriod r7 = r14.unpreparedMaskingMediaPeriod
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r7 = r7.id
            java.lang.Object r7 = r7.periodUid
            com.google.android.exoplayer2.Timeline$Period r8 = r14.period
            r6.getPeriodByUid(r7, r8)
            com.google.android.exoplayer2.Timeline$Period r6 = r14.period
            long r6 = r6.getPositionInWindowUs()
            long r6 = r6 + r4
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r4 = r14.timeline
            com.google.android.exoplayer2.Timeline$Window r5 = r14.window
            com.google.android.exoplayer2.Timeline$Window r1 = r4.getWindow(r1, r5)
            long r4 = r1.getDefaultPositionUs()
            int r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0074
            r12 = r6
            goto L_0x0075
        L_0x0074:
            r12 = r2
        L_0x0075:
            com.google.android.exoplayer2.Timeline$Window r9 = r14.window
            com.google.android.exoplayer2.Timeline$Period r10 = r14.period
            r11 = 0
            r8 = r15
            android.util.Pair r1 = r8.getPeriodPositionUs(r9, r10, r11, r12)
            java.lang.Object r2 = r1.first
            java.lang.Object r1 = r1.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r3 = r1.longValue()
            boolean r1 = r14.hasRealTimeline
            if (r1 == 0) goto L_0x0094
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r0 = r14.timeline
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r15 = r0.cloneWithUpdatedTimeline(r15)
            goto L_0x0098
        L_0x0094:
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r15 = com.google.android.exoplayer2.source.MaskingMediaSource.MaskingTimeline.createWithRealTimeline(r15, r0, r2)
        L_0x0098:
            r14.timeline = r15
            com.google.android.exoplayer2.source.MaskingMediaPeriod r15 = r14.unpreparedMaskingMediaPeriod
            if (r15 == 0) goto L_0x00b0
            r14.setPreparePositionOverrideToUnpreparedMaskingPeriod(r3)
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r0 = r15.id
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r15 = r15.id
            java.lang.Object r15 = r15.periodUid
            java.lang.Object r15 = r14.getInternalPeriodUid(r15)
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r15 = r0.copyWithPeriodUid((java.lang.Object) r15)
            goto L_0x00b1
        L_0x00b0:
            r15 = 0
        L_0x00b1:
            r0 = 1
            r14.hasRealTimeline = r0
            r14.isPrepared = r0
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r0 = r14.timeline
            r14.refreshSourceInfo(r0)
            if (r15 == 0) goto L_0x00c8
            com.google.android.exoplayer2.source.MaskingMediaPeriod r0 = r14.unpreparedMaskingMediaPeriod
            java.lang.Object r0 = com.google.android.exoplayer2.util.Assertions.checkNotNull(r0)
            com.google.android.exoplayer2.source.MaskingMediaPeriod r0 = (com.google.android.exoplayer2.source.MaskingMediaPeriod) r0
            r0.createPeriod(r15)
        L_0x00c8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.MaskingMediaSource.onChildSourceInfoRefreshed(com.google.android.exoplayer2.Timeline):void");
    }

    /* access modifiers changed from: protected */
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        return mediaPeriodId.copyWithPeriodUid(getExternalPeriodUid(mediaPeriodId.periodUid));
    }

    private Object getInternalPeriodUid(Object obj) {
        return (this.timeline.replacedInternalPeriodUid == null || !obj.equals(MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID)) ? obj : this.timeline.replacedInternalPeriodUid;
    }

    private Object getExternalPeriodUid(Object obj) {
        return (this.timeline.replacedInternalPeriodUid == null || !this.timeline.replacedInternalPeriodUid.equals(obj)) ? obj : MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
    }

    @RequiresNonNull({"unpreparedMaskingMediaPeriod"})
    private void setPreparePositionOverrideToUnpreparedMaskingPeriod(long j) {
        MaskingMediaPeriod maskingMediaPeriod = this.unpreparedMaskingMediaPeriod;
        int indexOfPeriod = this.timeline.getIndexOfPeriod(maskingMediaPeriod.id.periodUid);
        if (indexOfPeriod != -1) {
            long j2 = this.timeline.getPeriod(indexOfPeriod, this.period).durationUs;
            if (j2 != C.TIME_UNSET && j >= j2) {
                j = Math.max(0, j2 - 1);
            }
            maskingMediaPeriod.overridePreparePositionUs(j);
        }
    }

    private static final class MaskingTimeline extends ForwardingTimeline {
        public static final Object MASKING_EXTERNAL_PERIOD_UID = new Object();
        /* access modifiers changed from: private */
        public final Object replacedInternalPeriodUid;
        private final Object replacedInternalWindowUid;

        public static MaskingTimeline createWithPlaceholderTimeline(MediaItem mediaItem) {
            return new MaskingTimeline(new PlaceholderTimeline(mediaItem), Timeline.Window.SINGLE_WINDOW_UID, MASKING_EXTERNAL_PERIOD_UID);
        }

        public static MaskingTimeline createWithRealTimeline(Timeline timeline, Object obj, Object obj2) {
            return new MaskingTimeline(timeline, obj, obj2);
        }

        private MaskingTimeline(Timeline timeline, Object obj, Object obj2) {
            super(timeline);
            this.replacedInternalWindowUid = obj;
            this.replacedInternalPeriodUid = obj2;
        }

        public MaskingTimeline cloneWithUpdatedTimeline(Timeline timeline) {
            return new MaskingTimeline(timeline, this.replacedInternalWindowUid, this.replacedInternalPeriodUid);
        }

        public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            this.timeline.getWindow(i, window, j);
            if (Util.areEqual(window.uid, this.replacedInternalWindowUid)) {
                window.uid = Timeline.Window.SINGLE_WINDOW_UID;
            }
            return window;
        }

        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            this.timeline.getPeriod(i, period, z);
            if (Util.areEqual(period.uid, this.replacedInternalPeriodUid) && z) {
                period.uid = MASKING_EXTERNAL_PERIOD_UID;
            }
            return period;
        }

        public int getIndexOfPeriod(Object obj) {
            Object obj2;
            Timeline timeline = this.timeline;
            if (MASKING_EXTERNAL_PERIOD_UID.equals(obj) && (obj2 = this.replacedInternalPeriodUid) != null) {
                obj = obj2;
            }
            return timeline.getIndexOfPeriod(obj);
        }

        public Object getUidOfPeriod(int i) {
            Object uidOfPeriod = this.timeline.getUidOfPeriod(i);
            return Util.areEqual(uidOfPeriod, this.replacedInternalPeriodUid) ? MASKING_EXTERNAL_PERIOD_UID : uidOfPeriod;
        }
    }

    public static final class PlaceholderTimeline extends Timeline {
        private final MediaItem mediaItem;

        public int getPeriodCount() {
            return 1;
        }

        public int getWindowCount() {
            return 1;
        }

        public PlaceholderTimeline(MediaItem mediaItem2) {
            this.mediaItem = mediaItem2;
        }

        public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            Timeline.Window window2 = window;
            window.set(Timeline.Window.SINGLE_WINDOW_UID, this.mediaItem, (Object) null, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, false, true, (MediaItem.LiveConfiguration) null, 0, C.TIME_UNSET, 0, 0, 0);
            Timeline.Window window3 = window;
            window3.isPlaceholder = true;
            return window3;
        }

        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            Object obj = null;
            Integer num = z ? 0 : null;
            if (z) {
                obj = MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
            }
            period.set(num, obj, 0, C.TIME_UNSET, 0, AdPlaybackState.NONE, true);
            return period;
        }

        public int getIndexOfPeriod(Object obj) {
            return obj == MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID ? 0 : -1;
        }

        public Object getUidOfPeriod(int i) {
            return MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
        }
    }
}
