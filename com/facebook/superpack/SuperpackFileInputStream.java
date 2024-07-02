package com.facebook.superpack;

import X.C06S;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
/* loaded from: classes.dex */
public class SuperpackFileInputStream extends InputStream {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public Boolean mCloseWhenInputStreamIsClosed;
    public int mLength;
    public int mMarked;
    public int mOffset;
    public byte[] mOneByte;
    public final SuperpackFile mSuperpackFile;

    public static InputStream wrapAndClose(SuperpackFile superpackFile) {
        return new SuperpackFileInputStream(superpackFile, true);
    }

    @Override // java.io.InputStream
    public synchronized int available() {
        return this.mLength - this.mOffset;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        this.mMarked = this.mOffset;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        this.mOffset = this.mMarked;
    }

    @Override // java.io.InputStream
    public synchronized long skip(long j) {
        if (j < 0) {
            return 0L;
        }
        int i = this.mOffset;
        long j2 = i;
        int i2 = this.mLength;
        if (j2 + j > i2) {
            j = i2 - i;
        }
        this.mOffset = (int) (j2 + j);
        return j;
    }

    public static SuperpackFileInputStream createFromSingletonArchiveFile(File file, String str) {
        int i;
        if (str.equals("spo")) {
            i = Runtime.getRuntime().availableProcessors();
        } else {
            i = 1;
        }
        return createFromSingletonArchiveFile(file, str, i);
    }

    public static SuperpackFileInputStream createFromSingletonArchiveInputStream(InputStream inputStream, String str) {
        int i;
        if (str.equals("spo")) {
            i = Runtime.getRuntime().availableProcessors();
        } else {
            i = 1;
        }
        return createFromSingletonArchiveInputStream(inputStream, str, i);
    }

    public static int getDefaultThreadNum(String str) {
        if (str.equals("spo")) {
            return Runtime.getRuntime().availableProcessors();
        }
        return 1;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.mCloseWhenInputStreamIsClosed.booleanValue()) {
            this.mSuperpackFile.close();
        }
    }

    public SuperpackFileInputStream(SuperpackFile superpackFile) {
        int i;
        superpackFile.getClass();
        this.mSuperpackFile = superpackFile;
        this.mOffset = 0;
        synchronized (superpackFile) {
            if (superpackFile.mPtr != 0) {
                i = superpackFile.mLength;
            } else {
                throw new IllegalStateException();
            }
        }
        this.mLength = i;
        this.mMarked = 0;
        this.mOneByte = null;
        this.mCloseWhenInputStreamIsClosed = false;
    }

    public static SuperpackFileInputStream createFromSingletonArchive(SuperpackArchive superpackArchive, String str) {
        if (superpackArchive.hasNext()) {
            SuperpackFile next = superpackArchive.next();
            if (!superpackArchive.hasNext()) {
                return new SuperpackFileInputStream(next, true);
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    public SuperpackFileInputStream(SuperpackFile superpackFile, Boolean bool) {
        this(superpackFile);
        this.mCloseWhenInputStreamIsClosed = bool;
    }

    public static SuperpackFileInputStream createFromSingletonArchiveInputStream(InputStream inputStream, String str, int i) {
        long threadNumOption;
        inputStream.getClass();
        if (!str.matches("spo")) {
            threadNumOption = 0;
        } else {
            threadNumOption = SuperpackArchive.getThreadNumOption(i);
        }
        SuperpackArchive superpackArchive = new SuperpackArchive(SuperpackArchive.readNative(inputStream, str, threadNumOption), null);
        try {
            SuperpackFileInputStream createFromSingletonArchive = createFromSingletonArchive(superpackArchive, str);
            superpackArchive.close();
            return createFromSingletonArchive;
        } catch (Throwable th) {
            try {
                superpackArchive.close();
                throw th;
            } catch (Throwable th2) {
                Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                throw th;
            }
        }
    }

    public static SuperpackFileInputStream createFromSingletonArchiveFile(File file, String str, int i) {
        SuperpackArchive superpackArchive;
        file.getClass();
        try {
            try {
                superpackArchive = new SuperpackArchive(SuperpackArchive.readNative(file.getPath(), str, !str.matches("spo") ? 0L : SuperpackArchive.getThreadNumOption(i)), null);
            } catch (RuntimeException e) {
                C06S.A09("SuperpackArchive", "Failed to read superpack file, retrying.", e);
                FileInputStream fileInputStream = new FileInputStream(file);
                superpackArchive = new SuperpackArchive(SuperpackArchive.readNative(fileInputStream, str, 0L), null);
                fileInputStream.close();
            }
            SuperpackFileInputStream createFromSingletonArchive = createFromSingletonArchive(superpackArchive, str);
            superpackArchive.close();
            return createFromSingletonArchive;
        }
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) {
        int i3 = i2;
        synchronized (this) {
            if (bArr != null) {
                if (i >= 0 && i2 >= 0) {
                    int i4 = i2 + i;
                    int length = bArr.length;
                    if (i4 <= length) {
                        int i5 = this.mOffset;
                        int i6 = this.mLength;
                        if (i5 == i6) {
                            return -1;
                        }
                        if (i2 + i5 > i6) {
                            i3 = i6 - i5;
                        }
                        SuperpackFile superpackFile = this.mSuperpackFile;
                        synchronized (superpackFile) {
                            long j = superpackFile.mPtr;
                            if (j == 0) {
                                throw new IllegalStateException();
                            }
                            if (i5 >= 0 && i3 >= 0) {
                                if (i + i3 <= length) {
                                    if (i5 + i3 <= superpackFile.mLength) {
                                        SuperpackFile.readBytesNative(j, i5, i3, bArr, i);
                                    } else {
                                        throw new IndexOutOfBoundsException();
                                    }
                                } else {
                                    throw new IndexOutOfBoundsException();
                                }
                            } else {
                                throw new IndexOutOfBoundsException();
                            }
                        }
                        this.mOffset += i3;
                        return i3;
                    }
                }
                throw new IndexOutOfBoundsException();
            }
            throw new NullPointerException();
        }
    }

    @Override // java.io.InputStream
    public synchronized int read() {
        int i;
        byte[] bArr = this.mOneByte;
        if (bArr == null) {
            bArr = new byte[1];
            this.mOneByte = bArr;
        }
        int read = read(bArr);
        i = -1;
        if (read != -1) {
            if (read == 1) {
                i = this.mOneByte[0];
                if (i < 0) {
                    i += 256;
                }
            } else {
                throw new IllegalStateException("Unexpected number of bytes read");
            }
        }
        return i;
    }
}
