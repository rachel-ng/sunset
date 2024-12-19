package com.mergbw.core.ble;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TotalCommandDataQueue {
    private static TotalCommandDataQueue sendDataQueue;
    private final BlockingQueue<CommandData> mDataQueue = new LinkedBlockingQueue(100);

    private TotalCommandDataQueue() {
    }

    public static TotalCommandDataQueue getInstance() {
        if (sendDataQueue == null) {
            sendDataQueue = new TotalCommandDataQueue();
        }
        return sendDataQueue;
    }

    public void put(CommandData commandData) {
        try {
            this.mDataQueue.put(commandData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public CommandData take() {
        try {
            return this.mDataQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getSize() {
        return this.mDataQueue.size();
    }

    public synchronized void clear() {
        this.mDataQueue.clear();
    }
}
