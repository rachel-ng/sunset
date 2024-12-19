package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzfxm {
    public static String zza(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (zze(str.charAt(i))) {
                char[] charArray = str.toCharArray();
                while (i < length) {
                    char c2 = charArray[i];
                    if (zze(c2)) {
                        charArray[i] = (char) (c2 ^ ' ');
                    }
                    i++;
                }
                return String.valueOf(charArray);
            }
            i++;
        }
        return str;
    }

    public static String zzb(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (zzd(str.charAt(i))) {
                char[] charArray = str.toCharArray();
                while (i < length) {
                    char c2 = charArray[i];
                    if (zzd(c2)) {
                        charArray[i] = (char) (c2 ^ ' ');
                    }
                    i++;
                }
                return String.valueOf(charArray);
            }
            i++;
        }
        return str;
    }

    public static boolean zzc(CharSequence charSequence, CharSequence charSequence2) {
        int zzf;
        int length = charSequence.length();
        if (charSequence == charSequence2) {
            return true;
        }
        if (length != charSequence2.length()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            char charAt2 = charSequence2.charAt(i);
            if (charAt != charAt2 && ((zzf = zzf(charAt)) >= 26 || zzf != zzf(charAt2))) {
                return false;
            }
        }
        return true;
    }

    public static boolean zzd(char c2) {
        return c2 >= 'a' && c2 <= 'z';
    }

    public static boolean zze(char c2) {
        return c2 >= 'A' && c2 <= 'Z';
    }

    private static int zzf(char c2) {
        return (char) ((c2 | ' ') - 'a');
    }
}
