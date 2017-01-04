package com.like.longshao;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.like.longshao.adapter.CdToolBarAdapter;
import com.like.longshao.views.CdScrollView;

import java.util.ArrayList;
import java.util.List;

public class ToolBarActivity extends AppCompatActivity {

    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
//        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("返回");
//        toolbar.setNavigationIcon(R.mipmap.back);
//        toolbar.setLogo(R.mipmap.back);
//        setSupportActionBar(toolbar);

        CdScrollView conview= (CdScrollView) findViewById(R.id.conview);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            conview.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        }
        listview= (ListView) findViewById(R.id.listview);
        List<String> list=new ArrayList<String>();
        for (int i=0;i<20;i++){
            list.add("逗逼婷婷"+i);
        }
        listview.setAdapter(new CdToolBarAdapter(this,list));

        AlertDialog dialog=new AlertDialog.Builder(this).create();
        dialog.setTitle("doubi");
        dialog.show();
    }

    public void next1(View view){
//        Toast.makeText(ToolBarActivity.this,"逗逼",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(ToolBarActivity.this,SecondActivity.class);
        startActivity(intent);
    }
}
