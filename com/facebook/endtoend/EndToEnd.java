package com.facebook.endtoend;

import X.AnonymousClass000;
import X.C011905d;
import X.C05P;
import android.util.Log;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class EndToEnd {
    public static boolean A00;
    public static boolean A01;
    public static boolean A02;
    public static volatile Map A03;
    public static volatile JSONObject A04 = new JSONObject();
    public static volatile boolean A05;
    public static volatile boolean A06;

    public static String A00(String str, boolean z, boolean z2) {
        if (z2 || isRunningEndToEndTest()) {
            String property = System.getProperty(str);
            if (property == null || property.equals("")) {
                String property2 = System.getProperty(AnonymousClass000.A06("fb.e2e.", str, AnonymousClass000.A09()));
                if (property2 != null && !property2.equals("")) {
                    return property2;
                }
                if (z) {
                    try {
                        property = C05P.A02(str);
                        if (property.equals("")) {
                            if (AnonymousClass000.A06("fb.e2e.", str, AnonymousClass000.A09()).length() <= 31) {
                                String A022 = C05P.A02(AnonymousClass000.A06("fb.e2e.", str, AnonymousClass000.A09()));
                                if (!A022.equals("")) {
                                    return A022;
                                }
                            }
                        }
                    } catch (IllegalArgumentException unused) {
                    }
                }
                return null;
            }
            return property;
        }
        return null;
    }

    static {
        try {
            synchronized (C011905d.class) {
                throw new IllegalStateException("ApplicationHolder#set never called");
            }
        } catch (IllegalStateException unused) {
        }
    }

    public static synchronized boolean A01() {
        boolean z;
        synchronized (EndToEnd.class) {
            if (!A01) {
                boolean equals = "true".equals(C05P.A02("fb.running_mobilelab"));
                A02 = equals;
                if (equals) {
                    Log.w("Mobilelab", "Is running Mobilelab test");
                }
                A01 = true;
            }
            z = A02;
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0014, code lost:
        if (A02("persist.fb.running_e2e") != false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean isRunningEndToEndTest() {
        boolean z;
        if (!A05) {
            if (!A02("fb.running_e2e")) {
                z = false;
            }
            z = true;
            A06 = z;
            if (A06) {
                Log.w("EndToEnd-Test", "Is running E2E test");
            }
            A05 = true;
        }
        return A06;
    }

    public static boolean A02(String str) {
        if (!"true".equals(C05P.A02(str)) && !"true".equals(System.getProperty(str))) {
            return false;
        }
        return true;
    }
}
