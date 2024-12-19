package com.google.android.datatransport;

import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_ProductData extends ProductData {
    private final Integer productId;

    AutoValue_ProductData(Integer num) {
        this.productId = num;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public String toString() {
        return "ProductData{productId=" + this.productId + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProductData)) {
            return false;
        }
        Integer num = this.productId;
        Integer productId2 = ((ProductData) obj).getProductId();
        if (num != null) {
            return num.equals(productId2);
        }
        if (productId2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.productId;
        return (num == null ? 0 : num.hashCode()) ^ 1000003;
    }
}
