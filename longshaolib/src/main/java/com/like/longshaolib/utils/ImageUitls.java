package com.like.longshaolib.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.like.longshaolib.R;

/**
 * Created by longshaoz on 2016/6/21 0021.
 */

public class ImageUitls {
    /**
     * 加载图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .fitCenter()
//                .centerCrop()
                .placeholder(R.drawable.loading)
//                .crossFade()//深入浅出
                .into(imageView);
    }

    /**
     * 清除正在加载的图片
     * @param view
     */
    public static void clearQuest(View view){
        Glide.clear(view);
    }

    /**
     * 清除缓存图片
     * @param activity
     */
    public static void clear(Activity activity){
        Glide.get(activity).clearDiskCache();
        Glide.get(activity).clearMemory();
    }
}
