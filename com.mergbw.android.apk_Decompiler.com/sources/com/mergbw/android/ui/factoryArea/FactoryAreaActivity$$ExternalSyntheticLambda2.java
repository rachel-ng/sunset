package com.mergbw.android.ui.factoryArea;

import androidx.lifecycle.Observer;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FactoryAreaActivity$$ExternalSyntheticLambda2 implements Observer {
    public final /* synthetic */ FactoryAreaActivity f$0;

    public /* synthetic */ FactoryAreaActivity$$ExternalSyntheticLambda2(FactoryAreaActivity factoryAreaActivity) {
        this.f$0 = factoryAreaActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateFactoryProductList((List) obj);
    }
}
