package com.dc.lbx;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class Utils {

    @SuppressWarnings("deprecation")
    public static int getDeviceWidth(Context context) {
        WindowManager manager = (WindowManager) context .getSystemService(Context.WINDOW_SERVICE);
        return manager.getDefaultDisplay().getWidth();
    }
    @SuppressWarnings("deprecation")
    public static int getDeviceHeight(Context context) {
        WindowManager manager = (WindowManager) context .getSystemService(Context.WINDOW_SERVICE);
        return manager.getDefaultDisplay().getHeight();
    }
}
