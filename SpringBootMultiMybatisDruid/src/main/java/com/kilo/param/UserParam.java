package com.kilo.param;

/**
 * user的查询条件参数类
 * 继承分页基础类
 * Created by kilo on 2018/5/1.
 */
public class UserParam extends PageParam {
    private String userName;
    private String userSex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}
