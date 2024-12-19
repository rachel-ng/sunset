package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "X", "Y", "value", "invoke", "(Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Transformations.kt */
final class Transformations$switchMap$1 extends Lambda implements Function1<X, Unit> {
    final /* synthetic */ Ref.ObjectRef<LiveData<Y>> $liveData;
    final /* synthetic */ MediatorLiveData<Y> $result;
    final /* synthetic */ Function1<X, LiveData<Y>> $transform;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Transformations$switchMap$1(Function1<X, LiveData<Y>> function1, Ref.ObjectRef<LiveData<Y>> objectRef, MediatorLiveData<Y> mediatorLiveData) {
        super(1);
        this.$transform = function1;
        this.$liveData = objectRef;
        this.$result = mediatorLiveData;
    }

    public final void invoke(X x) {
        T t = (LiveData) this.$transform.invoke(x);
        if (this.$liveData.element != t) {
            if (this.$liveData.element != null) {
                MediatorLiveData<Y> mediatorLiveData = this.$result;
                T t2 = this.$liveData.element;
                Intrinsics.checkNotNull(t2);
                mediatorLiveData.removeSource((LiveData) t2);
            }
            this.$liveData.element = t;
            if (this.$liveData.element != null) {
                MediatorLiveData<Y> mediatorLiveData2 = this.$result;
                T t3 = this.$liveData.element;
                Intrinsics.checkNotNull(t3);
                final MediatorLiveData<Y> mediatorLiveData3 = this.$result;
                mediatorLiveData2.addSource((LiveData) t3, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1<Y, Unit>() {
                    public final void invoke(Y y) {
                        mediatorLiveData3.setValue(y);
                    }
                }));
            }
        }
    }
}
