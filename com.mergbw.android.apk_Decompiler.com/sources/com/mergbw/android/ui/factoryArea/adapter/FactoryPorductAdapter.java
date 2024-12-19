package com.mergbw.android.ui.factoryArea.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.mergbw.android.databinding.LayoutProductItemBinding;
import com.mergbw.core.network.factory.FactoryDataHelper;
import com.mergbw.core.network.factory.bean.FactoryProductInfoBean;
import com.mergbw.core.utils.UIUtils;
import java.util.ArrayList;
import java.util.List;

public class FactoryPorductAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private IFactoryProductClickListener mListener;
    private List<FactoryProductInfoBean.GoodsInfo> mProductList = new ArrayList();

    public void setData(List<FactoryProductInfoBean.GoodsInfo> list) {
        this.mProductList = list;
        notifyDataSetChanged();
    }

    public void setClickListener(IFactoryProductClickListener iFactoryProductClickListener) {
        this.mListener = iFactoryProductClickListener;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutProductItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        FactoryProductInfoBean.GoodsInfo goodsInfo = this.mProductList.get(i);
        itemViewHolder.mBinding.tvItemName.setText(goodsInfo.getGoodsName());
        RequestManager with = Glide.with((View) itemViewHolder.mBinding.ivItemIcon);
        with.load(FactoryDataHelper.getFactoryFileAddress() + goodsInfo.getGoodsPicture()).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new RoundedCorners((int) UIUtils.dp2px(12.0f)))).into(itemViewHolder.mBinding.ivItemIcon);
        itemViewHolder.mBinding.getRoot().setOnClickListener(new FactoryPorductAdapter$$ExternalSyntheticLambda0(this, itemViewHolder));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-android-ui-factoryArea-adapter-FactoryPorductAdapter  reason: not valid java name */
    public /* synthetic */ void m795lambda$onBindViewHolder$0$commergbwandroiduifactoryAreaadapterFactoryPorductAdapter(ItemViewHolder itemViewHolder, View view) {
        IFactoryProductClickListener iFactoryProductClickListener = this.mListener;
        if (iFactoryProductClickListener != null) {
            iFactoryProductClickListener.clickProductItem(this.mProductList.get(itemViewHolder.getLayoutPosition()));
        }
    }

    public int getItemCount() {
        return this.mProductList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutProductItemBinding mBinding;

        public ItemViewHolder(LayoutProductItemBinding layoutProductItemBinding) {
            super(layoutProductItemBinding.getRoot());
            this.mBinding = layoutProductItemBinding;
        }
    }
}
