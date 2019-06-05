package com.hznu.emood.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hznu.emood.annotation.UserLoginToken;
import com.hznu.emood.model.*;
import com.hznu.emood.service.GoodsService;
import com.hznu.emood.service.OrderService;
import com.hznu.emood.service.UserService;
import com.hznu.emood.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Api(value = "订单模块")
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    @Qualifier(value = "rocketMQTemplate")
    private RocketMQTemplate rocketMQTemplate;

    @Reference
    private GoodsService goodsService;

    @Reference
    private OrderService orderService;

    @Reference
    private UserService userService;

    @UserLoginToken
    @ApiOperation(value = "订单请求", notes = "返回下单状态结果（code=200不一定下单成功，需要跳到结果查询页面）")
    @RequestMapping(value = "/order/goodsId/{goodsId}", method = RequestMethod.GET)
    public R confirmOrder(HttpServletRequest request, @PathVariable("goodsId") Long goodsId) {
        String token = request.getHeader("Authorization");
        String userId = JwtUtil.getId(token);
        boolean isVip = userService.getVipByUserId(Long.parseLong(userId));
        if (isVip) {
            return R.ok(201, "已经是VIP!无需重复购买");
        }
        Map<String, Object> data = new HashMap<>();
        UUID uuid = UUID.randomUUID();
        data.put("uuid", uuid);
        try {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setToken(token);
            orderDTO.setGoodsId(goodsId);
            orderDTO.setUUID(String.valueOf(uuid));
            rocketMQTemplate.convertAndSend("order-topic", orderDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(400, "下单失败");
        }
        return R.ok(200, "正在下单中", data);
    }

    @UserLoginToken
    @ApiOperation(value = "查询订单结果请求", notes = "查看订单成交结果")
    @RequestMapping(value = "/order/result/{uuid}", method = RequestMethod.GET)
    public R orderResult(@PathVariable("uuid") String uuid) {
        Boolean status = orderService.getStatusByUUID(uuid);
        if (status) {
            return R.ok(200, "订单成交");
        } else {
            return R.error(400, "订单未成交");
        }
    }
}
