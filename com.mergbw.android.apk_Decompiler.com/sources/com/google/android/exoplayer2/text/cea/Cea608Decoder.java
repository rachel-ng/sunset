package com.google.android.exoplayer2.text.cea;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.internal.view.SupportMenu;
import androidx.core.location.LocationRequestCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.metadata.dvbsi.AppInfoTableDecoder;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.common.base.Ascii;
import com.mergbw.core.ble.CommandList;
import com.mergbw.core.network.ApiList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Cea608Decoder extends CeaDecoder {
    private static final int[] BASIC_CHARACTER_SET = {32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 225, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 97, 98, 99, 100, 101, 102, 103, LocationRequestCompat.QUALITY_LOW_POWER, 105, 106, 107, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 110, 111, 112, 113, 114, 115, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, ModuleDescriptor.MODULE_VERSION, 118, 119, 120, 121, 122, 231, 247, 209, 241, 9632};
    private static final int CC_FIELD_FLAG = 1;
    private static final byte CC_IMPLICIT_DATA_HEADER = -4;
    private static final int CC_MODE_PAINT_ON = 3;
    private static final int CC_MODE_POP_ON = 2;
    private static final int CC_MODE_ROLL_UP = 1;
    private static final int CC_MODE_UNKNOWN = 0;
    private static final int CC_TYPE_FLAG = 2;
    private static final int CC_VALID_FLAG = 4;
    private static final int[] COLUMN_INDICES = {0, 4, 8, 12, 16, 20, 24, 28};
    private static final byte CTRL_BACKSPACE = 33;
    private static final byte CTRL_CARRIAGE_RETURN = 45;
    private static final byte CTRL_DELETE_TO_END_OF_ROW = 36;
    private static final byte CTRL_END_OF_CAPTION = 47;
    private static final byte CTRL_ERASE_DISPLAYED_MEMORY = 44;
    private static final byte CTRL_ERASE_NON_DISPLAYED_MEMORY = 46;
    private static final byte CTRL_RESUME_CAPTION_LOADING = 32;
    private static final byte CTRL_RESUME_DIRECT_CAPTIONING = 41;
    private static final byte CTRL_RESUME_TEXT_DISPLAY = 43;
    private static final byte CTRL_ROLL_UP_CAPTIONS_2_ROWS = 37;
    private static final byte CTRL_ROLL_UP_CAPTIONS_3_ROWS = 38;
    private static final byte CTRL_ROLL_UP_CAPTIONS_4_ROWS = 39;
    private static final byte CTRL_TEXT_RESTART = 42;
    private static final int DEFAULT_CAPTIONS_ROW_COUNT = 4;
    public static final long MIN_DATA_CHANNEL_TIMEOUT_MS = 16000;
    private static final int NTSC_CC_CHANNEL_1 = 0;
    private static final int NTSC_CC_CHANNEL_2 = 1;
    private static final int NTSC_CC_FIELD_1 = 0;
    private static final int NTSC_CC_FIELD_2 = 1;
    private static final boolean[] ODD_PARITY_BYTE_TABLE = {false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false};
    private static final int[] ROW_INDICES = {11, 1, 3, 12, 14, 5, 7, 9};
    private static final int[] SPECIAL_CHARACTER_SET = {174, Opcodes.ARETURN, PsExtractor.PRIVATE_STREAM_1, 191, 8482, Opcodes.IF_ICMPGE, Opcodes.IF_ICMPGT, 9834, 224, 32, 232, 226, 234, 238, 244, 251};
    private static final int[] SPECIAL_ES_FR_CHARACTER_SET = {Opcodes.INSTANCEOF, ApiList.GET_VERSION_INFO, 211, 218, 220, 252, 8216, Opcodes.IF_ICMPLT, 42, 39, 8212, Opcodes.RET, 8480, 8226, 8220, 8221, 192, 194, Opcodes.IFNONNULL, 200, 202, 203, 235, 206, 207, 239, 212, 217, 249, 219, 171, Opcodes.NEW};
    private static final int[] SPECIAL_PT_DE_CHARACTER_SET = {195, 227, 205, 204, 236, 210, 242, 213, 245, 123, 125, 92, 94, 95, 124, 126, 196, 228, 214, 246, 223, Opcodes.IF_ACMPEQ, 164, 9474, 197, 229, 216, 248, 9484, 9488, 9492, 9496};
    /* access modifiers changed from: private */
    public static final int[] STYLE_COLORS = {-1, -16711936, -16776961, -16711681, SupportMenu.CATEGORY_MASK, InputDeviceCompat.SOURCE_ANY, -65281};
    private static final int STYLE_ITALICS = 7;
    private static final int STYLE_UNCHANGED = 8;
    private static final String TAG = "Cea608Decoder";
    private int captionMode;
    private int captionRowCount;
    private final ParsableByteArray ccData = new ParsableByteArray();
    private final ArrayList<CueBuilder> cueBuilders = new ArrayList<>();
    private List<Cue> cues;
    private int currentChannel = 0;
    private CueBuilder currentCueBuilder = new CueBuilder(0, 4);
    private boolean isCaptionValid;
    private boolean isInCaptionService;
    private long lastCueUpdateUs;
    private List<Cue> lastCues;
    private final int packetLength;
    private byte repeatableControlCc1;
    private byte repeatableControlCc2;
    private boolean repeatableControlSet;
    private final int selectedChannel;
    private final int selectedField;
    private final long validDataChannelTimeoutUs;

    private static int getChannel(byte b2) {
        return (b2 >> 3) & 1;
    }

    private static boolean isCtrlCode(byte b2) {
        return (b2 & 224) == 0;
    }

    private static boolean isExtendedWestEuropeanChar(byte b2, byte b3) {
        return (b2 & CommandList.CMD_CONFIG_COMPLETE_REQ) == 18 && (b3 & 224) == 32;
    }

    private static boolean isMidrowCtrlCode(byte b2, byte b3) {
        return (b2 & 247) == 17 && (b3 & 240) == 32;
    }

    private static boolean isMiscCode(byte b2, byte b3) {
        return (b2 & CommandList.CMD_CONFIG_COMPLETE_REQ) == 20 && (b3 & 240) == 32;
    }

    private static boolean isPreambleAddressCode(byte b2, byte b3) {
        return (b2 & 240) == 16 && (b3 & 192) == 64;
    }

    private static boolean isRepeatable(byte b2) {
        return (b2 & 240) == 16;
    }

    private static boolean isServiceSwitchCommand(byte b2) {
        return (b2 & CommandList.CMD_CONFIG_COMPLETE_REQ) == 20;
    }

    private static boolean isSpecialNorthAmericanChar(byte b2, byte b3) {
        return (b2 & 247) == 17 && (b3 & 240) == 48;
    }

    private static boolean isTabCtrlCode(byte b2, byte b3) {
        return (b2 & 247) == 23 && b3 >= 33 && b3 <= 35;
    }

    private static boolean isXdsControlCode(byte b2) {
        return 1 <= b2 && b2 <= 15;
    }

    public void release() {
    }

    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        return super.dequeueInputBuffer();
    }

    public /* bridge */ /* synthetic */ void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        super.queueInputBuffer(subtitleInputBuffer);
    }

    public /* bridge */ /* synthetic */ void setPositionUs(long j) {
        super.setPositionUs(j);
    }

    public Cea608Decoder(String str, int i, long j) {
        this.validDataChannelTimeoutUs = j > 0 ? j * 1000 : -9223372036854775807L;
        this.packetLength = MimeTypes.APPLICATION_MP4CEA608.equals(str) ? 2 : 3;
        if (i == 1) {
            this.selectedChannel = 0;
            this.selectedField = 0;
        } else if (i == 2) {
            this.selectedChannel = 1;
            this.selectedField = 0;
        } else if (i == 3) {
            this.selectedChannel = 0;
            this.selectedField = 1;
        } else if (i != 4) {
            Log.w(TAG, "Invalid channel. Defaulting to CC1.");
            this.selectedChannel = 0;
            this.selectedField = 0;
        } else {
            this.selectedChannel = 1;
            this.selectedField = 1;
        }
        setCaptionMode(0);
        resetCueBuilders();
        this.isInCaptionService = true;
        this.lastCueUpdateUs = C.TIME_UNSET;
    }

    public String getName() {
        return TAG;
    }

    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        setCaptionMode(0);
        setCaptionRowCount(4);
        resetCueBuilders();
        this.isCaptionValid = false;
        this.repeatableControlSet = false;
        this.repeatableControlCc1 = 0;
        this.repeatableControlCc2 = 0;
        this.currentChannel = 0;
        this.isInCaptionService = true;
        this.lastCueUpdateUs = C.TIME_UNSET;
    }

    public SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        SubtitleOutputBuffer availableOutputBuffer;
        SubtitleOutputBuffer dequeueOutputBuffer = super.dequeueOutputBuffer();
        if (dequeueOutputBuffer != null) {
            return dequeueOutputBuffer;
        }
        if (!shouldClearStuckCaptions() || (availableOutputBuffer = getAvailableOutputBuffer()) == null) {
            return null;
        }
        this.cues = Collections.emptyList();
        this.lastCueUpdateUs = C.TIME_UNSET;
        SubtitleOutputBuffer subtitleOutputBuffer = availableOutputBuffer;
        subtitleOutputBuffer.setContent(getPositionUs(), createSubtitle(), Long.MAX_VALUE);
        return availableOutputBuffer;
    }

    /* access modifiers changed from: protected */
    public boolean isNewSubtitleDataAvailable() {
        return this.cues != this.lastCues;
    }

    /* access modifiers changed from: protected */
    public Subtitle createSubtitle() {
        List<Cue> list = this.cues;
        this.lastCues = list;
        return new CeaSubtitle((List) Assertions.checkNotNull(list));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0017 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decode(com.google.android.exoplayer2.text.SubtitleInputBuffer r10) {
        /*
            r9 = this;
            java.nio.ByteBuffer r10 = r10.data
            java.lang.Object r10 = com.google.android.exoplayer2.util.Assertions.checkNotNull(r10)
            java.nio.ByteBuffer r10 = (java.nio.ByteBuffer) r10
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r9.ccData
            byte[] r1 = r10.array()
            int r10 = r10.limit()
            r0.reset(r1, r10)
            r10 = 0
            r0 = r10
        L_0x0017:
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r9.ccData
            int r1 = r1.bytesLeft()
            int r2 = r9.packetLength
            r3 = 1
            if (r1 < r2) goto L_0x00f5
            r1 = 2
            if (r2 != r1) goto L_0x0027
            r1 = -4
            goto L_0x002d
        L_0x0027:
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r9.ccData
            int r1 = r1.readUnsignedByte()
        L_0x002d:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r9.ccData
            int r2 = r2.readUnsignedByte()
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r9.ccData
            int r4 = r4.readUnsignedByte()
            r5 = r1 & 2
            if (r5 == 0) goto L_0x003e
            goto L_0x0017
        L_0x003e:
            r5 = r1 & 1
            int r6 = r9.selectedField
            if (r5 == r6) goto L_0x0045
            goto L_0x0017
        L_0x0045:
            r5 = r2 & 127(0x7f, float:1.78E-43)
            byte r5 = (byte) r5
            r6 = r4 & 127(0x7f, float:1.78E-43)
            byte r6 = (byte) r6
            if (r5 != 0) goto L_0x0050
            if (r6 != 0) goto L_0x0050
            goto L_0x0017
        L_0x0050:
            boolean r7 = r9.isCaptionValid
            r1 = r1 & 4
            r8 = 4
            if (r1 != r8) goto L_0x0063
            boolean[] r1 = ODD_PARITY_BYTE_TABLE
            boolean r2 = r1[r2]
            if (r2 == 0) goto L_0x0063
            boolean r1 = r1[r4]
            if (r1 == 0) goto L_0x0063
            r1 = r3
            goto L_0x0064
        L_0x0063:
            r1 = r10
        L_0x0064:
            r9.isCaptionValid = r1
            boolean r1 = r9.isRepeatedCommand(r1, r5, r6)
            if (r1 == 0) goto L_0x006d
            goto L_0x0017
        L_0x006d:
            boolean r1 = r9.isCaptionValid
            if (r1 != 0) goto L_0x0078
            if (r7 == 0) goto L_0x0017
            r9.resetCueBuilders()
        L_0x0076:
            r0 = r3
            goto L_0x0017
        L_0x0078:
            r9.maybeUpdateIsInCaptionService(r5, r6)
            boolean r1 = r9.isInCaptionService
            if (r1 != 0) goto L_0x0080
            goto L_0x0017
        L_0x0080:
            boolean r1 = r9.updateAndVerifyCurrentChannel(r5)
            if (r1 != 0) goto L_0x0087
            goto L_0x0017
        L_0x0087:
            boolean r0 = isCtrlCode(r5)
            if (r0 == 0) goto L_0x00de
            boolean r0 = isSpecialNorthAmericanChar(r5, r6)
            if (r0 == 0) goto L_0x009d
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r9.currentCueBuilder
            char r1 = getSpecialNorthAmericanChar(r6)
            r0.append(r1)
            goto L_0x0076
        L_0x009d:
            boolean r0 = isExtendedWestEuropeanChar(r5, r6)
            if (r0 == 0) goto L_0x00b2
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r9.currentCueBuilder
            r0.backspace()
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r9.currentCueBuilder
            char r1 = getExtendedWestEuropeanChar(r5, r6)
            r0.append(r1)
            goto L_0x0076
        L_0x00b2:
            boolean r0 = isMidrowCtrlCode(r5, r6)
            if (r0 == 0) goto L_0x00bc
            r9.handleMidrowCtrl(r6)
            goto L_0x0076
        L_0x00bc:
            boolean r0 = isPreambleAddressCode(r5, r6)
            if (r0 == 0) goto L_0x00c6
            r9.handlePreambleAddressCode(r5, r6)
            goto L_0x0076
        L_0x00c6:
            boolean r0 = isTabCtrlCode(r5, r6)
            if (r0 == 0) goto L_0x00d4
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r9.currentCueBuilder
            int r6 = r6 + -32
            int unused = r0.tabOffset = r6
            goto L_0x0076
        L_0x00d4:
            boolean r0 = isMiscCode(r5, r6)
            if (r0 == 0) goto L_0x0076
            r9.handleMiscCode(r6)
            goto L_0x0076
        L_0x00de:
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r9.currentCueBuilder
            char r1 = getBasicChar(r5)
            r0.append(r1)
            r0 = r6 & 224(0xe0, float:3.14E-43)
            if (r0 == 0) goto L_0x0076
            com.google.android.exoplayer2.text.cea.Cea608Decoder$CueBuilder r0 = r9.currentCueBuilder
            char r1 = getBasicChar(r6)
            r0.append(r1)
            goto L_0x0076
        L_0x00f5:
            if (r0 == 0) goto L_0x010a
            int r10 = r9.captionMode
            if (r10 == r3) goto L_0x00fe
            r0 = 3
            if (r10 != r0) goto L_0x010a
        L_0x00fe:
            java.util.List r10 = r9.getDisplayCues()
            r9.cues = r10
            long r0 = r9.getPositionUs()
            r9.lastCueUpdateUs = r0
        L_0x010a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.cea.Cea608Decoder.decode(com.google.android.exoplayer2.text.SubtitleInputBuffer):void");
    }

    private boolean updateAndVerifyCurrentChannel(byte b2) {
        if (isCtrlCode(b2)) {
            this.currentChannel = getChannel(b2);
        }
        return this.currentChannel == this.selectedChannel;
    }

    private boolean isRepeatedCommand(boolean z, byte b2, byte b3) {
        if (!z || !isRepeatable(b2)) {
            this.repeatableControlSet = false;
        } else if (this.repeatableControlSet && this.repeatableControlCc1 == b2 && this.repeatableControlCc2 == b3) {
            this.repeatableControlSet = false;
            return true;
        } else {
            this.repeatableControlSet = true;
            this.repeatableControlCc1 = b2;
            this.repeatableControlCc2 = b3;
        }
        return false;
    }

    private void handleMidrowCtrl(byte b2) {
        this.currentCueBuilder.append(' ');
        this.currentCueBuilder.setStyle((b2 >> 1) & 7, (b2 & 1) == 1);
    }

    private void handlePreambleAddressCode(byte b2, byte b3) {
        int i = ROW_INDICES[b2 & 7];
        if ((b3 & 32) != 0) {
            i++;
        }
        if (i != this.currentCueBuilder.row) {
            if (this.captionMode != 1 && !this.currentCueBuilder.isEmpty()) {
                CueBuilder cueBuilder = new CueBuilder(this.captionMode, this.captionRowCount);
                this.currentCueBuilder = cueBuilder;
                this.cueBuilders.add(cueBuilder);
            }
            int unused = this.currentCueBuilder.row = i;
        }
        boolean z = false;
        boolean z2 = (b3 & 16) == 16;
        if ((b3 & 1) == 1) {
            z = true;
        }
        int i2 = (b3 >> 1) & 7;
        this.currentCueBuilder.setStyle(z2 ? 8 : i2, z);
        if (z2) {
            int unused2 = this.currentCueBuilder.indent = COLUMN_INDICES[i2];
        }
    }

    private void handleMiscCode(byte b2) {
        if (b2 == 32) {
            setCaptionMode(2);
        } else if (b2 != 41) {
            switch (b2) {
                case 37:
                    setCaptionMode(1);
                    setCaptionRowCount(2);
                    return;
                case 38:
                    setCaptionMode(1);
                    setCaptionRowCount(3);
                    return;
                case 39:
                    setCaptionMode(1);
                    setCaptionRowCount(4);
                    return;
                default:
                    int i = this.captionMode;
                    if (i != 0) {
                        if (b2 != 33) {
                            switch (b2) {
                                case 44:
                                    this.cues = Collections.emptyList();
                                    int i2 = this.captionMode;
                                    if (i2 == 1 || i2 == 3) {
                                        resetCueBuilders();
                                        return;
                                    }
                                    return;
                                case 45:
                                    if (i == 1 && !this.currentCueBuilder.isEmpty()) {
                                        this.currentCueBuilder.rollUp();
                                        return;
                                    }
                                    return;
                                case 46:
                                    resetCueBuilders();
                                    return;
                                case 47:
                                    this.cues = getDisplayCues();
                                    resetCueBuilders();
                                    return;
                                default:
                                    return;
                            }
                        } else {
                            this.currentCueBuilder.backspace();
                            return;
                        }
                    } else {
                        return;
                    }
            }
        } else {
            setCaptionMode(3);
        }
    }

    private List<Cue> getDisplayCues() {
        int size = this.cueBuilders.size();
        ArrayList arrayList = new ArrayList(size);
        int i = 2;
        for (int i2 = 0; i2 < size; i2++) {
            Cue build = this.cueBuilders.get(i2).build(Integer.MIN_VALUE);
            arrayList.add(build);
            if (build != null) {
                i = Math.min(i, build.positionAnchor);
            }
        }
        ArrayList arrayList2 = new ArrayList(size);
        for (int i3 = 0; i3 < size; i3++) {
            Cue cue = (Cue) arrayList.get(i3);
            if (cue != null) {
                if (cue.positionAnchor != i) {
                    cue = (Cue) Assertions.checkNotNull(this.cueBuilders.get(i3).build(i));
                }
                arrayList2.add(cue);
            }
        }
        return arrayList2;
    }

    private void setCaptionMode(int i) {
        int i2 = this.captionMode;
        if (i2 != i) {
            this.captionMode = i;
            if (i == 3) {
                for (int i3 = 0; i3 < this.cueBuilders.size(); i3++) {
                    this.cueBuilders.get(i3).setCaptionMode(i);
                }
                return;
            }
            resetCueBuilders();
            if (i2 == 3 || i == 1 || i == 0) {
                this.cues = Collections.emptyList();
            }
        }
    }

    private void setCaptionRowCount(int i) {
        this.captionRowCount = i;
        this.currentCueBuilder.setCaptionRowCount(i);
    }

    private void resetCueBuilders() {
        this.currentCueBuilder.reset(this.captionMode);
        this.cueBuilders.clear();
        this.cueBuilders.add(this.currentCueBuilder);
    }

    private void maybeUpdateIsInCaptionService(byte b2, byte b3) {
        if (isXdsControlCode(b2)) {
            this.isInCaptionService = false;
        } else if (isServiceSwitchCommand(b2)) {
            if (!(b3 == 32 || b3 == 47)) {
                switch (b3) {
                    case 37:
                    case 38:
                    case 39:
                        break;
                    default:
                        switch (b3) {
                            case 41:
                                break;
                            case 42:
                            case 43:
                                this.isInCaptionService = false;
                                return;
                            default:
                                return;
                        }
                }
            }
            this.isInCaptionService = true;
        }
    }

    private static char getBasicChar(byte b2) {
        return (char) BASIC_CHARACTER_SET[(b2 & Byte.MAX_VALUE) - 32];
    }

    private static char getSpecialNorthAmericanChar(byte b2) {
        return (char) SPECIAL_CHARACTER_SET[b2 & 15];
    }

    private static char getExtendedWestEuropeanChar(byte b2, byte b3) {
        if ((b2 & 1) == 0) {
            return getExtendedEsFrChar(b3);
        }
        return getExtendedPtDeChar(b3);
    }

    private static char getExtendedEsFrChar(byte b2) {
        return (char) SPECIAL_ES_FR_CHARACTER_SET[b2 & Ascii.US];
    }

    private static char getExtendedPtDeChar(byte b2) {
        return (char) SPECIAL_PT_DE_CHARACTER_SET[b2 & Ascii.US];
    }

    private static final class CueBuilder {
        private static final int BASE_ROW = 15;
        private static final int SCREEN_CHARWIDTH = 32;
        private int captionMode;
        private int captionRowCount;
        private final StringBuilder captionStringBuilder = new StringBuilder();
        private final List<CueStyle> cueStyles = new ArrayList();
        /* access modifiers changed from: private */
        public int indent;
        private final List<SpannableString> rolledUpCaptions = new ArrayList();
        /* access modifiers changed from: private */
        public int row;
        /* access modifiers changed from: private */
        public int tabOffset;

        public CueBuilder(int i, int i2) {
            reset(i);
            this.captionRowCount = i2;
        }

        public void reset(int i) {
            this.captionMode = i;
            this.cueStyles.clear();
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.setLength(0);
            this.row = 15;
            this.indent = 0;
            this.tabOffset = 0;
        }

        public boolean isEmpty() {
            return this.cueStyles.isEmpty() && this.rolledUpCaptions.isEmpty() && this.captionStringBuilder.length() == 0;
        }

        public void setCaptionMode(int i) {
            this.captionMode = i;
        }

        public void setCaptionRowCount(int i) {
            this.captionRowCount = i;
        }

        public void setStyle(int i, boolean z) {
            this.cueStyles.add(new CueStyle(i, z, this.captionStringBuilder.length()));
        }

        public void backspace() {
            int length = this.captionStringBuilder.length();
            if (length > 0) {
                this.captionStringBuilder.delete(length - 1, length);
                int size = this.cueStyles.size() - 1;
                while (size >= 0) {
                    CueStyle cueStyle = this.cueStyles.get(size);
                    if (cueStyle.start == length) {
                        cueStyle.start--;
                        size--;
                    } else {
                        return;
                    }
                }
            }
        }

        public void append(char c2) {
            if (this.captionStringBuilder.length() < 32) {
                this.captionStringBuilder.append(c2);
            }
        }

        public void rollUp() {
            this.rolledUpCaptions.add(buildCurrentLine());
            this.captionStringBuilder.setLength(0);
            this.cueStyles.clear();
            int min = Math.min(this.captionRowCount, this.row);
            while (this.rolledUpCaptions.size() >= min) {
                this.rolledUpCaptions.remove(0);
            }
        }

        public Cue build(int i) {
            float f;
            int i2 = this.indent + this.tabOffset;
            int i3 = 32 - i2;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i4 = 0; i4 < this.rolledUpCaptions.size(); i4++) {
                spannableStringBuilder.append(Util.truncateAscii(this.rolledUpCaptions.get(i4), i3));
                spannableStringBuilder.append(10);
            }
            spannableStringBuilder.append(Util.truncateAscii(buildCurrentLine(), i3));
            if (spannableStringBuilder.length() == 0) {
                return null;
            }
            int length = i3 - spannableStringBuilder.length();
            int i5 = i2 - length;
            if (i == Integer.MIN_VALUE) {
                if (this.captionMode != 2 || (Math.abs(i5) >= 3 && length >= 0)) {
                    i = (this.captionMode != 2 || i5 <= 0) ? 0 : 2;
                } else {
                    i = 1;
                }
            }
            if (i != 1) {
                if (i == 2) {
                    i2 = 32 - length;
                }
                f = ((((float) i2) / 32.0f) * 0.8f) + 0.1f;
            } else {
                f = 0.5f;
            }
            int i6 = this.row;
            if (i6 > 7) {
                i6 -= 17;
            } else if (this.captionMode == 1) {
                i6 -= this.captionRowCount - 1;
            }
            return new Cue.Builder().setText(spannableStringBuilder).setTextAlignment(Layout.Alignment.ALIGN_NORMAL).setLine((float) i6, 1).setPosition(f).setPositionAnchor(i).build();
        }

        private SpannableString buildCurrentLine() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.captionStringBuilder);
            int length = spannableStringBuilder.length();
            int i = -1;
            int i2 = -1;
            int i3 = -1;
            int i4 = -1;
            int i5 = 0;
            int i6 = 0;
            boolean z = false;
            while (i5 < this.cueStyles.size()) {
                CueStyle cueStyle = this.cueStyles.get(i5);
                boolean z2 = cueStyle.underline;
                int i7 = cueStyle.style;
                if (i7 != 8) {
                    boolean z3 = i7 == 7;
                    if (i7 != 7) {
                        i4 = Cea608Decoder.STYLE_COLORS[i7];
                    }
                    z = z3;
                }
                int i8 = cueStyle.start;
                i5++;
                if (i8 != (i5 < this.cueStyles.size() ? this.cueStyles.get(i5).start : length)) {
                    if (i != -1 && !z2) {
                        setUnderlineSpan(spannableStringBuilder, i, i8);
                        i = -1;
                    } else if (i == -1 && z2) {
                        i = i8;
                    }
                    if (i2 != -1 && !z) {
                        setItalicSpan(spannableStringBuilder, i2, i8);
                        i2 = -1;
                    } else if (i2 == -1 && z) {
                        i2 = i8;
                    }
                    if (i4 != i3) {
                        setColorSpan(spannableStringBuilder, i6, i8, i3);
                        i3 = i4;
                        i6 = i8;
                    }
                }
            }
            if (!(i == -1 || i == length)) {
                setUnderlineSpan(spannableStringBuilder, i, length);
            }
            if (!(i2 == -1 || i2 == length)) {
                setItalicSpan(spannableStringBuilder, i2, length);
            }
            if (i6 != length) {
                setColorSpan(spannableStringBuilder, i6, length, i3);
            }
            return new SpannableString(spannableStringBuilder);
        }

        private static void setUnderlineSpan(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
        }

        private static void setItalicSpan(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
            spannableStringBuilder.setSpan(new StyleSpan(2), i, i2, 33);
        }

        private static void setColorSpan(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3) {
            if (i3 != -1) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(i3), i, i2, 33);
            }
        }

        private static class CueStyle {
            public int start;
            public final int style;
            public final boolean underline;

            public CueStyle(int i, boolean z, int i2) {
                this.style = i;
                this.underline = z;
                this.start = i2;
            }
        }
    }

    private boolean shouldClearStuckCaptions() {
        if (this.validDataChannelTimeoutUs == C.TIME_UNSET || this.lastCueUpdateUs == C.TIME_UNSET || getPositionUs() - this.lastCueUpdateUs < this.validDataChannelTimeoutUs) {
            return false;
        }
        return true;
    }
}
