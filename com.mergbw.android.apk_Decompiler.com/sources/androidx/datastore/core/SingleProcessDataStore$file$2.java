package androidx.datastore.core;

import java.io.File;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n"}, d2 = {"<anonymous>", "Ljava/io/File;", "T"}, k = 3, mv = {1, 5, 1}, xi = 48)
/* compiled from: SingleProcessDataStore.kt */
final class SingleProcessDataStore$file$2 extends Lambda implements Function0<File> {
    final /* synthetic */ SingleProcessDataStore<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SingleProcessDataStore$file$2(SingleProcessDataStore<T> singleProcessDataStore) {
        super(0);
        this.this$0 = singleProcessDataStore;
    }

    public final File invoke() {
        File file = (File) this.this$0.produceFile.invoke();
        String absolutePath = file.getAbsolutePath();
        synchronized (SingleProcessDataStore.Companion.getActiveFilesLock$datastore_core()) {
            if (!SingleProcessDataStore.Companion.getActiveFiles$datastore_core().contains(absolutePath)) {
                Set<String> activeFiles$datastore_core = SingleProcessDataStore.Companion.getActiveFiles$datastore_core();
                Intrinsics.checkNotNullExpressionValue(absolutePath, "it");
                activeFiles$datastore_core.add(absolutePath);
            } else {
                throw new IllegalStateException(("There are multiple DataStores active for the same file: " + file + ". You should either maintain your DataStore as a singleton or confirm that there is no two DataStore's active on the same file (by confirming that the scope is cancelled).").toString());
            }
        }
        return file;
    }
}
