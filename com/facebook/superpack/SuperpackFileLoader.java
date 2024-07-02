package com.facebook.superpack;

import X.AbstractC007102y;
import X.AnonymousClass000;
import X.AnonymousClass031;
import X.C02420Ay;
import X.C06S;
import X.C07p;
import X.C0B4;
import X.InterfaceC02400Aw;
import android.text.TextUtils;
import com.facebook.breakpad.BreakpadManager;
import com.facebook.common.stringformat.StringFormatUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes.dex */
public class SuperpackFileLoader implements InterfaceC02400Aw {
    public static final String LOADER_CLASS = "com.facebook.superpack.SuperpackFileLoader";
    public static final String SUPERPACK_EXTENSION = ".spk";
    public static final String SUPERPACK_LIBNAME = "libsuperpack-jni.so";
    public static final String SUPERPACK_LIBNAME_FOR_SOLOADER = "superpack-jni";
    public static final String TAG = "SuperpackFileLoader";
    public static SuperpackFileLoader sInstance;
    public static boolean sLoadedSuperpack;
    public final String mLocalLdLibraryPath;
    public final String mLocalLdLibraryPathNoZips;
    public final Method mNativeLoadRuntimeMethod;
    public static final String[] COMPRESSION_EXTENSIONS = {".lz4", ".zst", ".xz", ".zstd", ".br", ".spo"};
    public static final Map sPendingMappings = Collections.synchronizedMap(AnonymousClass000.A0C());
    public static final Set HOLDOUT_LIBRARIES = new HashSet(Arrays.asList("libliger.so"));
    public static final String[] SIGMUX_LIB = {"libdistractmerged.so", "libsigmux.so"};
    public static final String[] SIGMUX_SOLOADER_LIB = {"distractmerged", "sigmux"};
    public boolean mUnloadLibraries = false;
    public boolean mForceSystemLoad = false;
    public final Runtime mRuntime = Runtime.getRuntime();

    public static void addMappingsToBreakpad(MappingInfo[] mappingInfoArr) {
        for (MappingInfo mappingInfo : mappingInfoArr) {
            addMappingToBreakpad(mappingInfo);
        }
    }

    public static native boolean canLoadInMemoryNative();

    public static native MappingInfo[] loadBytesNative(String str, byte[] bArr);

    public static native MappingInfo[] loadFdNative(String str, int i, long j, long j2);

    public static native void loadFileNative(String str);

    public void setUnloadLibraries() {
        this.mUnloadLibraries = true;
    }

    /* loaded from: classes.dex */
    public class MappingInfo {
        public final byte[] buildId;
        public final long fileOffset;
        public final long mappingSize;
        public final String name;
        public final long startAddress;

        public MappingInfo(String str, byte[] bArr, long j, long j2, long j3) {
            this.name = str;
            this.buildId = bArr;
            this.startAddress = j;
            this.mappingSize = j2;
            this.fileOffset = j3;
        }
    }

    public static void addMappingToBreakpad(MappingInfo mappingInfo) {
        if (!BreakpadManager.initialized) {
            sPendingMappings.put(Long.valueOf(mappingInfo.startAddress), mappingInfo);
            return;
        }
        String str = mappingInfo.name;
        byte[] bArr = mappingInfo.buildId;
        BreakpadManager.addMappingInfo(str, bArr, bArr.length, mappingInfo.startAddress, mappingInfo.mappingSize, mappingInfo.fileOffset);
    }

    public static boolean canLoadLibraryInMemory(String str) {
        return !HOLDOUT_LIBRARIES.contains(str);
    }

