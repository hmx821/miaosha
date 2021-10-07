package com.hmx.miaosha.redis;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-05 14:14
 **/
public class UserKey extends BasePrefix{

    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");

}
