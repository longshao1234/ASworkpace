package com.like.longshao.net;

import com.like.longshao.models.Subject;
import com.like.longshao.net.interfase.ICdDataService;
import com.like.longshaolib.net.CdHttpManger;
import com.like.longshaolib.net.helper.HttpResultFunc;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * Created by longshao on 2016/10/19.
 */

public class CdHttpRequest extends CdHttpManger{

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static CdHttpRequest manger;
    private ICdDataService service;

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    /**
     * 单例初始化变量
     * @return
     */
    public static CdHttpRequest getInstance(){
        if (manger==null){
            synchronized (CdHttpManger.class){
                if (manger==null) {
                    manger = new CdHttpRequest();
                }
            }
        }
        return manger;
    }

    public CdHttpRequest() {
        super();
        service=retrofit.create(ICdDataService.class);
    }

    /**
     * 用于获取豆瓣电影Top250的数据
     * @param subscriber 由调用者传过来的观察者对象
     * @param start 起始位置
     * @param count 获取长度
     */
    public void getTopMovie(Subscriber<List<Subject>> subscriber, int start, int count){
        service.getTopMovice(start,count)
                .map(new HttpResultFunc<List<Subject>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
