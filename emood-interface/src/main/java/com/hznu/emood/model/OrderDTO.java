package com.hznu.emood.model;

import java.io.Serializable;

public class OrderDTO implements Serializable {

    private Long goodsId;

    private String token;

    private String UUID;

    public OrderDTO() {
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "goodsId=" + goodsId +
                ", token='" + token + '\'' +
                ", UUID='" + UUID + '\'' +
                '}';
    }
}
