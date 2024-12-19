package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.core.R;
import com.mergbw.core.ui.views.CommonColorPickerView;
import com.mergbw.core.ui.views.SubListView;

public final class ActivityDiySettingBinding implements ViewBinding {
    public final ImageView ivBack;
    public final LinearLayout layoutEffect;
    public final ConstraintLayout layoutHead;
    public final LinearLayout layoutSubItem;
    public final RecyclerView listSubItem;
    private final LinearLayout rootView;
    public final SeekBar sbSpeed;
    public final TextView tvAllCheck;
    public final TextView tvDeviceName;
    public final TextView tvEffect;
    public final TextView tvSave;
    public final TextView tvSpeed;
    public final TextView tvSpeedValue;
    public final TextView tvTitle;
    public final CommonColorPickerView viewCommonColorPicker;
    public final SubListView viewSubList;

    private ActivityDiySettingBinding(LinearLayout linearLayout, ImageView imageView, LinearLayout linearLayout2, ConstraintLayout constraintLayout, LinearLayout linearLayout3, RecyclerView recyclerView, SeekBar seekBar, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, CommonColorPickerView commonColorPickerView, SubListView subListView) {
        this.rootView = linearLayout;
        this.ivBack = imageView;
        this.layoutEffect = linearLayout2;
        this.layoutHead = constraintLayout;
        this.layoutSubItem = linearLayout3;
        this.listSubItem = recyclerView;
        this.sbSpeed = seekBar;
        this.tvAllCheck = textView;
        this.tvDeviceName = textView2;
        this.tvEffect = textView3;
        this.tvSave = textView4;
        this.tvSpeed = textView5;
        this.tvSpeedValue = textView6;
        this.tvTitle = textView7;
        this.viewCommonColorPicker = commonColorPickerView;
        this.viewSubList = subListView;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDiySettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityDiySettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_diy_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityDiySettingBinding bind(View view) {
        View view2 = view;
        int i = R.id.iv_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view2, i);
        if (imageView != null) {
            i = R.id.layout_effect;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view2, i);
            if (linearLayout != null) {
                i = R.id.layout_head;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view2, i);
                if (constraintLayout != null) {
                    i = R.id.layout_sub_item;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view2, i);
                    if (linearLayout2 != null) {
                        i = R.id.list_sub_item;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view2, i);
                        if (recyclerView != null) {
                            i = R.id.sb_speed;
                            SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(view2, i);
                            if (seekBar != null) {
                                i = R.id.tv_all_check;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view2, i);
                                if (textView != null) {
                                    i = R.id.tv_device_name;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view2, i);
                                    if (textView2 != null) {
                                        i = R.id.tv_effect;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view2, i);
                                        if (textView3 != null) {
                                            i = R.id.tv_save;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view2, i);
                                            if (textView4 != null) {
                                                i = R.id.tv_speed;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                if (textView5 != null) {
                                                    i = R.id.tv_speed_value;
                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                    if (textView6 != null) {
                                                        i = R.id.tv_title;
                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view2, i);
                                                        if (textView7 != null) {
                                                            i = R.id.view_common_color_picker;
                                                            CommonColorPickerView commonColorPickerView = (CommonColorPickerView) ViewBindings.findChildViewById(view2, i);
                                                            if (commonColorPickerView != null) {
                                                                i = R.id.view_sub_list;
                                                                SubListView subListView = (SubListView) ViewBindings.findChildViewById(view2, i);
                                                                if (subListView != null) {
                                                                    return new ActivityDiySettingBinding((LinearLayout) view2, imageView, linearLayout, constraintLayout, linearLayout2, recyclerView, seekBar, textView, textView2, textView3, textView4, textView5, textView6, textView7, commonColorPickerView, subListView);
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
