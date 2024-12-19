package com.mergbw.android.ui.deviceDetail.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.android.databinding.LayoutSceneItem2Binding;
import com.mergbw.core.R;
import com.mergbw.core.bean.SceneData;
import java.util.ArrayList;
import java.util.List;

public class SceneItemAdapter2 extends RecyclerView.Adapter<ItemViewHolder> {
    private int mCurrentIndex = -1;
    private List<SceneData> mGroupList = new ArrayList();
    private ISceneItemClickListener mListener;

    public void setData(List<SceneData> list) {
        this.mGroupList = list;
        this.mCurrentIndex = -1;
    }

    public void resetSelected() {
        this.mCurrentIndex = -1;
        notifyDataSetChanged();
    }

    public void setClickListener(ISceneItemClickListener iSceneItemClickListener) {
        this.mListener = iSceneItemClickListener;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutSceneItem2Binding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.mBinding.tvItemName.setText(this.mGroupList.get(i).getSceneNameRes());
        if (this.mCurrentIndex == i) {
            itemViewHolder.mBinding.getRoot().setBackgroundResource(R.drawable.bg_main_item_selected_solid);
        } else {
            itemViewHolder.mBinding.getRoot().setBackground((Drawable) null);
        }
        itemViewHolder.mBinding.getRoot().setOnClickListener(new SceneItemAdapter2$$ExternalSyntheticLambda0(this, itemViewHolder));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-android-ui-deviceDetail-adapter-SceneItemAdapter2  reason: not valid java name */
    public /* synthetic */ void m725lambda$onBindViewHolder$0$commergbwandroiduideviceDetailadapterSceneItemAdapter2(ItemViewHolder itemViewHolder, View view) {
        this.mCurrentIndex = itemViewHolder.getLayoutPosition();
        notifyDataSetChanged();
        ISceneItemClickListener iSceneItemClickListener = this.mListener;
        if (iSceneItemClickListener != null) {
            iSceneItemClickListener.clickSceneItem(this.mGroupList.get(itemViewHolder.getLayoutPosition()));
        }
    }

    public int getItemCount() {
        return this.mGroupList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutSceneItem2Binding mBinding;

        public ItemViewHolder(LayoutSceneItem2Binding layoutSceneItem2Binding) {
            super(layoutSceneItem2Binding.getRoot());
            this.mBinding = layoutSceneItem2Binding;
        }
    }
}
