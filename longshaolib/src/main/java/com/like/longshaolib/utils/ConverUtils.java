package com.like.longshaolib.utils;

/**
 * 变量转化类
 * Created by longshao on 2016/6/7 0007.
 */

public class ConverUtils {

    /**
     * 类型强转化
     * @param object
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T converToT(Object object, T defaultValue){
        if (object==null||"".equals(object)){
            return defaultValue;
        }
        Class<?> valueClass = defaultValue.getClass();
        if (valueClass==int.class||valueClass==Integer.class){
            return (T)(Integer)Integer.parseInt(object.toString());
        }
        if (valueClass==double.class||valueClass==Double.class){
            return (T)(Double)Double.parseDouble(object.toString());
        }
        if (valueClass==boolean.class||valueClass==Boolean.class){
            return (T)(Boolean)Boolean.parseBoolean(object.toString());
        }
        if (valueClass==String.class){
            return (T)object.toString();
        }
        return defaultValue;
    }
}
