package com.facebook.soloader;
/* loaded from: classes.dex */
public class SoLoaderULError extends UnsatisfiedLinkError {
    public String mSoName;

    public SoLoaderULError(String str) {
        this.mSoName = str;
    }

    public String getSoName() {
        return this.mSoName;
    }

    public SoLoaderULError(String str, String str2) {
        super(str2);
        this.mSoName = str;
    }
}
