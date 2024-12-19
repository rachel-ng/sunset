package com.mergbw.android.ui.groupDetail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.android.databinding.LayoutGroupSceneItem6Binding;
import com.mergbw.android.ui.deviceDetail.adapter.ISceneItemClickListener;
import com.mergbw.core.R;
import com.mergbw.core.bean.SceneData;
import java.util.ArrayList;
import java.util.List;

public class GroupSceneItemAdapter6 extends RecyclerView.Adapter<ItemViewHolder> {
    private int mCurrentIndex = -1;
    private List<SceneData> mGroupList = new ArrayList();
    private ISceneItemClickListener mListener;

    public void setData(List<SceneData> list) {
        this.mGroupList = list;
        this.mCurrentIndex = -1;
    }

    public void setClickListener(ISceneItemClickListener iSceneItemClickListener) {
        this.mListener = iSceneItemClickListener;
    }

    public void resetSelected() {
        this.mCurrentIndex = -1;
        notifyDataSetChanged();
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutGroupSceneItem6Binding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        SceneData sceneData = this.mGroupList.get(i);
        itemViewHolder.mBinding.tvItemName.setText(sceneData.getSceneNameRes());
        if (this.mCurrentIndex == i) {
            itemViewHolder.mBinding.getRoot().setBackgroundResource(R.drawable.bg_type_5_item);
        } else {
            itemViewHolder.mBinding.getRoot().setBackgroundResource(R.drawable.bg_group_item_solid);
        }
        itemViewHolder.mBinding.ivItemIcon.setImageResource(sceneData.getIconRes());
        itemViewHolder.mBinding.getRoot().setOnClickListener(new GroupSceneItemAdapter6$$ExternalSyntheticLambda0(this, itemViewHolder));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-android-ui-groupDetail-adapter-GroupSceneItemAdapter6  reason: not valid java name */
    public /* synthetic */ void m811lambda$onBindViewHolder$0$commergbwandroiduigroupDetailadapterGroupSceneItemAdapter6(ItemViewHolder itemViewHolder, View view) {
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
        public final LayoutGroupSceneItem6Binding mBinding;

        public ItemViewHolder(LayoutGroupSceneItem6Binding layoutGroupSceneItem6Binding) {
            super(layoutGroupSceneItem6Binding.getRoot());
            this.mBinding = layoutGroupSceneItem6Binding;
        }
    }
}
