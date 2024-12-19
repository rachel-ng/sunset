package com.mergbw.core.database.presenter;

import com.mergbw.core.database.AppDatabase;
import com.mergbw.core.database.bean.SubColorBean;
import io.reactivex.functions.Action;
import java.util.List;

public class SubColorDatabasePresenter extends BaseDatabasePresenter {
    private final ISubColorDatabaseListener mListener;

    public SubColorDatabasePresenter(ISubColorDatabaseListener iSubColorDatabaseListener) {
        this.mListener = iSubColorDatabaseListener;
    }

    public void addColor(SubColorBean subColorBean) {
        addDisposable(AppDatabase.getInstance().subColorDao().insertColor(subColorBean), new SubColorDatabasePresenter$$ExternalSyntheticLambda6(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addColor$0$com-mergbw-core-database-presenter-SubColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m901lambda$addColor$0$commergbwcoredatabasepresenterSubColorDatabasePresenter(Long l) throws Exception {
        ISubColorDatabaseListener iSubColorDatabaseListener = this.mListener;
        if (iSubColorDatabaseListener != null) {
            iSubColorDatabaseListener.onAddSubColor(l);
            this.mListener.onAddSuccess();
        }
    }

    public void getSubColor(int i) {
        addDisposable(AppDatabase.getInstance().subColorDao().querySubColor(i), new SubColorDatabasePresenter$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getSubColor$1$com-mergbw-core-database-presenter-SubColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m907lambda$getSubColor$1$commergbwcoredatabasepresenterSubColorDatabasePresenter(SubColorBean subColorBean) throws Exception {
        ISubColorDatabaseListener iSubColorDatabaseListener = this.mListener;
        if (iSubColorDatabaseListener != null) {
            iSubColorDatabaseListener.onGetSubColor(subColorBean);
        }
    }

    public void getColorList() {
        addDisposable(AppDatabase.getInstance().subColorDao().queryColorList(), new SubColorDatabasePresenter$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getColorList$2$com-mergbw-core-database-presenter-SubColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m904lambda$getColorList$2$commergbwcoredatabasepresenterSubColorDatabasePresenter(List list) throws Exception {
        ISubColorDatabaseListener iSubColorDatabaseListener = this.mListener;
        if (iSubColorDatabaseListener != null) {
            iSubColorDatabaseListener.onGetSubColorList(list);
        }
    }

    public void getColorList(String str) {
        addDisposable(AppDatabase.getInstance().subColorDao().queryColorList(str), new SubColorDatabasePresenter$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getColorList$3$com-mergbw-core-database-presenter-SubColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m905lambda$getColorList$3$commergbwcoredatabasepresenterSubColorDatabasePresenter(List list) throws Exception {
        ISubColorDatabaseListener iSubColorDatabaseListener = this.mListener;
        if (iSubColorDatabaseListener != null) {
            iSubColorDatabaseListener.onGetSubColorList(list);
        }
    }

    public void getColorList(int i) {
        addDisposable(AppDatabase.getInstance().subColorDao().queryColorList(i), new SubColorDatabasePresenter$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getColorList$4$com-mergbw-core-database-presenter-SubColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m906lambda$getColorList$4$commergbwcoredatabasepresenterSubColorDatabasePresenter(List list) throws Exception {
        ISubColorDatabaseListener iSubColorDatabaseListener = this.mListener;
        if (iSubColorDatabaseListener != null) {
            iSubColorDatabaseListener.onGetSubColorList(list);
        }
    }

    public void deleteColor(List<SubColorBean> list) {
        addDisposable(AppDatabase.getInstance().subColorDao().deleteColor(list), (Action) new SubColorDatabasePresenter$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$deleteColor$5$com-mergbw-core-database-presenter-SubColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m902lambda$deleteColor$5$commergbwcoredatabasepresenterSubColorDatabasePresenter() throws Exception {
        ISubColorDatabaseListener iSubColorDatabaseListener = this.mListener;
        if (iSubColorDatabaseListener != null) {
            iSubColorDatabaseListener.onDeleteSuccess();
        }
    }

    public void updateSubColor(SubColorBean subColorBean) {
        addDisposable(AppDatabase.getInstance().subColorDao().updateColor(subColorBean), (Action) new SubColorDatabasePresenter$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateSubColor$6$com-mergbw-core-database-presenter-SubColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m908lambda$updateSubColor$6$commergbwcoredatabasepresenterSubColorDatabasePresenter() throws Exception {
        ISubColorDatabaseListener iSubColorDatabaseListener = this.mListener;
        if (iSubColorDatabaseListener != null) {
            iSubColorDatabaseListener.onUpdateSuccess();
        }
    }

    public void deleteSubColor(SubColorBean subColorBean) {
        addDisposable(AppDatabase.getInstance().subColorDao().deleteColor(subColorBean), (Action) new SubColorDatabasePresenter$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$deleteSubColor$7$com-mergbw-core-database-presenter-SubColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m903lambda$deleteSubColor$7$commergbwcoredatabasepresenterSubColorDatabasePresenter() throws Exception {
        ISubColorDatabaseListener iSubColorDatabaseListener = this.mListener;
        if (iSubColorDatabaseListener != null) {
            iSubColorDatabaseListener.onDeleteSuccess();
        }
    }
}
