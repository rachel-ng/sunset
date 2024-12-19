package com.mergbw.android.ui.deviceDetail.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.android.databinding.LayoutSceneGroupBinding;
import com.mergbw.core.bean.SceneGroupData;
import java.util.ArrayList;
import java.util.List;

public class SceneGroupAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private int mCurrentIndex = 0;
    private List<SceneGroupData> mGroupList = new ArrayList();
    private ISceneGroupClickListener mListener;

    public void setData(List<SceneGroupData> list) {
        this.mGroupList = list;
    }

    public void setClickListener(ISceneGroupClickListener iSceneGroupClickListener) {
        this.mListener = iSceneGroupClickListener;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutSceneGroupBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.mBinding.groupName.setText(this.mGroupList.get(i).getGroupNameRes());
        if (this.mCurrentIndex == i) {
            itemViewHolder.mBinding.groupName.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else {
            itemViewHolder.mBinding.groupName.setTextColor(Color.parseColor("#88FFFFFF"));
        }
        itemViewHolder.mBinding.getRoot().setOnClickListener(new SceneGroupAdapter$$ExternalSyntheticLambda0(this, itemViewHolder));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-android-ui-deviceDetail-adapter-SceneGroupAdapter  reason: not valid java name */
    public /* synthetic */ void m723lambda$onBindViewHolder$0$commergbwandroiduideviceDetailadapterSceneGroupAdapter(ItemViewHolder itemViewHolder, View view) {
        this.mCurrentIndex = itemViewHolder.getLayoutPosition();
        notifyDataSetChanged();
        ISceneGroupClickListener iSceneGroupClickListener = this.mListener;
        if (iSceneGroupClickListener != null) {
            iSceneGroupClickListener.clickGroupItem(this.mGroupList.get(itemViewHolder.getLayoutPosition()));
        }
    }

    public int getItemCount() {
        return this.mGroupList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutSceneGroupBinding mBinding;

        public ItemViewHolder(LayoutSceneGroupBinding layoutSceneGroupBinding) {
            super(layoutSceneGroupBinding.getRoot());
            this.mBinding = layoutSceneGroupBinding;
        }
    }
}
