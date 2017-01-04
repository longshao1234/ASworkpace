package com.like.longshao.models;

import com.like.longshaolib.models.CdBaseGroupEntity;

/**
 * Created by longshao on 2016/10/25.
 */

public class  GroupEntity extends CdBaseGroupEntity<Subject> {

    public GroupEntity(Subject subject) {
        super(subject);
    }

    public GroupEntity(String header) {
        super(header);
    }
}
