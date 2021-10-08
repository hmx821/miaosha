package com.hmx.miaosha.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-04 22:47
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(type= IdType.AUTO)
    private Integer id;
    private String name;
}
