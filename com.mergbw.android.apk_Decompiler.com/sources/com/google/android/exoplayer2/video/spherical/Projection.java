package com.google.android.exoplayer2.video.spherical;

import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

final class Projection {
    public static final int DRAW_MODE_TRIANGLES = 0;
    public static final int DRAW_MODE_TRIANGLES_FAN = 2;
    public static final int DRAW_MODE_TRIANGLES_STRIP = 1;
    public static final int POSITION_COORDS_PER_VERTEX = 3;
    public static final int TEXTURE_COORDS_PER_VERTEX = 2;
    public final Mesh leftMesh;
    public final Mesh rightMesh;
    public final boolean singleMesh;
    public final int stereoMode;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DrawMode {
    }

    public static Projection createEquirectangular(int i) {
        return createEquirectangular(50.0f, 36, 72, 180.0f, 360.0f, i);
    }

    public static Projection createEquirectangular(float f, int i, int i2, float f2, float f3, int i3) {
        int i4;
        float f4;
        int i5;
        float[] fArr;
        int i6;
        int i7;
        int i8;
        float f5 = f;
        int i9 = i;
        int i10 = i2;
        float f6 = f2;
        float f7 = f3;
        Assertions.checkArgument(f5 > 0.0f);
        Assertions.checkArgument(i9 >= 1);
        Assertions.checkArgument(i10 >= 1);
        Assertions.checkArgument(f6 > 0.0f && f6 <= 180.0f);
        Assertions.checkArgument(f7 > 0.0f && f7 <= 360.0f);
        float radians = (float) Math.toRadians((double) f6);
        float radians2 = (float) Math.toRadians((double) f7);
        float f8 = radians / ((float) i9);
        float f9 = radians2 / ((float) i10);
        int i11 = i10 + 1;
        int i12 = ((i11 * 2) + 2) * i9;
        float[] fArr2 = new float[(i12 * 3)];
        float[] fArr3 = new float[(i12 * 2)];
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (i13 < i9) {
            float f10 = radians / 2.0f;
            float f11 = (((float) i13) * f8) - f10;
            int i16 = i13 + 1;
            float f12 = (((float) i16) * f8) - f10;
            int i17 = 0;
            while (i17 < i11) {
                float f13 = f11;
                int i18 = i16;
                int i19 = 0;
                int i20 = 2;
                while (i19 < i20) {
                    if (i19 == 0) {
                        f4 = f13;
                        i4 = i11;
                    } else {
                        i4 = i11;
                        f4 = f12;
                    }
                    float f14 = ((float) i17) * f9;
                    float f15 = f9;
                    int i21 = i17;
                    double d = (double) f5;
                    float f16 = f8;
                    double d2 = (double) ((f14 + 3.1415927f) - (radians2 / 2.0f));
                    int i22 = i19;
                    double d3 = (double) f4;
                    float[] fArr4 = fArr3;
                    float f17 = f12;
                    fArr2[i14] = -((float) (Math.sin(d2) * d * Math.cos(d3)));
                    float f18 = radians;
                    float f19 = radians2;
                    fArr2[i14 + 1] = (float) (d * Math.sin(d3));
                    int i23 = i14 + 3;
                    fArr2[i14 + 2] = (float) (d * Math.cos(d2) * Math.cos(d3));
                    fArr4[i15] = f14 / f19;
                    int i24 = i15 + 2;
                    fArr4[i15 + 1] = (((float) (i13 + i22)) * f16) / f18;
                    if (i21 == 0 && i22 == 0) {
                        i8 = i2;
                        i7 = i21;
                        i6 = i22;
                    } else {
                        i8 = i2;
                        i7 = i21;
                        i6 = i22;
                        if (!(i7 == i8 && i6 == 1)) {
                            fArr = fArr4;
                            i5 = 2;
                            i15 = i24;
                            i14 = i23;
                            int i25 = i6 + 1;
                            fArr3 = fArr;
                            i20 = i5;
                            radians = f18;
                            i11 = i4;
                            f9 = f15;
                            f8 = f16;
                            f12 = f17;
                            i19 = i25;
                            i10 = i8;
                            i17 = i7;
                            radians2 = f19;
                        }
                    }
                    System.arraycopy(fArr2, i14, fArr2, i23, 3);
                    i14 += 6;
                    fArr = fArr4;
                    i5 = 2;
                    System.arraycopy(fArr, i15, fArr, i24, 2);
                    i15 += 4;
                    int i252 = i6 + 1;
                    fArr3 = fArr;
                    i20 = i5;
                    radians = f18;
                    i11 = i4;
                    f9 = f15;
                    f8 = f16;
                    f12 = f17;
                    i19 = i252;
                    i10 = i8;
                    i17 = i7;
                    radians2 = f19;
                }
                float f20 = radians2;
                float f21 = f8;
                float f22 = f9;
                int i26 = i20;
                int i27 = i11;
                float[] fArr5 = fArr3;
                float f23 = f12;
                int i28 = i17;
                int i29 = i10;
                float f24 = radians;
                int i30 = i28 + 1;
                f11 = f13;
                i16 = i18;
                f9 = f22;
                radians2 = f20;
                f12 = f23;
                int i31 = i30;
                i10 = i29;
                i17 = i31;
            }
            i9 = i;
            i13 = i16;
        }
        return new Projection(new Mesh(new SubMesh(0, fArr2, fArr3, 1)), i3);
    }

    public Projection(Mesh mesh, int i) {
        this(mesh, mesh, i);
    }

    public Projection(Mesh mesh, Mesh mesh2, int i) {
        this.leftMesh = mesh;
        this.rightMesh = mesh2;
        this.stereoMode = i;
        this.singleMesh = mesh == mesh2;
    }

    public static final class SubMesh {
        public static final int VIDEO_TEXTURE_ID = 0;
        public final int mode;
        public final float[] textureCoords;
        public final int textureId;
        public final float[] vertices;

        public SubMesh(int i, float[] fArr, float[] fArr2, int i2) {
            this.textureId = i;
            Assertions.checkArgument(((long) fArr.length) * 2 == ((long) fArr2.length) * 3);
            this.vertices = fArr;
            this.textureCoords = fArr2;
            this.mode = i2;
        }

        public int getVertexCount() {
            return this.vertices.length / 3;
        }
    }

    public static final class Mesh {
        private final SubMesh[] subMeshes;

        public Mesh(SubMesh... subMeshArr) {
            this.subMeshes = subMeshArr;
        }

        public int getSubMeshCount() {
            return this.subMeshes.length;
        }

        public SubMesh getSubMesh(int i) {
            return this.subMeshes[i];
        }
    }
}
