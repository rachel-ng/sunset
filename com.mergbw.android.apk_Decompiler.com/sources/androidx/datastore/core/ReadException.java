package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/datastore/core/ReadException;", "T", "Landroidx/datastore/core/State;", "readException", "", "(Ljava/lang/Throwable;)V", "getReadException", "()Ljava/lang/Throwable;", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* compiled from: SingleProcessDataStore.kt */
final class ReadException<T> extends State<T> {
    private final Throwable readException;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReadException(Throwable th) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(th, "readException");
        this.readException = th;
    }

    public final Throwable getReadException() {
        return this.readException;
    }
}
