package com.like.longshao.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.like.longshao.R;
import com.like.longshao.activity.basetheme.CdBaseThemeActivity;
import com.like.longshao.fragment.CdCollectFragment;
import com.like.longshao.fragment.CdHomeFragment;
import com.like.longshao.fragment.CdListFragment;
import com.like.longshao.fragment.CdSettingFragment;
import com.like.longshaolib.utils.FragmentUtils;

/**
 * 主页
 */
public class CdHomeActivity extends CdBaseThemeActivity {

    private DrawerLayout _drawerLayout;
    private ListView _listView;
    private ActionBarDrawerToggle _drawtoggle;
    private Fragment _currentFragment=null;

    private String[] _meunStr=null;
    /** 监听退出的事件 */
    private long exitTime = 0;
    @Override
    protected void bindView() {
        setContentView(R.layout.activity_cd_home,"主页");
    }

    @Override
    protected void initView() {
        _drawerLayout= (DrawerLayout) findViewById(R.id.home_draw);
        _listView= (ListView) findViewById(R.id.left_view);
    }

    @Override
    protected void initData() {
        _meunStr=getResources().getStringArray(R.array.nav_name);

        _drawerLayout.setScrimColor(Color.GRAY);//阴影
        _listView.setAdapter(new ArrayAdapter<String>(this,R.layout.list_home_item,_meunStr));
        _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                _drawerLayout.closeDrawer(_listView);
                if(_meunStr[position].equals("主页")){
                    _currentFragment=FragmentUtils.switchFragnent(getSupportFragmentManager(),R.id.content_frame ,_currentFragment,CdHomeFragment.class,null,false);
                }else if(_meunStr[position].equals("列表")){
                    _currentFragment=FragmentUtils.switchFragnent(getSupportFragmentManager(),R.id.content_frame ,_currentFragment,CdListFragment.class,null,false);
                }else if(_meunStr[position].equals("收藏")){
                    _currentFragment=FragmentUtils.switchFragnent(getSupportFragmentManager(),R.id.content_frame ,_currentFragment,CdCollectFragment.class,null,false);
                }else {
                    _currentFragment=FragmentUtils.switchFragnent(getSupportFragmentManager(),R.id.content_frame ,_currentFragment,CdSettingFragment.class,null,false);
                }
            }
        });
        _listView.setItemChecked(0,true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        _drawtoggle=new ActionBarDrawerToggle(this,_drawerLayout,_toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                _toolbar.setNavigationIcon(R.drawable.back);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                _toolbar.setNavigationIcon(R.drawable.menuimg);
            }
        };
        _drawtoggle.syncState();
        _drawerLayout.addDrawerListener(_drawtoggle);
        _toolbar.setNavigationIcon(R.drawable.menuimg);
       // getSupportFragmentManager().beginTransaction().add(R.id.content_frame,new CdHomeFragment()).commit();
        _currentFragment=FragmentUtils.switchFragnent(getSupportFragmentManager(),R.id.content_frame ,_currentFragment,CdHomeFragment.class,null,false);
    }

    @Override
    protected boolean isLoadView() {
        return true;
    }

    /**
     * 退出系统
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showToast("再点击一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                _activityUtils.AppExit(CdHomeActivity.this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
