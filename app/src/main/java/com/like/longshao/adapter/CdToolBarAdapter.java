package com.like.longshao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.like.longshao.R;

import java.util.List;

/**
 * Created by longshao on 2016/9/6.
 */
public class CdToolBarAdapter extends BaseAdapter{

    private List<String> list;
    private Context context;
    private LayoutInflater inflater;
    private PopupMenu menu;

    public CdToolBarAdapter(Context context,List<String> list){
        this.context=context;
        this.list=list;
        inflater=LayoutInflater.from(this.context);
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyHolder mh=null;
        if(convertView==null){
            mh=new MyHolder();
            convertView=inflater.inflate(R.layout.listview_toolbar_item,parent,false);
            mh.toolbar_lv_item_bt= (Button) convertView.findViewById(R.id.toolbar_lv_item_bt);
            mh.toolbar_lv_item_tv= (TextView) convertView.findViewById(R.id.toolbar_lv_item_tv);
            convertView.setTag(mh);
        }else{
            mh= (MyHolder) convertView.getTag();
        }
        mh.toolbar_lv_item_tv.setText(list.get(position));
        mh.toolbar_lv_item_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu=new PopupMenu(context,v);
                menu.inflate(R.menu.toolbar_lv_item);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.toolbar_edit:
                                Toast.makeText(context,"编辑"+position,Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.toolbar_delete:
                                Toast.makeText(context,"删除"+position,Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.toolbar_add:
                                Toast.makeText(context,"新增"+position,Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                menu.show();
            }
        });
        return convertView;
    }

    public class MyHolder{
        public TextView toolbar_lv_item_tv;
        public Button toolbar_lv_item_bt;
    }
}
