package com.facebook.lite;

import X.AbstractC012505m;
import X.AbstractC012705o;
import X.AbstractC021009l;
import X.AbstractC021109m;
import X.AbstractC021209o;
import X.AbstractC021309p;
import X.AbstractC021609s;
import X.AnonymousClass000;
import X.AnonymousClass081;
import X.AnonymousClass093;
import X.AnonymousClass094;
import X.AnonymousClass095;
import X.C00I;
import X.C015306x;
import X.C015807c;
import X.C016007e;
import X.C020809j;
import X.C02300Ak;
import X.C02310Al;
import X.C04V;
import X.C05L;
import X.C06H;
import X.C06K;
import X.C06M;
import X.C06S;
import X.C06w;
import X.C07T;
import X.C07Z;
import X.C09E;
import X.C09F;
import X.C09G;
import X.C09I;
import X.C09O;
import X.C09T;
import X.C09X;
import X.C09Y;
import X.C09n;
import X.C0BA;
import X.C0BB;
import X.C0BC;
import X.InterfaceC019408s;
import X.InterfaceC02340Ap;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.common.dextricks.classtracing.logger.ClassTracingLoggerFbLite;
import com.facebook.errorreporting.lacrima.common.mappedfile.mlocked.MLockedFile;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipFile;
/* loaded from: classes.dex */
public class ClientApplicationSplittedShell extends Application {
    public C00I A00;

    @Override // android.content.ContextWrapper, android.content.Context
    public final File getDir(String str, int i) {
        C016007e.A04(str, 1);
        if (AbstractC012505m.A00 && "webview".equals(str)) {
            File A00 = AbstractC012705o.A00(this, 372754419);
            A00.mkdirs();
            return A00;
        }
        return super.getDir(str, i);
    }

