package com.hznu.emood.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hznu.emood.model.R;
import com.hznu.emood.model.User;
import com.hznu.emood.service.UserService;
import com.hznu.emood.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Api(value = "登录、注册模块")
@RestController
@RequestMapping("/api")
public class UserController {

    @Reference
    private UserService userService;

    @ApiOperation(value = "登录请求", notes = "根据用户名和密码进行登录校验")
    @PostMapping("/login")
    public R login(@RequestBody User user) {
        User userForBase = userService.getUserByUsername(user.getUsername());
        if (userForBase == null) {
            return R.error(400, "登录失败，用户不存在");
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                return R.error(401, "登录失败，密码错误");
            } else {
                String token = JwtUtil.getToken(userForBase);
                Map<String, Object> map = new HashMap<>();
                map.put("id", userForBase.getId());
                map.put("username", userForBase.getUsername());
                map.put("isVip", userForBase.getVip());
                map.put("token", token);
                return R.ok(200, "ok", map);
            }
        }
    }


    @ApiOperation(value = "注册请求", notes = "根据用户名和密码进行注册")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query"),
//            @ApiImplicitParam(name = "password", value = "密码", paramType = "query")
//    })
    @PostMapping("/register")
    public R register(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return R.ok(200, user.getUsername() + " 注册成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(400, "注册失败!");
        }
    }

}
