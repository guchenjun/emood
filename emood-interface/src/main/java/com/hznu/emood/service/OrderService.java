package com.hznu.emood.service;

import com.hznu.emood.model.Order;

public interface OrderService {
    void saveOrder(Order order, Long version);

    void updateOrderStatus(String uuid);

    Boolean getStatusByUUID(String uuid);
}
