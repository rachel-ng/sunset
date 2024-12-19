package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfqo implements zzfpn {
    private static final zzfqo zza = new zzfqo();
    private static final Handler zzb = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public static Handler zzc = null;
    /* access modifiers changed from: private */
    public static final Runnable zzd = new zzfqk();
    /* access modifiers changed from: private */
    public static final Runnable zze = new zzfql();
    private final List zzf = new ArrayList();
    private int zzg;
    private boolean zzh = false;
    private final List zzi = new ArrayList();
    private final zzfpp zzj = new zzfpp();
    private final zzfqh zzk = new zzfqh();
    /* access modifiers changed from: private */
    public final zzfqi zzl = new zzfqi(new zzfqr());
    private long zzm;

    zzfqo() {
    }

    public static zzfqo zzd() {
        return zza;
    }

    static /* bridge */ /* synthetic */ void zzg(zzfqo zzfqo) {
        zzfqo.zzg = 0;
        zzfqo.zzi.clear();
        zzfqo.zzh = false;
        for (zzfon zzfon : zzfpe.zza().zzb()) {
        }
        zzfqo.zzm = System.nanoTime();
        zzfqo.zzk.zzi();
        long nanoTime = System.nanoTime();
        zzfpo zza2 = zzfqo.zzj.zza();
        if (zzfqo.zzk.zze().size() > 0) {
            Iterator it = zzfqo.zzk.zze().iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                JSONObject zza3 = zza2.zza((View) null);
                View zza4 = zzfqo.zzk.zza(str);
                zzfpo zzb2 = zzfqo.zzj.zzb();
                String zzc2 = zzfqo.zzk.zzc(str);
                if (zzc2 != null) {
                    JSONObject zza5 = zzb2.zza(zza4);
                    zzfpy.zzb(zza5, str);
                    try {
                        zza5.put("notVisibleReason", zzc2);
                    } catch (JSONException e) {
                        zzfpz.zza("Error with setting not visible reason", e);
                    }
                    zzfpy.zzc(zza3, zza5);
                }
                zzfpy.zzf(zza3);
                HashSet hashSet = new HashSet();
                hashSet.add(str);
                zzfqo.zzl.zzc(zza3, hashSet, nanoTime);
            }
        }
        if (zzfqo.zzk.zzf().size() > 0) {
            JSONObject zza6 = zza2.zza((View) null);
            zzfqo.zzk((View) null, zza2, zza6, 1, false);
            zzfpy.zzf(zza6);
            zzfqo.zzl.zzd(zza6, zzfqo.zzk.zzf(), nanoTime);
            boolean z = zzfqo.zzh;
        } else {
            zzfqo.zzl.zzb();
        }
        zzfqo.zzk.zzg();
        long nanoTime2 = System.nanoTime() - zzfqo.zzm;
        if (zzfqo.zzf.size() > 0) {
            for (zzfqn zzfqn : zzfqo.zzf) {
                int i = zzfqo.zzg;
                TimeUnit.NANOSECONDS.toMillis(nanoTime2);
                zzfqn.zzb();
                if (zzfqn instanceof zzfqm) {
                    int i2 = zzfqo.zzg;
                    ((zzfqm) zzfqn).zza();
                }
            }
        }
    }

    private final void zzk(View view, zzfpo zzfpo, JSONObject jSONObject, int i, boolean z) {
        boolean z2 = true;
        if (i != 1) {
            z2 = false;
        }
        zzfpo.zzb(view, jSONObject, this, z2, z);
    }

    private static final void zzl() {
        Handler handler = zzc;
        if (handler != null) {
            handler.removeCallbacks(zze);
            zzc = null;
        }
    }

    public final void zza(View view, zzfpo zzfpo, JSONObject jSONObject, boolean z) {
        int zzk2;
        boolean z2;
        if (zzfqe.zza(view) == null && (zzk2 = this.zzk.zzk(view)) != 3) {
            JSONObject zza2 = zzfpo.zza(view);
            zzfpy.zzc(jSONObject, zza2);
            String zzd2 = this.zzk.zzd(view);
            if (zzd2 != null) {
                zzfpy.zzb(zza2, zzd2);
                try {
                    zza2.put("hasWindowFocus", Boolean.valueOf(this.zzk.zzj(view)));
                } catch (JSONException e) {
                    zzfpz.zza("Error with setting has window focus", e);
                }
                this.zzk.zzh();
            } else {
                zzfqg zzb2 = this.zzk.zzb(view);
                if (zzb2 != null) {
                    zzfph zza3 = zzb2.zza();
                    JSONArray jSONArray = new JSONArray();
                    ArrayList zzb3 = zzb2.zzb();
                    int size = zzb3.size();
                    for (int i = 0; i < size; i++) {
                        jSONArray.put((String) zzb3.get(i));
                    }
                    try {
                        zza2.put("isFriendlyObstructionFor", jSONArray);
                        zza2.put("friendlyObstructionClass", zza3.zzd());
                        zza2.put("friendlyObstructionPurpose", zza3.zza());
                        zza2.put("friendlyObstructionReason", zza3.zzc());
                    } catch (JSONException e2) {
                        zzfpz.zza("Error with setting friendly obstruction", e2);
                    }
                    z2 = true;
                } else {
                    z2 = false;
                }
                zzk(view, zzfpo, zza2, zzk2, z || z2);
            }
            this.zzg++;
        }
    }

    public final void zzh() {
        zzl();
    }

    public final void zzi() {
        if (zzc == null) {
            Handler handler = new Handler(Looper.getMainLooper());
            zzc = handler;
            handler.post(zzd);
            zzc.postDelayed(zze, 200);
        }
    }

    public final void zzj() {
        zzl();
        this.zzf.clear();
        zzb.post(new zzfqj(this));
    }
}
