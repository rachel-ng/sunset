package com.mergbw.core.network;

import com.mergbw.core.network.app.bean.CommonResult;

public interface WebInterface {
    void noMoreData();

    void showError(int i);

    void showResult(int i, CommonResult commonResult);
}
