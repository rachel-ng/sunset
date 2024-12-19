package com.mergbw.core.database.presenter;

import com.mergbw.core.database.bean.GroupItemBean;
import java.util.List;

public interface IGroupDatabaseListener extends IBaseDatabaseListener {
    void onGetGroupInfo(GroupItemBean groupItemBean);

    void onGetGroupList(List<GroupItemBean> list);
}
