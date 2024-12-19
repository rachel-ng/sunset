package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.GlideTrace;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.List;
import java.util.concurrent.Executor;

public final class SingleRequest<R> implements Request, SizeReadyCallback, ResourceCallback {
    private static final String GLIDE_TAG = "Glide";
    private static final boolean IS_VERBOSE_LOGGABLE = Log.isLoggable(TAG, 2);
    private static final String TAG = "GlideRequest";
    private final TransitionFactory<? super R> animationFactory;
    private final Executor callbackExecutor;
    private final Context context;
    private int cookie;
    private volatile Engine engine;
    private Drawable errorDrawable;
    private Drawable fallbackDrawable;
    private final GlideContext glideContext;
    private int height;
    private boolean isCallingCallbacks;
    private Engine.LoadStatus loadStatus;
    private final Object model;
    private final int overrideHeight;
    private final int overrideWidth;
    private Drawable placeholderDrawable;
    private final Priority priority;
    private final RequestCoordinator requestCoordinator;
    private final List<RequestListener<R>> requestListeners;
    private final Object requestLock;
    private final BaseRequestOptions<?> requestOptions;
    private RuntimeException requestOrigin;
    private Resource<R> resource;
    private long startTime;
    private final StateVerifier stateVerifier;
    private Status status;
    private final String tag;
    private final Target<R> target;
    private final RequestListener<R> targetListener;
    private final Class<R> transcodeClass;
    private int width;

    private enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    public static <R> SingleRequest<R> obtain(Context context2, GlideContext glideContext2, Object obj, Object obj2, Class<R> cls, BaseRequestOptions<?> baseRequestOptions, int i, int i2, Priority priority2, Target<R> target2, RequestListener<R> requestListener, List<RequestListener<R>> list, RequestCoordinator requestCoordinator2, Engine engine2, TransitionFactory<? super R> transitionFactory, Executor executor) {
        return new SingleRequest(context2, glideContext2, obj, obj2, cls, baseRequestOptions, i, i2, priority2, target2, requestListener, list, requestCoordinator2, engine2, transitionFactory, executor);
    }

