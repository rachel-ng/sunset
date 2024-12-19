package com.mergbw.core.ui.subColorSetting;

import androidx.lifecycle.Observer;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SubColorSettingActivity$$ExternalSyntheticLambda3 implements Observer {
    public final /* synthetic */ SubColorSettingActivity f$0;

    public /* synthetic */ SubColorSettingActivity$$ExternalSyntheticLambda3(SubColorSettingActivity subColorSettingActivity) {
        this.f$0 = subColorSettingActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateCommonColor((List) obj);
    }
}
