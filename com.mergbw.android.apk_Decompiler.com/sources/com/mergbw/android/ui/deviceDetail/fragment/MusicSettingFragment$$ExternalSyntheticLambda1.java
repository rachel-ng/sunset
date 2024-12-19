package com.mergbw.android.ui.deviceDetail.fragment;

import androidx.lifecycle.Observer;
import com.mergbw.core.network.factory.bean.FactoryInfoBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MusicSettingFragment$$ExternalSyntheticLambda1 implements Observer {
    public final /* synthetic */ MusicSettingFragment f$0;

    public /* synthetic */ MusicSettingFragment$$ExternalSyntheticLambda1(MusicSettingFragment musicSettingFragment) {
        this.f$0 = musicSettingFragment;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateFactoryInfo((FactoryInfoBean) obj);
    }
}
