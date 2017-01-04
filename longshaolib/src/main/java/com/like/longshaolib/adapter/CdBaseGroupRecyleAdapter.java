package com.like.longshaolib.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.like.longshaolib.adapter.rvhelper.CdBaseViewHolder;
import com.like.longshaolib.models.CdBaseGroupEntity;

import java.util.List;

/**
 * 分组的实现类适配器
 * Created by longshao on 2016/10/24.
 */

public abstract class CdBaseGroupRecyleAdapter<T extends CdBaseGroupEntity> extends CdBaseRecyleAdapter{

    private int _headLayoutRes;
    private static final int TYPE_HEAD=0x15123;//标题类型 正常的时候值为0
    protected abstract void convertHeader(CdBaseViewHolder holder, T item);//绑定分组标题数据数据
    protected abstract void convert(CdBaseViewHolder holder, T item);//绑定分组下的内容数据

    public CdBaseGroupRecyleAdapter(Context context, int headLayoutRes, int layoutRes, List<T> list) {
        super(context, layoutRes, list);
        _headLayoutRes=headLayoutRes;
    }

    @Override
    protected int getChildItemViewType(int position) {
        return ((CdBaseGroupEntity)_list.get(position)).isHeader()?TYPE_HEAD:0;
    }

    @Override
    protected CdBaseViewHolder onChildCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEAD)
            return new CdBaseViewHolder(_context,getItemView(_headLayoutRes,parent));
        return super.onChildCreateViewHolder(parent, viewType);
    }

    @Override
    protected void convert(CdBaseViewHolder holder, Object item) {
        int viewType=holder.getItemViewType();
        switch (viewType){
            case TYPE_HEAD:
                setFullSpan(holder);
                convertHeader(holder,(T)item);
                break;
            default:
                convert(holder,(T)item);
                break;
        }
    }
}
