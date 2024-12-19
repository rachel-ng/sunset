package androidx.lifecycle.viewmodel.internal;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u001b\b\u0016\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b¢\u0006\u0002\u0010\tB#\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b¢\u0006\u0002\u0010\nJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\bJ\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\bJ\b\u0010\u0017\u001a\u00020\u0014H\u0007J\u0012\u0010\u0018\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\bH\u0002J\u001f\u0010\u0019\u001a\u0004\u0018\u0001H\u001a\"\b\b\u0000\u0010\u001a*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0010¢\u0006\u0002\u0010\u001bR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/lifecycle/viewmodel/internal/ViewModelImpl;", "", "()V", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/CoroutineScope;)V", "closeables", "", "Ljava/lang/AutoCloseable;", "([Ljava/lang/AutoCloseable;)V", "(Lkotlinx/coroutines/CoroutineScope;[Ljava/lang/AutoCloseable;)V", "", "isCleared", "", "keyToCloseables", "", "", "lock", "Landroidx/lifecycle/viewmodel/internal/SynchronizedObject;", "addCloseable", "", "closeable", "key", "clear", "closeWithRuntimeException", "getCloseable", "T", "(Ljava/lang/String;)Ljava/lang/AutoCloseable;", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ViewModelImpl.kt */
public final class ViewModelImpl {
    /* access modifiers changed from: private */
    public final Set<AutoCloseable> closeables;
    private volatile boolean isCleared;
    /* access modifiers changed from: private */
    public final Map<String, AutoCloseable> keyToCloseables;
    private final SynchronizedObject lock;

    public ViewModelImpl() {
        this.lock = new SynchronizedObject();
        this.keyToCloseables = new LinkedHashMap();
        this.closeables = new LinkedHashSet();
    }

    public ViewModelImpl(CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(coroutineScope, "viewModelScope");
        this.lock = new SynchronizedObject();
        this.keyToCloseables = new LinkedHashMap();
        this.closeables = new LinkedHashSet();
        addCloseable(CloseableCoroutineScopeKt.VIEW_MODEL_SCOPE_KEY, CloseableCoroutineScopeKt.asCloseable(coroutineScope));
    }

    public ViewModelImpl(AutoCloseable... autoCloseableArr) {
        Intrinsics.checkNotNullParameter(autoCloseableArr, "closeables");
        this.lock = new SynchronizedObject();
        this.keyToCloseables = new LinkedHashMap();
        Set<AutoCloseable> linkedHashSet = new LinkedHashSet<>();
        this.closeables = linkedHashSet;
        CollectionsKt.addAll(linkedHashSet, (T[]) autoCloseableArr);
    }

    public ViewModelImpl(CoroutineScope coroutineScope, AutoCloseable... autoCloseableArr) {
        Intrinsics.checkNotNullParameter(coroutineScope, "viewModelScope");
        Intrinsics.checkNotNullParameter(autoCloseableArr, "closeables");
        this.lock = new SynchronizedObject();
        this.keyToCloseables = new LinkedHashMap();
        Set<AutoCloseable> linkedHashSet = new LinkedHashSet<>();
        this.closeables = linkedHashSet;
        addCloseable(CloseableCoroutineScopeKt.VIEW_MODEL_SCOPE_KEY, CloseableCoroutineScopeKt.asCloseable(coroutineScope));
        CollectionsKt.addAll(linkedHashSet, (T[]) autoCloseableArr);
    }

    public final void clear() {
        if (!this.isCleared) {
            this.isCleared = true;
            synchronized (this.lock) {
                for (AutoCloseable access$closeWithRuntimeException : this.keyToCloseables.values()) {
                    closeWithRuntimeException(access$closeWithRuntimeException);
                }
                for (AutoCloseable access$closeWithRuntimeException2 : this.closeables) {
                    closeWithRuntimeException(access$closeWithRuntimeException2);
                }
                this.closeables.clear();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final void addCloseable(String str, AutoCloseable autoCloseable) {
        AutoCloseable autoCloseable2;
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(autoCloseable, "closeable");
        if (this.isCleared) {
            closeWithRuntimeException(autoCloseable);
            return;
        }
        synchronized (this.lock) {
            autoCloseable2 = (AutoCloseable) this.keyToCloseables.put(str, autoCloseable);
        }
        closeWithRuntimeException(autoCloseable2);
    }

    public final void addCloseable(AutoCloseable autoCloseable) {
        Intrinsics.checkNotNullParameter(autoCloseable, "closeable");
        if (this.isCleared) {
            closeWithRuntimeException(autoCloseable);
            return;
        }
        synchronized (this.lock) {
            this.closeables.add(autoCloseable);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final <T extends AutoCloseable> T getCloseable(String str) {
        T t;
        Intrinsics.checkNotNullParameter(str, "key");
        synchronized (this.lock) {
            t = (AutoCloseable) this.keyToCloseables.get(str);
        }
        return t;
    }

    /* access modifiers changed from: private */
    public final void closeWithRuntimeException(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
