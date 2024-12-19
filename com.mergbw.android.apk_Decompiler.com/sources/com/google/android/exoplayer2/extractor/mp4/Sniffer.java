package com.google.android.exoplayer2.extractor.mp4;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;

final class Sniffer {
    public static final int BRAND_HEIC = 1751476579;
    public static final int BRAND_QUICKTIME = 1903435808;
    private static final int[] COMPATIBLE_BRANDS = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, Atom.TYPE_avc1, Atom.TYPE_hvc1, Atom.TYPE_hev1, Atom.TYPE_av01, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, BRAND_QUICKTIME, 1297305174, 1684175153, 1769172332, 1885955686};
    private static final int SEARCH_LENGTH = 4096;

    public static boolean sniffFragmented(ExtractorInput extractorInput) throws IOException {
        return sniffInternal(extractorInput, true, false);
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput) throws IOException {
        return sniffInternal(extractorInput, false, false);
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput, boolean z) throws IOException {
        return sniffInternal(extractorInput, false, z);
    }

    private static boolean sniffInternal(ExtractorInput extractorInput, boolean z, boolean z2) throws IOException {
        boolean z3;
        boolean z4;
        boolean z5;
        int i;
        boolean z6;
        ExtractorInput extractorInput2 = extractorInput;
        long length = extractorInput.getLength();
        long j = -1;
        int i2 = (length > -1 ? 1 : (length == -1 ? 0 : -1));
        long j2 = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        if (i2 != 0 && length <= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            j2 = length;
        }
        int i3 = (int) j2;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        boolean z7 = false;
        int i4 = 0;
        boolean z8 = false;
        while (true) {
            if (i4 >= i3) {
                break;
            }
            parsableByteArray.reset(8);
            if (!extractorInput2.peekFully(parsableByteArray.getData(), z7 ? 1 : 0, 8, true)) {
                break;
            }
            long readUnsignedInt = parsableByteArray.readUnsignedInt();
            int readInt = parsableByteArray.readInt();
            if (readUnsignedInt == 1) {
                extractorInput2.peekFully(parsableByteArray.getData(), 8, 8);
                parsableByteArray.setLimit(16);
                i = 16;
                readUnsignedInt = parsableByteArray.readLong();
            } else {
                if (readUnsignedInt == 0) {
                    long length2 = extractorInput.getLength();
                    if (length2 != j) {
                        readUnsignedInt = (length2 - extractorInput.getPeekPosition()) + ((long) 8);
                    }
                }
                i = 8;
            }
            long j3 = (long) i;
            if (readUnsignedInt < j3) {
                return z7;
            }
            i4 += i;
            if (readInt == 1836019574) {
                i3 += (int) readUnsignedInt;
                if (i2 != 0 && ((long) i3) > length) {
                    i3 = (int) length;
                }
            } else if (readInt == 1836019558 || readInt == 1836475768) {
                z4 = z7;
                z3 = true;
                z5 = true;
            } else {
                int i5 = i2;
                int i6 = i4;
                if ((((long) i4) + readUnsignedInt) - j3 >= ((long) i3)) {
                    z4 = false;
                    z3 = true;
                    break;
                }
                int i7 = (int) (readUnsignedInt - j3);
                i4 = i6 + i7;
                if (readInt != 1718909296) {
                    boolean z9 = z2;
                    z6 = false;
                    if (i7 != 0) {
                        extractorInput2.advancePeekPosition(i7);
                    }
                } else if (i7 < 8) {
                    return false;
                } else {
                    parsableByteArray.reset(i7);
                    extractorInput2.peekFully(parsableByteArray.getData(), 0, i7);
                    int i8 = i7 / 4;
                    int i9 = 0;
                    while (true) {
                        if (i9 >= i8) {
                            boolean z10 = z2;
                            break;
                        }
                        if (i9 == 1) {
                            parsableByteArray.skipBytes(4);
                            boolean z11 = z2;
                        } else if (isCompatibleBrand(parsableByteArray.readInt(), z2)) {
                            z8 = true;
                            break;
                        }
                        i9++;
                    }
                    if (!z8) {
                        return false;
                    }
                    z6 = false;
                }
                z7 = z6;
                i2 = i5;
            }
            j = -1;
        }
        z4 = z7;
        z3 = true;
        z5 = z4;
        return (!z8 || z != z5) ? z4 : z3;
    }

    private static boolean isCompatibleBrand(int i, boolean z) {
        if ((i >>> 8) == 3368816) {
            return true;
        }
        if (i == 1751476579 && z) {
            return true;
        }
        for (int i2 : COMPATIBLE_BRANDS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private Sniffer() {
    }
}
