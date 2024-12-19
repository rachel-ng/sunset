package com.mergbw.android.task;

import android.graphics.Color;
import android.media.AudioRecord;
import android.util.Log;
import org.jtransforms.fft.DoubleFFT_1D;

public class AudioRecordTask implements Runnable {
    private static final int FFT_SIZE = 640;
    private static final String TAG = "AudioRecordTask";
    private static final int audioFormat = 3;
    private static final int audioSource = 1;
    private static final int inputChannelConfig = 16;
    private static final int sampleRateInHz = 16000;
    private boolean isRecording = true;
    private final int mAudioBufferSize;
    private AudioRecord mAudioRecord;
    private int mAudioSensitive;
    private final DoubleFFT_1D mFFT;
    private float mHue;
    private final IAudioRecordListener mListener;

    public AudioRecordTask(int i, float f, IAudioRecordListener iAudioRecordListener) {
        setAudioSensitive(i);
        this.mHue = f;
        this.mListener = iAudioRecordListener;
        int minBufferSize = AudioRecord.getMinBufferSize(16000, 16, 3);
        this.mAudioBufferSize = minBufferSize;
        this.mAudioRecord = new AudioRecord(1, 16000, 16, 3, minBufferSize);
        this.mFFT = new DoubleFFT_1D(640);
    }

    public void run() {
        while (this.isRecording) {
            if (this.mAudioRecord == null) {
                this.mAudioRecord = new AudioRecord(1, 16000, 16, 3, this.mAudioBufferSize);
            }
            if (this.mAudioRecord.getRecordingState() == 1) {
                this.mAudioRecord.startRecording();
            }
            int i = this.mAudioBufferSize;
            byte[] bArr = new byte[i];
            if (this.mAudioRecord.read(bArr, 0, i) > 0) {
                toHSV(bArr);
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void toHSV(byte[] bArr) {
        double d;
        byte[] bArr2 = bArr;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (byte abs : bArr2) {
            float abs2 = (float) Math.abs(abs);
            f2 += abs2;
            f3 = Math.max(f3, abs2);
        }
        float length = (f3 - (f2 / ((float) bArr2.length))) / f3;
        if (((double) length) >= ((double) (100 - this.mAudioSensitive)) * 1.7E-4d) {
            f = 5.0f * length;
        }
        double[] dArr = new double[1280];
        int length2 = bArr2.length;
        int i = FFT_SIZE;
        if (length2 < FFT_SIZE) {
            i = bArr2.length;
        }
        int i2 = 0;
        while (true) {
            d = 0.0d;
            if (i2 >= i) {
                break;
            }
            int i3 = i2 * 2;
            dArr[i3] = (double) bArr2[i2];
            dArr[i3 + 1] = 0.0d;
            i2++;
        }
        this.mFFT.realForward(dArr);
        double[] dArr2 = new double[320];
        for (int i4 = 0; i4 < 320; i4++) {
            int i5 = i4 * 2;
            double d2 = dArr[i5];
            double d3 = dArr[i5 + 1];
            dArr2[i4] = Math.sqrt((d2 * d2) + (d3 * d3));
        }
        double d4 = 0.0d;
        double d5 = 0.0d;
        int i6 = 0;
        while (i6 < 320) {
            double d6 = dArr2[i6];
            d += Math.abs(d6);
            d4 = Math.min(d4, Math.abs(d6));
            d5 = Math.max(d5, Math.abs(d6));
            i6++;
            dArr2 = dArr2;
        }
        float abs3 = (float) (Math.abs(((d / ((double) 320)) - d4) / (d5 - d4)) * 10.0d);
        Log.i(TAG, "toHSV: " + abs3 + " " + f);
        IAudioRecordListener iAudioRecordListener = this.mListener;
        if (iAudioRecordListener != null) {
            float[] fArr = new float[3];
            float f4 = this.mHue;
            if (f4 == -1.0f) {
                fArr[0] = 360.0f * f;
            } else {
                fArr[0] = f4;
            }
            fArr[1] = abs3;
            fArr[2] = f;
            iAudioRecordListener.onColorResult(Color.HSVToColor(fArr));
        }
    }

    public void setAudioSensitive(int i) {
        if (i < 10) {
            i = 10;
        }
        if (i > 90) {
            i = 90;
        }
        this.mAudioSensitive = i;
    }

    public void setHue(float f) {
        this.mHue = f;
    }

    public void release() {
        this.isRecording = false;
        this.mAudioRecord.stop();
        this.mAudioRecord.release();
        this.mAudioRecord = null;
    }
}
