package pl.edu.icm.jlargearrays;

import java.lang.reflect.Field;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.math3.util.FastMath;
import sun.misc.Unsafe;

public class LargeArrayUtils {
    public static final Unsafe UNSAFE;

    static {
        Object obj = null;
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(cls);
            e = null;
            obj = obj2;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            e = e;
        }
        Unsafe unsafe = (Unsafe) obj;
        UNSAFE = unsafe;
        if (unsafe == null) {
            throw new Error("Could not obtain access to sun.misc.Unsafe", e);
        }
    }

    private LargeArrayUtils() {
    }

    public static void arraycopy(LargeArray largeArray, long j, LargeArray largeArray2, long j2, long j3) {
        if (largeArray.getType() == largeArray2.getType()) {
            switch (AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[largeArray.getType().ordinal()]) {
                case 1:
                    arraycopy((LogicLargeArray) largeArray, j, (LogicLargeArray) largeArray2, j2, j3);
                    return;
                case 2:
                    arraycopy((UnsignedByteLargeArray) largeArray, j, (UnsignedByteLargeArray) largeArray2, j2, j3);
                    return;
                case 3:
                    arraycopy((ShortLargeArray) largeArray, j, (ShortLargeArray) largeArray2, j2, j3);
                    return;
                case 4:
                    arraycopy((IntLargeArray) largeArray, j, (IntLargeArray) largeArray2, j2, j3);
                    return;
                case 5:
                    arraycopy((LongLargeArray) largeArray, j, (LongLargeArray) largeArray2, j2, j3);
                    return;
                case 6:
                    arraycopy((FloatLargeArray) largeArray, j, (FloatLargeArray) largeArray2, j2, j3);
                    return;
                case 7:
                    arraycopy((DoubleLargeArray) largeArray, j, (DoubleLargeArray) largeArray2, j2, j3);
                    return;
                case 8:
                    arraycopy((ComplexFloatLargeArray) largeArray, j, (ComplexFloatLargeArray) largeArray2, j2, j3);
                    return;
                case 9:
                    arraycopy((ComplexDoubleLargeArray) largeArray, j, (ComplexDoubleLargeArray) largeArray2, j2, j3);
                    return;
                case 10:
                    arraycopy((StringLargeArray) largeArray, j, (StringLargeArray) largeArray2, j2, j3);
                    return;
                case 11:
                    arraycopy((ObjectLargeArray) largeArray, j, (ObjectLargeArray) largeArray2, j2, j3);
                    return;
                default:
                    throw new IllegalArgumentException("Invalid array type.");
            }
        } else {
            throw new IllegalArgumentException("The type of source array is different than the type of destimation array.");
        }
    }

    /* renamed from: pl.edu.icm.jlargearrays.LargeArrayUtils$28  reason: invalid class name */
    static /* synthetic */ class AnonymousClass28 {
        static final /* synthetic */ int[] $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                pl.edu.icm.jlargearrays.LargeArrayType[] r0 = pl.edu.icm.jlargearrays.LargeArrayType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType = r0
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.LOGIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x001d }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.BYTE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x0028 }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.SHORT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x0033 }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.INT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x003e }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.LONG     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x0049 }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.FLOAT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x0054 }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x0060 }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x006c }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x0078 }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.STRING     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x0084 }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.OBJECT     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x0090 }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.UNSIGNED_BYTE     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LargeArrayUtils.AnonymousClass28.<clinit>():void");
        }
    }

    public static void arraycopy(Object obj, long j, LargeArray largeArray, long j2, long j3) {
        switch (AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[largeArray.getType().ordinal()]) {
            case 1:
                arraycopy((boolean[]) obj, (int) j, (LogicLargeArray) largeArray, j2, j3);
                return;
            case 2:
                arraycopy((byte[]) obj, (int) j, (ByteLargeArray) largeArray, j2, j3);
                return;
            case 3:
                arraycopy((short[]) obj, (int) j, (ShortLargeArray) largeArray, j2, j3);
                return;
            case 4:
                arraycopy((int[]) obj, (int) j, (IntLargeArray) largeArray, j2, j3);
                return;
            case 5:
                arraycopy((long[]) obj, (int) j, (LongLargeArray) largeArray, j2, j3);
                return;
            case 6:
                arraycopy((float[]) obj, (int) j, (FloatLargeArray) largeArray, j2, j3);
                return;
            case 7:
                arraycopy((double[]) obj, (int) j, (DoubleLargeArray) largeArray, j2, j3);
                return;
            case 8:
                arraycopy((float[]) obj, (int) j, (ComplexFloatLargeArray) largeArray, j2, j3);
                return;
            case 9:
                arraycopy((double[]) obj, (int) j, (ComplexDoubleLargeArray) largeArray, j2, j3);
                return;
            case 10:
                arraycopy((String[]) obj, (int) j, (StringLargeArray) largeArray, j2, j3);
                return;
            case 11:
                arraycopy((Object[]) obj, (int) j, (ObjectLargeArray) largeArray, j2, j3);
                return;
            case 12:
                if (obj.getClass().getComponentType() == Byte.TYPE) {
                    arraycopy((byte[]) obj, (int) j, (UnsignedByteLargeArray) largeArray, j2, j3);
                    return;
                } else {
                    arraycopy((short[]) obj, (int) j, (UnsignedByteLargeArray) largeArray, j2, j3);
                    return;
                }
            default:
                throw new IllegalArgumentException("Invalid array type.");
        }
    }

    public static void arraycopy(LogicLargeArray logicLargeArray, long j, LogicLargeArray logicLargeArray2, long j2, long j3) {
        LogicLargeArray logicLargeArray3 = logicLargeArray;
        LogicLargeArray logicLargeArray4 = logicLargeArray2;
        long j4 = j3;
        if (j < 0 || j >= logicLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        } else if (j2 < 0 || j2 >= logicLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j4 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!logicLargeArray2.isConstant()) {
            int min = (int) FastMath.min(j4, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j4 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j5 = j;
                long j6 = j2;
                while (j5 < j + j4) {
                    logicLargeArray4.setByte(j6, logicLargeArray3.getByte(j5));
                    j5++;
                    j6++;
                }
                return;
            }
            long j7 = j4 / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j8 = ((long) i) * j7;
                final long j9 = i == min + -1 ? j4 : j8 + j7;
                final LogicLargeArray logicLargeArray5 = logicLargeArray2;
                final long j10 = j2;
                final LogicLargeArray logicLargeArray6 = logicLargeArray;
                Future[] futureArr2 = futureArr;
                int i2 = i;
                final long j11 = j;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j8; j < j9; j++) {
                            logicLargeArray5.setByte(j10 + j, logicLargeArray6.getByte(j11 + j));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j12 = j;
                long j13 = j2;
                while (j12 < j + j4) {
                    logicLargeArray4.setByte(j13, logicLargeArray3.getByte(j12));
                    j12++;
                    j13++;
                }
            } catch (ExecutionException unused2) {
                long j14 = j;
                long j15 = j2;
                while (j14 < j + j4) {
                    logicLargeArray4.setByte(j15, logicLargeArray3.getByte(j14));
                    j14++;
                    j15++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(boolean[] zArr, int i, LogicLargeArray logicLargeArray, long j, long j2) {
        boolean[] zArr2 = zArr;
        int i2 = i;
        LogicLargeArray logicLargeArray2 = logicLargeArray;
        long j3 = j2;
        if (i2 < 0 || i2 >= zArr2.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        } else if (j < 0 || j >= logicLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!logicLargeArray.isConstant()) {
            int min = (int) FastMath.min(j3, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j4 = j;
                while (j4 < j + j3) {
                    logicLargeArray2.setBoolean(j4, zArr2[i2]);
                    j4++;
                    i2++;
                }
                return;
            }
            long j5 = j3 / ((long) min);
            Future[] futureArr = new Future[min];
            int i3 = 0;
            while (i3 < min) {
                final long j6 = ((long) i3) * j5;
                final long j7 = i3 == min + -1 ? j3 : j6 + j5;
                final LogicLargeArray logicLargeArray3 = logicLargeArray;
                final long j8 = j;
                int i4 = i3;
                final boolean[] zArr3 = zArr;
                Future[] futureArr2 = futureArr;
                final int i5 = i;
                futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j6; j < j7; j++) {
                            logicLargeArray3.setBoolean(j8 + j, zArr3[i5 + ((int) j)]);
                        }
                    }
                });
                i3 = i4 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j9 = j;
                while (j9 < j + j3) {
                    logicLargeArray2.setBoolean(j9, zArr2[i2]);
                    j9++;
                    i2++;
                }
            } catch (ExecutionException unused2) {
                long j10 = j;
                while (j10 < j + j3) {
                    logicLargeArray2.setBoolean(j10, zArr2[i2]);
                    j10++;
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(ByteLargeArray byteLargeArray, long j, ByteLargeArray byteLargeArray2, long j2, long j3) {
        ByteLargeArray byteLargeArray3 = byteLargeArray;
        ByteLargeArray byteLargeArray4 = byteLargeArray2;
        long j4 = j3;
        if (j < 0 || j >= byteLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        } else if (j2 < 0 || j2 >= byteLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j4 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!byteLargeArray2.isConstant()) {
            int min = (int) FastMath.min(j4, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j4 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j5 = j;
                long j6 = j2;
                while (j5 < j + j4) {
                    byteLargeArray4.setByte(j6, byteLargeArray3.getByte(j5));
                    j5++;
                    j6++;
                }
                return;
            }
            long j7 = j4 / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j8 = ((long) i) * j7;
                final long j9 = i == min + -1 ? j4 : j8 + j7;
                final ByteLargeArray byteLargeArray5 = byteLargeArray2;
                final long j10 = j2;
                final ByteLargeArray byteLargeArray6 = byteLargeArray;
                Future[] futureArr2 = futureArr;
                int i2 = i;
                final long j11 = j;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j8; j < j9; j++) {
                            byteLargeArray5.setByte(j10 + j, byteLargeArray6.getByte(j11 + j));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j12 = j;
                long j13 = j2;
                while (j12 < j + j4) {
                    byteLargeArray4.setByte(j13, byteLargeArray3.getByte(j12));
                    j12++;
                    j13++;
                }
            } catch (ExecutionException unused2) {
                long j14 = j;
                long j15 = j2;
                while (j14 < j + j4) {
                    byteLargeArray4.setByte(j15, byteLargeArray3.getByte(j14));
                    j14++;
                    j15++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(byte[] bArr, int i, ByteLargeArray byteLargeArray, long j, long j2) {
        byte[] bArr2 = bArr;
        int i2 = i;
        ByteLargeArray byteLargeArray2 = byteLargeArray;
        long j3 = j2;
        if (i2 < 0 || i2 >= bArr2.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        } else if (j < 0 || j >= byteLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!byteLargeArray.isConstant()) {
            int min = (int) FastMath.min(j3, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j4 = j;
                while (j4 < j + j3) {
                    byteLargeArray2.setByte(j4, bArr2[i2]);
                    j4++;
                    i2++;
                }
                return;
            }
            long j5 = j3 / ((long) min);
            Future[] futureArr = new Future[min];
            int i3 = 0;
            while (i3 < min) {
                final long j6 = ((long) i3) * j5;
                final long j7 = i3 == min + -1 ? j3 : j6 + j5;
                final ByteLargeArray byteLargeArray3 = byteLargeArray;
                final long j8 = j;
                int i4 = i3;
                final byte[] bArr3 = bArr;
                Future[] futureArr2 = futureArr;
                final int i5 = i;
                futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j6; j < j7; j++) {
                            byteLargeArray3.setByte(j8 + j, bArr3[i5 + ((int) j)]);
                        }
                    }
                });
                i3 = i4 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j9 = j;
                while (j9 < j + j3) {
                    byteLargeArray2.setByte(j9, bArr2[i2]);
                    j9++;
                    i2++;
                }
            } catch (ExecutionException unused2) {
                long j10 = j;
                while (j10 < j + j3) {
                    byteLargeArray2.setByte(j10, bArr2[i2]);
                    j10++;
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(UnsignedByteLargeArray unsignedByteLargeArray, long j, UnsignedByteLargeArray unsignedByteLargeArray2, long j2, long j3) {
        UnsignedByteLargeArray unsignedByteLargeArray3 = unsignedByteLargeArray;
        UnsignedByteLargeArray unsignedByteLargeArray4 = unsignedByteLargeArray2;
        long j4 = j3;
        if (j < 0 || j >= unsignedByteLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        } else if (j2 < 0 || j2 >= unsignedByteLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j4 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!unsignedByteLargeArray2.isConstant()) {
            int min = (int) FastMath.min(j4, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j4 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j5 = j;
                long j6 = j2;
                while (j5 < j + j4) {
                    unsignedByteLargeArray4.setByte(j6, unsignedByteLargeArray3.getByte(j5));
                    j5++;
                    j6++;
                }
                return;
            }
            long j7 = j4 / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j8 = ((long) i) * j7;
                final long j9 = i == min + -1 ? j4 : j8 + j7;
                final UnsignedByteLargeArray unsignedByteLargeArray5 = unsignedByteLargeArray2;
                final long j10 = j2;
                final UnsignedByteLargeArray unsignedByteLargeArray6 = unsignedByteLargeArray;
                Future[] futureArr2 = futureArr;
                int i2 = i;
                final long j11 = j;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j8; j < j9; j++) {
                            unsignedByteLargeArray5.setByte(j10 + j, unsignedByteLargeArray6.getByte(j11 + j));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j12 = j;
                long j13 = j2;
                while (j12 < j + j4) {
                    unsignedByteLargeArray4.setByte(j13, unsignedByteLargeArray3.getByte(j12));
                    j12++;
                    j13++;
                }
            } catch (ExecutionException unused2) {
                long j14 = j;
                long j15 = j2;
                while (j14 < j + j4) {
                    unsignedByteLargeArray4.setByte(j15, unsignedByteLargeArray3.getByte(j14));
                    j14++;
                    j15++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(byte[] bArr, int i, UnsignedByteLargeArray unsignedByteLargeArray, long j, long j2) {
        byte[] bArr2 = bArr;
        int i2 = i;
        UnsignedByteLargeArray unsignedByteLargeArray2 = unsignedByteLargeArray;
        long j3 = j2;
        if (i2 < 0 || i2 >= bArr2.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        } else if (j < 0 || j >= unsignedByteLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!unsignedByteLargeArray.isConstant()) {
            int min = (int) FastMath.min(j3, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j4 = j;
                while (j4 < j + j3) {
                    unsignedByteLargeArray2.setByte(j4, bArr2[i2]);
                    j4++;
                    i2++;
                }
                return;
            }
            long j5 = j3 / ((long) min);
            Future[] futureArr = new Future[min];
            int i3 = 0;
            while (i3 < min) {
                final long j6 = ((long) i3) * j5;
                final long j7 = i3 == min + -1 ? j3 : j6 + j5;
                final UnsignedByteLargeArray unsignedByteLargeArray3 = unsignedByteLargeArray;
                final long j8 = j;
                int i4 = i3;
                final byte[] bArr3 = bArr;
                Future[] futureArr2 = futureArr;
                final int i5 = i;
                futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j6; j < j7; j++) {
                            unsignedByteLargeArray3.setByte(j8 + j, bArr3[i5 + ((int) j)]);
                        }
                    }
                });
                i3 = i4 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j9 = j;
                while (j9 < j + j3) {
                    unsignedByteLargeArray2.setByte(j9, bArr2[i2]);
                    j9++;
                    i2++;
                }
            } catch (ExecutionException unused2) {
                long j10 = j;
                while (j10 < j + j3) {
                    unsignedByteLargeArray2.setByte(j10, bArr2[i2]);
                    j10++;
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(short[] sArr, int i, UnsignedByteLargeArray unsignedByteLargeArray, long j, long j2) {
        short[] sArr2 = sArr;
        int i2 = i;
        UnsignedByteLargeArray unsignedByteLargeArray2 = unsignedByteLargeArray;
        long j3 = j2;
        if (i2 < 0 || i2 >= sArr2.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        } else if (j < 0 || j >= unsignedByteLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!unsignedByteLargeArray.isConstant()) {
            int min = (int) FastMath.min(j3, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j4 = j;
                while (j4 < j + j3) {
                    unsignedByteLargeArray2.setUnsignedByte(j4, sArr2[i2]);
                    j4++;
                    i2++;
                }
                return;
            }
            long j5 = j3 / ((long) min);
            Future[] futureArr = new Future[min];
            int i3 = 0;
            while (i3 < min) {
                final long j6 = ((long) i3) * j5;
                final long j7 = i3 == min + -1 ? j3 : j6 + j5;
                final UnsignedByteLargeArray unsignedByteLargeArray3 = unsignedByteLargeArray;
                final long j8 = j;
                int i4 = i3;
                final short[] sArr3 = sArr;
                Future[] futureArr2 = futureArr;
                final int i5 = i;
                futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j6; j < j7; j++) {
                            unsignedByteLargeArray3.setUnsignedByte(j8 + j, sArr3[i5 + ((int) j)]);
                        }
                    }
                });
                i3 = i4 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j9 = j;
                while (j9 < j + j3) {
                    unsignedByteLargeArray2.setUnsignedByte(j9, sArr2[i2]);
                    j9++;
                    i2++;
                }
            } catch (ExecutionException unused2) {
                long j10 = j;
                while (j10 < j + j3) {
                    unsignedByteLargeArray2.setUnsignedByte(j10, sArr2[i2]);
                    j10++;
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(ShortLargeArray shortLargeArray, long j, ShortLargeArray shortLargeArray2, long j2, long j3) {
        ShortLargeArray shortLargeArray3 = shortLargeArray;
        ShortLargeArray shortLargeArray4 = shortLargeArray2;
        long j4 = j3;
        if (j < 0 || j >= shortLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        } else if (j2 < 0 || j2 >= shortLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j4 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!shortLargeArray2.isConstant()) {
            int min = (int) FastMath.min(j4, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j4 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j5 = j;
                long j6 = j2;
                while (j5 < j + j4) {
                    shortLargeArray4.setShort(j6, shortLargeArray3.getShort(j5));
                    j5++;
                    j6++;
                }
                return;
            }
            long j7 = j4 / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j8 = ((long) i) * j7;
                final long j9 = i == min + -1 ? j4 : j8 + j7;
                final ShortLargeArray shortLargeArray5 = shortLargeArray2;
                final long j10 = j2;
                final ShortLargeArray shortLargeArray6 = shortLargeArray;
                Future[] futureArr2 = futureArr;
                int i2 = i;
                final long j11 = j;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j8; j < j9; j++) {
                            shortLargeArray5.setShort(j10 + j, shortLargeArray6.getShort(j11 + j));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j12 = j;
                long j13 = j2;
                while (j12 < j + j4) {
                    shortLargeArray4.setShort(j13, shortLargeArray3.getShort(j12));
                    j12++;
                    j13++;
                }
            } catch (ExecutionException unused2) {
                long j14 = j;
                long j15 = j2;
                while (j14 < j + j4) {
                    shortLargeArray4.setShort(j15, shortLargeArray3.getShort(j14));
                    j14++;
                    j15++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(short[] sArr, int i, ShortLargeArray shortLargeArray, long j, long j2) {
        short[] sArr2 = sArr;
        int i2 = i;
        ShortLargeArray shortLargeArray2 = shortLargeArray;
        long j3 = j2;
        if (i2 < 0 || i2 >= sArr2.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        } else if (j < 0 || j >= shortLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!shortLargeArray.isConstant()) {
            int min = (int) FastMath.min(j3, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j4 = j;
                while (j4 < j + j3) {
                    shortLargeArray2.setShort(j4, sArr2[i2]);
                    j4++;
                    i2++;
                }
                return;
            }
            long j5 = j3 / ((long) min);
            Future[] futureArr = new Future[min];
            int i3 = 0;
            while (i3 < min) {
                final long j6 = ((long) i3) * j5;
                final long j7 = i3 == min + -1 ? j3 : j6 + j5;
                final ShortLargeArray shortLargeArray3 = shortLargeArray;
                final long j8 = j;
                int i4 = i3;
                final short[] sArr3 = sArr;
                Future[] futureArr2 = futureArr;
                final int i5 = i;
                futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j6; j < j7; j++) {
                            shortLargeArray3.setShort(j8 + j, sArr3[i5 + ((int) j)]);
                        }
                    }
                });
                i3 = i4 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j9 = j;
                while (j9 < j + j3) {
                    shortLargeArray2.setShort(j9, sArr2[i2]);
                    j9++;
                    i2++;
                }
            } catch (ExecutionException unused2) {
                long j10 = j;
                while (j10 < j + j3) {
                    shortLargeArray2.setShort(j10, sArr2[i2]);
                    j10++;
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(IntLargeArray intLargeArray, long j, IntLargeArray intLargeArray2, long j2, long j3) {
        IntLargeArray intLargeArray3 = intLargeArray;
        IntLargeArray intLargeArray4 = intLargeArray2;
        long j4 = j3;
        if (j < 0 || j >= intLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        } else if (j2 < 0 || j2 >= intLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j4 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!intLargeArray2.isConstant()) {
            int min = (int) FastMath.min(j4, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j4 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j5 = j;
                long j6 = j2;
                while (j5 < j + j4) {
                    intLargeArray4.setInt(j6, intLargeArray3.getInt(j5));
                    j5++;
                    j6++;
                }
                return;
            }
            long j7 = j4 / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j8 = ((long) i) * j7;
                final long j9 = i == min + -1 ? j4 : j8 + j7;
                final IntLargeArray intLargeArray5 = intLargeArray2;
                final long j10 = j2;
                final IntLargeArray intLargeArray6 = intLargeArray;
                Future[] futureArr2 = futureArr;
                int i2 = i;
                final long j11 = j;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j8; j < j9; j++) {
                            intLargeArray5.setInt(j10 + j, intLargeArray6.getInt(j11 + j));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j12 = j;
                long j13 = j2;
                while (j12 < j + j4) {
                    intLargeArray4.setInt(j13, intLargeArray3.getInt(j12));
                    j12++;
                    j13++;
                }
            } catch (ExecutionException unused2) {
                long j14 = j;
                long j15 = j2;
                while (j14 < j + j4) {
                    intLargeArray4.setInt(j15, intLargeArray3.getInt(j14));
                    j14++;
                    j15++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(int[] iArr, int i, IntLargeArray intLargeArray, long j, long j2) {
        int[] iArr2 = iArr;
        int i2 = i;
        IntLargeArray intLargeArray2 = intLargeArray;
        long j3 = j2;
        if (i2 < 0 || i2 >= iArr2.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        } else if (j < 0 || j >= intLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!intLargeArray.isConstant()) {
            int min = (int) FastMath.min(j3, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j4 = j;
                while (j4 < j + j3) {
                    intLargeArray2.setInt(j4, iArr2[i2]);
                    j4++;
                    i2++;
                }
                return;
            }
            long j5 = j3 / ((long) min);
            Future[] futureArr = new Future[min];
            int i3 = 0;
            while (i3 < min) {
                final long j6 = ((long) i3) * j5;
                final long j7 = i3 == min + -1 ? j3 : j6 + j5;
                final IntLargeArray intLargeArray3 = intLargeArray;
                final long j8 = j;
                int i4 = i3;
                final int[] iArr3 = iArr;
                Future[] futureArr2 = futureArr;
                final int i5 = i;
                futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j6; j < j7; j++) {
                            intLargeArray3.setInt(j8 + j, iArr3[i5 + ((int) j)]);
                        }
                    }
                });
                i3 = i4 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j9 = j;
                while (j9 < j + j3) {
                    intLargeArray2.setInt(j9, iArr2[i2]);
                    j9++;
                    i2++;
                }
            } catch (ExecutionException unused2) {
                long j10 = j;
                while (j10 < j + j3) {
                    intLargeArray2.setInt(j10, iArr2[i2]);
                    j10++;
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(LongLargeArray longLargeArray, long j, LongLargeArray longLargeArray2, long j2, long j3) {
        LongLargeArray longLargeArray3 = longLargeArray;
        LongLargeArray longLargeArray4 = longLargeArray2;
        long j4 = j3;
        if (j < 0 || j >= longLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        } else if (j2 < 0 || j2 >= longLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j4 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!longLargeArray2.isConstant()) {
            int min = (int) FastMath.min(j4, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j4 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j5 = j;
                long j6 = j2;
                while (j5 < j + j4) {
                    longLargeArray4.setLong(j6, longLargeArray3.getLong(j5));
                    j5++;
                    j6++;
                }
                return;
            }
            long j7 = j4 / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j8 = ((long) i) * j7;
                final long j9 = i == min + -1 ? j4 : j8 + j7;
                final LongLargeArray longLargeArray5 = longLargeArray2;
                final long j10 = j2;
                final LongLargeArray longLargeArray6 = longLargeArray;
                Future[] futureArr2 = futureArr;
                int i2 = i;
                final long j11 = j;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j8; j < j9; j++) {
                            longLargeArray5.setLong(j10 + j, longLargeArray6.getLong(j11 + j));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j12 = j;
                long j13 = j2;
                while (j12 < j + j4) {
                    longLargeArray4.setLong(j13, longLargeArray3.getLong(j12));
                    j12++;
                    j13++;
                }
            } catch (ExecutionException unused2) {
                long j14 = j;
                long j15 = j2;
                while (j14 < j + j4) {
                    longLargeArray4.setLong(j15, longLargeArray3.getLong(j14));
                    j14++;
                    j15++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(long[] jArr, int i, LongLargeArray longLargeArray, long j, long j2) {
        long[] jArr2 = jArr;
        int i2 = i;
        LongLargeArray longLargeArray2 = longLargeArray;
        long j3 = j2;
        if (i2 < 0 || i2 >= jArr2.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        } else if (j < 0 || j >= longLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!longLargeArray.isConstant()) {
            int min = (int) FastMath.min(j3, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j4 = j;
                while (j4 < j + j3) {
                    longLargeArray2.setLong(j4, jArr2[i2]);
                    j4++;
                    i2++;
                }
                return;
            }
            long j5 = j3 / ((long) min);
            Future[] futureArr = new Future[min];
            int i3 = 0;
            while (i3 < min) {
                final long j6 = ((long) i3) * j5;
                final long j7 = i3 == min + -1 ? j3 : j6 + j5;
                final LongLargeArray longLargeArray3 = longLargeArray;
                final long j8 = j;
                int i4 = i3;
                final long[] jArr3 = jArr;
                Future[] futureArr2 = futureArr;
                final int i5 = i;
                futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j6; j < j7; j++) {
                            longLargeArray3.setLong(j8 + j, jArr3[i5 + ((int) j)]);
                        }
                    }
                });
                i3 = i4 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j9 = j;
                while (j9 < j + j3) {
                    longLargeArray2.setLong(j9, jArr2[i2]);
                    j9++;
                    i2++;
                }
            } catch (ExecutionException unused2) {
                long j10 = j;
                while (j10 < j + j3) {
                    longLargeArray2.setLong(j10, jArr2[i2]);
                    j10++;
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(FloatLargeArray floatLargeArray, long j, FloatLargeArray floatLargeArray2, long j2, long j3) {
        FloatLargeArray floatLargeArray3 = floatLargeArray;
        FloatLargeArray floatLargeArray4 = floatLargeArray2;
        long j4 = j3;
        if (j < 0 || j >= floatLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        } else if (j2 < 0 || j2 >= floatLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j4 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!floatLargeArray2.isConstant()) {
            int min = (int) FastMath.min(j4, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j4 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j5 = j;
                long j6 = j2;
                while (j5 < j + j4) {
                    floatLargeArray4.setFloat(j6, floatLargeArray3.getFloat(j5));
                    j5++;
                    j6++;
                }
                return;
            }
            long j7 = j4 / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j8 = ((long) i) * j7;
                final long j9 = i == min + -1 ? j4 : j8 + j7;
                final FloatLargeArray floatLargeArray5 = floatLargeArray2;
                final long j10 = j2;
                final FloatLargeArray floatLargeArray6 = floatLargeArray;
                Future[] futureArr2 = futureArr;
                int i2 = i;
                final long j11 = j;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j8; j < j9; j++) {
                            floatLargeArray5.setFloat(j10 + j, floatLargeArray6.getFloat(j11 + j));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j12 = j;
                long j13 = j2;
                while (j12 < j + j4) {
                    floatLargeArray4.setFloat(j13, floatLargeArray3.getFloat(j12));
                    j12++;
                    j13++;
                }
            } catch (ExecutionException unused2) {
                long j14 = j;
                long j15 = j2;
                while (j14 < j + j4) {
                    floatLargeArray4.setFloat(j15, floatLargeArray3.getFloat(j14));
                    j14++;
                    j15++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(float[] fArr, int i, FloatLargeArray floatLargeArray, long j, long j2) {
        float[] fArr2 = fArr;
        int i2 = i;
        FloatLargeArray floatLargeArray2 = floatLargeArray;
        long j3 = j2;
        if (i2 < 0 || i2 >= fArr2.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        } else if (j < 0 || j >= floatLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!floatLargeArray.isConstant()) {
            int min = (int) FastMath.min(j3, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j4 = j;
                while (j4 < j + j3) {
                    floatLargeArray2.setFloat(j4, fArr2[i2]);
                    j4++;
                    i2++;
                }
                return;
            }
            long j5 = j3 / ((long) min);
            Future[] futureArr = new Future[min];
            int i3 = 0;
            while (i3 < min) {
                final long j6 = ((long) i3) * j5;
                final long j7 = i3 == min + -1 ? j3 : j6 + j5;
                final FloatLargeArray floatLargeArray3 = floatLargeArray;
                final long j8 = j;
                int i4 = i3;
                final float[] fArr3 = fArr;
                Future[] futureArr2 = futureArr;
                final int i5 = i;
                futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j6; j < j7; j++) {
                            floatLargeArray3.setFloat(j8 + j, fArr3[i5 + ((int) j)]);
                        }
                    }
                });
                i3 = i4 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j9 = j;
                while (j9 < j + j3) {
                    floatLargeArray2.setFloat(j9, fArr2[i2]);
                    j9++;
                    i2++;
                }
            } catch (ExecutionException unused2) {
                long j10 = j;
                while (j10 < j + j3) {
                    floatLargeArray2.setFloat(j10, fArr2[i2]);
                    j10++;
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(DoubleLargeArray doubleLargeArray, long j, DoubleLargeArray doubleLargeArray2, long j2, long j3) {
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j4 = j3;
        if (j < 0 || j >= doubleLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        } else if (j2 < 0 || j2 >= doubleLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j4 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!doubleLargeArray2.isConstant()) {
            int min = (int) FastMath.min(j4, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j4 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j5 = j;
                long j6 = j2;
                while (j5 < j + j4) {
                    doubleLargeArray4.setDouble(j6, doubleLargeArray3.getDouble(j5));
                    j5++;
                    j6++;
                }
                return;
            }
            long j7 = j4 / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j8 = ((long) i) * j7;
                final long j9 = i == min + -1 ? j4 : j8 + j7;
                final DoubleLargeArray doubleLargeArray5 = doubleLargeArray2;
                final long j10 = j2;
                final DoubleLargeArray doubleLargeArray6 = doubleLargeArray;
                Future[] futureArr2 = futureArr;
                int i2 = i;
                final long j11 = j;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j8; j < j9; j++) {
                            doubleLargeArray5.setDouble(j10 + j, doubleLargeArray6.getDouble(j11 + j));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j12 = j;
                long j13 = j2;
                while (j12 < j + j4) {
                    doubleLargeArray4.setDouble(j13, doubleLargeArray3.getDouble(j12));
                    j12++;
                    j13++;
                }
            } catch (ExecutionException unused2) {
                long j14 = j;
                long j15 = j2;
                while (j14 < j + j4) {
                    doubleLargeArray4.setDouble(j15, doubleLargeArray3.getDouble(j14));
                    j14++;
                    j15++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(double[] dArr, int i, DoubleLargeArray doubleLargeArray, long j, long j2) {
        double[] dArr2 = dArr;
        int i2 = i;
        DoubleLargeArray doubleLargeArray2 = doubleLargeArray;
        long j3 = j2;
        if (i2 < 0 || i2 >= dArr2.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        } else if (j < 0 || j >= doubleLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!doubleLargeArray.isConstant()) {
            int min = (int) FastMath.min(j3, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j4 = j;
                while (j4 < j + j3) {
                    doubleLargeArray2.setDouble(j4, dArr2[i2]);
                    j4++;
                    i2++;
                }
                return;
            }
            long j5 = j3 / ((long) min);
            Future[] futureArr = new Future[min];
            int i3 = 0;
            while (i3 < min) {
                final long j6 = ((long) i3) * j5;
                final long j7 = i3 == min + -1 ? j3 : j6 + j5;
                final DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
                final long j8 = j;
                int i4 = i3;
                final double[] dArr3 = dArr;
                Future[] futureArr2 = futureArr;
                final int i5 = i;
                futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j6; j < j7; j++) {
                            doubleLargeArray3.setDouble(j8 + j, dArr3[i5 + ((int) j)]);
                        }
                    }
                });
                i3 = i4 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j9 = j;
                while (j9 < j + j3) {
                    doubleLargeArray2.setDouble(j9, dArr2[i2]);
                    j9++;
                    i2++;
                }
            } catch (ExecutionException unused2) {
                long j10 = j;
                while (j10 < j + j3) {
                    doubleLargeArray2.setDouble(j10, dArr2[i2]);
                    j10++;
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(ComplexFloatLargeArray complexFloatLargeArray, long j, ComplexFloatLargeArray complexFloatLargeArray2, long j2, long j3) {
        ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray;
        ComplexFloatLargeArray complexFloatLargeArray4 = complexFloatLargeArray2;
        long j4 = j3;
        if (j < 0 || j >= complexFloatLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        } else if (j2 < 0 || j2 >= complexFloatLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j4 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!complexFloatLargeArray2.isConstant()) {
            int min = (int) FastMath.min(j4, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j4 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j5 = j;
                long j6 = j2;
                while (j5 < j + j4) {
                    complexFloatLargeArray4.setComplexFloat(j6, complexFloatLargeArray3.getComplexFloat(j5));
                    j5++;
                    j6++;
                }
                return;
            }
            long j7 = j4 / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j8 = ((long) i) * j7;
                final long j9 = i == min + -1 ? j4 : j8 + j7;
                final ComplexFloatLargeArray complexFloatLargeArray5 = complexFloatLargeArray2;
                final long j10 = j2;
                final ComplexFloatLargeArray complexFloatLargeArray6 = complexFloatLargeArray;
                Future[] futureArr2 = futureArr;
                int i2 = i;
                final long j11 = j;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j8; j < j9; j++) {
                            complexFloatLargeArray5.setComplexFloat(j10 + j, complexFloatLargeArray6.getComplexFloat(j11 + j));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j12 = j;
                long j13 = j2;
                while (j12 < j + j4) {
                    complexFloatLargeArray4.setComplexFloat(j13, complexFloatLargeArray3.getComplexFloat(j12));
                    j12++;
                    j13++;
                }
            } catch (ExecutionException unused2) {
                long j14 = j;
                long j15 = j2;
                while (j14 < j + j4) {
                    complexFloatLargeArray4.setComplexFloat(j15, complexFloatLargeArray3.getComplexFloat(j14));
                    j14++;
                    j15++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(float[] fArr, int i, ComplexFloatLargeArray complexFloatLargeArray, long j, long j2) {
        float[] fArr2 = fArr;
        int i2 = i;
        ComplexFloatLargeArray complexFloatLargeArray2 = complexFloatLargeArray;
        long j3 = j2;
        if (fArr2.length % 2 != 0) {
            throw new IllegalArgumentException("The length of the source array must be even.");
        } else if (i2 < 0 || i2 >= fArr2.length / 2) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length / 2");
        } else if (j < 0 || j >= complexFloatLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!complexFloatLargeArray.isConstant()) {
            int min = (int) FastMath.min(j3, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
                for (long j4 = j; j4 < j + j3; j4++) {
                    int i3 = i2 * 2;
                    complexFloatLargeArray2.setComplexFloat(j4, new float[]{fArr2[i3], fArr2[i3 + 1]});
                    i2++;
                }
                return;
            }
            long j5 = j3 / ((long) min);
            Future[] futureArr = new Future[min];
            int i4 = 0;
            while (i4 < min) {
                final long j6 = ((long) i4) * j5;
                final long j7 = i4 == min + -1 ? j3 : j6 + j5;
                final float[] fArr3 = fArr;
                final int i5 = i;
                int i6 = i4;
                final ComplexFloatLargeArray complexFloatLargeArray3 = complexFloatLargeArray;
                int i7 = min;
                Future[] futureArr2 = futureArr;
                final long j8 = j;
                futureArr2[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j6; j < j7; j++) {
                            float[] fArr = fArr3;
                            int i = i5;
                            int i2 = (int) j;
                            complexFloatLargeArray3.setComplexFloat(j8 + j, new float[]{fArr[(i + i2) * 2], fArr[((i + i2) * 2) + 1]});
                        }
                    }
                });
                i4 = i6 + 1;
                min = i7;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                for (long j9 = j; j9 < j + j3; j9++) {
                    int i8 = i2 * 2;
                    complexFloatLargeArray2.setComplexFloat(j9, new float[]{fArr2[i8], fArr2[i8 + 1]});
                    i2++;
                }
            } catch (ExecutionException unused2) {
                for (long j10 = j; j10 < j + j3; j10++) {
                    int i9 = i2 * 2;
                    complexFloatLargeArray2.setComplexFloat(j10, new float[]{fArr2[i9], fArr2[i9 + 1]});
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(ComplexDoubleLargeArray complexDoubleLargeArray, long j, ComplexDoubleLargeArray complexDoubleLargeArray2, long j2, long j3) {
        ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArray;
        ComplexDoubleLargeArray complexDoubleLargeArray4 = complexDoubleLargeArray2;
        long j4 = j3;
        if (j < 0 || j >= complexDoubleLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        } else if (j2 < 0 || j2 >= complexDoubleLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j4 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!complexDoubleLargeArray2.isConstant()) {
            int min = (int) FastMath.min(j4, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j4 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j5 = j;
                long j6 = j2;
                while (j5 < j + j4) {
                    complexDoubleLargeArray4.setComplexDouble(j6, complexDoubleLargeArray3.getComplexDouble(j5));
                    j5++;
                    j6++;
                }
                return;
            }
            long j7 = j4 / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j8 = ((long) i) * j7;
                final long j9 = i == min + -1 ? j4 : j8 + j7;
                final ComplexDoubleLargeArray complexDoubleLargeArray5 = complexDoubleLargeArray2;
                final long j10 = j2;
                final ComplexDoubleLargeArray complexDoubleLargeArray6 = complexDoubleLargeArray;
                Future[] futureArr2 = futureArr;
                int i2 = i;
                final long j11 = j;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j8; j < j9; j++) {
                            complexDoubleLargeArray5.setComplexDouble(j10 + j, complexDoubleLargeArray6.getComplexDouble(j11 + j));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j12 = j;
                long j13 = j2;
                while (j12 < j + j4) {
                    complexDoubleLargeArray4.setComplexDouble(j13, complexDoubleLargeArray3.getComplexDouble(j12));
                    j12++;
                    j13++;
                }
            } catch (ExecutionException unused2) {
                long j14 = j;
                long j15 = j2;
                while (j14 < j + j4) {
                    complexDoubleLargeArray4.setComplexDouble(j15, complexDoubleLargeArray3.getComplexDouble(j14));
                    j14++;
                    j15++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(double[] dArr, int i, ComplexDoubleLargeArray complexDoubleLargeArray, long j, long j2) {
        double[] dArr2 = dArr;
        int i2 = i;
        ComplexDoubleLargeArray complexDoubleLargeArray2 = complexDoubleLargeArray;
        long j3 = j2;
        if (dArr2.length % 2 != 0) {
            throw new IllegalArgumentException("The length of the source array must be even.");
        } else if (i2 < 0 || i2 >= dArr2.length / 2) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length / 2");
        } else if (j < 0 || j >= complexDoubleLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!complexDoubleLargeArray.isConstant()) {
            int min = (int) FastMath.min(j3, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
                for (long j4 = j; j4 < j + j3; j4++) {
                    int i3 = i2 * 2;
                    complexDoubleLargeArray2.setComplexDouble(j4, new double[]{dArr2[i3], dArr2[i3 + 1]});
                    i2++;
                }
                return;
            }
            long j5 = j3 / ((long) min);
            Future[] futureArr = new Future[min];
            int i4 = 0;
            while (i4 < min) {
                final long j6 = ((long) i4) * j5;
                final long j7 = i4 == min + -1 ? j3 : j6 + j5;
                final double[] dArr3 = dArr;
                final int i5 = i;
                int i6 = i4;
                final ComplexDoubleLargeArray complexDoubleLargeArray3 = complexDoubleLargeArray;
                int i7 = min;
                Future[] futureArr2 = futureArr;
                final long j8 = j;
                futureArr2[i6] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j6; j < j7; j++) {
                            double[] dArr = dArr3;
                            int i = i5;
                            int i2 = (int) j;
                            complexDoubleLargeArray3.setComplexDouble(j8 + j, new double[]{dArr[(i + i2) * 2], dArr[((i + i2) * 2) + 1]});
                        }
                    }
                });
                i4 = i6 + 1;
                min = i7;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                for (long j9 = j; j9 < j + j3; j9++) {
                    int i8 = i2 * 2;
                    complexDoubleLargeArray2.setComplexDouble(j9, new double[]{dArr2[i8], dArr2[i8 + 1]});
                    i2++;
                }
            } catch (ExecutionException unused2) {
                for (long j10 = j; j10 < j + j3; j10++) {
                    int i9 = i2 * 2;
                    complexDoubleLargeArray2.setComplexDouble(j10, new double[]{dArr2[i9], dArr2[i9 + 1]});
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(StringLargeArray stringLargeArray, long j, StringLargeArray stringLargeArray2, long j2, long j3) {
        StringLargeArray stringLargeArray3 = stringLargeArray;
        StringLargeArray stringLargeArray4 = stringLargeArray2;
        long j4 = j3;
        if (j < 0 || j >= stringLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        } else if (j2 < 0 || j2 >= stringLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j4 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!stringLargeArray2.isConstant()) {
            int min = (int) FastMath.min(j4, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j4 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j5 = j;
                long j6 = j2;
                while (j5 < j + j4) {
                    stringLargeArray4.set(j6, stringLargeArray3.get(j5));
                    j5++;
                    j6++;
                }
                return;
            }
            long j7 = j4 / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j8 = ((long) i) * j7;
                final long j9 = i == min + -1 ? j4 : j8 + j7;
                final StringLargeArray stringLargeArray5 = stringLargeArray2;
                final long j10 = j2;
                final StringLargeArray stringLargeArray6 = stringLargeArray;
                Future[] futureArr2 = futureArr;
                int i2 = i;
                final long j11 = j;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j8; j < j9; j++) {
                            stringLargeArray5.set(j10 + j, stringLargeArray6.get(j11 + j));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j12 = j;
                long j13 = j2;
                while (j12 < j + j4) {
                    stringLargeArray4.set(j13, stringLargeArray3.get(j12));
                    j12++;
                    j13++;
                }
            } catch (ExecutionException unused2) {
                long j14 = j;
                long j15 = j2;
                while (j14 < j + j4) {
                    stringLargeArray4.set(j15, stringLargeArray3.get(j14));
                    j14++;
                    j15++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(String[] strArr, int i, StringLargeArray stringLargeArray, long j, long j2) {
        String[] strArr2 = strArr;
        int i2 = i;
        StringLargeArray stringLargeArray2 = stringLargeArray;
        long j3 = j2;
        if (i2 < 0 || i2 >= strArr2.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        } else if (j < 0 || j >= stringLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!stringLargeArray.isConstant()) {
            int min = (int) FastMath.min(j3, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j4 = j;
                while (j4 < j + j3) {
                    stringLargeArray2.set(j4, strArr2[i2]);
                    j4++;
                    i2++;
                }
                return;
            }
            long j5 = j3 / ((long) min);
            Future[] futureArr = new Future[min];
            int i3 = 0;
            while (i3 < min) {
                final long j6 = ((long) i3) * j5;
                final long j7 = i3 == min + -1 ? j3 : j6 + j5;
                final StringLargeArray stringLargeArray3 = stringLargeArray;
                final long j8 = j;
                int i4 = i3;
                final String[] strArr3 = strArr;
                Future[] futureArr2 = futureArr;
                final int i5 = i;
                futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j6; j < j7; j++) {
                            stringLargeArray3.set(j8 + j, strArr3[i5 + ((int) j)]);
                        }
                    }
                });
                i3 = i4 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j9 = j;
                while (j9 < j + j3) {
                    stringLargeArray2.set(j9, strArr2[i2]);
                    j9++;
                    i2++;
                }
            } catch (ExecutionException unused2) {
                long j10 = j;
                while (j10 < j + j3) {
                    stringLargeArray2.set(j10, strArr2[i2]);
                    j10++;
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(ObjectLargeArray objectLargeArray, long j, ObjectLargeArray objectLargeArray2, long j2, long j3) {
        ObjectLargeArray objectLargeArray3 = objectLargeArray;
        ObjectLargeArray objectLargeArray4 = objectLargeArray2;
        long j4 = j3;
        if (j < 0 || j >= objectLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        } else if (j2 < 0 || j2 >= objectLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j4 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!objectLargeArray2.isConstant()) {
            int min = (int) FastMath.min(j4, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j4 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j5 = j;
                long j6 = j2;
                while (j5 < j + j4) {
                    objectLargeArray4.set(j6, objectLargeArray3.get(j5));
                    j5++;
                    j6++;
                }
                return;
            }
            long j7 = j4 / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j8 = ((long) i) * j7;
                final long j9 = i == min + -1 ? j4 : j8 + j7;
                final ObjectLargeArray objectLargeArray5 = objectLargeArray2;
                final long j10 = j2;
                final ObjectLargeArray objectLargeArray6 = objectLargeArray;
                Future[] futureArr2 = futureArr;
                int i2 = i;
                final long j11 = j;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j8; j < j9; j++) {
                            objectLargeArray5.set(j10 + j, objectLargeArray6.get(j11 + j));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j12 = j;
                long j13 = j2;
                while (j12 < j + j4) {
                    objectLargeArray4.set(j13, objectLargeArray3.get(j12));
                    j12++;
                    j13++;
                }
            } catch (ExecutionException unused2) {
                long j14 = j;
                long j15 = j2;
                while (j14 < j + j4) {
                    objectLargeArray4.set(j15, objectLargeArray3.get(j14));
                    j14++;
                    j15++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    public static void arraycopy(Object[] objArr, int i, ObjectLargeArray objectLargeArray, long j, long j2) {
        Object[] objArr2 = objArr;
        int i2 = i;
        ObjectLargeArray objectLargeArray2 = objectLargeArray;
        long j3 = j2;
        if (i2 < 0 || i2 >= objArr2.length) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
        } else if (j < 0 || j >= objectLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        } else if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        } else if (!objectLargeArray.isConstant()) {
            int min = (int) FastMath.min(j3, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
                long j4 = j;
                while (j4 < j + j3) {
                    objectLargeArray2.set(j4, objArr2[i2]);
                    j4++;
                    i2++;
                }
                return;
            }
            long j5 = j3 / ((long) min);
            Future[] futureArr = new Future[min];
            int i3 = 0;
            while (i3 < min) {
                final long j6 = ((long) i3) * j5;
                final long j7 = i3 == min + -1 ? j3 : j6 + j5;
                final ObjectLargeArray objectLargeArray3 = objectLargeArray;
                final long j8 = j;
                int i4 = i3;
                final Object[] objArr3 = objArr;
                Future[] futureArr2 = futureArr;
                final int i5 = i;
                futureArr2[i4] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j6; j < j7; j++) {
                            objectLargeArray3.set(j8 + j, objArr3[i5 + ((int) j)]);
                        }
                    }
                });
                i3 = i4 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                long j9 = j;
                while (j9 < j + j3) {
                    objectLargeArray2.set(j9, objArr2[i2]);
                    j9++;
                    i2++;
                }
            } catch (ExecutionException unused2) {
                long j10 = j;
                while (j10 < j + j3) {
                    objectLargeArray2.set(j10, objArr2[i2]);
                    j10++;
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v19, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v23, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v54, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v55, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v56, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v57, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v58, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v59, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v60, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v61, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v62, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v63, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v64, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v65, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v66, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v67, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v68, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v69, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v70, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v71, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v72, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v73, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v74, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v75, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v76, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v77, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v78, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v79, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v80, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v81, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v82, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v83, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v84, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v85, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v86, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v87, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v88, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v89, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v90, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v91, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static pl.edu.icm.jlargearrays.LargeArray createConstant(pl.edu.icm.jlargearrays.LargeArrayType r3, long r4, java.lang.Object r6) {
        /*
            int[] r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType
            int r3 = r3.ordinal()
            r3 = r0[r3]
            r0 = 0
            java.lang.String r1 = "Invalid value type."
            r2 = 1
            switch(r3) {
                case 1: goto L_0x02ef;
                case 2: goto L_0x0294;
                case 3: goto L_0x0239;
                case 4: goto L_0x01de;
                case 5: goto L_0x017f;
                case 6: goto L_0x0121;
                case 7: goto L_0x00c2;
                case 8: goto L_0x00a6;
                case 9: goto L_0x008a;
                case 10: goto L_0x0078;
                case 11: goto L_0x0072;
                case 12: goto L_0x0017;
                default: goto L_0x000f;
            }
        L_0x000f:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "Invalid array type."
            r3.<init>(r4)
            throw r3
        L_0x0017:
            boolean r3 = r6 instanceof java.lang.Boolean
            if (r3 == 0) goto L_0x0025
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r3 = r6.booleanValue()
            if (r3 != r2) goto L_0x0066
            r0 = r2
            goto L_0x0066
        L_0x0025:
            boolean r3 = r6 instanceof java.lang.Byte
            if (r3 == 0) goto L_0x0030
            java.lang.Byte r6 = (java.lang.Byte) r6
            short r0 = r6.shortValue()
            goto L_0x0066
        L_0x0030:
            boolean r3 = r6 instanceof java.lang.Short
            if (r3 == 0) goto L_0x003b
            java.lang.Short r6 = (java.lang.Short) r6
            short r0 = r6.shortValue()
            goto L_0x0066
        L_0x003b:
            boolean r3 = r6 instanceof java.lang.Integer
            if (r3 == 0) goto L_0x0046
            java.lang.Integer r6 = (java.lang.Integer) r6
            short r0 = r6.shortValue()
            goto L_0x0066
        L_0x0046:
            boolean r3 = r6 instanceof java.lang.Long
            if (r3 == 0) goto L_0x0051
            java.lang.Long r6 = (java.lang.Long) r6
            short r0 = r6.shortValue()
            goto L_0x0066
        L_0x0051:
            boolean r3 = r6 instanceof java.lang.Float
            if (r3 == 0) goto L_0x005c
            java.lang.Float r6 = (java.lang.Float) r6
            short r0 = r6.shortValue()
            goto L_0x0066
        L_0x005c:
            boolean r3 = r6 instanceof java.lang.Double
            if (r3 == 0) goto L_0x006c
            java.lang.Double r6 = (java.lang.Double) r6
            short r0 = r6.shortValue()
        L_0x0066:
            pl.edu.icm.jlargearrays.UnsignedByteLargeArray r3 = new pl.edu.icm.jlargearrays.UnsignedByteLargeArray
            r3.<init>((long) r4, (short) r0)
            return r3
        L_0x006c:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r3.<init>(r1)
            throw r3
        L_0x0072:
            pl.edu.icm.jlargearrays.ObjectLargeArray r3 = new pl.edu.icm.jlargearrays.ObjectLargeArray
            r3.<init>((long) r4, (java.lang.Object) r6)
            return r3
        L_0x0078:
            boolean r3 = r6 instanceof java.lang.String
            if (r3 == 0) goto L_0x0084
            java.lang.String r6 = (java.lang.String) r6
            pl.edu.icm.jlargearrays.StringLargeArray r3 = new pl.edu.icm.jlargearrays.StringLargeArray
            r3.<init>((long) r4, (java.lang.String) r6)
            return r3
        L_0x0084:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r3.<init>(r1)
            throw r3
        L_0x008a:
            java.lang.Class r3 = r6.getClass()
            java.lang.Class r3 = r3.getComponentType()
            java.lang.Class r0 = java.lang.Double.TYPE
            if (r3 != r0) goto L_0x00a0
            double[] r6 = (double[]) r6
            double[] r6 = (double[]) r6
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r3 = new pl.edu.icm.jlargearrays.ComplexDoubleLargeArray
            r3.<init>((long) r4, (double[]) r6)
            return r3
        L_0x00a0:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r3.<init>(r1)
            throw r3
        L_0x00a6:
            java.lang.Class r3 = r6.getClass()
            java.lang.Class r3 = r3.getComponentType()
            java.lang.Class r0 = java.lang.Float.TYPE
            if (r3 != r0) goto L_0x00bc
            float[] r6 = (float[]) r6
            float[] r6 = (float[]) r6
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r3 = new pl.edu.icm.jlargearrays.ComplexFloatLargeArray
            r3.<init>((long) r4, (float[]) r6)
            return r3
        L_0x00bc:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r3.<init>(r1)
            throw r3
        L_0x00c2:
            boolean r3 = r6 instanceof java.lang.Boolean
            if (r3 == 0) goto L_0x00d4
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r3 = r6.booleanValue()
            if (r3 != r2) goto L_0x00d1
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x0115
        L_0x00d1:
            r0 = 0
            goto L_0x0115
        L_0x00d4:
            boolean r3 = r6 instanceof java.lang.Byte
            if (r3 == 0) goto L_0x00df
            java.lang.Byte r6 = (java.lang.Byte) r6
            double r0 = r6.doubleValue()
            goto L_0x0115
        L_0x00df:
            boolean r3 = r6 instanceof java.lang.Short
            if (r3 == 0) goto L_0x00ea
            java.lang.Short r6 = (java.lang.Short) r6
            double r0 = r6.doubleValue()
            goto L_0x0115
        L_0x00ea:
            boolean r3 = r6 instanceof java.lang.Integer
            if (r3 == 0) goto L_0x00f5
            java.lang.Integer r6 = (java.lang.Integer) r6
            double r0 = r6.doubleValue()
            goto L_0x0115
        L_0x00f5:
            boolean r3 = r6 instanceof java.lang.Long
            if (r3 == 0) goto L_0x0100
            java.lang.Long r6 = (java.lang.Long) r6
            double r0 = r6.doubleValue()
            goto L_0x0115
        L_0x0100:
            boolean r3 = r6 instanceof java.lang.Float
            if (r3 == 0) goto L_0x010b
            java.lang.Float r6 = (java.lang.Float) r6
            double r0 = r6.doubleValue()
            goto L_0x0115
        L_0x010b:
            boolean r3 = r6 instanceof java.lang.Double
            if (r3 == 0) goto L_0x011b
            java.lang.Double r6 = (java.lang.Double) r6
            double r0 = r6.doubleValue()
        L_0x0115:
            pl.edu.icm.jlargearrays.DoubleLargeArray r3 = new pl.edu.icm.jlargearrays.DoubleLargeArray
            r3.<init>((long) r4, (double) r0)
            return r3
        L_0x011b:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r3.<init>(r1)
            throw r3
        L_0x0121:
            boolean r3 = r6 instanceof java.lang.Boolean
            if (r3 == 0) goto L_0x0132
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r3 = r6.booleanValue()
            if (r3 != r2) goto L_0x0130
            r3 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0173
        L_0x0130:
            r3 = 0
            goto L_0x0173
        L_0x0132:
            boolean r3 = r6 instanceof java.lang.Byte
            if (r3 == 0) goto L_0x013d
            java.lang.Byte r6 = (java.lang.Byte) r6
            float r3 = r6.floatValue()
            goto L_0x0173
        L_0x013d:
            boolean r3 = r6 instanceof java.lang.Short
            if (r3 == 0) goto L_0x0148
            java.lang.Short r6 = (java.lang.Short) r6
            float r3 = r6.floatValue()
            goto L_0x0173
        L_0x0148:
            boolean r3 = r6 instanceof java.lang.Integer
            if (r3 == 0) goto L_0x0153
            java.lang.Integer r6 = (java.lang.Integer) r6
            float r3 = r6.floatValue()
            goto L_0x0173
        L_0x0153:
            boolean r3 = r6 instanceof java.lang.Long
            if (r3 == 0) goto L_0x015e
            java.lang.Long r6 = (java.lang.Long) r6
            float r3 = r6.floatValue()
            goto L_0x0173
        L_0x015e:
            boolean r3 = r6 instanceof java.lang.Float
            if (r3 == 0) goto L_0x0169
            java.lang.Float r6 = (java.lang.Float) r6
            float r3 = r6.floatValue()
            goto L_0x0173
        L_0x0169:
            boolean r3 = r6 instanceof java.lang.Double
            if (r3 == 0) goto L_0x0179
            java.lang.Double r6 = (java.lang.Double) r6
            float r3 = r6.floatValue()
        L_0x0173:
            pl.edu.icm.jlargearrays.FloatLargeArray r6 = new pl.edu.icm.jlargearrays.FloatLargeArray
            r6.<init>((long) r4, (float) r3)
            return r6
        L_0x0179:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r3.<init>(r1)
            throw r3
        L_0x017f:
            boolean r3 = r6 instanceof java.lang.Boolean
            if (r3 == 0) goto L_0x0191
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r3 = r6.booleanValue()
            if (r3 != r2) goto L_0x018e
            r0 = 1
            goto L_0x01d2
        L_0x018e:
            r0 = 0
            goto L_0x01d2
        L_0x0191:
            boolean r3 = r6 instanceof java.lang.Byte
            if (r3 == 0) goto L_0x019c
            java.lang.Byte r6 = (java.lang.Byte) r6
            long r0 = r6.longValue()
            goto L_0x01d2
        L_0x019c:
            boolean r3 = r6 instanceof java.lang.Short
            if (r3 == 0) goto L_0x01a7
            java.lang.Short r6 = (java.lang.Short) r6
            long r0 = r6.longValue()
            goto L_0x01d2
        L_0x01a7:
            boolean r3 = r6 instanceof java.lang.Integer
            if (r3 == 0) goto L_0x01b2
            java.lang.Integer r6 = (java.lang.Integer) r6
            long r0 = r6.longValue()
            goto L_0x01d2
        L_0x01b2:
            boolean r3 = r6 instanceof java.lang.Long
            if (r3 == 0) goto L_0x01bd
            java.lang.Long r6 = (java.lang.Long) r6
            long r0 = r6.longValue()
            goto L_0x01d2
        L_0x01bd:
            boolean r3 = r6 instanceof java.lang.Float
            if (r3 == 0) goto L_0x01c8
            java.lang.Float r6 = (java.lang.Float) r6
            long r0 = r6.longValue()
            goto L_0x01d2
        L_0x01c8:
            boolean r3 = r6 instanceof java.lang.Double
            if (r3 == 0) goto L_0x01d8
            java.lang.Double r6 = (java.lang.Double) r6
            long r0 = r6.longValue()
        L_0x01d2:
            pl.edu.icm.jlargearrays.LongLargeArray r3 = new pl.edu.icm.jlargearrays.LongLargeArray
            r3.<init>((long) r4, (long) r0)
            return r3
        L_0x01d8:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r3.<init>(r1)
            throw r3
        L_0x01de:
            boolean r3 = r6 instanceof java.lang.Boolean
            if (r3 == 0) goto L_0x01ec
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r3 = r6.booleanValue()
            if (r3 != r2) goto L_0x022d
            r0 = r2
            goto L_0x022d
        L_0x01ec:
            boolean r3 = r6 instanceof java.lang.Byte
            if (r3 == 0) goto L_0x01f7
            java.lang.Byte r6 = (java.lang.Byte) r6
            int r0 = r6.intValue()
            goto L_0x022d
        L_0x01f7:
            boolean r3 = r6 instanceof java.lang.Short
            if (r3 == 0) goto L_0x0202
            java.lang.Short r6 = (java.lang.Short) r6
            int r0 = r6.intValue()
            goto L_0x022d
        L_0x0202:
            boolean r3 = r6 instanceof java.lang.Integer
            if (r3 == 0) goto L_0x020d
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r0 = r6.intValue()
            goto L_0x022d
        L_0x020d:
            boolean r3 = r6 instanceof java.lang.Long
            if (r3 == 0) goto L_0x0218
            java.lang.Long r6 = (java.lang.Long) r6
            int r0 = r6.intValue()
            goto L_0x022d
        L_0x0218:
            boolean r3 = r6 instanceof java.lang.Float
            if (r3 == 0) goto L_0x0223
            java.lang.Float r6 = (java.lang.Float) r6
            int r0 = r6.intValue()
            goto L_0x022d
        L_0x0223:
            boolean r3 = r6 instanceof java.lang.Double
            if (r3 == 0) goto L_0x0233
            java.lang.Double r6 = (java.lang.Double) r6
            int r0 = r6.intValue()
        L_0x022d:
            pl.edu.icm.jlargearrays.IntLargeArray r3 = new pl.edu.icm.jlargearrays.IntLargeArray
            r3.<init>((long) r4, (int) r0)
            return r3
        L_0x0233:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r3.<init>(r1)
            throw r3
        L_0x0239:
            boolean r3 = r6 instanceof java.lang.Boolean
            if (r3 == 0) goto L_0x0247
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r3 = r6.booleanValue()
            if (r3 != r2) goto L_0x0288
            r0 = r2
            goto L_0x0288
        L_0x0247:
            boolean r3 = r6 instanceof java.lang.Byte
            if (r3 == 0) goto L_0x0252
            java.lang.Byte r6 = (java.lang.Byte) r6
            short r0 = r6.shortValue()
            goto L_0x0288
        L_0x0252:
            boolean r3 = r6 instanceof java.lang.Short
            if (r3 == 0) goto L_0x025d
            java.lang.Short r6 = (java.lang.Short) r6
            short r0 = r6.shortValue()
            goto L_0x0288
        L_0x025d:
            boolean r3 = r6 instanceof java.lang.Integer
            if (r3 == 0) goto L_0x0268
            java.lang.Integer r6 = (java.lang.Integer) r6
            short r0 = r6.shortValue()
            goto L_0x0288
        L_0x0268:
            boolean r3 = r6 instanceof java.lang.Long
            if (r3 == 0) goto L_0x0273
            java.lang.Long r6 = (java.lang.Long) r6
            short r0 = r6.shortValue()
            goto L_0x0288
        L_0x0273:
            boolean r3 = r6 instanceof java.lang.Float
            if (r3 == 0) goto L_0x027e
            java.lang.Float r6 = (java.lang.Float) r6
            short r0 = r6.shortValue()
            goto L_0x0288
        L_0x027e:
            boolean r3 = r6 instanceof java.lang.Double
            if (r3 == 0) goto L_0x028e
            java.lang.Double r6 = (java.lang.Double) r6
            short r0 = r6.shortValue()
        L_0x0288:
            pl.edu.icm.jlargearrays.ShortLargeArray r3 = new pl.edu.icm.jlargearrays.ShortLargeArray
            r3.<init>((long) r4, (short) r0)
            return r3
        L_0x028e:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r3.<init>(r1)
            throw r3
        L_0x0294:
            boolean r3 = r6 instanceof java.lang.Boolean
            if (r3 == 0) goto L_0x02a2
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r3 = r6.booleanValue()
            if (r3 != r2) goto L_0x02e3
            r0 = r2
            goto L_0x02e3
        L_0x02a2:
            boolean r3 = r6 instanceof java.lang.Byte
            if (r3 == 0) goto L_0x02ad
            java.lang.Byte r6 = (java.lang.Byte) r6
            byte r0 = r6.byteValue()
            goto L_0x02e3
        L_0x02ad:
            boolean r3 = r6 instanceof java.lang.Short
            if (r3 == 0) goto L_0x02b8
            java.lang.Short r6 = (java.lang.Short) r6
            byte r0 = r6.byteValue()
            goto L_0x02e3
        L_0x02b8:
            boolean r3 = r6 instanceof java.lang.Integer
            if (r3 == 0) goto L_0x02c3
            java.lang.Integer r6 = (java.lang.Integer) r6
            byte r0 = r6.byteValue()
            goto L_0x02e3
        L_0x02c3:
            boolean r3 = r6 instanceof java.lang.Long
            if (r3 == 0) goto L_0x02ce
            java.lang.Long r6 = (java.lang.Long) r6
            byte r0 = r6.byteValue()
            goto L_0x02e3
        L_0x02ce:
            boolean r3 = r6 instanceof java.lang.Float
            if (r3 == 0) goto L_0x02d9
            java.lang.Float r6 = (java.lang.Float) r6
            byte r0 = r6.byteValue()
            goto L_0x02e3
        L_0x02d9:
            boolean r3 = r6 instanceof java.lang.Double
            if (r3 == 0) goto L_0x02e9
            java.lang.Double r6 = (java.lang.Double) r6
            byte r0 = r6.byteValue()
        L_0x02e3:
            pl.edu.icm.jlargearrays.ByteLargeArray r3 = new pl.edu.icm.jlargearrays.ByteLargeArray
            r3.<init>((long) r4, (byte) r0)
            return r3
        L_0x02e9:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r3.<init>(r1)
            throw r3
        L_0x02ef:
            boolean r3 = r6 instanceof java.lang.Boolean
            if (r3 == 0) goto L_0x02fd
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r3 = r6.booleanValue()
            if (r3 != r2) goto L_0x033e
            r0 = r2
            goto L_0x033e
        L_0x02fd:
            boolean r3 = r6 instanceof java.lang.Byte
            if (r3 == 0) goto L_0x0308
            java.lang.Byte r6 = (java.lang.Byte) r6
            byte r0 = r6.byteValue()
            goto L_0x033e
        L_0x0308:
            boolean r3 = r6 instanceof java.lang.Short
            if (r3 == 0) goto L_0x0313
            java.lang.Short r6 = (java.lang.Short) r6
            byte r0 = r6.byteValue()
            goto L_0x033e
        L_0x0313:
            boolean r3 = r6 instanceof java.lang.Integer
            if (r3 == 0) goto L_0x031e
            java.lang.Integer r6 = (java.lang.Integer) r6
            byte r0 = r6.byteValue()
            goto L_0x033e
        L_0x031e:
            boolean r3 = r6 instanceof java.lang.Long
            if (r3 == 0) goto L_0x0329
            java.lang.Long r6 = (java.lang.Long) r6
            byte r0 = r6.byteValue()
            goto L_0x033e
        L_0x0329:
            boolean r3 = r6 instanceof java.lang.Float
            if (r3 == 0) goto L_0x0334
            java.lang.Float r6 = (java.lang.Float) r6
            byte r0 = r6.byteValue()
            goto L_0x033e
        L_0x0334:
            boolean r3 = r6 instanceof java.lang.Double
            if (r3 == 0) goto L_0x0344
            java.lang.Double r6 = (java.lang.Double) r6
            byte r0 = r6.byteValue()
        L_0x033e:
            pl.edu.icm.jlargearrays.LogicLargeArray r3 = new pl.edu.icm.jlargearrays.LogicLargeArray
            r3.<init>((long) r4, (byte) r0)
            return r3
        L_0x0344:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r3.<init>(r1)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LargeArrayUtils.createConstant(pl.edu.icm.jlargearrays.LargeArrayType, long, java.lang.Object):pl.edu.icm.jlargearrays.LargeArray");
    }

    public static LargeArray create(LargeArrayType largeArrayType, long j) {
        return create(largeArrayType, j, true);
    }

    public static LargeArray create(LargeArrayType largeArrayType, long j, boolean z) {
        switch (AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[largeArrayType.ordinal()]) {
            case 1:
                return new LogicLargeArray(j, z);
            case 2:
                return new ByteLargeArray(j, z);
            case 3:
                return new ShortLargeArray(j, z);
            case 4:
                return new IntLargeArray(j, z);
            case 5:
                return new LongLargeArray(j, z);
            case 6:
                return new FloatLargeArray(j, z);
            case 7:
                return new DoubleLargeArray(j, z);
            case 8:
                return new ComplexFloatLargeArray(j, z);
            case 9:
                return new ComplexDoubleLargeArray(j, z);
            case 10:
                return new StringLargeArray(j, 100, z);
            case 11:
                return new ObjectLargeArray(j, 100, z);
            case 12:
                return new UnsignedByteLargeArray(j, z);
            default:
                throw new IllegalArgumentException("Invalid array type.");
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d4, code lost:
        r2 = r5.nextInt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00da, code lost:
        if (r10 >= r0) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00dc, code lost:
        r2 = r2 >> 16;
        r4.setShort(r10, (short) r2);
        r10 = r10 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0115, code lost:
        r2 = r5.nextInt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x011b, code lost:
        if (r10 >= r0) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x011d, code lost:
        r2 = r2 >> 8;
        r4.setByte(r10, (byte) r2);
        r10 = r10 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static pl.edu.icm.jlargearrays.LargeArray generateRandom(pl.edu.icm.jlargearrays.LargeArrayType r16, long r17) {
        /*
            r0 = r17
            r2 = 0
            r3 = r16
            pl.edu.icm.jlargearrays.LargeArray r4 = create(r3, r0, r2)
            java.util.Random r5 = new java.util.Random
            r5.<init>()
            int[] r6 = pl.edu.icm.jlargearrays.LargeArrayUtils.AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType
            int r3 = r16.ordinal()
            r3 = r6[r3]
            r6 = 1
            r7 = 2
            r8 = 2
            r10 = 0
            r12 = 1
            switch(r3) {
                case 1: goto L_0x0127;
                case 2: goto L_0x00e6;
                case 3: goto L_0x00b8;
                case 4: goto L_0x00aa;
                case 5: goto L_0x009c;
                case 6: goto L_0x008e;
                case 7: goto L_0x0080;
                case 8: goto L_0x0064;
                case 9: goto L_0x0047;
                case 10: goto L_0x003a;
                case 11: goto L_0x0029;
                case 12: goto L_0x00e6;
                default: goto L_0x0021;
            }
        L_0x0021:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Invalid array type."
            r0.<init>(r1)
            throw r0
        L_0x0029:
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x0135
            float r2 = r5.nextFloat()
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            r4.set(r10, r2)
            long r10 = r10 + r12
            goto L_0x0029
        L_0x003a:
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x0135
            float r2 = r5.nextFloat()
            r4.setFloat(r10, r2)
            long r10 = r10 + r12
            goto L_0x003a
        L_0x0047:
            r3 = r4
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r3 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r3
        L_0x004a:
            int r8 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r8 >= 0) goto L_0x0135
            double r8 = r5.nextDouble()
            double r14 = r5.nextDouble()
            double[] r12 = new double[r7]
            r12[r2] = r8
            r12[r6] = r14
            r3.setComplexDouble(r10, r12)
            r8 = 1
            long r10 = r10 + r8
            r12 = r8
            goto L_0x004a
        L_0x0064:
            r3 = r4
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r3 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r3
        L_0x0067:
            int r8 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r8 >= 0) goto L_0x0135
            float r8 = r5.nextFloat()
            float r9 = r5.nextFloat()
            float[] r12 = new float[r7]
            r12[r2] = r8
            r12[r6] = r9
            r3.setComplexFloat(r10, r12)
            r8 = 1
            long r10 = r10 + r8
            goto L_0x0067
        L_0x0080:
            r8 = r12
        L_0x0081:
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x0135
            double r2 = r5.nextDouble()
            r4.setDouble(r10, r2)
            long r10 = r10 + r8
            goto L_0x0081
        L_0x008e:
            r8 = r12
        L_0x008f:
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x0135
            float r2 = r5.nextFloat()
            r4.setFloat(r10, r2)
            long r10 = r10 + r8
            goto L_0x008f
        L_0x009c:
            r8 = r12
        L_0x009d:
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x0135
            long r2 = r5.nextLong()
            r4.setLong(r10, r2)
            long r10 = r10 + r8
            goto L_0x009d
        L_0x00aa:
            r8 = r12
        L_0x00ab:
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x0135
            int r2 = r5.nextInt()
            r4.setInt(r10, r2)
            long r10 = r10 + r8
            goto L_0x00ab
        L_0x00b8:
            long r2 = r0 / r8
            int r2 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x00d4
            int r2 = r5.nextInt()
            int r2 = r2 >> 16
            short r3 = (short) r2
            r4.setShort(r10, r3)
            r6 = 1
            long r12 = r10 + r6
            int r2 = r2 >> 16
            short r2 = (short) r2
            r4.setShort(r12, r2)
            long r10 = r10 + r8
            goto L_0x00b8
        L_0x00d4:
            int r2 = r5.nextInt()
        L_0x00d8:
            int r3 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r3 >= 0) goto L_0x0135
            int r2 = r2 >> 16
            short r3 = (short) r2
            r4.setShort(r10, r3)
            r5 = 1
            long r10 = r10 + r5
            goto L_0x00d8
        L_0x00e6:
            r2 = 4
            long r6 = r0 / r2
            int r6 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r6 >= 0) goto L_0x0115
            int r6 = r5.nextInt()
            int r7 = r6 >> 8
            byte r7 = (byte) r7
            r4.setByte(r10, r7)
            r12 = 1
            long r14 = r10 + r12
            int r7 = r6 >> 16
            byte r7 = (byte) r7
            r4.setByte(r14, r7)
            long r12 = r10 + r8
            int r6 = r6 >> 24
            byte r7 = (byte) r6
            r4.setByte(r12, r7)
            r12 = 3
            long r12 = r12 + r10
            int r6 = r6 >> 8
            byte r6 = (byte) r6
            r4.setByte(r12, r6)
            long r10 = r10 + r2
            goto L_0x00e6
        L_0x0115:
            int r2 = r5.nextInt()
        L_0x0119:
            int r3 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r3 >= 0) goto L_0x0135
            int r2 = r2 >> 8
            byte r3 = (byte) r2
            r4.setByte(r10, r3)
            r6 = 1
            long r10 = r10 + r6
            goto L_0x0119
        L_0x0127:
            r6 = r12
        L_0x0128:
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x0135
            boolean r2 = r5.nextBoolean()
            r4.setBoolean(r10, r2)
            long r10 = r10 + r6
            goto L_0x0128
        L_0x0135:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LargeArrayUtils.generateRandom(pl.edu.icm.jlargearrays.LargeArrayType, long):pl.edu.icm.jlargearrays.LargeArray");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static pl.edu.icm.jlargearrays.LargeArray convert(pl.edu.icm.jlargearrays.LargeArray r24, pl.edu.icm.jlargearrays.LargeArrayType r25) {
        /*
            r8 = r24
            r9 = r25
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r24.getType()
            if (r0 != r9) goto L_0x000b
            return r8
        L_0x000b:
            boolean r0 = r24.isConstant()
            java.lang.String r10 = "Invalid array type."
            r11 = 0
            if (r0 == 0) goto L_0x00d8
            int[] r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType
            int r1 = r25.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L_0x00ca;
                case 2: goto L_0x00bc;
                case 3: goto L_0x00ae;
                case 4: goto L_0x00a0;
                case 5: goto L_0x0092;
                case 6: goto L_0x0084;
                case 7: goto L_0x0076;
                case 8: goto L_0x0065;
                case 9: goto L_0x0054;
                case 10: goto L_0x0042;
                case 11: goto L_0x0034;
                case 12: goto L_0x0026;
                default: goto L_0x0020;
            }
        L_0x0020:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r10)
            throw r0
        L_0x0026:
            pl.edu.icm.jlargearrays.UnsignedByteLargeArray r0 = new pl.edu.icm.jlargearrays.UnsignedByteLargeArray
            long r1 = r24.length()
            short r3 = r8.getUnsignedByte(r11)
            r0.<init>((long) r1, (short) r3)
            return r0
        L_0x0034:
            pl.edu.icm.jlargearrays.ObjectLargeArray r0 = new pl.edu.icm.jlargearrays.ObjectLargeArray
            long r1 = r24.length()
            java.lang.Object r3 = r8.get(r11)
            r0.<init>((long) r1, (java.lang.Object) r3)
            return r0
        L_0x0042:
            pl.edu.icm.jlargearrays.StringLargeArray r0 = new pl.edu.icm.jlargearrays.StringLargeArray
            long r1 = r24.length()
            java.lang.Object r3 = r8.get(r11)
            java.lang.String r3 = r3.toString()
            r0.<init>((long) r1, (java.lang.String) r3)
            return r0
        L_0x0054:
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r0 = new pl.edu.icm.jlargearrays.ComplexDoubleLargeArray
            long r1 = r24.length()
            r3 = r8
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r3 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r3
            double[] r3 = r3.getComplexDouble(r11)
            r0.<init>((long) r1, (double[]) r3)
            return r0
        L_0x0065:
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r0 = new pl.edu.icm.jlargearrays.ComplexFloatLargeArray
            long r1 = r24.length()
            r3 = r8
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r3 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r3
            float[] r3 = r3.getComplexFloat(r11)
            r0.<init>((long) r1, (float[]) r3)
            return r0
        L_0x0076:
            pl.edu.icm.jlargearrays.DoubleLargeArray r0 = new pl.edu.icm.jlargearrays.DoubleLargeArray
            long r1 = r24.length()
            double r3 = r8.getDouble(r11)
            r0.<init>((long) r1, (double) r3)
            return r0
        L_0x0084:
            pl.edu.icm.jlargearrays.FloatLargeArray r0 = new pl.edu.icm.jlargearrays.FloatLargeArray
            long r1 = r24.length()
            float r3 = r8.getFloat(r11)
            r0.<init>((long) r1, (float) r3)
            return r0
        L_0x0092:
            pl.edu.icm.jlargearrays.LongLargeArray r0 = new pl.edu.icm.jlargearrays.LongLargeArray
            long r1 = r24.length()
            long r3 = r8.getLong(r11)
            r0.<init>((long) r1, (long) r3)
            return r0
        L_0x00a0:
            pl.edu.icm.jlargearrays.IntLargeArray r0 = new pl.edu.icm.jlargearrays.IntLargeArray
            long r1 = r24.length()
            int r3 = r8.getInt(r11)
            r0.<init>((long) r1, (int) r3)
            return r0
        L_0x00ae:
            pl.edu.icm.jlargearrays.ShortLargeArray r0 = new pl.edu.icm.jlargearrays.ShortLargeArray
            long r1 = r24.length()
            short r3 = r8.getShort(r11)
            r0.<init>((long) r1, (short) r3)
            return r0
        L_0x00bc:
            pl.edu.icm.jlargearrays.ByteLargeArray r0 = new pl.edu.icm.jlargearrays.ByteLargeArray
            long r1 = r24.length()
            byte r3 = r8.getByte(r11)
            r0.<init>((long) r1, (byte) r3)
            return r0
        L_0x00ca:
            pl.edu.icm.jlargearrays.LogicLargeArray r0 = new pl.edu.icm.jlargearrays.LogicLargeArray
            long r1 = r24.length()
            byte r3 = r8.getByte(r11)
            r0.<init>((long) r1, (byte) r3)
            return r0
        L_0x00d8:
            long r13 = r8.length
            r0 = 0
            pl.edu.icm.jlargearrays.LargeArray r15 = create(r9, r13, r0)
            int r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getNumberOfThreads()
            long r1 = (long) r1
            long r1 = org.apache.commons.math3.util.FastMath.min((long) r13, (long) r1)
            int r7 = (int) r1
            r1 = 2
            r16 = 1
            if (r7 < r1) goto L_0x0304
            long r1 = pl.edu.icm.jlargearrays.ConcurrencyUtils.getConcurrentThreshold()
            int r1 = (r13 > r1 ? 1 : (r13 == r1 ? 0 : -1))
            if (r1 >= 0) goto L_0x00f8
            goto L_0x0304
        L_0x00f8:
            long r1 = (long) r7
            long r18 = r13 / r1
            java.util.concurrent.Future[] r6 = new java.util.concurrent.Future[r7]
            r4 = r0
        L_0x00fe:
            if (r4 >= r7) goto L_0x012f
            long r0 = (long) r4
            long r2 = r0 * r18
            int r0 = r7 + -1
            if (r4 != r0) goto L_0x010a
            r20 = r13
            goto L_0x010e
        L_0x010a:
            long r0 = r2 + r18
            r20 = r0
        L_0x010e:
            pl.edu.icm.jlargearrays.LargeArrayUtils$26 r22 = new pl.edu.icm.jlargearrays.LargeArrayUtils$26
            r0 = r22
            r1 = r25
            r23 = r4
            r4 = r20
            r20 = r6
            r6 = r15
            r21 = r7
            r7 = r24
            r0.<init>(r1, r2, r4, r6, r7)
            java.util.concurrent.Future r0 = pl.edu.icm.jlargearrays.ConcurrencyUtils.submit((java.lang.Runnable) r22)
            r20[r23] = r0
            int r4 = r23 + 1
            r6 = r20
            r7 = r21
            goto L_0x00fe
        L_0x012f:
            r20 = r6
            pl.edu.icm.jlargearrays.ConcurrencyUtils.waitForCompletion(r20)     // Catch:{ InterruptedException -> 0x021d, ExecutionException -> 0x0136 }
            goto L_0x03eb
        L_0x0136:
            int[] r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType
            int r1 = r25.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L_0x020f;
                case 2: goto L_0x020f;
                case 3: goto L_0x0201;
                case 4: goto L_0x01f3;
                case 5: goto L_0x01e5;
                case 6: goto L_0x01d7;
                case 7: goto L_0x01c9;
                case 8: goto L_0x019f;
                case 9: goto L_0x0175;
                case 10: goto L_0x0163;
                case 11: goto L_0x0155;
                case 12: goto L_0x0147;
                default: goto L_0x0141;
            }
        L_0x0141:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r10)
            throw r0
        L_0x0147:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            short r0 = r8.getUnsignedByte(r11)
            r15.setUnsignedByte(r11, r0)
            long r11 = r11 + r16
            goto L_0x0147
        L_0x0155:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            java.lang.Object r0 = r8.get(r11)
            r15.set(r11, r0)
            long r11 = r11 + r16
            goto L_0x0155
        L_0x0163:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            java.lang.Object r0 = r8.get(r11)
            java.lang.String r0 = r0.toString()
            r15.set(r11, r0)
            long r11 = r11 + r16
            goto L_0x0163
        L_0x0175:
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r24.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            if (r0 != r1) goto L_0x0191
        L_0x017d:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            r0 = r15
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r0 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r0
            r1 = r8
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r1
            float[] r1 = r1.getComplexFloat(r11)
            r0.setComplexFloat(r11, r1)
            long r11 = r11 + r16
            goto L_0x017d
        L_0x0191:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            double r0 = r8.getDouble(r11)
            r15.setDouble(r11, r0)
            long r11 = r11 + r16
            goto L_0x0191
        L_0x019f:
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r24.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 != r1) goto L_0x01bb
        L_0x01a7:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            r0 = r15
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r0 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r0
            r1 = r8
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r1
            double[] r1 = r1.getComplexDouble(r11)
            r0.setComplexDouble(r11, r1)
            long r11 = r11 + r16
            goto L_0x01a7
        L_0x01bb:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            float r0 = r8.getFloat(r11)
            r15.setFloat(r11, r0)
            long r11 = r11 + r16
            goto L_0x01bb
        L_0x01c9:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            double r0 = r8.getDouble(r11)
            r15.setDouble(r11, r0)
            long r11 = r11 + r16
            goto L_0x01c9
        L_0x01d7:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            float r0 = r8.getFloat(r11)
            r15.setFloat(r11, r0)
            long r11 = r11 + r16
            goto L_0x01d7
        L_0x01e5:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            long r0 = r8.getLong(r11)
            r15.setLong(r11, r0)
            long r11 = r11 + r16
            goto L_0x01e5
        L_0x01f3:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            int r0 = r8.getInt(r11)
            r15.setInt(r11, r0)
            long r11 = r11 + r16
            goto L_0x01f3
        L_0x0201:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            short r0 = r8.getShort(r11)
            r15.setShort(r11, r0)
            long r11 = r11 + r16
            goto L_0x0201
        L_0x020f:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            byte r0 = r8.getByte(r11)
            r15.setByte(r11, r0)
            long r11 = r11 + r16
            goto L_0x020f
        L_0x021d:
            int[] r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType
            int r1 = r25.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L_0x02f6;
                case 2: goto L_0x02f6;
                case 3: goto L_0x02e8;
                case 4: goto L_0x02da;
                case 5: goto L_0x02cc;
                case 6: goto L_0x02be;
                case 7: goto L_0x02b0;
                case 8: goto L_0x0286;
                case 9: goto L_0x025c;
                case 10: goto L_0x024a;
                case 11: goto L_0x023c;
                case 12: goto L_0x022e;
                default: goto L_0x0228;
            }
        L_0x0228:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r10)
            throw r0
        L_0x022e:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            short r0 = r8.getUnsignedByte(r11)
            r15.setUnsignedByte(r11, r0)
            long r11 = r11 + r16
            goto L_0x022e
        L_0x023c:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            java.lang.Object r0 = r8.get(r11)
            r15.set(r11, r0)
            long r11 = r11 + r16
            goto L_0x023c
        L_0x024a:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            java.lang.Object r0 = r8.get(r11)
            java.lang.String r0 = r0.toString()
            r15.set(r11, r0)
            long r11 = r11 + r16
            goto L_0x024a
        L_0x025c:
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r24.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            if (r0 != r1) goto L_0x0278
        L_0x0264:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            r0 = r15
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r0 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r0
            r1 = r8
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r1
            float[] r1 = r1.getComplexFloat(r11)
            r0.setComplexFloat(r11, r1)
            long r11 = r11 + r16
            goto L_0x0264
        L_0x0278:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            double r0 = r8.getDouble(r11)
            r15.setDouble(r11, r0)
            long r11 = r11 + r16
            goto L_0x0278
        L_0x0286:
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r24.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 != r1) goto L_0x02a2
        L_0x028e:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            r0 = r15
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r0 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r0
            r1 = r8
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r1
            double[] r1 = r1.getComplexDouble(r11)
            r0.setComplexDouble(r11, r1)
            long r11 = r11 + r16
            goto L_0x028e
        L_0x02a2:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            float r0 = r8.getFloat(r11)
            r15.setFloat(r11, r0)
            long r11 = r11 + r16
            goto L_0x02a2
        L_0x02b0:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            double r0 = r8.getDouble(r11)
            r15.setDouble(r11, r0)
            long r11 = r11 + r16
            goto L_0x02b0
        L_0x02be:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            float r0 = r8.getFloat(r11)
            r15.setFloat(r11, r0)
            long r11 = r11 + r16
            goto L_0x02be
        L_0x02cc:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            long r0 = r8.getLong(r11)
            r15.setLong(r11, r0)
            long r11 = r11 + r16
            goto L_0x02cc
        L_0x02da:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            int r0 = r8.getInt(r11)
            r15.setInt(r11, r0)
            long r11 = r11 + r16
            goto L_0x02da
        L_0x02e8:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            short r0 = r8.getShort(r11)
            r15.setShort(r11, r0)
            long r11 = r11 + r16
            goto L_0x02e8
        L_0x02f6:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            byte r0 = r8.getByte(r11)
            r15.setByte(r11, r0)
            long r11 = r11 + r16
            goto L_0x02f6
        L_0x0304:
            int[] r0 = pl.edu.icm.jlargearrays.LargeArrayUtils.AnonymousClass28.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType
            int r1 = r25.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L_0x03dd;
                case 2: goto L_0x03dd;
                case 3: goto L_0x03cf;
                case 4: goto L_0x03c1;
                case 5: goto L_0x03b3;
                case 6: goto L_0x03a5;
                case 7: goto L_0x0397;
                case 8: goto L_0x036d;
                case 9: goto L_0x0343;
                case 10: goto L_0x0331;
                case 11: goto L_0x0323;
                case 12: goto L_0x0315;
                default: goto L_0x030f;
            }
        L_0x030f:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r10)
            throw r0
        L_0x0315:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            short r0 = r8.getUnsignedByte(r11)
            r15.setUnsignedByte(r11, r0)
            long r11 = r11 + r16
            goto L_0x0315
        L_0x0323:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            java.lang.Object r0 = r8.get(r11)
            r15.set(r11, r0)
            long r11 = r11 + r16
            goto L_0x0323
        L_0x0331:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            java.lang.Object r0 = r8.get(r11)
            java.lang.String r0 = r0.toString()
            r15.set(r11, r0)
            long r11 = r11 + r16
            goto L_0x0331
        L_0x0343:
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r24.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_FLOAT
            if (r0 != r1) goto L_0x035f
        L_0x034b:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            r0 = r15
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r0 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r0
            r1 = r8
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r1
            float[] r1 = r1.getComplexFloat(r11)
            r0.setComplexFloat(r11, r1)
            long r11 = r11 + r16
            goto L_0x034b
        L_0x035f:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            double r0 = r8.getDouble(r11)
            r15.setDouble(r11, r0)
            long r11 = r11 + r16
            goto L_0x035f
        L_0x036d:
            pl.edu.icm.jlargearrays.LargeArrayType r0 = r24.getType()
            pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.COMPLEX_DOUBLE
            if (r0 != r1) goto L_0x0389
        L_0x0375:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            r0 = r15
            pl.edu.icm.jlargearrays.ComplexFloatLargeArray r0 = (pl.edu.icm.jlargearrays.ComplexFloatLargeArray) r0
            r1 = r8
            pl.edu.icm.jlargearrays.ComplexDoubleLargeArray r1 = (pl.edu.icm.jlargearrays.ComplexDoubleLargeArray) r1
            double[] r1 = r1.getComplexDouble(r11)
            r0.setComplexDouble(r11, r1)
            long r11 = r11 + r16
            goto L_0x0375
        L_0x0389:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            float r0 = r8.getFloat(r11)
            r15.setFloat(r11, r0)
            long r11 = r11 + r16
            goto L_0x0389
        L_0x0397:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            double r0 = r8.getDouble(r11)
            r15.setDouble(r11, r0)
            long r11 = r11 + r16
            goto L_0x0397
        L_0x03a5:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            float r0 = r8.getFloat(r11)
            r15.setFloat(r11, r0)
            long r11 = r11 + r16
            goto L_0x03a5
        L_0x03b3:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            long r0 = r8.getLong(r11)
            r15.setLong(r11, r0)
            long r11 = r11 + r16
            goto L_0x03b3
        L_0x03c1:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            int r0 = r8.getInt(r11)
            r15.setInt(r11, r0)
            long r11 = r11 + r16
            goto L_0x03c1
        L_0x03cf:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            short r0 = r8.getShort(r11)
            r15.setShort(r11, r0)
            long r11 = r11 + r16
            goto L_0x03cf
        L_0x03dd:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x03eb
            byte r0 = r8.getByte(r11)
            r15.setByte(r11, r0)
            long r11 = r11 + r16
            goto L_0x03dd
        L_0x03eb:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LargeArrayUtils.convert(pl.edu.icm.jlargearrays.LargeArray, pl.edu.icm.jlargearrays.LargeArrayType):pl.edu.icm.jlargearrays.LargeArray");
    }

    public static LargeArray select(LargeArray largeArray, LogicLargeArray logicLargeArray) {
        LargeArray largeArray2 = largeArray;
        LogicLargeArray logicLargeArray2 = logicLargeArray;
        if (largeArray2.length == logicLargeArray2.length) {
            long j = largeArray2.length;
            int min = (int) FastMath.min(j, (long) ConcurrencyUtils.getNumberOfThreads());
            long j2 = j / ((long) min);
            ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j3 = ((long) i) * j2;
                final long j4 = i == min + -1 ? j : j3 + j2;
                AnonymousClass27 r16 = r1;
                int i2 = i;
                final LogicLargeArray logicLargeArray3 = logicLargeArray;
                AnonymousClass27 r1 = new Callable() {
                    public Long call() {
                        long j = 0;
                        for (long j2 = j3; j2 < j4; j2++) {
                            if (logicLargeArray3.getByte(j2) == 1) {
                                j++;
                            }
                        }
                        return Long.valueOf(j);
                    }
                };
                futureArr[i2] = newCachedThreadPool.submit(r1);
                i = i2 + 1;
            }
            long j5 = 0;
            int i3 = 0;
            while (i3 < min) {
                try {
                    Long l = (Long) futureArr[i3].get();
                    Long l2 = l;
                    j5 += l.longValue();
                    i3++;
                } catch (Exception unused) {
                    for (long j6 = 0; j6 < j; j6++) {
                        if (logicLargeArray2.getByte(j6) == 1) {
                            j5++;
                        }
                    }
                }
            }
            if (j5 <= 0) {
                return null;
            }
            LargeArray create = create(largeArray.getType(), j5, false);
            long j7 = 0;
            for (long j8 = 0; j8 < j; j8++) {
                if (logicLargeArray2.getByte(j8) == 1) {
                    create.set(j7, largeArray2.get(j8));
                    j7++;
                }
            }
            return create;
        }
        throw new IllegalArgumentException("src.length != mask.length");
    }
}
