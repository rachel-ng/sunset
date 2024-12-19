package com.mergbw.core.track;

import com.google.android.exoplayer2.util.MimeTypes;

public class TrackConstants {
    public static final int CLICK_ABOUT_US = 3;
    public static final int CLICK_ADD_DEVICE = 1;
    public static final int CLICK_CONTACT_US = 2;

    public enum EventType {
        APP_LAUNCHER(1),
        PAGE_ITEM_CLICK(2);
        
        public final int value;

        private EventType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum Page {
        APPLICATION(MimeTypes.BASE_TYPE_APPLICATION),
        MAIN_PAGE("main_page"),
        ADD_DEVICE_PAGE("add_device_page"),
        DEVICE_DETAIL_PAGE("device_detail_page"),
        ALARM_SETTING_PAGE("alarm_setting_page"),
        DEVICE_SETTING_PAGE("device_setting_page"),
        GROUP_DETAIL_PAGE("group_detail_page"),
        GROUP_SETTING_PAGE("group_setting_page");
        
        public final String value;

        private Page(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }
}
