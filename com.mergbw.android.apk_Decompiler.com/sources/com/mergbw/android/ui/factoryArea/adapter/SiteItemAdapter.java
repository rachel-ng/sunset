package com.mergbw.android.ui.factoryArea.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.android.databinding.LayoutSiteItemBinding;
import com.mergbw.android.ui.factoryArea.FactoryDataUtils;
import com.mergbw.core.network.factory.bean.FactoryInfoBean;
import java.util.ArrayList;
import java.util.List;

public class SiteItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private int mCurrentIndex = -1;
    private ISiteItemClickListener mListener;
    private List<FactoryInfoBean.SiteInfo> mSiteList = new ArrayList();

    public void setData(List<FactoryInfoBean.SiteInfo> list, int i) {
        this.mSiteList = list;
        this.mCurrentIndex = i;
    }

    public void setClickListener(ISiteItemClickListener iSiteItemClickListener) {
        this.mListener = iSiteItemClickListener;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutSiteItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        FactoryInfoBean.SiteInfo siteInfo = this.mSiteList.get(i);
        itemViewHolder.mBinding.tvItemName.setText(siteInfo.getSiteName());
        itemViewHolder.mBinding.ivSelected.setVisibility(this.mCurrentIndex == i ? 0 : 4);
        if (FactoryDataUtils.COUNTRY_FLAG.get(siteInfo.getSiteCode()) != null) {
            itemViewHolder.mBinding.ivItemIcon.setImageResource(FactoryDataUtils.COUNTRY_FLAG.get(siteInfo.getSiteCode()).intValue());
        }
        itemViewHolder.mBinding.getRoot().setOnClickListener(new SiteItemAdapter$$ExternalSyntheticLambda0(this, itemViewHolder));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-android-ui-factoryArea-adapter-SiteItemAdapter  reason: not valid java name */
    public /* synthetic */ void m796lambda$onBindViewHolder$0$commergbwandroiduifactoryAreaadapterSiteItemAdapter(ItemViewHolder itemViewHolder, View view) {
        this.mCurrentIndex = itemViewHolder.getLayoutPosition();
        notifyDataSetChanged();
        ISiteItemClickListener iSiteItemClickListener = this.mListener;
        if (iSiteItemClickListener != null) {
            iSiteItemClickListener.clickSiteItem(this.mSiteList.get(itemViewHolder.getLayoutPosition()));
        }
    }

    public int getItemCount() {
        return this.mSiteList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutSiteItemBinding mBinding;

        public ItemViewHolder(LayoutSiteItemBinding layoutSiteItemBinding) {
            super(layoutSiteItemBinding.getRoot());
            this.mBinding = layoutSiteItemBinding;
        }
    }
}
