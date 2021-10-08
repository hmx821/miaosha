package com.hmx.miaosha.redis;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-05 14:10
 **/
public interface KeyPrefix {
    int expireSeconds();

    String getPrefix();
}
