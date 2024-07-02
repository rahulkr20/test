package com.facebook.errorreporting.lacrima.detector.javacrash;

import X.AbstractC018908m;
import X.AnonymousClass042;
import X.AnonymousClass067;
import X.AnonymousClass070;
import X.AnonymousClass076;
import X.AnonymousClass090;
import X.C015206v;
import X.C03V;
import X.C04K;
import X.C04R;
import X.C05H;
import X.C05T;
import X.C05W;
import X.C06S;
import X.C07H;
import X.C07L;
import X.C08a;
import X.C0A1;
import X.EnumC017908b;
import X.InterfaceC009203w;
import X.InterfaceC018308f;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.errorreporting.lacrima.detector.javacrash.JavaCrashDetector;
import java.io.PrintWriter;
import java.lang.Thread;
/* loaded from: classes.dex */
public class JavaCrashDetector implements C08a {
    public static final InterfaceC018308f A05 = new InterfaceC018308f() { // from class: X.09h
        @Override // X.InterfaceC018308f
        public final boolean A1p(Thread thread, Throwable th) {
            return true;
        }
    };
    public C05H A00;
    public InterfaceC018308f A01;
    public final AnonymousClass076 A02;
    public final InterfaceC018308f A03;
    public final AnonymousClass090 A04;
    public byte[] mOomReservation;

    public final void A00(Thread thread, Throwable th) {
        C07L c07l;
        String A01;
        String valueOf;
        PrintWriter printWriter;
        this.mOomReservation = null;
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        AnonymousClass090 anonymousClass090 = this.A04;
        C05T c05t = anonymousClass090.A04;
        C04R.A02(c05t, "Did you call SessionManager.init()?");
        if (th instanceof C04K) {
            c07l = C07L.A08;
        } else {
            c07l = C07L.A07;
        }
        c05t.A02(c07l);
        boolean z = false;
        new AnonymousClass042(c05t.A01.A01).A01();
        if (this.A03.A1p(thread, th)) {
            C0A1 c0a1 = new C0A1(th);
            try {
                C0A1.A01(AnonymousClass070.A14, c0a1, 1);
                C05W c05w = AnonymousClass070.A38;
                Long valueOf2 = Long.valueOf(currentTimeMillis);
                c0a1.A03(c05w, valueOf2);
                c0a1.A04(AnonymousClass070.A4q, "exception");
                c0a1.A03(AnonymousClass070.A1S, valueOf2);
                synchronized (C015206v.class) {
                    if (C015206v.A01 != null && (printWriter = C015206v.A00) != null) {
                        th.printStackTrace(printWriter);
                        C015206v.A00.close();
                        A01 = C015206v.A01.toString();
                        C015206v.A00 = null;
                        C015206v.A01 = null;
                    } else {
                        A01 = C015206v.A01(th);
                    }
                }
                String str = "No stack trace";
                if (A01.length() < 10 && TextUtils.isEmpty(A01.trim())) {
                    C06S.A07("lacrima", "No stack trace");
                } else {
                    str = C015206v.A00(A01, 20000);
                }
                c0a1.A04(AnonymousClass070.A6D, str);
                c0a1.A04(AnonymousClass070.A6F, th.getClass().getName());
                c0a1.A04(AnonymousClass070.A6G, th.getMessage());
                if (thread != null) {
                    valueOf = String.valueOf(thread.getName());
                } else {
                    valueOf = "unknown";
                }
                c0a1.A04(AnonymousClass070.A6H, valueOf);
                while (th.getCause() != null) {
                    th = th.getCause();
                }
                c0a1.A04(AnonymousClass070.A68, th.getClass().getName());
                c0a1.A04(AnonymousClass070.A6A, C015206v.A01(th));
                c0a1.A04(AnonymousClass070.A69, th.getMessage());
                C0A1.A01(AnonymousClass070.A2W, c0a1, SystemClock.uptimeMillis() - anonymousClass090.A01);
                z = th instanceof OutOfMemoryError;
            } catch (Throwable th2) {
                AbstractC018908m.A00();
                c0a1.A04(AnonymousClass070.A5z, th2.getMessage());
            }
            AnonymousClass076 anonymousClass076 = this.A02;
            C07H c07h = C07H.CRITICAL_REPORT;
            anonymousClass076.A0B(c07h, this);
            anonymousClass076.A05(c0a1, c07h, this);
            anonymousClass076.A0C = true;
            if (!z) {
                anonymousClass076.A0A(c07h, this);
            }
            C07H c07h2 = C07H.LARGE_REPORT;
            anonymousClass076.A0B(c07h2, this);
            anonymousClass076.A05(c0a1, c07h2, this);
            anonymousClass076.A0D = true;
            if (z) {
                anonymousClass076.A0A(c07h, this);
            }
            anonymousClass076.A0A(c07h2, this);
        }
    }

    public JavaCrashDetector(AnonymousClass076 anonymousClass076, C05H c05h, InterfaceC018308f interfaceC018308f, InterfaceC018308f interfaceC018308f2, AnonymousClass090 anonymousClass090) {
        this.A04 = anonymousClass090;
        this.A02 = anonymousClass076;
        this.A00 = c05h;
        this.A01 = interfaceC018308f;
        this.A03 = interfaceC018308f2;
    }

    @Override // X.C08a
    public final void start() {
        AbstractC018908m.A00();
        AwakeTimeSinceBootClock.INSTANCE.now();
        if (AnonymousClass067.A01() != null) {
            AnonymousClass067.A03(new InterfaceC009203w() { // from class: X.03x
                @Override // X.InterfaceC009203w
                public final int AH8(C04L c04l, Thread thread, Throwable th) {
                    JavaCrashDetector javaCrashDetector = JavaCrashDetector.this;
                    if (javaCrashDetector.A01.A1p(thread, th)) {
                        javaCrashDetector.A00(thread, th);
                        return 0;
                    }
                    return 0;
                }
            }, 100);
        } else {
            final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: X.08e
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public final void uncaughtException(Thread thread, Throwable th) {
                    JavaCrashDetector javaCrashDetector = JavaCrashDetector.this;
                    if (javaCrashDetector.A01.A1p(thread, th)) {
                        javaCrashDetector.A00(thread, th);
                    }
                    defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                }
            });
        }
        this.mOomReservation = new byte[65536];
    }

    @Override // X.C08a
    public final /* synthetic */ C03V ABm() {
        return null;
    }

    @Override // X.C08a
    public final EnumC017908b ACZ() {
        return EnumC017908b.A07;
    }
}
