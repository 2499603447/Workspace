package com.chasedream.utils;

public class TextUtils {

    /**
     * check whether the value is valid(null or "")
     *
     * @param s passed value
     * @return whether the value is valid
     */
    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }
}
