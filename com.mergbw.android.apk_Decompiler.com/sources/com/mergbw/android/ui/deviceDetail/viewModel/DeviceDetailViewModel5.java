package com.mergbw.android.ui.deviceDetail.viewModel;

import android.app.Application;
import android.graphics.Color;
import com.mergbw.core.RGBMode;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.utils.ViewDataUtils;

public class DeviceDetailViewModel5 extends BaseDeviceDetailViewModel {
    public DeviceDetailViewModel5(Application application) {
        super(application, "DeviceDetailViewModel5");
    }

    /* access modifiers changed from: protected */
    public void onBleResponse(byte b2, byte[] bArr) {
        super.onBleResponse(b2, bArr);
        if (b2 == 0) {
            ViewDataUtils.analyseSyncData(this.mCurrentDevice, bArr);
            this.mCurrentDevice.setBound(true);
            this.mDetailViewData.postValue(this.mCurrentDevice);
        }
    }

    public void setColor(int i) {
        super.setColor(i);
        sendData(CommandUtils.getCommand((byte) 3, new byte[]{(byte) (Color.red(i) & 255), (byte) (Color.green(i) & 255), (byte) (Color.blue(i) & 255)}));
    }

    public void setBrightness(int i) {
        super.setBrightness(i);
        sendData(CommandUtils.getCommand((byte) 5, new byte[]{(byte) ((i + 5) & 255)}));
    }

    public void setScene(SceneData sceneData) {
        super.setScene(sceneData);
        if (sceneData.getMode() == RGBMode.STATIC_MODEL) {
            sendData(CommandUtils.getCommand((byte) 6, new byte[]{(byte) (sceneData.getSceneIndex() & 255)}));
        } else if (sceneData.getMode() == RGBMode.COLOR_MODEL) {
            sendData(CommandUtils.getCommand((byte) 3, new byte[]{(byte) sceneData.getRValue(), (byte) sceneData.getGValue(), (byte) sceneData.getBValue()}));
        }
    }
}
