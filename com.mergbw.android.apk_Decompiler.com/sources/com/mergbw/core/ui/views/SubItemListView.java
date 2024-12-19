package com.mergbw.core.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.mergbw.core.bean.SubItemBean;
import com.mergbw.core.databinding.LayoutSubItemListBinding;
import java.util.List;

public class SubItemListView extends LinearLayout {
    private LayoutSubItemListBinding mBinding;

    public SubItemListView(Context context) {
        super(context);
    }

    public SubItemListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutSubItemListBinding inflate = LayoutSubItemListBinding.inflate(LayoutInflater.from(context), this, true);
        this.mBinding = inflate;
        inflate.viewSub01.setType(1);
        this.mBinding.viewSub05.setType(2);
        this.mBinding.viewSub06.setType(3);
        this.mBinding.viewSub10.setType(4);
        this.mBinding.viewSub11.setType(5);
        this.mBinding.viewSub15.setType(2);
        this.mBinding.viewSub16.setType(3);
        this.mBinding.viewSub20.setType(1);
    }

    public void refreshData(List<SubItemBean> list) {
        this.mBinding.viewSub01.setColor(list.get(0).getColor());
        this.mBinding.viewSub02.setColor(list.get(1).getColor());
        this.mBinding.viewSub03.setColor(list.get(2).getColor());
        this.mBinding.viewSub04.setColor(list.get(3).getColor());
        this.mBinding.viewSub05.setColor(list.get(4).getColor());
        this.mBinding.viewSub06.setColor(list.get(5).getColor());
        this.mBinding.viewSub07.setColor(list.get(6).getColor());
        this.mBinding.viewSub08.setColor(list.get(7).getColor());
        this.mBinding.viewSub09.setColor(list.get(8).getColor());
        this.mBinding.viewSub10.setColor(list.get(9).getColor());
        this.mBinding.viewSub11.setColor(list.get(10).getColor());
        this.mBinding.viewSub12.setColor(list.get(11).getColor());
        this.mBinding.viewSub13.setColor(list.get(12).getColor());
        this.mBinding.viewSub14.setColor(list.get(13).getColor());
        this.mBinding.viewSub15.setColor(list.get(14).getColor());
        this.mBinding.viewSub16.setColor(list.get(15).getColor());
        this.mBinding.viewSub17.setColor(list.get(16).getColor());
        this.mBinding.viewSub18.setColor(list.get(17).getColor());
        this.mBinding.viewSub19.setColor(list.get(18).getColor());
        this.mBinding.viewSub20.setColor(list.get(19).getColor());
    }
}
