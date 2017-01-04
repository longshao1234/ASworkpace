package com.like.longshaolib.net.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.like.longshaolib.interfaces.ProgressCancelListener;

/**
 * 实现对话框消失之后，取消http请求的消息
 * Created by longshao on 2016/6/20 0020.
 */

public class ProgressDialogHandler extends Handler{
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private ProgressDialog dialog;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener progressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener progressCancelListener, boolean cancelable){
        super();
        this.cancelable=cancelable;
        this.context=context;
        this.progressCancelListener=progressCancelListener;
    }

    /**
     * 显示对话框
     */
    private void showProgressDialog(){
        if (dialog==null) {
            dialog = new ProgressDialog(context);
            dialog.setTitle("加载中,请稍后...");
            dialog.setCancelable(cancelable);//设置返回键是否可以退出 true可以退出
            if (cancelable) {
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        progressCancelListener.onCancelProgress();
                    }
                });
            }
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }
    }

    /**
     * 关闭对话框
     */
    private void dismissProgressDialog(){
        if (dialog!=null){
            dialog.dismiss();
            dialog=null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case SHOW_PROGRESS_DIALOG:
                showProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}
