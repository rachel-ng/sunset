package androidx.datastore.preferences.core;

import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Ljava/io/File;"}, k = 3, mv = {1, 5, 1}, xi = 48)
/* compiled from: PreferenceDataStoreFactory.kt */
final class PreferenceDataStoreFactory$create$delegate$1 extends Lambda implements Function0<File> {
    final /* synthetic */ Function0<File> $produceFile;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferenceDataStoreFactory$create$delegate$1(Function0<? extends File> function0) {
        super(0);
        this.$produceFile = function0;
    }

    public final File invoke() {
        File invoke = this.$produceFile.invoke();
        if (Intrinsics.areEqual((Object) FilesKt.getExtension(invoke), (Object) PreferencesSerializer.INSTANCE.getFileExtension())) {
            return invoke;
        }
        throw new IllegalStateException(("File extension for file: " + invoke + " does not match required extension for Preferences file: " + PreferencesSerializer.INSTANCE.getFileExtension()).toString());
    }
}
