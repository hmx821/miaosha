package com.hmx.miaosha.service;

import com.hmx.miaosha.domain.User;
import com.hmx.miaosha.mapper.UserMapper;
import com.hmx.miaosha.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-04 22:50
 **/
@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Result<User> findUserById(int id) {
        return Result.success(userMapper.selectById(id));
    }

    public Result<Boolean> tx() {
        User user = new User();
        user.setName("hmx1");
        userMapper.insert(user);
        return Result.success(true);
    }
}
