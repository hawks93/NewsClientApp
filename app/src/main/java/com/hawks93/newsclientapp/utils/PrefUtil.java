package com.hawks93.newsclientapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.renderscript.Sampler;

/**
 * 作者hawks93
 * 版本1.0
 * 创建时间
 */
public class PrefUtil {
    public static String name = "config";
    private static SharedPreferences mPref;
    private static int mode=Context.MODE_PRIVATE;

    public static boolean getBoolean(Context ctx, String key, boolean value) {
        mPref = ctx.getSharedPreferences(name,mode);
        return mPref.getBoolean(key, value);
    }

    public static void setBoolean(Context ctx, String key, boolean value) {
        mPref = ctx.getSharedPreferences(name, mode);
        mPref.edit().putBoolean(key, value).commit();
    }

}
