package com.like.longshao.activity;

import android.support.v4.app.Fragment;

import com.like.longshao.R;
import com.like.longshao.activity.basetheme.CdBaseThemeActivity;
import com.like.longshao.fragment.CdCollectFragment;
import com.like.longshao.fragment.CdHomeFragment;
import com.like.longshao.fragment.CdListFragment;
import com.like.longshao.fragment.CdSettingFragment;
import com.like.longshaolib.ui.navigation.CdNavigationBarLayout;
import com.like.longshaolib.utils.ConverUtils;
import com.like.longshaolib.utils.FragmentUtils;
import com.like.longshaolib.utils.PreferenceUtils;

/**
 * 底部导航栏样的主页
 */
public class CdNavHomeActivity extends CdBaseThemeActivity {

    private CdNavigationBarLayout _nav_layout;
    private int[] drawStr={R.drawable.home_shop,R.drawable.home_sort,R.drawable.home_cart,R.drawable.home_center};
    private int[] drawStrBlack={R.drawable.home_shop_black,R.drawable.home_sort_black,R.drawable.home_cart_black,R.drawable.home_center_black};
    private Fragment _currentFragment=null;
    private PreferenceUtils _preferenceUtils;

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_cd_nav_home,"主页",-1,MODE_HOME);
    }

    @Override
    protected void initView() {
        _nav_layout= (CdNavigationBarLayout) findViewById(R.id.nav_layout);
    }

    @Override
    protected void initData() {
        _preferenceUtils=new PreferenceUtils(this);
        int themeInt= ConverUtils.converToT(_preferenceUtils.getString("themeInt"),0);
        if (themeInt==0){
            _nav_layout.setData(getResources().getStringArray(R.array.nav_name),drawStr);
        }else{
            _nav_layout.setData(getResources().getStringArray(R.array.nav_name),drawStrBlack);
        }
        _nav_layout.setCurrentTab(0);
        _nav_layout.setOnTabChangeListener(new CdNavigationBarLayout.OnTabChangeListener() {
            @Override
            public void onTabChange(String tag) {
                if (tag.equals("home")){
                    _currentFragment=FragmentUtils.switchFragnent(getSupportFragmentManager(),R.id.frame_layout ,_currentFragment,CdHomeFragment.class,null,false);
                }else if(tag.equals("sort")){
                    _currentFragment=FragmentUtils.switchFragnent(getSupportFragmentManager(),R.id.frame_layout ,_currentFragment,CdListFragment.class,null,false);
                }else if(tag.equals("cart")){
                    _currentFragment=FragmentUtils.switchFragnent(getSupportFragmentManager(),R.id.frame_layout ,_currentFragment,CdCollectFragment.class,null,false);
                }else {
                    _currentFragment=FragmentUtils.switchFragnent(getSupportFragmentManager(),R.id.frame_layout ,_currentFragment,CdSettingFragment.class,null,false);
                }
            }
        });
        _currentFragment= FragmentUtils.switchFragnent(getSupportFragmentManager(),R.id.frame_layout ,_currentFragment,CdHomeFragment.class,null,false);
    }

    @Override
    protected boolean isLoadView() {
        return true;
    }
}
