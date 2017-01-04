package com.like.longshaolib.ui.navigation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.like.longshaolib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longshao on 2016/10/10.
 */

public class CdNavigationBarLayout extends LinearLayout implements View.OnClickListener {
    private OnTabChangeListener mOnTabChangedListener;

    private int mState =  5;

    private final Button mStateButton1;
    private final Button mStateButton2;
    private final Button mStateButton3;
    private final Button mStateButton4;
    private List<Button> buttonList=new ArrayList<Button>();

    public CdNavigationBarLayout(Context context) {
        this(context, null);
    }

    public CdNavigationBarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CdNavigationBarLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context, R.layout.view_tab, this);
        mStateButton1 = (Button) findViewById(R.id.button_state1);
        mStateButton2 = (Button) findViewById(R.id.button_state2);
        mStateButton3 = (Button) findViewById(R.id.button_state3);
        mStateButton4 = (Button) findViewById(R.id.button_state4);

        mStateButton1.setOnClickListener(this);
        mStateButton2.setOnClickListener(this);
        mStateButton3.setOnClickListener(this);
        mStateButton4.setOnClickListener(this);
        buttonList.add(mStateButton1);
        buttonList.add(mStateButton2);
        buttonList.add(mStateButton3);
        buttonList.add(mStateButton4);
    }

    public void setOnTabChangeListener(OnTabChangeListener listener) {
        mOnTabChangedListener = listener;
    }

    public void setCurrentTab(int index) {
        switchState(index);
    }

    private void switchState(int state) {
        if (mState == state) {
            return;
        } // else continue

        mState = state;
        mStateButton1.setSelected(false);
        mStateButton2.setSelected(false);
        mStateButton3.setSelected(false);
        mStateButton4.setSelected(false);

        Object tag = null;

        switch (mState) {
            case 0:
                mStateButton1.setSelected(true);
                tag = mStateButton1.getTag();
                break;

            case 1:
                mStateButton2.setSelected(true);
                tag = mStateButton2.getTag();
                break;

            case 2:
                mStateButton3.setSelected(true);
                tag = mStateButton3.getTag();
                break;

            case 3:
                mStateButton4.setSelected(true);
                tag = mStateButton4.getTag();
                break;

            default:
                break;
        }

        if (mOnTabChangedListener != null) {
            if (tag != null && mOnTabChangedListener != null) {
                mOnTabChangedListener.onTabChange(tag.toString());
            } else {
                mOnTabChangedListener.onTabChange(null);
            }
        }
    }


    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button_state1){
            switchState(0);
        }else if(v.getId()==R.id.button_state2){
            switchState(1);
        }else if(v.getId()==R.id.button_state3){
            switchState(2);
        }else if(v.getId()==R.id.button_state4){
            switchState(3);
        }else{

        }
    }

    public static interface OnTabChangeListener {
        public void onTabChange(String tag);
    }

    /**
     *  初始化数据 为4个按钮
     *  @param textStr //文字内容
     *  @param drawStr //图片内容
     */
    public void setData(String[] textStr,int[] drawStr){
        if(textStr!=null){
            for (int i=0;i<textStr.length;i++){
                Button button=buttonList.get(i);
                button.setText(textStr[i]);
            }
        }
        if (drawStr!=null){
            for (int i=0;i<drawStr.length;i++){
                Button button=buttonList.get(i);
                Drawable drawable=getResources().getDrawable(drawStr[i]);
                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                button.setCompoundDrawables(null,drawable,null,null);
            }
        }
    }
}