    private void ensureMappingsRegistered() {
        if (BreakpadManager.initialized) {
            Map map = sPendingMappings;
            if (!map.isEmpty()) {
                ArrayList arrayList = new ArrayList(map.size());
                synchronized (map) {
                    Iterator A0D = AnonymousClass000.A0D(map);
                    while (A0D.hasNext()) {
                        arrayList.add((MappingInfo) AnonymousClass000.A0E(A0D).getValue());
                        A0D.remove();
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    MappingInfo mappingInfo = (MappingInfo) it.next();
                    String str = mappingInfo.name;
                    byte[] bArr = mappingInfo.buildId;
                    BreakpadManager.addMappingInfo(str, bArr, bArr.length, mappingInfo.startAddress, mappingInfo.mappingSize, mappingInfo.fileOffset);
                }
            }
        }
    }

    public static File getCompressedSoFileOrNull(File file, String str) {
        String[] strArr;
        for (String str2 : COMPRESSION_EXTENSIONS) {
            File A02 = AnonymousClass000.A02(file, AnonymousClass000.A06(str, str2, AnonymousClass000.A09()));
            if (!A02.exists()) {
                File A022 = AnonymousClass000.A02(file, AnonymousClass000.A06(SUPERPACK_EXTENSION, str2, AnonymousClass000.A0A(str)));
                if (A022.exists()) {
                    return A022;
                }
            } else {
                return A02;
            }
        }
        return null;
    }

    public static SuperpackFileLoader getInstance() {
        if (sInstance == null) {
            synchronized (SuperpackFileLoader.class) {
                if (sInstance == null) {
                    sInstance = new SuperpackFileLoader();
                }
            }
        }
        return sInstance;
    }

    private String getLibHash(String str) {
        try {
            File file = new File(str);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    messageDigest.update(bArr, 0, read);
                } else {
                    String formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe("%32x", new BigInteger(1, messageDigest.digest()));
                    fileInputStream.close();
                    return formatStrLocaleSafe;
                }
            }
        } catch (IOException | SecurityException | NoSuchAlgorithmException e) {
            return e.toString();
        }
    }

    public static String getLibraryName(String str) {
        int lastIndexOf = str.lastIndexOf(File.separatorChar);
        int lastIndexOf2 = str.lastIndexOf(".so");
        int i = lastIndexOf + 1;
        if (lastIndexOf2 == -1) {
            return str.substring(i);
        }
        return str.substring(i, lastIndexOf2 + 3);
    }

    public static boolean isLibraryCompressed(String str) {
        return !str.endsWith(".so");
    }

    public static boolean isLoadingInMemorySupported() {
        if (!sLoadedSuperpack) {
            C02420Ay.A07(SUPERPACK_LIBNAME_FOR_SOLOADER);
        }
        return canLoadInMemoryNative();
    }

    public static boolean isSuperpackLib(String str) {
        return str.regionMatches(str.lastIndexOf(File.separatorChar) + 1, SUPERPACK_LIBNAME, 0, 19);
    }

    public static MappingInfo[] loadFd(String str, C07p c07p) {
        throw AnonymousClass000.A04("hasBackingFile");
    }

    private void loadLibrary(String str) {
        if (sLoadedSuperpack) {
            loadFile(str);
            ensureMappingsRegistered();
            return;
        }
        System.load(str);
        if (!isSuperpackLib(str)) {
            return;
        }
        sLoadedSuperpack = true;
    }

    public static void loadSigmux() {
        String[] strArr = SIGMUX_SOLOADER_LIB;
        for (String str : strArr) {
            String str2 = str;
            String A00 = AbstractC007102y.A00(str);
            if (A00 != null) {
                str2 = A00;
            }
            String mapLibraryName = System.mapLibraryName(str2);
            ReentrantReadWriteLock reentrantReadWriteLock = C02420Ay.A08;
            reentrantReadWriteLock.readLock().lock();
            try {
                if (C02420Ay.A0B != null) {
                    for (int i = 0; i < C02420Ay.A0B.length; i++) {
                        if (C02420Ay.A0B[i].A03(mapLibraryName) != null) {
                            AnonymousClass000.A0K(reentrantReadWriteLock);
                            C02420Ay.A07(str);
                            return;
                        }
                    }
                    continue;
                }
                AnonymousClass000.A0K(reentrantReadWriteLock);
            } catch (Throwable th) {
                AnonymousClass000.A0K(reentrantReadWriteLock);
                throw th;
            }
        }
        UnsatisfiedLinkError e = null;
        for (String str3 : strArr) {
            try {
                C02420Ay.A07(str3);
                return;
            } catch (UnsatisfiedLinkError e2) {
                e = e2;
            }
        }
        if (e != null) {
            C06S.A09(TAG, "Failed to load sigmux when loading superpack loader", e);
            throw e;
        }
    }

    public static Method tryGetLoaderMethod(String str, Class... clsArr) {
        try {
            return Class.forName(LOADER_CLASS).getMethod(str, clsArr);
        } catch (ClassNotFoundException e) {
            C06S.A09(TAG, "Could not find class com.facebook.superpack.SuperpackFileLoader", e);
            throw new RuntimeException("Could not find class com.facebook.superpack.SuperpackFileLoader", e);
        } catch (NoSuchMethodException e2) {
            StringBuilder A09 = AnonymousClass000.A09();
            A09.append("Could not find method ");
            A09.append(str);
            String A06 = AnonymousClass000.A06(" in ", LOADER_CLASS, A09);
            C06S.A09(TAG, A06, e2);
            throw new RuntimeException(A06, e2);
        }
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x0058: IF  (r5 I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) == (0 ??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:48:?, block:B:36:0x0058 */
    @Override // X.InterfaceC02400Aw
    public void load(String str, int i) {
        String str2;
        if (this.mNativeLoadRuntimeMethod == null) {
            if (this.mForceSystemLoad) {
                System.load(str);
                return;
            } else {
                loadLibrary(str);
                return;
            }
        }
        if ((i & 4) == 4) {
            str2 = this.mLocalLdLibraryPath;
        } else {
            str2 = this.mLocalLdLibraryPathNoZips;
        }
        try {
            try {
                try {
                    synchronized (this.mRuntime) {
                        try {
                            String str3 = (String) this.mNativeLoadRuntimeMethod.invoke(this.mRuntime, str, C02420Ay.class.getClassLoader(), str2);
                            if (str3 == null) {
                                return;
                            }
                            throw new UnsatisfiedLinkError(str3);
                        } catch (Throwable th) {
                            th = th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    throw th;
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e = e;
                    throw new RuntimeException(AnonymousClass000.A06("Error: Cannot load ", str, AnonymousClass000.A09()), e);
                }
            } finally {
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
            e = e2;
        } catch (Throwable th3) {
            throw th3;
        }
    }

    public SuperpackFileLoader() {
        String str;
        String join;
        Method A02 = C0B4.A02();
        this.mNativeLoadRuntimeMethod = A02;
        if (A02 != null) {
            str = C0B4.A01();
        } else {
            str = null;
        }
        this.mLocalLdLibraryPath = str;
        if (str == null) {
            join = null;
        } else {
            String[] split = str.split(":");
            ArrayList arrayList = new ArrayList(split.length);
            for (String str2 : split) {
                if (!str2.contains("!")) {
                    arrayList.add(str2);
                }
            }
            join = TextUtils.join(":", arrayList);
        }
        this.mLocalLdLibraryPathNoZips = join;
    }

    public static MappingInfo[] loadBytes(String str, AnonymousClass031 anonymousClass031) {
        try {
            ByteBuffer allocate = ByteBuffer.allocate((int) anonymousClass031.size());
            anonymousClass031.read(allocate);
            MappingInfo[] loadBytesNative = loadBytesNative(str, allocate.array());
            if (loadBytesNative != null) {
                addMappingsToBreakpad(loadBytesNative);
            }
            return loadBytesNative;
        } catch (IOException unused) {
            StringBuilder A09 = AnonymousClass000.A09();
            A09.append("Failed to load ");
            A09.append(str);
            throw new RuntimeException(AnonymousClass000.A07(": Could not read file", A09));
        }
    }

    public static void loadFile(String str) {
        try {
            loadFileNative(str);
        } catch (UnsatisfiedLinkError e) {
            if (!(!str.endsWith(".so"))) {
                System.load(str);
                return;
            }
            throw e;
        }
    }

    public void setForceSystemLoad(boolean z) {
        this.mForceSystemLoad = z;
    }

    public void loadBytes(String str, AnonymousClass031 anonymousClass031, int i) {
        MappingInfo[] loadBytes = loadBytes(str, anonymousClass031);
        if (this.mUnloadLibraries && loadBytes != null && loadBytes.length > 0) {
            SuperpackUnloader.registerLibraryForUnloading(str);
        }
        ensureMappingsRegistered();
    }
}
