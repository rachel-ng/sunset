package com.google.android.gms.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.admanager.AppEventListener;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zzea;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzbep;
import com.google.android.gms.internal.ads.zzbgi;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class BaseAdView extends ViewGroup {
    @NotOnlyInitialized
    protected final zzea zza;

    protected BaseAdView(Context context, int i) {
        super(context);
        this.zza = new zzea(this, i);
    }

    public void destroy() {
        zzbep.zza(getContext());
        if (((Boolean) zzbgi.zze.zze()).booleanValue()) {
            if (((Boolean) zzba.zzc().zza(zzbep.zzld)).booleanValue()) {
                zzb.zzb.execute(new zzd(this));
                return;
            }
        }
        this.zza.zzk();
    }

    public AdListener getAdListener() {
        return this.zza.zza();
    }

    public AdSize getAdSize() {
        return this.zza.zzb();
    }

    public String getAdUnitId() {
        return this.zza.zzj();
    }

    public OnPaidEventListener getOnPaidEventListener() {
        return this.zza.zzc();
    }

    public ResponseInfo getResponseInfo() {
        return this.zza.zzd();
    }

    public boolean isCollapsible() {
        return this.zza.zzA();
    }

    public boolean isLoading() {
        return this.zza.zzB();
    }

    public void loadAd(AdRequest adRequest) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbep.zza(getContext());
        if (((Boolean) zzbgi.zzf.zze()).booleanValue()) {
            if (((Boolean) zzba.zzc().zza(zzbep.zzlg)).booleanValue()) {
                zzb.zzb.execute(new zzf(this, adRequest));
                return;
            }
        }
        this.zza.zzm(adRequest.zza);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getVisibility() != 8) {
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i5 = ((i3 - i) - measuredWidth) / 2;
            int i6 = ((i4 - i2) - measuredHeight) / 2;
            childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        AdSize adSize;
        int i4 = 0;
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            try {
                adSize = getAdSize();
            } catch (NullPointerException e) {
                zzm.zzh("Unable to retrieve ad size.", e);
                adSize = null;
            }
            if (adSize != null) {
                Context context = getContext();
                int widthInPixels = adSize.getWidthInPixels(context);
                i3 = adSize.getHeightInPixels(context);
                i4 = widthInPixels;
            } else {
                i3 = 0;
            }
        } else {
            measureChild(childAt, i, i2);
            i4 = childAt.getMeasuredWidth();
            i3 = childAt.getMeasuredHeight();
        }
        setMeasuredDimension(View.resolveSize(Math.max(i4, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(i3, getSuggestedMinimumHeight()), i2));
    }

    public void pause() {
        zzbep.zza(getContext());
        if (((Boolean) zzbgi.zzg.zze()).booleanValue()) {
            if (((Boolean) zzba.zzc().zza(zzbep.zzle)).booleanValue()) {
                zzb.zzb.execute(new zze(this));
                return;
            }
        }
        this.zza.zzn();
    }

    public void resume() {
        zzbep.zza(getContext());
        if (((Boolean) zzbgi.zzh.zze()).booleanValue()) {
            if (((Boolean) zzba.zzc().zza(zzbep.zzlc)).booleanValue()) {
                zzb.zzb.execute(new zzc(this));
                return;
            }
        }
        this.zza.zzp();
    }

    public void setAdListener(AdListener adListener) {
        this.zza.zzr(adListener);
        if (adListener == null) {
            this.zza.zzq((zza) null);
            return;
        }
        if (adListener instanceof zza) {
            this.zza.zzq((zza) adListener);
        }
        if (adListener instanceof AppEventListener) {
            this.zza.zzv((AppEventListener) adListener);
        }
    }

    public void setAdSize(AdSize adSize) {
        this.zza.zzs(adSize);
    }

    public void setAdUnitId(String str) {
        this.zza.zzu(str);
    }

    public void setOnPaidEventListener(OnPaidEventListener onPaidEventListener) {
        this.zza.zzx(onPaidEventListener);
    }

    protected BaseAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.zza = new zzea(this, attributeSet, false, i);
    }

    protected BaseAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.zza = new zzea(this, attributeSet, false, i2);
    }

    protected BaseAdView(Context context, AttributeSet attributeSet, int i, int i2, boolean z) {
        super(context, attributeSet, i);
        this.zza = new zzea(this, attributeSet, z, i2);
    }

    protected BaseAdView(Context context, AttributeSet attributeSet, boolean z) {
        super(context, attributeSet);
        this.zza = new zzea(this, attributeSet, z);
    }
}
