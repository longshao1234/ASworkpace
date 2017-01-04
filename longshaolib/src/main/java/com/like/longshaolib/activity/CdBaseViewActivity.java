package com.like.longshaolib.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.like.longshaolib.CdBaseApplication;
import com.like.longshaolib.R;
import com.like.longshaolib.utils.ActivityManagerUtils;

/**
 * 基础的activity视图
 * Created by longshao on 2016/5/13.
 */
public abstract class CdBaseViewActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener{

    /*子类必须要实现的方法*/
    protected abstract void bindView();//绑定视图
    protected abstract void initView();//初始化视图
    protected abstract void initData();//初始化数据
    protected abstract boolean isLoadView();//是否要初始化布局 如果否，请在此方法里面写请求数据
    protected abstract void createAfer();//初始花主题

    private Toast cdToast;
    private ViewStub _content_layout,_error_layout;
    private View _content_view,_error_view;//内容布局、错误布局
    private TextView _data_fail_tv,_data_empty_tv,_data_error_tv;

    protected String TAG;
    protected ActivityManagerUtils _activityUtils;
    protected Toolbar _toolbar;//导航栏
    protected TextView _toolbar_tv;//导航栏上的标题

    public static final int MODE_BACK=0;//带返回的toolbar
    public static final int MODE_NONE=1;//没有toolbar
    public static final int MODE_HOME=2;//没有返回按钮的toolbar

    public static final int DATA_ERROR=0x100;//数据处理异常
    public static final int DATA_EMPTY=0x101;//数据为空
    public static final int DATA_FAILE=0x102;//数据加载失败
    public static final int DATA_SUCESS=0x103;//数据加载成功

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        createAfer();
        //强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置隐藏默认标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置输入框模式
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN| WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        _activityUtils=((CdBaseApplication)getApplication()).get_activityUtils();
        _activityUtils.addActivity(this);
        bindView();
        if(isLoadView()){
            loadingContentView();
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(layoutResID,"",-1,MODE_BACK);
    }

    public void setContentView(int layoutResID,String title){
        setContentView(layoutResID,title,-1,MODE_BACK);
    }

    public void setContentView(int layoutResID,int mode){
        setContentView(layoutResID,"",-1,mode);
    }

    public void setContentView(int layoutResID,String title,int menuId){
        setContentView(layoutResID,title,menuId,MODE_BACK);
    }

    /**
     * 初始化布局
     * @param layoutResID 布局ID
     * @param title 布局标题
     * @param menuId 右侧菜单
     * @param mode 模式
     */
    public void setContentView(@LayoutRes int layoutResID,String title,int menuId,int mode){
        super.setContentView(R.layout.layout);
        _content_layout= (ViewStub) findViewById(R.id.content_layout);
        _content_layout.setLayoutResource(layoutResID);
        _toolbar=(Toolbar) findViewById(R.id.toolbar);
        initNav(title,menuId,mode);
    }

    /**
     * 初始化导航栏
     * @param title
     * @param menuId
     * @param mode
     */
    protected void initNav(String title,int menuId,int mode){
        if(mode!=MODE_NONE){
            _toolbar.setTitle("");
            _toolbar.setVisibility(View.VISIBLE);
            setSupportActionBar(_toolbar);
            _toolbar_tv= (TextView) findViewById(R.id.title_tv);
            if(mode==MODE_BACK){
                _toolbar.setNavigationIcon(R.drawable.back);
                _toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
            if(title!=null&&!"".equals(title)&&_toolbar_tv!=null){
                _toolbar_tv.setText(title);
            }

            if(_toolbar!=null){
                _toolbar.getMenu().clear();
                if(menuId>0){
                    _toolbar.inflateMenu(menuId);
                    _toolbar.setOnMenuItemClickListener(this);
                }
            }
        }else{
            _toolbar.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Activity被系统杀死时被调用.
     * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死.
     * 另外,当跳转到其他Activity或者按Home键回到主屏时该方法也会被调用,系统是为了保存当前View组件的状态.
     * 在onPause之前被调用.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * Activity被系统杀死后再重建时被调用.
     * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死,用户又启动该Activity.
     * 这两种情况下onRestoreInstanceState都会被调用,在onStart之后.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancleToast();
    }

    /**
     * 发送提示消息
     *
     * @param message
     */
    protected void showToast(String message) {
        if (cdToast == null) {
            cdToast=Toast.makeText(this,message,Toast.LENGTH_SHORT);
        }else {
            cdToast.setText(message);
            cdToast.setDuration(Toast.LENGTH_SHORT);
        }
        cdToast.show();
    }

    /**
     * 取消Toast提示
     */
    protected void cancleToast(){
        if(cdToast!=null){
            cdToast.cancel();
        }
    }

    /**
     * 当activity触屏事件时，隐藏输入法
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hideSoftInput();
        return super.onTouchEvent(event);
    }

    /**
     * 隐藏输入法
     */
    protected void hideSoftInput() {
        InputMethodManager imeManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imeManager.isActive();
        imeManager.hideSoftInputFromWindow(getWindow().getDecorView()
                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示输入法
     */
    protected void showSoftInput() {
        InputMethodManager imeManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imeManager.isActive();
        imeManager.hideSoftInputFromWindow(getWindow().getDecorView()
                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        imeManager.showSoftInputFromInputMethod(getWindow().getDecorView()
                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 打印日志
     *
     * @param log
     */
    protected void log(String log) {
        Log.d(TAG, log);
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
