package com.hmx.miaosha.util;

import java.util.UUID;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-07 20:07
 **/
public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
