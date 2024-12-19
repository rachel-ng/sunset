package com.mergbw.core.database.presenter;

import com.mergbw.core.database.AppDatabase;
import com.mergbw.core.database.bean.ConfigInfoBean;
import io.reactivex.functions.Action;
import java.util.List;

public class ConfigDatabasePresenter extends BaseDatabasePresenter {
    private final IConfigDatabaseListener mListener;

    public ConfigDatabasePresenter(IConfigDatabaseListener iConfigDatabaseListener) {
        this.mListener = iConfigDatabaseListener;
    }

    public void addConfig(ConfigInfoBean configInfoBean) {
        addDisposable(AppDatabase.getInstance().configInfoDao().insertConfig(configInfoBean), new ConfigDatabasePresenter$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addConfig$0$com-mergbw-core-database-presenter-ConfigDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m879lambda$addConfig$0$commergbwcoredatabasepresenterConfigDatabasePresenter(Long l) throws Exception {
        IConfigDatabaseListener iConfigDatabaseListener = this.mListener;
        if (iConfigDatabaseListener != null) {
            iConfigDatabaseListener.onAddSuccess();
        }
    }

    public void getConfigList() {
        addDisposable(AppDatabase.getInstance().configInfoDao().queryConfigList(), new ConfigDatabasePresenter$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getConfigList$1$com-mergbw-core-database-presenter-ConfigDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m881lambda$getConfigList$1$commergbwcoredatabasepresenterConfigDatabasePresenter(List list) throws Exception {
        IConfigDatabaseListener iConfigDatabaseListener = this.mListener;
        if (iConfigDatabaseListener != null) {
            iConfigDatabaseListener.onGetConfigList(list);
        }
    }

    public void deleteConfig(ConfigInfoBean configInfoBean) {
        addDisposable(AppDatabase.getInstance().configInfoDao().deleteConfig(configInfoBean), (Action) new ConfigDatabasePresenter$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$deleteConfig$2$com-mergbw-core-database-presenter-ConfigDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m880lambda$deleteConfig$2$commergbwcoredatabasepresenterConfigDatabasePresenter() throws Exception {
        IConfigDatabaseListener iConfigDatabaseListener = this.mListener;
        if (iConfigDatabaseListener != null) {
            iConfigDatabaseListener.onDeleteSuccess();
        }
    }

    public void updateConfig(ConfigInfoBean configInfoBean) {
        addDisposable(AppDatabase.getInstance().configInfoDao().updateConfig(configInfoBean), (Action) new ConfigDatabasePresenter$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateConfig$3$com-mergbw-core-database-presenter-ConfigDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m882lambda$updateConfig$3$commergbwcoredatabasepresenterConfigDatabasePresenter() throws Exception {
        IConfigDatabaseListener iConfigDatabaseListener = this.mListener;
        if (iConfigDatabaseListener != null) {
            iConfigDatabaseListener.onUpdateSuccess();
        }
    }
}
