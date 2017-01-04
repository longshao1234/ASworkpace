package com.like.longshao.activity.guide;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.like.longshao.R;
import com.like.longshao.activity.basetheme.CdBaseThemeActivity;
import com.like.longshao.adapter.CdGuideViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.like.longshao.R.id.guide_page_vp;

/**
 * 引导页
 */
public class CdGuideActivity extends CdBaseThemeActivity implements ViewPager.OnPageChangeListener{

    private ViewPager _guide_page_vp;
    private LinearLayout _circle_layout;

    private List<View> _guideView;
    private ImageView[] _guideImageView;
    private int _currentIndex=0;

    private CdGuideViewPageAdapter _adapter;

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_cd_guide,MODE_NONE);
    }

    @Override
    protected void initView() {
        _guide_page_vp= (ViewPager) findViewById(guide_page_vp);
        _circle_layout= (LinearLayout) findViewById(R.id.circle_layout);
    }

    @Override
    protected void initData() {
        _guideView=new ArrayList<View>();
        LayoutInflater inflater=LayoutInflater.from(this);
        _guideView.add(inflater.inflate(R.layout.guide_one_layout,null,false));
        _guideView.add(inflater.inflate(R.layout.guide_two_layout,null,false));
        _guideView.add(inflater.inflate(R.layout.guide_three_layout,null,false));

        _guideImageView=new ImageView[_guideView.size()];
        for (int i=0;i<_guideView.size();i++){
            _guideImageView[i]= (ImageView) _circle_layout.getChildAt(i);
            _guideImageView[i].setEnabled(false);
        }
        _guideImageView[_currentIndex].setEnabled(true);//初始化选中状态
        _adapter=new CdGuideViewPageAdapter(_guideView,this);
        _guide_page_vp.setAdapter(_adapter);
        _guide_page_vp.addOnPageChangeListener(this);
    }

    @Override
    protected boolean isLoadView() {
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position < 0 || position > _guideView.size() - 1
                || _currentIndex == position) {
            return;
        }
        _guideImageView[position].setEnabled(true);
        _guideImageView[_currentIndex].setEnabled(false);
        _currentIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
