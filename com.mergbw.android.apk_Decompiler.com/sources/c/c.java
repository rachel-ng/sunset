package c;

public class c {

    /* renamed from: a  reason: collision with root package name */
    byte[] f35a = null;

    /* renamed from: b  reason: collision with root package name */
    int f36b = 0;

    public c(int i) {
        this.f35a = new byte[i];
    }

    public c a(byte b2) {
        return a(this.f36b, b2);
    }

    public String toString() {
        return d.a(this.f35a);
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        byte[] bArr = this.f35a;
        if (i > bArr.length) {
            int length = (bArr.length << 1) + 2;
            if (i <= length) {
                i = length;
            }
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, this.f36b);
            this.f35a = bArr2;
        }
    }

    public c a(int i, byte[] bArr, int i2) {
        int i3 = this.f36b;
        if (i > i3 || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        a(i3 + i2);
        byte[] bArr2 = this.f35a;
        System.arraycopy(bArr2, i, bArr2, i + i2, this.f36b - i);
        System.arraycopy(bArr, 0, this.f35a, i, i2);
        this.f36b += i2;
        return this;
    }

    public c a(int i, byte b2) {
        return a(i, new byte[]{b2}, 1);
    }

    public byte[] a() {
        int i = this.f36b;
        byte[] bArr = new byte[i];
        System.arraycopy(this.f35a, 0, bArr, 0, i);
        return bArr;
    }
}
