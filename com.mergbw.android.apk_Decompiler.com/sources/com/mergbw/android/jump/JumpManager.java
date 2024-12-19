package com.mergbw.android.jump;

import android.os.Bundle;
import android.text.TextUtils;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.gms.common.internal.ImagesContract;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.bean.GroupItemBean;
import com.mergbw.core.database.bean.UpgradeErrorRecordBean;
import com.mergbw.core.network.factory.bean.FactoryInfoBean;

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

    public void jumpToHomePage() {
        build().targetClass(JumpPath.HOME_PAGE).jump();
    }

    public void jumpToFirstCheck() {
        build().targetClass(JumpPath.FIRST_CHECK_PAGE).jump();
    }

    public void jumpToAddDevice() {
        build().targetClass(JumpPath.ADD_DEVICE).jump();
    }

    public void jumpToDeviceDetail(DeviceInfoBean deviceInfoBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("device", deviceInfoBean);
        build().targetClass(JumpPath.DEVICE_DETAIL).bundle(bundle).jump();
    }

    public void jumpToDeviceInfo(DeviceInfoBean deviceInfoBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("device", deviceInfoBean);
        build().targetClass(JumpPath.DEVICE_INFO).bundle(bundle).jump();
    }

    public void jumpToDeviceAlarm(DeviceInfoBean deviceInfoBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("device", deviceInfoBean);
        build().targetClass(JumpPath.DEVICE_ALARM).bundle(bundle).jump();
    }

    public void jumpToDeviceUpgrade(DeviceInfoBean deviceInfoBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("device", deviceInfoBean);
        build().targetClass(JumpPath.UPGRADE_PAGE).bundle(bundle).jump();
    }

    public void jumpToDeviceResume(UpgradeErrorRecordBean upgradeErrorRecordBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("errorRecord", upgradeErrorRecordBean);
        build().targetClass(JumpPath.RESUME_PAGE).bundle(bundle).jump();
    }

    public void jumpToGroupSetting(GroupItemBean groupItemBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("group", groupItemBean);
        build().targetClass(JumpPath.GROUP_SETTING).bundle(bundle).jump();
    }

    public void jumpToGroupDetail(GroupItemBean groupItemBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("group", groupItemBean);
        build().targetClass(JumpPath.GROUP_DETAIL).bundle(bundle).jump();
    }

    public void jumpToGroupDetail6(GroupItemBean groupItemBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("group", groupItemBean);
        build().targetClass(JumpPath.GROUP_DETAIL_6).bundle(bundle).jump();
    }

    public void jumpToLogin() {
        build().targetClass(JumpPath.LOGIN_PAGE).jump();
    }

    public void jumpToRegister() {
        build().targetClass(JumpPath.REGISTER_PAGE).jump();
    }

    public void jumpToReset() {
        build().targetClass(JumpPath.RESET_PAGE).jump();
    }

    public void jumpToAbout() {
        build().targetClass(JumpPath.ABOUT_PAGE).jump();
    }

    public void jumpToAgreementPage(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(SessionDescription.ATTR_TYPE, i);
        build().targetClass(JumpPath.AGREEMENT_PAGE).bundle(bundle).jump();
    }

    public void jumpToFactoryArea(FactoryInfoBean factoryInfoBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("factoryInfo", factoryInfoBean);
        build().targetClass(JumpPath.FACTORY_AREA_PAGE).bundle(bundle).jump();
    }

    public void jumpToWebViewPage(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putString(ImagesContract.URL, str2);
        build().targetClass(JumpPath.WEB_VIEW_PAGE).bundle(bundle).jump();
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
