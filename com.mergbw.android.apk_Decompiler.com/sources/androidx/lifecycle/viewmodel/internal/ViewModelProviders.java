package androidx.lifecycle.viewmodel.internal;

import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.InitializerViewModelFactory;
import androidx.lifecycle.viewmodel.ViewModelInitializer;
import java.util.Arrays;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\"B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J+\u0010\u0005\u001a\u00020\u00062\u001a\u0010\u0007\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\t0\b\"\u0006\u0012\u0002\b\u00030\tH\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0005\u001a\u00020\u00062\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\fH\u0000¢\u0006\u0002\b\nJK\u0010\r\u001a\u0002H\u000e\"\b\b\u0000\u0010\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u001a\u0010\u0007\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\t0\b\"\u0006\u0012\u0002\b\u00030\tH\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019J\u0015\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u001bJ%\u0010\u001c\u001a\u00020\u0004\"\b\b\u0000\u0010\u001d*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u0011H\u0000¢\u0006\u0002\b\u001eJ\u0019\u0010\u001f\u001a\u0002H\u000e\"\b\b\u0000\u0010\u000e*\u00020\u000fH\u0000¢\u0006\u0004\b \u0010!R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/lifecycle/viewmodel/internal/ViewModelProviders;", "", "()V", "VIEW_MODEL_PROVIDER_DEFAULT_KEY", "", "createInitializerFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "initializers", "", "Landroidx/lifecycle/viewmodel/ViewModelInitializer;", "createInitializerFactory$lifecycle_viewmodel_release", "([Landroidx/lifecycle/viewmodel/ViewModelInitializer;)Landroidx/lifecycle/ViewModelProvider$Factory;", "", "createViewModelFromInitializers", "VM", "Landroidx/lifecycle/ViewModel;", "modelClass", "Lkotlin/reflect/KClass;", "extras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "createViewModelFromInitializers$lifecycle_viewmodel_release", "(Lkotlin/reflect/KClass;Landroidx/lifecycle/viewmodel/CreationExtras;[Landroidx/lifecycle/viewmodel/ViewModelInitializer;)Landroidx/lifecycle/ViewModel;", "getDefaultCreationExtras", "owner", "Landroidx/lifecycle/ViewModelStoreOwner;", "getDefaultCreationExtras$lifecycle_viewmodel_release", "getDefaultFactory", "getDefaultFactory$lifecycle_viewmodel_release", "getDefaultKey", "T", "getDefaultKey$lifecycle_viewmodel_release", "unsupportedCreateViewModel", "unsupportedCreateViewModel$lifecycle_viewmodel_release", "()Landroidx/lifecycle/ViewModel;", "ViewModelKey", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ViewModelProviders.kt */
public final class ViewModelProviders {
    public static final ViewModelProviders INSTANCE = new ViewModelProviders();
    private static final String VIEW_MODEL_PROVIDER_DEFAULT_KEY = "androidx.lifecycle.ViewModelProvider.DefaultKey";

    private ViewModelProviders() {
    }

    public final <T extends ViewModel> String getDefaultKey$lifecycle_viewmodel_release(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "modelClass");
        String canonicalName = ViewModelProviders_jvmKt.getCanonicalName(kClass);
        if (canonicalName != null) {
            return "androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels".toString());
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/lifecycle/viewmodel/internal/ViewModelProviders$ViewModelKey;", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "", "()V", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ViewModelProviders.kt */
    public static final class ViewModelKey implements CreationExtras.Key<String> {
        public static final ViewModelKey INSTANCE = new ViewModelKey();

        private ViewModelKey() {
        }
    }

    public final <VM extends ViewModel> VM unsupportedCreateViewModel$lifecycle_viewmodel_release() {
        throw new UnsupportedOperationException("`Factory.create(String, CreationExtras)` is not implemented. You may need to override the method and provide a custom implementation. Note that using `Factory.create(String)` is not supported and considered an error.");
    }

    public final ViewModelProvider.Factory createInitializerFactory$lifecycle_viewmodel_release(Collection<? extends ViewModelInitializer<?>> collection) {
        Intrinsics.checkNotNullParameter(collection, "initializers");
        ViewModelInitializer[] viewModelInitializerArr = (ViewModelInitializer[]) collection.toArray(new ViewModelInitializer[0]);
        return new InitializerViewModelFactory((ViewModelInitializer[]) Arrays.copyOf(viewModelInitializerArr, viewModelInitializerArr.length));
    }

    public final ViewModelProvider.Factory createInitializerFactory$lifecycle_viewmodel_release(ViewModelInitializer<?>... viewModelInitializerArr) {
        Intrinsics.checkNotNullParameter(viewModelInitializerArr, "initializers");
        return new InitializerViewModelFactory((ViewModelInitializer[]) Arrays.copyOf(viewModelInitializerArr, viewModelInitializerArr.length));
    }

    public final ViewModelProvider.Factory getDefaultFactory$lifecycle_viewmodel_release(ViewModelStoreOwner viewModelStoreOwner) {
        Intrinsics.checkNotNullParameter(viewModelStoreOwner, "owner");
        if (viewModelStoreOwner instanceof HasDefaultViewModelProviderFactory) {
            return ((HasDefaultViewModelProviderFactory) viewModelStoreOwner).getDefaultViewModelProviderFactory();
        }
        return DefaultViewModelProviderFactory.INSTANCE;
    }

    public final CreationExtras getDefaultCreationExtras$lifecycle_viewmodel_release(ViewModelStoreOwner viewModelStoreOwner) {
        Intrinsics.checkNotNullParameter(viewModelStoreOwner, "owner");
        if (viewModelStoreOwner instanceof HasDefaultViewModelProviderFactory) {
            return ((HasDefaultViewModelProviderFactory) viewModelStoreOwner).getDefaultViewModelCreationExtras();
        }
        return CreationExtras.Empty.INSTANCE;
    }

    public final <VM extends ViewModel> VM createViewModelFromInitializers$lifecycle_viewmodel_release(KClass<VM> kClass, CreationExtras creationExtras, ViewModelInitializer<?>... viewModelInitializerArr) {
        VM vm;
        ViewModelInitializer<?> viewModelInitializer;
        Function1<CreationExtras, ?> initializer$lifecycle_viewmodel_release;
        Intrinsics.checkNotNullParameter(kClass, "modelClass");
        Intrinsics.checkNotNullParameter(creationExtras, "extras");
        Intrinsics.checkNotNullParameter(viewModelInitializerArr, "initializers");
        int length = viewModelInitializerArr.length;
        int i = 0;
        while (true) {
            vm = null;
            if (i >= length) {
                viewModelInitializer = null;
                break;
            }
            viewModelInitializer = viewModelInitializerArr[i];
            if (Intrinsics.areEqual((Object) viewModelInitializer.getClazz$lifecycle_viewmodel_release(), (Object) kClass)) {
                break;
            }
            i++;
        }
        if (!(viewModelInitializer == null || (initializer$lifecycle_viewmodel_release = viewModelInitializer.getInitializer$lifecycle_viewmodel_release()) == null)) {
            vm = (ViewModel) initializer$lifecycle_viewmodel_release.invoke(creationExtras);
        }
        if (vm != null) {
            return vm;
        }
        throw new IllegalArgumentException(("No initializer set for given class " + ViewModelProviders_jvmKt.getCanonicalName(kClass)).toString());
    }
}
