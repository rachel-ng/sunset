package com.mergbw.core.jump;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mergbw.core.database.bean.DIYInfoBean;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.bean.SubColorBean;

public class JumpManager {
    private JumpManager() {
    }

    public static JumpManager getInstance() {
        return JumpManagerFactory.jumpManager;
    }

    private static class JumpManagerFactory {
        /* access modifiers changed from: private */
        public static final JumpManager jumpManager = new JumpManager();

        private JumpManagerFactory() {
        }
    }

    public void jumpToSubSetting(DeviceInfoBean deviceInfoBean, SubColorBean subColorBean, int i) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("device", deviceInfoBean);
        bundle.putSerializable("subColor", subColorBean);
        bundle.putInt(TypedValues.TransitionType.S_FROM, i);
        build().targetClass(JumpPath.SUB_SETTING).bundle(bundle).jump();
    }

    public void jumpToDIYSetting(DeviceInfoBean deviceInfoBean, DIYInfoBean dIYInfoBean, int i) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("device", deviceInfoBean);
        bundle.putSerializable("diyInfo", dIYInfoBean);
        bundle.putInt(TypedValues.TransitionType.S_FROM, i);
        build().targetClass(JumpPath.DEVICE_DIY).bundle(bundle).jump();
    }

    private Builder build() {
        return new Builder();
    }

    private static class Builder {
        private Bundle bundle;
        private String targetClass;

        private Builder() {
        }

        public Builder bundle(Bundle bundle2) {
            this.bundle = bundle2;
            return this;
        }

        public Builder targetClass(String str) {
            this.targetClass = str;
            return this;
        }

        public void jump() throws NoTargetClassException {
            if (TextUtils.isEmpty(this.targetClass)) {
                throw new NoTargetClassException();
            } else if (this.bundle == null) {
                ARouter.getInstance().build(this.targetClass).navigation();
            } else {
                ARouter.getInstance().build(this.targetClass).with(this.bundle).navigation();
            }
        }
    }

    private static class NoTargetClassException extends RuntimeException {
        private NoTargetClassException() {
        }
    }
}
