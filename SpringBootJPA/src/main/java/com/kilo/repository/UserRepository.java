package com.kilo.repository;

import com.kilo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kilo on 2018/4/26.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByUserNameAndEmail(String userName, String email);

    @Override
    @Query("select u from User u")
    Page<User> findAll(Pageable pageable);

    Page<User> findByNickName(String nickName, Pageable pageable);

    @Transactional(timeout = 10)
    @Modifying
    @Query("update User set userName = ?1 where id =?2")
    int modifyById(String userName, Long id);

    @Transactional
    @Modifying
    @Query("delete from User where id =?1")
    void deleteById(Long id);

    @Query("select u from User u where u.email =?1")
    User findByEmail(String email);
}
