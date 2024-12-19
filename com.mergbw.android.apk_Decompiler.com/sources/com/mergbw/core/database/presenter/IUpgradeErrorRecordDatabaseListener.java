package com.mergbw.core.database.presenter;

import com.mergbw.core.database.bean.UpgradeErrorRecordBean;
import java.util.List;

public interface IUpgradeErrorRecordDatabaseListener extends IBaseDatabaseListener {
    void onGetRecordList(List<UpgradeErrorRecordBean> list);
}
