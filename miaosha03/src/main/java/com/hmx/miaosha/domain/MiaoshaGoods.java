package com.hmx.miaosha.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MiaoshaGoods {
	private Long id;
	private Long goodsId;
	private Integer stockCount;
	private Double miaoshaPrice;
	private Date startDate;
	private Date endDate;
}
