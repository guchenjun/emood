package com.hznu.emood.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {

    private Long id;

    private Long userId;

    private Long goodsId;

    private BigDecimal amount;

    private String UUID;

    private Boolean status;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String uuid) {
        this.UUID = uuid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", amount=" + amount +
                ", UUID='" + UUID + '\'' +
                ", status=" + status +
                '}';
    }
}
