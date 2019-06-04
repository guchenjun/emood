package com.hznu.emood.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hznu.emood.mapper.OrderMapper;
import com.hznu.emood.model.Order;
import com.hznu.emood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public void saveOrder(Order order, Long version) {
        orderMapper.insertOrder(order, version);
    }

    @Override
    public void updateOrderStatus(String uuid) {
        orderMapper.updateOrderStatus(uuid);
    }

    @Override
    public boolean getStatusByUUID(String uuid) {
        return orderMapper.getStatusByUUID(uuid);
    }
}
