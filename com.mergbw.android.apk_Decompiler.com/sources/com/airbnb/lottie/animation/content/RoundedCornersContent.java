package com.airbnb.lottie.animation.content;

import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.RoundedCorners;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;

public class RoundedCornersContent implements ShapeModifierContent, BaseKeyframeAnimation.AnimationListener {
    private static final float ROUNDED_CORNER_MAGIC_NUMBER = 0.5519f;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<Float, Float> roundedCorners;
    private ShapeData shapeData;

    public void setContents(List<Content> list, List<Content> list2) {
    }

    public RoundedCornersContent(LottieDrawable lottieDrawable2, BaseLayer baseLayer, RoundedCorners roundedCorners2) {
        this.lottieDrawable = lottieDrawable2;
        this.name = roundedCorners2.getName();
        BaseKeyframeAnimation<Float, Float> createAnimation = roundedCorners2.getCornerRadius().createAnimation();
        this.roundedCorners = createAnimation;
        baseLayer.addAnimation(createAnimation);
        createAnimation.addUpdateListener(this);
    }

    public String getName() {
        return this.name;
    }

    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    public BaseKeyframeAnimation<Float, Float> getRoundedCorners() {
        return this.roundedCorners;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x009c, code lost:
        if (r7 == (r0.size() - 1)) goto L_0x00a1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.airbnb.lottie.model.content.ShapeData modifyShape(com.airbnb.lottie.model.content.ShapeData r19) {
        /*
            r18 = this;
            java.util.List r0 = r19.getCurves()
            int r1 = r0.size()
            r2 = 2
            if (r1 > r2) goto L_0x000c
            return r19
        L_0x000c:
            r1 = r18
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r2 = r1.roundedCorners
            java.lang.Object r2 = r2.getValue()
            java.lang.Float r2 = (java.lang.Float) r2
            float r2 = r2.floatValue()
            r3 = 0
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 != 0) goto L_0x0020
            return r19
        L_0x0020:
            com.airbnb.lottie.model.content.ShapeData r3 = r18.getShapeData(r19)
            android.graphics.PointF r4 = r19.getInitialPoint()
            float r4 = r4.x
            android.graphics.PointF r5 = r19.getInitialPoint()
            float r5 = r5.y
            r3.setInitialPoint(r4, r5)
            java.util.List r4 = r3.getCurves()
            boolean r5 = r19.isClosed()
            r7 = 0
            r8 = 0
        L_0x003d:
            int r9 = r0.size()
            if (r7 >= r9) goto L_0x01ae
            java.lang.Object r9 = r0.get(r7)
            com.airbnb.lottie.model.CubicCurveData r9 = (com.airbnb.lottie.model.CubicCurveData) r9
            int r10 = r7 + -1
            int r11 = r0.size()
            int r10 = floorMod(r10, r11)
            java.lang.Object r10 = r0.get(r10)
            com.airbnb.lottie.model.CubicCurveData r10 = (com.airbnb.lottie.model.CubicCurveData) r10
            int r11 = r7 + -2
            int r12 = r0.size()
            int r11 = floorMod(r11, r12)
            java.lang.Object r11 = r0.get(r11)
            com.airbnb.lottie.model.CubicCurveData r11 = (com.airbnb.lottie.model.CubicCurveData) r11
            if (r7 != 0) goto L_0x0072
            if (r5 != 0) goto L_0x0072
            android.graphics.PointF r12 = r19.getInitialPoint()
            goto L_0x0076
        L_0x0072:
            android.graphics.PointF r12 = r10.getVertex()
        L_0x0076:
            if (r7 != 0) goto L_0x007c
            if (r5 != 0) goto L_0x007c
            r13 = r12
            goto L_0x0080
        L_0x007c:
            android.graphics.PointF r13 = r10.getControlPoint2()
        L_0x0080:
            android.graphics.PointF r14 = r9.getControlPoint1()
            android.graphics.PointF r11 = r11.getVertex()
            android.graphics.PointF r15 = r9.getVertex()
            boolean r16 = r19.isClosed()
            if (r16 != 0) goto L_0x009f
            if (r7 != 0) goto L_0x009f
            int r16 = r0.size()
            r17 = 1
            int r6 = r16 + -1
            if (r7 != r6) goto L_0x009f
            goto L_0x00a1
        L_0x009f:
            r17 = 0
        L_0x00a1:
            boolean r6 = r13.equals(r12)
            if (r6 == 0) goto L_0x015d
            boolean r6 = r14.equals(r12)
            if (r6 == 0) goto L_0x015d
            if (r17 != 0) goto L_0x015d
            float r6 = r12.x
            float r9 = r11.x
            float r6 = r6 - r9
            float r9 = r12.y
            float r10 = r11.y
            float r9 = r9 - r10
            float r10 = r15.x
            float r13 = r12.x
            float r10 = r10 - r13
            float r13 = r15.y
            float r14 = r12.y
            float r13 = r13 - r14
            r14 = r0
            double r0 = (double) r6
            r16 = r5
            double r5 = (double) r9
            double r0 = java.lang.Math.hypot(r0, r5)
            float r0 = (float) r0
            double r5 = (double) r10
            double r9 = (double) r13
            double r5 = java.lang.Math.hypot(r5, r9)
            float r1 = (float) r5
            float r0 = r2 / r0
            r5 = 1056964608(0x3f000000, float:0.5)
            float r0 = java.lang.Math.min(r0, r5)
            float r1 = r2 / r1
            float r1 = java.lang.Math.min(r1, r5)
            float r5 = r12.x
            float r6 = r11.x
            float r9 = r12.x
            float r6 = r6 - r9
            float r6 = r6 * r0
            float r5 = r5 + r6
            float r6 = r12.y
            float r9 = r11.y
            float r10 = r12.y
            float r9 = r9 - r10
            float r9 = r9 * r0
            float r6 = r6 + r9
            float r0 = r12.x
            float r9 = r15.x
            float r10 = r12.x
            float r9 = r9 - r10
            float r9 = r9 * r1
            float r0 = r0 + r9
            float r9 = r12.y
            float r10 = r15.y
            float r11 = r12.y
            float r10 = r10 - r11
            float r10 = r10 * r1
            float r9 = r9 + r10
            float r1 = r12.x
            float r1 = r5 - r1
            r10 = 1057835346(0x3f0d4952, float:0.5519)
            float r1 = r1 * r10
            float r1 = r5 - r1
            float r11 = r12.y
            float r11 = r6 - r11
            float r11 = r11 * r10
            float r11 = r6 - r11
            float r13 = r12.x
            float r13 = r0 - r13
            float r13 = r13 * r10
            float r13 = r0 - r13
            float r12 = r12.y
            float r12 = r9 - r12
            float r12 = r12 * r10
            float r10 = r9 - r12
            int r12 = r8 + -1
            int r15 = r4.size()
            int r12 = floorMod(r12, r15)
            java.lang.Object r12 = r4.get(r12)
            com.airbnb.lottie.model.CubicCurveData r12 = (com.airbnb.lottie.model.CubicCurveData) r12
            java.lang.Object r15 = r4.get(r8)
            com.airbnb.lottie.model.CubicCurveData r15 = (com.airbnb.lottie.model.CubicCurveData) r15
            r12.setControlPoint2(r5, r6)
            r12.setVertex(r5, r6)
            if (r7 != 0) goto L_0x0146
            r3.setInitialPoint(r5, r6)
        L_0x0146:
            r15.setControlPoint1(r1, r11)
            int r1 = r8 + 1
            java.lang.Object r1 = r4.get(r1)
            com.airbnb.lottie.model.CubicCurveData r1 = (com.airbnb.lottie.model.CubicCurveData) r1
            r15.setControlPoint2(r13, r10)
            r15.setVertex(r0, r9)
            r1.setControlPoint1(r0, r9)
            int r8 = r8 + 2
            goto L_0x01a5
        L_0x015d:
            r14 = r0
            r16 = r5
            int r0 = r8 + -1
            int r1 = r4.size()
            int r0 = floorMod(r0, r1)
            java.lang.Object r0 = r4.get(r0)
            com.airbnb.lottie.model.CubicCurveData r0 = (com.airbnb.lottie.model.CubicCurveData) r0
            java.lang.Object r1 = r4.get(r8)
            com.airbnb.lottie.model.CubicCurveData r1 = (com.airbnb.lottie.model.CubicCurveData) r1
            android.graphics.PointF r5 = r10.getVertex()
            float r5 = r5.x
            android.graphics.PointF r6 = r10.getVertex()
            float r6 = r6.y
            r0.setControlPoint2(r5, r6)
            android.graphics.PointF r5 = r10.getVertex()
            float r5 = r5.x
            android.graphics.PointF r6 = r10.getVertex()
            float r6 = r6.y
            r0.setVertex(r5, r6)
            android.graphics.PointF r0 = r9.getVertex()
            float r0 = r0.x
            android.graphics.PointF r5 = r9.getVertex()
            float r5 = r5.y
            r1.setControlPoint1(r0, r5)
            int r8 = r8 + 1
        L_0x01a5:
            int r7 = r7 + 1
            r1 = r18
            r0 = r14
            r5 = r16
            goto L_0x003d
        L_0x01ae:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.content.RoundedCornersContent.modifyShape(com.airbnb.lottie.model.content.ShapeData):com.airbnb.lottie.model.content.ShapeData");
    }

    private ShapeData getShapeData(ShapeData shapeData2) {
        PointF pointF;
        List<CubicCurveData> curves = shapeData2.getCurves();
        boolean isClosed = shapeData2.isClosed();
        int size = curves.size() - 1;
        int i = 0;
        while (size >= 0) {
            CubicCurveData cubicCurveData = curves.get(size);
            CubicCurveData cubicCurveData2 = curves.get(floorMod(size - 1, curves.size()));
            PointF vertex = (size != 0 || isClosed) ? cubicCurveData2.getVertex() : shapeData2.getInitialPoint();
            if (size != 0 || isClosed) {
                pointF = cubicCurveData2.getControlPoint2();
            } else {
                pointF = vertex;
            }
            i = (!pointF.equals(vertex) || !cubicCurveData.getControlPoint1().equals(vertex) || (!shapeData2.isClosed() && size == 0 && size == curves.size() - 1)) ? i + 1 : i + 2;
            size--;
        }
        ShapeData shapeData3 = this.shapeData;
        if (shapeData3 == null || shapeData3.getCurves().size() != i) {
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(new CubicCurveData());
            }
            this.shapeData = new ShapeData(new PointF(0.0f, 0.0f), false, arrayList);
        }
        this.shapeData.setClosed(isClosed);
        return this.shapeData;
    }

    private static int floorMod(int i, int i2) {
        return i - (floorDiv(i, i2) * i2);
    }

    private static int floorDiv(int i, int i2) {
        int i3 = i / i2;
        return ((i ^ i2) >= 0 || i2 * i3 == i) ? i3 : i3 - 1;
    }
}
