package com.like.longshaolib.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * 通知工具类
 * Created by longshao on 2016/6/21 0021.
 */

public class NotificationUtil {
    private NotificationManager manager=null;
    private Map<Integer,Notification> map=null;

    /**
     * 初始化
     * @param context
     */
    public NotificationUtil(Context context){
        manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        map=new HashMap<Integer, Notification>();
    }

    public void showNotification( ){

    }
}
