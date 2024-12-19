package com.mergbw.core.database.presenter;

import com.mergbw.core.database.AppDatabase;
import com.mergbw.core.database.bean.ColorBean;
import io.reactivex.functions.Action;
import java.util.List;

public class ColorDatabasePresenter extends BaseDatabasePresenter {
    private final IColorDatabaseListener mListener;

    public ColorDatabasePresenter(IColorDatabaseListener iColorDatabaseListener) {
        this.mListener = iColorDatabaseListener;
    }

    public void addColor(ColorBean colorBean) {
        addDisposable(AppDatabase.getInstance().colorInfoDao().insertColor(colorBean), (Action) new ColorDatabasePresenter$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addColor$0$com-mergbw-core-database-presenter-ColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m877lambda$addColor$0$commergbwcoredatabasepresenterColorDatabasePresenter() throws Exception {
        IColorDatabaseListener iColorDatabaseListener = this.mListener;
        if (iColorDatabaseListener != null) {
            iColorDatabaseListener.onAddSuccess();
        }
    }

    public void getColorList() {
        addDisposable(AppDatabase.getInstance().colorInfoDao().queryColorList(), new ColorDatabasePresenter$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getColorList$1$com-mergbw-core-database-presenter-ColorDatabasePresenter  reason: not valid java name */
    public /* synthetic */ void m878lambda$getColorList$1$commergbwcoredatabasepresenterColorDatabasePresenter(List list) throws Exception {
        IColorDatabaseListener iColorDatabaseListener = this.mListener;
        if (iColorDatabaseListener != null) {
            iColorDatabaseListener.onGetColorList(list);
        }
    }
}
