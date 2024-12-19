package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@"}, d2 = {"<anonymous>", "Landroidx/lifecycle/EmittedSource;", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.lifecycle.CoroutineLiveDataKt$addDisposableSource$2", f = "CoroutineLiveData.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CoroutineLiveData.kt */
final class CoroutineLiveDataKt$addDisposableSource$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EmittedSource>, Object> {
    final /* synthetic */ LiveData<T> $source;
    final /* synthetic */ MediatorLiveData<T> $this_addDisposableSource;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CoroutineLiveDataKt$addDisposableSource$2(MediatorLiveData<T> mediatorLiveData, LiveData<T> liveData, Continuation<? super CoroutineLiveDataKt$addDisposableSource$2> continuation) {
        super(2, continuation);
        this.$this_addDisposableSource = mediatorLiveData;
        this.$source = liveData;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CoroutineLiveDataKt$addDisposableSource$2(this.$this_addDisposableSource, this.$source, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super EmittedSource> continuation) {
        return ((CoroutineLiveDataKt$addDisposableSource$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediatorLiveData<T> mediatorLiveData = this.$this_addDisposableSource;
            LiveData<T> liveData = this.$source;
            final MediatorLiveData<T> mediatorLiveData2 = this.$this_addDisposableSource;
            mediatorLiveData.addSource(liveData, new CoroutineLiveDataKt$sam$androidx_lifecycle_Observer$0(new Function1<T, Unit>() {
                public final void invoke(T t) {
                    mediatorLiveData2.setValue(t);
                }
            }));
            return new EmittedSource(this.$source, this.$this_addDisposableSource);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
