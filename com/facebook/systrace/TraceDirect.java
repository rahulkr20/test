package com.facebook.systrace;

import X.C021909w;
import X.C05P;
import X.C06D;
import X.C06L;
import X.EnumC002200y;
import X.InterfaceC021809v;
import android.os.Process;
import android.util.Log;
/* loaded from: classes.dex */
public class TraceDirect {
    public static final String TAG = "TraceDirect";
    public static volatile int sPrevSoLoaderSourcesVersion = -1;
    public static final boolean sForceJavaImpl = "true".equals(C05P.A02("debug.fbsystrace.force_java"));
    public static final boolean sTraceLoad = "true".equals(C05P.A02("debug.fbsystrace.trace_load"));
    public static volatile EnumC002200y sNativeAvailable = EnumC002200y.UNSET;

    public static native void nativeAsyncTraceBegin(String str, int i, long j);

    public static native void nativeAsyncTraceCancel(String str, int i);

    public static native void nativeAsyncTraceEnd(String str, int i, long j);

    public static native void nativeAsyncTraceRename(String str, String str2, int i);

    public static native void nativeAsyncTraceStageBegin(String str, int i, long j, String str2);

    public static native void nativeBeginSection(String str);

    public static native void nativeBeginSectionWithArgs(String str, String[] strArr, int i);

    public static native void nativeEndAsyncFlow(String str, int i);

    public static native void nativeEndSection();

    public static native void nativeEndSectionWithArgs(String[] strArr, int i);

    public static native void nativeSetEnabledTags(long j);

    public static native void nativeStartAsyncFlow(String str, int i);

    public static native void nativeStepAsyncFlow(String str, int i);

    public static native void nativeTraceCounter(String str, int i);

    public static native void nativeTraceInstant(String str, String str2, char c);

    public static native void nativeTraceMetadata(String str, String str2, int i);

    public static boolean checkNative() {
        int i;
        InterfaceC021809v interfaceC021809v;
        if (sNativeAvailable == EnumC002200y.UNSET) {
            if (sForceJavaImpl) {
                sNativeAvailable = EnumC002200y.NO;
            } else {
                if (C021909w.A01()) {
                    synchronized (C021909w.class) {
                        interfaceC021809v = C021909w.A00;
                        if (interfaceC021809v == null) {
                            throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
                        }
                    }
                    i = interfaceC021809v.AF7();
                } else {
                    i = -1;
                }
                if (i != sPrevSoLoaderSourcesVersion) {
                    sPrevSoLoaderSourcesVersion = i;
                    String.format("Attempting to load fbsystrace.so [%d|%d].", Integer.valueOf(sPrevSoLoaderSourcesVersion), Integer.valueOf(i));
                    try {
                        C021909w.A02("fbsystrace");
                        nativeSetEnabledTags(C05P.A00("debug.fbsystrace.tags"));
                        nativeBeginSection("fbsystrace.so loaded");
                        nativeEndSection();
                        sNativeAvailable = EnumC002200y.YES;
                    } catch (UnsatisfiedLinkError unused) {
                        Log.w(TAG, "fbsystrace.so could not be loaded - switching to Java implementation.");
                    }
                }
            }
        }
        if (sNativeAvailable == EnumC002200y.YES) {
            return true;
        }
        return false;
    }

    public static void asyncTraceBegin(String str, int i, long j) {
        if (checkNative()) {
            nativeAsyncTraceBegin(str, i, j);
            return;
        }
        C06L c06l = new C06L('S');
        c06l.A00(Process.myPid());
        c06l.A02(str);
        c06l.A01("<0>");
        if (j != 0) {
            c06l.A01("<T");
            c06l.A01(Long.toString(j));
            c06l.A01(">");
        }
        c06l.A00(i);
        C06D.A00(c06l.A00.toString());
    }

    public static void asyncTraceCancel(String str, int i) {
        if (checkNative()) {
            nativeAsyncTraceCancel(str, i);
            return;
        }
        C06L c06l = new C06L('F');
        c06l.A00(Process.myPid());
        c06l.A02(str);
        c06l.A01("<X>");
        c06l.A00(i);
        C06D.A00(c06l.A00.toString());
    }

    public static void asyncTraceEnd(String str, int i, long j) {
        if (checkNative()) {
            nativeAsyncTraceEnd(str, i, j);
            return;
        }
        C06L c06l = new C06L('F');
        c06l.A00(Process.myPid());
        c06l.A02(str);
        if (j != 0) {
            c06l.A01("<T");
            c06l.A01(Long.toString(j));
            c06l.A01(">");
        }
        c06l.A00(i);
        C06D.A00(c06l.A00.toString());
    }

