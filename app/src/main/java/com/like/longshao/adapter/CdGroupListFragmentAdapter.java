package com.like.longshao.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.like.longshao.R;
import com.like.longshao.models.GroupEntity;
import com.like.longshaolib.adapter.CdBaseGroupRecyleAdapter;
import com.like.longshaolib.adapter.rvhelper.CdBaseViewHolder;
import com.like.longshaolib.utils.DensityUtils;
import com.like.longshaolib.utils.ScreenUtils;

import java.util.List;

/**
 * 收藏列表适配器
 * Created by longshao on 2016/10/25.
 */

public class CdGroupListFragmentAdapter extends CdBaseGroupRecyleAdapter<GroupEntity> {

    public CdGroupListFragmentAdapter(Context context,List<GroupEntity> list) {
        super(context, R.layout.recycleview_group_title_layout, R.layout.recycle_grid_group_item_layout, list);
    }

    @Override
    protected void convertHeader(CdBaseViewHolder holder, GroupEntity item) {
        holder.setText(R.id.group_title_tv_id,item.getHeader()).addItemClickListener(R.id.group_title_tv_id);
    }

    @Override
    protected void convert(CdBaseViewHolder holder, GroupEntity item) {
        int width= ScreenUtils.getScreenWidth(_context);
        width=(width-DensityUtils.dpTopx(_context,12))/2;
        int height=width*148/100;
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(width,height);
        ImageView view=holder.cdFindViewById(R.id.group_img);
        view.setLayoutParams(params);
        holder.setImageUrl(R.id.group_img,item.getT().getImages().getMedium()).setText(R.id.group_tv,item.getT().getTitle());
    }
}
