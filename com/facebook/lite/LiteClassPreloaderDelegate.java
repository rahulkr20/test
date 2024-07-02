package com.facebook.lite;

import X.AbstractC005102b;
import X.AnonymousClass000;
import android.content.Context;
import java.io.File;
import java.io.IOException;
/* loaded from: classes.dex */
public class LiteClassPreloaderDelegate {
    public static boolean isClassPreloadingActivityEnabled(Context context) {
        File A01 = AnonymousClass000.A01(context, "pref_key_class_preloading_activity");
        boolean exists = A01.exists();
        A01.delete();
        return exists;
    }

    public static boolean isClassPreloadingAppEnabled(Context context) {
        File A01 = AnonymousClass000.A01(context, "pref_key_class_preloading_app");
        boolean exists = A01.exists();
        A01.delete();
        return exists;
    }

    public static void setClassPreloadingActivityEnabled(Context context, boolean z) {
        File A01 = AnonymousClass000.A01(context, "pref_key_class_preloading_activity");
        try {
            if (z) {
                A01.createNewFile();
            } else {
                A01.delete();
            }
        } catch (IOException unused) {
        }
    }

    public static void setClassPreloadingAppEnabled(Context context, boolean z) {
        File A01 = AnonymousClass000.A01(context, "pref_key_class_preloading_app");
        try {
            if (z) {
                A01.createNewFile();
            } else {
                A01.delete();
            }
        } catch (IOException unused) {
        }
    }

    public static boolean isClassPreloadingEnabled(Context context, String str) {
        File A01 = AnonymousClass000.A01(context, str);
        boolean exists = A01.exists();
        A01.delete();
        return exists;
    }

    public static void setClassPreloadingEnabled(Context context, String str, boolean z) {
        File A01 = AnonymousClass000.A01(context, str);
        try {
            if (z) {
                A01.createNewFile();
            } else {
                A01.delete();
            }
        } catch (IOException unused) {
        }
    }

    public static void preloadClasses(Context context, boolean z, boolean z2) {
        AbstractC005102b.A01(context, z, z2);
    }
}
