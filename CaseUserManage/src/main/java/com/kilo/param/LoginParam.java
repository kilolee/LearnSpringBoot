package com.kilo.param;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * Created by kilo on 2018/5/19.
 */
public class LoginParam {

    @NotEmpty(message = "姓名不能为空")
    private String loginName;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能为空")
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
