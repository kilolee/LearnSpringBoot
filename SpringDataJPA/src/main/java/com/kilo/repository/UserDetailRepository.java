package com.kilo.repository;

import com.kilo.domain.UserDetail;
import com.kilo.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by kilo on 2018/4/27.
 */
public interface UserDetailRepository extends JpaRepository<UserDetail,Long> {

    UserDetail findByHobby(String hobby);

    @Query("select u.userName as userName,u.email as email,d.address as address,d.hobby as hobby " +
            "from User u,UserDetail d where u.id=d.userId and d.hobby = ?1")
    List<UserInfo> findUserInfo(String hobby);



}
