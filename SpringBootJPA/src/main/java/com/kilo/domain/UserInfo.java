package com.kilo.domain;

/**
 * 定义一个结果集的接口，接收连表查询后的结果
 *
 * 在运行中 Spring 会给接口（UserInfo）自动生产一个代理类来接收返回的结果，
 * 代码中使用 getXX 的形式来获取。
 * Created by kilo on 2018/4/27.
 */
public interface UserInfo {
    String getUserName();

    String getEmail();

    String getAddress();

    String getHobby();

}
