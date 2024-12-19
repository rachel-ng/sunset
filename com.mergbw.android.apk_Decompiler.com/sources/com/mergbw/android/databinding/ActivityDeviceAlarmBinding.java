package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class ActivityDeviceAlarmBinding implements ViewBinding {
    public final Button btnSave;
    public final CheckBox cbCloseWeek1;
    public final CheckBox cbCloseWeek2;
    public final CheckBox cbCloseWeek3;
    public final CheckBox cbCloseWeek4;
    public final CheckBox cbCloseWeek5;
    public final CheckBox cbCloseWeek6;
    public final CheckBox cbCloseWeek7;
    public final CheckBox cbOpenWeek1;
    public final CheckBox cbOpenWeek2;
    public final CheckBox cbOpenWeek3;
    public final CheckBox cbOpenWeek4;
    public final CheckBox cbOpenWeek5;
    public final CheckBox cbOpenWeek6;
    public final CheckBox cbOpenWeek7;
    public final ImageView ivBack;
    public final LinearLayout layoutCloseTime;
    public final RelativeLayout layoutHead;
    public final LinearLayout layoutOpenTime;
    private final LinearLayout rootView;
    public final SwitchCompat switchClose;
    public final SwitchCompat switchOpen;
    public final TextView tvCloseRepeat;
    public final TextView tvCloseRepeatTime;
    public final TextView tvCloseTime;
    public final TextView tvCloseTitle;
    public final TextView tvOpenRepeat;
    public final TextView tvOpenRepeatTime;
    public final TextView tvOpenTime;
    public final TextView tvOpenTitle;

    private ActivityDeviceAlarmBinding(LinearLayout linearLayout, Button button, CheckBox checkBox, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4, CheckBox checkBox5, CheckBox checkBox6, CheckBox checkBox7, CheckBox checkBox8, CheckBox checkBox9, CheckBox checkBox10, CheckBox checkBox11, CheckBox checkBox12, CheckBox checkBox13, CheckBox checkBox14, ImageView imageView, LinearLayout linearLayout2, RelativeLayout relativeLayout, LinearLayout linearLayout3, SwitchCompat switchCompat, SwitchCompat switchCompat2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        this.rootView = linearLayout;
        this.btnSave = button;
        this.cbCloseWeek1 = checkBox;
        this.cbCloseWeek2 = checkBox2;
        this.cbCloseWeek3 = checkBox3;
        this.cbCloseWeek4 = checkBox4;
        this.cbCloseWeek5 = checkBox5;
        this.cbCloseWeek6 = checkBox6;
        this.cbCloseWeek7 = checkBox7;
        this.cbOpenWeek1 = checkBox8;
        this.cbOpenWeek2 = checkBox9;
        this.cbOpenWeek3 = checkBox10;
        this.cbOpenWeek4 = checkBox11;
        this.cbOpenWeek5 = checkBox12;
        this.cbOpenWeek6 = checkBox13;
        this.cbOpenWeek7 = checkBox14;
        this.ivBack = imageView;
        this.layoutCloseTime = linearLayout2;
        this.layoutHead = relativeLayout;
        this.layoutOpenTime = linearLayout3;
        this.switchClose = switchCompat;
        this.switchOpen = switchCompat2;
        this.tvCloseRepeat = textView;
        this.tvCloseRepeatTime = textView2;
        this.tvCloseTime = textView3;
        this.tvCloseTitle = textView4;
        this.tvOpenRepeat = textView5;
        this.tvOpenRepeatTime = textView6;
        this.tvOpenTime = textView7;
        this.tvOpenTitle = textView8;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDeviceAlarmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityDeviceAlarmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_device_alarm, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityDeviceAlarmBinding bind(View view) {
        View view2 = view;
        int i = R.id.btn_save;
        Button button = (Button) ViewBindings.findChildViewById(view2, i);
        if (button != null) {
            i = R.id.cb_close_week_1;
            CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view2, i);
            if (checkBox != null) {
                i = R.id.cb_close_week_2;
                CheckBox checkBox2 = (CheckBox) ViewBindings.findChildViewById(view2, i);
                if (checkBox2 != null) {
                    i = R.id.cb_close_week_3;
                    CheckBox checkBox3 = (CheckBox) ViewBindings.findChildViewById(view2, i);
                    if (checkBox3 != null) {
                        i = R.id.cb_close_week_4;
                        CheckBox checkBox4 = (CheckBox) ViewBindings.findChildViewById(view2, i);
                        if (checkBox4 != null) {
                            i = R.id.cb_close_week_5;
                            CheckBox checkBox5 = (CheckBox) ViewBindings.findChildViewById(view2, i);
                            if (checkBox5 != null) {
                                i = R.id.cb_close_week_6;
                                CheckBox checkBox6 = (CheckBox) ViewBindings.findChildViewById(view2, i);
                                if (checkBox6 != null) {
                                    i = R.id.cb_close_week_7;
                                    CheckBox checkBox7 = (CheckBox) ViewBindings.findChildViewById(view2, i);
                                    if (checkBox7 != null) {
                                        i = R.id.cb_open_week_1;
                                        CheckBox checkBox8 = (CheckBox) ViewBindings.findChildViewById(view2, i);
                                        if (checkBox8 != null) {
                                            i = R.id.cb_open_week_2;
                                            CheckBox checkBox9 = (CheckBox) ViewBindings.findChildViewById(view2, i);
                                            if (checkBox9 != null) {
                                                i = R.id.cb_open_week_3;
                                                CheckBox checkBox10 = (CheckBox) ViewBindings.findChildViewById(view2, i);
                                                if (checkBox10 != null) {
                                                    i = R.id.cb_open_week_4;
                                                    CheckBox checkBox11 = (CheckBox) ViewBindings.findChildViewById(view2, i);
                                                    if (checkBox11 != null) {
                                                        i = R.id.cb_open_week_5;
                                                        CheckBox checkBox12 = (CheckBox) ViewBindings.findChildViewById(view2, i);
                                                        if (checkBox12 != null) {
                                                            i = R.id.cb_open_week_6;
                                                            CheckBox checkBox13 = (CheckBox) ViewBindings.findChildViewById(view2, i);
                                                            if (checkBox13 != null) {
                                                                i = R.id.cb_open_week_7;
                                                                CheckBox checkBox14 = (CheckBox) ViewBindings.findChildViewById(view2, i);
                                                                if (checkBox14 != null) {
                                                                    i = R.id.iv_back;
                                                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view2, i);
                                                                    if (imageView != null) {
                                                                        i = R.id.layout_close_time;
                                                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view2, i);
                                                                        if (linearLayout != null) {
                                                                            i = R.id.layout_head;
                                                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view2, i);
                                                                            if (relativeLayout != null) {
                                                                                i = R.id.layout_open_time;
                                                                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view2, i);
                                                                                if (linearLayout2 != null) {
                                                                                    i = R.id.switch_close;
                                                                                    SwitchCompat switchCompat = (SwitchCompat) ViewBindings.findChildViewById(view2, i);
                                                                                    if (switchCompat != null) {
                                                                                        i = R.id.switch_open;
                                                                                        SwitchCompat switchCompat2 = (SwitchCompat) ViewBindings.findChildViewById(view2, i);
                                                                                        if (switchCompat2 != null) {
                                                                                            i = R.id.tv_close_repeat;
                                                                                            TextView textView = (TextView) ViewBindings.findChildViewById(view2, i);
                                                                                            if (textView != null) {
                                                                                                i = R.id.tv_close_repeat_time;
                                                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                                                                if (textView2 != null) {
                                                                                                    i = R.id.tv_close_time;
                                                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                                                                    if (textView3 != null) {
                                                                                                        i = R.id.tv_close_title;
                                                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                                                                        if (textView4 != null) {
                                                                                                            i = R.id.tv_open_repeat;
                                                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                                                                            if (textView5 != null) {
                                                                                                                i = R.id.tv_open_repeat_time;
                                                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                                                                                if (textView6 != null) {
                                                                                                                    i = R.id.tv_open_time;
                                                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                                                                                    if (textView7 != null) {
                                                                                                                        i = R.id.tv_open_title;
                                                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                                                                                        if (textView8 != null) {
                                                                                                                            return new ActivityDeviceAlarmBinding((LinearLayout) view2, button, checkBox, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10, checkBox11, checkBox12, checkBox13, checkBox14, imageView, linearLayout, relativeLayout, linearLayout2, switchCompat, switchCompat2, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
