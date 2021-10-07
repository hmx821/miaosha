package com.hmx.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-05 18:27
 **/
public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "hmxP@ssw0rd";

    /**
     * 用户输入的密码经过固定的salt MD5首次加密
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * 加密后的密码再次经过可变的salt MD5加密后存到数据库中
     * @param fromPass
     * @param salt
     * @return
     */
    public static String formPassToDBPass(String fromPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + fromPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * 双重MD5加密
     * @param input
     * @param saltDB
     * @return
     */
    public static String inputPassToDBPass(String input, String saltDB) {
        return formPassToDBPass(inputPassToFormPass(input),saltDB);
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("hmx"));
        System.out.println(formPassToDBPass("hmx", "hmxsfd"));
        System.out.println(inputPassToDBPass("P@ssw0rd","hmxP@ssw0rd"));
    }

}
