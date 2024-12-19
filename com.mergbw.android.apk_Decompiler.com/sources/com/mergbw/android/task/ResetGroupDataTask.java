package com.mergbw.android.task;

import com.mergbw.core.database.bean.GroupItemBean;
import com.mergbw.core.database.presenter.GroupDatabasePresenter;
import com.mergbw.core.database.presenter.IGroupDatabaseListener;
import java.util.List;

public class ResetGroupDataTask implements Runnable {
    private final String mDeleteMac;
    private GroupDatabasePresenter mGroupDatabasePresenter;

    public ResetGroupDataTask(String str) {
        this.mDeleteMac = str;
    }

    public void run() {
        GroupDatabasePresenter groupDatabasePresenter = new GroupDatabasePresenter(new IGroupDatabaseListener() {
            public void onAddSuccess() {
            }

            public void onDeleteSuccess() {
            }

            public void onGetGroupInfo(GroupItemBean groupItemBean) {
            }

            public void onUpdateSuccess() {
            }

            public void onGetGroupList(List<GroupItemBean> list) {
                ResetGroupDataTask.this.resetGroupData(list);
            }
        });
        this.mGroupDatabasePresenter = groupDatabasePresenter;
        groupDatabasePresenter.getGroupList();
    }

    /* access modifiers changed from: private */
    public void resetGroupData(List<GroupItemBean> list) {
        for (GroupItemBean next : list) {
            String[] split = next.getDevices().split(";");
            int i = -1;
            for (int i2 = 0; i2 < split.length; i2++) {
                if (split[i2].equals(this.mDeleteMac)) {
                    i = i2;
                }
            }
            if (i != -1) {
                StringBuilder sb = new StringBuilder();
                for (int i3 = 0; i3 < split.length; i3++) {
                    if (i3 != i) {
                        if (i3 == 0) {
                            sb.append(split[i3]);
                        } else {
                            sb.append(";");
                            sb.append(split[i3]);
                        }
                    }
                }
                next.setDevices(sb.toString());
                this.mGroupDatabasePresenter.updateGroup(next);
            }
        }
    }
}
