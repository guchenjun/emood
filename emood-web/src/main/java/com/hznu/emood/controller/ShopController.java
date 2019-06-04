package com.hznu.emood.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hznu.emood.annotation.UserLoginToken;
import com.hznu.emood.model.Goods;
import com.hznu.emood.model.Message;
import com.hznu.emood.model.R;
import com.hznu.emood.service.GoodsService;
import com.hznu.emood.service.UserService;
import com.hznu.emood.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(value = "商店模块")
@RestController
@RequestMapping("/api/shop")
public class ShopController {

    @Reference
    GoodsService goodsService;

    @Reference
    UserService userService;

    @UserLoginToken
    @ApiOperation(value = "获取商品信息", notes = "返回商品信息")
    @GetMapping("/list")
    public R goodsList(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userId = JwtUtil.getId(token);
        boolean isVip = userService.getVipByUserId(Long.parseLong(userId));
        Goods goods = goodsService.listGoods();
        Map<String, Object> map = new HashMap<>();
        map.put("isVip", isVip);
        map.put("goods", goods);
        return R.ok(200, "ok", map);
    }
}
