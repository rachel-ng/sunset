package a;

import java.util.ArrayList;
import java.util.Iterator;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private int f6a;

    /* renamed from: b  reason: collision with root package name */
    private String f7b;

    /* renamed from: c  reason: collision with root package name */
    private ArrayList f8c = new ArrayList();

    public a(String str, boolean z) {
        this.f7b = str;
        a(str, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0099, code lost:
        r13.f8c.add(new a.c(r3, r0.toString(), r15));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r14, boolean r15) {
        /*
            r13 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.io.File r1 = new java.io.File
            r1.<init>(r14)
            java.io.BufferedReader r14 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r2.<init>(r1)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r14.<init>(r2)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r1 = 0
            java.lang.String r2 = ""
            r4 = r1
            r3 = r2
        L_0x0019:
            java.lang.String r5 = r14.readLine()     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            if (r5 == 0) goto L_0x00b1
            java.lang.String r5 = r5.trim()     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r6 = 3
            r7 = 1
            java.lang.String r8 = r5.substring(r7, r6)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r9 = 16
            int r8 = java.lang.Integer.parseInt(r8, r9)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r9 = 7
            r10 = 9
            java.lang.String r11 = r5.substring(r9, r10)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            java.lang.String r12 = "04"
            boolean r11 = r11.equals(r12)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            if (r11 == 0) goto L_0x005f
            int r4 = r0.length()     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            if (r4 <= 0) goto L_0x0052
            java.util.ArrayList r4 = r13.f8c     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            a.c r6 = new a.c     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            java.lang.String r0 = r0.toString()     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r6.<init>(r3, r0, r15)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r4.add(r6)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
        L_0x0052:
            r0 = 13
            java.lang.String r3 = r5.substring(r10, r0)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r0.<init>(r2)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r4 = r1
            goto L_0x0019
        L_0x005f:
            java.lang.String r11 = r5.substring(r9, r10)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            java.lang.String r12 = "05"
            boolean r11 = r11.equals(r12)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            if (r11 != 0) goto L_0x0099
            java.lang.String r11 = r5.substring(r9, r10)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            java.lang.String r12 = "01"
            boolean r11 = r11.equals(r12)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            if (r11 == 0) goto L_0x0078
            goto L_0x0099
        L_0x0078:
            if (r4 != 0) goto L_0x008e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r4.<init>()     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r4.append(r3)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            java.lang.String r3 = r5.substring(r6, r9)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r4.append(r3)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            java.lang.String r3 = r4.toString()     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r4 = r7
        L_0x008e:
            int r8 = r8 * 2
            int r8 = r8 + r10
            java.lang.String r5 = r5.substring(r10, r8)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r0.append(r5)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            goto L_0x0019
        L_0x0099:
            java.util.ArrayList r14 = r13.f8c     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            a.c r1 = new a.c     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            java.lang.String r0 = r0.toString()     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r1.<init>(r3, r0, r15)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            r14.add(r1)     // Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00a8 }
            goto L_0x00b1
        L_0x00a8:
            r14 = 101(0x65, float:1.42E-43)
            r13.f6a = r14
            goto L_0x00b1
        L_0x00ad:
            r14 = 100
            r13.f6a = r14
        L_0x00b1:
            r14 = 200(0xc8, float:2.8E-43)
            r13.f6a = r14
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.a(java.lang.String, boolean):void");
    }

    public long b() {
        Iterator it = this.f8c.iterator();
        long j = 0;
        while (it.hasNext()) {
            j += (long) ((c) it.next()).d();
        }
        return j;
    }

    public ArrayList c() {
        return this.f8c;
    }

    public String d() {
        return this.f7b;
    }

    public int a() {
        return this.f6a;
    }
}
