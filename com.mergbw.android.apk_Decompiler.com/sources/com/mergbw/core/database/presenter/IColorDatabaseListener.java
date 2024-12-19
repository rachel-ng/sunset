package com.mergbw.core.database.presenter;

import com.mergbw.core.database.bean.ColorBean;
import java.util.List;

public interface IColorDatabaseListener extends IBaseDatabaseListener {
    void onGetColorList(List<ColorBean> list);
}
