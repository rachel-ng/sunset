package com.mergbw.android.ui.deviceDetail.adapter;

import com.mergbw.core.bean.SceneData;

public interface IAudioItemClickListener {
    void pause();

    void resume();

    void startPlay(SceneData sceneData);
}
