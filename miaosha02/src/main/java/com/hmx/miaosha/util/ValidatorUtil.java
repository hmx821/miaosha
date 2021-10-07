package com.hmx.miaosha.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-05 19:23
 **/
public class ValidatorUtil {

    /**
     * 正则表达式
     */
    public static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    /**
     * 校验手机号格式是否正确
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return false;
        }
        Matcher m = mobile_pattern.matcher(mobile);
        return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(isMobile("18936095619"));
    }

}