    private SingleRequest(Context context2, GlideContext glideContext2, Object obj, Object obj2, Class<R> cls, BaseRequestOptions<?> baseRequestOptions, int i, int i2, Priority priority2, Target<R> target2, RequestListener<R> requestListener, List<RequestListener<R>> list, RequestCoordinator requestCoordinator2, Engine engine2, TransitionFactory<? super R> transitionFactory, Executor executor) {
        this.tag = IS_VERBOSE_LOGGABLE ? String.valueOf(super.hashCode()) : null;
        this.stateVerifier = StateVerifier.newInstance();
        this.requestLock = obj;
        this.context = context2;
        this.glideContext = glideContext2;
        this.model = obj2;
        this.transcodeClass = cls;
        this.requestOptions = baseRequestOptions;
        this.overrideWidth = i;
        this.overrideHeight = i2;
        this.priority = priority2;
        this.target = target2;
        this.targetListener = requestListener;
        this.requestListeners = list;
        this.requestCoordinator = requestCoordinator2;
        this.engine = engine2;
        this.animationFactory = transitionFactory;
        this.callbackExecutor = executor;
        this.status = Status.PENDING;
        if (this.requestOrigin == null && glideContext2.getExperiments().isEnabled(GlideBuilder.LogRequestOrigins.class)) {
            this.requestOrigin = new RuntimeException("Glide request origin trace");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00b1, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void begin() {
        /*
            r5 = this;
            java.lang.String r0 = "finished run method in "
            java.lang.Object r1 = r5.requestLock
            monitor-enter(r1)
            r5.assertNotCallingCallbacks()     // Catch:{ all -> 0x00ba }
            com.bumptech.glide.util.pool.StateVerifier r2 = r5.stateVerifier     // Catch:{ all -> 0x00ba }
            r2.throwIfRecycled()     // Catch:{ all -> 0x00ba }
            long r2 = com.bumptech.glide.util.LogTime.getLogTime()     // Catch:{ all -> 0x00ba }
            r5.startTime = r2     // Catch:{ all -> 0x00ba }
            java.lang.Object r2 = r5.model     // Catch:{ all -> 0x00ba }
            if (r2 != 0) goto L_0x003e
            int r0 = r5.overrideWidth     // Catch:{ all -> 0x00ba }
            int r2 = r5.overrideHeight     // Catch:{ all -> 0x00ba }
            boolean r0 = com.bumptech.glide.util.Util.isValidDimensions(r0, r2)     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x0029
            int r0 = r5.overrideWidth     // Catch:{ all -> 0x00ba }
            r5.width = r0     // Catch:{ all -> 0x00ba }
            int r0 = r5.overrideHeight     // Catch:{ all -> 0x00ba }
            r5.height = r0     // Catch:{ all -> 0x00ba }
        L_0x0029:
            android.graphics.drawable.Drawable r0 = r5.getFallbackDrawable()     // Catch:{ all -> 0x00ba }
            if (r0 != 0) goto L_0x0031
            r0 = 5
            goto L_0x0032
        L_0x0031:
            r0 = 3
        L_0x0032:
            com.bumptech.glide.load.engine.GlideException r2 = new com.bumptech.glide.load.engine.GlideException     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = "Received null model"
            r2.<init>(r3)     // Catch:{ all -> 0x00ba }
            r5.onLoadFailed(r2, r0)     // Catch:{ all -> 0x00ba }
            monitor-exit(r1)     // Catch:{ all -> 0x00ba }
            return
        L_0x003e:
            com.bumptech.glide.request.SingleRequest$Status r2 = r5.status     // Catch:{ all -> 0x00ba }
            com.bumptech.glide.request.SingleRequest$Status r3 = com.bumptech.glide.request.SingleRequest.Status.RUNNING     // Catch:{ all -> 0x00ba }
            if (r2 == r3) goto L_0x00b2
            com.bumptech.glide.request.SingleRequest$Status r2 = r5.status     // Catch:{ all -> 0x00ba }
            com.bumptech.glide.request.SingleRequest$Status r3 = com.bumptech.glide.request.SingleRequest.Status.COMPLETE     // Catch:{ all -> 0x00ba }
            if (r2 != r3) goto L_0x0054
            com.bumptech.glide.load.engine.Resource<R> r0 = r5.resource     // Catch:{ all -> 0x00ba }
            com.bumptech.glide.load.DataSource r2 = com.bumptech.glide.load.DataSource.MEMORY_CACHE     // Catch:{ all -> 0x00ba }
            r3 = 0
            r5.onResourceReady(r0, r2, r3)     // Catch:{ all -> 0x00ba }
            monitor-exit(r1)     // Catch:{ all -> 0x00ba }
            return
        L_0x0054:
            java.lang.Object r2 = r5.model     // Catch:{ all -> 0x00ba }
            r5.experimentalNotifyRequestStarted(r2)     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = "GlideRequest"
            int r2 = com.bumptech.glide.util.pool.GlideTrace.beginSectionAsync(r2)     // Catch:{ all -> 0x00ba }
            r5.cookie = r2     // Catch:{ all -> 0x00ba }
            com.bumptech.glide.request.SingleRequest$Status r2 = com.bumptech.glide.request.SingleRequest.Status.WAITING_FOR_SIZE     // Catch:{ all -> 0x00ba }
            r5.status = r2     // Catch:{ all -> 0x00ba }
            int r2 = r5.overrideWidth     // Catch:{ all -> 0x00ba }
            int r3 = r5.overrideHeight     // Catch:{ all -> 0x00ba }
            boolean r2 = com.bumptech.glide.util.Util.isValidDimensions(r2, r3)     // Catch:{ all -> 0x00ba }
            if (r2 == 0) goto L_0x0077
            int r2 = r5.overrideWidth     // Catch:{ all -> 0x00ba }
            int r3 = r5.overrideHeight     // Catch:{ all -> 0x00ba }
            r5.onSizeReady(r2, r3)     // Catch:{ all -> 0x00ba }
            goto L_0x007c
        L_0x0077:
            com.bumptech.glide.request.target.Target<R> r2 = r5.target     // Catch:{ all -> 0x00ba }
            r2.getSize(r5)     // Catch:{ all -> 0x00ba }
        L_0x007c:
            com.bumptech.glide.request.SingleRequest$Status r2 = r5.status     // Catch:{ all -> 0x00ba }
            com.bumptech.glide.request.SingleRequest$Status r3 = com.bumptech.glide.request.SingleRequest.Status.RUNNING     // Catch:{ all -> 0x00ba }
            if (r2 == r3) goto L_0x0088
            com.bumptech.glide.request.SingleRequest$Status r2 = r5.status     // Catch:{ all -> 0x00ba }
            com.bumptech.glide.request.SingleRequest$Status r3 = com.bumptech.glide.request.SingleRequest.Status.WAITING_FOR_SIZE     // Catch:{ all -> 0x00ba }
            if (r2 != r3) goto L_0x0097
        L_0x0088:
            boolean r2 = r5.canNotifyStatusChanged()     // Catch:{ all -> 0x00ba }
            if (r2 == 0) goto L_0x0097
            com.bumptech.glide.request.target.Target<R> r2 = r5.target     // Catch:{ all -> 0x00ba }
            android.graphics.drawable.Drawable r3 = r5.getPlaceholderDrawable()     // Catch:{ all -> 0x00ba }
            r2.onLoadStarted(r3)     // Catch:{ all -> 0x00ba }
        L_0x0097:
            boolean r2 = IS_VERBOSE_LOGGABLE     // Catch:{ all -> 0x00ba }
            if (r2 == 0) goto L_0x00b0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r2.<init>(r0)     // Catch:{ all -> 0x00ba }
            long r3 = r5.startTime     // Catch:{ all -> 0x00ba }
            double r3 = com.bumptech.glide.util.LogTime.getElapsedMillis(r3)     // Catch:{ all -> 0x00ba }
            r2.append(r3)     // Catch:{ all -> 0x00ba }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x00ba }
            r5.logV(r0)     // Catch:{ all -> 0x00ba }
        L_0x00b0:
            monitor-exit(r1)     // Catch:{ all -> 0x00ba }
            return
        L_0x00b2:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = "Cannot restart a running request"
            r0.<init>(r2)     // Catch:{ all -> 0x00ba }
            throw r0     // Catch:{ all -> 0x00ba }
        L_0x00ba:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00ba }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.begin():void");
    }

    private void experimentalNotifyRequestStarted(Object obj) {
        List<RequestListener<R>> list = this.requestListeners;
        if (list != null) {
            for (RequestListener next : list) {
                if (next instanceof ExperimentalRequestListener) {
                    ((ExperimentalRequestListener) next).onRequestStarted(obj);
                }
            }
        }
    }

    private void cancel() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        this.target.removeCallback(this);
        Engine.LoadStatus loadStatus2 = this.loadStatus;
        if (loadStatus2 != null) {
            loadStatus2.cancel();
            this.loadStatus = null;
        }
    }

    private void assertNotCallingCallbacks() {
        if (this.isCallingCallbacks) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        r4.engine.release(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void clear() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.requestLock
            monitor-enter(r0)
            r4.assertNotCallingCallbacks()     // Catch:{ all -> 0x0042 }
            com.bumptech.glide.util.pool.StateVerifier r1 = r4.stateVerifier     // Catch:{ all -> 0x0042 }
            r1.throwIfRecycled()     // Catch:{ all -> 0x0042 }
            com.bumptech.glide.request.SingleRequest$Status r1 = r4.status     // Catch:{ all -> 0x0042 }
            com.bumptech.glide.request.SingleRequest$Status r2 = com.bumptech.glide.request.SingleRequest.Status.CLEARED     // Catch:{ all -> 0x0042 }
            if (r1 != r2) goto L_0x0013
            monitor-exit(r0)     // Catch:{ all -> 0x0042 }
            return
        L_0x0013:
            r4.cancel()     // Catch:{ all -> 0x0042 }
            com.bumptech.glide.load.engine.Resource<R> r1 = r4.resource     // Catch:{ all -> 0x0042 }
            r2 = 0
            if (r1 == 0) goto L_0x001e
            r4.resource = r2     // Catch:{ all -> 0x0042 }
            goto L_0x001f
        L_0x001e:
            r1 = r2
        L_0x001f:
            boolean r2 = r4.canNotifyCleared()     // Catch:{ all -> 0x0042 }
            if (r2 == 0) goto L_0x002e
            com.bumptech.glide.request.target.Target<R> r2 = r4.target     // Catch:{ all -> 0x0042 }
            android.graphics.drawable.Drawable r3 = r4.getPlaceholderDrawable()     // Catch:{ all -> 0x0042 }
            r2.onLoadCleared(r3)     // Catch:{ all -> 0x0042 }
        L_0x002e:
            java.lang.String r2 = "GlideRequest"
            int r3 = r4.cookie     // Catch:{ all -> 0x0042 }
            com.bumptech.glide.util.pool.GlideTrace.endSectionAsync(r2, r3)     // Catch:{ all -> 0x0042 }
            com.bumptech.glide.request.SingleRequest$Status r2 = com.bumptech.glide.request.SingleRequest.Status.CLEARED     // Catch:{ all -> 0x0042 }
            r4.status = r2     // Catch:{ all -> 0x0042 }
            monitor-exit(r0)     // Catch:{ all -> 0x0042 }
            if (r1 == 0) goto L_0x0041
            com.bumptech.glide.load.engine.Engine r0 = r4.engine
            r0.release(r1)
        L_0x0041:
            return
        L_0x0042:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0042 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.clear():void");
    }

    public void pause() {
        synchronized (this.requestLock) {
            if (isRunning()) {
                clear();
            }
        }
    }

    public boolean isRunning() {
        boolean z;
        synchronized (this.requestLock) {
            if (this.status != Status.RUNNING) {
                if (this.status != Status.WAITING_FOR_SIZE) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public boolean isComplete() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.COMPLETE;
        }
        return z;
    }

    public boolean isCleared() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.CLEARED;
        }
        return z;
    }

    public boolean isAnyResourceSet() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.COMPLETE;
        }
        return z;
    }

    private Drawable getErrorDrawable() {
        if (this.errorDrawable == null) {
            Drawable errorPlaceholder = this.requestOptions.getErrorPlaceholder();
            this.errorDrawable = errorPlaceholder;
            if (errorPlaceholder == null && this.requestOptions.getErrorId() > 0) {
                this.errorDrawable = loadDrawable(this.requestOptions.getErrorId());
            }
        }
        return this.errorDrawable;
    }

    private Drawable getPlaceholderDrawable() {
        if (this.placeholderDrawable == null) {
            Drawable placeholderDrawable2 = this.requestOptions.getPlaceholderDrawable();
            this.placeholderDrawable = placeholderDrawable2;
            if (placeholderDrawable2 == null && this.requestOptions.getPlaceholderId() > 0) {
                this.placeholderDrawable = loadDrawable(this.requestOptions.getPlaceholderId());
            }
        }
        return this.placeholderDrawable;
    }

    private Drawable getFallbackDrawable() {
        if (this.fallbackDrawable == null) {
            Drawable fallbackDrawable2 = this.requestOptions.getFallbackDrawable();
            this.fallbackDrawable = fallbackDrawable2;
            if (fallbackDrawable2 == null && this.requestOptions.getFallbackId() > 0) {
                this.fallbackDrawable = loadDrawable(this.requestOptions.getFallbackId());
            }
        }
        return this.fallbackDrawable;
    }

    private Drawable loadDrawable(int i) {
        return DrawableDecoderCompat.getDrawable(this.context, i, this.requestOptions.getTheme() != null ? this.requestOptions.getTheme() : this.context.getTheme());
    }

    private void setErrorPlaceholder() {
        if (canNotifyStatusChanged()) {
            Drawable fallbackDrawable2 = this.model == null ? getFallbackDrawable() : null;
            if (fallbackDrawable2 == null) {
                fallbackDrawable2 = getErrorDrawable();
            }
            if (fallbackDrawable2 == null) {
                fallbackDrawable2 = getPlaceholderDrawable();
            }
            this.target.onLoadFailed(fallbackDrawable2);
        }
    }

    public void onSizeReady(int i, int i2) {
        Object obj;
        this.stateVerifier.throwIfRecycled();
        Object obj2 = this.requestLock;
        synchronized (obj2) {
            try {
                boolean z = IS_VERBOSE_LOGGABLE;
                if (z) {
                    logV("Got onSizeReady in " + LogTime.getElapsedMillis(this.startTime));
                }
                if (this.status == Status.WAITING_FOR_SIZE) {
                    this.status = Status.RUNNING;
                    float sizeMultiplier = this.requestOptions.getSizeMultiplier();
                    this.width = maybeApplySizeMultiplier(i, sizeMultiplier);
                    this.height = maybeApplySizeMultiplier(i2, sizeMultiplier);
                    if (z) {
                        logV("finished setup for calling load in " + LogTime.getElapsedMillis(this.startTime));
                    }
                    String str = "finished onSizeReady in ";
                    obj = obj2;
                    try {
                        this.loadStatus = this.engine.load(this.glideContext, this.model, this.requestOptions.getSignature(), this.width, this.height, this.requestOptions.getResourceClass(), this.transcodeClass, this.priority, this.requestOptions.getDiskCacheStrategy(), this.requestOptions.getTransformations(), this.requestOptions.isTransformationRequired(), this.requestOptions.isScaleOnlyOrNoTransform(), this.requestOptions.getOptions(), this.requestOptions.isMemoryCacheable(), this.requestOptions.getUseUnlimitedSourceGeneratorsPool(), this.requestOptions.getUseAnimationPool(), this.requestOptions.getOnlyRetrieveFromCache(), this, this.callbackExecutor);
                        if (this.status != Status.RUNNING) {
                            this.loadStatus = null;
                        }
                        if (z) {
                            logV(str + LogTime.getElapsedMillis(this.startTime));
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                obj = obj2;
                throw th;
            }
        }
    }

    private static int maybeApplySizeMultiplier(int i, float f) {
        return i == Integer.MIN_VALUE ? i : Math.round(f * ((float) i));
    }

    private boolean canSetResource() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || requestCoordinator2.canSetImage(this);
    }

    private boolean canNotifyCleared() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || requestCoordinator2.canNotifyCleared(this);
    }

    private boolean canNotifyStatusChanged() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || requestCoordinator2.canNotifyStatusChanged(this);
    }

    private boolean isFirstReadyResource() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || !requestCoordinator2.getRoot().isAnyResourceSet();
    }

    private void notifyRequestCoordinatorLoadSucceeded() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        if (requestCoordinator2 != null) {
            requestCoordinator2.onRequestSuccess(this);
        }
    }

    private void notifyRequestCoordinatorLoadFailed() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        if (requestCoordinator2 != null) {
            requestCoordinator2.onRequestFailed(this);
        }
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [com.bumptech.glide.load.engine.Engine$LoadStatus, com.bumptech.glide.load.engine.Resource<R>] */
    /* JADX WARNING: type inference failed for: r2v2, types: [com.bumptech.glide.load.engine.Resource] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0055, code lost:
        if (r7 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
        r6.engine.release(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        return;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onResourceReady(com.bumptech.glide.load.engine.Resource<?> r7, com.bumptech.glide.load.DataSource r8, boolean r9) {
        /*
            r6 = this;
            java.lang.String r0 = "Expected to receive an object of "
            java.lang.String r1 = "Expected to receive a Resource<R> with an object of "
            com.bumptech.glide.util.pool.StateVerifier r2 = r6.stateVerifier
            r2.throwIfRecycled()
            r2 = 0
            java.lang.Object r3 = r6.requestLock     // Catch:{ all -> 0x00ba }
            monitor-enter(r3)     // Catch:{ all -> 0x00ba }
            r6.loadStatus = r2     // Catch:{ all -> 0x00b7 }
            if (r7 != 0) goto L_0x002e
            com.bumptech.glide.load.engine.GlideException r7 = new com.bumptech.glide.load.engine.GlideException     // Catch:{ all -> 0x00b7 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b7 }
            r8.<init>(r1)     // Catch:{ all -> 0x00b7 }
            java.lang.Class<R> r9 = r6.transcodeClass     // Catch:{ all -> 0x00b7 }
            r8.append(r9)     // Catch:{ all -> 0x00b7 }
            java.lang.String r9 = " inside, but instead got null."
            r8.append(r9)     // Catch:{ all -> 0x00b7 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x00b7 }
            r7.<init>(r8)     // Catch:{ all -> 0x00b7 }
            r6.onLoadFailed(r7)     // Catch:{ all -> 0x00b7 }
            monitor-exit(r3)     // Catch:{ all -> 0x00b7 }
            return
        L_0x002e:
            java.lang.Object r1 = r7.get()     // Catch:{ all -> 0x00b7 }
            if (r1 == 0) goto L_0x0062
            java.lang.Class<R> r4 = r6.transcodeClass     // Catch:{ all -> 0x00b7 }
            java.lang.Class r5 = r1.getClass()     // Catch:{ all -> 0x00b7 }
            boolean r4 = r4.isAssignableFrom(r5)     // Catch:{ all -> 0x00b7 }
            if (r4 != 0) goto L_0x0041
            goto L_0x0062
        L_0x0041:
            boolean r0 = r6.canSetResource()     // Catch:{ all -> 0x00b7 }
            if (r0 != 0) goto L_0x005d
            r6.resource = r2     // Catch:{ all -> 0x00b3 }
            com.bumptech.glide.request.SingleRequest$Status r8 = com.bumptech.glide.request.SingleRequest.Status.COMPLETE     // Catch:{ all -> 0x00b3 }
            r6.status = r8     // Catch:{ all -> 0x00b3 }
            java.lang.String r8 = "GlideRequest"
            int r9 = r6.cookie     // Catch:{ all -> 0x00b3 }
            com.bumptech.glide.util.pool.GlideTrace.endSectionAsync(r8, r9)     // Catch:{ all -> 0x00b3 }
            monitor-exit(r3)     // Catch:{ all -> 0x00b3 }
            if (r7 == 0) goto L_0x005c
            com.bumptech.glide.load.engine.Engine r8 = r6.engine
            r8.release(r7)
        L_0x005c:
            return
        L_0x005d:
            r6.onResourceReady(r7, r1, r8, r9)     // Catch:{ all -> 0x00b7 }
            monitor-exit(r3)     // Catch:{ all -> 0x00b7 }
            return
        L_0x0062:
            r6.resource = r2     // Catch:{ all -> 0x00b3 }
            com.bumptech.glide.load.engine.GlideException r8 = new com.bumptech.glide.load.engine.GlideException     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b3 }
            r9.<init>(r0)     // Catch:{ all -> 0x00b3 }
            java.lang.Class<R> r0 = r6.transcodeClass     // Catch:{ all -> 0x00b3 }
            r9.append(r0)     // Catch:{ all -> 0x00b3 }
            java.lang.String r0 = " but instead got "
            r9.append(r0)     // Catch:{ all -> 0x00b3 }
            if (r1 == 0) goto L_0x007c
            java.lang.Class r0 = r1.getClass()     // Catch:{ all -> 0x00b3 }
            goto L_0x007e
        L_0x007c:
            java.lang.String r0 = ""
        L_0x007e:
            r9.append(r0)     // Catch:{ all -> 0x00b3 }
            java.lang.String r0 = "{"
            r9.append(r0)     // Catch:{ all -> 0x00b3 }
            r9.append(r1)     // Catch:{ all -> 0x00b3 }
            java.lang.String r0 = "} inside Resource{"
            r9.append(r0)     // Catch:{ all -> 0x00b3 }
            r9.append(r7)     // Catch:{ all -> 0x00b3 }
            java.lang.String r0 = "}."
            r9.append(r0)     // Catch:{ all -> 0x00b3 }
            if (r1 == 0) goto L_0x009b
            java.lang.String r0 = ""
            goto L_0x009d
        L_0x009b:
            java.lang.String r0 = " To indicate failure return a null Resource object, rather than a Resource object containing null data."
        L_0x009d:
            r9.append(r0)     // Catch:{ all -> 0x00b3 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00b3 }
            r8.<init>(r9)     // Catch:{ all -> 0x00b3 }
            r6.onLoadFailed(r8)     // Catch:{ all -> 0x00b3 }
            monitor-exit(r3)     // Catch:{ all -> 0x00b3 }
            if (r7 == 0) goto L_0x00b2
            com.bumptech.glide.load.engine.Engine r8 = r6.engine
            r8.release(r7)
        L_0x00b2:
            return
        L_0x00b3:
            r8 = move-exception
            r2 = r7
            r7 = r8
            goto L_0x00b8
        L_0x00b7:
            r7 = move-exception
        L_0x00b8:
            monitor-exit(r3)     // Catch:{ all -> 0x00b7 }
            throw r7     // Catch:{ all -> 0x00ba }
        L_0x00ba:
            r7 = move-exception
            if (r2 == 0) goto L_0x00c2
            com.bumptech.glide.load.engine.Engine r8 = r6.engine
            r8.release(r2)
        L_0x00c2:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.onResourceReady(com.bumptech.glide.load.engine.Resource, com.bumptech.glide.load.DataSource, boolean):void");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ce A[Catch:{ all -> 0x00e5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onResourceReady(com.bumptech.glide.load.engine.Resource<R> r16, R r17, com.bumptech.glide.load.DataSource r18, boolean r19) {
        /*
            r15 = this;
            r1 = r15
            r0 = r18
            boolean r9 = r15.isFirstReadyResource()
            com.bumptech.glide.request.SingleRequest$Status r2 = com.bumptech.glide.request.SingleRequest.Status.COMPLETE
            r1.status = r2
            r2 = r16
            r1.resource = r2
            com.bumptech.glide.GlideContext r2 = r1.glideContext
            int r2 = r2.getLogLevel()
            r3 = 3
            if (r2 > r3) goto L_0x006c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Finished loading "
            r2.<init>(r3)
            java.lang.Class r3 = r17.getClass()
            java.lang.String r3 = r3.getSimpleName()
            r2.append(r3)
            java.lang.String r3 = " from "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r3 = " for "
            r2.append(r3)
            java.lang.Object r3 = r1.model
            r2.append(r3)
            java.lang.String r3 = " with size ["
            r2.append(r3)
            int r3 = r1.width
            r2.append(r3)
            java.lang.String r3 = "x"
            r2.append(r3)
            int r3 = r1.height
            r2.append(r3)
            java.lang.String r3 = "] in "
            r2.append(r3)
            long r3 = r1.startTime
            double r3 = com.bumptech.glide.util.LogTime.getElapsedMillis(r3)
            r2.append(r3)
            java.lang.String r3 = " ms"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "Glide"
            android.util.Log.d(r3, r2)
        L_0x006c:
            r15.notifyRequestCoordinatorLoadSucceeded()
            r10 = 1
            r1.isCallingCallbacks = r10
            r11 = 0
            java.util.List<com.bumptech.glide.request.RequestListener<R>> r2 = r1.requestListeners     // Catch:{ all -> 0x00e5 }
            if (r2 == 0) goto L_0x00b4
            java.util.Iterator r12 = r2.iterator()     // Catch:{ all -> 0x00e5 }
            r8 = r11
        L_0x007c:
            boolean r2 = r12.hasNext()     // Catch:{ all -> 0x00e5 }
            if (r2 == 0) goto L_0x00b5
            java.lang.Object r2 = r12.next()     // Catch:{ all -> 0x00e5 }
            r13 = r2
            com.bumptech.glide.request.RequestListener r13 = (com.bumptech.glide.request.RequestListener) r13     // Catch:{ all -> 0x00e5 }
            java.lang.Object r4 = r1.model     // Catch:{ all -> 0x00e5 }
            com.bumptech.glide.request.target.Target<R> r5 = r1.target     // Catch:{ all -> 0x00e5 }
            r2 = r13
            r3 = r17
            r6 = r18
            r7 = r9
            boolean r2 = r2.onResourceReady(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00e5 }
            r14 = r8 | r2
            boolean r2 = r13 instanceof com.bumptech.glide.request.ExperimentalRequestListener     // Catch:{ all -> 0x00e5 }
            if (r2 == 0) goto L_0x00b2
            r2 = r13
            com.bumptech.glide.request.ExperimentalRequestListener r2 = (com.bumptech.glide.request.ExperimentalRequestListener) r2     // Catch:{ all -> 0x00e5 }
            java.lang.Object r4 = r1.model     // Catch:{ all -> 0x00e5 }
            com.bumptech.glide.request.target.Target<R> r5 = r1.target     // Catch:{ all -> 0x00e5 }
            r3 = r17
            r6 = r18
            r7 = r9
            r8 = r19
            boolean r2 = r2.onResourceReady(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x00e5 }
            r2 = r2 | r14
            r8 = r2
            goto L_0x007c
        L_0x00b2:
            r8 = r14
            goto L_0x007c
        L_0x00b4:
            r8 = r11
        L_0x00b5:
            com.bumptech.glide.request.RequestListener<R> r2 = r1.targetListener     // Catch:{ all -> 0x00e5 }
            if (r2 == 0) goto L_0x00c9
            java.lang.Object r4 = r1.model     // Catch:{ all -> 0x00e5 }
            com.bumptech.glide.request.target.Target<R> r5 = r1.target     // Catch:{ all -> 0x00e5 }
            r3 = r17
            r6 = r18
            r7 = r9
            boolean r2 = r2.onResourceReady(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00e5 }
            if (r2 == 0) goto L_0x00c9
            goto L_0x00ca
        L_0x00c9:
            r10 = r11
        L_0x00ca:
            r2 = r8 | r10
            if (r2 != 0) goto L_0x00db
            com.bumptech.glide.request.transition.TransitionFactory<? super R> r2 = r1.animationFactory     // Catch:{ all -> 0x00e5 }
            com.bumptech.glide.request.transition.Transition r0 = r2.build(r0, r9)     // Catch:{ all -> 0x00e5 }
            com.bumptech.glide.request.target.Target<R> r2 = r1.target     // Catch:{ all -> 0x00e5 }
            r3 = r17
            r2.onResourceReady(r3, r0)     // Catch:{ all -> 0x00e5 }
        L_0x00db:
            r1.isCallingCallbacks = r11
            java.lang.String r0 = "GlideRequest"
            int r2 = r1.cookie
            com.bumptech.glide.util.pool.GlideTrace.endSectionAsync(r0, r2)
            return
        L_0x00e5:
            r0 = move-exception
            r1.isCallingCallbacks = r11
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.onResourceReady(com.bumptech.glide.load.engine.Resource, java.lang.Object, com.bumptech.glide.load.DataSource, boolean):void");
    }

    public void onLoadFailed(GlideException glideException) {
        onLoadFailed(glideException, 5);
    }

    public Object getLock() {
        this.stateVerifier.throwIfRecycled();
        return this.requestLock;
    }

    /* JADX INFO: finally extract failed */
    private void onLoadFailed(GlideException glideException, int i) {
        boolean z;
        this.stateVerifier.throwIfRecycled();
        synchronized (this.requestLock) {
            glideException.setOrigin(this.requestOrigin);
            int logLevel = this.glideContext.getLogLevel();
            if (logLevel <= i) {
                Log.w(GLIDE_TAG, "Load failed for [" + this.model + "] with dimensions [" + this.width + "x" + this.height + "]", glideException);
                if (logLevel <= 4) {
                    glideException.logRootCauses(GLIDE_TAG);
                }
            }
            this.loadStatus = null;
            this.status = Status.FAILED;
            notifyRequestCoordinatorLoadFailed();
            boolean z2 = true;
            this.isCallingCallbacks = true;
            try {
                List<RequestListener<R>> list = this.requestListeners;
                if (list != null) {
                    z = false;
                    for (RequestListener<R> onLoadFailed : list) {
                        z |= onLoadFailed.onLoadFailed(glideException, this.model, this.target, isFirstReadyResource());
                    }
                } else {
                    z = false;
                }
                RequestListener<R> requestListener = this.targetListener;
                if (requestListener == null || !requestListener.onLoadFailed(glideException, this.model, this.target, isFirstReadyResource())) {
                    z2 = false;
                }
                if (!z && !z2) {
                    setErrorPlaceholder();
                }
                this.isCallingCallbacks = false;
                GlideTrace.endSectionAsync(TAG, this.cookie);
            } catch (Throwable th) {
                this.isCallingCallbacks = false;
                throw th;
            }
        }
    }

    public boolean isEquivalentTo(Request request) {
        int i;
        int i2;
        Object obj;
        Class<R> cls;
        BaseRequestOptions<?> baseRequestOptions;
        Priority priority2;
        int size;
        int i3;
        int i4;
        Object obj2;
        Class<R> cls2;
        BaseRequestOptions<?> baseRequestOptions2;
        Priority priority3;
        int size2;
        Request request2 = request;
        if (!(request2 instanceof SingleRequest)) {
            return false;
        }
        synchronized (this.requestLock) {
            i = this.overrideWidth;
            i2 = this.overrideHeight;
            obj = this.model;
            cls = this.transcodeClass;
            baseRequestOptions = this.requestOptions;
            priority2 = this.priority;
            List<RequestListener<R>> list = this.requestListeners;
            size = list != null ? list.size() : 0;
        }
        SingleRequest singleRequest = (SingleRequest) request2;
        synchronized (singleRequest.requestLock) {
            i3 = singleRequest.overrideWidth;
            i4 = singleRequest.overrideHeight;
            obj2 = singleRequest.model;
            cls2 = singleRequest.transcodeClass;
            baseRequestOptions2 = singleRequest.requestOptions;
            priority3 = singleRequest.priority;
            List<RequestListener<R>> list2 = singleRequest.requestListeners;
            size2 = list2 != null ? list2.size() : 0;
        }
        return i == i3 && i2 == i4 && Util.bothModelsNullEquivalentOrEquals(obj, obj2) && cls.equals(cls2) && Util.bothBaseRequestOptionsNullEquivalentOrEquals(baseRequestOptions, baseRequestOptions2) && priority2 == priority3 && size == size2;
    }

    private void logV(String str) {
        Log.v(TAG, str + " this: " + this.tag);
    }

    public String toString() {
        Object obj;
        Class<R> cls;
        synchronized (this.requestLock) {
            obj = this.model;
            cls = this.transcodeClass;
        }
        return super.toString() + "[model=" + obj + ", transcodeClass=" + cls + "]";
    }
}
