package com.yao.tab.util;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * @author Yao
 * @date 2016/8/17 0012
 */
public class ResUtil {

    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    public static void init(Context ctx){
        sContext = ctx;
    }

    public static String getString(int resId) {
        return sContext.getResources().getString(resId);
    }

    public static String getString(int format, int resId) {
        return String.format(getString(format), getString(resId));
    }

    public static String getString(int format, String... str) {
        return String.format(getString(format), (Object[]) str);
    }

    public static String getString(String format, String... str) {
        return String.format(format, (Object[]) str);
    }

    public static String getString(String format, int resId) {
        return String.format(format, getString(resId));
    }

    public static int getColor(int resId) {
        return sContext.getResources().getColor(resId);
    }

    public static Drawable getDrawable(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return sContext.getResources().getDrawable(resId, null);
        } else {
            return sContext.getResources().getDrawable(resId);
        }
    }

    public static int getDimen(int resId) {
        return (int) sContext.getResources().getDimension(resId);
    }
}
