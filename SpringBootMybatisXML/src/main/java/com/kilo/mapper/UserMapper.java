package com.kilo.mapper;

import com.kilo.entity.UserEntity;
import com.kilo.param.UserParam;

import java.util.List;

/**
 * Created by kilo on 2018/4/30.
 */
public interface UserMapper {

    List<UserEntity> getAll();

    List<UserEntity> getList(UserParam userParam);

    int getCount(UserParam userParam);

    UserEntity getOne(Long id);

    void insert(UserEntity user);

    int update(UserEntity user);

    int delete(Long id);
}
