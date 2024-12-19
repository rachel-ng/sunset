package com.mergbw.android.ui.deviceDetail.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.android.databinding.LayoutWhiteNoiseItemBinding;
import com.mergbw.core.R;
import com.mergbw.core.bean.SceneData;
import java.util.ArrayList;
import java.util.List;

public class WhiteNoiseItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private int mCurrentIndex = -1;
    private List<SceneData> mGroupList = new ArrayList();
    private IAudioItemClickListener mListener;

    public void setData(List<SceneData> list) {
        this.mGroupList = list;
        this.mCurrentIndex = -1;
    }

    public void resetSelected() {
        this.mCurrentIndex = -1;
        notifyDataSetChanged();
    }

    public void setClickListener(IAudioItemClickListener iAudioItemClickListener) {
        this.mListener = iAudioItemClickListener;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutWhiteNoiseItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        SceneData sceneData = this.mGroupList.get(i);
        itemViewHolder.mBinding.tvItemName.setText(sceneData.getSceneNameRes());
        if (this.mCurrentIndex == i) {
            itemViewHolder.mBinding.layoutIcon.setBackgroundResource(R.drawable.bg_item_stroke);
            itemViewHolder.mBinding.viewPlaying.setVisibility(0);
            itemViewHolder.mBinding.ivPlayState.setVisibility(0);
            itemViewHolder.mBinding.ivPlayState.setImageResource(sceneData.isPlaying() ? R.mipmap.icon_pause : R.mipmap.icon_play);
            if (sceneData.isPlaying()) {
                itemViewHolder.mBinding.viewPlaying.playAnimation();
            } else {
                itemViewHolder.mBinding.viewPlaying.pauseAnimation();
            }
        } else {
            itemViewHolder.mBinding.layoutIcon.setBackground((Drawable) null);
            itemViewHolder.mBinding.viewPlaying.setVisibility(8);
            itemViewHolder.mBinding.ivPlayState.setVisibility(8);
        }
        itemViewHolder.mBinding.ivItemIcon.setImageResource(sceneData.getIconRes());
        itemViewHolder.mBinding.getRoot().setOnClickListener(new WhiteNoiseItemAdapter$$ExternalSyntheticLambda0(this, itemViewHolder, sceneData));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-android-ui-deviceDetail-adapter-WhiteNoiseItemAdapter  reason: not valid java name */
    public /* synthetic */ void m729lambda$onBindViewHolder$0$commergbwandroiduideviceDetailadapterWhiteNoiseItemAdapter(ItemViewHolder itemViewHolder, SceneData sceneData, View view) {
        if (this.mCurrentIndex == itemViewHolder.getLayoutPosition()) {
            if (sceneData.isPlaying()) {
                IAudioItemClickListener iAudioItemClickListener = this.mListener;
                if (iAudioItemClickListener != null) {
                    iAudioItemClickListener.pause();
                }
            } else {
                IAudioItemClickListener iAudioItemClickListener2 = this.mListener;
                if (iAudioItemClickListener2 != null) {
                    iAudioItemClickListener2.resume();
                }
            }
            sceneData.setPlaying(!sceneData.isPlaying());
        } else {
            this.mCurrentIndex = itemViewHolder.getLayoutPosition();
            sceneData.setPlaying(true);
            IAudioItemClickListener iAudioItemClickListener3 = this.mListener;
            if (iAudioItemClickListener3 != null) {
                iAudioItemClickListener3.startPlay(this.mGroupList.get(itemViewHolder.getLayoutPosition()));
            }
        }
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.mGroupList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutWhiteNoiseItemBinding mBinding;

        public ItemViewHolder(LayoutWhiteNoiseItemBinding layoutWhiteNoiseItemBinding) {
            super(layoutWhiteNoiseItemBinding.getRoot());
            this.mBinding = layoutWhiteNoiseItemBinding;
        }
    }
}
