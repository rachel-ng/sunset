package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzahz extends zzahr {
    public static final Parcelable.Creator<zzahz> CREATOR = new zzahy();
    public final String zza;
    @Deprecated
    public final String zzb;
    public final zzgbc zzc;

    public zzahz(String str, String str2, List list) {
        super(str);
        zzeq.zzd(!list.isEmpty());
        this.zza = str2;
        zzgbc zzk = zzgbc.zzk(list);
        this.zzc = zzk;
        this.zzb = (String) zzk.get(0);
    }

    private static List zzb(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            if (str.length() >= 10) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(5, 7))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(8, 10))));
            } else if (str.length() >= 7) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(5, 7))));
            } else if (str.length() >= 4) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
            }
            return arrayList;
        } catch (NumberFormatException unused) {
            return new ArrayList();
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzahz zzahz = (zzahz) obj;
            return zzgd.zzG(this.zzf, zzahz.zzf) && zzgd.zzG(this.zza, zzahz.zza) && this.zzc.equals(zzahz.zzc);
        }
    }

    public final int hashCode() {
        int hashCode = this.zzf.hashCode() + 527;
        String str = this.zza;
        return (((hashCode * 31) + (str != null ? str.hashCode() : 0)) * 31) + this.zzc.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzc);
        return this.zzf + ": description=" + this.zza + ": values=" + valueOf;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzf);
        parcel.writeString(this.zza);
        parcel.writeStringArray((String[]) this.zzc.toArray(new String[0]));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.ads.zzby r9) {
        /*
            r8 = this;
            java.lang.String r0 = r8.zzf
            int r1 = r0.hashCode()
            r2 = 4
            r3 = -1
            r4 = 3
            r5 = 1
            r6 = 2
            r7 = 0
            switch(r1) {
                case 82815: goto L_0x00fe;
                case 82878: goto L_0x00f3;
                case 82897: goto L_0x00e8;
                case 83253: goto L_0x00de;
                case 83254: goto L_0x00d4;
                case 83255: goto L_0x00c9;
                case 83341: goto L_0x00be;
                case 83378: goto L_0x00b4;
                case 83536: goto L_0x00a9;
                case 83552: goto L_0x009d;
                case 2567331: goto L_0x0092;
                case 2569357: goto L_0x0086;
                case 2569891: goto L_0x007a;
                case 2570401: goto L_0x006e;
                case 2570410: goto L_0x0062;
                case 2571565: goto L_0x0056;
                case 2575251: goto L_0x004b;
                case 2581512: goto L_0x0040;
                case 2581513: goto L_0x0035;
                case 2581514: goto L_0x0029;
                case 2583398: goto L_0x001d;
                case 2590194: goto L_0x0011;
                default: goto L_0x000f;
            }
        L_0x000f:
            goto L_0x0108
        L_0x0011:
            java.lang.String r1 = "TYER"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 11
            goto L_0x0109
        L_0x001d:
            java.lang.String r1 = "TRCK"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 9
            goto L_0x0109
        L_0x0029:
            java.lang.String r1 = "TPE3"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 19
            goto L_0x0109
        L_0x0035:
            java.lang.String r1 = "TPE2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 5
            goto L_0x0109
        L_0x0040:
            java.lang.String r1 = "TPE1"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = r4
            goto L_0x0109
        L_0x004b:
            java.lang.String r1 = "TIT2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = r5
            goto L_0x0109
        L_0x0056:
            java.lang.String r1 = "TEXT"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 21
            goto L_0x0109
        L_0x0062:
            java.lang.String r1 = "TDRL"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 15
            goto L_0x0109
        L_0x006e:
            java.lang.String r1 = "TDRC"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 14
            goto L_0x0109
        L_0x007a:
            java.lang.String r1 = "TDAT"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 13
            goto L_0x0109
        L_0x0086:
            java.lang.String r1 = "TCOM"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 17
            goto L_0x0109
        L_0x0092:
            java.lang.String r1 = "TALB"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 7
            goto L_0x0109
        L_0x009d:
            java.lang.String r1 = "TYE"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 10
            goto L_0x0109
        L_0x00a9:
            java.lang.String r1 = "TXT"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 20
            goto L_0x0109
        L_0x00b4:
            java.lang.String r1 = "TT2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = r7
            goto L_0x0109
        L_0x00be:
            java.lang.String r1 = "TRK"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 8
            goto L_0x0109
        L_0x00c9:
            java.lang.String r1 = "TP3"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 18
            goto L_0x0109
        L_0x00d4:
            java.lang.String r1 = "TP2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = r2
            goto L_0x0109
        L_0x00de:
            java.lang.String r1 = "TP1"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = r6
            goto L_0x0109
        L_0x00e8:
            java.lang.String r1 = "TDA"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 12
            goto L_0x0109
        L_0x00f3:
            java.lang.String r1 = "TCM"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 16
            goto L_0x0109
        L_0x00fe:
            java.lang.String r1 = "TAL"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0108
            r0 = 6
            goto L_0x0109
        L_0x0108:
            r0 = r3
        L_0x0109:
            switch(r0) {
                case 0: goto L_0x0229;
                case 1: goto L_0x0229;
                case 2: goto L_0x021d;
                case 3: goto L_0x021d;
                case 4: goto L_0x0211;
                case 5: goto L_0x0211;
                case 6: goto L_0x0205;
                case 7: goto L_0x0205;
                case 8: goto L_0x01d5;
                case 9: goto L_0x01d5;
                case 10: goto L_0x01c1;
                case 11: goto L_0x01c1;
                case 12: goto L_0x019a;
                case 13: goto L_0x019a;
                case 14: goto L_0x0166;
                case 15: goto L_0x0132;
                case 16: goto L_0x0126;
                case 17: goto L_0x0126;
                case 18: goto L_0x011a;
                case 19: goto L_0x011a;
                case 20: goto L_0x010e;
                case 21: goto L_0x010e;
                default: goto L_0x010c;
            }
        L_0x010c:
            goto L_0x0234
        L_0x010e:
            com.google.android.gms.internal.ads.zzgbc r0 = r8.zzc
            java.lang.Object r0 = r0.get(r7)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.zzt(r0)
            return
        L_0x011a:
            com.google.android.gms.internal.ads.zzgbc r0 = r8.zzc
            java.lang.Object r0 = r0.get(r7)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.zzg(r0)
            return
        L_0x0126:
            com.google.android.gms.internal.ads.zzgbc r0 = r8.zzc
            java.lang.Object r0 = r0.get(r7)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.zzf(r0)
            return
        L_0x0132:
            com.google.android.gms.internal.ads.zzgbc r0 = r8.zzc
            java.lang.Object r0 = r0.get(r7)
            java.lang.String r0 = (java.lang.String) r0
            java.util.List r0 = zzb(r0)
            int r1 = r0.size()
            if (r1 == r5) goto L_0x015c
            if (r1 == r6) goto L_0x0153
            if (r1 == r4) goto L_0x014a
            goto L_0x0234
        L_0x014a:
            java.lang.Object r1 = r0.get(r6)
            java.lang.Integer r1 = (java.lang.Integer) r1
            r9.zzm(r1)
        L_0x0153:
            java.lang.Object r1 = r0.get(r5)
            java.lang.Integer r1 = (java.lang.Integer) r1
            r9.zzn(r1)
        L_0x015c:
            java.lang.Object r0 = r0.get(r7)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r9.zzo(r0)
            return
        L_0x0166:
            com.google.android.gms.internal.ads.zzgbc r0 = r8.zzc
            java.lang.Object r0 = r0.get(r7)
            java.lang.String r0 = (java.lang.String) r0
            java.util.List r0 = zzb(r0)
            int r1 = r0.size()
            if (r1 == r5) goto L_0x0190
            if (r1 == r6) goto L_0x0187
            if (r1 == r4) goto L_0x017e
            goto L_0x0234
        L_0x017e:
            java.lang.Object r1 = r0.get(r6)
            java.lang.Integer r1 = (java.lang.Integer) r1
            r9.zzj(r1)
        L_0x0187:
            java.lang.Object r1 = r0.get(r5)
            java.lang.Integer r1 = (java.lang.Integer) r1
            r9.zzk(r1)
        L_0x0190:
            java.lang.Object r0 = r0.get(r7)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r9.zzl(r0)
            return
        L_0x019a:
            com.google.android.gms.internal.ads.zzgbc r0 = r8.zzc     // Catch:{ NumberFormatException | StringIndexOutOfBoundsException -> 0x01c0 }
            java.lang.Object r0 = r0.get(r7)     // Catch:{ NumberFormatException | StringIndexOutOfBoundsException -> 0x01c0 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NumberFormatException | StringIndexOutOfBoundsException -> 0x01c0 }
            java.lang.String r1 = r0.substring(r6, r2)     // Catch:{ NumberFormatException | StringIndexOutOfBoundsException -> 0x01c0 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException | StringIndexOutOfBoundsException -> 0x01c0 }
            java.lang.String r0 = r0.substring(r7, r6)     // Catch:{ NumberFormatException | StringIndexOutOfBoundsException -> 0x01c0 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException | StringIndexOutOfBoundsException -> 0x01c0 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ NumberFormatException | StringIndexOutOfBoundsException -> 0x01c0 }
            r9.zzk(r1)     // Catch:{ NumberFormatException | StringIndexOutOfBoundsException -> 0x01c0 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ NumberFormatException | StringIndexOutOfBoundsException -> 0x01c0 }
            r9.zzj(r0)     // Catch:{ NumberFormatException | StringIndexOutOfBoundsException -> 0x01c0 }
        L_0x01c0:
            return
        L_0x01c1:
            com.google.android.gms.internal.ads.zzgbc r0 = r8.zzc     // Catch:{ NumberFormatException -> 0x01d4 }
            java.lang.Object r0 = r0.get(r7)     // Catch:{ NumberFormatException -> 0x01d4 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NumberFormatException -> 0x01d4 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x01d4 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ NumberFormatException -> 0x01d4 }
            r9.zzl(r0)     // Catch:{ NumberFormatException -> 0x01d4 }
        L_0x01d4:
            return
        L_0x01d5:
            com.google.android.gms.internal.ads.zzgbc r0 = r8.zzc
            java.lang.Object r0 = r0.get(r7)
            java.lang.String r0 = (java.lang.String) r0
            int r1 = com.google.android.gms.internal.ads.zzgd.zza
            java.lang.String r1 = "/"
            java.lang.String[] r0 = r0.split(r1, r3)
            r1 = r0[r7]     // Catch:{ NumberFormatException -> 0x0204 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x0204 }
            int r2 = r0.length     // Catch:{ NumberFormatException -> 0x0204 }
            if (r2 <= r5) goto L_0x01f9
            r0 = r0[r5]     // Catch:{ NumberFormatException -> 0x0204 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x0204 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ NumberFormatException -> 0x0204 }
            goto L_0x01fa
        L_0x01f9:
            r0 = 0
        L_0x01fa:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ NumberFormatException -> 0x0204 }
            r9.zzs(r1)     // Catch:{ NumberFormatException -> 0x0204 }
            r9.zzr(r0)     // Catch:{ NumberFormatException -> 0x0204 }
        L_0x0204:
            return
        L_0x0205:
            com.google.android.gms.internal.ads.zzgbc r0 = r8.zzc
            java.lang.Object r0 = r0.get(r7)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.zzd(r0)
            return
        L_0x0211:
            com.google.android.gms.internal.ads.zzgbc r0 = r8.zzc
            java.lang.Object r0 = r0.get(r7)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.zzc(r0)
            return
        L_0x021d:
            com.google.android.gms.internal.ads.zzgbc r0 = r8.zzc
            java.lang.Object r0 = r0.get(r7)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.zze(r0)
            return
        L_0x0229:
            com.google.android.gms.internal.ads.zzgbc r0 = r8.zzc
            java.lang.Object r0 = r0.get(r7)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.zzq(r0)
        L_0x0234:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahz.zza(com.google.android.gms.internal.ads.zzby):void");
    }
}