    public static void asyncTraceRename(String str, String str2, int i) {
        if (checkNative()) {
            nativeAsyncTraceRename(str, str2, i);
            return;
        }
        C06L c06l = new C06L('F');
        c06l.A00(Process.myPid());
        c06l.A02(str);
        c06l.A01("<M>");
        c06l.A00(i);
        c06l.A02(str2);
        C06D.A00(c06l.A00.toString());
    }

    public static void asyncTraceStageBegin(String str, int i, long j, String str2) {
        if (checkNative()) {
            nativeAsyncTraceStageBegin(str, i, j, str2);
            return;
        }
        C06L c06l = new C06L('T');
        c06l.A00(Process.myPid());
        c06l.A02(str);
        if (j != 0) {
            c06l.A01("<T");
            c06l.A01(Long.toString(j));
            c06l.A01(">");
        }
        c06l.A00(i);
        c06l.A02(str2);
        C06D.A00(c06l.A00.toString());
    }

    public static void beginSection(String str) {
        if (checkNative()) {
            nativeBeginSection(str);
            return;
        }
        C06L c06l = new C06L('B');
        c06l.A00(Process.myPid());
        c06l.A02(str);
        C06D.A00(c06l.A00.toString());
    }

    public static void beginSectionWithArgs(String str, String[] strArr, int i) {
        if (checkNative()) {
            nativeBeginSectionWithArgs(str, strArr, i);
            return;
        }
        C06L c06l = new C06L('B');
        c06l.A00(Process.myPid());
        c06l.A02(str);
        c06l.A03(strArr, i);
        C06D.A00(c06l.A00.toString());
    }

    public static void endAsyncFlow(String str, int i) {
        if (checkNative()) {
            nativeEndAsyncFlow(str, i);
            return;
        }
        C06L c06l = new C06L('f');
        c06l.A00(Process.myPid());
        c06l.A02(str);
        c06l.A00(i);
        C06D.A00(c06l.A00.toString());
    }

    public static void endSection() {
        if (checkNative()) {
            nativeEndSection();
        } else {
            C06D.A00("E");
        }
    }

    public static void endSectionWithArgs(String[] strArr, int i) {
        if (checkNative()) {
            nativeEndSectionWithArgs(strArr, i);
            return;
        }
        C06L c06l = new C06L('E');
        StringBuilder sb = c06l.A00;
        sb.append('|');
        sb.append('|');
        c06l.A03(strArr, i);
        C06D.A00(sb.toString());
    }

    public static void setEnabledTags(long j) {
        if (checkNative()) {
            nativeSetEnabledTags(j);
        }
    }

    public static void startAsyncFlow(String str, int i) {
        if (checkNative()) {
            nativeStartAsyncFlow(str, i);
            return;
        }
        C06L c06l = new C06L('s');
        c06l.A00(Process.myPid());
        c06l.A02(str);
        c06l.A00(i);
        C06D.A00(c06l.A00.toString());
    }

    public static void stepAsyncFlow(String str, int i) {
        if (checkNative()) {
            nativeStepAsyncFlow(str, i);
            return;
        }
        C06L c06l = new C06L('t');
        c06l.A00(Process.myPid());
        c06l.A02(str);
        c06l.A00(i);
        C06D.A00(c06l.A00.toString());
    }

    public static void traceCounter(String str, int i) {
        if (checkNative()) {
            nativeTraceCounter(str, i);
            return;
        }
        C06L c06l = new C06L('C');
        c06l.A00(Process.myPid());
        c06l.A02(str);
        c06l.A00(i);
        C06D.A00(c06l.A00.toString());
    }

    public static void traceInstant(String str, String str2, char c) {
        if (checkNative()) {
            nativeTraceInstant(str, str2, c);
            return;
        }
        C06L c06l = new C06L('I');
        c06l.A00(Process.myPid());
        c06l.A02(str2);
        StringBuilder sb = c06l.A00;
        sb.append('|');
        sb.append((c == 0 || c == '\r' || c == ';' || c == '|' || c == '\t' || c == '\n') ? ' ' : ' ');
        c06l.A02(str);
        C06D.A00(sb.toString());
    }

    public static void traceMetadata(String str, String str2, int i) {
        if (checkNative()) {
            nativeTraceMetadata(str, str2, i);
            return;
        }
        C06L c06l = new C06L('M');
        c06l.A00(Process.myPid());
        c06l.A02(str);
        c06l.A00(i);
        c06l.A02(str2);
        C06D.A00(c06l.A00.toString());
    }
}
