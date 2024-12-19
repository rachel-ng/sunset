package com.ota66.sdk;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.ota66.sdk.ble.OTACallBack;
import com.ota66.sdk.firware.UpdateFirewareCallBack;

public class OTASDKUtils {
    /* access modifiers changed from: private */
    public int APP2OTA = 7;
    /* access modifiers changed from: private */
    public int OTA_CONNECTING = 2;
    /* access modifiers changed from: private */
    public int OTA_ING = 3;
    /* access modifiers changed from: private */
    public int REBOOT = 4;
    /* access modifiers changed from: private */
    public int RES_ING = 6;
    /* access modifiers changed from: private */
    public int START_OTA = 1;
    /* access modifiers changed from: private */
    public int START_RES = 5;
    /* access modifiers changed from: private */
    public int STATUS = 0;
    /* access modifiers changed from: private */
    public String address;
    /* access modifiers changed from: private */
    public String filePath;
    /* access modifiers changed from: private */
    public UpdateFirewareCallBack firewareCallBack;
    /* access modifiers changed from: private */
    public boolean isQuick;
    private OTACallBack otaCallBack;
    /* access modifiers changed from: private */
    public a.b otaType;
    /* access modifiers changed from: private */
    public OTAUtils otaUtils;

    private class b implements OTACallBack {
        private b() {
        }

        public void onConnected(boolean z) {
            if (z) {
                boolean unused = OTASDKUtils.this.isQuick = OTAUtils.MTU_SIZE > 23;
                Log.e("TAG", "onConnected: STATUS:" + OTASDKUtils.this.STATUS + ",otaType:" + OTASDKUtils.this.otaType);
                if (OTASDKUtils.this.STATUS == 0) {
                    if (OTASDKUtils.this.otaType == a.b.OTA) {
                        OTASDKUtils oTASDKUtils = OTASDKUtils.this;
                        int unused2 = oTASDKUtils.STATUS = oTASDKUtils.START_OTA;
                    } else if (OTASDKUtils.this.otaType == a.b.RESOURCE) {
                        OTASDKUtils oTASDKUtils2 = OTASDKUtils.this;
                        int unused3 = oTASDKUtils2.STATUS = oTASDKUtils2.START_RES;
                    } else if (OTASDKUtils.this.otaType == a.b.Security) {
                        OTASDKUtils oTASDKUtils3 = OTASDKUtils.this;
                        int unused4 = oTASDKUtils3.STATUS = oTASDKUtils3.RES_ING;
                    }
                } else if (OTASDKUtils.this.otaType == a.b.OTA && OTASDKUtils.this.STATUS == OTASDKUtils.this.START_OTA) {
                    OTASDKUtils.this.otaUtils.startOTA();
                } else if (OTASDKUtils.this.otaType == a.b.Security && OTASDKUtils.this.STATUS == OTASDKUtils.this.RES_ING) {
                    OTASDKUtils.this.otaUtils.startSecurity();
                } else if (OTASDKUtils.this.otaType == a.b.RESOURCE && OTASDKUtils.this.STATUS == OTASDKUtils.this.START_RES) {
                    OTASDKUtils.this.otaUtils.startResource();
                } else if (OTASDKUtils.this.STATUS == OTASDKUtils.this.OTA_CONNECTING) {
                    OTASDKUtils oTASDKUtils4 = OTASDKUtils.this;
                    int unused5 = oTASDKUtils4.STATUS = oTASDKUtils4.APP2OTA;
                } else if (OTASDKUtils.this.STATUS == OTASDKUtils.this.APP2OTA) {
                    Log.e("TAG", "onConnected: isQuick:" + OTASDKUtils.this.isQuick);
                    Log.e("TAG", "onConnected: 222222222222222");
                    OTASDKUtils.this.startOta();
                } else {
                    Log.d("STATUS", "error:" + OTASDKUtils.this.STATUS);
                }
            } else if (OTASDKUtils.this.STATUS == OTASDKUtils.this.START_OTA || OTASDKUtils.this.STATUS == OTASDKUtils.this.START_RES || OTASDKUtils.this.STATUS == OTASDKUtils.this.RES_ING) {
                Log.e("TAG", "onConnected: 断开连接，重新扫描");
                OTASDKUtils.this.otaUtils.starScan();
            } else if (OTASDKUtils.this.STATUS == OTASDKUtils.this.OTA_CONNECTING) {
                OTASDKUtils.this.error(1001);
            } else if (OTASDKUtils.this.STATUS == OTASDKUtils.this.REBOOT) {
                OTASDKUtils.this.success();
            } else if (OTASDKUtils.this.STATUS == OTASDKUtils.this.OTA_ING) {
                OTASDKUtils.this.error(1001);
            } else {
                OTASDKUtils.this.error(1006);
            }
        }

