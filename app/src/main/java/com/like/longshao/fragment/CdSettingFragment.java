package com.like.longshao.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.like.longshao.R;
import com.like.longshao.utils.ThemeUtils;
import com.like.longshaolib.activity.CdBaseViewFragment;
import com.like.longshaolib.ui.CdBaseDialog;
import com.like.longshaolib.utils.ActivityManagerUtils;
import com.like.longshaolib.utils.ConverUtils;
import com.like.longshaolib.utils.PreferenceUtils;

/**
 * 设置
 */
public class CdSettingFragment extends CdBaseViewFragment implements View.OnClickListener{

    private Spinner _spinner;
    private TextView _isShowGuide_tv;
    private Spinner _isChangeTheme_sp;

    private PreferenceUtils _preferenceUtil;
    private String[] _homeValueStr;
    private CdBaseDialog _dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cd_setting;
    }

    @Override
    protected void initView() {
        _spinner= (Spinner) findViewById(R.id.spinner);
        _isShowGuide_tv= (TextView) findViewById(R.id.isShowGuide_tv);
        _isChangeTheme_sp= (Spinner) findViewById(R.id.isChangeTheme_sp);
        _isShowGuide_tv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        _preferenceUtil=new PreferenceUtils(getActivity());
        _homeValueStr=getResources().getStringArray(R.array.set_page_tag);

        if(_preferenceUtil.getString("home")==null||"".equals(_preferenceUtil.getString("home"))){
            _spinner.setSelection(0,true);
        }else {
            for (int i = 0; i < _homeValueStr.length; i++) {
                if (_preferenceUtil.getString("home").equals(_homeValueStr[i])) {
                    _spinner.setSelection(i, true);
                }
            }
        }

        //此处效果没有处理好 如果切换之后，用户点击了否是还原不到原来的内容
        _spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                _dialog=new CdBaseDialog.Builder(getActivity()).setTitle("切换").setMessage("是否立即重启？", new CdBaseDialog.OnLinearClickListener() {
                    @Override
                    public void onClick(int which) {

                    }
                }).setButton1("是", new CdBaseDialog.OnClickListener() {
                    @Override
                    public void onClick(Dialog dialog, int which) {
                        dialog.dismiss();
                        _preferenceUtil.setString("home",_homeValueStr[position]);
                        //重启
                        ActivityManagerUtils.getAppManager().finishAllActivity();
                        Intent i = getActivity().getBaseContext().getPackageManager().getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                }).setButton2("否", new CdBaseDialog.OnClickListener() {
                    @Override
                    public void onClick(Dialog dialog, int which) {
                        dialog.dismiss();
                        //setSpinnerItemSelectedByValue(_spinner,_spinnerStr);
                    }
                }).create();
                _dialog.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        boolean isFirstIn =_preferenceUtil.getBoolean("isFirstIn");
        if(isFirstIn){
            _isShowGuide_tv.setText("不展示");
        }else{
            _isShowGuide_tv.setText("展示");
        }

        dealTheme();
    }

    @Override
    protected boolean isLoadView() {
        return true;
    }

    /**
     * 根据值, 设置spinner默认选中:
     * @param spinner
     * @param value
     */
    private void setSpinnerItemSelectedByValue(Spinner spinner,String value){
        SpinnerAdapter apsAdapter= spinner.getAdapter(); //得到SpinnerAdapter对象
        int k= apsAdapter.getCount();
        for(int i=0;i<k;i++){
            if(value.equals(apsAdapter.getItem(i).toString())){
                spinner.setSelection(i,true);// 默认选中项
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.isShowGuide_tv:
                _dialog=new CdBaseDialog.Builder(getActivity()).setTitle("切换").setMessage("是否立即重启？", new CdBaseDialog.OnLinearClickListener() {
                    @Override
                    public void onClick(int which) {

                    }
                }).setButton1("是", new CdBaseDialog.OnClickListener() {
                    @Override
                    public void onClick(Dialog dialog, int which) {
                        _preferenceUtil.setBoolean("isFirstIn",false);
                        dialog.dismiss();
                        //重启
                        ActivityManagerUtils.getAppManager().finishAllActivity();
                        Intent i = getActivity().getBaseContext().getPackageManager().getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                }).setButton2("否", new CdBaseDialog.OnClickListener() {
                    @Override
                    public void onClick(Dialog dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
                _dialog.show();
                break;
        }
    }

    /**
     * 处理主题切换
     */
    private void dealTheme(){
        int themeInt= ConverUtils.converToT(_preferenceUtil.getString("themeInt"),0);
        _isChangeTheme_sp.setSelection(themeInt,true);
        _isChangeTheme_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                _dialog=new CdBaseDialog.Builder(getActivity()).setTitle("切换").setMessage("是否立即重启？", new CdBaseDialog.OnLinearClickListener() {
                    @Override
                    public void onClick(int which) {

                    }
                }).setButton1("是", new CdBaseDialog.OnClickListener() {
                    @Override
                    public void onClick(Dialog dialog, int which) {
                        _preferenceUtil.setString("themeInt",position+"");
                        dialog.dismiss();
                        ThemeUtils.changeToTheme(getActivity(),position);
                    }
                }).setButton2("否", new CdBaseDialog.OnClickListener() {
                    @Override
                    public void onClick(Dialog dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
                _dialog.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
