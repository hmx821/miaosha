package com.hmx.miaosha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmx.miaosha.domain.Goods;
import com.hmx.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-08 13:56
 **/
public interface GoodsMapper extends BaseMapper<Goods> {

    @Select("select g.*,mg.stock_count,mg.start_date,mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    List<GoodsVo> listGoodsVo();
    @Select("select g.*,mg.stock_count,mg.start_date,mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);
}
