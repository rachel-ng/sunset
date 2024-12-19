package org.apache.commons.math3.special;

import org.apache.commons.math3.util.FastMath;

public class Erf {
    private static final double X_CRIT = 0.4769362762044697d;

    private Erf() {
    }

    public static double erf(double d) {
        if (FastMath.abs(d) > 40.0d) {
            return d > 0.0d ? 1.0d : -1.0d;
        }
        double regularizedGammaP = Gamma.regularizedGammaP(0.5d, d * d, 1.0E-15d, 10000);
        return d < 0.0d ? -regularizedGammaP : regularizedGammaP;
    }

    public static double erfc(double d) {
        if (FastMath.abs(d) > 40.0d) {
            return d > 0.0d ? 0.0d : 2.0d;
        }
        double regularizedGammaQ = Gamma.regularizedGammaQ(0.5d, d * d, 1.0E-15d, 10000);
        return d < 0.0d ? 2.0d - regularizedGammaQ : regularizedGammaQ;
    }

    public static double erf(double d, double d2) {
        double d3;
        double d4;
        if (d > d2) {
            return -erf(d2, d);
        }
        if (d < -0.4769362762044697d) {
            if (d2 < 0.0d) {
                d3 = erfc(-d2);
                d4 = erfc(-d);
                return d3 - d4;
            }
        } else if (d2 > X_CRIT && d > 0.0d) {
            return erfc(d) - erfc(d2);
        }
        d3 = erf(d2);
        d4 = erf(d);
        return d3 - d4;
    }

    public static double erfInv(double d) {
        double d2;
        double sqrt;
        double d3;
        double d4;
        double d5 = -FastMath.log((1.0d - d) * (1.0d + d));
        if (d5 < 6.25d) {
            sqrt = d5 - 3.125d;
            d3 = (((((((((((((((((((((((((((((((((((((((((-3.64441206401782E-21d * sqrt) - 2.667043662401199E19d) * sqrt) + 1.28584807152564E-18d) * sqrt) + 1.1157877678025181E-17d) * sqrt) - 3.2411248538046888E16d) * sqrt) + 2.0972767875968562E-17d) * sqrt) + 6.637638134358324E-15d) * sqrt) - 1.1072003390794989E14d) * sqrt) - 5.5095087562318234E13d) * sqrt) + 2.6335093153082323E-12d) * sqrt) - 3.344470457369893E11d) * sqrt) - 7.829062832735399E10d) * sqrt) + 1.0512122733215323E-9d) * sqrt) - 1.0179197164351387E9d) * sqrt) - 1.4081056938032028E8d) * sqrt) + 4.2347877827932404E-7d) * sqrt) - 3288757.474276467d) * sqrt) - 309432.1293670094d) * sqrt) + 1.8673420803405714E-4d) * sqrt) - 6074.5367962725795d) * sqrt) - 745.1546915399075d) * sqrt) + 0.24015818242558962d;
            d4 = 1.6536545626831027d;
        } else if (d5 < 16.0d) {
            sqrt = FastMath.sqrt(d5) - 3.25d;
            d3 = (((((((((((((((((((((((((((((((((2.2137376921775787E-9d * sqrt) + 9.075656193888539E-8d) * sqrt) - 1.5483997379245123E7d) * sqrt) + 1.8239629214389228E-8d) * sqrt) + 1.5027403968909828E-6d) * sqrt) - 1021202.2477164116d) * sqrt) + 2.9234449089955446E-6d) * sqrt) + 1.2475304481671779E-5d) * sqrt) - 94992.87695073357d) * sqrt) + 6.828485145957318E-5d) * sqrt) + 2.4031110387097894E-5d) * sqrt) - 12662.917665536219d) * sqrt) + 9.532893797373805E-4d) * sqrt) - 2603.429541134195d) * sqrt) + 0.002491442096107851d) * sqrt) - 1105.2863939835377d) * sqrt) + 0.005370914553590064d) * sqrt) + 1.0052589676941592d;
            d4 = 3.0838856104922208d;
        } else if (!Double.isInfinite(d5)) {
            sqrt = FastMath.sqrt(d5) - 5.0d;
            d3 = (((((((((((((((((((((((((((((-2.7109920616438573E-11d * sqrt) - 1.6341149667559008E10d) * sqrt) + 1.5076572693500548E-9d) * sqrt) - 1.0644932113891793E9d) * sqrt) + 7.61570120807834E-9d) * sqrt) - 2.6790524331303596E8d) * sqrt) + 2.914795345090108E-8d) * sqrt) - 6.254483001581527E7d) * sqrt) + 2.2900482228026655E-7d) * sqrt) - 4107863.88563695d) * sqrt) + 4.526062597223154E-6d) * sqrt) - 224150.81341922528d) * sqrt) + 7.599527703001776E-5d) * sqrt) - 20291.145935925353d) * sqrt) - 30533.408263202306d) * sqrt) + 1.0103004648645344d;
            d4 = 4.849906401408584d;
        } else {
            d2 = Double.POSITIVE_INFINITY;
            return d2 * d;
        }
        d2 = (d3 * sqrt) + d4;
        return d2 * d;
    }

    public static double erfcInv(double d) {
        return erfInv(1.0d - d);
    }
}
