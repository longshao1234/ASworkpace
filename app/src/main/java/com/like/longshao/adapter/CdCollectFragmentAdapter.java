package com.like.longshao.adapter;

import android.content.Context;

import com.like.longshao.R;
import com.like.longshaolib.adapter.CdBaseRecyleAdapter;
import com.like.longshaolib.adapter.rvhelper.CdBaseViewHolder;

import java.util.List;

/**
 * 收藏下面的适配器
 * Created by longshao on 2016/10/24.
 */

public class CdCollectFragmentAdapter extends CdBaseRecyleAdapter<String> {

    public CdCollectFragmentAdapter(Context context, List<String> list) {
        super(context, R.layout.recycle_list_layout, list);
    }

    @Override
    protected void convert(CdBaseViewHolder holder, String item) {
        holder.setText(R.id.recycler_item_tv_id,item).addItemClickListener(R.id.recycler_item_tv_id).addItemClickListener(R.id.recycler_item_bt_id);
    }
}
