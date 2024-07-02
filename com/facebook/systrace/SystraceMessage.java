package com.facebook.systrace;

import X.C016207g;
import X.C02U;
import X.C02X;
import X.C07N;
/* loaded from: classes.dex */
public abstract class SystraceMessage {
    public static final C02U A00 = new C02U() { // from class: X.02V
        @Override // X.C02U
        public final C02U A00(Object obj, String str) {
            return this;
        }

        @Override // X.C02U
        public final C02U A01(String str, int i) {
            return this;
        }

        @Override // X.C02U
        public final void A02() {
        }
    };
    public static final ThreadLocal A01 = new ThreadLocal() { // from class: X.02W
        @Override // java.lang.ThreadLocal
        public final /* bridge */ /* synthetic */ Object initialValue() {
            return new C016207g();
        }
    };
    public static final C02X A02 = new Object() { // from class: X.02X
    };

    public static C02U A00(String str) {
        C02X c02x = A02;
        if (!Systrace.A0G(281474976710656L)) {
            return A00;
        }
        C016207g c016207g = (C016207g) A01.get();
        c016207g.A00 = 281474976710656L;
        c016207g.A02 = c02x;
        c016207g.A03 = str;
        C07N c07n = c016207g.A01;
        for (int i = 0; i < c07n.A00; i++) {
            c07n.A01[i] = null;
        }
        c07n.A00 = 0;
        return c016207g;
    }
}
