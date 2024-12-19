package com.google.android.exoplayer2;

import android.util.Pair;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MaskingMediaPeriod;
import com.google.android.exoplayer2.source.MaskingMediaSource;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class MediaSourceList {
    private static final String TAG = "MediaSourceList";
    private final HashMap<MediaSourceHolder, MediaSourceAndListener> childSources;
    private final Set<MediaSourceHolder> enabledMediaSourceHolders;
    /* access modifiers changed from: private */
    public final HandlerWrapper eventHandler;
    /* access modifiers changed from: private */
    public final AnalyticsCollector eventListener;
    private boolean isPrepared;
    private final IdentityHashMap<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod = new IdentityHashMap<>();
    private final Map<Object, MediaSourceHolder> mediaSourceByUid = new HashMap();
    private final List<MediaSourceHolder> mediaSourceHolders = new ArrayList();
    private final MediaSourceListInfoRefreshListener mediaSourceListInfoListener;
    private TransferListener mediaTransferListener;
    private final PlayerId playerId;
    private ShuffleOrder shuffleOrder = new ShuffleOrder.DefaultShuffleOrder(0);

    public interface MediaSourceListInfoRefreshListener {
        void onPlaylistUpdateRequested();
    }

    public MediaSourceList(MediaSourceListInfoRefreshListener mediaSourceListInfoRefreshListener, AnalyticsCollector analyticsCollector, HandlerWrapper handlerWrapper, PlayerId playerId2) {
        this.playerId = playerId2;
        this.mediaSourceListInfoListener = mediaSourceListInfoRefreshListener;
        this.eventListener = analyticsCollector;
        this.eventHandler = handlerWrapper;
        this.childSources = new HashMap<>();
        this.enabledMediaSourceHolders = new HashSet();
    }

    public Timeline setMediaSources(List<MediaSourceHolder> list, ShuffleOrder shuffleOrder2) {
        removeMediaSourcesInternal(0, this.mediaSourceHolders.size());
        return addMediaSources(this.mediaSourceHolders.size(), list, shuffleOrder2);
    }

    public Timeline addMediaSources(int i, List<MediaSourceHolder> list, ShuffleOrder shuffleOrder2) {
        if (!list.isEmpty()) {
            this.shuffleOrder = shuffleOrder2;
            for (int i2 = i; i2 < list.size() + i; i2++) {
                MediaSourceHolder mediaSourceHolder = list.get(i2 - i);
                if (i2 > 0) {
                    MediaSourceHolder mediaSourceHolder2 = this.mediaSourceHolders.get(i2 - 1);
                    mediaSourceHolder.reset(mediaSourceHolder2.firstWindowIndexInChild + mediaSourceHolder2.mediaSource.getTimeline().getWindowCount());
                } else {
                    mediaSourceHolder.reset(0);
                }
                correctOffsets(i2, mediaSourceHolder.mediaSource.getTimeline().getWindowCount());
                this.mediaSourceHolders.add(i2, mediaSourceHolder);
                this.mediaSourceByUid.put(mediaSourceHolder.uid, mediaSourceHolder);
                if (this.isPrepared) {
                    prepareChildSource(mediaSourceHolder);
                    if (this.mediaSourceByMediaPeriod.isEmpty()) {
                        this.enabledMediaSourceHolders.add(mediaSourceHolder);
                    } else {
                        disableChildSource(mediaSourceHolder);
                    }
                }
            }
        }
        return createTimeline();
    }

    public Timeline removeMediaSourceRange(int i, int i2, ShuffleOrder shuffleOrder2) {
        Assertions.checkArgument(i >= 0 && i <= i2 && i2 <= getSize());
        this.shuffleOrder = shuffleOrder2;
        removeMediaSourcesInternal(i, i2);
        return createTimeline();
    }

    public Timeline moveMediaSource(int i, int i2, ShuffleOrder shuffleOrder2) {
        return moveMediaSourceRange(i, i + 1, i2, shuffleOrder2);
    }

    public Timeline moveMediaSourceRange(int i, int i2, int i3, ShuffleOrder shuffleOrder2) {
        Assertions.checkArgument(i >= 0 && i <= i2 && i2 <= getSize() && i3 >= 0);
        this.shuffleOrder = shuffleOrder2;
        if (i == i2 || i == i3) {
            return createTimeline();
        }
        int min = Math.min(i, i3);
        int max = Math.max(((i2 - i) + i3) - 1, i2 - 1);
        int i4 = this.mediaSourceHolders.get(min).firstWindowIndexInChild;
        Util.moveItems(this.mediaSourceHolders, i, i2, i3);
        while (min <= max) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(min);
            mediaSourceHolder.firstWindowIndexInChild = i4;
            i4 += mediaSourceHolder.mediaSource.getTimeline().getWindowCount();
            min++;
        }
        return createTimeline();
    }

    public Timeline clear(ShuffleOrder shuffleOrder2) {
        if (shuffleOrder2 == null) {
            shuffleOrder2 = this.shuffleOrder.cloneAndClear();
        }
        this.shuffleOrder = shuffleOrder2;
        removeMediaSourcesInternal(0, getSize());
        return createTimeline();
    }

    public boolean isPrepared() {
        return this.isPrepared;
    }

    public int getSize() {
        return this.mediaSourceHolders.size();
    }

    public Timeline setShuffleOrder(ShuffleOrder shuffleOrder2) {
        int size = getSize();
        if (shuffleOrder2.getLength() != size) {
            shuffleOrder2 = shuffleOrder2.cloneAndClear().cloneAndInsert(0, size);
        }
        this.shuffleOrder = shuffleOrder2;
        return createTimeline();
    }

    public void prepare(TransferListener transferListener) {
        Assertions.checkState(!this.isPrepared);
        this.mediaTransferListener = transferListener;
        for (int i = 0; i < this.mediaSourceHolders.size(); i++) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i);
            prepareChildSource(mediaSourceHolder);
            this.enabledMediaSourceHolders.add(mediaSourceHolder);
        }
        this.isPrepared = true;
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        Object mediaSourceHolderUid = getMediaSourceHolderUid(mediaPeriodId.periodUid);
        MediaSource.MediaPeriodId copyWithPeriodUid = mediaPeriodId.copyWithPeriodUid(getChildPeriodUid(mediaPeriodId.periodUid));
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) Assertions.checkNotNull(this.mediaSourceByUid.get(mediaSourceHolderUid));
        enableMediaSource(mediaSourceHolder);
        mediaSourceHolder.activeMediaPeriodIds.add(copyWithPeriodUid);
        MaskingMediaPeriod createPeriod = mediaSourceHolder.mediaSource.createPeriod(copyWithPeriodUid, allocator, j);
        this.mediaSourceByMediaPeriod.put(createPeriod, mediaSourceHolder);
        disableUnusedMediaSources();
        return createPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) Assertions.checkNotNull(this.mediaSourceByMediaPeriod.remove(mediaPeriod));
        mediaSourceHolder.mediaSource.releasePeriod(mediaPeriod);
        mediaSourceHolder.activeMediaPeriodIds.remove(((MaskingMediaPeriod) mediaPeriod).id);
        if (!this.mediaSourceByMediaPeriod.isEmpty()) {
            disableUnusedMediaSources();
        }
        maybeReleaseChildSource(mediaSourceHolder);
    }

    public void release() {
        for (MediaSourceAndListener next : this.childSources.values()) {
            try {
                next.mediaSource.releaseSource(next.caller);
            } catch (RuntimeException e) {
                Log.e(TAG, "Failed to release child source.", e);
            }
            next.mediaSource.removeEventListener(next.eventListener);
            next.mediaSource.removeDrmEventListener(next.eventListener);
        }
        this.childSources.clear();
        this.enabledMediaSourceHolders.clear();
        this.isPrepared = false;
    }

    public Timeline createTimeline() {
        if (this.mediaSourceHolders.isEmpty()) {
            return Timeline.EMPTY;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.mediaSourceHolders.size(); i2++) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i2);
            mediaSourceHolder.firstWindowIndexInChild = i;
            i += mediaSourceHolder.mediaSource.getTimeline().getWindowCount();
        }
        return new PlaylistTimeline(this.mediaSourceHolders, this.shuffleOrder);
    }

    private void enableMediaSource(MediaSourceHolder mediaSourceHolder) {
        this.enabledMediaSourceHolders.add(mediaSourceHolder);
        MediaSourceAndListener mediaSourceAndListener = this.childSources.get(mediaSourceHolder);
        if (mediaSourceAndListener != null) {
            mediaSourceAndListener.mediaSource.enable(mediaSourceAndListener.caller);
        }
    }

    private void disableUnusedMediaSources() {
        Iterator<MediaSourceHolder> it = this.enabledMediaSourceHolders.iterator();
        while (it.hasNext()) {
            MediaSourceHolder next = it.next();
            if (next.activeMediaPeriodIds.isEmpty()) {
                disableChildSource(next);
                it.remove();
            }
        }
    }

    private void disableChildSource(MediaSourceHolder mediaSourceHolder) {
        MediaSourceAndListener mediaSourceAndListener = this.childSources.get(mediaSourceHolder);
        if (mediaSourceAndListener != null) {
            mediaSourceAndListener.mediaSource.disable(mediaSourceAndListener.caller);
        }
    }

    private void removeMediaSourcesInternal(int i, int i2) {
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            MediaSourceHolder remove = this.mediaSourceHolders.remove(i3);
            this.mediaSourceByUid.remove(remove.uid);
            correctOffsets(i3, -remove.mediaSource.getTimeline().getWindowCount());
            remove.isRemoved = true;
            if (this.isPrepared) {
                maybeReleaseChildSource(remove);
            }
        }
    }

    private void correctOffsets(int i, int i2) {
        while (i < this.mediaSourceHolders.size()) {
            this.mediaSourceHolders.get(i).firstWindowIndexInChild += i2;
            i++;
        }
    }

    /* access modifiers changed from: private */
    public static MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSourceHolder mediaSourceHolder, MediaSource.MediaPeriodId mediaPeriodId) {
        for (int i = 0; i < mediaSourceHolder.activeMediaPeriodIds.size(); i++) {
            if (mediaSourceHolder.activeMediaPeriodIds.get(i).windowSequenceNumber == mediaPeriodId.windowSequenceNumber) {
                return mediaPeriodId.copyWithPeriodUid(getPeriodUid(mediaSourceHolder, mediaPeriodId.periodUid));
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static int getWindowIndexForChildWindowIndex(MediaSourceHolder mediaSourceHolder, int i) {
        return i + mediaSourceHolder.firstWindowIndexInChild;
    }

    private void prepareChildSource(MediaSourceHolder mediaSourceHolder) {
        MaskingMediaSource maskingMediaSource = mediaSourceHolder.mediaSource;
        MediaSourceList$$ExternalSyntheticLambda0 mediaSourceList$$ExternalSyntheticLambda0 = new MediaSourceList$$ExternalSyntheticLambda0(this);
        ForwardingEventListener forwardingEventListener = new ForwardingEventListener(mediaSourceHolder);
        this.childSources.put(mediaSourceHolder, new MediaSourceAndListener(maskingMediaSource, mediaSourceList$$ExternalSyntheticLambda0, forwardingEventListener));
        maskingMediaSource.addEventListener(Util.createHandlerForCurrentOrMainLooper(), forwardingEventListener);
        maskingMediaSource.addDrmEventListener(Util.createHandlerForCurrentOrMainLooper(), forwardingEventListener);
        maskingMediaSource.prepareSource(mediaSourceList$$ExternalSyntheticLambda0, this.mediaTransferListener, this.playerId);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$prepareChildSource$0$com-google-android-exoplayer2-MediaSourceList  reason: not valid java name */
    public /* synthetic */ void m420lambda$prepareChildSource$0$comgoogleandroidexoplayer2MediaSourceList(MediaSource mediaSource, Timeline timeline) {
        this.mediaSourceListInfoListener.onPlaylistUpdateRequested();
    }

    private void maybeReleaseChildSource(MediaSourceHolder mediaSourceHolder) {
        if (mediaSourceHolder.isRemoved && mediaSourceHolder.activeMediaPeriodIds.isEmpty()) {
            MediaSourceAndListener mediaSourceAndListener = (MediaSourceAndListener) Assertions.checkNotNull(this.childSources.remove(mediaSourceHolder));
            mediaSourceAndListener.mediaSource.releaseSource(mediaSourceAndListener.caller);
            mediaSourceAndListener.mediaSource.removeEventListener(mediaSourceAndListener.eventListener);
            mediaSourceAndListener.mediaSource.removeDrmEventListener(mediaSourceAndListener.eventListener);
            this.enabledMediaSourceHolders.remove(mediaSourceHolder);
        }
    }

    private static Object getMediaSourceHolderUid(Object obj) {
        return PlaylistTimeline.getChildTimelineUidFromConcatenatedUid(obj);
    }

    private static Object getChildPeriodUid(Object obj) {
        return PlaylistTimeline.getChildPeriodUidFromConcatenatedUid(obj);
    }

    private static Object getPeriodUid(MediaSourceHolder mediaSourceHolder, Object obj) {
        return PlaylistTimeline.getConcatenatedUid(mediaSourceHolder.uid, obj);
    }

    static final class MediaSourceHolder implements MediaSourceInfoHolder {
        public final List<MediaSource.MediaPeriodId> activeMediaPeriodIds = new ArrayList();
        public int firstWindowIndexInChild;
        public boolean isRemoved;
        public final MaskingMediaSource mediaSource;
        public final Object uid = new Object();

        public MediaSourceHolder(MediaSource mediaSource2, boolean z) {
            this.mediaSource = new MaskingMediaSource(mediaSource2, z);
        }

        public void reset(int i) {
            this.firstWindowIndexInChild = i;
            this.isRemoved = false;
            this.activeMediaPeriodIds.clear();
        }

        public Object getUid() {
            return this.uid;
        }

        public Timeline getTimeline() {
            return this.mediaSource.getTimeline();
        }
    }

    private static final class MediaSourceAndListener {
        public final MediaSource.MediaSourceCaller caller;
        public final ForwardingEventListener eventListener;
        public final MediaSource mediaSource;

        public MediaSourceAndListener(MediaSource mediaSource2, MediaSource.MediaSourceCaller mediaSourceCaller, ForwardingEventListener forwardingEventListener) {
            this.mediaSource = mediaSource2;
            this.caller = mediaSourceCaller;
            this.eventListener = forwardingEventListener;
        }
    }

    private final class ForwardingEventListener implements MediaSourceEventListener, DrmSessionEventListener {
        private final MediaSourceHolder id;

        public /* synthetic */ void onDrmSessionAcquired(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            DrmSessionEventListener.CC.$default$onDrmSessionAcquired(this, i, mediaPeriodId);
        }

        public ForwardingEventListener(MediaSourceHolder mediaSourceHolder) {
            this.id = mediaSourceHolder;
        }

        public void onLoadStarted(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda8(this, eventParameters, loadEventInfo, mediaLoadData));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onLoadStarted$0$com-google-android-exoplayer2-MediaSourceList$ForwardingEventListener  reason: not valid java name */
        public /* synthetic */ void m431lambda$onLoadStarted$0$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(Pair pair, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            MediaSourceList.this.eventListener.onLoadStarted(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, loadEventInfo, mediaLoadData);
        }

        public void onLoadCompleted(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda0(this, eventParameters, loadEventInfo, mediaLoadData));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onLoadCompleted$1$com-google-android-exoplayer2-MediaSourceList$ForwardingEventListener  reason: not valid java name */
        public /* synthetic */ void m429lambda$onLoadCompleted$1$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(Pair pair, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            MediaSourceList.this.eventListener.onLoadCompleted(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, loadEventInfo, mediaLoadData);
        }

        public void onLoadCanceled(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda10(this, eventParameters, loadEventInfo, mediaLoadData));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onLoadCanceled$2$com-google-android-exoplayer2-MediaSourceList$ForwardingEventListener  reason: not valid java name */
        public /* synthetic */ void m428lambda$onLoadCanceled$2$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(Pair pair, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            MediaSourceList.this.eventListener.onLoadCanceled(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, loadEventInfo, mediaLoadData);
        }

        public void onLoadError(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda11(this, eventParameters, loadEventInfo, mediaLoadData, iOException, z));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onLoadError$3$com-google-android-exoplayer2-MediaSourceList$ForwardingEventListener  reason: not valid java name */
        public /* synthetic */ void m430lambda$onLoadError$3$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(Pair pair, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            MediaSourceList.this.eventListener.onLoadError(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, loadEventInfo, mediaLoadData, iOException, z);
        }

        public void onUpstreamDiscarded(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda1(this, eventParameters, mediaLoadData));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onUpstreamDiscarded$4$com-google-android-exoplayer2-MediaSourceList$ForwardingEventListener  reason: not valid java name */
        public /* synthetic */ void m432lambda$onUpstreamDiscarded$4$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(Pair pair, MediaLoadData mediaLoadData) {
            MediaSourceList.this.eventListener.onUpstreamDiscarded(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) Assertions.checkNotNull((MediaSource.MediaPeriodId) pair.second), mediaLoadData);
        }

        public void onDownstreamFormatChanged(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda4(this, eventParameters, mediaLoadData));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onDownstreamFormatChanged$5$com-google-android-exoplayer2-MediaSourceList$ForwardingEventListener  reason: not valid java name */
        public /* synthetic */ void m421lambda$onDownstreamFormatChanged$5$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(Pair pair, MediaLoadData mediaLoadData) {
            MediaSourceList.this.eventListener.onDownstreamFormatChanged(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, mediaLoadData);
        }

        public void onDrmSessionAcquired(int i, MediaSource.MediaPeriodId mediaPeriodId, int i2) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda9(this, eventParameters, i2));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onDrmSessionAcquired$6$com-google-android-exoplayer2-MediaSourceList$ForwardingEventListener  reason: not valid java name */
        public /* synthetic */ void m425lambda$onDrmSessionAcquired$6$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(Pair pair, int i) {
            MediaSourceList.this.eventListener.onDrmSessionAcquired(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, i);
        }

        public void onDrmKeysLoaded(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda2(this, eventParameters));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onDrmKeysLoaded$7$com-google-android-exoplayer2-MediaSourceList$ForwardingEventListener  reason: not valid java name */
        public /* synthetic */ void m422lambda$onDrmKeysLoaded$7$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(Pair pair) {
            MediaSourceList.this.eventListener.onDrmKeysLoaded(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second);
        }

        public void onDrmSessionManagerError(int i, MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda6(this, eventParameters, exc));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onDrmSessionManagerError$8$com-google-android-exoplayer2-MediaSourceList$ForwardingEventListener  reason: not valid java name */
        public /* synthetic */ void m426lambda$onDrmSessionManagerError$8$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(Pair pair, Exception exc) {
            MediaSourceList.this.eventListener.onDrmSessionManagerError(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, exc);
        }

        public void onDrmKeysRestored(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda3(this, eventParameters));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onDrmKeysRestored$9$com-google-android-exoplayer2-MediaSourceList$ForwardingEventListener  reason: not valid java name */
        public /* synthetic */ void m424lambda$onDrmKeysRestored$9$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(Pair pair) {
            MediaSourceList.this.eventListener.onDrmKeysRestored(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second);
        }

        public void onDrmKeysRemoved(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda7(this, eventParameters));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onDrmKeysRemoved$10$com-google-android-exoplayer2-MediaSourceList$ForwardingEventListener  reason: not valid java name */
        public /* synthetic */ void m423lambda$onDrmKeysRemoved$10$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(Pair pair) {
            MediaSourceList.this.eventListener.onDrmKeysRemoved(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second);
        }

        public void onDrmSessionReleased(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda5(this, eventParameters));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onDrmSessionReleased$11$com-google-android-exoplayer2-MediaSourceList$ForwardingEventListener  reason: not valid java name */
        public /* synthetic */ void m427lambda$onDrmSessionReleased$11$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(Pair pair) {
            MediaSourceList.this.eventListener.onDrmSessionReleased(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second);
        }

        private Pair<Integer, MediaSource.MediaPeriodId> getEventParameters(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            MediaSource.MediaPeriodId mediaPeriodId2 = null;
            if (mediaPeriodId != null) {
                MediaSource.MediaPeriodId access$100 = MediaSourceList.getMediaPeriodIdForChildMediaPeriodId(this.id, mediaPeriodId);
                if (access$100 == null) {
                    return null;
                }
                mediaPeriodId2 = access$100;
            }
            return Pair.create(Integer.valueOf(MediaSourceList.getWindowIndexForChildWindowIndex(this.id, i)), mediaPeriodId2);
        }
    }
}
