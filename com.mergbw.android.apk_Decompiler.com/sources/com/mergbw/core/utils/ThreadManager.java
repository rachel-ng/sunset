package com.mergbw.core.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadManager {
    /* access modifiers changed from: private */
    public static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    public static final String DEFAULT_SINGLE_POOL_NAME = "DEFAULT_SINGLE_POOL_NAME";
    private static final int KEEP_ALIVE_SECONDS = 30;
    /* access modifiers changed from: private */
    public static final int MAXIMUM_POOL_SIZE;
    private static final Object mDownloadLock = new Object();
    private static ThreadPoolProxy mDownloadPool = null;
    private static final Object mLongLock = new Object();
    private static ThreadPoolProxy mLongPool = null;
    private static final Map<String, ThreadPoolProxy> mMap = new HashMap();
    private static final Object mShortLock = new Object();
    private static ThreadPoolProxy mShortPool = null;
    private static final Object mSingleLock = new Object();

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = availableProcessors;
        CORE_POOL_SIZE = availableProcessors;
        MAXIMUM_POOL_SIZE = (availableProcessors * 2) + 1;
    }

    public static ThreadPoolProxy getDownloadPool() {
        ThreadPoolProxy threadPoolProxy;
        synchronized (mDownloadLock) {
            if (mDownloadPool == null) {
                mDownloadPool = new ThreadPoolProxy();
            }
            threadPoolProxy = mDownloadPool;
        }
        return threadPoolProxy;
    }

    public static ThreadPoolProxy getLongPool() {
        ThreadPoolProxy threadPoolProxy;
        synchronized (mLongLock) {
            if (mLongPool == null) {
                mLongPool = new ThreadPoolProxy();
            }
            threadPoolProxy = mLongPool;
        }
        return threadPoolProxy;
    }

    public static ThreadPoolProxy getShortPool() {
        ThreadPoolProxy threadPoolProxy;
        synchronized (mShortLock) {
            if (mShortPool == null) {
                mShortPool = new ThreadPoolProxy();
            }
            threadPoolProxy = mShortPool;
        }
        return threadPoolProxy;
    }

    public static ThreadPoolProxy getSinglePool() {
        return getSinglePool(DEFAULT_SINGLE_POOL_NAME);
    }

    public static ThreadPoolProxy getSinglePool(String str) {
        ThreadPoolProxy threadPoolProxy;
        synchronized (mSingleLock) {
            Map<String, ThreadPoolProxy> map = mMap;
            threadPoolProxy = map.get(str);
            if (threadPoolProxy == null) {
                threadPoolProxy = new ThreadPoolProxy();
                map.put(str, threadPoolProxy);
            }
        }
        return threadPoolProxy;
    }

    public static class ThreadPoolProxy {
        private ThreadPoolExecutor mPool;

        private ThreadPoolProxy() {
        }

        public synchronized void execute(Runnable runnable) {
            if (runnable != null) {
                ThreadPoolExecutor threadPoolExecutor = this.mPool;
                if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
                    this.mPool = new ThreadPoolExecutor(ThreadManager.CORE_POOL_SIZE, ThreadManager.MAXIMUM_POOL_SIZE, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
                }
                this.mPool.execute(runnable);
            }
        }

        public synchronized void cancel(Runnable runnable) {
            ThreadPoolExecutor threadPoolExecutor = this.mPool;
            if (threadPoolExecutor != null && (!threadPoolExecutor.isShutdown() || this.mPool.isTerminating())) {
                this.mPool.getQueue().remove(runnable);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
            return false;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized boolean contains(java.lang.Runnable r2) {
            /*
                r1 = this;
                monitor-enter(r1)
                java.util.concurrent.ThreadPoolExecutor r0 = r1.mPool     // Catch:{ all -> 0x0022 }
                if (r0 == 0) goto L_0x001f
                boolean r0 = r0.isShutdown()     // Catch:{ all -> 0x0022 }
                if (r0 == 0) goto L_0x0013
                java.util.concurrent.ThreadPoolExecutor r0 = r1.mPool     // Catch:{ all -> 0x0022 }
                boolean r0 = r0.isTerminating()     // Catch:{ all -> 0x0022 }
                if (r0 == 0) goto L_0x001f
            L_0x0013:
                java.util.concurrent.ThreadPoolExecutor r0 = r1.mPool     // Catch:{ all -> 0x0022 }
                java.util.concurrent.BlockingQueue r0 = r0.getQueue()     // Catch:{ all -> 0x0022 }
                boolean r2 = r0.contains(r2)     // Catch:{ all -> 0x0022 }
                monitor-exit(r1)
                return r2
            L_0x001f:
                monitor-exit(r1)
                r2 = 0
                return r2
            L_0x0022:
                r2 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0022 }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mergbw.core.utils.ThreadManager.ThreadPoolProxy.contains(java.lang.Runnable):boolean");
        }

        public void stop() {
            ThreadPoolExecutor threadPoolExecutor = this.mPool;
            if (threadPoolExecutor == null) {
                return;
            }
            if (!threadPoolExecutor.isShutdown() || this.mPool.isTerminating()) {
                this.mPool.shutdownNow();
            }
        }

        public synchronized void shutdown() {
            ThreadPoolExecutor threadPoolExecutor = this.mPool;
            if (threadPoolExecutor != null && (!threadPoolExecutor.isShutdown() || this.mPool.isTerminating())) {
                this.mPool.shutdownNow();
            }
        }
    }
}
