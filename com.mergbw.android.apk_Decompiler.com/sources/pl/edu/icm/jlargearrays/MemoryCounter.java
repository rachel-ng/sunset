package pl.edu.icm.jlargearrays;

public class MemoryCounter {
    private static long counter;

    private MemoryCounter() {
    }

    public static long getCounter() {
        return counter;
    }

    public static void increaseCounter(long j) {
        counter += j;
    }

    public static void decreaseCounter(long j) {
        long j2 = counter - j;
        counter = j2;
        if (j2 < 0) {
            counter = 0;
        }
    }
}
