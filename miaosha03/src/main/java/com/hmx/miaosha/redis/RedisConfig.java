package com.hmx.miaosha.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-05 08:17
 **/
@Component
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisConfig {

    private String host;
    private int port = 6379;
    //秒
    private int timeout;
    // 使用几号库
    private int database = 0;
    private String password;
    private int maxActive = 10;
    private int maxIdle = 10;
    //秒
    private int maxWait = 3;

}
