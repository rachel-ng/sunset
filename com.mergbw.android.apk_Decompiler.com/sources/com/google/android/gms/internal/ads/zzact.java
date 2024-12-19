package com.google.android.gms.internal.ads;

import androidx.core.location.LocationRequestCompat;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.audio.OpusUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import okio.Utf8;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzact {
    public static final /* synthetic */ int zza = 0;
    private static final int[] zzb = {1, 2, 3, 6};
    private static final int[] zzc = {OpusUtil.SAMPLE_RATE, 44100, 32000};
    private static final int[] zzd = {24000, 22050, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND};
    private static final int[] zze = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] zzf = {32, 40, 48, 56, 64, 80, 96, 112, 128, Opcodes.IF_ICMPNE, 192, 224, 256, 320, RendererCapabilities.MODE_SUPPORT_MASK, 448, 512, 576, 640};
    private static final int[] zzg = {69, 87, LocationRequestCompat.QUALITY_LOW_POWER, 121, 139, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    public static int zza(ByteBuffer byteBuffer) {
        int i = 3;
        if (((byteBuffer.get(byteBuffer.position() + 5) & 248) >> 3) <= 10) {
            return 1536;
        }
        if (((byteBuffer.get(byteBuffer.position() + 4) & 192) >> 6) != 3) {
            i = (byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4;
        }
        return zzb[i] * 256;
    }

    public static int zzb(byte[] bArr) {
        if (bArr.length < 6) {
            return -1;
        }
        if (((bArr[5] & 248) >> 3) > 10) {
            int i = ((bArr[3] & 255) | ((bArr[2] & 7) << 8)) + 1;
            return i + i;
        }
        byte b2 = bArr[4];
        return zzf((b2 & 192) >> 6, b2 & Utf8.REPLACEMENT_BYTE);
    }

    public static zzan zzc(zzfu zzfu, String str, String str2, zzae zzae) {
        zzft zzft = new zzft();
        zzft.zzi(zzfu);
        int i = zzc[zzft.zzd(2)];
        zzft.zzm(8);
        int i2 = zze[zzft.zzd(3)];
        if (zzft.zzd(1) != 0) {
            i2++;
        }
        int i3 = zzf[zzft.zzd(5)] * 1000;
        zzft.zze();
        zzfu.zzK(zzft.zzb());
        zzal zzal = new zzal();
        zzal.zzK(str);
        zzal.zzX(MimeTypes.AUDIO_AC3);
        zzal.zzy(i2);
        zzal.zzY(i);
        zzal.zzE(zzae);
        zzal.zzO(str2);
        zzal.zzx(i3);
        zzal.zzS(i3);
        return zzal.zzad();
    }

    public static zzan zzd(zzfu zzfu, String str, String str2, zzae zzae) {
        String str3;
        zzft zzft = new zzft();
        zzft.zzi(zzfu);
        int zzd2 = zzft.zzd(13) * 1000;
        zzft.zzm(3);
        int i = zzc[zzft.zzd(2)];
        zzft.zzm(10);
        int i2 = zze[zzft.zzd(3)];
        if (zzft.zzd(1) != 0) {
            i2++;
        }
        zzft.zzm(3);
        int zzd3 = zzft.zzd(4);
        zzft.zzm(1);
        if (zzd3 > 0) {
            zzft.zzm(6);
            if (zzft.zzd(1) != 0) {
                i2 += 2;
            }
            zzft.zzm(1);
        }
        if (zzft.zza() > 7) {
            zzft.zzm(7);
            if (zzft.zzd(1) != 0) {
                str3 = MimeTypes.AUDIO_E_AC3_JOC;
                zzft.zze();
                zzfu.zzK(zzft.zzb());
                zzal zzal = new zzal();
                zzal.zzK(str);
                zzal.zzX(str3);
                zzal.zzy(i2);
                zzal.zzY(i);
                zzal.zzE(zzae);
                zzal.zzO(str2);
                zzal.zzS(zzd2);
                return zzal.zzad();
            }
        }
        str3 = MimeTypes.AUDIO_E_AC3;
        zzft.zze();
        zzfu.zzK(zzft.zzb());
        zzal zzal2 = new zzal();
        zzal2.zzK(str);
        zzal2.zzX(str3);
        zzal2.zzy(i2);
        zzal2.zzY(i);
        zzal2.zzE(zzae);
        zzal2.zzO(str2);
        zzal2.zzS(zzd2);
        return zzal2.zzad();
    }

    public static zzacs zze(zzft zzft) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        String str;
        int i6;
        String str2;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        zzft zzft2 = zzft;
        int zzc2 = zzft.zzc();
        zzft2.zzm(40);
        int zzd2 = zzft2.zzd(5);
        zzft2.zzk(zzc2);
        int i12 = -1;
        if (zzd2 > 10) {
            zzft2.zzm(16);
            int zzd3 = zzft2.zzd(2);
            if (zzd3 == 0) {
                i12 = 0;
            } else if (zzd3 == 1) {
                i12 = 1;
            } else if (zzd3 == 2) {
                i12 = 2;
            }
            zzft2.zzm(3);
            int zzd4 = zzft2.zzd(11) + 1;
            int zzd5 = zzft2.zzd(2);
            if (zzd5 == 3) {
                i9 = zzd[zzft2.zzd(2)];
                i7 = 6;
                i8 = 3;
            } else {
                int zzd6 = zzft2.zzd(2);
                int i13 = zzb[zzd6];
                i8 = zzd6;
                i9 = zzc[zzd5];
                i7 = i13;
            }
            int i14 = zzd4 + zzd4;
            int i15 = (i14 * i9) / (i7 * 32);
            int zzd7 = zzft2.zzd(3);
            boolean zzo = zzft.zzo();
            i6 = zze[zzd7] + (zzo ? 1 : 0);
            zzft2.zzm(10);
            if (zzft.zzo()) {
                zzft2.zzm(8);
            }
            if (zzd7 == 0) {
                zzft2.zzm(5);
                if (zzft.zzo()) {
                    zzft2.zzm(8);
                }
                i10 = 0;
                zzd7 = 0;
            } else {
                i10 = zzd7;
            }
            if (i12 == 1) {
                if (zzft.zzo()) {
                    zzft2.zzm(16);
                }
                i11 = 1;
            } else {
                i11 = i12;
            }
            if (zzft.zzo()) {
                if (i10 > 2) {
                    zzft2.zzm(2);
                }
                if ((i10 & 1) != 0 && i10 > 2) {
                    zzft2.zzm(6);
                }
                if ((i10 & 4) != 0) {
                    zzft2.zzm(6);
                }
                if (zzo && zzft.zzo()) {
                    zzft2.zzm(5);
                }
                if (i11 == 0) {
                    if (zzft.zzo()) {
                        zzft2.zzm(6);
                    }
                    if (i10 == 0 && zzft.zzo()) {
                        zzft2.zzm(6);
                    }
                    if (zzft.zzo()) {
                        zzft2.zzm(6);
                    }
                    int zzd8 = zzft2.zzd(2);
                    if (zzd8 == 1) {
                        zzft2.zzm(5);
                    } else if (zzd8 == 2) {
                        zzft2.zzm(12);
                    } else if (zzd8 == 3) {
                        int zzd9 = zzft2.zzd(5);
                        if (zzft.zzo()) {
                            zzft2.zzm(5);
                            if (zzft.zzo()) {
                                zzft2.zzm(4);
                            }
                            if (zzft.zzo()) {
                                zzft2.zzm(4);
                            }
                            if (zzft.zzo()) {
                                zzft2.zzm(4);
                            }
                            if (zzft.zzo()) {
                                zzft2.zzm(4);
                            }
                            if (zzft.zzo()) {
                                zzft2.zzm(4);
                            }
                            if (zzft.zzo()) {
                                zzft2.zzm(4);
                            }
                            if (zzft.zzo()) {
                                zzft2.zzm(4);
                            }
                            if (zzft.zzo()) {
                                if (zzft.zzo()) {
                                    zzft2.zzm(4);
                                }
                                if (zzft.zzo()) {
                                    zzft2.zzm(4);
                                }
                            }
                        }
                        if (zzft.zzo()) {
                            zzft2.zzm(5);
                            if (zzft.zzo()) {
                                zzft2.zzm(7);
                                if (zzft.zzo()) {
                                    zzft2.zzm(8);
                                }
                            }
                        }
                        zzft2.zzm((zzd9 + 2) * 8);
                        zzft.zze();
                    }
                    if (i10 < 2) {
                        if (zzft.zzo()) {
                            zzft2.zzm(14);
                        }
                        if (zzd7 == 0 && zzft.zzo()) {
                            zzft2.zzm(14);
                        }
                    }
                    if (zzft.zzo()) {
                        if (i8 == 0) {
                            zzft2.zzm(5);
                            i11 = 0;
                            i8 = 0;
                        } else {
                            for (int i16 = 0; i16 < i7; i16++) {
                                if (zzft.zzo()) {
                                    zzft2.zzm(5);
                                }
                            }
                        }
                    }
                    i11 = 0;
                }
            }
            if (zzft.zzo()) {
                zzft2.zzm(5);
                if (i10 == 2) {
                    zzft2.zzm(4);
                    i10 = 2;
                }
                if (i10 >= 6) {
                    zzft2.zzm(2);
                }
                if (zzft.zzo()) {
                    zzft2.zzm(8);
                }
                if (i10 == 0 && zzft.zzo()) {
                    zzft2.zzm(8);
                }
                if (zzd5 < 3) {
                    zzft.zzl();
                }
            }
            if (i11 == 0 && i8 != 3) {
                zzft.zzl();
            }
            if (i11 == 2 && (i8 == 3 || zzft.zzo())) {
                zzft2.zzm(6);
            }
            str = (zzft.zzo() && zzft2.zzd(6) == 1 && zzft2.zzd(8) == 1) ? MimeTypes.AUDIO_E_AC3_JOC : MimeTypes.AUDIO_E_AC3;
            i5 = i12;
            i3 = i14;
            i4 = i9;
            i2 = i7 * 256;
            i = i15;
        } else {
            zzft2.zzm(32);
            int zzd10 = zzft2.zzd(2);
            if (zzd10 == 3) {
                str2 = null;
            } else {
                str2 = MimeTypes.AUDIO_AC3;
            }
            int zzd11 = zzft2.zzd(6);
            int i17 = zzf[zzd11 / 2] * 1000;
            int zzf2 = zzf(zzd10, zzd11);
            zzft2.zzm(8);
            int zzd12 = zzft2.zzd(3);
            if (!((zzd12 & 1) == 0 || zzd12 == 1)) {
                zzft2.zzm(2);
            }
            if ((zzd12 & 4) != 0) {
                zzft2.zzm(2);
            }
            if (zzd12 == 2) {
                zzft2.zzm(2);
            }
            int i18 = zzd10 < 3 ? zzc[zzd10] : -1;
            i6 = zze[zzd12] + (zzft.zzo() ? 1 : 0);
            i5 = -1;
            str = str2;
            i = i17;
            i3 = zzf2;
            i4 = i18;
            i2 = 1536;
        }
        return new zzacs(str, i5, i6, i4, i3, i2, i, (zzacr) null);
    }

    private static int zzf(int i, int i2) {
        int i3;
        if (i < 0 || i >= 3 || i2 < 0 || (i3 = i2 >> 1) >= 19) {
            return -1;
        }
        int i4 = zzc[i];
        if (i4 == 44100) {
            int i5 = zzg[i3] + (i2 & 1);
            return i5 + i5;
        }
        int i6 = zzf[i3];
        return i4 == 32000 ? i6 * 6 : i6 * 4;
    }
}
