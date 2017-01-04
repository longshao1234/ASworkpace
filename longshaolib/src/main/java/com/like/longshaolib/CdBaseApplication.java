package com.like.longshaolib;

import android.app.Application;

import com.like.longshaolib.utils.ActivityManagerUtils;

/**
 *基础的Application
 * 内容：初始化OKttp的网络请求（如果需要使用，请继承，再配上联网的权限）
 * Created by longshao on 2016/6/8 0008.
 */

public class CdBaseApplication extends Application{

    protected ActivityManagerUtils _activityUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        _activityUtils=ActivityManagerUtils.getAppManager();
    }

    public ActivityManagerUtils get_activityUtils() {
        return _activityUtils;
    }
}
