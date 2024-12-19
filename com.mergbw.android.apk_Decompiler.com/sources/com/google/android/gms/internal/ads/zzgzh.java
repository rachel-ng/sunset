package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzgzh;
import com.google.android.gms.internal.ads.zzgzi;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class zzgzh<MessageType extends zzgzi<MessageType, BuilderType>, BuilderType extends zzgzh<MessageType, BuilderType>> implements zzhdd {
    private String zza(String str) {
        String name = getClass().getName();
        return "Reading " + name + " from a " + str + " threw an IOException (should never happen).";
    }

    private static <T> void zzb(Iterable<T> iterable, List<? super T> list) {
        if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
            ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
        }
        int size = list.size();
        for (T next : iterable) {
            if (next == null) {
                String str = "Element at index " + (list.size() - size) + " is null.";
                int size2 = list.size();
                while (true) {
                    size2--;
                    if (size2 >= size) {
                        list.remove(size2);
                    } else {
                        throw new NullPointerException(str);
                    }
                }
            } else {
                list.add(next);
            }
        }
    }

    protected static zzhep zzbb(zzhde zzhde) {
        return new zzhep(zzhde);
    }

    @Deprecated
    protected static <T> void zzbc(Iterable<T> iterable, Collection<? super T> collection) {
        zzbd(iterable, (List) collection);
    }

    protected static <T> void zzbd(Iterable<T> iterable, List<? super T> list) {
        byte[] bArr = zzhcb.zzd;
        iterable.getClass();
        if (iterable instanceof zzhcm) {
            List zzh = ((zzhcm) iterable).zzh();
            zzhcm zzhcm = (zzhcm) list;
            int size = list.size();
            for (Object next : zzh) {
                if (next == null) {
                    String str = "Element at index " + (zzhcm.size() - size) + " is null.";
                    int size2 = zzhcm.size();
                    while (true) {
                        size2--;
                        if (size2 >= size) {
                            zzhcm.remove(size2);
                        } else {
                            throw new NullPointerException(str);
                        }
                    }
                } else if (next instanceof zzhac) {
                    zzhcm.zzi((zzhac) next);
                } else {
                    zzhcm.add((String) next);
                }
            }
        } else if (iterable instanceof zzhdn) {
            list.addAll((Collection) iterable);
        } else {
            zzb(iterable, list);
        }
    }

    /* renamed from: zzaC */
    public abstract BuilderType zzaP();

    /* access modifiers changed from: protected */
    public abstract BuilderType zzaD(MessageType messagetype);

    public BuilderType zzaE(zzhac zzhac) throws zzhcd {
        try {
            zzham zzl = zzhac.zzl();
            zzaR(zzl);
            zzl.zzz(0);
            return this;
        } catch (zzhcd e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException(zza("ByteString"), e2);
        }
    }

    /* renamed from: zzaF */
    public BuilderType zzaR(zzham zzham) throws IOException {
        return zzaW(zzham, zzhay.zza);
    }

    /* renamed from: zzaG */
    public BuilderType zzaS(zzhde zzhde) {
        if (zzbt().getClass().isInstance(zzhde)) {
            return zzaD((zzgzi) zzhde);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    public BuilderType zzaH(InputStream inputStream) throws IOException {
        zzham zzI = zzham.zzI(inputStream, 4096);
        zzaR(zzI);
        zzI.zzz(0);
        return this;
    }

    /* renamed from: zzaI */
    public BuilderType zzaU(byte[] bArr) throws zzhcd {
        return zzaZ(bArr, 0, bArr.length);
    }

    public BuilderType zzaJ(zzhac zzhac, zzhay zzhay) throws zzhcd {
        try {
            zzham zzl = zzhac.zzl();
            zzaW(zzl, zzhay);
            zzl.zzz(0);
            return this;
        } catch (zzhcd e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException(zza("ByteString"), e2);
        }
    }

    /* renamed from: zzaK */
    public abstract BuilderType zzaW(zzham zzham, zzhay zzhay) throws IOException;

    public BuilderType zzaL(InputStream inputStream, zzhay zzhay) throws IOException {
        zzham zzI = zzham.zzI(inputStream, 4096);
        zzaW(zzI, zzhay);
        zzI.zzz(0);
        return this;
    }

    /* renamed from: zzaM */
    public BuilderType zzaY(byte[] bArr, zzhay zzhay) throws zzhcd {
        return zzba(bArr, 0, bArr.length, zzhay);
    }

    /* renamed from: zzaN */
    public BuilderType zzaZ(byte[] bArr, int i, int i2) throws zzhcd {
        try {
            zzham zzJ = zzham.zzJ(bArr, i, i2, false);
            zzaR(zzJ);
            zzJ.zzz(0);
            return this;
        } catch (zzhcd e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException(zza("byte array"), e2);
        }
    }

    /* renamed from: zzaO */
    public BuilderType zzba(byte[] bArr, int i, int i2, zzhay zzhay) throws zzhcd {
        try {
            zzham zzJ = zzham.zzJ(bArr, i, i2, false);
            zzaW(zzJ, zzhay);
            zzJ.zzz(0);
            return this;
        } catch (zzhcd e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException(zza("byte array"), e2);
        }
    }

    public /* bridge */ /* synthetic */ zzhdd zzaQ(zzhac zzhac) throws zzhcd {
        zzaE(zzhac);
        return this;
    }

    public /* bridge */ /* synthetic */ zzhdd zzaT(InputStream inputStream) throws IOException {
        zzaH(inputStream);
        return this;
    }

    public /* bridge */ /* synthetic */ zzhdd zzaV(zzhac zzhac, zzhay zzhay) throws zzhcd {
        zzaJ(zzhac, zzhay);
        return this;
    }

    public /* bridge */ /* synthetic */ zzhdd zzaX(InputStream inputStream, zzhay zzhay) throws IOException {
        zzaL(inputStream, zzhay);
        return this;
    }

    public boolean zzbe(InputStream inputStream) throws IOException {
        return zzbf(inputStream, zzhay.zza);
    }

    public boolean zzbf(InputStream inputStream, zzhay zzhay) throws IOException {
        int read = inputStream.read();
        if (read == -1) {
            return false;
        }
        zzaL(new zzgzg(inputStream, zzham.zzG(read, inputStream)), zzhay);
        return true;
    }
}
