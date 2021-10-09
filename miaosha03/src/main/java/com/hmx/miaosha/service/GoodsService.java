package com.hmx.miaosha.service;

import com.hmx.miaosha.mapper.GoodsMapper;
import com.hmx.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-08 13:54
 **/
@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<GoodsVo> listGoodsVo() {
        return goodsMapper.listGoodsVo();
    }


    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        goodsMapper.reduceStock(goods.getId());
    }
}
