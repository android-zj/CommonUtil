package com.zj.library;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Toast提示工具类
 * Created by ZhangJian on 2018/4/17
 */
public class ToastUtils {
    private final static int RELEASE_TIME = 5000;
    private static int sTime;
    private static ReleaseThread sThread;
    private static Toast sToast;

    @SuppressLint("ShowToast")
    public static void showToast(Context context, CharSequence text) {
        if (context == null) {
            return;
        }
        if (sToast == null) {
            sToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(text);
            sToast.setDuration(Toast.LENGTH_SHORT);
        }
        sToast.show();
        startReleaseThread();
    }

    @SuppressLint("ShowToast")
    public static void showToast(Context context, @StringRes int resId) {
        if (context == null) {
            return;
        }
        if (sToast == null) {
            sToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(resId);
            sToast.setDuration(Toast.LENGTH_SHORT);
        }
        sToast.show();
        startReleaseThread();
    }

    private static void startReleaseThread() {
        sTime = RELEASE_TIME;
        if (sThread == null) {
            sThread = new ReleaseThread();
            new Thread(sThread).start();
        }
    }

    private static class ReleaseThread implements Runnable {

        @Override
        public void run() {
            while (sTime > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sTime -= 1000;
            }
            sThread = null;
            sToast = null;
        }
    }
}
