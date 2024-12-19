package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.PlaybackException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhs extends zzgv {
    private RandomAccessFile zza;
    private Uri zzb;
    private long zzc;
    private boolean zzd;

    public zzhs() {
        super(false);
    }

    public final int zza(byte[] bArr, int i, int i2) throws zzhr {
        if (i2 == 0) {
            return 0;
        }
        if (this.zzc == 0) {
            return -1;
        }
        try {
            RandomAccessFile randomAccessFile = this.zza;
            int i3 = zzgd.zza;
            int read = randomAccessFile.read(bArr, i, (int) Math.min(this.zzc, (long) i2));
            if (read > 0) {
                this.zzc -= (long) read;
                zzg(read);
            }
            return read;
        } catch (IOException e) {
            throw new zzhr(e, 2000);
        }
    }

    public final long zzb(zzhh zzhh) throws zzhr {
        Uri uri = zzhh.zza;
        this.zzb = uri;
        zzi(zzhh);
        int i = PlaybackException.ERROR_CODE_IO_NO_PERMISSION;
        try {
            String path = uri.getPath();
            if (path != null) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");
                this.zza = randomAccessFile;
                try {
                    randomAccessFile.seek(zzhh.zze);
                    long j = zzhh.zzf;
                    if (j == -1) {
                        j = this.zza.length() - zzhh.zze;
                    }
                    this.zzc = j;
                    if (j >= 0) {
                        this.zzd = true;
                        zzj(zzhh);
                        return this.zzc;
                    }
                    throw new zzhr((String) null, (Throwable) null, 2008);
                } catch (IOException e) {
                    throw new zzhr(e, 2000);
                }
            } else {
                throw null;
            }
        } catch (FileNotFoundException e2) {
            if (!TextUtils.isEmpty(uri.getQuery()) || !TextUtils.isEmpty(uri.getFragment())) {
                throw new zzhr(String.format("uri has query and/or fragment, which are not supported. Did you call Uri.parse() on a string containing '?' or '#'? Use Uri.fromFile(new File(path)) to avoid this. path=%s,query=%s,fragment=%s", new Object[]{uri.getPath(), uri.getQuery(), uri.getFragment()}), e2, 1004);
            }
            int i2 = zzgd.zza;
            if (true != zzhq.zzb(e2.getCause())) {
                i = PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND;
            }
            throw new zzhr(e2, i);
        } catch (SecurityException e3) {
            throw new zzhr(e3, PlaybackException.ERROR_CODE_IO_NO_PERMISSION);
        } catch (RuntimeException e4) {
            throw new zzhr(e4, 2000);
        }
    }

    public final Uri zzc() {
        return this.zzb;
    }

    public final void zzd() throws zzhr {
        this.zzb = null;
        try {
            RandomAccessFile randomAccessFile = this.zza;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            this.zza = null;
            if (this.zzd) {
                this.zzd = false;
                zzh();
            }
        } catch (IOException e) {
            throw new zzhr(e, 2000);
        } catch (Throwable th) {
            this.zza = null;
            if (this.zzd) {
                this.zzd = false;
                zzh();
            }
            throw th;
        }
    }
}
