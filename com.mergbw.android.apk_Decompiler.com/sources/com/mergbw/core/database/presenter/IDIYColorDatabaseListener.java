package com.mergbw.core.database.presenter;

import com.mergbw.core.database.bean.DIYInfoBean;
import java.util.List;

public interface IDIYColorDatabaseListener extends IBaseDatabaseListener {
    void onAddDiy(Long l);

    void onGetDIYColor(DIYInfoBean dIYInfoBean);

    void onGetDIYColorList(List<DIYInfoBean> list);
}
