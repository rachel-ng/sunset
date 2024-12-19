package io.reactivex.internal.operators.parallel;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class ParallelSortedJoin<T> extends Flowable<T> {
    final Comparator<? super T> comparator;
    final ParallelFlowable<List<T>> source;

    public ParallelSortedJoin(ParallelFlowable<List<T>> parallelFlowable, Comparator<? super T> comparator2) {
        this.source = parallelFlowable;
        this.comparator = comparator2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        SortedJoinSubscription sortedJoinSubscription = new SortedJoinSubscription(subscriber, this.source.parallelism(), this.comparator);
        subscriber.onSubscribe(sortedJoinSubscription);
        this.source.subscribe(sortedJoinSubscription.subscribers);
    }

    static final class SortedJoinSubscription<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 3481980673745556697L;
        volatile boolean cancelled;
        final Comparator<? super T> comparator;
        final Subscriber<? super T> downstream;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final int[] indexes;
        final List<T>[] lists;
        final AtomicInteger remaining = new AtomicInteger();
        final AtomicLong requested = new AtomicLong();
        final SortedJoinInnerSubscriber<T>[] subscribers;

        SortedJoinSubscription(Subscriber<? super T> subscriber, int i, Comparator<? super T> comparator2) {
            this.downstream = subscriber;
            this.comparator = comparator2;
            SortedJoinInnerSubscriber<T>[] sortedJoinInnerSubscriberArr = new SortedJoinInnerSubscriber[i];
            for (int i2 = 0; i2 < i; i2++) {
                sortedJoinInnerSubscriberArr[i2] = new SortedJoinInnerSubscriber<>(this, i2);
            }
            this.subscribers = sortedJoinInnerSubscriberArr;
            this.lists = new List[i];
            this.indexes = new int[i];
            this.remaining.lazySet(i);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                if (this.remaining.get() == 0) {
                    drain();
                }
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    Arrays.fill(this.lists, (Object) null);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void cancelAll() {
            for (SortedJoinInnerSubscriber<T> cancel : this.subscribers) {
                cancel.cancel();
            }
        }

        /* access modifiers changed from: package-private */
        public void innerNext(List<T> list, int i) {
            this.lists[i] = list;
            if (this.remaining.decrementAndGet() == 0) {
                drain();
            }
        }

        /* access modifiers changed from: package-private */
        public void innerError(Throwable th) {
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.error, (Object) null, th)) {
                drain();
            } else if (th != this.error.get()) {
                RxJavaPlugins.onError(th);
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x009e, code lost:
            if (r13 != 0) goto L_0x00d6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a2, code lost:
            if (r1.cancelled == false) goto L_0x00a9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a4, code lost:
            java.util.Arrays.fill(r3, (java.lang.Object) null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a8, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a9, code lost:
            r10 = r1.error.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b2, code lost:
            if (r10 == null) goto L_0x00be;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b4, code lost:
            cancelAll();
            java.util.Arrays.fill(r3, (java.lang.Object) null);
            r2.onError(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x00bd, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x00be, code lost:
            if (r14 >= r4) goto L_0x00ce;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c8, code lost:
            if (r0[r14] == r3[r14].size()) goto L_0x00cb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x00cb, code lost:
            r14 = r14 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ce, code lost:
            java.util.Arrays.fill(r3, (java.lang.Object) null);
            r2.onComplete();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x00d5, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x00da, code lost:
            if (r11 == 0) goto L_0x00eb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e3, code lost:
            if (r7 == Long.MAX_VALUE) goto L_0x00eb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e5, code lost:
            r1.requested.addAndGet(-r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x00eb, code lost:
            r5 = get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x00ef, code lost:
            if (r5 != r6) goto L_0x00f9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x00f1, code lost:
            r5 = addAndGet(-r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x00f6, code lost:
            if (r5 != 0) goto L_0x00f9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x00f8, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drain() {
            /*
                r16 = this;
                r1 = r16
                int r0 = r16.getAndIncrement()
                if (r0 == 0) goto L_0x0009
                return
            L_0x0009:
                org.reactivestreams.Subscriber<? super T> r2 = r1.downstream
                java.util.List<T>[] r3 = r1.lists
                int[] r0 = r1.indexes
                int r4 = r0.length
                r6 = 1
            L_0x0011:
                java.util.concurrent.atomic.AtomicLong r7 = r1.requested
                long r7 = r7.get()
                r11 = 0
            L_0x0019:
                int r13 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
                r14 = 0
                r15 = 0
                if (r13 == 0) goto L_0x009d
                boolean r13 = r1.cancelled
                if (r13 == 0) goto L_0x0027
                java.util.Arrays.fill(r3, r15)
                return
            L_0x0027:
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r13 = r1.error
                java.lang.Object r13 = r13.get()
                java.lang.Throwable r13 = (java.lang.Throwable) r13
                if (r13 == 0) goto L_0x003b
                r16.cancelAll()
                java.util.Arrays.fill(r3, r15)
                r2.onError(r13)
                return
            L_0x003b:
                r13 = -1
                r9 = r15
            L_0x003d:
                if (r14 >= r4) goto L_0x0085
                r10 = r3[r14]
                r5 = r0[r14]
                int r15 = r10.size()
                if (r15 == r5) goto L_0x0081
                if (r9 != 0) goto L_0x0051
                java.lang.Object r9 = r10.get(r5)
            L_0x004f:
                r13 = r14
                goto L_0x0081
            L_0x0051:
                java.lang.Object r5 = r10.get(r5)
                java.util.Comparator<? super T> r10 = r1.comparator     // Catch:{ all -> 0x005f }
                int r10 = r10.compare(r9, r5)     // Catch:{ all -> 0x005f }
                if (r10 <= 0) goto L_0x0081
                r9 = r5
                goto L_0x004f
            L_0x005f:
                r0 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r0)
                r16.cancelAll()
                r4 = 0
                java.util.Arrays.fill(r3, r4)
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r3 = r1.error
                boolean r3 = androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(r3, r4, r0)
                if (r3 != 0) goto L_0x0075
                io.reactivex.plugins.RxJavaPlugins.onError(r0)
            L_0x0075:
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r0 = r1.error
                java.lang.Object r0 = r0.get()
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                r2.onError(r0)
                return
            L_0x0081:
                int r14 = r14 + 1
                r15 = 0
                goto L_0x003d
            L_0x0085:
                if (r9 != 0) goto L_0x008f
                r5 = 0
                java.util.Arrays.fill(r3, r5)
                r2.onComplete()
                return
            L_0x008f:
                r2.onNext(r9)
                r5 = r0[r13]
                r9 = 1
                int r5 = r5 + r9
                r0[r13] = r5
                r13 = 1
                long r11 = r11 + r13
                goto L_0x0019
            L_0x009d:
                r9 = 1
                if (r13 != 0) goto L_0x00d6
                boolean r5 = r1.cancelled
                if (r5 == 0) goto L_0x00a9
                r5 = 0
                java.util.Arrays.fill(r3, r5)
                return
            L_0x00a9:
                r5 = 0
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r10 = r1.error
                java.lang.Object r10 = r10.get()
                java.lang.Throwable r10 = (java.lang.Throwable) r10
                if (r10 == 0) goto L_0x00be
                r16.cancelAll()
                java.util.Arrays.fill(r3, r5)
                r2.onError(r10)
                return
            L_0x00be:
                if (r14 >= r4) goto L_0x00ce
                r5 = r0[r14]
                r10 = r3[r14]
                int r10 = r10.size()
                if (r5 == r10) goto L_0x00cb
                goto L_0x00d6
            L_0x00cb:
                int r14 = r14 + 1
                goto L_0x00be
            L_0x00ce:
                r5 = 0
                java.util.Arrays.fill(r3, r5)
                r2.onComplete()
                return
            L_0x00d6:
                r13 = 0
                int r5 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
                if (r5 == 0) goto L_0x00eb
                r13 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r5 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
                if (r5 == 0) goto L_0x00eb
                java.util.concurrent.atomic.AtomicLong r5 = r1.requested
                long r7 = -r11
                r5.addAndGet(r7)
            L_0x00eb:
                int r5 = r16.get()
                if (r5 != r6) goto L_0x00f9
                int r5 = -r6
                int r5 = r1.addAndGet(r5)
                if (r5 != 0) goto L_0x00f9
                return
            L_0x00f9:
                r6 = r5
                goto L_0x0011
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelSortedJoin.SortedJoinSubscription.drain():void");
        }
    }

    static final class SortedJoinInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<List<T>> {
        private static final long serialVersionUID = 6751017204873808094L;
        final int index;
        final SortedJoinSubscription<T> parent;

        public void onComplete() {
        }

        SortedJoinInnerSubscriber(SortedJoinSubscription<T> sortedJoinSubscription, int i) {
            this.parent = sortedJoinSubscription;
            this.index = i;
        }

        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.setOnce(this, subscription, Long.MAX_VALUE);
        }

        public void onNext(List<T> list) {
            this.parent.innerNext(list, this.index);
        }

        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        /* access modifiers changed from: package-private */
        public void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }
}
