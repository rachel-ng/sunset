package com.youth.banner.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.youth.banner.R;
import com.youth.banner.holder.IViewHolder;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;
import java.util.ArrayList;
import java.util.List;

public abstract class BannerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements IViewHolder<T, VH> {
    protected List<T> mDatas = new ArrayList();
    private int mIncreaseCount = 2;
    private OnBannerListener<T> mOnBannerListener;
    private VH mViewHolder;

    public BannerAdapter(List<T> list) {
        setDatas(list);
    }

    public void setDatas(List<T> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.mDatas.clear();
        this.mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public T getData(int i) {
        if (i > this.mDatas.size() - 1) {
            return null;
        }
        return this.mDatas.get(i);
    }

    public T getRealData(int i) {
        int realPosition = getRealPosition(i);
        if (realPosition > this.mDatas.size() - 1) {
            return null;
        }
        return this.mDatas.get(realPosition);
    }

    public final void onBindViewHolder(VH vh, int i) {
        this.mViewHolder = vh;
        int realPosition = getRealPosition(i);
        T t = this.mDatas.get(realPosition);
        vh.itemView.setTag(R.id.banner_data_key, t);
        vh.itemView.setTag(R.id.banner_pos_key, Integer.valueOf(realPosition));
        onBindView(vh, this.mDatas.get(realPosition), realPosition, getRealCount());
        if (this.mOnBannerListener != null) {
            vh.itemView.setOnClickListener(new BannerAdapter$$ExternalSyntheticLambda1(this, t, realPosition));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-youth-banner-adapter-BannerAdapter  reason: not valid java name */
    public /* synthetic */ void m980lambda$onBindViewHolder$0$comyouthbanneradapterBannerAdapter(Object obj, int i, View view) {
        this.mOnBannerListener.OnBannerClick(obj, i);
    }

    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        VH vh = (RecyclerView.ViewHolder) onCreateHolder(viewGroup, i);
        vh.itemView.setOnClickListener(new BannerAdapter$$ExternalSyntheticLambda0(this, vh));
        return vh;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCreateViewHolder$1$com-youth-banner-adapter-BannerAdapter  reason: not valid java name */
    public /* synthetic */ void m981lambda$onCreateViewHolder$1$comyouthbanneradapterBannerAdapter(RecyclerView.ViewHolder viewHolder, View view) {
        if (this.mOnBannerListener != null) {
            this.mOnBannerListener.OnBannerClick(viewHolder.itemView.getTag(R.id.banner_data_key), ((Integer) viewHolder.itemView.getTag(R.id.banner_pos_key)).intValue());
        }
    }

    public int getItemCount() {
        return getRealCount() > 1 ? getRealCount() + this.mIncreaseCount : getRealCount();
    }

    public int getRealCount() {
        List<T> list = this.mDatas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int getRealPosition(int i) {
        return BannerUtils.getRealPosition(this.mIncreaseCount == 2, i, getRealCount());
    }

    public void setOnBannerListener(OnBannerListener<T> onBannerListener) {
        this.mOnBannerListener = onBannerListener;
    }

    public VH getViewHolder() {
        return this.mViewHolder;
    }

    public void setIncreaseCount(int i) {
        this.mIncreaseCount = i;
    }
}
