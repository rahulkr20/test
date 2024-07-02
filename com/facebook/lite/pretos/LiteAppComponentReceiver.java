package com.facebook.lite.pretos;

import X.AbstractC012705o;
import X.AbstractServiceC005202c;
import X.AnonymousClass000;
import X.C015306x;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.appcomponentmanager.AppComponentManagerService;
import java.io.File;
/* loaded from: classes.dex */
public class LiteAppComponentReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        intent.getAction();
        if ("android.intent.action.MY_PACKAGE_REPLACED".equals(intent.getAction())) {
            File A00 = AbstractC012705o.A00(context, 1436876361);
            A00.mkdirs();
            String[] list = AnonymousClass000.A02(A00, "versions").list();
            if ((list != null && list.length != 0) || C015306x.A02.A00() != 0) {
                AbstractServiceC005202c.A00(context, new Intent("com.facebook.appcomponentmanager.ACTION_ENABLE_COMPONENTS"), AppComponentManagerService.class, 137875812);
            }
        }
    }
}
