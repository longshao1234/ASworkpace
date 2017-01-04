package com.like.longshao.models;

import com.like.longshaolib.models.CdBaseGroupEntity;

/**
 * Listfragment列表实体类
 * Created by longshao on 2016/11/16.
 */

public class CdListFragmentEntity extends CdBaseGroupEntity<CdListFiagmentItemEntity>{
    public CdListFragmentEntity(String header) {
        super(header);
    }

    public CdListFragmentEntity(CdListFiagmentItemEntity cdListFiagmentItemEntity) {
        super(cdListFiagmentItemEntity);
    }
}
