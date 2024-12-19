package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzhbi;
import com.google.android.gms.internal.ads.zzhbo;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public class zzhbi<MessageType extends zzhbo<MessageType, BuilderType>, BuilderType extends zzhbi<MessageType, BuilderType>> extends zzgzh<MessageType, BuilderType> {
    protected MessageType zza;
    private final MessageType zzb;

    protected zzhbi(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzce()) {
            this.zza = zza();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    private MessageType zza() {
        return this.zzb.zzbj();
    }

    private static <MessageType> void zzb(MessageType messagetype, MessageType messagetype2) {
        zzhdo.zza().zzb(messagetype.getClass()).zzg(messagetype, messagetype2);
    }

    /* access modifiers changed from: protected */
    public /* bridge */ /* synthetic */ zzgzh zzaD(zzgzi zzgzi) {
        zzbi((zzhbo) zzgzi);
        return this;
    }

    public /* bridge */ /* synthetic */ zzgzh zzaK(zzham zzham, zzhay zzhay) throws IOException {
        zzbk(zzham, zzhay);
        return this;
    }

    public /* bridge */ /* synthetic */ zzgzh zzaN(byte[] bArr, int i, int i2) throws zzhcd {
        zzbl(bArr, i, i2);
        return this;
    }

    public /* bridge */ /* synthetic */ zzgzh zzaO(byte[] bArr, int i, int i2, zzhay zzhay) throws zzhcd {
        zzbm(bArr, i, i2, zzhay);
        return this;
    }

    public /* bridge */ /* synthetic */ zzhdd zzaW(zzham zzham, zzhay zzhay) throws IOException {
        zzbk(zzham, zzhay);
        return this;
    }

    public /* bridge */ /* synthetic */ zzhdd zzaZ(byte[] bArr, int i, int i2) throws zzhcd {
        zzbl(bArr, i, i2);
        return this;
    }

    public /* bridge */ /* synthetic */ zzhdd zzba(byte[] bArr, int i, int i2, zzhay zzhay) throws zzhcd {
        zzbm(bArr, i, i2, zzhay);
        return this;
    }

    public final BuilderType zzbg() {
        if (!this.zzb.zzce()) {
            this.zza = zza();
            return this;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    /* renamed from: zzbh */
    public BuilderType zzaP() {
        BuilderType zzbb = zzbt().zzcY();
        zzbb.zza = zzbs();
        return zzbb;
    }

    /* access modifiers changed from: protected */
    public BuilderType zzbi(MessageType messagetype) {
        zzbj(messagetype);
        return this;
    }

    public BuilderType zzbj(MessageType messagetype) {
        if (zzbt().equals(messagetype)) {
            return this;
        }
        zzbu();
        zzb(this.zza, messagetype);
        return this;
    }

    public BuilderType zzbk(zzham zzham, zzhay zzhay) throws IOException {
        zzbu();
        try {
            zzhdo.zza().zzb(this.zza.getClass()).zzh(this.zza, zzhan.zzq(zzham), zzhay);
            return this;
        } catch (RuntimeException e) {
            if (e.getCause() instanceof IOException) {
                throw ((IOException) e.getCause());
            }
            throw e;
        }
    }

    public BuilderType zzbl(byte[] bArr, int i, int i2) throws zzhcd {
        zzbm(bArr, i, i2, zzhay.zza);
        return this;
    }

    public BuilderType zzbm(byte[] bArr, int i, int i2, zzhay zzhay) throws zzhcd {
        zzbu();
        try {
            zzhdo.zza().zzb(this.zza.getClass()).zzi(this.zza, bArr, i, i + i2, new zzgzn(zzhay));
            return this;
        } catch (zzhcd e) {
            throw e;
        } catch (IndexOutOfBoundsException unused) {
            throw zzhcd.zzj();
        } catch (IOException e2) {
            throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
        }
    }

    /* renamed from: zzbn */
    public final MessageType zzbr() {
        MessageType zzbo = zzbs();
        if (zzbo.zzbw()) {
            return zzbo;
        }
        throw zzbb(zzbo);
    }

    /* renamed from: zzbo */
    public MessageType zzbs() {
        if (!this.zza.zzce()) {
            return this.zza;
        }
        this.zza.zzbV();
        return this.zza;
    }

    /* renamed from: zzbp */
    public MessageType zzbt() {
        return this.zzb;
    }

    public /* bridge */ /* synthetic */ zzhdd zzbq() {
        zzbg();
        return this;
    }

    /* access modifiers changed from: protected */
    public final void zzbu() {
        if (!this.zza.zzce()) {
            zzbv();
        }
    }

    /* access modifiers changed from: protected */
    public void zzbv() {
        MessageType zza2 = zza();
        zzb(zza2, this.zza);
        this.zza = zza2;
    }

    public final boolean zzbw() {
        return zzhbo.zzcd(this.zza, false);
    }
}
