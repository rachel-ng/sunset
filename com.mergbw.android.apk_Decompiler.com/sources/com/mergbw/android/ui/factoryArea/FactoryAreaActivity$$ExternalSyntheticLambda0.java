package com.mergbw.android.ui.factoryArea;

import androidx.lifecycle.Observer;
import com.mergbw.core.network.factory.bean.FactoryInfoBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FactoryAreaActivity$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ FactoryAreaActivity f$0;

    public /* synthetic */ FactoryAreaActivity$$ExternalSyntheticLambda0(FactoryAreaActivity factoryAreaActivity) {
        this.f$0 = factoryAreaActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateFactoryInfo((FactoryInfoBean) obj);
    }
}
