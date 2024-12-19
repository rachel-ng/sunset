package io.reactivex.internal.operators.maybe;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeCache<T> extends Maybe<T> implements MaybeObserver<T> {
    static final CacheDisposable[] EMPTY = new CacheDisposable[0];
    static final CacheDisposable[] TERMINATED = new CacheDisposable[0];
    Throwable error;
    final AtomicReference<CacheDisposable<T>[]> observers = new AtomicReference<>(EMPTY);
    final AtomicReference<MaybeSource<T>> source;
    T value;

    public void onSubscribe(Disposable disposable) {
    }

    public MaybeCache(MaybeSource<T> maybeSource) {
        this.source = new AtomicReference<>(maybeSource);
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        CacheDisposable cacheDisposable = new CacheDisposable(maybeObserver, this);
        maybeObserver.onSubscribe(cacheDisposable);
        if (add(cacheDisposable)) {
            if (cacheDisposable.isDisposed()) {
                remove(cacheDisposable);
                return;
            }
            MaybeSource andSet = this.source.getAndSet((Object) null);
            if (andSet != null) {
                andSet.subscribe(this);
            }
        } else if (!cacheDisposable.isDisposed()) {
            Throwable th = this.error;
            if (th != null) {
                maybeObserver.onError(th);
                return;
            }
            T t = this.value;
            if (t != null) {
                maybeObserver.onSuccess(t);
            } else {
                maybeObserver.onComplete();
            }
        }
    }

    public void onSuccess(T t) {
        this.value = t;
        for (CacheDisposable cacheDisposable : (CacheDisposable[]) this.observers.getAndSet(TERMINATED)) {
            if (!cacheDisposable.isDisposed()) {
                cacheDisposable.downstream.onSuccess(t);
            }
        }
    }

    public void onError(Throwable th) {
        this.error = th;
        for (CacheDisposable cacheDisposable : (CacheDisposable[]) this.observers.getAndSet(TERMINATED)) {
            if (!cacheDisposable.isDisposed()) {
                cacheDisposable.downstream.onError(th);
            }
        }
    }

    public void onComplete() {
        for (CacheDisposable cacheDisposable : (CacheDisposable[]) this.observers.getAndSet(TERMINATED)) {
            if (!cacheDisposable.isDisposed()) {
                cacheDisposable.downstream.onComplete();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean add(CacheDisposable<T> cacheDisposable) {
        CacheDisposable[] cacheDisposableArr;
        CacheDisposable[] cacheDisposableArr2;
        do {
            cacheDisposableArr = (CacheDisposable[]) this.observers.get();
            if (cacheDisposableArr == TERMINATED) {
                return false;
            }
            int length = cacheDisposableArr.length;
            cacheDisposableArr2 = new CacheDisposable[(length + 1)];
            System.arraycopy(cacheDisposableArr, 0, cacheDisposableArr2, 0, length);
            cacheDisposableArr2[length] = cacheDisposable;
        } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.observers, cacheDisposableArr, cacheDisposableArr2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void remove(CacheDisposable<T> cacheDisposable) {
        CacheDisposable<T>[] cacheDisposableArr;
        CacheDisposable[] cacheDisposableArr2;
        do {
            cacheDisposableArr = (CacheDisposable[]) this.observers.get();
            int length = cacheDisposableArr.length;
            if (length != 0) {
                int i = 0;
                while (true) {
                    if (i >= length) {
                        i = -1;
                        break;
                    } else if (cacheDisposableArr[i] == cacheDisposable) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        cacheDisposableArr2 = EMPTY;
                    } else {
                        CacheDisposable[] cacheDisposableArr3 = new CacheDisposable[(length - 1)];
                        System.arraycopy(cacheDisposableArr, 0, cacheDisposableArr3, 0, i);
                        System.arraycopy(cacheDisposableArr, i + 1, cacheDisposableArr3, i, (length - i) - 1);
                        cacheDisposableArr2 = cacheDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.observers, cacheDisposableArr, cacheDisposableArr2));
    }

    static final class CacheDisposable<T> extends AtomicReference<MaybeCache<T>> implements Disposable {
        private static final long serialVersionUID = -5791853038359966195L;
        final MaybeObserver<? super T> downstream;

        CacheDisposable(MaybeObserver<? super T> maybeObserver, MaybeCache<T> maybeCache) {
            super(maybeCache);
            this.downstream = maybeObserver;
        }

        public void dispose() {
            MaybeCache maybeCache = (MaybeCache) getAndSet((Object) null);
            if (maybeCache != null) {
                maybeCache.remove(this);
            }
        }

        public boolean isDisposed() {
            return get() == null;
        }
    }
}
