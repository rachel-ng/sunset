package com.google.firebase.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import org.apache.commons.math3.geometry.VectorFormat;

final class SequentialExecutor implements Executor {
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(SequentialExecutor.class.getName());
    private final Executor executor;
    /* access modifiers changed from: private */
    public final Deque<Runnable> queue = new ArrayDeque();
    private final QueueWorker worker = new QueueWorker();
    private long workerRunCount = 0;
    /* access modifiers changed from: private */
    public WorkerRunningState workerRunningState = WorkerRunningState.IDLE;

    enum WorkerRunningState {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING
    }

    static /* synthetic */ long access$308(SequentialExecutor sequentialExecutor) {
        long j = sequentialExecutor.workerRunCount;
        sequentialExecutor.workerRunCount = 1 + j;
        return j;
    }

    SequentialExecutor(Executor executor2) {
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r5.executor.execute(r5.worker);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        if (r5.workerRunningState == com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.QUEUING) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        r6 = r5.queue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
        if (r5.workerRunCount != r1) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003f, code lost:
        if (r5.workerRunningState != com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.QUEUING) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0041, code lost:
        r5.workerRunningState = com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.QUEUED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0045, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0046, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004a, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004f, code lost:
        monitor-enter(r5.queue);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0054, code lost:
        if (r5.workerRunningState == com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.IDLE) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0064, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0066, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0069, code lost:
        if ((r6 instanceof java.util.concurrent.RejectedExecutionException) == false) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x006e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x006f, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute(final java.lang.Runnable r6) {
        /*
            r5 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)
            java.util.Deque<java.lang.Runnable> r0 = r5.queue
            monitor-enter(r0)
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r1 = r5.workerRunningState     // Catch:{ all -> 0x007a }
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r2 = com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch:{ all -> 0x007a }
            if (r1 == r2) goto L_0x0073
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r1 = r5.workerRunningState     // Catch:{ all -> 0x007a }
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r2 = com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.QUEUED     // Catch:{ all -> 0x007a }
            if (r1 != r2) goto L_0x0013
            goto L_0x0073
        L_0x0013:
            long r1 = r5.workerRunCount     // Catch:{ all -> 0x007a }
            com.google.firebase.concurrent.SequentialExecutor$1 r3 = new com.google.firebase.concurrent.SequentialExecutor$1     // Catch:{ all -> 0x007a }
            r3.<init>(r6)     // Catch:{ all -> 0x007a }
            java.util.Deque<java.lang.Runnable> r6 = r5.queue     // Catch:{ all -> 0x007a }
            r6.add(r3)     // Catch:{ all -> 0x007a }
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r6 = com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.QUEUING     // Catch:{ all -> 0x007a }
            r5.workerRunningState = r6     // Catch:{ all -> 0x007a }
            monitor-exit(r0)     // Catch:{ all -> 0x007a }
            java.util.concurrent.Executor r6 = r5.executor     // Catch:{ RuntimeException -> 0x004c, Error -> 0x004a }
            com.google.firebase.concurrent.SequentialExecutor$QueueWorker r0 = r5.worker     // Catch:{ RuntimeException -> 0x004c, Error -> 0x004a }
            r6.execute(r0)     // Catch:{ RuntimeException -> 0x004c, Error -> 0x004a }
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r6 = r5.workerRunningState
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r0 = com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.QUEUING
            if (r6 == r0) goto L_0x0032
            return
        L_0x0032:
            java.util.Deque<java.lang.Runnable> r6 = r5.queue
            monitor-enter(r6)
            long r3 = r5.workerRunCount     // Catch:{ all -> 0x0047 }
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x0045
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r0 = r5.workerRunningState     // Catch:{ all -> 0x0047 }
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r1 = com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.QUEUING     // Catch:{ all -> 0x0047 }
            if (r0 != r1) goto L_0x0045
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r0 = com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.QUEUED     // Catch:{ all -> 0x0047 }
            r5.workerRunningState = r0     // Catch:{ all -> 0x0047 }
        L_0x0045:
            monitor-exit(r6)     // Catch:{ all -> 0x0047 }
            return
        L_0x0047:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0047 }
            throw r0
        L_0x004a:
            r6 = move-exception
            goto L_0x004d
        L_0x004c:
            r6 = move-exception
        L_0x004d:
            java.util.Deque<java.lang.Runnable> r1 = r5.queue
            monitor-enter(r1)
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r0 = r5.workerRunningState     // Catch:{ all -> 0x0070 }
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r2 = com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.IDLE     // Catch:{ all -> 0x0070 }
            if (r0 == r2) goto L_0x005c
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r0 = r5.workerRunningState     // Catch:{ all -> 0x0070 }
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r2 = com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.QUEUING     // Catch:{ all -> 0x0070 }
            if (r0 != r2) goto L_0x0066
        L_0x005c:
            java.util.Deque<java.lang.Runnable> r0 = r5.queue     // Catch:{ all -> 0x0070 }
            boolean r0 = r0.removeLastOccurrence(r3)     // Catch:{ all -> 0x0070 }
            if (r0 == 0) goto L_0x0066
            r0 = 1
            goto L_0x0067
        L_0x0066:
            r0 = 0
        L_0x0067:
            boolean r2 = r6 instanceof java.util.concurrent.RejectedExecutionException     // Catch:{ all -> 0x0070 }
            if (r2 == 0) goto L_0x006f
            if (r0 != 0) goto L_0x006f
            monitor-exit(r1)     // Catch:{ all -> 0x0070 }
            return
        L_0x006f:
            throw r6     // Catch:{ all -> 0x0070 }
        L_0x0070:
            r6 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0070 }
            throw r6
        L_0x0073:
            java.util.Deque<java.lang.Runnable> r1 = r5.queue     // Catch:{ all -> 0x007a }
            r1.add(r6)     // Catch:{ all -> 0x007a }
            monitor-exit(r0)     // Catch:{ all -> 0x007a }
            return
        L_0x007a:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x007a }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.concurrent.SequentialExecutor.execute(java.lang.Runnable):void");
    }

    private final class QueueWorker implements Runnable {
        @CheckForNull
        Runnable task;

        private QueueWorker() {
        }

        public void run() {
            try {
                workOnQueue();
            } catch (Error e) {
                synchronized (SequentialExecutor.this.queue) {
                    WorkerRunningState unused = SequentialExecutor.this.workerRunningState = WorkerRunningState.IDLE;
                    throw e;
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
            if (r1 == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0054, code lost:
            r1 = r1 | java.lang.Thread.interrupted();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            r8.task.run();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0060, code lost:
            r3 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
            com.google.firebase.concurrent.SequentialExecutor.access$400().log(java.util.logging.Level.SEVERE, "Exception while executing runnable " + r8.task, r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
            return;
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
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
            if (r1 == false) goto L_?;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void workOnQueue() {
            /*
                r8 = this;
                r0 = 0
                r1 = r0
            L_0x0002:
                com.google.firebase.concurrent.SequentialExecutor r2 = com.google.firebase.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0084 }
                java.util.Deque r2 = r2.queue     // Catch:{ all -> 0x0084 }
                monitor-enter(r2)     // Catch:{ all -> 0x0084 }
                if (r0 != 0) goto L_0x002d
                com.google.firebase.concurrent.SequentialExecutor r0 = com.google.firebase.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0081 }
                com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r0 = r0.workerRunningState     // Catch:{ all -> 0x0081 }
                com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch:{ all -> 0x0081 }
                if (r0 != r3) goto L_0x0020
                monitor-exit(r2)     // Catch:{ all -> 0x0081 }
                if (r1 == 0) goto L_0x001f
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L_0x001f:
                return
            L_0x0020:
                com.google.firebase.concurrent.SequentialExecutor r0 = com.google.firebase.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0081 }
                com.google.firebase.concurrent.SequentialExecutor.access$308(r0)     // Catch:{ all -> 0x0081 }
                com.google.firebase.concurrent.SequentialExecutor r0 = com.google.firebase.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0081 }
                com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch:{ all -> 0x0081 }
                com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState unused = r0.workerRunningState = r3     // Catch:{ all -> 0x0081 }
                r0 = 1
            L_0x002d:
                com.google.firebase.concurrent.SequentialExecutor r3 = com.google.firebase.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0081 }
                java.util.Deque r3 = r3.queue     // Catch:{ all -> 0x0081 }
                java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x0081 }
                java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch:{ all -> 0x0081 }
                r8.task = r3     // Catch:{ all -> 0x0081 }
                if (r3 != 0) goto L_0x004f
                com.google.firebase.concurrent.SequentialExecutor r0 = com.google.firebase.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0081 }
                com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState.IDLE     // Catch:{ all -> 0x0081 }
                com.google.firebase.concurrent.SequentialExecutor.WorkerRunningState unused = r0.workerRunningState = r3     // Catch:{ all -> 0x0081 }
                monitor-exit(r2)     // Catch:{ all -> 0x0081 }
                if (r1 == 0) goto L_0x004e
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L_0x004e:
                return
            L_0x004f:
                monitor-exit(r2)     // Catch:{ all -> 0x0081 }
                boolean r2 = java.lang.Thread.interrupted()     // Catch:{ all -> 0x0084 }
                r1 = r1 | r2
                r2 = 0
                java.lang.Runnable r3 = r8.task     // Catch:{ RuntimeException -> 0x0060 }
                r3.run()     // Catch:{ RuntimeException -> 0x0060 }
            L_0x005b:
                r8.task = r2     // Catch:{ all -> 0x0084 }
                goto L_0x0002
            L_0x005e:
                r0 = move-exception
                goto L_0x007e
            L_0x0060:
                r3 = move-exception
                java.util.logging.Logger r4 = com.google.firebase.concurrent.SequentialExecutor.log     // Catch:{ all -> 0x005e }
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch:{ all -> 0x005e }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x005e }
                r6.<init>()     // Catch:{ all -> 0x005e }
                java.lang.String r7 = "Exception while executing runnable "
                r6.append(r7)     // Catch:{ all -> 0x005e }
                java.lang.Runnable r7 = r8.task     // Catch:{ all -> 0x005e }
                r6.append(r7)     // Catch:{ all -> 0x005e }
                java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x005e }
                r4.log(r5, r6, r3)     // Catch:{ all -> 0x005e }
                goto L_0x005b
            L_0x007e:
                r8.task = r2     // Catch:{ all -> 0x0084 }
                throw r0     // Catch:{ all -> 0x0084 }
            L_0x0081:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0081 }
                throw r0     // Catch:{ all -> 0x0084 }
            L_0x0084:
                r0 = move-exception
                if (r1 == 0) goto L_0x008e
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                r1.interrupt()
            L_0x008e:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.concurrent.SequentialExecutor.QueueWorker.workOnQueue():void");
        }

        public String toString() {
            Runnable runnable = this.task;
            if (runnable != null) {
                return "SequentialExecutorWorker{running=" + runnable + VectorFormat.DEFAULT_SUFFIX;
            }
            return "SequentialExecutorWorker{state=" + SequentialExecutor.this.workerRunningState + VectorFormat.DEFAULT_SUFFIX;
        }
    }

    public String toString() {
        return "SequentialExecutor@" + System.identityHashCode(this) + VectorFormat.DEFAULT_PREFIX + this.executor + VectorFormat.DEFAULT_SUFFIX;
    }
}
