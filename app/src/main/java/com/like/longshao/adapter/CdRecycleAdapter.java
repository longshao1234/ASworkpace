package com.like.longshao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.like.longshao.R;

import java.util.List;

/**
 * Created by longshao on 2016/10/21.
 */

public class CdRecycleAdapter extends RecyclerView.Adapter<CdRecycleAdapter.MyViewHolder>{

    private Context _context;
    private List<String> _list;
    private LayoutInflater _infalter;

    public CdRecycleAdapter(Context context, List<String> list){
        _context=context;
        _list=list;
        _infalter=LayoutInflater.from(_context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=_infalter.inflate(R.layout.recycle_list_layout,parent,false);
        MyViewHolder mh=new MyViewHolder(view);
        return mh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(_list.get(position));
    }

    @Override
    public int getItemCount() {
        return _list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.recycler_item_tv_id);
        }
    }
}
