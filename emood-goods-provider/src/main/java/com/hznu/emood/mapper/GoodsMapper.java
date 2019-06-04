package com.hznu.emood.mapper;

import com.hznu.emood.model.Goods;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsMapper {

    Goods listGoods();

    BigDecimal getPriceById(@Param("goodsId") Long goodsId);

    Long getVersionById(@Param("goodsId") Long goodsId);

    int updateGoodsStock(@Param("goodsId") Long goodsId, @Param("version") Long version);
}
