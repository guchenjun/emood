package com.hznu.emood;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.hznu.emood.model.Order;
import com.hznu.emood.model.OrderDTO;
import com.hznu.emood.service.GoodsService;
import com.hznu.emood.service.OrderService;
import com.hznu.emood.service.UserService;
import com.hznu.emood.util.JwtUtil;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.Date;

@EnableDubbo
@SpringBootApplication
public class EmoodOrderConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmoodOrderConsumerApplication.class, args);
    }

    @Reference
    private GoodsService goodsService;

    @Reference
    private OrderService orderService;

    @Reference
    private UserService userService;

    @Service
    @RocketMQMessageListener(topic = "order-topic", consumerGroup = "my-consumer_test-topic-1")
    public class OrderConsumer implements RocketMQListener<OrderDTO> {
        public void onMessage(OrderDTO orderDTO) {
            String userId = JwtUtil.getId(orderDTO.getToken());
            Order order = new Order();
            Long goodsId = orderDTO.getGoodsId();
            order.setUserId(Long.parseLong(userId));
            order.setGoodsId(goodsId);
            order.setUUID(orderDTO.getUUID());
            order.setAmount(goodsService.getPriceById(goodsId));
            order.setStatus(false);
            Long version = goodsService.getVersionById(goodsId);
            try {
                // 存入订单，状态false
                orderService.saveOrder(order, version);
                // 更新库存，可能version字段不一致导致失败
                int row = goodsService.updateGoodsStock(goodsId, version);
                // 更新库存成功下一步，更新订单为true
                if (row > 0) {
                    orderService.updateOrderStatus(orderDTO.getUUID());
                    userService.updateUserVip(userId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
