package com.mergbw.core.database.dao;

import com.mergbw.core.database.bean.GroupItemBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.util.List;

public interface GroupInfoDao {
    Completable deleteGroup(GroupItemBean groupItemBean);

    Completable insertGroup(GroupItemBean groupItemBean);

    Flowable<GroupItemBean> queryGroupInfo(int i);

    Maybe<List<GroupItemBean>> queryGroupList();

    Flowable<List<GroupItemBean>> queryGroupListWithFlowable();

    Completable updateGroup(GroupItemBean groupItemBean);
}
