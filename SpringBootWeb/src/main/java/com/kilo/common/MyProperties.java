package com.kilo.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 自定义配置类
 * Created by kilo on 2018/4/26.
 */
@Component
public class MyProperties {
    @Value("${com.kilo.title}")
    private String title;
    @Value("${com.kilo.description}")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
