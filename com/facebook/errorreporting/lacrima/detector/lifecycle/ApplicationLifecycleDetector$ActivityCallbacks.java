package com.facebook.errorreporting.lacrima.detector.lifecycle;

import X.C03W;
import X.C04P;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
/* loaded from: classes.dex */
public class ApplicationLifecycleDetector$ActivityCallbacks implements Application.ActivityLifecycleCallbacks {
    public final /* synthetic */ C03W A00;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public ApplicationLifecycleDetector$ActivityCallbacks(C03W c03w) {
        this.A00 = c03w;
    }

    public final void A01(Activity activity, Integer num) {
        String obj;
        C03W c03w = this.A00;
        synchronized (c03w.A0C) {
            if (Build.VERSION.SDK_INT < 29 || num.equals(C04P.A01)) {
                C03W.A00(activity, c03w);
                c03w.A06.A00(C04P.A0N, activity);
            }
            Intent intent = activity.getIntent();
            if (intent == null) {
                obj = "Null intent";
            } else {
                obj = intent.toString();
            }
            c03w.A01(activity, num, obj, false);
        }
        synchronized (C03W.A0I) {
        }
    }

    public final void A02(Activity activity, Integer num) {
        C03W c03w = this.A00;
        synchronized (c03w.A0C) {
            if (Build.VERSION.SDK_INT < 29 || num.equals(C04P.A01)) {
                C03W.A00(null, c03w);
                c03w.A06.A00(C04P.A0h, activity);
            }
            c03w.A01(activity, num, null, true);
        }
    }

    public final void A03(Activity activity, Integer num) {
        C03W c03w = this.A00;
        synchronized (c03w.A0C) {
            if (Build.VERSION.SDK_INT < 29 || num.equals(C04P.A01)) {
                C03W.A00(null, c03w);
                c03w.A06.A00(C04P.A0T, activity);
            }
            c03w.A01(activity, num, null, activity.isFinishing());
        }
    }

    public final void A04(Activity activity, Integer num) {
        C03W c03w = this.A00;
        synchronized (c03w.A0C) {
            if (Build.VERSION.SDK_INT < 29 || num.equals(C04P.A01)) {
                C03W.A00(null, c03w);
                c03w.A06.A00(C04P.A00, activity);
            }
            c03w.A01(activity, num, null, false);
        }
    }

    public final void A05(Activity activity, Integer num) {
        C03W c03w = this.A00;
        synchronized (c03w.A0C) {
            if (Build.VERSION.SDK_INT < 29 || num.equals(C04P.A01)) {
                C03W.A00(null, c03w);
                c03w.A06.A00(C04P.A0C, activity);
            }
            c03w.A01(activity, num, null, false);
        }
    }

    public final void A06(Activity activity, Integer num) {
        C03W c03w = this.A00;
        synchronized (c03w.A0C) {
            if (Build.VERSION.SDK_INT < 29 || num.equals(C04P.A01)) {
                C03W.A00(null, c03w);
                c03w.A06.A00(C04P.A0Z, activity);
            }
            c03w.A01(activity, num, null, activity.isFinishing());
        }
    }

    public static final boolean A00(Activity activity) {
        return activity.getClass().getName().equals(null);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        if (!A00(activity)) {
            A01(activity, C04P.A0C);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        if (!A00(activity)) {
            A02(activity, C04P.A0C);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        if (!A00(activity)) {
            A03(activity, C04P.A0C);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        if (!A00(activity)) {
            A04(activity, C04P.A0C);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        if (!A00(activity)) {
            A05(activity, C04P.A0C);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        if (!A00(activity)) {
            A06(activity, C04P.A0C);
        }
    }
}
