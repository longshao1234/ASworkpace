package com.like.longshao.net.interfase;

import com.like.longshao.models.Subject;
import com.like.longshaolib.models.CdBaseEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by longshao on 2016/10/19.
 */

public interface ICdDataService {

    @GET("top250")
    Observable<CdBaseEntity<List<Subject>>> getTopMovice(@Query("start") int start, @Query("count") int count);


}
