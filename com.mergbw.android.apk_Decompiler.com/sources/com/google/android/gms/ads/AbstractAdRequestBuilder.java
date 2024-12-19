package com.google.android.gms.ads;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AbstractAdRequestBuilder;
import com.google.android.gms.ads.internal.client.zzdw;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.mediation.MediationExtrasReceiver;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class AbstractAdRequestBuilder<T extends AbstractAdRequestBuilder<T>> {
    protected final zzdw zza;

    protected AbstractAdRequestBuilder() {
        zzdw zzdw = new zzdw();
        this.zza = zzdw;
        zzdw.zzt("B3EEABB8EE11C2BE770B684D95219ECB");
    }

    @Deprecated
    public T addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
        this.zza.zzo(cls, bundle);
        return self();
    }

    public T addKeyword(String str) {
        this.zza.zzq(str);
        return self();
    }

    public T addNetworkExtrasBundle(Class<? extends MediationExtrasReceiver> cls, Bundle bundle) {
        this.zza.zzr(cls, bundle);
        if (cls.equals(AdMobAdapter.class) && bundle.getBoolean("_emulatorLiveAds")) {
            this.zza.zzu("B3EEABB8EE11C2BE770B684D95219ECB");
        }
        return self();
    }

    /* access modifiers changed from: protected */
    public abstract T self();

    public T setAdString(String str) {
        this.zza.zzv(str);
        return self();
    }

    public T setContentUrl(String str) {
        Preconditions.checkNotNull(str, "Content URL must be non-null.");
        Preconditions.checkNotEmpty(str, "Content URL must be non-empty.");
        int length = str.length();
        boolean z = false;
        Object[] objArr = {512, Integer.valueOf(str.length())};
        if (length <= 512) {
            z = true;
        }
        Preconditions.checkArgument(z, "Content URL must not exceed %d in length.  Provided length was %d.", objArr);
        this.zza.zzw(str);
        return self();
    }

    public T setHttpTimeoutMillis(int i) {
        this.zza.zzx(i);
        return self();
    }

    public T setNeighboringContentUrls(List<String> list) {
        if (list == null) {
            zzm.zzj("neighboring content URLs list should not be null");
            return self();
        }
        this.zza.zzz(list);
        return self();
    }

    public T setRequestAgent(String str) {
        this.zza.zzB(str);
        return self();
    }

    @Deprecated
    public final AbstractAdRequestBuilder zza(String str) {
        this.zza.zzt(str);
        return self();
    }

    @Deprecated
    public final AbstractAdRequestBuilder zzb(boolean z) {
        this.zza.zzy(z);
        return self();
    }

    @Deprecated
    public final AbstractAdRequestBuilder zzc(boolean z) {
        this.zza.zzC(z);
        return self();
    }
}
