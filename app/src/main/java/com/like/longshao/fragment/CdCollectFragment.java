package com.like.longshao.fragment;

import com.like.longshao.R;
import com.like.longshao.models.Subject;
import com.like.longshao.net.CdHttpRequest;
import com.like.longshao.adapter.CdGroupListFragmentAdapter;
import com.like.longshao.models.GroupEntity;
import com.like.longshaolib.activity.CdBaseViewFragment;
import com.like.longshaolib.interfaces.SubscriberOnNextListener;
import com.like.longshaolib.net.helper.ProgressSubscriber;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏
 */
public class CdCollectFragment extends CdBaseViewFragment {

    private PullLoadMoreRecyclerView recycler_pull_id;
    private CdGroupListFragmentAdapter _groupAdapter;
    private List<GroupEntity> _groupList=new ArrayList<GroupEntity>();
    private int pageIndex=1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cd_collect;
    }

    @Override
    protected void initView() {
        _groupAdapter=new CdGroupListFragmentAdapter(getActivity(),_groupList);
        recycler_pull_id= (PullLoadMoreRecyclerView) findViewById(R.id.recycler_pull_id);
        recycler_pull_id.setGridLayout(2);
        recycler_pull_id.setAdapter(_groupAdapter);
        recycler_pull_id.setFooterViewText("正在加载中...");
        recycler_pull_id.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
             //   recycler_pull_id.setPullLoadMoreCompleted();
                pageIndex=0;
                _groupList.clear();
                recycler_pull_id.setPushRefreshEnable(true);
                getData();
            }

            @Override
            public void onLoadMore() {
               // recycler_pull_id.setPullLoadMoreCompleted();
                pageIndex+=1;
                getData();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean isLoadView() {
        reRequest();
        return false;
    }

    @Override
    protected void reRequest() {
        super.reRequest();
        CdHttpRequest.getInstance().getTopMovie(new ProgressSubscriber<List<Subject>>(getActivity(), new SubscriberOnNextListener() {

            @Override
            public void onNext(Object o) {
                showLoadingView(DATA_SUCESS);
                List<Subject> _list1= (List<Subject>) o;
                if(_list1.size()==0){
                    showToast("ssss");
                    showLoadingView(DATA_EMPTY);
                }else{
                    for (int i=0;i<_list1.size();i++){
                        if(i==0||i==10||i==20||i==30){
                            _groupList.add(new GroupEntity(_list1.get(i).getTitle()));
                        }else {
                            _groupList.add(new GroupEntity(_list1.get(i)));
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                showToast(e.getMessage());
                showLoadingView(DATA_ERROR);
            }
        },false),0,40);
    }

    private void getData(){
       final List<GroupEntity> _groupListChrled=new ArrayList<GroupEntity>();
        CdHttpRequest.getInstance().getTopMovie(new ProgressSubscriber<List<Subject>>(getActivity(), new SubscriberOnNextListener() {

            @Override
            public void onNext(Object o) {
                recycler_pull_id.setPullLoadMoreCompleted();
                showLoadingView(DATA_SUCESS);
                List<Subject> _list1= (List<Subject>) o;
                if(_list1.size()==0){
                    recycler_pull_id.setPushRefreshEnable(false);
                }else{
                    for (int i=0;i<_list1.size();i++){
                        if(i==0||i==10||i==20||i==30){
                            _groupListChrled.add(new GroupEntity(_list1.get(i).getTitle()));
                        }else {
                            _groupListChrled.add(new GroupEntity(_list1.get(i)));
                        }
                    }
                    _groupAdapter.addAll(_groupListChrled);
                }
            }

            @Override
            public void onError(Throwable e) {
                showToast(e.getMessage());
                recycler_pull_id.setPullLoadMoreCompleted();
                showLoadingView(DATA_ERROR);
            }
        },false),pageIndex*40,(pageIndex+1)*40);
    }
}