    private void A00() {
        if (this.A00 == null) {
            try {
                this.A00 = (C00I) Class.forName("com.facebook.lite.LeanClientApplication").getConstructor(Application.class).newInstance(this);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        Object systemService;
        C00I c00i = this.A00;
        if (c00i != null && (systemService = c00i.getSystemService(str)) != null) {
            return systemService;
        }
        return super.getSystemService(str);
    }

    public static void A01(String str) {
        Trace.beginSection(AnonymousClass000.A06("ClientApplicationSplittedShell.", str, AnonymousClass000.A09()));
    }

    /* JADX WARN: Can't wrap try/catch for region: R(18:43|44|(1:46)|47|(14:49|(1:51)|52|53|54|55|(1:57)(1:162)|58|59|60|(2:62|(1:156)(35:66|67|(3:69|70|71)|72|(3:74|(1:(1:77))(1:79)|78)|80|81|82|83|84|85|(1:87)|88|89|90|91|(21:93|(1:95)|97|(17:99|100|101|102|103|104|(10:106|107|108|109|110|111|112|113|114|115)|124|(1:126)|127|(1:131)|132|(1:134)|(1:143)|137|138|(2:140|141)(1:142))|147|101|102|103|104|(0)|124|(0)|127|(2:129|131)|132|(0)|(0)|143|137|138|(0)(0))|148|147|101|102|103|104|(0)|124|(0)|127|(0)|132|(0)|(0)|143|137|138|(0)(0)))|158|159|160)|(1:165)|52|53|54|55|(0)(0)|58|59|60|(0)|158|159|160) */
    /* JADX WARN: Can't wrap try/catch for region: R(22:89|90|91|(21:93|(1:95)|97|(17:99|100|101|102|103|104|(10:106|107|108|109|110|111|112|113|114|115)|124|(1:126)|127|(1:131)|132|(1:134)|(1:143)|137|138|(2:140|141)(1:142))|147|101|102|103|104|(0)|124|(0)|127|(2:129|131)|132|(0)|(0)|143|137|138|(0)(0))|148|147|101|102|103|104|(0)|124|(0)|127|(0)|132|(0)|(0)|143|137|138|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x02c5, code lost:
        if (X.C020809j.A05(r13, 10) == false) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x0352, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0353, code lost:
        X.C06S.A0B("SecondaryDexLoader", "No secondary dex manifest file, trying to start anyway", r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01b1, code lost:
        if (r2.contains(":") != false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01cc, code lost:
        r11 = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:117:0x030d A[Catch: all -> 0x03be, TRY_LEAVE, TryCatch #12 {IOException -> 0x03c8, blocks: (B:100:0x02ad, B:156:0x03a9, B:101:0x02b4, B:103:0x02b8, B:105:0x02bc, B:107:0x02c7, B:112:0x02d9, B:113:0x02e5, B:114:0x02f1, B:115:0x02fa, B:117:0x030d, B:141:0x035b, B:137:0x034b, B:138:0x0351, B:142:0x0361, B:144:0x0365, B:145:0x036c, B:147:0x037a, B:150:0x0386, B:152:0x038a, B:155:0x039c, B:140:0x0353), top: B:190:0x02ad }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0365 A[Catch: all -> 0x03be, TryCatch #12 {IOException -> 0x03c8, blocks: (B:100:0x02ad, B:156:0x03a9, B:101:0x02b4, B:103:0x02b8, B:105:0x02bc, B:107:0x02c7, B:112:0x02d9, B:113:0x02e5, B:114:0x02f1, B:115:0x02fa, B:117:0x030d, B:141:0x035b, B:137:0x034b, B:138:0x0351, B:142:0x0361, B:144:0x0365, B:145:0x036c, B:147:0x037a, B:150:0x0386, B:152:0x038a, B:155:0x039c, B:140:0x0353), top: B:190:0x02ad }] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x037a A[Catch: all -> 0x03be, TryCatch #12 {IOException -> 0x03c8, blocks: (B:100:0x02ad, B:156:0x03a9, B:101:0x02b4, B:103:0x02b8, B:105:0x02bc, B:107:0x02c7, B:112:0x02d9, B:113:0x02e5, B:114:0x02f1, B:115:0x02fa, B:117:0x030d, B:141:0x035b, B:137:0x034b, B:138:0x0351, B:142:0x0361, B:144:0x0365, B:145:0x036c, B:147:0x037a, B:150:0x0386, B:152:0x038a, B:155:0x039c, B:140:0x0353), top: B:190:0x02ad }] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x038a A[Catch: all -> 0x03be, TryCatch #12 {IOException -> 0x03c8, blocks: (B:100:0x02ad, B:156:0x03a9, B:101:0x02b4, B:103:0x02b8, B:105:0x02bc, B:107:0x02c7, B:112:0x02d9, B:113:0x02e5, B:114:0x02f1, B:115:0x02fa, B:117:0x030d, B:141:0x035b, B:137:0x034b, B:138:0x0351, B:142:0x0361, B:144:0x0365, B:145:0x036c, B:147:0x037a, B:150:0x0386, B:152:0x038a, B:155:0x039c, B:140:0x0353), top: B:190:0x02ad }] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x039a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:202:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01dc  */
    /* JADX WARN: Type inference failed for: r2v29, types: [X.04f] */
    @Override // android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void attachBaseContext(final Context context) {
        Callable c0bc;
        C0BC c0bc2;
        Callable c0bb;
        C09I[] values;
        boolean z;
        final boolean z2;
        boolean z3;
        int A00;
        C09X A002;
        SharedPreferences.Editor putInt;
        boolean z4;
        final String A003;
        AnonymousClass081 anonymousClass081;
        super.attachBaseContext(context);
        Long valueOf = Long.valueOf(System.nanoTime());
        C020809j.A02();
        boolean A05 = C020809j.A05(this, 61);
        C020809j.A04(this, 61, false);
        if (A05) {
            c0bc = new C0BA(this, 1);
            c0bc2 = new C0BC(this, 2);
            c0bb = new C0BA(this, 2);
        } else {
            c0bc = new C0BC(this, 3);
            c0bc2 = new C0BC(this, 4);
            c0bb = new C0BB(this, 4);
        }
        C09E.A00(this, "275254692598279", c0bc, c0bc2, c0bb);
        C020809j.A02();
        C020809j.A04(this, 61, A05);
        if (C09E.A03 == null) {
            C09E.A00(this, null, null, null, null);
        }
        C09E.A03.A01 = true;
        AbstractC021109m.A05 = new C09n();
        A01("attachBaseContext");
        AbstractC021209o.A02 = valueOf;
        if (AbstractC021209o.A01 == null) {
            AbstractC021209o.A01 = Long.valueOf(SystemClock.elapsedRealtime());
        }
        if (AbstractC021209o.A03 == null) {
            AbstractC021209o.A03 = Long.valueOf(SystemClock.uptimeMillis());
        }
        C020809j.A02();
        if (C020809j.A05(context, 42)) {
            A01("snapPerfStats");
            C09n c09n = new C09n();
            AbstractC021109m.A0A = c09n;
            try {
                C06w c06w = new C06w();
                c06w.A7b(15);
                AbstractC021109m.A01 = c06w;
                c09n.close();
                Trace.endSection();
            }
        }
        A01("initErrorReporter");
        C09n c09n2 = new C09n();
        AbstractC021109m.A08 = c09n2;
        Long l = AbstractC021209o.A03;
        if (l == null) {
            l = Long.valueOf(SystemClock.uptimeMillis());
            AbstractC021209o.A03 = l;
        }
        long longValue = l.longValue();
        try {
            A003 = AbstractC021309p.A00(this);
            AbstractC021609s.A00 = "275254692598279";
            AbstractC021609s.A01 = Long.toString(AbstractC021609s.A00(this));
            C09F.A02(this, new Object() { // from class: X.04f
            }, longValue);
            anonymousClass081 = C09F.A02;
        } catch (Exception e) {
            AbstractC021609s.A01(this);
            AbstractC021609s.A02(this, e, "Failed earlyErrorReporting init");
            C06S.A0B("EarlyErrorReporting", "Failed earlyErrorReporting init", e);
        }
        if (anonymousClass081 != null) {
            anonymousClass081.A07 = new InterfaceC019408s(this, 2) { // from class: X.08l
                public Object A00;
                public final int A01;

                {
                    this.A01 = r2;
                    this.A00 = this;
                }

                @Override // X.InterfaceC019408s
                public final /* bridge */ /* synthetic */ Object get() {
                    switch (this.A01) {
                        case 0:
                            Context context2 = (Context) this.A00;
                            SharedPreferences sharedPreferences = context2.getSharedPreferences(context2.getPackageName(), 0);
                            String string = sharedPreferences.getString("device_id", null);
                            if (!TextUtils.isEmpty(string)) {
                                return string;
                            }
                            String string2 = sharedPreferences.getString("primary_dex_device_id_key", null);
                            if (!TextUtils.isEmpty(string2)) {
                                return string2;
                            }
                            String obj = UUID.randomUUID().toString();
                            sharedPreferences.edit().putString("primary_dex_device_id_key", obj).apply();
                            return obj;
                        case 1:
                            return AbstractC021309p.A00((Context) this.A00);
                        case 2:
                            String A004 = AbstractC021309p.A00((Context) this.A00);
                            if (A004 == null) {
                                return "";
                            }
                            String[] split = A004.split(":");
                            if (split.length <= 1) {
                                return "";
                            }
                            return split[1];
                        default:
                            return Long.toString(AbstractC021609s.A00((Application) this.A00));
                    }
                }
            };
            anonymousClass081.A04 = new InterfaceC019408s(this, 1) { // from class: X.08l
                public Object A00;
                public final int A01;

                {
                    this.A01 = r2;
                    this.A00 = this;
                }

                @Override // X.InterfaceC019408s
                public final /* bridge */ /* synthetic */ Object get() {
                    switch (this.A01) {
                        case 0:
                            Context context2 = (Context) this.A00;
                            SharedPreferences sharedPreferences = context2.getSharedPreferences(context2.getPackageName(), 0);
                            String string = sharedPreferences.getString("device_id", null);
                            if (!TextUtils.isEmpty(string)) {
                                return string;
                            }
                            String string2 = sharedPreferences.getString("primary_dex_device_id_key", null);
                            if (!TextUtils.isEmpty(string2)) {
                                return string2;
                            }
                            String obj = UUID.randomUUID().toString();
                            sharedPreferences.edit().putString("primary_dex_device_id_key", obj).apply();
                            return obj;
                        case 1:
                            return AbstractC021309p.A00((Context) this.A00);
                        case 2:
                            String A004 = AbstractC021309p.A00((Context) this.A00);
                            if (A004 == null) {
                                return "";
                            }
                            String[] split = A004.split(":");
                            if (split.length <= 1) {
                                return "";
                            }
                            return split[1];
                        default:
                            return Long.toString(AbstractC021609s.A00((Application) this.A00));
                    }
                }
            };
            anonymousClass081.A03 = new InterfaceC019408s(this, 0) { // from class: X.08l
                public Object A00;
                public final int A01;

                {
                    this.A01 = r2;
                    this.A00 = this;
                }

                @Override // X.InterfaceC019408s
                public final /* bridge */ /* synthetic */ Object get() {
                    switch (this.A01) {
                        case 0:
                            Context context2 = (Context) this.A00;
                            SharedPreferences sharedPreferences = context2.getSharedPreferences(context2.getPackageName(), 0);
                            String string = sharedPreferences.getString("device_id", null);
                            if (!TextUtils.isEmpty(string)) {
                                return string;
                            }
                            String string2 = sharedPreferences.getString("primary_dex_device_id_key", null);
                            if (!TextUtils.isEmpty(string2)) {
                                return string2;
                            }
                            String obj = UUID.randomUUID().toString();
                            sharedPreferences.edit().putString("primary_dex_device_id_key", obj).apply();
                            return obj;
                        case 1:
                            return AbstractC021309p.A00((Context) this.A00);
                        case 2:
                            String A004 = AbstractC021309p.A00((Context) this.A00);
                            if (A004 == null) {
                                return "";
                            }
                            String[] split = A004.split(":");
                            if (split.length <= 1) {
                                return "";
                            }
                            return split[1];
                        default:
                            return Long.toString(AbstractC021609s.A00((Application) this.A00));
                    }
                }
            };
            anonymousClass081.A08 = new InterfaceC019408s(this, 3) { // from class: X.08l
                public Object A00;
                public final int A01;

                {
                    this.A01 = r2;
                    this.A00 = this;
                }

                @Override // X.InterfaceC019408s
                public final /* bridge */ /* synthetic */ Object get() {
                    switch (this.A01) {
                        case 0:
                            Context context2 = (Context) this.A00;
                            SharedPreferences sharedPreferences = context2.getSharedPreferences(context2.getPackageName(), 0);
                            String string = sharedPreferences.getString("device_id", null);
                            if (!TextUtils.isEmpty(string)) {
                                return string;
                            }
                            String string2 = sharedPreferences.getString("primary_dex_device_id_key", null);
                            if (!TextUtils.isEmpty(string2)) {
                                return string2;
                            }
                            String obj = UUID.randomUUID().toString();
                            sharedPreferences.edit().putString("primary_dex_device_id_key", obj).apply();
                            return obj;
                        case 1:
                            return AbstractC021309p.A00((Context) this.A00);
                        case 2:
                            String A004 = AbstractC021309p.A00((Context) this.A00);
                            if (A004 == null) {
                                return "";
                            }
                            String[] split = A004.split(":");
                            if (split.length <= 1) {
                                return "";
                            }
                            return split[1];
                        default:
                            return Long.toString(AbstractC021609s.A00((Application) this.A00));
                    }
                }
            };
            anonymousClass081.A05 = new C05L(false);
            C07Z.A03.execute(new Runnable() { // from class: X.09q
                public static final String __redex_internal_original_name = "EarlyErrorReporting$13";

                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        C09F.A01();
                        C06S.A0J("EarlyErrorReporting", "Lacrima init executor done in EarlyErrorReporting for: %s", A003);
                    } catch (Exception e2) {
                        AbstractC021609s.A02(this, e2, "Failed earlyJavaInit");
                    }
                }
            });
            C06S.A0J("EarlyErrorReporting", "Lacrima init done in EarlyErrorReporting for: %s", A003);
            C04V c04v = new C04V() { // from class: X.08z
                @Override // X.C04V
                public final String A9f(Throwable th) {
                    Application application = this;
                    return Long.toString(application.getSharedPreferences(application.getPackageName(), 0).getLong("client_id", -1L));
                }
            };
            if (C09F.A03) {
                synchronized (C06M.class) {
                }
                C02300Ak A004 = C02300Ak.A00();
                synchronized (C02300Ak.A03) {
                    A004.A00.put("client_id", c04v);
                }
            }
            c09n2.close();
            Trace.endSection();
            boolean[] zArr = {false, false};
            A01("createProvidersAndEarlySocket");
            if (!C09O.A00) {
                C09O.A00 = true;
                if (!C015306x.A03) {
                    C015306x.A03 = true;
                    C015306x.A02 = new C015306x(context, context.getPackageName());
                    C09n c09n3 = new C09n();
                    AbstractC021109m.A07 = c09n3;
                    String str = C06H.A00().A01;
                    if (str == null) {
                        str = "unknown";
                    }
                    if ("unknown".equals(str)) {
                        String str2 = (String) C06K.A00().first;
                        if (str2 != null) {
                            str = str2.trim();
                        }
                        r0 = false;
                        zArr[0] = r0;
                        if (2 - AnonymousClass095.A00().A01().intValue() == 0) {
                            z4 = false;
                        } else {
                            z4 = true;
                        }
                        boolean z5 = !z4;
                        zArr[1] = z5;
                        c09n3.close();
                        boolean z6 = zArr[0];
                        for (C09I c09i : C09I.values()) {
                            if (c09i.A00 == z5 && c09i.A01 == z6) {
                                if (c09i.A02) {
                                    C09n c09n4 = new C09n();
                                    AbstractC021109m.A04 = c09n4;
                                    FutureTask futureTask = new FutureTask(new Callable(context) { // from class: X.09A
                                        public final Context A00;

                                        /* JADX WARN: Multi-variable type inference failed */
                                        /* JADX WARN: Type inference failed for: r0v0, types: [X.0AW] */
                                        /* JADX WARN: Type inference failed for: r4v3, types: [java.util.AbstractCollection, java.util.ArrayList] */
                                        /* JADX WARN: Type inference failed for: r4v5, types: [java.util.List] */
                                        /* JADX WARN: Type inference failed for: r4v6, types: [java.util.List] */
                                        @Override // java.util.concurrent.Callable
                                        public final /* bridge */ /* synthetic */ Object call() {
                                            Integer num;
                                            List A005;
                                            C09R c09r;
                                            String str3;
                                            int i;
                                            C09K c09k;
                                            C01X ALP;
                                            List list;
                                            String str4;
                                            Map<String, ?> all;
                                            C09H c09h = new C09H();
                                            Context context2 = this.A00;
                                            c09h.A03 = context2;
                                            c09h.A04 = new C09L() { // from class: X.0AW
                                                @Override // X.C09L
                                                public final C01X ALP(C0AV c0av) {
                                                    Socket socket = new Socket();
                                                    C09R c09r2 = c0av.A00;
                                                    socket.connect(new InetSocketAddress((InetAddress) c0av.A01.get(0), c09r2.A01.A00), c09r2.A00);
                                                    socket.getOutputStream();
                                                    return new AnonymousClass005(c0av, socket);
                                                }
                                            };
                                            C020809j.A02();
                                            c09h.A00 = C020809j.A00(context2, 45, 2);
                                            C020809j.A02();
                                            c09h.A08 = C020809j.A05(context2, 46);
                                            C020809j.A02();
                                            c09h.A07 = C020809j.A05(context2, 58);
                                            C020809j.A02();
                                            c09h.A02 = C020809j.A00(context2, 52, -1);
                                            C020809j.A02();
                                            c09h.A01 = C020809j.A00(context2, 57, 0);
                                            if (c09h.A03 != null && c09h.A04 != null) {
                                                Map map = c09h.A06;
                                                AnonymousClass000.A0G(C09J.REFERENCE_TIME, map, System.nanoTime());
                                                try {
                                                    try {
                                                        C015306x c015306x = C015306x.A02;
                                                        C09R A006 = C09C.A00(c015306x);
                                                        ?? A0B = AnonymousClass000.A0B();
                                                        if (c09h.A08) {
                                                            SharedPreferences sharedPreferences = c015306x.A00.getSharedPreferences("snaptu_domians_fallback_mapping", 0);
                                                            if (sharedPreferences == null) {
                                                                all = null;
                                                            } else {
                                                                all = sharedPreferences.getAll();
                                                            }
                                                            C01P.A00 = all;
                                                        }
                                                        C0AV c0av = null;
                                                        if (A006 == null) {
                                                            A006 = new C09R(new C09S("snaptu-z.facebook.com", 443), 5000);
                                                            SharedPreferences.Editor edit = c015306x.A00.getSharedPreferences(c015306x.A01, 0).edit();
                                                            C09S c09s = A006.A01;
                                                            int i2 = c09s.A00;
                                                            SharedPreferences.Editor putInt2 = edit.putInt("early_socket_connection_port", i2);
                                                            String str5 = c09s.A03;
                                                            putInt2.putString("early_socket_connection_host", str5).putInt("early_connection_default_timeout_mills", 5000).apply();
                                                            if (c09h.A08) {
                                                                String str6 = c09s.A02;
                                                                Map map2 = C01P.A00;
                                                                if (map2 != null && (str4 = (String) map2.get(str6)) != null) {
                                                                    c09r = new C09R(new C09S(str4, i2), 5000);
                                                                } else {
                                                                    c09r = null;
                                                                }
                                                                if (c09r != null) {
                                                                    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
                                                                    Future submit = newFixedThreadPool.submit(new C0BB(1, A006, c09h));
                                                                    Future submit2 = newFixedThreadPool.submit(new C0BB(2, c09r, c09h));
                                                                    try {
                                                                        A005 = (List) submit.get();
                                                                        try {
                                                                            int i3 = c09h.A02;
                                                                            if (i3 < 0) {
                                                                                list = (List) submit2.get();
                                                                            } else {
                                                                                list = (List) submit2.get(i3, TimeUnit.MILLISECONDS);
                                                                            }
                                                                            A0B.addAll(list);
                                                                        } catch (Exception unused) {
                                                                        } catch (Throwable th) {
                                                                            newFixedThreadPool.shutdown();
                                                                            throw th;
                                                                        }
                                                                        newFixedThreadPool.shutdown();
                                                                    } catch (Exception unused2) {
                                                                        newFixedThreadPool.shutdown();
                                                                        throw new UnknownHostException();
                                                                    }
                                                                } else {
                                                                    A005 = Arrays.asList(InetAddress.getAllByName(str5));
                                                                }
                                                            } else {
                                                                A005 = Arrays.asList(InetAddress.getAllByName(str5));
                                                                c09r = null;
                                                            }
                                                        } else {
                                                            Context context3 = c09h.A03;
                                                            C09S c09s2 = A006.A01;
                                                            A005 = A00(context3, c09s2.A03, C09B.A02(context3));
                                                            if (c09h.A08) {
                                                                String str7 = c09s2.A02;
                                                                Map map3 = C01P.A00;
                                                                if (map3 != null && (str3 = (String) map3.get(str7)) != null) {
                                                                    c09r = new C09R(new C09S(str3, c09s2.A00), A006.A00);
                                                                } else {
                                                                    c09r = null;
                                                                }
                                                                if (c09r != null) {
                                                                    Context context4 = c09h.A03;
                                                                    A0B = A00(context4, c09r.A01.A03, C09B.A02(context4));
                                                                }
                                                            }
                                                            c09r = null;
                                                        }
                                                        if (A005.isEmpty()) {
                                                            return new C01Y(C04P.A01, map);
                                                        }
                                                        C0AV c0av2 = new C0AV(A006, A005, false);
                                                        if (!A0B.isEmpty()) {
                                                            c0av = new C0AV(c09r, A0B, false);
                                                        }
                                                        AnonymousClass000.A0G(C09J.TIME_TO_PARAMS, map, System.nanoTime());
                                                        Context context5 = c09h.A03;
                                                        int availableProcessors = Runtime.getRuntime().availableProcessors();
                                                        boolean z7 = true;
                                                        if (availableProcessors <= 0) {
                                                            try {
                                                                i = new File("/sys/devices/system/cpu/").listFiles(new FileFilter() { // from class: X.099
                                                                    @Override // java.io.FileFilter
                                                                    public final boolean accept(File file) {
                                                                        return file.getName().matches("cpu[0-9]+");
                                                                    }
                                                                }).length;
                                                                if (i == 0) {
                                                                    i = -1;
                                                                }
                                                            } catch (Exception unused3) {
                                                                i = -1;
                                                            }
                                                            availableProcessors = Math.max(i, 1);
                                                        }
                                                        if (availableProcessors < 2) {
                                                            z7 = false;
                                                        }
                                                        Integer A007 = C09B.A00(context5);
                                                        if (z7 && A007 != C04P.A0C) {
                                                            c09k = C09K.CONCURRENT;
                                                        } else {
                                                            c09k = C09K.SINGLE;
                                                        }
                                                        c09h.A05 = c09k;
                                                        C0AW c0aw = c09h.A04;
                                                        if (c09k.ordinal() != 0) {
                                                            ArrayList A0B2 = AnonymousClass000.A0B();
                                                            boolean z8 = c09h.A07;
                                                            C03P c03p = new C03P(c0av2, 0L, z8);
                                                            int i4 = 0;
                                                            if (c0av != null) {
                                                                while (i4 < c09h.A00 - 1) {
                                                                    A0B2.add(c03p);
                                                                    i4++;
                                                                }
                                                                A0B2.add(new C03P(c0av, c09h.A01, z8));
                                                            } else {
                                                                while (i4 < c09h.A00) {
                                                                    A0B2.add(c03p);
                                                                    i4++;
                                                                }
                                                            }
                                                            ALP = new C03M(c0aw, Executors.newCachedThreadPool()).A01(A0B2);
                                                        } else {
                                                            ALP = c0aw.ALP(c0av2);
                                                        }
                                                        AnonymousClass005 anonymousClass005 = (AnonymousClass005) ALP;
                                                        AnonymousClass000.A0G(C09J.TIME_TO_SOCKET_CONNECTED, map, System.nanoTime());
                                                        C0AV c0av3 = anonymousClass005.A00;
                                                        Socket socket = anonymousClass005.A01;
                                                        ParcelFileDescriptor fromSocket = ParcelFileDescriptor.fromSocket(socket);
                                                        if (fromSocket == null) {
                                                            return C09H.A00(c09h, null, C04P.A0h, "Failed to get fd from EarlySocket.", socket);
                                                        }
                                                        try {
                                                            return new C01Y(fromSocket, ParcelFileDescriptor.dup(fromSocket.getFileDescriptor()), c09h.A05, c0av3, C04P.A0C, C04P.A00, "", socket, map);
                                                        } catch (IOException e2) {
                                                            return C09H.A00(c09h, e2, C04P.A0Z, "Failed to create dupped EarlySocket fd.", socket);
                                                        }
                                                    } catch (InterruptedException e3) {
                                                        e = e3;
                                                        Log.e("EarlySocket", "Unable to create early socket concurrently.", e);
                                                        num = C04P.A0o;
                                                        return new C01Y(null, null, c09h.A05, null, C04P.A01, num, e.getMessage(), null, map);
                                                    }
                                                } catch (UnknownHostException e4) {
                                                    e = e4;
                                                    Log.e("EarlySocket", "Unable to resolve ip address for early socket.", e);
                                                    num = C04P.A0N;
                                                    return new C01Y(null, null, c09h.A05, null, C04P.A01, num, e.getMessage(), null, map);
                                                } catch (IOException e5) {
                                                    e = e5;
                                                    Log.e("EarlySocket", "Unable to connect early socket.", e);
                                                    num = C04P.A0T;
                                                    return new C01Y(null, null, c09h.A05, null, C04P.A01, num, e.getMessage(), null, map);
                                                }
                                            }
                                            throw new IllegalStateException("Not all EarlySocketBuilder params were initialized before use.");
                                        }

                                        {
                                            this.A00 = context;
                                        }

                                        public static List A00(Context context2, String str3, boolean z7) {
                                            String A07 = AnonymousClass000.A07("_dns_cache", AnonymousClass000.A0A(context2.getPackageName()));
                                            StringBuilder A0A = AnonymousClass000.A0A(str3);
                                            A0A.append("/");
                                            A0A.append(z7);
                                            return C09M.A01(str3, context2.getSharedPreferences(A07, 0).getString(A0A.toString(), ""));
                                        }
                                    });
                                    new Thread(futureTask).start();
                                    AnonymousClass094.A01 = futureTask;
                                    c09n4.close();
                                }
                                AnonymousClass094.A00 = c09i;
                                Trace.endSection();
                                if (zArr[0]) {
                                    SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), 0);
                                    String string = sharedPreferences.getString("prev_startup_app_version", null);
                                    int i = sharedPreferences.getInt("new_processes_since_upgrade", 0);
                                    if ("413.0.0.5.100".equals(string)) {
                                        if (i < 2) {
                                            putInt = sharedPreferences.edit().putInt("new_processes_since_upgrade", i + 1);
                                        }
                                    } else {
                                        putInt = sharedPreferences.edit().putString("prev_startup_app_version", "413.0.0.5.100").putInt("new_processes_since_upgrade", 1);
                                    }
                                    putInt.apply();
                                }
                                FutureTask futureTask2 = new FutureTask(new C0BB(0, this, this));
                                new Thread(futureTask2).start();
                                AnonymousClass093.A00 = futureTask2;
                                A01("FbSoLoaderInit");
                                C09n c09n5 = new C09n();
                                AbstractC021109m.A06 = c09n5;
                                C015807c.A00(this, 131074);
                                c09n5.close();
                                Trace.endSection();
                                try {
                                    C07Z.A02.execute(new Runnable() { // from class: X.09r
                                        public static final String __redex_internal_original_name = "EarlyErrorReporting$8";

                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            try {
                                                C09F.A09.await();
                                                synchronized (C09F.A08) {
                                                    try {
                                                        if (C09F.A06) {
                                                            return;
                                                        }
                                                        C09F.A06 = true;
                                                        AnonymousClass080 A005 = C09F.A00();
                                                        if (A005.A01 != null) {
                                                            Trace.beginSection("earlyNativeInit");
                                                            AbstractC018908m.A00();
                                                            if (A005.A0U && !MLockedFile.A00) {
                                                                boolean z7 = true;
                                                                try {
                                                                    C02420Ay.A07("appstatelogger2");
                                                                } catch (RuntimeException | UnsatisfiedLinkError unused) {
                                                                    AbstractC018908m.A00();
                                                                    z7 = false;
                                                                }
                                                                MLockedFile.A00 = z7;
                                                            }
                                                            AnonymousClass090 anonymousClass090 = A005.A01;
                                                            C04R.A02(anonymousClass090, "Did you call earlyInit()?");
                                                            C05T c05t = anonymousClass090.A04;
                                                            C04R.A02(c05t, "Did you call SessionManager.init()?");
                                                            synchronized (c05t.A02) {
                                                                try {
                                                                    c05t.A01.A00();
                                                                } catch (Throwable th) {
                                                                    throw th;
                                                                }
                                                            }
                                                            for (InterfaceC017607x interfaceC017607x : A005.A0F) {
                                                                AbstractC018908m.A00();
                                                                interfaceC017607x.AHf(A005);
                                                            }
                                                            AnonymousClass090 anonymousClass0902 = A005.A01;
                                                            C04R.A02(anonymousClass0902, "Did you call earlyInit()?");
                                                            anonymousClass0902.A00 = SystemClock.uptimeMillis();
                                                            AbstractC018908m.A00();
                                                            Trace.endSection();
                                                        }
                                                    } catch (Throwable th2) {
                                                        throw th2;
                                                    }
                                                }
                                            } catch (Exception e2) {
                                                AbstractC021609s.A02(this, e2, "Failed earlyNativeInit");
                                            }
                                        }
                                    });
                                } catch (Exception e2) {
                                    AbstractC021609s.A02(this, e2, "Failed earlyNativeInit");
                                }
                                C020809j.A02();
                                if (C020809j.A05(context, 56)) {
                                    A01("DisableClassVerification");
                                    C09n c09n6 = new C09n();
                                    AbstractC021109m.A03 = c09n6;
                                    c09n6.close();
                                    Trace.endSection();
                                }
                                Trace.beginSection("ClientApplicationSplittedShell.secondaryDexLoad");
                                try {
                                    C09n c09n7 = new C09n();
                                    AbstractC021109m.A02 = c09n7;
                                    if (zArr[0]) {
                                        if (zArr[1]) {
                                            C020809j.A02();
                                        }
                                        C020809j.A02();
                                        z = C020809j.A05(context, 9);
                                        C020809j.A04(context, 9, false);
                                        if (!z) {
                                            C020809j.A02();
                                            z2 = C020809j.A05(context, 8);
                                            C020809j.A04(context, 8, false);
                                            boolean z7 = zArr[0];
                                            Executor executor = new Executor() { // from class: X.091
                                                @Override // java.util.concurrent.Executor
                                                public final void execute(Runnable runnable) {
                                                    AbstractC021109m.A0D.add(runnable);
                                                }
                                            };
                                            C09T c09t = new C09T(this) { // from class: X.0AL
                                                public ZipFile A00;
                                                public final Context A01;

                                                @Override // X.C09T
                                                public final InputStream AW0(String str3) {
                                                    return this.A01.getAssets().open(AnonymousClass000.A06("secondary-program-dex-jars/", str3, AnonymousClass000.A09()));
                                                }

                                                @Override // X.C09T
                                                public final InputStream AW2(String str3) {
                                                    ZipFile zipFile = this.A00;
                                                    if (zipFile == null) {
                                                        zipFile = new ZipFile(this.A01.getApplicationInfo().sourceDir);
                                                        this.A00 = zipFile;
                                                    }
                                                    return zipFile.getInputStream(zipFile.getEntry(str3));
                                                }

                                                {
                                                    this.A01 = this;
                                                }
                                            };
                                            c09t.AW0("metadata.txt").close();
                                            File file = new File(getApplicationInfo().dataDir, "dex");
                                            A002 = C09Y.A00(this, c09t, file);
                                            if (A002 != null) {
                                                try {
                                                    C02310Al c02310Al = new C02310Al(AnonymousClass000.A02(file, "dex_lock"));
                                                    A002.A3y();
                                                    c02310Al.close();
                                                    try {
                                                        C02310Al c02310Al2 = new C02310Al(AnonymousClass000.A02(file, "dex_lock"));
                                                        AbstractC021009l.A02 = System.nanoTime();
                                                        A002.AHw(this, executor, z7);
                                                        c02310Al2.close();
                                                        AbstractC021009l.A01 = System.nanoTime();
                                                    } catch (Throwable th) {
                                                        AbstractC021009l.A01 = System.nanoTime();
                                                        throw th;
                                                    }
                                                } catch (Throwable th2) {
                                                    throw th2;
                                                }
                                            }
                                            if (C09G.A00) {
                                                C06S.A06("MDCDClassLoadInstaller", "Tried to install MDCD class logger twice");
                                            }
                                            C09G.A00 = true;
                                            C020809j.A02();
                                            z3 = false;
                                            A00 = C020809j.A00(this, 39, 0);
                                            if (A00 > 0 && new Random().nextInt(A00) == 0) {
                                                z3 = true;
                                            }
                                            C09G.A01 = z3;
                                            if (z3) {
                                                C07T.A00(this).A06.add(new InterfaceC02340Ap() { // from class: X.0Aj
                                                    public static boolean A00;

                                                    @Override // X.InterfaceC02340Ap
                                                    public final void A3s(Class cls) {
                                                        if (A00) {
                                                            ClassTracingLoggerFbLite.classLoaded(cls);
                                                        }
                                                    }

                                                    @Override // X.InterfaceC02340Ap
                                                    public final void A3t() {
                                                        if (A00) {
                                                            ClassTracingLoggerFbLite.classNotFound();
                                                        }
                                                    }

                                                    {
                                                        ClassTracingLoggerFbLite.sEnabled = true;
                                                        A00 = true;
                                                    }

                                                    @Override // X.InterfaceC02340Ap
                                                    public final void A3u(String str3) {
                                                    }
                                                });
                                            }
                                            if (!z || z2) {
                                                new Thread(new Runnable() { // from class: X.09V
                                                    public static final String __redex_internal_original_name = "DexCompilation$1";

                                                    @Override // java.lang.Runnable
                                                    public final void run() {
                                                        StringBuilder A09 = AnonymousClass000.A09();
                                                        A09.append(this.getApplicationInfo().dataDir);
                                                        File[] listFiles = new File(AnonymousClass000.A07("/app_modules", A09)).listFiles();
                                                        if (listFiles != null) {
                                                            for (File file2 : listFiles) {
                                                                try {
                                                                    if ((!z2 || file2.getCanonicalPath().contains("heroplayer")) && file2.isDirectory()) {
                                                                        StringBuilder A092 = AnonymousClass000.A09();
                                                                        A092.append(file2.getCanonicalPath());
                                                                        File[] listFiles2 = new File(AnonymousClass000.A07("/dex", A092)).listFiles();
                                                                        if (listFiles2 != null) {
                                                                            for (File file3 : listFiles2) {
                                                                                if (file3.getCanonicalPath().endsWith(".dex")) {
                                                                                    file3.getCanonicalPath();
                                                                                    new C09W(new C0AN(), new C019508t(), file3).A01(null);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                } catch (IOException unused) {
                                                                }
                                                            }
                                                        }
                                                    }
                                                }).start();
                                            }
                                            c09n7.close();
                                            Trace.endSection();
                                            C020809j.A02();
                                            if (!C020809j.A05(context, 62)) {
                                                A00();
                                                return;
                                            }
                                            return;
                                        }
                                        z2 = false;
                                        boolean z72 = zArr[0];
                                        Executor executor2 = new Executor() { // from class: X.091
                                            @Override // java.util.concurrent.Executor
                                            public final void execute(Runnable runnable) {
                                                AbstractC021109m.A0D.add(runnable);
                                            }
                                        };
                                        C09T c09t2 = new C09T(this) { // from class: X.0AL
                                            public ZipFile A00;
                                            public final Context A01;

                                            @Override // X.C09T
                                            public final InputStream AW0(String str3) {
                                                return this.A01.getAssets().open(AnonymousClass000.A06("secondary-program-dex-jars/", str3, AnonymousClass000.A09()));
                                            }

                                            @Override // X.C09T
                                            public final InputStream AW2(String str3) {
                                                ZipFile zipFile = this.A00;
                                                if (zipFile == null) {
                                                    zipFile = new ZipFile(this.A01.getApplicationInfo().sourceDir);
                                                    this.A00 = zipFile;
                                                }
                                                return zipFile.getInputStream(zipFile.getEntry(str3));
                                            }

                                            {
                                                this.A01 = this;
                                            }
                                        };
                                        c09t2.AW0("metadata.txt").close();
                                        File file2 = new File(getApplicationInfo().dataDir, "dex");
                                        A002 = C09Y.A00(this, c09t2, file2);
                                        if (A002 != null) {
                                        }
                                        if (C09G.A00) {
                                        }
                                        C09G.A00 = true;
                                        C020809j.A02();
                                        z3 = false;
                                        A00 = C020809j.A00(this, 39, 0);
                                        if (A00 > 0) {
                                            z3 = true;
                                        }
                                        C09G.A01 = z3;
                                        if (z3) {
                                        }
                                        if (!z) {
                                        }
                                        new Thread(new Runnable() { // from class: X.09V
                                            public static final String __redex_internal_original_name = "DexCompilation$1";

                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                StringBuilder A09 = AnonymousClass000.A09();
                                                A09.append(this.getApplicationInfo().dataDir);
                                                File[] listFiles = new File(AnonymousClass000.A07("/app_modules", A09)).listFiles();
                                                if (listFiles != null) {
                                                    for (File file22 : listFiles) {
                                                        try {
                                                            if ((!z2 || file22.getCanonicalPath().contains("heroplayer")) && file22.isDirectory()) {
                                                                StringBuilder A092 = AnonymousClass000.A09();
                                                                A092.append(file22.getCanonicalPath());
                                                                File[] listFiles2 = new File(AnonymousClass000.A07("/dex", A092)).listFiles();
                                                                if (listFiles2 != null) {
                                                                    for (File file3 : listFiles2) {
                                                                        if (file3.getCanonicalPath().endsWith(".dex")) {
                                                                            file3.getCanonicalPath();
                                                                            new C09W(new C0AN(), new C019508t(), file3).A01(null);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } catch (IOException unused) {
                                                        }
                                                    }
                                                }
                                            }
                                        }).start();
                                        c09n7.close();
                                        Trace.endSection();
                                        C020809j.A02();
                                        if (!C020809j.A05(context, 62)) {
                                        }
                                    }
                                    z = false;
                                    z2 = false;
                                    boolean z722 = zArr[0];
                                    Executor executor22 = new Executor() { // from class: X.091
                                        @Override // java.util.concurrent.Executor
                                        public final void execute(Runnable runnable) {
                                            AbstractC021109m.A0D.add(runnable);
                                        }
                                    };
                                    C09T c09t22 = new C09T(this) { // from class: X.0AL
                                        public ZipFile A00;
                                        public final Context A01;

                                        @Override // X.C09T
                                        public final InputStream AW0(String str3) {
                                            return this.A01.getAssets().open(AnonymousClass000.A06("secondary-program-dex-jars/", str3, AnonymousClass000.A09()));
                                        }

                                        @Override // X.C09T
                                        public final InputStream AW2(String str3) {
                                            ZipFile zipFile = this.A00;
                                            if (zipFile == null) {
                                                zipFile = new ZipFile(this.A01.getApplicationInfo().sourceDir);
                                                this.A00 = zipFile;
                                            }
                                            return zipFile.getInputStream(zipFile.getEntry(str3));
                                        }

                                        {
                                            this.A01 = this;
                                        }
                                    };
                                    c09t22.AW0("metadata.txt").close();
                                    File file22 = new File(getApplicationInfo().dataDir, "dex");
                                    A002 = C09Y.A00(this, c09t22, file22);
                                    if (A002 != null) {
                                    }
                                    if (C09G.A00) {
                                    }
                                    C09G.A00 = true;
                                    C020809j.A02();
                                    z3 = false;
                                    A00 = C020809j.A00(this, 39, 0);
                                    if (A00 > 0) {
                                    }
                                    C09G.A01 = z3;
                                    if (z3) {
                                    }
                                    if (!z) {
                                    }
                                    new Thread(new Runnable() { // from class: X.09V
                                        public static final String __redex_internal_original_name = "DexCompilation$1";

                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            StringBuilder A09 = AnonymousClass000.A09();
                                            A09.append(this.getApplicationInfo().dataDir);
                                            File[] listFiles = new File(AnonymousClass000.A07("/app_modules", A09)).listFiles();
                                            if (listFiles != null) {
                                                for (File file222 : listFiles) {
                                                    try {
                                                        if ((!z2 || file222.getCanonicalPath().contains("heroplayer")) && file222.isDirectory()) {
                                                            StringBuilder A092 = AnonymousClass000.A09();
                                                            A092.append(file222.getCanonicalPath());
                                                            File[] listFiles2 = new File(AnonymousClass000.A07("/dex", A092)).listFiles();
                                                            if (listFiles2 != null) {
                                                                for (File file3 : listFiles2) {
                                                                    if (file3.getCanonicalPath().endsWith(".dex")) {
                                                                        file3.getCanonicalPath();
                                                                        new C09W(new C0AN(), new C019508t(), file3).A01(null);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } catch (IOException unused) {
                                                    }
                                                }
                                            }
                                        }
                                    }).start();
                                    c09n7.close();
                                    Trace.endSection();
                                    C020809j.A02();
                                    if (!C020809j.A05(context, 62)) {
                                    }
                                } catch (IOException e3) {
                                    throw new RuntimeException(e3);
                                }
                            }
                        }
                        throw new UnsupportedOperationException();
                    }
                    boolean z8 = str != null ? true : true;
                    z8 = false;
                    zArr[0] = z8;
                    if (2 - AnonymousClass095.A00().A01().intValue() == 0) {
                    }
                    boolean z52 = !z4;
                    zArr[1] = z52;
                    c09n3.close();
                    boolean z62 = zArr[0];
                    while (r2 < r7) {
                    }
                    throw new UnsupportedOperationException();
                }
                throw new IllegalStateException("LeanAndroidSharedPrefsProvider was already initialized");
            }
            throw new IllegalStateException("LeanMockConnectivityProvider was already initialized");
        }
        throw new IllegalStateException("Accessing LacrimaConfigBuilder before Lacrima init");
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final File getCacheDir() {
        File cacheDir = super.getCacheDir();
        C016007e.A04(cacheDir, 1);
        if (AbstractC012505m.A00) {
            File A00 = AbstractC012705o.A00(this, 486209204);
            A00.mkdirs();
            if (A00.isDirectory() || A00.mkdirs()) {
                return A00;
            }
        }
        return cacheDir;
    }

    @Override // android.app.Application
    public final void onCreate() {
        super.onCreate();
        A00();
        this.A00.onCreate();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        super.onTrimMemory(i);
        C00I c00i = this.A00;
        if (c00i != null) {
            c00i.onTrimMemory(i);
        }
    }
}
