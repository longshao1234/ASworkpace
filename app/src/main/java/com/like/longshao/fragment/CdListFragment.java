package com.like.longshao.fragment;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.like.longshao.R;
import com.like.longshao.activity.city.CdChooseCityActivity;
import com.like.longshao.adapter.CdListFragmentAdapter;
import com.like.longshao.models.CdListFiagmentItemEntity;
import com.like.longshao.models.CdListFragmentEntity;
import com.like.longshaolib.activity.CdBaseViewFragment;
import com.like.longshaolib.adapter.CdBaseRecyleAdapter;
import com.like.longshaolib.adapter.rvhelper.CdDividerItemDecoration;
import com.like.longshaolib.adapter.rvhelper.CdOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表
 */
public class CdListFragment extends CdBaseViewFragment {

    private RecyclerView _recycler_id;

    private CdListFragmentAdapter _adapter;
    private List<CdListFragmentEntity> _list;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cd_list;
    }

    @Override
    protected void initView() {
        _recycler_id= (RecyclerView) findViewById(R.id.recycler_id);
    }

    @Override
    protected void initData() {
        _list=new ArrayList<CdListFragmentEntity>();
        addData();

        _adapter=new CdListFragmentAdapter(getContext(),_list);
        _adapter.openLoadAnimation();
        _adapter.isFirstOnly(false);
        _adapter.setLayoutManager(_recycler_id,LinearLayoutManager.VERTICAL);
        _recycler_id.addItemDecoration(new CdDividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        _recycler_id.setAdapter(_adapter);

        _recycler_id.addOnItemTouchListener(new CdOnItemClickListener() {
            @Override
            public void SimpleOnItemClick(CdBaseRecyleAdapter adapter, View view, int position) {
                Intent intent=null;
                if (!_list.get(position).isHeader()) {
                    if (_list.get(position).getT().getContent().equals("城市地区选择")) {
                       intent=new Intent(getActivity(), CdChooseCityActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onItemChildClick(CdBaseRecyleAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    protected boolean isLoadView() {
        return true;
    }

    /**
     * 添加数据
     */
    private void addData(){
        CdListFragmentEntity titleEntity=new CdListFragmentEntity("城市选择");
        CdListFragmentEntity entity=new CdListFragmentEntity(new CdListFiagmentItemEntity("城市地区选择","分组及定位常用城市选择"));
        _list.add(titleEntity);
        _list.add(entity);
    }
}
