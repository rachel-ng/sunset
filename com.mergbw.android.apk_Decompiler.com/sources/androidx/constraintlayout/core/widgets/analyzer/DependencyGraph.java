package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class DependencyGraph {
    private static final boolean USE_GROUPS = true;
    private ConstraintWidgetContainer container;
    private ConstraintWidgetContainer mContainer;
    ArrayList<RunGroup> mGroups = new ArrayList<>();
    private BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    private BasicMeasure.Measurer mMeasurer = null;
    private boolean mNeedBuildGraph = true;
    private boolean mNeedRedoMeasures = true;
    private ArrayList<WidgetRun> mRuns = new ArrayList<>();
    private ArrayList<RunGroup> runGroups = new ArrayList<>();

    public DependencyGraph(ConstraintWidgetContainer constraintWidgetContainer) {
        this.container = constraintWidgetContainer;
        this.mContainer = constraintWidgetContainer;
    }

    public void setMeasurer(BasicMeasure.Measurer measurer) {
        this.mMeasurer = measurer;
    }

    private int computeWrap(ConstraintWidgetContainer constraintWidgetContainer, int i) {
        int size = this.mGroups.size();
        long j = 0;
        for (int i2 = 0; i2 < size; i2++) {
            j = Math.max(j, this.mGroups.get(i2).computeWrapSize(constraintWidgetContainer, i));
        }
        return (int) j;
    }

    public void defineTerminalWidgets(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2) {
        if (this.mNeedBuildGraph) {
            buildGraph();
            Iterator it = this.container.mChildren.iterator();
            boolean z = false;
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                constraintWidget.isTerminalWidget[0] = true;
                constraintWidget.isTerminalWidget[1] = true;
                if (constraintWidget instanceof Barrier) {
                    z = true;
                }
            }
            if (!z) {
                Iterator<RunGroup> it2 = this.mGroups.iterator();
                while (it2.hasNext()) {
                    it2.next().defineTerminalWidgets(dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
                }
            }
        }
    }

    public boolean directMeasure(boolean z) {
        boolean z2;
        boolean z3 = false;
        if (this.mNeedBuildGraph || this.mNeedRedoMeasures) {
            Iterator it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                constraintWidget.ensureWidgetRuns();
                constraintWidget.measured = false;
                constraintWidget.horizontalRun.reset();
                constraintWidget.verticalRun.reset();
            }
            this.container.ensureWidgetRuns();
            this.container.measured = false;
            this.container.horizontalRun.reset();
            this.container.verticalRun.reset();
            this.mNeedRedoMeasures = false;
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.container.setX(0);
        this.container.setY(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.container.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.container.getDimensionBehaviour(1);
        if (this.mNeedBuildGraph) {
            buildGraph();
        }
        int x = this.container.getX();
        int y = this.container.getY();
        this.container.horizontalRun.start.resolve(x);
        this.container.verticalRun.start.resolve(y);
        measureWidgets();
        if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            if (z) {
                Iterator<WidgetRun> it2 = this.mRuns.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (!it2.next().supportsWrapComputation()) {
                            z = false;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (z && dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer = this.container;
                constraintWidgetContainer.setWidth(computeWrap(constraintWidgetContainer, 0));
                this.container.horizontalRun.dimension.resolve(this.container.getWidth());
            }
            if (z && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer2 = this.container;
                constraintWidgetContainer2.setHeight(computeWrap(constraintWidgetContainer2, 1));
                this.container.verticalRun.dimension.resolve(this.container.getHeight());
            }
        }
        if (this.container.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED || this.container.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int width = this.container.getWidth() + x;
            this.container.horizontalRun.end.resolve(width);
            this.container.horizontalRun.dimension.resolve(width - x);
            measureWidgets();
            if (this.container.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.FIXED || this.container.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int height = this.container.getHeight() + y;
                this.container.verticalRun.end.resolve(height);
                this.container.verticalRun.dimension.resolve(height - y);
            }
            measureWidgets();
            z2 = true;
        } else {
            z2 = false;
        }
        Iterator<WidgetRun> it3 = this.mRuns.iterator();
        while (it3.hasNext()) {
            WidgetRun next = it3.next();
            if (next.widget != this.container || next.resolved) {
                next.applyToWidget();
            }
        }
        Iterator<WidgetRun> it4 = this.mRuns.iterator();
        while (true) {
            if (!it4.hasNext()) {
                z3 = true;
                break;
            }
            WidgetRun next2 = it4.next();
            if ((z2 || next2.widget != this.container) && (!next2.start.resolved || ((!next2.end.resolved && !(next2 instanceof GuidelineReference)) || (!next2.dimension.resolved && !(next2 instanceof ChainRun) && !(next2 instanceof GuidelineReference))))) {
                break;
            }
        }
        this.container.setHorizontalDimensionBehaviour(dimensionBehaviour);
        this.container.setVerticalDimensionBehaviour(dimensionBehaviour2);
        return z3;
    }

    public boolean directMeasureSetup(boolean z) {
        if (this.mNeedBuildGraph) {
            Iterator it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                constraintWidget.ensureWidgetRuns();
                constraintWidget.measured = false;
                constraintWidget.horizontalRun.dimension.resolved = false;
                constraintWidget.horizontalRun.resolved = false;
                constraintWidget.horizontalRun.reset();
                constraintWidget.verticalRun.dimension.resolved = false;
                constraintWidget.verticalRun.resolved = false;
                constraintWidget.verticalRun.reset();
            }
            this.container.ensureWidgetRuns();
            this.container.measured = false;
            this.container.horizontalRun.dimension.resolved = false;
            this.container.horizontalRun.resolved = false;
            this.container.horizontalRun.reset();
            this.container.verticalRun.dimension.resolved = false;
            this.container.verticalRun.resolved = false;
            this.container.verticalRun.reset();
            buildGraph();
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.container.setX(0);
        this.container.setY(0);
        this.container.horizontalRun.start.resolve(0);
        this.container.verticalRun.start.resolve(0);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0153 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean directMeasureWithOrientation(boolean r10, int r11) {
        /*
            r9 = this;
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r0 = r9.container
            r1 = 0
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r0 = r0.getDimensionBehaviour(r1)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r2 = r9.container
            r3 = 1
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = r2.getDimensionBehaviour(r3)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r4 = r9.container
            int r4 = r4.getX()
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r5 = r9.container
            int r5 = r5.getY()
            if (r10 == 0) goto L_0x008e
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 == r6) goto L_0x0024
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 != r6) goto L_0x008e
        L_0x0024:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r6 = r9.mRuns
            java.util.Iterator r6 = r6.iterator()
        L_0x002a:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0041
            java.lang.Object r7 = r6.next()
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r7 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r7
            int r8 = r7.orientation
            if (r8 != r11) goto L_0x002a
            boolean r7 = r7.supportsWrapComputation()
            if (r7 != 0) goto L_0x002a
            r10 = r1
        L_0x0041:
            if (r11 != 0) goto L_0x0069
            if (r10 == 0) goto L_0x008e
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r10) goto L_0x008e
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r10.setHorizontalDimensionBehaviour(r6)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            int r6 = r9.computeWrap(r10, r1)
            r10.setWidth(r6)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r10 = r10.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r10.dimension
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r6 = r9.container
            int r6 = r6.getWidth()
            r10.resolve(r6)
            goto L_0x008e
        L_0x0069:
            if (r10 == 0) goto L_0x008e
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 != r10) goto L_0x008e
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r10.setVerticalDimensionBehaviour(r6)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            int r6 = r9.computeWrap(r10, r3)
            r10.setHeight(r6)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r10 = r10.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r10.dimension
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r6 = r9.container
            int r6 = r6.getHeight()
            r10.resolve(r6)
        L_0x008e:
            if (r11 != 0) goto L_0x00bf
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r10.mListDimensionBehaviors
            r10 = r10[r1]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r10 == r5) goto L_0x00a4
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r10.mListDimensionBehaviors
            r10 = r10[r1]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            if (r10 != r5) goto L_0x00d4
        L_0x00a4:
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            int r10 = r10.getWidth()
            int r10 = r10 + r4
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r5 = r9.container
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r5 = r5.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r5 = r5.end
            r5.resolve(r10)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r5 = r9.container
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r5 = r5.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r5 = r5.dimension
            int r10 = r10 - r4
            r5.resolve(r10)
            goto L_0x00f0
        L_0x00bf:
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r10.mListDimensionBehaviors
            r10 = r10[r3]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r10 == r4) goto L_0x00d6
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r10.mListDimensionBehaviors
            r10 = r10[r3]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            if (r10 != r4) goto L_0x00d4
            goto L_0x00d6
        L_0x00d4:
            r10 = r1
            goto L_0x00f1
        L_0x00d6:
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            int r10 = r10.getHeight()
            int r10 = r10 + r5
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r4 = r9.container
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r4 = r4.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = r4.end
            r4.resolve(r10)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r4 = r9.container
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r4 = r4.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r4 = r4.dimension
            int r10 = r10 - r5
            r4.resolve(r10)
        L_0x00f0:
            r10 = r3
        L_0x00f1:
            r9.measureWidgets()
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r4 = r9.mRuns
            java.util.Iterator r4 = r4.iterator()
        L_0x00fa:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x011a
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r5 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r5
            int r6 = r5.orientation
            if (r6 == r11) goto L_0x010b
            goto L_0x00fa
        L_0x010b:
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r5.widget
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r7 = r9.container
            if (r6 != r7) goto L_0x0116
            boolean r6 = r5.resolved
            if (r6 != 0) goto L_0x0116
            goto L_0x00fa
        L_0x0116:
            r5.applyToWidget()
            goto L_0x00fa
        L_0x011a:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r4 = r9.mRuns
            java.util.Iterator r4 = r4.iterator()
        L_0x0120:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0153
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r5 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r5
            int r6 = r5.orientation
            if (r6 == r11) goto L_0x0131
            goto L_0x0120
        L_0x0131:
            if (r10 != 0) goto L_0x013a
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r5.widget
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r7 = r9.container
            if (r6 != r7) goto L_0x013a
            goto L_0x0120
        L_0x013a:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r6 = r5.start
            boolean r6 = r6.resolved
            if (r6 != 0) goto L_0x0141
            goto L_0x0154
        L_0x0141:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r6 = r5.end
            boolean r6 = r6.resolved
            if (r6 != 0) goto L_0x0148
            goto L_0x0154
        L_0x0148:
            boolean r6 = r5 instanceof androidx.constraintlayout.core.widgets.analyzer.ChainRun
            if (r6 != 0) goto L_0x0120
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r5 = r5.dimension
            boolean r5 = r5.resolved
            if (r5 != 0) goto L_0x0120
            goto L_0x0154
        L_0x0153:
            r1 = r3
        L_0x0154:
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            r10.setHorizontalDimensionBehaviour(r0)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            r10.setVerticalDimensionBehaviour(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.DependencyGraph.directMeasureWithOrientation(boolean, int):boolean");
    }

    private void measure(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i2) {
        this.mMeasure.horizontalBehavior = dimensionBehaviour;
        this.mMeasure.verticalBehavior = dimensionBehaviour2;
        this.mMeasure.horizontalDimension = i;
        this.mMeasure.verticalDimension = i2;
        this.mMeasurer.measure(constraintWidget, this.mMeasure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
    }

    private boolean basicMeasureWidgets(ConstraintWidgetContainer constraintWidgetContainer) {
        int i;
        int i2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        Iterator it = constraintWidgetContainer.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget.mListDimensionBehaviors[0];
            ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = constraintWidget.mListDimensionBehaviors[1];
            if (constraintWidget.getVisibility() == 8) {
                constraintWidget.measured = true;
            } else {
                if (constraintWidget.mMatchConstraintPercentWidth < 1.0f && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.mMatchConstraintDefaultWidth = 2;
                }
                if (constraintWidget.mMatchConstraintPercentHeight < 1.0f && dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.mMatchConstraintDefaultHeight = 2;
                }
                if (constraintWidget.getDimensionRatio() > 0.0f) {
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.mMatchConstraintDefaultWidth = 3;
                    } else if (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.mMatchConstraintDefaultHeight = 3;
                    } else if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        if (constraintWidget.mMatchConstraintDefaultWidth == 0) {
                            constraintWidget.mMatchConstraintDefaultWidth = 3;
                        }
                        if (constraintWidget.mMatchConstraintDefaultHeight == 0) {
                            constraintWidget.mMatchConstraintDefaultHeight = 3;
                        }
                    }
                }
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 1 && (constraintWidget.mLeft.mTarget == null || constraintWidget.mRight.mTarget == null)) {
                    dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = dimensionBehaviour2;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 1 && (constraintWidget.mTop.mTarget == null || constraintWidget.mBottom.mTarget == null)) ? ConstraintWidget.DimensionBehaviour.WRAP_CONTENT : dimensionBehaviour3;
                constraintWidget.horizontalRun.dimensionBehavior = dimensionBehaviour4;
                constraintWidget.horizontalRun.matchConstraintsType = constraintWidget.mMatchConstraintDefaultWidth;
                constraintWidget.verticalRun.dimensionBehavior = dimensionBehaviour5;
                constraintWidget.verticalRun.matchConstraintsType = constraintWidget.mMatchConstraintDefaultHeight;
                if ((dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT || dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT || dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    int width = constraintWidget.getWidth();
                    if (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                        i = (constraintWidgetContainer.getWidth() - constraintWidget.mLeft.mMargin) - constraintWidget.mRight.mMargin;
                        dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        i = width;
                    }
                    int height = constraintWidget.getHeight();
                    if (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                        i2 = (constraintWidgetContainer.getHeight() - constraintWidget.mTop.mMargin) - constraintWidget.mBottom.mMargin;
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        dimensionBehaviour = dimensionBehaviour5;
                        i2 = height;
                    }
                    measure(constraintWidget, dimensionBehaviour4, i, dimensionBehaviour, i2);
                    constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                    constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                    constraintWidget.measured = true;
                } else {
                    if (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (constraintWidget.mMatchConstraintDefaultWidth == 3) {
                            if (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                                measure(constraintWidget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0);
                            }
                            int height2 = constraintWidget.getHeight();
                            measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, (int) ((((float) height2) * constraintWidget.mDimensionRatio) + 0.5f), ConstraintWidget.DimensionBehaviour.FIXED, height2);
                            constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                            constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = true;
                        } else if (constraintWidget.mMatchConstraintDefaultWidth == 1) {
                            measure(constraintWidget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, dimensionBehaviour5, 0);
                            constraintWidget.horizontalRun.dimension.wrapValue = constraintWidget.getWidth();
                        } else if (constraintWidget.mMatchConstraintDefaultWidth == 2) {
                            if (constraintWidgetContainer.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED || constraintWidgetContainer.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                                measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, (int) ((constraintWidget.mMatchConstraintPercentWidth * ((float) constraintWidgetContainer.getWidth())) + 0.5f), dimensionBehaviour5, constraintWidget.getHeight());
                                constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                                constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = true;
                            }
                        } else if (constraintWidget.mListAnchors[0].mTarget == null || constraintWidget.mListAnchors[1].mTarget == null) {
                            measure(constraintWidget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, dimensionBehaviour5, 0);
                            constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                            constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = true;
                        }
                    }
                    if (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (constraintWidget.mMatchConstraintDefaultHeight == 3) {
                            if (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                                measure(constraintWidget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0);
                            }
                            int width2 = constraintWidget.getWidth();
                            float f = constraintWidget.mDimensionRatio;
                            if (constraintWidget.getDimensionRatioSide() == -1) {
                                f = 1.0f / f;
                            }
                            measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, width2, ConstraintWidget.DimensionBehaviour.FIXED, (int) ((((float) width2) * f) + 0.5f));
                            constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                            constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = true;
                        } else if (constraintWidget.mMatchConstraintDefaultHeight == 1) {
                            measure(constraintWidget, dimensionBehaviour4, 0, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0);
                            constraintWidget.verticalRun.dimension.wrapValue = constraintWidget.getHeight();
                        } else if (constraintWidget.mMatchConstraintDefaultHeight == 2) {
                            if (constraintWidgetContainer.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.FIXED || constraintWidgetContainer.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                                ConstraintWidget constraintWidget2 = constraintWidget;
                                measure(constraintWidget2, dimensionBehaviour4, constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, (int) ((constraintWidget.mMatchConstraintPercentHeight * ((float) constraintWidgetContainer.getHeight())) + 0.5f));
                                constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                                constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = true;
                            }
                        } else if (constraintWidget.mListAnchors[2].mTarget == null || constraintWidget.mListAnchors[3].mTarget == null) {
                            measure(constraintWidget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, dimensionBehaviour5, 0);
                            constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                            constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = true;
                        }
                    }
                    if (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        if (constraintWidget.mMatchConstraintDefaultWidth == 1 || constraintWidget.mMatchConstraintDefaultHeight == 1) {
                            measure(constraintWidget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, 0);
                            constraintWidget.horizontalRun.dimension.wrapValue = constraintWidget.getWidth();
                            constraintWidget.verticalRun.dimension.wrapValue = constraintWidget.getHeight();
                        } else if (constraintWidget.mMatchConstraintDefaultHeight == 2 && constraintWidget.mMatchConstraintDefaultWidth == 2 && constraintWidgetContainer.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED && constraintWidgetContainer.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.FIXED) {
                            float f2 = constraintWidget.mMatchConstraintPercentWidth;
                            float f3 = constraintWidget.mMatchConstraintPercentHeight;
                            measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, (int) ((f2 * ((float) constraintWidgetContainer.getWidth())) + 0.5f), ConstraintWidget.DimensionBehaviour.FIXED, (int) ((f3 * ((float) constraintWidgetContainer.getHeight())) + 0.5f));
                            constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                            constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void measureWidgets() {
        Iterator it = this.container.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            if (!constraintWidget.measured) {
                boolean z = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.mListDimensionBehaviors[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget.mListDimensionBehaviors[1];
                int i = constraintWidget.mMatchConstraintDefaultWidth;
                int i2 = constraintWidget.mMatchConstraintDefaultHeight;
                boolean z2 = dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i == 1);
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i2 == 1)) {
                    z = true;
                }
                boolean z3 = constraintWidget.horizontalRun.dimension.resolved;
                boolean z4 = constraintWidget.verticalRun.dimension.resolved;
                if (z3 && z4) {
                    measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, constraintWidget.horizontalRun.dimension.value, ConstraintWidget.DimensionBehaviour.FIXED, constraintWidget.verticalRun.dimension.value);
                    constraintWidget.measured = true;
                } else if (z3 && z) {
                    measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, constraintWidget.horizontalRun.dimension.value, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, constraintWidget.verticalRun.dimension.value);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.verticalRun.dimension.wrapValue = constraintWidget.getHeight();
                    } else {
                        constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                        constraintWidget.measured = true;
                    }
                } else if (z4 && z2) {
                    measure(constraintWidget, ConstraintWidget.DimensionBehaviour.WRAP_CONTENT, constraintWidget.horizontalRun.dimension.value, ConstraintWidget.DimensionBehaviour.FIXED, constraintWidget.verticalRun.dimension.value);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.horizontalRun.dimension.wrapValue = constraintWidget.getWidth();
                    } else {
                        constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                        constraintWidget.measured = true;
                    }
                }
                if (constraintWidget.measured && constraintWidget.verticalRun.baselineDimension != null) {
                    constraintWidget.verticalRun.baselineDimension.resolve(constraintWidget.getBaselineDistance());
                }
            }
        }
    }

    public void invalidateGraph() {
        this.mNeedBuildGraph = true;
    }

    public void invalidateMeasures() {
        this.mNeedRedoMeasures = true;
    }

    public void buildGraph() {
        buildGraph(this.mRuns);
        this.mGroups.clear();
        RunGroup.index = 0;
        findGroup(this.container.horizontalRun, 0, this.mGroups);
        findGroup(this.container.verticalRun, 1, this.mGroups);
        this.mNeedBuildGraph = false;
    }

    public void buildGraph(ArrayList<WidgetRun> arrayList) {
        arrayList.clear();
        this.mContainer.horizontalRun.clear();
        this.mContainer.verticalRun.clear();
        arrayList.add(this.mContainer.horizontalRun);
        arrayList.add(this.mContainer.verticalRun);
        Iterator it = this.mContainer.mChildren.iterator();
        HashSet hashSet = null;
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            if (constraintWidget instanceof Guideline) {
                arrayList.add(new GuidelineReference(constraintWidget));
            } else {
                if (constraintWidget.isInHorizontalChain()) {
                    if (constraintWidget.horizontalChainRun == null) {
                        constraintWidget.horizontalChainRun = new ChainRun(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.horizontalChainRun);
                } else {
                    arrayList.add(constraintWidget.horizontalRun);
                }
                if (constraintWidget.isInVerticalChain()) {
                    if (constraintWidget.verticalChainRun == null) {
                        constraintWidget.verticalChainRun = new ChainRun(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.verticalChainRun);
                } else {
                    arrayList.add(constraintWidget.verticalRun);
                }
                if (constraintWidget instanceof HelperWidget) {
                    arrayList.add(new HelperReferences(constraintWidget));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<WidgetRun> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            it2.next().clear();
        }
        Iterator<WidgetRun> it3 = arrayList.iterator();
        while (it3.hasNext()) {
            WidgetRun next = it3.next();
            if (next.widget != this.mContainer) {
                next.apply();
            }
        }
    }

    private void displayGraph() {
        Iterator<WidgetRun> it = this.mRuns.iterator();
        String str = "digraph {\n";
        while (it.hasNext()) {
            str = generateDisplayGraph(it.next(), str);
        }
        String str2 = str + "\n}\n";
        System.out.println("content:<<\n" + str2 + "\n>>");
    }

    private void applyGroup(DependencyNode dependencyNode, int i, int i2, DependencyNode dependencyNode2, ArrayList<RunGroup> arrayList, RunGroup runGroup) {
        WidgetRun widgetRun = dependencyNode.run;
        if (widgetRun.runGroup == null && widgetRun != this.container.horizontalRun && widgetRun != this.container.verticalRun) {
            if (runGroup == null) {
                runGroup = new RunGroup(widgetRun, i2);
                arrayList.add(runGroup);
            }
            widgetRun.runGroup = runGroup;
            runGroup.add(widgetRun);
            for (Dependency next : widgetRun.start.dependencies) {
                if (next instanceof DependencyNode) {
                    applyGroup((DependencyNode) next, i, 0, dependencyNode2, arrayList, runGroup);
                }
            }
            for (Dependency next2 : widgetRun.end.dependencies) {
                if (next2 instanceof DependencyNode) {
                    applyGroup((DependencyNode) next2, i, 1, dependencyNode2, arrayList, runGroup);
                }
            }
            if (i == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                for (Dependency next3 : ((VerticalWidgetRun) widgetRun).baseline.dependencies) {
                    if (next3 instanceof DependencyNode) {
                        applyGroup((DependencyNode) next3, i, 2, dependencyNode2, arrayList, runGroup);
                    }
                }
            }
            for (DependencyNode next4 : widgetRun.start.targets) {
                if (next4 == dependencyNode2) {
                    runGroup.dual = true;
                }
                applyGroup(next4, i, 0, dependencyNode2, arrayList, runGroup);
            }
            for (DependencyNode next5 : widgetRun.end.targets) {
                if (next5 == dependencyNode2) {
                    runGroup.dual = true;
                }
                applyGroup(next5, i, 1, dependencyNode2, arrayList, runGroup);
            }
            if (i == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                for (DependencyNode applyGroup : ((VerticalWidgetRun) widgetRun).baseline.targets) {
                    applyGroup(applyGroup, i, 2, dependencyNode2, arrayList, runGroup);
                }
            }
        }
    }

    private void findGroup(WidgetRun widgetRun, int i, ArrayList<RunGroup> arrayList) {
        for (Dependency next : widgetRun.start.dependencies) {
            if (next instanceof DependencyNode) {
                applyGroup((DependencyNode) next, i, 0, widgetRun.end, arrayList, (RunGroup) null);
            } else if (next instanceof WidgetRun) {
                applyGroup(((WidgetRun) next).start, i, 0, widgetRun.end, arrayList, (RunGroup) null);
            }
        }
        for (Dependency next2 : widgetRun.end.dependencies) {
            if (next2 instanceof DependencyNode) {
                applyGroup((DependencyNode) next2, i, 1, widgetRun.start, arrayList, (RunGroup) null);
            } else if (next2 instanceof WidgetRun) {
                applyGroup(((WidgetRun) next2).end, i, 1, widgetRun.start, arrayList, (RunGroup) null);
            }
        }
        if (i == 1) {
            for (Dependency next3 : ((VerticalWidgetRun) widgetRun).baseline.dependencies) {
                if (next3 instanceof DependencyNode) {
                    applyGroup((DependencyNode) next3, i, 2, (DependencyNode) null, arrayList, (RunGroup) null);
                }
            }
        }
    }

    private String generateDisplayNode(DependencyNode dependencyNode, boolean z, String str) {
        StringBuilder sb = new StringBuilder(str);
        for (DependencyNode name : dependencyNode.targets) {
            String str2 = ("\n" + dependencyNode.name()) + " -> " + name.name();
            if (dependencyNode.margin > 0 || z || (dependencyNode.run instanceof HelperReferences)) {
                String str3 = str2 + "[";
                if (dependencyNode.margin > 0) {
                    str3 = str3 + "label=\"" + dependencyNode.margin + "\"";
                    if (z) {
                        str3 = str3 + ",";
                    }
                }
                if (z) {
                    str3 = str3 + " style=dashed ";
                }
                if (dependencyNode.run instanceof HelperReferences) {
                    str3 = str3 + " style=bold,color=gray ";
                }
                str2 = str3 + "]";
            }
            sb.append(str2 + "\n");
        }
        return sb.toString();
    }

    private String nodeDefinition(WidgetRun widgetRun) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        boolean z = widgetRun instanceof VerticalWidgetRun;
        String debugName = widgetRun.widget.getDebugName();
        StringBuilder sb = new StringBuilder(debugName);
        ConstraintWidget constraintWidget = widgetRun.widget;
        if (!z) {
            dimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
        } else {
            dimensionBehaviour = constraintWidget.getVerticalDimensionBehaviour();
        }
        RunGroup runGroup = widgetRun.runGroup;
        if (!z) {
            sb.append("_HORIZONTAL");
        } else {
            sb.append("_VERTICAL");
        }
        sb.append(" [shape=none, label=<<TABLE BORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"2\">  <TR>");
        if (!z) {
            sb.append("    <TD ");
            if (widgetRun.start.resolved) {
                sb.append(" BGCOLOR=\"green\"");
            }
            sb.append(" PORT=\"LEFT\" BORDER=\"1\">L</TD>");
        } else {
            sb.append("    <TD ");
            if (widgetRun.start.resolved) {
                sb.append(" BGCOLOR=\"green\"");
            }
            sb.append(" PORT=\"TOP\" BORDER=\"1\">T</TD>");
        }
        sb.append("    <TD BORDER=\"1\" ");
        if (widgetRun.dimension.resolved && !widgetRun.widget.measured) {
            sb.append(" BGCOLOR=\"green\" ");
        } else if (widgetRun.dimension.resolved) {
            sb.append(" BGCOLOR=\"lightgray\" ");
        } else if (widgetRun.widget.measured) {
            sb.append(" BGCOLOR=\"yellow\" ");
        }
        if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            sb.append("style=\"dashed\"");
        }
        sb.append(">");
        sb.append(debugName);
        if (runGroup != null) {
            sb.append(" [");
            sb.append(runGroup.groupIndex + 1);
            sb.append(RemoteSettings.FORWARD_SLASH_STRING);
            sb.append(RunGroup.index);
            sb.append("]");
        }
        sb.append(" </TD>");
        if (!z) {
            sb.append("    <TD ");
            if (widgetRun.end.resolved) {
                sb.append(" BGCOLOR=\"green\"");
            }
            sb.append(" PORT=\"RIGHT\" BORDER=\"1\">R</TD>");
        } else {
            sb.append("    <TD ");
            if (((VerticalWidgetRun) widgetRun).baseline.resolved) {
                sb.append(" BGCOLOR=\"green\"");
            }
            sb.append(" PORT=\"BASELINE\" BORDER=\"1\">b</TD>    <TD ");
            if (widgetRun.end.resolved) {
                sb.append(" BGCOLOR=\"green\"");
            }
            sb.append(" PORT=\"BOTTOM\" BORDER=\"1\">B</TD>");
        }
        sb.append("  </TR></TABLE>>];\n");
        return sb.toString();
    }

    private String generateChainDisplayGraph(ChainRun chainRun, String str) {
        int i = chainRun.orientation;
        StringBuilder sb = new StringBuilder("subgraph cluster_");
        sb.append(chainRun.widget.getDebugName());
        if (i == 0) {
            sb.append("_h");
        } else {
            sb.append("_v");
        }
        sb.append(" {\n");
        Iterator<WidgetRun> it = chainRun.widgets.iterator();
        String str2 = "";
        while (it.hasNext()) {
            WidgetRun next = it.next();
            sb.append(next.widget.getDebugName());
            if (i == 0) {
                sb.append("_HORIZONTAL");
            } else {
                sb.append("_VERTICAL");
            }
            sb.append(";\n");
            str2 = generateDisplayGraph(next, str2);
        }
        sb.append("}\n");
        return str + str2 + sb;
    }

    private boolean isCenteredConnection(DependencyNode dependencyNode, DependencyNode dependencyNode2) {
        int i = 0;
        for (DependencyNode dependencyNode3 : dependencyNode.targets) {
            if (dependencyNode3 != dependencyNode2) {
                i++;
            }
        }
        int i2 = 0;
        for (DependencyNode dependencyNode4 : dependencyNode2.targets) {
            if (dependencyNode4 != dependencyNode) {
                i2++;
            }
        }
        if (i <= 0 || i2 <= 0) {
            return false;
        }
        return true;
    }

    private String generateDisplayGraph(WidgetRun widgetRun, String str) {
        boolean z;
        DependencyNode dependencyNode = widgetRun.start;
        DependencyNode dependencyNode2 = widgetRun.end;
        StringBuilder sb = new StringBuilder(str);
        if (!(widgetRun instanceof HelperReferences) && dependencyNode.dependencies.isEmpty() && (dependencyNode2.dependencies.isEmpty() && dependencyNode.targets.isEmpty()) && dependencyNode2.targets.isEmpty()) {
            return str;
        }
        sb.append(nodeDefinition(widgetRun));
        boolean isCenteredConnection = isCenteredConnection(dependencyNode, dependencyNode2);
        String generateDisplayNode = generateDisplayNode(dependencyNode2, isCenteredConnection, generateDisplayNode(dependencyNode, isCenteredConnection, str));
        boolean z2 = widgetRun instanceof VerticalWidgetRun;
        if (z2) {
            generateDisplayNode = generateDisplayNode(((VerticalWidgetRun) widgetRun).baseline, isCenteredConnection, generateDisplayNode);
        }
        if ((widgetRun instanceof HorizontalWidgetRun) || (((z = widgetRun instanceof ChainRun)) && ((ChainRun) widgetRun).orientation == 0)) {
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = widgetRun.widget.getHorizontalDimensionBehaviour();
            if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED || horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                if (!dependencyNode.targets.isEmpty() && dependencyNode2.targets.isEmpty()) {
                    sb.append("\n");
                    sb.append(dependencyNode2.name());
                    sb.append(" -> ");
                    sb.append(dependencyNode.name());
                    sb.append("\n");
                } else if (dependencyNode.targets.isEmpty() && !dependencyNode2.targets.isEmpty()) {
                    sb.append("\n");
                    sb.append(dependencyNode.name());
                    sb.append(" -> ");
                    sb.append(dependencyNode2.name());
                    sb.append("\n");
                }
            } else if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun.widget.getDimensionRatio() > 0.0f) {
                sb.append("\n");
                sb.append(widgetRun.widget.getDebugName());
                sb.append("_HORIZONTAL -> ");
                sb.append(widgetRun.widget.getDebugName());
                sb.append("_VERTICAL;\n");
            }
        } else if (z2 || (z && ((ChainRun) widgetRun).orientation == 1)) {
            ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = widgetRun.widget.getVerticalDimensionBehaviour();
            if (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED || verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                if (!dependencyNode.targets.isEmpty() && dependencyNode2.targets.isEmpty()) {
                    sb.append("\n");
                    sb.append(dependencyNode2.name());
                    sb.append(" -> ");
                    sb.append(dependencyNode.name());
                    sb.append("\n");
                } else if (dependencyNode.targets.isEmpty() && !dependencyNode2.targets.isEmpty()) {
                    sb.append("\n");
                    sb.append(dependencyNode.name());
                    sb.append(" -> ");
                    sb.append(dependencyNode2.name());
                    sb.append("\n");
                }
            } else if (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun.widget.getDimensionRatio() > 0.0f) {
                sb.append("\n");
                sb.append(widgetRun.widget.getDebugName());
                sb.append("_VERTICAL -> ");
                sb.append(widgetRun.widget.getDebugName());
                sb.append("_HORIZONTAL;\n");
            }
        }
        if (widgetRun instanceof ChainRun) {
            return generateChainDisplayGraph((ChainRun) widgetRun, generateDisplayNode);
        }
        return sb.toString();
    }
}
