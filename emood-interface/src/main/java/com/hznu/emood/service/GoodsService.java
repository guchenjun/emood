package com.hznu.emood.service;

import com.hznu.emood.model.Goods;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsService {

    Goods listGoods();

    BigDecimal getPriceById(Long goodsId);

    Long getVersionById(Long goodsId);

    int updateGoodsStock(Long goodsId, Long version);
}
