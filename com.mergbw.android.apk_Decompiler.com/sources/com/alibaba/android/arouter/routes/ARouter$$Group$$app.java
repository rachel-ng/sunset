package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.mergbw.android.jump.JumpPath;
import com.mergbw.android.ui.AboutActivity;
import com.mergbw.android.ui.AgreementActivity;
import com.mergbw.android.ui.FirstCheckPrivacyActivity;
import com.mergbw.android.ui.WebViewActivity;
import com.mergbw.android.ui.addDevice.AddDeviceActivity;
import com.mergbw.android.ui.deviceAlarm.DeviceAlarmActivity;
import com.mergbw.android.ui.deviceDetail.DeviceDetailActivity;
import com.mergbw.android.ui.deviceInfo.DeviceInfoActivity;
import com.mergbw.android.ui.deviceUpgrade.DeviceResumeActivity;
import com.mergbw.android.ui.deviceUpgrade.DeviceUpgradeActivity;
import com.mergbw.android.ui.factoryArea.FactoryAreaActivity;
import com.mergbw.android.ui.groupDetail.GroupDetailActivity;
import com.mergbw.android.ui.groupDetail.GroupDetailActivity6;
import com.mergbw.android.ui.groupSetting.GroupSettingActivity;
import com.mergbw.android.ui.home.MainActivity;
import com.mergbw.android.ui.login.LoginActivity;
import com.mergbw.android.ui.login.RegisterActivity;
import com.mergbw.android.ui.login.ResetPasswordActivity;
import java.util.Map;

public class ARouter$$Group$$app implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(JumpPath.ABOUT_PAGE, RouteMeta.build(RouteType.ACTIVITY, AboutActivity.class, JumpPath.ABOUT_PAGE, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.ADD_DEVICE, RouteMeta.build(RouteType.ACTIVITY, AddDeviceActivity.class, JumpPath.ADD_DEVICE, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.AGREEMENT_PAGE, RouteMeta.build(RouteType.ACTIVITY, AgreementActivity.class, JumpPath.AGREEMENT_PAGE, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.DEVICE_ALARM, RouteMeta.build(RouteType.ACTIVITY, DeviceAlarmActivity.class, JumpPath.DEVICE_ALARM, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.DEVICE_DETAIL, RouteMeta.build(RouteType.ACTIVITY, DeviceDetailActivity.class, JumpPath.DEVICE_DETAIL, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.DEVICE_INFO, RouteMeta.build(RouteType.ACTIVITY, DeviceInfoActivity.class, JumpPath.DEVICE_INFO, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.FACTORY_AREA_PAGE, RouteMeta.build(RouteType.ACTIVITY, FactoryAreaActivity.class, JumpPath.FACTORY_AREA_PAGE, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.FIRST_CHECK_PAGE, RouteMeta.build(RouteType.ACTIVITY, FirstCheckPrivacyActivity.class, JumpPath.FIRST_CHECK_PAGE, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.GROUP_DETAIL, RouteMeta.build(RouteType.ACTIVITY, GroupDetailActivity.class, JumpPath.GROUP_DETAIL, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.GROUP_DETAIL_6, RouteMeta.build(RouteType.ACTIVITY, GroupDetailActivity6.class, JumpPath.GROUP_DETAIL_6, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.GROUP_SETTING, RouteMeta.build(RouteType.ACTIVITY, GroupSettingActivity.class, JumpPath.GROUP_SETTING, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.HOME_PAGE, RouteMeta.build(RouteType.ACTIVITY, MainActivity.class, JumpPath.HOME_PAGE, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.LOGIN_PAGE, RouteMeta.build(RouteType.ACTIVITY, LoginActivity.class, JumpPath.LOGIN_PAGE, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.REGISTER_PAGE, RouteMeta.build(RouteType.ACTIVITY, RegisterActivity.class, JumpPath.REGISTER_PAGE, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.RESET_PAGE, RouteMeta.build(RouteType.ACTIVITY, ResetPasswordActivity.class, JumpPath.RESET_PAGE, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.RESUME_PAGE, RouteMeta.build(RouteType.ACTIVITY, DeviceResumeActivity.class, JumpPath.RESUME_PAGE, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.UPGRADE_PAGE, RouteMeta.build(RouteType.ACTIVITY, DeviceUpgradeActivity.class, JumpPath.UPGRADE_PAGE, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.WEB_VIEW_PAGE, RouteMeta.build(RouteType.ACTIVITY, WebViewActivity.class, JumpPath.WEB_VIEW_PAGE, "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
