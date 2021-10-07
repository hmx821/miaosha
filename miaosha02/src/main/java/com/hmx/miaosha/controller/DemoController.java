package com.hmx.miaosha.controller;

import com.hmx.miaosha.domain.User;
import com.hmx.miaosha.redis.RedisService;
import com.hmx.miaosha.redis.UserKey;
import com.hmx.miaosha.result.CodeMsg;
import com.hmx.miaosha.result.Result;
import com.hmx.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-04 17:25
 **/
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        return Result.success(redisService.get(UserKey.getById, "2", User.class));
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user = new User();
        user.setId(2);
        user.setName("hmx2");
        return Result.success(redisService.set(UserKey.getById, "2", user));
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> tx() {
        return userService.tx();
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "HMX");
        return "hello";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public Result<String> hello() {
        return Result.success("hello springboot");
    }

    @ResponseBody
    @RequestMapping("/error")
    public Result<String> error() {
        return Result.fail(CodeMsg.SERVER_ERROR);
    }

}
