package com.like.longshao.activity.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.like.longshao.R;
import com.like.longshao.activity.CdHomeActivity;
import com.like.longshao.activity.CdNavHomeActivity;
import com.like.longshao.activity.basetheme.CdBaseThemeActivity;
import com.like.longshao.activity.guide.CdGuideActivity;
import com.like.longshaolib.utils.PreferenceUtils;

/**
 * 闪屏页
 */
public class CdSplashActivity extends CdBaseThemeActivity {

    private ImageView splash_img;
    private PreferenceUtils _preferenceUtil;

    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Intent intent=null;
            if(_preferenceUtil.getBoolean("isFirstIn")){
                if("Nav".equals(_preferenceUtil.getString("home"))){
                    intent=new Intent(CdSplashActivity.this,CdNavHomeActivity.class);
                }else {
                    intent=new Intent(CdSplashActivity.this,CdHomeActivity.class);
                }
            }else{
                intent=new Intent(CdSplashActivity.this, CdGuideActivity.class);
            }
            startActivity(intent);
            _activityUtils.finishActivity(CdSplashActivity.this);
            return false;
        }
    });

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_cd_splash,MODE_NONE);
    }

    @Override
    protected void initView() {
        splash_img= (ImageView) findViewById(R.id.splash_img);
    }

    @Override
    protected void initData() {
        _preferenceUtil=new PreferenceUtils(this);
        Glide.with(this).load("http://uploads.xuexila.com/allimg/1504/633-15042G43023.gif").asGif().error(R.mipmap.splash_error).into(splash_img);
        handler.sendMessageDelayed(handler.obtainMessage(),3000);
    }

    @Override
    protected boolean isLoadView() {
        return true;
    }
}
