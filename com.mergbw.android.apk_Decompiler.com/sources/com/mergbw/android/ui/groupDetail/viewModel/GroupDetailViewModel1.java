package com.mergbw.android.ui.groupDetail.viewModel;

import android.app.Application;
import com.mergbw.core.Constants;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.utils.ColorUtils;

public class GroupDetailViewModel1 extends BaseGroupDetailViewModel {
    public GroupDetailViewModel1(Application application) {
        super(application);
    }

    public void setBrightness(int i) {
        super.setBrightness(i);
        sendData(CommandUtils.getCommand((byte) 5, CommandUtils.getByteArray((i + 5) * 10)));
    }

    public void setRGBColor(int i) {
        super.setRGBColor(i);
        sendData(CommandUtils.getCommand((byte) 3, ColorUtils.getHSVColorData(i)));
    }

    public void setScene(SceneData sceneData) {
        sendData(CommandUtils.getCommand((byte) 6, CommandUtils.getByteArray(sceneData.getSceneIndex())));
        sendData(CommandUtils.getCommand((byte) 15, new byte[]{(byte) (sceneData.getSpeed() & 255)}));
    }

    public void setMusicModel(int i) {
        sendData(CommandUtils.getCommand((byte) 7, new byte[]{(byte) (i & 255)}));
    }

    public void setMusicSens(int i) {
        sendData(CommandUtils.getCommand((byte) 8, new byte[]{(byte) (((int) (((((double) i) / 100.0d) * ((double) (100 - Constants.MIN_SENSITIVE_VALUE))) + ((double) Constants.MIN_SENSITIVE_VALUE))) & 255)}));
    }
}
