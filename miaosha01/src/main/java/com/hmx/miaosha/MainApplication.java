package com.hmx.miaosha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-04 17:21
 **/
@SpringBootApplication
@MapperScan("com.hmx.miaosha.mapper")
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

}
