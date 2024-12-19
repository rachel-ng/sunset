package com.mergbw.core.ui.DIYSetting;

import androidx.lifecycle.Observer;
import com.mergbw.core.database.bean.DIYInfoBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DIYSettingActivity$$ExternalSyntheticLambda6 implements Observer {
    public final /* synthetic */ DIYSettingActivity f$0;

    public /* synthetic */ DIYSettingActivity$$ExternalSyntheticLambda6(DIYSettingActivity dIYSettingActivity) {
        this.f$0 = dIYSettingActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateDIYInfo((DIYInfoBean) obj);
    }
}
