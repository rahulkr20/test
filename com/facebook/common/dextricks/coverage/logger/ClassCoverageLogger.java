package com.facebook.common.dextricks.coverage.logger;

import X.AnonymousClass000;
import X.C05P;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/* loaded from: classes.dex */
public class ClassCoverageLogger {
    public static final Class TAG = ClassCoverageLogger.class;
    public static final Queue LOADED_CLASSES = new ConcurrentLinkedQueue();
    public static final String ENABLE_CLASS_COVERAGE = "fb.enable_class_coverage";
    public static volatile boolean ENABLED = "true".equals(C05P.A02(ENABLE_CLASS_COVERAGE));
    public static final String THROW_ON_CLASS_LOAD = "fb.throw_on_class_load";
    public static volatile String THROW_ON_LOADED = C05P.A02(THROW_ON_CLASS_LOAD);

    public static void checkThrowOnLoad(String str) {
        if (THROW_ON_LOADED != null && !THROW_ON_LOADED.isEmpty() && str.startsWith(THROW_ON_LOADED)) {
            throw new UnsupportedOperationException(AnonymousClass000.A06("Class load disallowed: ", str, AnonymousClass000.A09()));
        }
    }

    public static List getCoverage() {
        LinkedList linkedList = new LinkedList();
        for (Object obj : LOADED_CLASSES) {
            linkedList.add(obj);
        }
        return linkedList;
    }

    public static void classLoaded(String str) {
        checkThrowOnLoad(str);
        if (ENABLED) {
            LOADED_CLASSES.add(str);
        }
    }

    public static void classLoaded(Class cls) {
        classLoaded(cls.getName());
    }
}
