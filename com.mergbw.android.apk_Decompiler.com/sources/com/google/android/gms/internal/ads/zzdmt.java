package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.zza;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.util.zzbz;
import com.google.android.gms.ads.internal.util.zze;
import com.google.android.gms.ads.internal.util.zzg;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzdmt {
    static final ImageView.ScaleType zza = ImageView.ScaleType.CENTER_INSIDE;
    private final zzg zzb;
    private final zzfho zzc;
    private final zzdly zzd;
    private final zzdlt zze;
    private final zzdnf zzf;
    private final zzdnn zzg;
    private final Executor zzh;
    private final Executor zzi;
    private final zzbhk zzj;
    private final zzdlq zzk;

    public zzdmt(zzg zzg2, zzfho zzfho, zzdly zzdly, zzdlt zzdlt, zzdnf zzdnf, zzdnn zzdnn, Executor executor, Executor executor2, zzdlq zzdlq) {
        this.zzb = zzg2;
        this.zzc = zzfho;
        this.zzj = zzfho.zzi;
        this.zzd = zzdly;
        this.zze = zzdlt;
        this.zzf = zzdnf;
        this.zzg = zzdnn;
        this.zzh = executor;
        this.zzi = executor2;
        this.zzk = zzdlq;
    }

    private final boolean zzi(ViewGroup viewGroup, boolean z) {
        FrameLayout.LayoutParams layoutParams;
        View zzf2 = z ? this.zze.zzf() : this.zze.zzg();
        if (zzf2 == null) {
            return false;
        }
        viewGroup.removeAllViews();
        if (zzf2.getParent() instanceof ViewGroup) {
            ((ViewGroup) zzf2.getParent()).removeView(zzf2);
        }
        if (((Boolean) zzba.zzc().zza(zzbep.zzdQ)).booleanValue()) {
            layoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
        } else {
            layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
        }
        viewGroup.addView(zzf2, layoutParams);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(ViewGroup viewGroup) {
        zzdlt zzdlt = this.zze;
        if (zzdlt.zzf() != null) {
            boolean z = viewGroup != null;
            if (zzdlt.zzc() == 2 || zzdlt.zzc() == 1) {
                this.zzb.zzK(this.zzc.zzf, String.valueOf(zzdlt.zzc()), z);
            } else if (zzdlt.zzc() == 6) {
                this.zzb.zzK(this.zzc.zzf, ExifInterface.GPS_MEASUREMENT_2D, z);
                this.zzb.zzK(this.zzc.zzf, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzdnp zzdnp) {
        ViewGroup viewGroup;
        View view;
        ViewGroup viewGroup2;
        zzbhs zza2;
        Drawable drawable;
        Context context = null;
        if (this.zzd.zzf() || this.zzd.zze()) {
            String[] strArr = {NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW, "3011"};
            int i = 0;
            while (true) {
                if (i >= 2) {
                    break;
                }
                View zzg2 = zzdnp.zzg(strArr[i]);
                if (zzg2 != null && (zzg2 instanceof ViewGroup)) {
                    viewGroup = (ViewGroup) zzg2;
                    break;
                }
                i++;
            }
        }
        viewGroup = null;
        Context context2 = zzdnp.zzf().getContext();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        zzdlt zzdlt = this.zze;
        if (zzdlt.zze() != null) {
            zzbhk zzbhk = this.zzj;
            view = zzdlt.zze();
            if (zzbhk != null && viewGroup == null) {
                zzh(layoutParams, zzbhk.zze);
                view.setLayoutParams(layoutParams);
                viewGroup = null;
            }
        } else if (!(zzdlt.zzl() instanceof zzbhf)) {
            view = null;
        } else {
            zzbhf zzbhf = (zzbhf) zzdlt.zzl();
            if (viewGroup == null) {
                zzh(layoutParams, zzbhf.zzc());
                viewGroup = null;
            }
            zzbhg zzbhg = new zzbhg(context2, zzbhf, layoutParams);
            zzbhg.setContentDescription((CharSequence) zzba.zzc().zza(zzbep.zzdO));
            view = zzbhg;
        }
        if (view != null) {
            if (view.getParent() instanceof ViewGroup) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            if (viewGroup != null) {
                viewGroup.removeAllViews();
                viewGroup.addView(view);
            } else {
                zza zza3 = new zza(zzdnp.zzf().getContext());
                zza3.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                zza3.addView(view);
                FrameLayout zzh2 = zzdnp.zzh();
                if (zzh2 != null) {
                    zzh2.addView(zza3);
                }
            }
            zzdnp.zzq(zzdnp.zzk(), view, true);
        }
        zzgbc zzgbc = zzdmp.zza;
        int size = zzgbc.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                viewGroup2 = null;
                break;
            }
            View zzg3 = zzdnp.zzg((String) zzgbc.get(i2));
            i2++;
            if (zzg3 instanceof ViewGroup) {
                viewGroup2 = (ViewGroup) zzg3;
                break;
            }
        }
        this.zzi.execute(new zzdmq(this, viewGroup2));
        if (viewGroup2 != null) {
            if (zzi(viewGroup2, true)) {
                zzdlt zzdlt2 = this.zze;
                if (zzdlt2.zzs() != null) {
                    zzdlt2.zzs().zzar(new zzdms(zzdnp, viewGroup2));
                    return;
                }
                return;
            }
            if (!((Boolean) zzba.zzc().zza(zzbep.zzjZ)).booleanValue() || !zzi(viewGroup2, false)) {
                viewGroup2.removeAllViews();
                View zzf2 = zzdnp.zzf();
                if (zzf2 != null) {
                    context = zzf2.getContext();
                }
                if (context != null && (zza2 = this.zzk.zza()) != null) {
                    try {
                        IObjectWrapper zzi2 = zza2.zzi();
                        if (zzi2 != null && (drawable = (Drawable) ObjectWrapper.unwrap(zzi2)) != null) {
                            ImageView imageView = new ImageView(context);
                            imageView.setImageDrawable(drawable);
                            IObjectWrapper zzj2 = zzdnp.zzj();
                            if (zzj2 != null) {
                                if (((Boolean) zzba.zzc().zza(zzbep.zzgr)).booleanValue()) {
                                    imageView.setScaleType((ImageView.ScaleType) ObjectWrapper.unwrap(zzj2));
                                    imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                                    viewGroup2.addView(imageView);
                                }
                            }
                            imageView.setScaleType(zza);
                            imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                            viewGroup2.addView(imageView);
                        }
                    } catch (RemoteException unused) {
                        zzm.zzj("Could not get main image drawable");
                    }
                }
            } else {
                zzdlt zzdlt3 = this.zze;
                if (zzdlt3.zzq() != null) {
                    zzdlt3.zzq().zzar(new zzdms(zzdnp, viewGroup2));
                }
            }
        }
    }

    public final void zzc(zzdnp zzdnp) {
        if (zzdnp != null && this.zzf != null && zzdnp.zzh() != null && this.zzd.zzg()) {
            try {
                zzdnp.zzh().addView(this.zzf.zza());
            } catch (zzchp e) {
                zze.zzb("web view can not be obtained", e);
            }
        }
    }

    public final void zzd(zzdnp zzdnp) {
        if (zzdnp != null) {
            Context context = zzdnp.zzf().getContext();
            if (!zzbz.zzh(context, this.zzd.zza)) {
                return;
            }
            if (!(context instanceof Activity)) {
                zzm.zze("Activity context is needed for policy validator.");
            } else if (this.zzg != null && zzdnp.zzh() != null) {
                try {
                    WindowManager windowManager = (WindowManager) context.getSystemService("window");
                    windowManager.addView(this.zzg.zza(zzdnp.zzh(), windowManager), zzbz.zzb());
                } catch (zzchp e) {
                    zze.zzb("web view can not be obtained", e);
                }
            }
        }
    }

    public final void zze(zzdnp zzdnp) {
        this.zzh.execute(new zzdmr(this, zzdnp));
    }

    public final boolean zzf(ViewGroup viewGroup) {
        return zzi(viewGroup, false);
    }

    public final boolean zzg(ViewGroup viewGroup) {
        return zzi(viewGroup, true);
    }

    private static void zzh(RelativeLayout.LayoutParams layoutParams, int i) {
        if (i == 0) {
            layoutParams.addRule(10);
            layoutParams.addRule(9);
        } else if (i == 2) {
            layoutParams.addRule(12);
            layoutParams.addRule(11);
        } else if (i != 3) {
            layoutParams.addRule(10);
            layoutParams.addRule(11);
        } else {
            layoutParams.addRule(12);
            layoutParams.addRule(9);
        }
    }
}
