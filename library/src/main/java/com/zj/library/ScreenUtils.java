package com.zj.library;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 屏幕宽高和屏幕背景
 */

public class ScreenUtils {
      static DisplayMetrics dm = new DisplayMetrics();

    //设置背景渐变色
    public static void darkenBackground(Activity activity, Float bgcolor) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgcolor;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity. getWindow().setAttributes(lp);
    }

    //获取屏幕宽高
    public static int getScreenWidth(Activity activity) {
        activity. getWindowManager().getDefaultDisplay().getMetrics(dm);
        int  width = dm.widthPixels;
        return width;
    }

    public static int getScreenHigh(Activity activity) {
        activity. getWindowManager().getDefaultDisplay().getMetrics(dm);
        int heigth = dm.heightPixels;
        return heigth;
    }

}
