package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.Base64;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzams {
    public final String zza;
    public final String zzb;
    public final boolean zzc;
    public final long zzd;
    public final long zze;
    public final zzamy zzf;
    public final String zzg;
    public final String zzh;
    public final zzams zzi;
    private final String[] zzj;
    private final HashMap zzk;
    private final HashMap zzl;
    private List zzm;

    public static zzams zzb(String str, long j, long j2, zzamy zzamy, String[] strArr, String str2, String str3, zzams zzams) {
        return new zzams(str, (String) null, j, j2, zzamy, strArr, str2, str3, zzams);
    }

    public static zzams zzc(String str) {
        return new zzams((String) null, str.replaceAll("\r\n", "\n").replaceAll(" *\n *", "\n").replaceAll("\n", " ").replaceAll("[ \t\\x0B\f\r]+", " "), C.TIME_UNSET, C.TIME_UNSET, (zzamy) null, (String[]) null, "", (String) null, (zzams) null);
    }

    private static SpannableStringBuilder zzi(String str, Map map) {
        if (!map.containsKey(str)) {
            zzeg zzeg = new zzeg();
            zzeg.zzl(new SpannableStringBuilder());
            map.put(str, zzeg);
        }
        CharSequence zzq = ((zzeg) map.get(str)).zzq();
        zzq.getClass();
        return (SpannableStringBuilder) zzq;
    }

    private final void zzj(TreeSet treeSet, boolean z) {
        String str = this.zza;
        boolean equals = TtmlNode.TAG_P.equals(str);
        boolean equals2 = TtmlNode.TAG_DIV.equals(str);
        if (z || equals || (equals2 && this.zzh != null)) {
            long j = this.zzd;
            if (j != C.TIME_UNSET) {
                treeSet.add(Long.valueOf(j));
            }
            long j2 = this.zze;
            if (j2 != C.TIME_UNSET) {
                treeSet.add(Long.valueOf(j2));
            }
        }
        if (this.zzm != null) {
            for (int i = 0; i < this.zzm.size(); i++) {
                zzams zzams = (zzams) this.zzm.get(i);
                boolean z2 = true;
                if (!z && !equals) {
                    z2 = false;
                }
                zzams.zzj(treeSet, z2);
            }
        }
    }

    private final void zzk(long j, String str, List list) {
        String str2;
        if (!"".equals(this.zzg)) {
            str = this.zzg;
        }
        if (!zzg(j) || !TtmlNode.TAG_DIV.equals(this.zza) || (str2 = this.zzh) == null) {
            for (int i = 0; i < zza(); i++) {
                zzd(i).zzk(j, str, list);
            }
            return;
        }
        list.add(new Pair(str, str2));
    }

    private final void zzl(long j, Map map, Map map2, String str, Map map3) {
        zzams zzams;
        int i;
        int i2;
        zzamy zza2;
        int i3;
        Map map4 = map;
        if (zzg(j)) {
            String str2 = !"".equals(this.zzg) ? this.zzg : str;
            for (Map.Entry entry : this.zzl.entrySet()) {
                String str3 = (String) entry.getKey();
                int intValue = this.zzk.containsKey(str3) ? ((Integer) this.zzk.get(str3)).intValue() : 0;
                int intValue2 = ((Integer) entry.getValue()).intValue();
                if (intValue != intValue2) {
                    zzeg zzeg = (zzeg) map3.get(str3);
                    zzeg.getClass();
                    zzamw zzamw = (zzamw) map2.get(str2);
                    zzamw.getClass();
                    zzamy zza3 = zzamx.zza(this.zzf, this.zzj, map4);
                    SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) zzeg.zzq();
                    if (spannableStringBuilder == null) {
                        spannableStringBuilder = new SpannableStringBuilder();
                        zzeg.zzl(spannableStringBuilder);
                    }
                    if (zza3 != null) {
                        zzams zzams2 = this.zzi;
                        if (zza3.zzh() != -1) {
                            spannableStringBuilder.setSpan(new StyleSpan(zza3.zzh()), intValue, intValue2, 33);
                        }
                        if (zza3.zzI()) {
                            spannableStringBuilder.setSpan(new StrikethroughSpan(), intValue, intValue2, 33);
                        }
                        if (zza3.zzJ()) {
                            spannableStringBuilder.setSpan(new UnderlineSpan(), intValue, intValue2, 33);
                        }
                        if (zza3.zzH()) {
                            zzeo.zza(spannableStringBuilder, new ForegroundColorSpan(zza3.zzd()), intValue, intValue2, 33);
                        }
                        if (zza3.zzG()) {
                            zzeo.zza(spannableStringBuilder, new BackgroundColorSpan(zza3.zzc()), intValue, intValue2, 33);
                        }
                        if (zza3.zzD() != null) {
                            zzeo.zza(spannableStringBuilder, new TypefaceSpan(zza3.zzD()), intValue, intValue2, 33);
                        }
                        if (zza3.zzk() != null) {
                            zzamr zzk2 = zza3.zzk();
                            zzk2.getClass();
                            int i4 = zzk2.zza;
                            if (i4 == -1) {
                                int i5 = zzamw.zzj;
                                i4 = (i5 == 2 || i5 == 1) ? 3 : 1;
                                i3 = 1;
                            } else {
                                i3 = zzk2.zzb;
                            }
                            int i6 = zzk2.zzc;
                            if (i6 == -2) {
                                i6 = 1;
                            }
                            zzeo.zza(spannableStringBuilder, new zzep(i4, i3, i6), intValue, intValue2, 33);
                        }
                        int zzg2 = zza3.zzg();
                        if (zzg2 == 2) {
                            while (true) {
                                if (zzams2 != null) {
                                    zzamy zza4 = zzamx.zza(zzams2.zzf, zzams2.zzj, map4);
                                    if (zza4 != null && zza4.zzg() == 1) {
                                        break;
                                    }
                                    zzams2 = zzams2.zzi;
                                } else {
                                    zzams2 = null;
                                    break;
                                }
                            }
                            if (zzams2 != null) {
                                ArrayDeque arrayDeque = new ArrayDeque();
                                arrayDeque.push(zzams2);
                                while (true) {
                                    if (arrayDeque.isEmpty()) {
                                        zzams = null;
                                        break;
                                    }
                                    zzams zzams3 = (zzams) arrayDeque.pop();
                                    zzamy zza5 = zzamx.zza(zzams3.zzf, zzams3.zzj, map4);
                                    if (zza5 != null && zza5.zzg() == 3) {
                                        zzams = zzams3;
                                        break;
                                    }
                                    for (int zza6 = zzams3.zza() - 1; zza6 >= 0; zza6--) {
                                        arrayDeque.push(zzams3.zzd(zza6));
                                    }
                                }
                                if (zzams != null) {
                                    if (zzams.zza() != 1 || zzams.zzd(0).zzb == null) {
                                        zzfk.zze("TtmlRenderUtil", "Skipping rubyText node without exactly one text child.");
                                    } else {
                                        String str4 = zzams.zzd(0).zzb;
                                        int i7 = zzgd.zza;
                                        zzamy zza7 = zzamx.zza(zzams.zzf, zzams.zzj, map4);
                                        if (zza7 != null) {
                                            i = zza7.zzf();
                                            i2 = -1;
                                        } else {
                                            i2 = -1;
                                            i = -1;
                                        }
                                        if (i == i2 && (zza2 = zzamx.zza(zzams2.zzf, zzams2.zzj, map4)) != null) {
                                            i = zza2.zzf();
                                        }
                                        spannableStringBuilder.setSpan(new zzen(str4, i), intValue, intValue2, 33);
                                    }
                                }
                            }
                        } else if (zzg2 == 3 || zzg2 == 4) {
                            spannableStringBuilder.setSpan(new zzamq(), intValue, intValue2, 33);
                        }
                        if (zza3.zzF()) {
                            zzeo.zza(spannableStringBuilder, new zzem(), intValue, intValue2, 33);
                        }
                        int zze2 = zza3.zze();
                        if (zze2 == 1) {
                            zzeo.zza(spannableStringBuilder, new AbsoluteSizeSpan((int) zza3.zza(), true), intValue, intValue2, 33);
                        } else if (zze2 == 2) {
                            zzeo.zza(spannableStringBuilder, new RelativeSizeSpan(zza3.zza()), intValue, intValue2, 33);
                        } else if (zze2 == 3) {
                            zzeo.zza(spannableStringBuilder, new RelativeSizeSpan(zza3.zza() / 100.0f), intValue, intValue2, 33);
                        }
                        if (TtmlNode.TAG_P.equals(this.zza)) {
                            if (zza3.zzb() != Float.MAX_VALUE) {
                                zzeg.zzj((zza3.zzb() * -90.0f) / 100.0f);
                            }
                            if (zza3.zzj() != null) {
                                zzeg.zzm(zza3.zzj());
                            }
                            if (zza3.zzi() != null) {
                                zzeg.zzg(zza3.zzi());
                            }
                        }
                    }
                } else {
                    Map map5 = map2;
                    Map map6 = map3;
                }
            }
            Map map7 = map2;
            Map map8 = map3;
            for (int i8 = 0; i8 < zza(); i8++) {
                zzd(i8).zzl(j, map, map2, str2, map3);
            }
        }
    }

    private final void zzm(long j, boolean z, String str, Map map) {
        this.zzk.clear();
        this.zzl.clear();
        if (!TtmlNode.TAG_METADATA.equals(this.zza)) {
            if (!"".equals(this.zzg)) {
                str = this.zzg;
            }
            if (this.zzc && z) {
                SpannableStringBuilder zzi2 = zzi(str, map);
                String str2 = this.zzb;
                str2.getClass();
                zzi2.append(str2);
            } else if (TtmlNode.TAG_BR.equals(this.zza) && z) {
                zzi(str, map).append(10);
            } else if (zzg(j)) {
                for (Map.Entry entry : map.entrySet()) {
                    CharSequence zzq = ((zzeg) entry.getValue()).zzq();
                    zzq.getClass();
                    this.zzk.put((String) entry.getKey(), Integer.valueOf(zzq.length()));
                }
                boolean equals = TtmlNode.TAG_P.equals(this.zza);
                for (int i = 0; i < zza(); i++) {
                    zzd(i).zzm(j, z || equals, str, map);
                }
                if (equals) {
                    SpannableStringBuilder zzi3 = zzi(str, map);
                    int length = zzi3.length();
                    do {
                        length--;
                        if (length < 0 || zzi3.charAt(length) != ' ') {
                            if (length >= 0 && zzi3.charAt(length) != 10) {
                                zzi3.append(10);
                            }
                        }
                        length--;
                        break;
                    } while (zzi3.charAt(length) != ' ');
                    zzi3.append(10);
                }
                for (Map.Entry entry2 : map.entrySet()) {
                    CharSequence zzq2 = ((zzeg) entry2.getValue()).zzq();
                    zzq2.getClass();
                    this.zzl.put((String) entry2.getKey(), Integer.valueOf(zzq2.length()));
                }
            }
        }
    }

    public final int zza() {
        List list = this.zzm;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public final zzams zzd(int i) {
        List list = this.zzm;
        if (list != null) {
            return (zzams) list.get(i);
        }
        throw new IndexOutOfBoundsException();
    }

    public final List zze(long j, Map map, Map map2, Map map3) {
        ArrayList arrayList = new ArrayList();
        zzk(j, this.zzg, arrayList);
        TreeMap treeMap = new TreeMap();
        long j2 = j;
        zzm(j2, false, this.zzg, treeMap);
        zzl(j2, map, map2, this.zzg, treeMap);
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Pair pair = (Pair) arrayList.get(i);
            String str = (String) map3.get(pair.second);
            if (str != null) {
                byte[] decode = Base64.decode(str, 0);
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                zzamw zzamw = (zzamw) map2.get(pair.first);
                zzamw.getClass();
                zzeg zzeg = new zzeg();
                zzeg.zzc(decodeByteArray);
                zzeg.zzh(zzamw.zzb);
                zzeg.zzi(0);
                zzeg.zze(zzamw.zzc, 0);
                zzeg.zzf(zzamw.zze);
                zzeg.zzk(zzamw.zzf);
                zzeg.zzd(zzamw.zzg);
                zzeg.zzo(zzamw.zzj);
                arrayList2.add(zzeg.zzp());
            }
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            zzamw zzamw2 = (zzamw) map2.get(entry.getKey());
            zzamw2.getClass();
            zzeg zzeg2 = (zzeg) entry.getValue();
            CharSequence zzq = zzeg2.zzq();
            zzq.getClass();
            SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) zzq;
            for (zzamq zzamq : (zzamq[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), zzamq.class)) {
                spannableStringBuilder.replace(spannableStringBuilder.getSpanStart(zzamq), spannableStringBuilder.getSpanEnd(zzamq), "");
            }
            int i2 = 0;
            while (i2 < spannableStringBuilder.length()) {
                int i3 = i2 + 1;
                if (spannableStringBuilder.charAt(i2) == ' ') {
                    int i4 = i3;
                    while (i4 < spannableStringBuilder.length() && spannableStringBuilder.charAt(i4) == ' ') {
                        i4++;
                    }
                    int i5 = i4 - i3;
                    if (i5 > 0) {
                        spannableStringBuilder.delete(i2, i5 + i2);
                    }
                }
                i2 = i3;
            }
            if (spannableStringBuilder.length() > 0 && spannableStringBuilder.charAt(0) == ' ') {
                spannableStringBuilder.delete(0, 1);
            }
            int i6 = 0;
            while (i6 < spannableStringBuilder.length() - 1) {
                int i7 = i6 + 1;
                if (spannableStringBuilder.charAt(i6) == 10 && spannableStringBuilder.charAt(i7) == ' ') {
                    spannableStringBuilder.delete(i7, i6 + 2);
                }
                i6 = i7;
            }
            if (spannableStringBuilder.length() > 0 && spannableStringBuilder.charAt(spannableStringBuilder.length() - 1) == ' ') {
                spannableStringBuilder.delete(spannableStringBuilder.length() - 1, spannableStringBuilder.length());
            }
            int i8 = 0;
            while (i8 < spannableStringBuilder.length() - 1) {
                int i9 = i8 + 1;
                if (spannableStringBuilder.charAt(i8) == ' ' && spannableStringBuilder.charAt(i9) == 10) {
                    spannableStringBuilder.delete(i8, i9);
                }
                i8 = i9;
            }
            if (spannableStringBuilder.length() > 0 && spannableStringBuilder.charAt(spannableStringBuilder.length() - 1) == 10) {
                spannableStringBuilder.delete(spannableStringBuilder.length() - 1, spannableStringBuilder.length());
            }
            zzeg2.zze(zzamw2.zzc, zzamw2.zzd);
            zzeg2.zzf(zzamw2.zze);
            zzeg2.zzh(zzamw2.zzb);
            zzeg2.zzk(zzamw2.zzf);
            zzeg2.zzn(zzamw2.zzi, zzamw2.zzh);
            zzeg2.zzo(zzamw2.zzj);
            arrayList2.add(zzeg2.zzp());
        }
        return arrayList2;
    }

    public final void zzf(zzams zzams) {
        if (this.zzm == null) {
            this.zzm = new ArrayList();
        }
        this.zzm.add(zzams);
    }

    public final boolean zzg(long j) {
        long j2 = this.zzd;
        if (j2 == C.TIME_UNSET) {
            if (this.zze == C.TIME_UNSET) {
                return true;
            }
            j2 = -9223372036854775807L;
        }
        int i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
        if (i <= 0 && this.zze == C.TIME_UNSET) {
            return true;
        }
        if (j2 == C.TIME_UNSET && j < this.zze) {
            return true;
        }
        if (i <= 0) {
            return j < this.zze;
        }
        return false;
    }

    public final long[] zzh() {
        TreeSet treeSet = new TreeSet();
        int i = 0;
        zzj(treeSet, false);
        long[] jArr = new long[treeSet.size()];
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            jArr[i] = ((Long) it.next()).longValue();
            i++;
        }
        return jArr;
    }

    private zzams(String str, String str2, long j, long j2, zzamy zzamy, String[] strArr, String str3, String str4, zzams zzams) {
        this.zza = str;
        this.zzb = str2;
        this.zzh = str4;
        this.zzf = zzamy;
        this.zzj = strArr;
        this.zzc = str2 != null;
        this.zzd = j;
        this.zze = j2;
        str3.getClass();
        this.zzg = str3;
        this.zzi = zzams;
        this.zzk = new HashMap();
        this.zzl = new HashMap();
    }
}
