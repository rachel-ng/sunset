package org.apache.commons.math3.util;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import java.io.PrintStream;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class FastMath {
    private static final double[] CBRTTWO = {0.6299605249474366d, 0.7937005259840998d, 1.0d, 1.2599210498948732d, 1.5874010519681994d};
    private static final double[] COSINE_TABLE_A = {1.0d, 0.9921976327896118d, 0.9689123630523682d, 0.9305076599121094d, 0.8775825500488281d, 0.8109631538391113d, 0.7316888570785522d, 0.6409968137741089d, 0.5403022766113281d, 0.4311765432357788d, 0.3153223395347595d, 0.19454771280288696d, 0.07073719799518585d, -0.05417713522911072d};
    private static final double[] COSINE_TABLE_B = {0.0d, 3.4439717236742845E-8d, 5.865827662008209E-8d, -3.7999795083850525E-8d, 1.184154459111628E-8d, -3.43338934259355E-8d, 1.1795268640216787E-8d, 4.438921624363781E-8d, 2.925681159240093E-8d, -2.6437112632041807E-8d, 2.2860509143963117E-8d, -4.813899778443457E-9d, 3.6725170580355583E-9d, 2.0217439756338078E-10d};
    public static final double E = 2.718281828459045d;
    private static final double[] EIGHTHS = {0.0d, 0.125d, F_1_4, 0.375d, F_1_2, 0.625d, F_3_4, F_7_8, 1.0d, 1.125d, 1.25d, 1.375d, 1.5d, 1.625d};
    static final int EXP_FRAC_TABLE_LEN = 1025;
    static final int EXP_INT_TABLE_LEN = 1500;
    static final int EXP_INT_TABLE_MAX_INDEX = 750;
    private static final double F_11_12 = 0.9166666666666666d;
    private static final double F_13_14 = 0.9285714285714286d;
    private static final double F_15_16 = 0.9375d;
    private static final double F_1_11 = 0.09090909090909091d;
    private static final double F_1_13 = 0.07692307692307693d;
    private static final double F_1_15 = 0.06666666666666667d;
    private static final double F_1_17 = 0.058823529411764705d;
    private static final double F_1_2 = 0.5d;
    private static final double F_1_3 = 0.3333333333333333d;
    private static final double F_1_4 = 0.25d;
    private static final double F_1_5 = 0.2d;
    private static final double F_1_7 = 0.14285714285714285d;
    private static final double F_1_9 = 0.1111111111111111d;
    private static final double F_3_4 = 0.75d;
    private static final double F_5_6 = 0.8333333333333334d;
    private static final double F_7_8 = 0.875d;
    private static final double F_9_10 = 0.9d;
    private static final long HEX_40000000 = 1073741824;
    private static final double LN_2_A = 0.6931470632553101d;
    private static final double LN_2_B = 1.1730463525082348E-7d;
    private static final double[][] LN_HI_PREC_COEF = {new double[]{1.0d, -6.032174644509064E-23d}, new double[]{-0.25d, -0.25d}, new double[]{0.3333333134651184d, 1.9868161777724352E-8d}, new double[]{-0.2499999701976776d, -2.957007209750105E-8d}, new double[]{0.19999954104423523d, 1.5830993332061267E-10d}, new double[]{-0.16624879837036133d, -2.6033824355191673E-8d}};
    static final int LN_MANT_LEN = 1024;
    private static final double[][] LN_QUICK_COEF = {new double[]{1.0d, 5.669184079525E-24d}, new double[]{-0.25d, -0.25d}, new double[]{0.3333333134651184d, 1.986821492305628E-8d}, new double[]{-0.25d, -6.663542893624021E-14d}, new double[]{0.19999998807907104d, 1.1921056801463227E-8d}, new double[]{-0.1666666567325592d, -7.800414592973399E-9d}, new double[]{0.1428571343421936d, 5.650007086920087E-9d}, new double[]{-0.12502530217170715d, -7.44321345601866E-11d}, new double[]{0.11113807559013367d, 9.219544613762692E-9d}};
    private static final double LOG_MAX_VALUE = StrictMath.log(Double.MAX_VALUE);
    private static final long MASK_30BITS = -1073741824;
    private static final int MASK_NON_SIGN_INT = Integer.MAX_VALUE;
    private static final long MASK_NON_SIGN_LONG = Long.MAX_VALUE;
    public static final double PI = 3.141592653589793d;
    private static final long[] PI_O_4_BITS = {-3958705157555305932L, -4267615245585081135L};
    private static final long[] RECIP_2PI = {2935890503282001226L, 9154082963658192752L, 3952090531849364496L, 9193070505571053912L, 7910884519577875640L, 113236205062349959L, 4577762542105553359L, -5034868814120038111L, 4208363204685324176L, 5648769086999809661L, 2819561105158720014L, -4035746434778044925L, -302932621132653753L, -2644281811660520851L, -3183605296591799669L, 6722166367014452318L, -3512299194304650054L, -7278142539171889152L};
    private static final boolean RECOMPUTE_TABLES_AT_RUNTIME = false;
    private static final double[] SINE_TABLE_A = {0.0d, 0.1246747374534607d, 0.24740394949913025d, 0.366272509098053d, 0.4794255495071411d, 0.5850973129272461d, 0.6816387176513672d, 0.7675435543060303d, 0.8414709568023682d, 0.902267575263977d, 0.9489846229553223d, 0.9808930158615112d, 0.9974949359893799d, 0.9985313415527344d};
    private static final double[] SINE_TABLE_B = {0.0d, -4.068233003401932E-9d, 9.755392680573412E-9d, 1.9987994582857286E-8d, -1.0902938113007961E-8d, -3.9986783938944604E-8d, 4.23719669792332E-8d, -5.207000323380292E-8d, 2.800552834259E-8d, 1.883511811213715E-8d, -3.5997360512765566E-9d, 4.116164446561962E-8d, 5.0614674548127384E-8d, -1.0129027912496858E-9d};
    private static final int SINE_TABLE_LEN = 14;
    private static final double[] TANGENT_TABLE_A = {0.0d, 0.1256551444530487d, 0.25534194707870483d, 0.3936265707015991d, 0.5463024377822876d, 0.7214844226837158d, 0.9315965175628662d, 1.1974215507507324d, 1.5574076175689697d, 2.092571258544922d, 3.0095696449279785d, 5.041914939880371d, 14.101419448852539d, -18.430862426757812d};
    private static final double[] TANGENT_TABLE_B = {0.0d, -7.877917738262007E-9d, -2.5857668567479893E-8d, 5.2240336371356666E-9d, 5.206150291559893E-8d, 1.8307188599677033E-8d, -5.7618793749770706E-8d, 7.848361555046424E-8d, 1.0708593250394448E-7d, 1.7827257129423813E-8d, 2.893485277253286E-8d, 3.1660099222737955E-7d, 4.983191803254889E-7d, -3.356118100840571E-7d};
    private static final double TWO_POWER_52 = 4.503599627370496E15d;
    private static final double TWO_POWER_53 = 9.007199254740992E15d;

    public static int abs(int i) {
        int i2 = i >>> 31;
        return (i ^ ((~i2) + 1)) + i2;
    }

    public static long abs(long j) {
        long j2 = j >>> 63;
        return (j ^ ((~j2) + 1)) + j2;
    }

    public static double floor(double d) {
        if (d != d || d >= TWO_POWER_52 || d <= -4.503599627370496E15d) {
            return d;
        }
        long j = (long) d;
        if (d < 0.0d && ((double) j) != d) {
            j--;
        }
        return j == 0 ? d * ((double) j) : (double) j;
    }

    public static int max(int i, int i2) {
        return i <= i2 ? i2 : i;
    }

    public static long max(long j, long j2) {
        return j <= j2 ? j2 : j;
    }

    public static int min(int i, int i2) {
        return i <= i2 ? i : i2;
    }

    public static long min(long j, long j2) {
        return j <= j2 ? j : j2;
    }

    private static double polyCosine(double d) {
        double d2 = d * d;
        return ((((((2.479773539153719E-5d * d2) - 3231.288930800263d) * d2) + 0.041666666666621166d) * d2) - 8.000000000000002d) * d2;
    }

    private static double polySine(double d) {
        double d2 = d * d;
        return ((((((2.7553817452272217E-6d * d2) - 22521.49865654966d) * d2) + 0.008333333333329196d) * d2) - 26.666666666666668d) * d2 * d;
    }

    public static double pow(double d, int i) {
        double d2;
        int i2 = i;
        double d3 = 1.0d;
        if (i2 == 0) {
            return 1.0d;
        }
        if (i2 < 0) {
            i2 = -i2;
            d2 = 1.0d / d;
        } else {
            d2 = d;
        }
        double d4 = d2 * 1.34217729E8d;
        double d5 = d4 - (d4 - d2);
        double d6 = d2 - d5;
        double d7 = 0.0d;
        while (i2 != 0) {
            if ((i2 & 1) != 0) {
                double d8 = d3 * d2;
                double d9 = d3 * 1.34217729E8d;
                double d10 = d9 - (d9 - d3);
                double d11 = d3 - d10;
                d7 = (d7 * d2) + ((d11 * d6) - (((d8 - (d10 * d5)) - (d11 * d5)) - (d10 * d6)));
                d3 = d8;
            }
            double d12 = d5 * d2;
            double d13 = d5 * 1.34217729E8d;
            double d14 = d13 - (d13 - d5);
            double d15 = d5 - d14;
            double d16 = d12 * 1.34217729E8d;
            d5 = d16 - (d16 - d12);
            d6 = (d6 * d2) + ((d15 * d6) - (((d12 - (d14 * d5)) - (d15 * d5)) - (d14 * d6))) + (d12 - d5);
            d2 = d5 + d6;
            i2 >>= 1;
        }
        return d3 + d7;
    }

    public static double signum(double d) {
        if (d < 0.0d) {
            return -1.0d;
        }
        if (d > 0.0d) {
            return 1.0d;
        }
        return d;
    }

    public static float signum(float f) {
        if (f < 0.0f) {
            return -1.0f;
        }
        if (f > 0.0f) {
            return 1.0f;
        }
        return f;
    }

    private FastMath() {
    }

    private static double doubleHighPart(double d) {
        if (d <= (-Precision.SAFE_MIN) || d >= Precision.SAFE_MIN) {
            return Double.longBitsToDouble(Double.doubleToRawLongBits(d) & MASK_30BITS);
        }
        return d;
    }

    public static double sqrt(double d) {
        return Math.sqrt(d);
    }

    public static double cosh(double d) {
        double exp;
        double exp2;
        double d2 = d;
        if (d2 != d2) {
            return d2;
        }
        if (d2 > 20.0d) {
            if (d2 >= LOG_MAX_VALUE) {
                exp2 = exp(d2 * F_1_2);
            } else {
                exp = exp(d);
                return exp * F_1_2;
            }
        } else if (d2 >= -20.0d) {
            double[] dArr = new double[2];
            if (d2 < 0.0d) {
                d2 = -d2;
            }
            exp(d2, 0.0d, dArr);
            double d3 = dArr[0];
            double d4 = dArr[1];
            double d5 = d3 + d4;
            double d6 = -((d5 - d3) - d4);
            double d7 = d5 * 1.073741824E9d;
            double d8 = (d5 + d7) - d7;
            double d9 = d5 - d8;
            double d10 = 1.0d / d5;
            double d11 = 1.073741824E9d * d10;
            double d12 = (d10 + d11) - d11;
            double d13 = d10 - d12;
            double d14 = d13 + (((((1.0d - (d8 * d12)) - (d8 * d13)) - (d9 * d12)) - (d9 * d13)) * d10) + ((-d6) * d10 * d10);
            double d15 = d5 + d12;
            double d16 = d6 + (-((d15 - d5) - d12));
            double d17 = d15 + d14;
            return (d17 + d16 + (-((d17 - d15) - d14))) * F_1_2;
        } else if (d2 <= (-LOG_MAX_VALUE)) {
            exp2 = exp(d2 * -0.5d);
        } else {
            exp = exp(-d2);
            return exp * F_1_2;
        }
        return F_1_2 * exp2 * exp2;
    }

    public static double sinh(double d) {
        boolean z;
        double d2;
        double d3;
        double d4;
        double exp;
        double exp2;
        double d5 = d;
        if (d5 != d5) {
            return d5;
        }
        int i = (d5 > 20.0d ? 1 : (d5 == 20.0d ? 0 : -1));
        double d6 = F_1_2;
        if (i > 0) {
            if (d5 >= LOG_MAX_VALUE) {
                exp2 = exp(d5 * F_1_2);
            } else {
                exp = exp(d);
                return exp * d6;
            }
        } else if (d5 < -20.0d) {
            d6 = -0.5d;
            if (d5 <= (-LOG_MAX_VALUE)) {
                exp2 = exp(d5 * -0.5d);
            } else {
                exp = exp(-d5);
                return exp * d6;
            }
        } else if (d5 == 0.0d) {
            return d5;
        } else {
            if (d5 < 0.0d) {
                d5 = -d5;
                z = true;
            } else {
                z = false;
            }
            if (d5 > F_1_4) {
                double[] dArr = new double[2];
                exp(d5, 0.0d, dArr);
                double d7 = dArr[0];
                double d8 = dArr[1];
                double d9 = d7 + d8;
                double d10 = -((d9 - d7) - d8);
                double d11 = d9 * 1.073741824E9d;
                double d12 = (d9 + d11) - d11;
                double d13 = d9 - d12;
                double d14 = 1.0d / d9;
                double d15 = 1.073741824E9d * d14;
                double d16 = (d14 + d15) - d15;
                double d17 = d14 - d16;
                double d18 = -d16;
                double d19 = -(d17 + (((((1.0d - (d12 * d16)) - (d12 * d17)) - (d13 * d16)) - (d13 * d17)) * d14) + ((-d10) * d14 * d14));
                double d20 = d9 + d18;
                d4 = d10 + (-((d20 - d9) - d18));
                d2 = d20 + d19;
                d3 = -((d2 - d20) - d19);
            } else {
                double[] dArr2 = new double[2];
                expm1(d5, dArr2);
                double d21 = dArr2[0];
                double d22 = dArr2[1];
                double d23 = d21 + d22;
                double d24 = -((d23 - d21) - d22);
                double d25 = d23 + 1.0d;
                double d26 = 1.0d / d25;
                double d27 = d23 * d26;
                double d28 = d27 * 1.073741824E9d;
                double d29 = (d27 + d28) - d28;
                double d30 = d27 - d29;
                double d31 = 1.073741824E9d * d25;
                double d32 = (d25 + d31) - d31;
                double d33 = d25 - d32;
                double d34 = d30 + (((((d23 - (d32 * d29)) - (d32 * d30)) - (d33 * d29)) - (d33 * d30)) * d26) + (d24 * d26) + ((-d23) * ((-((d25 - 1.0d) - d23)) + d24) * d26 * d26);
                double d35 = d23 + d29;
                d4 = d24 + (-((d35 - d23) - d29));
                d2 = d35 + d34;
                d3 = -((d2 - d35) - d34);
            }
            double d36 = (d2 + d4 + d3) * F_1_2;
            return z ? -d36 : d36;
        }
        return d6 * exp2 * exp2;
    }

    public static double tanh(double d) {
        boolean z;
        double d2;
        double d3 = d;
        if (d3 != d3) {
            return d3;
        }
        if (d3 > 20.0d) {
            return 1.0d;
        }
        if (d3 < -20.0d) {
            return -1.0d;
        }
        if (d3 == 0.0d) {
            return d3;
        }
        if (d3 < 0.0d) {
            d3 = -d3;
            z = true;
        } else {
            z = false;
        }
        if (d3 >= F_1_2) {
            double[] dArr = new double[2];
            exp(d3 * 2.0d, 0.0d, dArr);
            double d4 = dArr[0];
            double d5 = dArr[1];
            double d6 = d4 + d5;
            double d7 = -((d6 - d4) - d5);
            double d8 = -1.0d + d6;
            double d9 = d8 + d7;
            double d10 = (-((d8 + 1.0d) - d6)) + (-((d9 - d8) - d7));
            double d11 = d6 + 1.0d;
            double d12 = d11 + d7;
            double d13 = (-((d11 - 1.0d) - d6)) + (-((d12 - d11) - d7));
            double d14 = d12 * 1.073741824E9d;
            double d15 = (d12 + d14) - d14;
            double d16 = d12 - d15;
            double d17 = d9 / d12;
            double d18 = 1.073741824E9d * d17;
            double d19 = (d17 + d18) - d18;
            double d20 = d17 - d19;
            d2 = d19 + d20 + (((((d9 - (d15 * d19)) - (d15 * d20)) - (d16 * d19)) - (d16 * d20)) / d12) + (d10 / d12) + ((((-d13) * d9) / d12) / d12);
        } else {
            double[] dArr2 = new double[2];
            expm1(d3 * 2.0d, dArr2);
            double d21 = dArr2[0];
            double d22 = dArr2[1];
            double d23 = d21 + d22;
            double d24 = -((d23 - d21) - d22);
            double d25 = d23 + 2.0d;
            double d26 = d25 + d24;
            double d27 = (-((d25 - 2.0d) - d23)) + (-((d26 - d25) - d24));
            double d28 = d26 * 1.073741824E9d;
            double d29 = (d26 + d28) - d28;
            double d30 = d26 - d29;
            double d31 = d23 / d26;
            double d32 = 1.073741824E9d * d31;
            double d33 = (d31 + d32) - d32;
            double d34 = d31 - d33;
            d2 = d33 + d34 + (((((d23 - (d29 * d33)) - (d29 * d34)) - (d30 * d33)) - (d30 * d34)) / d26) + (d24 / d26) + ((((-d27) * d23) / d26) / d26);
        }
        double d35 = d2;
        return z ? -d35 : d35;
    }

    public static double acosh(double d) {
        return log(d + sqrt((d * d) - 1.0d));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00b1, code lost:
        if (r0 > 0.0036d) goto L_0x0087;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static double asinh(double r36) {
        /*
            r0 = r36
            r2 = 0
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x000b
            double r0 = -r0
            r2 = 1
            goto L_0x000c
        L_0x000b:
            r2 = 0
        L_0x000c:
            r3 = 4595184829392702407(0x3fc5604189374bc7, double:0.167)
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r3 <= 0) goto L_0x0025
            double r6 = r0 * r0
            double r6 = r6 + r4
            double r3 = sqrt(r6)
            double r3 = r3 + r0
            double r0 = log(r3)
            goto L_0x00b4
        L_0x0025:
            double r6 = r0 * r0
            r8 = 4591654007284843938(0x3fb8d4fdf3b645a2, double:0.097)
            int r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            r8 = 4606281698874543309(0x3feccccccccccccd, double:0.9)
            r10 = 4606431818862122325(0x3fed555555555555, double:0.9166666666666666)
            r12 = 4590207312512236308(0x3fb3b13b13b13b14, double:0.07692307692307693)
            r14 = 4591215111030249286(0x3fb745d1745d1746, double:0.09090909090909091)
            r16 = 4605681218924227243(0x3feaaaaaaaaaaaab, double:0.8333333333333334)
            r18 = 4606056518893174784(0x3fec000000000000, double:0.875)
            r20 = 4592670820000712476(0x3fbc71c71c71c71c, double:0.1111111111111111)
            r22 = 4594314991293244562(0x3fc2492492492492, double:0.14285714285714285)
            r24 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            r26 = 4604930618986332160(0x3fe8000000000000, double:0.75)
            r28 = 4596373779694328218(0x3fc999999999999a, double:0.2)
            r30 = 4599676419421066581(0x3fd5555555555555, double:0.3333333333333333)
            if (r3 <= 0) goto L_0x00a0
            r32 = 4588638185040256542(0x3fae1e1e1e1e1e1e, double:0.058823529411764705)
            double r32 = r32 * r6
            r34 = 4606619468846596096(0x3fee000000000000, double:0.9375)
            double r32 = r32 * r34
            r34 = 4589468260265693457(0x3fb1111111111111, double:0.06666666666666667)
            double r34 = r34 - r32
            double r34 = r34 * r6
            r32 = 4606539047424678766(0x3fedb6db6db6db6e, double:0.9285714285714286)
            double r34 = r34 * r32
            double r12 = r12 - r34
        L_0x0080:
            double r12 = r12 * r6
            double r12 = r12 * r10
            double r14 = r14 - r12
            double r14 = r14 * r6
            double r14 = r14 * r8
            double r20 = r20 - r14
        L_0x0087:
            double r20 = r20 * r6
            double r20 = r20 * r18
            double r22 = r22 - r20
            double r22 = r22 * r6
            double r22 = r22 * r16
            double r28 = r28 - r22
        L_0x0093:
            double r28 = r28 * r6
            double r28 = r28 * r26
            double r30 = r30 - r28
            double r6 = r6 * r30
            double r6 = r6 * r24
            double r4 = r4 - r6
            double r0 = r0 * r4
            goto L_0x00b4
        L_0x00a0:
            r32 = 4585348967806525243(0x3fa26e978d4fdf3b, double:0.036)
            int r3 = (r0 > r32 ? 1 : (r0 == r32 ? 0 : -1))
            if (r3 <= 0) goto L_0x00aa
            goto L_0x0080
        L_0x00aa:
            r8 = 4570447457359481746(0x3f6d7dbf487fcb92, double:0.0036)
            int r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r3 <= 0) goto L_0x0093
            goto L_0x0087
        L_0x00b4:
            if (r2 == 0) goto L_0x00b7
            double r0 = -r0
        L_0x00b7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.FastMath.asinh(double):double");
    }

    public static double atanh(double d) {
        boolean z;
        double d2;
        double d3 = d;
        if (d3 < 0.0d) {
            d3 = -d3;
            z = true;
        } else {
            z = false;
        }
        if (d3 > 0.15d) {
            d2 = log((d3 + 1.0d) / (1.0d - d3)) * F_1_2;
        } else {
            double d4 = d3 * d3;
            d2 = d3 * ((d3 > 0.087d ? d4 * ((((((((((((((F_1_17 * d4) + F_1_15) * d4) + F_1_13) * d4) + F_1_11) * d4) + F_1_9) * d4) + F_1_7) * d4) + F_1_5) * d4) + F_1_3) : d3 > 0.031d ? d4 * ((((((((((F_1_13 * d4) + F_1_11) * d4) + F_1_9) * d4) + F_1_7) * d4) + F_1_5) * d4) + F_1_3) : d3 > 0.003d ? d4 * ((((((F_1_9 * d4) + F_1_7) * d4) + F_1_5) * d4) + F_1_3) : d4 * ((F_1_5 * d4) + F_1_3)) + 1.0d);
        }
        return z ? -d2 : d2;
    }

    public static double nextUp(double d) {
        return nextAfter(d, Double.POSITIVE_INFINITY);
    }

    public static float nextUp(float f) {
        return nextAfter(f, Double.POSITIVE_INFINITY);
    }

    public static double nextDown(double d) {
        return nextAfter(d, Double.NEGATIVE_INFINITY);
    }

    public static float nextDown(float f) {
        return nextAfter(f, Double.NEGATIVE_INFINITY);
    }

    public static double random() {
        return Math.random();
    }

    public static double exp(double d) {
        return exp(d, 0.0d, (double[]) null);
    }

    private static double exp(double d, double d2, double[] dArr) {
        double d3;
        double d4 = d;
        double d5 = d2;
        double[] dArr2 = dArr;
        int i = (int) d4;
        if (d4 < 0.0d) {
            if (d4 < -746.0d) {
                if (dArr2 != null) {
                    dArr2[0] = 0.0d;
                    dArr2[1] = 0.0d;
                }
                return 0.0d;
            } else if (i < -709) {
                double exp = exp(d4 + 40.19140625d, d5, dArr2) / 2.85040095144011776E17d;
                if (dArr2 != null) {
                    dArr2[0] = dArr2[0] / 2.85040095144011776E17d;
                    dArr2[1] = dArr2[1] / 2.85040095144011776E17d;
                }
                return exp;
            } else if (i == -709) {
                double exp2 = exp(d4 + 1.494140625d, d5, dArr2) / 4.455505956692757d;
                if (dArr2 != null) {
                    dArr2[0] = dArr2[0] / 4.455505956692757d;
                    dArr2[1] = dArr2[1] / 4.455505956692757d;
                }
                return exp2;
            } else {
                i--;
            }
        } else if (i > 709) {
            if (dArr2 != null) {
                dArr2[0] = Double.POSITIVE_INFINITY;
                dArr2[1] = 0.0d;
            }
            return Double.POSITIVE_INFINITY;
        }
        double[] access$000 = ExpIntTable.EXP_INT_TABLE_A;
        int i2 = i + EXP_INT_TABLE_MAX_INDEX;
        double d6 = access$000[i2];
        double d7 = ExpIntTable.EXP_INT_TABLE_B[i2];
        double d8 = (double) i;
        int i3 = (int) ((d4 - d8) * 1024.0d);
        double d9 = ExpFracTable.EXP_FRAC_TABLE_A[i3];
        double d10 = ExpFracTable.EXP_FRAC_TABLE_B[i3];
        double d11 = d4 - (d8 + (((double) i3) / 1024.0d));
        double d12 = (((((((0.04168701738764507d * d11) + 0.1666666505023083d) * d11) + 0.5000000000042687d) * d11) + 1.0d) * d11) - 1.1409003175371524E20d;
        double d13 = d6 * d9;
        double d14 = (d6 * d10) + (d9 * d7) + (d7 * d10);
        double d15 = d14 + d13;
        if (d5 != 0.0d) {
            double d16 = d15 * d5;
            d3 = (d16 * d12) + d16 + (d15 * d12) + d14 + d13;
        } else {
            d3 = (d15 * d12) + d14 + d13;
        }
        if (dArr2 != null) {
            dArr2[0] = d13;
            double d17 = d15 * d5;
            dArr2[1] = (d17 * d12) + d17 + (d15 * d12) + d14;
        }
        return d3;
    }

    public static double expm1(double d) {
        return expm1(d, (double[]) null);
    }

    private static double expm1(double d, double[] dArr) {
        int i;
        boolean z;
        double d2 = d;
        if (d2 != d2 || d2 == 0.0d) {
            return d2;
        }
        if (d2 <= -1.0d || d2 >= 1.0d) {
            double[] dArr2 = new double[2];
            exp(d2, 0.0d, dArr2);
            if (i > 0) {
                return (dArr2[0] - 4.0d) + dArr2[1];
            }
            double d3 = dArr2[0];
            double d4 = -1.0d + d3;
            return d4 + (-((1.0d + d4) - d3)) + dArr2[1];
        }
        if (d2 < 0.0d) {
            d2 = -d2;
            z = true;
        } else {
            z = false;
        }
        int i2 = (int) (d2 * 1024.0d);
        double d5 = ExpFracTable.EXP_FRAC_TABLE_A[i2] - 1.0d;
        double d6 = ExpFracTable.EXP_FRAC_TABLE_B[i2];
        double d7 = d5 + d6;
        double d8 = d7 * 1.073741824E9d;
        double d9 = (d7 + d8) - d8;
        double d10 = (-((d7 - d5) - d6)) + (d7 - d9);
        double d11 = d2 - (((double) i2) / 1024.0d);
        double d12 = ((((((0.008336750013465571d * d11) + 0.041666663879186654d) * d11) + 0.16666666666745392d) * d11) + 0.49999999999999994d) * d11 * d11;
        double d13 = d11 + d12;
        double d14 = d13 * 1.073741824E9d;
        double d15 = (d13 + d14) - d14;
        double d16 = (-((d13 - d11) - d12)) + (d13 - d15);
        double d17 = d15 * d9;
        double d18 = d15 * d10;
        double d19 = d17 + d18;
        double d20 = d16 * d9;
        double d21 = d19 + d20;
        double d22 = (-((d19 - d17) - d18)) + (-((d21 - d19) - d20));
        double d23 = d16 * d10;
        double d24 = d21 + d23;
        double d25 = d22 + (-((d24 - d21) - d23));
        double d26 = d24 + d9;
        double d27 = d26 + d15;
        double d28 = d25 + (-((d26 - d9) - d24)) + (-((d27 - d26) - d15));
        double d29 = d27 + d10;
        double d30 = d28 + (-((d29 - d27) - d10));
        double d31 = d29 + d16;
        double d32 = d30 + (-((d31 - d29) - d16));
        if (z) {
            double d33 = d31 + 1.0d;
            double d34 = 1.0d / d33;
            double d35 = (-((d33 - 1.0d) - d31)) + d32;
            double d36 = d31 * d34;
            double d37 = d36 * 1.073741824E9d;
            double d38 = d31;
            double d39 = (d36 + d37) - d37;
            double d40 = d36 - d39;
            double d41 = 1.073741824E9d * d33;
            double d42 = (d33 + d41) - d41;
            double d43 = d33 - d42;
            d31 = -d39;
            d32 = -(d40 + (((((d38 - (d42 * d39)) - (d42 * d40)) - (d43 * d39)) - (d43 * d40)) * d34) + (d32 * d34) + ((-d38) * d35 * d34 * d34));
        } else {
            double d44 = d31;
        }
        if (dArr != null) {
            dArr[0] = d31;
            dArr[1] = d32;
        }
        return d31 + d32;
    }

    public static double log(double d) {
        return log(d, (double[]) null);
    }

    private static double log(double d, double[] dArr) {
        double d2;
        double d3;
        int i = (d > 0.0d ? 1 : (d == 0.0d ? 0 : -1));
        if (i == 0) {
            return Double.NEGATIVE_INFINITY;
        }
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        if (((Long.MIN_VALUE & doubleToRawLongBits) != 0 || d != d) && i != 0) {
            if (dArr != null) {
                dArr[0] = Double.NaN;
            }
            return Double.NaN;
        } else if (d == Double.POSITIVE_INFINITY) {
            if (dArr != null) {
                dArr[0] = Double.POSITIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        } else {
            int i2 = ((int) (doubleToRawLongBits >> 52)) - 1023;
            if ((9218868437227405312L & doubleToRawLongBits) == 0) {
                if (i == 0) {
                    if (dArr != null) {
                        dArr[0] = Double.NEGATIVE_INFINITY;
                    }
                    return Double.NEGATIVE_INFINITY;
                }
                doubleToRawLongBits <<= 1;
                while ((4503599627370496L & doubleToRawLongBits) == 0) {
                    i2--;
                    doubleToRawLongBits <<= 1;
                }
            }
            if ((i2 == -1 || i2 == 0) && d < 1.01d && d > 0.99d && dArr == null) {
                double d4 = d - 1.0d;
                double d5 = d4 * 1.073741824E9d;
                double d6 = (d4 + d5) - d5;
                double d7 = d4 - d6;
                double[][] dArr2 = LN_QUICK_COEF;
                double[] dArr3 = dArr2[dArr2.length - 1];
                double d8 = dArr3[0];
                double d9 = dArr3[1];
                for (int length = dArr2.length - 2; length >= 0; length--) {
                    double d10 = d8 * d6;
                    double d11 = (d8 * d7) + (d9 * d6) + (d9 * d7);
                    double d12 = d10 * 1.073741824E9d;
                    double d13 = (d10 + d12) - d12;
                    double d14 = (d10 - d13) + d11;
                    double[] dArr4 = LN_QUICK_COEF[length];
                    double d15 = d13 + dArr4[0];
                    double d16 = d15 * 1.073741824E9d;
                    d8 = (d15 + d16) - d16;
                    d9 = (d15 - d8) + d14 + dArr4[1];
                }
                double d17 = d8 * d6;
                double d18 = 1.073741824E9d * d17;
                double d19 = (d17 + d18) - d18;
                return d19 + (d17 - d19) + (d8 * d7) + (d6 * d9) + (d9 * d7);
            }
            long j = 4499201580859392L & doubleToRawLongBits;
            double[] dArr5 = lnMant.LN_MANT[(int) (j >> 42)];
            double d20 = (double) (doubleToRawLongBits & 4398046511103L);
            double d21 = ((double) j) + TWO_POWER_52;
            double d22 = d20 / d21;
            if (dArr != null) {
                double d23 = d22 * 1.073741824E9d;
                double d24 = (d22 + d23) - d23;
                double d25 = d22 - d24;
                double d26 = d25 + (((d20 - (d24 * d21)) - (d25 * d21)) / d21);
                double[][] dArr6 = LN_HI_PREC_COEF;
                double[] dArr7 = dArr6[dArr6.length - 1];
                double d27 = dArr7[0];
                double d28 = dArr7[1];
                for (int length2 = dArr6.length - 2; length2 >= 0; length2--) {
                    double d29 = d27 * d24;
                    double d30 = (d27 * d26) + (d28 * d24) + (d28 * d26);
                    double d31 = d29 * 1.073741824E9d;
                    double d32 = (d29 + d31) - d31;
                    double d33 = (d29 - d32) + d30;
                    double[] dArr8 = LN_HI_PREC_COEF[length2];
                    double d34 = d32 + dArr8[0];
                    double d35 = d33 + dArr8[1];
                    double d36 = d34 * 1.073741824E9d;
                    d27 = (d34 + d36) - d36;
                    d28 = (d34 - d27) + d35;
                }
                double d37 = d27 * d24;
                double d38 = (d27 * d26) + (d24 * d28) + (d28 * d26);
                d2 = d37 + d38;
                d3 = -((d2 - d37) - d38);
            } else {
                d2 = ((((((((((-0.16624882440418567d * d22) + 0.19999954120254515d) * d22) - 16.00000002972804d) * d22) + 0.3333333333332802d) * d22) - 8.0d) * d22) + 1.0d) * d22;
                d3 = 0.0d;
            }
            double d39 = (double) i2;
            double d40 = LN_2_A * d39;
            double d41 = dArr5[0];
            double d42 = d40 + d41;
            double d43 = d42 + d2;
            double d44 = (-((d42 - d40) - d41)) + 0.0d + (-((d43 - d42) - d2));
            double d45 = d39 * LN_2_B;
            double d46 = d43 + d45;
            double d47 = d44 + (-((d46 - d43) - d45));
            double d48 = dArr5[1];
            double d49 = d46 + d48;
            double d50 = d47 + (-((d49 - d46) - d48));
            double d51 = d49 + d3;
            double d52 = d50 + (-((d51 - d49) - d3));
            if (dArr != null) {
                dArr[0] = d51;
                dArr[1] = d52;
            }
            return d51 + d52;
        }
    }

    public static double log1p(double d) {
        if (d == -1.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        if (d == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }
        if (d <= 1.0E-6d && d >= -1.0E-6d) {
            return ((((F_1_3 * d) - F_1_2) * d) + 1.0d) * d;
        }
        double d2 = d + 1.0d;
        double d3 = -((d2 - 1.0d) - d);
        double[] dArr = new double[2];
        double log = log(d2, dArr);
        if (Double.isInfinite(log)) {
            return log;
        }
        double d4 = d3 / d2;
        return (((F_1_2 * d4) + 1.0d) * d4) + dArr[1] + dArr[0];
    }

    public static double log10(double d) {
        double[] dArr = new double[2];
        double log = log(d, dArr);
        if (Double.isInfinite(log)) {
            return log;
        }
        double d2 = dArr[0];
        double d3 = 1.073741824E9d * d2;
        double d4 = (d2 + d3) - d3;
        double d5 = (d2 - d4) + dArr[1];
        return (d5 * 1.9699272335463627E-8d) + (1.9699272335463627E-8d * d4) + (d5 * 0.4342944622039795d) + (d4 * 0.4342944622039795d);
    }

    public static double log(double d, double d2) {
        return log(d2) / log(d);
    }

    public static double pow(double d, double d2) {
        double d3;
        double d4 = d;
        double d5 = d2;
        double[] dArr = new double[2];
        int i = (d5 > 0.0d ? 1 : (d5 == 0.0d ? 0 : -1));
        if (i == 0) {
            return 1.0d;
        }
        if (d4 != d4) {
            return d4;
        }
        if (d4 == 0.0d) {
            if ((Double.doubleToRawLongBits(d) & Long.MIN_VALUE) != 0) {
                long j = (long) d5;
                if (d5 < 0.0d && d5 == ((double) j) && (j & 1) == 1) {
                    return Double.NEGATIVE_INFINITY;
                }
                if (i > 0 && d5 == ((double) j) && (j & 1) == 1) {
                    return -0.0d;
                }
            }
            if (d5 < 0.0d) {
                return Double.POSITIVE_INFINITY;
            }
            return i > 0 ? 0.0d : Double.NaN;
        } else if (d4 == Double.POSITIVE_INFINITY) {
            if (d5 != d5) {
                return d5;
            }
            return d5 < 0.0d ? 0.0d : Double.POSITIVE_INFINITY;
        } else if (d5 == Double.POSITIVE_INFINITY) {
            int i2 = ((d4 * d4) > 1.0d ? 1 : ((d4 * d4) == 1.0d ? 0 : -1));
            if (i2 == 0) {
                return Double.NaN;
            }
            return i2 > 0 ? Double.POSITIVE_INFINITY : 0.0d;
        } else {
            if (d4 == Double.NEGATIVE_INFINITY) {
                if (d5 != d5) {
                    return d5;
                }
                if (d5 < 0.0d) {
                    long j2 = (long) d5;
                    return (d5 == ((double) j2) && (j2 & 1) == 1) ? -0.0d : 0.0d;
                } else if (i > 0) {
                    long j3 = (long) d5;
                    return (d5 == ((double) j3) && (j3 & 1) == 1) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
                }
            }
            if (d5 == Double.NEGATIVE_INFINITY) {
                double d6 = d4 * d4;
                if (d6 == 1.0d) {
                    return Double.NaN;
                }
                return d6 < 1.0d ? Double.POSITIVE_INFINITY : 0.0d;
            } else if (d4 >= 0.0d) {
                if (d5 >= 8.0E298d || d5 <= -8.0E298d) {
                    double d7 = d5 * 9.313225746154785E-10d;
                    d3 = (((9.313225746154785E-10d * d7) + d7) - d7) * 1.073741824E9d * 1.073741824E9d;
                } else {
                    double d8 = d5 * 1.073741824E9d;
                    d3 = (d5 + d8) - d8;
                }
                double d9 = d5 - d3;
                double log = log(d4, dArr);
                if (Double.isInfinite(log)) {
                    return log;
                }
                double d10 = dArr[0];
                double d11 = dArr[1];
                double d12 = 1.073741824E9d * d10;
                double d13 = (d10 + d12) - d12;
                double d14 = d11 + (d10 - d13);
                double d15 = d13 * d3;
                double d16 = (d13 * d9) + (d3 * d14) + (d14 * d9);
                double d17 = d15 + d16;
                double d18 = -((d17 - d15) - d16);
                return exp(d17, ((((((((0.008333333333333333d * d18) + 0.041666666666666664d) * d18) + 0.16666666666666666d) * d18) + F_1_2) * d18) + 1.0d) * d18, (double[]) null);
            } else if (d5 >= TWO_POWER_53 || d5 <= -9.007199254740992E15d) {
                return pow(-d4, d5);
            } else {
                long j4 = (long) d5;
                if (d5 != ((double) j4)) {
                    return Double.NaN;
                }
                int i3 = ((j4 & 1) > 0 ? 1 : ((j4 & 1) == 0 ? 0 : -1));
                double pow = pow(-d4, d5);
                return i3 == 0 ? pow : -pow;
            }
        }
    }

    private static double sinQ(double d, double d2) {
        int i = (int) ((8.0d * d) + F_1_2);
        double d3 = d - EIGHTHS[i];
        double d4 = SINE_TABLE_A[i];
        double d5 = SINE_TABLE_B[i];
        double d6 = COSINE_TABLE_A[i];
        double d7 = COSINE_TABLE_B[i];
        double polySine = polySine(d3);
        double polyCosine = polyCosine(d3);
        double d8 = 1.073741824E9d * d3;
        double d9 = (d3 + d8) - d8;
        double d10 = polySine + (d3 - d9);
        double d11 = d4 + 0.0d;
        double d12 = d6 * d9;
        double d13 = d11 + d12;
        double d14 = d7;
        double d15 = (-((d11 - 0.0d) - d4)) + 0.0d + (-((d13 - d11) - d12)) + (d4 * polyCosine) + (d6 * d10) + d5 + (d14 * d9) + (d5 * polyCosine) + (d14 * d10);
        if (d2 != 0.0d) {
            double d16 = (((d6 + d14) * (polyCosine + 1.0d)) - ((d4 + d5) * (d9 + d10))) * d2;
            double d17 = d13 + d16;
            d15 += -((d17 - d13) - d16);
            d13 = d17;
        }
        return d13 + d15;
    }

    private static double cosQ(double d, double d2) {
        double d3 = 1.5707963267948966d - d;
        return sinQ(d3, (-((d3 - 1.5707963267948966d) + d)) + (6.123233995736766E-17d - d2));
    }

    private static double tanQ(double d, double d2, boolean z) {
        double d3;
        double d4;
        int i = (int) ((8.0d * d) + F_1_2);
        double d5 = d - EIGHTHS[i];
        double d6 = SINE_TABLE_A[i];
        double d7 = SINE_TABLE_B[i];
        double d8 = COSINE_TABLE_A[i];
        double d9 = COSINE_TABLE_B[i];
        double polySine = polySine(d5);
        double polyCosine = polyCosine(d5);
        double d10 = d5 * 1.073741824E9d;
        double d11 = (d5 + d10) - d10;
        double d12 = polySine + (d5 - d11);
        double d13 = d6 + 0.0d;
        double d14 = d8 * d11;
        double d15 = d13 + d14;
        double d16 = d7;
        double d17 = (-((d13 - 0.0d) - d6)) + 0.0d + (-((d15 - d13) - d14)) + (d6 * polyCosine) + (d8 * d12) + d16 + (d9 * d11) + (d16 * polyCosine) + (d9 * d12);
        double d18 = d15 + d17;
        double d19 = -((d18 - d15) - d17);
        double d20 = d8 * 1.0d;
        double d21 = d20 + 0.0d;
        double d22 = d18;
        double d23 = (-d6) * d11;
        double d24 = d21 + d23;
        double d25 = ((((-((d21 - 0.0d) - d20)) + 0.0d) + (-((d24 - d21) - d23))) + (((1.0d * d9) + (d8 * polyCosine)) + (d9 * polyCosine))) - (((d16 * d11) + (d6 * d12)) + (d16 * d12));
        double d26 = d24 + d25;
        double d27 = -((d26 - d24) - d25);
        if (z) {
            d3 = d19;
            d19 = d27;
            d4 = d26;
            d26 = d22;
        } else {
            d3 = d27;
            d4 = d22;
        }
        double d28 = d4 / d26;
        double d29 = d28 * 1.073741824E9d;
        double d30 = (d28 + d29) - d29;
        double d31 = d28 - d30;
        double d32 = 1.073741824E9d * d26;
        double d33 = (d26 + d32) - d32;
        double d34 = d26 - d33;
        double d35 = (((((d4 - (d30 * d33)) - (d30 * d34)) - (d33 * d31)) - (d31 * d34)) / d26) + (d19 / d26) + ((((-d4) * d3) / d26) / d26);
        if (d2 != 0.0d) {
            double d36 = d2 + (d28 * d28 * d2);
            if (z) {
                d36 = -d36;
            }
            d35 += d36;
        }
        return d28 + d35;
    }

    private static void reducePayneHanek(double d, double[] dArr) {
        long j;
        long j2;
        long j3;
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        int i = ((int) ((doubleToRawLongBits >> 52) & 2047)) - 1022;
        long j4 = ((doubleToRawLongBits & 4503599627370495L) | 4503599627370496L) << 11;
        int i2 = i >> 6;
        int i3 = i - (i2 << 6);
        if (i3 != 0) {
            long j5 = i2 == 0 ? 0 : RECIP_2PI[i2 - 1] << i3;
            long[] jArr = RECIP_2PI;
            long j6 = jArr[i2];
            int i4 = 64 - i3;
            j2 = j5 | (j6 >>> i4);
            long j7 = jArr[i2 + 1];
            j = (j6 << i3) | (j7 >>> i4);
            j3 = (jArr[i2 + 2] >>> i4) | (j7 << i3);
        } else {
            if (i2 == 0) {
                j2 = 0;
            } else {
                j2 = RECIP_2PI[i2 - 1];
            }
            long[] jArr2 = RECIP_2PI;
            j = jArr2[i2];
            j3 = jArr2[i2 + 1];
        }
        long j8 = j4 >>> 32;
        long j9 = j4 & 4294967295L;
        long j10 = j >>> 32;
        long j11 = j & 4294967295L;
        long j12 = j8 * j10;
        long j13 = j9 * j11;
        long j14 = j10 * j9;
        long j15 = j11 * j8;
        long j16 = j13 + (j15 << 32);
        long j17 = j12 + (j15 >>> 32);
        boolean z = (j13 & Long.MIN_VALUE) != 0;
        boolean z2 = (j15 & 2147483648L) != 0;
        int i5 = ((j16 & Long.MIN_VALUE) > 0 ? 1 : ((j16 & Long.MIN_VALUE) == 0 ? 0 : -1));
        boolean z3 = i5 != 0;
        if ((z && z2) || ((z || z2) && !z3)) {
            j17++;
        }
        boolean z4 = i5 != 0;
        boolean z5 = (j14 & 2147483648L) != 0;
        long j18 = j16 + (j14 << 32);
        long j19 = j17 + (j14 >>> 32);
        int i6 = ((j18 & Long.MIN_VALUE) > 0 ? 1 : ((j18 & Long.MIN_VALUE) == 0 ? 0 : -1));
        boolean z6 = i6 != 0;
        if ((z4 && z5) || ((z4 || z5) && !z6)) {
            j19++;
        }
        long j20 = j3 >>> 32;
        long j21 = (j8 * j20) + (((j20 * j9) + ((j3 & 4294967295L) * j8)) >>> 32);
        boolean z7 = i6 != 0;
        boolean z8 = (j21 & Long.MIN_VALUE) != 0;
        long j22 = j18 + j21;
        boolean z9 = (j22 & Long.MIN_VALUE) != 0;
        if ((z7 && z8) || ((z7 || z8) && !z9)) {
            j19++;
        }
        long j23 = j2 >>> 32;
        long j24 = j2 & 4294967295L;
        long j25 = j19 + (j9 * j24) + (((j9 * j23) + (j8 * j24)) << 32);
        int i7 = (int) (j25 >>> 62);
        long j26 = (j25 << 2) | (j22 >>> 62);
        long j27 = j22 << 2;
        long j28 = j26 >>> 32;
        long j29 = j26 & 4294967295L;
        long[] jArr3 = PI_O_4_BITS;
        long j30 = jArr3[0];
        long j31 = j30 >>> 32;
        long j32 = j30 & 4294967295L;
        long j33 = j28 * j31;
        long j34 = j29 * j32;
        long j35 = j31 * j29;
        long j36 = j32 * j28;
        long j37 = j34 + (j36 << 32);
        long j38 = j33 + (j36 >>> 32);
        boolean z10 = (j34 & Long.MIN_VALUE) != 0;
        boolean z11 = (j36 & 2147483648L) != 0;
        int i8 = ((j37 & Long.MIN_VALUE) > 0 ? 1 : ((j37 & Long.MIN_VALUE) == 0 ? 0 : -1));
        boolean z12 = i8 != 0;
        if ((z10 && z11) || ((z10 || z11) && !z12)) {
            j38++;
        }
        boolean z13 = i8 != 0;
        boolean z14 = (j35 & 2147483648L) != 0;
        long j39 = j37 + (j35 << 32);
        long j40 = j38 + (j35 >>> 32);
        int i9 = ((j39 & Long.MIN_VALUE) > 0 ? 1 : ((j39 & Long.MIN_VALUE) == 0 ? 0 : -1));
        boolean z15 = i9 != 0;
        if ((z13 && z14) || ((z13 || z14) && !z15)) {
            j40++;
        }
        long j41 = jArr3[1];
        long j42 = j41 >>> 32;
        long j43 = (j28 * j42) + (((j29 * j42) + (j28 * (j41 & 4294967295L))) >>> 32);
        boolean z16 = i9 != 0;
        boolean z17 = (j43 & Long.MIN_VALUE) != 0;
        long j44 = j39 + j43;
        int i10 = ((j44 & Long.MIN_VALUE) > 0 ? 1 : ((j44 & Long.MIN_VALUE) == 0 ? 0 : -1));
        boolean z18 = i10 != 0;
        if ((z16 && z17) || ((z16 || z17) && !z18)) {
            j40++;
        }
        long j45 = j27 >>> 32;
        long j46 = j30 >>> 32;
        long j47 = (j45 * j46) + ((((j27 & 4294967295L) * j46) + (j45 * (j30 & 4294967295L))) >>> 32);
        boolean z19 = i10 != 0;
        boolean z20 = (j47 & Long.MIN_VALUE) != 0;
        long j48 = j44 + j47;
        boolean z21 = (j48 & Long.MIN_VALUE) != 0;
        if ((z19 && z20) || ((z19 || z20) && !z21)) {
            j40++;
        }
        double d2 = ((double) (j40 >>> 12)) / TWO_POWER_52;
        double d3 = (((double) (((j40 & 4095) << 40) + (j48 >>> 24))) / TWO_POWER_52) / TWO_POWER_52;
        double d4 = d2 + d3;
        dArr[0] = (double) i7;
        dArr[1] = d4 * 2.0d;
        dArr[2] = (-((d4 - d2) - d3)) * 2.0d;
    }

    public static double sin(double d) {
        double d2;
        boolean z;
        double sinQ;
        double d3 = 0.0d;
        int i = 0;
        if (d < 0.0d) {
            d2 = -d;
            z = true;
        } else {
            d2 = d;
            z = false;
        }
        if (d2 == 0.0d) {
            if (Double.doubleToRawLongBits(d) < 0) {
                return -0.0d;
            }
            return 0.0d;
        } else if (d2 != d2 || d2 == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        } else {
            if (d2 > 3294198.0d) {
                double[] dArr = new double[3];
                reducePayneHanek(d2, dArr);
                i = ((int) dArr[0]) & 3;
                d2 = dArr[1];
                d3 = dArr[2];
            } else if (d2 > 1.5707963267948966d) {
                CodyWaite codyWaite = new CodyWaite(d2);
                i = codyWaite.getK() & 3;
                d2 = codyWaite.getRemA();
                d3 = codyWaite.getRemB();
            }
            if (z) {
                i ^= 2;
            }
            if (i == 0) {
                return sinQ(d2, d3);
            }
            if (i == 1) {
                return cosQ(d2, d3);
            }
            if (i == 2) {
                sinQ = sinQ(d2, d3);
            } else if (i != 3) {
                return Double.NaN;
            } else {
                sinQ = cosQ(d2, d3);
            }
            return -sinQ;
        }
    }

    public static double cos(double d) {
        double sinQ;
        double d2 = 0.0d;
        if (d < 0.0d) {
            d = -d;
        }
        if (d != d || d == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        int i = 0;
        if (d > 3294198.0d) {
            double[] dArr = new double[3];
            reducePayneHanek(d, dArr);
            i = ((int) dArr[0]) & 3;
            d = dArr[1];
            d2 = dArr[2];
        } else if (d > 1.5707963267948966d) {
            CodyWaite codyWaite = new CodyWaite(d);
            i = codyWaite.getK() & 3;
            d = codyWaite.getRemA();
            d2 = codyWaite.getRemB();
        }
        if (i == 0) {
            return cosQ(d, d2);
        }
        if (i == 1) {
            sinQ = sinQ(d, d2);
        } else if (i == 2) {
            sinQ = cosQ(d, d2);
        } else if (i != 3) {
            return Double.NaN;
        } else {
            return sinQ(d, d2);
        }
        return -sinQ;
    }

    public static double tan(double d) {
        double d2;
        boolean z;
        int i;
        double d3;
        double d4 = 0.0d;
        if (d < 0.0d) {
            d2 = -d;
            z = true;
        } else {
            d2 = d;
            z = false;
        }
        if (d2 == 0.0d) {
            if (Double.doubleToRawLongBits(d) < 0) {
                return -0.0d;
            }
            return 0.0d;
        } else if (d2 != d2 || d2 == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        } else {
            if (d2 > 3294198.0d) {
                double[] dArr = new double[3];
                reducePayneHanek(d2, dArr);
                i = 3 & ((int) dArr[0]);
                d2 = dArr[1];
                d4 = dArr[2];
            } else if (d2 > 1.5707963267948966d) {
                CodyWaite codyWaite = new CodyWaite(d2);
                i = 3 & codyWaite.getK();
                d2 = codyWaite.getRemA();
                d4 = codyWaite.getRemB();
            } else {
                i = 0;
            }
            if (d2 > 1.5d) {
                double d5 = 1.5707963267948966d - d2;
                double d6 = (-((d5 - 1.5707963267948966d) + d2)) + (6.123233995736766E-17d - d4);
                double d7 = d5 + d6;
                i ^= 1;
                z = !z;
                double d8 = d7;
                d4 = -((d7 - d5) - d6);
                d2 = d8;
            }
            if ((i & 1) == 0) {
                d3 = tanQ(d2, d4, false);
            } else {
                d3 = -tanQ(d2, d4, true);
            }
            return z ? -d3 : d3;
        }
    }

    public static double atan(double d) {
        return atan(d, 0.0d, false);
    }

    private static double atan(double d, double d2, boolean z) {
        boolean z2;
        double d3;
        int i;
        double d4;
        double d5;
        double d6 = d;
        if (d6 != 0.0d) {
            if (d6 < 0.0d) {
                d6 = -d6;
                d3 = -d2;
                z2 = true;
            } else {
                d3 = d2;
                z2 = false;
            }
            if (d6 > 1.633123935319537E16d) {
                return z2 ^ z ? -1.5707963267948966d : 1.5707963267948966d;
            }
            if (d6 < 1.0d) {
                i = (int) ((((-1.7168146928204135d * d6 * d6) + 8.0d) * d6) + F_1_2);
            } else {
                double d7 = 1.0d / d6;
                i = (int) ((-(((-1.7168146928204135d * d7 * d7) + 8.0d) * d7)) + 13.07d);
            }
            double d8 = TANGENT_TABLE_A[i];
            double d9 = TANGENT_TABLE_B[i];
            double d10 = d6 - d8;
            double d11 = (-((d10 - d6) + d8)) + (d3 - d9);
            double d12 = d10 + d11;
            double d13 = -((d12 - d10) - d11);
            double d14 = d6 * 1.073741824E9d;
            double d15 = (d6 + d14) - d14;
            double d16 = d3 + ((d6 + d3) - d15);
            if (i == 0) {
                double d17 = 1.0d / (((d15 + d16) * (d8 + d9)) + 1.0d);
                d4 = d12 * d17;
                d5 = d13 * d17;
            } else {
                double d18 = d15 * d8;
                double d19 = d18 + 1.0d;
                double d20 = (d8 * d16) + (d15 * d9);
                double d21 = d19 + d20;
                double d22 = (-((d19 - 1.0d) - d18)) + (-((d21 - d19) - d20)) + (d16 * d9);
                double d23 = d12 / d21;
                double d24 = d23 * 1.073741824E9d;
                double d25 = (d23 + d24) - d24;
                double d26 = d23 - d25;
                double d27 = 1.073741824E9d * d21;
                double d28 = (d21 + d27) - d27;
                double d29 = d21 - d28;
                d5 = (((((d12 - (d25 * d28)) - (d25 * d29)) - (d28 * d26)) - (d26 * d29)) / d21) + ((((-d12) * d22) / d21) / d21) + (d13 / d21);
                d4 = d23;
            }
            double d30 = d4 * d4;
            double d31 = ((((((((((0.07490822288864472d * d30) - 0.09088450866185192d) * d30) + 0.11111095942313305d) * d30) - 0.1428571423679182d) * d30) + 0.19999999999923582d) * d30) - 0.33333333333333287d) * d30 * d4;
            double d32 = d4 + d31;
            double d33 = (-((d32 - d4) - d31)) + (d5 / (d30 + 1.0d));
            double d34 = EIGHTHS[i];
            double d35 = d34 + d32;
            double d36 = d35 + d33;
            double d37 = (-((d35 - d34) - d32)) + (-((d36 - d35) - d33));
            double d38 = d36 + d37;
            if (z) {
                double d39 = 3.141592653589793d - d38;
                d38 = (-((d39 - 3.141592653589793d) + d38)) + (1.2246467991473532E-16d - (-((d38 - d36) - d37))) + d39;
            }
            return z2 ^ z ? -d38 : d38;
        } else if (z) {
            return copySign(3.141592653589793d, d6);
        } else {
            return d6;
        }
    }

    public static double atan2(double d, double d2) {
        double d3 = d;
        if (d2 != d2 || d3 != d3) {
            return Double.NaN;
        }
        int i = (d3 > 0.0d ? 1 : (d3 == 0.0d ? 0 : -1));
        if (i == 0) {
            double d4 = d2 * d3;
            double d5 = 1.0d / d2;
            double d6 = 1.0d / d3;
            if (d5 == 0.0d) {
                return d2 > 0.0d ? d3 : copySign(3.141592653589793d, d3);
            }
            if (d2 < 0.0d || d5 < 0.0d) {
                return (d3 < 0.0d || d6 < 0.0d) ? -3.141592653589793d : 3.141592653589793d;
            }
            return d4;
        } else if (d3 == Double.POSITIVE_INFINITY) {
            if (d2 == Double.POSITIVE_INFINITY) {
                return 0.7853981633974483d;
            }
            return d2 == Double.NEGATIVE_INFINITY ? 2.356194490192345d : 1.5707963267948966d;
        } else if (d3 != Double.NEGATIVE_INFINITY) {
            if (d2 == Double.POSITIVE_INFINITY) {
                if (i <= 0) {
                    double d7 = 1.0d / d3;
                    if (d7 <= 0.0d) {
                        if (d3 < 0.0d || d7 < 0.0d) {
                            return -0.0d;
                        }
                    }
                }
                return 0.0d;
            }
            if (d2 == Double.NEGATIVE_INFINITY) {
                if (i <= 0) {
                    double d8 = 1.0d / d3;
                    if (d8 <= 0.0d) {
                        if (d3 < 0.0d || d8 < 0.0d) {
                            return -3.141592653589793d;
                        }
                    }
                }
                return 3.141592653589793d;
            }
            if (d2 == 0.0d) {
                if (i <= 0) {
                    double d9 = 1.0d / d3;
                    if (d9 <= 0.0d) {
                        if (d3 < 0.0d || d9 < 0.0d) {
                            return -1.5707963267948966d;
                        }
                    }
                }
                return 1.5707963267948966d;
            }
            double d10 = d3 / d2;
            boolean z = true;
            if (Double.isInfinite(d10)) {
                if (d2 >= 0.0d) {
                    z = false;
                }
                return atan(d10, 0.0d, z);
            }
            double doubleHighPart = doubleHighPart(d10);
            double d11 = d10 - doubleHighPart;
            double doubleHighPart2 = doubleHighPart(d2);
            double d12 = d2 - doubleHighPart2;
            double d13 = d11 + (((((d3 - (doubleHighPart * doubleHighPart2)) - (doubleHighPart * d12)) - (doubleHighPart2 * d11)) - (d12 * d11)) / d2);
            double d14 = doubleHighPart + d13;
            double d15 = -((d14 - doubleHighPart) - d13);
            if (d14 == 0.0d) {
                d14 = copySign(0.0d, d3);
            }
            if (d2 >= 0.0d) {
                z = false;
            }
            return atan(d14, d15, z);
        } else if (d2 == Double.POSITIVE_INFINITY) {
            return -0.7853981633974483d;
        } else {
            return d2 == Double.NEGATIVE_INFINITY ? -2.356194490192345d : -1.5707963267948966d;
        }
    }

    public static double asin(double d) {
        int i;
        double d2 = d;
        if (d2 != d2 || d2 > 1.0d || d2 < -1.0d) {
            return Double.NaN;
        }
        if (i == 0) {
            return 1.5707963267948966d;
        }
        if (d2 == -1.0d) {
            return -1.5707963267948966d;
        }
        if (d2 == 0.0d) {
            return d2;
        }
        double d3 = d2 * 1.073741824E9d;
        double d4 = (d2 + d3) - d3;
        double d5 = d2 - d4;
        double d6 = d4 * d4;
        double d7 = (d4 * d5 * 2.0d) + (d5 * d5);
        double d8 = -d6;
        double d9 = -d7;
        double d10 = d8 + 1.0d;
        double d11 = d10 + d9;
        double d12 = (-((d10 - 1.0d) - d8)) + (-((d11 - d10) - d9));
        double sqrt = sqrt(d11);
        double d13 = sqrt * 1.073741824E9d;
        double d14 = (sqrt + d13) - d13;
        double d15 = sqrt - d14;
        double d16 = 2.0d * sqrt;
        double d17 = d15 + ((((d11 - (d14 * d14)) - ((d14 * 2.0d) * d15)) - (d15 * d15)) / d16);
        double d18 = d2 / sqrt;
        double d19 = 1.073741824E9d * d18;
        double d20 = (d18 + d19) - d19;
        double d21 = d18 - d20;
        double d22 = d21 + (((((d2 - (d20 * d14)) - (d20 * d17)) - (d14 * d21)) - (d17 * d21)) / sqrt) + ((((-d2) * (d12 / d16)) / sqrt) / sqrt);
        double d23 = d20 + d22;
        return atan(d23, -((d23 - d20) - d22), false);
    }

    public static double acos(double d) {
        int i;
        if (d != d || d > 1.0d || d < -1.0d) {
            return Double.NaN;
        }
        if (d == -1.0d) {
            return 3.141592653589793d;
        }
        if (i == 0) {
            return 0.0d;
        }
        if (d == 0.0d) {
            return 1.5707963267948966d;
        }
        double d2 = d * 1.073741824E9d;
        double d3 = (d + d2) - d2;
        double d4 = d - d3;
        double d5 = -(d3 * d3);
        double d6 = -((d3 * d4 * 2.0d) + (d4 * d4));
        double d7 = d5 + 1.0d;
        double d8 = d7 + d6;
        double d9 = (-((d7 - 1.0d) - d5)) + (-((d8 - d7) - d6));
        double sqrt = sqrt(d8);
        double d10 = 1.073741824E9d * sqrt;
        double d11 = (sqrt + d10) - d10;
        double d12 = sqrt - d11;
        double d13 = sqrt * 2.0d;
        double d14 = d12 + ((((d8 - (d11 * d11)) - ((d11 * 2.0d) * d12)) - (d12 * d12)) / d13) + (d9 / d13);
        double d15 = d11 + d14;
        double d16 = -((d15 - d11) - d14);
        double d17 = d15 / d;
        if (Double.isInfinite(d17)) {
            return 1.5707963267948966d;
        }
        double doubleHighPart = doubleHighPart(d17);
        double d18 = d17 - doubleHighPart;
        double d19 = d18 + (((((d15 - (doubleHighPart * d3)) - (doubleHighPart * d4)) - (d3 * d18)) - (d4 * d18)) / d) + (d16 / d);
        double d20 = doubleHighPart + d19;
        return atan(d20, -((d20 - doubleHighPart) - d19), d < 0.0d);
    }

    public static double cbrt(double d) {
        boolean z;
        int i;
        long j;
        double d2;
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        int i2 = ((int) ((doubleToRawLongBits >> 52) & 2047)) - 1023;
        if (i2 != -1023) {
            z = false;
            i = i2;
            j = doubleToRawLongBits;
            d2 = d;
        } else if (d == 0.0d) {
            return d;
        } else {
            d2 = 1.8014398509481984E16d * d;
            j = Double.doubleToRawLongBits(d2);
            i = ((int) (2047 & (j >> 52))) - 1023;
            z = true;
        }
        if (i == 1024) {
            return d2;
        }
        double longBitsToDouble = Double.longBitsToDouble((Long.MIN_VALUE & j) | (((long) (((i / 3) + AnalyticsListener.EVENT_DRM_KEYS_LOADED) & 2047)) << 52));
        double longBitsToDouble2 = Double.longBitsToDouble((j & 4503599627370495L) | 4607182418800017408L);
        double d3 = ((((((((-0.010714690733195933d * longBitsToDouble2) + 0.0875862700108075d) * longBitsToDouble2) - 14.214349574856733d) * longBitsToDouble2) + 0.7249995199969751d) * longBitsToDouble2) + 0.5039018405998233d) * CBRTTWO[(i % 3) + 2];
        double d4 = d2 / ((longBitsToDouble * longBitsToDouble) * longBitsToDouble);
        double d5 = d3 + ((d4 - ((d3 * d3) * d3)) / ((d3 * 3.0d) * d3));
        double d6 = d5 + ((d4 - ((d5 * d5) * d5)) / ((d5 * 3.0d) * d5));
        double d7 = d6 * 1.073741824E9d;
        double d8 = (d6 + d7) - d7;
        double d9 = d6 - d8;
        double d10 = d8 * d8;
        double d11 = 1.073741824E9d * d10;
        double d12 = (d10 + d11) - d11;
        double d13 = (d8 * d9 * 2.0d) + (d9 * d9) + (d10 - d12);
        double d14 = d12 * d8;
        double d15 = d4 - d14;
        double d16 = (d6 + ((d15 + ((-((d15 - d4) + d14)) - (((d12 * d9) + (d8 * d13)) + (d13 * d9)))) / ((3.0d * d6) * d6))) * longBitsToDouble;
        return z ? d16 * 3.814697265625E-6d : d16;
    }

    public static double toRadians(double d) {
        if (Double.isInfinite(d) || d == 0.0d) {
            return d;
        }
        double doubleHighPart = doubleHighPart(d);
        double d2 = d - doubleHighPart;
        double d3 = (d2 * 1.997844754509471E-9d) + (d2 * 0.01745329052209854d) + (1.997844754509471E-9d * doubleHighPart) + (doubleHighPart * 0.01745329052209854d);
        return d3 == 0.0d ? d3 * d : d3;
    }

    public static double toDegrees(double d) {
        if (Double.isInfinite(d) || d == 0.0d) {
            return d;
        }
        double doubleHighPart = doubleHighPart(d);
        double d2 = d - doubleHighPart;
        return (d2 * 3.145894820876798E-6d) + (d2 * 57.2957763671875d) + (3.145894820876798E-6d * doubleHighPart) + (doubleHighPart * 57.2957763671875d);
    }

    public static float abs(float f) {
        return Float.intBitsToFloat(Float.floatToRawIntBits(f) & Integer.MAX_VALUE);
    }

    public static double abs(double d) {
        return Double.longBitsToDouble(Double.doubleToRawLongBits(d) & Long.MAX_VALUE);
    }

    public static double ulp(double d) {
        if (Double.isInfinite(d)) {
            return Double.POSITIVE_INFINITY;
        }
        return abs(d - Double.longBitsToDouble(Double.doubleToRawLongBits(d) ^ 1));
    }

    public static float ulp(float f) {
        if (Float.isInfinite(f)) {
            return Float.POSITIVE_INFINITY;
        }
        return abs(f - Float.intBitsToFloat(Float.floatToIntBits(f) ^ 1));
    }

    public static double scalb(double d, int i) {
        int i2;
        int i3 = i;
        if (i3 > -1023 && i3 < 1024) {
            return Double.longBitsToDouble(((long) (i3 + AnalyticsListener.EVENT_DRM_KEYS_LOADED)) << 52) * d;
        }
        if (Double.isNaN(d) || Double.isInfinite(d) || d == 0.0d) {
            return d;
        }
        if (i3 < -2098) {
            return i2 > 0 ? 0.0d : -0.0d;
        }
        if (i3 > 2097) {
            return i2 > 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
        }
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        long j = Long.MIN_VALUE & doubleToRawLongBits;
        int i4 = ((int) (doubleToRawLongBits >>> 52)) & 2047;
        long j2 = doubleToRawLongBits & 4503599627370495L;
        int i5 = i4 + i3;
        if (i3 < 0) {
            if (i5 > 0) {
                return Double.longBitsToDouble((((long) i5) << 52) | j | j2);
            }
            if (i5 <= -53) {
                return j == 0 ? 0.0d : -0.0d;
            }
            long j3 = 4503599627370496L | j2;
            long j4 = (1 << (-i5)) & j3;
            long j5 = j3 >>> (1 - i5);
            if (j4 != 0) {
                j5++;
            }
            return Double.longBitsToDouble(j5 | j);
        } else if (i4 == 0) {
            while ((j2 >>> 52) != 1) {
                j2 <<= 1;
                i5--;
            }
            int i6 = i5 + 1;
            long j6 = j2 & 4503599627370495L;
            if (i6 < 2047) {
                return Double.longBitsToDouble(j6 | (((long) i6) << 52) | j);
            }
            if (j == 0) {
                return Double.POSITIVE_INFINITY;
            }
            return Double.NEGATIVE_INFINITY;
        } else if (i5 < 2047) {
            return Double.longBitsToDouble((((long) i5) << 52) | j | j2);
        } else {
            if (j == 0) {
                return Double.POSITIVE_INFINITY;
            }
            return Double.NEGATIVE_INFINITY;
        }
    }

    public static float scalb(float f, int i) {
        int i2;
        if (i > -127 && i < 128) {
            return f * Float.intBitsToFloat((i + 127) << 23);
        }
        if (Float.isNaN(f) || Float.isInfinite(f) || f == 0.0f) {
            return f;
        }
        if (i < -277) {
            return i2 > 0 ? 0.0f : -0.0f;
        }
        if (i > 276) {
            return i2 > 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
        }
        int floatToIntBits = Float.floatToIntBits(f);
        int i3 = Integer.MIN_VALUE & floatToIntBits;
        int i4 = (floatToIntBits >>> 23) & 255;
        int i5 = floatToIntBits & 8388607;
        int i6 = i4 + i;
        if (i < 0) {
            if (i6 > 0) {
                return Float.intBitsToFloat(i5 | (i6 << 23) | i3);
            }
            if (i6 > -24) {
                int i7 = i5 | 8388608;
                int i8 = (1 << (-i6)) & i7;
                int i9 = i7 >>> (1 - i6);
                if (i8 != 0) {
                    i9++;
                }
                return Float.intBitsToFloat(i9 | i3);
            } else if (i3 == 0) {
                return 0.0f;
            } else {
                return -0.0f;
            }
        } else if (i4 == 0) {
            while ((i5 >>> 23) != 1) {
                i5 <<= 1;
                i6--;
            }
            int i10 = i6 + 1;
            int i11 = i5 & 8388607;
            if (i10 < 255) {
                return Float.intBitsToFloat(i11 | (i10 << 23) | i3);
            }
            if (i3 == 0) {
                return Float.POSITIVE_INFINITY;
            }
            return Float.NEGATIVE_INFINITY;
        } else if (i6 < 255) {
            return Float.intBitsToFloat(i5 | (i6 << 23) | i3);
        } else {
            if (i3 == 0) {
                return Float.POSITIVE_INFINITY;
            }
            return Float.NEGATIVE_INFINITY;
        }
    }

    public static double nextAfter(double d, double d2) {
        if (Double.isNaN(d) || Double.isNaN(d2)) {
            return Double.NaN;
        }
        if (d == d2) {
            return d2;
        }
        if (Double.isInfinite(d)) {
            return d < 0.0d ? -1.7976931348623157E308d : Double.MAX_VALUE;
        }
        if (d == 0.0d) {
            return d2 < 0.0d ? -4.9E-324d : Double.MIN_VALUE;
        }
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        long j = Long.MIN_VALUE & doubleToRawLongBits;
        boolean z = true;
        boolean z2 = d2 < d;
        if (j != 0) {
            z = false;
        }
        if (z2 ^ z) {
            return Double.longBitsToDouble(j | ((doubleToRawLongBits & Long.MAX_VALUE) + 1));
        }
        return Double.longBitsToDouble(j | ((doubleToRawLongBits & Long.MAX_VALUE) - 1));
    }

    public static float nextAfter(float f, double d) {
        double d2 = (double) f;
        if (Double.isNaN(d2) || Double.isNaN(d)) {
            return Float.NaN;
        }
        if (d2 == d) {
            return (float) d;
        }
        if (Float.isInfinite(f)) {
            return f < 0.0f ? -3.4028235E38f : Float.MAX_VALUE;
        }
        if (f == 0.0f) {
            return d < 0.0d ? -1.4E-45f : Float.MIN_VALUE;
        }
        int floatToIntBits = Float.floatToIntBits(f);
        int i = Integer.MIN_VALUE & floatToIntBits;
        boolean z = false;
        boolean z2 = d < d2;
        if (i == 0) {
            z = true;
        }
        if (z2 ^ z) {
            return Float.intBitsToFloat(((floatToIntBits & Integer.MAX_VALUE) + 1) | i);
        }
        return Float.intBitsToFloat(((floatToIntBits & Integer.MAX_VALUE) - 1) | i);
    }

    public static double ceil(double d) {
        if (d != d) {
            return d;
        }
        double floor = floor(d);
        if (floor == d) {
            return floor;
        }
        double d2 = floor + 1.0d;
        return d2 == 0.0d ? d * d2 : d2;
    }

    public static double rint(double d) {
        double floor = floor(d);
        double d2 = d - floor;
        if (d2 <= F_1_2) {
            return (d2 >= F_1_2 && (((long) floor) & 1) != 0) ? floor + 1.0d : floor;
        }
        if (floor == -1.0d) {
            return -0.0d;
        }
        return floor + 1.0d;
    }

    public static long round(double d) {
        return (long) floor(d + F_1_2);
    }

    public static int round(float f) {
        return (int) floor((double) (f + 0.5f));
    }

    public static float min(float f, float f2) {
        int i = (f > f2 ? 1 : (f == f2 ? 0 : -1));
        if (i > 0) {
            return f2;
        }
        if (f < f2) {
            return f;
        }
        if (i != 0) {
            return Float.NaN;
        }
        return Float.floatToRawIntBits(f) == Integer.MIN_VALUE ? f : f2;
    }

    public static double min(double d, double d2) {
        int i = (d > d2 ? 1 : (d == d2 ? 0 : -1));
        if (i > 0) {
            return d2;
        }
        if (d < d2) {
            return d;
        }
        if (i != 0) {
            return Double.NaN;
        }
        return Double.doubleToRawLongBits(d) == Long.MIN_VALUE ? d : d2;
    }

    public static float max(float f, float f2) {
        int i = (f > f2 ? 1 : (f == f2 ? 0 : -1));
        if (i > 0) {
            return f;
        }
        if (f < f2) {
            return f2;
        }
        if (i != 0) {
            return Float.NaN;
        }
        return Float.floatToRawIntBits(f) == Integer.MIN_VALUE ? f2 : f;
    }

    public static double max(double d, double d2) {
        int i = (d > d2 ? 1 : (d == d2 ? 0 : -1));
        if (i > 0) {
            return d;
        }
        if (d < d2) {
            return d2;
        }
        if (i != 0) {
            return Double.NaN;
        }
        return Double.doubleToRawLongBits(d) == Long.MIN_VALUE ? d2 : d;
    }

    public static double hypot(double d, double d2) {
        if (Double.isInfinite(d) || Double.isInfinite(d2)) {
            return Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(d) || Double.isNaN(d2)) {
            return Double.NaN;
        }
        int exponent = getExponent(d);
        int exponent2 = getExponent(d2);
        if (exponent > exponent2 + 27) {
            return abs(d);
        }
        if (exponent2 > exponent + 27) {
            return abs(d2);
        }
        int i = (exponent + exponent2) / 2;
        int i2 = -i;
        double scalb = scalb(d, i2);
        double scalb2 = scalb(d2, i2);
        return scalb(sqrt((scalb * scalb) + (scalb2 * scalb2)), i);
    }

    public static double IEEEremainder(double d, double d2) {
        return StrictMath.IEEEremainder(d, d2);
    }

    public static int toIntExact(long j) throws MathArithmeticException {
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]);
    }

    public static int incrementExact(int i) throws MathArithmeticException {
        if (i != Integer.MAX_VALUE) {
            return i + 1;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, Integer.valueOf(i), 1);
    }

    public static long incrementExact(long j) throws MathArithmeticException {
        if (j != Long.MAX_VALUE) {
            return j + 1;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, Long.valueOf(j), 1);
    }

    public static int decrementExact(int i) throws MathArithmeticException {
        if (i != Integer.MIN_VALUE) {
            return i - 1;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, Integer.valueOf(i), 1);
    }

    public static long decrementExact(long j) throws MathArithmeticException {
        if (j != Long.MIN_VALUE) {
            return j - 1;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, Long.valueOf(j), 1);
    }

    public static int addExact(int i, int i2) throws MathArithmeticException {
        int i3 = i + i2;
        if ((i ^ i2) < 0 || (i3 ^ i2) >= 0) {
            return i3;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static long addExact(long j, long j2) throws MathArithmeticException {
        long j3 = j + j2;
        if ((j ^ j2) < 0 || (j3 ^ j2) >= 0) {
            return j3;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, Long.valueOf(j), Long.valueOf(j2));
    }

    public static int subtractExact(int i, int i2) {
        int i3 = i - i2;
        if ((i ^ i2) >= 0 || (i3 ^ i2) < 0) {
            return i3;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static long subtractExact(long j, long j2) {
        long j3 = j - j2;
        if ((j ^ j2) >= 0 || (j3 ^ j2) < 0) {
            return j3;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, Long.valueOf(j), Long.valueOf(j2));
    }

    public static int multiplyExact(int i, int i2) {
        if ((i2 <= 0 || (i <= Integer.MAX_VALUE / i2 && i >= Integer.MIN_VALUE / i2)) && ((i2 >= -1 || (i <= Integer.MIN_VALUE / i2 && i >= Integer.MAX_VALUE / i2)) && (i2 != -1 || i != Integer.MIN_VALUE))) {
            return i * i2;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_MULTIPLICATION, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static long multiplyExact(long j, long j2) {
        int i;
        if ((j2 <= 0 || (j <= Long.MAX_VALUE / j2 && j >= Long.MIN_VALUE / j2)) && ((j2 >= -1 || (j <= Long.MIN_VALUE / j2 && j >= Long.MAX_VALUE / j2)) && (i != 0 || j != Long.MIN_VALUE))) {
            return j * j2;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_MULTIPLICATION, Long.valueOf(j), Long.valueOf(j2));
    }

    public static int floorDiv(int i, int i2) throws MathArithmeticException {
        if (i2 != 0) {
            int i3 = i % i2;
            if ((i ^ i2) >= 0 || i3 == 0) {
                return i / i2;
            }
            return (i / i2) - 1;
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
    }

    public static long floorDiv(long j, long j2) throws MathArithmeticException {
        if (j2 != 0) {
            long j3 = j % j2;
            if ((j ^ j2) >= 0 || j3 == 0) {
                return j / j2;
            }
            return (j / j2) - 1;
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
    }

    public static int floorMod(int i, int i2) throws MathArithmeticException {
        if (i2 != 0) {
            int i3 = i % i2;
            return ((i ^ i2) >= 0 || i3 == 0) ? i3 : i2 + i3;
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
    }

    public static long floorMod(long j, long j2) {
        if (j2 != 0) {
            long j3 = j % j2;
            return ((j ^ j2) >= 0 || j3 == 0) ? j3 : j2 + j3;
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
    }

    public static double copySign(double d, double d2) {
        return (Double.doubleToRawLongBits(d2) ^ Double.doubleToRawLongBits(d)) >= 0 ? d : -d;
    }

    public static float copySign(float f, float f2) {
        return (Float.floatToRawIntBits(f2) ^ Float.floatToRawIntBits(f)) >= 0 ? f : -f;
    }

    public static int getExponent(double d) {
        return ((int) ((Double.doubleToRawLongBits(d) >>> 52) & 2047)) - 1023;
    }

    public static int getExponent(float f) {
        return ((Float.floatToRawIntBits(f) >>> 23) & 255) - 127;
    }

    public static void main(String[] strArr) {
        PrintStream printStream = System.out;
        FastMathCalc.printarray(printStream, "EXP_INT_TABLE_A", 1500, ExpIntTable.EXP_INT_TABLE_A);
        FastMathCalc.printarray(printStream, "EXP_INT_TABLE_B", 1500, ExpIntTable.EXP_INT_TABLE_B);
        FastMathCalc.printarray(printStream, "EXP_FRAC_TABLE_A", 1025, ExpFracTable.EXP_FRAC_TABLE_A);
        FastMathCalc.printarray(printStream, "EXP_FRAC_TABLE_B", 1025, ExpFracTable.EXP_FRAC_TABLE_B);
        FastMathCalc.printarray(printStream, "LN_MANT", 1024, lnMant.LN_MANT);
        FastMathCalc.printarray(printStream, "SINE_TABLE_A", 14, SINE_TABLE_A);
        FastMathCalc.printarray(printStream, "SINE_TABLE_B", 14, SINE_TABLE_B);
        FastMathCalc.printarray(printStream, "COSINE_TABLE_A", 14, COSINE_TABLE_A);
        FastMathCalc.printarray(printStream, "COSINE_TABLE_B", 14, COSINE_TABLE_B);
        FastMathCalc.printarray(printStream, "TANGENT_TABLE_A", 14, TANGENT_TABLE_A);
        FastMathCalc.printarray(printStream, "TANGENT_TABLE_B", 14, TANGENT_TABLE_B);
    }

    private static class ExpIntTable {
        /* access modifiers changed from: private */
        public static final double[] EXP_INT_TABLE_A = FastMathLiteralArrays.loadExpIntA();
        /* access modifiers changed from: private */
        public static final double[] EXP_INT_TABLE_B = FastMathLiteralArrays.loadExpIntB();

        private ExpIntTable() {
        }
    }

    private static class ExpFracTable {
        /* access modifiers changed from: private */
        public static final double[] EXP_FRAC_TABLE_A = FastMathLiteralArrays.loadExpFracA();
        /* access modifiers changed from: private */
        public static final double[] EXP_FRAC_TABLE_B = FastMathLiteralArrays.loadExpFracB();

        private ExpFracTable() {
        }
    }

    private static class lnMant {
        /* access modifiers changed from: private */
        public static final double[][] LN_MANT = FastMathLiteralArrays.loadLnMant();

        private lnMant() {
        }
    }

    private static class CodyWaite {
        private final int finalK;
        private final double finalRemA;
        private final double finalRemB;

        CodyWaite(double d) {
            int i = (int) (0.6366197723675814d * d);
            while (true) {
                double d2 = (double) (-i);
                double d3 = 1.570796251296997d * d2;
                double d4 = d + d3;
                double d5 = 7.549789948768648E-8d * d2;
                double d6 = d5 + d4;
                double d7 = (-((d4 - d) - d3)) + (-((d6 - d4) - d5));
                double d8 = d2 * 6.123233995736766E-17d;
                double d9 = d8 + d6;
                double d10 = d7 + (-((d9 - d6) - d8));
                if (d9 > 0.0d) {
                    this.finalK = i;
                    this.finalRemA = d9;
                    this.finalRemB = d10;
                    return;
                }
                i--;
            }
        }

        /* access modifiers changed from: package-private */
        public int getK() {
            return this.finalK;
        }

        /* access modifiers changed from: package-private */
        public double getRemA() {
            return this.finalRemA;
        }

        /* access modifiers changed from: package-private */
        public double getRemB() {
            return this.finalRemB;
        }
    }
}
