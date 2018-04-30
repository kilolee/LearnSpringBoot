package com.kilo.mapper;

import com.kilo.entity.UserEntity;
import com.kilo.enums.UserSexEnum;
import com.kilo.param.UserParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 采用注解的方式
 * Created by kilo on 2018/4/30.
 */
public interface UserMapper {

    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    List<UserEntity> getAll();


    @SelectProvider(type = UserSql.class, method = "getList")
    List<UserEntity> getList(UserParam userParam);

    @SelectProvider(type = UserSql.class, method = "getCount")
    int getCount(UserParam userParam);


    @Select("SELECT * FROM users WHERE id=#{id}")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    UserEntity getOne(Long id);

    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES (#{userName},#{passWord},#{userSex})")
    void insert(UserEntity user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id=#{id}")
    int update(UserEntity user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    int delete(Long id);


}
