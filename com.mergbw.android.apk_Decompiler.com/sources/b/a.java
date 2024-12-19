package b;

import a.b;
import a.c;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.util.Log;
import c.d;
import com.ota66.sdk.OTAUtils;
import java.util.List;

public class a extends BluetoothGattCallback {
    private static final String w = "a";

    /* renamed from: a  reason: collision with root package name */
    private c f27a;

    /* renamed from: b  reason: collision with root package name */
    private a.a f28b;

    /* renamed from: c  reason: collision with root package name */
    private b f29c;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private long g = 0;
    private List h;
    private boolean i;
    private String j;
    private float k;
    private float l;
    private int m = 3;
    private int n;
    private int o;
    private boolean p;
    private int q = -1;
    private int r = -1;
    private boolean s;
    private String t;
    private String u;
    public String v;

    public void a(c cVar) {
        this.f27a = cVar;
    }

    public void b() {
        this.p = true;
    }

    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        String uuid = bluetoothGattCharacteristic.getUuid().toString();
        this.j = d.b(bluetoothGattCharacteristic.getValue());
        if (uuid.equals("5833ff03-9b8b-5191-6142-22a4536ef123")) {
            String str = w;
            Log.d(str, "收到特征值:" + d.b(bluetoothGattCharacteristic.getValue()));
            this.i = true;
            if ("0087".equals(this.j)) {
                if (this.o > 0) {
                    this.o = 0;
                }
                int i2 = this.e + 1;
                this.e = i2;
                this.f = 0;
                if (i2 < ((c) this.f28b.c().get(this.d)).b().size()) {
                    List list = (List) ((c) this.f28b.c().get(this.d)).b().get(this.e);
                    this.h = list;
                    a(bluetoothGatt, (String) list.get(this.f));
                }
            } else if ("0085".equals(this.j)) {
                int i3 = this.d + 1;
                this.d = i3;
                this.e = 0;
                if (i3 < this.f28b.c().size()) {
                    b bVar = this.f29c;
                    if (bVar == b.OTA || bVar == b.Security) {
                        c cVar = (c) this.f28b.c().get(this.d - 1);
                        if (285212672 > Long.parseLong(cVar.a(), 16) || Long.parseLong(cVar.a(), 16) > 285736959) {
                            if (this.f28b.d().endsWith("hexe16")) {
                                this.g = this.g + ((long) cVar.d()) + 4;
                            } else {
                                this.g = this.g + ((long) cVar.d()) + 8;
                            }
                        }
                    }
                    a(bluetoothGatt, this.f28b, this.d, this.g);
                }
            } else if ("0083".equals(this.j)) {
                b bVar2 = this.f29c;
                if (bVar2 == b.OTA || bVar2 == b.Security) {
                    this.f27a.onOTAFinish();
                } else if (bVar2 == b.RESOURCE) {
                    this.f27a.onResourceFinish();
                }
            } else if ("008a".equals(this.j.toLowerCase())) {
                this.f27a.a();
            } else if ("00".equals(this.j)) {
                Log.e(str, "========111111");
            } else if ("6887".equals(this.j)) {
                if (!this.p) {
                    a(bluetoothGatt, 1005);
                }
            } else if (!"0081".equals(this.j) && !"0084".equals(this.j) && !"0089".equals(this.j)) {
                if (this.j.length() == 34 && this.j.startsWith("71")) {
                    this.u = this.j.substring(2);
                } else if (this.j.length() == 34 && this.j.startsWith("72")) {
                } else {
                    if (this.j.length() == 34 && this.j.startsWith("73")) {
                        return;
                    }
                    if (this.j.length() == 34 && this.j.startsWith("8B")) {
                        return;
                    }
                    if (this.j.length() == 34 && this.j.startsWith("8C")) {
                        return;
                    }
                    if (this.j.length() != 34 || !this.j.startsWith("8D")) {
                        this.f27a.onError(1005);
                        Log.d(str, "error:" + this.j);
                    }
                }
            }
        }
    }

    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        String str = w;
        Log.e(str, "onCharacteristicWrite: 数据发送成功：" + this.j);
        if (bluetoothGattCharacteristic.getUuid().toString().equals("5833ff02-9b8b-5191-6142-22a4536ef123")) {
            if ("0081".equals(this.j) && this.i) {
                b bVar = this.f29c;
                if (bVar == b.OTA || bVar == b.Security) {
                    a(bluetoothGatt, this.f28b, this.d, this.g);
                    this.e = 0;
                } else if (bVar == b.RESOURCE) {
                    a(bluetoothGatt, this.f28b);
                }
            } else if ("0084".equals(this.j) && this.i) {
                this.f = 0;
                List list = (List) ((c) this.f28b.c().get(this.d)).b().get(this.e);
                this.h = list;
                a(bluetoothGatt, (String) list.get(this.f));
            } else if ("0089".equals(this.j)) {
                a(bluetoothGatt, this.f28b, this.d, this.g);
                this.e = 0;
            }
            String str2 = this.j;
            if (str2 == null || str2.length() != 34 || !this.j.startsWith("71")) {
                String str3 = this.j;
                if (str3 == null || str3.length() != 34 || !this.j.startsWith("72")) {
                    String str4 = this.j;
                    if (str4 == null || str4.length() != 34 || !this.j.startsWith("73")) {
                        String str5 = this.j;
                        if (str5 == null || str5.length() != 34 || !this.j.startsWith("8B")) {
                            String str6 = this.j;
                            if (str6 == null || str6.length() != 34 || !this.j.startsWith("8C")) {
                                String str7 = this.j;
                                if (str7 == null || str7.length() != 34 || !this.j.startsWith("8D")) {
                                    String str8 = this.j;
                                    if (str8 != null && str8.equals("0102")) {
                                        bluetoothGatt.disconnect();
                                    }
                                } else {
                                    this.f27a.onStartSecurityData();
                                }
                            } else {
                                String a2 = c.a.a(this.u, this.v);
                                if (this.j.substring(2).equals(a2)) {
                                    String b2 = c.a.b(c.a.b(a2, this.t), this.v);
                                    c.b.a(bluetoothGatt, "08" + b2, true);
                                } else {
                                    Log.e("OTAUtils", "responseSecurity: AES加密验证失败");
                                }
                            }
                        } else {
                            this.u = this.j.substring(2);
                            c.b.a(bluetoothGatt, "07" + this.t, true);
                        }
                    } else {
                        c.b.a(bluetoothGatt, "0102", true);
                        this.j = "0102";
                    }
                } else {
                    String a3 = c.a.a(this.u, this.v);
                    Log.e(str, "onCharacteristicWrite: " + a3 + " :" + this.j.substring(2));
                    if (this.j.substring(2).equals(a3)) {
                        String b3 = c.a.b(c.a.b(a3, this.t), this.v);
                        c.b.a(bluetoothGatt, "07" + b3, true);
                    } else {
                        Log.e("OTAUtils", "responseSecurity: AES加密验证失败");
                    }
                }
            } else {
                this.u = this.j.substring(2);
                c.b.a(bluetoothGatt, "06" + this.t, true);
            }
            if ("0102".equals(d.b(bluetoothGattCharacteristic.getValue())) || "0103".equals(d.b(bluetoothGattCharacteristic.getValue()))) {
                Log.d(str, "start ota or resource");
            }
            this.i = false;
        } else if (!bluetoothGattCharacteristic.getUuid().toString().equals("5833ff04-9b8b-5191-6142-22a4536ef123")) {
        } else {
            if (i2 == 0) {
                if (this.n > 0) {
                    this.n = 0;
                }
                if (!(this.q == this.d && this.r == this.e)) {
                    if (this.s) {
                        this.s = false;
                        this.q = -1;
                        this.r = -1;
                    }
                    float length = this.l + ((float) bluetoothGattCharacteristic.getValue().length);
                    this.l = length;
                    this.f27a.onProcess((length * 100.0f) / this.k);
                }
                int i3 = this.f + 1;
                this.f = i3;
                if (i3 < this.h.size()) {
                    a(bluetoothGatt, (String) this.h.get(this.f));
                    return;
                }
                return;
            }
            b(bluetoothGatt, 1004);
        }
    }

    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i2, int i3) {
        if (i3 == 2) {
            Log.e(w, "onConnectionStateChange:连接成功 ");
            bluetoothGatt.requestMtu(512);
        } else if (i3 == 0) {
            Log.e(w, "onConnectionStateChange:断开连接 ");
            if (bluetoothGatt != null) {
                bluetoothGatt.close();
            }
            this.f27a.a(false);
        }
    }

    public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
        if (!"00002902-0000-1000-8000-00805f9b34fb".equals(bluetoothGattDescriptor.getUuid().toString().toLowerCase())) {
            return;
        }
        if (i2 == 0) {
            this.f27a.a(true);
        } else {
            bluetoothGatt.disconnect();
        }
    }

    public void onMtuChanged(BluetoothGatt bluetoothGatt, int i2, int i3) {
        if (i3 == 0) {
            OTAUtils.MTU_SIZE = i2;
        } else {
            OTAUtils.MTU_SIZE = 23;
        }
        bluetoothGatt.discoverServices();
    }

    public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i2, int i3, int i4) {
        this.f27a.a(bluetoothGatt, i2, i3, i4);
    }

    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i2) {
        boolean z;
        if (i2 == 0) {
            String str = w;
            Log.d(str, "onServicesDiscovered: success");
            if (c.b.a(bluetoothGatt)) {
                Log.e(str, "onServicesDiscovered: 连接OTA模式");
                z = c.b.b(bluetoothGatt);
                this.f27a.a(true);
            } else {
                boolean b2 = c.b.b(bluetoothGatt);
                Log.e(str, "onServicesDiscovered: 首页连接应用模式");
                if (b2) {
                    this.f27a.a(true);
                } else {
                    Log.e(str, "开启通知属性异常");
                }
                z = b2;
            }
            if (!z) {
                bluetoothGatt.disconnect();
                return;
            }
            return;
        }
        bluetoothGatt.disconnect();
    }

    private void b(BluetoothGatt bluetoothGatt, int i2) {
        Log.d(w, "retryCmd: ");
        if (this.n < this.m) {
            a(bluetoothGatt, (String) this.h.get(this.f));
            this.n++;
            return;
        }
        this.f27a.onError(i2);
    }

    public void a(a.a aVar, b bVar) {
        this.f28b = aVar;
        this.f29c = bVar;
        this.k = (float) aVar.b();
        a();
    }

    public void a(int i2) {
        this.m = i2;
    }

    private void a() {
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = null;
        this.l = 0.0f;
        this.p = false;
    }

    private void a(BluetoothGatt bluetoothGatt, String str) {
        if (!this.p && !c.b.a(bluetoothGatt, str)) {
            this.f27a.onError(1002);
        }
    }

    private void a(BluetoothGatt bluetoothGatt, a.a aVar, int i2, long j2) {
        if (!this.p) {
            c cVar = (c) aVar.c().get(i2);
            if (285212672 <= Long.parseLong(cVar.a(), 16) && Long.parseLong(cVar.a(), 16) <= 285736959) {
                j2 = Long.parseLong(cVar.a(), 16);
            }
            if (this.f29c == b.RESOURCE) {
                j2 = 0;
                this.g = 0;
            }
            if (!c.b.a(bluetoothGatt, aVar, i2, j2)) {
                this.f27a.onError(1003);
            }
        }
    }

    private void a(BluetoothGatt bluetoothGatt, a.a aVar) {
        if (!this.p && !c.b.a(bluetoothGatt, aVar)) {
            this.f27a.onError(1003);
        }
    }

    public void a(BluetoothGatt bluetoothGatt) {
        String a2 = c.b.a();
        this.t = a2;
        String b2 = c.a.b(a2, this.v);
        if (c.b.a(bluetoothGatt)) {
            c.b.a(bluetoothGatt, "06" + b2, true);
            return;
        }
        c.b.a(bluetoothGatt, "05" + b2, true);
    }

    private void a(BluetoothGatt bluetoothGatt, int i2) {
        Log.d(w, "retry block:");
        if (this.o < this.m) {
            this.r = this.e;
            this.q = this.d;
            this.s = true;
            this.f = 0;
            List list = (List) ((c) this.f28b.c().get(this.d)).b().get(this.e);
            this.h = list;
            a(bluetoothGatt, (String) list.get(this.f));
            this.o++;
            return;
        }
        this.f27a.onError(i2);
    }
}
