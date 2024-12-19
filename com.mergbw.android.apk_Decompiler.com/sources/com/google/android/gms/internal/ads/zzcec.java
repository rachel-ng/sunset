package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzcec extends Thread implements SurfaceTexture.OnFrameAvailableListener, zzcea {
    private static final float[] zza = {-1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f};
    private volatile boolean zzA;
    private volatile boolean zzB;
    private final zzceb zzb;
    private final float[] zzc = new float[9];
    private final float[] zzd = new float[9];
    private final float[] zze = new float[9];
    private final float[] zzf = new float[9];
    private final float[] zzg = new float[9];
    private final float[] zzh = new float[9];
    private final float[] zzi = new float[9];
    private float zzj = Float.NaN;
    private float zzk;
    private float zzl;
    private int zzm;
    private int zzn;
    private SurfaceTexture zzo;
    private SurfaceTexture zzp;
    private int zzq;
    private int zzr;
    private int zzs;
    private final FloatBuffer zzt;
    private final CountDownLatch zzu;
    private final Object zzv;
    private EGL10 zzw;
    private EGLDisplay zzx;
    private EGLContext zzy;
    private EGLSurface zzz;

    public zzcec(Context context) {
        super("SphericalVideoProcessor");
        float[] fArr = zza;
        int length = fArr.length;
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(48).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.zzt = asFloatBuffer;
        asFloatBuffer.put(fArr).position(0);
        zzceb zzceb = new zzceb(context);
        this.zzb = zzceb;
        zzceb.zzb(this);
        this.zzu = new CountDownLatch(1);
        this.zzv = new Object();
    }

    private static final void zzh(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            Log.e("SphericalVideoRenderer", str + ": glError " + glGetError);
        }
    }

    private static final void zzi(float[] fArr, float[] fArr2, float[] fArr3) {
        float f = fArr2[1];
        float f2 = fArr3[3];
        float f3 = fArr2[2];
        float f4 = fArr3[6];
        fArr[0] = (fArr2[0] * fArr3[0]) + (f * f2) + (f3 * f4);
        float f5 = fArr2[0];
        float f6 = fArr3[4];
        float f7 = fArr3[7];
        fArr[1] = (fArr3[1] * f5) + (f * f6) + (f3 * f7);
        float f8 = f5 * fArr3[2];
        float f9 = fArr2[1];
        float f10 = fArr3[5];
        float f11 = fArr3[8];
        fArr[2] = f8 + (f9 * f10) + (f3 * f11);
        float f12 = fArr2[3];
        float f13 = fArr3[0];
        float f14 = fArr2[4];
        float f15 = fArr2[5];
        fArr[3] = (f12 * f13) + (f2 * f14) + (f15 * f4);
        float f16 = fArr2[3];
        float f17 = fArr3[1];
        fArr[4] = (f16 * f17) + (f14 * f6) + (f15 * f7);
        float f18 = fArr3[2];
        fArr[5] = (f16 * f18) + (fArr2[4] * f10) + (f15 * f11);
        float f19 = fArr2[6] * f13;
        float f20 = fArr2[7];
        float f21 = fArr2[8];
        fArr[6] = f19 + (fArr3[3] * f20) + (f4 * f21);
        float f22 = fArr2[6];
        fArr[7] = (f17 * f22) + (f20 * fArr3[4]) + (f7 * f21);
        fArr[8] = (f22 * f18) + (fArr2[7] * fArr3[5]) + (f21 * f11);
    }

    private static final void zzj(float[] fArr, float f) {
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        double d = (double) f;
        fArr[4] = (float) Math.cos(d);
        fArr[5] = (float) (-Math.sin(d));
        fArr[6] = 0.0f;
        fArr[7] = (float) Math.sin(d);
        fArr[8] = (float) Math.cos(d);
    }

    private static final void zzk(float[] fArr, float f) {
        double d = (double) f;
        fArr[0] = (float) Math.cos(d);
        fArr[1] = (float) (-Math.sin(d));
        fArr[2] = 0.0f;
        fArr[3] = (float) Math.sin(d);
        fArr[4] = (float) Math.cos(d);
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
    }

    private static final int zzl(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        zzh("createShader");
        if (glCreateShader != 0) {
            GLES20.glShaderSource(glCreateShader, str);
            zzh("shaderSource");
            GLES20.glCompileShader(glCreateShader);
            zzh("compileShader");
            int[] iArr = new int[1];
            GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
            zzh("getShaderiv");
            if (iArr[0] == 0) {
                Log.e("SphericalVideoRenderer", "Could not compile shader " + i + ":");
                Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(glCreateShader));
                GLES20.glDeleteShader(glCreateShader);
                zzh("deleteShader");
                return 0;
            }
        }
        return glCreateShader;
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.zzs++;
        synchronized (this.zzv) {
            this.zzv.notifyAll();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x01c5 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r15 = this;
            android.graphics.SurfaceTexture r0 = r15.zzp
            if (r0 == 0) goto L_0x0373
            javax.microedition.khronos.egl.EGL r0 = javax.microedition.khronos.egl.EGLContext.getEGL()
            javax.microedition.khronos.egl.EGL10 r0 = (javax.microedition.khronos.egl.EGL10) r0
            r15.zzw = r0
            java.lang.Object r1 = javax.microedition.khronos.egl.EGL10.EGL_DEFAULT_DISPLAY
            javax.microedition.khronos.egl.EGLDisplay r0 = r0.eglGetDisplay(r1)
            r15.zzx = r0
            javax.microedition.khronos.egl.EGLDisplay r1 = javax.microedition.khronos.egl.EGL10.EGL_NO_DISPLAY
            r2 = 2
            r3 = 0
            r4 = 1
            r5 = 0
            if (r0 != r1) goto L_0x001f
        L_0x001c:
            r0 = r5
            goto L_0x008d
        L_0x001f:
            int[] r0 = new int[r2]
            javax.microedition.khronos.egl.EGL10 r1 = r15.zzw
            javax.microedition.khronos.egl.EGLDisplay r6 = r15.zzx
            boolean r0 = r1.eglInitialize(r6, r0)
            if (r0 != 0) goto L_0x002c
            goto L_0x001c
        L_0x002c:
            int[] r0 = new int[r4]
            javax.microedition.khronos.egl.EGLConfig[] r1 = new javax.microedition.khronos.egl.EGLConfig[r4]
            r6 = 11
            int[] r8 = new int[r6]
            r8 = {12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12344} // fill-array
            javax.microedition.khronos.egl.EGL10 r6 = r15.zzw
            javax.microedition.khronos.egl.EGLDisplay r7 = r15.zzx
            r10 = 1
            r9 = r1
            r11 = r0
            boolean r6 = r6.eglChooseConfig(r7, r8, r9, r10, r11)
            if (r6 != 0) goto L_0x0046
        L_0x0044:
            r0 = r3
            goto L_0x004c
        L_0x0046:
            r0 = r0[r5]
            if (r0 <= 0) goto L_0x0044
            r0 = r1[r5]
        L_0x004c:
            if (r0 != 0) goto L_0x004f
            goto L_0x001c
        L_0x004f:
            r1 = 12440(0x3098, float:1.7432E-41)
            r6 = 12344(0x3038, float:1.7298E-41)
            int[] r1 = new int[]{r1, r2, r6}
            javax.microedition.khronos.egl.EGL10 r6 = r15.zzw
            javax.microedition.khronos.egl.EGLDisplay r7 = r15.zzx
            javax.microedition.khronos.egl.EGLContext r8 = javax.microedition.khronos.egl.EGL10.EGL_NO_CONTEXT
            javax.microedition.khronos.egl.EGLContext r1 = r6.eglCreateContext(r7, r0, r8, r1)
            r15.zzy = r1
            if (r1 == 0) goto L_0x001c
            javax.microedition.khronos.egl.EGLContext r6 = javax.microedition.khronos.egl.EGL10.EGL_NO_CONTEXT
            if (r1 != r6) goto L_0x006a
            goto L_0x001c
        L_0x006a:
            javax.microedition.khronos.egl.EGL10 r1 = r15.zzw
            javax.microedition.khronos.egl.EGLDisplay r6 = r15.zzx
            android.graphics.SurfaceTexture r7 = r15.zzp
            javax.microedition.khronos.egl.EGLSurface r0 = r1.eglCreateWindowSurface(r6, r0, r7, r3)
            r15.zzz = r0
            if (r0 == 0) goto L_0x001c
            javax.microedition.khronos.egl.EGLSurface r1 = javax.microedition.khronos.egl.EGL10.EGL_NO_SURFACE
            if (r0 != r1) goto L_0x007d
            goto L_0x001c
        L_0x007d:
            javax.microedition.khronos.egl.EGL10 r0 = r15.zzw
            javax.microedition.khronos.egl.EGLDisplay r1 = r15.zzx
            javax.microedition.khronos.egl.EGLSurface r6 = r15.zzz
            javax.microedition.khronos.egl.EGLContext r7 = r15.zzy
            boolean r0 = r0.eglMakeCurrent(r1, r6, r6, r7)
            if (r0 != 0) goto L_0x008c
            goto L_0x001c
        L_0x008c:
            r0 = r4
        L_0x008d:
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzbk
            com.google.android.gms.internal.ads.zzben r6 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r6 = r6.zza(r1)
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r1.zzm()
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x00ae
            com.google.android.gms.internal.ads.zzben r6 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r1 = r6.zza(r1)
            java.lang.String r1 = (java.lang.String) r1
            goto L_0x00b0
        L_0x00ae:
            java.lang.String r1 = "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}"
        L_0x00b0:
            r6 = 35633(0x8b31, float:4.9932E-41)
            int r1 = zzl(r6, r1)
            if (r1 != 0) goto L_0x00bc
        L_0x00b9:
            r8 = r5
            goto L_0x013e
        L_0x00bc:
            com.google.android.gms.internal.ads.zzbeg r6 = com.google.android.gms.internal.ads.zzbep.zzbl
            com.google.android.gms.internal.ads.zzben r7 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r7 = r7.zza(r6)
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r6.zzm()
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L_0x00dd
            com.google.android.gms.internal.ads.zzben r7 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r6 = r7.zza(r6)
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x00df
        L_0x00dd:
            java.lang.String r6 = "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}"
        L_0x00df:
            r7 = 35632(0x8b30, float:4.9931E-41)
            int r6 = zzl(r7, r6)
            if (r6 != 0) goto L_0x00e9
            goto L_0x00b9
        L_0x00e9:
            java.lang.String r7 = "createProgram"
            int r8 = android.opengl.GLES20.glCreateProgram()
            zzh(r7)
            if (r8 == 0) goto L_0x013e
            android.opengl.GLES20.glAttachShader(r8, r1)
            java.lang.String r1 = "attachShader"
            zzh(r1)
            android.opengl.GLES20.glAttachShader(r8, r6)
            java.lang.String r1 = "attachShader"
            zzh(r1)
            android.opengl.GLES20.glLinkProgram(r8)
            java.lang.String r1 = "linkProgram"
            zzh(r1)
            int[] r1 = new int[r4]
            r6 = 35714(0x8b82, float:5.0046E-41)
            android.opengl.GLES20.glGetProgramiv(r8, r6, r1, r5)
            java.lang.String r6 = "getProgramiv"
            zzh(r6)
            r1 = r1[r5]
            if (r1 == r4) goto L_0x0136
            java.lang.String r1 = "SphericalVideoRenderer"
            java.lang.String r6 = "Could not link program: "
            android.util.Log.e(r1, r6)
            java.lang.String r1 = android.opengl.GLES20.glGetProgramInfoLog(r8)
            java.lang.String r6 = "SphericalVideoRenderer"
            android.util.Log.e(r6, r1)
            android.opengl.GLES20.glDeleteProgram(r8)
            java.lang.String r1 = "deleteProgram"
            zzh(r1)
            goto L_0x00b9
        L_0x0136:
            android.opengl.GLES20.glValidateProgram(r8)
            java.lang.String r1 = "validateProgram"
            zzh(r1)
        L_0x013e:
            r15.zzq = r8
            android.opengl.GLES20.glUseProgram(r8)
            java.lang.String r1 = "useProgram"
            zzh(r1)
            int r1 = r15.zzq
            java.lang.String r6 = "aPosition"
            int r1 = android.opengl.GLES20.glGetAttribLocation(r1, r6)
            r11 = 12
            java.nio.FloatBuffer r12 = r15.zzt
            r8 = 3
            r9 = 5126(0x1406, float:7.183E-42)
            r10 = 0
            r7 = r1
            android.opengl.GLES20.glVertexAttribPointer(r7, r8, r9, r10, r11, r12)
            java.lang.String r6 = "vertexAttribPointer"
            zzh(r6)
            android.opengl.GLES20.glEnableVertexAttribArray(r1)
            java.lang.String r1 = "enableVertexAttribArray"
            zzh(r1)
            int[] r1 = new int[r4]
            android.opengl.GLES20.glGenTextures(r4, r1, r5)
            java.lang.String r6 = "genTextures"
            zzh(r6)
            r1 = r1[r5]
            r6 = 36197(0x8d65, float:5.0723E-41)
            android.opengl.GLES20.glBindTexture(r6, r1)
            java.lang.String r7 = "bindTextures"
            zzh(r7)
            r7 = 10240(0x2800, float:1.4349E-41)
            r8 = 9729(0x2601, float:1.3633E-41)
            android.opengl.GLES20.glTexParameteri(r6, r7, r8)
            java.lang.String r7 = "texParameteri"
            zzh(r7)
            r7 = 10241(0x2801, float:1.435E-41)
            android.opengl.GLES20.glTexParameteri(r6, r7, r8)
            java.lang.String r7 = "texParameteri"
            zzh(r7)
            r7 = 10242(0x2802, float:1.4352E-41)
            r8 = 33071(0x812f, float:4.6342E-41)
            android.opengl.GLES20.glTexParameteri(r6, r7, r8)
            java.lang.String r7 = "texParameteri"
            zzh(r7)
            r7 = 10243(0x2803, float:1.4354E-41)
            android.opengl.GLES20.glTexParameteri(r6, r7, r8)
            java.lang.String r6 = "texParameteri"
            zzh(r6)
            int r6 = r15.zzq
            java.lang.String r7 = "uVMat"
            int r6 = android.opengl.GLES20.glGetUniformLocation(r6, r7)
            r15.zzr = r6
            r7 = 9
            float[] r7 = new float[r7]
            r7 = {1065353216, 0, 0, 0, 1065353216, 0, 0, 0, 1065353216} // fill-array
            android.opengl.GLES20.glUniformMatrix3fv(r6, r4, r5, r7, r5)
            int r6 = r15.zzq
            if (r0 == 0) goto L_0x0345
            if (r6 != 0) goto L_0x01c9
            goto L_0x0345
        L_0x01c9:
            android.graphics.SurfaceTexture r0 = new android.graphics.SurfaceTexture
            r0.<init>(r1)
            r15.zzo = r0
            r0.setOnFrameAvailableListener(r15)
            java.util.concurrent.CountDownLatch r0 = r15.zzu
            r0.countDown()
            com.google.android.gms.internal.ads.zzceb r0 = r15.zzb
            r0.zzc()
            r15.zzA = r4     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
        L_0x01df:
            boolean r0 = r15.zzB     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            if (r0 == 0) goto L_0x01e5
            goto L_0x0324
        L_0x01e5:
            int r0 = r15.zzs     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            if (r0 <= 0) goto L_0x01f5
            android.graphics.SurfaceTexture r0 = r15.zzo     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r0.updateTexImage()     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            int r0 = r15.zzs     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            int r0 = r0 + -1
            r15.zzs = r0     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            goto L_0x01e5
        L_0x01f5:
            com.google.android.gms.internal.ads.zzceb r0 = r15.zzb     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float[] r1 = r15.zzc     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            boolean r0 = r0.zze(r1)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r1 = 5
            r6 = -1077342245(0xffffffffbfc90fdb, float:-1.5707964)
            r7 = 4
            if (r0 == 0) goto L_0x0260
            float r0 = r15.zzj     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            boolean r0 = java.lang.Float.isNaN(r0)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            if (r0 == 0) goto L_0x0255
            float[] r0 = r15.zzc     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r8 = 3
            float[] r9 = new float[r8]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r9 = {0, 1065353216, 0} // fill-array     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r10 = r0[r5]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r11 = r9[r5]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r10 = r10 * r11
            r12 = r0[r4]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r9 = r9[r4]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r12 = r12 * r9
            float r10 = r10 + r12
            r12 = r0[r2]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r13 = 0
            float r12 = r12 * r13
            float r10 = r10 + r12
            r12 = r0[r8]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r12 = r12 * r11
            r14 = r0[r7]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r14 = r14 * r9
            float r12 = r12 + r14
            r14 = r0[r1]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r14 = r14 * r13
            float r12 = r12 + r14
            r14 = 6
            r14 = r0[r14]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r14 = r14 * r11
            r11 = 7
            r11 = r0[r11]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r11 = r11 * r9
            float r14 = r14 + r11
            r9 = 8
            r0 = r0[r9]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r0 = r0 * r13
            float r14 = r14 + r0
            float[] r0 = new float[r8]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r0[r5] = r10     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r0[r4] = r12     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r0[r2] = r14     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r8 = r0[r4]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            double r8 = (double) r8     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r0 = r0[r5]     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            double r10 = (double) r0     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            double r8 = java.lang.Math.atan2(r8, r10)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r0 = (float) r8     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r0 = r0 + r6
            float r0 = -r0
            r15.zzj = r0     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
        L_0x0255:
            float[] r0 = r15.zzh     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r6 = r15.zzj     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r8 = r15.zzk     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r6 = r6 + r8
            zzk(r0, r6)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            goto L_0x026c
        L_0x0260:
            float[] r0 = r15.zzc     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            zzj(r0, r6)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float[] r0 = r15.zzh     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r6 = r15.zzk     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            zzk(r0, r6)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
        L_0x026c:
            float[] r0 = r15.zzd     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r6 = 1070141403(0x3fc90fdb, float:1.5707964)
            zzj(r0, r6)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float[] r0 = r15.zze     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float[] r6 = r15.zzh     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float[] r8 = r15.zzd     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            zzi(r0, r6, r8)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float[] r0 = r15.zzf     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float[] r6 = r15.zzc     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float[] r8 = r15.zze     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            zzi(r0, r6, r8)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float[] r0 = r15.zzg     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r6 = r15.zzl     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            zzj(r0, r6)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float[] r0 = r15.zzi     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float[] r6 = r15.zzg     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float[] r8 = r15.zzf     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            zzi(r0, r6, r8)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            int r0 = r15.zzr     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float[] r6 = r15.zzi     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            android.opengl.GLES20.glUniformMatrix3fv(r0, r4, r5, r6, r5)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            android.opengl.GLES20.glDrawArrays(r1, r5, r7)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            java.lang.String r0 = "drawArrays"
            zzh(r0)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            android.opengl.GLES20.glFinish()     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            javax.microedition.khronos.egl.EGL10 r0 = r15.zzw     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            javax.microedition.khronos.egl.EGLDisplay r1 = r15.zzx     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            javax.microedition.khronos.egl.EGLSurface r6 = r15.zzz     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r0.eglSwapBuffers(r1, r6)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            boolean r0 = r15.zzA     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            if (r0 == 0) goto L_0x02f5
            int r0 = r15.zzn     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            int r1 = r15.zzm     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            android.opengl.GLES20.glViewport(r5, r5, r0, r1)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            java.lang.String r0 = "viewport"
            zzh(r0)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            int r0 = r15.zzq     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            java.lang.String r1 = "uFOVx"
            int r0 = android.opengl.GLES20.glGetUniformLocation(r0, r1)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            int r1 = r15.zzq     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            java.lang.String r6 = "uFOVy"
            int r1 = android.opengl.GLES20.glGetUniformLocation(r1, r6)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            int r6 = r15.zzn     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            int r7 = r15.zzm     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            r8 = 1063216883(0x3f5f66f3, float:0.87266463)
            if (r6 <= r7) goto L_0x02e9
            android.opengl.GLES20.glUniform1f(r0, r8)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            int r0 = r15.zzm     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r0 = (float) r0     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r0 = r0 * r8
            int r6 = r15.zzn     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r6 = (float) r6     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r0 = r0 / r6
            android.opengl.GLES20.glUniform1f(r1, r0)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            goto L_0x02f3
        L_0x02e9:
            float r6 = (float) r6     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r6 = r6 * r8
            float r7 = (float) r7     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            float r6 = r6 / r7
            android.opengl.GLES20.glUniform1f(r0, r6)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
            android.opengl.GLES20.glUniform1f(r1, r8)     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
        L_0x02f3:
            r15.zzA = r5     // Catch:{ IllegalStateException -> 0x031f, all -> 0x030f }
        L_0x02f5:
            java.lang.Object r0 = r15.zzv     // Catch:{ InterruptedException -> 0x01df }
            monitor-enter(r0)     // Catch:{ InterruptedException -> 0x01df }
            boolean r1 = r15.zzB     // Catch:{ all -> 0x030c }
            if (r1 != 0) goto L_0x0309
            boolean r1 = r15.zzA     // Catch:{ all -> 0x030c }
            if (r1 != 0) goto L_0x0309
            int r1 = r15.zzs     // Catch:{ all -> 0x030c }
            if (r1 != 0) goto L_0x0309
            java.lang.Object r1 = r15.zzv     // Catch:{ all -> 0x030c }
            r1.wait()     // Catch:{ all -> 0x030c }
        L_0x0309:
            monitor-exit(r0)     // Catch:{ all -> 0x030c }
            goto L_0x01df
        L_0x030c:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x030c }
            throw r1     // Catch:{ InterruptedException -> 0x01df }
        L_0x030f:
            r0 = move-exception
            java.lang.String r1 = "SphericalVideoProcessor died."
            com.google.android.gms.ads.internal.util.client.zzm.zzh(r1, r0)     // Catch:{ all -> 0x0334 }
            com.google.android.gms.internal.ads.zzcby r1 = com.google.android.gms.ads.internal.zzu.zzo()     // Catch:{ all -> 0x0334 }
            java.lang.String r2 = "SphericalVideoProcessor.run.2"
            r1.zzw(r0, r2)     // Catch:{ all -> 0x0334 }
            goto L_0x0324
        L_0x031f:
            java.lang.String r0 = "SphericalVideoProcessor halted unexpectedly."
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r0)     // Catch:{ all -> 0x0334 }
        L_0x0324:
            com.google.android.gms.internal.ads.zzceb r0 = r15.zzb
            r0.zzd()
            android.graphics.SurfaceTexture r0 = r15.zzo
            r0.setOnFrameAvailableListener(r3)
            r15.zzo = r3
            r15.zzg()
            return
        L_0x0334:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzceb r1 = r15.zzb
            r1.zzd()
            android.graphics.SurfaceTexture r1 = r15.zzo
            r1.setOnFrameAvailableListener(r3)
            r15.zzo = r3
            r15.zzg()
            throw r0
        L_0x0345:
            javax.microedition.khronos.egl.EGL10 r0 = r15.zzw
            int r0 = r0.eglGetError()
            java.lang.String r0 = android.opengl.GLUtils.getEGLErrorString(r0)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = "EGL initialization failed: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.ads.internal.util.client.zzm.zzg(r0)
            com.google.android.gms.internal.ads.zzcby r1 = com.google.android.gms.ads.internal.zzu.zzo()
            java.lang.Throwable r2 = new java.lang.Throwable
            r2.<init>(r0)
            java.lang.String r0 = "SphericalVideoProcessor.run.1"
            r1.zzw(r2, r0)
            r15.zzg()
            java.util.concurrent.CountDownLatch r0 = r15.zzu
            r0.countDown()
            return
        L_0x0373:
            java.lang.String r0 = "SphericalVideoProcessor started with no output texture."
            com.google.android.gms.ads.internal.util.client.zzm.zzg(r0)
            java.util.concurrent.CountDownLatch r0 = r15.zzu
            r0.countDown()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcec.run():void");
    }

    public final void zza() {
        synchronized (this.zzv) {
            this.zzv.notifyAll();
        }
    }

    public final SurfaceTexture zzb() {
        if (this.zzp == null) {
            return null;
        }
        try {
            this.zzu.await();
        } catch (InterruptedException unused) {
        }
        return this.zzo;
    }

    public final void zzc(int i, int i2) {
        synchronized (this.zzv) {
            this.zzn = i;
            this.zzm = i2;
            this.zzA = true;
            this.zzv.notifyAll();
        }
    }

    public final void zzd(SurfaceTexture surfaceTexture, int i, int i2) {
        this.zzn = i;
        this.zzm = i2;
        this.zzp = surfaceTexture;
    }

    public final void zze() {
        synchronized (this.zzv) {
            this.zzB = true;
            this.zzp = null;
            this.zzv.notifyAll();
        }
    }

    public final void zzf(float f, float f2) {
        int i = this.zzn;
        int i2 = this.zzm;
        if (i <= i2) {
            i = i2;
        }
        float f3 = (float) i;
        this.zzk -= (f * 1.7453293f) / f3;
        float f4 = this.zzl - ((f2 * 1.7453293f) / f3);
        this.zzl = f4;
        if (f4 < -1.5707964f) {
            this.zzl = -1.5707964f;
            f4 = -1.5707964f;
        }
        if (f4 > 1.5707964f) {
            this.zzl = 1.5707964f;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzg() {
        EGLSurface eGLSurface = this.zzz;
        boolean z = false;
        if (!(eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE)) {
            z = this.zzw.eglDestroySurface(this.zzx, this.zzz) | this.zzw.eglMakeCurrent(this.zzx, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            this.zzz = null;
        }
        EGLContext eGLContext = this.zzy;
        if (eGLContext != null) {
            z |= this.zzw.eglDestroyContext(this.zzx, eGLContext);
            this.zzy = null;
        }
        EGLDisplay eGLDisplay = this.zzx;
        if (eGLDisplay == null) {
            return z;
        }
        boolean eglTerminate = this.zzw.eglTerminate(eGLDisplay) | z;
        this.zzx = null;
        return eglTerminate;
    }
}
