package com.facebook.common.stringformat;

import X.AnonymousClass000;
import java.util.Formattable;
import java.util.Formatter;
import java.util.MissingFormatArgumentException;
import java.util.UnknownFormatConversionException;
/* loaded from: classes.dex */
public class StringFormatUtil {
    public static final int APPENDED_TO_OUTPUT = -3;
    public static final int FALLBACK_TO_SYSTEM = -1;
    public static final int INVALID_FORMAT_PERCENT = -101;
    public static final int NEXT_SEG_RESULT_FINISHED = -200;
    public static final int NEXT_SEG_RESULT_FINISHED_PERCENT = -201;
    public static final int NO_FORMATTING_REQUIRED = -2;
    public static final String NULL_STRING = "null";
    public static final Object[] SINGLE_ITEM_NULL_OBJECT_ARRAY = {null};
    public static final int VALID_FORMAT_PERCENT = -100;

    public static void appendFormatStrLocaleSafe(StringBuilder sb, String str, Object... objArr) {
        int doFormatArray = doFormatArray(null, str, objArr);
        if (doFormatArray == -1) {
            new Formatter(sb).format(null, str, objArr);
        } else if (doFormatArray == -2) {
            sb.append(str);
        } else {
            sb.ensureCapacity(doFormatArray);
            doFormatArray(sb, str, objArr);
        }
    }

    public static int appendIntTypeArg(StringBuilder sb, Object obj) {
        if (obj == null) {
            if (sb == null) {
                return 4;
            }
            sb.append(NULL_STRING);
        } else {
            if (obj instanceof Integer) {
                if (sb == null) {
                    return 11;
                }
            } else if (obj instanceof Short) {
                if (sb == null) {
                    return 6;
                }
            } else if (obj instanceof Byte) {
                if (sb == null) {
                    return 4;
                }
            } else if (obj instanceof Long) {
                if (sb == null) {
                    return 20;
                }
                sb.append(((Number) obj).longValue());
            } else if (sb == null) {
                return -1;
            } else {
                throw new AssertionError();
            }
            sb.append(((Number) obj).intValue());
        }
        return -3;
    }

    public static int doDryRun(String str) {
        return doFormatArgs(null, str, 0, null, null, null, null);
    }

    public static String doFallbackToSystem(String str, Object... objArr) {
        try {
            return String.format(null, str, objArr);
        } catch (MissingFormatArgumentException | UnknownFormatConversionException e) {
            StringBuilder A09 = AnonymousClass000.A09();
            A09.append(e.getMessage());
            throw new RuntimeException(AnonymousClass000.A06(": ", str, A09));
        }
    }

    public static int doFormatArray(StringBuilder sb, String str, Object... objArr) {
        boolean z;
        boolean z2 = true;
        int i = 0;
        boolean z3 = false;
        if (sb == null) {
            z3 = true;
        }
        if (objArr != null && objArr.length != 0) {
            z = false;
        } else {
            objArr = SINGLE_ITEM_NULL_OBJECT_ARRAY;
            z = true;
        }
        int length = objArr.length;
        int i2 = 0;
        int i3 = 0;
        boolean z4 = false;
        while (i < length) {
            int appendSegmentFormat = appendSegmentFormat(sb, str, i2, objArr[i], !z);
            if (appendSegmentFormat == -1) {
                return -1;
            }
            if (z3) {
                i3 += appendSegmentFormat;
            }
            i2 = getNextFormatSegmentIndex(str, i2);
            if (i2 == -200) {
                break;
            } else if (i2 == -201) {
                break;
            } else {
                i++;
                z4 = true;
            }
        }
        z2 = z4;
        if (z3 && !z2) {
            return -2;
        }
        if (i2 != -200 && i2 != -201) {
            return processRemainingString(sb, str, i2, i3, z3);
        }
        if (z3) {
            return i3;
        }
        return -3;
    }

