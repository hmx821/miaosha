package com.hmx.miaosha.service;

import com.alibaba.druid.util.StringUtils;
import com.hmx.miaosha.domain.MiaoshaUser;
import com.hmx.miaosha.exception.GlobalException;
import com.hmx.miaosha.mapper.MiaoshaUserMapper;
import com.hmx.miaosha.result.CodeMsg;
import com.hmx.miaosha.result.Result;
import com.hmx.miaosha.util.MD5Util;
import com.hmx.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-05 20:42
 **/
@Service
public class MiaoshaUserService {

    @Autowired
    private MiaoshaUserMapper miaoshaUserMapper;

    public Result<CodeMsg> login(LoginVo loginVo) {
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
        return Result.success(null);
    }
}
