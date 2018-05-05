package com.kilo.repository;

import com.kilo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kilo on 2018/5/4.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
