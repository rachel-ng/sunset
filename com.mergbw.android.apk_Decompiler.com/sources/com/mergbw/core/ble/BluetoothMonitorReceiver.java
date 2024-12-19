package com.mergbw.core.ble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.mergbw.core.event.EventMessage;
import com.mergbw.core.event.GlobalEvent;
import com.mergbw.core.utils.MeRGBWLog;
import org.greenrobot.eventbus.EventBus;

public class BluetoothMonitorReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        MeRGBWLog.i("BluetoothMonitorReceiver action: " + action);
        if (action != null && action.equals("android.bluetooth.adapter.action.STATE_CHANGED") && intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0) == 12) {
            EventBus.getDefault().post(new EventMessage(GlobalEvent.BLUETOOTH_ON));
        }
    }
}
