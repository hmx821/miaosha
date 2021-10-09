package com.hmx.miaosha.service;

import com.hmx.miaosha.domain.MiaoshaUser;
import com.hmx.miaosha.domain.OrderInfo;
import com.hmx.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-09 11:11
 **/
@Service
public class MiaoshaService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        // 减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);

        // order_info miaosha_order
        return orderService.createOrder(user, goods);
    }
}
