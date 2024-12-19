package com.mergbw.core.database.presenter;

import com.mergbw.core.database.AppDatabase;
import com.mergbw.core.database.bean.GroupItemBean;
import io.reactivex.functions.Action;
import java.util.List;

public class GroupDatabasePresenter extends BaseDatabasePresenter {
    private final IGroupDatabaseListener mListener;

    public GroupDatabasePresenter(IGroupDatabaseListener iGroupDatabaseListener) {
        this.mListener = iGroupDatabaseListener;
    }

    public void addGroup(GroupItemBean groupItemBean) {
        addDisposable(AppDatabase.getInstance().groupInfoDao().insertGroup(groupItemBean), (Action) new GroupDatabasePresenter$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addGroup$0$com-mergbw-core-database-presenter-GroupDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m895lambda$addGroup$0$commergbwcoredatabasepresenterGroupDatabasePresenter() throws Exception {
        IGroupDatabaseListener iGroupDatabaseListener = this.mListener;
        if (iGroupDatabaseListener != null) {
            iGroupDatabaseListener.onAddSuccess();
        }
    }

    public void getGroupListWithFlowable() {
        addDisposable(AppDatabase.getInstance().groupInfoDao().queryGroupListWithFlowable(), new GroupDatabasePresenter$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGroupListWithFlowable$1$com-mergbw-core-database-presenter-GroupDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m899lambda$getGroupListWithFlowable$1$commergbwcoredatabasepresenterGroupDatabasePresenter(List list) throws Exception {
        IGroupDatabaseListener iGroupDatabaseListener = this.mListener;
        if (iGroupDatabaseListener != null) {
            iGroupDatabaseListener.onGetGroupList(list);
        }
    }

    public void getGroupList() {
        addDisposable(AppDatabase.getInstance().groupInfoDao().queryGroupList(), new GroupDatabasePresenter$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGroupList$2$com-mergbw-core-database-presenter-GroupDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m898lambda$getGroupList$2$commergbwcoredatabasepresenterGroupDatabasePresenter(List list) throws Exception {
        IGroupDatabaseListener iGroupDatabaseListener = this.mListener;
        if (iGroupDatabaseListener != null) {
            iGroupDatabaseListener.onGetGroupList(list);
        }
    }

    public void getGroupInfo(int i) {
        addDisposable(AppDatabase.getInstance().groupInfoDao().queryGroupInfo(i), new GroupDatabasePresenter$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGroupInfo$3$com-mergbw-core-database-presenter-GroupDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m897lambda$getGroupInfo$3$commergbwcoredatabasepresenterGroupDatabasePresenter(GroupItemBean groupItemBean) throws Exception {
        IGroupDatabaseListener iGroupDatabaseListener = this.mListener;
        if (iGroupDatabaseListener != null) {
            iGroupDatabaseListener.onGetGroupInfo(groupItemBean);
        }
    }

    public void updateGroup(GroupItemBean groupItemBean) {
        addDisposable(AppDatabase.getInstance().groupInfoDao().updateGroup(groupItemBean), (Action) new GroupDatabasePresenter$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateGroup$4$com-mergbw-core-database-presenter-GroupDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m900lambda$updateGroup$4$commergbwcoredatabasepresenterGroupDatabasePresenter() throws Exception {
        IGroupDatabaseListener iGroupDatabaseListener = this.mListener;
        if (iGroupDatabaseListener != null) {
            iGroupDatabaseListener.onUpdateSuccess();
        }
    }

    public void deleteGroup(GroupItemBean groupItemBean) {
        addDisposable(AppDatabase.getInstance().groupInfoDao().deleteGroup(groupItemBean), (Action) new GroupDatabasePresenter$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$deleteGroup$5$com-mergbw-core-database-presenter-GroupDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m896lambda$deleteGroup$5$commergbwcoredatabasepresenterGroupDatabasePresenter() throws Exception {
        IGroupDatabaseListener iGroupDatabaseListener = this.mListener;
        if (iGroupDatabaseListener != null) {
            iGroupDatabaseListener.onDeleteSuccess();
        }
    }
}
