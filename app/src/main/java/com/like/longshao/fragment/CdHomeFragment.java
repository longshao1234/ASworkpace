package com.like.longshao.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.like.longshao.R;
import com.like.longshao.models.Subject;
import com.like.longshao.net.CdHttpRequest;
import com.like.longshaolib.activity.CdBaseViewFragment;
import com.like.longshaolib.interfaces.SubscriberOnNextListener;
import com.like.longshaolib.net.helper.ProgressSubscriber;

import java.util.List;

/**
 * @author longshao
 * 主页fragment
 */
public class CdHomeFragment extends CdBaseViewFragment implements View.OnClickListener{

    private TextView _fragment_tv;
    private Button _fragment_bt;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cd_home;
    }

    @Override
    protected void initView() {
        _fragment_tv= (TextView) findViewById(R.id.fragment_tv);
        _fragment_bt= (Button) findViewById(R.id.fragment_bt);
        _fragment_bt.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setToolbar("",-1,MODE_NONE);
    }

    @Override
    protected boolean isLoadView() {
        reRequest();
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_bt:
                reRequest();
                break;
        }
    }

    @Override
    protected void reRequest() {
        super.reRequest();
        CdHttpRequest.getInstance().getTopMovie(new ProgressSubscriber<List<Subject>>(getActivity(), new SubscriberOnNextListener() {

            @Override
            public void onNext(Object o) {
                showLoadingView(DATA_SUCESS);
                List<Subject> _list= (List<Subject>) o;
                if(_list.size()==0){
                    showToast("ssss");
                    showLoadingView(DATA_EMPTY);
                }else{
                    _fragment_tv.setText(_list.get(0).getTitle());
                }
            }

            @Override
            public void onError(Throwable e) {
                showToast(e.getMessage());
                showLoadingView(DATA_ERROR);
            }
        },true),0,1);
    }
}
