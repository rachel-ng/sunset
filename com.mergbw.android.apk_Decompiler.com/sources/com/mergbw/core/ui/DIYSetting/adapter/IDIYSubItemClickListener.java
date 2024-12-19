package com.mergbw.core.ui.DIYSetting.adapter;

import com.mergbw.core.database.bean.SubColorBean;

public interface IDIYSubItemClickListener {
    void clickDIYSubItem(SubColorBean subColorBean);

    void deleteSubItem(SubColorBean subColorBean, int i, int i2);
}
