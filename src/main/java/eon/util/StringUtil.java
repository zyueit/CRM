package eon.util;

public class StringUtil {
    public static boolean isbank(String string) {
        return string != null && !"".equals(string.trim()) ? false : true;
    }
}
