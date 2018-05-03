package com.kilo.repository;

import com.kilo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by kilo on 2018/5/3.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByUserNameOrEmail(String userName, String email);

    List<User> findByNickName(String nickName);


}
