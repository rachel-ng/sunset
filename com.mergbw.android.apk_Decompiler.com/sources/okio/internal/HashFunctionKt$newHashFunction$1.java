package okio.internal;

import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00050\u0005H\u0016J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016R\u0016\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"okio/internal/HashFunctionKt$newHashFunction$1", "Lokio/internal/HashFunction;", "digest", "Ljava/security/MessageDigest;", "kotlin.jvm.PlatformType", "", "update", "", "input", "offset", "", "byteCount", "okio"}, k = 1, mv = {1, 4, 0})
/* compiled from: HashFunction.kt */
public final class HashFunctionKt$newHashFunction$1 implements HashFunction {
    final /* synthetic */ String $algorithm;
    private final MessageDigest digest;

    HashFunctionKt$newHashFunction$1(String str) {
        this.$algorithm = str;
        this.digest = MessageDigest.getInstance(str);
    }

    public void update(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "input");
        this.digest.update(bArr, i, i2);
    }

    public byte[] digest() {
        return this.digest.digest();
    }
}
