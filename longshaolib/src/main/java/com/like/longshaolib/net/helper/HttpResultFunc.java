package com.like.longshaolib.net.helper;

import com.like.longshaolib.models.CdBaseEntity;

import rx.functions.Func1;

/**
 * 这里是分离数据的类
 * Created by longshao on 2016/6/20 0020.
 */

public class HttpResultFunc<T> implements Func1<CdBaseEntity<T>,T> {
    @Override
    public T call(CdBaseEntity<T> tMovieEntity) {
        if (tMovieEntity.getCount()!=40) {
          throw new ApiException(tMovieEntity.getCount());
        }
        return tMovieEntity.getSubjects();
    }
}
