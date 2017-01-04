package com.like.longshao.utils;

import android.app.Activity;
import android.content.Intent;

import com.like.longshao.R;

/**
 * 样式的切换
 * Created by longshao on 2016/11/3.
 */

public class ThemeUtils {
    private static int themeInt=0;
    public final static int THEME_DEFAULT=0;
    public final static int THEHE_BLOCK=1;

    public static void changeToTheme(Activity activity,int theme){
        themeInt=theme;
        activity.finish();
        activity.startActivity(new Intent(activity,activity.getClass()));
    }

    public static void onActivityCreateSetTheme(Activity activity){
        switch (themeInt){
            case THEME_DEFAULT:
                activity.setTheme(R.style.longshao);
                break;
            case THEHE_BLOCK:
                activity.setTheme(R.style.longshao_black);
                break;
        }
    }
}
