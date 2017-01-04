package com.like.longshaolib.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.like.longshaolib.R;

/**
 * 基础的碎片类
 * Created by Administrator on 2016/5/13.
 */
public abstract class CdBaseViewFragment extends Fragment implements Toolbar.OnMenuItemClickListener{
    /*子类必须要实现的方法*/
    protected abstract int getLayoutId();//绑定视图
    protected abstract void initView();//初始化视图
    protected abstract void initData();//初始化数据
    protected abstract boolean isLoadView();//是否要初始化布局 如果否，请在此方法里面写请求数据

    private View fragmentview;
    private Toast cdToast;
    private ViewStub _content_layout,_error_layout;
    private View _content_view,_error_view;//内容布局、错误布局
    private TextView _data_fail_tv,_data_empty_tv,_data_error_tv;

    protected String TAG;
    protected Toolbar _fragToolbar;

    public static final int MODE_BACK=0;//带返回的toolbar
    public static final int MODE_NONE=1;//没有toolbar
    public static final int MODE_HOME=2;//没有返回按钮的toolbar

    public static final int DATA_ERROR=0x100;//数据处理异常
    public static final int DATA_EMPTY=0x101;//数据为空
    public static final int DATA_FAILE=0x102;//数据加载失败
    public static final int DATA_SUCESS=0x103;//数据加载成功

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentview = inflater.inflate(R.layout.layout, container, false);
        _content_layout= (ViewStub) findViewById(R.id.content_layout);
        _content_layout.setLayoutResource(getLayoutId());
        _fragToolbar= (Toolbar) findViewById(R.id.toolbar);
        return fragmentview;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TAG = getActivity().getClass().getSimpleName();
        setHasOptionsMenu(true);
        if(isLoadView()){
            loadingContentView();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
    }

    /**
     * 隐藏输入法
     */
    protected void hideSoftInput() {
        InputMethodManager imeManager = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imeManager.isActive();
        imeManager.hideSoftInputFromWindow(getActivity().getWindow()
                        .getDecorView().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示输入法
     */
    protected void showSoftInput() {
        InputMethodManager imeManager = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imeManager.isActive();
        imeManager.hideSoftInputFromWindow(getActivity().getWindow()
                        .getDecorView().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        imeManager.showSoftInputFromInputMethod(getActivity().getWindow()
                        .getDecorView().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 发送提示消息
     *
     * @param message
     */
    protected void showToast(String message) {
        if (cdToast == null) {
            cdToast=Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT);
        }else {
            cdToast.setText(message);
            cdToast.setDuration(Toast.LENGTH_SHORT);
        }
        cdToast.show();
    }

    /**
     * 加载视图组件
     *
     * @param id
     * @return
     */
    protected View findViewById(int id) {
        return fragmentview.findViewById(id);
    }

    /**
     * 打印日志
     *
     * @param log
     */
    protected void log(String log) {
        Log.d(TAG, log);
    }

    /**
     * 设置toolbar
     * @param title
     * @param meunId
     * @param mode
     */
    public void setToolbar(String title,int meunId,int mode){
        if(mode!=MODE_NONE){
            _fragToolbar= (Toolbar) findViewById(R.id.toolbar);
            _fragToolbar.setVisibility(View.VISIBLE);
            ((AppCompatActivity)getActivity()).setSupportActionBar(_fragToolbar);
            if(title!=null) {
                _fragToolbar.setTitle(title);
            }
            if(_fragToolbar!=null){
                _fragToolbar.getMenu().clear();
                if(meunId>0){
                    _fragToolbar.inflateMenu(meunId);
                    _fragToolbar.setOnMenuItemClickListener(this);
                }
            }
        }else{
            _fragToolbar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    /**
     * 网络加载数据
     * @param state
     */
    protected void showLoadingView(int state){
        if(_error_view==null){
            _error_layout= (ViewStub) findViewById(R.id.error_layout);
            _error_view=_error_layout.inflate();
            _data_fail_tv= (TextView) findViewById(R.id.data_fail_tv);
            _data_empty_tv= (TextView)findViewById(R.id.data_empty_tv);
            _data_error_tv= (TextView)findViewById(R.id.data_error_tv);
        }
        _error_view.setVisibility(View.VISIBLE);
        switch (state){
            case DATA_EMPTY:
                _data_fail_tv.setVisibility(View.GONE);
                _data_error_tv.setVisibility(View.GONE);
                _data_empty_tv.setVisibility(View.VISIBLE);
                if(_content_view!=null) {
                    _content_view.setVisibility(View.GONE);
                }
                break;
            case DATA_FAILE:
                _data_error_tv.setVisibility(View.GONE);
                _data_empty_tv.setVisibility(View.GONE);
                _data_fail_tv.setVisibility(View.VISIBLE);
                if(_content_view!=null) {
                    _content_view.setVisibility(View.GONE);
                }
                break;
            case DATA_ERROR:
                _data_fail_tv.setVisibility(View.GONE);
                _data_empty_tv.setVisibility(View.GONE);
                _data_error_tv.setVisibility(View.VISIBLE);
                if(_content_view!=null) {
                    _content_view.setVisibility(View.GONE);
                }
                break;
            case DATA_SUCESS:
                _error_view.setVisibility(View.GONE);
                if(_content_view!=null) {
                    _content_view.setVisibility(View.VISIBLE);
                }else {
                    loadingContentView();
                }
                break;
            default:
                _error_view.setVisibility(View.GONE);
                break;
        }
        if(_data_fail_tv!=null){
            _data_fail_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reRequest();
                }
            });
        }
    }

    /**
     * 再次请求数据
     */
    protected void reRequest(){

    }

    /**
     * 加载主布局
     */
    protected void loadingContentView(){
        _content_view=_content_layout.inflate();
        initView();
        initData();
    }
}
