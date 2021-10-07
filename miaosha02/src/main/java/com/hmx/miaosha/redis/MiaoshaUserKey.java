package com.hmx.miaosha.redis;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-07 20:10
 **/
public class MiaoshaUserKey extends BasePrefix{

    public static final int TOKEN_EXPIRE = 3600 * 24;

    private MiaoshaUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MiaoshaUserKey token = new MiaoshaUserKey(TOKEN_EXPIRE, "token");
}
