package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.mergbw.core.jump.JumpPath;
import com.mergbw.core.ui.DIYSetting.DIYSettingActivity;
import com.mergbw.core.ui.subColorSetting.SubColorSettingActivity;
import java.util.Map;

public class ARouter$$Group$$core implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(JumpPath.DEVICE_DIY, RouteMeta.build(RouteType.ACTIVITY, DIYSettingActivity.class, JumpPath.DEVICE_DIY, "core", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(JumpPath.SUB_SETTING, RouteMeta.build(RouteType.ACTIVITY, SubColorSettingActivity.class, JumpPath.SUB_SETTING, "core", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
