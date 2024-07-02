package com.facebook.common.time;

import X.InterfaceC009003t;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class AwakeTimeSinceBootClock implements InterfaceC009003t {
    public static final AwakeTimeSinceBootClock INSTANCE = new AwakeTimeSinceBootClock();

    @Override // X.InterfaceC009003t
    public /* synthetic */ long now() {
        long millis;
        millis = TimeUnit.NANOSECONDS.toMillis(nowNanos());
        return millis;
    }

    @Override // X.InterfaceC009003t
    public long nowNanos() {
        return System.nanoTime();
    }

    public static AwakeTimeSinceBootClock get() {
        return INSTANCE;
    }
}
