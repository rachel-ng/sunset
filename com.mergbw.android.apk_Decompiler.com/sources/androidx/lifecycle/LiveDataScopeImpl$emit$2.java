package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.lifecycle.LiveDataScopeImpl$emit$2", f = "CoroutineLiveData.kt", i = {}, l = {99}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CoroutineLiveData.kt */
final class LiveDataScopeImpl$emit$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ T $value;
    int label;
    final /* synthetic */ LiveDataScopeImpl<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LiveDataScopeImpl$emit$2(LiveDataScopeImpl<T> liveDataScopeImpl, T t, Continuation<? super LiveDataScopeImpl$emit$2> continuation) {
        super(2, continuation);
        this.this$0 = liveDataScopeImpl;
        this.$value = t;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LiveDataScopeImpl$emit$2(this.this$0, this.$value, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LiveDataScopeImpl$emit$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.this$0.getTarget$lifecycle_livedata_release().clearSource$lifecycle_livedata_release(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.getTarget$lifecycle_livedata_release().setValue(this.$value);
        return Unit.INSTANCE;
    }
}
