package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n"}, d2 = {"<anonymous>", "", "T", "it", ""}, k = 3, mv = {1, 5, 1}, xi = 48)
/* compiled from: SingleProcessDataStore.kt */
final class SingleProcessDataStore$actor$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ SingleProcessDataStore<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SingleProcessDataStore$actor$1(SingleProcessDataStore<T> singleProcessDataStore) {
        super(1);
        this.this$0 = singleProcessDataStore;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        if (th != null) {
            this.this$0.downstreamFlow.setValue(new Final(th));
        }
        Object activeFilesLock$datastore_core = SingleProcessDataStore.Companion.getActiveFilesLock$datastore_core();
        SingleProcessDataStore<T> singleProcessDataStore = this.this$0;
        synchronized (activeFilesLock$datastore_core) {
            SingleProcessDataStore.Companion.getActiveFiles$datastore_core().remove(singleProcessDataStore.getFile().getAbsolutePath());
            Unit unit = Unit.INSTANCE;
        }
    }
}
