package androidx.lifecycle;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "run", "kotlinx/coroutines/RunnableKt$Runnable$1"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Runnable.kt */
public final class WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$lambda$2$$inlined$Runnable$1 implements Runnable {
    final /* synthetic */ WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1 $observer$inlined;
    final /* synthetic */ Lifecycle $this_suspendWithStateAtLeastUnchecked$inlined;

    public WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$lambda$2$$inlined$Runnable$1(Lifecycle lifecycle, WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1 withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1) {
        this.$this_suspendWithStateAtLeastUnchecked$inlined = lifecycle;
        this.$observer$inlined = withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1;
    }

    public final void run() {
        this.$this_suspendWithStateAtLeastUnchecked$inlined.addObserver(this.$observer$inlined);
    }
}
