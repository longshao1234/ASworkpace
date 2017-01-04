package com.like.longshao.adapter;

import android.content.Context;

import com.like.longshao.R;
import com.like.longshao.models.CdListFiagmentItemEntity;
import com.like.longshao.models.CdListFragmentEntity;
import com.like.longshaolib.adapter.CdBaseGroupRecyleAdapter;
import com.like.longshaolib.adapter.rvhelper.CdBaseViewHolder;

import java.util.List;

/**
 * ListFragment 列表适配器
 * Created by longshao on 2016/11/16.
 */

public class CdListFragmentAdapter extends CdBaseGroupRecyleAdapter<CdListFragmentEntity>{

    public CdListFragmentAdapter(Context context, List<CdListFragmentEntity> list) {
        super(context, R.layout.recycle_list_fragmentlist_title_layout, R.layout.recycle_list_fragmentlist_content_layout, list);
    }

    @Override
    protected void convertHeader(CdBaseViewHolder holder, CdListFragmentEntity item) {
        holder.setText(R.id.recycler_fragmentlist_item_title_tv,item.getHeader());
    }

    @Override
    protected void convert(CdBaseViewHolder holder, CdListFragmentEntity item) {
        holder.setText(R.id.recycler_fragmentlist_item_content_tv,((CdListFiagmentItemEntity)item.getT()).getContent());
    }
}
