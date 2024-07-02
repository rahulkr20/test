package com.facebook.appcomponentmanager;

import X.AbstractServiceC005202c;
import X.AnonymousClass000;
import X.C011705b;
import X.C014906s;
import X.C05U;
import X.C05Z;
import X.C06S;
import X.InterfaceC014806r;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;
/* loaded from: classes.dex */
public class AppComponentManagerService extends AbstractServiceC005202c {
    @Override // X.AbstractServiceC005202c
    public final void A04(Intent intent) {
        intent.getAction();
        if ("com.facebook.appcomponentmanager.ACTION_ENABLE_COMPONENTS".equals(intent.getAction())) {
            try {
                C05U.A03(this, "app_update");
                Intent intent2 = new Intent("com.facebook.appcomponentmanager.ENABLING_CMPS_DONE");
                intent2.setPackage(getPackageName());
                sendBroadcast(intent2);
            } catch (RuntimeException e) {
                C06S.A09("AppComponentManagerService", "Exception while enabling components. Aborting.", e);
                synchronized (C014906s.class) {
                    InterfaceC014806r interfaceC014806r = C014906s.A00;
                    if (interfaceC014806r == null) {
                        return;
                    }
                    interfaceC014806r.AGy(e);
                }
            }
        } else if ("com.facebook.appcomponentmanager.ACTION_EFNORCE_MANIFEST_CONSISTENCY".equals(intent.getAction())) {
            PackageManager packageManager = getPackageManager();
            C011705b c011705b = new C011705b();
            File A00 = AnonymousClass000.A00(this);
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            C05Z A03 = c011705b.A03(A00);
            int i = packageInfo.versionCode;
            String num = Integer.toString(i);
            String str = A03.A01;
            if (num.equals(str) && packageInfo.versionName.equals(A03.A02) && packageInfo.packageName.equals(A03.A00)) {
                return;
            }
            StringBuilder A09 = AnonymousClass000.A09();
            A09.append("PackageInfo{package=");
            AnonymousClass000.A0H(packageInfo.packageName, ",", "versionCode=", A09);
            A09.append(i);
            A09.append(",");
            A09.append("versionName=");
            A09.append(packageInfo.versionName);
            A09.append("} ,");
            A09.append("Manifest{package=");
            AnonymousClass000.A0H(A03.A00, ", ", "versionCode=", A09);
            AnonymousClass000.A0H(str, ", ", "versionName=", A09);
            A09.append(A03.A02);
            A09.append(", ");
            A09.append("activities=");
            A09.append(A03.A03.size());
            A09.append(", ");
            A09.append("receivers=");
            A09.append(A03.A05.size());
            A09.append(", ");
            A09.append("services=");
            A09.append(A03.A06.size());
            A09.append(", ");
            A09.append("providers=");
            A09.append(A03.A04.size());
            throw new IllegalStateException(AnonymousClass000.A07("}", A09));
        }
    }
}
