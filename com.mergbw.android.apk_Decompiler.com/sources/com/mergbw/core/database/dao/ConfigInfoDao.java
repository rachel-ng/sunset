package com.mergbw.core.database.dao;

import com.mergbw.core.database.bean.ConfigInfoBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.util.List;

public interface ConfigInfoDao {
    Completable deleteConfig(ConfigInfoBean configInfoBean);

    Maybe<Long> insertConfig(ConfigInfoBean configInfoBean);

    Flowable<List<ConfigInfoBean>> queryConfigList();

    Completable updateConfig(ConfigInfoBean configInfoBean);
}
