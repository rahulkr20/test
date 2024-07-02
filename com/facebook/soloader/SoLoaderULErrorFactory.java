package com.facebook.soloader;

import X.AnonymousClass000;
import android.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class SoLoaderULErrorFactory {
    public static boolean corruptedLibName(String str) {
        Matcher matcher = Pattern.compile("\\P{ASCII}+").matcher(str);
        if (matcher.find()) {
            Log.w("SoLoader", AnonymousClass000.A06("Library name is corrupted, contains non-ASCII characters ", matcher.group(), AnonymousClass000.A09()));
            return true;
        }
        return false;
    }

    public static SoLoaderULError create(String str, UnsatisfiedLinkError unsatisfiedLinkError) {
        SoLoaderULError soLoaderULError;
        if (unsatisfiedLinkError.getMessage() != null && unsatisfiedLinkError.getMessage().contains("ELF")) {
            soLoaderULError = new SoLoaderCorruptedLibFileError(str, unsatisfiedLinkError.toString());
        } else if (corruptedLibName(str)) {
            StringBuilder A09 = AnonymousClass000.A09();
            A09.append("corrupted lib name: ");
            soLoaderULError = new SoLoaderCorruptedLibNameError(str, AnonymousClass000.A07(unsatisfiedLinkError.toString(), A09));
        } else {
            soLoaderULError = new SoLoaderULError(str, unsatisfiedLinkError.toString());
        }
        soLoaderULError.initCause(unsatisfiedLinkError);
        return soLoaderULError;
    }
}
