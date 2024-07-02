package com.facebook.superpack;

import X.AnonymousClass000;
import X.C018408g;
import X.C06E;
import X.C06S;
import android.os.SystemClock;
import com.facebook.common.dextricks.classtracing.logger.ClassTracingLoggerFbLite;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public class SuperpackUnloader {
    public static final Set COLD_START_LIBS;
    public static final Set HOLDOUT_LIBRARIES;
    public static final long LEVEL_1_LIBRARIES_THRESHOLD = 20000;
    public static final long LEVEL_2_LIBRARIES_THRESHOLD = 300000;
    public static final long LEVEL_3_LIBRARIES_THRESHOLD = 1500000;
    public static final long STARTUP_LOAD_TIME_THRESHOLD = 10000;
    public static final String TAG = "SuperpackUnloader";
    public static final List sInMemoryLibraries;
    public static long sStartTime;

    public static native long getProcessMappings(String str);

    public static native void releaseProcessMappings(long j);

    public static native void unload(long j, String str);

    static {
        String[] strArr = new String[35];
        System.arraycopy(new String[]{"libbreakpad.so", "libliger.so", "libcoldstart.so", "libdexload.so", "libreliabilitymerged.so", "libsigquit.so", "libappstatelogger2.so", "libnative_allocation_hooks_installer_jni.so", "libforker.so", "libgrimsey.so", "libfb_mboost.so", "libplthooks.so", "libfbandroid_native_cppdistract_cppdistract.so", "liblogcat-interceptor.so", "libbreakpad_extra.so", "libfbandroid_native_sigmuxutils_sigmuxutils.so", "libxplat_pvd_segmentation_model_holder_plc_pytorch_model_holdersAndroid.so", "libdistractutil.so", "libchipsetmerged.so", "libvmasaver.so", "libdalvikdistract.so", "libfbandroid_native_museum_museum.so", "libglog.so", "libpando-core.so", "libpando-engine.so", "libxplat_third-party_jsoncpp_jsoncppAndroid.so", "libxplat_mobilenetwork_fbdomainsAndroid.so"}, 0, strArr, 0, 27);
        System.arraycopy(new String[]{"libfmt.so", "libthird-party_boost_boost_contextAndroid.so", "libthird-party_boost_boostAndroid.so", "liblinkerutils.so", "libmem_alloc_marker.so", "libfbunwindstack.so", "liblive-query-jni.so", "libaospbugfixmerged.so"}, 0, strArr, 27, 8);
        HOLDOUT_LIBRARIES = new HashSet(Arrays.asList(strArr));
        String[] strArr2 = new String[35];
        System.arraycopy(new String[]{"libfbandroid_native_cppdistract_cppdistract.so", "libfbandroid_native_museum_museum.so", "libglog.so", "libfbandroid_native_sigmuxutils_sigmuxutils.so", "libbreakpad.so", "libdexload.so", "libxplat_third-party_jsoncpp_jsoncppAndroid.so", "libappstatelogger2.so", "libpreconnector.so", "libxplat_mobilenetwork_fbdomainsAndroid.so", "libfmt.so", "libthird-party_boost_boost_contextAndroid.so", "libthird-party_boost_boostAndroid.so", "liblinkerutils.so", "libplthooks.so", "libmem_alloc_marker.so", "libfbunwindstack.so", "liblive-query-jni.so", "libcoldstart.so", "libaospbugfixmerged.so", "libsigquit.so", "libreliabilitymerged.so", "libfb_mboost.so", "libtigonnativeauthedservice.so", "libimagepipeline.so", "libxplat_arfx_versioning_sdk_version_constants_constantsAndroid.so", "libard-upload.so"}, 0, strArr2, 0, 27);
        System.arraycopy(new String[]{"libIGL.so", "libimagesmerged.so", "libthreadutils-jni.so", "libclasstracing.so", "libunwindstack_stream.so", "libbreakpad_extra.so", "libfbnightwatch.so", "libreliablemediamonitor.so"}, 0, strArr2, 27, 8);
        COLD_START_LIBS = new HashSet(Arrays.asList(strArr2));
        sInMemoryLibraries = Collections.synchronizedList(AnonymousClass000.A0B());
    }

    public static boolean canUnloadLibrary(String str) {
        return !HOLDOUT_LIBRARIES.contains(str);
    }

    public static boolean isStartupLibrary(C018408g c018408g) {
        if (COLD_START_LIBS.contains(c018408g.A01) && c018408g.A00 < STARTUP_LOAD_TIME_THRESHOLD) {
            return true;
        }
        return false;
    }

    public static void registerLibraryForUnloading(String str) {
        if (!str.endsWith(".so")) {
            String libraryName = SuperpackFileLoader.getLibraryName(str);
            if (!HOLDOUT_LIBRARIES.contains(libraryName)) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                long j = 0;
                if (sStartTime == 0) {
                    synchronized (SuperpackUnloader.class) {
                        if (sStartTime == 0) {
                            sStartTime = elapsedRealtime;
                        }
                    }
                }
                long j2 = elapsedRealtime - sStartTime;
                if (j2 >= 0) {
                    j = j2;
                }
                try {
                    str = new File(str).getCanonicalPath();
                } catch (IOException unused) {
                }
                sInMemoryLibraries.add(new C018408g(libraryName, str, j));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0066 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onTrim(C06E c06e) {
        long j;
        if (c06e != C06E.UI_HIDDEN && c06e != C06E.JAVA_HEAP_ALMOST_FULL) {
            List list = sInMemoryLibraries;
            if (!list.isEmpty()) {
                ArrayList arrayList = new ArrayList(list);
                ArrayList A0B = AnonymousClass000.A0B();
                if (c06e != C06E.COMPLETE) {
                    int size = arrayList.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        C018408g c018408g = (C018408g) arrayList.get(size);
                        if (!shouldUnloadLibrary(c06e, c018408g)) {
                            break;
                        }
                        A0B.add(c018408g);
                    }
                    arrayList = A0B;
                }
                try {
                    j = getProcessMappings(SuperpackFileLoader.SUPERPACK_EXTENSION);
                    try {
                        try {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                unload(j, ((C018408g) it.next()).A02);
                            }
                        } catch (RuntimeException e) {
                            e = e;
                            C06S.A09(TAG, "Failed to unload in-memory compressed libraries. Ignoring.", e);
                        }
                    } finally {
                        if (j != 0) {
                            releaseProcessMappings(j);
                        }
                    }
                } catch (RuntimeException e2) {
                    e = e2;
                    j = 0;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0024 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0009 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean shouldUnloadLibrary(C06E c06e, C018408g c018408g) {
        long j;
        long j2;
        switch (c06e.ordinal()) {
            case 0:
            case 5:
                return true;
            case 1:
                j = c018408g.A00;
                j2 = LEVEL_3_LIBRARIES_THRESHOLD;
                if (j <= j2) {
                    return false;
                }
                return true;
            case 2:
                j = c018408g.A00;
                j2 = LEVEL_2_LIBRARIES_THRESHOLD;
                if (j <= j2) {
                }
                break;
            case 3:
                if (!isStartupLibrary(c018408g)) {
                    j = c018408g.A00;
                    j2 = LEVEL_1_LIBRARIES_THRESHOLD;
                    if (j <= j2) {
                    }
                }
                return false;
            case ClassTracingLoggerFbLite.ENCODED_CLASS_NAMES_LENGTH /* 4 */:
                return !isStartupLibrary(c018408g);
        }
    }
}
