package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.NetworkConnectionInfo;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_NetworkConnectionInfo extends NetworkConnectionInfo {
    private final NetworkConnectionInfo.MobileSubtype mobileSubtype;
    private final NetworkConnectionInfo.NetworkType networkType;

    private AutoValue_NetworkConnectionInfo(NetworkConnectionInfo.NetworkType networkType2, NetworkConnectionInfo.MobileSubtype mobileSubtype2) {
        this.networkType = networkType2;
        this.mobileSubtype = mobileSubtype2;
    }

    public NetworkConnectionInfo.NetworkType getNetworkType() {
        return this.networkType;
    }

    public NetworkConnectionInfo.MobileSubtype getMobileSubtype() {
        return this.mobileSubtype;
    }

    public String toString() {
        return "NetworkConnectionInfo{networkType=" + this.networkType + ", mobileSubtype=" + this.mobileSubtype + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NetworkConnectionInfo)) {
            return false;
        }
        NetworkConnectionInfo networkConnectionInfo = (NetworkConnectionInfo) obj;
        NetworkConnectionInfo.NetworkType networkType2 = this.networkType;
        if (networkType2 != null ? networkType2.equals(networkConnectionInfo.getNetworkType()) : networkConnectionInfo.getNetworkType() == null) {
            NetworkConnectionInfo.MobileSubtype mobileSubtype2 = this.mobileSubtype;
            if (mobileSubtype2 == null) {
                if (networkConnectionInfo.getMobileSubtype() == null) {
                    return true;
                }
            } else if (mobileSubtype2.equals(networkConnectionInfo.getMobileSubtype())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        NetworkConnectionInfo.NetworkType networkType2 = this.networkType;
        int i = 0;
        int hashCode = ((networkType2 == null ? 0 : networkType2.hashCode()) ^ 1000003) * 1000003;
        NetworkConnectionInfo.MobileSubtype mobileSubtype2 = this.mobileSubtype;
        if (mobileSubtype2 != null) {
            i = mobileSubtype2.hashCode();
        }
        return hashCode ^ i;
    }

    static final class Builder extends NetworkConnectionInfo.Builder {
        private NetworkConnectionInfo.MobileSubtype mobileSubtype;
        private NetworkConnectionInfo.NetworkType networkType;

        Builder() {
        }

        public NetworkConnectionInfo.Builder setNetworkType(NetworkConnectionInfo.NetworkType networkType2) {
            this.networkType = networkType2;
            return this;
        }

        public NetworkConnectionInfo.Builder setMobileSubtype(NetworkConnectionInfo.MobileSubtype mobileSubtype2) {
            this.mobileSubtype = mobileSubtype2;
            return this;
        }

        public NetworkConnectionInfo build() {
            return new AutoValue_NetworkConnectionInfo(this.networkType, this.mobileSubtype);
        }
    }
}
