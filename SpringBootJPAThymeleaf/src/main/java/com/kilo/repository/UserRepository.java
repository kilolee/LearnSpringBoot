package com.kilo.repository;

import com.kilo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Created by kilo on 2018/4/29.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(long id);

    User findByUserName(String userName);

    Long deleteById(long id);

    @Query("select u from User u")
    Page<User> findList(Pageable pageable);
}
