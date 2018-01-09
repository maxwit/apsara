//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.dingdingdemo;

public class StringUtil {
    public StringUtil() {
    }

    public static String filterNull(String str) {
        return str == null ? "" : str.trim();
    }

    public static boolean stringEquals(String source, String target) {
        return isEmpty(source) && isEmpty(target) || !isEmpty(source) && !isEmpty(target) && source.equals(target);
    }

    public static boolean isEmpty(String str) {
        return filterNull(str).equals("");
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
