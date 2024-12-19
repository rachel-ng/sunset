package com.google.firebase;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Timestamp.kt */
/* synthetic */ class Timestamp$compareTo$2 extends PropertyReference1Impl {
    public static final Timestamp$compareTo$2 INSTANCE = new Timestamp$compareTo$2();

    Timestamp$compareTo$2() {
        super(Timestamp.class, "nanoseconds", "getNanoseconds()I", 0);
    }

    public Object get(Object obj) {
        return Integer.valueOf(((Timestamp) obj).getNanoseconds());
    }
}
