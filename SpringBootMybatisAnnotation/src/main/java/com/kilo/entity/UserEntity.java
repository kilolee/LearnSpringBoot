package com.kilo.entity;

import com.kilo.enums.UserSexEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by kilo on 2018/4/30.
 */
public class UserEntity implements Serializable {
    private Long id;
    private String userName;
    private String passWord;
    private UserSexEnum userSex;
    private String nickName;

    public UserEntity() {
    }

    public UserEntity(String userName, String passWord, UserSexEnum userSex) {
        this.userName = userName;
        this.passWord = passWord;
        this.userSex = userSex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public UserSexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSexEnum userSex) {
        this.userSex = userSex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
