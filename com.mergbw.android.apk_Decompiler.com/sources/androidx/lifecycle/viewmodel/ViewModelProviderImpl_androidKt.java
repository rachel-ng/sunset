package androidx.lifecycle.viewmodel;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a5\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"createViewModel", "VM", "Landroidx/lifecycle/ViewModel;", "factory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "modelClass", "Lkotlin/reflect/KClass;", "extras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "(Landroidx/lifecycle/ViewModelProvider$Factory;Lkotlin/reflect/KClass;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModel;", "lifecycle-viewmodel_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ViewModelProviderImpl.android.kt */
public final class ViewModelProviderImpl_androidKt {
    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        return r1.create(kotlin.jvm.JvmClassMappingKt.getJavaClass(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        return r1.create(kotlin.jvm.JvmClassMappingKt.getJavaClass(r2), r3);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <VM extends androidx.lifecycle.ViewModel> VM createViewModel(androidx.lifecycle.ViewModelProvider.Factory r1, kotlin.reflect.KClass<VM> r2, androidx.lifecycle.viewmodel.CreationExtras r3) {
        /*
            java.lang.String r0 = "factory"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "modelClass"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "extras"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            androidx.lifecycle.ViewModel r1 = r1.create(r2, (androidx.lifecycle.viewmodel.CreationExtras) r3)     // Catch:{ AbstractMethodError -> 0x0014 }
            goto L_0x0025
        L_0x0014:
            java.lang.Class r0 = kotlin.jvm.JvmClassMappingKt.getJavaClass(r2)     // Catch:{ AbstractMethodError -> 0x001d }
            androidx.lifecycle.ViewModel r1 = r1.create(r0, (androidx.lifecycle.viewmodel.CreationExtras) r3)     // Catch:{ AbstractMethodError -> 0x001d }
            goto L_0x0025
        L_0x001d:
            java.lang.Class r2 = kotlin.jvm.JvmClassMappingKt.getJavaClass(r2)
            androidx.lifecycle.ViewModel r1 = r1.create(r2)
        L_0x0025:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.viewmodel.ViewModelProviderImpl_androidKt.createViewModel(androidx.lifecycle.ViewModelProvider$Factory, kotlin.reflect.KClass, androidx.lifecycle.viewmodel.CreationExtras):androidx.lifecycle.ViewModel");
    }
}
