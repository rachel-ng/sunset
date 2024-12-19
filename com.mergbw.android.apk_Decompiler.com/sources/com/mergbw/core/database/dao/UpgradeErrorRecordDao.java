package com.mergbw.core.database.dao;

import com.mergbw.core.database.bean.UpgradeErrorRecordBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.List;

public interface UpgradeErrorRecordDao {
    Completable deleteRecord(UpgradeErrorRecordBean upgradeErrorRecordBean);

    Completable insertRecord(UpgradeErrorRecordBean upgradeErrorRecordBean);

    Flowable<List<UpgradeErrorRecordBean>> queryRecordList();
}
