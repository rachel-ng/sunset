package c;

import a.a;
import a.c;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.util.Log;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public abstract class b {
    public static boolean a(BluetoothGatt bluetoothGatt) {
        BluetoothGattService service = bluetoothGatt.getService(UUID.fromString("5833ff01-9b8b-5191-6142-22a4536ef123"));
        if (service == null || service.getCharacteristic(UUID.fromString("5833ff04-9b8b-5191-6142-22a4536ef123")) == null) {
            return false;
        }
        return true;
    }

    public static boolean b(BluetoothGatt bluetoothGatt) {
        BluetoothGattService service = bluetoothGatt.getService(UUID.fromString("5833ff01-9b8b-5191-6142-22a4536ef123"));
        if (service == null) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString("5833ff03-9b8b-5191-6142-22a4536ef123"));
        if (!bluetoothGatt.setCharacteristicNotification(characteristic, true)) {
            return false;
        }
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        bluetoothGatt.writeDescriptor(descriptor);
        return true;
    }

    public static boolean a(BluetoothGatt bluetoothGatt, String str, boolean z) {
        BluetoothGattService service = bluetoothGatt.getService(UUID.fromString("5833ff01-9b8b-5191-6142-22a4536ef123"));
        if (service == null) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString("5833ff02-9b8b-5191-6142-22a4536ef123"));
        if (!z) {
            characteristic.setWriteType(1);
        } else {
            characteristic.setWriteType(2);
        }
        characteristic.setValue(d.a(str));
        if (bluetoothGatt.writeCharacteristic(characteristic)) {
            Log.d("send ota commond", str);
        } else {
            Log.e("send ota commond", "发送失败：" + str);
        }
        return true;
    }

    public static String a(String str) {
        String substring = str.substring(0, 15);
        String format = String.format("%02X", new Object[]{Integer.valueOf((Integer.valueOf(str.substring(15), 16).intValue() + 1) & 255)});
        return substring + format;
    }

    public static boolean a(BluetoothGatt bluetoothGatt, String str) {
        BluetoothGattService service = bluetoothGatt.getService(UUID.fromString("5833ff01-9b8b-5191-6142-22a4536ef123"));
        if (service == null) {
            Log.e(" OTA service", "service is null");
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString("5833ff04-9b8b-5191-6142-22a4536ef123"));
        characteristic.setValue(d.a(str.toLowerCase()));
        bluetoothGatt.writeCharacteristic(characteristic);
        Log.d("send ota data", str);
        return true;
    }

    public static String a(int i, long j, String str, int i2, int i3) {
        String a2 = e.a(e.a(Long.toHexString(j), 8));
        String a3 = e.a(e.a(str, 8));
        String a4 = e.a(e.a(Integer.toHexString(i2), 8));
        String a5 = e.a(e.a(Integer.toHexString(i3), 4));
        String a6 = e.a(Integer.toHexString(i), 2);
        return "02" + a6 + a2 + a3 + a4 + a5;
    }

    public static String a(int i, long j, String str, int i2, String str2) {
        String a2 = e.a(e.a(Long.toHexString(j), 8));
        String a3 = e.a(e.a(str, 8));
        String a4 = e.a(e.a(Integer.toHexString(i2), 8));
        String a5 = e.a(str2, 8);
        String a6 = e.a(Integer.toHexString(i), 2);
        return "02" + a6 + a2 + a3 + a4 + a5;
    }

    public static String a(a aVar) {
        String a2 = ((c) aVar.c().get(0)).a();
        long parseLong = Long.parseLong(a2, 16) & -4096;
        long parseLong2 = Long.parseLong(a2, 16) & 4095;
        Iterator it = aVar.c().iterator();
        while (it.hasNext()) {
            parseLong2 += (long) ((c) it.next()).d();
        }
        String a3 = e.a(e.a(Long.toHexString(parseLong), 8));
        String a4 = e.a(e.a(Long.toHexString((parseLong2 + 4095) & -4096), 8));
        return "05" + a3 + a4;
    }

    public static boolean a(BluetoothGatt bluetoothGatt, a aVar, int i, long j) {
        c cVar = (c) aVar.c().get(i);
        String a2 = a(i, j, cVar.a(), cVar.d(), a(cVar));
        if (aVar.d().endsWith("hexe16")) {
            List b2 = cVar.b();
            List list = (List) b2.get(b2.size() - 1);
            String str = (String) list.get(list.size() - 1);
            if (str.length() < 8) {
                str = ((String) list.get(list.size() - 2)) + str;
            }
            a2 = a(i, j, cVar.a(), cVar.d(), str.substring(str.length() - 8, str.length()));
        }
        Log.e("TAG", "sendPartition: ==================" + a2);
        return a(bluetoothGatt, a2, true);
    }

    public static boolean a(BluetoothGatt bluetoothGatt, a aVar) {
        return a(bluetoothGatt, a(aVar), true);
    }

    public static int a(c cVar) {
        return a(0, d.a(cVar.c()));
    }

    private static int a(int i, byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr2[i2] = bArr[i2];
        }
        for (int i3 = 0; i3 < length; i3++) {
            byte b2 = bArr2[i3];
            byte b3 = b2;
            if (b2 < 0) {
                b3 = b2 + 256;
            }
            i ^= b3;
            for (int i4 = 8; i4 != 0; i4--) {
                int i5 = i & 1;
                i >>= 1;
                if (i5 != 0) {
                    i ^= 40961;
                }
            }
        }
        return i;
    }

    public static String a() {
        StringBuffer stringBuffer = new StringBuffer();
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 32; i++) {
            stringBuffer.append(cArr[secureRandom.nextInt(16)]);
        }
        return String.valueOf(stringBuffer);
    }
}
