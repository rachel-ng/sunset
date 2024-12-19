package com.mergbw.android.ui.groupDetail.viewModel;

import android.app.Application;
import android.graphics.Color;
import com.mergbw.core.RGBMode;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.ble.CommandUtils;

public class GroupDetailViewModel5 extends BaseGroupDetailViewModel {
    public GroupDetailViewModel5(Application application) {
        super(application);
    }

    public void setBrightness(int i) {
        super.setBrightness(i);
        sendData(CommandUtils.getCommand((byte) 5, new byte[]{(byte) ((i + 5) & 255)}));
    }

    public void setRGBColor(int i) {
        super.setRGBColor(i);
        byte[] bArr = new byte[4];
        bArr[0] = (byte) (Color.red(i) & 255);
        bArr[1] = (byte) (Color.green(i) & 255);
        bArr[2] = (byte) (Color.blue(i) & 255);
        sendData(CommandUtils.getCommand((byte) 3, bArr));
    }

    public void setScene(SceneData sceneData) {
        if (sceneData.getMode() == RGBMode.STATIC_MODEL) {
            sendData(CommandUtils.getCommand((byte) 6, new byte[]{(byte) (sceneData.getSceneIndex() & 255)}));
        } else if (sceneData.getMode() == RGBMode.COLOR_MODEL) {
            sendData(CommandUtils.getCommand((byte) 3, new byte[]{(byte) sceneData.getRValue(), (byte) sceneData.getGValue(), (byte) sceneData.getBValue()}));
        }
    }
}
