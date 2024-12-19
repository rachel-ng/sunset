package com.mergbw.core.ble;

import com.clj.fastble.data.BleDevice;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SendCommandQueue {
    /* access modifiers changed from: private */
    public boolean canSendAudio = true;
    private final BlockingQueue<CommandData> dataQueue = new LinkedBlockingQueue(50);
    private int mtu = 20;
    /* access modifiers changed from: private */
    public boolean runSend = true;

    public SendCommandQueue() {
        new sendThread().start();
    }

    private class sendThread extends Thread {
        private sendThread() {
        }

        public void run() {
            super.run();
            while (SendCommandQueue.this.runSend) {
                CommandData take = SendCommandQueue.this.take();
                if (take != null) {
                    try {
                        boolean unused = SendCommandQueue.this.canSendAudio = false;
                        Thread.sleep(100);
                        RGBDeviceManager.getInstance().sendData(take.getBleDevice(), take.getData());
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                boolean unused2 = SendCommandQueue.this.canSendAudio = true;
            }
        }
    }

    public void put(CommandData commandData) {
        try {
            this.dataQueue.put(commandData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public CommandData take() {
        try {
            return this.dataQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendAudioData(BleDevice bleDevice, byte[] bArr) {
        if (this.canSendAudio) {
            RGBDeviceManager.getInstance().sendData(bleDevice, bArr);
        }
    }

    public int getSize() {
        return this.dataQueue.size();
    }

    public synchronized void clear() {
        this.dataQueue.clear();
    }

    public void stop() {
        this.runSend = false;
    }

    public int getMtu() {
        return this.mtu;
    }

    public void setMtu(int i) {
        this.mtu = i;
    }
}
