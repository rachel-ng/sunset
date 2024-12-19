package com.google.firebase.crashlytics.ktx;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\nH\u0007J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000bH\u0007J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\fH\u0007J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\rH\u0007J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/google/firebase/crashlytics/ktx/KeyValueBuilder;", "", "crashlytics", "Lcom/google/firebase/crashlytics/FirebaseCrashlytics;", "(Lcom/google/firebase/crashlytics/FirebaseCrashlytics;)V", "key", "", "", "value", "", "", "", "", "", "com.google.firebase-firebase-crashlytics"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(message = "Use `com.google.firebase.crashlytics.KeyValueBuilder` from the main module.")
/* compiled from: KeyValueBuilder.kt */
public final class KeyValueBuilder {
    private final FirebaseCrashlytics crashlytics;

    public KeyValueBuilder(FirebaseCrashlytics firebaseCrashlytics) {
        Intrinsics.checkNotNullParameter(firebaseCrashlytics, "crashlytics");
        this.crashlytics = firebaseCrashlytics;
    }

    @Deprecated(message = "Use `com.google.firebase.crashlytics.KeyValueBuilder.key(key, value)` from the main module.", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public final void key(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.crashlytics.setCustomKey(str, z);
    }

    @Deprecated(message = "Use `com.google.firebase.crashlytics.KeyValueBuilder.key(key, value)` from the main module.", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public final void key(String str, double d) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.crashlytics.setCustomKey(str, d);
    }

    @Deprecated(message = "Use `com.google.firebase.crashlytics.KeyValueBuilder.key(key, value)` from the main module.", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public final void key(String str, float f) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.crashlytics.setCustomKey(str, f);
    }

    @Deprecated(message = "Use `com.google.firebase.crashlytics.KeyValueBuilder.key(key, value)` from the main module.", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public final void key(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.crashlytics.setCustomKey(str, i);
    }

    @Deprecated(message = "Use `com.google.firebase.crashlytics.KeyValueBuilder.key(key, value)` from the main module.", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public final void key(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.crashlytics.setCustomKey(str, j);
    }

    @Deprecated(message = "Use `com.google.firebase.crashlytics.KeyValueBuilder.key(key, value)` from the main module.", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public final void key(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "value");
        this.crashlytics.setCustomKey(str, str2);
    }
}
