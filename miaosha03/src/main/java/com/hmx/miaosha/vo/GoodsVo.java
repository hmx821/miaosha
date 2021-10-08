package com.hmx.miaosha.vo;

import com.hmx.miaosha.domain.Goods;
import lombok.Data;

import java.util.Date;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-08 14:01
 **/
@Data
public class GoodsVo extends Goods {
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    private Double miaoshaPrice;
}
