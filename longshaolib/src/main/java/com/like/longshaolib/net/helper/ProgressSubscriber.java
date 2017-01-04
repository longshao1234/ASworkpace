package com.like.longshaolib.net.helper;

import android.content.Context;

import com.like.longshaolib.interfaces.ProgressCancelListener;
import com.like.longshaolib.interfaces.SubscriberOnNextListener;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/20 0020.
 */

public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private SubscriberOnNextListener subscriberOnNextListener;
    private Context context;
    private ProgressDialogHandler handler;
    private boolean _isShowDialog=false;

    public ProgressSubscriber(Context context,SubscriberOnNextListener subscriberOnNextListener,boolean b){
        this.context=context;
        this.subscriberOnNextListener=subscriberOnNextListener;
        _isShowDialog=b;
        handler=new ProgressDialogHandler(context,this,_isShowDialog);
    }

    @Override
    public void onStart() {
        if(_isShowDialog) {
            showProgressDialog();
        }
    }

    @Override
    public void onCompleted() {
        if(_isShowDialog){
            dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        if(_isShowDialog){
            dismissProgressDialog();
        }
        subscriberOnNextListener.onError(e);
    }

    @Override
    public void onNext(T t) {
        subscriberOnNextListener.onNext(t);
    }

    @Override
    public void onCancelProgress() {
        if (this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    /**
     * 显示对话框
     */
    private void showProgressDialog(){
        if (handler==null){
            handler=new ProgressDialogHandler(context,this,_isShowDialog);
        }
        if (handler!=null){
            handler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    /**
     * 关闭对话框
     */
    private void dismissProgressDialog(){
        if (handler!=null){
            handler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            handler=null;
        }
    }
}
