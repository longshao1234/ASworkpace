package com.like.longshao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.like.longshaolib.adapter.CdBaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 */
public class MyBaseAdapter extends CdBaseAdapter<String> {

    public MyBaseAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder mh = null;
        if (convertView == null) {
            convertView = initConvertView(R.layout.content_main);
            mh = new MyHolder();
            mh.textView = (TextView) convertView.findViewById(R.id.textview);
            convertView.setTag(mh);
        } else {
            mh = (MyHolder) convertView.getTag();
        }
        mh.textView.setText(list.get(position));
        return convertView;
    }

    public class MyHolder {
        private TextView textView;
    }
}
