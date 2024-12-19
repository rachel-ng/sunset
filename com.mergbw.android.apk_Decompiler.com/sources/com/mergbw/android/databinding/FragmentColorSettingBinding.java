package com.mergbw.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.android.R;
import com.mergbw.core.ui.views.CommonColorPickerView;
import com.mergbw.core.ui.views.CustomScrollView;

public final class FragmentColorSettingBinding implements ViewBinding {
    public final Button btnSave;
    private final CustomScrollView rootView;
    public final CommonColorPickerView viewCommonColorPicker;

    private FragmentColorSettingBinding(CustomScrollView customScrollView, Button button, CommonColorPickerView commonColorPickerView) {
        this.rootView = customScrollView;
        this.btnSave = button;
        this.viewCommonColorPicker = commonColorPickerView;
    }

    public CustomScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentColorSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentColorSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_color_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentColorSettingBinding bind(View view) {
        int i = R.id.btn_save;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R.id.view_common_color_picker;
            CommonColorPickerView commonColorPickerView = (CommonColorPickerView) ViewBindings.findChildViewById(view, i);
            if (commonColorPickerView != null) {
                return new FragmentColorSettingBinding((CustomScrollView) view, button, commonColorPickerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
