package com.mergbw.core.database.presenter;

import com.mergbw.core.database.AppDatabase;
import com.mergbw.core.database.bean.UpgradeErrorRecordBean;
import io.reactivex.functions.Action;
import java.util.List;

public class UpgradeErrorRecordDatabasePresenter extends BaseDatabasePresenter {
    private final IUpgradeErrorRecordDatabaseListener mListener;

    public UpgradeErrorRecordDatabasePresenter(IUpgradeErrorRecordDatabaseListener iUpgradeErrorRecordDatabaseListener) {
        this.mListener = iUpgradeErrorRecordDatabaseListener;
    }

    public void addRecord(UpgradeErrorRecordBean upgradeErrorRecordBean) {
        addDisposable(AppDatabase.getInstance().upgradeErrorRecordDao().insertRecord(upgradeErrorRecordBean), (Action) new UpgradeErrorRecordDatabasePresenter$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addRecord$0$com-mergbw-core-database-presenter-UpgradeErrorRecordDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m909lambda$addRecord$0$commergbwcoredatabasepresenterUpgradeErrorRecordDatabasePresenter() throws Exception {
        IUpgradeErrorRecordDatabaseListener iUpgradeErrorRecordDatabaseListener = this.mListener;
        if (iUpgradeErrorRecordDatabaseListener != null) {
            iUpgradeErrorRecordDatabaseListener.onAddSuccess();
        }
    }

    public void getRecordList() {
        addDisposable(AppDatabase.getInstance().upgradeErrorRecordDao().queryRecordList(), new UpgradeErrorRecordDatabasePresenter$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getRecordList$1$com-mergbw-core-database-presenter-UpgradeErrorRecordDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m911lambda$getRecordList$1$commergbwcoredatabasepresenterUpgradeErrorRecordDatabasePresenter(List list) throws Exception {
        IUpgradeErrorRecordDatabaseListener iUpgradeErrorRecordDatabaseListener = this.mListener;
        if (iUpgradeErrorRecordDatabaseListener != null) {
            iUpgradeErrorRecordDatabaseListener.onGetRecordList(list);
        }
    }

    public void deleteConfig(UpgradeErrorRecordBean upgradeErrorRecordBean) {
        addDisposable(AppDatabase.getInstance().upgradeErrorRecordDao().deleteRecord(upgradeErrorRecordBean), (Action) new UpgradeErrorRecordDatabasePresenter$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$deleteConfig$2$com-mergbw-core-database-presenter-UpgradeErrorRecordDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m910lambda$deleteConfig$2$commergbwcoredatabasepresenterUpgradeErrorRecordDatabasePresenter() throws Exception {
        IUpgradeErrorRecordDatabaseListener iUpgradeErrorRecordDatabaseListener = this.mListener;
        if (iUpgradeErrorRecordDatabaseListener != null) {
            iUpgradeErrorRecordDatabaseListener.onDeleteSuccess();
        }
    }
}
