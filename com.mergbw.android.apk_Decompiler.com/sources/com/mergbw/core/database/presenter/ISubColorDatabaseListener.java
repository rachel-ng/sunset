package com.mergbw.core.database.presenter;

import com.mergbw.core.database.bean.SubColorBean;
import java.util.List;

public interface ISubColorDatabaseListener extends IBaseDatabaseListener {
    void onAddSubColor(Long l);

    void onGetSubColor(SubColorBean subColorBean);

    void onGetSubColorList(List<SubColorBean> list);
}
