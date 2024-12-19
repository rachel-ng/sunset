package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzftk {
    final zzftn zza;
    final boolean zzb;

    private zzftk(zzftn zzftn) {
        this.zza = zzftn;
        this.zzb = zzftn != null;
    }

    public static zzftk zzb(Context context, String str, String str2) {
        zzftn zzftn;
        try {
            IBinder instantiate = DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.gass.internal.clearcut.GassDynamiteClearcutLogger");
            if (instantiate == null) {
                zzftn = null;
            } else {
                IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.gass.internal.clearcut.IGassClearcut");
                zzftn = queryLocalInterface instanceof zzftn ? (zzftn) queryLocalInterface : new zzftl(instantiate);
            }
            try {
                zzftn.zze(ObjectWrapper.wrap(context), str, (String) null);
                Log.i("GASS", "GassClearcutLogger Initialized.");
                return new zzftk(zzftn);
            } catch (RemoteException | zzfsm | NullPointerException | SecurityException unused) {
                Log.d("GASS", "Cannot dynamite load clearcut");
                return new zzftk(new zzfto());
            }
        } catch (Exception e) {
            throw new zzfsm(e);
        } catch (Exception e2) {
            throw new zzfsm(e2);
        }
    }

    public static zzftk zzc() {
        zzfto zzfto = new zzfto();
        Log.d("GASS", "Clearcut logging disabled");
        return new zzftk(zzfto);
    }

    public final zzftj zza(byte[] bArr) {
        return new zzftj(this, bArr, (zzfti) null);
    }
}
