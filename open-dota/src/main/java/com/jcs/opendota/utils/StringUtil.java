package com.jcs.opendota.utils;

import java.util.UUID;

/**
 * @auther xukh
 * @date 2019/8/6 15:02
 */
public class StringUtil {

    private StringUtil() {
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isBlank(final CharSequence cs) {
        return isEmpty(cs);
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static String buildUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean contains(String str, String searchStr) {
        if (str != null && searchStr != null) {
            return str.indexOf(searchStr) >= 0;
        } else {
            return false;
        }
    }

    public static boolean contains(String str, char searchChar) {
        if (isEmpty(str)) {
            return false;
        } else {
            return str.indexOf(searchChar) >= 0;
        }
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }
}
