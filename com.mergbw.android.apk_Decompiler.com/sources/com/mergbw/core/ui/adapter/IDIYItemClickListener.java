package com.mergbw.core.ui.adapter;

import com.mergbw.core.database.bean.DIYInfoBean;

public interface IDIYItemClickListener {
    void clickDIYItem(DIYInfoBean dIYInfoBean);

    void longClickDIYItem(DIYInfoBean dIYInfoBean);
}
