package com.like.longshaolib.adapter.rvhelper;

import android.view.View;

import com.like.longshaolib.adapter.CdBaseRecyleAdapter;

/**
 * Created by AllenCoder on 2016/8/03.
 *
 *
 * A convenience class to extend when you only want to CdOnItemClickListener for a subset
 * of all the SimpleClickListener. This implements all methods in the
 * {@link CdSimpleClickListener}
 */
public abstract   class CdOnItemClickListener extends CdSimpleClickListener {


    @Override
    public void onItemClick(CdBaseRecyleAdapter adapter, View view, int position) {
        SimpleOnItemClick(adapter,view,position);
    }

    @Override
    public void onItemLongClick(CdBaseRecyleAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(CdBaseRecyleAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(CdBaseRecyleAdapter adapter, View view, int position) {

    }
    public abstract void SimpleOnItemClick(CdBaseRecyleAdapter adapter, View view, int position);
}
