package com.mergbw.core.ui;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import com.clj.fastble.BleManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mergbw.core.R;
import com.mergbw.core.event.EventMessage;
import com.mergbw.core.event.GlobalEvent;
import com.mergbw.core.ui.views.ConfirmDialog;
import com.mergbw.core.utils.MeRGBWLog;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import pub.devrel.easypermissions.EasyPermissions;

public class BaseBluetoothActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    private static final int OPEN_BLUETOOTH = 200;
    private static final int OPEN_GPS = 300;
    private static final int REQUEST_BLUE_PERMISSION = 400;
    private static final int REQUEST_LOCATION_PERMISSION = 500;

    /* access modifiers changed from: protected */
    public void bluetoothReady() {
    }

    public void onPermissionsDenied(int i, List<String> list) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EventBus.getDefault().register(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void initBluetooth() {
        if (Build.VERSION.SDK_INT >= 31) {
            checkBluePermission();
        } else {
            checkLocationPermission();
        }
    }

    private void checkLocationPermission() {
        String[] strArr = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        if (!EasyPermissions.hasPermissions(this, strArr)) {
            new ConfirmDialog(this.mContext, getString(R.string.ble_need_location_title), getString(R.string.ble_need_location_content), getString(17039360), getString(17039370), new BaseBluetoothActivity$$ExternalSyntheticLambda0(this, strArr)).show();
        }
        checkGPS();
        checkBluetooth();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$checkLocationPermission$0$com-mergbw-core-ui-BaseBluetoothActivity  reason: not valid java name */
    public /* synthetic */ void m916lambda$checkLocationPermission$0$commergbwcoreuiBaseBluetoothActivity(String[] strArr, boolean z) {
        if (z) {
            EasyPermissions.requestPermissions((Activity) this, getString(R.string.ble_need_location_content), (int) REQUEST_LOCATION_PERMISSION, strArr);
        }
    }

    private void checkGPS() {
        LocationManager locationManager = (LocationManager) getSystemService(FirebaseAnalytics.Param.LOCATION);
        boolean isProviderEnabled = locationManager.isProviderEnabled("gps");
        boolean isProviderEnabled2 = locationManager.isProviderEnabled("network");
        if (!isProviderEnabled && !isProviderEnabled2) {
            startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 300);
        }
    }

    private void checkBluePermission() {
        String[] strArr = {"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"};
        if (!EasyPermissions.hasPermissions(this, strArr)) {
            new ConfirmDialog(this.mContext, getString(R.string.permission_scan), getString(R.string.permission_scan), getString(17039360), getString(17039370), new BaseBluetoothActivity$$ExternalSyntheticLambda1(this, strArr)).show();
        } else {
            checkBluetooth();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$checkBluePermission$1$com-mergbw-core-ui-BaseBluetoothActivity  reason: not valid java name */
    public /* synthetic */ void m914lambda$checkBluePermission$1$commergbwcoreuiBaseBluetoothActivity(String[] strArr, boolean z) {
        if (z) {
            EasyPermissions.requestPermissions((Activity) this, getString(R.string.permission_scan), (int) REQUEST_BLUE_PERMISSION, strArr);
        }
    }

    private void checkBluetooth() {
        if (!BleManager.getInstance().isSupportBle()) {
            showToast(getString(R.string.not_support_ble));
        } else if (!BleManager.getInstance().isBlueEnable()) {
            new ConfirmDialog(this.mContext, getString(R.string.need_open_bluetooth_title), getString(R.string.need_open_bluetooth_content), getString(17039360), getString(17039370), new BaseBluetoothActivity$$ExternalSyntheticLambda2(this)).show();
        } else {
            bluetoothReady();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$checkBluetooth$2$com-mergbw-core-ui-BaseBluetoothActivity  reason: not valid java name */
    public /* synthetic */ void m915lambda$checkBluetooth$2$commergbwcoreuiBaseBluetoothActivity(boolean z) {
        if (z) {
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 200);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        EasyPermissions.onRequestPermissionsResult(i, strArr, iArr, this);
    }

    public void onPermissionsGranted(int i, List<String> list) {
        if (i == REQUEST_BLUE_PERMISSION) {
            checkBluetooth();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage eventMessage) {
        MeRGBWLog.e("BaseBluetoothActivity onEvent: " + eventMessage.getAction());
        if (GlobalEvent.BLUETOOTH_ON.equals(eventMessage.getAction())) {
            bluetoothReady();
        }
    }
}
