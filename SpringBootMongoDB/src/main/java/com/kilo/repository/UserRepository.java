package com.kilo.repository;

import com.kilo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 继承了MongoRepository,MongoRepository内置了很多方法可直接使用
 * Created by kilo on 2018/5/9.
 */
public interface UserRepository extends MongoRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);

    Page<UserEntity> findAll(Pageable val);


}
