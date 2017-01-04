package com.like.longshao.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.like.longshao.R;
import com.like.longshao.activity.CdHomeActivity;
import com.like.longshao.activity.CdNavHomeActivity;
import com.like.longshaolib.utils.PreferenceUtils;

import java.util.List;

/**
 * Created by longshao on 2016/11/4.
 * 引导页适配器
 */

public class CdGuideViewPageAdapter extends PagerAdapter{

    private List<View> _list;
    private Activity _activity;

    public CdGuideViewPageAdapter(List<View> list, Activity activity){
        _list=list;
        _activity=activity;
    }

    @Override
    public int getCount() {
        if(_list!=null){
            return _list.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(_list.get(position));
    }

    //初始化页面
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(_list.get(position));
        if(position==_list.size()-1){
            TextView startBton= (TextView) container.findViewById(R.id.guide_three_tv);
            startBton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceUtils preferenceUtils=new PreferenceUtils(_activity);
                    preferenceUtils.setBoolean("isFirstIn",true);
                    Intent intent=null;
                    if("Nav".equals(preferenceUtils.getString("home"))){
                        intent=new Intent(_activity,CdNavHomeActivity.class);
                    }else {
                        intent=new Intent(_activity,CdHomeActivity.class);
                    }
                    _activity.finish();
                    _activity.startActivity(intent);
                }
            });
        }
        return _list.get(position);
    }
}
