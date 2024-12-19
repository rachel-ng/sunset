package okio.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"newHashFunction", "Lokio/internal/HashFunction;", "algorithm", "", "okio"}, k = 2, mv = {1, 4, 0})
/* compiled from: HashFunction.kt */
public final class HashFunctionKt {
    public static final HashFunction newHashFunction(String str) {
        Intrinsics.checkNotNullParameter(str, "algorithm");
        return new HashFunctionKt$newHashFunction$1(str);
    }
}
