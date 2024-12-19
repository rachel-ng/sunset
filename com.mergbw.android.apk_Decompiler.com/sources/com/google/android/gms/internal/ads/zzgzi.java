package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzgzh;
import com.google.android.gms.internal.ads.zzgzi;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class zzgzi<MessageType extends zzgzi<MessageType, BuilderType>, BuilderType extends zzgzh<MessageType, BuilderType>> implements zzhde {
    protected int zzq = 0;

    protected static <T> void zzaQ(Iterable<T> iterable, List<? super T> list) {
        zzgzh.zzbd(iterable, list);
    }

    protected static void zzaR(zzhac zzhac) throws IllegalArgumentException {
        if (!zzhac.zzp()) {
            throw new IllegalArgumentException("Byte string is not UTF-8.");
        }
    }

    private String zzdI(String str) {
        String name = getClass().getName();
        return "Serializing " + name + " to a " + str + " threw an IOException (should never happen).";
    }

    /* access modifiers changed from: package-private */
    public int zzaL() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public int zzaM(zzhdz zzhdz) {
        return zzaL();
    }

    public zzhac zzaN() {
        try {
            int zzaY = zzaY();
            zzhac zzhac = zzhac.zzb;
            byte[] bArr = new byte[zzaY];
            zzhat zzF = zzhat.zzF(bArr, 0, zzaY);
            zzda(zzF);
            zzF.zzG();
            return new zzgzy(bArr);
        } catch (IOException e) {
            throw new RuntimeException(zzdI("ByteString"), e);
        }
    }

    public zzhdj zzaO() {
        throw new UnsupportedOperationException("mutableCopy() is not implemented.");
    }

    /* access modifiers changed from: package-private */
    public zzhep zzaP() {
        return new zzhep(this);
    }

    /* access modifiers changed from: package-private */
    public void zzaS(int i) {
        throw new UnsupportedOperationException();
    }

    public void zzaT(OutputStream outputStream) throws IOException {
        int zzaY = zzaY();
        zzhar zzhar = new zzhar(outputStream, zzhat.zzB(zzhat.zzD(zzaY) + zzaY));
        zzhar.zzu(zzaY);
        zzda(zzhar);
        zzhar.zzL();
    }

    public void zzaU(OutputStream outputStream) throws IOException {
        zzhar zzhar = new zzhar(outputStream, zzhat.zzB(zzaY()));
        zzda(zzhar);
        zzhar.zzL();
    }

    public byte[] zzaV() {
        try {
            int zzaY = zzaY();
            byte[] bArr = new byte[zzaY];
            zzhat zzF = zzhat.zzF(bArr, 0, zzaY);
            zzda(zzF);
            zzF.zzG();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException(zzdI("byte array"), e);
        }
    }
}
