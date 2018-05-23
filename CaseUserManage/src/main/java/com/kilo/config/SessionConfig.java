package com.kilo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by kilo on 2018/5/22.
 */
@Configuration
//maxInactiveIntervalInSeconds: 设置 Session 失效时间，
// 使用 Redis Session 之后，原 Boot 的 server.session.timeout 属性不再生效
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=1000*60)
public class SessionConfig {
}
