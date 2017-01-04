package com.like.longshaolib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.like.longshaolib.interfaces.CdAdapterWhichClick;

import java.util.ArrayList;
import java.util.List;

/**
 * 最基础的适配器
 * Created by Administrator on 2016/5/13.
 */
public abstract class CdBaseAdapter<T> extends BaseAdapter{

    protected List<T> list;
    protected Context context;
    protected CdAdapterWhichClick click;

    private LayoutInflater inflater;

    public CdBaseAdapter(Context context,List<T> list){
        this.context=context;
        this.list=list;
        if (this.list==null)
            this.list=new ArrayList<T>();
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 更新适配器数据集
     * @param list
     */
    public void updataList(List<T> list){
        this.list=list;
        this.notifyDataSetChanged();
    }

    /**
     * 加载视图
     * @param resource
     * @return
     */
    protected View initConvertView(int resource){
        return inflater.inflate(resource,null);
    }

    protected void setAdapterWhichClickListener(CdAdapterWhichClick click){
        this.click=click;
    }
}
