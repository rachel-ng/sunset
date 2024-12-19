package com.mergbw.core.database.presenter;

import com.mergbw.core.database.AppDatabase;
import com.mergbw.core.database.bean.DIYInfoBean;
import io.reactivex.functions.Action;
import java.util.List;

public class DIYColorDatabasePresenter extends BaseDatabasePresenter {
    private final IDIYColorDatabaseListener mListener;

    public DIYColorDatabasePresenter(IDIYColorDatabaseListener iDIYColorDatabaseListener) {
        this.mListener = iDIYColorDatabaseListener;
    }

    public void addDIYColor(DIYInfoBean dIYInfoBean) {
        addDisposable(AppDatabase.getInstance().diyColorDao().insertDIYColor(dIYInfoBean), new DIYColorDatabasePresenter$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addDIYColor$0$com-mergbw-core-database-presenter-DIYColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m883lambda$addDIYColor$0$commergbwcoredatabasepresenterDIYColorDatabasePresenter(Long l) throws Exception {
        IDIYColorDatabaseListener iDIYColorDatabaseListener = this.mListener;
        if (iDIYColorDatabaseListener != null) {
            iDIYColorDatabaseListener.onAddDiy(l);
        }
    }

    public void getDIYColor(int i) {
        addDisposable(AppDatabase.getInstance().diyColorDao().queryDIYColor(i), new DIYColorDatabasePresenter$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getDIYColor$1$com-mergbw-core-database-presenter-DIYColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m885lambda$getDIYColor$1$commergbwcoredatabasepresenterDIYColorDatabasePresenter(DIYInfoBean dIYInfoBean) throws Exception {
        IDIYColorDatabaseListener iDIYColorDatabaseListener = this.mListener;
        if (iDIYColorDatabaseListener != null) {
            iDIYColorDatabaseListener.onGetDIYColor(dIYInfoBean);
        }
    }

    public void getDIYColorList() {
        addDisposable(AppDatabase.getInstance().diyColorDao().queryDIYColorList(), new DIYColorDatabasePresenter$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getDIYColorList$2$com-mergbw-core-database-presenter-DIYColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m886lambda$getDIYColorList$2$commergbwcoredatabasepresenterDIYColorDatabasePresenter(List list) throws Exception {
        IDIYColorDatabaseListener iDIYColorDatabaseListener = this.mListener;
        if (iDIYColorDatabaseListener != null) {
            iDIYColorDatabaseListener.onGetDIYColorList(list);
        }
    }

    public void getDIYColorList(String str) {
        addDisposable(AppDatabase.getInstance().diyColorDao().queryDIYColorList(str), new DIYColorDatabasePresenter$$ExternalSyntheticLambda6(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getDIYColorList$3$com-mergbw-core-database-presenter-DIYColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m887lambda$getDIYColorList$3$commergbwcoredatabasepresenterDIYColorDatabasePresenter(List list) throws Exception {
        IDIYColorDatabaseListener iDIYColorDatabaseListener = this.mListener;
        if (iDIYColorDatabaseListener != null) {
            iDIYColorDatabaseListener.onGetDIYColorList(list);
        }
    }

    public void getDIYColorList(int i) {
        addDisposable(AppDatabase.getInstance().diyColorDao().queryDIYColorList(i), new DIYColorDatabasePresenter$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getDIYColorList$4$com-mergbw-core-database-presenter-DIYColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m888lambda$getDIYColorList$4$commergbwcoredatabasepresenterDIYColorDatabasePresenter(List list) throws Exception {
        IDIYColorDatabaseListener iDIYColorDatabaseListener = this.mListener;
        if (iDIYColorDatabaseListener != null) {
            iDIYColorDatabaseListener.onGetDIYColorList(list);
        }
    }

    public void deleteDIYColor(DIYInfoBean dIYInfoBean) {
        addDisposable(AppDatabase.getInstance().diyColorDao().deleteColor(dIYInfoBean), (Action) new DIYColorDatabasePresenter$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$deleteDIYColor$5$com-mergbw-core-database-presenter-DIYColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m884lambda$deleteDIYColor$5$commergbwcoredatabasepresenterDIYColorDatabasePresenter() throws Exception {
        IDIYColorDatabaseListener iDIYColorDatabaseListener = this.mListener;
        if (iDIYColorDatabaseListener != null) {
            iDIYColorDatabaseListener.onDeleteSuccess();
        }
    }

    public void updateDIYColor(DIYInfoBean dIYInfoBean) {
        addDisposable(AppDatabase.getInstance().diyColorDao().updateColor(dIYInfoBean), (Action) new DIYColorDatabasePresenter$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateDIYColor$6$com-mergbw-core-database-presenter-DIYColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m889lambda$updateDIYColor$6$commergbwcoredatabasepresenterDIYColorDatabasePresenter() throws Exception {
        IDIYColorDatabaseListener iDIYColorDatabaseListener = this.mListener;
        if (iDIYColorDatabaseListener != null) {
            iDIYColorDatabaseListener.onUpdateSuccess();
        }
    }
}
