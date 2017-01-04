package com.like.longshao.activity.basetheme;

import com.like.longshao.utils.ThemeUtils;
import com.like.longshaolib.activity.CdBaseViewActivity;

/**
 * 处理基础主题切换
 * Created by longshao on 2016/11/14.
 */

public class CdBaseThemeActivity extends CdBaseViewActivity{

    @Override
    protected void bindView() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean isLoadView() {
        return false;
    }

    @Override
    protected void createAfer() {
        ThemeUtils.onActivityCreateSetTheme(this);
    }
}
