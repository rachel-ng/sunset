package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.internal.ViewModelProviders;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u001d\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ1\u0010\f\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/lifecycle/viewmodel/ViewModelProviderImpl;", "", "owner", "Landroidx/lifecycle/ViewModelStoreOwner;", "factory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "extras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "(Landroidx/lifecycle/ViewModelStoreOwner;Landroidx/lifecycle/ViewModelProvider$Factory;Landroidx/lifecycle/viewmodel/CreationExtras;)V", "store", "Landroidx/lifecycle/ViewModelStore;", "(Landroidx/lifecycle/ViewModelStore;Landroidx/lifecycle/ViewModelProvider$Factory;Landroidx/lifecycle/viewmodel/CreationExtras;)V", "getViewModel", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Lkotlin/reflect/KClass;", "key", "", "getViewModel$lifecycle_viewmodel_release", "(Lkotlin/reflect/KClass;Ljava/lang/String;)Landroidx/lifecycle/ViewModel;", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ViewModelProviderImpl.kt */
public final class ViewModelProviderImpl {
    private final CreationExtras extras;
    private final ViewModelProvider.Factory factory;
    private final ViewModelStore store;

    public ViewModelProviderImpl(ViewModelStore viewModelStore, ViewModelProvider.Factory factory2, CreationExtras creationExtras) {
        Intrinsics.checkNotNullParameter(viewModelStore, "store");
        Intrinsics.checkNotNullParameter(factory2, "factory");
        Intrinsics.checkNotNullParameter(creationExtras, "extras");
        this.store = viewModelStore;
        this.factory = factory2;
        this.extras = creationExtras;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ViewModelProviderImpl(ViewModelStoreOwner viewModelStoreOwner, ViewModelProvider.Factory factory2, CreationExtras creationExtras) {
        this(viewModelStoreOwner.getViewModelStore(), factory2, creationExtras);
        Intrinsics.checkNotNullParameter(viewModelStoreOwner, "owner");
        Intrinsics.checkNotNullParameter(factory2, "factory");
        Intrinsics.checkNotNullParameter(creationExtras, "extras");
    }

    public static /* synthetic */ ViewModel getViewModel$lifecycle_viewmodel_release$default(ViewModelProviderImpl viewModelProviderImpl, KClass kClass, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = ViewModelProviders.INSTANCE.getDefaultKey$lifecycle_viewmodel_release(kClass);
        }
        return viewModelProviderImpl.getViewModel$lifecycle_viewmodel_release(kClass, str);
    }

    public final <T extends ViewModel> T getViewModel$lifecycle_viewmodel_release(KClass<T> kClass, String str) {
        Intrinsics.checkNotNullParameter(kClass, "modelClass");
        Intrinsics.checkNotNullParameter(str, "key");
        T t = this.store.get(str);
        if (kClass.isInstance(t)) {
            ViewModelProvider.Factory factory2 = this.factory;
            if (factory2 instanceof ViewModelProvider.OnRequeryFactory) {
                Intrinsics.checkNotNull(t);
                ((ViewModelProvider.OnRequeryFactory) factory2).onRequery(t);
            }
            Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of androidx.lifecycle.viewmodel.ViewModelProviderImpl.getViewModel");
            return t;
        }
        MutableCreationExtras mutableCreationExtras = new MutableCreationExtras(this.extras);
        mutableCreationExtras.set(ViewModelProviders.ViewModelKey.INSTANCE, str);
        T createViewModel = ViewModelProviderImpl_androidKt.createViewModel(this.factory, kClass, mutableCreationExtras);
        this.store.put(str, createViewModel);
        return createViewModel;
    }
}
