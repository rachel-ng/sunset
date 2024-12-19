package com.mergbw.core.database.dao;

import com.mergbw.core.database.bean.ColorBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.List;

public interface ColorInfoDao {
    Completable insertColor(ColorBean colorBean);

    Flowable<List<ColorBean>> queryColorList();
}
