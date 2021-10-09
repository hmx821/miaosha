package com.hmx.miaosha.controller;

import com.hmx.miaosha.domain.MiaoshaOrder;
import com.hmx.miaosha.domain.MiaoshaUser;
import com.hmx.miaosha.domain.OrderInfo;
import com.hmx.miaosha.result.CodeMsg;
import com.hmx.miaosha.service.GoodsService;
import com.hmx.miaosha.service.MiaoshaService;
import com.hmx.miaosha.service.OrderService;
import com.hmx.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-09 10:14
 **/
@RequestMapping("/miaosha")
@Controller
public class MiaoshaController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String doMiaosha(Model model, MiaoshaUser user, @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return "login";
        }
        // 判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_fail";
        }
        // 判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEAT_MIAOSHA.getMsg());
            return "miaosha_fail";
        }
        // 减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }

}
