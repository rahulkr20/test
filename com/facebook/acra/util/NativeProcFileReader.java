package com.facebook.acra.util;

import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public class NativeProcFileReader {
    public static final AtomicBoolean A00 = new AtomicBoolean(false);

    private native int[] getOpenFDLimitsNative();

    public native int getOpenFDCount();

    public native String getOpenFileDescriptors();

    public NativeProcFileReader() {
        if (A00.get()) {
            return;
        }
        throw new IllegalStateException("Class is not ready");
    }
}
