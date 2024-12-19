package com.google.android.datatransport;

import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_Event<T> extends Event<T> {
    private final Integer code;
    private final EventContext eventContext;
    private final T payload;
    private final Priority priority;
    private final ProductData productData;

    AutoValue_Event(Integer num, T t, Priority priority2, ProductData productData2, EventContext eventContext2) {
        this.code = num;
        if (t != null) {
            this.payload = t;
            if (priority2 != null) {
                this.priority = priority2;
                this.productData = productData2;
                this.eventContext = eventContext2;
                return;
            }
            throw new NullPointerException("Null priority");
        }
        throw new NullPointerException("Null payload");
    }

    public Integer getCode() {
        return this.code;
    }

    public T getPayload() {
        return this.payload;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public ProductData getProductData() {
        return this.productData;
    }

    public EventContext getEventContext() {
        return this.eventContext;
    }

    public String toString() {
        return "Event{code=" + this.code + ", payload=" + this.payload + ", priority=" + this.priority + ", productData=" + this.productData + ", eventContext=" + this.eventContext + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        ProductData productData2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        Integer num = this.code;
        if (num != null ? num.equals(event.getCode()) : event.getCode() == null) {
            if (this.payload.equals(event.getPayload()) && this.priority.equals(event.getPriority()) && ((productData2 = this.productData) != null ? productData2.equals(event.getProductData()) : event.getProductData() == null)) {
                EventContext eventContext2 = this.eventContext;
                if (eventContext2 == null) {
                    if (event.getEventContext() == null) {
                        return true;
                    }
                } else if (eventContext2.equals(event.getEventContext())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.code;
        int i = 0;
        int hashCode = ((((((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003) ^ this.payload.hashCode()) * 1000003) ^ this.priority.hashCode()) * 1000003;
        ProductData productData2 = this.productData;
        int hashCode2 = (hashCode ^ (productData2 == null ? 0 : productData2.hashCode())) * 1000003;
        EventContext eventContext2 = this.eventContext;
        if (eventContext2 != null) {
            i = eventContext2.hashCode();
        }
        return hashCode2 ^ i;
    }
}