        public void onDeviceSearch(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            if ((OTASDKUtils.this.STATUS == OTASDKUtils.this.START_OTA || OTASDKUtils.this.STATUS == OTASDKUtils.this.START_RES || OTASDKUtils.this.STATUS == OTASDKUtils.this.RES_ING) && bluetoothDevice.getAddress().equals(c.b.a(OTASDKUtils.this.address))) {
                OTASDKUtils.this.otaUtils.stopScan();
                OTASDKUtils.this.otaUtils.connectDevice(bluetoothDevice.getAddress());
                OTASDKUtils oTASDKUtils = OTASDKUtils.this;
                int unused = oTASDKUtils.STATUS = oTASDKUtils.OTA_CONNECTING;
            }
        }

        public void onError(int i) {
            Log.d("onError", "error:" + i);
            OTASDKUtils.this.error(i);
        }

        public void onOTA(boolean z) {
            if (!z) {
                return;
            }
            if (!OTASDKUtils.this.isQuick || Build.VERSION.SDK_INT < 26) {
                OTASDKUtils.this.startOta();
            } else if (!OTASDKUtils.this.otaUtils.setPHY()) {
                OTASDKUtils.this.startOta();
            }
        }

        public void onOTAFinish() {
            OTASDKUtils oTASDKUtils = OTASDKUtils.this;
            int unused = oTASDKUtils.STATUS = oTASDKUtils.REBOOT;
            OTASDKUtils.this.otaUtils.reBoot();
        }

        public void onPhyUpdate() {
            OTASDKUtils.this.startOta();
        }

        public void onProcess(float f) {
            OTASDKUtils.this.firewareCallBack.onProcess(f);
        }

        public void onReboot() {
        }

        public void onRebootSuccess() {
            OTASDKUtils.this.otaUtils.disConnectDevice();
        }

        public void onResource(boolean z) {
            if (!z) {
                return;
            }
            if (!OTASDKUtils.this.isQuick || Build.VERSION.SDK_INT < 26) {
                OTASDKUtils.this.startOta();
            } else if (!OTASDKUtils.this.otaUtils.setPHY()) {
                OTASDKUtils.this.startOta();
            }
        }

        public void onResourceFinish() {
            OTASDKUtils oTASDKUtils = OTASDKUtils.this;
            int unused = oTASDKUtils.STATUS = oTASDKUtils.REBOOT;
            OTASDKUtils.this.otaUtils.reBoot();
        }

        public void onStartSecurityData() {
            OTASDKUtils oTASDKUtils = OTASDKUtils.this;
            int unused = oTASDKUtils.STATUS = oTASDKUtils.OTA_ING;
            OTASDKUtils.this.otaUtils.updateFirmware(OTASDKUtils.this.filePath, OTASDKUtils.this.isQuick, a.b.Security);
        }
    }

    public OTASDKUtils(Context context, UpdateFirewareCallBack updateFirewareCallBack) {
        this.firewareCallBack = updateFirewareCallBack;
        this.otaCallBack = new b();
        this.otaUtils = new OTAUtils(context, this.otaCallBack);
    }

    /* access modifiers changed from: private */
    public void error(int i) {
        initStatus();
        this.firewareCallBack.onError(i);
        this.otaUtils.close();
    }

    private void initStatus() {
        this.STATUS = 0;
    }

    /* access modifiers changed from: private */
    public void startOta() {
        a.b bVar = this.otaType;
        if (bVar == a.b.OTA) {
            this.otaUtils.updateFirmware(this.filePath, this.isQuick);
            this.STATUS = this.OTA_ING;
        } else if (bVar == a.b.RESOURCE) {
            this.otaUtils.updateResource(this.filePath, this.isQuick);
            this.STATUS = this.RES_ING;
        } else if (bVar == a.b.Security) {
            this.otaUtils.startSecurity();
            this.STATUS = this.RES_ING;
        }
    }

    /* access modifiers changed from: private */
    public void success() {
        initStatus();
        this.firewareCallBack.onUpdateComplete();
        this.otaUtils.close();
    }

    public void cancleOTA() {
        this.otaUtils.cancleOTA();
        initStatus();
    }

    public void setOtaKey(String str) {
        this.otaUtils.setOtaKey(str);
    }

    public void setOtaKeyCmd0x74(boolean z) {
        this.otaUtils.setOtaKeyCmd0x74(z);
    }

    public void setRetryTimes(int i) {
        OTAUtils oTAUtils = this.otaUtils;
        if (oTAUtils != null) {
            oTAUtils.setRetryTimes(i);
        }
    }

    public void updateFirware(String str, String str2) {
        this.address = str;
        this.filePath = str2;
        this.otaType = a.b.OTA;
        initStatus();
        this.otaUtils.connectDevice(str);
    }

    public void updateResource(String str, String str2) {
        this.address = str;
        this.filePath = str2;
        this.otaType = a.b.RESOURCE;
        initStatus();
        this.otaUtils.connectDevice(str);
    }

    public void updateSecurityFirware(String str, String str2) {
        this.address = str;
        this.filePath = str2;
        this.otaType = a.b.Security;
        initStatus();
        this.otaUtils.connectDevice(str);
    }
}
