package com.utils;
import com.alibaba.fastjson.JSONObject;

public class JsonUtils {
    public static long get( JSONObject jsonObject, String key, long defaultValue) {
        try {
            return (long) jsonObject.get(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static int get( JSONObject jsonObject, String key, int defaultValue) {
        try {
            return (int) jsonObject.get(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static String get( JSONObject jsonObject, String key, String defaultValue) {
        try {
            return (String) jsonObject.get(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean get( JSONObject jsonObject, String key, boolean defaultValue) {
        try {
            return (boolean) jsonObject.get(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
