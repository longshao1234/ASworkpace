package com.like.longshao.activity.city;

import com.like.longshao.R;
import com.like.longshao.activity.basetheme.CdBaseThemeActivity;

/**
 * 城市选择
 * 定位+最近+热门城市+分组城市
 */
public class CdChooseCityActivity extends CdBaseThemeActivity {

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_cd_choose_city,"选择城市");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean isLoadView() {
        return true;
    }
}
