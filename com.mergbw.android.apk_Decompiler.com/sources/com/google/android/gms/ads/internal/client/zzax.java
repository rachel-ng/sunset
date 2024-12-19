package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzf;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.ads.zzbep;
import com.google.android.gms.internal.ads.zzbgf;
import com.google.android.gms.internal.ads.zzbgt;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
abstract class zzax {
    private static final zzce zza;

    static {
        zzce zzce = null;
        try {
            Object newInstance = zzaw.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
            if (!(newInstance instanceof IBinder)) {
                zzm.zzj("ClientApi class is not an instance of IBinder.");
            } else {
                IBinder iBinder = (IBinder) newInstance;
                if (iBinder != null) {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzce = queryLocalInterface instanceof zzce ? (zzce) queryLocalInterface : new zzcc(iBinder);
                }
            }
        } catch (Exception unused) {
            zzm.zzj("Failed to instantiate ClientApi class.");
        }
        zza = zzce;
    }

    zzax() {
    }

    private final Object zze() {
        zzce zzce = zza;
        if (zzce != null) {
            try {
                return zzb(zzce);
            } catch (RemoteException e) {
                zzm.zzk("Cannot invoke local loader using ClientApi class.", e);
                return null;
            }
        } else {
            zzm.zzj("ClientApi class cannot be loaded.");
            return null;
        }
    }

    private final Object zzf() {
        try {
            return zzc();
        } catch (RemoteException e) {
            zzm.zzk("Cannot invoke remote loader.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zza();

    /* access modifiers changed from: protected */
    public abstract Object zzb(zzce zzce) throws RemoteException;

    /* access modifiers changed from: protected */
    public abstract Object zzc() throws RemoteException;

    public final Object zzd(Context context, boolean z) {
        boolean z2;
        Object obj;
        if (!z) {
            zzay.zzb();
            if (!zzf.zzt(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE)) {
                zzm.zze("Google Play Services is not available.");
                z = true;
            }
        }
        boolean z3 = false;
        boolean z4 = !(DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID) <= DynamiteModule.getRemoteVersion(context, ModuleDescriptor.MODULE_ID));
        zzbep.zza(context);
        if (((Boolean) zzbgf.zza.zze()).booleanValue()) {
            z2 = false;
        } else if (((Boolean) zzbgf.zzb.zze()).booleanValue()) {
            z2 = true;
            z3 = true;
        } else {
            z3 = z | z4;
            z2 = false;
        }
        if (z3) {
            obj = zze();
            if (obj == null && !z2) {
                obj = zzf();
            }
        } else {
            Object zzf = zzf();
            if (zzf == null) {
                if (zzay.zze().nextInt(((Long) zzbgt.zza.zze()).intValue()) == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("action", "dynamite_load");
                    bundle.putInt("is_missing", 1);
                    zzay.zzb().zzo(context, zzay.zzc().afmaVersion, "gmob-apps", bundle, true);
                }
            }
            obj = zzf == null ? zze() : zzf;
        }
        return obj == null ? zza() : obj;
    }
}
