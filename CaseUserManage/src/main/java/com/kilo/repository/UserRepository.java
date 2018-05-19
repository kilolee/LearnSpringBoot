package com.kilo.repository;

import com.kilo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Created by kilo on 2018/5/18.
 */
public interface UserRepository extends MongoRepository<UserEntity, String> {

    Page<UserEntity> findAll(Pageable pageable);

    UserEntity findUserById(String id);

    UserEntity findUserByUserNameOrEmail(String userName, String email);

    UserEntity findByUserName(String userName);

    UserEntity findByEmail(String email);

    Long deleteUserById(String s);
}
