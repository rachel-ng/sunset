package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.exception.MathInternalError;

public enum FilterType {
    TRIGGER_ONLY_DECREASING_EVENTS {
        /* access modifiers changed from: protected */
        public boolean getTriggeredIncreasing() {
            return false;
        }

        /* access modifiers changed from: protected */
        public Transformer selectTransformer(Transformer transformer, double d, boolean z) {
            if (z) {
                int i = AnonymousClass3.$SwitchMap$org$apache$commons$math3$ode$events$Transformer[transformer.ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i == 5) {
                                    return d <= 0.0d ? Transformer.PLUS : transformer;
                                }
                                throw new MathInternalError();
                            } else if (d <= 0.0d) {
                                return Transformer.MINUS;
                            } else {
                                return transformer;
                            }
                        } else if (d >= 0.0d) {
                            return Transformer.MAX;
                        } else {
                            return transformer;
                        }
                    } else if (d >= 0.0d) {
                        return Transformer.MIN;
                    } else {
                        return transformer;
                    }
                } else if (d > 0.0d) {
                    return Transformer.MAX;
                } else {
                    if (d < 0.0d) {
                        return Transformer.PLUS;
                    }
                    return Transformer.UNINITIALIZED;
                }
            } else {
                int i2 = AnonymousClass3.$SwitchMap$org$apache$commons$math3$ode$events$Transformer[transformer.ordinal()];
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            if (i2 != 4) {
                                if (i2 == 5) {
                                    return d >= 0.0d ? Transformer.MINUS : transformer;
                                }
                                throw new MathInternalError();
                            } else if (d >= 0.0d) {
                                return Transformer.PLUS;
                            } else {
                                return transformer;
                            }
                        } else if (d <= 0.0d) {
                            return Transformer.MIN;
                        } else {
                            return transformer;
                        }
                    } else if (d <= 0.0d) {
                        return Transformer.MAX;
                    } else {
                        return transformer;
                    }
                } else if (d > 0.0d) {
                    return Transformer.MINUS;
                } else {
                    if (d < 0.0d) {
                        return Transformer.MIN;
                    }
                    return Transformer.UNINITIALIZED;
                }
            }
        }
    },
    TRIGGER_ONLY_INCREASING_EVENTS {
        /* access modifiers changed from: protected */
        public boolean getTriggeredIncreasing() {
            return true;
        }

        /* access modifiers changed from: protected */
        public Transformer selectTransformer(Transformer transformer, double d, boolean z) {
            if (z) {
                int i = AnonymousClass3.$SwitchMap$org$apache$commons$math3$ode$events$Transformer[transformer.ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i == 5) {
                                    return d >= 0.0d ? Transformer.MINUS : transformer;
                                }
                                throw new MathInternalError();
                            } else if (d >= 0.0d) {
                                return Transformer.PLUS;
                            } else {
                                return transformer;
                            }
                        } else if (d <= 0.0d) {
                            return Transformer.MIN;
                        } else {
                            return transformer;
                        }
                    } else if (d <= 0.0d) {
                        return Transformer.MAX;
                    } else {
                        return transformer;
                    }
                } else if (d > 0.0d) {
                    return Transformer.PLUS;
                } else {
                    if (d < 0.0d) {
                        return Transformer.MIN;
                    }
                    return Transformer.UNINITIALIZED;
                }
            } else {
                int i2 = AnonymousClass3.$SwitchMap$org$apache$commons$math3$ode$events$Transformer[transformer.ordinal()];
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            if (i2 != 4) {
                                if (i2 == 5) {
                                    return d <= 0.0d ? Transformer.PLUS : transformer;
                                }
                                throw new MathInternalError();
                            } else if (d <= 0.0d) {
                                return Transformer.MINUS;
                            } else {
                                return transformer;
                            }
                        } else if (d >= 0.0d) {
                            return Transformer.MAX;
                        } else {
                            return transformer;
                        }
                    } else if (d >= 0.0d) {
                        return Transformer.MIN;
                    } else {
                        return transformer;
                    }
                } else if (d > 0.0d) {
                    return Transformer.MAX;
                } else {
                    if (d < 0.0d) {
                        return Transformer.MINUS;
                    }
                    return Transformer.UNINITIALIZED;
                }
            }
        }
    };

    /* access modifiers changed from: protected */
    public abstract boolean getTriggeredIncreasing();

    /* access modifiers changed from: protected */
    public abstract Transformer selectTransformer(Transformer transformer, double d, boolean z);

    /* renamed from: org.apache.commons.math3.ode.events.FilterType$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$ode$events$Transformer = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.commons.math3.ode.events.Transformer[] r0 = org.apache.commons.math3.ode.events.Transformer.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$ode$events$Transformer = r0
                org.apache.commons.math3.ode.events.Transformer r1 = org.apache.commons.math3.ode.events.Transformer.UNINITIALIZED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$ode$events$Transformer     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.ode.events.Transformer r1 = org.apache.commons.math3.ode.events.Transformer.PLUS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$commons$math3$ode$events$Transformer     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.math3.ode.events.Transformer r1 = org.apache.commons.math3.ode.events.Transformer.MINUS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$commons$math3$ode$events$Transformer     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.commons.math3.ode.events.Transformer r1 = org.apache.commons.math3.ode.events.Transformer.MIN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$commons$math3$ode$events$Transformer     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.commons.math3.ode.events.Transformer r1 = org.apache.commons.math3.ode.events.Transformer.MAX     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.events.FilterType.AnonymousClass3.<clinit>():void");
        }
    }
}
