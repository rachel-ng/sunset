package com.mergbw.android.ui.groupSetting;

import androidx.lifecycle.Observer;
import com.mergbw.core.database.bean.GroupItemBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GroupSettingActivity$$ExternalSyntheticLambda8 implements Observer {
    public final /* synthetic */ GroupSettingActivity f$0;

    public /* synthetic */ GroupSettingActivity$$ExternalSyntheticLambda8(GroupSettingActivity groupSettingActivity) {
        this.f$0 = groupSettingActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateGroupInfo((GroupItemBean) obj);
    }
}
