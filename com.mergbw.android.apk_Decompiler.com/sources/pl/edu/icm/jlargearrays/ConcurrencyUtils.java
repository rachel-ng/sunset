package pl.edu.icm.jlargearrays;

import java.lang.Thread;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.apache.commons.math3.util.FastMath;

public class ConcurrencyUtils {
    private static final ExecutorService DEFAULT_THREAD_POOL;
    private static long concurrentThreshold = 100000;
    private static int nthreads = getNumberOfProcessors();
    private static ExecutorService threadPool;

    static {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool(new CustomThreadFactory(new CustomExceptionHandler()));
        DEFAULT_THREAD_POOL = newCachedThreadPool;
        threadPool = newCachedThreadPool;
    }

    private ConcurrencyUtils() {
    }

    private static class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {
        private CustomExceptionHandler() {
        }

        public void uncaughtException(Thread thread, Throwable th) {
            th.printStackTrace();
        }
    }

    private static class CustomThreadFactory implements ThreadFactory {
        private static final ThreadFactory DEFAULT_FACTORY = Executors.defaultThreadFactory();
        private final Thread.UncaughtExceptionHandler handler;

        CustomThreadFactory(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.handler = uncaughtExceptionHandler;
        }

        public Thread newThread(Runnable runnable) {
            Thread newThread = DEFAULT_FACTORY.newThread(runnable);
            newThread.setUncaughtExceptionHandler(this.handler);
            return newThread;
        }
    }

    public static long getConcurrentThreshold() {
        return concurrentThreshold;
    }

    public static void setConcurrentThreshold(long j) {
        concurrentThreshold = FastMath.max(1, j);
    }

    public static int getNumberOfProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static int getNumberOfThreads() {
        return nthreads;
    }

    public static void setNumberOfThreads(int i) {
        nthreads = i;
    }

    public static <T> Future<T> submit(Callable<T> callable) {
        if (threadPool.isShutdown() || threadPool.isTerminated()) {
            threadPool = DEFAULT_THREAD_POOL;
        }
        return threadPool.submit(callable);
    }

    public static Future<?> submit(Runnable runnable) {
        if (threadPool.isShutdown() || threadPool.isTerminated()) {
            threadPool = DEFAULT_THREAD_POOL;
        }
        return threadPool.submit(runnable);
    }

    public static void waitForCompletion(Future<?>[] futureArr) throws InterruptedException, ExecutionException {
        for (Future<?> future : futureArr) {
            future.get();
        }
    }

    public static void setThreadPool(ExecutorService executorService) {
        threadPool = executorService;
    }

    public static ExecutorService getThreadPool() {
        return threadPool;
    }

    public static void shutdownThreadPoolAndAwaitTermination() {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException unused) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
