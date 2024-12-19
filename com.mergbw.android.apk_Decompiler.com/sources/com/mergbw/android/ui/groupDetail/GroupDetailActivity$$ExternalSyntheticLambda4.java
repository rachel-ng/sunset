package com.mergbw.android.ui.groupDetail;

import androidx.lifecycle.Observer;
import com.mergbw.core.database.bean.GroupItemBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GroupDetailActivity$$ExternalSyntheticLambda4 implements Observer {
    public final /* synthetic */ GroupDetailActivity f$0;

    public /* synthetic */ GroupDetailActivity$$ExternalSyntheticLambda4(GroupDetailActivity groupDetailActivity) {
        this.f$0 = groupDetailActivity;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateGroupInfo((GroupItemBean) obj);
    }
}
