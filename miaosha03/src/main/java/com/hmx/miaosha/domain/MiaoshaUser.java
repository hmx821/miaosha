package com.hmx.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-05 20:39
 **/
@Data
public class MiaoshaUser {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
