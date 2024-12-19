package com.mergbw.core.database.dao;

import com.mergbw.core.database.bean.DIYInfoBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.util.List;

public interface DIYColorDao {
    Completable deleteColor(DIYInfoBean dIYInfoBean);

    Maybe<Long> insertDIYColor(DIYInfoBean dIYInfoBean);

    Maybe<DIYInfoBean> queryDIYColor(int i);

    Flowable<List<DIYInfoBean>> queryDIYColorList();

    Flowable<List<DIYInfoBean>> queryDIYColorList(int i);

    Flowable<List<DIYInfoBean>> queryDIYColorList(String str);

    Completable updateColor(DIYInfoBean dIYInfoBean);
}
