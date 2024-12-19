package com.mergbw.core.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mergbw.core.R;
import com.mergbw.core.ui.views.SubItemView;

public final class LayoutSubItemListBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final SubItemView viewSub01;
    public final SubItemView viewSub02;
    public final SubItemView viewSub03;
    public final SubItemView viewSub04;
    public final SubItemView viewSub05;
    public final SubItemView viewSub06;
    public final SubItemView viewSub07;
    public final SubItemView viewSub08;
    public final SubItemView viewSub09;
    public final SubItemView viewSub10;
    public final SubItemView viewSub11;
    public final SubItemView viewSub12;
    public final SubItemView viewSub13;
    public final SubItemView viewSub14;
    public final SubItemView viewSub15;
    public final SubItemView viewSub16;
    public final SubItemView viewSub17;
    public final SubItemView viewSub18;
    public final SubItemView viewSub19;
    public final SubItemView viewSub20;

    private LayoutSubItemListBinding(LinearLayout linearLayout, SubItemView subItemView, SubItemView subItemView2, SubItemView subItemView3, SubItemView subItemView4, SubItemView subItemView5, SubItemView subItemView6, SubItemView subItemView7, SubItemView subItemView8, SubItemView subItemView9, SubItemView subItemView10, SubItemView subItemView11, SubItemView subItemView12, SubItemView subItemView13, SubItemView subItemView14, SubItemView subItemView15, SubItemView subItemView16, SubItemView subItemView17, SubItemView subItemView18, SubItemView subItemView19, SubItemView subItemView20) {
        this.rootView = linearLayout;
        this.viewSub01 = subItemView;
        this.viewSub02 = subItemView2;
        this.viewSub03 = subItemView3;
        this.viewSub04 = subItemView4;
        this.viewSub05 = subItemView5;
        this.viewSub06 = subItemView6;
        this.viewSub07 = subItemView7;
        this.viewSub08 = subItemView8;
        this.viewSub09 = subItemView9;
        this.viewSub10 = subItemView10;
        this.viewSub11 = subItemView11;
        this.viewSub12 = subItemView12;
        this.viewSub13 = subItemView13;
        this.viewSub14 = subItemView14;
        this.viewSub15 = subItemView15;
        this.viewSub16 = subItemView16;
        this.viewSub17 = subItemView17;
        this.viewSub18 = subItemView18;
        this.viewSub19 = subItemView19;
        this.viewSub20 = subItemView20;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutSubItemListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static LayoutSubItemListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_sub_item_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutSubItemListBinding bind(View view) {
        View view2 = view;
        int i = R.id.view_sub_01;
        SubItemView subItemView = (SubItemView) ViewBindings.findChildViewById(view2, i);
        if (subItemView != null) {
            i = R.id.view_sub_02;
            SubItemView subItemView2 = (SubItemView) ViewBindings.findChildViewById(view2, i);
            if (subItemView2 != null) {
                i = R.id.view_sub_03;
                SubItemView subItemView3 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                if (subItemView3 != null) {
                    i = R.id.view_sub_04;
                    SubItemView subItemView4 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                    if (subItemView4 != null) {
                        i = R.id.view_sub_05;
                        SubItemView subItemView5 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                        if (subItemView5 != null) {
                            i = R.id.view_sub_06;
                            SubItemView subItemView6 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                            if (subItemView6 != null) {
                                i = R.id.view_sub_07;
                                SubItemView subItemView7 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                                if (subItemView7 != null) {
                                    i = R.id.view_sub_08;
                                    SubItemView subItemView8 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                                    if (subItemView8 != null) {
                                        i = R.id.view_sub_09;
                                        SubItemView subItemView9 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                                        if (subItemView9 != null) {
                                            i = R.id.view_sub_10;
                                            SubItemView subItemView10 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                                            if (subItemView10 != null) {
                                                i = R.id.view_sub_11;
                                                SubItemView subItemView11 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                                                if (subItemView11 != null) {
                                                    i = R.id.view_sub_12;
                                                    SubItemView subItemView12 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                                                    if (subItemView12 != null) {
                                                        i = R.id.view_sub_13;
                                                        SubItemView subItemView13 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                                                        if (subItemView13 != null) {
                                                            i = R.id.view_sub_14;
                                                            SubItemView subItemView14 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                                                            if (subItemView14 != null) {
                                                                i = R.id.view_sub_15;
                                                                SubItemView subItemView15 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                                                                if (subItemView15 != null) {
                                                                    i = R.id.view_sub_16;
                                                                    SubItemView subItemView16 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                                                                    if (subItemView16 != null) {
                                                                        i = R.id.view_sub_17;
                                                                        SubItemView subItemView17 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                                                                        if (subItemView17 != null) {
                                                                            i = R.id.view_sub_18;
                                                                            SubItemView subItemView18 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                                                                            if (subItemView18 != null) {
                                                                                i = R.id.view_sub_19;
                                                                                SubItemView subItemView19 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                                                                                if (subItemView19 != null) {
                                                                                    i = R.id.view_sub_20;
                                                                                    SubItemView subItemView20 = (SubItemView) ViewBindings.findChildViewById(view2, i);
                                                                                    if (subItemView20 != null) {
                                                                                        return new LayoutSubItemListBinding((LinearLayout) view2, subItemView, subItemView2, subItemView3, subItemView4, subItemView5, subItemView6, subItemView7, subItemView8, subItemView9, subItemView10, subItemView11, subItemView12, subItemView13, subItemView14, subItemView15, subItemView16, subItemView17, subItemView18, subItemView19, subItemView20);
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
