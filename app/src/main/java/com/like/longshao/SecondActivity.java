package com.like.longshao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView recycler_id;
//    private SectionedRecyclerViewAdapter _adapter;
//    private CdRecyclerAdaper _readapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        recycler_id = (RecyclerView) findViewById(R.id.recycler_id);
        recycler_id.setLayoutManager(new LinearLayoutManager(this));

//        _adapter=new SectionedRecyclerViewAdapter();
//        List<String> _list=new ArrayList<String>();
//        for (int i=0;i<10;i++){
//            _list.add("item"+i);
//        }
//        _readapter=new CdRecyclerAdaper(_list,this);
//        _adapter.addSection(_readapter);
//        recycler_id.setAdapter(_adapter);
    }

    public void back1(View view){
        finish();
    }
}
