package com.hmx.miaosha.service;

import com.hmx.miaosha.domain.MiaoshaOrder;
import com.hmx.miaosha.domain.MiaoshaUser;
import com.hmx.miaosha.domain.OrderInfo;
import com.hmx.miaosha.mapper.OrderMapper;
import com.hmx.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-09 11:04
 **/
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
        return orderMapper.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
    }

    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderMapper.insert(orderInfo);
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setUserId(user.getId());
        long id = orderMapper.insertMiaoshaOrder(miaoshaOrder);
        return orderInfo;
    }
}
