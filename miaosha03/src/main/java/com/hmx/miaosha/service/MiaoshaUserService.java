package com.hmx.miaosha.service;

import com.hmx.miaosha.domain.MiaoshaUser;
import com.hmx.miaosha.exception.GlobalException;
import com.hmx.miaosha.mapper.MiaoshaUserMapper;
import com.hmx.miaosha.redis.MiaoshaUserKey;
import com.hmx.miaosha.redis.RedisService;
import com.hmx.miaosha.result.CodeMsg;
import com.hmx.miaosha.result.Result;
import com.hmx.miaosha.util.MD5Util;
import com.hmx.miaosha.util.UUIDUtil;
import com.hmx.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-05 20:42
 **/
@Service
public class MiaoshaUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private MiaoshaUserMapper miaoshaUserMapper;

    @Autowired
    private RedisService redisService;

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        if (user == null) {
            throw new GlobalException(CodeMsg.TOKEN_ERROR);
        }
        //延长token有效期
        addCookie(response,token,user);
        return user;
    }

    public Result<CodeMsg> login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            int i = 1;
            throw new GlobalException(CodeMsg.SERVER_ERROR) ;
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = miaoshaUserMapper.selectById(mobile);
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String dbSalt = user.getSalt();
        String pwd = MD5Util.formPassToDBPass(formPass, dbSalt);
        if (!StringUtils.equals(pwd, dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return Result.success(null);
    }

    //将token返回给浏览器并在redis中添加一份
    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        //生成token
        //设置redis中token的生存时间与cookie中的一致
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
