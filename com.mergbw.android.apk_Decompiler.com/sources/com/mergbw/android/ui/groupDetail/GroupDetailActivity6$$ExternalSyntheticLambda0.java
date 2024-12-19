package com.mergbw.android.ui.groupDetail;

import androidx.lifecycle.Observer;
import com.mergbw.core.database.bean.GroupItemBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GroupDetailActivity6$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ GroupDetailActivity6 f$0;

    public /* synthetic */ GroupDetailActivity6$$ExternalSyntheticLambda0(GroupDetailActivity6 groupDetailActivity6) {
        this.f$0 = groupDetailActivity6;
    }

    public final void onChanged(Object obj) {
        this.f$0.updateGroupInfo((GroupItemBean) obj);
    }
}
