package com.alibaba.android.arouter.core;

import android.content.Context;
import androidx.work.WorkRequest;
import com.alibaba.android.arouter.exception.HandlerException;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.service.InterceptorService;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.thread.CancelableCountDownLatch;
import com.alibaba.android.arouter.utils.MapUtils;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class InterceptorServiceImpl implements InterceptorService {
    /* access modifiers changed from: private */
    public static boolean interceptorHasInit;
    /* access modifiers changed from: private */
    public static final Object interceptorInitLock = new Object();

    public void doInterceptions(final Postcard postcard, final InterceptorCallback interceptorCallback) {
        if (MapUtils.isNotEmpty(Warehouse.interceptorsIndex)) {
            checkInterceptorsInitStatus();
            if (!interceptorHasInit) {
                interceptorCallback.onInterrupt(new HandlerException("Interceptors initialization takes too much time."));
            } else {
                LogisticsCenter.executor.execute(new Runnable() {
                    public void run() {
                        CancelableCountDownLatch cancelableCountDownLatch = new CancelableCountDownLatch(Warehouse.interceptors.size());
                        try {
                            InterceptorServiceImpl._execute(0, cancelableCountDownLatch, postcard);
                            cancelableCountDownLatch.await((long) postcard.getTimeout(), TimeUnit.SECONDS);
                            if (cancelableCountDownLatch.getCount() > 0) {
                                interceptorCallback.onInterrupt(new HandlerException("The interceptor processing timed out."));
                            } else if (postcard.getTag() != null) {
                                interceptorCallback.onInterrupt((Throwable) postcard.getTag());
                            } else {
                                interceptorCallback.onContinue(postcard);
                            }
                        } catch (Exception e) {
                            interceptorCallback.onInterrupt(e);
                        }
                    }
                });
            }
        } else {
            interceptorCallback.onContinue(postcard);
        }
    }

    /* access modifiers changed from: private */
    public static void _execute(final int i, final CancelableCountDownLatch cancelableCountDownLatch, final Postcard postcard) {
        if (i < Warehouse.interceptors.size()) {
            Warehouse.interceptors.get(i).process(postcard, new InterceptorCallback() {
                public void onContinue(Postcard postcard) {
                    cancelableCountDownLatch.countDown();
                    InterceptorServiceImpl._execute(i + 1, cancelableCountDownLatch, postcard);
                }

                public void onInterrupt(Throwable th) {
                    Postcard postcard = postcard;
                    if (th == null) {
                        th = new HandlerException("No message.");
                    }
                    postcard.setTag(th);
                    cancelableCountDownLatch.cancel();
                }
            });
        }
    }

    public void init(final Context context) {
        LogisticsCenter.executor.execute(new Runnable() {
            public void run() {
                if (MapUtils.isNotEmpty(Warehouse.interceptorsIndex)) {
                    for (Map.Entry<Integer, Class<? extends IInterceptor>> value : Warehouse.interceptorsIndex.entrySet()) {
                        Class cls = (Class) value.getValue();
                        try {
                            IInterceptor iInterceptor = (IInterceptor) cls.getConstructor((Class[]) null).newInstance((Object[]) null);
                            iInterceptor.init(context);
                            Warehouse.interceptors.add(iInterceptor);
                        } catch (Exception e) {
                            throw new HandlerException("ARouter::ARouter init interceptor error! name = [" + cls.getName() + "], reason = [" + e.getMessage() + "]");
                        }
                    }
                    boolean unused = InterceptorServiceImpl.interceptorHasInit = true;
                    ARouter.logger.info("ARouter::", "ARouter interceptors init over.");
                    synchronized (InterceptorServiceImpl.interceptorInitLock) {
                        InterceptorServiceImpl.interceptorInitLock.notifyAll();
                    }
                }
            }
        });
    }

    private static void checkInterceptorsInitStatus() {
        synchronized (interceptorInitLock) {
            while (!interceptorHasInit) {
                try {
                    interceptorInitLock.wait(WorkRequest.MIN_BACKOFF_MILLIS);
                } catch (InterruptedException e) {
                    throw new HandlerException("ARouter::Interceptor init cost too much time error! reason = [" + e.getMessage() + "]");
                }
            }
        }
    }
}