    public static String fallbackToSystem(String str, int i, Object obj, Object obj2, Object obj3, Object obj4, Object[] objArr) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i == 4) {
                            objArr = new Object[]{obj, obj2, obj3, obj4};
                        }
                    } else {
                        objArr = new Object[]{obj, obj2, obj3};
                    }
                } else {
                    objArr = new Object[]{obj, obj2};
                }
            } else {
                objArr = new Object[]{obj};
            }
        } else {
            objArr = new Object[0];
        }
        return doFallbackToSystem(str, objArr);
    }

    public static String formatBytes(long j) {
        Float valueOf;
        String str;
        float f = (float) j;
        if (f < 1024.0f) {
            StringBuilder A09 = AnonymousClass000.A09();
            A09.append(j);
            return AnonymousClass000.A07("B", A09);
        }
        if (f < 1048576.0f) {
            valueOf = Float.valueOf(f / 1024.0f);
            str = "%.2fKB";
        } else if (f < 1.0737418E9f) {
            valueOf = Float.valueOf(f / 1048576.0f);
            str = "%.2fMB";
        } else {
            valueOf = Float.valueOf(f / 1.0737418E9f);
            str = "%.2fGB";
        }
        return formatStrLocaleSafe(str, valueOf);
    }

    public static String formatStrLocaleSafe(String str) {
        return formatStrLocaleSafeInner(str, 0, null, null, null, null, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0014, code lost:
        if (r2 == null) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int appendStringTypeArg(StringBuilder sb, Object obj) {
        String obj2;
        if (obj instanceof Formattable) {
            if (sb == null) {
                return -1;
            }
            throw new AssertionError();
        } else if (obj instanceof String) {
            obj2 = (String) obj;
        } else {
            if (obj != null) {
                obj2 = obj.toString();
            }
            obj2 = NULL_STRING;
            if (sb == null) {
                return obj2.length();
            }
            sb.append(obj2);
            return -3;
        }
    }

    public static int doDryRunInternal(String str, int i, Object obj, Object obj2, Object obj3, Object obj4, Object[] objArr) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return doFormatArray(null, str, objArr);
                        }
                        return doDryRun(str, obj, obj2, obj3, obj4);
                    }
                    return doDryRun(str, obj, obj2, obj3);
                }
                return doDryRun(str, obj, obj2);
            }
            return doDryRun(str, obj);
        }
        return doDryRun(str);
    }

    public static int validateFormatPercent(String str, int i) {
        int i2 = i + 1;
        if (str.length() > i2) {
            char charAt = str.charAt(i2);
            if (charAt == 's' || charAt == 'd' || charAt == '%') {
                return -100;
            }
            return INVALID_FORMAT_PERCENT;
        }
        return INVALID_FORMAT_PERCENT;
    }

    public static String formatStrLocaleSafeInner(String str, int i, Object obj, Object obj2, Object obj3, Object obj4, Object[] objArr) {
        int doDryRunInternal = doDryRunInternal(str, i, obj, obj2, obj3, obj4, objArr);
        if (doDryRunInternal == -1) {
            return fallbackToSystem(str, i, obj, obj2, obj3, obj4, objArr);
        }
        if (doDryRunInternal == -2) {
            return str;
        }
        StringBuilder sb = new StringBuilder(doDryRunInternal);
        if (i == -1) {
            doFormatArray(sb, str, objArr);
        } else {
            doFormatArgs(sb, str, i, obj, obj2, obj3, obj4);
        }
        return sb.toString();
    }

    public static int getNextFormatSegmentIndex(String str, int i) {
        int length = str.length();
        boolean z = false;
        while (i < length) {
            if (str.charAt(i) == '%' && validateFormatPercent(str, i) == -100) {
                int i2 = i + 1;
                if (str.charAt(i2) == '%') {
                    i = i2;
                    z = true;
                } else {
                    return i + 2;
                }
            }
            i++;
        }
        if (!z) {
            return NEXT_SEG_RESULT_FINISHED;
        }
        return NEXT_SEG_RESULT_FINISHED_PERCENT;
    }

    public static int processRemainingString(StringBuilder sb, String str, int i, int i2, boolean z) {
        int length = str.length();
        int i3 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt != '%' || (length > (i = i + 1) && str.charAt(i) == '%')) {
                if (sb == null) {
                    i3++;
                } else {
                    sb.append(charAt);
                }
                i++;
            } else if (z) {
                return -1;
            } else {
                throw new AssertionError();
            }
        }
        if (!z) {
            return -3;
        }
        return i2 + i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int appendSegmentFormat(StringBuilder sb, String str, int i, Object obj, boolean z) {
        int appendIntTypeArg;
        boolean z2;
        int length = str.length();
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == '%') {
                if (validateFormatPercent(str, i) == -100) {
                    int i3 = i + 1;
                    char charAt2 = str.charAt(i3);
                    if (z) {
                        if (charAt2 == 's') {
                            appendIntTypeArg = appendStringTypeArg(sb, obj);
                        } else if (charAt2 == 'd') {
                            appendIntTypeArg = appendIntTypeArg(sb, obj);
                        } else {
                            if (charAt2 != '%') {
                            }
                            if (sb != null) {
                            }
                            i = i3;
                            appendIntTypeArg = 1;
                            z2 = false;
                        }
                        z2 = true;
                        if (appendIntTypeArg == -1) {
                            return -1;
                        }
                    } else {
                        if (charAt2 != '%') {
                        }
                        if (sb != null) {
                            sb.append('%');
                        }
                        i = i3;
                        appendIntTypeArg = 1;
                        z2 = false;
                    }
                    if (sb == null) {
                        i2 += appendIntTypeArg;
                    }
                    if (z2) {
                        break;
                    }
                }
                return -1;
            } else if (sb == null) {
                i2++;
            } else {
                sb.append(charAt);
            }
            i++;
        }
        if (sb != null) {
            return -3;
        }
        return i2;
    }

    public static int appendSegmentFormatArgs(StringBuilder sb, String str, int i, Object obj, Object obj2, Object obj3, Object obj4, int i2) {
        if (i2 != -1) {
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 == 3) {
                            return appendSegmentFormat(sb, str, i, obj4, true);
                        }
                        throw new AssertionError();
                    }
                    return appendSegmentFormat(sb, str, i, obj3, true);
                }
                return appendSegmentFormat(sb, str, i, obj2, true);
            }
            return appendSegmentFormat(sb, str, i, obj, true);
        }
        return appendSegmentFormat(sb, str, i, null, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x002d, code lost:
        return -3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int doFormatArgs(StringBuilder sb, String str, int i, Object obj, Object obj2, Object obj3, Object obj4) {
        boolean z = sb == null;
        int i2 = i == 0 ? -1 : 0;
        int i3 = i2;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i3 >= i) {
                break;
            }
            int appendSegmentFormatArgs = appendSegmentFormatArgs(sb, str, i4, obj, obj2, obj3, obj4, i3);
            if (appendSegmentFormatArgs == -1) {
                return -1;
            }
            if (z) {
                i5 += appendSegmentFormatArgs;
            }
            i4 = getNextFormatSegmentIndex(str, i4);
            if (i3 == i2 && i4 == -200) {
                if (z) {
                    return -2;
                }
            } else if (i4 >= 0) {
                i3++;
            } else if (i4 == -200 || i4 == -201) {
                if (z) {
                    return i5;
                }
            }
        }
        return processRemainingString(sb, str, i4, i5, z);
    }

    public static int doDryRun(String str, Object obj, Object obj2) {
        return doFormatArgs(null, str, 2, obj, obj2, null, null);
    }

    public static String formatStrLocaleSafe(String str, Object obj, Object obj2) {
        return formatStrLocaleSafeInner(str, 2, obj, obj2, null, null, null);
    }

    public static int doDryRun(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return doFormatArgs(null, str, 4, obj, obj2, obj3, obj4);
    }

    public static String formatStrLocaleSafe(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return formatStrLocaleSafeInner(str, 4, obj, obj2, obj3, obj4, null);
    }

    public static int doDryRun(String str, Object obj, Object obj2, Object obj3) {
        return doFormatArgs(null, str, 3, obj, obj2, obj3, null);
    }

    public static String formatStrLocaleSafe(String str, Object obj, Object obj2, Object obj3) {
        return formatStrLocaleSafeInner(str, 3, obj, obj2, obj3, null, null);
    }

    public static int doDryRun(String str, Object[] objArr) {
        return doFormatArray(null, str, objArr);
    }

    public static String formatStrLocaleSafe(String str, Object... objArr) {
        return formatStrLocaleSafeInner(str, -1, null, null, null, null, objArr);
    }

    public static int doDryRun(String str, Object obj) {
        return doFormatArgs(null, str, 1, obj, null, null, null);
    }

    public static String formatStrLocaleSafe(String str, Object obj) {
        return formatStrLocaleSafeInner(str, 1, obj, null, null, null, null);
    }
}
