package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;

public abstract class WrappingMediaSource extends CompositeMediaSource<Void> {
    private static final Void CHILD_SOURCE_ID = null;
    protected final MediaSource mediaSource;

    /* access modifiers changed from: protected */
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        return mediaPeriodId;
    }

    /* access modifiers changed from: protected */
    public long getMediaTimeForChildMediaTime(long j) {
        return j;
    }

    /* access modifiers changed from: protected */
    public int getWindowIndexForChildWindowIndex(int i) {
        return i;
    }

    protected WrappingMediaSource(MediaSource mediaSource2) {
        this.mediaSource = mediaSource2;
    }

    /* access modifiers changed from: protected */
    public final void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        prepareSourceInternal();
    }

    /* access modifiers changed from: protected */
    public void prepareSourceInternal() {
        prepareChildSource();
    }

    public Timeline getInitialTimeline() {
        return this.mediaSource.getInitialTimeline();
    }

    public boolean isSingleWindow() {
        return this.mediaSource.isSingleWindow();
    }

    public MediaItem getMediaItem() {
        return this.mediaSource.getMediaItem();
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        return this.mediaSource.createPeriod(mediaPeriodId, allocator, j);
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        this.mediaSource.releasePeriod(mediaPeriod);
    }

    /* access modifiers changed from: protected */
    public final void onChildSourceInfoRefreshed(Void voidR, MediaSource mediaSource2, Timeline timeline) {
        onChildSourceInfoRefreshed(timeline);
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(Timeline timeline) {
        refreshSourceInfo(timeline);
    }

    /* access modifiers changed from: protected */
    public final int getWindowIndexForChildWindowIndex(Void voidR, int i) {
        return getWindowIndexForChildWindowIndex(i);
    }

    /* access modifiers changed from: protected */
    public final MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Void voidR, MediaSource.MediaPeriodId mediaPeriodId) {
        return getMediaPeriodIdForChildMediaPeriodId(mediaPeriodId);
    }

    /* access modifiers changed from: protected */
    public final long getMediaTimeForChildMediaTime(Void voidR, long j) {
        return getMediaTimeForChildMediaTime(j);
    }

    /* access modifiers changed from: protected */
    public final void prepareChildSource() {
        prepareChildSource(CHILD_SOURCE_ID, this.mediaSource);
    }

    /* access modifiers changed from: protected */
    public final void enableChildSource() {
        enableChildSource(CHILD_SOURCE_ID);
    }

    /* access modifiers changed from: protected */
    public final void disableChildSource() {
        disableChildSource(CHILD_SOURCE_ID);
    }

    /* access modifiers changed from: protected */
    public final void releaseChildSource() {
        releaseChildSource(CHILD_SOURCE_ID);
    }
}
