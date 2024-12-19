package b;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private a f30a;

    /* renamed from: b  reason: collision with root package name */
    private C0000b f31b;
    /* access modifiers changed from: private */

    /* renamed from: c  reason: collision with root package name */
    public c f32c;
    private Context d;

    class a implements BluetoothAdapter.LeScanCallback {
        a() {
        }

        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            if (b.this.f32c != null) {
                b.this.f32c.onDeviceSearch(bluetoothDevice, i, bArr);
                return;
            }
            throw new RuntimeException("otaUtilsCallBack is null");
        }
    }

    /* renamed from: b.b$b  reason: collision with other inner class name */
    class C0000b extends ScanCallback {
        C0000b() {
        }

        public void onScanResult(int i, ScanResult scanResult) {
            if (b.this.f32c != null) {
                b.this.f32c.onDeviceSearch(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes());
                return;
            }
            throw new RuntimeException("otaUtilsCallBack is null");
        }
    }

    public b(Context context, c cVar) {
        this.d = context;
        this.f32c = cVar;
    }

    public void b() {
        BluetoothAdapter adapter = ((BluetoothManager) this.d.getApplicationContext().getSystemService("bluetooth")).getAdapter();
        if (adapter != null && adapter.isEnabled()) {
            if (this.f30a != null || this.f31b != null) {
                adapter.getBluetoothLeScanner().stopScan(this.f31b);
            }
        }
    }

    public void a() {
        BluetoothAdapter adapter = ((BluetoothManager) this.d.getApplicationContext().getSystemService("bluetooth")).getAdapter();
        this.f31b = new C0000b();
        adapter.getBluetoothLeScanner().startScan(this.f31b);
    }
}
