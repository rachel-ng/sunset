package com.mergbw.android.ui.deviceDetail.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.android.databinding.LayoutSceneItemBinding;
import com.mergbw.core.R;
import com.mergbw.core.bean.SceneData;
import java.util.ArrayList;
import java.util.List;

public class SceneItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
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
        return new ItemViewHolder(LayoutSceneItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        SceneData sceneData = this.mGroupList.get(i);
        itemViewHolder.mBinding.tvItemName.setText(sceneData.getSceneNameRes());
        if (this.mCurrentIndex == i) {
            itemViewHolder.mBinding.layoutIcon.setBackgroundResource(R.drawable.bg_item_stroke);
        } else {
            itemViewHolder.mBinding.layoutIcon.setBackground((Drawable) null);
        }
        itemViewHolder.mBinding.ivItemIcon.setImageResource(sceneData.getIconRes());
        itemViewHolder.mBinding.getRoot().setOnClickListener(new SceneItemAdapter$$ExternalSyntheticLambda0(this, itemViewHolder));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-android-ui-deviceDetail-adapter-SceneItemAdapter  reason: not valid java name */
    public /* synthetic */ void m724lambda$onBindViewHolder$0$commergbwandroiduideviceDetailadapterSceneItemAdapter(ItemViewHolder itemViewHolder, View view) {
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
        public final LayoutSceneItemBinding mBinding;

        public ItemViewHolder(LayoutSceneItemBinding layoutSceneItemBinding) {
            super(layoutSceneItemBinding.getRoot());
            this.mBinding = layoutSceneItemBinding;
        }
    }
}
