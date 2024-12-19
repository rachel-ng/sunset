package com.google.firebase.crashlytics.internal.send;

import android.os.SystemClock;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.runtime.ForcedSender;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId;
import com.google.firebase.crashlytics.internal.common.OnDemandCounter;
import com.google.firebase.crashlytics.internal.common.Utils;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.settings.Settings;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class ReportQueue {
    private static final int MAX_DELAY_MS = 3600000;
    private static final int MS_PER_MINUTE = 60000;
    private static final int MS_PER_SECOND = 1000;
    private static final int STARTUP_DURATION_MS = 2000;
    private final double base;
    private long lastUpdatedMs;
    /* access modifiers changed from: private */
    public final OnDemandCounter onDemandCounter;
    private final BlockingQueue<Runnable> queue;
    private final int queueCapacity;
    private final double ratePerMinute;
    private final ThreadPoolExecutor singleThreadExecutor;
    private final long startTimeMs;
    private int step;
    private final long stepDurationMs;
    private final Transport<CrashlyticsReport> transport;

    ReportQueue(Transport<CrashlyticsReport> transport2, Settings settings, OnDemandCounter onDemandCounter2) {
        this(settings.onDemandUploadRatePerMinute, settings.onDemandBackoffBase, ((long) settings.onDemandBackoffStepDurationSeconds) * 1000, transport2, onDemandCounter2);
    }

    ReportQueue(double d, double d2, long j, Transport<CrashlyticsReport> transport2, OnDemandCounter onDemandCounter2) {
        this.ratePerMinute = d;
        this.base = d2;
        this.stepDurationMs = j;
        this.transport = transport2;
        this.onDemandCounter = onDemandCounter2;
        this.startTimeMs = SystemClock.elapsedRealtime();
        int i = (int) d;
        this.queueCapacity = i;
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(i);
        this.queue = arrayBlockingQueue;
        this.singleThreadExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, arrayBlockingQueue);
        this.step = 0;
        this.lastUpdatedMs = 0;
    }

    /* access modifiers changed from: package-private */
    public TaskCompletionSource<CrashlyticsReportWithSessionId> enqueueReport(CrashlyticsReportWithSessionId crashlyticsReportWithSessionId, boolean z) {
        synchronized (this.queue) {
            TaskCompletionSource<CrashlyticsReportWithSessionId> taskCompletionSource = new TaskCompletionSource<>();
            if (z) {
                this.onDemandCounter.incrementRecordedOnDemandExceptions();
                if (isQueueAvailable()) {
                    Logger logger = Logger.getLogger();
                    logger.d("Enqueueing report: " + crashlyticsReportWithSessionId.getSessionId());
                    Logger logger2 = Logger.getLogger();
                    logger2.d("Queue size: " + this.queue.size());
                    this.singleThreadExecutor.execute(new ReportRunnable(crashlyticsReportWithSessionId, taskCompletionSource));
                    Logger logger3 = Logger.getLogger();
                    logger3.d("Closing task for report: " + crashlyticsReportWithSessionId.getSessionId());
                    taskCompletionSource.trySetResult(crashlyticsReportWithSessionId);
                    return taskCompletionSource;
                }
                calcStep();
                Logger logger4 = Logger.getLogger();
                logger4.d("Dropping report due to queue being full: " + crashlyticsReportWithSessionId.getSessionId());
                this.onDemandCounter.incrementDroppedOnDemandExceptions();
                taskCompletionSource.trySetResult(crashlyticsReportWithSessionId);
                return taskCompletionSource;
            }
            sendReport(crashlyticsReportWithSessionId, taskCompletionSource);
            return taskCompletionSource;
        }
    }

    public void flushScheduledReportsIfAble() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new ReportQueue$$ExternalSyntheticLambda0(this, countDownLatch)).start();
        Utils.awaitUninterruptibly(countDownLatch, 2, TimeUnit.SECONDS);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$flushScheduledReportsIfAble$0$com-google-firebase-crashlytics-internal-send-ReportQueue  reason: not valid java name */
    public /* synthetic */ void m657lambda$flushScheduledReportsIfAble$0$comgooglefirebasecrashlyticsinternalsendReportQueue(CountDownLatch countDownLatch) {
        try {
            ForcedSender.sendBlocking(this.transport, Priority.HIGHEST);
        } catch (Exception unused) {
        }
        countDownLatch.countDown();
    }

    /* access modifiers changed from: private */
    public void sendReport(CrashlyticsReportWithSessionId crashlyticsReportWithSessionId, TaskCompletionSource<CrashlyticsReportWithSessionId> taskCompletionSource) {
        Logger logger = Logger.getLogger();
        logger.d("Sending report through Google DataTransport: " + crashlyticsReportWithSessionId.getSessionId());
        this.transport.schedule(Event.ofUrgent(crashlyticsReportWithSessionId.getReport()), new ReportQueue$$ExternalSyntheticLambda1(this, taskCompletionSource, SystemClock.elapsedRealtime() - this.startTimeMs < ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, crashlyticsReportWithSessionId));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$sendReport$1$com-google-firebase-crashlytics-internal-send-ReportQueue  reason: not valid java name */
    public /* synthetic */ void m658lambda$sendReport$1$comgooglefirebasecrashlyticsinternalsendReportQueue(TaskCompletionSource taskCompletionSource, boolean z, CrashlyticsReportWithSessionId crashlyticsReportWithSessionId, Exception exc) {
        if (exc != null) {
            taskCompletionSource.trySetException(exc);
            return;
        }
        if (z) {
            flushScheduledReportsIfAble();
        }
        taskCompletionSource.trySetResult(crashlyticsReportWithSessionId);
    }

    private boolean isQueueAvailable() {
        return this.queue.size() < this.queueCapacity;
    }

    private boolean isQueueFull() {
        return this.queue.size() == this.queueCapacity;
    }

    /* access modifiers changed from: private */
    public double calcDelay() {
        return Math.min(3600000.0d, (60000.0d / this.ratePerMinute) * Math.pow(this.base, (double) calcStep()));
    }

    private int calcStep() {
        int i;
        if (this.lastUpdatedMs == 0) {
            this.lastUpdatedMs = now();
        }
        int now = (int) ((now() - this.lastUpdatedMs) / this.stepDurationMs);
        if (isQueueFull()) {
            i = Math.min(100, this.step + now);
        } else {
            i = Math.max(0, this.step - now);
        }
        if (this.step != i) {
            this.step = i;
            this.lastUpdatedMs = now();
        }
        return i;
    }

    private long now() {
        return System.currentTimeMillis();
    }

    private final class ReportRunnable implements Runnable {
        private final CrashlyticsReportWithSessionId reportWithSessionId;
        private final TaskCompletionSource<CrashlyticsReportWithSessionId> tcs;

        private ReportRunnable(CrashlyticsReportWithSessionId crashlyticsReportWithSessionId, TaskCompletionSource<CrashlyticsReportWithSessionId> taskCompletionSource) {
            this.reportWithSessionId = crashlyticsReportWithSessionId;
            this.tcs = taskCompletionSource;
        }

        public void run() {
            ReportQueue.this.sendReport(this.reportWithSessionId, this.tcs);
            ReportQueue.this.onDemandCounter.resetDroppedOnDemandExceptions();
            double access$300 = ReportQueue.this.calcDelay();
            Logger logger = Logger.getLogger();
            logger.d("Delay for: " + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(access$300 / 1000.0d)}) + " s for report: " + this.reportWithSessionId.getSessionId());
            ReportQueue.sleep(access$300);
        }
    }

    /* access modifiers changed from: private */
    public static void sleep(double d) {
        try {
            Thread.sleep((long) d);
        } catch (InterruptedException unused) {
        }
    }
}
