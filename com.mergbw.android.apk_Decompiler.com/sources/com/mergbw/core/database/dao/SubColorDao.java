package com.mergbw.core.database.dao;

import com.mergbw.core.database.bean.SubColorBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.util.List;

public interface SubColorDao {
    Completable deleteColor(SubColorBean subColorBean);

    Completable deleteColor(List<SubColorBean> list);

    Maybe<Long> insertColor(SubColorBean subColorBean);

    Flowable<List<SubColorBean>> queryColorList();

    Flowable<List<SubColorBean>> queryColorList(int i);

    Flowable<List<SubColorBean>> queryColorList(String str);

    Maybe<SubColorBean> querySubColor(int i);

    Completable updateColor(SubColorBean subColorBean);
}
