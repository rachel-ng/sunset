package com.ota66.sdk.firware;

public interface UpdateFirewareCallBack {
    void onError(int i);

    void onProcess(float f);

    void onUpdateComplete();
}
