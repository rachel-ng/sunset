package com.mergbw.core.ui.subColorSetting;

import androidx.lifecycle.Observer;
import com.mergbw.core.database.bean.SubColorBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SubColorSettingActivity$$ExternalSyntheticLambda1 implements Observer {
    public final /* synthetic */ SubColorSettingActivity f$0;

    public /* synthetic */ SubColorSettingActivity$$ExternalSyntheticLambda1(SubColorSettingActivity subColorSettingActivity) {
        this.f$0 = subColorSettingActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateSubColor((SubColorBean) obj);
    }
}
