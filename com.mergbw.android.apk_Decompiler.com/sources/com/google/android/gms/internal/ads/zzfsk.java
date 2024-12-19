package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfsk {
    public static boolean zza(zzazw zzazw) {
        zzazw zzazw2 = zzazw.UNSUPPORTED;
        int ordinal = zzazw.ordinal();
        return ordinal == 1 || ordinal == 2 || ordinal == 3 || ordinal == 4 || ordinal == 5;
    }

    public static final zzazw zzb(Context context, zzfre zzfre) {
        zzazw zzazw;
        FileInputStream fileInputStream;
        File file = new File(new File(context.getApplicationInfo().dataDir), "lib");
        if (!file.exists()) {
            zzfre.zzb(5017, "No lib/");
            zzazw = zzazw.UNKNOWN;
        } else {
            File[] listFiles = file.listFiles(new zzgdn(Pattern.compile(".*\\.so$", 2)));
            if (listFiles == null || listFiles.length == 0) {
                zzfre.zzb(5017, "No .so");
                zzazw = zzazw.UNKNOWN;
            } else {
                try {
                    fileInputStream = new FileInputStream(listFiles[0]);
                    byte[] bArr = new byte[20];
                    if (fileInputStream.read(bArr) == 20) {
                        byte[] bArr2 = {0, 0};
                        if (bArr[5] == 2) {
                            zzd(bArr, (String) null, context, zzfre);
                            zzazw = zzazw.UNSUPPORTED;
                        } else {
                            bArr2[0] = bArr[19];
                            bArr2[1] = bArr[18];
                            short s = ByteBuffer.wrap(bArr2).getShort();
                            if (s == 3) {
                                zzazw = zzazw.X86;
                            } else if (s == 40) {
                                zzazw = zzazw.ARM7;
                            } else if (s == 62) {
                                zzazw = zzazw.X86_64;
                            } else if (s == 183) {
                                zzazw = zzazw.ARM64;
                            } else if (s != 243) {
                                zzd(bArr, (String) null, context, zzfre);
                                zzazw = zzazw.UNSUPPORTED;
                            } else {
                                zzazw = zzazw.RISCV64;
                            }
                        }
                        fileInputStream.close();
                    } else {
                        fileInputStream.close();
                        zzazw = zzazw.UNSUPPORTED;
                    }
                } catch (IOException e) {
                    zzd((byte[]) null, e.toString(), context, zzfre);
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
        }
        if (zzazw == zzazw.UNKNOWN) {
            String zzc = zzc(context, zzfre);
            if (TextUtils.isEmpty(zzc)) {
                zzd((byte[]) null, "Empty dev arch", context, zzfre);
                zzazw = zzazw.UNSUPPORTED;
            } else if (zzc.equalsIgnoreCase("i686") || zzc.equalsIgnoreCase("x86")) {
                zzazw = zzazw.X86;
            } else if (zzc.equalsIgnoreCase("x86_64")) {
                zzazw = zzazw.X86_64;
            } else if (zzc.equalsIgnoreCase("arm64-v8a")) {
                zzazw = zzazw.ARM64;
            } else if (zzc.equalsIgnoreCase("armeabi-v7a") || zzc.equalsIgnoreCase("armv71")) {
                zzazw = zzazw.ARM7;
            } else if (zzc.equalsIgnoreCase("riscv64")) {
                zzazw = zzazw.RISCV64;
            } else {
                zzd((byte[]) null, zzc, context, zzfre);
                zzazw = zzazw.UNSUPPORTED;
            }
        }
        zzfre.zzb(5018, zzazw.name());
        return zzazw;
        throw th;
    }

    private static final String zzc(Context context, zzfre zzfre) {
        HashSet hashSet = new HashSet(Arrays.asList(new String[]{"i686", "armv71"}));
        String zza = zzfyu.OS_ARCH.zza();
        if (!TextUtils.isEmpty(zza) && hashSet.contains(zza)) {
            return zza;
        }
        try {
            String[] strArr = (String[]) Build.class.getField("SUPPORTED_ABIS").get((Object) null);
            if (strArr != null && strArr.length > 0) {
                return strArr[0];
            }
        } catch (NoSuchFieldException e) {
            zzfre.zzc(2024, 0, e);
        } catch (IllegalAccessException e2) {
            zzfre.zzc(2024, 0, e2);
        }
        return Build.CPU_ABI != null ? Build.CPU_ABI : Build.CPU_ABI2;
    }

    private static final void zzd(byte[] bArr, String str, Context context, zzfre zzfre) {
        StringBuilder sb = new StringBuilder("os.arch:");
        sb.append(zzfyu.OS_ARCH.zza());
        sb.append(";");
        try {
            String[] strArr = (String[]) Build.class.getField("SUPPORTED_ABIS").get((Object) null);
            if (strArr != null) {
                sb.append("supported_abis:");
                sb.append(Arrays.toString(strArr));
                sb.append(";");
            }
        } catch (IllegalAccessException | NoSuchFieldException unused) {
        }
        sb.append("CPU_ABI:");
        sb.append(Build.CPU_ABI);
        sb.append(";CPU_ABI2:");
        sb.append(Build.CPU_ABI2);
        sb.append(";");
        if (bArr != null) {
            sb.append("ELF:");
            sb.append(Arrays.toString(bArr));
            sb.append(";");
        }
        if (str != null) {
            sb.append("dbg:");
            sb.append(str);
            sb.append(";");
        }
        zzfre.zzb(4007, sb.toString());
    }
}
