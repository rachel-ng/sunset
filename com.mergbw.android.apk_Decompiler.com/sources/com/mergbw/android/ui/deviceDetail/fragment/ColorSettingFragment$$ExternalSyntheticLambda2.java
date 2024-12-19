package com.mergbw.android.ui.deviceDetail.fragment;

import androidx.lifecycle.Observer;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ColorSettingFragment$$ExternalSyntheticLambda2 implements Observer {
    public final /* synthetic */ ColorSettingFragment f$0;

    public /* synthetic */ ColorSettingFragment$$ExternalSyntheticLambda2(ColorSettingFragment colorSettingFragment) {
        this.f$0 = colorSettingFragment;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateCommonColor((List) obj);
    }
}
