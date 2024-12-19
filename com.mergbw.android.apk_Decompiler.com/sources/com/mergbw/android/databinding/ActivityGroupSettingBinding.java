package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;

public final class ActivityGroupSettingBinding implements ViewBinding {
    public final Button btnDelete;
    public final ImageView ivBack;
    public final FrameLayout layoutBottom;
    public final ConstraintLayout layoutGroupName;
    public final RelativeLayout layoutHead;
    public final ExpandableListView listDevice;
    private final RelativeLayout rootView;
    public final TextView tvGroupName;
    public final TextView tvSave;
    public final TextView tvTitle;

    private ActivityGroupSettingBinding(RelativeLayout relativeLayout, Button button, ImageView imageView, FrameLayout frameLayout, ConstraintLayout constraintLayout, RelativeLayout relativeLayout2, ExpandableListView expandableListView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.btnDelete = button;
        this.ivBack = imageView;
        this.layoutBottom = frameLayout;
        this.layoutGroupName = constraintLayout;
        this.layoutHead = relativeLayout2;
        this.listDevice = expandableListView;
        this.tvGroupName = textView;
        this.tvSave = textView2;
        this.tvTitle = textView3;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityGroupSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityGroupSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_group_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityGroupSettingBinding bind(View view) {
        int i = R.id.btn_delete;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R.id.iv_back;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.layout_bottom;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                if (frameLayout != null) {
                    i = R.id.layout_group_name;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                    if (constraintLayout != null) {
                        i = R.id.layout_head;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i);
                        if (relativeLayout != null) {
                            i = R.id.list_device;
                            ExpandableListView expandableListView = (ExpandableListView) ViewBindings.findChildViewById(view, i);
                            if (expandableListView != null) {
                                i = R.id.tv_group_name;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView != null) {
                                    i = R.id.tv_save;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView2 != null) {
                                        i = R.id.tv_title;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView3 != null) {
                                            return new ActivityGroupSettingBinding((RelativeLayout) view, button, imageView, frameLayout, constraintLayout, relativeLayout, expandableListView, textView, textView2, textView3);
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
