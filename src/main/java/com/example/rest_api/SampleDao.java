package com.example.rest_api;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface SampleDao {
    @Select
    List<SampleEntity> selectAll();

    @Insert
    int insert(SampleEntity entity);
}
