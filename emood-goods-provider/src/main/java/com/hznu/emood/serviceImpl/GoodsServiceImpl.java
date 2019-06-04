package com.hznu.emood.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hznu.emood.mapper.GoodsMapper;
import com.hznu.emood.model.Goods;
import com.hznu.emood.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public Goods listGoods() {
        return goodsMapper.listGoods();
    }

    @Override
    public BigDecimal getPriceById(Long goodsId) {
        return goodsMapper.getPriceById(goodsId);
    }

    @Override
    public Long getVersionById(Long goodsId) {
        return goodsMapper.getVersionById(goodsId);
    }

    @Override
    public int updateGoodsStock(Long goodsId, Long version) {
        return goodsMapper.updateGoodsStock(goodsId, version);
    }
}
